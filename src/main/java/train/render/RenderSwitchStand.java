package train.render;

import ebf.tim.blocks.TileRenderFacing;
import ebf.tim.utility.CommonUtil;
import fexcraft.tmt.slim.ModelRendererTurbo;
import fexcraft.tmt.slim.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.core.ClientProxy;
import train.library.Info;
import train.render.models.ModelSwitchStandOff;

public class RenderSwitchStand extends TileEntitySpecialRenderer {

	private static final ResourceLocation texture = new ResourceLocation(Info.resourceLocation,Info.modelTexPrefix + "switchStand_uv_draw_1.png");
	private static final ResourceLocation texture2 = new ResourceLocation(Info.resourceLocation,Info.modelTexPrefix + "switchStand_uv_draw_2.png");

	@Override
	public void render(TileEntity tileEntity, double x, double y, double z, float tick, int destroyStage, float alpha) {
		if(tileEntity==null){return;}
		GL11.glPushMatrix();

		//item render
		if(tileEntity==null || tileEntity.getWorldObj()==null){
			GL11.glTranslated( x+0.2,  y,  z);
			GL11.glScalef(0.65f,0.65f,0.65f);

			TextureManager.bindTexture(texture2);
			//this is stupid levels of finicky
			if(ClientProxy.modelSwitch2.bodyModel==null){
				ClientProxy.modelSwitch2= new ModelSwitchStandOff();
			}

			ClientProxy.modelSwitch2.render(null, 0, 0, 0, 0, 0, 0.0625f);
		}
		//inworld render
		else {
			GL11.glTranslated( x + 0.5,  y+0.7f,  z + 0.5);
			if(tileEntity instanceof TileRenderFacing) {
				GL11.glRotatef(180,0,0,1);
				switch (((TileRenderFacing) tileEntity).facing){
					//north
					case 0:{GL11.glRotatef(90,0,1,0);break;}
					//east
					case 1:{break;}
					//south
					case 2:{GL11.glRotatef(270,0,1,0);break;}
					//west
					case 3:{GL11.glRotatef(180,0,1,0);break;}
				}
			}

			//extra offset for track height
			if(ebf.tim.utility.ClientProxy.railSkin==3){
				GL11.glTranslatef(0, 0.09f, 0);
			} else {
				GL11.glTranslatef(0, 0.01875f, 0);
			}

			//on
			if (CommonUtil.getBlockAt(tileEntity.getWorldObj(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord).isProvidingWeakPower(tileEntity.getWorldObj(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, 0) > 0) {
				TextureManager.bindTexture(texture2);
				ClientProxy.modelSwitch2.render(null, 0, 0, 0, 0, 0, 0.0625f);
			} else {//off
				TextureManager.bindTexture(texture);
				ClientProxy.modelSwitch.render(null, 0, 0, 0, 0, 0, 0.0625f);
			}
		}

		GL11.glPopMatrix();
	}
}
