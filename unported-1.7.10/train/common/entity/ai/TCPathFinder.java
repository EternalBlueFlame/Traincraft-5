package train.common.entity.ai;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.world.IBlockAccess;
import train.common.blocks.BlockTCRail;
import train.common.blocks.BlockTCRailGag;

public class TCPathFinder extends PathFinder {
    private final boolean waterAllowed;
    private final boolean movementAllowed;
    private final boolean doorAllowed;

    public TCPathFinder(IBlockAccess worldMap, boolean isWoddenDoorAllowed, boolean isMovementBlockAllowed,
                        boolean isPathingInWater, boolean canEntityDrown) {
        super(worldMap, isWoddenDoorAllowed, isMovementBlockAllowed, isPathingInWater, canEntityDrown);
        waterAllowed = isPathingInWater;
        movementAllowed = isMovementBlockAllowed;
        doorAllowed = isWoddenDoorAllowed;
    }

    public int getVerticalOffset(Entity entity, int x, int y, int z, PathPoint point) {
        return getVertical(entity, x, y, z, point, waterAllowed, movementAllowed, doorAllowed);
    }

    public static int getVertical(Entity entity, int x, int y, int z, PathPoint point, boolean water, boolean movement, boolean door) {
        boolean flag3 = false;

        for (int i = x; i < x + point.xCoord; ++i) {
            for (int j = y; j < y + point.yCoord; ++j) {
                for (int k = z; k < z + point.zCoord; ++k) {
                    Block block = CommonUtil.getBlockAt(entity.world, i, j, k);

                    if (CommonUtil.getMaterial(block) != Material.air) {
                        if (block == Blocks.TRAPDOOR) {
                            flag3 = true;
                        } else if (block != Blocks.FLOWING_WATER && block != Blocks.WATER) {
                            if (!door && block == Blocks.OAK_DOOR) {
                                return 0;
                            }
                        } else {
                            if (water) {
                                return -1;
                            }

                            flag3 = true;
                        }

                        int k1 = block.getRenderType();
                        if(block instanceof BlockTCRail || block instanceof BlockTCRailGag){
                            k1=9;
                        }

                        if (CommonUtil.getBlockAt(entity.world, i, j, k).getRenderType() == 9) {
                            int j2 = CommonUtil.floorDouble(entity.posX);
                            int l1 = CommonUtil.floorDouble(entity.posY);
                            int i2 = CommonUtil.floorDouble(entity.posZ);

                            if (CommonUtil.getBlockAt(entity.world, j2, l1, i2).getRenderType() != 9 && CommonUtil.getBlockAt(entity.world, j2, l1 - 1, i2).getRenderType() != 9) {
                                return -3;
                            }
                        } else if (!block.getBlocksMovement(entity.getWorld(), i, j, k) && (!movement || block != Blocks.OAK_DOOR)) {
                            if (k1 == 11 || block == Blocks.OAK_FENCE_GATE || k1 == 32) {
                                return -3;
                            }

                            if (block == Blocks.TRAPDOOR) {
                                return -4;
                            }

                            Material material = CommonUtil.getMaterial(block);

                            if (material != Material.LAVA) {
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
