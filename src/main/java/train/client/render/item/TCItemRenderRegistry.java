package train.client.render.item;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.PerspectiveMapWrapper;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.util.vector.Vector3f;
import train.client.render.obj.TCObjModel;
import train.common.library.Info;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.Collections;
import java.util.List;

/**
 * Replacement for {@code MinecraftForgeClient.registerItemRenderer(item, renderer)}.
 * See {@link TCItemRenderer}.
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Info.modID)
public final class TCItemRenderRegistry {
    /** One shared built-in model for every item drawn by a {@link TCItemRenderer}. */
    public static final ModelResourceLocation MODEL_LOCATION = new ModelResourceLocation(Info.modID + ":tc_item_renderer", "inventory");

    private static final java.util.Set<Item> ITEMS = java.util.Collections.newSetFromMap(new java.util.IdentityHashMap<>());

    public static boolean hasRenderer(Item item) {
        return ITEMS.contains(item);
    }

    private static ItemCameraTransforms.TransformType currentTransform = ItemCameraTransforms.TransformType.NONE;

    private TCItemRenderRegistry() {
    }

    public static ItemCameraTransforms.TransformType getCurrentTransform() {
        return currentTransform;
    }

    /** Can be called any time on the client (pre-init, init or later). */
    public static void register(@Nullable Item item, TCItemRenderer renderer) {
        if (item == null) {
            return;
        }
        item.setTileEntityItemStackRenderer(renderer);
        ITEMS.add(item);
        // before init (no RenderItem yet) TCModels assigns the model during ModelRegistryEvent
        if (Minecraft.getMinecraft().getRenderItem() != null) {
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, stack -> MODEL_LOCATION);
        }
    }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
        event.getModelRegistry().putObject(MODEL_LOCATION, new PerspectiveRecordingModel());
        // resource packs may have changed the .obj files
        TCObjModel.clearCache();
    }

    private static ItemTransformVec3f transform(float rx, float ry, float rz, float tx, float ty, float tz, float scale) {
        return new ItemTransformVec3f(new Vector3f(rx, ry, rz), new Vector3f(tx / 16F, ty / 16F, tz / 16F), new Vector3f(scale, scale, scale));
    }

    /** Built-in (TileEntityItemStackRenderer) model with vanilla block display transforms that records the current view. */
    private static final class PerspectiveRecordingModel implements IBakedModel {
        private static final ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> TRANSFORMS =
                PerspectiveMapWrapper.getTransforms(new ItemCameraTransforms(
                        transform(75, 45, 0, 0, 2.5F, 0, 0.375F),   // third person left
                        transform(75, 45, 0, 0, 2.5F, 0, 0.375F),   // third person right
                        transform(0, 225, 0, 0, 0, 0, 0.4F),        // first person left
                        transform(0, 45, 0, 0, 0, 0, 0.4F),         // first person right
                        ItemTransformVec3f.DEFAULT,                  // head
                        transform(30, 225, 0, 0, 0, 0, 0.625F),      // gui
                        transform(0, 0, 0, 0, 3, 0, 0.25F),          // ground
                        transform(0, 0, 0, 0, 0, 0, 0.5F)));         // fixed

        @Override
        public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
            return Collections.emptyList();
        }

        @Override
        public boolean isAmbientOcclusion() {
            return false;
        }

        @Override
        public boolean isGui3d() {
            return true;
        }

        @Override
        public boolean isBuiltInRenderer() {
            return true;
        }

        @Override
        public TextureAtlasSprite getParticleTexture() {
            return Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
        }

        @Override
        public ItemOverrideList getOverrides() {
            return ItemOverrideList.NONE;
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType type) {
            currentTransform = type;
            return PerspectiveMapWrapper.handlePerspective(this, TRANSFORMS, type);
        }
    }
}
