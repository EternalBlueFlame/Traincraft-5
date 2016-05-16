package src.train.common.core;

import com.google.common.collect.Lists;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.MinecraftForge;
import src.train.client.core.handlers.ClientTickHandler;
import src.train.client.gui.HUDloco;
import src.train.common.Traincraft;
import src.train.common.api.*;
import src.train.common.containers.*;
import src.train.common.core.handlers.ChunkEvents;
import src.train.common.core.handlers.CraftingHandler;
import src.train.common.core.handlers.WorldEvents;
import src.train.common.entity.digger.EntityRotativeDigger;
import src.train.common.entity.rollingStock.EntityJukeBoxCart;
import src.train.common.entity.rollingStock.EntityTracksBuilder;
import src.train.common.entity.zeppelin.AbstractZeppelin;
import src.train.common.inventory.*;
import src.train.common.library.GuiIDs;
import src.train.common.tile.*;

import java.util.List;

public class CommonProxy implements IGuiHandler {

	public void setKeyBinding(String name, int value) {}

	public void registerRenderInformation() {}

	public void registerEvents(FMLPreInitializationEvent event){
		System.out.println("Events");
		WorldEvents worldEvents = new WorldEvents();
		ClientTickHandler tickHandler = new ClientTickHandler();
		ChunkEvents chunkEvents = new ChunkEvents();
		HUDloco huDloco = new HUDloco();

		registerEvent(worldEvents);
		registerEvent(tickHandler);
		registerEvent(chunkEvents);
		registerEvent(huDloco);

	}

	private void registerEvent(Object o){
		FMLCommonHandler.instance().bus().register(o);
		MinecraftForge.EVENT_BUS.register(o);
	}

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileCrafterTierI.class, "TileCrafterTierI");
		GameRegistry.registerTileEntity(TileCrafterTierII.class, "TileCrafterTierII");
		GameRegistry.registerTileEntity(TileCrafterTierIII.class, "TileCrafterTierIII");
		GameRegistry.registerTileEntity(TileTrainWbench.class, "TileTrainWbench");
		GameRegistry.registerTileEntity(TileEntityDistil.class, "Tile Distil");
		GameRegistry.registerTileEntity(TileEntityOpenHearthFurnace.class, "Tile OpenHearthFurnace");
		GameRegistry.registerTileEntity(TileStopper.class, "TileStopper");
		GameRegistry.registerTileEntity(TileSignal.class, "TileSignal");
		GameRegistry.registerTileEntity(TileLantern.class, "tileLantern");
		GameRegistry.registerTileEntity(TileWaterWheel.class, "tileWaterWheel");
		GameRegistry.registerTileEntity(TileWindMill.class, "tileWindMill");
		GameRegistry.registerTileEntity(TileGeneratorDiesel.class, "tileGeneratorDiesel");
		GameRegistry.registerTileEntity(TileBook.class, "tileBook");
		GameRegistry.registerTileEntity(TileTCRailGag.class, "tileTCRailGag");
		GameRegistry.registerTileEntity(TileTCRail.class, "tileTCRail");
		GameRegistry.registerTileEntity(TileBridgePillar.class, "tileTCBridgePillar");
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		EntityPlayer riddenByEntity = null;
		Entity entity = null;
		entity = player.ridingEntity;

		if (player.ridingEntity != null) {
			riddenByEntity = (EntityPlayer) entity.riddenByEntity;
		}

		Entity entity1 = null;
		if (y == -1) {
			entity1 = getEntity(world, x);
		}

		switch (ID) {
		case (GuiIDs.CRAFTER_TIER_I):
			return te != null && te instanceof TileCrafterTierI ? new ContainerTier(player.inventory, (TileCrafterTierI) te) : null;
		case (GuiIDs.CRAFTER_TIER_II):
			return te != null && te instanceof TileCrafterTierII ? new ContainerTier(player.inventory, (TileCrafterTierII) te) : null;
		case (GuiIDs.CRAFTER_TIER_III):
			return te != null && te instanceof TileCrafterTierIII ? new ContainerTier(player.inventory, (TileCrafterTierIII) te) : null;
		case (GuiIDs.DISTIL):
			return te != null && te instanceof TileEntityDistil ? new ContainerDistil(player.inventory, (TileEntityDistil) te) : null;
		case (GuiIDs.GENERATOR_DIESEL):
			return te != null && te instanceof TileGeneratorDiesel ? new ContainerGeneratorDiesel(player.inventory, (TileGeneratorDiesel) te) : null;
		case (GuiIDs.OPEN_HEARTH_FURNACE):
			return te != null && te instanceof TileEntityOpenHearthFurnace ? new ContainerOpenHearthFurnace(player.inventory, (TileEntityOpenHearthFurnace) te) : null;
		case (GuiIDs.TRAIN_WORKBENCH):
			return te != null && te instanceof TileTrainWbench ? new ContainerTrainWorkbench(player.inventory, player.worldObj, (TileTrainWbench) te) : null;
		case (GuiIDs.LOCO):
			return riddenByEntity != null && entity != null ? new InventoryLoco(riddenByEntity.inventory, (EntityRollingStock) entity) : null;
		case (GuiIDs.FORNEY):
			return riddenByEntity != null && entity != null ? new InventoryForney(player.inventory, (EntityRollingStock) entity) : null;
		case (GuiIDs.CRAFTING_CART):
			return new ContainerWorkbenchCart(player.inventory, player.worldObj);
		case (GuiIDs.FURNACE_CART):
			return riddenByEntity != null && entity != null ? new InventoryWorkCart(player.inventory, entity) : null;
		case (GuiIDs.ZEPPELIN):
			return riddenByEntity != null && entity != null ? new InventoryZepp(player.inventory, (AbstractZeppelin) entity) : null;
		case (GuiIDs.DIGGER):
			return riddenByEntity != null && entity != null ? new InventoryRotativeDigger(player.inventory, (EntityRotativeDigger) entity) : null;

			/* Stationary entities while player is not riding. */
		case (GuiIDs.FREIGHT):
			//System.out.println("Freight: " + ID + " | " + entity1.getEntityName() + " | " + x + ":" + y + ":" + z);
			return entity1 != null && entity1 instanceof Freight ? new InventoryFreight(player.inventory, (Freight) entity1) : null;
		case (GuiIDs.JUKEBOX):
			return entity1 != null && entity1 instanceof EntityJukeBoxCart ? new InventoryJukeBoxCart(player.inventory, (EntityJukeBoxCart) entity1) : null;
		case (GuiIDs.TENDER):
			return entity1 != null && entity1 instanceof Tender ? new InventoryTender(player.inventory, (Tender) entity1) : null;
		case (GuiIDs.BUILDER):
			return entity1 != null && entity1 instanceof EntityTracksBuilder ? new InventoryBuilder(player.inventory, (EntityTracksBuilder) entity1) : null;
		case (GuiIDs.LIQUID):
			return entity1 != null && entity1 instanceof LiquidTank ? new InventoryLiquid(player.inventory, (LiquidTank) entity1) : null;
		default:
			return null;
		}
	}
	public void registerChunkHandler(Traincraft instance){
		ForgeChunkManager.setForcedChunkLoadingCallback(instance, new locoChunkloadCallback());
	}
	
	public class locoChunkloadCallback implements ForgeChunkManager.OrderedLoadingCallback {

		@Override
		public void ticketsLoaded(List<Ticket> tickets, World world) {
			for (Ticket ticket : tickets) {
				int locoID = ticket.getModData().getInteger("locoID");
				Entity entity = world.getEntityByID(locoID);
				if(entity!=null && entity instanceof AbstractTrains){
					((AbstractTrains)entity).forceChunkLoading(ticket);
				}
			}
		}

		@Override
		public List<Ticket> ticketsLoaded(List<Ticket> tickets, World world, int maxTicketCount) {
			List<Ticket> validTickets = Lists.newArrayList();
			for (Ticket ticket : tickets) {
				int locoID = ticket.getModData().getInteger("locoID");
				Entity entity = world.getEntityByID(locoID);
				if(entity!=null && entity instanceof AbstractTrains){
					validTickets.add(ticket);
				}
			}
			return validTickets;
		}
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	public static Entity getEntity(World world, int entityId) {
		if ((world != null) && (world instanceof WorldServer)) {
			return ((WorldServer) world).getEntityByID(entityId);
		}
		return null;
	}

	public int addArmor(String armor) {
		return 0;
	}

	public Minecraft getClientInstance() {
		return FMLClientHandler.instance().getClient();
	}

	public void getKeysFromProperties() {}

	public GuiScreen getCurrentScreen() {
		return null;
	}

	public void registerTextureFX() {}

	public void registerSounds() {}
	
	public void registerBookHandler() {}
    
	public void registerBlock(Block block) {
        GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.", ""));
	}
	
	public void registerBlock(Block block, Class<? extends ItemBlock> item) {
		GameRegistry.registerBlock(block, item, block.getUnlocalizedName().replace("tile.", ""));
    }

	public Minecraft getMinecraft() {
		return null;
	}

	public void registerVillagerSkin(int villagerId, String textureName) {}
	
	public static void killAllStreams() {
		/*
		for (MP3Player p : playerList) {
			p.stop();
		}
		*/
	}
	
	public static boolean checkJukeboxEntity(World world, int id) {
		if (world.getEntityByID(id)!=null) {
			return true;
		}
		return false;
	}
	
	public void isHoliday() {}

	public void doNEICheck(ItemStack stack) {}
	
	public EntityPlayer getPlayer() {
		return null;
	}
}