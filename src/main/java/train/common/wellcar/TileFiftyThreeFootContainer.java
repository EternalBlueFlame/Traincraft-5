package train.common.wellcar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import train.common.api.blocks.TileTraincraft;

import java.util.ArrayList;

public class TileFiftyThreeFootContainer extends TileTraincraft {
    public ItemStack[] inventory;
    public int directionPlaced;
    public int currentColor = 0;

    public TileFiftyThreeFootContainer() {
        super(45);
        this.inventory = this.slots;
    }

    public TileFiftyThreeFootContainer(int pl) {
        super(45);
        this.inventory = this.slots;
        this.directionPlaced = pl;
    }

    @Override
    public String getInventoryName() {
        return "53 Foot Container";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack removed = super.decrStackSize(slot, amount);
        if (removed != null) {
            markDirty();
        }
        return removed;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack removed = super.getStackInSlotOnClosing(slot);
        if (removed != null) {
            markDirty();
        }
        return removed;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (slot < 0 || slot >= getSizeInventory()) {
            return;
        }
        if (stack != null && stack.stackSize == 0) {
            stack = null;
        }
        super.setInventorySlotContents(slot, stack);
        markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        inventory = slots;
        currentColor = nbt.getInteger("currentColor");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("currentColor", currentColor);
        nbt.setString("currentColorString", getAvailableColors().get(currentColor));
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return true;
    }

    public ArrayList<String> getAvailableColors() {
        ArrayList<String> leColors = new ArrayList<String>();
        leColors.add("generic");
        leColors.add("PS");
        leColors.add("cosco");
        leColors.add("BN1");
        leColors.add("BN2");
        leColors.add("mannco");
        return leColors;
    }

    public void goToNextColor() {
        if (getAvailableColors().size() > 0) {
            if (currentColor > getAvailableColors().size() -5) {
                currentColor = 0;
            } else {
                currentColor++;
            }
        }

    }

}

