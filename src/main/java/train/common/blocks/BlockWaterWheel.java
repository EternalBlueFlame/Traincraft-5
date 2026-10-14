package train.common.blocks;
import train.common.core.util.LegacyRender;

import net.minecraft.world.IBlockAccess;

import net.minecraft.util.math.AxisAlignedBB;

import net.minecraft.util.EnumParticleTypes;

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
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.tile.TileWaterWheel;

import java.util.Random;

public class BlockWaterWheel extends Block {

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


	public BlockWaterWheel() {
		super(Material.WOOD);
		setCreativeTab(Traincraft.tcTab);
		this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1F, 1F);
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
		return new TileWaterWheel();
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
		if (tile instanceof TileWaterWheel && ((TileWaterWheel) tile).getFacing() != null) {
			double d0 = (double) ((float) par2 + 0.5F);
			double d2 = (double) ((float) par4 + 0.5F);

			par1World.spawnParticle(EnumParticleTypes.getByName("splash"), d0, par3 + 1, d2, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle(EnumParticleTypes.getByName("splash"), d0, par3, d2, 0.0D, 0.0D, 0.0D);
			if (par5Random.nextInt(20) == 0) {
				CommonUtil.playSound(par1World, par2, par3, par4, "liquid.water", par5Random.nextFloat() * 0.25F + 0.75F, par5Random.nextFloat() * 1F + 0.1F, 0);
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
		int l = CommonUtil.getBlockFacing(par1World, par2, par3, par4);
		TileEntity tile = CommonUtil.getTileEntity(par1World, par2, par3, par4);
		if (tile != null && tile instanceof TileWaterWheel) {
			(tile).onChunkUnload();
		}
		super.breakBlock(par1World, pos, state);
	}
}
