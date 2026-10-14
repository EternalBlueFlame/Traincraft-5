package train.common.blocks;
import train.common.core.util.LegacyRender;

import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import net.minecraft.util.math.BlockPos;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;
import train.common.tile.TileMetroMadridPole;

import java.util.List;

public class BlockMetroMadridPole extends Block implements ITileEntityProvider {

	// 1.12: keep the 1.7.10 metadata (facing/variant) in the block state, see LegacyMeta
	@Override
	protected net.minecraft.block.state.BlockStateContainer createBlockState() {
		return new net.minecraft.block.state.BlockStateContainer(this, train.common.api.blocks.LegacyMeta.META);
	}

	@Override
	public net.minecraft.block.state.IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(train.common.api.blocks.LegacyMeta.META, meta & 15);
	}

	@Override
	public int getMetaFromState(net.minecraft.block.state.IBlockState state) {
		return state.getValue(train.common.api.blocks.LegacyMeta.META);
	}


	/* 1.7.10 block bounds (setBlockBounds, minX..maxZ) on top of 1.12 getBoundingBox() */
	protected double minX = 0, minY = 0, minZ = 0, maxX = 1, maxY = 1, maxZ = 1;

	public void setBlockBounds(float x1, float y1, float z1, float x2, float y2, float z2) {
		minX = x1; minY = y1; minZ = z1; maxX = x2; maxY = y2; maxZ = z2;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
	}


    public BlockMetroMadridPole(Material p_i45410_2_) {
        super(p_i45410_2_);
        setCreativeTab(Traincraft.tcTab);

        //this.setTickRandomly(true);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1F, 1F);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase living, ItemStack stack) {
    	int par2 = pos.getX(), par3 = pos.getY(), par4 = pos.getZ();
        TileMetroMadridPole te = (TileMetroMadridPole) CommonUtil.getTileEntity(world, par2, par3, par4);
        int dir = CommonUtil.floorDouble((double) ((living.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        te.setFacing(EnumFacing.byIndex(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
    }

    public void addCollisionBoxToList(IBlockState state, World p_149743_1_, BlockPos pos, AxisAlignedBB p_149743_5_, List<AxisAlignedBB> p_149743_6_, @Nullable Entity p_149743_7_, boolean isActualState) {
    	int p_149743_2_ = pos.getX(), p_149743_3_ = pos.getY(), p_149743_4_ = pos.getZ();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
    	int metadata = getMetaFromState(state);
        return true;
    }


    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }


    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
    	int metadata = getMetaFromState(state);
        return new TileMetroMadridPole();
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileMetroMadridPole();
    }


    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return LegacyRender.renderType(-1);
    }

    public void breakBlock(World p_149749_1_, BlockPos pos, IBlockState state) {
    	int p_149749_2_ = pos.getX(), p_149749_3_ = pos.getY(), p_149749_4_ = pos.getZ(); Block p_149749_5_ = state.getBlock(); int p_149749_6_ = getMetaFromState(state);
        super.breakBlock(p_149749_1_, pos, state);
        if(p_149749_1_.getTileEntity(new BlockPos(p_149749_2_, p_149749_3_, p_149749_4_))!=null){
            p_149749_1_.removeTileEntity(new BlockPos(p_149749_2_, p_149749_3_, p_149749_4_));
        }
    }








}
