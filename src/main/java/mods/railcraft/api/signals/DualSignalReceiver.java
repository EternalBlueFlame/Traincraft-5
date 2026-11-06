/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/
package mods.railcraft.api.signals;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.EnumMap;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class DualSignalReceiver extends SignalReceiver {

    private EnumMap<DualLamp, SignalAspect> aspects = new EnumMap<>(DualLamp.class);

    public DualSignalReceiver(String locTag, TileEntity tile) {
        super(locTag, tile, 2);
    }

    {
        for (DualLamp lamp : DualLamp.values()) {
            aspects.put(lamp, SignalAspect.BLINK_RED);
        }
    }

    @Override
    public void onControllerAspectChange(SignalController con, SignalAspect aspect) {
        BlockPos coord = pairings.peekFirst();
        if (coord == null) {
            return;
        }
        if (coord.equals(con.getCoords())) {
            if (setAspect(DualLamp.TOP, aspect)) {
                super.onControllerAspectChange(con, aspect);
            }
        } else {
            if (setAspect(DualLamp.BOTTOM, aspect)) {
                super.onControllerAspectChange(con, aspect);
            }
        }
    }

    @Override
    protected void saveNBT(NBTTagCompound data) {
        super.saveNBT(data);
        data.setByte("topAspect", (byte) aspects.get(DualLamp.TOP).ordinal());
        data.setByte("bottomAspect", (byte) aspects.get(DualLamp.BOTTOM).ordinal());
    }

    @Override
    protected void loadNBT(NBTTagCompound data) {
        super.loadNBT(data);
        setAspect(DualLamp.TOP, SignalAspect.values()[data.getByte("topAspect")]);
        setAspect(DualLamp.BOTTOM, SignalAspect.values()[data.getByte("bottomAspect")]);
    }

    @Override
    public void writePacketData(DataOutputStream data) throws IOException {
        super.writePacketData(data);
        data.writeByte(aspects.get(DualLamp.TOP).ordinal());
        data.writeByte(aspects.get(DualLamp.BOTTOM).ordinal());
    }

    @Override
    public void readPacketData(DataInputStream data) throws IOException {
        super.readPacketData(data);
        setAspect(DualLamp.TOP, SignalAspect.values()[data.readByte()]);
        setAspect(DualLamp.BOTTOM, SignalAspect.values()[data.readByte()]);
    }

    public SignalAspect getAspect(DualLamp lamp) {
        return aspects.get(lamp);
    }

    public boolean setAspect(DualLamp lamp, SignalAspect aspect) {
        return aspects.put(lamp, aspect) != aspect;
    }
}
