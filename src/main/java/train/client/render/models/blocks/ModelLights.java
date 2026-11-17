/*******************************************************************************
 * Copyright (c) 2013 Mrbrutal. All rights reserved.
 * 
 * @name Traincraft
 * @author Mrbrutal
 ******************************************************************************/

package train.client.render.models.blocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import fexcraft.tmt.slim.ModelRendererTurbo;
import org.lwjgl.opengl.GL11;
import train.common.library.Info;

public class ModelLights extends ModelBase {
	private ModelRendererTurbo branch;
	private ModelRendererTurbo bulb;
	private ModelRendererTurbo plane;
	
	public ModelLights() {
		String model = Info.modelPrefix + "lights2.obj";
		branch = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(model, "Branch_1");
		bulb = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(model, "Bulb_1");
		plane = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(model, "Plane_1");
	}
	
	public void render(String partname) {
		if ("Branch_1".equals(partname)) {
			branch.render();
		} else if ("Bulb_1".equals(partname)) {
			bulb.render();
		} else if ("Plane_1".equals(partname)) {
			plane.render();
		}
	}
	
	public void render(int color) {
		//GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glRotatef(90f, 0, 1, 0);
		GL11.glRotatef(90,0,0,1);
		fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation,Info.modelTexPrefix + "lights3.png"));
		render("Branch_1");
		Minecraft.getMinecraft().entityRenderer.disableLightmap(1D);
		GL11.glEnable(GL11.GL_BLEND);
		fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation,Info.modelTexPrefix + "lights.png"));
		render("Bulb_1");
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.3f);
		render("Plane_1");
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		Minecraft.getMinecraft().entityRenderer.enableLightmap(1D);
		GL11.glPopMatrix();
	}
}
