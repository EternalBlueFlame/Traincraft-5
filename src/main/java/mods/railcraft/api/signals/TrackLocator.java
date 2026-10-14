/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.signals;

import mods.railcraft.api.tracks.TrackToolsAPI;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * Created by CovertJaguar on 7/9/2017 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TrackLocator {
    private final TileEntity signalTile;
    private @Nullable BlockPos trackLocation;

    public TrackLocator(TileEntity signalTile) {
        this.signalTile = signalTile;
    }

    public @Nullable BlockPos getTrackLocation() {
        if (trackLocation == null)
            locateTrack();
        return trackLocation;
    }

    public Status getTrackStatus() {
        if (trackLocation == null)
            return locateTrack();
        if (!signalTile.getWorld().isBlockLoaded(trackLocation))
            return Status.UNKNOWN;
        if (!TrackToolsAPI.isRailBlockAt(signalTile.getWorld(), trackLocation)) {
            trackLocation = null;
            return locateTrack();
        }
        return Status.VALID;
    }

    private Status locateTrack() {
        int x = signalTile.getPos().getX();
        int y = signalTile.getPos().getY();
        int z = signalTile.getPos().getZ();
        Status status = testForTrack(x, y, z);
        if (status != Status.INVALID)
            return status;
        status = testForTrack(x - 1, y, z);
        if (status != Status.INVALID)
            return status;
        status = testForTrack(x + 1, y, z);
        if (status != Status.INVALID)
            return status;
        status = testForTrack(x, y, z - 1);
        if (status != Status.INVALID)
            return status;
        status = testForTrack(x, y, z + 1);
        if (status != Status.INVALID)
            return status;
        status = testForTrack(x - 2, y, z);
        if (status != Status.INVALID)
            return status;
        status = testForTrack(x + 2, y, z);
        if (status != Status.INVALID)
            return status;
        status = testForTrack(x, y, z - 2);
        if (status != Status.INVALID)
            return status;
        status = testForTrack(x, y, z + 2);
        if (status != Status.INVALID)
            return status;
        return Status.INVALID;
    }

    private Status testForTrack(int x, int y, int z) {
        World world = signalTile.getWorld();
        for (int jj = -2; jj < 4; jj++) {
            BlockPos pos = new BlockPos(x, y - jj, z);
            if (!world.isBlockLoaded(pos))
                return Status.UNKNOWN;
            if (TrackToolsAPI.isRailBlockAt(world, pos)) {
                trackLocation = pos;
                return Status.VALID;
            }
        }
        return Status.INVALID;
    }

    public enum Status {

        VALID, INVALID, UNKNOWN
    }
}
