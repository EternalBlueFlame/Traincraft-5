package train.client.render.models.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import train.common.library.Info;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public class ModelLeftParallelCurveTCTrack extends ModelBase {

    private IModelCustom modelSmallLeftParallelCurve;
    private IModelCustom modelMediumLeftParallelCurve;
    private IModelCustom modelLargeLeftParallelCurve;
    private IModelCustom model20x2SCurveLeft;

    public ModelLeftParallelCurveTCTrack() {
        modelSmallLeftParallelCurve = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_parallel_s_left.obj"));
        modelMediumLeftParallelCurve = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_parallel_m_left.obj"));
        modelLargeLeftParallelCurve = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_parallel_l_left.obj"));
        model20x2SCurveLeft = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "20x2_s_curve_left.obj"));
    }

    public void renderSmall() {modelSmallLeftParallelCurve.renderAll();}
    public void renderMedium() {modelMediumLeftParallelCurve.renderAll();}
    public void renderLarge() {modelLargeLeftParallelCurve.renderAll();}

    public void render20x2() {model20x2SCurveLeft.renderAll();}

    public void render(String type, String variant, TileTCRail tcRail, double x, double y, double z) {
        render( type, variant, tcRail.getWorld().getBlockMetadata(tcRail.xCoord, tcRail.yCoord, tcRail.zCoord), x, y, z, 1, 1, 1, 1 );
    }

    public void render(String type,String variant,int facing, double x, double y, double z, float r, float g, float b, float a) {

        // Bind the texture, so that OpenGL properly textures our block.
        if (variant.contains("embedded"))
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_embedded.png"));
        else  fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z - 1.5f);
        GL11.glColor4f(r, g, b, a);
        //GL11.glScalef(0.5f, 0.5f, 0.5f);
        /** where l = 0 is SOUTH
         *        l = 1 is WEST
         *        l = 2 is NORTH
         *        l = 3 is EAST
         */
        if (facing == 0) {
            GL11.glRotatef(180, 0, 1, 0);
            GL11.glTranslatef(0, 0.0f, -2.0f);

        }

        if (facing == 1) {
            GL11.glRotatef(90, 0, 1, 0);
            GL11.glTranslatef(-2f, 0.0f, 0f);

        }

        if (facing == 2) {
            GL11.glTranslatef(0, 0.0f,   2.0f);

        }

        if (facing == 3) {
            GL11.glRotatef(-90, 0, 1, 0);
            GL11.glTranslatef(2.0f, 0.0f, 0);

        }

        if(type.equals("small"))this.renderSmall();
        if(type.equals("medium"))this.renderMedium();
        if(type.equals("large"))this.renderLarge();
        if(type.equals("20x2"))this.render20x2();

        GL11.glPopMatrix();
    }

}