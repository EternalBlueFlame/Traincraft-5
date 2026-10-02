/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/
package mods.railcraft.api.core;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.oredict.OreIngredient;
import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.Nullable;

/**
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public interface IVariantEnum extends IStringSerializable {

    int ordinal();

    default String getResourcePathSuffix() {
        return getName().replace(".", "_");
    }

    default @Nullable String getOreTag() {
        return null;
    }

    default Ingredient getAlternate(IIngredientSource container) {
        String oreTeg = getOreTag();
        if (!Strings.isEmpty(oreTeg))
            return new OreIngredient(oreTeg);
        return Ingredient.EMPTY;
    }

    default boolean isEnabled() {
        return true;
    }

    default boolean isDeprecated() {
        return false;
    }
}
