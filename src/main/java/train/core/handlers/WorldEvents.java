package train.core.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.world.ChunkEvent;
import train.Traincraft;
import train.entity.ai.EntityAIFearHorn;
import train.entity.rollingStock.EntityJukeBoxCart;
import train.items.ItemTCArmor;
import train.items.ItemTCCompositeSuit;
import train.library.ItemIDs;

import java.util.Random;

public class WorldEvents{
	private int windTicker = 0;
	private static Random rand = new Random();
	public static int windStrength = 10 + rand.nextInt(10);

	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent handler){
		if(windTicker % 128 == 0){
			int upChance = 10;
			int downChance = 10;
			if (windStrength > 20) {
				upChance -= windStrength - 20;
			}
			else if (windStrength < 10) {
				downChance -= 10 - windStrength;
			}
			if (rand.nextInt(100) <= upChance) {
				windStrength += 1;
			}
			if (rand.nextInt(100) <= downChance) {
				windStrength -= 1;
			}
			windTicker=0;
		}
		windTicker++;
	}

	
	@SubscribeEvent
	public void entitySpawn(EntityJoinWorldEvent event) {
		if(event.entity instanceof EntityAnimal) {
			((EntityAnimal) event.entity).tasks.addTask(0, new EntityAIFearHorn(((EntityAnimal) event.entity)));
		}
	}

	@SubscribeEvent
	public void chunkUnloadEvent(ChunkEvent.Unload event){
		for(Object o : event.getChunk().entityLists){
			if (o instanceof EntityJukeBoxCart){
				((EntityJukeBoxCart) o).player.setVolume(0);
				((EntityJukeBoxCart) o).player.stop();
			}
		}
	}

	@SubscribeEvent
	public void onEntityLivingJumpEvent(LivingEvent.LivingJumpEvent event) {
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;
			ItemStack armor=null;
			for (ItemStack s : player.inventory.armorInventory){
				if (s!=null && s.getItem() == ItemIDs.boots_suit_paintable.item){
					armor=s; break;
				}
			}
			if(armor!=null && armor.getItem() instanceof ItemTCArmor && !player.isInWater()){
				ItemTCArmor itemarmor = (ItemTCArmor)armor.getItem();
				if(itemarmor.getArmorMaterial() == Traincraft.instance.armorCompositeSuit){
					if (armor.getMaxDamage()-armor.getItemDamage()>5) {
						event.entity.motionY+=0.05;
						armor.damageItem(5, player);
					} else {
						armor.damageItem(armor.getMaxDamage()-armor.getItemDamage(), player);
					}
				}
			}
		}
	}

	/**
	 * Boots protect against fall damage
	 */
	@SubscribeEvent
	public void onEntityLivingFallEvent(LivingFallEvent event) {
		if(event.entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.entity;
			ItemStack armor=null;
			for (ItemStack s : player.inventory.armorInventory){
				if (s!=null && s.getItem() == ItemIDs.boots_suit_paintable.item){
					armor=s; break;
				}
			}
			if(armor!=null && !player.isInWater()){
				ItemTCCompositeSuit itemarmor = (ItemTCCompositeSuit)armor.getItem();
				if(itemarmor.getArmorMaterial() == Traincraft.instance.armorCompositeSuit){
					if(event.distance-3>0){
						if (armor.getMaxDamage()-armor.getItemDamage()>5) {
							armor.damageItem(5, player);
							event.setCanceled(true);
						} else {
							armor.damageItem(armor.getMaxDamage()-armor.getItemDamage(), player);
						}
					}
				}
			}
		}

	}
}
