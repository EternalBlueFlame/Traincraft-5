package train.common.tile.switchStand;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.math.AxisAlignedBB;

import javax.annotation.Nullable;
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
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

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
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
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

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {

		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);

		return new SPacketUpdateTileEntity(getPos(), 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
		this.readFromNBT(pkt.func_148857_g());
		super.onDataPacket(net, pkt);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 2, yCoord + 2, zCoord + 2);
	}
}
