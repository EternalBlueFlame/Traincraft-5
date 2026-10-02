/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.tracks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

/**
 * Used by rails that modify the bounding boxes.
 *
 * For example, the Gated Rails.
 *
 * Not very useful since there is no system in place to insert custom render code.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface ITrackKitCustomShape extends ITrackKitInstance {

    @Nullable
    AxisAlignedBB getCollisionBoundingBox(IBlockState state);

    AxisAlignedBB getSelectedBoundingBox();

    @Nullable
    RayTraceResult collisionRayTrace(Vec3d vec3d, Vec3d vec3d1);
}
