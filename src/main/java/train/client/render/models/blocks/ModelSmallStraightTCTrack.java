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

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ModelSmallStraightTCTrack extends ModelBase {
	
	private TCObjModel modelSmallStraight;
	private TCObjModel modelRoadCrossing;
	private TCObjModel modelRoadCrossingBase;
	private TCObjModel modelRoadCrossingDynamic;

	String[] ballastTexture = new String[2];

	public ModelSmallStraightTCTrack() {
		modelSmallStraight = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_normal.obj"));
		modelRoadCrossing = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_roadcrossing.obj"));
		modelRoadCrossingBase = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_roadcrossing_base.obj"));
		modelRoadCrossingDynamic = TCObjModel.load(new ResourceLocation(Info.modelPrefix + "track_roadcrossing_dynamic.obj"));
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


		render( type, CommonUtil.getBlockFacing(tcRail.getWorld(), tcRail.getPos().getX(), tcRail.getPos().getY(), tcRail.getPos().getZ()), x, y, z, 1, 1, 1, 1 , iconName, colour);
	}


	public void render(String type, int facing, double x, double y, double z, float r, float g, float b, float a, @Nullable  String  ballastTexture, int colour)
	{


		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the
		// center of the object)
		GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);

		net.minecraft.client.renderer.GlStateManager.color(r, g, b, a);
		// GL11.glScalef(0.5f, 0.5f, 0.5f);

		if (facing == 3) {
			GL11.glRotatef(-90, 0, 1, 0);
		}
		if (facing == 1) {
			GL11.glRotatef(90, 0, 1, 0);
		}
		if (facing == 0) {
			GL11.glRotatef(180, 0, 1, 0);
		}
		// GL11.glTranslatef(0.0f, 0.0f, -1.0f);
		render(type, ballastTexture, colour);

		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}




	public void render( String type, @Nullable String  ballast, int colour)
	{
		if (ballast !=null) {
			if (ballast.contains(":")) {
				ballastTexture = ballast.split(":", 5);
			} else {
				ballastTexture[0] = "minecraft";
				ballastTexture[1] = ballast;

			}
		}
		else {
			ballastTexture[0] = "minecraft";
			ballastTexture[1] = "air";
		}


		// Bind the texture, so that OpenGL properly textures our block.
		if (type.equals("normal")) {
 			fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_normal.png"));
		 	modelSmallStraight.renderAll();
		}
		if (type.equals("embedded")) {
			fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_embedded.png"));
			modelSmallStraight.renderAll();
		}

		if (type.equals("crossing")) {
			fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_roadcrossing.png"));
			modelRoadCrossing.renderAll();
		}		
		if (type.equals("crossing1")) {
			fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_roadcrossing_1.png"));
			modelRoadCrossing.renderAll();
		}		
		if (type.equals("crossing2")) {
			fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_roadcrossing_2.png"));
			modelRoadCrossing.renderAll();
		}
		if (type.equals("dynamic")){
			fexcraft.tmt.slim.Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "track_roadcrossing_base.png"));
			modelRoadCrossing.renderAll();
			fexcraft.tmt.slim.Tessellator.bindTexture(train.client.core.helpers.SpriteHelper.blockTexture(ballastTexture[0], ballastTexture[1]));
			float r =  (float)(colour >> 16 & 255) / 255.0F;
			float g = (float)(colour >> 8 & 255) / 255.0F;
			float b = (float)(colour & 255) / 255.0F;
			net.minecraft.client.renderer.GlStateManager.color(r,g,b,1);
			modelRoadCrossingDynamic.renderAll();


		}


	}

}
