/*******************************************************************************
 * Copyright (c) 2013 Mrbrutal. All rights reserved.
 * 
 * @name Traincraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public class TileBook extends TileEntity {
	
	private int facingMeta;
	
	public TileBook() {
		
		facingMeta = this.blockMetadata;
	}
	
	public int getFacing() {
		
		return facingMeta;
	}

	public void setFacing(int facing) {
		
		this.facingMeta = facing;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTag) {
		
		super.readFromNBT(nbtTag);
		
		facingMeta = nbtTag.getByte("Orientation");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		
		super.writeToNBT(nbtTag);
		
		nbtTag.setByte("Orientation", (byte) facingMeta);
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {

		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);

		return new SPacketUpdateTileEntity(getPos(), 1, nbt);
	}
	
	/*
	@Override // Why would you even do this..?
	public void updateEntity() {
		
		super.updateEntity();
	}*/
}