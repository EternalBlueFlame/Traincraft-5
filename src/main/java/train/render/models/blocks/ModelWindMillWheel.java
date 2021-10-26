package train.render.models.blocks;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import train.library.Info;
import train.blocks.windmill.TileWindMill;

@SideOnly(Side.CLIENT)
public class ModelWindMillWheel extends ModelBase {
	//private IModelCustom modelWindMill;
	private IModelCustom modelWindMillWheel;
	private long lastframe;
	private float wheel;
	private int l;
	public float wheel1 = 0.4188790204786391F;

	public ModelWindMillWheel() {
		modelWindMillWheel = AdvancedModelLoader.loadModel(new ResourceLocation(Info.modelPrefix + "wind_mill_wheel.obj"));
	}

	public void render() {
		modelWindMillWheel.renderAll();
	}

	public void render(TileEntity windMill, double x, double y, double z) {
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
		if(windMill.getWorldObj()==null){
			GL11.glTranslated( x,  y+0.5,  z);
			GL11.glScalef(0.8f,0.8f,0.8f);
		} else {
			GL11.glTranslated( x + 0.5,  y+1.5,  z + 0.5);
		}

		// Bind the texture, so that OpenGL properly textures our block.
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation,Info.modelTexPrefix + "water_wheel_uv.png"));
		int j = 0x7A7674;//0x331D14;//0x1C0F0A; //0x5a3222;
		//System.out.println(j);
		float f1 = 1.0F;
		float f2 = (float) (j >> 16 & 255) / 255.0F;
		float f3 = (float) (j >> 8 & 255) / 255.0F;
		float f4 = (float) (j & 255) / 255.0F;
		GL11.glColor4f(f1 * f2, f1 * f3, f1 * f4,1);
		GL11.glScalef(0.45f, 0.45f, 0.45f);
		int windStrength=1;
		if(windMill.getWorldObj()!=null) {
			int facing = windMill.getWorldObj().getBlockMetadata(windMill.xCoord, windMill.yCoord, windMill.zCoord);
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
			windStrength = (int) (windMill instanceof TileWindMill ? ((TileWindMill) windMill).windClient : 0 + (((double) windMill.yCoord / 256) * 10));//* (windMill.yCoord - 64);
			if (windMill.getWorldObj().isThundering()) {
				windStrength *= 7.5;
			} else if (windMill.getWorldObj().isRaining()) {
				windStrength *= 4.5;
			}
		}
		//System.out.println(windStrength+" "+(((double)windMill.yCoord/256)*10));
		if (windStrength > 80)
			windStrength = 80;
		if (windStrength <= 0)
			windStrength = 1;

		long now = System.nanoTime();
		int elapsed = (int) ((now - lastframe) / (1000 * (100 - (windStrength))));
		wheel -= (float) elapsed / (250.0f);
		lastframe = now;
		GL11.glRotatef(-(wheel + wheel1), 0F, 0F, 1F);

		this.render();
		//GL11.glColor3f(1, 1, 1);

		// Pop this matrix from the stack.
		GL11.glPopMatrix();
	}

}
