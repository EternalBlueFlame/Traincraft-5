/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.tracks;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public interface ISwitchActuator {
    /**
     * This method is used by the switch track to ask the actuator
     * device whether it thinks the track should be switched or not. Ultimately,
     * the track itself will decide whether it will be switched, however the
     * track will usually try to honor results of this method when possible.
     *
     * @param cart        The cart that the switch may use to determine switch status.
     *                    Implementations should expect null values.
     * @return true if the actuator would like the track switched
     * @see ITrackKitSwitch
     */
    boolean shouldSwitch(@Nullable EntityMinecart cart);

    /**
     * Announces track state changes to the actuator.
     * Server side only.
     */
    void onSwitch(boolean isSwitched);

    /**
     * Tell the actuator to refresh its arrows directions.
     */
    void updateArrows();

    enum ArrowDirection implements IStringSerializable {
        NORTH, SOUTH, EAST, WEST, NORTH_SOUTH, EAST_WEST;
        public static ArrowDirection[] VALUES = values();

        @Override
        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
