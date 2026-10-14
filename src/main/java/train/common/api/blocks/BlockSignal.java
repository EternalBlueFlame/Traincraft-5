package train.common.api.blocks;

import ebf.tim.utility.CommonUtil;

import net.minecraft.entity.EntityLivingBase;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumFacing;

import net.minecraft.block.state.IBlockState;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//NOTE: TileSwitch is re-used for signals, because the only differences between them are in block logic
public class BlockSignal extends BlockSwitch {

    public BlockSignal(Material material, int storage) {
        super(material, storage);
    }

    @Override
    public int getWeakPower(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ(); int meta = side.getIndex();
        return 0;
    }

    @Override
    public int getStrongPower(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ(); int meta = side.getIndex();
        return 0;
    }

    @Override
    public boolean canProvidePower(IBlockState state) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ(); int side = facing.getIndex();
        return false;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        super.onBlockAdded(world, new BlockPos(x, y, z), world.getBlockState(new BlockPos(x, y, z)));
        TileEntity tile = CommonUtil.getTileEntity(world, x,y,z);
        if (tile instanceof TileSwitch) {
            ((TileSwitch)tile).setStrength(world.getStrongPower(new BlockPos(x, y, z)),0);
        }
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ(); int side = facing.getIndex();
        TileEntity tile = CommonUtil.getTileEntity(world, x,y,z);
        if (tile instanceof TileSwitch) {
            ((TileSwitch)tile).setStrength(world.getStrongPower(new BlockPos(x, y, z)),0);
        }
        return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block other, BlockPos fromPos) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        super.neighborChanged(state, world, pos, other, fromPos);
        TileEntity tile = CommonUtil.getTileEntity(world, x, y, z);
        if (tile instanceof TileSwitch && !world.isRemote) {
            ((TileSwitch) tile).setStrength(world.getStrongPower(new BlockPos(x, y, z)),0);
        }
    }

}
