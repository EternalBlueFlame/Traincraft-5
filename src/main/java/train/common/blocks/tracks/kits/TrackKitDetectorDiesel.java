package train.common.blocks.tracks.kits;

import net.minecraft.entity.item.EntityMinecart;
import train.common.api.DieselTrain;

/**
 * Emits a redstone signal on all passing Traincraft diesel locomotives.
 */
public class TrackKitDetectorDiesel extends TrackKitDetectorBase {

    @Override
    public TCTrackKits getKitType() {
        return TCTrackKits.DETECTOR_DIESEL;
    }

    @Override
    public void onMinecartPass(EntityMinecart cart) {
        if (cart instanceof DieselTrain) {
            setTrackPowering();
        }
    }
}
