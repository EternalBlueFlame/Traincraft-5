/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/
package mods.railcraft.api.tracks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class TrackKitMissing extends TrackKitInstance {
    private final boolean swapOut;
    private int counter;

    public TrackKitMissing() {
        this(true);
    }

    public TrackKitMissing(boolean swapOut) {
        this.swapOut = swapOut;
    }

    @Override
    public TrackKit getTrackKit() {
        return TrackRegistry.getMissingTrackKit();
    }

    @Override
    public void update() {
        swapTrack();
    }

    @Override
    public void onNeighborBlockChange(IBlockState state, @Nullable Block neighborBlock) {
        super.onNeighborBlockChange(state, neighborBlock);
        swapTrack();
    }

    private void swapTrack() {
        World world = theWorld();
        if (swapOut && world != null && !world.isRemote && counter > 4) {
            IBlockState oldState = world.getBlockState(getPos());
            BlockRailBase oldBlock = (BlockRailBase) oldState.getBlock();
            TrackType type = getTile().getTrackType();
            BlockRailBase newBlock = type.getBaseBlock();
            IBlockState newState = newBlock.getDefaultState().withProperty(newBlock.getShapeProperty(), oldState.getValue(oldBlock.getShapeProperty()));
            world.setBlockState(getPos(), newState);
        }
        counter++;
    }
}
