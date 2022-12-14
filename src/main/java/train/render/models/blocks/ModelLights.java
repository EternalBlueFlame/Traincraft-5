/*******************************************************************************
 * Copyright (c) 2013 Mrbrutal. All rights reserved.
 * 
 * @name Traincraft
 * @author Mrbrutal
 ******************************************************************************/

package train.render.models.blocks;

import fexcraft.tmt.slim.ModelBase;
import fexcraft.tmt.slim.ModelRendererTurbo;
import fexcraft.tmt.slim.TextureManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import train.library.Info;

public class ModelLights extends ModelBase {
	private ModelRendererTurbo branch;
	private ModelRendererTurbo plane;
	private ModelRendererTurbo bulb;
	
	public ModelLights() {
		branch = new ModelRendererTurbo(this);
		branch.addObj(Info.getModelAddress("lights2.branch1.obj"));
		plane = new ModelRendererTurbo(this);
		plane.addObj(Info.getModelAddress("lights2.plane1.obj"));
		bulb = new ModelRendererTurbo(this);
		bulb.addObj(Info.getModelAddress("lights2.bulb1.obj"));
	}

	
	public void render(int color, World world) {
		//GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPushMatrix();
		GL11.glRotatef(90f, 0, 1, 0);
		TextureManager.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "lights3.png"));
		branch.render();
		if(world!=null) {
			Minecraft.getMinecraft().entityRenderer.disableLightmap(1D);
		}
		GL11.glEnable(GL11.GL_BLEND);
		TextureManager.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "lights.png"));
		bulb.render();
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.3f);
		plane.render();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		if(world!=null) {
			Minecraft.getMinecraft().entityRenderer.enableLightmap(1D);
		}
		GL11.glPopMatrix();
	}
}
