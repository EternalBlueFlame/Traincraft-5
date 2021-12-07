/**
 * @author Spitfire4466
 */
package train.blocks.tracks;

import ebf.tim.TrainsInMotion;
import ebf.tim.entities.GenericRailTransport;
import ebf.tim.utility.CommonUtil;
import mods.railcraft.api.tracks.ITrackEmitter;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import depreciated.minecraft.util.IIcon;
import train.library.Tracks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class BlockMobBoardingTrack extends TrackBaseTraincraft implements ITrackEmitter {
	private byte delay = 0;

	@Override
	public TrackType getTrackType() {
		return Tracks.MOB_BOARDING_TRACK;
	}
	@Override
	public void onMinecartPass(EntityMinecart cart) {
		if (cart instanceof GenericRailTransport &&
				!TrainsInMotion.transportTypes.isTrain(((GenericRailTransport) cart).getTypes())) {
			if (cart.riddenByEntity != null)
				return;
			AxisAlignedBB box = null;
			box = cart.boundingBox.expand(4, 4, 4);
			List list = this.getWorld().getEntitiesWithinAABBExcludingEntity(cart, box);
			if (list != null && list.size() > 0) {

				for (int j1 = 0; j1 < list.size(); j1++) {
					Entity entity = (Entity) list.get(j1);
					if ((entity instanceof EntityCreature)) {
						entity.startRiding(cart);
						setTrackPowering();
					}
				}
			}
		}
	}
	@Override
	public void updateEntity() {
		if (getWorld().isRemote) {//not sure
			return;
		}
		if (this.delay > 0) {
			this.delay = (byte) (this.delay - 1);
			if (this.delay == 0)
				notifyNeighbors();
		}
	}
	@Override
	public IIcon getIcon() {
		if (this.delay > 0) {
			return getIcon(1);
		}
		return getIcon(0);
	}

	protected void notifyNeighbors() {
		Block block = CommonUtil.getBlockAt(getWorld(), getX(), getY(), getZ());
		getWorld().notifyBlocksOfNeighborChange(getX(), getY(), getZ(), block);
		getWorld().notifyBlocksOfNeighborChange(getX(), getY() - 1, getZ(), block);

		markBlockNeedsUpdate();
	}

	protected void setTrackPowering() {
		boolean notify = this.delay == 0;
		this.delay = 3;
		if (notify)
			notifyNeighbors();
	}

	@Override
	public boolean canUpdate() {
		return true;
	}
	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setByte("delay", this.delay);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		this.delay = nbttagcompound.getByte("delay");
	}
	@Override
	public void writePacketData(DataOutputStream data) throws IOException {
		super.writePacketData(data);
		data.writeByte(this.delay);
	}
	@Override
	public void readPacketData(DataInputStream data) throws IOException {
		super.readPacketData(data);
		this.delay = data.readByte();

		markBlockNeedsUpdate();
	}

	@Override
	public int getPowerOutput() {
		return this.delay > 0 ? 15 : 0;
	}
}