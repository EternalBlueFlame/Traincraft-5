package train.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockMushroom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.util.vector.Matrix2f;
import org.lwjgl.util.vector.Vector2f;
import train.common.blocks.TCBlocks;
import train.common.library.BlockIDs;
import train.common.library.EnumTracks;
import train.common.library.Info;
import train.common.library.ItemIDs;
import train.common.tile.TileTCRail;
import train.common.tile.TileTCRailGag;

import javax.annotation.Nullable;
import javax.sound.midi.Track;
import java.util.List;

import static train.common.items.TCRailTypes.*;

public class ItemTCRail extends ItemPart {


    private final EnumTracks type;

    private String typeVariantStraight = EnumTracks.SMALL_STRAIGHT.getLabel();
    private String typeVariantDiagonal = EnumTracks.SMALL_DIAGONAL_STRAIGHT.getLabel();
    private String typeVariant90Turn;
    private String typeVariantCrossing;


    private Item idVariantSwitch;
    EnumTracks tempType;





    public ItemTCRail(EnumTracks t) {
        super(t.getItem().iconName, Info.modID);
        this.overridePath("tracks");
        this.type = t;
    }

    private boolean canPlaceTrack(EntityPlayer player, World world, int x, int y, int z) {
        Block l1 = world.getBlock(x, y - 1, z);

        if (player != null && (!player.canPlayerEdit(x, y - 1, z, 0, player.getCurrentEquippedItem()) ||
                !player.canPlayerEdit(x, y, z, 0, player.getCurrentEquippedItem()))
        ) {
            return false;
        }

        return canBeReplaced(world, x, y, z) && (World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) || l1 == TCBlocks.bridgePillar);
    }

    private boolean canBeReplaced(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        return block == null || block.isReplaceable(world, x, y, z) || block instanceof BlockFlower
                || block == Blocks.double_plant || block instanceof BlockMushroom;
    }
    /**
     * @param world
     * @param x
     * @param y
     * @param z          //@param posX[] array of gag
     *                   //@param posZ[] array of gag
     * @param l          orientation
     * @param exitFacing
     * @param posExitX
     * @param posExitZ
     * @param r          ray
     * @param cx         circle center
     * @param cy         circle center
     * @param cz         circle center
     * @param type
     * @return
     */
    private boolean putDownTurn(@Nullable EntityPlayer player, World world, boolean putDownEnterTrack, int x, int y, int z, int[] posX, int[] posZ,
                                int l, boolean putDownExitTrack, int exitFacing, int posExitX, int posExitZ, double r, double cx, double cy,
                                double cz, String type, Item idDrop) {


        TileTCRailGag[] tileGag = new TileTCRailGag[posX.length - 1];

        /* check if first straight rail can be placed */
        if (putDownEnterTrack && !canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }

        /* check if Gag rails can be placed */
        for (int gag = 0; gag < posX.length; gag++) {
            if (!canPlaceTrack(player, world, posX[gag], y + 1, posZ[gag])) {
                return false;
            }
        }
        /* Check last block */
        if (putDownExitTrack && !canPlaceTrack(player, world, posExitX, y + 1, posExitZ)) {
            return false;
        }
        if (putDownEnterTrack) {
            /* first rail of the turn is a 1 block straight */
            placeTrack(world, x, y + 1, z, BlockIDs.tcRail.block, l);
            TileTCRail tcRailStart = (TileTCRail) world.getTileEntity(x, y + 1, z);
            tcRailStart.setType(typeVariantStraight);
            tcRailStart.setFacing(l);
            tcRailStart.isLinkedToRail = true;
            tcRailStart.linkedX = posX[0];
            tcRailStart.linkedY = y + 1;
            tcRailStart.linkedZ = posZ[0];
        }

        /* the turn starts with this rail */
        placeTrack(world, posX[0], y + 1, posZ[0], BlockIDs.tcRail.block, l);
        TileTCRail tcRail = (TileTCRail) world.getTileEntity(posX[0], y + 1, posZ[0]);
        tcRail.setFacing(l);
        tcRail.r = r;
        tcRail.cx = cx;
        tcRail.cy = cy;
        tcRail.cz = cz;
        tcRail.setType(type);
        tcRail.idDrop = idDrop;

        /* Gag rails containing reference to first turn rail */
        for (int gag = 1; gag < posX.length; gag++) {
            placeTrack(world, posX[gag], y + 1, posZ[gag], BlockIDs.tcRailGag.block, 0);
            tileGag[gag - 1] = (TileTCRailGag) world.getTileEntity(posX[gag], y + 1, posZ[gag]);
        }

        if (putDownExitTrack) {
            /* Last rail is a 1 block straight */
            placeTrack(world, posExitX, y + 1, posExitZ, BlockIDs.tcRail.block, exitFacing);
            TileTCRail tcRailEnd = (TileTCRail) world.getTileEntity(posExitX, y + 1, posExitZ);
            tcRailEnd.setFacing(exitFacing);
            tcRailEnd.setType(typeVariantStraight);
            tcRailEnd.isLinkedToRail = true;
            tcRailEnd.linkedX = posX[0];
            tcRailEnd.linkedY = y + 1;
            tcRailEnd.linkedZ = posZ[0];
        }

        for (TileTCRailGag tileTCRailGag : tileGag) {
            tileTCRailGag.originX = posX[0];
            tileTCRailGag.originY = y + 1;
            tileTCRailGag.originZ = posZ[0];
            tileTCRailGag.type = type;
        }
        return true;
    }

    private boolean putDownSlopedTurn(@Nullable EntityPlayer player, World world, int x, int y, int z, int[] posX, int[] posZ,
                                      int l, double r, double cx, double cy, double cz, float slopeAngle, double slopeLength, String type, Item idDrop) {


        /*if (world.getBlock(x, y, z) == BlockIDs.bridgePillar.block) {
            return false;
        }*/

        TileTCRailGag[] tileGag = new TileTCRailGag[posX.length - 1];

        /** check if Gag rails can be placed */
        for (int gag = 0; gag < posX.length; gag++) {
            if (!canPlaceTrack(player, world, posX[gag], y + 1, posZ[gag])) {
                return false;
            }
        }

        /** the turn starts with this rail */
        placeTrack(world, posX[0], y + 1, posZ[0], BlockIDs.tcRail.block, l);
        TileTCRail tcRail = (TileTCRail) world.getTileEntity(posX[0], y + 1, posZ[0]);
        tcRail.setFacing(l);
        tcRail.r = r;
        tcRail.cx = cx;
        tcRail.cy = cy;
        tcRail.cz = cz;
        tcRail.setType(type);
        tcRail.idDrop = idDrop;
        tcRail.slopeAngle = slopeAngle;
        tcRail.slopeHeight = 1;
        tcRail.slopeLength = slopeLength;
        Block block = world.getBlock(x, y, z);
        int blockID = Block.getIdFromBlock(block);
        tcRail.setBallastMaterial(blockID);
        tcRail.ballastMetadata = world.getBlockMetadata(x, y, z);
        /** Gag rails containing reference to first turn rail */
        for (int gag = 1; gag <= posX.length - 1; gag++) {
            placeTrack(world, posX[gag], y + 1, posZ[gag], BlockIDs.tcRailGag.block, 0);
            tileGag[gag - 1] = (TileTCRailGag) world.getTileEntity(posX[gag], y + 1, posZ[gag]);
            tileGag[gag - 1].bbHeight = Math.max(0.125f, Math.min(1f, gag / (float) (posX.length - 1)));

        }
        for (TileTCRailGag tileTCRailGag : tileGag) {
            tileTCRailGag.originX = posX[0];
            tileTCRailGag.originY = y + 1;
            tileTCRailGag.originZ = posZ[0];
            tileTCRailGag.type = type;
        }

        return true;
    }

    private void putDownSingleRail(World world, int posX, int posY, int posZ, int l, double cx, double cy, double cz, double r, String label, boolean hasModel, int linkedX, int linkedY, int linkedZ, boolean canTypeBeModifiedBySwitch, boolean shouldDrop) {
        /** Switch rail */
        placeTrack(world, posX, posY, posZ, BlockIDs.tcRail.block, l);
        TileTCRail tcRail = (TileTCRail) world.getTileEntity(posX, posY, posZ);
        //world.setBlockMetadataWithNotify(posX, posY, posZ, l, 2);
        tcRail.setFacing(l);
        tcRail.cx = cx;
        tcRail.cy = cy;
        tcRail.cz = cz;
        tcRail.r = r;
        tcRail.setType(label);
        tcRail.hasModel = hasModel;
        tcRail.isLinkedToRail = true;
        tcRail.linkedX = linkedX;
        tcRail.linkedY = linkedY;
        tcRail.linkedZ = linkedZ;
        tcRail.canTypeBeModifiedBySwitch = canTypeBeModifiedBySwitch;
        if (shouldDrop) tcRail.idDrop = ItemIDs.tcRailSmallStraight.item;
    }

    public static String getTrackOrientation(int l, float yaw) {
        if (l == 2 && yaw >= -180 && yaw <= -135) {
            return "right";
        }
        if (l == 2 && yaw <= 180 && yaw >= 135) {
            return "left";
        }
        if (l == 3 && yaw > -135 && yaw <= -90) {
            return "left";
        }
        if (l == 3 && yaw > -90 && yaw <= -45) {
            return "right";
        }
        if (l == 0 && yaw > -45 && yaw <= 0) {
            return "left";
        }
        if (l == 0 && yaw > 0 && yaw <= 45) {
            return "right";
        }
        if (l == 1 && yaw > 45 && yaw <= 90) {
            return "left";
        }
        if (l == 1 && yaw > 90 && yaw <= 135) {
            return "right";
        }
        return "";
    }


    public static Vector2f getDirectionVector(int facing) {
        Matrix2f nrot90 = new Matrix2f();
        nrot90.m00 = +0;
        nrot90.m01 = +1;
        nrot90.m10 = -1;
        nrot90.m11 = +0;

        Vector2f vec = new Vector2f();
        vec.x = 0;
        vec.y = 1;

        for (int i = 0; i < facing; i++) {
            Vector2f nvec = new Vector2f();
            nvec.x = vec.x * nrot90.m00 + vec.y * nrot90.m10;
            nvec.y = vec.x * nrot90.m01 + vec.y * nrot90.m11;
            vec = nvec;
        }
        return vec;

    }

    private int[][] getUsedSpaceFromType(EnumTracks type, @Nullable EntityPlayer player) {
        /** Straights */
        if (type == EnumTracks.SMALL_STRAIGHT
                || type == EnumTracks.SMALL_ROAD_CROSSING
                || type == EnumTracks.SMALL_ROAD_CROSSING_1
                || type == EnumTracks.SMALL_ROAD_CROSSING_2
                || type == EnumTracks.SMALL_ROAD_CROSSING_DYNAMIC
                || type == EnumTracks.EMBEDDED_SMALL_STRAIGHT
                || type == EnumTracks.TURN_1X1
                || type == EnumTracks.EMBEDDED_TURN_1X1)
            return new int[][]{{0, 0}};
        else if (type == EnumTracks.MEDIUM_STRAIGHT || type == EnumTracks.EMBEDDED_MEDIUM_STRAIGHT)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}};
        else if (type == EnumTracks.LONG_STRAIGHT || type == EnumTracks.EMBEDDED_LONG_STRAIGHT)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}};
        else if (type == EnumTracks.VERY_LONG_STRAIGHT || type == EnumTracks.EMBEDDED_VERY_LONG_STRAIGHT)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {10, 0}, {11, 0}};
        /** Diagonals*/
        else if (type == EnumTracks.SMALL_DIAGONAL_STRAIGHT || type == EnumTracks.EMBEDDED_SMALL_DIAGONAL_STRAIGHT)
            return new int[][]{{0, 0}, {1, 0}, {0, 1}};
        else if (type == EnumTracks.MEDIUM_DIAGONAL_STRAIGHT || type == EnumTracks.EMBEDDED_MEDIUM_DIAGONAL_STRAIGHT)
            return new int[][]{{0, 0}, {1, 0}, {0, 1}, {1, 1}, {2, 1}, {1, 2}, {2, 2}, {3, 2}, {2, 3}};
        else if (type == EnumTracks.LONG_DIAGONAL_STRAIGHT || type == EnumTracks.EMBEDDED_LONG_DIAGONAL_STRAIGHT)
            return new int[][]{{0, 0}, {1, 0}, {0, 1}, {1, 1}, {2, 1}, {1, 2}, {2, 2}, {3, 2}, {2, 3}, {3, 3}, {4, 3}, {3, 4}, {4, 4}, {4, 5}, {5, 4}, {5, 5}, {6, 5}, {5, 6}};
        else if (type == EnumTracks.VERY_LONG_DIAGONAL_STRAIGHT || type == EnumTracks.EMBEDDED_VERY_LONG_DIAGONAL_STRAIGHT)
            return new int[][]{{0, 0}, {1, 0}, {0, 1}, {1, 1}, {2, 1}, {1, 2}, {2, 2}, {3, 2}, {2, 3}, {3, 3}, {4, 3}, {3, 4}, {4, 4}, {4, 5}, {5, 4}, {5, 5}, {6, 5}, {5, 6},
                    {6, 6}, {6, 7}, {7, 6}, {7, 7}, {8, 7}, {7, 8}, {8, 8}, {9, 8}, {8, 9}, {9, 9}, {10, 9}, {9, 10}, {10, 10}, {11, 10}, {10, 11}, {11, 11}, {12, 11}, {11, 12}};
        /** Slopes */
        else if (type == EnumTracks.SLOPE_BALLAST
                || type == EnumTracks.SLOPE_GRAVEL
                || type == EnumTracks.SLOPE_WOOD
                || type == EnumTracks.SLOPE_SNOW_GRAVEL
                || type == EnumTracks.SLOPE_DYNAMIC
                || type == EnumTracks.EMBEDDED_SLOPE_DYNAMIC
        )
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}};

        else if (type == EnumTracks.LARGE_SLOPE_BALLAST
                || type == EnumTracks.LARGE_SLOPE_GRAVEL
                || type == EnumTracks.LARGE_SLOPE_WOOD
                || type == EnumTracks.LARGE_SLOPE_SNOW_GRAVEL
                || type == EnumTracks.LARGE_SLOPE_DYNAMIC
                || type == EnumTracks.EMBEDDED_LARGE_SLOPE_DYNAMIC)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {10, 0}, {11, 0}};
        else if (type == EnumTracks.VERY_LARGE_SLOPE_BALLAST
                || type == EnumTracks.VERY_LARGE_SLOPE_GRAVEL
                || type == EnumTracks.VERY_LARGE_SLOPE_WOOD
                || type == EnumTracks.VERY_LARGE_SLOPE_SNOW_GRAVEL
                || type == EnumTracks.VERY_LARGE_SLOPE_DYNAMIC
                || type == EnumTracks.EMBEDDED_VERY_LARGE_SLOPE_DYNAMIC
        )
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {10, 0}, {11, 0},
                    {12, 0}, {13, 0}, {14, 0}, {15, 0}, {16, 0}, {17, 0}};
        /** Crossing */
        else if (type == EnumTracks.TWO_WAYS_CROSSING || type == EnumTracks.EMBEDDED_TWO_WAYS_CROSSING)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {1, 1}, {1, -1}};
        else if (type == EnumTracks.DIAMOND_CROSSING || type == EnumTracks.EMBEDDED_DIAMOND_CROSSING)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {0, -1}, {2, 1}};
        else if (type == EnumTracks.DOUBLE_DIAMOND_CROSSING || type == EnumTracks.EMBEDDED_DOUBLE_DIAMOND_CROSSING)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {0, -1}, {2, -1}, {0, 1}, {2, 1}};
        else if (type == EnumTracks.DIAGONAL_TWO_WAYS_CROSSING || type == EnumTracks.EMBEDDED_DIAGONAL_TWO_WAYS_CROSSING)
            return new int[][]{{0, 0}, {-1, -1}, {1, -1}, {1, 1}, {-1, 1}};
        else if (type == EnumTracks.FOUR_WAYS_CROSSING || type == EnumTracks.EMBEDDED_FOUR_WAYS_CROSSING)
            return new int[][]{{0, 0}, {1, 0}, {-1, 0}, {0, -1}, {1, -1}, {-1, -1}, {0, 1}, {1, 1}, {-1, 1}};
        /** Turns */
        else if (type == EnumTracks.MEDIUM_TURN || type == EnumTracks.EMBEDDED_MEDIUM_TURN)
            return new int[][]{{0, 0}, {1, 0}, {1, 1}, {2, 1}, {2, 2}};
        else if (type == EnumTracks.LARGE_TURN || type == EnumTracks.EMBEDDED_LARGE_TURN)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {1, 1}, {2, 1}, {3, 1}, {2, 2}, {3, 2}, {4, 2}, {3, 3}, {4, 3}, {4, 4}};
        else if (type == EnumTracks.VERY_LARGE_TURN || type == EnumTracks.EMBEDDED_VERY_LARGE_TURN)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {4, 2}, {5, 2}, {6, 2},
                    {6, 3}, {7, 3}, {7, 4}, {8, 4}, {7, 5}, {8, 5}, {9, 5}, {8, 6}, {9, 6}, {8, 7}, {9, 7}, {9, 8}, {9, 9}};
        else if (type == EnumTracks.SUPER_LARGE_TURN || type == EnumTracks.EMBEDDED_SUPER_LARGE_TURN)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {5, 2}, {6, 2}, {7, 2}, {8, 2},
                    {7, 3}, {8, 3}, {9, 3}, {10, 3}, {9, 4}, {10, 4}, {11, 4}, {10, 5}, {11, 5}, {12, 5}, {11, 6}, {12, 6}, {13, 6}, {12, 7}, {13, 7},
                    {12, 8}, {13, 8}, {14, 8}, {13, 9}, {14, 9}, {13, 10}, {14, 10}, {15, 10}, {14, 11}, {15, 11}, {14, 12}, {15, 12}, {14, 12,}, {15, 13}, {15, 14}, {15, 15}};
        else if (type == EnumTracks.TURN_29X29 || type == EnumTracks.EMBEDDED_TURN_29X29)
            return new int[][]{{0, 0}, {1, 0},{2, 0},{3, 0},{4, 0},{5, 0},{6, 0},{7, 0},{3, 1},{4, 1},{5, 1},{6, 1},{7, 1},{8, 1},{9, 1},{10, 1},{7,2},{8, 2},{9, 2},{10, 2},{11, 2},{12, 2},{10,3},{11, 3},{12, 3},{13, 3},{14, 3},{12,4},{13, 4},{14, 4},{15, 4},{14, 5},{15, 5},{16, 5},{17, 5},{16, 6},{17, 6},{18, 6},{17, 7},{18, 7},{19, 7},{18, 8},{19, 8},{20, 8},{19, 9},{20, 9},{21, 9},{20, 10},{21, 10},{22, 10},{21, 11},{22, 11},{23, 11},{22, 12},{23, 12},{23, 13},{24, 13},{23, 14},{24, 14},{25, 14},{24, 15},{25, 15},{24, 16},{25, 16},{26, 16},{25, 17},{26, 17},{25, 18},{26, 18},{27, 18},{26, 19},{27, 19},{26, 20},{27, 20},{26, 21},{27, 21},{28, 21},{27, 22},{28, 22},{27, 23},{28, 23},{27, 24},{28, 24},{27, 25},{28, 25},{28, 26},{28, 27},{28, 28}};




        else if (type == EnumTracks.TURN_32X32 || type == EnumTracks.EMBEDDED_TURN_32X32)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1}, {9, 1},
                    {10, 1}, {7,2}, {8, 2}, {9, 2}, {10, 2}, {11, 2}, {12, 2}, {10,3}, {11, 3}, {12, 3}, {13, 3}, {14, 3}, {15, 3}, {13, 4}, {14, 4},
                    {15, 4}, {16, 4}, {14,5}, {15, 5}, {16, 5}, {17, 5}, {18, 5}, {16, 6}, {17, 6}, {18, 6}, {19, 6}, {18, 7}, {19, 7}, {20, 7}, {19, 8}, {20, 8},
                    {21, 8}, {20, 9}, {21, 9}, {22, 9}, {21, 10}, {22, 10}, {23, 10}, {22, 11}, {23, 11}, {24, 11}, {23, 12}, {24, 12}, {25, 12}, {24, 13}, {25, 13},
                    {26, 13}, {25, 14}, {26, 14}, {25, 15}, {26, 15}, {27, 15}, {26, 16}, {27, 16}, {28, 16}, {26,17}, {27, 17}, {28, 17}, {27, 18}, {28, 18}, {28, 19},
                    {29, 19}, {28, 20}, {29, 20}, {28, 21}, {29, 21}, {30, 21}, {29, 22}, {30, 22}, {29, 23}, {30, 23}, {29,24}, {30, 24}, {31, 24}, {30, 25}, {31, 25}, {30, 26},
                    {31, 26}, {30, 27}, {31, 27}, {30, 28}, {31, 28}, {30, 29}, {31, 29}, {31, 30}, {31, 31}};



        /** Switches*/
        else if (type == EnumTracks.MEDIUM_PARALLEL_SWITCH || type == EnumTracks.EMBEDDED_MEDIUM_PARALLEL_SWITCH)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {10, 0},
                    {2, 1}, {3, 1}, {4, 1}, {5, 1}, {4, 2}, {5, 2}, {6, 2}, {7, 2}, {8, 2}, {6, 3}, {7, 3}, {8, 3}, {9, 3}, {10, 3}};
        else if (type == EnumTracks.LARGE_PARALLEL_SWITCH || type == EnumTracks.EMBEDDED_LARGE_PARALLEL_SWITCH)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}, {8, 0}, {9, 0}, {10, 0},{11, 0},{12, 0},{13, 0},{14, 0},{15, 0},{16, 0}, {14, 3},{15, 3},{16, 3},{3, 1}, {4, 1}, {5, 1}, {6, 1}, {6, 2}, {7, 1}, {7, 2}, {8, 2}, {9, 2}, {10, 2}, {11, 2}, {9, 3}, {10, 3}, {11, 3}, {12, 3}, {13, 3}};
        else if (type == EnumTracks.MEDIUM_SWITCH || type == EnumTracks.EMBEDDED_MEDIUM_SWITCH)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {2, 1}, {3, 1}, {3, 2}, {3, 3}};
        else if (type == EnumTracks.LARGE_SWITCH || type == EnumTracks.EMBEDDED_LARGE_SWITCH)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0},
                    {2, 1}, {3, 1}, {4, 1}, {3, 2}, {4, 2}, {5, 2}, {4, 3}, {5, 3}, {5, 4}, {5, 5}};
        else if (type == EnumTracks.VERY_LARGE_SWITCH || type == EnumTracks.EMBEDDED_VERY_LARGE_SWITCH)
            return new int[][]{{0,0},{1,0}, {2,0},{3,0},{4,0},{5,0},{6,0}, {2, 1},{3, 1},{4, 1},{5, 1},{6, 1},{6, 2},{6, 2},{7, 2},{7, 3},{8, 3},{8, 4},{9, 4},{8, 5},{9, 5},{10, 5},{9, 6},{10, 6},{9, 7},{10, 7},{10, 8},{10, 9}, {10,10}};

        /** Parallel Curves */
        else if (type == EnumTracks.SMALL_PARALLEL_CURVE || type == EnumTracks.EMBEDDED_SMALL_PARALLEL_CURVE)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}};
        else if (type == EnumTracks.MEDIUM_PARALLEL_CURVE || type == EnumTracks.EMBEDDED_MEDIUM_PARALLEL_CURVE)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1}, {7, 2}, {8, 2}, {9, 2}, {10, 2}, {11, 2}};
        else if (type == EnumTracks.LARGE_PARALLEL_CURVE || type == EnumTracks.EMBEDDED_LARGE_PARALLEL_CURVE)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {8, 1}, {7, 2}, {8, 2}, {9, 2}, {10, 2}, {11, 2}, {10, 3}, {11, 3}, {12, 3}, {13, 3}, {14, 3}, {15, 3}};
        else if (type == EnumTracks.S_CURVE_20x2 || type == EnumTracks.EMBEDDED_S_CURVE_20x2) {
            return new int[][]{{0,0},{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0},{8,0},{9,0},{10,0},{11,0},{12,0},{13,0},{14,0},{15,0},{16,0},{3,1},{4,1},{5,1},{6,1},{7,1},{8,1},{9,1},{10,1},{11,1},{12,1},{13,1},{14,1},{15,1},{16,1},{17,1},{18,1},{19,1}};
        }
        /**45 degree curves*/

        else if ((type == EnumTracks.MEDIUM_45DEGREE_SWITCH || type == EnumTracks.EMBEDDED_MEDIUM_45DEGREE_SWITCH) && !player.isSneaking())
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {2, 1}, {3, 1}, {4, 1}, {3, 2}};
        else if ((type == EnumTracks.LARGE_45DEGREE_SWITCH || type == EnumTracks.EMBEDDED_LARGE_45DEGREE_SWITCH) && !player.isSneaking())
            return new int[][]{{0,0},{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{3, 1}, {4, 1}, {5, 1}, {6, 1}, {5, 2}, {6, 2}, {7, 2}, {6, 3},};
        else if ((type == EnumTracks.MEDIUM_45DEGREE_TURN || type == EnumTracks.EMBEDDED_MEDIUM_45DEGREE_TURN))
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {1, 1}, {2, 1}};
        else if ((type == EnumTracks.LARGE_45DEGREE_TURN || type == EnumTracks.EMBEDDED_LARGE_45DEGREE_TURN) )
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {4, 2}, {5, 2}};
        else if ((type == EnumTracks.VERY_LARGE_45DEGREE_TURN || type == EnumTracks.EMBEDDED_VERY_LARGE_45DEGREE_TURN) )
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {4, 2}, {5, 2}, {6, 2}, {7, 2}, {6, 3}, {7, 3}};
        else if ((type == EnumTracks.SUPER_LARGE_45DEGREE_TURN || type == EnumTracks.EMBEDDED_SUPER_LARGE_45DEGREE_TURN) )
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {1, 1}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {5, 2}, {6, 2}, {7, 2}, {8, 2}, {9, 2}, {7, 3}, {8, 3}, {9, 3}, {10, 3}, {9, 4}, {10, 4}};
        else if ((type == EnumTracks.MEDIUM_45DEGREE_SWITCH || type == EnumTracks.EMBEDDED_MEDIUM_45DEGREE_SWITCH) && player.isSneaking())
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {2, 1}, {3, 1}};
        else if ((type == EnumTracks.LARGE_45DEGREE_SWITCH || type == EnumTracks.EMBEDDED_LARGE_45DEGREE_SWITCH) && player.isSneaking())
            return new int[][]{{0,0},{1,0},{2,0},{3,0},{4,0},{5,0},{6,0}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {5, 2}, {6, 2}};
        else if (type == EnumTracks.LARGE_CURVED_SLOPE_DYNAMIC || type == EnumTracks.EMBEDDED_LARGE_CURVED_SLOPE_DYNAMIC)
            return new int[][]{{0, 0}, {1, 0}, {1, 1}, {2, 0}, {2, 1}, {3, 1}, {2, 2}, {3, 2}, {3, 3}, {4, 3}, {4, 4}};
        else if (type == EnumTracks.VERY_LARGE_CURVED_SLOPE_DYNAMIC || type == EnumTracks.EMBEDDED_VERY_LARGE_CURVED_SLOPE_DYNAMIC)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {4, 2}, {5, 2}, {6, 2}, {5, 3}, {6, 3}, {7, 3}, {6, 4}, {7, 4}, {7, 5}, {8, 5}, {7, 6}, {8, 7}, {8, 8}};
        else if (type == EnumTracks.SUPER_LARGE_CURVED_SLOPE_DYNAMIC || type == EnumTracks.EMBEDDED_SUPER_LARGE_CURVED_SLOPE_DYNAMIC)
            return new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {2, 1}, {3, 1}, {4, 1}, {5, 1}, {6, 1}, {7, 1}, {5, 2}, {6, 2}, {7, 2}, {8, 2}, {9, 2}, {8, 3}, {9, 3}, {10, 3}, {9, 4}, {10, 4}, {11, 4}, {10, 5}, {11, 5}, {12, 5}, {11, 6}, {12, 6}, {13, 6}, {12, 7}, {13, 7}, {12, 8}, {13, 8}, {14, 8}, {13, 9}, {14, 9}, {13, 10}, {14, 10}, {15, 10}, {14, 11}, {15, 11}, {14, 12}, {15, 12}, {14, 13}, {15, 13}, {15, 14}, {15, 15}};

        else {
            return null;
        }

    }

    public boolean tryToPlaceTrack(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, boolean changeWorld) {
        if (!(itemStack.getItem() instanceof ItemTCRail))
            return false;

        y = getPlacementHeight(world, x, y, z);

        ItemTCRail item = (ItemTCRail) itemStack.getItem();
        if (world.getBlock(x, y, z) == TCBlocks.bridgePillar && item.getTrackType().getLabel().contains("DYNAMIC")) {
            return false;
        }
        int facing0 = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        Vector2f dir0 = ItemTCRail.getDirectionVector(facing0);

        float yaw = MathHelper.wrapAngleTo180_float(player.rotationYaw);
        boolean isLeftTurn = getTrackOrientation(facing0, yaw).equals("left");
        int facing1 = isLeftTurn ? (facing0 + 4 - 1) % 4 : (facing0 + 1) % 4;
        Vector2f dir1 = getDirectionVector(facing1);

        int[][] trackPositions = getUsedSpaceFromType(item.getTrackType(), player);

        if (trackPositions != null) {

            for (int[] pos : trackPositions) {
                int dx = (int) (pos[0] * dir0.getX() + pos[1] * dir1.getX());
                int dz = (int) (pos[0] * dir0.getY() + pos[1] * dir1.getY());

                if (!canPlaceTrack(player, world, x + dx, y + 1, z + dz))
                    return false;
            }

            return true;
        } else {
            return false;
        }
    }

    public int getPlacementHeight(World world, int x, int y, int z) {
        if (canBeReplaced(world, x, y, z)) {
            y--;
        }
        return y;
    }

    /**
     * changes the sign of an array and adds the current world pos to it
     */
    public int[] flipArraySign(int[] array, int pos, boolean needsConverting) {
        if (needsConverting)
            for (int i = 0; i < array.length; i++) {
                array[i] = (array[i] * -1) + pos;
            }
        else {
            for (int i = 0; i < array.length; i++) {
                array[i] = (array[i] + pos);
            }
        }

        return array;
    }

    public int[] flipArraySign(int[] array) {
        for(int i=0;i<array.length;i++) {
            array[i] = (array[i] * -1);
        }
        return array;
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (!world.isRemote) {


            y = getPlacementHeight(world, x, y, z);
            int l;
            int[] curveXArray;
            int[] curveZArray;
            int[] curveXArray2;
            int[] curveZArray2;

            if (type.getRailType() == RailTypes.DIAGONAL) {
                l = MathHelper.floor_double((player != null ? player.rotationYaw : par10) * 4.0F / 360.0F) & 3;
                l += 4;
            } else {
                l = MathHelper.floor_double((player != null ? player.rotationYaw : par10) * 4.0F / 360.0F + 0.5D) & 3;
            }


            tempType = getPlacementDirection(player, world, l, par10);

            /**Maybe new thing if   MathHelper.floor_double((player != null ? player.rotationYaw : par10) * 8.0F / 360.0F + 0.5D) & 7; is used
             fun thing is, diagonals are uneven, straights are even, maybe do something with that?
            * l = direction
            * l = 0 = south
            *  l = 1 = south-west
            *  l = 2 = west
            *  l = 3 = north-west
            *  l = 4 = north
            *  l = 5 = north-east
            *  l = 6 = ea st
            *  l = 7 = south-east
             */

            /** This code below actually places the stuff
             * l = direction
             *  l = 0 = south
             *  l = 1 = west
             *  l = 2 = north
             *  l = 3 = east
             *  l = 4 = south-west
             *  l = 5 = north-west
             *  l = 6 = north-east
             *  l = 7 = south-east
             **/
            switch (tempType) {
                case SMALL_STRAIGHT:
                case EMBEDDED_SMALL_STRAIGHT:
                case SMALL_ROAD_CROSSING:
                case SMALL_ROAD_CROSSING_1:
                case SMALL_ROAD_CROSSING_2:
                case SMALL_ROAD_CROSSING_DYNAMIC:
                    if (!smallStraight(player,world,x,y,z,l,type)) {return false;}
                    break;

                case MEDIUM_STRAIGHT:
                case LONG_STRAIGHT:
                case VERY_LONG_STRAIGHT:
                case EMBEDDED_MEDIUM_STRAIGHT:
                case EMBEDDED_LONG_STRAIGHT:
                case EMBEDDED_VERY_LONG_STRAIGHT:
                    if (!straight(player, world, x, y, z, l, type)) {return false;}
                    break;

                case SMALL_DIAGONAL_STRAIGHT:
                case EMBEDDED_SMALL_DIAGONAL_STRAIGHT:
                    if (!smallDiagonalStraight(player, world, x, y, z, l, type)) {return false;}
                    break;

                case MEDIUM_DIAGONAL_STRAIGHT:
                case LONG_DIAGONAL_STRAIGHT:
                case VERY_LONG_DIAGONAL_STRAIGHT:
                case EMBEDDED_MEDIUM_DIAGONAL_STRAIGHT:
                case EMBEDDED_LONG_DIAGONAL_STRAIGHT: case EMBEDDED_VERY_LONG_DIAGONAL_STRAIGHT:
                    if (!diagonalStraight(player, world, x, y, z, l, type)) {return false;}
                    break;

                case LEFT_TURN_1X1:
                case RIGHT_TURN_1X1:
                case EMBEDDED_LEFT_TURN_1X1:
                case EMBEDDED_RIGHT_TURN_1X1:
                    curveXArray = new int[] {0};
                    curveZArray = new int[] {0};
                    if (!turnTrack(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, 0.5f)) {return false;}
                    break;

                case MEDIUM_LEFT_TURN:
                case MEDIUM_RIGHT_TURN:
                case EMBEDDED_MEDIUM_LEFT_TURN:
                case EMBEDDED_MEDIUM_RIGHT_TURN:
                    curveXArray = new int[] {0, 0, 1, 1, 2};
                    curveZArray = new int[] {0, 1, 1, 2, 2};
                    if (!turnTrack(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, 2.5f)) {return false;}
                    break;

                case LARGE_LEFT_TURN:
                case LARGE_RIGHT_TURN:
                case EMBEDDED_LARGE_LEFT_TURN:
                case EMBEDDED_LARGE_RIGHT_TURN:
                    curveXArray = new int[] {0, 0, 1, 1, 2, 0, 1, 2, 3, 4, 3, 2};
                    curveZArray = new int[] {0, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4};
                    if (!turnTrack(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, 4.5f)) {return false;}
                    break;

                case VERY_LARGE_LEFT_TURN:
                case VERY_LARGE_RIGHT_TURN:
                case EMBEDDED_VERY_LARGE_LEFT_TURN:
                case EMBEDDED_VERY_LARGE_RIGHT_TURN:
                    curveXArray = new int[] {0, 0, 0, 1, 0, 1, 0, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 5, 6, 6, 7, 7, 8, 9};
                    curveZArray = new int[] {0, 1, 2, 2, 3, 3, 4, 4, 5, 4, 5, 6, 6, 7, 7, 8, 7, 8, 9, 8, 9, 8, 9, 9, 9};
                    if (!turnTrack(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, 9.5f)) {return false;}
                    break;

                case SUPER_LARGE_LEFT_TURN:
                case SUPER_LARGE_RIGHT_TURN:
                case EMBEDDED_SUPER_LARGE_LEFT_TURN:
                case EMBEDDED_SUPER_LARGE_RIGHT_TURN:
                    curveXArray = new int[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 9, 9, 10, 11, 11, 12, 12, 13, 14, 15};
                    curveZArray = new int[] {0, 1, 2, 3, 4, 3, 4, 5, 6, 6, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 13, 14, 14, 14, 15, 14, 15, 15, 15, 15};
                    if (!turnTrack(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, 15.5f)) {return false;}
                    break;

                case LEFT_TURN_29X29:
                case RIGHT_TURN_29X29:
                case EMBEDDED_LEFT_TURN_29X29:
                case EMBEDDED_RIGHT_TURN_29X29: //#!#

                    curveXArray = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 10, 10, 11, 11, 11, 12, 12, 13, 13, 14, 14, 14, 15, 15, 16, 16, 16, 17, 17, 18, 18, 18, 19, 19, 20, 20, 21, 21, 21, 22, 22, 23, 23, 24, 24, 25, 25, 26, 27, 28};
                    curveZArray = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 3, 4, 5, 6, 7, 8, 9, 10, 7, 8, 9, 10, 11, 12, 10, 11, 12, 13, 14, 12, 13, 14, 15, 14, 15, 16, 17, 16, 17, 18, 17, 18, 19, 18, 19, 20, 19, 20, 21, 20, 21, 22, 21, 22, 23, 22, 23, 23, 24, 23, 24, 25, 24, 25, 24, 25, 26, 25, 26, 25, 26, 27, 26, 27, 26, 27, 26, 27, 28, 27, 28, 27, 28, 27, 28, 27, 28, 28, 28, 28};

                    if (!turnTrack(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, 28.5f)) {return false;}
                    break;

                case LEFT_TURN_32X32:
                case RIGHT_TURN_32X32:
                case EMBEDDED_LEFT_TURN_32X32:
                case EMBEDDED_RIGHT_TURN_32X32:
                    curveXArray = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 10, 10, 10, 11, 11, 11, 12, 12, 12, 13, 13, 13, 14, 14, 15, 15, 15, 16, 16, 16, 17, 17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 21, 22, 22, 23, 23, 24, 24, 24, 25, 25, 26, 26, 27, 27, 28, 28, 29, 29, 30, 31};
                    curveZArray = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 2, 3, 4, 5, 6, 7, 8, 9, 10, 7, 8, 9, 10, 11, 12, 10, 11, 12, 13, 14, 15, 13, 14, 15, 16, 14, 15, 16, 17, 18, 16, 17, 18, 19, 18, 19, 20, 19, 20, 21, 20, 21, 22, 21, 22, 23, 22, 23, 24, 23, 24, 25, 24, 25, 26, 25, 26, 25, 26, 27, 26, 27, 28, 26, 27, 28, 27, 28, 28, 29, 28, 29, 28, 29, 30, 29, 30, 29, 30, 29, 30, 31, 30, 31, 30, 31, 30, 31, 30, 31, 30, 31, 31, 31};

                    if (!turnTrack(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, 31.5f)) {return false;}
                    break;

                case MEDIUM_LEFT_45DEGREE_TURN:
                case MEDIUM_RIGHT_45DEGREE_TURN:
                case EMBEDDED_MEDIUM_LEFT_45DEGREE_TURN:
                case EMBEDDED_MEDIUM_RIGHT_45DEGREE_TURN:
                    curveXArray = new int[] {0, 0, 0, 1, 1};
                    curveZArray = new int[] {0, 1, 2, 1, 2};
                    if (!turnTrack(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, 3.75f)) {return false;}
                    break;

                case LARGE_LEFT_45DEGREE_TURN:
                case LARGE_RIGHT_45DEGREE_TURN:
                case EMBEDDED_LARGE_LEFT_45DEGREE_TURN:
                case EMBEDDED_LARGE_RIGHT_45DEGREE_TURN:
                    curveXArray = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2};
                    curveZArray = new int[]{0, 1, 2, 3, 1, 2, 3, 4, 5, 4, 5};

                    if (!turnTrack(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, 8.45f)) {return false;}
                    break;

                case VERY_LARGE_LEFT_45DEGREE_TURN:
                case VERY_LARGE_RIGHT_45DEGREE_TURN:
                case EMBEDDED_VERY_LARGE_LEFT_45DEGREE_TURN:
                case EMBEDDED_VERY_LARGE_RIGHT_45DEGREE_TURN:
                    curveXArray = new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3};
                    curveZArray = new int[]{0, 1, 2, 3, 4, 1, 2, 3, 4, 5, 6, 4, 5, 6, 7, 6, 7};

                    if (!turnTrack(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, 10.89f)) {return false;}
                    break;

                case SUPER_LARGE_LEFT_45DEGREE_TURN:
                case SUPER_LARGE_RIGHT_45DEGREE_TURN:
                case EMBEDDED_SUPER_LARGE_LEFT_45DEGREE_TURN:
                case EMBEDDED_SUPER_LARGE_RIGHT_45DEGREE_TURN:
                    curveXArray = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4};
                    curveZArray = new int[]{0, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6, 7, 5, 6, 7, 8, 9, 7, 8, 9, 10, 9, 10};

                    if (!turnTrack(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, 15.69f)) {return false;}
                    break;

                case LARGE_RIGHT_CURVED_SLOPE_DYNAMIC:
                case EMBEDDED_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC:
                    if (!largeRightCurvedSlope(player, world, x, y, z, l, tempType)) {return false;}
                    break;

                case LARGE_LEFT_CURVED_SLOPE_DYNAMIC:
                case EMBEDDED_LARGE_LEFT_CURVED_SLOPE_DYNAMIC:
                    if (!largeLeftCurvedSlope(player, world, x, y, z, l, tempType)) {return false;}
                    break;

                case VERY_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC:
                case EMBEDDED_VERY_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC:
                    if (!veryLargeRightCurvedSlope(player, world, x, y, z, l, tempType)) {return false;}
                    break;

                case VERY_LARGE_LEFT_CURVED_SLOPE_DYNAMIC:
                case EMBEDDED_VERY_LARGE_LEFT_CURVED_SLOPE_DYNAMIC:
                    if (!veryLargeLeftCurvedSlope(player, world, x, y, z, l, tempType)) {return false;}
                    break;

                case SUPER_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC:
                case EMBEDDED_SUPER_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC:
                    if (!superLargeRightCurvedSlope(player, world, x, y, z, l, tempType)) {return false;}
                    break;

                case SUPER_LARGE_LEFT_CURVED_SLOPE_DYNAMIC:
                case EMBEDDED_SUPER_LARGE_LEFT_CURVED_SLOPE_DYNAMIC:
                    if (!superLargeLeftCurvedSlope(player, world, x, y, z, l, tempType)) {return false;}
                    break;

                case RIGHT_DIAMOND_CROSSING:
                case EMBEDDED_RIGHT_DIAMOND_CROSSING:
                    if (!rightDiamondCrossing(player, world, x, y, z, l, tempType)) {return false;}
                    break;

                case LEFT_DIAMOND_CROSSING:
                case EMBEDDED_LEFT_DIAMOND_CROSSING:
                    if (!leftDiamondCrossing(player, world, x, y, z, l, tempType)) {return false;}
                    break;

                case DOUBLE_DIAMOND_CROSSING:
                case EMBEDDED_DOUBLE_DIAMOND_CROSSING:
                    if (!doubleDiamondCrossing(player, world, x, y, z, l, type)) {return false;}
                    break;

                case DIAGONAL_TWO_WAYS_CROSSING:
                case EMBEDDED_DIAGONAL_TWO_WAYS_CROSSING:
                    if (!diagonalTwoWaysCrossing(player, world, x, y, z, l, type)) {return false;}
                    break;

                case FOUR_WAYS_CROSSING:
                case EMBEDDED_FOUR_WAYS_CROSSING:
                    if (!fourWaysCrossing(player, world, x, y, z, l, type)) {return false;}
                    break;

                case SMALL_RIGHT_PARALLEL_CURVE:
                case SMALL_LEFT_PARALLEL_CURVE:
                case EMBEDDED_SMALL_RIGHT_PARALLEL_CURVE:
                case EMBEDDED_SMALL_LEFT_PARALLEL_CURVE:

                    curveXArray = new int[]{0, 0, 0, 0, 0, 0};
                    curveZArray = new int[]{0, 1, 2, 3, 4, 5};
                    curveXArray2 = new int[]{1, 1, 1, 1, 1, 1};
                    curveZArray2 = new int[]{2, 3, 4, 5, 6, 7};
                    if (!SCurve(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, curveXArray2, curveZArray2, 16.25f, 8, 2)) {return false;}
                    break;

                case MEDIUM_RIGHT_PARALLEL_CURVE:
                case MEDIUM_LEFT_PARALLEL_CURVE:
                case EMBEDDED_MEDIUM_RIGHT_PARALLEL_CURVE:
                case EMBEDDED_MEDIUM_LEFT_PARALLEL_CURVE:

                    curveXArray = new int[]{0, 0, 0, 0, 0, 1, 1, 1};
                    curveZArray = new int[]{0, 1, 2, 3, 4, 3, 4, 5};
                    curveXArray2 = new int[]{1, 1, 1, 2, 2, 2, 2, 2};
                    curveZArray2 = new int[]{6, 7, 8, 7, 8, 9, 10, 11};
                    if (!SCurve(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, curveXArray2, curveZArray2, 18.50f, 12, 3)) {return false;}
                    break;

                case LARGE_RIGHT_PARALLEL_CURVE:
                case LARGE_LEFT_PARALLEL_CURVE:
                case EMBEDDED_LARGE_RIGHT_PARALLEL_CURVE:
                case EMBEDDED_LARGE_LEFT_PARALLEL_CURVE:

                    curveXArray = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1};
                    curveZArray = new int[]{0, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8};
                    curveXArray2 = new int[]{2, 2, 2, 2, 2, 3, 3, 3, 3, 3,3};
                    curveZArray2 = new int[]{7, 8, 9, 10, 11, 10, 11, 12, 13, 14,15};
                    if (!SCurve(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, curveXArray2, curveZArray2, 22f, 16, 4)) {return false;}
                    break;

                case S_CURVE_20x2_LEFT:
                case S_CURVE_20x2_RIGHT:
                case EMBEDDED_S_CURVE_20x2_LEFT:
                case EMBEDDED_S_CURVE_20x2_RIGHT:
                    curveXArray = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1};
                    curveZArray = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 3, 4, 5, 6, 7, 8, 9};
                    curveXArray2 = new int[] {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
                    curveZArray2 = new int[] {10, 11, 12, 13, 14, 15, 16, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};

                    if (!SCurve(player, world, x, y, z, l, tempType, par10, curveXArray, curveZArray, curveXArray2, curveZArray2, 100.25f, 20, 2)) {return false;}
                    break;

                case MEDIUM_RIGHT_45DEGREE_SWITCH:
                case EMBEDDED_MEDIUM_RIGHT_45DEGREE_SWITCH:
                    typeVariantStraight = tempType.getLabel().contains("EMBEDDED") ? EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel() : EnumTracks.SMALL_STRAIGHT.getLabel();
                    typeVariant90Turn = tempType.getLabel().contains("EMBEDDED") ? EnumTracks.EMBEDDED_MEDIUM_RIGHT_TURN.getLabel() : EnumTracks.MEDIUM_RIGHT_TURN.getLabel();
                    if (!mediumRight45DegreeSwitch(player, world, x, y, z, l, tempType, typeVariantStraight, typeVariant90Turn)) {
                        return false;
                    }
                    break;

                case MEDIUM_LEFT_45DEGREE_SWITCH:
                case EMBEDDED_MEDIUM_LEFT_45DEGREE_SWITCH:
                    typeVariantStraight = tempType.getLabel().contains("EMBEDDED") ? EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel() : EnumTracks.SMALL_STRAIGHT.getLabel();
                    typeVariant90Turn = tempType.getLabel().contains("EMBEDDED") ? EnumTracks.EMBEDDED_LARGE_LEFT_TURN.getLabel() : EnumTracks.LARGE_LEFT_TURN.getLabel();
                    if (!mediumLeft45DegreeSwitch(player, world, x, y, z, l, tempType, typeVariantStraight, typeVariant90Turn)){return false;}
                    break;

                case LARGE_RIGHT_45DEGREE_SWITCH:
                case EMBEDDED_LARGE_RIGHT_45DEGREE_SWITCH:
                    typeVariantStraight = tempType.getLabel().contains("EMBEDDED") ? EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel() : EnumTracks.SMALL_STRAIGHT.getLabel();
                    typeVariant90Turn = tempType.getLabel().contains("EMBEDDED") ? EnumTracks.EMBEDDED_LARGE_RIGHT_TURN.getLabel() : EnumTracks.LARGE_RIGHT_TURN.getLabel();
                    if (!largeRight45DegreeSwitch(player, world, x, y, z, l, tempType, typeVariantStraight, typeVariant90Turn)){return false;}
                    break;

                case LARGE_LEFT_45DEGREE_SWITCH:
                case EMBEDDED_LARGE_LEFT_45DEGREE_SWITCH:
                    typeVariantStraight = tempType.getLabel().contains("EMBEDDED") ? EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel() : EnumTracks.SMALL_STRAIGHT.getLabel();
                    typeVariant90Turn = tempType.getLabel().contains("EMBEDDED") ? EnumTracks.EMBEDDED_LARGE_LEFT_TURN.getLabel() : EnumTracks.LARGE_LEFT_TURN.getLabel();
                    if (!largeLeft45DegreeSwitch(player, world, x, y, z, l, tempType, typeVariantStraight, typeVariant90Turn)){return false;}
                    break;


                case LARGE_LEFT_PARALLEL_SWITCH:
                case EMBEDDED_LARGE_LEFT_PARALLEL_SWITCH:
                    if (tempType == EnumTracks.LARGE_LEFT_PARALLEL_SWITCH) {
                        typeVariantStraight = EnumTracks.SMALL_STRAIGHT.getLabel();
                    } else {
                        typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
                    }
                    if (!largeLeftParallelSwitch(player, world, x, y, z, l, tempType, typeVariantStraight)){return false;}
                    break;

                case LARGE_RIGHT_PARALLEL_SWITCH:
                case EMBEDDED_LARGE_RIGHT_PARALLEL_SWITCH:
                    if (tempType == EnumTracks.LARGE_RIGHT_PARALLEL_SWITCH) {
                        typeVariantStraight = EnumTracks.SMALL_STRAIGHT.getLabel();
                    } else {
                        typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
                    }
                    if (!largeRightParallelSwitch(player, world, x, y, z, l, tempType, typeVariantStraight)){return false;}
                    break;

                case MEDIUM_LEFT_PARALLEL_SWITCH:
                case EMBEDDED_MEDIUM_LEFT_PARALLEL_SWITCH:
                    if(tempType == EnumTracks.MEDIUM_LEFT_PARALLEL_SWITCH) {
                        typeVariantStraight = EnumTracks.SMALL_STRAIGHT.getLabel();
                        idVariantSwitch = ItemIDs.tcRailMediumParallelSwitch.item;
                    }
                    else {
                            typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
                            idVariantSwitch = ItemIDs.tcRailEmbeddedMediumParallelSwitch.item;

                    }
                    if (l == 2) {
                        if (!parallelLeftSwitchNorth(player, world, x, y, z, l, tempType, typeVariantStraight, idVariantSwitch))
                            return false;
                    }
                    if (l == 0) {
                        if (!parallelLeftSwitchSouth(player, world, x, y, z, l, tempType, typeVariantStraight, idVariantSwitch))
                            return false;
                    }
                    if (l == 1) {
                        if (!parallelLeftSwitchWest(player, world, x, y, z, l, tempType, typeVariantStraight, idVariantSwitch))
                            return false;
                    }
                    if (l == 3) {
                        if (!parallelLeftSwitchEast(player, world, x, y, z, l, tempType, typeVariantStraight, idVariantSwitch))
                            return false;
                    }
                    break;

                case MEDIUM_RIGHT_PARALLEL_SWITCH:
                case EMBEDDED_MEDIUM_RIGHT_PARALLEL_SWITCH:
                    if(tempType == EnumTracks.MEDIUM_RIGHT_PARALLEL_SWITCH) {
                        typeVariantStraight = EnumTracks.SMALL_STRAIGHT.getLabel();
                        idVariantSwitch = ItemIDs.tcRailMediumParallelSwitch.item;
                    }
                    else {
                        typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
                        idVariantSwitch = ItemIDs.tcRailEmbeddedMediumParallelSwitch.item;
                    }
                    if (l == 2) {
                        if (!parallelRightSwitchNorth(player, world, x, y, z, l, tempType, typeVariantStraight, idVariantSwitch))
                            return false;
                    }
                    if (l == 0) {
                        if (!parallelRightSwitchSouth(player, world, x, y, z, l, tempType, typeVariantStraight, idVariantSwitch))
                            return false;
                    }
                    if (l == 1) {
                        if (!parallelRightSwitchWest(player, world, x, y, z, l, tempType, typeVariantStraight, idVariantSwitch))
                            return false;
                    }
                    if (l == 3) {
                        if (!parallelRightSwitchEast(player, world, x, y, z, l, tempType, typeVariantStraight, idVariantSwitch))
                            return false;
                    }
                    break;

                case MEDIUM_RIGHT_SWITCH:
                case EMBEDDED_MEDIUM_RIGHT_SWITCH: { //TODO: move to a method

                    if (tempType == EnumTracks.MEDIUM_RIGHT_SWITCH) {
                        idVariantSwitch = ItemIDs.tcRailMediumSwitch.item;
                        typeVariant90Turn = EnumTracks.MEDIUM_RIGHT_TURN.getLabel();
                        typeVariantStraight = EnumTracks.SMALL_STRAIGHT.getLabel();
                    } else {
                        idVariantSwitch = ItemIDs.tcRailEmbeddedMediumSwitch.item;
                        typeVariant90Turn = EnumTracks.EMBEDDED_MEDIUM_RIGHT_TURN.getLabel();
                        typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
                    }

                    if (l == 2) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x, y + 1, z - 3) || !canPlaceTrack(player, world, x, y + 1, z - 2) || !canPlaceTrack(player, world, x, y + 1, z - 1)) {
                            return false;
                        }

                        int[] xArray = {x + 1, x + 1, x + 2};
                        int[] zArray = {z - 2, z - 3, z - 3};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 1, x + 3, z - 3, 2.5, x + 3, y + 1,
                                z, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z - 2);
                        if (tcRailTurn != null) {
                            tcRailTurn.hasModel = false;
                        }
                        world.setBlockMetadataWithNotify(x + 1, y + 1, z - 2, l, 2);//to force client update
                        /** Switch rail 1 */
                        putDownSingleRail(world, x, y + 1, z - 1, l, x + 3, y + 1, z, 2.5, tempType.getLabel(), true, x + 1, y + 1, z - 2, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x, y + 1, z - 2, l, x + 3, y + 1, z, 2.5, typeVariantStraight, false, x + 1, y + 1, z - 2, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x, y + 1, z - 3, l, x, y + 1, z, 0, typeVariantStraight, true, x + 1, y + 1, z - 2, false, false);
                    }
                    if (l == 0) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x, y + 1, z + 3) || !canPlaceTrack(player, world, x, y + 1, z + 2) || !canPlaceTrack(player, world, x, y + 1, z + 1)) {
                            return false;
                        }

                        int[] xArray = {x - 1, x - 1, x - 2};
                        int[] zArray = {z + 2, z + 3, z + 3};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 1, x - 3, z + 3, 2.5, x - 2, y + 1,
                                z + 1, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z + 2);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x - 1, y + 1, z + 2, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x, y + 1, z + 1, l, x - 2, y + 1, z + 1, 2.5, tempType.getLabel(), true, x - 1, y + 1, z + 2, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x, y + 1, z + 2, l, x - 2, y + 1, z + 1, 2.5, typeVariantStraight, false, x - 1, y + 1, z + 2, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x, y + 1, z + 3, l, x, y + 1, z, 0, typeVariantStraight, true, x - 1, y + 1, z + 2, false, false);

                    }
                    if (l == 1) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x - 3, y + 1, z) || !canPlaceTrack(player, world, x - 2, y + 1, z) || !canPlaceTrack(player, world, x - 1, y + 1, z)) {
                            return false;
                        }

                        int[] xArray = {x - 2, x - 3, x - 3};
                        int[] zArray = {z - 1, z - 1, z - 2};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 2, x - 3, z - 3, 2.5, x, y + 1,
                                z - 2, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 2, y + 1, z - 1);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x - 2, y + 1, z - 1, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x - 1, y + 1, z, l, x, y + 1, z - 2, 2.5, tempType.getLabel(), true, x - 2, y + 1, z - 1, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x - 2, y + 1, z, l, x, y + 1, z - 2, 2.5, typeVariantStraight, false, x - 2, y + 1, z - 1, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x - 3, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, true, x - 2, y + 1, z - 1, false, false);

                    }
                    if (l == 3) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x + 3, y + 1, z) || !canPlaceTrack(player, world, x + 2, y + 1, z) || !canPlaceTrack(player, world, x + 1, y + 1, z)) {
                            return false;
                        }

                        int[] xArray = {x + 2, x + 3, x + 3};
                        int[] zArray = {z + 1, z + 1, z + 2};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 0, x + 3, z + 3, 2.5, x + 1, y + 1,
                                z + 3, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 2, y + 1, z + 1);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x + 2, y + 1, z + 1, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x + 1, y + 1, z, l, x + 1, y + 1, z + 3, 2.5, tempType.getLabel(), true, x + 2, y + 1, z + 1, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x + 2, y + 1, z, l, x + 1, y + 1, z + 3, 2.5, typeVariantStraight, false, x + 2, y + 1, z + 1, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x + 3, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, true, x + 2, y + 1, z + 1, false, false);

                    }
                    break;
                }

                case MEDIUM_LEFT_SWITCH:
                case EMBEDDED_MEDIUM_LEFT_SWITCH: {
                    if (tempType == EnumTracks.MEDIUM_LEFT_SWITCH) {
                        idVariantSwitch = ItemIDs.tcRailMediumSwitch.item;
                        typeVariant90Turn = EnumTracks.MEDIUM_LEFT_TURN.getLabel();
                        typeVariantStraight = EnumTracks.SMALL_STRAIGHT.getLabel();
                    }
                    else {
                        idVariantSwitch = ItemIDs.tcRailEmbeddedMediumSwitch.item;
                        typeVariant90Turn = EnumTracks.EMBEDDED_MEDIUM_LEFT_TURN.getLabel();
                        typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
                    }
                    if (l == 2) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x, y + 1, z - 3) || !canPlaceTrack(player, world, x, y + 1, z - 2) || !canPlaceTrack(player, world, x, y + 1, z - 1)) {
                            return false;
                        }

                        int[] xArray = {x - 1, x - 1, x - 2};
                        int[] zArray = {z - 2, z - 3, z - 3};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 1, x - 3, z - 3, 2.5, x - 2, y + 1,
                                z, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z - 2);
                        if (tcRailTurn != null) {
                            tcRailTurn.hasModel = false;
                        }
                        world.setBlockMetadataWithNotify(x - 1, y + 1, z - 2, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x, y + 1, z - 1, l, x - 2, y + 1, z, 2.5, tempType.getLabel(), true, x - 1, y + 1, z - 2, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x, y + 1, z - 2, l, x - 2, y + 1, z, 2.5, typeVariantStraight, false, x - 1, y + 1, z - 2, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x, y + 1, z - 3, l, x, y + 1, z, 0, typeVariantStraight, true, x - 1, y + 1, z - 2, false, false);

                    }
                    if (l == 0) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x, y + 1, z + 3) || !canPlaceTrack(player, world, x, y + 1, z + 2) || !canPlaceTrack(player, world, x, y + 1, z + 1)) {
                            return false;
                        }
                        int[] xArray = {x + 1, x + 1, x + 2};
                        int[] zArray = {z + 2, z + 3, z + 3};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 3, x + 3, z + 3, 2.5, x + 3, y + 1,
                                z + 1, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z + 2);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x + 1, y + 1, z + 2, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x, y + 1, z + 1, l, x + 3, y + 1, z + 1, 2.5, tempType.getLabel(), true, x + 1, y + 1, z + 2, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x, y + 1, z + 2, l, x + 3, y + 1, z + 1, 2.5, typeVariantStraight, false, x + 1, y + 1, z + 2, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x, y + 1, z + 3, l, x, y + 1, z, 0, typeVariantStraight, true, x + 1, y + 1, z + 2, false, false);

                    }
                    if (l == 1) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x - 3, y + 1, z) || !canPlaceTrack(player, world, x - 2, y + 1, z) || !canPlaceTrack(player, world, x - 1, y + 1, z)) {
                            return false;
                        }
                        int[] xArray = {x - 2, x - 3, x - 3};
                        int[] zArray = {z + 1, z + 1, z + 2};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 0, x - 3, z + 3, 2.5, x, y + 1,
                                z + 3, typeVariant90Turn, idVariantSwitch))
                            return false;

                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 2, y + 1, z + 1);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x - 2, y + 1, z + 1, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x - 1, y + 1, z, l, x, y + 1, z + 3, 2.5, tempType.getLabel(), true, x - 2, y + 1, z + 1, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x - 2, y + 1, z, l, x, y + 1, z + 3, 2.5, typeVariantStraight, false, x - 2, y + 1, z + 1, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x - 3, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, true, x - 2, y + 1, z + 1, false, false);

                    }
                    if (l == 3) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x + 3, y + 1, z) || !canPlaceTrack(player, world, x + 2, y + 1, z) || !canPlaceTrack(player, world, x + 1, y + 1, z)) {
                            return false;
                        }
                        int[] xArray = {x + 2, x + 3, x + 3};
                        int[] zArray = {z - 1, z - 1, z - 2};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 2, x + 3, z - 3, 2.5, x + 1, y + 1,
                                z - 2, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 2, y + 1, z - 1);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x + 2, y + 1, z - 1, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x + 1, y + 1, z, l, x + 1, y + 1, z - 2, 2.5, tempType.getLabel(), true, x + 2, y + 1, z - 1, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x + 2, y + 1, z, l, x + 1, y + 1, z - 2, 2.5, typeVariantStraight, false, x + 2, y + 1, z - 1, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x + 3, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, true, x + 2, y + 1, z - 1, false, false);

                    }
                    break;
                }

                case LARGE_RIGHT_SWITCH:
                case EMBEDDED_LARGE_RIGHT_SWITCH: {
                    if (tempType == EnumTracks.LARGE_RIGHT_SWITCH) {
                        idVariantSwitch = ItemIDs.tcRailLargeSwitch.item;
                        typeVariant90Turn = EnumTracks.LARGE_RIGHT_TURN.getLabel();
                        typeVariantStraight = EnumTracks.SMALL_STRAIGHT.getLabel();
                    }
                    else {
                        idVariantSwitch = ItemIDs.tcRailEmbeddedLargeSwitch.item;
                        typeVariant90Turn = EnumTracks.EMBEDDED_LARGE_RIGHT_TURN.getLabel();
                        typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
                    }
                    if (l == 2) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x, y + 1, z - 5) || !canPlaceTrack(player, world, x, y + 1, z - 4) || !canPlaceTrack(player, world, x, y + 1, z - 3) || !canPlaceTrack(player, world, x, y + 1, z - 2) || !canPlaceTrack(player, world, x, y + 1, z - 1)) {
                            return false;
                        }
                        int[] xArray = {x + 1, x + 1, x + 2, x + 1, x + 2, x + 3, x + 4, x + 3, x + 2};
                        int[] zArray = {z - 2, z - 3, z - 3, z - 4, z - 4, z - 4, z - 5, z - 5, z - 5};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 1, x + 5, z - 5, 4.5, x + 5, y + 1,
                                z, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z - 2);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x + 1, y + 1, z - 2, l, 2);//to force client update
                        /** Switch rail 1 */
                        putDownSingleRail(world, x, y + 1, z - 1, l, x + 5, y + 1, z, 4.5, tempType.getLabel(), true, x + 1, y + 1, z - 2, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x, y + 1, z - 2, l, x + 5, y + 1, z, 4.5, typeVariantStraight, false, x + 1, y + 1, z - 2, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x, y + 1, z - 3, l, x + 5, y + 1, z, 4.5, typeVariantStraight, false, x + 1, y + 1, z - 2, true, false);


                        /** Put down straight exit **/
                        putDownSingleRail(world, x, y + 1, z - 4, l, x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z - 2, false, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x, y + 1, z - 5, l, x, y + 1, z, 0, typeVariantStraight, true, x + 1, y + 1, z - 2, false, false);

                    }
                    if (l == 0) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x, y + 1, z + 5) || !canPlaceTrack(player, world, x, y + 1, z + 4) || !canPlaceTrack(player, world, x, y + 1, z + 3) || !canPlaceTrack(player, world, x, y + 1, z + 2) || !canPlaceTrack(player, world, x, y + 1, z + 1)) {
                            return false;
                        }

                        int[] xArray = {x - 1, x - 1, x - 2, x - 1, x - 2, x - 3, x - 2, x - 3, x - 4};
                        int[] zArray = {z + 2, z + 3, z + 3, z + 4, z + 4, z + 4, z + 5, z + 5, z + 5};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 1, x - 5, z + 5, 4.5, x - 4, y + 1,
                                z + 1, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z + 2);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x - 1, y + 1, z + 2, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x, y + 1, z + 1, l, x - 4, y + 1, z + 1, 4.5, tempType.getLabel(), true, x - 1, y + 1, z + 2, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x, y + 1, z + 2, l, x - 4, y + 1, z + 1, 4.5, typeVariantStraight, false, x - 1, y + 1, z + 2, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x, y + 1, z + 3, l, x - 4, y + 1, z + 1, 4.5, typeVariantStraight, false, x - 1, y + 1, z + 2, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x, y + 1, z + 4, l, x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z + 2, false, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x, y + 1, z + 5, l, x, y + 1, z, 0, typeVariantStraight, true, x - 1, y + 1, z + 2, false, false);

                    }
                    if (l == 1) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x - 5, y + 1, z) || !canPlaceTrack(player, world, x - 4, y + 1, z) || !canPlaceTrack(player, world, x - 3, y + 1, z) || !canPlaceTrack(player, world, x - 2, y + 1, z) || !canPlaceTrack(player, world, x - 1, y + 1, z)) {
                            return false;
                        }
                        int[] xArray = {x - 2, x - 3, x - 3, x - 4, x - 4, x - 4, x - 5, x - 5, x - 5};
                        int[] zArray = {z - 1, z - 1, z - 2, z - 1, z - 2, z - 3, z - 2, z - 3, z - 4};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 2, x - 5, z - 5, 4.5, x, y + 1,
                                z - 4, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 2, y + 1, z - 1);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x - 2, y + 1, z - 1, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x - 1, y + 1, z, l, x, y + 1, z - 4, 4.5, tempType.getLabel(), true, x - 2, y + 1, z - 1, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x - 2, y + 1, z, l, x, y + 1, z - 4, 4.5, typeVariantStraight, false, x - 2, y + 1, z - 1, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x - 3, y + 1, z, l, x, y + 1, z - 4, 4.5, typeVariantStraight, false, x - 2, y + 1, z - 1, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x - 4, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false, x - 2, y + 1, z - 1, false, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x - 5, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, true, x - 2, y + 1, z - 1, false, false);

                    }
                    if (l == 3) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x + 5, y + 1, z) || !canPlaceTrack(player, world, x + 4, y + 1, z) || !canPlaceTrack(player, world, x + 3, y + 1, z) || !canPlaceTrack(player, world, x + 2, y + 1, z) || !canPlaceTrack(player, world, x + 1, y + 1, z)) {
                            return false;
                        }
                        int[] xArray = {x + 2, x + 3, x + 3, x + 4, x + 4, x + 4, x + 5, x + 5, x + 5};
                        int[] zArray = {z + 1, z + 1, z + 2, z + 1, z + 2, z + 3, z + 2, z + 3, z + 4};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 0, x + 5, z + 5, 4.5, x + 1, y + 1,
                                z + 5, typeVariant90Turn, idVariantSwitch))
                            return false;

                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 2, y + 1, z + 1);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x + 2, y + 1, z + 1, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x + 1, y + 1, z, l, x + 1, y + 1, z + 5, 4.5, tempType.getLabel(), true, x + 2, y + 1, z + 1, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x + 2, y + 1, z, l, x + 1, y + 1, z + 5, 4.5, typeVariantStraight, false, x + 2, y + 1, z + 1, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x + 3, y + 1, z, l, x + 1, y + 1, z + 5, 4.5, typeVariantStraight, false, x + 2, y + 1, z + 1, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x + 4, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false, x + 2, y + 1, z + 1, false, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x + 5, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, true, x + 2, y + 1, z + 1, false, false);

                    }
                    break;
                }

                case LARGE_LEFT_SWITCH:
                case EMBEDDED_LARGE_LEFT_SWITCH:{
                    if (tempType == EnumTracks.LARGE_LEFT_SWITCH) {
                        idVariantSwitch = ItemIDs.tcRailLargeSwitch.item;
                        typeVariant90Turn = EnumTracks.LARGE_LEFT_TURN.getLabel();
                        typeVariantStraight = EnumTracks.SMALL_STRAIGHT.getLabel();
                    }
                    else {
                        idVariantSwitch = ItemIDs.tcRailEmbeddedLargeSwitch.item;
                        typeVariant90Turn = EnumTracks.EMBEDDED_LARGE_LEFT_TURN.getLabel();
                        typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
                    }
                    if (l == 2) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x, y + 1, z - 5) || !canPlaceTrack(player, world, x, y + 1, z - 4) || !canPlaceTrack(player, world, x, y + 1, z - 3) || !canPlaceTrack(player, world, x, y + 1, z - 2) || !canPlaceTrack(player, world, x, y + 1, z - 1)) {
                            return false;
                        }
                        int[] xArray = {x - 1, x - 1, x - 2, x - 1, x - 2, x - 3, x - 4, x - 3, x - 2};
                        int[] zArray = {z - 2, z - 3, z - 3, z - 4, z - 4, z - 4, z - 5, z - 5, z - 5};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 1, x - 5, z - 5, 4.5, x - 4, y + 1,
                                z, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z - 2);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x - 1, y + 1, z - 2, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x, y + 1, z - 1, l, x - 4, y + 1, z, 4.5, tempType.getLabel(), true, x - 1, y + 1, z - 2, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x, y + 1, z - 2, l, x - 4, y + 1, z, 4.5, typeVariantStraight, false, x - 1, y + 1, z - 2, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x, y + 1, z - 3, l, x - 4, y + 1, z, 4.5, typeVariantStraight, false, x - 1, y + 1, z - 2, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x, y + 1, z - 4, l, x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z - 2, false, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x, y + 1, z - 5, l, x, y + 1, z, 0, typeVariantStraight, true, x - 1, y + 1, z - 2, false, false);

                    }
                    if (l == 0) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x, y + 1, z + 5) || !canPlaceTrack(player, world, x, y + 1, z + 4) || !canPlaceTrack(player, world, x, y + 1, z + 3) || !canPlaceTrack(player, world, x, y + 1, z + 2) || !canPlaceTrack(player, world, x, y + 1, z + 1)) {
                            return false;
                        }
                        int[] xArray = {x + 1, x + 1, x + 2, x + 1, x + 2, x + 3, x + 2, x + 3, x + 4};
                        int[] zArray = {z + 2, z + 3, z + 3, z + 4, z + 4, z + 4, z + 5, z + 5, z + 5};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 1, x + 5, z + 5, 4.5, x + 5, y + 1,
                                z + 1, typeVariant90Turn, idVariantSwitch))
                            return false;

                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z + 2);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x + 1, y + 1, z + 2, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x, y + 1, z + 1, l, x + 5, y + 1, z + 1, 4.5, tempType.getLabel(), true, x + 1, y + 1, z + 2, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x, y + 1, z + 2, l, x + 5, y + 1, z + 1, 4.5, typeVariantStraight, false, x + 1, y + 1, z + 2, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x, y + 1, z + 3, l, x + 5, y + 1, z + 1, 4.5, typeVariantStraight, false, x + 1, y + 1, z + 2, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x, y + 1, z + 4, l, x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z + 2, false, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x, y + 1, z + 5, l, x, y + 1, z, 0, typeVariantStraight, true, x + 1, y + 1, z + 2, false, false);

                    }
                    if (l == 1) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x - 5, y + 1, z) || !canPlaceTrack(player, world, x - 4, y + 1, z) || !canPlaceTrack(player, world, x - 3, y + 1, z) || !canPlaceTrack(player, world, x - 2, y + 1, z) || !canPlaceTrack(player, world, x - 1, y + 1, z)) {
                            return false;
                        }
                        int[] xArray = {x - 2, x - 3, x - 3, x - 4, x - 4, x - 4, x - 5, x - 5, x - 5};
                        int[] zArray = {z + 1, z + 1, z + 2, z + 1, z + 2, z + 3, z + 2, z + 3, z + 4};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 2, x - 5, z + 5, 4.5, x, y + 1,
                                z + 5, typeVariant90Turn, idVariantSwitch))
                            return false;

                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 2, y + 1, z + 1);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x - 2, y + 1, z + 1, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x - 1, y + 1, z, l, x, y + 1, z + 5, 4.5, tempType.getLabel(), true, x - 2, y + 1, z + 1, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x - 2, y + 1, z, l, x, y + 1, z + 5, 4.5, typeVariantStraight, false, x - 2, y + 1, z + 1, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x - 3, y + 1, z, l, x, y + 1, z + 5, 4.5, typeVariantStraight, false, x - 2, y + 1, z + 1, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x - 4, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false, x - 2, y + 1, z + 1, false, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x - 5, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, true, x - 2, y + 1, z + 1, false, false);

                    }
                    if (l == 3) {
                        /** Check if straight exit can be put down */
                        if (!canPlaceTrack(player, world, x + 5, y + 1, z) || !canPlaceTrack(player, world, x + 4, y + 1, z) || !canPlaceTrack(player, world, x + 3, y + 1, z) || !canPlaceTrack(player, world, x + 2, y + 1, z) || !canPlaceTrack(player, world, x + 1, y + 1, z)) {
                            return false;
                        }
                        int[] xArray = {x + 2, x + 3, x + 3, x + 4, x + 4, x + 4, x + 5, x + 5, x + 5};
                        int[] zArray = {z - 1, z - 1, z - 2, z - 1, z - 2, z - 3, z - 2, z - 3, z - 4};
                        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 0, x + 5, z - 5, 4.5, x + 1, y + 1,
                                z - 4, typeVariant90Turn, idVariantSwitch))
                            return false;

                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 2, y + 1, z - 1);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x + 2, y + 1, z - 1, l, 2);//to force client update

                        /** Switch rail 1 */
                        putDownSingleRail(world, x + 1, y + 1, z, l, x + 1, y + 1, z - 4, 4.5, tempType.getLabel(), true, x + 2, y + 1, z - 1, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x + 2, y + 1, z, l, x + 1, y + 1, z - 4, 4.5, typeVariantStraight, false, x + 2, y + 1, z - 1, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x + 3, y + 1, z, l, x + 1, y + 1, z - 4, 4.5, typeVariantStraight, false, x + 2, y + 1, z - 1, true, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x + 4, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, true, x + 2, y + 1, z - 1, false, false);

                        /** Put down straight exit **/
                        putDownSingleRail(world, x + 5, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight,
                                true, x + 2, y + 1, z - 1, false, false);

                    }
                    break;
                }

                case VERY_LARGE_RIGHT_SWITCH:
                case EMBEDDED_VERY_LARGE_RIGHT_SWITCH: {
                    if (tempType == EnumTracks.VERY_LARGE_RIGHT_SWITCH) {
                        idVariantSwitch = ItemIDs.tcRailVeryLargeSwitch.item;
                        typeVariant90Turn = EnumTracks.VERY_LARGE_RIGHT_TURN.getLabel();
                        typeVariantStraight = EnumTracks.SMALL_STRAIGHT.getLabel();
                    }
                    else {
                        idVariantSwitch = ItemIDs.tcRailEmbeddedVeryLargeSwitch.item;
                        typeVariant90Turn = EnumTracks.EMBEDDED_VERY_LARGE_RIGHT_TURN.getLabel();
                        typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
                    }

                    int[] xArray = { 1,1,1,1,1,2,2,2,3,3,4,4,5,5,5,6,6,7,7,8,9};
                    int[] zArray = { 2,3,4,5,6,6,6,7,7,8,8,9,8,9,10,9,10,9,10, 10,10};


                    if(l == 2) {
                        for (int i = 1; i < 7; i++) {

                            if (!canPlaceTrack(player, world, x, y + 1, z - i)) return false;
                        }

                        if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, true), l, true, 3, x + 10, z - 10, 9.5, x + 10, y + 1,
                                z, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z - 2);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x + 1, y + 1, z - 2, l, 2);//to force client update

                        /** Switch rail 1 **/
                        putDownSingleRail(world, x, y + 1, z - 1, l, x + 10, y + 1, z, 9.5, tempType.getLabel(), true, x + 1, y + 1, z - 2, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x, y + 1, z - 2, l, x + 10, y + 1, z, 9.5, typeVariantStraight, false, x + 1, y + 1, z - 2, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x, y + 1, z - 3, l, x + 10, y + 1, z, 9.5, typeVariantStraight, false, x + 1, y + 1, z - 2, true, false);

                        /** Switch rail 4 **/
                        putDownSingleRail(world, x, y + 1, z - 4, l, x + 10, y + 1, z, 9.5, typeVariantStraight, false, x + 1, y + 1, z - 2, true, false);
                        for (int straight = 5 ; straight < 7 ; straight++){
                            putDownSingleRail(world, x, y + 1, z - straight, l, x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z - 2, false, false);

                        }

                    }

                    if(l == 0) {
                        for (int i = 1; i < 7; i++) {

                            if (!canPlaceTrack(player, world, x, y + 1, z + i)) return false;
                        }

                        if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, false), l, true, 1, x - 10, z + 10, 9.5, x - 9, y + 1,
                                z + 1, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z + 2);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x - 1, y + 1, z + 2, l, 0);//to force client update

                        /** Switch rail 1 **/
                        putDownSingleRail(world, x, y + 1, z + 1, l, x - 9, y + 1, z + 1, 9.5, tempType.getLabel(), true, x - 1, y + 1, z + 2, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x, y + 1, z + 2, l, x - 9, y + 1, z + 1, 9.5, typeVariantStraight, false, x - 1, y + 1, z + 2, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x, y + 1, z + 3, l, x - 9, y + 1, z + 1, 9.5, typeVariantStraight, false, x - 1, y + 1, z + 2, true, false);

                        /** Switch rail 4 **/
                        putDownSingleRail(world, x, y + 1, z + 4, l, x - 9, y + 1, z + 1, 9.5, typeVariantStraight, false, x - 1, y + 1, z + 2, true, false);


                        for (int straight = 5 ; straight < 7 ; straight++){
                            putDownSingleRail(world, x, y + 1, z + straight, l, x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z + 2, false, false);

                        }

                    }

                    if(l == 1) {
                        for (int i = 1; i < 7; i++) {

                            if (!canPlaceTrack(player, world, x - i, y + 1, z)) return false;
                        }

                        if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, true), l, true, 2, x - 10, z - 10, 9.5, x , y + 1,
                                z - 9, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 2, y + 1, z - 1);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x  - 2, y + 1, z - 1, l, 1);//to force client update

                        /** Switch rail 1 **/
                        putDownSingleRail(world, x - 1, y + 1, z , l, x , y + 1, z - 9, 9.5, tempType.getLabel(), true, x - 2, y + 1, z - 1, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x - 2, y + 1, z , l, x, y + 1, z - 9, 9.5, typeVariantStraight, false, x - 2, y + 1, z - 1, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x - 3, y + 1, z , l, x , y + 1, z - 9, 9.5, typeVariantStraight, false, x - 2 , y + 1, z - 1, true, false);

                        /** Switch rail 4 **/
                        putDownSingleRail(world, x - 4, y + 1, z , l, x , y + 1, z - 9, 9.5, typeVariantStraight, false, x - 2, y + 1, z - 1, true, false);


                        for (int straight = 5 ; straight < 7 ; straight++){
                            putDownSingleRail(world, x - straight, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false, x - 2, y + 1, z - 1, false, false);

                        }

                    }

                    if(l == 3) {
                        for (int i = 1; i < 7; i++) {

                            if (!canPlaceTrack(player, world, x + i, y + 1, z)) return false;
                        }

                        if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, false), l, true, 0, x + 10, z + 10, 9.5, x + 1, y + 1,
                                z + 10, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 2, y + 1, z + 1);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x  + 2, y + 1, z + 1, l, 3);//to force client update

                        /** Switch rail 1 **/
                        putDownSingleRail(world, x + 1, y + 1, z , l, x + 1, y + 1, z + 10, 9.5, tempType.getLabel(), true, x + 2, y + 1, z + 1, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x + 2, y + 1, z , l, x + 1 , y + 1, z + 10, 9.5, typeVariantStraight, false, x + 2, y + 1, z + 1, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x + 3, y + 1, z , l, x + 1, y + 1, z + 10, 9.5, typeVariantStraight, false, x + 2 , y + 1, z + 1, true, false);

                        /** Switch rail 4 **/
                        putDownSingleRail(world, x + 4, y + 1, z , l, x + 1, y + 1, z + 10, 9.5, typeVariantStraight, false, x + 2, y + 1, z + 1, true, false);
                        for (int straight = 5 ; straight < 7 ; straight++){
                            putDownSingleRail(world, x + straight, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false, x + 2, y + 1, z + 1, false, false);

                        }

                    }
                    break;
                }

                case VERY_LARGE_LEFT_SWITCH:
                case EMBEDDED_VERY_LARGE_LEFT_SWITCH: {
                    if (tempType == EnumTracks.VERY_LARGE_LEFT_SWITCH) {
                        idVariantSwitch = ItemIDs.tcRailVeryLargeSwitch.item;
                        typeVariant90Turn = EnumTracks.VERY_LARGE_LEFT_TURN.getLabel();
                        typeVariantStraight = EnumTracks.SMALL_STRAIGHT.getLabel();
                    }
                    else {
                        idVariantSwitch = ItemIDs.tcRailEmbeddedVeryLargeSwitch.item;
                        typeVariant90Turn = EnumTracks.EMBEDDED_VERY_LARGE_LEFT_TURN.getLabel();
                        typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
                    }

                    int[] xArray = { 1,1,1,1,1,2,2,2,3,3,4,4,5,5,5,6,6,7,7,8,9};
                    int[] zArray = { 2,3,4,5,6,6,6,7,7,8,8,9,8,9,10,9,10,9,10, 10,10};


                    if(l == 2) {
                        for (int i = 1; i < 7; i++) {

                            if (!canPlaceTrack(player, world, x, y + 1, z - i)) return false;
                        }

                        if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, true), l, true, 1, x - 10, z - 10, 9.5, x - 9, y + 1,
                                z, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z - 2);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x - 1, y + 1, z - 2, l, 2);//to force client update

                        /** Switch rail 1 **/
                        putDownSingleRail(world, x, y + 1, z - 1, l, x - 9, y + 1, z, 9.5, tempType.getLabel(), true, x - 1, y + 1, z - 2, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x, y + 1, z - 2, l, x - 9, y + 1, z, 9.5, typeVariantStraight, false, x - 1, y + 1, z - 2, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x, y + 1, z - 3, l, x - 9, y + 1, z, 9.5, typeVariantStraight, false, x - 1, y + 1, z - 2, true, false);

                        /** Switch rail 4 **/
                        putDownSingleRail(world, x, y + 1, z - 4, l, x - 9, y + 1, z, 9.5, typeVariantStraight, false, x - 1, y + 1, z - 2, true, false);
                        for (int straight = 5 ; straight < 7 ; straight++){
                            putDownSingleRail(world, x, y + 1, z - straight, l, x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z - 2, false, false);

                        }

                    }

                    if(l == 0) {
                        for (int i = 1; i < 7; i++) {

                            if (!canPlaceTrack(player, world, x, y + 1, z + i)) return false;
                        }

                        if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, false), l, true, 1, x + 10, z + 10, 9.5, x + 10, y + 1,
                                z + 1, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z + 2);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x + 1, y + 1, z + 2, l, 0);//to force client update

                        /** Switch rail 1 **/
                        putDownSingleRail(world, x, y + 1, z + 1, l, x + 10, y + 1, z + 1, 9.5, tempType.getLabel(), true, x + 1, y + 1, z + 2, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x, y + 1, z + 2, l, x + 10, y + 1, z + 1, 9.5, typeVariantStraight, false, x + 1, y + 1, z + 2, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x, y + 1, z + 3, l, x + 10, y + 1, z + 1, 9.5, typeVariantStraight, false, x + 1, y + 1, z + 2, true, false);

                        /** Switch rail 4 **/
                        putDownSingleRail(world, x, y + 1, z + 4, l, x + 10, y + 1, z + 1, 9.5, typeVariantStraight, false, x + 1, y + 1, z + 2, true, false);


                        for (int straight = 5 ; straight < 7 ; straight++){
                            putDownSingleRail(world, x, y + 1, z + straight, l, x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z + 2, false, false);

                        }

                    }

                    if(l == 1) {
                        for (int i = 1; i < 7; i++) {

                            if (!canPlaceTrack(player, world, x - i, y + 1, z)) return false;
                        }

                        if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, false), l, true, 0, x - 10, z + 10, 9.5, x , y + 1,
                                z + 10, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 2, y + 1, z + 1);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x  - 2, y + 1, z + 1, l, 1);//to force client update

                        /** Switch rail 1 **/
                        putDownSingleRail(world, x - 1, y + 1, z , l, x , y + 1, z + 10, 9.5, tempType.getLabel(), true, x - 2, y + 1, z + 1, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x - 2, y + 1, z , l, x, y + 1, z + 10, 9.5, typeVariantStraight, false, x - 2, y + 1, z + 1, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x - 3, y + 1, z , l, x , y + 1, z + 10 , 9.5, typeVariantStraight, false, x - 2 , y + 1, z + 1, true, false);

                        /** Switch rail 4 **/
                        putDownSingleRail(world, x - 4, y + 1, z , l, x , y + 1, z + 10, 9.5, typeVariantStraight, false, x - 2, y + 1, z + 1, true, false);
                        for (int straight = 5 ; straight < 7 ; straight++){
                            putDownSingleRail(world, x - straight, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false, x - 2, y + 1, z + 1, false, false);

                        }

                    }

                    if(l == 3) {
                        for (int i = 1; i < 7; i++) {

                            if (!canPlaceTrack(player, world, x + i, y + 1, z)) return false;
                        }

                        if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, true), l, true, 2, x + 10, z - 10, 9.5, x + 1, y + 1,
                                z - 9, typeVariant90Turn, idVariantSwitch))
                            return false;
                        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 2, y + 1, z - 1);
                        if (tcRailTurn != null)
                            tcRailTurn.hasModel = false;
                        world.setBlockMetadataWithNotify(x  + 2, y + 1, z - 1, l, 3);//to force client update

                        /** Switch rail 1 **/
                        putDownSingleRail(world, x + 1, y + 1, z , l, x + 1, y + 1, z - 9, 9.5, tempType.getLabel(), true, x + 2, y + 1, z - 1, true, false);

                        /** Switch rail 2 **/
                        putDownSingleRail(world, x + 2, y + 1, z , l, x + 1 , y + 1, z - 9, 9.5, typeVariantStraight, false, x + 2, y + 1, z - 1, true, false);

                        /** Switch rail 3 **/
                        putDownSingleRail(world, x + 3, y + 1, z , l, x + 1, y + 1, z - 9, 9.5, typeVariantStraight, false, x + 2 , y + 1, z - 1, true, false);

                        /** Switch rail 4 **/
                        putDownSingleRail(world, x + 4, y + 1, z , l, x + 1, y + 1, z - 9, 9.5, typeVariantStraight, false, x + 2, y + 1, z - 1, true, false);
                        for (int straight = 5 ; straight < 7 ; straight++){
                            putDownSingleRail(world, x + straight, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false, x + 2, y + 1, z - 1, false, false);

                        }

                    }
                    break;
                }
                case SLOPE_WOOD:
                case SLOPE_GRAVEL:
                case SLOPE_BALLAST:
                case SLOPE_SNOW_GRAVEL:
                case SLOPE_DYNAMIC:
                case EMBEDDED_SLOPE_DYNAMIC:
                case LARGE_SLOPE_WOOD:
                case LARGE_SLOPE_GRAVEL:
                case LARGE_SLOPE_BALLAST:
                case LARGE_SLOPE_SNOW_GRAVEL:
                case LARGE_SLOPE_DYNAMIC:
                case EMBEDDED_LARGE_SLOPE_DYNAMIC:
                case VERY_LARGE_SLOPE_WOOD:
                case VERY_LARGE_SLOPE_GRAVEL:
                case VERY_LARGE_SLOPE_BALLAST:
                case VERY_LARGE_SLOPE_SNOW_GRAVEL:
                case VERY_LARGE_SLOPE_DYNAMIC:
                case EMBEDDED_VERY_LARGE_SLOPE_DYNAMIC: {
                    if (!canPlaceTrack(player, world, x, y + 1, z)) {
                        return false;
                    }
                    if (type.getLabel().contains("DYNAMIC") && world.getBlock(x, y, z) == TCBlocks.bridgePillar) {
                        return false;
                    }


                    int gagEnd = 0;
                    double slopeAngle = 0;
                    /**
                     * Explanation: normally you would divide 100 by (gagEnd+1) but this seems to be
                     * against TCs own brain. you need to divide 100 by (gagEnd+1)
                     **/
                    if (type == EnumTracks.SLOPE_WOOD || type == EnumTracks.SLOPE_GRAVEL
                            || type == EnumTracks.SLOPE_BALLAST || type == EnumTracks.SLOPE_SNOW_GRAVEL
                            || type == EnumTracks.SLOPE_DYNAMIC || type == EnumTracks.EMBEDDED_SLOPE_DYNAMIC) {
                        gagEnd = 5;
                        slopeAngle = 0.13;
                    }

                    if (type == EnumTracks.LARGE_SLOPE_WOOD || type == EnumTracks.LARGE_SLOPE_GRAVEL
                            || type == EnumTracks.LARGE_SLOPE_BALLAST || type == EnumTracks.LARGE_SLOPE_SNOW_GRAVEL
                            || type == EnumTracks.LARGE_SLOPE_DYNAMIC || type == EnumTracks.EMBEDDED_LARGE_SLOPE_DYNAMIC) {
                        gagEnd = 11;
                        slopeAngle = 0.0666;
                    }

                    if (type == EnumTracks.VERY_LARGE_SLOPE_WOOD || type == EnumTracks.VERY_LARGE_SLOPE_GRAVEL
                            || type == EnumTracks.VERY_LARGE_SLOPE_BALLAST || type == EnumTracks.VERY_LARGE_SLOPE_SNOW_GRAVEL
                            || type == EnumTracks.VERY_LARGE_SLOPE_DYNAMIC || type == EnumTracks.EMBEDDED_VERY_LARGE_SLOPE_DYNAMIC) {
                        gagEnd = 17;
                        slopeAngle = 0.0444;
                    }


                    Item idDropped = this.type.getItem().item;
                    TileTCRailGag[] tileGag = new TileTCRailGag[gagEnd];

                    for (int i = 1; i <= gagEnd; i++) {
                        if (l == 2) {
                            if (!canPlaceTrack(player, world, x, y + 1, z - i)) {
                                return false;
                            }
                        }
                        if (l == 0) {
                            if (!canPlaceTrack(player, world, x, y + 1, z + i)) {
                                return false;
                            }
                        }
                        if (l == 1) {
                            if (!canPlaceTrack(player, world, x - i, y + 1, z)) {
                                return false;
                            }
                        }
                        if (l == 3) {
                            if (!canPlaceTrack(player, world, x + i, y + 1, z)) {
                                return false;
                            }
                        }
                    }
                    placeTrack(world, x, y + 1, z, BlockIDs.tcRail.block, l);
                    TileTCRail tcRail = (TileTCRail) world.getTileEntity(x, y + 1, z);
                    tcRail.setFacing(l);
                    tcRail.setType(type.getLabel());
                    tcRail.idDrop = idDropped;
                    tcRail.slopeHeight = 1;
                    tcRail.slopeAngle = slopeAngle;
                    tcRail.slopeLength = gagEnd + 1;

                    Block block = world.getBlock(x, y, z);
                    int blockID = Block.getIdFromBlock(block);
                    tcRail.setBallastMaterial(blockID);
                    tcRail.ballastMetadata = world.getBlockMetadata(x, y, z);


                    for (int i2 = 1; i2 <= gagEnd; i2++) {
                        if (l == 2) {
                            placeTrack(world, x, y + 1, z - i2, BlockIDs.tcRailGag.block, l);
                            tileGag[i2 - 1] = (TileTCRailGag) world.getTileEntity(x, y + 1, z - i2);
                            tileGag[i2 - 1].bbHeight = Math.max(0.125f, Math.min(1f, i2 / (float) gagEnd));
                        }
                        if (l == 0) {
                            placeTrack(world, x, y + 1, z + i2, BlockIDs.tcRailGag.block, l);
                            tileGag[i2 - 1] = (TileTCRailGag) world.getTileEntity(x, y + 1, z + i2);
                            tileGag[i2 - 1].bbHeight = Math.max(0.125f, Math.min(1f, i2 / (float) gagEnd));
                        }
                        if (l == 1) {
                            placeTrack(world, x - i2, y + 1, z, BlockIDs.tcRailGag.block, l);
                            tileGag[i2 - 1] = (TileTCRailGag) world.getTileEntity(x - i2, y + 1, z);
                            tileGag[i2 - 1].bbHeight = Math.max(0.125f, Math.min(1f, i2 / (float) gagEnd));
                        }
                        if (l == 3) {
                            placeTrack(world, x + i2, y + 1, z, BlockIDs.tcRailGag.block, l);
                            tileGag[i2 - 1] = (TileTCRailGag) world.getTileEntity(x + i2, y + 1, z);
                            tileGag[i2 - 1].bbHeight = Math.max(0.125f, Math.min(1f, i2 / (float) gagEnd));
                        }
                    }
                    for (int i = 0; i < tileGag.length; i++) {
                        if (player != null && tileGag[i] == null) {
                            player.addChatMessage(new ChatComponentText(
                                    "There was a problem when placing the track. Possibly too many tracks around"));
                            return false;
                        }
                        tileGag[i].originX = x;
                        tileGag[i].originY = y + 1;
                        tileGag[i].originZ = z;
                        tileGag[i].type = type.getLabel();
                    }
                    break;
                }

                case TWO_WAYS_CROSSING:
                case EMBEDDED_TWO_WAYS_CROSSING: {
                    switch (type) {
                        case TWO_WAYS_CROSSING:
                            typeVariantStraight = EnumTracks.SMALL_STRAIGHT.getLabel();
                            typeVariantCrossing = EnumTracks.TWO_WAYS_CROSSING.getLabel();
                            break;

                        case EMBEDDED_TWO_WAYS_CROSSING:
                            typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
                            typeVariantCrossing = EnumTracks.EMBEDDED_TWO_WAYS_CROSSING.getLabel();
                            break;

                    }



                    if (!canPlaceTrack(player, world, x, y + 1, z)) {
                        return false;
                    }

                    int zDisplace = 0;
                    int xDisplace = 0;
                    int xSideDisplace = 0;
                    int zSideDisplace = 0;
                    int sideFacing = l;

                    if (l == 2) {
                        zDisplace = -1;
                        xSideDisplace = 1;
                        sideFacing = 1;
                    }
                    if (l == 3) {
                        xDisplace = 1;
                        zSideDisplace = 1;
                        sideFacing = 2;
                    }
                    if (l == 0) {
                        zDisplace = 1;
                        xSideDisplace = 1;
                        sideFacing = 1;
                    }
                    if (l == 1) {
                        xDisplace = -1;
                        zSideDisplace = 1;
                        sideFacing = 2;
                    }

                    if (!canPlaceTrack(player, world, x + xDisplace, y + 1, z + zDisplace) || !canPlaceTrack(player, world, x + (xDisplace * 2), y + 1, z + (zDisplace * 2))) {
                        return false;
                    }
                    if (!canPlaceTrack(player, world, x + (xDisplace) + (xSideDisplace), y + 1,
                            z + (zDisplace) + (zSideDisplace))) {
                        return false;
                    }
                    if (!canPlaceTrack(player, world, x + (xDisplace) - (xSideDisplace), y + 1,
                            z + (zDisplace) - (zSideDisplace))) {
                        return false;
                    }


                    //Top
                    putDownSingleRail(world, x + (xDisplace * 2), y + 1, z + (zDisplace * 2), l, x + (xDisplace * 2), y + 1,
                            z + (zDisplace * 2), 0, typeVariantStraight, true, x + (xDisplace), y + 1,
                            z + (zDisplace), false, false);

                    //Main
                    placeTrack(world, x + (xDisplace), y + 1, z + (zDisplace), BlockIDs.tcRail.block, l);
                    TileTCRail tcRail2 = (TileTCRail) world.getTileEntity(x + (xDisplace), y + 1, z + (zDisplace));
                    tcRail2.setFacing(l);
                    tcRail2.cx = x + (xDisplace);
                    tcRail2.cy = y + 1;
                    tcRail2.cz = z + (zDisplace);
                    tcRail2.setType(typeVariantCrossing);
                    tcRail2.idDrop = this.type.getItem().item;

                    //Bottom
                    putDownSingleRail(world, x, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, true,
                            x + (xDisplace), y + 1, z + (zDisplace), false, false);

                    //Right
                    putDownSingleRail(world, x + (xDisplace) + (xSideDisplace), y + 1,
                            z + (zDisplace) + (zSideDisplace), sideFacing,
                            x + (xDisplace) + (xSideDisplace), y + 1, z + (zDisplace) + (zSideDisplace), 0,
                            typeVariantStraight, true, x + (xDisplace), y + 1, z + (zDisplace),
                            false, false);

                    //Left
                    putDownSingleRail(world, x + (xDisplace) - (xSideDisplace), y + 1,
                            z + (zDisplace) - (zSideDisplace), sideFacing,
                            x + (xDisplace) - (xSideDisplace), y + 1, z + (zDisplace) - (zSideDisplace), 0,
                            typeVariantStraight, true, x + (xDisplace), y + 1, z + (zDisplace),
                            false, false);
                    break;
                }
            }
            if (player == null || !player.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }
            return true;
        }
        return false;
    }


    private EnumTracks getPlacementDirection(EntityPlayer player, World world, int l, float par10) {
        if (!world.isRemote) {

            /** Determines if track is left or right*/
            float yaw = MathHelper.wrapAngleTo180_float(player != null ? player.rotationYaw : par10);
            tempType = type;

            if (type == EnumTracks.TURN_1X1) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.RIGHT_TURN_1X1;

                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.LEFT_TURN_1X1;
                }
            }

            if (type == EnumTracks.MEDIUM_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.MEDIUM_RIGHT_TURN;

                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.MEDIUM_LEFT_TURN;
                }
            }
            if (type == EnumTracks.LARGE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.LARGE_RIGHT_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.LARGE_LEFT_TURN;
                }
            }
            if (type == EnumTracks.VERY_LARGE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.VERY_LARGE_RIGHT_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.VERY_LARGE_LEFT_TURN;
                }
            }
            if (type == EnumTracks.SUPER_LARGE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.SUPER_LARGE_RIGHT_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.SUPER_LARGE_LEFT_TURN;
                }
            }
            if (type == EnumTracks.TURN_29X29) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.RIGHT_TURN_29X29;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.LEFT_TURN_29X29;

                }
            }
            if (type == EnumTracks.TURN_32X32) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.RIGHT_TURN_32X32;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.LEFT_TURN_32X32;

                }
            }
            if (type == EnumTracks.MEDIUM_45DEGREE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.MEDIUM_RIGHT_45DEGREE_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.MEDIUM_LEFT_45DEGREE_TURN;

                }
            }
            if (type == EnumTracks.LARGE_45DEGREE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.LARGE_RIGHT_45DEGREE_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.LARGE_LEFT_45DEGREE_TURN;

                }
            }
            if (type == EnumTracks.VERY_LARGE_45DEGREE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.VERY_LARGE_RIGHT_45DEGREE_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.VERY_LARGE_LEFT_45DEGREE_TURN;

                }
            }
            if (type == EnumTracks.SUPER_LARGE_45DEGREE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.SUPER_LARGE_RIGHT_45DEGREE_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.SUPER_LARGE_LEFT_45DEGREE_TURN;

                }
            }
            if (type == EnumTracks.SMALL_PARALLEL_CURVE) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.SMALL_RIGHT_PARALLEL_CURVE;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.SMALL_LEFT_PARALLEL_CURVE;
                }
            }
            if (type == EnumTracks.MEDIUM_PARALLEL_CURVE) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.MEDIUM_RIGHT_PARALLEL_CURVE;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.MEDIUM_LEFT_PARALLEL_CURVE;
                }
            }
            if (type == EnumTracks.LARGE_PARALLEL_CURVE) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.LARGE_RIGHT_PARALLEL_CURVE;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.LARGE_LEFT_PARALLEL_CURVE;
                }
            }
            if (type == EnumTracks.S_CURVE_20x2) {
                if (getTrackOrientation(l,yaw).equals("right")) {
                    tempType = EnumTracks.S_CURVE_20x2_RIGHT;
                }
                if (getTrackOrientation(l,yaw).equals("left")) {
                    tempType = EnumTracks.S_CURVE_20x2_LEFT;
                }
            }
            if (type == EnumTracks.MEDIUM_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.MEDIUM_RIGHT_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.MEDIUM_LEFT_SWITCH;
                }
            }
            if (type == EnumTracks.LARGE_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.LARGE_RIGHT_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.LARGE_LEFT_SWITCH;
                }
            }
            if (type == EnumTracks.VERY_LARGE_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.VERY_LARGE_RIGHT_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.VERY_LARGE_LEFT_SWITCH;
                }
            }
            if (type == EnumTracks.MEDIUM_PARALLEL_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.MEDIUM_RIGHT_PARALLEL_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.MEDIUM_LEFT_PARALLEL_SWITCH;
                }
            }
            if (type == EnumTracks.LARGE_PARALLEL_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.LARGE_RIGHT_PARALLEL_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.LARGE_LEFT_PARALLEL_SWITCH;
                }
            }
            if (type == EnumTracks.MEDIUM_45DEGREE_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.MEDIUM_RIGHT_45DEGREE_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.MEDIUM_LEFT_45DEGREE_SWITCH;
                }
            }
            if (type == EnumTracks.LARGE_45DEGREE_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.LARGE_RIGHT_45DEGREE_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.LARGE_LEFT_45DEGREE_SWITCH;
                }
            }

            if (type == EnumTracks.EMBEDDED_TURN_1X1) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_RIGHT_TURN_1X1;

                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_LEFT_TURN_1X1;
                }
            }
            if (type == EnumTracks.EMBEDDED_MEDIUM_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_MEDIUM_RIGHT_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_MEDIUM_LEFT_TURN;
                }
            }
            if (type == EnumTracks.EMBEDDED_LARGE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_RIGHT_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_LEFT_TURN;
                }
            }
            if (type == EnumTracks.EMBEDDED_VERY_LARGE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_VERY_LARGE_RIGHT_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_VERY_LARGE_LEFT_TURN;
                }
            }
            if (type == EnumTracks.EMBEDDED_SUPER_LARGE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_SUPER_LARGE_RIGHT_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_SUPER_LARGE_LEFT_TURN;
                }
            }
            if (type == EnumTracks.EMBEDDED_TURN_29X29) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_RIGHT_TURN_29X29;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_LEFT_TURN_29X29;

                }
            }
            if (type == EnumTracks.EMBEDDED_TURN_32X32) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_RIGHT_TURN_32X32;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_LEFT_TURN_32X32;

                }
            }
            if (type == EnumTracks.EMBEDDED_MEDIUM_45DEGREE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_MEDIUM_RIGHT_45DEGREE_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_MEDIUM_LEFT_45DEGREE_TURN;

                }
            }
            if (type == EnumTracks.EMBEDDED_LARGE_45DEGREE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_RIGHT_45DEGREE_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_LEFT_45DEGREE_TURN;

                }
            }
            if (type == EnumTracks.EMBEDDED_VERY_LARGE_45DEGREE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_VERY_LARGE_RIGHT_45DEGREE_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_VERY_LARGE_LEFT_45DEGREE_TURN;

                }
            }
            if (type == EnumTracks.EMBEDDED_SUPER_LARGE_45DEGREE_TURN) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_SUPER_LARGE_RIGHT_45DEGREE_TURN;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_SUPER_LARGE_LEFT_45DEGREE_TURN;

                }
            }
            if (type == EnumTracks.EMBEDDED_SMALL_PARALLEL_CURVE) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_SMALL_RIGHT_PARALLEL_CURVE;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_SMALL_LEFT_PARALLEL_CURVE;
                }
            }
            if (type == EnumTracks.EMBEDDED_MEDIUM_PARALLEL_CURVE) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_MEDIUM_RIGHT_PARALLEL_CURVE;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_MEDIUM_LEFT_PARALLEL_CURVE;
                }
            }
            if (type == EnumTracks.EMBEDDED_LARGE_PARALLEL_CURVE) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_RIGHT_PARALLEL_CURVE;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_LEFT_PARALLEL_CURVE;
                }
            }
            if (type == EnumTracks.EMBEDDED_S_CURVE_20x2) {
                if (getTrackOrientation(l,yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_S_CURVE_20x2_RIGHT;
                }
                if (getTrackOrientation(l,yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_S_CURVE_20x2_LEFT;
                }
            }
            if (type == EnumTracks.EMBEDDED_MEDIUM_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_MEDIUM_RIGHT_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_MEDIUM_LEFT_SWITCH;
                }
            }
            if (type == EnumTracks.EMBEDDED_LARGE_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_RIGHT_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_LEFT_SWITCH;
                }
            }
            if (type == EnumTracks.EMBEDDED_VERY_LARGE_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_VERY_LARGE_RIGHT_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_VERY_LARGE_LEFT_SWITCH;
                }
            }
            if (type == EnumTracks.EMBEDDED_MEDIUM_PARALLEL_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_MEDIUM_RIGHT_PARALLEL_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_MEDIUM_LEFT_PARALLEL_SWITCH;
                }
            }
            if (type == EnumTracks.EMBEDDED_LARGE_PARALLEL_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_RIGHT_PARALLEL_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_LEFT_PARALLEL_SWITCH;
                }
            }
            if (type == EnumTracks.EMBEDDED_MEDIUM_45DEGREE_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_MEDIUM_RIGHT_45DEGREE_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_MEDIUM_LEFT_45DEGREE_SWITCH;
                }
            }
            if (type == EnumTracks.EMBEDDED_LARGE_45DEGREE_SWITCH) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_RIGHT_45DEGREE_SWITCH;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_LEFT_45DEGREE_SWITCH;
                }
            }
            if (type == EnumTracks.LARGE_CURVED_SLOPE_DYNAMIC) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.LARGE_RIGHT_CURVED_SLOPE_DYNAMIC;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.LARGE_LEFT_CURVED_SLOPE_DYNAMIC;
                }
            }

            if (type == EnumTracks.EMBEDDED_LARGE_CURVED_SLOPE_DYNAMIC) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_LARGE_LEFT_CURVED_SLOPE_DYNAMIC;
                }
            }
            if (type == EnumTracks.VERY_LARGE_CURVED_SLOPE_DYNAMIC) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.VERY_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.VERY_LARGE_LEFT_CURVED_SLOPE_DYNAMIC;
                }
            }

            if (type == EnumTracks.EMBEDDED_VERY_LARGE_CURVED_SLOPE_DYNAMIC) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_VERY_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_VERY_LARGE_LEFT_CURVED_SLOPE_DYNAMIC;
                }
            }
            if (type == EnumTracks.SUPER_LARGE_CURVED_SLOPE_DYNAMIC) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.SUPER_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.SUPER_LARGE_LEFT_CURVED_SLOPE_DYNAMIC;
                }
            }

            if (type == EnumTracks.EMBEDDED_SUPER_LARGE_CURVED_SLOPE_DYNAMIC) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_SUPER_LARGE_RIGHT_CURVED_SLOPE_DYNAMIC;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_SUPER_LARGE_LEFT_CURVED_SLOPE_DYNAMIC;
                }
            }
            if (type == EnumTracks.DIAMOND_CROSSING) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.RIGHT_DIAMOND_CROSSING;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.LEFT_DIAMOND_CROSSING;
                }
            }
            if (type == EnumTracks.EMBEDDED_DIAMOND_CROSSING) {
                if (getTrackOrientation(l, yaw).equals("right")) {
                    tempType = EnumTracks.EMBEDDED_RIGHT_DIAMOND_CROSSING;
                }
                if (getTrackOrientation(l, yaw).equals("left")) {
                    tempType = EnumTracks.EMBEDDED_LEFT_DIAMOND_CROSSING;
                }
            }

        }
        return tempType;
    }

    /**
     * Track Placement (because onItemUse is getting too big)
     */

    private boolean smallStraight(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks type) {
        if (!canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }
        placeTrack(world, x, y + 1, z, BlockIDs.tcRail.block, l);
        TileTCRail tcRail = (TileTCRail) world.getTileEntity(x, y + 1, z);
        tcRail.setFacing(l);
        tcRail.cx = x;
        tcRail.cy = y + 1;
        tcRail.cz = z;
        tcRail.setType(type.getLabel());
        tcRail.idDrop = this.type.getItem().item;

        if (type == EnumTracks.SMALL_ROAD_CROSSING_DYNAMIC){
            Block block = world.getBlock(x, y, z);
            int blockID = Block.getIdFromBlock(block);
            tcRail.setBallastMaterial(blockID);
            tcRail.ballastMetadata = world.getBlockMetadata(x, y, z);
        }




        return true;
    }

    private boolean straight(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks type) {

        int trackLength = 0; // Initialise tracklength for medium.
        int dx = 0;
        int dz = 0;

        //Adjust tracklength based on track type.
        if (type == EnumTracks.LONG_STRAIGHT || type == EnumTracks.EMBEDDED_LONG_STRAIGHT) trackLength = 3;
        if (type == EnumTracks.VERY_LONG_STRAIGHT || type == EnumTracks.EMBEDDED_VERY_LONG_STRAIGHT) trackLength = 9;

        //Initialise arrays of Rails and Gags, based on tracklength.
        TileTCRail[] tileRail = new TileTCRail[(trackLength / 3) + 1];
        TileTCRailGag[] tileGag = new TileTCRailGag[(2*(trackLength / 3)) + 2];

        //determine direction for placement
        if (l == 0) dz = 1;
        if (l == 1) dx = -1;
        if (l == 2) dz = -1;
        if (l == 3) dx = 1;

        //Check placement

        for (int i = 0; i <= trackLength ; i += 3){
            if (
                    !canPlaceTrack(player,world, x + (i * dx), y + 1, z + (i * dz))
                    || !canPlaceTrack(player,world, x + (i * dx) + (dx), y + 1, z + (i * dz) + dz)
                    || !canPlaceTrack(player,world, x + (i * dx) + (dx * 2), y + 1, z + (i * dz) + (dz) * 2)){
                return false;
            }

        }
        //places track
        for (int i = 0; i <= trackLength ; i += 3){
            placeTrack(world, x + (i * dx), y + 1, z + (i * dz), BlockIDs.tcRail.block, l);
            tileRail[i / 3] = (TileTCRail) world.getTileEntity(x + (i * dx), y + 1, z + (i * dz));
            tileRail[i / 3].setType(type.getLabel());
            tileRail[i / 3].setFacing(l);
            tileRail[0].idDrop = this.type.getItem().item;
            if (i / 3 != 0){
                tileRail[i / 3].isLinkedToRail = true;
                tileRail[i / 3].linkedX = x + dx;
                tileRail[i / 3].linkedY = y + 1;
                tileRail[i / 3].linkedZ = z + dz;
            }
            for (int j = 1; j < 3; j++){
                placeTrack(world, x + (i * dx) + (j * dx ), y + 1,z + (i * dz) + (j * dz ), BlockIDs.tcRailGag.block, l );
                tileGag[(i - (i/3))+ j - 1] = (TileTCRailGag) world.getTileEntity(x + (i * dx) + (j * dx ), y + 1,z + (i * dz) + (j * dz ));
            }
        }

        //gives Gag rails the TileTCRail as their origin
        for (TileTCRailGag tileTCRailGag : tileGag) {
            if (player != null && tileTCRailGag == null) {
                player.addChatMessage(new ChatComponentText("There was a problem when placing the track. Possibly too many tracks around"));
                return false;
            }
            tileTCRailGag.originX = x;
            tileTCRailGag.originY = y + 1;
            tileTCRailGag.originZ = z;
            tileTCRailGag.type = EnumTracks.MEDIUM_STRAIGHT.getLabel();
        }



        return true;
    }
    private boolean rightDiamondCrossing(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType) {


        if (tempType.getLabel().contains("EMBEDDED")) {
            typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
            typeVariantDiagonal = EnumTracks.EMBEDDED_SMALL_DIAGONAL_STRAIGHT.getLabel();
        }


        if (!canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }

        int zDisplace = 0;
        int xDisplace = 0;
        int xSideDisplace = 0;
        int zSideDisplace = 0;
        int sideFacing = l;

        if (l == 2) {
            zDisplace = -1;
            xSideDisplace = 1;
            sideFacing = 6;
        }
        if (l == 0) {
            zDisplace = 1;
            xSideDisplace = -1;
            sideFacing = 4;
        }
        if (l == 1) {
            xDisplace = -1;
            zSideDisplace = -1;
            sideFacing = 5;
        }
        if (l == 3) {
            xDisplace = 1;
            zSideDisplace = 1;
            sideFacing = 7;
        }

        if (!canPlaceTrack(player, world, x + xDisplace, y + 1, z + zDisplace) || !canPlaceTrack(player, world, x + (xDisplace * 2), y + 1, z + (zDisplace * 2))) {
            return false;
        }
        if (!canPlaceTrack(player, world, x + (xDisplace * 2) + xSideDisplace, y + 1, z + (zDisplace * 2) + zSideDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x - (xSideDisplace), y + 1, z - zSideDisplace)) {
            return false;
        }

        //Top

        putDownSingleRail(world, x + (xDisplace * 2), y + 1, z + (zDisplace * 2), l, x + (xDisplace * 2), y + 1,
                z + (zDisplace * 2), 0, typeVariantStraight, false, x + (xDisplace), y + 1,
                z + (zDisplace), false, false);

        //Main

        placeTrack(world, x + (xDisplace), y + 1, z + (zDisplace), BlockIDs.tcRail.block, l);
        TileTCRail tcRail2 = (TileTCRail) world.getTileEntity(x + (xDisplace), y + 1, z + (zDisplace));
        tcRail2.setFacing(l);
        tcRail2.cx = x + (xDisplace);
        tcRail2.cy = y + 1;
        tcRail2.cz = z + (zDisplace);
        tcRail2.setType(tempType.getLabel());
        tcRail2.idDrop = this.type.getItem().item;

        //Bottom


        putDownSingleRail(world, x, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false,
                x + (xDisplace), y + 1, z + (zDisplace), false, false);

        //Right

        putDownSingleRail(world, x + (xDisplace * 2) + (xSideDisplace), y + 1,
                z + (zDisplace * 2) + (zSideDisplace), sideFacing,
                x + (xDisplace) + (xSideDisplace), y + 1, z + (zDisplace * 2) + (zSideDisplace), 0,
                typeVariantDiagonal, false, x + (xDisplace), y + 1, z + (zDisplace),
                false, false);

        //Left

        putDownSingleRail(world, x - (xSideDisplace), y + 1,
                z - (zSideDisplace), sideFacing,
                x - (xSideDisplace), y + 1, z - (zSideDisplace), 0,
                typeVariantDiagonal, false, x + (xDisplace), y + 1, z + (zDisplace),
                false, false);


        return true;
    }
    private boolean leftDiamondCrossing(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType) {

        if (tempType.getLabel().contains("EMBEDDED")) {
            typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
            typeVariantDiagonal = EnumTracks.EMBEDDED_SMALL_DIAGONAL_STRAIGHT.getLabel();
        }


        if (!canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }

        int zDisplace = 0;
        int xDisplace = 0;
        int xSideDisplace = 0;
        int zSideDisplace = 0;
        int sideFacing = l;

        if (l == 2) {
            zDisplace = -1;
            xSideDisplace = 1;
            sideFacing = 5;
        }
        if (l == 0) {
            zDisplace = 1;
            xSideDisplace = -1;
            sideFacing = 7;
        }
        if (l == 1) {
            xDisplace = -1;
            zSideDisplace = -1;
            sideFacing = 4;
        }
        if (l == 3) {
            xDisplace = 1;
            zSideDisplace = 1;
            sideFacing = 6;
        }


        if (!canPlaceTrack(player, world, x + xDisplace, y + 1, z + zDisplace) || !canPlaceTrack(player, world, x + (xDisplace * 2), y + 1, z + (zDisplace * 2))) {
            return false;
        }
        if (!canPlaceTrack(player, world, x + (xDisplace * 2) - xSideDisplace, y + 1, z + (zDisplace * 2) - zSideDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x + (xSideDisplace), y + 1, z + zSideDisplace)) {
            return false;
        }

        //Top

        putDownSingleRail(world, x + (xDisplace * 2), y + 1, z + (zDisplace * 2), l, x + (xDisplace * 2), y + 1,
                z + (zDisplace * 2), 0, typeVariantStraight, false, x + (xDisplace), y + 1,
                z + (zDisplace), false, false);

        //Main

        placeTrack(world, x + (xDisplace), y + 1, z + (zDisplace), BlockIDs.tcRail.block, l);
        TileTCRail tcRail2 = (TileTCRail) world.getTileEntity(x + (xDisplace), y + 1, z + (zDisplace));
        tcRail2.setFacing(l);
        tcRail2.cx = x + (xDisplace);
        tcRail2.cy = y + 1;
        tcRail2.cz = z + (zDisplace);
        tcRail2.setType(tempType.getLabel());
        tcRail2.idDrop = this.type.getItem().item;

        //Bottom


        putDownSingleRail(world, x, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false,
                x + (xDisplace), y + 1, z + (zDisplace), false, false);

        //Left

        putDownSingleRail(world, x + (xDisplace * 2) - (xSideDisplace), y + 1,
                z + (zDisplace * 2) - (zSideDisplace), sideFacing,
                x - (xDisplace) - (xSideDisplace), y + 1, z + (zDisplace * 2) - (zSideDisplace), 0,
                typeVariantDiagonal, false, x + (xDisplace), y + 1, z + (zDisplace),
                false, false);

        //Right

        putDownSingleRail(world, x + (xSideDisplace), y + 1,
                z + (zSideDisplace), sideFacing,
                x + (xSideDisplace), y + 1, z + (zSideDisplace), 0,
                typeVariantDiagonal, false, x + (xDisplace), y + 1, z + (zDisplace),
                false, false);


        return true;
    }
    private boolean doubleDiamondCrossing(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks type) {

        if (type.getLabel().contains("EMBEDDED")) {
            typeVariantStraight = EnumTracks.EMBEDDED_SMALL_STRAIGHT.getLabel();
            typeVariantDiagonal = EnumTracks.EMBEDDED_SMALL_DIAGONAL_STRAIGHT.getLabel();
        }


        if (!canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }

        int zDisplace = 0;
        int xDisplace = 0;
        int xSideDisplace = 0;
        int zSideDisplace = 0;
        int sideFacing = l;
        int sideFacing2 = l;

        if (l == 2) {
            zDisplace = -1;
            xSideDisplace = 1;
            sideFacing = 6;
            sideFacing2 = 5;
        }
        if (l == 0) {
            zDisplace = 1;
            xSideDisplace = -1;
            sideFacing = 4;
            sideFacing2 = 7;
        }
        if (l == 1) {
            xDisplace = -1;
            zSideDisplace = -1;
            sideFacing = 5;
            sideFacing2 = 4;
        }
        if (l == 3) {
            xDisplace = 1;
            zSideDisplace = 1;
            sideFacing = 7;
            sideFacing2 = 6;
        }

        if (!canPlaceTrack(player, world, x + xDisplace, y + 1, z + zDisplace) || !canPlaceTrack(player, world, x + (xDisplace * 2), y + 1, z + (zDisplace * 2))) {
            return false;
        }
        if (!canPlaceTrack(player, world, x + (xDisplace * 2) - xSideDisplace, y + 1, z + (zDisplace * 2) - zSideDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x + (xSideDisplace), y + 1, z + zSideDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x + (xDisplace * 2) + xSideDisplace, y + 1, z + (zDisplace * 2) + zSideDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x - (xSideDisplace), y + 1, z - zSideDisplace)) {
            return false;
        }

        //Top

        putDownSingleRail(world, x + (xDisplace * 2), y + 1, z + (zDisplace * 2), l, x + (xDisplace * 2), y + 1,
                z + (zDisplace * 2), 0, typeVariantStraight, false, x + (xDisplace), y + 1,
                z + (zDisplace), false, false);

        //Main

        placeTrack(world, x + (xDisplace), y + 1, z + (zDisplace), BlockIDs.tcRail.block, l);
        TileTCRail tcRail2 = (TileTCRail) world.getTileEntity(x + (xDisplace), y + 1, z + (zDisplace));
        tcRail2.setFacing(l);
        tcRail2.cx = x + (xDisplace);
        tcRail2.cy = y + 1;
        tcRail2.cz = z + (zDisplace);
        tcRail2.setType(type.getLabel());
        tcRail2.idDrop = this.type.getItem().item;

        //Bottom


        putDownSingleRail(world, x, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false,
                x + (xDisplace), y + 1, z + (zDisplace), false, false);
        //RIGHT
        putDownSingleRail(world, x + (xDisplace * 2) + (xSideDisplace), y + 1,
                z + (zDisplace * 2) + (zSideDisplace), sideFacing,
                x + (xDisplace) + (xSideDisplace), y + 1, z + (zDisplace * 2) + (zSideDisplace), 0,
                typeVariantDiagonal, false, x + (xDisplace), y + 1, z + (zDisplace),
                false, false);

        //Left

        putDownSingleRail(world, x - (xSideDisplace), y + 1,
                z - (zSideDisplace), sideFacing,
                x - (xSideDisplace), y + 1, z - (zSideDisplace), 0,
                typeVariantDiagonal, false, x + (xDisplace), y + 1, z + (zDisplace),
                false, false);
        //Left2

        putDownSingleRail(world, x + (xDisplace * 2) - (xSideDisplace), y + 1,
                z + (zDisplace * 2) - (zSideDisplace), sideFacing2,
                x - (xDisplace) - (xSideDisplace), y + 1, z + (zDisplace * 2) - (zSideDisplace), 0,
                typeVariantDiagonal, false, x + (xDisplace), y + 1, z + (zDisplace),
                false, false);

        //Right2

        putDownSingleRail(world, x + (xSideDisplace), y + 1,
                z + (zSideDisplace), sideFacing2,
                x + (xSideDisplace), y + 1, z + (zSideDisplace), 0,
                typeVariantDiagonal, false, x + (xDisplace), y + 1, z + (zDisplace),
                false, false);

        return true;
    }
    private boolean diagonalTwoWaysCrossing(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks type) {
        if (type.getLabel().contains("EMBEDDED")) {
            typeVariantDiagonal = EnumTracks.EMBEDDED_SMALL_DIAGONAL_STRAIGHT.getLabel();
        }


        if (!canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }

        int zDisplace = 0;
        int xDisplace = 0;
        int xSideDisplace = 0;
        int zSideDisplace = 0;
        int sideFacing = l;
        int sideFacing2 = l;

        if (l == 2) {
            zDisplace = -1;
            xSideDisplace = 1;
            sideFacing = 6;
            sideFacing2 = 5;
        }
        if (l == 0) {
            zDisplace = 1;
            xSideDisplace = -1;
            sideFacing = 4;
            sideFacing2 = 7;
        }
        if (l == 1) {
            xDisplace = -1;
            zSideDisplace = -1;
            sideFacing = 5;
            sideFacing2 = 4;
        }
        if (l == 3) {
            xDisplace = 1;
            zSideDisplace = 1;
            sideFacing = 7;
            sideFacing2 = 6;
        }

        if (!canPlaceTrack(player, world, x + xDisplace + xSideDisplace, y + 1, z + zDisplace + zSideDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x - xDisplace - (xSideDisplace), y + 1, z - zDisplace - zSideDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x + xDisplace - xSideDisplace, y + 1, z + zDisplace - zSideDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x - xDisplace + (xSideDisplace), y + 1, z - zDisplace + zSideDisplace)) {
            return false;
        }


        //Main
        placeTrack(world, x, y + 1, z, BlockIDs.tcRail.block, l);
        TileTCRail tcRail2 = (TileTCRail) world.getTileEntity(x, y + 1, z);
        tcRail2.setFacing(l);
        tcRail2.cx = x + (xDisplace);
        tcRail2.cy = y + 1;
        tcRail2.cz = z + (zDisplace);
        tcRail2.setType(type.getLabel());
        tcRail2.idDrop = this.type.getItem().item;

        //RIGHT
        putDownSingleRail(world, x + xDisplace + (xSideDisplace), y + 1,
                z + zDisplace + (zSideDisplace), sideFacing,
                x + (xDisplace) + (xSideDisplace), y + 1, z + zDisplace + (zSideDisplace), 0,
                typeVariantDiagonal, false, x, y + 1, z,
                false, false);

        //Left

        putDownSingleRail(world, x - xDisplace - (xSideDisplace), y + 1,
                z - zDisplace - (zSideDisplace), sideFacing,
                x - xDisplace - (xSideDisplace), y + 1, z - zDisplace - (zSideDisplace), 0,
                typeVariantDiagonal, false, x, y + 1, z,
                false, false);
        //Left2

        putDownSingleRail(world, x + xDisplace - (xSideDisplace), y + 1,
                z + zDisplace - (zSideDisplace), sideFacing2,
                x + (xDisplace) - (xSideDisplace), y + 1, z + zDisplace - (zSideDisplace), 0,
                typeVariantDiagonal, false, x, y + 1, z,
                false, false);

        //Right2

        putDownSingleRail(world, x - xDisplace + (xSideDisplace), y + 1,
                z - zDisplace + (zSideDisplace), sideFacing2,
                x - xDisplace + (xSideDisplace), y + 1, z - zDisplace + (zSideDisplace), 0,
                typeVariantDiagonal, false, x, y + 1, z,
                false, false);


        return true;
    }
    private boolean fourWaysCrossing(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks type) {

        if (type.getLabel().contains("EMBEDDED")) {
            typeVariantDiagonal = EnumTracks.EMBEDDED_SMALL_DIAGONAL_STRAIGHT.getLabel();
        }


        if (!canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }

        int zDisplace = 0;
        int xDisplace = 0;
        int xSideDisplace = 0;
        int zSideDisplace = 0;
        int sideFacing = l;
        int sideFacing2 = l;
        int facing = l;

        if (l == 2) {
            zDisplace = -1;
            xSideDisplace = 1;
            sideFacing = 6;
            sideFacing2 = 5;
            facing = 1;
        }
        if (l == 0) {
            zDisplace = 1;
            xSideDisplace = -1;
            sideFacing = 4;
            sideFacing2 = 7;
            facing = 1;
        }
        if (l == 1) {
            xDisplace = -1;
            zSideDisplace = -1;
            sideFacing = 5;
            sideFacing2 = 4;
            facing = 2;
        }
        if (l == 3) {
            xDisplace = 1;
            zSideDisplace = 1;
            sideFacing = 7;
            sideFacing2 = 6;
            facing = 2;
        }

        if (!canPlaceTrack(player, world, x + xDisplace + xSideDisplace, y + 1, z + zDisplace + zSideDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x - xDisplace - (xSideDisplace), y + 1, z - zDisplace - zSideDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x + xDisplace - xSideDisplace, y + 1, z + zDisplace - zSideDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x - xDisplace + (xSideDisplace), y + 1, z - zDisplace + zSideDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x + xDisplace, y + 1, z + zDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x - xDisplace, y + 1, z - zDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x + xSideDisplace, y + 1, z + zSideDisplace)) {
            return false;
        }
        if (!canPlaceTrack(player, world, x - xSideDisplace, y + 1, z - zSideDisplace)) {
            return false;
        }


        //Main
        placeTrack(world, x, y + 1, z, BlockIDs.tcRail.block, l);
        TileTCRail tcRail2 = (TileTCRail) world.getTileEntity(x, y + 1, z);
        tcRail2.setFacing(l);
        tcRail2.cx = x + (xDisplace);
        tcRail2.cy = y + 1;
        tcRail2.cz = z + (zDisplace);
        tcRail2.setType(type.getLabel());
        tcRail2.idDrop = this.type.getItem().item;


        //T
        putDownSingleRail(world, x + xDisplace, y + 1,
                z + zDisplace, l,
                x + (xDisplace), y + 1, z + zDisplace, 0,
                typeVariantStraight, false, x, y + 1, z,
                false, false);
        //B
        putDownSingleRail(world, x - xDisplace, y + 1,
                z - zDisplace, l,
                x - (xDisplace), y + 1, z - zDisplace, 0,
                typeVariantStraight, false, x, y + 1, z,
                false, false);
        //L
        putDownSingleRail(world, x + xSideDisplace, y + 1,
                z + zSideDisplace, facing,
                x + (xSideDisplace), y + 1, z + zSideDisplace, 0,
                typeVariantStraight, false, x, y + 1, z,
                false, false);
        //R
        putDownSingleRail(world, x - xSideDisplace, y + 1,
                z - zSideDisplace, facing,
                x - (xSideDisplace), y + 1, z - zSideDisplace, 0,
                typeVariantStraight, false, x, y + 1, z,
                false, false);


        //BR
        putDownSingleRail(world, x + xDisplace + (xSideDisplace), y + 1,
                z + zDisplace + (zSideDisplace), sideFacing,
                x + (xDisplace) + (xSideDisplace), y + 1, z + zDisplace + (zSideDisplace), 0,
                typeVariantDiagonal, false, x, y + 1, z,
                false, false);

        //TL

        putDownSingleRail(world, x - xDisplace - (xSideDisplace), y + 1,
                z - zDisplace - (zSideDisplace), sideFacing,
                x - xDisplace - (xSideDisplace), y + 1, z - zDisplace - (zSideDisplace), 0,
                typeVariantDiagonal, false, x, y + 1, z,
                false, false);
        //TR

        putDownSingleRail(world, x + xDisplace - (xSideDisplace), y + 1,
                z + zDisplace - (zSideDisplace), sideFacing2,
                x + (xDisplace) - (xSideDisplace), y + 1, z + zDisplace - (zSideDisplace), 0,
                typeVariantDiagonal, false, x, y + 1, z,
                false, false);

        //BL

        putDownSingleRail(world, x - xDisplace + (xSideDisplace), y + 1,
                z - zDisplace + (zSideDisplace), sideFacing2,
                x - xDisplace + (xSideDisplace), y + 1, z - zDisplace + (zSideDisplace), 0,
                typeVariantDiagonal, false, x, y + 1, z,
                false, false);


        return true;
    }
    private boolean smallDiagonalStraight(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks type) {


        TileTCRailGag[] tileGag = new TileTCRailGag[2];

        int dx = 1;
        int dz = 1;

        if (l == 6) dz = -1;

        if (l == 4) dx = -1;

        if (l == 5){
            dx = -1;
            dz = -1;
        }

        if (!canPlaceTrack(player, world, x, y + 1, z) || !canPlaceTrack(player, world, x, y + 1, z + dz) || !canPlaceTrack(player, world, x + dx, y + 1, z)) {
            return false;
        }

        placeTrack(world, x, y + 1, z, BlockIDs.tcRail.block, l);
        TileTCRail tcRail = (TileTCRail) world.getTileEntity(x, y + 1, z);

        placeTrack(world, x, y + 1, z + dz, BlockIDs.tcRailGag.block, l);
        tileGag[0] = (TileTCRailGag) world.getTileEntity(x, y + 1, z + dz);
        tileGag[0].canPlaceRollingstock = false;

        placeTrack(world, x + dx , y + 1, z, BlockIDs.tcRailGag.block, l);
        tileGag[1] = (TileTCRailGag) world.getTileEntity(x + dx, y + 1, z);
        tileGag[1].canPlaceRollingstock = false;
        tcRail.setFacing(l);
        tcRail.setType(type.getLabel());
        tcRail.setRailLength(1D);
        tcRail.idDrop = this.type.getItem().item;

        for (TileTCRailGag tileTCRailGag : tileGag) {
            if (player != null && tileTCRailGag == null) {
                player.addChatMessage(new ChatComponentText("There was a problem when placing the track. Possibly too many tracks around"));
                return false;
            }
            tileTCRailGag.originX = x;
            tileTCRailGag.originY = y + 1;
            tileTCRailGag.originZ = z;
            tileTCRailGag.type = type.getLabel();
            tileTCRailGag.canPlaceRollingstock = false;
        }
        return true;
    }
    private boolean diagonalStraight(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks type) {

        int trackLength = 0;
        if (type == EnumTracks.LONG_DIAGONAL_STRAIGHT || type == EnumTracks.EMBEDDED_LONG_DIAGONAL_STRAIGHT) trackLength = 3;
        if (type == EnumTracks.VERY_LONG_DIAGONAL_STRAIGHT || type == EnumTracks.EMBEDDED_VERY_LONG_DIAGONAL_STRAIGHT) trackLength = 9;

        TileTCRail[] tcRail = new TileTCRail[(trackLength / 3) + 1];
        TileTCRailGag[] tcRailGag = new TileTCRailGag[8 * (trackLength / 3) + 8];

        int dx = 1;
        int dz = 1;

        if (l == 6) dz = -1;

        if (l == 4) dx = -1;

        if (l == 5){
            dx = -1;
            dz = -1;
        }

        for (int i = 0; i <= trackLength; i += 3){
           if (!canPlaceTrack(player, world, x + (i * dx), y + 1, z + (i * dz))
                   || !canPlaceTrack(player, world, x + (i * dx) + dx, y + 1, z + (i * dz) + dz)
                   || !canPlaceTrack(player, world, x + (i * dx) + (2*dx), y + 1, z + (i * dz) + (2*dz)))
                return false;

            for(int j = 0; j < 3 ; j++){

                if(!canPlaceTrack(player, world, x + (i * dx) + (j * dx ) + dx , y + 1, z + (i * dz) + (j * dz))
                    || !canPlaceTrack(player, world, x + (i * dx) + (j * dx), y + 1 , z + (i * dz) + (j * dz) + dz))
                   return false;
            }


        }

        for (int i = 0; i <= trackLength; i += 3){
            placeTrack(world, x + (i * dx), y+ 1, z + (i * dz), BlockIDs.tcRail.block, l);
            tcRail[i / 3] = (TileTCRail) world.getTileEntity(x + (i * dx), y+ 1, z + (i * dz));
            tcRail[i / 3].setFacing(l);
            tcRail[(i / 3)].setType(type.getLabel());
            tcRail[0].setRailLength((double) trackLength + 3);
            tcRail[0].idDrop = this.type.getItem().item;
            tcRail[i / 3].setRailLength(3D);
            if (i / 3 != 0){
               tcRail[i / 3].isLinkedToRail = true;
               tcRail[i / 3].linkedX = x + dx;
                tcRail[i / 3].linkedY = y + 1;
                tcRail[i / 3].linkedZ = z + dz;
            }

            placeTrack(world, x + (i * dx) + dx, y + 1, z + (i * dz) + dz, BlockIDs.tcRailGag.block, l);
            tcRailGag[(3* i) - (i / 3)] = (TileTCRailGag) world.getTileEntity(x + (i * dx) + dx, y + 1, z + (i * dz) + dz);
            tcRailGag[(3* i) - (i / 3)].canPlaceRollingstock = true;
            placeTrack(world,x + (i * dx) + (2 * dx), y + 1, z + (i * dz) + (2 * dz), BlockIDs.tcRailGag.block, l);
            tcRailGag[((3* i) - (i / 3)) + 1] = (TileTCRailGag) world.getTileEntity(x + (i * dx) +  (2 * dx), y + 1, z + (i * dz) + (2 * dz));
            tcRailGag[((3* i) - (i / 3)) + 1].canPlaceRollingstock = true;
            for (int j = 0; j < 3; j++){
                placeTrack(world, x + (i * dx) + (j * dx ) + dx , y + 1, z + (i * dz) + (j * dz), BlockIDs.tcRailGag.block, l);
                tcRailGag[((3 * i) - (i / 3)) + ((2 * j) + 2)] = (TileTCRailGag) world.getTileEntity(x + (i * dx) + (j * dx) + dx, y + 1, z + (i * dz) + (j * dz) );
                tcRailGag[((3 * i) - (i / 3)) + ((2 * j) + 2)].canPlaceRollingstock = false;
                placeTrack(world, x + (i * dx) + (j * dx), y + 1, z + (i * dz) + (j * dz) + dz, BlockIDs.tcRailGag.block, l);
                tcRailGag[((3 * i) - (i / 3)) + ((2 * j) + 3)] = (TileTCRailGag) world.getTileEntity(x + (i * dx) + (j * dx), y + 1, z + (i * dz) + (j * dz) + dz);
                tcRailGag[((3 * i) - (i / 3)) + ((2 * j) + 3)].canPlaceRollingstock = false;
            }

        }

        for (TileTCRailGag tileTCRailGag : tcRailGag) {
            if (player != null && tileTCRailGag == null) {
                player.addChatMessage(new ChatComponentText("There was a problem when placing the track. Possibly too many tracks around"));
                return false;
            }
            tileTCRailGag.originX = x;
            tileTCRailGag.originY = y + 1;
            tileTCRailGag.originZ = z;
            tileTCRailGag.type = type.getLabel();

        }

        return true;
    }


    private boolean turnTrack(EntityPlayer player, World world, int x, int y, int z, int dir, EnumTracks tempType, float pyaw, int[] xArray, int[]zArray, float radius){

        float yaw = MathHelper.wrapAngleTo180_float(player != null ? player.rotationYaw : pyaw);
        String orientation = getTrackOrientation(dir,yaw);

        if (orientation.equals("right")) {
            xArray = flipArraySign(xArray);
        }

        double cx = 0;
        double cz = 0;
        int[] usedXArray = new int[0];
        int[] usedZArray = new int[0];

        int xOffset = 0;
        int zOffset = 0;

        if (dir == 2){
            usedXArray = flipArraySign(xArray,x,true);
            usedZArray = flipArraySign(zArray,z,true);
            cx = orientation.equals("right") ? -(radius + 0.5f) : (radius - 0.5);
            cz = -1f;
        }
        else if (dir == 0){
            usedXArray = flipArraySign(xArray,x,false);
            usedZArray = flipArraySign(zArray,z,false);
            cx = orientation.equals("right") ? radius - 0.5f : -(radius + 0.5);
        }
        else if (dir == 1){
            usedXArray = flipArraySign(zArray, x, true);
            usedZArray = flipArraySign(xArray, z, false);
            cz = orientation.equals("right") ? -(-radius + 0.5f) : -(radius + 0.5f);
            cx = -1f;
        }
        else if (dir == 3){
            usedXArray = flipArraySign(zArray, x, false);
            usedZArray = flipArraySign(xArray, z, true);
            cz = orientation.equals("right") ? -(radius + 0.5f) : -(-radius + 0.5f) ;
        }

        if (usedXArray.length == 0) {
            return false;
        }

        for (int check = 0; check < usedXArray.length; check++){
            if (!canPlaceTrack(player, world, usedXArray[check], y + 1, usedZArray[check])){
                return false;
            }
        }

        if (!putDownTurn(player, world, false, x, y, z, usedXArray, usedZArray, dir, false, dir, (x + xOffset), (z + zOffset), radius, x - cx,
                y + 1, z - cz, tempType.getLabel(), tempType.getItem().item))
            return false;

        return true;
    }

    public boolean SCurve(EntityPlayer player, World world, int x, int y, int z, int dir, EnumTracks tempType, float pyaw, int[] xArray, int[]zArray, int[] xArray2, int[] zArray2, float radius, int length, int width){
        float yaw = MathHelper.wrapAngleTo180_float(player != null ? player.rotationYaw : pyaw);
        String ori = getTrackOrientation(dir,yaw);

        if (ori.equals("right")) {
            xArray = flipArraySign(xArray);
            xArray2 = flipArraySign(xArray2);
        }

        int xOffset = 0;
        int zOffset = 0;
        double cx = 0;
        double cz = 0;
        double cx2 = 0;
        double cz2 = 0;
        int[] usedXArray = new int[0];
        int[] usedZArray = new int[0];
        int[] usedXArray2 = new int[0];
        int[] usedZArray2 = new int[0];

        if (dir==2) {
            usedXArray = flipArraySign(xArray,x,true);
            usedZArray = flipArraySign(zArray,z,true);
            usedXArray2 = flipArraySign(xArray2, x, true);
            usedZArray2 = flipArraySign(zArray2, z, true);

            if (ori.equals("right")) {
                xOffset = width;
                cx = -(radius + 0.5f);
                cx2 = -(-radius + (width - 0.5f));
            } else {
                xOffset = -width;
                cx = (radius - 0.5);
                cx2 = -(radius - width + 1.5f);

            }
            zOffset = -length;
            cz = -1f;
            cz2 = -(-length + 1);
        }
        else if (dir == 0) {
            usedXArray = flipArraySign(xArray,x,false);
            usedZArray = flipArraySign(zArray,z,false);
            usedXArray2 = flipArraySign(xArray2, x, false);
            usedZArray2 = flipArraySign(zArray2, z, false);

            if (ori.equals("right")) {
                xOffset = -width;
                cx = radius - 0.5f;
                cx2 = -(radius - (width - 0.5f - 1));

            } else {
                xOffset = width;
                cx = -(radius + 0.5);
                cx2 = -(-radius + width - 0.5f);
            }
            cz2 = -(length);
            zOffset = length;

        }
        else if (dir == 1) {
            usedXArray = flipArraySign(zArray, x, true);
            usedZArray = flipArraySign(xArray, z, false);
            usedXArray2 = flipArraySign(zArray2, x, true);
            usedZArray2 = flipArraySign(xArray2, z, false);

            if (ori.equals("right")) {
                zOffset = -width;
                cz = -(-radius + 0.5f);
                cz2 = -(radius - width + 1.5f);
            } else {
                zOffset = width;
                cz = -(radius + 0.5f);
                cz2 = -(-radius + (width - 0.5f));
            }
            xOffset = -length;
            cx = -1f;
            cx2 = -(-length + 1);
        }
        else if (dir == 3){
            usedXArray = flipArraySign(zArray, x, false);
            usedZArray = flipArraySign(xArray, z, true);
            usedXArray2 = flipArraySign(zArray2, x, false);
            usedZArray2 = flipArraySign(xArray2, z, true);
            if (ori.equals("right")) {
                zOffset = width;
                cz = -(radius + 0.5f);
                cz2 = radius - (width - 0.5f);

            } else {
                zOffset = -width;
                cz = -(-radius + 0.5f);
                cz2 = -(radius - (width - 0.5) + 1);
            }
            cx2 = -length;
            xOffset = length;

        }
        if (usedXArray.length == 0) {
            return false;
        }

        for (int check = 0; check < usedXArray.length; check++){
            if (!canPlaceTrack(player, world, usedXArray[check], y + 1, usedZArray[check])
                    || !canPlaceTrack(player, world, usedXArray2[check], y + 1, usedZArray2[check])){
                return false;
            }
        }


        if (!putDownTurn(player, world, false, x, y, z, usedXArray, usedZArray, dir, false, dir, (x + xOffset), (z + zOffset), radius, x - cx,
                y + 1, z - cz, tempType.getLabel(), tempType.getItem().item))
            return false;
        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x, y + 1, z);


        if (tcRailTurn != null) {
            tcRailTurn.hasModel = true;
            if (!putDownTurn(player, world, false, x, y, z, usedXArray2, usedZArray2, dir, false, dir, (x + xOffset), (z + zOffset), radius, x - cx2,
                    y + 1, z - cz2, EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), (Item) null))
                return false;
            TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(usedXArray2[0], y + 1, usedZArray2[0]);

            if (tcRailTurn2 != null) {
                tcRailTurn2.hasModel = false;
                tcRailTurn2.isLinkedToRail = true;
                tcRailTurn2.linkedX = x;
                tcRailTurn2.linkedY = y + 1;
                tcRailTurn2.linkedZ = z;
            }
            tcRailTurn.isLinkedToRail = true;
            tcRailTurn.linkedX = usedXArray2[0];
            tcRailTurn.linkedY = y + 1;
            tcRailTurn.linkedZ = usedZArray2[0];
        }
        return true;

    }
    public boolean largeRightCurvedSlope(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType) {

        int[] xArray = {0, 0, 1, 0, 1, 1, 2, 2, 2, 3, 3, 4};
        int[] zArray = {0, 1, 1, 2, 2, 3, 2, 3, 4, 3, 4, 4};

        if (l == 2) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, true), l, 4.5, x + 5,
                    y + 1, z + 1, 0.1558f, 6.3639, tempType.getLabel(), tempType.getItem().item)
                    ;
        }
        if (l == 0) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, false), l, 4.5, x - 4,
                    y + 1, z, 0.1558f, 6.3639, tempType.getLabel(), tempType.getItem().item);
        }
        if (l == 1) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, true), l, 4.5, x + 1,
                    y + 1, z - 4, 0.1558f, 6.3639, tempType.getLabel(), tempType.getItem().item);
        }
        if (l == 3) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, false), l, 4.5, x,
                    y + 1, z + 5, 0.1558f, 6.3639, tempType.getLabel(), tempType.getItem().item);
        }
        return true;
    }
    public boolean largeLeftCurvedSlope(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType) {

        int[] xArray = {0, 0, 1, 0, 1, 1, 2, 2, 2, 3, 3, 4};
        int[] zArray = {0, 1, 1, 2, 2, 3, 2, 3, 4, 3, 4, 4};

        if (l == 2) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, true), l, 4.5, x - 4,
                    y + 1, z + 1, 0.1558f, 6.3639, tempType.getLabel(), tempType.getItem().item)
                    ;
        }
        if (l == 0) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, false), l, 4.5, x + 5,
                    y + 1, z, 0.1558f, 6.3639, tempType.getLabel(), tempType.getItem().item);
        }
        if (l == 1) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, false), l, 4.5, x + 1,
                    y + 1, z + 5, 0.1558f, 6.3639, tempType.getLabel(), tempType.getItem().item);
        }
        if (l == 3) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, true), l, 4.5, x,
                    y + 1, z - 4, 0.1558f, 6.3639, tempType.getLabel(), tempType.getItem().item);
        }
        return true;
    }
    public boolean veryLargeRightCurvedSlope(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType) {
        int[] xArray = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8};
        int[] zArray = {0, 1, 2, 3, 4, 2, 3, 4, 5, 4, 5, 6, 5, 6, 7, 6, 7, 7, 8, 7, 8, 8, 8};

        if (l == 2) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, true), l, 8.5, x + 9,
                    y + 1, z + 1, 0.083003f, 12.0208, tempType.getLabel(), tempType.getItem().item)
                    ;
        }
        if (l == 0) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, false), l, 8.5, x - 8,
                    y + 1, z, 0.083003f, 12.0208, tempType.getLabel(), tempType.getItem().item);
        }
        if (l == 1) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, true), l, 8.5, x + 1,
                    y + 1, z - 8, 0.083003f, 12.0208, tempType.getLabel(), tempType.getItem().item);
        }
        if (l == 3) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, false), l, 8.5, x,
                    y + 1, z + 9, 0.083003f, 12.0208, tempType.getLabel(), tempType.getItem().item);
        }
        return true;
    }
    public boolean veryLargeLeftCurvedSlope(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType) {

        int[] xArray = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 5, 5, 6, 6, 7, 8};
        int[] zArray = {0, 1, 2, 3, 4, 2, 3, 4, 5, 4, 5, 6, 5, 6, 7, 6, 7, 7, 8, 7, 8, 8, 8};

        if (l == 2) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, true), l, 8.5, x - 8,
                    y + 1, z + 1, 0.083003f, 12.0208, tempType.getLabel(), tempType.getItem().item)
                    ;
        }
        if (l == 0) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, false), l, 8.5, x + 9,
                    y + 1, z, 0.083003f, 12.0208, tempType.getLabel(), tempType.getItem().item);
        }
        if (l == 1) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, false), l, 8.5, x + 1,
                    y + 1, z + 9, 0.083003f, 12.0208, tempType.getLabel(), tempType.getItem().item);
        }
        if (l == 3) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, true), l, 8.5, x,
                    y + 1, z - 8, 0.083003f, 12.0208, tempType.getLabel(), tempType.getItem().item);
        }
        return true;
    }
    public boolean superLargeRightCurvedSlope(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType) {
        int[] xArray = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9, 9, 10, 10, 10, 11, 11, 12, 12, 13, 13, 14, 15};
        int[] zArray = {0, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 7, 5, 6, 7, 8, 9, 8, 9, 10, 9, 10, 11, 10, 11, 12, 11, 12, 13, 12, 13, 12, 13, 14, 13, 14, 13, 14, 15, 14, 15, 14, 15, 14, 15, 15, 15};

        if (l == 2) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, true), l, 15.5, x + 16,
                    y + 1, z + 1, 0.045589f, 21.9203, tempType.getLabel(), tempType.getItem().item)
                    ;
        }
        if (l == 0) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, false), l, 15.5, x - 15,
                    y + 1, z, 0.045589f, 21.9203, tempType.getLabel(), tempType.getItem().item);
        }
        if (l == 1) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, true), l, 15.5, x + 1,
                    y + 1, z - 15, 0.045589f, 21.9203, tempType.getLabel(), tempType.getItem().item);
        }
        if (l == 3) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, false), l, 15.5, x,
                    y + 1, z + 16, 0.045589f, 21.9203, tempType.getLabel(), tempType.getItem().item);
        }
        return true;
    }
    public boolean superLargeLeftCurvedSlope(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType) {

        int[] xArray = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 8, 8, 8, 9, 9, 10, 10, 10, 11, 11, 12, 12, 13, 13, 14, 15};
        int[] zArray = {0, 1, 2, 3, 4, 5, 2, 3, 4, 5, 6, 7, 5, 6, 7, 8, 9, 8, 9, 10, 9, 10, 11, 10, 11, 12, 11, 12, 13, 12, 13, 12, 13, 14, 13, 14, 13, 14, 15, 14, 15, 14, 15, 14, 15, 15, 15};

        if (l == 2) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, true), l, 15.5, x - 15,
                    y + 1, z + 1, 0.045589f, 21.9203, tempType.getLabel(), tempType.getItem().item);
        }
        if (l == 0) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, false), l, 15.5, x + 16,
                    y + 1, z, 0.045589f, 21.9203, tempType.getLabel(), tempType.getItem().item);
        }
        if (l == 1) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, false), l, 15.5, x + 1,
                    y + 1, z + 16, 0.045589f, 21.9203, tempType.getLabel(), tempType.getItem().item);
        }
        if (l == 3) {
            return putDownSlopedTurn(player, world, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, true), l, 15.5, x,
                    y + 1, z - 15, 0.045589f, 21.9203, tempType.getLabel(), tempType.getItem().item);
        }
        return true;
    }
    private boolean parallelRightSwitchEast(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String typeVariantStraight, Item idVariantSwitch) {
        for (int check = 1; check < 10; check++) {
            if (!canPlaceTrack(player, world, x + check, y + 1, z))
                return false;
        }
        if (!canPlaceTrack(player, world, x + 3, y + 1, z + 1) || !canPlaceTrack(player, world, x + 4, y + 1, z + 1) || !canPlaceTrack(player, world, x + 5, y + 1, z + 1) || !canPlaceTrack(player, world, x + 4, y + 1, z + 2) || !canPlaceTrack(player, world, x + 5, y + 1, z + 2) || !canPlaceTrack(player, world, x + 6, y + 1, z + 2) || !canPlaceTrack(player, world, x + 7, y + 1, z + 2) || !canPlaceTrack(player, world, x + 8, y + 1, z + 2) || !canPlaceTrack(player, world, x + 6, y + 1, z + 3) || !canPlaceTrack(player, world, x + 7, y + 1, z + 3) || !canPlaceTrack(player, world, x + 8, y + 1, z + 3) || !canPlaceTrack(player, world, x + 9, y + 1, z + 3) || !canPlaceTrack(player, world, x + 10, y + 1, z + 3) || !canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }
        int[] xArray = {x + 3, x + 2, x + 4, x + 5};
        int[] zArray = {z + 1, z + 1, z + 1, z + 1};
        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 3, x + 10, z, 8.5, x + 0.5, y + 1, z + 9,
                EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), idVariantSwitch))
            return false;
        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 3, y + 1, z + 1);
        if (tcRailTurn != null) {
            tcRailTurn.hasModel = false;

            /** Switch rail 1 */
            putDownSingleRail(world, x + 1, y + 1, z, l, x + 0.5, y + 1, z + 9, 8.5, tempType.getLabel(), true, x + 3, y + 1, z + 1, true, false);

            /** Switch rail 2 **/
            putDownSingleRail(world, x + 2, y + 1, z, l, x + 0.5, y + 1, z + 9, 8.5, typeVariantStraight, false, x + 3, y + 1, z + 1, true, false);
            /** Switch rail 3 **/
            putDownSingleRail(world, x + 3, y + 1, z, l, x + 0.5, y + 1, z + 9, 8.5, typeVariantStraight, false, x + 3, y + 1, z + 1, true, false);

            int[] xArray2 = {x + 4, x + 5, x + 6, x + 7, x + 8, x + 6, x + 7, x + 8, x + 9};
            int[] zArray2 = {z + 2, z + 2, z + 2, z + 2, z + 2, z + 3, z + 3, z + 3, z + 3};
            if (!putDownTurn(player, world, false, x, y, z, xArray2, zArray2, 0, true, 3, x + 10, z + 3, 8.5, x + 10, y + 1, z - 5,
                    EnumTracks.MEDIUM_LEFT_TURN.getLabel(), null))
                return false;
            TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x + 4, y + 1, z + 2);
            if (tcRailTurn2 != null) {
                tcRailTurn2.hasModel = false;
                tcRailTurn2.isLinkedToRail = true;
                tcRailTurn2.linkedX = x + 3;
                tcRailTurn2.linkedY = y + 1;
                tcRailTurn2.linkedZ = z + 1;
            }
            tcRailTurn.isLinkedToRail = true;
            tcRailTurn.linkedX = x + 4;
            tcRailTurn.linkedY = y + 1;
            tcRailTurn.linkedZ = z + 2;
        }
        /** Put down straight **/
        putDownSingleRail(world, x + 4, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false, x + 3, y + 1, z + 1, false, false);
        for (int straight = 5; straight < 10; straight++) {
            putDownSingleRail(world, x + straight, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, true, x + 3, y + 1, z + 1, false, false);
        }
        return true;
    }
    private boolean parallelRightSwitchWest(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String typeVariantStraight, Item idVariantSwitch) {
        for (int check = 1; check < 10; check++) {
            if (!canPlaceTrack(player, world, x - check, y + 1, z))
                return false;
        }
        if (!canPlaceTrack(player, world, x - 3, y + 1, z - 1) || !canPlaceTrack(player, world, x - 4, y + 1, z - 1) || !canPlaceTrack(player, world, x - 5, y + 1, z - 1) || !canPlaceTrack(player, world, x - 4, y + 1, z - 2) || !canPlaceTrack(player, world, x - 5, y + 1, z - 2) || !canPlaceTrack(player, world, x - 6, y + 1, z - 2) || !canPlaceTrack(player, world, x - 7, y + 1, z - 2) || !canPlaceTrack(player, world, x - 8, y + 1, z - 2) || !canPlaceTrack(player, world, x - 6, y + 1, z - 3) || !canPlaceTrack(player, world, x - 7, y + 1, z - 3) || !canPlaceTrack(player, world, x - 8, y + 1, z - 3) || !canPlaceTrack(player, world, x - 9, y + 1, z - 3) || !canPlaceTrack(player, world, x - 10, y + 1, z - 3) || !canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }
        int[] xArray = {x - 3, x - 2, x - 4, x - 5};
        int[] zArray = {z - 1, z - 1, z - 1, z - 1};
        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 1, x - 10, z, 8.5, x + 0.5, y + 1, z - 8,
                EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), idVariantSwitch))
            return false;
        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 3, y + 1, z - 1);
        if (tcRailTurn != null) {
            tcRailTurn.hasModel = false;

            /** Switch rail 1 */
            putDownSingleRail(world, x - 1, y + 1, z, l, x + 0.5, y + 1, z - 8, 8.5, tempType.getLabel(), true, x - 3, y + 1, z - 1, true, false);

            /** Switch rail 2 **/
            putDownSingleRail(world, x - 2, y + 1, z, l, x + 0.5, y + 1, z - 8, 8.5, typeVariantStraight, false, x - 3, y + 1, z - 1, true, false);
            /** Switch rail 3 **/
            putDownSingleRail(world, x - 3, y + 1, z, l, x + 0.5, y + 1, z - 8, 8.5, typeVariantStraight, false, x - 3, y + 1, z - 1, true, false);

            int[] xArray2 = {x - 4, x - 5, x - 6, x - 7, x - 8, x - 6, x - 7, x - 8, x - 9};
            int[] zArray2 = {z - 2, z - 2, z - 2, z - 2, z - 2, z - 3, z - 3, z - 3, z - 3};
            if (!putDownTurn(player, world, false, x, y, z, xArray2, zArray2, 2, true, 1, x - 10, z - 3, 8.5, x - 9, y + 1, z + 6,
                    EnumTracks.MEDIUM_LEFT_TURN.getLabel(), null))
                return false;
            TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x - 4, y + 1, z - 2);
            if (tcRailTurn2 != null) {
                tcRailTurn2.hasModel = false;
                tcRailTurn2.isLinkedToRail = true;
                tcRailTurn2.linkedX = x - 3;
                tcRailTurn2.linkedY = y + 1;
                tcRailTurn2.linkedZ = z - 1;
            }
            tcRailTurn.isLinkedToRail = true;
            tcRailTurn.linkedX = x - 4;
            tcRailTurn.linkedY = y + 1;
            tcRailTurn.linkedZ = z - 2;
        }

        /** Put down straight **/
        putDownSingleRail(world, x - 4, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false, x - 3, y + 1, z - 1, false, false);
        for (int straight = 5; straight < 10; straight++) {
            putDownSingleRail(world, x - straight, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, true, x - 3, y + 1, z - 1, false, false);
        }
        return true;
    }
    private boolean parallelRightSwitchSouth(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String typeVariantStraight, Item idVariantSwitch) {
        for (int check = 1; check < 10; check++) {
            if (!canPlaceTrack(player, world, x, y + 1, z + check))
                return false;
        }
        if (!canPlaceTrack(player, world, x - 1, y + 1, z + 3) || !canPlaceTrack(player, world, x - 1, y + 1, z + 4) || !canPlaceTrack(player, world, x - 1, y + 1, z + 5) || !canPlaceTrack(player, world, x - 2, y + 1, z + 4) || !canPlaceTrack(player, world, x - 2, y + 1, z + 5) || !canPlaceTrack(player, world, x - 2, y + 1, z + 6) || !canPlaceTrack(player, world, x - 2, y + 1, z + 7) || !canPlaceTrack(player, world, x - 2, y + 1, z + 8) || !canPlaceTrack(player, world, x - 3, y + 1, z + 6) || !canPlaceTrack(player, world, x - 3, y + 1, z + 7) || !canPlaceTrack(player, world, x - 3, y + 1, z + 8) || !canPlaceTrack(player, world, x - 3, y + 1, z + 9) || !canPlaceTrack(player, world, x - 3, y + 1, z + 10) || !canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }
        int[] xArray = {x - 1, x - 1, x - 1, x - 1};
        int[] zArray = {z + 3, z + 2, z + 4, z + 5};
        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 0, x, z + 10, 8.5, x - 8, y + 1, z + 0.5,
                EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), idVariantSwitch))
            return false;
        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z + 3);
        if (tcRailTurn != null) {
            tcRailTurn.hasModel = false;

            /** Switch rail 1 */
            putDownSingleRail(world, x, y + 1, z + 1, l, x - 8, y + 1, z + 0.5, 8.5, tempType.getLabel(), true, x - 1, y + 1, z + 3, true, false);

            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z + 2, l, x - 8, y + 1, z + 0.5, 8.5, typeVariantStraight, false, x - 1, y + 1, z + 3, true, false);
            /** Switch rail 3 **/
            putDownSingleRail(world, x, y + 1, z + 3, l, x - 8, y + 1, z + 0.5, 8.5, typeVariantStraight, false, x - 1, y + 1, z + 3, true, false);

            int[] xArray2 = {x - 2, x - 2, x - 2, x - 2, x - 2, x - 3, x - 3, x - 3, x - 3};
            int[] zArray2 = {z + 4, z + 5, z + 6, z + 7, z + 8, z + 6, z + 7, z + 8, z + 9};
            if (!putDownTurn(player, world, false, x, y, z, xArray2, zArray2, 1, true, 0, x - 3, z + 10, 8.5, x + 6, y + 1, z + 10,
                    EnumTracks.MEDIUM_LEFT_TURN.getLabel(), null))
                return false;
            TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x - 2, y + 1, z + 4);
            if (tcRailTurn2 != null) {
                tcRailTurn2.hasModel = false;
                tcRailTurn2.isLinkedToRail = true;
                tcRailTurn2.linkedX = x - 1;
                tcRailTurn2.linkedY = y + 1;
                tcRailTurn2.linkedZ = z + 3;
            }
            tcRailTurn.isLinkedToRail = true;
            tcRailTurn.linkedX = x - 2;
            tcRailTurn.linkedY = y + 1;
            tcRailTurn.linkedZ = z + 4;
        }
        /** Put down straight **/
        putDownSingleRail(world, x, y + 1, z + 4, l, x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z + 3, false, false);
        for (int straight = 5; straight < 10; straight++) {
            putDownSingleRail(world, x, y + 1, z + straight, l, x, y + 1, z, 0, typeVariantStraight, true, x - 1, y + 1, z + 3, false, false);
        }
        return true;
    }
    private boolean parallelRightSwitchNorth(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String typeVariantStraight, Item idVariantSwitch) {
        for (int check = 1; check < 10; check++) {
            if (!canPlaceTrack(player, world, x, y + 1, z - check))
                return false; /** checks if the next 11 blocks can be placed */
        }
        if (!canPlaceTrack(player, world, x + 1, y + 1, z - 3) ||
                !canPlaceTrack(player, world, x + 1, y + 1, z - 4) ||
                !canPlaceTrack(player, world, x + 1, y + 1, z - 5) ||
                !canPlaceTrack(player, world, x + 2, y + 1, z - 4) ||
                !canPlaceTrack(player, world, x + 2, y + 1, z - 5) ||
                !canPlaceTrack(player, world, x + 2, y + 1, z - 6) ||
                !canPlaceTrack(player, world, x + 2, y + 1, z - 7) ||
                !canPlaceTrack(player, world, x + 2, y + 1, z - 8) ||
                !canPlaceTrack(player, world, x + 3, y + 1, z - 6) ||
                !canPlaceTrack(player, world, x + 3, y + 1, z - 7) ||
                !canPlaceTrack(player, world, x + 3, y + 1, z - 8) ||
                !canPlaceTrack(player, world, x + 3, y + 1, z - 9) ||
                !canPlaceTrack(player, world, x + 3, y + 1, z - 10) ||
                !canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }
        int[] xArray = {x + 1, x + 1, x + 1, x + 1};
        int[] zArray = {z - 3, z - 2, z - 4, z - 5};
        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 2, x, z - 10, 8.5, x + 9, y + 1, z + 0.5,
                EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), idVariantSwitch))
            return false;
        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z - 3);
        if (tcRailTurn != null) {
            tcRailTurn.hasModel = false;

            /** Switch rail 1 */
            putDownSingleRail(world, x, y + 1, z - 1, l, x + 9, y + 1, z + 0.5, 8.5, tempType.getLabel(), true, x + 1, y + 1, z - 3, true, false);

            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z - 2, l, x + 9, y + 1, z + 0.5, 8.5, typeVariantStraight, false, x + 1, y + 1, z - 3, true, false);
            /** Switch rail 3 **/
            putDownSingleRail(world, x, y + 1, z - 3, l, x + 9, y + 1, z + 0.5, 8.5, typeVariantStraight, false, x + 1, y + 1, z - 3, true, false);

            int[] xArray2 = {x + 2, x + 2, x + 2, x + 2, x + 2, x + 3, x + 3, x + 3, x + 3};
            int[] zArray2 = {z - 4, z - 5, z - 6, z - 7, z - 8, z - 6, z - 7, z - 8, z - 9};
            if (!putDownTurn(player, world, false, x, y, z, xArray2, zArray2, 3, true, 2, x + 3, z - 10, 8.5, x - 5, y + 1, z - 9,
                    EnumTracks.MEDIUM_LEFT_TURN.getLabel(), null))
                return false;
            TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x + 2, y + 1, z - 4);
            if (tcRailTurn2 != null) {
                tcRailTurn2.hasModel = false;
                tcRailTurn2.isLinkedToRail = true;
                tcRailTurn2.linkedX = x + 1;
                tcRailTurn2.linkedY = y + 1;
                tcRailTurn2.linkedZ = z - 3;
            }
            tcRailTurn.isLinkedToRail = true;
            tcRailTurn.linkedX = x + 2;
            tcRailTurn.linkedY = y + 1;
            tcRailTurn.linkedZ = z - 4;
        }
        /** Put down straight **/
        putDownSingleRail(world, x, y + 1, z - 4, l, x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z - 3, false, false);
        for (int straight = 5; straight < 10; straight++) {
            putDownSingleRail(world, x, y + 1, z - straight, l, x, y + 1, z, 0, typeVariantStraight, true, x + 1, y + 1, z - 3, false, false);
        }

        return true;
    }
    private boolean parallelLeftSwitchNorth(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String variantStraight, Item idVariantSwitch) {
        for (int check = 1; check < 10; check++) {
            if (!canPlaceTrack(player, world, x, y + 1, z - check))
                return false;
        }
        if (!canPlaceTrack(player, world, x - 1, y + 1, z - 3) || !canPlaceTrack(player, world, x - 1, y + 1, z - 4) || !canPlaceTrack(player, world, x - 1, y + 1, z - 5) || !canPlaceTrack(player, world, x - 2, y + 1, z - 4) || !canPlaceTrack(player, world, x - 2, y + 1, z - 5) || !canPlaceTrack(player, world, x - 2, y + 1, z - 6) || !canPlaceTrack(player, world, x - 2, y + 1, z - 7) || !canPlaceTrack(player, world, x - 2, y + 1, z - 8) || !canPlaceTrack(player, world, x - 3, y + 1, z - 6) || !canPlaceTrack(player, world, x - 3, y + 1, z - 7) || !canPlaceTrack(player, world, x - 3, y + 1, z - 8) || !canPlaceTrack(player, world, x - 3, y + 1, z - 9) || !canPlaceTrack(player, world, x - 3, y + 1, z - 10) || !canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }
        int[] xArray = {x - 1, x - 1, x - 1, x - 1};
        int[] zArray = {z - 3, z - 2, z - 4, z - 5};
        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 2, x, z - 10, 8.5, x - 8, y + 1, z + 0.5,
                EnumTracks.MEDIUM_LEFT_TURN.getLabel(), idVariantSwitch))
            return false;
        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z - 3);
        if (tcRailTurn != null) {
            tcRailTurn.hasModel = false;

            /** Switch rail 1 */
            putDownSingleRail(world, x, y + 1, z - 1, l, x - 8, y + 1, z + 0.5, 8.5, tempType.getLabel(), true, x - 1, y + 1, z - 3, true, false);

            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z - 2, l, x - 8, y + 1, z + 0.5, 8.5, typeVariantStraight, false, x - 1, y + 1, z - 3, true, false);
            /** Switch rail 3 **/
            putDownSingleRail(world, x, y + 1, z - 3, l, x - 8, y + 1, z + 0.5, 8.5, typeVariantStraight, false, x - 1, y + 1, z - 3, true, false);

            int[] xArray2 = {x - 2, x - 2, x - 2, x - 2, x - 2, x - 3, x - 3, x - 3, x - 3};
            int[] zArray2 = {z - 4, z - 5, z - 6, z - 7, z - 8, z - 6, z - 7, z - 8, z - 9};
            if (!putDownTurn(player, world, false, x, y, z, xArray2, zArray2, 1, true, 2, x - 3, z - 10, 8.5, x + 6, y + 1, z - 9,
                    EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), null))
                return false;
            TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x - 2, y + 1, z - 4);
            if (tcRailTurn2 != null) {
                tcRailTurn2.hasModel = false;
                tcRailTurn2.isLinkedToRail = true;
                tcRailTurn2.linkedX = x - 1;
                tcRailTurn2.linkedY = y + 1;
                tcRailTurn2.linkedZ = z - 3;
            }
            tcRailTurn.isLinkedToRail = true;
            tcRailTurn.linkedX = x - 2;
            tcRailTurn.linkedY = y + 1;
            tcRailTurn.linkedZ = z - 4;
        }
        /** Put down straight **/
        putDownSingleRail(world, x, y + 1, z - 4, l, x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z - 3, false, false);
        for (int straight = 5; straight < 10; straight++) {
            putDownSingleRail(world, x, y + 1, z - straight, l, x, y + 1, z, 0, typeVariantStraight, true, x - 1, y + 1, z - 3, false, false);
        }
        return true;
    }
    private boolean parallelLeftSwitchSouth(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String typeVariantStraight, Item idVariantSwitch) {
        for (int check = 1; check < 10; check++) {
            if (!canPlaceTrack(player, world, x, y + 1, z + check))
                return false;
        }
        if (!canPlaceTrack(player, world, x + 1, y + 1, z + 3) || !canPlaceTrack(player, world, x + 1, y + 1, z + 4) || !canPlaceTrack(player, world, x + 1, y + 1, z + 5) || !canPlaceTrack(player, world, x + 2, y + 1, z + 4) || !canPlaceTrack(player, world, x + 2, y + 1, z + 5) || !canPlaceTrack(player, world, x + 2, y + 1, z + 6) || !canPlaceTrack(player, world, x + 2, y + 1, z + 7) || !canPlaceTrack(player, world, x + 2, y + 1, z + 8) || !canPlaceTrack(player, world, x + 3, y + 1, z + 6) || !canPlaceTrack(player, world, x + 3, y + 1, z + 7) || !canPlaceTrack(player, world, x + 3, y + 1, z + 8) || !canPlaceTrack(player, world, x + 3, y + 1, z + 9) || !canPlaceTrack(player, world, x + 3, y + 1, z + 10) || !canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }
        int[] xArray = {x + 1, x + 1, x + 1, x + 1};
        int[] zArray = {z + 3, z + 2, z + 4, z + 5};
        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 0, x, z + 10, 8.5, x + 9, y + 1, z + 0.5,
                EnumTracks.MEDIUM_LEFT_TURN.getLabel(), idVariantSwitch))
            return false;
        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z + 3);
        if (tcRailTurn != null) {
            tcRailTurn.hasModel = false;

            /** Switch rail 1 */
            putDownSingleRail(world, x, y + 1, z + 1, l, x + 9, y + 1, z + 0.5, 8.5, tempType.getLabel(), true, x + 1, y + 1, z + 3, true, false);

            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z + 2, l, x + 9, y + 1, z + 0.5, 8.5, typeVariantStraight, false, x + 1, y + 1, z + 3, true, false);
            /** Switch rail 3 **/
            putDownSingleRail(world, x, y + 1, z + 3, l, x + 9, y + 1, z + 0.5, 8.5, typeVariantStraight, false, x + 1, y + 1, z + 3, true, false);

            int[] xArray2 = {x + 2, x + 2, x + 2, x + 2, x + 2, x + 3, x + 3, x + 3, x + 3};
            int[] zArray2 = {z + 4, z + 5, z + 6, z + 7, z + 8, z + 6, z + 7, z + 8, z + 9};
            if (!putDownTurn(player, world, false, x, y, z, xArray2, zArray2, 3, true, 0, x + 3, z + 10, 8.5, x - 5, y + 1, z + 10,
                    EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), null))
                return false;
            TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x + 2, y + 1, z + 4);
            if (tcRailTurn2 != null) {
                tcRailTurn2.hasModel = false;
                tcRailTurn2.isLinkedToRail = true;
                tcRailTurn2.linkedX = x + 1;
                tcRailTurn2.linkedY = y + 1;
                tcRailTurn2.linkedZ = z + 3;
            }
            tcRailTurn.isLinkedToRail = true;
            tcRailTurn.linkedX = x + 2;
            tcRailTurn.linkedY = y + 1;
            tcRailTurn.linkedZ = z + 4;
        }
        /** Put down straight **/
        putDownSingleRail(world, x, y + 1, z + 4, l, x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z + 3, false, false);
        for (int straight = 5; straight < 10; straight++) {
            putDownSingleRail(world, x, y + 1, z + straight, l, x, y + 1, z, 0, typeVariantStraight, true, x + 1, y + 1, z + 3, false, false);
        }
        return true;
    }
    private boolean parallelLeftSwitchEast(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String typeVariantStraight, Item idVariantSwitch) {
        for (int check = 1; check < 10; check++) {
            if (!canPlaceTrack(player, world, x + check, y + 1, z))
                return false;
        }
        if (!canPlaceTrack(player, world, x + 3, y + 1, z - 1) || !canPlaceTrack(player, world, x + 4, y + 1, z - 1) || !canPlaceTrack(player, world, x + 5, y + 1, z - 1) || !canPlaceTrack(player, world, x + 4, y + 1, z - 2) || !canPlaceTrack(player, world, x + 5, y + 1, z - 2) || !canPlaceTrack(player, world, x + 6, y + 1, z - 2) || !canPlaceTrack(player, world, x + 7, y + 1, z - 2) || !canPlaceTrack(player, world, x + 8, y + 1, z - 2) || !canPlaceTrack(player, world, x + 6, y + 1, z - 3) || !canPlaceTrack(player, world, x + 7, y + 1, z - 3) || !canPlaceTrack(player, world, x + 8, y + 1, z - 3) || !canPlaceTrack(player, world, x + 9, y + 1, z - 3) || !canPlaceTrack(player, world, x + 10, y + 1, z - 3) || !canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }
        int[] xArray = {x + 3, x + 2, x + 4, x + 5};
        int[] zArray = {z - 1, z - 1, z - 1, z - 1};
        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 3, x + 10, z, 8.5, x + 0.5, y + 1, z - 8,
                EnumTracks.MEDIUM_LEFT_TURN.getLabel(), idVariantSwitch))
            return false;
        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 3, y + 1, z - 1);
        if (tcRailTurn != null) {
            tcRailTurn.hasModel = false;

            /** Switch rail 1 */
            putDownSingleRail(world, x + 1, y + 1, z, l, x + 0.5, y + 1, z - 8, 8.5, tempType.getLabel(), true, x + 3, y + 1, z - 1, true, false);

            /** Switch rail 2 **/
            putDownSingleRail(world, x + 2, y + 1, z, l, x + 0.5, y + 1, z - 8, 8.5, typeVariantStraight, false, x + 3, y + 1, z - 1, true, false);
            /** Switch rail 3 **/
            putDownSingleRail(world, x + 3, y + 1, z, l, x + 0.5, y + 1, z - 8, 8.5, typeVariantStraight, false, x + 3, y + 1, z - 1, true, false);

            int[] xArray2 = {x + 4, x + 5, x + 6, x + 7, x + 8, x + 6, x + 7, x + 8, x + 9};
            int[] zArray2 = {z - 2, z - 2, z - 2, z - 2, z - 2, z - 3, z - 3, z - 3, z - 3};
            if (!putDownTurn(player, world, false, x, y, z, xArray2, zArray2, 2, true, 3, x + 10, z - 3, 8.5, x + 10, y + 1, z + 6,
                    EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), null))
                return false;
            TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x + 4, y + 1, z - 2);
            if (tcRailTurn2 != null) {
                tcRailTurn2.hasModel = false;

                tcRailTurn2.isLinkedToRail = true;
                tcRailTurn2.linkedX = x + 3;
                tcRailTurn2.linkedY = y + 1;
                tcRailTurn2.linkedZ = z - 1;
            }
            tcRailTurn.isLinkedToRail = true;
            tcRailTurn.linkedX = x + 4;
            tcRailTurn.linkedY = y + 1;
            tcRailTurn.linkedZ = z - 2;
        }
        /** Put down straight **/
        putDownSingleRail(world, x + 4, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false, x + 3, y + 1, z - 1, false, false);
        for (int straight = 5; straight < 10; straight++) {
            putDownSingleRail(world, x + straight, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, true, x + 3, y + 1, z - 1, false, false);
        }
        return true;
    }
    private boolean parallelLeftSwitchWest(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String typeVariantStraight, Item idVariantSwitch) {
        for (int check = 1; check < 10; check++) {
            if (!canPlaceTrack(player, world, x - check, y + 1, z))
                return false;
        }
        if (!canPlaceTrack(player, world, x - 3, y + 1, z + 1) || !canPlaceTrack(player, world, x - 4, y + 1, z + 1) || !canPlaceTrack(player, world, x - 5, y + 1, z + 1) || !canPlaceTrack(player, world, x - 4, y + 1, z + 2) || !canPlaceTrack(player, world, x - 5, y + 1, z + 2) || !canPlaceTrack(player, world, x - 6, y + 1, z + 2) || !canPlaceTrack(player, world, x - 7, y + 1, z + 2) || !canPlaceTrack(player, world, x - 8, y + 1, z + 2) || !canPlaceTrack(player, world, x - 6, y + 1, z + 3) || !canPlaceTrack(player, world, x - 7, y + 1, z + 3) || !canPlaceTrack(player, world, x - 8, y + 1, z + 3) || !canPlaceTrack(player, world, x - 9, y + 1, z + 3) || !canPlaceTrack(player, world, x - 10, y + 1, z + 3) || !canPlaceTrack(player, world, x, y + 1, z)) {
            return false;
        }
        int[] xArray = {x - 3, x - 2, x - 4, x - 5};
        int[] zArray = {z + 1, z + 1, z + 1, z + 1};
        if (!putDownTurn(player, world, true, x, y, z, xArray, zArray, l, true, 1, x - 10, z, 8.5, x + 0.5, y + 1, z + 9,
                EnumTracks.MEDIUM_LEFT_TURN.getLabel(), idVariantSwitch))
            return false;
        TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 3, y + 1, z + 1);
        if (tcRailTurn != null) {
            tcRailTurn.hasModel = false;

            /** Switch rail 1 */
            putDownSingleRail(world, x - 1, y + 1, z, l, x + 0.5, y + 1, z + 9, 8.5, tempType.getLabel(), true, x - 3, y + 1, z + 1, true, false);

            /** Switch rail 2 **/
            putDownSingleRail(world, x - 2, y + 1, z, l, x + 0.5, y + 1, z + 9, 8.5, typeVariantStraight, false, x - 3, y + 1, z + 1, true, false);
            /** Switch rail 3 **/
            putDownSingleRail(world, x - 3, y + 1, z, l, x + 0.5, y + 1, z + 9, 8.5, typeVariantStraight, false, x - 3, y + 1, z + 1, true, false);

            int[] xArray2 = {x - 4, x - 5, x - 6, x - 7, x - 8, x - 6, x - 7, x - 8, x - 9};
            int[] zArray2 = {z + 2, z + 2, z + 2, z + 2, z + 2, z + 3, z + 3, z + 3, z + 3};
            if (!putDownTurn(player, world, false, x, y, z, xArray2, zArray2, 0, true, 1, x - 10, z + 3, 8.5, x - 9, y + 1, z - 5,
                    EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), null))
                return false;
            TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x - 4, y + 1, z + 2);
            if (tcRailTurn2 != null) {
                tcRailTurn2.hasModel = false;
                tcRailTurn2.isLinkedToRail = true;
                tcRailTurn2.linkedX = x - 3;
                tcRailTurn2.linkedY = y + 1;
                tcRailTurn2.linkedZ = z + 1;
            }
            tcRailTurn.isLinkedToRail = true;
            tcRailTurn.linkedX = x - 4;
            tcRailTurn.linkedY = y + 1;
            tcRailTurn.linkedZ = z + 2;
        }
        /** Put down straight **/
        putDownSingleRail(world, x - 4, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false, x - 3, y + 1, z + 1, false, false);
        for (int straight = 5; straight < 10; straight++) {
            putDownSingleRail(world, x - straight, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, true, x - 3, y + 1, z + 1, false, false);
        }
        return true;
    }
    private boolean mediumRight45DegreeSwitch(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String typeVariantStraight, String typeVariant90Turn){
        int dx = 0;
        int dz = 0;

        if (l == 0) dz = 1;
        if (l == 1) dx = -1;
        if (l == 2) dz = -1;
        if (l == 3) dx = 1;

        for (int i = 0; i <= 3 ; i++){
            if (!canPlaceTrack(player, world, x + (dx * i), y + 1, z + (dz * i))){
                return false;
            }
        }
        int[] xArray;
        int[] zArray;

        if (player.isSneaking()) {
            xArray = new int[]{1, 1};
            zArray = new int[]{2, 3};
        }

        else {
            xArray = new int[]{1, 1, 1, 2};
            zArray = new int[]{2, 3, 4, 3};
        }

        if (l == 2) {
            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, true), l, false, 3, x + 1, z - 3, 3.75, x + 4.25, y + 1,
                    z, typeVariant90Turn, tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z - 2);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
            }

            world.setBlockMetadataWithNotify(x + 1, y + 1, z - 2, l, 2);//to force client update
            /** Switch rail 1 **/
            putDownSingleRail(world, x, y + 1, z - 1, l, x + 4.25, y + 1, z, 3.75, tempType.getLabel(), true, x + 1, y + 1, z - 2, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z - 2, l, x + 4.25, y + 1, z, 3.75, typeVariantStraight, false, x + 1, y + 1, z - 2, true, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x, y + 1, z - 3, l, x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z - 2, true, false);
        }

        if (l == 0) {
            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, false), l, false, 1, x - 1, z + 3, 3.75, x - 3.25, y + 1,
                    z + 1, typeVariant90Turn, tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z + 2);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
            }

            world.setBlockMetadataWithNotify(x - 1, y + 1, z + 2, l, 0);//to force client update
            /** Switch rail 1 **/
            putDownSingleRail(world, x, y + 1, z + 1, l, x - 3.25, y + 1, z + 1, 3.75, tempType.getLabel(), true, x - 1, y + 1, z + 2, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z + 2, l, x - 3.25, y + 1, z + 1, 3.75, typeVariantStraight, false, x - 1, y + 1, z + 2, true, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x, y + 1, z + 3, l, x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z + 2, true, false);
        }
        if (l == 1) {
            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, true), l, false, 2, x - 3, z - 1, 3.75, x, y + 1,
                    z - 3.25, typeVariant90Turn, tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 2, y + 1, z - 1);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
            }

            world.setBlockMetadataWithNotify(x - 2, y + 1, z - 1, l, 2);//to force client update
            /** Switch rail 1 **/
            putDownSingleRail(world, x - 1, y + 1, z, l, x, y + 1, z - 3.25, 3.75, tempType.getLabel(), true, x - 2, y + 1, z - 1, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x - 2, y + 1, z, l, x, y + 1, z - 3.25, 3.75, typeVariantStraight, false, x - 2, y + 1, z - 1, true, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x - 3, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false, x - 2, y + 1, z - 1, true, false);
        }
        if (l == 3) {
            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, false), l, false, 2, x + 3, z + 1, 3.75, x + 1, y + 1,
                    z + 4.25, typeVariant90Turn, tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 2, y + 1, z + 1);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
            }

            world.setBlockMetadataWithNotify(x + 2, y + 1, z + 1, l, 2);//to force client update
            /** Switch rail 1 **/
            putDownSingleRail(world, x + 1, y + 1, z, l, x + 1, y + 1, z + 4.25, 3.75, tempType.getLabel(), true, x + 2, y + 1, z + 1, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x + 2, y + 1, z, l, x + 1, y + 1, z + 4.25, 3.75, typeVariantStraight, false, x + 2, y + 1, z + 1, true, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x + 3, y + 1, z, l, x + 1, y + 1, z, 0, typeVariantStraight, false, x + 2, y + 1, z + 1, true, false);
        }


        return true;
    }
    private boolean mediumLeft45DegreeSwitch(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String typeVariantStraight, String typeVariant90Turn){
        int dx = 0;
        int dz = 0;

        if (l == 0) dz = 1;
        if (l == 1) dx = -1;
        if (l == 2) dz = -1;
        if (l == 3) dx = 1;

        for (int i = 0; i <= 3 ; i++){
            if (!canPlaceTrack(player, world, x + (dx * i), y + 1, z + (dz * i))){
                return false;
            }
        }
        int[] xArray;
        int[] zArray;
        if (player.isSneaking()) {
            xArray = new int[]{1, 1};
            zArray = new int[]{2, 3};
        }
        else {
            xArray = new int[]{1, 1, 1, 2};
            zArray = new int[]{2, 3, 4, 3};
        }
            if (l == 2) {

                if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, true), l, false, 1, x - 1, z - 3, 3.75, x - 3.25, y + 1,
                        z, typeVariant90Turn, tempType.getItem().item))
                    return false;
                TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z - 2);
                if (tcRailTurn != null) {
                    tcRailTurn.hasModel = false;
                }

                world.setBlockMetadataWithNotify(x - 1, y + 1, z - 2, l, 2);//to force client update
                /** Switch rail 1 **/
                putDownSingleRail(world, x, y + 1, z - 1, l, x - 3.25, y + 1, z, 3.75, tempType.getLabel(), true, x - 1, y + 1, z - 2, true, false);
                /** Switch rail 2 **/
                putDownSingleRail(world, x, y + 1, z - 2, l, x - 3.25, y + 1, z, 3.75, typeVariantStraight, false, x - 1, y + 1, z - 2, true, false);
                /** Put down straight exit **/
                putDownSingleRail(world, x, y + 1, z - 3, l, x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z - 2, true, false);
            }

            if (l == 0) {
                if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, false), l, false, 3, x + 1, z + 3, 3.75, x + 4.25, y + 1,
                        z + 1, typeVariant90Turn, tempType.getItem().item))
                    return false;
                TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z + 2);
                if (tcRailTurn != null) {
                    tcRailTurn.hasModel = false;
                }

                world.setBlockMetadataWithNotify(x + 1, y + 1, z + 2, l, 2);//to force client update
                /** Switch rail 1 **/
                putDownSingleRail(world, x, y + 1, z + 1, l, x + 4.25, y + 1, z + 1, 3.75, tempType.getLabel(), true, x + 1, y + 1, z + 2, true, false);
                /** Switch rail 2 **/
                putDownSingleRail(world, x, y + 1, z + 2, l, x + 4.25, y + 1, z + 1, 3.75, typeVariantStraight, false, x + 1, y + 1, z + 2, true, false);
                /** Put down straight exit **/
                putDownSingleRail(world, x, y + 1, z + 3, l, x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z + 2, true, false);
            }
            if (l == 1) {
                if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, false), l, false, 0, x - 3, z + 1, 3.75, x, y + 1,
                        z + 4.25, typeVariant90Turn, tempType.getItem().item))
                    return false;
                TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 2, y + 1, z + 1);
                if (tcRailTurn != null) {
                    tcRailTurn.hasModel = false;
                }

                world.setBlockMetadataWithNotify(x - 2, y + 1, z + 1, l, 2);//to force client update
                /** Switch rail 1 **/
                putDownSingleRail(world, x - 1, y + 1, z, l, x, y + 1, z + 4.25, 3.75, tempType.getLabel(), true, x - 2, y + 1, z + 1, true, false);
                /** Switch rail 2 **/
                putDownSingleRail(world, x - 2, y + 1, z, l, x, y + 1, z + 4.25, 3.75, typeVariantStraight, false, x - 2, y + 1, z + 1, true, false);
                /** Put down straight exit **/
                putDownSingleRail(world, x - 3, y + 1, z, l, x, y + 1, z, 0, typeVariantStraight, false, x - 2, y + 1, z + 1, true, false);
            }
            if (l == 3) {
                if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, true), l, false, 2, x + 3, z - 1, 3.75, x + 1, y + 1,
                        z - 3.25, typeVariant90Turn, tempType.getItem().item))
                    return false;
                TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 2, y + 1, z - 1);
                if (tcRailTurn != null) {
                    tcRailTurn.hasModel = false;
                }

                world.setBlockMetadataWithNotify(x + 2, y + 1, z + 1, l, 2);//to force client update
                /** Switch rail 1 **/
                putDownSingleRail(world, x + 1, y + 1, z, l, x + 1, y + 1, z - 3.25, 3.75, tempType.getLabel(), true, x + 2, y + 1, z - 1, true, false);
                /** Switch rail 2 **/
                putDownSingleRail(world, x + 2, y + 1, z, l, x + 1, y + 1, z - 3.25, 3.75, typeVariantStraight, false, x + 2, y + 1, z - 1, true, false);
                /** Put down straight exit **/
                putDownSingleRail(world, x + 3, y + 1, z, l, x + 1, y + 1, z, 0, typeVariantStraight, false, x + 2, y + 1, z - 1, true, false);
            }


        return true;
    }
    private boolean largeRight45DegreeSwitch(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String typeVariantStraight, String typeVariant90Turn){

        int dx = 0;
        int dz = 0;

        if (l == 0) dz = 1;
        if (l == 1) dx = -1;
        if (l == 2) dz = -1;
        if (l == 3) dx = 1;

        for (int i = 0; i <= 6 ; i++){
            if (!canPlaceTrack(player, world, x + (dx * i), y + 1, z + (dz * i))){
                return false;
            }
        }


        int[] xArray;
        int[] zArray;

        if (player.isSneaking()){
            xArray = new int[] {1, 1, 1, 1, 2, 2 };
            zArray = new int[] {3, 4, 5, 6, 5, 6};
        }
        else {
            xArray = new int[] {1, 1, 1, 1, 2, 2, 2, 3};
            zArray = new int[] {3, 4, 5, 6, 5, 6, 7, 6};
        }

        if (l == 2) {

            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, true), l, false, 3, x, z - 7, 8.49, x + 8.99,
                    y + 1, z, typeVariant90Turn, tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z - 3);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
            }


            world.setBlockMetadataWithNotify(x + 1, y + 1, z - 3, l, 2);//to force client update
            /** Switch rail 1 **/
            putDownSingleRail(world, x, y + 1, z - 1 , l, x + 8.99, y + 1, z , 8.49, tempType.getLabel(), true, x + 1, y + 1, z - 3, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z - 2, l, x + 8.99, y + 1, z , 8.49, typeVariantStraight, false, x + 1, y + 1, z - 3, true, false);
            /** Switch rail 1 **/
            putDownSingleRail(world, x, y + 1, z - 3 , l, x + 8.99, y + 1, z , 8.49, typeVariantStraight, false, x + 1, y + 1, z - 3, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z - 4, l, x + 8.99, y + 1, z , 8.49, typeVariantStraight, false, x + 1, y + 1, z - 3, true, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x, y + 1, z - 5, l, x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z - 3, false, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x, y + 1, z - 6, l, x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z - 3, false, false);
        }

        if (l == 0) {

            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, false), l, false, 1, x, z + 7, 8.49, x - 7.99,
                    y + 1, z + 1, typeVariant90Turn, tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z + 3);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
            }


            world.setBlockMetadataWithNotify(x - 1, y + 1, z + 3, l, 0);//to force client update
            /** Switch rail 1 **/
            putDownSingleRail(world, x, y + 1, z + 1 , l, x - 7.99, y + 1, z + 1, 8.49, tempType.getLabel(), true, x - 1, y + 1, z + 3, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z + 2, l, x - 7.99, y + 1, z + 1 , 8.49, typeVariantStraight, false, x - 1, y + 1, z + 3, true, false);
            /** Switch rail 1 **/
            putDownSingleRail(world, x, y + 1, z + 3 , l, x - 7.99, y + 1, z + 1 , 8.49, typeVariantStraight, false, x - 1, y + 1, z + 3, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z + 4, l, x - 7.99, y + 1, z + 1, 8.49, typeVariantStraight, false, x - 1, y + 1, z + 3, true, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x, y + 1, z + 5, l, x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z + 3, false, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x, y + 1, z + 6, l, x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z + 3, false, false);
        }

        if (l == 1) {

            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, true), l, false, 2, x - 7, z , 8.49, x ,
                    y + 1, z - 7.99, typeVariant90Turn, tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 3, y + 1, z - 1);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
            }


            world.setBlockMetadataWithNotify(x - 3, y + 1, z - 1, l, 1);//to force client update
            /** Switch rail 1 **/
            putDownSingleRail(world, x - 1, y + 1, z  , l, x , y + 1, z - 7.99, 8.49, tempType.getLabel(), true, x - 3, y + 1, z - 1, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x - 2, y + 1, z , l, x , y + 1, z - 7.99 , 8.49, typeVariantStraight, false, x - 3, y + 1, z - 1, true, false);
            /** Switch rail 1 **/
            putDownSingleRail(world, x - 3, y + 1, z , l, x , y + 1, z - 7.99 , 8.49, typeVariantStraight, false, x - 3, y + 1, z - 1, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x - 4, y + 1, z , l, x , y + 1, z - 7.99, 8.49, typeVariantStraight, false, x - 3, y + 1, z - 1, true, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x - 5, y + 1, z , l, x, y + 1, z, 0, typeVariantStraight, false, x - 3, y + 1, z - 1, false, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x - 6, y + 1, z , l, x, y + 1, z, 0, typeVariantStraight, false, x - 3, y + 1, z - 1, false, false);
        }

        if (l == 3) {

            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, false), l, false, 0, x + 7, z , 8.49, x + 1 ,
                    y + 1, z + 8.99, typeVariant90Turn, tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 3, y + 1, z + 1);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
            }


            world.setBlockMetadataWithNotify(x + 3, y + 1, z + 1, l, 3);//to force client update
            /** Switch rail 1 **/
            putDownSingleRail(world, x + 1, y + 1, z  , l, x + 1 , y + 1, z + 8.99, 8.49, tempType.getLabel(), true, x + 3, y + 1, z + 1, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x + 2, y + 1, z , l, x + 1 , y + 1, z + 8.99 , 8.49, typeVariantStraight, false, x + 3, y + 1, z + 1, true, false);
            /** Switch rail 1 **/
            putDownSingleRail(world, x + 3, y + 1, z , l, x + 1 , y + 1, z + 8.99 , 8.49, typeVariantStraight, false, x + 3, y + 1, z + 1, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x + 4, y + 1, z , l, x + 1 , y + 1, z + 8.99, 8.49, typeVariantStraight, false, x + 3, y + 1, z + 1, true, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x + 5, y + 1, z , l, x, y + 1, z, 0, typeVariantStraight, false, x + 3, y + 1, z + 1, false, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x + 6, y + 1, z , l, x, y + 1, z, 0, typeVariantStraight, false, x + 3, y + 1, z + 1, false, false);
        }
        return true;
    }
    private boolean largeLeft45DegreeSwitch(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String typeVariantStraight, String typeVariant90Turn){

        int dx = 0;
        int dz = 0;

        if (l == 0) dz = 1;
        if (l == 1) dx = -1;
        if (l == 2) dz = -1;
        if (l == 3) dx = 1;

        for (int i = 0; i <= 6 ; i++){
            if (!canPlaceTrack(player, world, x + (dx * i), y + 1, z + (dz * i))){
                return false;
            }
        }


        int[] xArray;
        int[] zArray;

        if (player.isSneaking()){
            xArray = new int[] {1, 1, 1, 1, 2, 2 };
            zArray = new int[] {3, 4, 5, 6, 5, 6};
        }
        else {
            xArray = new int[] {1, 1, 1, 1, 2, 2, 2, 3};
            zArray = new int[] {3, 4, 5, 6, 5, 6, 7, 6};
        }

        if (l == 2) {

            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, true), l, false, 1, x, z - 7, 8.49, x - 7.99,
                    y + 1, z, typeVariant90Turn, tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z - 3);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
            }


            world.setBlockMetadataWithNotify(x - 1, y + 1, z - 3, l, 2);//to force client update
            /** Switch rail 1 **/
            putDownSingleRail(world, x, y + 1, z - 1 , l, x - 7.99, y + 1, z , 8.49, tempType.getLabel(), true, x - 1, y + 1, z - 3, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z - 2, l, x - 7.99, y + 1, z , 8.49, typeVariantStraight, false, x - 1, y + 1, z - 3, true, false);
            /** Switch rail 1 **/
            putDownSingleRail(world, x, y + 1, z - 3 , l, x - 7.99, y + 1, z , 8.49, typeVariantStraight, false, x - 1, y + 1, z - 3, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z - 4, l, x - 7.99, y + 1, z , 8.49, typeVariantStraight, false, x - 1, y + 1, z - 3, true, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x, y + 1, z - 5, l, x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z - 3, false, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x, y + 1, z - 6, l, x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z - 3, false, false);
        }

        if (l == 0) {

            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, false), l, false, 3, x, z + 7, 8.49, x + 8.99,
                    y + 1, z + 1, typeVariant90Turn, tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z + 3);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
            }


            world.setBlockMetadataWithNotify(x + 1, y + 1, z + 3, l, 0);//to force client update
            /** Switch rail 1 **/
            putDownSingleRail(world, x, y + 1, z + 1 , l, x + 8.99, y + 1, z + 1, 8.49, tempType.getLabel(), true, x + 1, y + 1, z + 3, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z + 2, l, x + 8.99, y + 1, z + 1 , 8.49, typeVariantStraight, false, x + 1, y + 1, z + 3, true, false);
            /** Switch rail 1 **/
            putDownSingleRail(world, x, y + 1, z + 3 , l, x + 8.99, y + 1, z + 1 , 8.49, typeVariantStraight, false, x + 1, y + 1, z + 3, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x, y + 1, z + 4, l, x + 8.99, y + 1, z + 1, 8.49, typeVariantStraight, false, x + 1, y + 1, z + 3, true, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x, y + 1, z + 5, l, x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z + 3, false, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x, y + 1, z + 6, l, x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z + 3, false, false);
        }

        if (l == 1) {

            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, false), l, false, 0, x - 7, z , 8.49, x ,
                    y + 1, z + 8.99, typeVariant90Turn, tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 3, y + 1, z + 1);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
            }


            world.setBlockMetadataWithNotify(x - 3, y + 1, z + 1, l, 1);//to force client update
            /** Switch rail 1 **/
            putDownSingleRail(world, x - 1, y + 1, z  , l, x , y + 1, z + 8.99, 8.49, tempType.getLabel(), true, x - 3, y + 1, z + 1, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x - 2, y + 1, z , l, x , y + 1, z + 8.99 , 8.49, typeVariantStraight, false, x - 3, y + 1, z + 1, true, false);
            /** Switch rail 1 **/
            putDownSingleRail(world, x - 3, y + 1, z , l, x , y + 1, z + 8.99 , 8.49, typeVariantStraight, false, x - 3, y + 1, z + 1, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x - 4, y + 1, z , l, x , y + 1, z + 8.99, 8.49, typeVariantStraight, false, x - 3, y + 1, z + 1, true, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x - 5, y + 1, z , l, x, y + 1, z, 0, typeVariantStraight, false, x - 3, y + 1, z + 1, false, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x - 6, y + 1, z , l, x, y + 1, z, 0, typeVariantStraight, false, x - 3, y + 1, z + 1, false, false);
        }

        if (l == 3) {

            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, true), l, false, 2, x + 7, z , 8.49, x + 1 ,
                    y + 1, z - 7.99, typeVariant90Turn, tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 3, y + 1, z - 1);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
            }


            world.setBlockMetadataWithNotify(x + 3, y + 1, z - 1, l, 3);//to force client update
            /** Switch rail 1 **/
            putDownSingleRail(world, x + 1, y + 1, z  , l, x + 1 , y + 1, z - 7.99, 8.49, tempType.getLabel(), true, x + 3, y + 1, z - 1, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x + 2, y + 1, z , l, x + 1 , y + 1, z - 7.99 , 8.49, typeVariantStraight, false, x + 3, y + 1, z - 1, true, false);
            /** Switch rail 1 **/
            putDownSingleRail(world, x + 3, y + 1, z , l, x + 1 , y + 1, z - 7.99 , 8.49, typeVariantStraight, false, x + 3, y + 1, z - 1, true, false);
            /** Switch rail 2 **/
            putDownSingleRail(world, x + 4, y + 1, z , l, x + 1 , y + 1, z - 7.99, 8.49, typeVariantStraight, false, x + 3, y + 1, z - 1, true, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x + 5, y + 1, z , l, x, y + 1, z, 0, typeVariantStraight, false, x + 3, y + 1, z - 1, false, false);
            /** Put down straight exit **/
            putDownSingleRail(world, x + 6, y + 1, z , l, x, y + 1, z, 0, typeVariantStraight, false, x + 3, y + 1, z - 1, false, false);
        }
        return true;
    }
    private boolean largeRightParallelSwitch(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String typeVariantStraight){

        int dx = 0;
        int dz = 0;
        int dx2 = 0;
        int dz2 = 0;

        if (l == 0) {
            dz = 1;
            dx2 = -1;
        }
        if (l == 1) {
            dx = -1;
            dz2 = -1;
        }
        if (l == 2) {
            dz = -1;
            dx2 = 1;

        }
        if (l == 3) {
            dx = 1;
            dz2 = 1;
        }

       for (int i = 0; i < 17 ; i++){
           if (!canPlaceTrack(player, world, x + (dx * i), y + 1, z + (dz * i))){
                return false;
           }
       }
        for (int i = 14; i < 17 ; i++){
            if (!canPlaceTrack(player, world, x + ((dx * i) + (dx2 * 3)), y + 1, z + ((dz * i) + (dz2 * 3)))){
                return false;
            }
        }


        int[] xArray = new int[] {1,1,1,1,2};
        int[] zArray = new int[] {3,4,5,6,6};
        int[] xArray2 = new int[] {1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3};
        int[] zArray2 = new int[] {7, 7, 8, 9, 10, 11, 9, 10, 11, 12, 13};


        if (l == 2) {
            for (int gag = 0; gag < xArray2.length; gag++) {
                if (!canPlaceTrack(player, world, x + xArray2[gag] , y + 1, z -  zArray2[gag] )) {
                    return false;
                }
            }
            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, true), l, false, 2, x + 2, z - 17, 18, x + 18.48,
                    y + 1, z + 0.95, EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z - 3);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
                world.setBlockMetadataWithNotify(x + 1, y + 1, z - 3, l, 2);//to force client update
                /** Switch rail 1 **/
                putDownSingleRail(world, x, y + 1, z - 1, l, x + 18.48, y + 1, z + 0.95, 18, tempType.getLabel(), true, x + 1, y + 1, z - 3, true, false);
                /** Switch rail 2 **/
                putDownSingleRail(world, x, y + 1, z - 2, l, x + 18.48, y + 1, z + 0.95, 18, typeVariantStraight, false, x + 1, y + 1, z - 3, true, false);
                /** Switch rail 3 **/
                putDownSingleRail(world, x, y + 1, z - 3, l, x + 18.48, y + 1, z + 0.95, 18, typeVariantStraight, false, x + 1, y + 1, z - 3, true, false);
                /** Switch rail 4 **/
                putDownSingleRail(world, x, y + 1, z - 4, l, x + 18.48, y + 1, z + 0.95, 18, typeVariantStraight, false, x + 1, y + 1, z - 3, true, false);

                if (!putDownTurn(player, world, false, x, y, z, flipArraySign(xArray2, x, false), flipArraySign(zArray2, z, true), l, false, 2, x + 2, z - 20, 18, x - 14.5,
                        y + 1, z - 13.5, EnumTracks.MEDIUM_LEFT_TURN.getLabel(), tempType.getItem().item))
                    return false;
                TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x + 1, y + 1, z - 7);
                if (tcRailTurn2 != null) {
                    tcRailTurn2.hasModel = false;
                    tcRailTurn2.isLinkedToRail = true;
                    tcRailTurn2.linkedX = x + 1;
                    tcRailTurn2.linkedY = y + 1;
                    tcRailTurn2.linkedZ = z - 3;
                }
                tcRailTurn.isLinkedToRail = true;
                tcRailTurn.linkedX = x + 1;
                tcRailTurn.linkedY = y + 1;
                tcRailTurn.linkedZ = z - 7;

            }
            /** Straight rail exit**/
            for (int i = 5; i < 17 ; i++){
                putDownSingleRail(world, x, y + 1, z - i, l , x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z - 3, false, false);
            }
            for (int i = 14; i < 17 ; i++){
                putDownSingleRail(world, x + 3, y + 1, z - i, l , x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z - 3, false, false);
            }
            return true;

        }

        if (l == 0) {
            for (int gag = 0; gag < xArray2.length; gag++) {
                if (!canPlaceTrack(player, world, x - xArray2[gag] , y + 1, z + zArray2[gag] )) {
                    return false;
                }
            }
            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, false), l, false, 0, x - 2, z + 17, 18, x - 17.48,
                    y + 1, z + 0.05 , EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z + 3);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
                world.setBlockMetadataWithNotify(x - 1, y + 1, z + 3, l, 0);//to force client update
                /** Switch rail 1 **/
                putDownSingleRail(world, x, y + 1, z + 1, l, x - 17.48, y + 1, z + 0.05, 18, tempType.getLabel(), true, x - 1, y + 1, z + 3, true, false);
                /** Switch rail 2 **/
                putDownSingleRail(world, x, y + 1, z + 2, l, x - 17.48, y + 1, z + 0.05, 18, typeVariantStraight, false, x - 1, y + 1, z + 3, true, false);
                /** Switch rail 3 **/
                putDownSingleRail(world, x, y + 1, z + 3, l, x - 17.48, y + 1, z + 0.05, 18, typeVariantStraight, false, x - 1, y + 1, z + 3, true, false);
                /** Switch rail 4 **/
                putDownSingleRail(world, x, y + 1, z + 4, l, x - 17.48, y + 1, z + 0.05, 18, typeVariantStraight, false, x - 1, y + 1, z + 3, true, false);

                if (!putDownTurn(player, world, false, x, y, z, flipArraySign(xArray2, x, true), flipArraySign(zArray2, z, false), l, false, 0, x - 2, z + 17, 18, x + 15.48,
                        y + 1, z + 14.5, EnumTracks.MEDIUM_LEFT_TURN.getLabel(), tempType.getItem().item))
                    return false;
                TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x - 1, y + 1, z + 7);
                if (tcRailTurn2 != null) {
                    tcRailTurn2.hasModel = false;
                    tcRailTurn2.isLinkedToRail = true;
                    tcRailTurn2.linkedX = x - 1;
                    tcRailTurn2.linkedY = y + 1;
                    tcRailTurn2.linkedZ = z + 3;
                }
                tcRailTurn.isLinkedToRail = true;
                tcRailTurn.linkedX = x - 1;
                tcRailTurn.linkedY = y + 1;
                tcRailTurn.linkedZ = z + 7;

            }
            /** Straight rail exit**/
            for (int i = 5; i < 17 ; i++){
                putDownSingleRail(world, x, y + 1, z + i, l , x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z + 3, false, false);
            }
            for (int i = 14; i < 17 ; i++){
                putDownSingleRail(world, x - 3, y + 1, z + i, l , x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z + 3, false, false);
            }
            return true;

        }

        if (l == 1) {
            for (int gag = 0; gag < xArray2.length; gag++) {
                if (!canPlaceTrack(player, world, x - zArray2[gag] , y + 1, z -  xArray2[gag] )) {
                    return false;
                }
            }
            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, true), l, false, 1, x - 2, z - 17, 18, x + 0.95,
                    y + 1, z - 17.48 , EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 3, y + 1, z - 1);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
                world.setBlockMetadataWithNotify(x - 3, y + 1, z - 1, l, 1);//to force client update
                /** Switch rail 1 **/
                putDownSingleRail(world, x - 1, y + 1, z , l, x + 0.95, y + 1, z - 17.48, 18, tempType.getLabel(), true, x - 3, y + 1, z - 1, true, false);
                /** Switch rail 2 **/
                putDownSingleRail(world, x - 2, y + 1, z , l, x + 0.95, y + 1, z - 17.48, 18, typeVariantStraight, false, x - 3, y + 1, z - 1, true, false);
                /** Switch rail 3 **/
                putDownSingleRail(world, x - 3, y + 1, z , l, x + 0.95, y + 1, z - 17.48, 18, typeVariantStraight, false, x - 3, y + 1, z - 1, true, false);
                /** Switch rail 4 **/
                putDownSingleRail(world, x - 4, y + 1, z , l, x + 0.95, y + 1, z - 17.48, 18, typeVariantStraight, false, x - 3, y + 1, z - 1, true, false);

                if (!putDownTurn(player, world, false, x, y, z, flipArraySign(zArray2, x, true), flipArraySign(xArray2, z, true), l, false, 1, x - 17, z - 3, 18, x - 13.5,
                        y + 1, z + 15.5, EnumTracks.MEDIUM_LEFT_TURN.getLabel(), tempType.getItem().item))
                    return false;
                TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x - 7, y + 1, z - 1);
                if (tcRailTurn2 != null) {
                    tcRailTurn2.hasModel = false;
                    tcRailTurn2.isLinkedToRail = true;
                    tcRailTurn2.linkedX = x - 3;
                    tcRailTurn2.linkedY = y + 1;
                    tcRailTurn2.linkedZ = z - 1;
                }
                tcRailTurn.isLinkedToRail = true;
                tcRailTurn.linkedX = x - 7;
                tcRailTurn.linkedY = y + 1;
                tcRailTurn.linkedZ = z - 1;

            }
            /** Straight rail exit**/
            for (int i = 5; i < 17 ; i++){
                putDownSingleRail(world, x - i, y + 1, z, l , x, y + 1, z, 0, typeVariantStraight, false, x - 3, y + 1, z - 1, false, false);
            }
            for (int i = 14; i < 17 ; i++){
                putDownSingleRail(world, x - i, y + 1, z - 3, l , x, y + 1, z, 0, typeVariantStraight, false, x - 3, y + 1, z - 1, false, false);
            }
            return true;

        }

        if (l == 3) {
            for (int gag = 0; gag < xArray2.length; gag++) {
                if (!canPlaceTrack(player, world, x + zArray2[gag] , y + 1, z +  xArray2[gag] )) {
                    return false;
                }
            }
            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, false), l, false, 3, x + 2, z + 17, 18, x + 0.05,
                    y + 1, z + 18.48 , EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 3, y + 1, z + 1);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
                world.setBlockMetadataWithNotify(x + 3, y + 1, z + 1, l, 3);//to force client update
                /** Switch rail 1 **/
                putDownSingleRail(world, x + 1, y + 1, z , l, x + 0.05, y + 1, z + 18.48, 18, tempType.getLabel(), true, x + 3, y + 1, z + 1, true, false);
                /** Switch rail 2 **/
                putDownSingleRail(world, x + 2, y + 1, z , l, x + 0.05, y + 1, z + 18.48, 18, typeVariantStraight, false, x + 3, y + 1, z + 1, true, false);
                /** Switch rail 3 **/
                putDownSingleRail(world, x + 3, y + 1, z , l, x + 0.05, y + 1, z + 18.48, 18, typeVariantStraight, false, x + 3, y + 1, z + 1, true, false);
                /** Switch rail 4 **/
                putDownSingleRail(world, x + 4, y + 1, z , l, x + 0.05, y + 1, z + 18.48, 18, typeVariantStraight, false, x + 3, y + 1, z + 1, true, false);

                if (!putDownTurn(player, world, false, x, y, z, flipArraySign(zArray2, x, false), flipArraySign(xArray2, z, false), l, false, 3, x + 17, z + 3, 18, x + 14.5,
                        y + 1, z - 14.5, EnumTracks.MEDIUM_LEFT_TURN.getLabel(), tempType.getItem().item))
                    return false;
                TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x + 7, y + 1, z + 1);
                if (tcRailTurn2 != null) {
                    tcRailTurn2.hasModel = false;
                    tcRailTurn2.isLinkedToRail = true;
                    tcRailTurn2.linkedX = x + 3;
                    tcRailTurn2.linkedY = y + 1;
                    tcRailTurn2.linkedZ = z + 1;
                }
                tcRailTurn.isLinkedToRail = true;
                tcRailTurn.linkedX = x + 7;
                tcRailTurn.linkedY = y + 1;
                tcRailTurn.linkedZ = z + 1;

            }
            /** Straight rail exit**/
            for (int i = 5; i < 17 ; i++){
                putDownSingleRail(world, x + i, y + 1, z, l , x, y + 1, z, 0, typeVariantStraight, false, x + 3, y + 1, z + 1, false, false);
            }
            for (int i = 14; i < 17 ; i++){
                putDownSingleRail(world, x + i, y + 1, z + 3, l , x, y + 1, z, 0, typeVariantStraight, false, x + 3, y + 1, z + 1, false, false);
            }
            return true;

        }



        return false;

    }
    private boolean largeLeftParallelSwitch(EntityPlayer player, World world, int x, int y, int z, int l, EnumTracks tempType, String typeVariantStraight){

        int dx = 0;
        int dz = 0;
        int dx2 = 0;
        int dz2 = 0;

        if (l == 0) {
            dz = 1;
            dx2 = 1;
        }
        if (l == 1) {
            dx = -1;
            dz2 = 1;
        }
        if (l == 2) {
            dz = -1;
            dx2 = -1;

        }
        if (l == 3) {
            dx = 1;
            dz2 = -1;
        }

        for (int i = 0; i < 17 ; i++){
            if (!canPlaceTrack(player, world, x + (dx * i), y + 1, z + (dz * i))){
                return false;
            }
        }
        for (int i = 14; i < 17 ; i++){
            if (!canPlaceTrack(player, world, x + ((dx * i) + (dx2 * 3)), y + 1, z + ((dz * i) + (dz2 * 3)))){
                return false;
            }
        }


        int[] xArray = new int[] {1,1,1,1,2};
        int[] zArray = new int[] {3,4,5,6,6};
        int[] xArray2 = new int[] {1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3};
        int[] zArray2 = new int[] {7, 7, 8, 9, 10, 11, 9, 10, 11, 12, 13};


        if (l == 2) {
            for (int gag = 0; gag < xArray2.length; gag++) {
                if (!canPlaceTrack(player, world, x - xArray2[gag] , y + 1, z -  zArray2[gag] )) {
                    return false;
                }
            }
            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, true), flipArraySign(zArray, z, true), l, false, 2, x - 2, z - 17, 18, x -17.48,
                    y + 1, z + 0.95, EnumTracks.MEDIUM_LEFT_TURN.getLabel(), tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 1, y + 1, z - 3);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
                world.setBlockMetadataWithNotify(x - 1, y + 1, z - 3, l, 2);//to force client update
                /** Switch rail 1 **/
                putDownSingleRail(world, x, y + 1, z - 1, l, x - 17.48, y + 1, z + 0.95, 18, tempType.getLabel(), true, x - 1, y + 1, z - 3, true, false);
                /** Switch rail 2 **/
                putDownSingleRail(world, x, y + 1, z - 2, l, x - 17.48, y + 1, z + 0.95, 18, typeVariantStraight, false, x - 1, y + 1, z - 3, true, false);
                /** Switch rail 3 **/
                putDownSingleRail(world, x, y + 1, z - 3, l, x - 17.48, y + 1, z + 0.95, 18, typeVariantStraight, false, x - 1, y + 1, z - 3, true, false);
                /** Switch rail 4 **/
                putDownSingleRail(world, x, y + 1, z - 4, l, x - 17.48, y + 1, z + 0.95, 18, typeVariantStraight, false, x - 1, y + 1, z - 3, true, false);

                if (!putDownTurn(player, world, false, x, y, z, flipArraySign(xArray2, x, true), flipArraySign(zArray2, z, true), l, false, 2, x - 2, z - 17, 18, x + 15.5,
                        y + 1, z - 13.5, EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), tempType.getItem().item))
                    return false;
                TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x - 1, y + 1, z - 7);
                if (tcRailTurn2 != null) {
                    tcRailTurn2.hasModel = false;
                    tcRailTurn2.isLinkedToRail = true;
                    tcRailTurn2.linkedX = x - 1;
                    tcRailTurn2.linkedY = y + 1;
                    tcRailTurn2.linkedZ = z - 3;
                }
                tcRailTurn.isLinkedToRail = true;
                tcRailTurn.linkedX = x - 1;
                tcRailTurn.linkedY = y + 1;
                tcRailTurn.linkedZ = z - 7;

            }
            /** Straight rail exit**/
            for (int i = 5; i < 17 ; i++){
                putDownSingleRail(world, x, y + 1, z - i, l , x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z - 3, false, false);
            }
            for (int i = 14; i < 17 ; i++){
                putDownSingleRail(world, x - 3, y + 1, z - i, l , x, y + 1, z, 0, typeVariantStraight, false, x - 1, y + 1, z - 3, false, false);
            }
            return true;

        }

        if (l == 0) {
            for (int gag = 0; gag < xArray2.length; gag++) {
                if (!canPlaceTrack(player, world, x + xArray2[gag] , y + 1, z + zArray2[gag] )) {
                    return false;
                }
            }
            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(xArray, x, false), flipArraySign(zArray, z, false), l, false, 0, x + 2, z + 17, 18, x + 18.48,
                    y + 1, z + 0.05 , EnumTracks.MEDIUM_LEFT_TURN.getLabel(), tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 1, y + 1, z + 3);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
                world.setBlockMetadataWithNotify(x + 1, y + 1, z + 3, l, 0);//to force client update
                /** Switch rail 1 **/
                putDownSingleRail(world, x, y + 1, z + 1, l, x + 18.48, y + 1, z + 0.05, 18, tempType.getLabel(), true, x + 1, y + 1, z + 3, true, false);
                /** Switch rail 2 **/
                putDownSingleRail(world, x, y + 1, z + 2, l, x + 18.48, y + 1, z + 0.05, 18, typeVariantStraight, false, x + 1, y + 1, z + 3, true, false);
                /** Switch rail 3 **/
                putDownSingleRail(world, x, y + 1, z + 3, l, x + 18.48, y + 1, z + 0.05, 18, typeVariantStraight, false, x + 1, y + 1, z + 3, true, false);
                /** Switch rail 4 **/
                putDownSingleRail(world, x, y + 1, z + 4, l, x + 18.48, y + 1, z + 0.05, 18, typeVariantStraight, false, x + 1, y + 1, z + 3, true, false);

                if (!putDownTurn(player, world, false, x, y, z, flipArraySign(xArray2, x, false), flipArraySign(zArray2, z, false), l, false, 0, x - 2, z + 17, 18, x -14.48,
                        y + 1, z + 14.5, EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), tempType.getItem().item))
                    return false;
                TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x + 1, y + 1, z + 7);
                if (tcRailTurn2 != null) {
                    tcRailTurn2.hasModel = false;
                    tcRailTurn2.isLinkedToRail = true;
                    tcRailTurn2.linkedX = x + 1;
                    tcRailTurn2.linkedY = y + 1;
                    tcRailTurn2.linkedZ = z + 3;
                }
                tcRailTurn.isLinkedToRail = true;
                tcRailTurn.linkedX = x + 1;
                tcRailTurn.linkedY = y + 1;
                tcRailTurn.linkedZ = z + 7;

            }
            /** Straight rail exit**/
            for (int i = 5; i < 17 ; i++){
                putDownSingleRail(world, x, y + 1, z + i, l , x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z + 3, false, false);
            }
            for (int i = 14; i < 17 ; i++){
                putDownSingleRail(world, x + 3, y + 1, z + i, l , x, y + 1, z, 0, typeVariantStraight, false, x + 1, y + 1, z + 3, false, false);
            }
            return true;

        }

        if (l == 1) {
            for (int gag = 0; gag < xArray2.length; gag++) {
                if (!canPlaceTrack(player, world, x - zArray2[gag] , y + 1, z +  xArray2[gag] )) {
                    return false;
                }
            }
            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, true), flipArraySign(xArray, z, false), l, false, 1, x - 2, z - 17, 18, x + 0.95,
                    y + 1, z + 18.48 , EnumTracks.MEDIUM_LEFT_TURN.getLabel(), tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x - 3, y + 1, z + 1);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
                world.setBlockMetadataWithNotify(x - 3, y + 1, z + 1, l, 1);//to force client update
                /** Switch rail 1 **/
                putDownSingleRail(world, x - 1, y + 1, z , l, x + 0.95, y + 1, z + 18.48, 18, tempType.getLabel(), true, x - 3, y + 1, z + 1, true, false);
                /** Switch rail 2 **/
                putDownSingleRail(world, x - 2, y + 1, z , l, x + 0.95, y + 1, z + 18.48, 18, typeVariantStraight, false, x - 3, y + 1, z + 1, true, false);
                /** Switch rail 3 **/
                putDownSingleRail(world, x - 3, y + 1, z , l, x + 0.95, y + 1, z + 18.48, 18, typeVariantStraight, false, x - 3, y + 1, z + 1, true, false);
                /** Switch rail 4 **/
                putDownSingleRail(world, x - 4, y + 1, z , l, x + 0.95, y + 1, z + 18.48, 18, typeVariantStraight, false, x - 3, y + 1, z + 1, true, false);

                if (!putDownTurn(player, world, false, x, y, z, flipArraySign(zArray2, x, true), flipArraySign(xArray2, z, false), l, false, 1, x - 17, z + 3, 18, x - 13.5,
                        y + 1, z - 14.5, EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), tempType.getItem().item))
                    return false;
                TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x - 7, y + 1, z + 1);
                if (tcRailTurn2 != null) {
                    tcRailTurn2.hasModel = false;
                    tcRailTurn2.isLinkedToRail = true;
                    tcRailTurn2.linkedX = x - 3;
                    tcRailTurn2.linkedY = y + 1;
                    tcRailTurn2.linkedZ = z + 1;
                }
                tcRailTurn.isLinkedToRail = true;
                tcRailTurn.linkedX = x - 7;
                tcRailTurn.linkedY = y + 1;
                tcRailTurn.linkedZ = z + 1;

            }
            /** Straight rail exit**/
            for (int i = 5; i < 17 ; i++){
                putDownSingleRail(world, x - i, y + 1, z, l , x, y + 1, z, 0, typeVariantStraight, false, x - 3, y + 1, z + 1, false, false);
            }
            for (int i = 14; i < 17 ; i++){
                putDownSingleRail(world, x - i, y + 1, z + 3, l , x, y + 1, z, 0, typeVariantStraight, false, x - 3, y + 1, z + 1, false, false);
            }
            return true;

        }

        if (l == 3) {
            for (int gag = 0; gag < xArray2.length; gag++) {
                if (!canPlaceTrack(player, world, x + zArray2[gag] , y + 1, z -  xArray2[gag] )) {
                    return false;
                }
            }
            if (!putDownTurn(player, world, true, x, y, z, flipArraySign(zArray, x, false), flipArraySign(xArray, z, true), l, false, 3, x + 2, z + 17, 18, x + 0.05,
                    y + 1, z - 17.48 , EnumTracks.MEDIUM_LEFT_TURN.getLabel(), tempType.getItem().item))
                return false;
            TileTCRail tcRailTurn = (TileTCRail) world.getTileEntity(x + 3, y + 1, z - 1);
            if (tcRailTurn != null) {
                tcRailTurn.hasModel = false;
                world.setBlockMetadataWithNotify(x + 3, y + 1, z - 1, l, 3);//to force client update
                /** Switch rail 1 **/
                putDownSingleRail(world, x + 1, y + 1, z , l, x + 0.05, y + 1, z - 17.48, 18, tempType.getLabel(), true, x + 3, y + 1, z - 1, true, false);
                /** Switch rail 2 **/
                putDownSingleRail(world, x + 2, y + 1, z , l, x + 0.05, y + 1, z - 17.48, 18, typeVariantStraight, false, x + 3, y + 1, z - 1, true, false);
                /** Switch rail 3 **/
                putDownSingleRail(world, x + 3, y + 1, z , l, x + 0.05, y + 1, z - 17.48, 18, typeVariantStraight, false, x + 3, y + 1, z - 1, true, false);
                /** Switch rail 4 **/
                putDownSingleRail(world, x + 4, y + 1, z , l, x + 0.05, y + 1, z - 17.48, 18, typeVariantStraight, false, x + 3, y + 1, z - 1, true, false);

                if (!putDownTurn(player, world, false, x, y, z, flipArraySign(zArray2, x, false), flipArraySign(xArray2, z, true), l, false, 3, x + 17, z + 3, 18, x + 14.5,
                        y + 1, z + 15.5, EnumTracks.MEDIUM_RIGHT_TURN.getLabel(), tempType.getItem().item))
                    return false;
                TileTCRail tcRailTurn2 = (TileTCRail) world.getTileEntity(x + 7, y + 1, z - 1);
                if (tcRailTurn2 != null) {
                    tcRailTurn2.hasModel = false;
                    tcRailTurn2.isLinkedToRail = true;
                    tcRailTurn2.linkedX = x + 3;
                    tcRailTurn2.linkedY = y + 1;
                    tcRailTurn2.linkedZ = z - 1;
                }
                tcRailTurn.isLinkedToRail = true;
                tcRailTurn.linkedX = x + 7;
                tcRailTurn.linkedY = y + 1;
                tcRailTurn.linkedZ = z - 1;

            }
            /** Straight rail exit**/
            for (int i = 5; i < 17 ; i++){
                putDownSingleRail(world, x + i, y + 1, z, l , x, y + 1, z, 0, typeVariantStraight, false, x + 3, y + 1, z - 1, false, false);
            }
            for (int i = 14; i < 17 ; i++){
                putDownSingleRail(world, x + i, y + 1, z - 3, l , x, y + 1, z, 0, typeVariantStraight, false, x + 3, y + 1, z - 1, false, false);
            }
            return true;

        }



        return false;

    }

    /**
     * @param player
     * @param world
     * @param x
     * @param y
     * @param z
     * @param dir          orientation
     * @param tempType
     * @param pyaw        looking direction
     * @param xArray
     * @param zArray
     * @param xArray2
     * @param zArray2
     * @param radius
     * @param length
     * @param width
     * @return
     */

    /**
     * Drop the previous block before placing the track.
     */
    private void placeTrack(World world, int x, int y, int z, Block block, int metadata) {
        Block removed = world.getBlock(x, y, z);
        if (removed != null) {
            removed.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
        }
        world.setBlock(x, y, z, block, metadata, 3);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("\u00a77" + type.getItemToolTip());
    }

    public EnumTracks getTrackType() {
        return this.type;

    }

}

