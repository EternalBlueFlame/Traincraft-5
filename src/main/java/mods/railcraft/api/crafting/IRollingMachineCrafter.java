/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * This interface allows you to interact with the Rolling Machine Crafting Manager.
 *
 * Be aware that Rolling Machine won't accept any unstackable items or items which return containers on crafting.
 * So no fluid crafting.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface IRollingMachineCrafter {

    int DEFAULT_PROCESS_TIME = 100;

    default IRollingMachineRecipeBuilder newRecipe(ItemStack output) {
        return new IRollingMachineRecipeBuilder() {};
    }

    /**
     * Given a specific combination of ItemStacks, this function will try to provide a matching recipe.
     *
     * @param inventoryCrafting the crafting inventory
     * @param world             the world
     * @return the matching recipe
     */
    default Optional<IRollingRecipe> getRecipe(InventoryCrafting inventoryCrafting, World world) {
        return Optional.empty();
    }

    /**
     * You can remove recipes from this list, but do not add them, it will throw an UnsupportedOperationException.
     */
    default List<IRollingRecipe> getRecipes() {
        return Collections.emptyList();
    }

    interface IRollingRecipe extends IRecipe {
        int getTickTime();
    }

    interface IRollingMachineRecipeBuilder extends
            IRecipeBuilder<IRollingMachineRecipeBuilder>,
            IRecipeBuilder.ISingleItemStackOutputFeature<IRollingMachineRecipeBuilder>,
            IRecipeBuilder.ITimeFeature<IRollingMachineRecipeBuilder> {

        default IRollingMachineRecipeBuilder group(ResourceLocation group) {
            return this;
        }

        default void recipe(IRecipe recipe) {}

        default void shaped(Object... components) {}

        default void shapeless(Object... components) {}
    }

}
