/**
 * @author Spitfire4466
 */
package train.blocks.tracks;

import net.minecraft.util.IIcon;
import train.library.RCTracks;

public class BlockVanillaSnowyTrack extends TrackBaseTraincraft {

	@Override
	public RCTracks getTrackType() {
		return RCTracks.VANILLA_SNOWY_TRACK;
	}
	@Override
	public IIcon getIcon() {
		int meta = this.tileEntity.getBlockMetadata();
		if (meta >= 6) {
			return getIcon(1);
		}
		return getIcon(0);
	}
	@Override
	public boolean isFlexibleRail() {
		return true;
	}
}