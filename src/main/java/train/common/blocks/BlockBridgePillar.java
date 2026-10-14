package train.common.blocks;
import train.common.core.util.LegacyRender;

import net.minecraft.world.IBlockAccess;

import net.minecraft.util.math.AxisAlignedBB;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import ebf.tim.utility.CommonUtil;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.tile.TileBridgePillar;

public class BlockBridgePillar extends Block implements ITileEntityProvider {

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


	public BlockBridgePillar() {
		super(Material.WOOD);
		setCreativeTab(Traincraft.tcTab);
		setHarvestLevel("axe", 0);
		//this.setTickRandomly(true);
		//this.setBlockBounds(0.5F , 0.0F, 0.5F , 0.5F ,  2.0F, 0.5F);
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
		return new TileBridgePillar();
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileBridgePillar();
	}


	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return LegacyRender.renderType(-1);
	}

	public void breakBlock(World p_149749_1_, BlockPos pos, IBlockState state) {
		int p_149749_2_ = pos.getX(), p_149749_3_ = pos.getY(), p_149749_4_ = pos.getZ(); Block p_149749_5_ = state.getBlock(); int p_149749_6_ = getMetaFromState(state);
		super.breakBlock(p_149749_1_, pos, state);
		if(p_149749_1_.getTileEntity(pos)!=null){
			p_149749_1_.removeTileEntity(new BlockPos(p_149749_2_, p_149749_3_, p_149749_4_));
		}
	}



}
