package src.train.common.tile;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileLantern extends TileEntity {

	/** Static instance used to access random number generation to create random colors. */
	protected static final Random rand = new Random();

	public int randomColor = (rand.nextInt() * 0xFFFFFF << 0);

	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);

		this.randomColor = nbt.getInteger("randomColor");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);

		nbt.setInteger("randomColor", this.randomColor);
	}

	@Override
	public Packet getDescriptionPacket() {

		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
	}

	public String getColor() {

		return String.format("#%06X", (0xFFFFFF & this.randomColor));
	}
}