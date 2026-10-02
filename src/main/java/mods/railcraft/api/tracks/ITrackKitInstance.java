/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.tracks;

import mcp.MethodsReturnNonnullByDefault;
import mods.railcraft.api.core.INetworkedObject;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface defines a track.
 *
 * Basically all block and tile entity functions for Tracks are delegated to an
 * ITrackInstance.
 *
 * Instead of implementing this interface directly, you should probably extend
 * TrackInstanceBase. It will simplify your life.
 *
 * You must have a constructor that accepts a single TileEntity object.
 *
 * All packet manipulation is handled by Railcraft's code, you just need to
 * implement the functions in INetworkedObject to pass data from the server to
 * the client.
 *
 * @author CovertJaguar
 * @see TrackKitInstance
 */
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public interface ITrackKitInstance extends INetworkedObject<DataInputStream, DataOutputStream> {

    <T extends TileEntity & IOutfittedTrackTile> T getTile();

    <T extends TileEntity & IOutfittedTrackTile> void setTile(T tileEntity);

    TrackKit getTrackKit();

    /**
     * Return the render state. Ranges from 0 to 15.
     * Used by the TrackKit blockstate JSON to determine which model/texture to display.
     */
    default int getRenderState() {
        return 0;
    }

    default List<ItemStack> getDrops(int fortune) {
        List<ItemStack> drops = new ArrayList<>();
        drops.add(getTrackKit().getTrackKitItem());
        return drops;
    }

    /**
     * Return the rail's shape.
     * Can be used to make the cart think the rail something other than it is,
     * for example when making diamond junctions or switches.
     *
     * @param cart The cart asking for the metadata, null if it is not called by
     *             EntityMinecart.
     * @return The metadata.
     */
    BlockRailBase.EnumRailDirection getRailDirection(IBlockState state, @Nullable EntityMinecart cart);

    /**
     * This function is called by any minecart that passes over this rail. It is
     * called once per update tick that the minecart is on the rail.
     *
     * @param cart The cart on the rail.
     */
    default void onMinecartPass(EntityMinecart cart) {
    }

    default void writeToNBT(NBTTagCompound data) {
    }

    default void readFromNBT(NBTTagCompound data) {
    }

    default void update() {
    }

    boolean blockActivated(EntityPlayer player, EnumHand hand);

    default void onBlockRemoved() {
    }

    void onBlockPlacedBy(IBlockState state, @Nullable EntityLivingBase placer, ItemStack stack);

    void onNeighborBlockChange(IBlockState state, @Nullable Block neighborBlock);

    default BlockPos getPos() {
        return getTile().getPos();
    }

    @Override
    default @Nullable World theWorld() {
        return getTile().getWorld();
    }

    /**
     * Returns the max speed of the rail.
     *
     * @param cart The cart on the rail, may be null.
     * @return The max speed of the current rail.
     */
    default float getRailMaxSpeed(World world, @Nullable EntityMinecart cart, BlockPos pos) {
        return getTrackType().getEventHandler().getMaxSpeed(world, cart, pos);
    }

    /**
     * Returning true here will make the track unbreakable.
     */
    default boolean isProtected() {
        return false;
    }

    /**
     * Returns the track type of this track.
     */
    default TrackType getTrackType() {
        return getTile().getTrackType();
    }

    /**
     * Requests for saving the data of the track kit.
     */
    default void markDirty() {
        getTile().markDirty();
    }
}
