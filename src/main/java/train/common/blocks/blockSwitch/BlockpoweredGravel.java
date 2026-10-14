package train.common.blocks.blockSwitch;

import net.minecraft.util.math.AxisAlignedBB;

import javax.annotation.Nullable;

import java.util.List;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumFacing;

import net.minecraft.block.state.IBlockState;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockpoweredGravel extends Block {
    public BlockpoweredGravel(Material p_i45394_1_) {
        super(p_i45394_1_);
    }

    /*
    @Override
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

     */

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing facing) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ(); int side = facing == null ? -1 : facing.getIndex();
        return super.canConnectRedstone(state, world, pos, facing);
    }


   /*
    @Override
    public void addCollisionBoxToList(IBlockState state, World p_149743_1_, BlockPos pos, AxisAlignedBB p_149743_5_, List<AxisAlignedBB> p_149743_6_, @Nullable Entity p_149743_7_, boolean isActualState) {
    	int p_149743_2_ = pos.getX(), p_149743_3_ = pos.getY(), p_149743_4_ = pos.getZ();
    }

     */


    @Override
    public boolean isFullCube(IBlockState state) {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }

    @Override
    public int getStrongPower(IBlockState state, IBlockAccess p_149748_1_, BlockPos pos, EnumFacing side) {
    	int p_149748_2_ = pos.getX(), p_149748_3_ = pos.getY(), p_149748_4_ = pos.getZ(); int p_149748_5_ = side.getIndex();
        return 15;
    }

    @Override
    public int getWeakPower(IBlockState state, IBlockAccess p_149709_1_, BlockPos pos, EnumFacing side) {
    	int p_149709_2_ = pos.getX(), p_149709_3_ = pos.getY(), p_149709_4_ = pos.getZ(); int p_149709_5_ = side.getIndex();
        return 15;
    }

}
