package train.core;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import train.core.handlers.WorldEvents;
import train.core.util.MP3Player;
import train.entity.inventory.InventoryJukeBoxCart;
import train.entity.inventory.InventoryZepp;
import train.entity.rollingStock.EntityJukeBoxCart;
import train.entity.zeppelin.AbstractZeppelin;
import train.library.GuiIDs;

import java.util.ArrayList;
import java.util.List;

public class CommonProxy implements IGuiHandler {
	public static List<MP3Player> playerList = new ArrayList<MP3Player>();

	public static WorldEvents eventManager = new WorldEvents();

	public void registerRenderInformation() {}

	public void registerEvents(FMLPreInitializationEvent event){
		FMLCommonHandler.instance().bus().register(eventManager);
		MinecraftForge.EVENT_BUS.register(eventManager);

	}


	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if(ID==GuiIDs.ZEPPELIN){
			Entity entity = player.ridingEntity;
			if(entity!=null) {
				return new InventoryZepp(player.inventory, (AbstractZeppelin) entity);
			}
		} else if (ID==GuiIDs.JUKEBOX){
			Entity entity1 = getEntity(world, x);
			if(entity1 instanceof EntityJukeBoxCart){
				return new InventoryJukeBoxCart(player.inventory, (EntityJukeBoxCart) entity1);
			}
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	public static Entity getEntity(World world, int entityId) {
		if ((world instanceof WorldServer)) {
			return world.getEntityByID(entityId);
		}
		return null;
	}

	public int addArmor(String armor) {
		return 0;
	}

	public Minecraft getMinecraft() {
		return null;
	}

	public void registerVillagerSkin(int villagerId, String textureName) {}

	public static void killAllStreams() {
		for (MP3Player p : playerList) {
			p.stop();
		}
	}

	public EntityPlayer getPlayer() {
		return null;
	}

	public float getJukeboxVolume() {
		return 0;
	}

	public void registerKeyBindingHandler() {}

	public void setHook() {}
	
}