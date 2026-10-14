package train.common.blocks.tracks.kits;

import mods.railcraft.api.tracks.TrackKit;
import mods.railcraft.api.tracks.TrackKitInstance;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.world.World;

/**
 * Base class of all Traincraft track kits (Railcraft 12 "outfitted track" kits).
 * <p>
 * Replaces the 1.7.10 {@code TrackBaseTraincraft}, which extended Railcraft's old
 * {@code TrackInstanceBase}. Only ever loaded from {@link ModuleTraincraftTracks},
 * so nothing here is touched when Railcraft is not installed.
 */
public abstract class TrackKitTraincraft extends TrackKitInstance {

    public abstract TCTrackKits getKitType();

    @Override
    public TrackKit getTrackKit() {
        return getKitType().getTrackKit();
    }

    protected World getWorld() {
        return theWorldAsserted();
    }

    protected boolean isServer() {
        World world = theWorld();
        return world != null && !world.isRemote;
    }

    /**
     * 1.7.10 rail meta 0, 4, 5 (north-south, ascending north/south).
     * Everything else an outfitted track can be is east-west.
     */
    protected boolean isNorthSouth() {
        BlockRailBase.EnumRailDirection dir = getRailDirectionRaw();
        return dir == BlockRailBase.EnumRailDirection.NORTH_SOUTH
                || dir == BlockRailBase.EnumRailDirection.ASCENDING_NORTH
                || dir == BlockRailBase.EnumRailDirection.ASCENDING_SOUTH;
    }

    protected void notifyNeighbors() {
        World world = getWorld();
        Block block = world.getBlockState(getPos()).getBlock();
        world.notifyNeighborsOfStateChange(getPos(), block, true);
        world.notifyNeighborsOfStateChange(getPos().down(), block, true);
        markBlockNeedsUpdate();
    }
}
