package train.common.tile.tileSwitch;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import train.common.library.BlockIDs;
import train.common.api.blocks.TileTraincraft;

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
        if (!getWorld().isRemote) {
            if (updateTicks % 20 == 0) {
                if (!this.getWorld().isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord)) {
                    Block block = this.getWorld().getBlock(this.xCoord, this.yCoord + 1, this.zCoord);
                    if (block != null) {
                        EntityItem entityitem = new EntityItem(getWorld(), this.xCoord, this.yCoord + 1, this.zCoord, new ItemStack(Item.getItemFromBlock(BlockIDs.kSignal.block), 1));
                        float f3 = 0.05F;
                        entityitem.motionX = (float) rand.nextGaussian() * f3;
                        entityitem.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float) rand.nextGaussian() * f3;
                        getWorld().spawnEntityInWorld(entityitem);
                    }
                    this.getWorld().setBlockToAir(getPos());
                }
                syncTileEntity();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 2, yCoord + 2, zCoord + 2);
    }
}
