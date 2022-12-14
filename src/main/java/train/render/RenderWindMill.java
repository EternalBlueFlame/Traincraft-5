package train.render;

import net.minecraft.tileentity.TileEntity;
import train.render.models.blocks.ModelWindMill;
import train.render.models.blocks.ModelWindMillWheel;

public class RenderWindMill extends TiMTESR {

	private static final ModelWindMill modelWindMill = new ModelWindMill();
	private static final ModelWindMillWheel modelWindMillWheel = new ModelWindMillWheel();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
		modelWindMill.render(tileEntity, x, y, z);
		modelWindMillWheel.render(tileEntity, x, y, z);
	}
}
