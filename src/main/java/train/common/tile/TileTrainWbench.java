/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import train.common.api.blocks.TileTraincraft;
import train.common.library.Info;

public class TileTrainWbench extends TileTraincraft {

	public TileTrainWbench() {
		super(9);
	}

	@Override
	public String getInventoryName() {

		return "TrainWorkbench";
	}

	@Override
	public boolean hasCustomInventoryName() {

		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {

		return true;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getTexture(int x, int y, int z){
		return texture;
	}


	static final ResourceLocation texture = new ResourceLocation(Info.modID, "textures/blocks/train_table.png");

}