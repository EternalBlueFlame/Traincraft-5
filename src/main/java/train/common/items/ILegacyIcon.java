package train.common.items;

/**
 * Items that had a plain 2D icon in 1.7.10 ({@code registerIcons}). The value is the old icon name
 * ({@code "tc:parts/steel"}); the client turns it into a generated item model with the texture
 * {@code tc:items/parts/steel}, so no per-item model json is needed.
 */
public interface ILegacyIcon {
    /** @return the 1.7.10 icon name for that metadata, or null to use the normal json model */
    String legacyIcon(int meta);
}
