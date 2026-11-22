/*******************************************************************************
 * Copyright (c) 2013 Spitfire4466. All rights reserved.
 * 
 * @name TrainCraft
 * @author Spitfire4466
 ******************************************************************************/

package train.common.items;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockOWOYardSwitch extends ItemBlock {

	public ItemBlockOWOYardSwitch(Block id) {
		super(id);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> par3List, ITooltipFlag flagIn) {
		par3List.add("\u00a77" + "Designed for the fictional rail line on JCIR.");
	}
}
