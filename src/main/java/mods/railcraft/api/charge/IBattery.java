/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.charge;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Base interface for charge batteries.
 *
 * Created by CovertJaguar on 5/13/2017 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface IBattery {

    /**
     * Gets the charge in the battery.
     *
     * This value can potentially exceed the capacity on occasion.
     * Batteries can have more charge than the max capacity for performance reasons.
     *
     * @return The charge
     */
    double getCharge();

    /**
     * The amount of charge that can be drawn from this battery right now.
     *
     * Some implementations limit this by how much can be drawn from a battery per tick.
     *
     * @return The charge amount
     */
    default double getAvailableCharge() {
        return getCharge();
    }

    /**
     * Gets the maximum charge the battery can have.
     *
     * @return The maximum charge
     */
    double getCapacity();

    /**
     * True if and only if {@code getCharge()< getCapacity()}.
     *
     * @return {@code getCharge()< getCapacity()}
     */
    default boolean needsCharging() {
        return getCharge() < getCapacity();
    }

    default double room() {
        return Math.max(0.0, getCapacity() - getCharge());
    }

    /**
     * Sets the charge in the battery.
     *
     * @param charge The target amount
     */
    void setCharge(double charge);

    /**
     * Adds some charge to the battery.
     *
     * You are responsible for ensuring that you don't add charge to a full battery.
     *
     * Batteries can have more charge than the max capacity for performance reasons.
     *
     * @param charge The charge intended to add
     * @see #needsCharging()
     */
    void addCharge(double charge);

    /**
     * Removes some charge from the battery.
     *
     * @param charge The maximum amount of charge requested
     * @return The amount of charge removed
     */
    double removeCharge(double charge);

    /**
     * The efficiency refers to how much of the power put into a battery can be drawn back out of it.
     */
    default double getEfficiency() {
        return 1.0;
    }

    /**
     * Reads the charge information from the minecart.
     *
     * @param data The tag that stores the information
     * @return The tag provided
     */
    default NBTTagCompound readFromNBT(NBTTagCompound data) {
        setCharge(data.getDouble("charge"));
        return data;
    }

    /**
     * Saves the charge information to the minecart.
     *
     * @param data The tag that saves the information
     * @return The tag provided
     */
    default NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setDouble("charge", getCharge());
        return data;
    }

}
