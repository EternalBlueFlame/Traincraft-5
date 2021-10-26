/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.blocks.bench;

import ebf.tim.blocks.BlockDynamic;
import ebf.tim.blocks.TileEntityStorage;
import ebf.tim.utility.ItemStackSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class TileTrainWbench extends TileEntityStorage {

	public TileTrainWbench(){}

	public TileTrainWbench(BlockDynamic host) {
		super(host);
		initInventoryFromBlock( host );
	}

	protected void initInventoryFromBlock( BlockDynamic block )
	{
		if ( host == null )
			super.initInventoryFromBlock( block );

		inventory=new ArrayList<>();

		int var6;
		int var7;

		for (var6 = 0; var6 < 3; ++var6) {
			for (var7 = 0; var7 < 3; ++var7) {
				inventory.add(new ItemStackSlot(this, 400+var7 + (var6 * 3), 30 + var7 * 18, 17 + var6 * 18));
			}
		}

		//add result slot after input slots
		inventory.add(new ItemStackSlot(this, 409, 124, 35));
	}

	@Override
	public String getInventoryName() {
		return "TrainWorkbench";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}


	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (worldObj == null || worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}

		return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
}