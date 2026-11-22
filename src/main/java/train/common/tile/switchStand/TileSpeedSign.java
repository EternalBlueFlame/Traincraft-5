package train.common.tile.switchStand;

import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import train.common.api.blocks.TileRenderFacing;
import train.common.blocks.blockSwitch.BlockSpeedSign;

public class TileSpeedSign extends TileRenderFacing {

	private int skinstate;

	public TileSpeedSign(){
	}
	public TileSpeedSign(BlockSpeedSign block){
		host = block;
	}
	public void setSkinstate(int skinstate) {
		this.skinstate = skinstate;
		CommonUtil.markBlockForUpdate(getWorld(), getPos().getX(),getPos().getY(),getPos().getZ());

	}

	public int getSkinstate() {
		return skinstate;
	}

	public void increaseSkinState(){
		if (skinstate >= 4){
			skinstate = 0;
		} else {
			skinstate++;
		}
		CommonUtil.markBlockForUpdate(getWorld(), getPos().getX(),getPos().getY(),getPos().getZ());
	}



	public void readFromNBT(NBTTagCompound nbtTag) {
		super.readFromNBT(nbtTag);
		if(nbtTag.hasKey("skinstate")){
			skinstate = nbtTag.getInteger("skinstate");
		}

		else {
			System.out.println("No Skins");
		}

	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		super.writeToNBT(nbtTag);
		nbtTag.setInteger("skinstate", this.skinstate);


	}

	public SPacketUpdateTileEntity getDescriptionPacket() {

		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);

		return new SPacketUpdateTileEntity(getPos(), 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
		this.readFromNBT(pkt.getNbtCompound());
		super.onDataPacket(net, pkt);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return CommonUtil.createAABB(getPos().getX() - 1, getPos().getY() - 1, getPos().getZ() - 1, getPos().getX() + 2, getPos().getY() + 2, getPos().getZ() + 2);
	}
}
