package net.minecraft.client.renderer.entity;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class RenderItem {
	public boolean shouldBob() {
		return true;
	}

	public void setRenderManager(RenderManager manager) {
	}

	public void doRender(EntityItem entity, double x, double y, double z, float yaw, float partialTicks) {
	}

	public void renderItemIntoGUI(FontRenderer fontRenderer, TextureManager textureManager, ItemStack stack, int x, int y) {
	}

	public void renderItemOverlayIntoGUI(FontRenderer fontRenderer, TextureManager textureManager, ItemStack stack, int x, int y) {
	}

	public void renderItemOverlayIntoGUI(FontRenderer fontRenderer, TextureManager textureManager, ItemStack stack, int x, int y, String text) {
	}

	public void renderItemAndEffectIntoGUI(FontRenderer fontRenderer, TextureManager textureManager, ItemStack stack, int x, int y) {
	}
}
