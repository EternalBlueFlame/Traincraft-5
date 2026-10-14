package train.common.blocks.tracks.kits;

import ebf.tim.utility.CommonUtil;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import train.common.api.AbstractTrains;
import train.common.api.Locomotive;

import javax.annotation.Nullable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Requires redstone power. Holds any Traincraft locomotive (and any non-Traincraft minecart)
 * and releases it on an internal clock; the timing mode is cycled with a crowbar.
 */
public class TrackKitStation extends TrackKitLockingBase {
    /** {release time, period} in ticks for each mode */
    private static final int[][] MODES = {{100, 300}, {300, 600}, {600, 1200}, {600, 6000}};
    private static final String[] MODE_MESSAGES = {
            "Activated every 15s for 5s",
            "Activated every 30s for 15s",
            "Activated every minute for 30s",
            "Activated every five minutes for 30s"};

    private int updateTicks = 0;
    private int mode = 0;

    @Override
    public TCTrackKits getKitType() {
        return TCTrackKits.STATION;
    }

    @Override
    protected boolean acceptsCart(EntityMinecart cart) {
        return cart instanceof Locomotive || !(cart instanceof AbstractTrains);
    }

    @Override
    protected boolean isReleasing() {
        return this.delay > 0;
    }

    @Override
    protected int getDelayTime() {
        return MODES[this.mode][0];
    }

    private int getActivateRate() {
        return MODES[this.mode][1];
    }

    @Override
    public boolean onCrowbarWhack(EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem) {
        if (!isServer()) {
            return true;
        }
        this.mode = (this.mode + 1) % MODES.length;
        CommonUtil.sendChat(player, MODE_MESSAGES[this.mode]);
        sendUpdateToClient();
        return true;
    }

    @Override
    public void update() {
        dropDeadCart();
        if (isServer()) {
            if (this.updateTicks >= getActivateRate()) {
                if (isPowered()) {
                    this.delay = getDelayTime();
                }
                this.updateTicks = 0;
                sendUpdateToClient();
            }
            if (this.delay > 0) {
                this.delay--;
                if (this.delay == 0) {
                    setCurrentCart(null);
                    sendUpdateToClient();
                }
            }
            if (this.reset > 0) {
                this.reset--;
            }
        }
        this.prevDelay = this.delay;
        this.updateTicks++;
    }

    @Override
    public int getRenderState() {
        return this.delay > 0 ? 4 + this.mode : this.mode;
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("mode", this.mode);
        data.setInteger("ticks", this.updateTicks);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.mode = Math.max(0, Math.min(MODES.length - 1, data.getInteger("mode")));
        this.updateTicks = data.getInteger("ticks");
    }

    @Override
    public void writePacketData(DataOutputStream data) throws IOException {
        super.writePacketData(data);
        data.writeInt(this.mode);
    }

    @Override
    public void readPacketData(DataInputStream data) throws IOException {
        super.readPacketData(data);
        this.mode = data.readInt();
        markBlockNeedsUpdate();
    }
}
