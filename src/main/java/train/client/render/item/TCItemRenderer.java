package train.client.render.item;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Compatibility base for Traincraft's 1.7.10 {@code IItemRenderer} implementations
 * (the interface was removed from Forge in 1.8).
 * <p>
 * A renderer keeps its old methods ({@link #handleRenderType}, {@link #shouldUseRenderHelper},
 * {@link #renderItem}) and the old {@link ItemRenderType} / {@link ItemRendererHelper} names;
 * only {@code implements IItemRenderer} becomes {@code extends TCItemRenderer} and the
 * registration moves from {@code MinecraftForgeClient.registerItemRenderer} to
 * {@link TCItemRenderRegistry#register}.
 * <p>
 * Under the hood it is a 1.12 {@link TileEntityItemStackRenderer}: the item gets a built-in model
 * (block-like display transforms) which records the current view, and that view is mapped to the
 * old render type. The origin is moved to the centre of the item, like 1.7.10's block helpers did.
 * The in-hand/GUI placement can differ a bit from 1.7.10 and may need tuning per item.
 */
@SideOnly(Side.CLIENT)
public abstract class TCItemRenderer extends TileEntityItemStackRenderer {

    /** Same constants as Forge 1.7.10's {@code IItemRenderer.ItemRenderType}. */
    public enum ItemRenderType {
        ENTITY, EQUIPPED, EQUIPPED_FIRST_PERSON, INVENTORY, FIRST_PERSON_MAP
    }

    /** Same constants as Forge 1.7.10's {@code IItemRenderer.ItemRendererHelper}. Kept for source compatibility. */
    public enum ItemRendererHelper {
        ENTITY_ROTATION, ENTITY_BOBBING, EQUIPPED_BLOCK, BLOCK_3D, INVENTORY_BLOCK
    }

    public abstract boolean handleRenderType(ItemStack item, ItemRenderType type);

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    public abstract void renderItem(ItemRenderType type, ItemStack item, Object... data);

    public static ItemRenderType toRenderType(ItemCameraTransforms.TransformType transform) {
        switch (transform) {
            case GUI:
                return ItemRenderType.INVENTORY;
            case FIRST_PERSON_LEFT_HAND:
            case FIRST_PERSON_RIGHT_HAND:
                return ItemRenderType.EQUIPPED_FIRST_PERSON;
            case THIRD_PERSON_LEFT_HAND:
            case THIRD_PERSON_RIGHT_HAND:
            case HEAD:
                return ItemRenderType.EQUIPPED;
            case GROUND:
            case FIXED:
            case NONE:
            default:
                return ItemRenderType.ENTITY;
        }
    }

    @Override
    public void renderByItem(ItemStack stack) {
        renderByItem(stack, 1.0F);
    }

    @Override
    public void renderByItem(ItemStack stack, float partialTicks) {
        ItemRenderType type = toRenderType(TCItemRenderRegistry.getCurrentTransform());
        if (!handleRenderType(stack, type)) {
            return;
        }
        // The 1.7.10 renderers change GL state directly (GL11.*), behind GlStateManager's back.
        // Saving/restoring it keeps GlStateManager's cache truthful for the items drawn after this one.
        train.client.render.gl.GLStateHelper.begin();
        GlStateManager.pushMatrix();
        // TileEntityItemStackRenderer draws in a 0..1 cube; the 1.7.10 renderers expect the origin in the middle
        GlStateManager.translate(0.5F, 0.5F, 0.5F);
        try {
            renderItem(type, stack);
        } finally {
            GlStateManager.popMatrix();
            train.client.render.gl.GLStateHelper.end();
        }
    }
}
