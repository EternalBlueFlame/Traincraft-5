package train.common.blocks.tracks.kits;

import net.minecraft.entity.item.EntityMinecart;
import train.common.api.IPassenger;

/**
 * Emits a redstone signal on all passing Traincraft passenger carts.
 */
public class TrackKitDetectorPassenger extends TrackKitDetectorBase {

    @Override
    public TCTrackKits getKitType() {
        return TCTrackKits.DETECTOR_PASSENGER;
    }

    @Override
    public void onMinecartPass(EntityMinecart cart) {
        if (cart instanceof IPassenger) {
            setTrackPowering();
        }
    }
}
