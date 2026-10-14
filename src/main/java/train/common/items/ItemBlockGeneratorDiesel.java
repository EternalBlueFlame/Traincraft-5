/*******************************************************************************
 * Copyright (c) 2013 Spitfire4466. All rights reserved.
 * 
 * @name TrainCraft
 * @author Spitfire4466
 ******************************************************************************/

package train.common.items;
import train.common.Traincraft;

import net.minecraft.world.World;

import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockGeneratorDiesel extends ItemBlock {

	public ItemBlockGeneratorDiesel(Block id) {
		super(id);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, @Nullable World world, List<String> par3List, ITooltipFlag flag) {
		boolean par4 = flag.isAdvanced(); EntityPlayer par2EntityPlayer = Traincraft.proxy.getPlayer();
		par3List.add("\u00a77" + "RF generator.");
		par3List.add("\u00a77" + "Max Production: 70 RF/t.");
		par3List.add("\u00a77" + "Don't place 2 together.");
	}

	/**
	 * Return an item rarity from EnumRarity
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.RARE;
	}
}
