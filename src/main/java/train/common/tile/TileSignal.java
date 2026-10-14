package train.common.tile;

import net.minecraft.util.ITickable;

import ebf.tim.utility.CommonUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

//client
public class TileSignal extends TileEntity implements ITickable {

	// 1.12: modded tile entities are dropped on any block state change by default; only drop them when the block changes
	@Override
	public boolean shouldRefresh(net.minecraft.world.World world, net.minecraft.util.math.BlockPos pos, net.minecraft.block.state.IBlockState oldState, net.minecraft.block.state.IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	public int state;// 0=red 1=green
	public int rot;

	public double tempSpeedX;// signal
	public double tempSpeedZ;// signal
	public double fu;// signal
	private int facingMeta;

	public TileSignal() {
		// signal
		tempSpeedX = 0;
		tempSpeedZ = 0;
		fu = 0;
		facingMeta = this.getBlockMetadata();
	}

	public int getFacing() {
		return facingMeta;
	}

	public void setFacing(int facing) {
		this.facingMeta = facing;
		this.rot = facing;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		state = nbttagcompound.getInteger("state");
		rot = nbttagcompound.getInteger("rot");
		facingMeta = nbttagcompound.getByte("Orientation");
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setByte("Orientation", (byte) facingMeta);
		nbttagcompound.setInteger("state", this.state);
		nbttagcompound.setInteger("rot", this.rot);
		return nbttagcompound;
	}

	/** 1.12 sends this with the chunk data (1.7.10 sent the description packet instead). */
	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {

		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);

		return new SPacketUpdateTileEntity(getPos(), 1, nbt);
	}

	@Override
	public void update() {
		int x1 = 1;// x2
		int x2 = 1;// y2
		int x3 = 1;// z2
		int x4 = 1;// x1
		int x5 = 1;// z1

		switch (this.rot) {

		case 2:
			x4 = -1;
			x5 = -9;
			x1 = 1;
			x3 = 1;
			break;

		case 3:
			x3 = 9;
			x1 = 1;
			x4 = -1;
			x5 = 1;
			break;

		case 4:
			x4 = -9;
			x5 = -1;
			x1 = 1;
			x3 = 1;
			break;

		case 5:
			x3 = 1;
			x1 = 9;
			x4 = 1;
			x5 = -1;
			break;
		}

		List list = this.world.getEntitiesWithinAABB(EntityMinecart.class, CommonUtil.createAABB( this.getPos().getX() + x4, this.getPos().getY(), this.getPos().getZ() + x5, (this.getPos().getX() + x1), (this.getPos().getY() + 1),  (this.getPos().getZ() + x3)).expand(1.0D, 1.0D, 1.0D));
		Entity entity;

		if (list != null && list.size() > 0) {

			for (int j1 = 0; j1 < list.size(); j1++) {

				entity = (Entity) list.get(j1);

				if (entity instanceof EntityMinecart) {

					if (state == 0) {

						if (fu == 0) {

							tempSpeedX = entity.motionX;
							tempSpeedZ = entity.motionZ;
						}

						entity.motionX *= 0.85D;
						entity.motionZ *= 0.85D;
						fu++;
					}
					else if (fu > 0 && state == 1) {

						entity.motionX = tempSpeedX;
						entity.motionZ = tempSpeedZ;
						fu = 0;
					}
				}
			}
		}
	}
}