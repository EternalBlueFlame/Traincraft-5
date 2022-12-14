/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.render;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.blocks.generator.TileGeneratorDiesel;
import train.library.Info;
import train.render.models.blocks.ModelGeneratorDiesel;

public class RenderGeneratorDiesel extends TiMTESR {

	private static final ModelGeneratorDiesel modelGenerator = new ModelGeneratorDiesel((float) (1.0 / 16.0));
	private static final ResourceLocation texture = new ResourceLocation(Info.resourceLocation,Info.modelTexPrefix + "generator_diesel.png");

	public RenderGeneratorDiesel() {
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float tick) {
		GL11.glPushMatrix();

		GL11.glTranslated(x, y, z);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);

		if(tile==null || tile.getWorldObj()==null){
			GL11.glTranslatef(0, -0.5F, 0);
			GL11.glScalef(0.85f,0.85f,0.85f);
		} else {
			GL11.glTranslatef(0.5F, 0.0F, 0.5F);
		}
		modelGenerator.render(0.0625F, tile==null?0:((TileGeneratorDiesel) tile).facing);
		GL11.glPopMatrix();
	}
}
