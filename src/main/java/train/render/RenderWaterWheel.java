package train.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import train.render.models.blocks.ModelWaterWheel;

public class RenderWaterWheel extends TileEntitySpecialRenderer {
	private static final ModelWaterWheel modelWaterWheel = new ModelWaterWheel();

	@Override
	public void render(TileEntity tileEntity, double x, double y, double z, float tick, int destroyStage, float alpha) {
		if(tileEntity!=null) {
			modelWaterWheel.render(tileEntity, x, y, z);
		}
	}
}
