package train.common.tile.switchStand;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import train.common.api.blocks.TileTraincraft;
import train.common.library.BlockIDs;

import java.util.Random;

public class TilekSignal extends TileTraincraft {



    public int state;// 0=red 1=green
    private int updateTicks = 0;
    private static Random rand = new Random();

    @Override
    public void readFromNBT(NBTTagCompound nbtTag, boolean forSyncing) {
        super.readFromNBT(nbtTag, forSyncing);
        state = nbtTag.getInteger("state");
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        updateTicks++;

        /**
         * Remove any block on top of the wind mill
         */
        if (!world.isRemote) {
            if (updateTicks % 20 == 0) {
                if (!this.world.isAirBlock(getPos().getX(), getPos().getY() + 1, getPos().getZ())) {
                    Block block = CommonUtil.getBlockAt(this.world, getPos().getX(), getPos().getY() + 1, getPos().getZ());
                    if (block != null) {
                        EntityItem entityitem = new EntityItem(world, getPos().getX(), getPos().getY() + 1, getPos().getZ(), new ItemStack(Item.getItemFromBlock(BlockIDs.kSignal.block), 1));
                        float f3 = 0.05F;
                        entityitem.motionX = (float) rand.nextGaussian() * f3;
                        entityitem.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float) rand.nextGaussian() * f3;
                        CommonUtil.spawnEntity(world, entityitem);
                    }
                    CommonUtil.setBlockToAir(this.world, getPos().getX(), getPos().getY(), getPos().getZ());
                }
                syncTileEntity();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return CommonUtil.createAABB(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 2, yCoord + 2, zCoord + 2);
    }
}
