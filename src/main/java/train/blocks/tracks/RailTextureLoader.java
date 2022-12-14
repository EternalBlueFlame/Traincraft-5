package train.blocks.tracks;

import ebf.tim.utility.DebugUtil;
import mods.railcraft.api.core.ITextureLoader;
import mods.railcraft.api.tracks.ITrackItemIconProvider;
import mods.railcraft.api.tracks.TrackSpec;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import train.library.RCTracks;

import java.util.HashMap;
import java.util.Map;

public class RailTextureLoader implements ITextureLoader, ITrackItemIconProvider {
	public static final RailTextureLoader INSTANCE = new RailTextureLoader();
	public final Map textures = new HashMap();

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		for (RCTracks track : RCTracks.values()) {

			IIcon[] icons = new IIcon[track.getNumIcons()];
			for (int i = 0; i < track.getNumIcons(); i++) {
				icons[i]=iconRegister.registerIcon(track.getTextureTag() + "." + i);
			}
			this.textures.put(track.getTrackSpec(), icons);
		}
	}
	@Override
	public IIcon getTrackItemIcon(TrackSpec spec) {
		return getTrackIcons(spec)[0];
	}

	public IIcon[] getTrackIcons(TrackSpec spec) {
		return (IIcon[]) this.textures.get(spec);
	}

}
