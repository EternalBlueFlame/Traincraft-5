package train.common.core.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/** Replacements for the 1.7.10 {@code GameRegistry.findItem/findBlock/findItemStack} lookups. */
public final class LegacyRegistry {
    private LegacyRegistry() {
    }

    /** @return the item, or {@link ItemStack#EMPTY} if that mod/item isn't there */
    public static ItemStack findItemStack(String modId, String name, int stackSize) {
        Item item = Item.REGISTRY.getObject(new ResourceLocation(modId.toLowerCase(), name));
        if (item == null) {
            Block block = Block.REGISTRY.containsKey(new ResourceLocation(modId.toLowerCase(), name)) ? Block.REGISTRY.getObject(new ResourceLocation(modId.toLowerCase(), name)) : null;
            item = block == null ? null : Item.getItemFromBlock(block);
        }
        return item == null ? ItemStack.EMPTY : new ItemStack(item, stackSize);
    }

    public static Item findItem(String modId, String name) {
        return Item.REGISTRY.getObject(new ResourceLocation(modId.toLowerCase(), name));
    }
}
