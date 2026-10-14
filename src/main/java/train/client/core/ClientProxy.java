package train.client.core;

import train.client.render.RenderNothing;

import ebf.tim.utility.CommonUtil;

import train.client.render.item.TCItemRenderRegistry;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import ebf.tim.entities.EntitySeat;
import ebf.tim.gui.GUISeatManager;
import javazoom.jl.decoder.JavaLayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundCategory;
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
import train.common.entity.CollisionBox;
import train.common.entity.digger.EntityRotativeDigger;
import train.common.entity.digger.EntityRotativeWheel;
import train.common.entity.rollingStockOld.special.EntityJukeBoxCart;
import train.common.entity.zeppelin.EntityZeppelinOneBalloon;
import train.common.entity.zeppelin.EntityZeppelinTwoBalloons;
import train.common.library.BlockIDs;
import train.common.library.GuiIDs;
import train.common.library.Info;
import train.common.tile.*;
import train.common.tile.switchStand.*;
import train.common.wellcar.GuiFortyFootContainer;
import train.common.wellcar.TileFortyFootContainer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

public class ClientProxy extends CommonProxy {

    public static int railSkin=3;

    /** Scales players riding rolling stock (1.7.10 replaced the player renderer for this). */
    public static class PlayerScaler {
        private boolean pushed = false;

        @net.minecraftforge.fml.common.eventhandler.SubscribeEvent
        public void pre(net.minecraftforge.client.event.RenderPlayerEvent.Pre event) {
            EntityPlayer player = event.getEntityPlayer();
            EntityRollingStock stock;
            if (player.getRidingEntity() instanceof EntityRollingStock) {
                stock = (EntityRollingStock) player.getRidingEntity();
            } else if (player.getRidingEntity() instanceof EntitySeat) {
                stock = ((EntitySeat) player.getRidingEntity()).parent;
            } else {
                stock = null;
            }
            pushed = stock != null;
            if (stock != null) {
                double x = event.getX(), y = event.getY(), z = event.getZ();
                GL11.glPushMatrix();
                float scale = stock.getPlayerScale();
                GL11.glTranslated(x, (y + .35), z);
                GL11.glScalef(scale, scale, scale);
                GL11.glTranslated(-x, -(y + .35), -z);
                if (player != Minecraft.getMinecraft().player && stock.getPlayerScale() != 1) {
                    GL11.glTranslated(0, 1 - (stock.getPlayerScale() - 0.2), 0); //rough approx. but gets the job done for everything in range 0.5-1
                } else {
                    GL11.glTranslated(0, (1 - stock.getPlayerScale()) * -1, 0); //rough approx. but gets the job done for everything in range 0.5-1
                }
            }
        }

        @net.minecraftforge.fml.common.eventhandler.SubscribeEvent
        public void post(net.minecraftforge.client.event.RenderPlayerEvent.Post event) {
            if (pushed) {
                GL11.glPopMatrix();
                pushed = false;
            }
        }
    }

    @Override
    public void registerPlayerScaler(){
        MinecraftForge.EVENT_BUS.register(new PlayerScaler());
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
        if (Loader.isModLoaded("computercraft")) {
            HUDMTC hudMTC = new HUDMTC();
            registerEvent(hudMTC);
        }

        registerEvent(tickHandler);

        registerEvent(huDloco);
    }

    @Override
    public void registerEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(CollisionBox.class, RenderNothing::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySeat.class, RenderNothing::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRollingStock.class, RenderRollingStock::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityZeppelinTwoBalloons.class, RenderZeppelins::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityZeppelinOneBalloon.class, RenderZeppelins::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRotativeDigger.class, RenderRotativeDigger::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRotativeWheel.class, RenderRotativeWheel::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBogie.class, RenderBogie::new);
    }

    @Override
    public void registerRenderInformation() {
        FMLCommonHandler.instance().bus().register(new ClientTickHandler());
        ClientRegistry.bindTileEntitySpecialRenderer(TileStopper.class, train.client.render.gl.TCSafeTESR.wrap(new RenderStopper()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.stopper.block), new ItemRenderStopper());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEmbeddedStopper.class, train.client.render.gl.TCSafeTESR.wrap(new RenderEmbeddedStopper()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.embeddedStopper.block), new ItemRenderEmbeddedStopper());

        ClientRegistry.bindTileEntitySpecialRenderer(TileAmericanStopper.class, train.client.render.gl.TCSafeTESR.wrap(new RenderAmericanStopper()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.americanstopper.block), new ItemRenderAmericanStopper());

        //ClientRegistry.bindTileEntitySpecialRenderer(TileBook.class, train.client.render.gl.TCSafeTESR.wrap(new RenderTCBook()));
        //TCItemRenderRegistry.register(BlockIDs.book.blockID, new ItemRenderBook());

        ClientRegistry.bindTileEntitySpecialRenderer(TileSignal.class, train.client.render.gl.TCSafeTESR.wrap(new RenderSignal()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.signal.block), new ItemRenderSignal());

        ClientRegistry.bindTileEntitySpecialRenderer(TileLantern.class, train.client.render.gl.TCSafeTESR.wrap(new RenderLantern()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(TCBlocks.lantern), new ItemRenderLantern());

        ClientRegistry.bindTileEntitySpecialRenderer(TileMFPBWigWag.class, train.client.render.gl.TCSafeTESR.wrap(new RenderMFPBWigWag()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(TCBlocks.MFPBWigWag), new ItemRenderMFPBWigWag());

        ClientRegistry.bindTileEntitySpecialRenderer(TileWaterWheel.class, train.client.render.gl.TCSafeTESR.wrap(new RenderWaterWheel()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.waterWheel.block), new ItemRenderWaterWheel());

        ClientRegistry.bindTileEntitySpecialRenderer(TileWindMill.class, train.client.render.gl.TCSafeTESR.wrap(new RenderWindMill()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.windMill.block), new ItemRenderWindMill());

        ClientRegistry.bindTileEntitySpecialRenderer(TileGeneratorDiesel.class, train.client.render.gl.TCSafeTESR.wrap(new RenderGeneratorDiesel()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.generatorDiesel.block), new ItemRenderGeneratorDiesel());

        ClientRegistry.bindTileEntitySpecialRenderer(TileTCRail.class, train.client.render.gl.TCSafeTESR.wrap(new RenderTCRail()));

        ClientRegistry.bindTileEntitySpecialRenderer(TileMetroMadridPole.class, train.client.render.gl.TCSafeTESR.wrap(new RenderMetroMadridPole()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.metroMadridPole.block), new ItemRenderModelMetroMadridPole());

        ClientRegistry.bindTileEntitySpecialRenderer(TileSwitchStand.class, train.client.render.gl.TCSafeTESR.wrap(new RenderSwitchStand()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(TCBlocks.switchStand), new ItemRenderSwitchStand());

        ClientRegistry.bindTileEntitySpecialRenderer(TileOWOYardSwitchStand.class, train.client.render.gl.TCSafeTESR.wrap(new RenderowoYardSwtichStand()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(TCBlocks.owoYardSwitchStand), new ItemRenderowoYardSwitchStand());

        ClientRegistry.bindTileEntitySpecialRenderer(TileMILWSwitchStand.class, train.client.render.gl.TCSafeTESR.wrap(new RenderMILWSwitchStand()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(TCBlocks.MILWSwitchStand), new ItemRenderMILWSwitchStand());

        ClientRegistry.bindTileEntitySpecialRenderer(TileCircleSwitchStand.class, train.client.render.gl.TCSafeTESR.wrap(new RendercircleSwitchStand()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(TCBlocks.circleSwitchStand), new ItemRendercircleSwitchStand());

        ClientRegistry.bindTileEntitySpecialRenderer(TileOWOSwitchStand.class, train.client.render.gl.TCSafeTESR.wrap(new RenderowoSwitchStand()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(TCBlocks.owoSwitchStand), new ItemRenderowoSwitchStand());

        ClientRegistry.bindTileEntitySpecialRenderer(TileAutoSwitchStand.class, train.client.render.gl.TCSafeTESR.wrap(new RenderautoSwitchStand()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(TCBlocks.autoSwtichStand), new ItemRenderautoSwitchStand());

        ClientRegistry.bindTileEntitySpecialRenderer(TileoverheadWire.class, train.client.render.gl.TCSafeTESR.wrap(new RenderoverheadWire()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.overheadWire.block), new ItemRenderoverheadWire());

        ClientRegistry.bindTileEntitySpecialRenderer(TileoverheadWireDouble.class, train.client.render.gl.TCSafeTESR.wrap(new RenderoverheadWireDouble()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.overheadWireDouble.block), new ItemRenderoverheadWireDouble());

        ClientRegistry.bindTileEntitySpecialRenderer(TilesignalSpanish.class, train.client.render.gl.TCSafeTESR.wrap(new RendersignalSpanish()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.signalSpanish.block), new ItemRendersignalSpanish());

        //ClientRegistry.bindTileEntitySpecialRenderer(TiletrackConcrete.class, train.client.render.gl.TCSafeTESR.wrap(new RendertrackEmbeddedSmallStraight()));
        //TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.trackConcrete.block), new ItemRendertrackEmbeddedSmallStraight());


        ClientRegistry.bindTileEntitySpecialRenderer(TilekSignal.class, train.client.render.gl.TCSafeTESR.wrap(new RenderkSignal()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.kSignal.block), new ItemRenderkSignal());

        ClientRegistry.bindTileEntitySpecialRenderer(TileSpeedSign.class, train.client.render.gl.TCSafeTESR.wrap(new RenderSpeedSign()));
        TCItemRenderRegistry.register(Item.getItemFromBlock(TCBlocks.speedSign), new ItemRenderSpeedSign());



		/*
		ClientRegistry.bindTileEntitySpecialRenderer(TileFortyFootContainer.class, train.client.render.gl.TCSafeTESR.wrap(new FortyFootContainerRender()));
		TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.FortyFootContainer.block), new ItemRenderFortyFootContainer());
		*/

		/*
		ClientRegistry.bindTileEntitySpecialRenderer(TileFortyFootContainer.class, train.client.render.gl.TCSafeTESR.wrap(new FortyFootContainerRender()));
		TCItemRenderRegistry.register(Item.getItemFromBlock(BlockIDs.FortyFootContainer.block), new ItemRenderFortyFootContainer());*/
    }


    @Override
    public Object getTESR(){return specialRenderer;}

    @Override
    public Object getCustomItemModel(){return ebf.tim.render.CustomItemModel.instance;}

    @Override
    public void registerItemRenderer(net.minecraft.item.Item item, Object renderer){
        if (renderer instanceof train.client.render.item.TCItemRenderer) {
            train.client.render.item.TCItemRenderRegistry.register(item, (train.client.render.item.TCItemRenderer) renderer);
        }
    }

    @Override
    public void registerBlockItemRenderer(net.minecraft.block.Block block, net.minecraft.tileentity.TileEntity template){
        net.minecraft.item.Item item = net.minecraft.item.Item.getItemFromBlock(block);
        if (item != net.minecraft.init.Items.AIR) {
            train.client.render.item.TCItemRenderRegistry.register(item, ebf.tim.render.CustomItemModel.instance);
            ebf.tim.render.CustomItemModel.registerBlockTextures(item, template);
        }
    }

    @Override
    public Object newBlockTESR(){return new ebf.tim.render.BlockTESR();}

    @Override
    public Render getEntityRender(){return getTransportRenderer();}
    @Override
    public Object getNullRender(){return null;}


    public static final TileEntitySpecialRenderer specialRenderer = new TileEntitySpecialRenderer() {
        @Override
        public void render(TileEntity tileEntity, double x, double y, double z, float p_147500_8_, int destroyStage, float alpha) {
            GL11.glPushMatrix();
            GL11.glTranslated(x,y, z);
            tileEntity.addInfoToCrashReport(null);
            GL11.glPopMatrix();
        }

        @Override
        protected void bindTexture(ResourceLocation p_147499_1_){}
    };


    private static RenderRollingStock transportRenderer;

    /** Shared rolling stock renderer (for item/GUI rendering); created once the render manager exists. */
    public static RenderRollingStock getTransportRenderer() {
        if (transportRenderer == null) {
            transportRenderer = new RenderRollingStock(Minecraft.getMinecraft().getRenderManager());
        }
        return transportRenderer;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = CommonUtil.getTileEntity(world, x, y, z);
    Entity entity = player.getRidingEntity();
    EntityPlayer passenger = null;

    if (entity != null && !entity.getPassengers().isEmpty() && ebf.tim.utility.CommonUtil.rider(entity) instanceof EntityPlayer) {
        passenger = (EntityPlayer) ebf.tim.utility.CommonUtil.rider(entity);
    }

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
                return te instanceof TileTrainWbench ? new GuiTrainCraftingBlock(player.inventory, player.world, (TileTrainWbench) te) : null;

            case (GuiIDs.LOCO):
                if (passenger != null && entity instanceof EntitySeat) {
                    return new GuiLoco2(passenger.inventory, world.getEntityByID(((EntitySeat) entity).parentId));
                } else if (passenger != null) {
                    return new GuiLoco2(passenger.inventory, entity);
                } else {
                    return null;
                }

            case (GuiIDs.CONTROL_CAR):
                if (passenger != null && entity instanceof EntityRollingStock) {
                    return new GuiControlCar(passenger.inventory, entity);
                } else if (passenger != null && entity instanceof EntitySeat) {
                    return new GuiControlCar(passenger.inventory, world.getEntityByID(((EntitySeat) entity).parentId));
                } else {
                    return null;
                }

            case (GuiIDs.FORNEY):
                if (passenger != null && entity instanceof EntityRollingStock) {
                    return new GuiForney(passenger.inventory, entity);
                } else if (passenger != null && entity instanceof EntitySeat) {
                    return new GuiForney(passenger.inventory, world.getEntityByID(((EntitySeat) entity).parentId));
                } else {
                    return null;
                }

            case (GuiIDs.CRAFTING_CART):
                return passenger != null ? new GuiCraftingCart(passenger.inventory, world) : null;

            case (GuiIDs.FURNACE_CART):
                if (passenger != null && entity instanceof EntityRollingStock) {
                    return new GuiFurnaceCart(passenger.inventory, entity);
                } else if (passenger != null && entity instanceof EntitySeat) {
                    return new GuiFurnaceCart(passenger.inventory, world.getEntityByID(((EntitySeat) entity).parentId));
                } else {
                    return null;
                }

            case (GuiIDs.ZEPPELIN):
                return passenger != null ? new GuiZepp(passenger.inventory, entity) : null;

            case (GuiIDs.DIGGER):
                return passenger != null ? new GuiBuilder(player, passenger.inventory, entity) : null;

            case (GuiIDs.MTC_INFO):
                return passenger != null && Loader.isModLoaded("computercraft") ? new GuiMTCInfo(player) : null;

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
                return new GuiRecipeBook(player, player.getHeldItemMainhand());
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
            case (GuiIDs.LOCK_MENU):
                return entity1 != null ? new GuiLockMenu(player, ((EntityRollingStock) entity1)) : null;
            case (GuiIDs.SEAT_GUI):
                return entity1 != null ? new GUISeatManager(player, (EntityRollingStock) entity1) : null;
            default:
                return null;
        }
    }


    @Override
    public GuiScreen getCurrentScreen() {
        return Minecraft.getMinecraft().currentScreen;
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
        return getMinecraft().player;
    }

    @Optional.Method(modid = "jei")
    @Override
    public void doNEICheck(ItemStack stack) {
        if (Minecraft.getMinecraft().player != null) {
            if (Loader.isModLoaded("jei")) {
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
