package train.common.blocks.tracks.kits;

import ebf.tim.utility.CommonUtil;
import mods.railcraft.api.tracks.ITrackKitInstance;
import mods.railcraft.api.tracks.ITrackKitPowered;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import train.common.api.Locomotive;

import javax.annotation.Nullable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Sets the speed limiter of passing Traincraft locomotives.
 * The mode (0-15) is cycled with a crowbar, or taken from the redstone signal strength when powered.
 *
 * @author Spitfire4466
 */
public class TrackKitSpeedController extends TrackKitTraincraft implements ITrackKitPowered {
    /** speed limiter for each mode 0..15 (same values as 1.7.10) */
    private static final double[] LIMITS = {0.1, 0.15, 0.2, 0.25, 0.3, 0.35, 0.4, 0.45, 0.5, 0.6, 0.65, 0.7, 0.75, 0.85, 0.9, 1};
    private int mode = 0;
    private boolean powered;

    @Override
    public TCTrackKits getKitType() {
        return TCTrackKits.SPEED_CONTROLLER;
    }

    @Override
    public boolean onCrowbarWhack(EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem) {
        if (!isServer()) {
            return true;
        }
        this.mode += 3;
        if (this.mode > 15) {
            this.mode = 0;
        }
        switch (this.mode) {
            case 0: CommonUtil.sendChat(player, "20 percent of max speed"); break;
            case 3: CommonUtil.sendChat(player, "40 percent of max speed"); break;
            case 6: CommonUtil.sendChat(player, "60 percent of max speed"); break;
            case 9: CommonUtil.sendChat(player, "80 percent of max speed"); break;
            case 12: CommonUtil.sendChat(player, "90 percent of max speed"); break;
            case 15: CommonUtil.sendChat(player, "100 percent of max speed"); break;
        }
        sendUpdateToClient();
        return true;
    }

    @Override
    public void onNeighborBlockChange(IBlockState state, @Nullable Block neighborBlock) {
        super.onNeighborBlockChange(state, neighborBlock);
        if (this.powered) {
            int newMode = Math.min(15, getWorld().getRedstonePowerFromNeighbors(getPos()));
            if (newMode != this.mode) {
                this.mode = newMode;
                sendUpdateToClient();
            }
        }
    }

    @Override
    public void onMinecartPass(EntityMinecart cart) {
        if (cart instanceof Locomotive) {
            ((Locomotive) cart).speedLimiter = LIMITS[Math.max(0, Math.min(15, this.mode))];
        }
    }

    @Override
    public int getRenderState() {
        if (this.mode < 3) return 0;
        if (this.mode < 6) return 1;
        if (this.mode < 8) return 2;
        if (this.mode < 13) return 3;
        return 4;
    }

    @Override
    public boolean isPowered() {
        return this.powered;
    }

    @Override
    public void setPowered(boolean powered) {
        this.powered = powered;
    }

    @Override
    public int getPowerPropagation() {
        return 0;
    }

    @Override
    public boolean canPropagatePowerTo(ITrackKitInstance track) {
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("mode", this.mode);
        data.setBoolean("powered", this.powered);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.mode = data.getInteger("mode");
        this.powered = data.getBoolean("powered");
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
