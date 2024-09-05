package train.common.wellcar.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import fexcraft.tmt.slim.Tessellator;
import train.client.render.models.containers.Modelcontainer53;
import train.common.wellcar.BlockFiftyThreeFootContainer;
import train.common.wellcar.TileFiftyThreeFootContainer;

public class FiftyThreeFootContainerRender extends TileEntitySpecialRenderer {
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float p_147500_8_) {

        //Tessellator.bindTexture(new ResourceLocation("tc:textures/trains/container56_Yellow.png"));
        //This will make your block brightness dependent from surroundings lighting.

        TileFiftyThreeFootContainer theTileEntity = (TileFiftyThreeFootContainer)tileEntity;
        Block two = tileEntity.getWorld().getBlock(tileEntity.xCoord,tileEntity.yCoord - 1,tileEntity.zCoord);
        Block three = tileEntity.getWorld().getBlock(tileEntity.xCoord,tileEntity.yCoord - 2,tileEntity.zCoord);
        Block four = tileEntity.getWorld().getBlock(tileEntity.xCoord,tileEntity.yCoord - 3,tileEntity.zCoord);
               /* int skyLight = tileEntity.getWorld().getSkyBlockTypeBrightness(EnumSkyBlock.Block, (int)x,(int)y,(int)z);

               // skyLight= tileEntity.getWorld().getSkyBlockTypeBrightness(EnumSkyBlock.Sky, (int)x, (int)y, (int)z) << 20 | (skyLight<0?0:skyLight) << 4; OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  skyLight % 65536,  skyLight * 0.00001525878f);
                GL11.glColor4f(1,1,1,1);
                tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
                skyLight=tileEntity.getWorld().getSkyBlockTypeBrightness(EnumSkyBlock.Sky, (int)x, (int)y, (int)z) << 20 | (Math.max(skyLight, 0)) << 4;
                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  skyLight % 65536,  skyLight * 0.00001525878f);*/
      //  GL11.glColor4f(1,1,1,1);

        Tessellator.bindTexture(new ResourceLocation("tc:textures/trains/container53_" + theTileEntity.getAvailableColors().get(theTileEntity.currentColor) + ".png"));
       // GL11.glColor4f(1,1,1,1);
       // tessellator.setBrightness(theBlock.getMixedBrightnessForBlock(tileEntity.getWorld(), (int)x, (int)y, (int)z));
       // tessellator.setBrightness(15);
       // Tessellator.bindTexture(new ResourceLocation("tc:textures/trains/test.png"));
        GL11.glPushMatrix();

       //GL11.glRotatef(180,0,10,1);

        if (two instanceof BlockFiftyThreeFootContainer) {
            GL11.glTranslated(x + 0.5, y + 0.8, z + 0.44F);
        } else if (three instanceof BlockFiftyThreeFootContainer) {
            GL11.glTranslated(x + 0.5, y + 1, z + 0.44F);
        } else {
            GL11.glTranslated(x+ 0.5 ,y + 0.5, z+ 0.44F);
        }
        GL11.glRotatef(180,0,0,1);
        Modelcontainer53 theContainer = new Modelcontainer53();
        theContainer.render(null,0, 0, 0,0, 0, .0625f);
        GL11.glPopMatrix();
    }

    @Override
    protected void bindTexture(ResourceLocation p_147499_1_){
    }
}
