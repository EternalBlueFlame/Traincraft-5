/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.tracks;

import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by CovertJaguar on 5/20/2017 for Railcraft.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface IBlockTrackOutfitted extends IBlockTrack {

    TrackKit getTrackKit(IBlockAccess world, BlockPos pos);

    boolean place(World world, BlockPos pos, EntityLivingBase placer, BlockRailBase.EnumRailDirection shape, TrackType trackType, TrackKit trackKit);
}
