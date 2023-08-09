/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.carts;

import net.minecraft.entity.item.MinecartEntity;
import javax.annotation.Nullable;

import java.util.stream.Stream;

/**
 * The LinkageManager contains all the functions needed to link and interact
 * with linked carts.
 * <p/>
 * To obtain an instance of this interface, call {@link CartToolsAPI#linkageManager()}.
 * <p/>
 * Each cart can up to two links. They are called Link A and Link B. Some carts
 * will have only Link A, for example the Tunnel Bore.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 * @see CartToolsAPI , ILinkableCart
 */
public interface ILinkageManager {

    /**
     * The default max distance at which carts can be linked, divided by 2.
     */
    float LINKAGE_DISTANCE = 1.25f;
    /**
     * The default distance at which linked carts are maintained, divided by 2.
     */
    float OPTIMAL_DISTANCE = 0.78f;

    /**
     * Allows or disallows the cart to automatically link to the next cart it collides with.
     *
     * @param cart     The minecart
     * @param autoLink Whether the auto link feature is enabled
     * @return True if tries to disable link or enable link while there is any free link
     */
    default boolean setAutoLink(MinecartEntity cart, boolean autoLink) {
        return false;
    }

    default boolean hasAutoLink(MinecartEntity cart) {
        return false;
    }

    default boolean tryAutoLink(MinecartEntity cart1, MinecartEntity cart2) {
        return false;
    }

    /**
     * Creates a link between two carts, but only if there is nothing preventing
     * such a link.
     *
     * @return True if the link succeeded.
     */
    default boolean createLink(MinecartEntity cart1, MinecartEntity cart2) {
        return false;
    }

    default boolean hasFreeLink(MinecartEntity cart) {
        return false;
    }

    /**
     * Returns the cart linked to Link A or null if nothing is currently
     * occupying Link A.
     *
     * @param cart The cart for which to get the link
     * @return The linked cart or null
     */
    default @Nullable MinecartEntity getLinkedCartA(MinecartEntity cart) {
        return null;
    }

    /**
     * Returns the cart linked to Link B or null if nothing is currently
     * occupying Link B.
     *
     * @param cart The cart for which to get the link
     * @return The linked cart or null
     */
    default @Nullable MinecartEntity getLinkedCartB(MinecartEntity cart) {
        return null;
    }

    /**
     * Returns true if the two carts are linked to each other.
     *
     * @return True if linked
     */
    default boolean areLinked(MinecartEntity cart1, MinecartEntity cart2) {
        return false;
    }

    /**
     * Breaks a link between two carts, if any link exists.
     */
    default void breakLink(MinecartEntity cart1, MinecartEntity cart2) {
    }

    /**
     * Breaks all links the cart has.
     */
    default void breakLinks(MinecartEntity cart) {
    }

    /**
     * Counts how many carts are in the train.
     *
     * @param cart Any cart in the train
     * @return The number of carts in the train
     */
    @SuppressWarnings("unused")
    default int countCartsInTrain(MinecartEntity cart) {
        return 0;
    }

    /**
     * Returns a Stream which will iterate over every cart in the provided cart's train.
     *
     * There is no guarantee of order.
     *
     * If called on the client, it will only contain the passed cart object.
     * There is no linkage information on the client.
     */
    default Stream<MinecartEntity> streamTrain(MinecartEntity cart) {
        return Stream.empty();
    }

}
