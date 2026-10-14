package train.common.blocks.tracks.kits;

import net.minecraft.entity.item.EntityMinecart;
import train.common.api.LiquidTank;

/**
 * Emits a redstone signal on all passing Traincraft tank carts.
 */
public class TrackKitDetectorTank extends TrackKitDetectorBase {

    @Override
    public TCTrackKits getKitType() {
        return TCTrackKits.DETECTOR_TANK;
    }

    @Override
    public void onMinecartPass(EntityMinecart cart) {
        if (cart instanceof LiquidTank) {
            setTrackPowering();
        }
    }
}
