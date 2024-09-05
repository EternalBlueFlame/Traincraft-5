/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.tile;

import net.minecraft.util.EnumFacing;

public class TileHelper {

	/**
	 * Finds the side of a block depending on its facing direction from the given side. The side numbers are compatible with the function "getBlockTextureFromSideAndMetadata".<p>
	 * 
	 * Bottom: 0<br>
	 * Top: 1<br>
	 * Back: 2<br>
	 * Front: 3<br>
	 * Left: 4<br>
	 * Right: 5<br>
	 * 
	 * @param front - The direction in which this block is facing/front. Use a number between 0 and 5. Default is 3.
	 * @param side - The side you are trying to find. A number between 0 and 5.
	 * @return The side relative to the facing direction.
	 */
	public static EnumFacing byHorizontalIndexFromSide(EnumFacing front, EnumFacing side) {
		if (front != EnumFacing.UNKNOWN && side != EnumFacing.UNKNOWN) {
			switch (front.ordinal()) {
			case 0:
				switch (side.ordinal()) {
				case 0:
					return EnumFacing.byHorizontalIndex(3);
				case 1:
					return EnumFacing.byHorizontalIndex(2);
				case 2:
					return EnumFacing.byHorizontalIndex(1);
				case 3:
					return EnumFacing.byHorizontalIndex(0);
				case 4:
					return EnumFacing.byHorizontalIndex(5);
				case 5:
					return EnumFacing.byHorizontalIndex(4);
				}

			case 1:
				switch (side.ordinal()) {
				case 0:
					return EnumFacing.byHorizontalIndex(4);
				case 1:
					return EnumFacing.byHorizontalIndex(5);
				case 2:
					return EnumFacing.byHorizontalIndex(0);
				case 3:
					return EnumFacing.byHorizontalIndex(1);
				case 4:
					return EnumFacing.byHorizontalIndex(2);
				case 5:
					return EnumFacing.byHorizontalIndex(3);
				}

			case 2:
				switch (side.ordinal()) {
				case 0:
					return EnumFacing.byHorizontalIndex(0);
				case 1:
					return EnumFacing.byHorizontalIndex(1);
				case 2:
					return EnumFacing.byHorizontalIndex(3);
				case 3:
					return EnumFacing.byHorizontalIndex(2);
				case 4:
					return EnumFacing.byHorizontalIndex(5);
				case 5:
					return EnumFacing.byHorizontalIndex(4);
				}

			case 3:
				return side;

			case 4:
				switch (side.ordinal()) {
				case 0:
					return EnumFacing.byHorizontalIndex(0);
				case 1:
					return EnumFacing.byHorizontalIndex(1);
				case 2:
					return EnumFacing.byHorizontalIndex(5);
				case 3:
					return EnumFacing.byHorizontalIndex(4);
				case 4:
					return EnumFacing.byHorizontalIndex(3);
				case 5:
					return EnumFacing.byHorizontalIndex(2);
				}

			case 5:
				switch (side.ordinal()) {
				case 0:
					return EnumFacing.byHorizontalIndex(0);
				case 1:
					return EnumFacing.byHorizontalIndex(1);
				case 2:
					return EnumFacing.byHorizontalIndex(4);
				case 3:
					return EnumFacing.byHorizontalIndex(5);
				case 4:
					return EnumFacing.byHorizontalIndex(2);
				case 5:
					return EnumFacing.byHorizontalIndex(3);
				}
			}
		}
		return EnumFacing.UNKNOWN;
	}
}
