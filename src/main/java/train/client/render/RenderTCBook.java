/*******************************************************************************
 * Copyright (c) 2013 Mrbrutal. All rights reserved.
 *
 * @name Traincraft
 * @author Mrbrutal
 ******************************************************************************/

package train.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import train.client.render.models.blocks.ModelTCBook;
import train.common.tile.TileBook;

public class RenderTCBook extends TileEntitySpecialRenderer {

    private final ModelTCBook book = new ModelTCBook();

    @Override
    public void render(TileEntity tileentity, double x, double y, double z, float tick, int destroyStage, float alpha) {
        book.render((TileBook) tileentity, x, y, z);
    }
}