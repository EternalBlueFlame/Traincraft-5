/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.events;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * These events are fired on {@link net.minecraftforge.common.MinecraftForge#EVENT_BUS}.
 *
 * <p>In order to cancel linking, call {@link mods.railcraft.api.carts.ILinkageManager#breakLink(EntityMinecart, EntityMinecart)}</p>
 */
public class CartLinkEvent extends Event {

    private final EntityMinecart one;
    private final EntityMinecart two;

    CartLinkEvent(EntityMinecart one, EntityMinecart two) {
        this.one = one;
        this.two = two;
    }

    public EntityMinecart getCartOne() {
        return one;
    }

    public EntityMinecart getCartTwo() {
        return two;
    }

    public static final class Link extends CartLinkEvent {
        public Link(EntityMinecart one, EntityMinecart two) {
            super(one, two);
        }
    }

    public static final class Unlink extends CartLinkEvent {
        public Unlink(EntityMinecart one, EntityMinecart two) {
            super(one, two);
        }
    }
}
