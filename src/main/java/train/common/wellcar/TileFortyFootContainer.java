package train.common.wellcar;

import train.common.core.util.StackUtil;

import ebf.tim.utility.CommonUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;

public class TileFortyFootContainer extends TileEntity implements IInventory {

	// 1.12: modded tile entities are dropped on any block state change by default; only drop them when the block changes
	@Override
	public boolean shouldRefresh(net.minecraft.world.World world, net.minecraft.util.math.BlockPos pos, net.minecraft.block.state.IBlockState oldState, net.minecraft.block.state.IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

    public ItemStack[] inventory;
    public int directionPlaced;
    public int currentColor = 0;

    public TileFortyFootContainer() {
        this.inventory = new ItemStack[this.getSizeInventory()];
    }

    public TileFortyFootContainer(int pl) {
        this.inventory = new ItemStack[this.getSizeInventory()];
        this.directionPlaced = pl;
    }



    @Override
    public int getSizeInventory() {
        return 36;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= this.getSizeInventory())
            return ItemStack.EMPTY;
        return StackUtil.nn(this.inventory[index]);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (!CommonUtil.isItemStackEmpty(this.getStackInSlot(index))) {
            ItemStack itemstack;

            if (this.getStackInSlot(index).getCount() <= count) {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, ItemStack.EMPTY);
                this.markDirty();
                return StackUtil.nn(itemstack);
            } else {
                itemstack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).getCount() <= 0) {
                    this.setInventorySlotContents(index, ItemStack.EMPTY);
                } else {
                    //Just to show that changes happened
                    this.setInventorySlotContents(index, this.getStackInSlot(index));
                }

                this.markDirty();
                return StackUtil.nn(itemstack);
            }
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = this.getStackInSlot(index);
        this.setInventorySlotContents(index, ItemStack.EMPTY);
        return StackUtil.nn(stack);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) { if (stack != null && stack.isEmpty()) stack = null;
        if (index < 0 || index >= this.getSizeInventory())
            return;

        if (stack != null && stack.getCount() > this.getInventoryStackLimit())
            stack.setCount(this.getInventoryStackLimit());

        if (stack != null && stack.getCount() == 0)
            stack = null;

        this.inventory[index] = stack;
        this.markDirty();
    }



    @Override
    public String getName() {
        return "40 Foot Container";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer p) {

    }

    @Override
    public void closeInventory(EntityPlayer p) {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        NBTTagList list = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        inventory = new ItemStack[getSizeInventory()];

        for (int i = 0; i < list.tagCount(); ++i) { NBTTagCompound comp = list.getCompoundTagAt(i); int j = comp.getByte("Slot") & 255; if (j >= 0 && j < inventory.length)
        {
            inventory[j] = new ItemStack(comp);
        }

        }
        currentColor = nbt.getInteger("currentColor");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList list = new NBTTagList();

        for (int i = 0; i < inventory.length; ++i)
        {
            if (inventory[i] != null)
            {
                NBTTagCompound comp = new NBTTagCompound();
                comp.setByte("Slot", (byte)i);
                inventory[i].writeToNBT(comp);
                list.appendTag(comp);
            }
        }

        nbt.setTag("Items", list);
        nbt.setInteger("currentColor", currentColor);
        nbt.setString("currentColorString", getAvailableColors().get(currentColor));
    	return nbt;
    }

    public ArrayList<String> getAvailableColors() {
        ArrayList<String> leColors = new ArrayList<String>();
        leColors.add("flag");
        leColors.add("generic");
        leColors.add("Honex");
        return leColors;
    }

    public void goToNextColor() {
        if (getAvailableColors().size() > 0) {
            if (currentColor > getAvailableColors().size() -2) {
                currentColor = 0;
            } else {
                currentColor++;
            }
        }

    }


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

