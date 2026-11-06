/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.items;

import mods.railcraft.api.core.RailcraftConstantsAPI;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Created by CovertJaguar on 6/7/2017 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public final class InvToolsAPI {
    private InvToolsAPI() {
    }

    @Contract("null -> true; !null -> _;")
    public static boolean isEmpty(@Nullable ItemStack stack) {
        return stack == null || stack.isEmpty();
    }

    @SuppressWarnings("SameReturnValue")
    public static ItemStack emptyStack() {
        return ItemStack.EMPTY;
    }

    public static Optional<NBTTagCompound> getRailcraftData(ItemStack stack, boolean create) {
        if (isEmpty(stack))
            return Optional.empty();
        return Optional.ofNullable(create ? stack.getOrCreateSubCompound(RailcraftConstantsAPI.MOD_ID) : stack.getSubCompound(RailcraftConstantsAPI.MOD_ID));
    }

    public static void clearRailcraftDataSubtag(ItemStack stack, String tag) {
        getRailcraftData(stack, false)
                .filter(nbt -> nbt.hasKey(tag))
                .ifPresent(nbt -> nbt.removeTag(tag));
    }

    public static void setRailcraftDataSubtag(ItemStack stack, String tag, NBTBase data) {
        if (isEmpty(stack)) {
            return;
        }
        getRailcraftData(stack, true).ifPresent(nbt -> nbt.setTag(tag, data));

    }

    public static Optional<NBTTagCompound> getRailcraftDataSubtag(ItemStack stack, String tag) {
        return getRailcraftDataSubtag(stack, tag, false);
    }

    public static Optional<NBTTagCompound> getRailcraftDataSubtag(ItemStack stack, String tag, boolean create) {
        return getRailcraftData(stack, create)
                .filter(nbt -> create || nbt.hasKey(tag))
                .map(nbt -> {
                    NBTTagCompound subNBT = nbt.getCompoundTag(tag);
                    nbt.setTag(tag, subNBT);
                    return subNBT;
                });
    }
}
