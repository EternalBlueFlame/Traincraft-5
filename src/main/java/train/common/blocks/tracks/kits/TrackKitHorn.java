package train.common.blocks.tracks.kits;

import net.minecraft.entity.item.EntityMinecart;
import train.common.api.Locomotive;

/**
 * Sounds the horn of every passing Traincraft locomotive.
 */
public class TrackKitHorn extends TrackKitDetectorBase {

    @Override
    public TCTrackKits getKitType() {
        return TCTrackKits.HORN;
    }

    @Override
    public void onMinecartPass(EntityMinecart cart) {
        if (cart instanceof Locomotive) {
            ((Locomotive) cart).soundHorn();
        }
    }
}
