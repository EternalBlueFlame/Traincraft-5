/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.items;

import net.minecraft.init.MobEffects;

import net.minecraft.inventory.EntityEquipmentSlot;

import net.minecraft.util.math.BlockPos;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.library.ItemIDs;

public class ItemTCCompositeSuit extends ItemTCArmor {
	public ItemTCCompositeSuit(String iconName, ArmorMaterial material, int par3, int par4, int color) {
		super(iconName, material, par3, par4,color);
		setCreativeTab(Traincraft.tcTab);
		this.color = color;
		Traincraft.proxy.registerEvent(this);
	}

	/**
     * Called by RenderBiped and RenderPlayer to determine the armor texture that 
     * should be use for the currently equiped item.
     * This will only be called on instances of ItemArmor. 
     * 
     * Returning null from this function will use the default value.
     * 
     * @param stack ItemStack for the equpt armor
     * @param entity The entity wearing the armor
     * @param slot The slot the armor is in
     * @param type The subtype, can be null or "overlay"
     * @return Path of texture to bind, or null to use default
     */
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slotType, String type) {
		int slot = 3 - slotType.getIndex();
		if(stack.getItem() == ItemIDs.helmet_suit_paintable.item || stack.getItem() == ItemIDs.jacket_suit_paintable.item || stack.getItem() == ItemIDs.boots_suit_paintable.item){
			if(type!=null)return Info.resourceLocation+":"+Info.armorPrefix+"composite_suit_"+2+".png";
			return Info.resourceLocation+":"+Info.armorPrefix+"composite_suit_"+1+".png";
		}else if(stack.getItem() == ItemIDs.pants_suit_paintable.item){
			if(type!=null)return Info.resourceLocation+":"+Info.armorPrefix+"composite_suit_pants_"+2+".png";	
			return Info.resourceLocation+":"+Info.armorPrefix+"composite_suit_pants_"+1+".png";	
		}
		else{
			return super.getArmorTexture(stack, entity, slotType, type);
		}
	}

	/**
     * Return an item rarity from EnumRarity
     */
	@Override
	@SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.EPIC;
    }
	@Override
	public void onArmorTick(World world, EntityPlayer player,
			ItemStack stack) {
		super.onArmorTick(world, player, stack);
		updateTicks++;
		ItemStack armorHelmet = player.inventory.armorItemInSlot(3);
		if(armorHelmet!=null && armorHelmet.getItem() instanceof ItemTCCompositeSuit){
			ItemTCCompositeSuit itemarmor = (ItemTCCompositeSuit)armorHelmet.getItem();
			/**
			 * Helmet cures poison blindness, confusion
			 * allows water breathing
			 * and night vision
			 */
			if(itemarmor.getArmorMaterial() == Traincraft.instance.armorCompositeSuit){
				PotionEffect poison = player.getActivePotionEffect(MobEffects.POISON);
				PotionEffect wither = player.getActivePotionEffect(MobEffects.WITHER);
				PotionEffect blindness = player.getActivePotionEffect(MobEffects.BLINDNESS);
				PotionEffect confusion = player.getActivePotionEffect(MobEffects.NAUSEA);
				if(poison!=null){
					if (armorHelmet.getMaxDamage()-armorHelmet.getItemDamage()>5) {
						player.removePotionEffect(poison.getPotion());
						armorHelmet.damageItem(5, player);
					} else {
						armorHelmet.damageItem(armorHelmet.getMaxDamage()-armorHelmet.getItemDamage(), player);
					}
				}
				if(wither!=null){
					if (armorHelmet.getMaxDamage()-armorHelmet.getItemDamage()>5) {
						player.removePotionEffect(wither.getPotion());
						armorHelmet.damageItem(5, player);
					} else {
						armorHelmet.damageItem(armorHelmet.getMaxDamage()-armorHelmet.getItemDamage(), player);
					}
				}
				if(blindness!=null){
					if (armorHelmet.getMaxDamage()-armorHelmet.getItemDamage()>5) {
						player.removePotionEffect(blindness.getPotion());
						armorHelmet.damageItem(5, player);
					} else {
						armorHelmet.damageItem(armorHelmet.getMaxDamage()-armorHelmet.getItemDamage(), player);
					}
				}
				if(confusion!=null){
					if (armorHelmet.getMaxDamage()-armorHelmet.getItemDamage()>5) {
						player.removePotionEffect(confusion.getPotion());
						armorHelmet.damageItem(5, player);
					} else {
						armorHelmet.damageItem(armorHelmet.getMaxDamage()-armorHelmet.getItemDamage(), player);
					}
				}
				if(player.isInWater() && player.getActivePotionEffect(MobEffects.WATER_BREATHING)==null){
					if (armorHelmet.getMaxDamage()-armorHelmet.getItemDamage()>1) {
						player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 10 * 20, 0));
						armorHelmet.damageItem(1, player);
					} else {
						armorHelmet.damageItem(armorHelmet.getMaxDamage()-armorHelmet.getItemDamage(), player);
					}
				}
				//System.out.println(world.getLightFromNeighbors(new BlockPos((int)player.posX, (int)player.posY+(int)player.getEyeHeight(), (int)player.posZ)) +" "+world.isAirBlock(new net.minecraft.util.math.BlockPos((int)player.posX, (int)player.posY+(int)player.getEyeHeight(), (int)player.posZ)) +" "+world.containsAnyLiquid(player.boundingBox));
				if(!world.isRemote && world.getLightFromNeighbors(new BlockPos((int)player.posX, (int)player.posY+(int)player.getEyeHeight(), (int)player.posZ))<=4 && (world.isAirBlock(new BlockPos((int)player.posX, (int)player.posY+(int)player.getEyeHeight(), (int)player.posZ))||world.containsAnyLiquid(player.getEntityBoundingBox()))){
					if (armorHelmet.getMaxDamage()-armorHelmet.getItemDamage()>1) {
						if (player.getActivePotionEffect(MobEffects.NIGHT_VISION) ==null || player.getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration()<220) {
							player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 820, 0, true, true));
							armorHelmet.damageItem(1, player);
						}
					} else {
						armorHelmet.damageItem(armorHelmet.getMaxDamage()-armorHelmet.getItemDamage(), player);
					}
				}
			}
		}
		/**
		 * Chest heals half a heart every 100 ticks
		 */
		ItemStack armorChest = player.inventory.armorItemInSlot(2);
		if(armorChest!=null && armorChest.getItem() instanceof ItemTCCompositeSuit){
			ItemTCCompositeSuit itemarmor = (ItemTCCompositeSuit)armorChest.getItem();
			if(itemarmor.getArmorMaterial() == Traincraft.instance.armorCompositeSuit){
				if(player.getHealth()<player.getMaxHealth() && updateTicks%100==0){
					if (armorChest.getMaxDamage()-armorChest.getItemDamage()>1) {
						player.heal(1);
						armorChest.damageItem(1, player);
					} else {
						armorChest.damageItem(armorChest.getMaxDamage()-armorChest.getItemDamage(), player);
					}
				}
			}
		}
		/**
		 * pants are fire resistant
		 */
		ItemStack armorPants = player.inventory.armorItemInSlot(1);
		if(armorPants!=null && armorPants.getItem() instanceof ItemTCCompositeSuit){
			if(player.isBurning()){
				if (armorPants.getMaxDamage()-armorPants.getItemDamage()>1) {
					player.extinguish();
					armorPants.damageItem(1, player);
				} else {
					armorPants.damageItem(armorPants.getMaxDamage()-armorPants.getItemDamage(), player);
				}
			}
			/*if(itemarmor.getArmorMaterial() == Traincraft.instance.armorCompositeSuit && armorPants.isItemEnchantable()){
				armorPants.addEnchantment(Enchantment.fireProtection, 3);
			}*/
		}

	}

	/**
	 * Initially projected to have a jump boost
	 */
	@SubscribeEvent
	public void onEntityLivingJumpEvent(LivingEvent.LivingJumpEvent event)
	{
		if(event.getEntity() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.getEntity();
			ItemStack armor = player.inventory.armorItemInSlot(1);
			if(armor!=null && armor.getItem() instanceof ItemTCArmor){
				ItemTCArmor itemarmor = (ItemTCArmor)armor.getItem();
				if(itemarmor.getArmorMaterial() == Traincraft.instance.armorCompositeSuit){
					if (armor.getMaxDamage()-armor.getItemDamage()>5) {
						event.getEntity().motionY+=0.05;
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
	public void onEntityLivingFallEvent(LivingFallEvent event)
	{
		if(event.getEntity() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)event.getEntity();
			ItemStack armor = player.inventory.armorItemInSlot(0);
			if(armor!=null && armor.getItem() instanceof ItemTCCompositeSuit){
				ItemTCCompositeSuit itemarmor = (ItemTCCompositeSuit)armor.getItem();
				if(itemarmor.getArmorMaterial() == Traincraft.instance.armorCompositeSuit){
					if(event.getDistance()-3>0){
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

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return(par2ItemStack.getItem() == Items.DIAMOND);
	}
}
