package train.common.tile.switchStand;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import train.common.api.blocks.TileSwitch;
import train.common.library.BlockIDs;

import java.util.Random;

public class TilesignalSpanish extends TileSwitch {

    public int state = 1;// 0=red 1=green
    private int updateTicks = 0;
    private static Random rand = new Random();


    public void setState(int st){
        state = st;
        CommonUtil.markBlockForUpdate(world, getPos().getX(),getPos().getY(),getPos().getZ());
    }

    public int getState(){
       return state;
    }


    @Override
    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag);
        tag.setInteger("state", state);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag){
        super.readFromNBT(tag);
        state = tag.getInteger("state");
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
                if (!(CommonUtil.getBlockAt(getWorld(), getPos().getX(), getPos().getY() + 1, getPos().getZ()) instanceof BlockAir)) {
                    Block block = CommonUtil.getBlockAt(this.world, getPos().getX(), getPos().getY() + 1, getPos().getZ());
                    if (block != null) {
                        EntityItem entityitem = new EntityItem(world, getPos().getX(), getPos().getY() + 1, getPos().getZ(), new ItemStack(Item.getItemFromBlock(BlockIDs.signalSpanish.block), 1));
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
        return CommonUtil.createAABB(getPos().getX() - 1, getPos().getY() - 1, getPos().getZ() - 1, getPos().getX() + 2, getPos().getY() + 2, getPos().getZ() + 2);
    }
}
