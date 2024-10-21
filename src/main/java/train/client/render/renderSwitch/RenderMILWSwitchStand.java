package train.client.render.renderSwitch;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import fexcraft.tmt.slim.Tessellator;
import train.client.render.renderSwitch.models.ModelMILWSwitchStandOff;
import train.client.render.renderSwitch.models.ModelMILWSwitchStandOn;
import train.common.library.Info;
import train.common.tile.switchStand.TileMILWSwitchStand;

public class RenderMILWSwitchStand extends TileEntitySpecialRenderer {
	private static final ModelMILWSwitchStandOn modelSwitch2 = new ModelMILWSwitchStandOn();
	private static final ModelMILWSwitchStandOff modelSwitch = new ModelMILWSwitchStandOff();
	private static final ResourceLocation texture2 = new ResourceLocation(Info.resourceLocation,Info.modelTexPrefix + "milwswitchon.png");
	private static final ResourceLocation texture = new ResourceLocation(Info.resourceLocation,Info.modelTexPrefix + "milwswitchoff.png");

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
		boolean powered = tileEntity.getWorld().getBlock(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord).isProvidingWeakPower(tileEntity.getWorld(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, 0) > 0;
		Tessellator.bindTexture(powered?texture2:texture);
		GL11.glPushMatrix();
		GL11.glTranslated(x+0.5,y+0.6,z+0.5);
		GL11.glRotated(180,0,1,0);
		boolean skipRender = false;

		switch (((TileMILWSwitchStand)tileEntity).getFacing()){
			case NORTH:{
				GL11.glRotated(180,0,0,1);
				GL11.glRotated(270,0,1,0);
				GL11.glTranslated(0.1875,0,0.125);
				break;
			}
			case SOUTH:{
				GL11.glRotated(180,0,0,1);
				GL11.glRotated(90,0,1,0);
				GL11.glTranslated(0.1875,0,0.125);
				break;
			}
			case EAST:{
				GL11.glRotated(180,0,0,1);
				GL11.glRotated(0,0,1,0);
				GL11.glTranslated(0.1875,0,0.125);
				break;
			}
			case WEST:{
				GL11.glRotated(180,0,0,1);
				GL11.glRotated(180,0,1,0);
				GL11.glTranslated(0.1875,0,0.125);
				break;
			}
			default:{
				skipRender = true;
			}
		}

		if (!skipRender) {
			if (powered) {
				modelSwitch2.render(null, 0, 0, 0, 0, 0, 0.0625f);
			} else {
				modelSwitch.render(null, 0, 0, 0, 0, 0, 0.0625f);
			}
		}
		GL11.glPopMatrix();
	}
}
