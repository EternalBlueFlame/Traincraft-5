package train.common.core.util;

import net.minecraft.item.ItemStack;

/**
 * 1.7.10 used {@code null} for "no item"; 1.12 requires {@link ItemStack#EMPTY} anywhere vanilla code
 * reads a stack (Container sync, slots, hoppers). Traincraft's inventories still keep null internally;
 * every IInventory/Container boundary converts through {@link #nn}.
 */
public final class StackUtil {
    private StackUtil() {}

    public static ItemStack nn(ItemStack stack) {
        return stack == null ? ItemStack.EMPTY : stack;
    }

    public static boolean isEmpty(ItemStack stack) {
        return stack == null || stack.isEmpty();
    }
}
