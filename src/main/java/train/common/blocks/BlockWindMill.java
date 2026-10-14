package train.common.blocks;
import train.common.core.util.LegacyRender;

import net.minecraft.world.IBlockAccess;

import net.minecraft.util.math.AxisAlignedBB;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.tile.TileWindMill;

import java.util.Random;

public class BlockWindMill extends Block {

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


	public BlockWindMill() {
		super(Material.WOOD);
		setCreativeTab(Traincraft.tcTab);
		this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 2F, 1F);
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
	public TileEntity createTileEntity(World world, IBlockState state) {
		int metadata = getMetaFromState(state);
		return new TileWindMill();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return LegacyRender.renderType(-1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState state, World par1World, BlockPos pos, Random par5Random) {
		int par2 = pos.getX(), par3 = pos.getY(), par4 = pos.getZ();
		TileEntity tile = CommonUtil.getTileEntity(par1World, par2, par3, par4);
		if (tile != null && tile instanceof TileWindMill && ((TileWindMill) tile).windClient > 0) {
			if (par5Random.nextInt(20) == 0) {
				CommonUtil.playSound(par1World, par2, par3, par4, "minecart.inside", par5Random.nextFloat() * 0.25F + 0.1F, par5Random.nextFloat() * 1F - 0.6F, 0);
			}
		}
	}

	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World par1World, BlockPos pos, IBlockState state, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
		int par2 = pos.getX(), par3 = pos.getY(), par4 = pos.getZ();
		int l = CommonUtil.floorDouble((double) (par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int i1 = CommonUtil.getBlockFacing(par1World, par2, par3, par4) >> 2;
		++l;
		l %= 4;

		if (l == 0) {
			CommonUtil.setBlockMeta(par1World, par2, par3, par4, 2 | i1 << 2);
		}

		if (l == 1) {
			CommonUtil.setBlockMeta(par1World, par2, par3, par4, 3 | i1 << 2);
		}

		if (l == 2) {
			CommonUtil.setBlockMeta(par1World, par2, par3, par4, 0 | i1 << 2);
		}

		if (l == 3) {
			CommonUtil.setBlockMeta(par1World, par2, par3, par4, 1 | i1 << 2);
		}
	}



	/**
	 * ejects contained items into the world, and notifies neighbours of an update, as appropriate
	 */
	@Override
	public void breakBlock(World par1World, BlockPos pos, IBlockState state) {
		int par2 = pos.getX(), par3 = pos.getY(), par4 = pos.getZ(); Block par5 = state.getBlock(); int par6 = getMetaFromState(state);
		TileEntity tile = CommonUtil.getTileEntity(par1World, par2, par3, par4);
		if (tile != null && tile instanceof TileWindMill) {
			tile.onChunkUnload();
		}
		super.breakBlock(par1World, pos, state);
	}
}
