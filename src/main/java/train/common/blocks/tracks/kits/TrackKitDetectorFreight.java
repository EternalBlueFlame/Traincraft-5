package train.common.blocks.tracks.kits;

import net.minecraft.entity.item.EntityMinecart;
import train.common.api.Freight;

/**
 * Emits a redstone signal on all passing Traincraft freight carts.
 */
public class TrackKitDetectorFreight extends TrackKitDetectorBase {

    @Override
    public TCTrackKits getKitType() {
        return TCTrackKits.DETECTOR_FREIGHT;
    }

    @Override
    public void onMinecartPass(EntityMinecart cart) {
        if (cart instanceof Freight) {
            setTrackPowering();
        }
    }
}
