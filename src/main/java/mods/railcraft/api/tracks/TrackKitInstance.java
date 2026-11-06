/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.tracks;

import com.mojang.authlib.GameProfile;
import mods.railcraft.api.core.RailcraftFakePlayer;
import mods.railcraft.api.items.IToolCrowbar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.block.BlockRailBase.EnumRailDirection.*;

/**
 * All ITrackKits should extend this class. It contains a number of default
 * functions and standard behavior for Tracks that should greatly simplify
 * implementing new Track Kits when using this API.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 * @see ITrackKitInstance
 * @see TrackRegistry
 * @see TrackKit
 */
public abstract class TrackKitInstance implements ITrackKitInstance {

    private TileEntity tileEntity = new DummyTileEntity();

    protected static BlockRailBase.EnumRailDirection getRailDirectionRaw(IBlockState state) {
        if (state.getBlock() instanceof BlockRailBase)
            return state.getValue(((BlockRailBase) state.getBlock()).getShapeProperty());
        return NORTH_SOUTH;
    }

    private BlockRailBase getBlock() {
        return (BlockRailBase) getTile().getBlockType();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends TileEntity & IOutfittedTrackTile> T getTile() {
        return (T) tileEntity;
    }

    @Override
    public <T extends TileEntity & IOutfittedTrackTile> void setTile(T tileEntity) {
        this.tileEntity = tileEntity;
    }

    @Override
    public BlockRailBase.EnumRailDirection getRailDirection(IBlockState state, @Nullable EntityMinecart cart) {
        return getRailDirectionRaw(state);
    }

    public final BlockRailBase.EnumRailDirection getRailDirectionRaw() {
        World world = theWorldAsserted();
        IBlockState state = world.getBlockState(getPos());
        return getRailDirectionRaw(state);
    }

    @Override
    public boolean blockActivated(EntityPlayer player, EnumHand hand) {
        ItemStack heldItem = player.getHeldItem(hand);
        if (heldItem.getItem() instanceof IToolCrowbar) {
            IToolCrowbar crowbar = (IToolCrowbar) heldItem.getItem();
            if (crowbar.canWhack(player, hand, heldItem, getPos()) && onCrowbarWhack(player, hand, heldItem)) {
                crowbar.onWhack(player, hand, heldItem, getPos());
                return true;
            }
        }
        return false;
    }

    public boolean onCrowbarWhack(EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem) {
        if (this instanceof ITrackKitReversible) {
            ITrackKitReversible track = (ITrackKitReversible) this;
            track.setReversed(!track.isReversed());
            markBlockNeedsUpdate();
            return true;
        }
        return false;
    }

    @Override
    public void onBlockPlacedBy(IBlockState state, @Nullable EntityLivingBase placer, ItemStack stack) {
        if (placer != null && this instanceof ITrackKitReversible) {
            EnumFacing facing = placer.getHorizontalFacing();
            ((ITrackKitReversible) this).setReversed(facing == EnumFacing.SOUTH || facing == EnumFacing.WEST);
        }
        switchTrack(state, true);
        testPower(state);
        markBlockNeedsUpdate();
    }

    @Override
    public void sendUpdateToClient() {
        getTile().sendUpdateToClient();
    }

    public void markBlockNeedsUpdate() {
        World world = theWorld();
        if (world != null) {
            IBlockState state = world.getBlockState(getTile().getPos());
            world.notifyBlockUpdate(getTile().getPos(), state, state, 3);
        }
    }

    @Override
    public void onNeighborBlockChange(IBlockState state, @Nullable Block neighborBlock) {
        testPower(state);
    }

    private void switchTrack(IBlockState state, @SuppressWarnings("SameParameterValue") boolean flag) {
        World world = theWorldAsserted();
        BlockPos pos = getTile().getPos();
        BlockRailBase blockTrack = getBlock();
        blockTrack.new Rail(world, pos, state).place(world.isBlockPowered(pos), flag);
    }

    protected final void testPower(IBlockState state) {
        if (!(this instanceof ITrackKitPowered))
            return;
        World world = theWorldAsserted();
        ITrackKitPowered r = (ITrackKitPowered) this;
        boolean powered = world.getRedstonePowerFromNeighbors(getPos()) > 0 || testPowerPropagation(world, getPos(), getTrackKit(), state, r.getPowerPropagation());
        if (powered != r.isPowered()) {
            r.setPowered(powered);
            Block blockTrack = getBlock();
            world.notifyNeighborsOfStateChange(getPos(), blockTrack, true);
            world.notifyNeighborsOfStateChange(getPos().down(), blockTrack, true);
            BlockRailBase.EnumRailDirection railDirection = state.getValue(((BlockRailBase) state.getBlock()).getShapeProperty());
            if (railDirection.isAscending())
                world.notifyNeighborsOfStateChange(getPos().up(), blockTrack, true);
            sendUpdateToClient();
            // System.out.println("Setting power [" + i + ", " + j + ", " + k + "]");
        }
    }

    private boolean testPowerPropagation(World world, BlockPos pos, TrackKit baseSpec, IBlockState state, int maxDist) {
        return isConnectedRailPowered(world, pos, baseSpec, state, true, 0, maxDist) || isConnectedRailPowered(world, pos, baseSpec, state, false, 0, maxDist);
    }

    private boolean isConnectedRailPowered(World world, BlockPos pos, TrackKit baseSpec, IBlockState state, boolean dir, int dist, int maxDist) {
        if (dist >= maxDist)
            return false;
        boolean powered = true;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        BlockRailBase.EnumRailDirection railDirection = state.getValue(getBlock().getShapeProperty());
        switch (railDirection) {
            case NORTH_SOUTH: // '\0'
                if (dir)
                    z++;
                else
                    z--;
                break;

            case EAST_WEST: // '\001'
                if (dir)
                    x--;
                else
                    x++;
                break;

            case ASCENDING_EAST: // '\002'
                if (dir)
                    x--;
                else {
                    x++;
                    y++;
                    powered = false;
                }
                railDirection = EAST_WEST;
                break;

            case ASCENDING_WEST: // '\003'
                if (dir) {
                    x--;
                    y++;
                    powered = false;
                } else
                    x++;
                railDirection = EAST_WEST;
                break;

            case ASCENDING_NORTH: // '\004'
                if (dir)
                    z++;
                else {
                    z--;
                    y++;
                    powered = false;
                }
                railDirection = NORTH_SOUTH;
                break;

            case ASCENDING_SOUTH: // '\005'
                if (dir) {
                    z++;
                    y++;
                    powered = false;
                } else
                    z--;
                railDirection = NORTH_SOUTH;
                break;
        }
        pos = new BlockPos(x, y, z);
        return testPowered(world, pos, baseSpec, dir, dist, maxDist, railDirection) || (powered && testPowered(world, pos.down(), baseSpec, dir, dist, maxDist, railDirection));
    }

    private boolean testPowered(World world, BlockPos nextPos, TrackKit baseSpec, boolean dir, int dist, int maxDist, BlockRailBase.EnumRailDirection prevOrientation) {
        // System.out.println("Testing Power at <" + nextPos + ">");
        IBlockState nextBlockState = world.getBlockState(nextPos);
        if (nextBlockState.getBlock() == getBlock()) {
            BlockRailBase.EnumRailDirection nextOrientation = nextBlockState.getValue(((BlockRailBase) nextBlockState.getBlock()).getShapeProperty());
            TileEntity nextTile = world.getTileEntity(nextPos);
            if (nextTile instanceof IOutfittedTrackTile) {
                ITrackKitInstance nextTrack = ((IOutfittedTrackTile) nextTile).getTrackKitInstance();
                if (!(nextTrack instanceof ITrackKitPowered) || nextTrack.getTrackKit() != baseSpec || !((ITrackKitPowered) this).canPropagatePowerTo(nextTrack))
                    return false;
                if (prevOrientation == EAST_WEST && (nextOrientation == NORTH_SOUTH || nextOrientation == ASCENDING_NORTH || nextOrientation == ASCENDING_SOUTH))
                    return false;
                if (prevOrientation == NORTH_SOUTH && (nextOrientation == EAST_WEST || nextOrientation == ASCENDING_EAST || nextOrientation == ASCENDING_WEST))
                    return false;
                if (((ITrackKitPowered) nextTrack).isPowered())
                    return world.isBlockPowered(nextPos) || world.isBlockPowered(nextPos.up()) || isConnectedRailPowered(world, nextPos, baseSpec, nextBlockState, dir, dist + 1, maxDist);
            }
        }
        return false;
    }

    private class DummyTileEntity extends TileEntity implements IOutfittedTrackTile {
        @Override
        public TrackType getTrackType() {
            return TrackRegistry.TRACK_TYPE.getFallback();
        }

        @Override
        public ITrackKitInstance getTrackKitInstance() {
            return new TrackKitMissing(false);
        }

        @Override
        public void sendUpdateToClient() {
            throw new RuntimeException();
        }

        @Override
        public GameProfile getOwner() {
            return RailcraftFakePlayer.RAILCRAFT_USER_PROFILE;
        }
    }
}
