package train.client.render.models.blocks;

import train.client.render.obj.TCObjModel;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.common.library.Info;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public class ModelTwoWaysCrossingTCTrack extends ModelBase {
	private TCObjModel modelTwoWaysCrossing;
	private TCObjModel modelDoubleDiamondCrossing;
	private TCObjModel modelDiagonalTwoWaysCrossing;
	private TCObjModel modelFourWaysCrossing;

	public ModelTwoWaysCrossingTCTrack() {
		modelTwoWaysCrossing = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_x.obj"));
		modelDoubleDiamondCrossing = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_double_diamond_crossing.obj"));
		modelDiagonalTwoWaysCrossing = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_diagonal_crossing.obj"));
		modelFourWaysCrossing = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_universal_crossing.obj"));
	}

	public void render(String type) {
	if (type.equals("normal_crossing") || type.equals("embedded_crossing"))
		modelTwoWaysCrossing.renderAll();
	else if(type.equals("normal_diamond") || type.equals("embedded_diamond"))
		modelDoubleDiamondCrossing.renderAll();
	else if (type.equals("normal_diagonal_crossing") || type.equals("embedded_diagonal_crossing"))
		modelDiagonalTwoWaysCrossing.renderAll();
	else if (type.equals("normal_universal_crossing") || type.equals("embedded_universal_crossing"))
		modelFourWaysCrossing.renderAll();
	}



	public void render(String type, TileTCRail tcRail,  double x, double y, double z) {
		render(type, x, y, z, CommonUtil.getBlockFacing(tcRail.getWorld(), tcRail.getPos().getX(), tcRail.getPos().getY(), tcRail.getPos().getZ()), 1, 1, 1, 1);

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

		net.minecraft.client.renderer.GlStateManager.color(r, g, b, a);
		//GL11.glScalef(0.5f, 0.5f, 0.5f);

		if (type.contains("diamond"))
			if (facing == 1 || facing == 3) {
				GL11.glRotatef(90, 0, 1,0);
			}
		this.render(type);
		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}
}