package train.client.render.models.blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import train.common.library.Info;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public class ModelLeftCurvedSlopeTCTrack extends ModelBase {
    private IModelCustom modelLargeLeftCurvedTrack;
    private IModelCustom modelVeryLargeLeftCurvedTrack;
    private IModelCustom modelSuperLargeLeftCurvedTrack;
    private IModelCustom modelLargeLeftCurvedBallast;
    private IModelCustom modelVeryLargeLeftCurvedBallast;
    private IModelCustom modelSuperLargeLeftCurvedBallast;
    private String[] ballastTexture = new String[2];
    public ModelLeftCurvedSlopeTCTrack() {
        modelLargeLeftCurvedTrack = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_slope_curve_large_l.obj"));
        modelVeryLargeLeftCurvedTrack = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_slope_curve_very_large_l.obj"));
        modelSuperLargeLeftCurvedTrack = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_slope_curve_super_large_l.obj"));
        modelLargeLeftCurvedBallast = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "ballast_curved_slope_large_l.obj"));
        modelVeryLargeLeftCurvedBallast = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "ballast_curved_slope_very_large_l.obj"));
        modelSuperLargeLeftCurvedBallast = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "ballast_curved_slope_super_large_l.obj"));
    }

    public void render(String type, String ballast, int ballastColour) {


        if (ballast.contains(":")) {
            ballastTexture = ballast.split(":", 5);
        }
        else {
            ballastTexture[0] = "minecraft";
            ballastTexture[1] = ballast;

        }
        if (type.equals("large")) {
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
            modelLargeLeftCurvedTrack.renderAll();
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(ballastTexture[0],  "textures/blocks/" + ballastTexture[1] +".png"));

            float r = (float)(ballastColour >> 16 & 255) / 255.0F;
            float g = (float)(ballastColour >> 8 & 255) / 255.0F;
            float b = (float)(ballastColour & 255) / 255.0F;
            GL11.glColor4f(r,g,b,1);
            modelLargeLeftCurvedBallast.renderAll();

        }

        if (type.equals("embedded_large")) {
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_embedded.png"));
            modelLargeLeftCurvedTrack.renderAll();
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(ballastTexture[0],  "textures/blocks/" + ballastTexture[1] +".png"));

            float r = (float)(ballastColour >> 16 & 255) / 255.0F;
            float g = (float)(ballastColour >> 8 & 255) / 255.0F;
            float b = (float)(ballastColour & 255) / 255.0F;
            GL11.glColor4f(r,g,b,1);
            modelLargeLeftCurvedBallast.renderAll();

        }

        if (type.equals("verylarge")) {
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
            modelVeryLargeLeftCurvedTrack.renderAll();
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(ballastTexture[0],  "textures/blocks/" + ballastTexture[1] +".png"));

            float r = (float)(ballastColour >> 16 & 255) / 255.0F;
            float g = (float)(ballastColour >> 8 & 255) / 255.0F;
            float b = (float)(ballastColour & 255) / 255.0F;
            GL11.glColor4f(r,g,b,1);
            modelVeryLargeLeftCurvedBallast.renderAll();

        }

        if (type.equals("embedded_verylarge")) {
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_embedded.png"));
            modelVeryLargeLeftCurvedTrack.renderAll();
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(ballastTexture[0],  "textures/blocks/" + ballastTexture[1] +".png"));

            float r = (float)(ballastColour >> 16 & 255) / 255.0F;
            float g = (float)(ballastColour >> 8 & 255) / 255.0F;
            float b = (float)(ballastColour & 255) / 255.0F;
            GL11.glColor4f(r,g,b,1);
            modelVeryLargeLeftCurvedBallast.renderAll();

        }
        if (type.equals("superlarge")) {
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
            modelSuperLargeLeftCurvedTrack.renderAll();
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(ballastTexture[0],  "textures/blocks/" + ballastTexture[1] +".png"));

            float r = (float)(ballastColour >> 16 & 255) / 255.0F;
            float g = (float)(ballastColour >> 8 & 255) / 255.0F;
            float b = (float)(ballastColour & 255) / 255.0F;
            GL11.glColor4f(r,g,b,1);
            modelSuperLargeLeftCurvedBallast.renderAll();

        }

        if (type.equals("embedded_superlarge")) {
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_embedded.png"));
            modelSuperLargeLeftCurvedTrack.renderAll();
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(ballastTexture[0],  "textures/blocks/" + ballastTexture[1] +".png"));

            float r = (float)(ballastColour >> 16 & 255) / 255.0F;
            float g = (float)(ballastColour >> 8 & 255) / 255.0F;
            float b = (float)(ballastColour & 255) / 255.0F;
            GL11.glColor4f(r,g,b,1);
            modelSuperLargeLeftCurvedBallast.renderAll();

        }
    }

    public void render(String type, TileTCRail tcRail, double x, double y, double z) {

        String iconName;
        Block block = Block.getBlockById(tcRail.getBallastMaterial());
        IIcon icon = block.getIcon(1, tcRail.ballastMetadata);
        int colour = block.colorMultiplier(tcRail.getWorldObj(), tcRail.xCoord, tcRail.yCoord- 1, tcRail.zCoord);
        if (icon != null) {
            iconName = icon.getIconName();
        }
        else {
            iconName = "tc:ballast_test";
            colour = 16777215;
        }
        render( type, tcRail.getWorldObj().getBlockMetadata(tcRail.xCoord, tcRail.yCoord, tcRail.zCoord), x, y, z, 1, 1, 1, 1, iconName, colour);

    }

    public void render(String type, int facing, double x, double y, double z, float r, float g, float b, float a, String  ballastTexture, int colour)
    {


        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because the OBJ's origin is the
        // center of the object)
        GL11.glTranslatef((float) x , (float) y, (float) z);

        GL11.glColor4f(r, g, b, a);
        // GL11.glScalef(0.5f, 0.5f, 0.5f);
        if (facing == 2) {
            GL11.glTranslatef((float) 0, (float) 0, (float) +1);
        }
        if (facing == 3) {
            GL11.glRotatef(-90, 0, 1, 0);
            GL11.glTranslatef((float) 0, (float) 0, (float) 0);
        }
        if (facing == 1) {
            GL11.glRotatef(90, 0, 1, 0);
            GL11.glTranslatef((float) - 1, (float) 0, (float) +1);
        }
        if (facing == 0) {
            GL11.glRotatef(180, 0, 1, 0);
            GL11.glTranslatef((float) - 1, (float) 0, (float) 0);
        }
        // GL11.glTranslatef(0.0f, 0.0f, -1.0f);
        render(type, ballastTexture, colour);

        // Pop this matrix from the stack.
        GL11.glPopMatrix();
    }
}
