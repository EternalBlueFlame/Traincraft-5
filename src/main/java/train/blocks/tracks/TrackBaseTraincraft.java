/**
 * This is based on Railcraft's code
 * @author CovertJaguar
 */
package train.blocks.tracks;

import mods.railcraft.api.tracks.TrackInstanceBase;
import mods.railcraft.api.tracks.TrackRegistry;
import mods.railcraft.api.tracks.TrackSpec;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.IIcon;
import train.library.RCTracks;

public abstract class TrackBaseTraincraft extends TrackInstanceBase {

	public SpeedController speedController;

	public abstract RCTracks getTrackType();
	@Override
	public float getRailMaxSpeed(EntityMinecart cart) {
		if (this.speedController == null) {
			this.speedController = SpeedController.getInstance();
		}
		return this.speedController.getMaxSpeed(this, cart);
	}
	@Override
	public TrackSpec getTrackSpec() {
		return TrackRegistry.getTrackSpec(getTrackType().ordinal() + 513);
	}
	@Override
	public IIcon getIcon() {
		return getIcon(0);
	}

	public IIcon getIcon(int index) {
		return RailTextureLoader.INSTANCE.getTrackIcons(getTrackSpec())[index];
	}

	public int getPowerPropagation() {
		return 0;
	}
}
