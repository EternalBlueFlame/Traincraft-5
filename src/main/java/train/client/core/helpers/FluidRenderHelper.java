package train.client.core.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nullable;

public class FluidRenderHelper {
    private static final ResourceLocation BLOCK_TEXTURE = TextureMap.LOCATION_BLOCKS_TEXTURE;

    /** The fluid's still or flowing sprite on the block atlas (missing texture if it has none). */
    @Nullable
    public static TextureAtlasSprite getFluidTexture(Fluid fluid, boolean flowing) {
        if (fluid == null) {
            return null;
        }
        TextureMap map = Minecraft.getMinecraft().getTextureMapBlocks();
        ResourceLocation location = flowing ? fluid.getFlowing() : fluid.getStill();
        return location == null ? map.getMissingSprite() : map.getAtlasSprite(location.toString());
    }

    /**
     * Returns the sheet the fluid texture is on.
     * @param liquid The fluid
     * @return Resource Location of the sheet
     */
    public static ResourceLocation getFluidSheet(Fluid liquid) {
        return BLOCK_TEXTURE;
    }
}
