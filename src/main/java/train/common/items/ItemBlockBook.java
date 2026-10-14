/*******************************************************************************
 * Copyright (c) 2013 Mrbrutal. All rights reserved.
 * 
 * @name Traincraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.items;
import train.common.Traincraft;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumActionResult;

import net.minecraft.util.ActionResult;

import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class ItemBlockBook extends ItemBlock {

	public ItemBlockBook(Block par1) {
		super(par1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> list, ITooltipFlag flag) {
		boolean par4 = flag.isAdvanced(); EntityPlayer player = Traincraft.proxy.getPlayer();
		list.add("\u00a77" + "Book 2, WIP");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.COMMON;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		NBTTagCompound var3 = stack.getTagCompound();
		if (var3 == null) {
			var3 = new NBTTagCompound();
			stack.setTagCompound(var3);
			stack.getTagCompound().setInteger("currPage", 0);
			stack.getTagCompound().setInteger("currRecipe", 0);
			stack.getTagCompound().setInteger("open", 1);
		}
		if (!world.isRemote)
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
		//player.openGui(Traincraft.instance, GuiIDs.RECIPE_BOOK2, world, (int) player.posX, (int) player.posY, (int) player.posZ);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}
}
