package train.common.core.util;

import net.minecraft.util.EnumBlockRenderType;

/** Maps 1.7.10 block render type ids to 1.12 {@link EnumBlockRenderType}. */
public final class LegacyRender {
    private LegacyRender() {
    }

    /**
     * -1 (in 1.7.10: "no block renderer, a TileEntitySpecialRenderer draws it") becomes
     * {@link EnumBlockRenderType#ENTITYBLOCK_ANIMATED}; everything else (vanilla or custom
     * ISimpleBlockRenderingHandler ids) becomes a normal JSON model.
     */
    public static EnumBlockRenderType renderType(int legacyId) {
        return legacyId == -1 ? EnumBlockRenderType.ENTITYBLOCK_ANIMATED : EnumBlockRenderType.MODEL;
    }
}
