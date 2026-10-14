/*******************************************************************************
 * Copyright (c) 2013 Mrbrutal. All rights reserved.
 * 
 * @name Traincraft
 * @author Mrbrutal
 ******************************************************************************/

package train.client.render.models.blocks;

import train.client.render.obj.TCObjModel;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.common.library.Info;

public class ModelPresent extends ModelBase {
	private TCObjModel present;
	
	public ModelPresent() {
		present = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "present.obj"));
	}
	
	public void render() {
		present.renderAll();
	}

	public void render(int color) {
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glScalef(0.4f, 0.4f, 0.4f);
		fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation,Info.modelTexPrefix + "present2.png"));
		float f1 = 1.0F;
		float f2 = (float) (color >> 16 & 255) / 255.0F;
		float f3 = (float) (color >> 8 & 255) / 255.0F;
		float f4 = (float) (color & 255) / 255.0F;
		net.minecraft.client.renderer.GlStateManager.color(f1 * f2, f1 * f3, f1 * f4, 1.0F);
		render();
		GL11.glEnable(GL11.GL_LIGHTING);
		fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation,Info.modelTexPrefix + "present3.png"));
		net.minecraft.client.renderer.GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		render();
		GL11.glPopMatrix();
	}
}
