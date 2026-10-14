package train.client.render.gl;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL14;

import java.nio.FloatBuffer;

/**
 * Keeps Minecraft's {@link GlStateManager} cache honest around Traincraft's 1.7.10-era renderers.
 * <p>
 * 1.12 caches GL state (bound texture, colour, enabled caps...) in {@link GlStateManager} and skips
 * GL calls it thinks are redundant. The old renderers still call {@code GL11.*} directly, so after
 * them the cache no longer matches the real GL state and the next vanilla draw comes out wrong
 * (e.g. blocks in the inventory drawn with a train's texture, or dark/tinted).
 * <p>
 * {@link #begin()} saves the GL state, {@link #end()} restores it and then re-reads the restored
 * state into {@link GlStateManager}, so whatever the renderer did, vanilla continues from a
 * consistent state.
 */
@SideOnly(Side.CLIENT)
public final class GLStateHelper {
    private static final FloatBuffer COLOR = BufferUtils.createFloatBuffer(16);
    private static int depth;

    private GLStateHelper() {
    }

    public static void begin() {
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        depth++;
    }

    public static void end() {
        GL11.glPopAttrib();
        depth--;
        if (depth == 0) {
            resync();
        }
    }

    /** Forces {@link GlStateManager}'s cache to match the current GL state. */
    public static void resync() {
        sync(GL11.GL_LIGHTING, GlStateManager::enableLighting, GlStateManager::disableLighting);
        sync(GL11.GL_BLEND, GlStateManager::enableBlend, GlStateManager::disableBlend);
        sync(GL11.GL_ALPHA_TEST, GlStateManager::enableAlpha, GlStateManager::disableAlpha);
        sync(GL11.GL_CULL_FACE, GlStateManager::enableCull, GlStateManager::disableCull);
        sync(GL11.GL_DEPTH_TEST, GlStateManager::enableDepth, GlStateManager::disableDepth);
        sync(GL11.GL_TEXTURE_2D, GlStateManager::enableTexture2D, GlStateManager::disableTexture2D);
        sync(GL12.GL_RESCALE_NORMAL, GlStateManager::enableRescaleNormal, GlStateManager::disableRescaleNormal);
        sync(GL11.GL_NORMALIZE, GlStateManager::enableNormalize, GlStateManager::disableNormalize);
        sync(GL11.GL_COLOR_MATERIAL, GlStateManager::enableColorMaterial, GlStateManager::disableColorMaterial);
        sync(GL11.GL_FOG, GlStateManager::enableFog, GlStateManager::disableFog);
        sync(GL11.GL_POLYGON_OFFSET_FILL, GlStateManager::enablePolygonOffset, GlStateManager::disablePolygonOffset);
        sync(GL11.GL_LIGHT0, () -> GlStateManager.enableLight(0), () -> GlStateManager.disableLight(0));
        sync(GL11.GL_LIGHT1, () -> GlStateManager.enableLight(1), () -> GlStateManager.disableLight(1));

        boolean mask = GL11.glGetBoolean(GL11.GL_DEPTH_WRITEMASK);
        GlStateManager.depthMask(!mask);
        GlStateManager.depthMask(mask);

        int depthFunc = GL11.glGetInteger(GL11.GL_DEPTH_FUNC);
        GlStateManager.depthFunc(depthFunc == GL11.GL_LEQUAL ? GL11.GL_LESS : GL11.GL_LEQUAL);
        GlStateManager.depthFunc(depthFunc);

        int shade = GL11.glGetInteger(GL11.GL_SHADE_MODEL);
        GlStateManager.shadeModel(shade == GL11.GL_SMOOTH ? GL11.GL_FLAT : GL11.GL_SMOOTH);
        GlStateManager.shadeModel(shade);

        int alphaFunc = GL11.glGetInteger(GL11.GL_ALPHA_TEST_FUNC);
        float alphaRef = GL11.glGetFloat(GL11.GL_ALPHA_TEST_REF);
        GlStateManager.alphaFunc(alphaFunc, alphaRef + 1F);
        GlStateManager.alphaFunc(alphaFunc, alphaRef);

        int srcRgb = GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB);
        int dstRgb = GL11.glGetInteger(GL14.GL_BLEND_DST_RGB);
        int srcA = GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA);
        int dstA = GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA);
        GlStateManager.tryBlendFuncSeparate(GL11.GL_ZERO, GL11.GL_ZERO, GL11.GL_ZERO, GL11.GL_ZERO);
        GlStateManager.tryBlendFuncSeparate(srcRgb, dstRgb, srcA, dstA);

        COLOR.clear();
        GL11.glGetFloat(GL11.GL_CURRENT_COLOR, COLOR);
        float r = COLOR.get(0), g = COLOR.get(1), b = COLOR.get(2), a = COLOR.get(3);
        GlStateManager.color(-1F, -1F, -1F, -1F);
        GlStateManager.color(r, g, b, a);

        // bindTexture only calls GL when the cached id differs, so this just corrects the cache
        GlStateManager.bindTexture(GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D));
    }

    private static void sync(int cap, Runnable enable, Runnable disable) {
        if (GL11.glIsEnabled(cap)) {
            disable.run();
            enable.run();
        } else {
            enable.run();
            disable.run();
        }
    }
}
