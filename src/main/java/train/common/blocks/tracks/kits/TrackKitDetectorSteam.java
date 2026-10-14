package train.common.blocks.tracks.kits;

import net.minecraft.entity.item.EntityMinecart;
import train.common.api.SteamTrain;

/**
 * Emits a redstone signal on all passing Traincraft steam locomotives.
 */
public class TrackKitDetectorSteam extends TrackKitDetectorBase {

    @Override
    public TCTrackKits getKitType() {
        return TCTrackKits.DETECTOR_STEAM;
    }

    @Override
    public void onMinecartPass(EntityMinecart cart) {
        if (cart instanceof SteamTrain) {
            setTrackPowering();
        }
    }
}
