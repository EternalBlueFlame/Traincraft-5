/*
 * ******************************************************************************
 *  Copyright 2011-2015 CovertJaguar
 *
 *  This work (the API) is licensed under the "MIT" License, see LICENSE.md for details.
 * ***************************************************************************
 */

package mods.railcraft.api.tracks;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.BlockRailBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * A number of utility functions related to rails.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public abstract class RailTools {
    public static boolean isRailBlockAt(IBlockAccess world, int x, int y, int z) {
        return world.getBlock(x, y, z) instanceof BlockRailBase;
    }


    public static int countAdjecentTracks(World world, int x, int y, int z) {
        int i = 0;

        if (isTrackFuzzyAt(world, x, y, z - 1))
            ++i;

        if (isTrackFuzzyAt(world, x, y, z + 1))
            ++i;

        if (isTrackFuzzyAt(world, x - 1, y, z))
            ++i;

        if (isTrackFuzzyAt(world, x + 1, y, z))
            ++i;

        return i;
    }

    public static boolean isTrackFuzzyAt(World world, int x, int y, int z) {
        return CommonUtil.isRailBlockAt(world, x, y, z) || (CommonUtil.isRailBlockAt(world, x, y + 1, z) || CommonUtil.isRailBlockAt(world, x, y - 1, z));
    }

    public static <T> T getTrackObjectAt(World world, int x, int y, int z, Class<T> type) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile == null)
            return null;
        if (type.isInstance(tile))
            return (T) tile;
        if (tile instanceof ITrackTile) {
            ITrackInstance track = ((ITrackTile) tile).getTrackInstance();
            if (type.isInstance(track))
                return (T) track;
        }
        return null;
    }

}
