/**
 * A track that detects all instance of DieselTrain
 * 
 * @author Spitfire4466
 */
package train.blocks.tracks;

import ebf.tim.TrainsInMotion;
import ebf.tim.entities.EntityTrainCore;
import ebf.tim.entities.GenericRailTransport;
import mods.railcraft.api.tracks.ITrackKitEmitter;
import mods.railcraft.api.tracks.TrackType;
import net.minecraft.entity.item.EntityMinecart;
import train.library.Tracks;

public class BlockDetectorDieselLocomotiveTrack extends BlockDetectorTrack implements ITrackKitEmitter {

	@Override
	public TrackType getTrackType() {
		return Tracks.DETECTOR_DIESEL_LOCOMOTIVES;
	}

	@Override
	public void onMinecartPass(EntityMinecart cart) {
		if (cart instanceof EntityTrainCore
				&& ((GenericRailTransport) cart).getTypes().contains(TrainsInMotion.transportTypes.DIESEL)) {
			setTrackPowering();
		}
	}
}
