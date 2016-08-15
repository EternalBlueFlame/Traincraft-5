/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.api.crafting;

import net.minecraft.item.ItemStack;

import java.util.List;

public abstract interface ITierCraftingManager {

	public abstract void addRecipe(ItemStack planks, ItemStack wheels, ItemStack frame, ItemStack coupler, ItemStack chimney, ItemStack cab, ItemStack boiler, ItemStack firebox, ItemStack additional, ItemStack dye, ItemStack output);

	public abstract void addRecipe(int tier, ItemStack planks, ItemStack wheels, ItemStack frame, ItemStack coupler, ItemStack chimney, ItemStack cab, ItemStack boiler, ItemStack firebox, ItemStack additional, ItemStack dye, ItemStack output);

	public abstract void addRecipe(ItemStack planks, ItemStack wheels, ItemStack frame, ItemStack coupler, ItemStack chimney, ItemStack cab, ItemStack boiler, ItemStack firebox, ItemStack additional, ItemStack dye, ItemStack output, int outputSize);

	public abstract void addRecipe(int tier, ItemStack planks, ItemStack wheels, ItemStack frame, ItemStack coupler, ItemStack chimney, ItemStack cab, ItemStack boiler, ItemStack firebox, ItemStack additional, ItemStack dye, ItemStack output, int outputSize);

	public abstract ITierRecipe getRecipe(ItemStack output);

	public abstract ITierRecipe getTierRecipe(int tier, ItemStack output);

	public abstract List<?> getRecipeList();

	public abstract List<?> getTierRecipeList(int tier);

	public abstract int getRecipeSize();

	public abstract int getRecipeTierSize(int tier);
}
