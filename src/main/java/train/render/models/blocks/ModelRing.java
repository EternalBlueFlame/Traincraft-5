/*******************************************************************************
 * Copyright (c) 2013 Mrbrutal. All rights reserved.
 * 
 * @name Traincraft
 * @author Mrbrutal
 ******************************************************************************/

package train.render.models.blocks;

import cpw.mods.fml.client.FMLClientHandler;
import fexcraft.tmt.slim.ModelBase;
import fexcraft.tmt.slim.ModelRendererTurbo;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.library.Info;

public class ModelRing extends ModelBase {
private ModelRendererTurbo ring;
	
	public ModelRing() {
		ring = new ModelRendererTurbo(this);
		ring.addObj(Info.getModelAddress("ring.obj"));
	}
	
	public void render() {
		ring.render(1);
	}
	
	public void render(int color) {
		//GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glColor4f(1, 1, 1, 1);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "ring.png"));
		render();
		GL11.glPopMatrix();
	}
}
