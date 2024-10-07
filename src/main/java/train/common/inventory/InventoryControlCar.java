package train.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import train.common.api.AbstractControlCar;
import train.common.api.EntityRollingStock;


public class InventoryControlCar extends Container
{
    private AbstractControlCar controlCar;
    private InventoryPlayer player;
    private int inventorySize;

    public InventoryControlCar(InventoryPlayer iinventory, EntityRollingStock entityminecart)
    {
        player = iinventory;
        controlCar = (AbstractControlCar) entityminecart;
        inventorySize = ((AbstractControlCar) entityminecart).inventorySize;
        int i = 1;

        // region Adds the slots for the Control Car
        // This Slot is typically the slot for fuel in a locomotive

        // Disabling this slot for the time being until it can either be removed from the GUI or A Special Item goes here
        //addSlotToContainer(new Slot((IInventory) entityminecart, 0, 8, 53));

        for (int j = 0; j < controlCar.numCargoSlots; j++) {
            addSlotToContainer(new Slot((IInventory) entityminecart, i, 80 + j * 18, 18));
            i++;
        }
        for (int k = 0; k < controlCar.numCargoSlots1; k++) {
            addSlotToContainer(new Slot((IInventory) entityminecart, i, 80 + k * 18, 36));
            i++;
        }
        for (int l = 0; l < controlCar.numCargoSlots2; l++) {
            addSlotToContainer(new Slot((IInventory) entityminecart, i, 80 + l * 18, 54));
            i++;
        }
        // endregion Adds the slots for the Control Car

        // region Adds the slots for the player inventory section
        for (int i1 = 0; i1 < 3; i1++)
        {
            for (int k1 = 0; k1 < 9; k1++)
            {
                addSlotToContainer(new Slot(iinventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 84 + i1 * 18));
            }
        }
        for (int j1 = 0; j1 < 9; j1++)
        {
            addSlotToContainer(new Slot(iinventory, j1, 8 + j1 * 18, 142));
        }
        // endregion Adds the slots for the player inventory section
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return !controlCar.isDead;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i) {
        Slot slot = (Slot) inventorySlots.get(i);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            if (i < inventorySize)
            {
                if (!mergeItemStack(itemstack1, inventorySize, inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (i > inventorySize)
            {
                if (!mergeItemStack(itemstack1, 2, inventorySize, false))
                {
                    return null;
                }
            }
            else if (!mergeItemStack(itemstack1, 2, inventorySize, false))
            {
                return null;
            }
            if (itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            }
            else
            {
                slot.onSlotChanged();
            }
            return itemstack1;
        }
        else
        {
            return null;
        }
    }
}