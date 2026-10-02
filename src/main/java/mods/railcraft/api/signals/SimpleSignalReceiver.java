/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/
package mods.railcraft.api.signals;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class SimpleSignalReceiver extends SignalReceiver {

    private SignalAspect aspect = SignalAspect.BLINK_RED;

    public SimpleSignalReceiver(String locTag, TileEntity tile) {
        super(locTag, tile, 1);
    }

    @Override
    public void tickServer() {
        super.tickServer();
        SignalAspect prevAspect = getAspect();
        if (!isPaired()) {
            setAspect(SignalAspect.BLINK_RED);
        }
        if (prevAspect != getAspect()) {
            sendUpdateToClient();
        }
    }

    public SignalAspect getAspect() {
        return aspect;
    }

    public void setAspect(SignalAspect aspect) {
        this.aspect = aspect;
    }

    @Override
    public void onControllerAspectChange(SignalController con, SignalAspect aspect) {
        if (this.aspect != aspect) {
            this.aspect = aspect;
            super.onControllerAspectChange(con, aspect);
        }
    }

    @Override
    protected void saveNBT(NBTTagCompound data) {
        super.saveNBT(data);
        data.setByte("aspect", (byte) aspect.ordinal());
    }

    @Override
    protected void loadNBT(NBTTagCompound data) {
        super.loadNBT(data);
        aspect = SignalAspect.VALUES[data.getByte("aspect")];
    }

    @Override
    public void writePacketData(DataOutputStream data) throws IOException {
        super.writePacketData(data);
        data.writeByte(aspect.ordinal());
    }

    @Override
    public void readPacketData(DataInputStream data) throws IOException {
        super.readPacketData(data);
        aspect = SignalAspect.VALUES[data.readByte()];
    }

    @Override
    public String toString() {
        return String.format("Receiver:%s (%s)", aspect, super.toString());
    }
}
