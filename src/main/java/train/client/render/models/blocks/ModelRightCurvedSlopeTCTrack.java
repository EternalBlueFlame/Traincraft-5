package train.client.render.models.blocks;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import train.client.core.helpers.SpriteHelper;

import train.client.render.obj.TCObjModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.common.library.Info;
import train.common.tile.TileTCRail;

@SideOnly(Side.CLIENT)
public class ModelRightCurvedSlopeTCTrack extends ModelBase {
    private TCObjModel modelLargeRightCurvedTrack;
    private TCObjModel modelVeryLargeRightCurvedTrack;
    private TCObjModel modelSuperLargeRightCurvedTrack;
    private TCObjModel modelLargeRightCurvedBallast;
    private TCObjModel modelVeryLargeRightCurvedBallast;
    private TCObjModel modelSuperLargeRightCurvedBallast;

    String[] ballastTexture = new String[2];

    public ModelRightCurvedSlopeTCTrack() {
        modelLargeRightCurvedTrack = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_slope_curve_large.obj"));
        modelVeryLargeRightCurvedTrack = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_slope_curve_very_large.obj"));
        modelSuperLargeRightCurvedTrack = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_slope_curve_super_large.obj"));
        modelLargeRightCurvedBallast = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "ballast_curved_slope_large.obj"));
        modelVeryLargeRightCurvedBallast = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "ballast_curved_slope_very_large.obj"));
        modelSuperLargeRightCurvedBallast = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "ballast_curved_slope_super_large.obj"));
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
           modelLargeRightCurvedTrack.renderAll();
            fexcraft.tmt.slim.Tessellator.bindTexture(train.client.core.helpers.SpriteHelper.blockTexture(ballastTexture[0], ballastTexture[1]));

            float r = (float)(ballastColour >> 16 & 255) / 255.0F;
            float g = (float)(ballastColour >> 8 & 255) / 255.0F;
            float b = (float)(ballastColour & 255) / 255.0F;
            net.minecraft.client.renderer.GlStateManager.color(r,g,b,1);
            modelLargeRightCurvedBallast.renderAll();

        }
        if (type.equals("embedded_large")) {
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_embedded.png"));
            modelLargeRightCurvedTrack.renderAll();
            fexcraft.tmt.slim.Tessellator.bindTexture(train.client.core.helpers.SpriteHelper.blockTexture(ballastTexture[0], ballastTexture[1]));

            float r = (float)(ballastColour >> 16 & 255) / 255.0F;
            float g = (float)(ballastColour >> 8 & 255) / 255.0F;
            float b = (float)(ballastColour & 255) / 255.0F;
            net.minecraft.client.renderer.GlStateManager.color(r,g,b,1);
           modelLargeRightCurvedBallast.renderAll();

        }
        if (type.equals("verylarge")) {
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
            modelVeryLargeRightCurvedTrack.renderAll();
            fexcraft.tmt.slim.Tessellator.bindTexture(train.client.core.helpers.SpriteHelper.blockTexture(ballastTexture[0], ballastTexture[1]));

            float r = (float)(ballastColour >> 16 & 255) / 255.0F;
            float g = (float)(ballastColour >> 8 & 255) / 255.0F;
            float b = (float)(ballastColour & 255) / 255.0F;
            net.minecraft.client.renderer.GlStateManager.color(r,g,b,1);
            modelVeryLargeRightCurvedBallast.renderAll();

        }
        if (type.equals("embedded_verylarge")) {
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_embedded.png"));
            modelVeryLargeRightCurvedTrack.renderAll();
            fexcraft.tmt.slim.Tessellator.bindTexture(train.client.core.helpers.SpriteHelper.blockTexture(ballastTexture[0], ballastTexture[1]));

            float r = (float)(ballastColour >> 16 & 255) / 255.0F;
            float g = (float)(ballastColour >> 8 & 255) / 255.0F;
            float b = (float)(ballastColour & 255) / 255.0F;
            net.minecraft.client.renderer.GlStateManager.color(r,g,b,1);
            modelVeryLargeRightCurvedBallast.renderAll();

        }
        if (type.equals("superlarge")) {
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
            modelSuperLargeRightCurvedTrack.renderAll();
            fexcraft.tmt.slim.Tessellator.bindTexture(train.client.core.helpers.SpriteHelper.blockTexture(ballastTexture[0], ballastTexture[1]));

            float r = (float)(ballastColour >> 16 & 255) / 255.0F;
            float g = (float)(ballastColour >> 8 & 255) / 255.0F;
            float b = (float)(ballastColour & 255) / 255.0F;
            net.minecraft.client.renderer.GlStateManager.color(r,g,b,1);
            modelSuperLargeRightCurvedBallast.renderAll();

        }
        if (type.equals("embedded_superlarge")) {
            fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_embedded.png"));
            modelSuperLargeRightCurvedTrack.renderAll();
            fexcraft.tmt.slim.Tessellator.bindTexture(train.client.core.helpers.SpriteHelper.blockTexture(ballastTexture[0], ballastTexture[1]));

            float r = (float)(ballastColour >> 16 & 255) / 255.0F;
            float g = (float)(ballastColour >> 8 & 255) / 255.0F;
            float b = (float)(ballastColour & 255) / 255.0F;
            net.minecraft.client.renderer.GlStateManager.color(r,g,b,1);
            modelSuperLargeRightCurvedBallast.renderAll();

        }
    }

    public void render(String type, TileTCRail tcRail, double x, double y, double z) {

        String iconName;
        Block block = Block.getBlockById(tcRail.getBallastMaterial());
        TextureAtlasSprite icon = SpriteHelper.getTopSprite(block, tcRail.ballastMetadata);
        int colour = ebf.tim.utility.ClientUtil.colorMultiplier(tcRail.getWorld(), tcRail.getPos().getX(), tcRail.getPos().getY()- 1, tcRail.getPos().getZ());
        if (icon != null) {
            iconName = icon.getIconName();
        }
        else {
            iconName = "tc:ballast_test";
            colour = 16777215;
        }
        render( type, CommonUtil.getBlockFacing(tcRail.getWorld(), tcRail.getPos().getX(), tcRail.getPos().getY(), tcRail.getPos().getZ()), x, y, z, 1, 1, 1, 1, iconName, colour);

    }

    public void render(String type, int facing, double x, double y, double z, float r, float g, float b, float a, String  ballastTexture, int colour)
    {


        // Push a blank matrix onto the stack
        GL11.glPushMatrix();

        // Move the object into the correct position on the block (because the OBJ's origin is the
        // center of the object)
        GL11.glTranslatef((float) x, (float) y, (float) z );

        net.minecraft.client.renderer.GlStateManager.color(r, g, b, a);
        // GL11.glScalef(0.5f, 0.5f, 0.5f);
        if (facing == 2) {
            GL11.glTranslatef((float) 0, (float) 0, (float) + 1);
        }
        if (facing == 3) {
            GL11.glRotatef(-90, 0, 1, 0);
            GL11.glTranslatef((float)0, (float) 0, 0);
        }
        if (facing == 1) {
            GL11.glRotatef(90, 0, 1, 0);
            GL11.glTranslatef((float) - 1, (float) 0, (float) + 1);
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
