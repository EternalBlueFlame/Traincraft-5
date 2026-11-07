package train.client.core.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidRenderHelper {
    private static final ResourceLocation BLOCK_TEXTURE = TextureMap.locationBlocksTexture;

    public static TextureAtlasSprite getFluidTexture(Fluid fluid, boolean flowing) {
        if (fluid == null) {
            return null;
        }

        TextureAtlasSprite icon = flowing ? fluid.getFlowingIcon() : fluid.getStillIcon();

        if (icon == null) {
            icon = ((TextureMap) Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
        }

        return icon;
    }

    /** Draws one atlas sprite quad using the sprite's stitched UV bounds. */
    public static void drawFluidTexture(int x, int y, float z, TextureAtlasSprite sprite, int width, int height) {
        if (sprite == null) {
            return;
        }

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(x, y + height, z).tex(sprite.getMinU(), sprite.getMaxV()).endVertex();
        buffer.pos(x + width, y + height, z).tex(sprite.getMaxU(), sprite.getMaxV()).endVertex();
        buffer.pos(x + width, y, z).tex(sprite.getMaxU(), sprite.getMinV()).endVertex();
        buffer.pos(x, y, z).tex(sprite.getMinU(), sprite.getMinV()).endVertex();
        tessellator.draw();
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
