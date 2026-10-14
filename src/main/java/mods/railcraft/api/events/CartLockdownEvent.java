/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.events;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public abstract class CartLockdownEvent extends Event {

    public final EntityMinecart cart;
    public final BlockPos pos;

    CartLockdownEvent(EntityMinecart cart, BlockPos pos) {
        this.cart = cart;
        this.pos = pos;
    }

    /**
     * This event is posted every tick that a LockType Track (Lockdown, Holding,
     * Boarding) is holding onto a minecart.
     */
    public static final class Lock extends CartLockdownEvent {

        public Lock(EntityMinecart cart, BlockPos pos) {
            super(cart, pos);
        }
    }

    /**
     * This event is posted every tick that a LockType Track (Lockdown, Holding,
     * Boarding) is releasing a minecart.
     */
    public static final class Release extends CartLockdownEvent {

        public Release(EntityMinecart cart, BlockPos pos) {
            super(cart, pos);
        }
    }
}
