package train.client.render.obj;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import train.common.Traincraft;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Drop-in replacement for the 1.7.10 {@code IModelCustom} / {@code AdvancedModelLoader}
 * (removed from Forge in 1.8), built on Forge 1.12's own OBJ loader ({@link OBJModel}).
 * <p>
 * Usage stays as in 1.7.10: bind a texture, set up the GL transforms, then call
 * {@link #renderAll()} or {@link #renderPart(String)}.
 * <p>
 * How it works: the .obj is parsed and baked by Forge, using a sprite that covers the whole
 * 0..1 UV range instead of a texture atlas slot, so the quads keep the model's original UVs
 * and render with whatever texture is currently bound - exactly like the old immediate-mode
 * renderer. The baked quads are cached per group and drawn through the Tessellator
 * (position/uv/normal only, so {@code glColor} tinting still works).
 * <p>
 * Traincraft's 1.7.10 .obj files are cleaned up in memory before Forge parses them:
 * {@code mtllib}/{@code usemtl} lines are dropped (most .mtl files were never shipped and the
 * old loader ignored them), and faces with more than 4 vertices are split into a triangle fan
 * (the old loader drew them as GL_POLYGON; Forge only accepts triangles and quads).
 */
@SideOnly(Side.CLIENT)
public final class TCObjModel {
    private static final VertexFormat FORMAT = DefaultVertexFormats.POSITION_TEX_NORMAL;
    private static final Map<ResourceLocation, TCObjModel> CACHE = new HashMap<>();
    private static final FullTextureSprite FULL_SPRITE = new FullTextureSprite();

    private final ResourceLocation location;
    @Nullable
    private OBJModel model;
    private boolean failed;
    @Nullable
    private List<BakedQuad> allQuads;
    private final Map<String, List<BakedQuad>> partQuads = new HashMap<>();
    /**
     * UV remapping applied by {@link #cleanUp}: Forge's parser rejects UVs outside 0..1, which most
     * of Traincraft's track/support models have (tiling textures, or tiny export errors like -0.0001).
     * The .obj UVs are squeezed into 0..1 before parsing and stretched back after baking, so the
     * rendered result matches the 1.7.10 renderer exactly. {uMin, uRange, vMin, vRange}
     */
    private final float[] uvRemap = {0f, 1f, 0f, 1f};

    private TCObjModel(ResourceLocation location) {
        this.location = location;
    }

    /** Replacement for {@code TCObjModel.load(location)}. Loading is lazy. */
    public static TCObjModel load(ResourceLocation location) {
        synchronized (CACHE) {
            return CACHE.computeIfAbsent(location, TCObjModel::new);
        }
    }

    /** Forget all parsed/baked data, e.g. after a resource pack reload. */
    public static void clearCache() {
        synchronized (CACHE) {
            for (TCObjModel m : CACHE.values()) {
                m.model = null;
                m.failed = false;
                m.allQuads = null;
                m.partQuads.clear();
                m.uvRemap[0] = 0f; m.uvRemap[1] = 1f; m.uvRemap[2] = 0f; m.uvRemap[3] = 1f;
            }
        }
    }

    public ResourceLocation getLocation() {
        return this.location;
    }

    public void renderAll() {
        if (this.allQuads == null) {
            this.allQuads = bake(Collections.singletonList(OBJModel.Group.ALL));
        }
        draw(this.allQuads);
    }

    public void renderPart(String partName) {
        List<BakedQuad> quads = this.partQuads.get(partName);
        if (quads == null) {
            quads = bake(Collections.singletonList(partName));
            this.partQuads.put(partName, quads);
        }
        draw(quads);
    }

    public void renderOnly(String... groupNames) {
        for (String name : groupNames) {
            renderPart(name);
        }
    }

    public void renderAllExcept(String... excludedGroupNames) {
        OBJModel obj = getModel();
        if (obj == null) {
            return;
        }
        Set<String> excluded = new HashSet<>(Arrays.asList(excludedGroupNames));
        for (String name : obj.getMatLib().getGroups().keySet()) {
            if (!excluded.contains(name)) {
                renderPart(name);
            }
        }
    }

    @Nullable
    private OBJModel getModel() {
        if (this.model == null && !this.failed) {
            try {
                IResourceManager manager = Minecraft.getMinecraft().getResourceManager();
                try (IResource original = manager.getResource(this.location)) {
                    byte[] cleaned = cleanUp(original.getInputStream(), this.uvRemap);
                    OBJModel parsed = new OBJModel.Parser(new MemoryResource(original, cleaned), manager).parse();
                    // Without a material Forge bakes every face with its tiny "white" atlas sprite, so all
                    // UVs collapse onto one texel. Point all materials at the full-texture sprite instead.
                    for (String name : parsed.getMatLib().getMaterialNames()) {
                        parsed.getMatLib().getMaterial(name).setTexture(new OBJModel.Texture(FULL_SPRITE.getIconName()));
                    }
                    this.model = parsed;
                }
            } catch (Exception e) {
                this.failed = true;
                Traincraft.tcLog.error("Could not load OBJ model " + this.location, e);
            }
        }
        return this.model;
    }

    private List<BakedQuad> bake(List<String> visibleGroups) {
        OBJModel obj = getModel();
        if (obj == null) {
            return Collections.emptyList();
        }
        try {
            IBakedModel baked = obj.bake(new OBJModel.OBJState(visibleGroups, true), FORMAT, rl -> FULL_SPRITE);
            List<BakedQuad> quads = new ArrayList<>(baked.getQuads(null, null, 0L));
            postProcess(quads, this.uvRemap);
            return quads;
        } catch (Exception e) {
            Traincraft.tcLog.error("Could not bake OBJ model " + this.location + " groups " + visibleGroups, e);
            return Collections.emptyList();
        }
    }

    private static void draw(List<BakedQuad> quads) {
        if (quads.isEmpty()) {
            return;
        }
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, FORMAT);
        for (BakedQuad quad : quads) {
            buffer.addVertexData(quad.getVertexData());
        }
        tessellator.draw();
    }

    /**
     * Fixes up Forge's baked quads to match the 1.7.10 renderer:
     * <ul>
     * <li>UVs are stretched back to the model's original range (inverse of the remap in {@link #cleanUp});</li>
     * <li>normals are recomputed per face from the vertex positions, as 1.7.10's WavefrontObject did. Many of
     * Traincraft's .obj files have unreliable {@code vn} data (often pointing down), which Forge uses as-is
     * and which makes the models render almost black under Minecraft's item/TESR lighting.</li>
     * </ul>
     */
    private static void postProcess(List<BakedQuad> quads, float[] remap) {
        boolean remapUV = remap[0] != 0f || remap[1] != 1f || remap[2] != 0f || remap[3] != 1f;
        int stride = FORMAT.getIntegerSize();
        int uv = FORMAT.getUvOffsetById(0) / 4;
        int normal = FORMAT.getNormalOffset() / 4;
        for (int q = 0; q < quads.size(); q++) {
            BakedQuad quad = quads.get(q);
            int[] data = quad.getVertexData().clone();
            if (remapUV) {
                for (int i = 0; i + uv + 1 < data.length; i += stride) {
                    float u = Float.intBitsToFloat(data[i + uv]);
                    float v = Float.intBitsToFloat(data[i + uv + 1]);
                    data[i + uv] = Float.floatToRawIntBits(u * remap[1] + remap[0]);
                    data[i + uv + 1] = Float.floatToRawIntBits(v * remap[3] + remap[2]);
                }
            }
            if (normal >= 0 && data.length >= stride * 3) {
                float x0 = Float.intBitsToFloat(data[0]), y0 = Float.intBitsToFloat(data[1]), z0 = Float.intBitsToFloat(data[2]);
                float ax = Float.intBitsToFloat(data[stride]) - x0, ay = Float.intBitsToFloat(data[stride + 1]) - y0, az = Float.intBitsToFloat(data[stride + 2]) - z0;
                float bx = Float.intBitsToFloat(data[2 * stride]) - x0, by = Float.intBitsToFloat(data[2 * stride + 1]) - y0, bz = Float.intBitsToFloat(data[2 * stride + 2]) - z0;
                float nx = ay * bz - az * by, ny = az * bx - ax * bz, nz = ax * by - ay * bx;
                float len = (float) Math.sqrt(nx * nx + ny * ny + nz * nz);
                if (len > 1.0E-7F) {
                    int packed = (((byte) Math.round(nx / len * 127F)) & 0xFF)
                            | ((((byte) Math.round(ny / len * 127F)) & 0xFF) << 8)
                            | ((((byte) Math.round(nz / len * 127F)) & 0xFF) << 16);
                    for (int i = 0; i + normal < data.length; i += stride) {
                        data[i + normal] = (data[i + normal] & 0xFF000000) | packed;
                    }
                }
            }
            quads.set(q, new BakedQuad(data, quad.getTintIndex(), quad.getFace(), quad.getSprite(), quad.shouldApplyDiffuseLighting(), quad.getFormat()));
        }
    }

    /**
     * Keeps v/vt/vn/f/o/g lines, drops material lines, fan-triangulates faces with more than 4 vertices,
     * and squeezes the UVs into 0..1 if needed (the remap is written to {@code uvRemap}).
     */
    static byte[] cleanUp(InputStream in, float[] uvRemap) throws IOException {
        List<String> lines = new ArrayList<>();
        float uMin = 0f, uMax = 1f, vMin = 0f, vMax = 1f;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    continue;
                }
                String[] tokens = trimmed.split("\\s+");
                String key = tokens[0];
                if (key.equals("f") && tokens.length > 5) {
                    for (int i = 2; i < tokens.length - 1; i++) {
                        lines.add("f " + tokens[1] + ' ' + tokens[i] + ' ' + tokens[i + 1]);
                    }
                } else if (key.equals("vt")) {
                    float u = tokens.length > 1 ? Float.parseFloat(tokens[1]) : 0f;
                    // 1.7.10's WavefrontObject flipped V (1 - v) while parsing; Forge's loader doesn't
                    float v = 1f - (tokens.length > 2 ? Float.parseFloat(tokens[2]) : 0f);
                    uMin = Math.min(uMin, u); uMax = Math.max(uMax, u);
                    vMin = Math.min(vMin, v); vMax = Math.max(vMax, v);
                    lines.add("vt " + u + ' ' + v);
                } else if (key.equals("v") || key.equals("vn") || key.equals("f") || key.equals("o") || key.equals("g")) {
                    lines.add(trimmed);
                }
            }
        }
        float uRange = uMax - uMin, vRange = vMax - vMin;
        boolean remap = uMin < 0f || uMax > 1f || vMin < 0f || vMax > 1f;
        uvRemap[0] = remap ? uMin : 0f;
        uvRemap[1] = remap ? uRange : 1f;
        uvRemap[2] = remap ? vMin : 0f;
        uvRemap[3] = remap ? vRange : 1f;
        StringBuilder out = new StringBuilder();
        for (String l : lines) {
            if (remap && l.startsWith("vt")) {
                String[] tokens = l.split("\\s+");
                float u = tokens.length > 1 ? Float.parseFloat(tokens[1]) : 0f;
                float v = tokens.length > 2 ? Float.parseFloat(tokens[2]) : 0f;
                // clamp guards against float rounding pushing a value a hair outside 0..1
                u = Math.max(0f, Math.min(1f, (u - uMin) / uRange));
                v = Math.max(0f, Math.min(1f, (v - vMin) / vRange));
                out.append("vt ").append(u).append(' ').append(v).append('\n');
            } else {
                out.append(l).append('\n');
            }
        }
        return out.toString().getBytes(StandardCharsets.UTF_8);
    }

    /** A sprite spanning the whole texture, so baked UVs equal the model's UVs. */
    private static final class FullTextureSprite extends TextureAtlasSprite {
        FullTextureSprite() {
            super("tc:obj_full_texture");
            // a large size keeps Forge's UV contraction (meant against atlas bleeding) negligible
            setIconWidth(4096);
            setIconHeight(4096);
            initSprite(4096, 4096, 0, 0, false);
        }
    }

    /** The cleaned-up .obj text, presented to Forge's parser as a resource. */
    private static final class MemoryResource implements IResource {
        private final ResourceLocation location;
        private final String packName;
        private final byte[] data;

        MemoryResource(IResource original, byte[] data) {
            this.location = original.getResourceLocation();
            this.packName = original.getResourcePackName();
            this.data = data;
        }

        @Override
        public ResourceLocation getResourceLocation() {
            return this.location;
        }

        @Override
        public InputStream getInputStream() {
            return new ByteArrayInputStream(this.data);
        }

        @Override
        public boolean hasMetadata() {
            return false;
        }

        @Nullable
        @Override
        public <T extends IMetadataSection> T getMetadata(String sectionName) {
            return null;
        }

        @Override
        public String getResourcePackName() {
            return this.packName;
        }

        @Override
        public void close() {
        }
    }
}
