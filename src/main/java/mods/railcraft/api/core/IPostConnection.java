/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.core;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Locale;

/**
 * If you want your block to connect (or not connect) to posts, implement this
 * interface.
 * <p/>
 * The result takes priority over any other rules.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface IPostConnection {

    enum ConnectStyle implements IStringSerializable {

        NONE,
        SINGLE_THICK,
        TWO_THIN;

        @Override
        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

    /**
     * Return the ConnectStyle that should be used if the block at this location
     * connects to a post.
     *
     * @param world The World
     * @param pos   Our position
     * @param state Our BlockState
     * @param side  Side to connect to
     * @return true if connect
     */
    ConnectStyle connectsToPost(IBlockAccess world, BlockPos pos, IBlockState state, EnumFacing side);

}
