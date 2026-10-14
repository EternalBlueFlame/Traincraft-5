package train.common.blocks.tracks.kits;

import ebf.tim.utility.CommonUtil;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import train.common.api.DieselTrain;
import train.common.api.ElectricTrain;
import train.common.api.Locomotive;
import train.common.api.SteamTrain;

import javax.annotation.Nullable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Detects locomotives. A crowbar cycles the filter: all / steam / diesel / electric.
 *
 * @author Spitfire4466 (edited by NitroxydeX)
 */
public class TrackKitDetectorLocomotive extends TrackKitDetectorBase {
    private static final int ALL = 0, STEAM = 3, DIESEL = 6, ELECTRIC = 9;
    private int filter = ALL;

    @Override
    public TCTrackKits getKitType() {
        return TCTrackKits.DETECTOR_LOCOMOTIVE;
    }

    @Override
    public boolean onCrowbarWhack(EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem) {
        if (!isServer()) {
            return true;
        }
        this.filter = this.filter == ELECTRIC ? ALL : this.filter + 3;
        switch (this.filter) {
            case ALL:
                CommonUtil.sendChat(player, "Now set to emit a signal on all trains");
                break;
            case STEAM:
                CommonUtil.sendChat(player, "Now set to emit a signal on all steam trains");
                break;
            case DIESEL:
                CommonUtil.sendChat(player, "Now set to emit a signal on all diesel trains");
                break;
            case ELECTRIC:
                CommonUtil.sendChat(player, "Now set to emit a signal on all electric trains");
                break;
        }
        sendUpdateToClient();
        return true;
    }

    @Override
    public void onMinecartPass(EntityMinecart cart) {
        boolean match;
        switch (this.filter) {
            case STEAM:
                match = cart instanceof SteamTrain;
                break;
            case DIESEL:
                match = cart instanceof DieselTrain;
                break;
            case ELECTRIC:
                match = cart instanceof ElectricTrain;
                break;
            default:
                match = cart instanceof Locomotive;
        }
        if (match) {
            setTrackPowering();
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("state", this.filter);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.filter = data.getInteger("state");
    }

    @Override
    public void writePacketData(DataOutputStream data) throws IOException {
        super.writePacketData(data);
        data.writeInt(this.filter);
    }

    @Override
    public void readPacketData(DataInputStream data) throws IOException {
        super.readPacketData(data);
        this.filter = data.readInt();
    }
}
