package train.client.render.models.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import fexcraft.tmt.slim.ModelRendererTurbo;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.common.library.Info;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public class ModelTwoWaysCrossingTCTrack extends ModelBase {
	private ModelRendererTurbo modelTwoWaysCrossing;
	private ModelRendererTurbo modelDoubleDiamondCrossing;
	private ModelRendererTurbo modelDiagonalTwoWaysCrossing;
	private ModelRendererTurbo modelFourWaysCrossing;

	public ModelTwoWaysCrossingTCTrack() {
		modelTwoWaysCrossing = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_x.obj");
		modelDoubleDiamondCrossing = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_double_diamond_crossing.obj");
		modelDiagonalTwoWaysCrossing = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_diagonal_crossing.obj");
		modelFourWaysCrossing = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_universal_crossing.obj");
	}

	public void render(String type) {
	if (type.equals("normal_crossing") || type.equals("embedded_crossing"))
		modelTwoWaysCrossing.render(1);
	else if(type.equals("normal_diamond") || type.equals("embedded_diamond"))
		modelDoubleDiamondCrossing.render(1);
	else if (type.equals("normal_diagonal_crossing") || type.equals("embedded_diagonal_crossing"))
		modelDiagonalTwoWaysCrossing.render(1);
	else if (type.equals("normal_universal_crossing") || type.equals("embedded_universal_crossing"))
		modelFourWaysCrossing.render(1);
	}



	public void render(String type, TileTCRail tcRail,  double x, double y, double z) {
		render(type, x, y, z, CommonUtil.getBlockFacing(tcRail.getWorldObj(), tcRail.xCoord, tcRail.yCoord, tcRail.zCoord), 1, 1, 1, 1);

	}

	public void render(String type , double x, double y, double z, int facing, float r, float g, float b, float a) {
		// Push a blank matrix onto the stack

		// Bind the texture, so that OpenGL properly textures our block.
		if (type.contains("embedded"))
			fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_embedded.png"));
		else  fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);

		GL11.glColor4f(r, g, b, a);
		//GL11.glScalef(0.5f, 0.5f, 0.5f);
		GL11.glRotatef(90,0,0,1);

		if (type.contains("diamond"))
			if (facing == 1 || facing == 3) {
				GL11.glRotatef(90, 0, 1,0);
			}
		this.render(type);
		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}
}