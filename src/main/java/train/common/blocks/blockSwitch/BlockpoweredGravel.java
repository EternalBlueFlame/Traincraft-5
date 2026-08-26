package train.common.blocks.blockSwitch;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockpoweredGravel extends Block {
    public BlockpoweredGravel(Material p_i45394_1_) {
        super(p_i45394_1_);
    }

    /*
    @Override
    public boolean canProvidePower() {
        return true;
    }

     */

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return super.canConnectRedstone(state, worldIn, pos, side);
    }


   /*
    @Override
    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
    {
    }

     */


    public boolean renderAsNormalBlock() {
        return true;
    }

    public boolean isOpaqueCube() {
        return true;
    }

    public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_) {
        return 15;
    }

    public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_) {
        return 15;
    }

    @Override
    public void dropBlockAsItemWithChance(World p_149642_1_, BlockPos p_149642_2_, IBlockState p_149642_3_, float p_149642_4_, int p_149642_5_) {
        super.dropBlockAsItemWithChance(p_149642_1_, p_149642_2_, p_149642_3_, p_149642_4_, p_149642_5_);
    }
}
