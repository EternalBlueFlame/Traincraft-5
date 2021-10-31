package train.entity.ai;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class TCPathFinder extends PathFinder{

	private boolean waterAllowed;
	private boolean movementAllowed;
	private boolean doorAllowed;
	
	public TCPathFinder(IBlockAccess worldMap, boolean isWoddenDoorAllowed, boolean isMovementBlockAllowed, 
											boolean isPathingInWater, boolean canEntityDrown) {
		super(worldMap, isWoddenDoorAllowed, isMovementBlockAllowed, isPathingInWater, canEntityDrown);
		waterAllowed = isPathingInWater;
		movementAllowed = isMovementBlockAllowed;
		doorAllowed = isWoddenDoorAllowed;
	}

	@Override
    public int getVerticalOffset(Entity entity, int x, int y, int z, PathPoint point) {

        boolean flag3 = false;
        Block block;

        for (int i = x; i < x + point.xCoord; ++i) {
            for (int j = y; j < y + point.yCoord; ++j) {
                for (int k = z; k < z + point.zCoord; ++k) {
                    block = CommonUtil.getBlockAt(entity.worldObj, i, j, k);

                    if (block.getMaterial() != Material.air) {
                        if (block == Blocks.trapdoor) {
                            flag3 = true;
                        }
                        else if (block != Blocks.flowing_water && block != Blocks.water) {
                            if (!doorAllowed && block == Blocks.wooden_door) {
                                return 0;
                            }
                        }
                        else {
                            if (waterAllowed) {
                                return -1;
                            }

                            flag3 = true;
                        }

                        int k1 = block.getRenderType();

                        if (CommonUtil.getBlockAt(entity.worldObj, i, j, k).getRenderType() == 9) {
                            int j2 = MathHelper.floor_double(entity.posX);
                            int l1 = MathHelper.floor_double(entity.posY);
                            int i2 = MathHelper.floor_double(entity.posZ);

                            if (CommonUtil.getBlockAt(entity.worldObj, j2, l1, i2).getRenderType() != 9 && CommonUtil.getBlockAt(entity.worldObj, j2, l1 - 1, i2).getRenderType() != 9)
                            {
                                return -3;
                            }
                        }
                        else if (!block.getBlocksMovement(entity.worldObj, i, j, k) && (!movementAllowed || block != Blocks.wooden_door)) {
                            if (k1 == 11 || block == Blocks.fence_gate || k1 == 32) {
                                return -3;
                            }

                            if (block == Blocks.trapdoor) {
                                return -4;
                            }

                            Material material = block.getMaterial();

                            if (material != Material.lava) {
                                return 0;
                            }

                            if (!entity.handleLavaMovement()) {
                                return -2;
                            }
                        }
                    }
                }
            }
        }

        return flag3 ? 2 : 1;

    }
}
