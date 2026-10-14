/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.ToIntFunction;

/**
 * Created by CovertJaguar on 12/25/2018 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
@SuppressWarnings("unchecked")
public interface IRecipeBuilder<B extends IRecipeBuilder> {

    /**
     * Adds a resource location that describes the recipe.
     * It is only used for logging, so it doesn't need to be exact.
     *
     * This is required.
     */
    default B name(@Nullable ResourceLocation name) {
        return (B) this;
    }

    /**
     * Adds a resource location that describes the recipe.
     * It is only used for logging, so it doesn't need to be exact.
     *
     * This is required.
     */
    default B name(String name) {
        name(new ResourceLocation(name));
        return (B) this;
    }

    /**
     * Adds a resource location that describes the recipe.
     * It is only used for logging, so it doesn't need to be exact.
     *
     * This is required.
     */
    default B name(String namespace, String descriptor) {
        name(new ResourceLocation(namespace, descriptor));
        return (B) this;
    }

    /**
     * This function will attempt to generate a ResourceLocation from the given object.
     *
     * This is required.
     */
    default B name(@Nullable Object name) {
        return (B) this;
    }

    default ResourceLocation getName() {
        return new ResourceLocation("invalid", "dummy");
    }

    default boolean notRegistered() {return true;}

    interface IFeature {
        default <F> Optional<F> getFeature(Class<F> feature) {
            return Optional.empty();
        }
    }

    interface ITimeFeature<B extends IRecipeBuilder> extends IFeature {

        /**
         * Sets the cooking time/heat value/process time for this recipe, in ticks.
         */
        default B time(int ticks) {
            return time(stack -> ticks);
        }

        /**
         * Sets the cooking time/heat value/process time for this recipe, in ticks.
         */
        default B time(ToIntFunction<ItemStack> tickFunction) {
            getFeature(ITimeFeature.class).ifPresent(impl -> impl.time(tickFunction));
            return (B) this;
        }

        default ToIntFunction<ItemStack> getTimeFunction() {
            return getFeature(ITimeFeature.class).map(ITimeFeature::getTimeFunction).get();
        }
    }

    interface ISingleInputFeature extends IFeature {
        default Ingredient getInput() {
            return getFeature(ISingleInputFeature.class).map(ISingleInputFeature::getInput).get();
        }
    }

    interface ISingleItemStackOutputFeature<B extends IRecipeBuilder> extends IFeature {
        default B output(@Nullable ItemStack output) {
            getFeature(ISingleItemStackOutputFeature.class).ifPresent(impl -> impl.output(output));
            return (B) this;
        }

        default B output(@Nullable Item output) {
            return output(output, 1);
        }

        default B output(@Nullable Item output, int amount) {
            return output(new ItemStack(output, amount));
        }

        default B output(@Nullable Block output) {
            return output(output, 1);
        }

        default B output(@Nullable Block output, int amount) {
            return output(new ItemStack(output, amount));
        }

        default ItemStack getOutput() {
            return getFeature(ISingleItemStackOutputFeature.class).map(ISingleItemStackOutputFeature::getOutput).orElse(ItemStack.EMPTY);
        }
    }
}
