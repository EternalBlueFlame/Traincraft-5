package train.render.models.blocks;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import fexcraft.tmt.slim.ModelBase;
import fexcraft.tmt.slim.ModelRendererTurbo;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.library.Info;

@SideOnly(Side.CLIENT)
public class ModelWindMill extends ModelBase {
	private ModelRendererTurbo modelWindMill;

	public ModelWindMill() {
		modelWindMill = new ModelRendererTurbo(this);
		modelWindMill.addObj(Info.getModelAddress("wind_mill.obj"));
	}

	public void render() {
		modelWindMill.render(1);
	}

	public void render(TileEntity windMill, double x, double y, double z) {
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
		if(windMill==null ||windMill.getWorldObj()==null){
			GL11.glTranslated( x,  y-0.1,  z);
			GL11.glRotatef(90,1,0,0);
			GL11.glRotatef(90,0,0,1);
			GL11.glScalef(0.8f,0.8f,0.8f);
		} else {
			GL11.glTranslated( x + 0.5,  y+0.5,  z + 0.5);
			GL11.glRotatef(90,1,0,0);
			GL11.glRotatef(90,0,0,1);
		}

		// Bind the texture, so that OpenGL properly textures our block.
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "water_wheel_uv.png"));
		int j = 0x331D14;//0x1C0F0A; //0x5a3222;
		//System.out.println(j);
		float f1 = 1.0F;
		float f2 = (float) (j >> 16 & 255) / 255.0F;
		float f3 = (float) (j >> 8 & 255) / 255.0F;
		float f4 = (float) (j & 255) / 255.0F;
		GL11.glColor4f(f1 * f2, f1 * f3, f1 * f4, 1);
		GL11.glScalef(0.5f, 0.5f, 0.5f);
		if(windMill==null && windMill.getWorldObj()!=null) {
			int facing = CommonUtil.getBlockFacing(windMill.getWorldObj(), windMill.xCoord,windMill.yCoord, windMill.zCoord);
			if (facing == 3) {
			}
			if (facing == 1) {
				GL11.glRotatef(180, 0, 1, 0);
			}
			if (facing == 0) {
				GL11.glRotatef(-90, 0, 1, 0);
			}
			if (facing == 2) {
				GL11.glRotatef(90, 0, 1, 0);
			}
		}

		this.render();

		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}

}
