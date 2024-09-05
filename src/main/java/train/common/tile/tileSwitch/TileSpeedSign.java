package train.common.tile.tileSwitch;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;

public class TileSpeedSign extends TileEntity {

	private int skinstate;
	private EnumFacing facing;

	public EnumFacing getFacing() {
		if(facing != null){
			return this.facing;
		}
		return EnumFacing.UNKNOWN;
	}

	public void setSkinstate(int skinstate) {
		getWorld().markBlockForUpdate(xCoord, yCoord, zCoord);
		this.skinstate = skinstate;
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
		getWorld().markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	public void setFacing(EnumFacing face) {
		getWorld().markBlockForUpdate(xCoord, yCoord, zCoord);
		this.facing = face;
	}

	public void readFromNBT(NBTTagCompound nbtTag) {
		if(nbtTag.hasKey("Orientation")) {
			facing = EnumFacing.byHorizontalIndex(nbtTag.getByte("Orientation"));
		}
		if(nbtTag.hasKey("skinstate")){
			skinstate = nbtTag.getInteger("skinstate");
		}

		else {
			System.out.println("No Skins or Direction");
		}
		super.readFromNBT(nbtTag);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		nbtTag.setByte("Orientation", (byte) facing.ordinal());
		nbtTag.setInteger("skinstate", this.skinstate);

		super.writeToNBT(nbtTag);
	}

	public Packet getDescriptionPacket() {

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
