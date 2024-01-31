/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 *
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.client.render.models.blocks.ModelStopper;
import train.common.library.Info;
import train.common.tile.TileStopper;

public class RenderStopper extends TileEntitySpecialRenderer {

    private static final ModelStopper modelStopper = new ModelStopper(1.0F / 16.0F);
    private static final ResourceLocation texture = new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "buffer.png");

    public RenderStopper() {
    }

    public void render(TileEntity tile, double x, double y, double z) {
        fexcraft.tmt.slim.Tessellator.bindTexture(texture);
        GL11.glPushMatrix();

        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);
        modelStopper.render(0.0625F, ((TileStopper) tile).getFacing());

        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float var8) {
        render(tile, x, y, z);
    }
}
