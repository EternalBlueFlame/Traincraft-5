/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.signals;

import net.minecraft.nbt.NBTTagCompound;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

/**
 * Represents a Signal state.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public enum SignalAspect {

    /**
     * The All Clear.
     */
    GREEN(0, 5, "gui.railcraft.aspect.green.name"),
    /**
     * Typically means pairing in progress.
     */
    BLINK_YELLOW(1, 3, "gui.railcraft.aspect.blink.yellow.name"),
    /**
     * Caution, cart heading away.
     */
    YELLOW(1, 5, "gui.railcraft.aspect.yellow.name"),
    /**
     * Maintenance warning, the signal is malfunctioning.
     */
    BLINK_RED(2, 3, "gui.railcraft.aspect.blink.red.name"),
    /**
     * Stop!
     */
    RED(2, 5, "gui.railcraft.aspect.red.name"),
    /**
     * Can't happen, really it can't (or shouldn't). Only used when rendering
     * blink states (for the texture offset).
     */
    OFF(3, 0, "gui.railcraft.aspect.off.name");
    private final int textureIndex;
    private final int lightValue;
    private final String localizationTag;
    private static boolean blinkState;
    private static final int SIGNAL_BRIGHTNESS = 210;
    public static final SignalAspect[] VALUES = values();

    SignalAspect(int textureIndex, int lightValue, String localizationTag) {
        this.textureIndex = textureIndex;
        this.lightValue = lightValue;
        this.localizationTag = localizationTag;
    }

    /**
     * Returns the texture offset for this specific aspect.
     *
     * @return offset
     */
    public int getTextureIndex() {
        return textureIndex;
    }

    /**
     * Returns the texture brightness for this specific aspect.
     *
     * @return brightness
     */
    public float getTextureBrightness() {
        if (this == OFF) return 0F;
        return SIGNAL_BRIGHTNESS / 16F;
    }

    /**
     * Returns true if the aspect is one of the blink states.
     *
     * @return true if blinks
     */
    public boolean isBlinkAspect() {
        return this == BLINK_YELLOW || this == BLINK_RED;
    }

    /**
     * Returns true if the aspect should appear off. The return value varies for
     * Blink states.
     *
     * @return true if appears off.
     */
    public boolean isOffState() {
        return this == OFF || (isBlinkAspect() && !isBlinkOn());
    }

    /**
     * Returns the SignalAspect that should be used during rendering.
     * This will vary for blinking SignalAspects based on the global blink state.
     *
     * @return the SignalAspect that should be rendered
     */
    public SignalAspect getDisplayAspect() {
        if (isOffState())
            return OFF;
        if (this == BLINK_YELLOW)
            return YELLOW;
        if (this == BLINK_RED)
            return RED;
        return this;
    }

    /**
     * Returns the level at which the Aspect emits light.
     */
    public int getLightValue() {
        return lightValue;
    }

    /**
     * Return true if the light is currently off.
     *
     * @return true if the light is currently off.
     */
    public static boolean isBlinkOn() {
        return blinkState;
    }

    /**
     * Don't call this, its used to change blink states by Railcraft.
     */
    public static void invertBlinkState() {
        blinkState = !blinkState;
    }

    /**
     * Takes an Ordinal and returns the corresponding SignalAspect.
     *
     * @param ordinal the ordinal
     * @return the Signal Aspect with the specified Ordinal
     */
    public static SignalAspect fromOrdinal(int ordinal) {
        if (ordinal < 0 || ordinal >= VALUES.length)
            return SignalAspect.RED;
        return VALUES[ordinal];
    }

    /**
     * Read an aspect from NBT.
     */
    public static SignalAspect readFromNBT(NBTTagCompound nbt, String tag) {
        if (nbt.hasKey(tag, 1))
            return fromOrdinal(nbt.getByte(tag));
        return RED;
    }

    /**
     * Write an aspect to NBT.
     */
    public void writeToNBT(NBTTagCompound nbt, String tag) {
        nbt.setByte(tag, (byte) ordinal());
    }

    /**
     * Tests two Aspects and determines which is more restrictive. The concept
     * of "most restrictive" refers to which aspect enforces the most
     * limitations of movement to a train.
     * <p/>
     * In Railcraft the primary use is in Signal Box logic.
     *
     * @param first  aspect one
     * @param second aspect two
     * @return The most restrictive Aspect
     */
    public static SignalAspect mostRestrictive(@Nullable SignalAspect first, @Nullable SignalAspect second) {
        if (first == null && second == null)
            return RED;
        if (first == null)
            return second;
        if (second == null)
            return first;
        if (first.ordinal() > second.ordinal())
            return first;
        return second;
    }

    public String getLocalizationTag() {
        return localizationTag;
    }

    @Override
    public String toString() {
        String[] sa = name().split("_");
        StringBuilder out = new StringBuilder();
        for (String s : sa) {
            out.append(s, 0, 1).append(s.substring(1).toLowerCase(Locale.ENGLISH)).append(" ");
        }
        return out.toString().trim();
    }

}
