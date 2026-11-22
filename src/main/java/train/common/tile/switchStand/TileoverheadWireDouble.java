package train.common.tile.switchStand;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import train.common.api.blocks.TileTraincraft;
import train.common.library.BlockIDs;

import java.util.Random;

public class TileoverheadWireDouble extends TileTraincraft {

    private int updateTicks = 0;
    private static Random rand = new Random();

    @Override
    public void updateEntity() {
        super.updateEntity();
        updateTicks++;

        /**
         * Remove any block on top of the wind mill
         */
        if (!getWorld().isRemote) {
            if (updateTicks % 20 == 0) {
                if (!this.getWorld().isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord)) {
                    Block block = CommonUtil.getBlockAt(this.getWorld(), this.xCoord, this.yCoord + 1, this.zCoord);
                    if (block != null) {
                        EntityItem entityitem = new EntityItem(getWorld(), this.xCoord, this.yCoord + 1, this.zCoord, new ItemStack(Item.getItemFromBlock(BlockIDs.overheadWireDouble.block), 1));
                        float f3 = 0.05F;
                        entityitem.motionX = (float) rand.nextGaussian() * f3;
                        entityitem.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float) rand.nextGaussian() * f3;
                        CommonUtil.spawnEntity(getWorld(), entityitem);
                    }
                    CommonUtil.setBlockToAir(this.getWorld(), this.xCoord, this.yCoord, this.zCoord);
                }
                syncTileEntity();
            }
        }
    }


    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return CommonUtil.createAABB(getPos().getX() - 1, getPos().getY() - 1, getPos().getZ() - 1, getPos().getX() + 2, getPos().getY() + 2, getPos().getZ() + 2);
    }
}
