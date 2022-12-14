package train.render;

import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import train.render.models.blocks.ModelBridgePillar;

public class RenderBridgePillar extends TiMTESR {

	static final ModelBridgePillar modelBridgePillar = new ModelBridgePillar();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
		GL11.glPushMatrix();
		modelBridgePillar.render(tileEntity, x, y, z);
		GL11.glPopMatrix();
	}
}
