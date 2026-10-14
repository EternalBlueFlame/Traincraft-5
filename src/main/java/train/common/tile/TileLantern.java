package train.common.tile;

import net.minecraft.util.ITickable;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileLantern extends TileEntity implements ITickable {

	// 1.12: modded tile entities are dropped on any block state change by default; only drop them when the block changes
	@Override
	public boolean shouldRefresh(net.minecraft.world.World world, net.minecraft.util.math.BlockPos pos, net.minecraft.block.state.IBlockState oldState, net.minecraft.block.state.IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}


	/** Static instance used to access random number generation to create random colors. */
	protected static final Random rand = new Random();

	private int randomColor = (rand.nextInt() * 0xFFFFFF);
	private int oldColor = 0;

	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);

		randomColor = nbt.getInteger("randomColor");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);

		nbt.setInteger("randomColor", randomColor);
		return nbt;
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

	public String getColor() {

		return String.format("#%06X", (0xFFFFFF & this.randomColor));
	}

	public int getRandomColor(){
		return this.oldColor;
	}

	public void  setColor(int col){
		randomColor = col;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public void update() {
		if (oldColor != randomColor && getPos().getX()!=0 && getPos().getY()!=0 && getPos().getZ()!=0){
			oldColor = randomColor;
			this.markDirty();
			this.syncTileEntity();
		}
	}
	
	public void syncTileEntity() {
		for (Object o : this.world.playerEntities) {
			if (o instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) o;
				if (player.getDistance(getPos().getX(), getPos().getY(), getPos().getZ()) <= 64) {
					player.connection.sendPacket(this.getUpdatePacket());
				}
			}
		}
	}

}