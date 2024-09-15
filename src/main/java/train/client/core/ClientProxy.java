package train.client.core;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.VillagerRegistry;
import ebf.tim.entities.EntitySeat;
import ebf.tim.gui.GUISeatManager;
import javazoom.jl.decoder.JavaLayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Level;
import org.lwjgl.opengl.GL11;
import train.client.core.handlers.ClientTickHandler;
import train.client.core.handlers.CustomRenderHandler;
import train.client.core.handlers.RecipeBookHandler;
import train.client.core.handlers.TCKeyHandler;
import train.client.core.helpers.JLayerHook;
import train.client.gui.*;
import train.client.render.*;
import train.client.render.renderSwitch.*;
import train.common.Traincraft;
import train.common.adminbook.GUIAdminBook;
import train.common.api.EntityBogie;
import train.common.api.EntityRollingStock;
import train.common.blocks.TCBlocks;
import train.common.core.CommonProxy;
import train.common.core.Traincraft_EventSounds;
import train.common.core.handlers.ConfigHandler;
import train.common.entity.digger.EntityRotativeDigger;
import train.common.entity.digger.EntityRotativeWheel;
import train.common.entity.rollingStock.EntityJukeBoxCart;
import train.common.entity.zeppelin.EntityZeppelinOneBalloon;
import train.common.entity.zeppelin.EntityZeppelinTwoBalloons;
import train.common.library.BlockIDs;
import train.common.library.GuiIDs;
import train.common.library.Info;
import train.common.tile.*;
import train.common.tile.tileSwitch.*;
import train.common.wellcar.GuiFortyFootContainer;
import train.common.wellcar.TileFortyFootContainer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

public class ClientProxy extends CommonProxy {

    public static final net.minecraft.client.renderer.entity.RenderPlayer playerRender = new net.minecraft.client.renderer.entity.RenderPlayer(){
        EntityRollingStock stock;
        @Override
        public void doRender(net.minecraft.client.entity.AbstractClientPlayer player, double x, double y, double z, float f0, float f1){
            if(player.ridingEntity instanceof EntityRollingStock) {
                stock = (EntityRollingStock) player.ridingEntity;
            } else if (player.ridingEntity instanceof EntitySeat) {
                stock =  ((EntitySeat) player.ridingEntity).parent;
            } else {
                stock = null;
            }
            if (stock != null) {
                GL11.glPushMatrix();
                float scale = stock.getPlayerScale();
                scale = player.height * scale / player.height;
                GL11.glTranslated(x, (y + .35), z);
                GL11.glScalef(scale, scale, scale);
                GL11.glTranslated(-x, -(y + .35), -z);
                if (player != Minecraft.getMinecraft().thePlayer && stock.getPlayerScale() != 1) {
                    GL11.glTranslated(0, 1 - (stock.getPlayerScale() - 0.2), 0); //rough approx. but gets the job done for everything in range 0.5-1
                } else {
                    GL11.glTranslated(0, (1 - stock.getPlayerScale()) * -1, 0); //rough approx. but gets the job done for everything in range 0.5-1
                }
                super.doRender(player, x, y, z, f0, f1);
                GL11.glPopMatrix();
                return;
            }
            super.doRender(player, x, y, z, f0, f1);
        }
    };

    @Override
    public void registerPlayerScaler(){
        RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, playerRender);
    }

    public static boolean hdTransportItems=false;
    public static boolean preRenderModels=false;
    /**
     * Checks if the month is December or January
     *
     * @return true if it is December or January
     */
    public static boolean isHoliday() {
        if(ConfigHandler.HOLIDAY_SKINS==2){
            return true;
        }
        if(ConfigHandler.HOLIDAY_SKINS==1){
            return false;
        }
        Calendar cal = Calendar.getInstance();
        return (cal.get(Calendar.MONTH) == Calendar.DECEMBER || (cal.get(Calendar.MONTH) == Calendar.JANUARY) && cal.get(Calendar.DATE) < 7);
    }

    /**
     * Checks if the month is October or November (and is less than 15 days for november).
     *
     * @return true if it is October or November (and is less than 15 days for november).
     */
    public static boolean isPumpkin() {
        Calendar cal = Calendar.getInstance();
        return (cal.get(Calendar.MONTH) == Calendar.OCTOBER || cal.get(Calendar.MONTH) == Calendar.NOVEMBER && cal.get(Calendar.DATE) < 15);
    }

    @Override
    public boolean isClient(){
        return true;
    }

    @Override
    public void throwAlphaException() {
        throw new AlphaExpiredException();
    }

    @Override
    public void registerEvents(FMLPreInitializationEvent event) {
        super.registerEvents(event);
        ClientTickHandler tickHandler = new ClientTickHandler();
        if (ConfigHandler.ENABLE_TRACK_HOLOGRAM) {
            CustomRenderHandler renderHandler = new CustomRenderHandler();
            registerEvent(renderHandler);
        }

        HUDloco huDloco = new HUDloco();
        if (Loader.isModLoaded("ComputerCraft")) {
            HUDMTC hudMTC = new HUDMTC();
            registerEvent(hudMTC);
        }

        registerEvent(tickHandler);

        registerEvent(huDloco);
    }

    @Override
    public void registerRenderInformation() {
        FMLCommonHandler.instance().bus().register(new ClientTickHandler());

        RenderingRegistry.registerEntityRenderingHandler(EntityRollingStock.class, new RenderRollingStock());
        RenderingRegistry.registerEntityRenderingHandler(EntityZeppelinTwoBalloons.class, new RenderZeppelins());
        RenderingRegistry.registerEntityRenderingHandler(EntityZeppelinOneBalloon.class, new RenderZeppelins());
        RenderingRegistry.registerEntityRenderingHandler(EntityRotativeDigger.class, new RenderRotativeDigger());
        RenderingRegistry.registerEntityRenderingHandler(EntityRotativeWheel.class, new RenderRotativeWheel());

        // Register Renderer for Bogies
        RenderingRegistry.registerEntityRenderingHandler(EntityBogie.class, new RenderBogie());


        ClientRegistry.bindTileEntitySpecialRenderer(TileStopper.class, new RenderStopper());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.stopper.block), new ItemRenderStopper());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEmbeddedStopper.class, new RenderEmbeddedStopper());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.embeddedStopper.block), new ItemRenderEmbeddedStopper());

        ClientRegistry.bindTileEntitySpecialRenderer(TileAmericanStopper.class, new RenderAmericanStopper());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.americanstopper.block), new ItemRenderAmericanStopper());

        //ClientRegistry.bindTileEntitySpecialRenderer(TileBook.class, new RenderTCBook());
        //MinecraftForgeClient.registerItemRenderer(BlockIDs.book.blockID, new ItemRenderBook());

        ClientRegistry.bindTileEntitySpecialRenderer(TileSignal.class, new RenderSignal());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.signal.block), new ItemRenderSignal());

        ClientRegistry.bindTileEntitySpecialRenderer(TileLantern.class, new RenderLantern());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TCBlocks.lantern), new ItemRenderLantern());

        ClientRegistry.bindTileEntitySpecialRenderer(TileSwitchStand.class, new RenderSwitchStand());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TCBlocks.switchStand), new ItemRenderSwitchStand());

        ClientRegistry.bindTileEntitySpecialRenderer(TileMFPBWigWag.class, new RenderMFPBWigWag());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(TCBlocks.MFPBWigWag), new ItemRenderMFPBWigWag());

        ClientRegistry.bindTileEntitySpecialRenderer(TileWaterWheel.class, new RenderWaterWheel());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.waterWheel.block), new ItemRenderWaterWheel());

        ClientRegistry.bindTileEntitySpecialRenderer(TileWindMill.class, new RenderWindMill());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.windMill.block), new ItemRenderWindMill());

        ClientRegistry.bindTileEntitySpecialRenderer(TileGeneratorDiesel.class, new RenderGeneratorDiesel());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.generatorDiesel.block), new ItemRenderGeneratorDiesel());

        ClientRegistry.bindTileEntitySpecialRenderer(TileTCRail.class, new RenderTCRail());

        ClientRegistry.bindTileEntitySpecialRenderer(TileMetroMadridPole.class, new RenderMetroMadridPole());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.metroMadridPole.block), new ItemRenderModelMetroMadridPole());

        ClientRegistry.bindTileEntitySpecialRenderer(TileowoYardSwitchStand.class, new RenderowoYardSwtichStand());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.owoYardSwitchStand.block), new ItemRenderowoYardSwitchStand());

        ClientRegistry.bindTileEntitySpecialRenderer(TileMILWSwitchStand.class, new RenderMILWSwitchStand());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.MILWSwitchStand.block), new ItemRenderMILWSwitchStand());

        ClientRegistry.bindTileEntitySpecialRenderer(TilecircleSwitchStand.class, new RendercircleSwitchStand());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.circleSwitchStand.block), new ItemRendercircleSwitchStand());

        ClientRegistry.bindTileEntitySpecialRenderer(TileowoSwitchStand.class, new RenderowoSwitchStand());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.owoSwitchStand.block), new ItemRenderowoSwitchStand());

        ClientRegistry.bindTileEntitySpecialRenderer(TileautoSwitchStand.class, new RenderautoSwitchStand());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.autoSwtichStand.block), new ItemRenderautoSwitchStand());

        ClientRegistry.bindTileEntitySpecialRenderer(TileoverheadWire.class, new RenderoverheadWire());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.overheadWire.block), new ItemRenderoverheadWire());

        ClientRegistry.bindTileEntitySpecialRenderer(TileoverheadWireDouble.class, new RenderoverheadWireDouble());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.overheadWireDouble.block), new ItemRenderoverheadWireDouble());

        ClientRegistry.bindTileEntitySpecialRenderer(TilesignalSpanish.class, new RendersignalSpanish());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.signalSpanish.block), new ItemRendersignalSpanish());

        //ClientRegistry.bindTileEntitySpecialRenderer(TiletrackConcrete.class, new RendertrackEmbeddedSmallStraight());
        //MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.trackConcrete.block), new ItemRendertrackEmbeddedSmallStraight());


        ClientRegistry.bindTileEntitySpecialRenderer(TilekSignal.class, new RenderkSignal());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.kSignal.block), new ItemRenderkSignal());

        ClientRegistry.bindTileEntitySpecialRenderer(TileSpeedSign.class, new RenderSpeedSign());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.speedSign.block), new ItemRenderSpeedSign());


        //seats
        RenderingRegistry.registerEntityRenderingHandler(EntitySeat.class, nullRender);

		/*
		ClientRegistry.bindTileEntitySpecialRenderer(TileFortyFootContainer.class, new FortyFootContainerRender());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.FortyFootContainer.block), new ItemRenderFortyFootContainer());
		*/

		/*
		ClientRegistry.bindTileEntitySpecialRenderer(TileFortyFootContainer.class, new FortyFootContainerRender());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.FortyFootContainer.block), new ItemRenderFortyFootContainer());*/
    }


    @Override
    public Object getTESR(){return specialRenderer;}

    @Override
    public Render getEntityRender(){return transportRenderer;}
    @Override
    public Object getNullRender(){return nullRender;}


    public static final TileEntitySpecialRenderer specialRenderer = new TileEntitySpecialRenderer() {
        @Override
        public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float p_147500_8_) {
            GL11.glPushMatrix();
            GL11.glTranslated(x,y, z);
            tileEntity.func_145828_a(null);
            GL11.glPopMatrix();
        }

        @Override
        protected void bindTexture(ResourceLocation p_147499_1_){}
    };


    public static final RenderRollingStock transportRenderer = new RenderRollingStock();

    /**
     * <h3>null render</h3>
     * this is just a simple render that never draws anything, since its static it only ever needs to exist once, which makes it lighter on the render.
     */
    private static final Render nullRender = new Render() {
        @Override
        public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {}

        @Override
        protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
            return null;
        }
    };

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        Entity entity = player.ridingEntity;
        EntityPlayer riddenByEntity = player.ridingEntity != null ? (EntityPlayer) entity.riddenByEntity : null;

        Entity entity1 = null;
        if (y == -1) {
            for (Object ent : world.loadedEntityList) {
                if (((Entity) ent).getEntityId() == x) {
                    entity1 = (Entity) ent;
                }
            }
        }

        switch (ID) {
            case (GuiIDs.CRAFTER_TIER_I):
                return te instanceof TileCrafterTierI ? new GuiCrafterTier(player.inventory, (TileCrafterTierI) te) : null;
            case (GuiIDs.CRAFTER_TIER_II):
                return te instanceof TileCrafterTierII ? new GuiCrafterTier(player.inventory, (TileCrafterTierII) te) : null;
            case (GuiIDs.CRAFTER_TIER_III):
                return te instanceof TileCrafterTierIII ? new GuiCrafterTier(player.inventory, (TileCrafterTierIII) te) : null;
            case (GuiIDs.DISTIL):
                return te instanceof TileEntityDistil ? new GuiDistil(player.inventory, (TileEntityDistil) te) : null;
            case (GuiIDs.GENERATOR_DIESEL):
                return te instanceof TileGeneratorDiesel ? new GuiGeneratorDiesel(player.inventory, (TileGeneratorDiesel) te) : null;
            case (GuiIDs.OPEN_HEARTH_FURNACE):
                return te instanceof TileEntityOpenHearthFurnace ? new GuiOpenHearthFurnace(player.inventory, (TileEntityOpenHearthFurnace) te) : null;
            case GuiIDs.TRAIN_WORKBENCH:
                return te instanceof TileTrainWbench ? new GuiTrainCraftingBlock(player.inventory, player.worldObj, (TileTrainWbench) te) : null;
            case (GuiIDs.LOCO):
                if (riddenByEntity != null && riddenByEntity.ridingEntity instanceof EntityRollingStock) {
                    return new GuiLoco2(riddenByEntity.inventory, entity);
                } else if (riddenByEntity != null && riddenByEntity.ridingEntity instanceof EntitySeat) {
                    return new GuiLoco2(riddenByEntity.inventory, world.getEntityByID(((EntitySeat) entity).parentId));
                } else {
                    return null;
                }
            case (GuiIDs.CONTROL_CAR):
                if (riddenByEntity != null && riddenByEntity.ridingEntity instanceof EntityRollingStock) {
                    return new GuiControlCar(riddenByEntity.inventory, entity);
                } else if (riddenByEntity != null && riddenByEntity.ridingEntity instanceof EntitySeat) {
                    return new GuiControlCar(riddenByEntity.inventory, world.getEntityByID(((EntitySeat) entity).parentId));
                } else {
                    return null;
                }
            case (GuiIDs.FORNEY):
                if (riddenByEntity != null && riddenByEntity.ridingEntity instanceof EntityRollingStock) {
                    return new GuiForney(riddenByEntity.inventory, entity);
                } else if (riddenByEntity != null && riddenByEntity.ridingEntity instanceof EntitySeat) {
                    return new GuiForney(riddenByEntity.inventory, world.getEntityByID(((EntitySeat) entity).parentId));
                } else {
                    return null;
                }
            case (GuiIDs.CRAFTING_CART):
                return riddenByEntity != null ? new GuiCraftingCart(riddenByEntity.inventory, world) : null;
            case (GuiIDs.FURNACE_CART):
                if (riddenByEntity != null && riddenByEntity.ridingEntity instanceof EntityRollingStock) {
                    return new GuiFurnaceCart(riddenByEntity.inventory, entity);
                } else if (riddenByEntity != null && riddenByEntity.ridingEntity instanceof EntitySeat) {
                    return new GuiFurnaceCart(riddenByEntity.inventory, world.getEntityByID(((EntitySeat) entity).parentId));
                } else {
                    return null;
                }
            case (GuiIDs.ZEPPELIN):
                return riddenByEntity != null ? new GuiZepp(riddenByEntity.inventory, entity) : null;
            case (GuiIDs.DIGGER):
                return riddenByEntity != null ? new GuiBuilder(player, riddenByEntity.inventory, entity) : null;
            case (GuiIDs.MTC_INFO):
                return riddenByEntity != null && Loader.isModLoaded("ComputerCraft") ? new GuiMTCInfo(player) : null;

            // Stationary entities while player is not riding.
            case (GuiIDs.FREIGHT):
                return entity1 != null ? new GuiFreight(player, player.inventory, entity1) : null;
            case (GuiIDs.TENDER):
                return entity1 != null ? new GuiTender(player, player.inventory, entity1) : null;
            case (GuiIDs.BUILDER):
                return entity1 != null ? new GuiBuilder(player, player.inventory, entity1) : null;
            case (GuiIDs.LIQUID):
                return entity1 != null ? new GuiLiquid(player, player.inventory, entity1) : null;
            case (GuiIDs.RECIPE_BOOK):
                return new GuiRecipeBook(player, player.getCurrentEquippedItem());
		/*case (GuiIDs.RECIPE_BOOK2):
			return te != null && te instanceof TileBook ? new GuiRecipeBook2(player, player.getCurrentEquippedItem()) : new GuiRecipeBook2(player, player.getCurrentEquippedItem());*/
            case (GuiIDs.LANTERN):
                return new GuiLantern(player, (TileLantern) te);
            case (GuiIDs.JUKEBOX):
                return entity1 != null ? new GuiJukebox(player, (EntityJukeBoxCart) entity1) : null;
            case (GuiIDs.FORTY_FOOT_CONTAINER):
                return new GuiFortyFootContainer((TileFortyFootContainer) te, player);
            case (GuiIDs.PAINTBRUSH):
                return entity1 != null ? new GuiPaintbrushMenu(player, (EntityRollingStock) entity1) : null;
            case (GuiIDs.FIXED_OVERLAY):
                return entity1 != null ? new GuiFixedOverlay(player, (EntityRollingStock) entity1) : null;
            case (GuiIDs.DYNAMIC_OVERLAY):
                return entity1 != null ? new GuiDynamicOverlay(player, (EntityRollingStock) entity1) : null;
            case (GuiIDs.SEAT_GUI):
                return entity1 != null ? new GUISeatManager(player, (EntityRollingStock) entity1) : null;
            default:
                return null;
        }
    }

    @Override
    public int addArmor(String armor) {
        return RenderingRegistry.addNewArmourRendererPrefix(armor);
    }

    @Override
    public GuiScreen getCurrentScreen() {
        return Minecraft.getMinecraft().currentScreen;
    }

    @Override
    public void registerVillagerSkin(int villagerId, String textureName) {
        VillagerRegistry.instance().registerVillagerSkin(villagerId, new ResourceLocation(Info.resourceLocation, Info.villagerPrefix + textureName));
    }

    @Override
    public void registerSounds() {
        MinecraftForge.EVENT_BUS.register(new Traincraft_EventSounds());
    }

    @Override
    public void registerBookHandler() {
        RecipeBookHandler recipeBookHandler = new RecipeBookHandler();
    }

    @Override
    public Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }

    @Override
    public EntityPlayer getPlayer() {
        return getMinecraft().thePlayer;
    }

    @Optional.Method(modid = "NotEnoughItems")
    @Override
    public void doNEICheck(ItemStack stack) {
        if (Minecraft.getMinecraft().thePlayer != null) {
            if (Loader.isModLoaded("Not Enough Items")) {
                try {
                    Class<?> neiApi = Class.forName("codechicken.nei.api.API");
                    Method hideItem = neiApi.getDeclaredMethod("hideItem", stack.getClass());
                    hideItem.invoke(null, stack);
                } catch (ClassNotFoundException e) {
                    Traincraft.tcLog.log(Level.WARN, "Chicken core didn't have required class: Wrong version of the library or something is horribly wrong", e);
                } catch (NoSuchMethodException e) {
                    Traincraft.tcLog.log(Level.WARN, "Chicken core didn't have required method: Wrong version of the library or something is horribly wrong", e);
                } catch (SecurityException | IllegalAccessException e) {
                    Traincraft.tcLog.log(Level.FATAL, "Something is horribly wrong", e);
                } catch (IllegalArgumentException e) {
                    Traincraft.tcLog.log(Level.WARN, "Chicken core had the method but it's signature was wrong: Wrong version of the library or something is horribly wrong", e);
                } catch (InvocationTargetException e) {
                    Traincraft.tcLog.log(Level.WARN, "The method we called from Chicken core threw an exception", e);
                }
            }
        }
    }

    @Override
    public float getJukeboxVolume() {
        return Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.RECORDS) * Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MASTER);
    }

    @Override
    public void openadmingui(String data) {
        Minecraft.getMinecraft().displayGuiScreen(new GUIAdminBook(data));
    }

    @Override
    public void registerKeyBindingHandler() {
        FMLCommonHandler.instance().bus().register(new TCKeyHandler());
    }

    @Override
    public void setHook() {
        JavaLayerUtils.setHook(new JLayerHook(Minecraft.getMinecraft()));
    }

    @Override
    public void seatGUI(EntityPlayer player, EntityRollingStock transport) {
        Minecraft.getMinecraft().displayGuiScreen(new GUISeatManager(transport));
    }
}