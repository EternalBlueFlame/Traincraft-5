package train.client.render.models.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import train.common.library.Info;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)

public class ModelLeft45DegreeTurnTCTrack {

    private IModelCustom modelMediumLeft45DegreeTurn;
    private IModelCustom modelLargeLeft45DegreeTurn;
    private IModelCustom modelVeryLarge45DegreeTurn;
    private IModelCustom modelSuperLarge45DegreeTurn;
    private IModelCustom model9x2045DegreeTurn;
    private IModelCustom model10x2245DegreeTurn;

    public ModelLeft45DegreeTurnTCTrack(){
        modelMediumLeft45DegreeTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_45degree_medium_left.obj"));
        modelLargeLeft45DegreeTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_45degree_large_left.obj"));
        modelVeryLarge45DegreeTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_45degree_very_large_left.obj"));
        modelSuperLarge45DegreeTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_45degree_super_large_left.obj"));
        model9x2045DegreeTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_45degree_9x20_left.obj"));
        model10x2245DegreeTurn = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "track_curve_45degree_10x22_left.obj"));

    }

    public void renderMedium() {modelMediumLeft45DegreeTurn.renderAll();}
    public void renderLarge() {modelLargeLeft45DegreeTurn.renderAll();}
    public void renderVeryLarge() {modelVeryLarge45DegreeTurn.renderAll();}
    public void renderSuperLarge() {modelSuperLarge45DegreeTurn.renderAll();}

    public void render9x20() {model9x2045DegreeTurn.renderAll();}

    public void render10x22() {model10x2245DegreeTurn.renderAll();}


    public void render(String type,String variant, TileTCRail tcRail, double x, double y, double z) {
        render( type, variant, tcRail.getWorldObj().getBlockMetadata(tcRail.xCoord, tcRail.yCoord, tcRail.zCoord), x, y, z, 1, 1, 1, 1);
    }

    public void render(String type, String variant ,int facing, double x, double y, double z, float r, float g, float b, float a) {

        // Bind the texture, so that OpenGL properly textures our block.
        if (variant.contains("embedded"))
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_embedded.png"));
        else fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));

        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);

        GL11.glColor4f(r, g, b, a);
        //GL11.glScalef(0.5f, 0.5f, 0.5f);

        if (facing == 0) {
            GL11.glRotatef(180, 0, 1, 0);
            GL11.glTranslatef(0.5f,0,0.5f);
        }
        if (facing == 1) {
            GL11.glRotatef(90, 0, 1, 0);
            GL11.glTranslatef(0.5f,0,0.5f);
        }
        if (facing == 2) {
            GL11.glTranslatef(0.5f,0,0.5f);
        }
        if (facing == 3) {
            GL11.glRotatef(-90, 0, 1, 0);
            GL11.glTranslatef(0.5f,0,0.5f);
        }

        if (type.equals("medium") )
            this.renderMedium();
        if (type.equals("large") )
            this.renderLarge();
        if (type.equals("verylarge") )
            this.renderVeryLarge();
        if (type.equals("superlarge") )
            this.renderSuperLarge();
        if (type.equals("9x20"))
            this.render9x20();
        if (type.equals("10x22"))
            this.render10x22();


        // Pop this matrix from the stack.
        GL11.glPopMatrix();
    }
}
