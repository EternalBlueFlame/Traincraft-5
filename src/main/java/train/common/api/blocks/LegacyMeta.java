package train.common.api.blocks;

import net.minecraft.block.properties.PropertyInteger;

/**
 * 1.7.10 blocks stored their facing/variant in 4 bits of metadata. In 1.12 metadata only survives
 * if the block declares a property for it: without one, getStateFromMeta() always returns the
 * default state and getMetaFromState() always returns 0, so every placed block (tracks, signals,
 * stoppers...) ended up facing the same way. Blocks that still use raw metadata expose it as "meta".
 */
public final class LegacyMeta {
    public static final PropertyInteger META = PropertyInteger.create("meta", 0, 15);

    private LegacyMeta() {}
}
