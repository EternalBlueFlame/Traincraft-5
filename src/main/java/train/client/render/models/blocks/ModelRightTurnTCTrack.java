package train.client.render.models.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import train.common.library.Info;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public class ModelRightTurnTCTrack extends ModelBase {

	private IModelCustom model1XRightTurn;
	private IModelCustom modelMediumRightTurn;
	private IModelCustom modelLargeRightTurn;
	private IModelCustom modelVeryLargeRightTurn;
	private IModelCustom modelSuperLargeRightTurn;

	private IModelCustom model29XRightTurn;
	private IModelCustom model32XRightTurn;

	public ModelRightTurnTCTrack() {
		model1XRightTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_1x.obj"));
		modelMediumRightTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_medium.obj"));
		modelLargeRightTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_big.obj"));
		modelVeryLargeRightTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_very_big.obj"));
		modelSuperLargeRightTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_super_big.obj"));
		model29XRightTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_29x.obj"));
		model32XRightTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_32x.obj"));
	}

	public void render1X() {model1XRightTurn.renderAll();}
	public void renderMedium() {
		modelMediumRightTurn.renderAll();
	}
	public void renderLarge() {
		modelLargeRightTurn.renderAll();
	}
	public void renderVeryLarge() {
		modelVeryLargeRightTurn.renderAll();
	}
	public void renderSuperLarge() {
		modelSuperLargeRightTurn.renderAll();
	}

	public void render29X() {model29XRightTurn.renderAll();}
	public void render32X() {model32XRightTurn.renderAll();}

	public void render(String type, TileTCRail tcRail, double x, double y, double z) {
		render( type, tcRail.getgetWorld()().getBlockMetadata(tcRail.xCoord, tcRail.yCoord, tcRail.zCoord), x, y, z, 1, 1, 1, 1);
	}

	public void render(String type, int facing, double x, double y, double z, float r, float g, float b, float a) {
		// Bind the texture, so that OpenGL properly textures our block.
		if (type.contains("embedded"))
			fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_embedded.png"));
		else fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);

		GL11.glColor4f(r, g, b, a);
		//GL11.glScalef(0.5f, 0.5f, 0.5f);

		if (facing == 3) {
			if(type.equals("super_large") || type.equals("embedded_super_large"))
				GL11.glTranslatef(14.5f, 0.0f, 15.5f);
			if(type.equals("very_large") || type.equals("embedded_very_large"))
				GL11.glTranslatef(8.5f, 0.0f, 9.5f);
			if(type.equals("large") || type.equals("embedded_large"))
				GL11.glTranslatef(-1.0f, 0.0f, 5.0f);
			if(type.equals("medium") || type.equals("embedded_medium"))
				GL11.glTranslatef(1.5f, 0.0f, 2.5f);
			if(type.equals("1x") || type.equals("embedded_1x"))
				GL11.glTranslatef(-0.5f,0,0.5f);
			if(type.equals("29x") || type.equals("embedded_29x"))
				GL11.glTranslatef(27.5f,0,28.5f);
			if(type.equals("32x") || type.equals("embedded_32x"))
				GL11.glTranslatef(30.5f,0,31.5f);
		}
		if (facing == 1) {
			GL11.glRotatef(180, 0, 1, 0);
			if(type.equals("super_large") || type.equals("embedded_super_large"))
				GL11.glTranslatef(14.5f, 0.0f, 15.5f);
			if(type.equals("very_large") || type.equals("embedded_very_large"))
				GL11.glTranslatef(8.5f, 0.0f, 9.50f);
			if(type.equals("large") || type.equals("embedded_large"))
				GL11.glTranslatef(-1.0f, 0.0f, 5.0f);
			if(type.equals("medium") || type.equals("embedded_medium"))
				GL11.glTranslatef(1.5f, 0.0f, 2.5f);
			if(type.equals("1x") || type.equals("embedded_1x"))
				GL11.glTranslatef(-0.5f,0,0.5f);
			if(type.equals("29x") || type.equals("embedded_29x"))
				GL11.glTranslatef(27.5f,0,28.5f);
			if(type.equals("32x") || type.equals("embedded_32x"))
				GL11.glTranslatef(30.5f,0,31.5f);
		}
		if (facing == 2) {
			GL11.glRotatef(90, 0, 1, 0);
			if(type.equals("super_large") || type.equals("embedded_super_large"))
				GL11.glTranslatef(14.5f, 0.0f, 15.5f);
			if(type.equals("very_large") || type.equals("embedded_very_large"))
				GL11.glTranslatef(8.5f, 0.0f, 9.50f);
			if(type.equals("large") || type.equals("embedded_large"))
				GL11.glTranslatef(-1.0f, 0.0f, 5.0f);
			if(type.equals("medium") || type.equals("embedded_medium"))
				GL11.glTranslatef(1.5f, 0.0f, 2.5f);
			if(type.equals("1x") || type.equals("embedded_1x"))
				GL11.glTranslatef(-0.5f,0,0.5f);
			if(type.equals("29x") || type.equals("embedded_29x"))
				GL11.glTranslatef(27.5f,0,28.5f);
			if(type.equals("32x") || type.equals("embedded_32x"))
				GL11.glTranslatef(30.5f,0f,31.5f);
		}
		if (facing == 0) {
			GL11.glRotatef(-90, 0, 1, 0);
			if(type.equals("super_large") || type.equals("embedded_super_large"))
				GL11.glTranslatef(14.5f, 0.0f, 15.5f);
			if(type.equals("very_large") || type.equals("embedded_very_large"))
				GL11.glTranslatef(8.5f, 0.0f, 9.50f);
			if(type.equals("large") || type.equals("embedded_large"))
				GL11.glTranslatef(-1.0f, 0.0f, 5.0f);
			if(type.equals("medium") || type.equals("embedded_medium"))
				GL11.glTranslatef(1.5f, 0.0f, 2.5f);
			if(type.equals("1x") || type.equals("embedded_1x"))
				GL11.glTranslatef(-0.5f,0,0.5f);
			if(type.equals("29x") || type.equals("embedded_29x"))
				GL11.glTranslatef(27.5f,0,28.5f);
			if(type.equals("32x") || type.equals("embedded_32x"))
				GL11.glTranslatef(30.5f,0,31.5f);
		}
		if(type.equals("1x") || type.equals("embedded_1x"))
			this.render1X();
		if (type.equals("medium") || type.equals("embedded_medium"))
			this.renderMedium();
		if (type.equals("large") || type.equals("embedded_large"))
			this.renderLarge();
		if (type.equals("very_large") || type.equals("embedded_very_large"))
			this.renderVeryLarge();
		if (type.equals("super_large") || type.equals("embedded_super_large"))
			this.renderSuperLarge();
		if(type.equals("29x") || type.equals("embedded_29x"))
			this.render29X();
		if(type.equals("32x") || type.equals("embedded_32x"))
			this.render32X();

		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}
}