package train.render.models.blocks;

import fexcraft.tmt.slim.ModelBase;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import train.render.CustomModelRenderer;

public class ModelBlockSignal extends ModelBase {
	public CustomModelRenderer box;
	public CustomModelRenderer box0;
	public CustomModelRenderer box1;

	public ModelBlockSignal() {
		box = new CustomModelRenderer(this, 77, 17, 128, 64);
		box.addBox(0F, 0F, 0F, 4, 41, 2, 0.0625f);
		box.setPosition(-2F, 0F, -1F);

		box0 = new CustomModelRenderer(this, 39, 44, 128, 64);
		box0.addBox(0F, 0F, 0F, 12, 12, 2, 0.0625f);
		box0.setPosition(-6F, 12F, -3F);

		box1 = new CustomModelRenderer(this, 43, 20, 128, 64);
		box1.addBox(0F, 0F, 0F, 6, 17, 2, 0.0625f);
		box1.setPosition(-3F, 25F, -3F);

	}
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		box.render(f5);
		box0.render(f5);
		box1.render(f5);
	}

}
