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
public class ModelLeftSwitchTCTrack extends ModelBase {
	private ModelRendererTurbo modelMediumLeftSwitchActive;
	private ModelRendererTurbo modelMediumLeftSwitchInactive;
	private ModelRendererTurbo modelMediumLeftParallelSwitchInactive;
	private ModelRendererTurbo modelMediumLeftParallelSwitchActive;
	private ModelRendererTurbo modelLargeLeftParallelSwitchInactive;
	private ModelRendererTurbo modelLargeLeftParallelSwitchActive;
	private ModelRendererTurbo modelLargeLeftSwitchActive;
	private ModelRendererTurbo modelLargeLeftSwitchInactive;

	private ModelRendererTurbo modelVeryLargeLeftSwitchActive;
	private ModelRendererTurbo modelVeryLargeLeftSwitchInactive;
	private ModelRendererTurbo modelMediumLeft45degreeSwitchActive;
	private ModelRendererTurbo modelMediumLeft45degreeSwitchInActive;
	private ModelRendererTurbo modelLargeLeft45degreeSwitchActive;
	private ModelRendererTurbo modelLargeLeft45degreeSwitchInActive;

	public ModelLeftSwitchTCTrack() {
		modelMediumLeftSwitchActive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_small_active_left.obj");
		modelMediumLeftSwitchInactive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_small_inactive_left.obj");
		modelMediumLeftParallelSwitchInactive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_parallel_inactive_left.obj");
		modelMediumLeftParallelSwitchActive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_parallel_active_left.obj");
		modelLargeLeftParallelSwitchInactive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_parallel_large_inactive_left.obj");
		modelLargeLeftParallelSwitchActive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_parallel_large_active_left.obj");
		modelLargeLeftSwitchActive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_medium_active_left.obj");
		modelLargeLeftSwitchInactive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_medium_inactive_left.obj");
		modelMediumLeft45degreeSwitchActive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_medium_45degree_active_left.obj");
		modelMediumLeft45degreeSwitchInActive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_medium_45degree_inactive_left.obj");
		modelLargeLeft45degreeSwitchActive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_large_45degree_active_left.obj");
		modelLargeLeft45degreeSwitchInActive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_large_45degree_inactive_left.obj");
		modelVeryLargeLeftSwitchActive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_very_large_active_left.obj");
		modelVeryLargeLeftSwitchInactive = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "track_switch_very_large_left.obj");

	}

	public void renderMediumActive() {
		modelMediumLeftSwitchActive.render(1);
	}
	public void renderMediumInactive() {
		modelMediumLeftSwitchInactive.render(1);
	}
	public void renderMediumParallelInactive() {
		modelMediumLeftParallelSwitchInactive.render(1);
	}
	public void renderMediumParallelActive() {
		modelMediumLeftParallelSwitchActive.render(1);
	}
	public void renderLargeParallelInactive() {
		modelLargeLeftParallelSwitchInactive.render(1);
	}
	public void renderLargeParallelActive() {
		modelLargeLeftParallelSwitchActive.render(1);
	}
	public void renderLarge90Active() {
		modelLargeLeftSwitchActive.render(1);
	}
	public void renderLarge90Inactive() {
		modelLargeLeftSwitchInactive.render(1);
	}

	public void renderVeryLarge90Active() {
		modelVeryLargeLeftSwitchActive.render(1);
	}
	public void renderVeryLarge90Inactive() {
		modelVeryLargeLeftSwitchInactive.render(1);
	}

	public void renderMedium45degreeActive() {modelMediumLeft45degreeSwitchActive.render(1);}
	public void renderMedium45degreeInActive() {modelMediumLeft45degreeSwitchInActive.render(1);}

	public void renderLarge45degreeActive() {modelLargeLeft45degreeSwitchActive.render(1);}
	public void renderLarge45degreeInActive() {modelLargeLeft45degreeSwitchInActive.render(1);}

	public void render(String type, String variant, TileTCRail tcRail, double x, double y, double z) {
		render( type, variant, CommonUtil.getBlockFacing(tcRail.getWorldObj(), tcRail.xCoord, tcRail.yCoord, tcRail.zCoord), tcRail.getSwitchState(), x, y, z, 1, 1, 1, 1);
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

		GL11.glColor4f(r, g, b, a);
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
		GL11.glRotatef(90,0,0,1);
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