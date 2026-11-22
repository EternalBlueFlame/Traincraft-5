package train.common.tile.switchStand;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import train.common.api.blocks.TileSwitch;
import train.common.blocks.TCBlocks;
import train.common.blocks.switchStand.BlockOWOSwitchStand;

import java.util.Random;

public class TileOWOSwitchStand extends TileSwitch {

    public TileOWOSwitchStand(){

    }
    public TileOWOSwitchStand(BlockOWOSwitchStand block){
        host = block;
    }
    private int updateTicks = 0;
    private static Random rand = new Random();

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
                        EntityItem entityitem = new EntityItem(world, getPos().getX(), getPos().getY() + 1, getPos().getZ(), new ItemStack(Item.getItemFromBlock(TCBlocks.owoSwitchStand), 1));
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
