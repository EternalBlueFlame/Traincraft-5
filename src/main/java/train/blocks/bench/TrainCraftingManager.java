package train.blocks.bench;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import train.core.interfaces.ITCRecipe;

import java.util.ArrayList;
import java.util.List;

public class TrainCraftingManager {
	/**
	 * The static instance of this class
	 */
	public static final TrainCraftingManager instance = new TrainCraftingManager();

	/**
	 * A list of all the recipes added
	 */
	public List recipes = new ArrayList();


	public static final TrainCraftingManager getInstance() {
		return instance;
	}

	private TrainCraftingManager() {
	}


	public ItemStack func_82787_a(IInventory inv) {
		int occupedSlot = 0;
		ItemStack var3 = null;
		ItemStack var4 = null;

		//what does this do? something about repairing
		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			ItemStack var6 = inv.getStackInSlot(i);

			if (var6 != null) {
				if (occupedSlot == 0) {
					var3 = var6;
				}

				if (occupedSlot == 1) {
					var4 = var6;
				}
				++occupedSlot;
			}
		}

		//first part also has to do with repairing
		if (occupedSlot == 2 && var3.getItem() == var4.getItem() && var3.getCount() == 1 && var4.getCount() == 1 && var3.getItem().isRepairable()) {
			Item var11 = var3.getItem();
			int var10 = var11.getMaxDamage() - var3.getItemDamage();
			int var7 = var11.getMaxDamage() - var4.getItemDamage();
			int var8 = var10 + var7 + var11.getMaxDamage() * 10 / 100;
			int var9 = var11.getMaxDamage() - var8;

			if (var9 < 0) {
				var9 = 0;
			}
			return new ItemStack(var3.getItem(), 1, var9);
		} else {
			for (int i = 0; i < this.recipes.size(); ++i) {
				ITCRecipe recipe = (ITCRecipe) this.recipes.get(i);
				if (recipe.matches(inv)) {
					return recipe.getCraftingResult(inv);
				}
			}
			return null;
		}
	}
}
