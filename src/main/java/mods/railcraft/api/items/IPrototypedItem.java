/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.items;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * This interface marks an item that can have another item "added" to its NBT.
 * Filter Items and Tank Carts and Cargo Carts all do this.
 * The benefit is that PrototypeRecipe can then be used to set the prototype item.
 *
 *
 * Created by CovertJaguar on 6/7/2017 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface IPrototypedItem {
    default boolean isValidPrototype(ItemStack stack) {
        return true;
    }

    default ItemStack setPrototype(ItemStack filter, ItemStack prototype) {
        filter = filter.copy();
        NBTTagCompound nbt = new NBTTagCompound();
        prototype.writeToNBT(nbt);
        InvToolsAPI.setRailcraftDataSubtag(filter, "prototype", nbt);
        return filter;
    }

    default ItemStack getPrototype(ItemStack stack) {
        return InvToolsAPI.getRailcraftDataSubtag(stack, "prototype").map(ItemStack::new).orElse(ItemStack.EMPTY);
    }

}
