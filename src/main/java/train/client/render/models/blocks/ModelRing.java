/*******************************************************************************
 * Copyright (c) 2013 Mrbrutal. All rights reserved.
 * 
 * @name Traincraft
 * @author Mrbrutal
 ******************************************************************************/

package train.client.render.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.common.library.Info;
import fexcraft.tmt.slim.ModelRendererTurbo;

public class ModelRing extends ModelBase  {
private ModelRendererTurbo ring;
	
	public ModelRing() {
		ring = new ModelRendererTurbo((fexcraft.tmt.slim.ModelBase) null).addObj(Info.modelPrefix + "ring.obj");
	}
	
	public void render() {
		ring.render(1);
	}
	
	public void render(int color) {
		//GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glRotatef(90,0,0,1);
		fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "ring.png"));
		render();
		GL11.glPopMatrix();
	}
}
