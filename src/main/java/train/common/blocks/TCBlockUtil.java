package train.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import train.common.Traincraft;

import java.lang.reflect.Method;

/** Small helpers for building blocks the 1.7.10 way (chained setters). */
public final class TCBlockUtil {
    private static Method setSoundType;

    private TCBlockUtil() {
    }

    /**
     * {@code Block.setSoundType} is protected in 1.12 (it was the public {@code setStepSound} in 1.7.10),
     * so it can't be chained from outside the block class any more. Works in dev and in the built jar.
     */
    public static <T extends Block> T sound(T block, SoundType sound) {
        try {
            if (setSoundType == null) {
                setSoundType = ObfuscationReflectionHelper.findMethod(Block.class, "func_149672_a", Block.class, SoundType.class);
            }
            setSoundType.invoke(block, sound);
        } catch (Exception e) {
            Traincraft.tcLog.error("Could not set the sound type of " + block, e);
        }
        return block;
    }
}
