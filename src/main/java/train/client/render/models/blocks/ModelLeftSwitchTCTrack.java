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
public class ModelLeftSwitchTCTrack extends ModelBase {
	private TCObjModel modelMediumLeftSwitchActive;
	private TCObjModel modelMediumLeftSwitchInactive;
	private TCObjModel modelMediumLeftParallelSwitchInactive;
	private TCObjModel modelMediumLeftParallelSwitchActive;
	private TCObjModel modelLargeLeftParallelSwitchInactive;
	private TCObjModel modelLargeLeftParallelSwitchActive;
	private TCObjModel modelLargeLeftSwitchActive;
	private TCObjModel modelLargeLeftSwitchInactive;

	private TCObjModel modelVeryLargeLeftSwitchActive;
	private TCObjModel modelVeryLargeLeftSwitchInactive;
	private TCObjModel modelMediumLeft45degreeSwitchActive;
	private TCObjModel modelMediumLeft45degreeSwitchInActive;
	private TCObjModel modelLargeLeft45degreeSwitchActive;
	private TCObjModel modelLargeLeft45degreeSwitchInActive;

	public ModelLeftSwitchTCTrack() {
		modelMediumLeftSwitchActive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_small_active_left.obj"));
		modelMediumLeftSwitchInactive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_small_inactive_left.obj"));
		modelMediumLeftParallelSwitchInactive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_parallel_inactive_left.obj"));
		modelMediumLeftParallelSwitchActive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_parallel_active_left.obj"));
		modelLargeLeftParallelSwitchInactive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_parallel_large_inactive_left.obj"));
		modelLargeLeftParallelSwitchActive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_parallel_large_active_left.obj"));
		modelLargeLeftSwitchActive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_medium_active_left.obj"));
		modelLargeLeftSwitchInactive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_medium_inactive_left.obj"));
		modelMediumLeft45degreeSwitchActive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_medium_45degree_active_left.obj"));
		modelMediumLeft45degreeSwitchInActive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_medium_45degree_inactive_left.obj"));
		modelLargeLeft45degreeSwitchActive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_large_45degree_active_left.obj"));
		modelLargeLeft45degreeSwitchInActive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_large_45degree_inactive_left.obj"));
		modelVeryLargeLeftSwitchActive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_very_large_active_left.obj"));
		modelVeryLargeLeftSwitchInactive = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_switch_very_large_left.obj"));

	}

	public void renderMediumActive() {
		modelMediumLeftSwitchActive.renderAll();
	}
	public void renderMediumInactive() {
		modelMediumLeftSwitchInactive.renderAll();
	}
	public void renderMediumParallelInactive() {
		modelMediumLeftParallelSwitchInactive.renderAll();
	}
	public void renderMediumParallelActive() {
		modelMediumLeftParallelSwitchActive.renderAll();
	}
	public void renderLargeParallelInactive() {
		modelLargeLeftParallelSwitchInactive.renderAll();
	}
	public void renderLargeParallelActive() {
		modelLargeLeftParallelSwitchActive.renderAll();
	}
	public void renderLarge90Active() {
		modelLargeLeftSwitchActive.renderAll();
	}
	public void renderLarge90Inactive() {
		modelLargeLeftSwitchInactive.renderAll();
	}

	public void renderVeryLarge90Active() {
		modelVeryLargeLeftSwitchActive.renderAll();
	}
	public void renderVeryLarge90Inactive() {
		modelVeryLargeLeftSwitchInactive.renderAll();
	}

	public void renderMedium45degreeActive() {modelMediumLeft45degreeSwitchActive.renderAll();}
	public void renderMedium45degreeInActive() {modelMediumLeft45degreeSwitchInActive.renderAll();}

	public void renderLarge45degreeActive() {modelLargeLeft45degreeSwitchActive.renderAll();}
	public void renderLarge45degreeInActive() {modelLargeLeft45degreeSwitchInActive.renderAll();}

	public void render(String type, String variant, TileTCRail tcRail, double x, double y, double z) {
		render( type, variant, CommonUtil.getBlockFacing(tcRail.getWorld(), tcRail.getPos().getX(), tcRail.getPos().getY(), tcRail.getPos().getZ()), tcRail.getSwitchState(), x, y, z, 1, 1, 1, 1);
	}

	public void render(String type, String variant, int facing, boolean active, double x, double y, double z, float r, float g, float b, float a) {

		// Bind the texture, so that OpenGL properly textures our block.
		if (variant.contains("embedded"))
			fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_embedded.png"));
		else  fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);

		net.minecraft.client.renderer.GlStateManager.color(r, g, b, a);
		//GL11.glScalef(0.5f, 0.5f, 0.5f);

		if (facing == 3) {
			if(type.equals("medium")){
				GL11.glTranslatef(-1.0f, 0.0f, 1.0f);
			}
			if(type.equals("large_90")){
				GL11.glRotatef(0, 0, 1, 0);
				GL11.glTranslatef(0.0f, 0.0f, -4.0f);
			}
			if(type.equals("very_large_90")){
				GL11.glRotatef(-90, 0, 1, 0);
				GL11.glTranslatef(-0.5f, 0.0f, 0.5f);
			}
			if(type.equals("medium_parallel")){
				GL11.glRotatef(-90, 0, 1, 0);
			}
			if(type.equals("large_parallel")){
				GL11.glRotatef(-90, 0, 1, 0);
				GL11.glTranslatef(-0.5f, 0.0f, 0.5f);
			}
			if(type.equals("medium_45degree")){
				GL11.glRotatef(-90, 0, 1, 0);
				GL11.glTranslatef(0.0f, 0.0f, 0);
			}
			if(type.equals("large_45degree")){
				GL11.glRotatef(-90, 0, 1, 0);
				GL11.glTranslatef(-0.5f, 0.0f, 1.5f);
			}

		}
		if (facing == 1) {
			if(type.equals("medium")){
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glTranslatef(-1.0f, 0.0f, 1.0f);
			}
			if(type.equals("large_90")){
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glTranslatef(0.0f, 0.0f, -4.0f);
			}
			if(type.equals("very_large_90")){
				GL11.glRotatef(90, 0, 1, 0);
				GL11.glTranslatef(-0.5f, 0.0f, 0.5f);
			}
			if(type.equals("medium_parallel")){
				GL11.glRotatef(90, 0, 1, 0);
			}
			if(type.equals("large_parallel")){
				GL11.glRotatef(90, 0, 1, 0);
				GL11.glTranslatef(-0.5f, 0.0f, 0.5f);
			}
			if(type.equals("medium_45degree")){
				GL11.glRotatef(90, 0, 1, 0);
				GL11.glTranslatef(0.0f, 0.0f, 0);
			}
			if(type.equals("large_45degree")){
				GL11.glRotatef(90, 0, 1, 0);
				GL11.glTranslatef(-0.5f, 0.0f, 1.5f);
			}
		}
		if(facing == 2){
			if(type.equals("medium")){
				GL11.glRotatef(90, 0, 1, 0);
				GL11.glTranslatef(-1.0f, 0.0f, 1.0f);
			}
			if(type.equals("large_90")){
				GL11.glRotatef(90, 0, 1, 0);
				GL11.glTranslatef(0.0f, 0.0f, -4.0f);
			}
			if(type.equals("very_large_90")){
				GL11.glRotatef(0, 0, 1, 0);
				GL11.glTranslatef(-0.5f, 0.0f, 0.5f);
			}
			if(type.equals("large_parallel")){
				GL11.glRotatef(0, 0, 1, 0);
				GL11.glTranslatef(-0.5f, 0.0f, 0.5f);
			}
			if(type.equals("medium_45degree")){
				GL11.glRotatef(0, 0, 1, 0);
				GL11.glTranslatef(0.0f, 0.0f, 0f);
			}
			if(type.equals("large_45degree")){
				GL11.glRotatef(0, 0, 1, 0);
				GL11.glTranslatef(-0.5f, 0.0f, 1.5f);
			}
		}
		if(facing == 0){
			if(type.equals("medium")){
				GL11.glRotatef(-90, 0, 1, 0);
				GL11.glTranslatef(-1.0f, 0.0f, 1.0f);
			}
			if(type.equals("large_90")){
				GL11.glRotatef(-90, 0, 1, 0);
				GL11.glTranslatef(.0f, 0.0f, -4.0f);
			}
			if(type.equals("very_large_90")){
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glTranslatef(-0.5f, 0.0f, 0.5f);
			}
			if(type.equals("medium_parallel")){
				GL11.glRotatef(180, 0, 1, 0);
			}
			if(type.equals("large_parallel")){
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glTranslatef(-0.5f, 0.0f, 0.5f);
			}
			if(type.equals("medium_45degree")){
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glTranslatef(0f, 0.0f, 0);
			}
			if(type.equals("large_45degree")){
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glTranslatef(-0.5f, 0.0f, 1.5f);
			}
		}
		if(type.equals("medium")&&!active)this.renderMediumInactive();
		if(type.equals("medium")&&active)this.renderMediumActive();
		if(type.equals("medium_parallel")&&!active)this.renderMediumParallelInactive();
		if(type.equals("medium_parallel")&&active)this.renderMediumParallelActive();
		if(type.equals("large_parallel")&&!active)this.renderLargeParallelInactive();
		if(type.equals("large_parallel")&&active)this.renderLargeParallelActive();
		if(type.equals("large_90")&&!active)this.renderLarge90Inactive();
		if(type.equals("large_90")&&active)this.renderLarge90Active();
		if(type.equals("very_large_90")&&!active)this.renderVeryLarge90Inactive();
		if(type.equals("very_large_90")&&active)this.renderVeryLarge90Active();
		if(type.equals("medium_45degree")&&!active)this.renderMedium45degreeInActive();
		if(type.equals("medium_45degree")&&active)this.renderMedium45degreeActive();
		if(type.equals("large_45degree")&&!active)this.renderLarge45degreeInActive();
		if(type.equals("large_45degree")&&active)this.renderLarge45degreeActive();
		//if(type.equals("large"))this.renderLarge();

		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}
}