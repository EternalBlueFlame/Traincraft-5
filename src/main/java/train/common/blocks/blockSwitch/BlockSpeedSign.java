package train.common.blocks.blockSwitch;
import train.common.core.util.LegacyRender;

import javax.annotation.Nullable;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import net.minecraft.util.math.BlockPos;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;
import train.common.api.blocks.BlockDynamic;
import train.common.tile.switchStand.TileSpeedSign;

import java.util.List;

public class BlockSpeedSign extends BlockDynamic {
	private int skinstate = 0;

	public BlockSpeedSign() {
		super(Material.IRON, 0);
		this.setTickRandomly(true);
		setBlockBounds(0.2F,0.0F,0.2F,0.8F,1.25F,0.8F);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		int metadata = getMetaFromState(state);
		return true;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World p_149743_1_, BlockPos pos, AxisAlignedBB p_149743_5_, List<AxisAlignedBB> p_149743_6_, @Nullable Entity p_149743_7_, boolean isActualState) {
		int p_149743_2_ = pos.getX(), p_149743_3_ = pos.getY(), p_149743_4_ = pos.getZ();
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
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		super.onBlockPlacedBy(world, new BlockPos(i, j, k), world.getBlockState(new BlockPos(i, j, k)), entityliving, stack);
		TileSpeedSign te = (TileSpeedSign) CommonUtil.getTileEntity(world, i, j, k);
		if (te != null) {
			int dir = CommonUtil.floorDouble((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
			te.setFacing(CommonUtil.getOrientation(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
			te.setSkinstate(0);
			CommonUtil.markBlockForUpdate(world, i, j, k);


		}
	}

	@Override
	public boolean onBlockActivated(World p_149727_1_, BlockPos pos, IBlockState state, EntityPlayer p_149727_5_, EnumHand hand, EnumFacing facing, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		int p_149727_2_ = pos.getX(), p_149727_3_ = pos.getY(), p_149727_4_ = pos.getZ(); int p_149727_6_ = facing.getIndex();
		TileSpeedSign te = (TileSpeedSign) CommonUtil.getTileEntity(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
		te.increaseSkinState();
		CommonUtil.markBlockForUpdate(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);


		return super.onBlockActivated(p_149727_1_, pos, state, p_149727_5_, hand, facing, p_149727_7_, p_149727_8_, p_149727_9_);
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		int metadata = getMetaFromState(state);
		return new TileSpeedSign(this);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileSpeedSign(this);
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
