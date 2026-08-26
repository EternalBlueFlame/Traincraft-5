package net.minecraftforge.client.model;

import net.minecraft.util.ResourceLocation;

public class AdvancedModelLoader {
	public static IModelCustom loadModel(ResourceLocation resource) {
		return new IModelCustom() {
			@Override
			public void renderAll() {
			}

			@Override
			public void renderPart(String partName) {
			}
		};
	}
}
