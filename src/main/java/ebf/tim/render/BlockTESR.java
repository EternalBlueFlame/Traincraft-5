package ebf.tim.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class BlockTESR extends TileEntitySpecialRenderer {

    @Override
    public void render(TileEntity tileEntity, double x, double y, double z, float tick, int destroyStage, float alpha) {
        GL11.glPushMatrix();
        if(tileEntity.getWorld()!=null) {
            GL11.glTranslated(x, y, z);
        }
        tileEntity.addInfoToCrashReport(null);
        GL11.glPopMatrix();
    }
} 