package net.minecraft.stats;

import net.minecraft.item.ItemStack;

public class Achievement extends StatBase {
	public Achievement(String id, String name, int column, int row, ItemStack stack, Achievement parent) {
		super(id, null);
	}

	public Achievement initIndependentStat() {
		return this;
	}

	public Achievement registerStat() {
		return this;
	}

	public Achievement setSpecial() {
		return this;
	}
}
