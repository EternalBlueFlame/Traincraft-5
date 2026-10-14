package train.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/** A renderer that never draws anything (seats, collision boxes). */
public class RenderNothing<T extends Entity> extends Render<T> {
    public RenderNothing(RenderManager renderManager) {
        super(renderManager);
        this.shadowSize = 0F;
    }

    @Override
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
    }

    @Override
    public boolean shouldRender(T livingEntity, net.minecraft.client.renderer.culling.ICamera camera, double camX, double camY, double camZ) {
        return false;
    }

    @Override
    protected ResourceLocation getEntityTexture(T entity) {
        return null;
    }
}
