package train.common.core.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import train.common.Traincraft;
import train.common.library.Info;

import java.util.HashSet;
import java.util.Set;

/**
 * Drop-in replacement for 1.7.10's {@code GameRegistry.addRecipe(output, params...)} and
 * {@code GameRegistry.addShapelessRecipe(output, params...)}.
 * <p>
 * In 1.12 every recipe needs a unique registry name; it is derived from the output item
 * ({@code tc:<item>_<meta>}, with a numeric suffix for duplicates). Ingredients may be items, blocks,
 * item stacks or ore dictionary names, as before.
 */
public final class TCRecipes {
    public static final ResourceLocation GROUP = new ResourceLocation(Info.modID, "traincraft");
    private static final Set<String> USED_NAMES = new HashSet<>();

    private TCRecipes() {
    }

    private static ItemStack toStack(Object o) {
        if (o instanceof ItemStack) return (ItemStack) o;
        if (o instanceof Item) return new ItemStack((Item) o);
        if (o instanceof Block) return new ItemStack((Block) o);
        return ItemStack.EMPTY;
    }

    private static ResourceLocation nameFor(ItemStack output) {
        String base = "recipe";
        if (!output.isEmpty() && output.getItem().getRegistryName() != null) {
            base = output.getItem().getRegistryName().getPath() + "_" + output.getMetadata();
        }
        base = base.toLowerCase().replaceAll("[^a-z0-9_./-]", "_");
        String name = base;
        int i = 1;
        while (!USED_NAMES.add(name)) {
            name = base + "_" + (++i);
        }
        return new ResourceLocation(Info.modID, name);
    }

    /** Registers an already built recipe (e.g. a {@link ShapedOreRecipe}) under a generated name. */
    public static IRecipe add(IRecipe recipe) {
        if (recipe.getRegistryName() == null) {
            recipe.setRegistryName(nameFor(recipe.getRecipeOutput()));
        }
        try {
            ForgeRegistries.RECIPES.register(recipe);
        } catch (Exception e) {
            Traincraft.tcLog.error("Could not register recipe for " + recipe.getRecipeOutput(), e);
        }
        return recipe;
    }

    public static IRecipe shaped(Object output, Object... params) {
        ItemStack out = toStack(output);
        try {
            return add(new ShapedOreRecipe(GROUP, out, params));
        } catch (RuntimeException e) {
            Traincraft.tcLog.error("Invalid shaped recipe for " + out, e);
            return null;
        }
    }

    public static IRecipe shapeless(Object output, Object... params) {
        ItemStack out = toStack(output);
        try {
            return add(new ShapelessOreRecipe(GROUP, out, params));
        } catch (RuntimeException e) {
            Traincraft.tcLog.error("Invalid shapeless recipe for " + out, e);
            return null;
        }
    }
}
