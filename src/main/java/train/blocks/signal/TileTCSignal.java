package train.blocks.signal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.blocks.BlockSignal;
import ebf.tim.blocks.TileSwitch;
import ebf.tim.utility.DebugUtil;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.library.Info;

import java.util.List;

public class TileTCSignal extends TileSwitch {

	public TileTCSignal() {
		super();
	}

	public TileTCSignal(BlockSignal block){
		host=block;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		//check if given redstone signal, let that override normal detection.
		if(getEnabled()){
			return;
		}

		int x1 = 1;// x2
		int x3 = 1;// z2
		int x4 = 1;// x1
		int x5 = 1;// z1

		switch (this.facing) {
			case 2: {
				x4 = -1;
				x5 = -9;
				x1 = 1;
				x3 = 1;
				break;
			}

			case 3: {
				x3 = 9;
				x1 = 1;
				x4 = -1;
				x5 = 1;
				break;
			}

			case 4: {
				x4 = -9;
				x5 = -1;
				x1 = 1;
				x3 = 1;
				break;
			}

			case 5: {
				x3 = 1;
				x1 = 9;
				x4 = 1;
				x5 = -1;
				break;
			}
		}

		List list = this.worldObj.getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox(
				this.xCoord + x4, this.yCoord-1, this.zCoord + x5,
				(this.xCoord + x1), (this.yCoord + 1), (this.zCoord + x3))
				.expand(1.0D, 2.0D, 1.0D));

		if (list != null && list.size() > 0) {
			for (Object o : list) {
				if (o instanceof EntityMinecart) {
					setEnabled(true);
					return;
				}
			}
		}
	}



	@SideOnly(Side.CLIENT)
	@Override
	public void renderModel(){
		org.lwjgl.opengl.GL11.glTranslatef( 0,  0.5f,  0);
		org.lwjgl.opengl.GL11.glRotatef(180, 0,  0,  1f);
		//modifications for item render
		if(getWorldObj()==null){
			GL11.glScalef(0.65f,0.65f,0.65f);
			org.lwjgl.opengl.GL11.glTranslatef( 0,  -0.5f,  0);
		}
		//this would normally be the regular render() call, but this model is weird.
		new train.render.models.blocks.ModelBlockSignal()
				.render(null, 0, 0, 0, 0, 0, 0.0625f);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ResourceLocation getTexture(int x, int y, int z){
		if (!getEnabled()) {
			return new ResourceLocation(Info.modID, Info.trainsPrefix + "signal_suisse_green.png");
		} else {
			return new ResourceLocation(Info.modID,Info.trainsPrefix + "signal_suisse_red.png");
		}
	}

}