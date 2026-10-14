package train.client.core.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * Recipes built from the ore dictionary contain "any metadata" stacks (meta 32767, e.g. plankWood, logWood).
 * 1.7.10 drew those with the icon of meta 0, 1.12 has no model for meta 32767 and draws the pink/black
 * missing texture. This picks a real variant to show instead, cycling through them once a second like JEI/NEI.
 */
@SideOnly(Side.CLIENT)
public final class DisplayStack {

    private static final Map<Item, NonNullList<ItemStack>> VARIANTS = new HashMap<>();

    private DisplayStack() {}

    public static ItemStack of(ItemStack stack) {
        if (stack == null || stack.isEmpty() || stack.getMetadata() != OreDictionary.WILDCARD_VALUE) {
            return stack;
        }
        NonNullList<ItemStack> variants = VARIANTS.computeIfAbsent(stack.getItem(), item -> {
            NonNullList<ItemStack> list = NonNullList.create();
            try {
                item.getSubItems(CreativeTabs.SEARCH, list);
            } catch (Throwable ignored) {
                list.clear();
            }
            return list;
        });
        ItemStack shown;
        if (variants.isEmpty()) {
            shown = new ItemStack(stack.getItem(), stack.getCount(), 0);
        } else {
            shown = variants.get((int) ((System.currentTimeMillis() / 1000L) % variants.size())).copy();
            shown.setCount(stack.getCount());
        }
        if (stack.hasTagCompound() && !shown.hasTagCompound()) {
            shown.setTagCompound(stack.getTagCompound().copy());
        }
        return shown;
    }
}
