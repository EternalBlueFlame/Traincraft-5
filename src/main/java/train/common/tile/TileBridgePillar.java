package train.common.tile;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import train.common.api.blocks.TileRenderFacing;
import train.common.library.Info;

public class TileBridgePillar extends TileRenderFacing {

	@SideOnly(Side.CLIENT)
	@Override
	public double getMaxRenderDistanceSquared() {
		if(FMLClientHandler.instance()!=null && FMLClientHandler.instance().getClient()!=null && FMLClientHandler.instance().getClient().gameSettings!=null){
			if (FMLClientHandler.instance().getClient().gameSettings.renderDistanceChunks == 0) {
				return 30000.0D;
			}
			else if (FMLClientHandler.instance().getClient().gameSettings.renderDistanceChunks == 1) {
				return 15900.0D;
			}
			else if (FMLClientHandler.instance().getClient().gameSettings.renderDistanceChunks == 2) {
				return 4000.0D;
			} else return 4096.0;
		}else{
			return 4096.0;
		}
	}
	public boolean canUpdate()
	{
		return false;
	}


	static final train.client.render.models.blocks.ModelBridgePillar modelBridgePillar = new train.client.render.models.blocks.ModelBridgePillar();

	static final ResourceLocation texture = new ResourceLocation(Info.modID, Info.modelTexPrefix + "bridgePillar.png");

	@Override
	@SideOnly(Side.CLIENT)
	public void renderModel(){
		org.lwjgl.opengl.GL11.glTranslated(0, 0.5, 0);
		org.lwjgl.opengl.GL11.glRotatef(180f, 0f, 1f, 0f);
		modelBridgePillar.renderBlock();
	}
	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getTexture(int x, int y, int z){
		return texture;
	}
}