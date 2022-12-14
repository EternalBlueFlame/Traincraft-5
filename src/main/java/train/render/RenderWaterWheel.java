package train.render;

import net.minecraft.tileentity.TileEntity;
import train.render.models.blocks.ModelWaterWheel;

public class RenderWaterWheel extends TiMTESR {

	static final ModelWaterWheel modelWaterWheel = new ModelWaterWheel();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
		modelWaterWheel.render(tileEntity, x, y, z);
	}
}
