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

public class ModelRing extends ModelBase  {
private TCObjModel ring;
	
	public ModelRing() {
		ring = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "ring.obj"));
	}
	
	public void render() {
		ring.renderAll();
	}
	
	public void render(int color) {
		//GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		net.minecraft.client.renderer.GlStateManager.color(1, 1, 1, 1);
		fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "ring.png"));
		render();
		GL11.glPopMatrix();
	}
}
