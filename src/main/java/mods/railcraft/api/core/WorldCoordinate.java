/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/
package mods.railcraft.api.core;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

/**
 * This immutable class represents a point in the Minecraft world, while taking
 * into account the possibility of coordinates in different dimensions.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public final class WorldCoordinate {
    /**
     * The dimension
     */
    private final int dimension;

    private final BlockPos pos;

    /**
     * Creates a new WorldCoordinate
     *
     * @param dimension Dimension ID
     * @param x         World Coordinate
     * @param y         World Coordinate
     * @param z         World Coordinate
     */
    public WorldCoordinate(int dimension, int x, int y, int z) {
        this.dimension = dimension;
        this.pos = new BlockPos(x, y, z);
    }

    /**
     * Creates a new WorldCoordinate
     *
     * @param dimension Dimension ID
     * @param pos       World Coordinates
     */
    public WorldCoordinate(int dimension, BlockPos pos) {
        this.dimension = dimension;
        this.pos = pos;
    }

    public static WorldCoordinate from(TileEntity tile) {
        return new WorldCoordinate(tile.getWorld().provider != null ? tile.getWorld().provider.getDimension() : 0, tile.getPos());
    }

    public static @Nullable WorldCoordinate readFromNBT(NBTTagCompound data, String key) {
        if (data.hasKey(key, 10)) {
            NBTTagCompound nbt = data.getCompoundTag(key);
            int dim = nbt.getInteger("dim");
            int x = nbt.getInteger("x");
            int y = nbt.getInteger("y");
            int z = nbt.getInteger("z");
            return new WorldCoordinate(dim, x, y, z);
        } else if (data.hasKey(key, 11)) {
            int[] c = data.getIntArray(key);
            return new WorldCoordinate(c[0], c[1], c[2], c[3]);
        }
        return null;
    }

    public void writeToNBT(NBTTagCompound data, String tag) {
        data.setIntArray(tag, new int[]{dimension, getX(), getY(), getZ()});
    }

    public boolean isInSameChunk(WorldCoordinate otherCoord) {
        return dimension == otherCoord.dimension && getX() >> 4 == otherCoord.getX() >> 4 && getZ() >> 4 == otherCoord.getZ() >> 4;
    }

    public boolean isEqual(int dim, int x, int y, int z) {
        return getX() == x && getY() == y && getZ() == z && dimension == dim;
    }

    public boolean isEqual(int dim, BlockPos p) {
        return getX() == p.getX() && getY() == p.getY() && getZ() == p.getZ() && dimension == dim;
    }

    public boolean isEqual(WorldCoordinate p) {
        return getX() == p.getX() && getY() == p.getY() && getZ() == p.getZ() && getDim() == p.getDim();
    }

//    public int compareTo(@Nonnull WorldCoordinate o) {
//        if (dimension != o.dimension)
//            return dimension - o.dimension;
//        if (getX() != o.getX())
//            return getX() - o.getX();
//        if (getY() != o.getY())
//            return getY() - o.getY();
//        if (getZ() != o.getZ())
//            return getZ() - o.getZ();
//        return 0;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorldCoordinate that = (WorldCoordinate) o;

        return dimension == that.dimension && pos.equals(that.pos);
    }

    @Override
    public int hashCode() {
        int result = dimension;
        result = 31 * result + pos.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "WorldCoordinate{" + "dimension=" + dimension + ", x=" + getX() + ", y=" + getY() + ", z=" + getZ() + '}';
    }

    public int getDim() {
        return dimension;
    }

    public BlockPos getPos() {
        return pos;
    }

    public int getX() {
        return pos.getX();
    }

    public int getY() {
        return pos.getY();
    }

    public int getZ() {
        return pos.getZ();
    }

    public Vec3d getVec3d() {
        return new Vec3d(getPos());
    }

    @Deprecated // cubic chunks
    public boolean isBelowWorld() {
        return getY() < 0;
    }
}
