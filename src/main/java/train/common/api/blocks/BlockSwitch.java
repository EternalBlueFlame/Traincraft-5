package train.common.api.blocks;
import train.common.core.util.LegacyRender;

import ebf.tim.utility.CommonUtil;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumFacing;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSwitch extends BlockDynamic {


    public BlockSwitch(Material material, int storage) {
        super(material, storage);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
    	int metadata = getMetaFromState(state);
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return LegacyRender.renderType(-1);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileSwitch(this);
    }


    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ(); int side = facing.getIndex();
        if (world.isRemote) {
            return true;
        } else {

            TileSwitch t = (TileSwitch)CommonUtil.getTileEntity(world, x, y, z);
            if(t!=null){
                t.toggleEnabled(0);
                world.playSound(null, (double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, net.minecraft.init.SoundEvents.BLOCK_LEVER_CLICK, net.minecraft.util.SoundCategory.BLOCKS, 0.3F, t.getStrength(0)>0 ? 0.6F : 0.5F);
                world.notifyNeighborsOfStateChange(new BlockPos(x, y, z), this, true);
            }

            return true;
        }
    }

    public void breakBlock(World p_149749_1_, BlockPos pos, IBlockState state) {
    	int p_149749_2_ = pos.getX(), p_149749_3_ = pos.getY(), p_149749_4_ = pos.getZ(); Block p_149749_5_ = state.getBlock(); int p_149749_6_ = getMetaFromState(state);
        p_149749_1_.notifyNeighborsOfStateChange(new BlockPos(p_149749_2_, p_149749_3_, p_149749_4_), this, true);
        super.breakBlock(p_149749_1_, pos, state);
    }



    public int getWeakPower(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ(); int meta = side.getIndex();
        return getStrongPower(state, world, pos, side);
    }

    public int getStrongPower(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ(); int meta = side.getIndex();
        TileSwitch t = (TileSwitch)CommonUtil.getTileEntity(world, x, y, z);
        if(t!=null && t.getStrength(0)>0){
            return 15;
        }
        return 0;
    }

    public boolean canProvidePower(IBlockState state) {
        return true;
    }


    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        super.onBlockAdded(world, new BlockPos(x, y, z), world.getBlockState(new BlockPos(x, y, z)));
        TileSwitch te = (TileSwitch) CommonUtil.getTileEntity(world, x, y, z);
        if (te !=null) {
            te.setStrength(0,0);
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block other, BlockPos fromPos) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        super.neighborChanged(state, world, pos, other, fromPos);
    }
}
