package train.common.blocks.tracks.kits;

import mods.railcraft.api.tracks.ITrackKitEmitter;
import net.minecraft.nbt.NBTTagCompound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A kit that emits a short redstone pulse when something happens to a passing cart.
 * Shared by the detector, boarding and disembark kits (they were copies of each other in 1.7.10).
 */
public abstract class TrackKitDetectorBase extends TrackKitTraincraft implements ITrackKitEmitter {
    private byte delay = 0;

    @Override
    public void update() {
        if (!isServer()) {
            return;
        }
        if (this.delay > 0) {
            this.delay--;
            if (this.delay == 0) {
                notifyNeighbors();
                sendUpdateToClient();
            }
        }
    }

    protected void setTrackPowering() {
        boolean notify = this.delay == 0;
        this.delay = 3;
        if (notify) {
            notifyNeighbors();
            sendUpdateToClient();
        }
    }

    @Override
    public int getPowerOutput() {
        return this.delay > 0 ? 15 : 0;
    }

    @Override
    public int getRenderState() {
        return this.delay > 0 ? 1 : 0;
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setByte("delay", this.delay);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.delay = data.getByte("delay");
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
}
