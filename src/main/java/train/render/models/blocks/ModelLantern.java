package train.render.models.blocks;

import fexcraft.tmt.slim.ModelBase;
import fexcraft.tmt.slim.ModelRendererTurbo;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import train.blocks.lantern.TileLantern;
import train.library.Info;

@SideOnly(Side.CLIENT)
public class ModelLantern extends ModelBase {
	private ModelRendererTurbo modelLantern;

	public ModelLantern() {
		modelLantern = new ModelRendererTurbo(this);
		modelLantern.addObj(Info.getModelAddress("lantern.obj"));

		//renderItem.setRenderManager(RenderManager.instance);
	}

	public void render() {
		modelLantern.render(1);
	}

	public void render(TileEntity lantern, double x, double y, double z) {
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		// Move the object into the correct position on the block (because the OBJ's origin is the center of the object)
		if(lantern.getWorld()==null){
			GL11.glTranslated(x+0, y+0.3, z+0);
			GL11.glScalef(0.8f, 0.8f, 0.8f);
		} else {
			GL11.glTranslated(x+0.5, y+0.5, z+0.5);
			// Scale our object to about half-size in all directions (the OBJ file is a little large)
			GL11.glScalef(0.5f, 0.5f, 0.5f);
		}
		GL11.glRotatef(90,1,0,0);


		// Bind the texture, so that OpenGL properly textures our block.
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, Info.modelTexPrefix + "lantern_uv_draw_2.png"));
		int j = lantern instanceof TileLantern ?((TileLantern)lantern).getRandomColor():0xaaaaaa;
		//System.out.println(j);
		float f1 = 1.0F;
		float f2 = (float) (j >> 16 & 255) / 255.0F;
		float f3 = (float) (j >> 8 & 255) / 255.0F;
		float f4 = (float) (j & 255) / 255.0F;
		GL11.glColor4f(f1 * f2, f1 * f3, f1 * f4,1);
		// Render the object, using modelTutBox.renderAll();

		this.render();
		GL11.glColor4f(1, 1, 1,1);

		// Pop this matrix from the stack.
		GL11.glPopMatrix();
		//todo: model remake include torch
		/*if(lantern.getWorld()!=null) {
			GL11.glPushMatrix();
			EntityItem ghostEntityItem = new EntityItem(lantern.getWorld());
			ghostEntityItem.setItem(new ItemStack(Blocks.TORCH, 1));
			ghostEntityItem.hoverStart = 0.0F;

			GL11.glTranslatef((float) x + 0.5F, (float) y + 0.1F, (float) z + 0.5F);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			renderItem.doRender(ghostEntityItem, 0, 0, 0, 0, 0);

			GL11.glPopMatrix();
		}*/
	}

}
