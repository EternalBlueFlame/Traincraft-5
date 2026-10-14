package train.common.blocks;
import train.common.core.util.LegacyRender;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;
import train.common.api.blocks.BlockSignal;
import train.common.tile.TileMFPBWigWag;

public class BlockMFPBWigWag extends BlockSignal {

	public BlockMFPBWigWag() {
		super(Material.ROCK,0);
		setCreativeTab(Traincraft.tcTab);
		this.setTickRandomly(true);
	}

	@Override
	public float[] hitboxShape(){return new float[]{0,0,0,1,2,1};}

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
		return new TileMFPBWigWag(this);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileMFPBWigWag(this);
	}


	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return LegacyRender.renderType(-1);
	}



	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		super.onBlockPlacedBy(world, new BlockPos(i, j, k), world.getBlockState(new BlockPos(i, j, k)), entityliving, stack);
		TileMFPBWigWag te = null;
		if (CommonUtil.getTileEntity(world, i,j,k) instanceof TileMFPBWigWag) {
			te = (TileMFPBWigWag) CommonUtil.getTileEntity(world, i, j, k);
		}
		if (te != null) {
			int dir = CommonUtil.floorDouble((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
			te.setFacing(CommonUtil.getOrientation(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
			CommonUtil.markBlockForUpdate(world, i, j, k);
		}
	}
}
