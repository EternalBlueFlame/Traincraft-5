/**
 * @author Spitfire4466
 */
package train.blocks.tracks;

import net.minecraft.util.IIcon;
import train.library.RCTracks;

public class BlockSteelTrack extends TrackBaseTraincraft {
	public BlockSteelTrack() {
		this.speedController = SpeedControllerSteel.getInstance();
	}

	@Override
	public RCTracks getTrackType() {
		return RCTracks.STEEL_TRACK;
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