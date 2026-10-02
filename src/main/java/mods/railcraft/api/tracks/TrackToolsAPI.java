/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.tracks;

import mods.railcraft.api.core.RailcraftFakePlayer;
import mods.railcraft.api.items.ITrackItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * A number of utility functions related to rails.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public final class TrackToolsAPI {
    /**
     * This variable is replaced with an instance of BlockTrackOutfitted at runtime,
     * assuming it is enabled. Avoid relying on the return values of these functions unless you've checked that the
     * block in the world is indeed an instance of IBlockTrackOutfitted.
     */
    public static IBlockTrackOutfitted blockTrackOutfitted = new IBlockTrackOutfitted() {
        @Override
        public TrackKit getTrackKit(IBlockAccess world, BlockPos pos) {
            return TrackRegistry.TRACK_KIT.getFallback();
        }

        @Override
        public boolean place(World world, BlockPos pos, EntityLivingBase placer, BlockRailBase.EnumRailDirection shape, TrackType trackType, TrackKit trackKit) {
            return false;
        }

        @Override
        public TrackType getTrackType(IBlockAccess world, BlockPos pos) {
            return TrackRegistry.TRACK_TYPE.getFallback();
        }
    };

    /**
     * Check if the block at the location is a Track.
     */
    public static boolean isRailBlockAt(IBlockAccess world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() instanceof BlockRailBase;
    }

    public static IBlockState makeTrackState(BlockRailBase block, @Nullable BlockRailBase.EnumRailDirection trackShape) {
        IProperty<BlockRailBase.EnumRailDirection> property = block.getShapeProperty();
        IBlockState state = block.getDefaultState();
        if (trackShape != null && property.getAllowedValues().contains(trackShape))
            state = state.withProperty(property, trackShape);
        return state;
    }

    /**
     * Attempts to place a rail of the type provided. There is no need to verify
     * that the ItemStack contains a valid rail prior to calling this function.
     * <p/>
     * The function takes care of that and will return false if the ItemStack is
     * not a valid ITrackItem or an ItemBlock who's id will return true when
     * passed to BlockRailBase.isRailBlock(itemID).
     * <p/>
     * That means this function can place any Railcraft or vanilla rail and has
     * at least a decent chance of being able to place most third party rails.
     *
     * @param stack The ItemStack containing the rail
     * @param world The World object
     * @return true if successful
     * @see ITrackItem
     */
    public static boolean placeRailAt(ItemStack stack, WorldServer world, BlockPos pos, BlockRailBase.EnumRailDirection trackShape) {
        if (stack.getItem() instanceof ITrackItem)
            return ((ITrackItem) stack.getItem()).placeTrack(stack.copy(), RailcraftFakePlayer.get(world, pos.offset(EnumFacing.UP)), world, pos, trackShape);
        if (stack.getItem() instanceof ItemBlock) {
            Block block = ((ItemBlock) stack.getItem()).getBlock();
            if (block instanceof BlockRailBase) {
                IBlockState blockState = makeTrackState((BlockRailBase) block, trackShape);
                boolean success = world.setBlockState(pos, blockState);
                if (success)
                    world.playSound(null, pos, block.getSoundType(blockState, world, pos, null).getPlaceSound(), SoundCategory.BLOCKS, (block.getSoundType().getVolume() + 1.0F) / 2.0F, block.getSoundType().getPitch() * 0.8F);
                return success;
            }
        }
        return false;
    }

    public static boolean placeRailAt(ItemStack stack, WorldServer world, BlockPos pos) {
        return placeRailAt(stack, world, pos, BlockRailBase.EnumRailDirection.NORTH_SOUTH);
    }

    /**
     * Returns true if the ItemStack contains a valid Railcraft Track item.
     * <p/>
     * Will return false is passed a vanilla rail.
     *
     * @param stack The ItemStack to test
     * @return true if rail
     * @see ITrackItem
     */
    public static boolean isTrackItem(@Nullable ItemStack stack) {
        return stack != null && stack.getItem() instanceof ITrackItem;
    }

    /**
     * Checks to see if a cart is being held by a ITrackLockdown.
     *
     * @param cart The cart to check
     * @return True if being held
     */
    public static boolean isCartLockedDown(EntityMinecart cart) {
        BlockPos pos = cart.getPosition();

        if (BlockRailBase.isRailBlock(cart.getEntityWorld(), pos.down()))
            pos = pos.down();

        TileEntity tile = cart.getEntityWorld().getTileEntity(pos);
        if (tile instanceof IOutfittedTrackTile) {
            ITrackKitInstance track = ((IOutfittedTrackTile) tile).getTrackKitInstance();
            return track instanceof ITrackKitLockdown && ((ITrackKitLockdown) track).isCartLockedDown(cart);
        }
        return false;
    }

    public static int countAdjacentTracks(World world, BlockPos pos) {
        return (int) Arrays.stream(EnumFacing.HORIZONTALS).filter(side -> isTrackFuzzyAt(world, pos.offset(side))).count();
    }

    public static boolean isTrackFuzzyAt(World world, BlockPos pos) {
        return BlockRailBase.isRailBlock(world, pos) || (BlockRailBase.isRailBlock(world, pos.up()) || BlockRailBase.isRailBlock(world, pos.down()));
    }

    public static @Nullable TrackKit getTrackKit(World world, BlockPos pos) {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof IOutfittedTrackTile) {
            return ((IOutfittedTrackTile) te).getTrackKitInstance().getTrackKit();
        }
        return null;
    }

    public static TrackKit getTrackKitSafe(World world, BlockPos pos) {
        TrackKit ret = getTrackKit(world, pos);
        return ret == null ? TrackRegistry.getMissingTrackKit() : ret;
    }

    public static TrackType getTrackType(ItemStack stack) {
        if (stack.getItem() instanceof IItemTrack) {
            return ((IItemTrack) stack.getItem()).getTrackType(stack);
        }
        return TrackRegistry.TRACK_TYPE.get(stack);
    }

    private TrackToolsAPI() {
    }

}
