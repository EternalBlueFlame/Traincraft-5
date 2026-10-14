package train.client.core.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/** 1.12 replacement for the 1.7.10 {@code block.getIcon(side, meta)} lookups used by renderers. */
@SideOnly(Side.CLIENT)
public final class SpriteHelper {
    private SpriteHelper() {
    }

    /** The texture on the given side of a block's model, like {@code block.getIcon(side, meta)} in 1.7.10. */
    @SuppressWarnings("deprecation")
    @javax.annotation.Nullable
    public static TextureAtlasSprite getSprite(Block block, int meta, EnumFacing side) {
        // 1.7.10 air had no icon; the renderers then fall back to their default ballast texture
        if (block == null || block == net.minecraft.init.Blocks.AIR) {
            return null;
        }
        IBlockState state = block.getStateFromMeta(meta);
        IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state);
        List<BakedQuad> quads = model.getQuads(state, side, 0L);
        TextureAtlasSprite sprite = !quads.isEmpty() ? quads.get(0).getSprite() : model.getParticleTexture();
        if (sprite == null || sprite == Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite()) {
            return null;
        }
        return sprite;
    }

    /**
     * Texture file for a sprite name split at ':' - {@code ("minecraft", "blocks/gravel")} (1.12 sprite names)
     * or {@code ("tc", "ballast_test")} (1.7.10 icon names, implicitly under blocks/).
     */
    public static net.minecraft.util.ResourceLocation blockTexture(String domain, String path) {
        return new net.minecraft.util.ResourceLocation(domain, "textures/" + (path.contains("/") ? path : "blocks/" + path) + ".png");
    }

    /** {@code block.getIcon(1, meta)} - the top texture. */
    public static TextureAtlasSprite getTopSprite(Block block, int meta) {
        return getSprite(block, meta, EnumFacing.UP);
    }
}
