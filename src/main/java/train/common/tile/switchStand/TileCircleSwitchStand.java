package train.common.tile.switchStand;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.ITickable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import train.common.api.blocks.TileSwitch;
import train.common.blocks.TCBlocks;
import train.common.blocks.switchStand.BlockCircleSwitchStand;

import java.util.Random;

public class TileCircleSwitchStand extends TileSwitch implements ITickable {

    public TileCircleSwitchStand(){
    }
    public TileCircleSwitchStand(BlockCircleSwitchStand block){
        host = block;
    }
    private int updateTicks = 0;
    private static Random rand = new Random();

    @Override
    public void update() {
        super.update();
        updateTicks++;

        /**
         * Remove any block on top of the wind mill
         */
        if (!world.isRemote) {
            if (updateTicks % 20 == 0) {
                if (!this.world.isAirBlock(new BlockPos(this.getPos().getX(), this.getPos().getY() + 1, this.getPos().getZ()))) {
                    Block block = CommonUtil.getBlockAt(this.world, this.getPos().getX(), this.getPos().getY() + 1, this.getPos().getZ());
                    if (block != null) {
                        EntityItem entityitem = new EntityItem(world, this.getPos().getX(), this.getPos().getY() + 1, this.getPos().getZ(), new ItemStack(Item.getItemFromBlock(TCBlocks.circleSwitchStand), 1));
                        float f3 = 0.05F;
                        entityitem.motionX = (float) rand.nextGaussian() * f3;
                        entityitem.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float) rand.nextGaussian() * f3;
                        CommonUtil.spawnEntity(world, entityitem);
                    }
                    this.world.setBlockToAir(new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ()));
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
