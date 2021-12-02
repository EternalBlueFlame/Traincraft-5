/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.carts;

import net.minecraft.item.EnumDyeColor;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info/>
 */
public interface IPaintedCart {

    EnumDyeColor getPrimaryDyeColor();

    EnumDyeColor getSecondaryDyeColor();
    
}
