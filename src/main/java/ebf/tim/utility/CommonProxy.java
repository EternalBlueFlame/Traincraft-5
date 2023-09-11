package ebf.tim.utility;


import ebf.tim.api.SkinRegistry;
import ebf.tim.blocks.BlockDynamic;
import ebf.tim.entities.GenericRailTransport;
import ebf.tim.gui.GUISeatManager;
import ebf.tim.registry.TiMBlocks;
import ebf.tim.registry.TiMFluids;
import ebf.tim.registry.TiMItems;
import ebf.tim.registry.TiMOres;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * <h1>Common and Server proxy</h1>
 * defines some of the more important server only, and dual sided functionality that runs on the main thread of the mod.
 * @author Eternal Blue Flame
 */
public class CommonProxy implements IGuiHandler {


    public static EventManagerServer eventManagerServer = new EventManagerServer();
    public static Map<String, List<Recipe>> recipesInMods = new HashMap<>();
    public static String configDirectory;

    public static boolean pushabletrains=true;
    public static boolean realSpeed=false;
    public static boolean enableChunkloading = true;
    public static boolean doAluminumGeneration = true;
    public static boolean doSteelGeneration = true;
    public static boolean doCopperGeneration = true;
    /**Decides whether to use Traincraft or TiM assembly tables. */
    public static boolean isTraincraft = true;


    /**
     * <h2> Server GUI Redirect </h2>
     * Mostly a redirect between the event handler and the actual Container Handler
     *
     * the inventory manager that server uses for this menu is defined in
     * @see ClientProxy#getClientGuiElement(int, EntityPlayer, World, int, int, int)
     */
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        //Trains
        if (player != null && y!=0) {
            if (player.world.getEntityByID(ID) instanceof GenericRailTransport && !((GenericRailTransport) player.world.getEntityByID(ID)).hasCustomGUI()) {
                return new TransportSlotManager(player.inventory, (GenericRailTransport) player.world.getEntityByID(ID));
                //tile entities
            } else if (CommonUtil.getBlockAt(world,x,y,z) instanceof BlockDynamic){
                return ((BlockDynamic) CommonUtil.getBlockAt(world, x, y, z))
                        .getInventoryManager(player, world.getTileEntity(new BlockPos(x,y,z)));
            }
        }
        return null;
    }

    public void adminGui(String datacsv){}

    public void seatGUI(EntityPlayer player, GenericRailTransport transport) {    }

    public boolean isClient(){return false;}

    /**
     * <h2>Load config</h2>
     * this loads the config values that will only effect server.
     */
    public void loadConfig(FMLPreInitializationEvent event){

        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        config.addCustomCategoryComment("Debug (Common)", "Used on server and client.");
        SkinRegistry.forceSkinRegister = config.getBoolean("ForceSkinRegister", "Debug (Common)", false,
                "Forces skins to register even if the add-on for said TransportSkin is not available, doesn't cause instability just uses unnecessary ram.");

        config.addCustomCategoryComment("Debug (Common, IDE Only)", "Only runs from IDE instances.");
        SkinRegistry.debugSkinRegistration = config.getBoolean("DebugSkinRegister", "Debug (Common, IDE Only)",false,
                "Logs all TransportSkin registration events to debug console.");

        config.addCustomCategoryComment("Gameplay", "Settings that affect gameplay (ie. chunk loading, world generation, train speed");

        pushabletrains=config.getBoolean("PushableTrains", "Gameplay",true,"Enables/Disables the ability to push trains and rollingstock around.");
        realSpeed=config.getBoolean("UseRealSpeed","Gameplay", false,
                "Real speed moves the train on the assumption a block is a meter. Setting this to false will move the train on the assumption a block is 16 meters (more similar to Traincraft).");

        enableChunkloading=config.getBoolean("enableChunkloading","Gameplay", true,
                "Allows Trains and Rollingstock to load chunks in a 3x3 area around them, this allows them to function without players nearby, but at the cost of performance (effects server and singleplayer only)");

        doAluminumGeneration = config.getBoolean("doAluminumGeneration","Gameplay", true,
                "Decides whether or not to generate the aluminum ore in the overworld. Enabling will not cause retrogen (yet).");
        doSteelGeneration = config.getBoolean("doSteelGeneration","Gameplay", true,
                "Decides whether or not to generate the steel ore in the nether. Enabling will not cause retrogen (yet).");
        doCopperGeneration = config.getBoolean("doCopperGeneration","Gameplay", true,
                "Decides whether or not to generate the copper ore in the nether. Enabling will not cause retrogen (yet).");

        config.save();
        configDirectory = event.getModConfigurationDirectory().getAbsolutePath();



    }

    /**
     * <h2>load entity from UUID</h2>
     * This checks every entity in every dimension for one with the proper UUID,
     * this is very similar to the system used in 1.8+.
     * there is an additional optimization it will check the current dimension first,
     * NOTE: this is SERVER ONLY.
     *
     * We can't use a foreach loop, if we do it will very often throw a java.util.ConcurrentModificationException
     */
    @Nullable
    public static Entity getEntityFromUuid(UUID uuid, World dim) {
        int i;
        for (i=0; i< dim.loadedEntityList.size();i++) {
            //if it's an entity, not null, and has a matching UUID, then return it.
            if (dim.loadedEntityList.get(i) instanceof Entity &&
                    ((Entity) dim.loadedEntityList.get(i)).getUniqueID().equals(uuid)) {
                return (Entity) dim.loadedEntityList.get(i);
            }
        }

        //loop for dimensions, even ones from mods.
        for (int w = 0; w < dim.getMinecraftServer().getServer().worlds.length; w++) {
            if (dim.getMinecraftServer().getServer().worlds[w] != null && dim.provider.getDimension()!=w) {
                //if the server isn't null, loop for the entities loaded in that server
                for (i=0; i< dim.getMinecraftServer().getServer().worlds[w].loadedEntityList.size();i++) {
                    //if it's an entity, not null, and has a matching UUID, then return it.
                    if (Static.getServer().worlds[w].loadedEntityList.get(i) instanceof Entity &&
                            ((Entity) Static.getServer().worlds[w].loadedEntityList.get(i)).getUniqueID().equals(uuid)) {
                        return (Entity) Static.getServer().worlds[w].loadedEntityList.get(i);
                    }
                }
            }
        }
        return null;
    }


    /**
     * <h2>registry</h2>
     * placeholder code for the client registration.
     * @see ClientProxy#register) for actual use:
     */
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {return null;}


    public Object getRailTESR(){return null;}
    public Object getEntityRender(){return null;}
    public Object getNullRender(){return null;}

    /*
     * <h1> registration </h1>
     */



    /**
     * <h2>Server Register</h2>
     * Used for registering server only functions.
     * Also serves as a placeholder for the client function, which is actually used, so we don't get a missing function error.
     */
    public void register() {
        TiMFluids.registerFluids();
        TiMOres.registerOres();
        TiMItems.registerItems();
        TiMBlocks.registerBlocks();
    }

}
