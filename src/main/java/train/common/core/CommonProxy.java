package train.common.core;

import train.common.library.Info;

import ebf.tim.utility.CommonUtil;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ebf.tim.entities.EntitySeat;
import ebf.tim.gui.GUISeatManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import train.common.Traincraft;
import train.common.api.EntityRollingStock;
import train.common.api.Freight;
import train.common.api.LiquidTank;
import train.common.api.Tender;
import train.common.containers.*;
import train.common.core.handlers.ChunkEvents;
import train.common.core.handlers.WorldEvents;
import train.common.core.util.MP3Player;
import train.common.entity.digger.EntityRotativeDigger;
import train.common.entity.rollingStockOld.special.EntityJukeBoxCart;
import train.common.entity.rollingStockOld.special.EntityTracksBuilder;
import train.common.entity.zeppelin.AbstractZeppelin;
import train.common.inventory.*;
import train.common.library.GuiIDs;
import train.common.mtc.*;
import train.common.tile.*;
import train.common.tile.switchStand.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CommonProxy implements IGuiHandler {
    public static List<MP3Player> playerList = new ArrayList<MP3Player>();
    public static boolean debug = false;

    public static String configDirectory = "";
    public void throwAlphaException() {
        throw new IllegalStateException("You're trying to use a Traincraft alpha-version past its expiry date. Download a release-build at https://minecraft.curseforge.com/projects/traincraft.");
    }

    public boolean isClient(){
		return false;
	}
	public void setKeyBinding(String name, int value) {
    }

    public void registerPlayerScaler(){}

    /** Client: register entity renderer factories (must happen in preInit). */
    public void registerEntityRenderers(){}

    public void registerRenderInformation() {
    }

    public void registerEvents(FMLPreInitializationEvent event) {
        WorldEvents worldEvents = new WorldEvents();
        ChunkEvents chunkEvents = new ChunkEvents();

        registerEvent(worldEvents);
        registerEvent(chunkEvents);
        ForgeChunkManager.setForcedChunkLoadingCallback(Traincraft.instance, chunkEvents);

    }

    public void registerEvent(Object o) {
        FMLCommonHandler.instance().bus().register(o);
        MinecraftForge.EVENT_BUS.register(o);
    }

    public void registerTileEntities() {
        registerTile(TileCrafterTierI.class, "TileCrafterTierI");
        registerTile(TileCrafterTierII.class, "TileCrafterTierII");
        registerTile(TileCrafterTierIII.class, "TileCrafterTierIII");
        registerTile(TileTrainWbench.class, "TileTrainWbench");
        registerTile(TileEntityDistil.class, "Tile Distil");
        registerTile(TileEntityOpenHearthFurnace.class, "Tile OpenHearthFurnace");
        registerTile(TileStopper.class, "TileStopper");
        registerTile(TileEmbeddedStopper.class, "TileEmbeddedStopper");
        registerTile(TileAmericanStopper.class, "TileAmericanStopper");
        registerTile(TileSignal.class, "TileTrainSignal");
        registerTile(TileLantern.class, "tileLantern");
        registerTile(TileMFPBWigWag.class, "tileMFPBWigWag");
        registerTile(TileWaterWheel.class, "tileWaterWheel");
        registerTile(TileWindMill.class, "tileWindMill");
        registerTile(TileGeneratorDiesel.class, "tileGeneratorDiesel");
        registerTile(TileBook.class, "tileBook");
        registerTile(TileTCRailGag.class, "tileTCRailGag");
        registerTile(TileTCRail.class, "tileTCRail");
        registerTile(TileBridgePillar.class, "tileTCBridgePillar");
        registerTile(TileMetroMadridPole.class, "tileMetroMadridPole");

        /**SwitchStands*/
        registerTile(TileSwitchStand.class, "tileSwitchStand");

        registerTile(TileOWOSwitchStand.class, "tileowoswitchstand");
        registerTile(TileCircleSwitchStand.class, "tilecircleswitchstand");
        registerTile(TileMILWSwitchStand.class, "tileMILWSwitchStand");
        registerTile(TileAutoSwitchStand.class, "tileautoSwitchStand");
        registerTile(TileOWOYardSwitchStand.class, "tileowoYardSwitchStand");

        registerTile(TileoverheadWire.class, "tileoverheadwire");
        registerTile(TileoverheadWireDouble.class, "tileoverheadwiredouble");
        registerTile(TilesignalSpanish.class, "tilesignalspanish");
        registerTile(TilekSignal.class, "tileksignal");
        registerTile(TileSpeedSign.class, "tilespeedsign");

        if (Traincraft.hasComputerCraft()) {
            registerTile(TileInfoTransmitterSpeed.class, "tileInfoTransmitterSpeed");
            registerTile(TileInfoTransmitterMTC.class, "tileInfoTransmitterMTC");
            registerTile(TileInfoGrabberMTC.class, "tileInfoReceiverMTC");
            registerTile(TileInfoGrabberDestination.class, "tileInfoReceiverDestination");
            registerTile(TileATOTransmitterStopPoint.class, "tileATOTransmitterStopPoint");
            registerTile(TilePDMInstructionRadio.class, "tilePDMInstructionRadio");
        }

        //registerTile(TileFortyFootContainer.class, "tileFortyFootContainer");
    }

    public void registerComputerCraftPeripherals() throws ClassNotFoundException {
        //Register all CC required blocks (no-op when ComputerCraft is not installed)
        dan200.computercraft.api.ComputerCraftAPI.registerPeripheralProvider(BlockInfoTransmitterSpeed.instance);
        dan200.computercraft.api.ComputerCraftAPI.registerPeripheralProvider(BlockInfoGrabberMTC.instance);
        dan200.computercraft.api.ComputerCraftAPI.registerPeripheralProvider(BlockInfoTransmitterMTC.instance);
        dan200.computercraft.api.ComputerCraftAPI.registerPeripheralProvider(BlockATOTransmitterStopPoint.instance);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = CommonUtil.getTileEntity(world, x, y, z);
    Entity entity = null;
    EntityPlayer passenger = null;

    Entity riding = player.getRidingEntity();

    if (riding instanceof EntityRollingStock || riding instanceof AbstractZeppelin || riding instanceof EntityRotativeDigger) {
        entity = riding;
        if (!entity.getPassengers().isEmpty() && ebf.tim.utility.CommonUtil.rider(entity) instanceof EntityPlayer) {
            passenger = (EntityPlayer) ebf.tim.utility.CommonUtil.rider(entity);
        }
    }

    if (riding instanceof EntitySeat) {
        entity = ((EntitySeat) riding).parent;
        if (entity instanceof EntityRollingStock) {
            EntityRollingStock stock = (EntityRollingStock) entity;
            if (!stock.seats.isEmpty() && stock.seats.get(0).getPassenger() instanceof EntityPlayer) {
                passenger = (EntityPlayer) stock.seats.get(0).getPassenger();
            }
        }
    }

    Entity entity1 = null;
    if (y == -1) {
        entity1 = getEntity(world, x);
    }

                switch (ID) {
            case (GuiIDs.CRAFTER_TIER_I):
                return te instanceof TileCrafterTierI ? new ContainerTier(player.inventory, (TileCrafterTierI) te) : null;
            case (GuiIDs.CRAFTER_TIER_II):
                return te instanceof TileCrafterTierII ? new ContainerTier(player.inventory, (TileCrafterTierII) te) : null;
            case (GuiIDs.CRAFTER_TIER_III):
                return te instanceof TileCrafterTierIII ? new ContainerTier(player.inventory, (TileCrafterTierIII) te) : null;
            case (GuiIDs.DISTIL):
                return te instanceof TileEntityDistil ? new ContainerDistil(player.inventory, (TileEntityDistil) te) : null;
            case (GuiIDs.GENERATOR_DIESEL):
                return te instanceof TileGeneratorDiesel ? new ContainerGeneratorDiesel(player.inventory, (TileGeneratorDiesel) te) : null;
            case (GuiIDs.OPEN_HEARTH_FURNACE):
                return te instanceof TileEntityOpenHearthFurnace ? new ContainerOpenHearthFurnace(player.inventory, (TileEntityOpenHearthFurnace) te) : null;
            case (GuiIDs.TRAIN_WORKBENCH):
                return te instanceof TileTrainWbench ? new ContainerTrainWorkbench(player.inventory, player.world, (TileTrainWbench) te) : null;

            case (GuiIDs.LOCO):
                if (entity instanceof EntityRollingStock && passenger != null) {
                    return new InventoryLoco(passenger.inventory, (EntityRollingStock) entity);
                }
                return null;

            case (GuiIDs.CONTROL_CAR):
                if (entity instanceof EntityRollingStock && passenger != null) {
                    return new InventoryControlCar(passenger.inventory, (EntityRollingStock) entity);
                }
                return null;

            case (GuiIDs.FORNEY):
                if (entity instanceof EntityRollingStock && passenger != null) {
                    return new InventoryForney(player.inventory, (EntityRollingStock) entity);
                }
                return null;

            case (GuiIDs.CRAFTING_CART):
                return new ContainerWorkbenchCart(player.inventory, player.world);

            case (GuiIDs.FURNACE_CART):
                return passenger != null ? new InventoryWorkCart(player.inventory, entity) : null;

            case (GuiIDs.ZEPPELIN):
                if (entity instanceof AbstractZeppelin && passenger != null) {
                    return new InventoryZepp(player.inventory, (AbstractZeppelin) entity);
                }
                return null;

            case (GuiIDs.DIGGER):
                if (entity instanceof EntityRotativeDigger && passenger != null) {
                    return new InventoryRotativeDigger(player.inventory, (EntityRotativeDigger) entity);
                }
                return null;

            /* Stationary entities while player is not riding. */
            case (GuiIDs.FREIGHT):
                return entity1 instanceof Freight ? new InventoryFreight(player.inventory, (Freight) entity1) : null;
            case (GuiIDs.JUKEBOX):
                return entity1 instanceof EntityJukeBoxCart ? new InventoryJukeBoxCart(player.inventory, (EntityJukeBoxCart) entity1) : null;
            case (GuiIDs.TENDER):
                return entity1 instanceof Tender ? new InventoryTender(player.inventory, (Tender) entity1) : null;
            case (GuiIDs.BUILDER):
                return entity1 instanceof EntityTracksBuilder ? new InventoryBuilder(player.inventory, (EntityTracksBuilder) entity1) : null;
            case (GuiIDs.LIQUID):
                return entity1 instanceof LiquidTank ? new InventoryLiquid(player.inventory, (LiquidTank) entity1) : null;

            case (GuiIDs.SEAT_GUI):
                if (entity instanceof EntityRollingStock && passenger != null) {
                    return new GUISeatManager(player, (EntityRollingStock) entity);
                }
                return null;

            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public static Entity getEntity(World world, int entityId) {
        if (world instanceof WorldServer) {
            return world.getEntityByID(entityId);
        }
        return null;
    }

    public int addArmor(String armor) {
        return 0;
    }

    public Minecraft getClientInstance() {
        return FMLClientHandler.instance().getClient();
    }

    public GuiScreen getCurrentScreen() {
        return null;
    }

    public void registerTextureFX() {
    }

    public void registerSounds() {
    }

    public void registerBookHandler() {
    }

    public Minecraft getMinecraft() {
        return null;
    }


    public static void killAllStreams() {
        for (MP3Player p : playerList) {
            p.stop();
        }
    }

    public void openadmingui(String data) {
    }

    public static boolean checkJukeboxEntity(World world, int id) {
        return world.getEntityByID(id) != null;
    }

    public void doNEICheck(ItemStack stack) {
    }

    public EntityPlayer getPlayer() {
        return null;
    }

    public float getJukeboxVolume() {
        return 0;
    }

    public void registerKeyBindingHandler() {
    }

    public void setHook() {
    }


    public void seatGUI(EntityPlayer player, EntityRollingStock transport) {    }


    /** Registers a tile entity class under {@code tc:<name>}, skipping classes that are already registered. */
    public static void registerTile(Class<? extends TileEntity> tile, String name) {
        if (TileEntity.getKey(tile) == null) {
            GameRegistry.registerTileEntity(tile, new net.minecraft.util.ResourceLocation(Info.modID, name.toLowerCase()));
        }
    }

    public Object getTESR(){return null;}

    /** Client: the shared item renderer for TiM blocks ({@code CustomItemModel.instance}); null on a server. */
    public Object getCustomItemModel(){return null;}

    /** client: attaches a 1.7.10-style item renderer (TCItemRenderer) to the item. */
    public void registerItemRenderer(net.minecraft.item.Item item, Object renderer){}

    /** client: lets {@code CustomItemModel} draw a TESR block's item from a template tile entity. */
    public void registerBlockItemRenderer(net.minecraft.block.Block block, net.minecraft.tileentity.TileEntity template){}

    /** Client: a new {@code BlockTESR}; null on a server. */
    public Object newBlockTESR(){return null;}
    public Object getEntityRender(){return null;}
    public Object getNullRender(){return null;}

}
