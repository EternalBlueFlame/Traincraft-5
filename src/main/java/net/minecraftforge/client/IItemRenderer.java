package net.minecraftforge.client;

import net.minecraft.item.ItemStack;

public interface IItemRenderer {
	enum ItemRenderType {
		ENTITY,
		EQUIPPED,
		EQUIPPED_FIRST_PERSON,
		INVENTORY,
		FIRST_PERSON_MAP
	}

	enum ItemRendererHelper {
		ENTITY_ROTATION,
		ENTITY_BOBBING,
		EQUIPPED_BLOCK,
		BLOCK_3D,
		INVENTORY_BLOCK
	}

	boolean handleRenderType(ItemStack item, ItemRenderType type);

	boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper);

	void renderItem(ItemRenderType type, ItemStack item, Object... data);
}
