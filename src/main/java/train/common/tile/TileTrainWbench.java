/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.tile;

import train.common.core.util.StackUtil;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraft.util.EnumFacing;
import train.common.api.blocks.TileRenderFacing;
import train.common.library.Info;
import ebf.tim.utility.CommonUtil;

public class TileTrainWbench extends TileRenderFacing implements IInventory {

	private ItemStack[] workbenchItemStacks;
	private EnumFacing facing;

	public TileTrainWbench() {

		workbenchItemStacks = new ItemStack[9];
	}

	@Override
	public void closeInventory(EntityPlayer p) {}

	@Override
	public String getName() {

		return "TrainWorkbench";
	}

	@Override
	public boolean hasCustomName() {

		return false;
	}

	@Override
	public void openInventory(EntityPlayer p) {}

	@Override
	public int getSizeInventory() {

		return this.workbenchItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {

		return StackUtil.nn(this.workbenchItemStacks[i]);
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {

		if (workbenchItemStacks[i] != null) {

			if (workbenchItemStacks[i].getCount() <= j) {

				ItemStack itemstack = workbenchItemStacks[i];
				workbenchItemStacks[i] = null;

				return StackUtil.nn(itemstack);
			}

			ItemStack itemstack1 = workbenchItemStacks[i].splitStack(j);

			if (workbenchItemStacks[i].getCount() == 0) {

				workbenchItemStacks[i] = null;
			}

			return StackUtil.nn(itemstack1);
		}
		else {

			return ItemStack.EMPTY;
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int i) {

		if (this.workbenchItemStacks[i] != null) {

			ItemStack stack = this.workbenchItemStacks[i];
			this.workbenchItemStacks[i] = null;

			return StackUtil.nn(stack);
		}
		else {

			return ItemStack.EMPTY;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack) { if (stack != null && stack.isEmpty()) stack = null;

		workbenchItemStacks[i] = stack;

		if (stack != null && stack.getCount() > getInventoryStackLimit()) {

			stack.setCount(getInventoryStackLimit());
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTag) {

		super.readFromNBT(nbtTag);

		facing = EnumFacing.byIndex(nbtTag.getByte("Orientation"));
		NBTTagList tagList = nbtTag.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		workbenchItemStacks = new ItemStack[getSizeInventory()];

		for (int i = 0; i < tagList.tagCount(); ++i) {

			NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
			byte slot = tagCompound.getByte("Slot");

			if (slot >= 0 && slot < workbenchItemStacks.length) {

				workbenchItemStacks[slot] = new ItemStack(tagCompound);
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbtTag) {

		super.writeToNBT(nbtTag);

		if (facing != null) {

			nbtTag.setByte("Orientation", (byte) facing.ordinal());
		}
		else {

			nbtTag.setByte("Orientation", (byte) EnumFacing.NORTH.ordinal());
		}

		NBTTagList tagList = new NBTTagList();

		for (int i = 0; i < workbenchItemStacks.length; ++i) {

			if (workbenchItemStacks[i] != null) {

				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) i);
				workbenchItemStacks[i].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}

		nbtTag.setTag("Items", tagList);
		return nbtTag;
	}

	@Override
	public int getInventoryStackLimit() {

		return 64;
	}


	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {

		if (world == null || CommonUtil.getTileEntity(world, getPos().getX(), getPos().getY(), getPos().getZ()) != this) {

			return false;
		}

		return player.getDistanceSq(getPos().getX() + 0.5D, getPos().getY() + 0.5D, getPos().getZ() + 0.5D) <= 64.0D;
	}



	/** 1.12 sends this with the chunk data (1.7.10 sent the description packet instead). */
	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {

		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);

		return new SPacketUpdateTileEntity(getPos(), 1, nbt);
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {

		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getTexture(int x, int y, int z){
		return texture;
	}


	static final ResourceLocation texture = new ResourceLocation(Info.modID, "textures/blocks/train_table.png");


	/* 1.12 IInventory methods */
	@Override
	public void clear() {
		for (int i = 0; i < getSizeInventory(); i++) {
			setInventorySlotContents(i, ItemStack.EMPTY);
		}
	}

	/* 1.12 IInventory methods */
	@Override
	public int getFieldCount() {
		return 0;
	}

	/* 1.12 IInventory methods */
	@Override
	public void setField(int id, int value) {
	}

	/* 1.12 IInventory methods */
	@Override
	public int getField(int id) {
		return 0;
	}

	/* 1.12 IInventory methods */
	@Override
	public boolean isEmpty() {
		for (int i = 0; i < getSizeInventory(); i++) {
			ItemStack s = getStackInSlot(i);
			if (s != null && !s.isEmpty()) return false;
		}
		return true;
	}
}