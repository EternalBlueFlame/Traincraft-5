/**
 * A track that detects all instance of SteamTrain
 * 
 * @author Spitfire4466
 */
package train.blocks.tracks;

import ebf.tim.TrainsInMotion;
import ebf.tim.entities.GenericRailTransport;
import mods.railcraft.api.tracks.ITrackEmitter;
import net.minecraft.entity.item.EntityMinecart;
import train.library.Tracks;

public class BlockDetectorSteamLocomotiveTrack extends BlockDetectorTrack implements ITrackEmitter {

	@Override
	public TrackType getTrackType() {
		return Tracks.DETECTOR_STEAM_LOCOMOTIVES;
	}
	@Override
	public void onMinecartPass(EntityMinecart cart) {
		if (((GenericRailTransport)cart).getTypes().contains(TrainsInMotion.transportTypes.STEAM)) {
			setTrackPowering();
		}
	}
}