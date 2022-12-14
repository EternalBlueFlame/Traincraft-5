/*******************************************************************************
 * Copyright (c) 2014 Mrbrutal. All rights reserved.
 * 
 * @name Traincraft
 * @author Mrbrutal
 ******************************************************************************/

package train.render.models.blocks;

import fexcraft.tmt.slim.ModelBase;
import fexcraft.tmt.slim.ModelRendererTurbo;
import fexcraft.tmt.slim.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.library.Info;

public class ModelBridgePillar extends ModelBase {
	private ModelRendererTurbo bridgePillar;

	public ModelBridgePillar() {
		bridgePillar = new ModelRendererTurbo(this);
		bridgePillar.addObj(Info.getModelAddress("bridge_block.obj"));
	}
	
	public void render() {
		bridgePillar.render(1);
	}
	
	public void render(TileEntity pillar, double x, double y, double z) {
		if(pillar !=null || pillar.getWorldObj()==null){
			GL11.glTranslated( x,  y+0.15,  z+0.5);
			GL11.glRotatef(180,0,0,1);
		} else {
			GL11.glTranslated( x + 0.5,  y+0.5,  z+1);
		}

		Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_slope.png"));
		/*int facing = pillar.getWorldObj().getBlockMetadata(pillar.xCoord, pillar.yCoord, pillar.zCoord);
		if(facing == 2 || facing==0){
			GL11.glRotatef(90, 0, 1, 0);
		}*/
		render();
	}
}
