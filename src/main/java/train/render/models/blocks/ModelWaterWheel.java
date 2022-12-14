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
import train.blocks.waterwheel.TileWaterWheel;
import train.library.Info;

@SideOnly(Side.CLIENT)
public class ModelWaterWheel extends ModelBase {
	private ModelRendererTurbo modelWaterWheel;
	private long lastframe;
	private float wheel;
	private int l;
	public float wheel1 = 0.4188790204786391F;

	public ModelWaterWheel() {
		modelWaterWheel = new ModelRendererTurbo(this);
		modelWaterWheel.addObj(Info.getModelAddress("water_wheel.obj"));
	}

	public void render() {
		modelWaterWheel.render(1);
	}

	public void render(TileEntity waterWheel, double x, double y, double z) {
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
		if(waterWheel==null || waterWheel.getWorldObj()==null){
			GL11.glTranslated( x,  y+0.15f,  z);
		} else {
			GL11.glTranslated( x + 0.5,  y+0.5,  z + 0.5);
		}
		GL11.glRotatef(90,0,0,1);

		// Bind the texture, so that OpenGL properly textures our block.
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "water_wheel_uv.png"));
		int j = 0x331D14;//0x1C0F0A; //0x5a3222;
		//System.out.println(j);
		float f1 = 1.0F;
		float f2 = (float) (j >> 16 & 255) / 255.0F;
		float f3 = (float) (j >> 8 & 255) / 255.0F;
		float f4 = (float) (j & 255) / 255.0F;
		GL11.glColor4f(f1 * f2, f1 * f3, f1 * f4, 1);
		// Render the object, using modelTutBox.renderAll();
		if(waterWheel!=null && waterWheel.getWorldObj()!=null) {
			int facing = CommonUtil.getBlockFacing(waterWheel.getWorldObj(), waterWheel.xCoord,waterWheel.yCoord, waterWheel.zCoord);
			if (facing == 3) {
				GL11.glRotatef(90, 1, 0, 0);
			}
			if (facing == 1) {
				GL11.glRotatef(-90, 1, 0, 0);
			}
			if (facing == 2) {
				GL11.glRotatef(180, 1, 0, 0);
			}
		}
		if(waterWheel!=null && waterWheel instanceof TileWaterWheel) {
			if (((TileWaterWheel)waterWheel).getWaterDir() > -1001) {
				long now = System.nanoTime();
				int elapsed = (int) ((now - lastframe) / (1000 * 100));
				wheel -= (float) elapsed / 300.0f;
				lastframe = now;
				//System.out.println(facing);
				if (((TileWaterWheel)waterWheel).getWaterDir() == 0 || ((TileWaterWheel)waterWheel).getWaterDir() == -3 || ((TileWaterWheel)waterWheel).getWaterDir() == -1 || ((TileWaterWheel)waterWheel).getWaterDir() == 1 || ((TileWaterWheel)waterWheel).getWaterDir() == -2) {
					GL11.glRotatef(-(wheel + wheel1), 0F, 1F, 0F);
				}
			}
		}
		GL11.glScalef(0.25f, 0.5f, 0.25f);
		this.render();
		//GL11.glColor3f(1, 1, 1);

		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}
}