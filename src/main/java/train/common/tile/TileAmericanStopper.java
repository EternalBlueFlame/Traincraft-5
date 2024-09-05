/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 *
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileAmericanStopper extends TileEntity {

    private int facingMeta;

    public TileAmericanStopper() {
        //facingMeta = this.getBlockMetadata(); // Changed from this.blockMetadata to the method call to avoid receiving invalid Metadata.
    }

    public  TileAmericanStopper(int meta){

        this.facingMeta = meta;
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

    @Override
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
}
