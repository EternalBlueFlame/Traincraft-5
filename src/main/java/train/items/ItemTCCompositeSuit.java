/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import train.Traincraft;
import train.library.Info;
import train.library.ItemIDs;

public class ItemTCCompositeSuit extends ItemTCArmor {
	public ItemTCCompositeSuit(String iconName, ArmorMaterial material, int par3, int par4, int color) {
		super(iconName, material, par3, par4,color);
		setCreativeTab(Traincraft.tcTab);
		this.color = color;
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
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type){
		if(stack.getItem() == ItemIDs.helmet_suit_paintable.item || stack.getItem() == ItemIDs.jacket_suit_paintable.item || stack.getItem() == ItemIDs.boots_suit_paintable.item){
			if(type!=null)return Info.resourceLocation+":"+Info.armorPrefix+"composite_suit_"+2+".png";
			return Info.resourceLocation+":"+Info.armorPrefix+"composite_suit_"+1+".png";
		}else if(stack.getItem() == ItemIDs.pants_suit_paintable.item){
			if(type!=null)return Info.resourceLocation+":"+Info.armorPrefix+"composite_suit_pants_"+2+".png";	
			return Info.resourceLocation+":"+Info.armorPrefix+"composite_suit_pants_"+1+".png";	
		}
		else{
			return super.getArmorTexture(stack, entity, slot, type);
		}
	}

	/**
     * Return an item rarity from EnumRarity
     */
	@Override
	@SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.epic;
    }
	@Override
	public void onArmorTick(World world, EntityPlayer player,
			ItemStack stack) {
		super.onArmorTick(world, player, stack);
		updateTicks++;
		ItemStack armorHelmet=null;
		for (ItemStack s : player.inventory.armorInventory){
			if (s!=null && s.getItem() == ItemIDs.helmet_suit_paintable.item){
				armorHelmet=s; break;
			}
		}
		if(armorHelmet!=null){
			ItemTCCompositeSuit itemarmor = (ItemTCCompositeSuit)armorHelmet.getItem();
			/**
			 * Helmet cures poison blindness, confusion
			 * allows water breathing
			 * and night vision
			 */
			if(itemarmor.getArmorMaterial() == Traincraft.instance.armorCompositeSuit){
				PotionEffect poison = player.getActivePotionEffect(Potion.poison);
				PotionEffect wither = player.getActivePotionEffect(Potion.wither);
				PotionEffect blindness = player.getActivePotionEffect(Potion.blindness);
				PotionEffect confusion = player.getActivePotionEffect(Potion.confusion);
				if(poison!=null){
					if (armorHelmet.getMaxDamage()-armorHelmet.getItemDamage()>5) {
						player.removePotionEffect(poison.getPotionID());
						armorHelmet.damageItem(5, player);
					} else {
						armorHelmet.damageItem(armorHelmet.getMaxDamage()-armorHelmet.getItemDamage(), player);
					}
				}
				if(wither!=null){
					if (armorHelmet.getMaxDamage()-armorHelmet.getItemDamage()>5) {
						player.removePotionEffect(wither.getPotionID());
						armorHelmet.damageItem(5, player);
					} else {
						armorHelmet.damageItem(armorHelmet.getMaxDamage()-armorHelmet.getItemDamage(), player);
					}
				}
				if(blindness!=null){
					if (armorHelmet.getMaxDamage()-armorHelmet.getItemDamage()>5) {
						player.removePotionEffect(blindness.getPotionID());
						armorHelmet.damageItem(5, player);
					} else {
						armorHelmet.damageItem(armorHelmet.getMaxDamage()-armorHelmet.getItemDamage(), player);
					}
				}
				if(confusion!=null){
					if (armorHelmet.getMaxDamage()-armorHelmet.getItemDamage()>5) {
						player.removePotionEffect(confusion.getPotionID());
						armorHelmet.damageItem(5, player);
					} else {
						armorHelmet.damageItem(armorHelmet.getMaxDamage()-armorHelmet.getItemDamage(), player);
					}
				}
				if(player.isInWater() && player.getActivePotionEffect(Potion.waterBreathing)==null){
					if (armorHelmet.getMaxDamage()-armorHelmet.getItemDamage()>1) {
						player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 10 * 20, 0));
						armorHelmet.damageItem(1, player);
					} else {
						armorHelmet.damageItem(armorHelmet.getMaxDamage()-armorHelmet.getItemDamage(), player);
					}
				}
				//System.out.println(world.getBlockLightValue((int)player.posX, (int)player.posY+(int)player.getEyeHeight(), (int)player.posZ) +" "+world.isAirBlock((int)player.posX, (int)player.posY+(int)player.getEyeHeight(), (int)player.posZ) +" "+world.isAnyLiquid(player.boundingBox));
				if(!world.isRemote && world.getBlockLightValue((int)player.posX, (int)player.posY+(int)player.getEyeHeight(), (int)player.posZ)<=4 && (world.isAirBlock((int)player.posX, (int)player.posY+(int)player.getEyeHeight(), (int)player.posZ)||world.isAnyLiquid(player.boundingBox))){
					if (armorHelmet.getMaxDamage()-armorHelmet.getItemDamage()>1) {
						if (player.getActivePotionEffect(Potion.nightVision) ==null || player.getActivePotionEffect(Potion.nightVision).getDuration()<220) {
							player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 820, 0, true));
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
		ItemStack armorChest=null;
		for (ItemStack s : player.inventory.armorInventory){
			if (s!=null && s.getItem() == ItemIDs.jacket_suit_paintable.item){
				armorChest=s; break;
			}
		}
		if(armorChest!=null){
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

		ItemStack armorPants=null;
		for (ItemStack s : player.inventory.armorInventory){
			if (s!=null && s.getItem() == ItemIDs.pants_suit_paintable.item){
				armorPants=s; break;
			}
		}
		if(armorPants!=null){
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


	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return(par2ItemStack.getItem() == Items.diamond);
	}
}
