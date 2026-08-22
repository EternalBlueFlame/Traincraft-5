package net.minecraftforge.common.util;

import net.minecraft.util.EnumFacing;

public enum ForgeDirection {
	DOWN(0, -1, 0),
	UP(0, 1, 0),
	NORTH(0, 0, -1),
	SOUTH(0, 0, 1),
	WEST(-1, 0, 0),
	EAST(1, 0, 0),
	UNKNOWN(0, 0, 0);

	public static final ForgeDirection[] VALID_DIRECTIONS = {DOWN, UP, NORTH, SOUTH, WEST, EAST};

	public final int offsetX;
	public final int offsetY;
	public final int offsetZ;

	private ForgeDirection(int x, int y, int z) {
		this.offsetX = x;
		this.offsetY = y;
		this.offsetZ = z;
	}

	public static ForgeDirection getOrientation(int id) {
		if (id >= 0 && id < VALID_DIRECTIONS.length) {
			return VALID_DIRECTIONS[id];
		}
		return UNKNOWN;
	}

	public ForgeDirection getOpposite() {
		switch (this) {
			case DOWN: return UP;
			case UP: return DOWN;
			case NORTH: return SOUTH;
			case SOUTH: return NORTH;
			case WEST: return EAST;
			case EAST: return WEST;
			default: return UNKNOWN;
		}
	}

	public ForgeDirection opposite() {
		return getOpposite();
	}

	public EnumFacing toDirection() {
		switch (this) {
			case DOWN: return EnumFacing.DOWN;
			case UP: return EnumFacing.UP;
			case NORTH: return EnumFacing.NORTH;
			case SOUTH: return EnumFacing.SOUTH;
			case WEST: return EnumFacing.WEST;
			case EAST: return EnumFacing.EAST;
			default: return null;
		}
	}
}
