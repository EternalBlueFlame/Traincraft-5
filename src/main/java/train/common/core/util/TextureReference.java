package train.common.core.util;

/**
 * Version-neutral texture atlas.
 */
public interface TextureReference {

    float getMinU();

    float getMaxU();

    float getMinV();

    float getMaxV();

    /** Returns the texture name in modID:path format. */
    String getResourceName();
}