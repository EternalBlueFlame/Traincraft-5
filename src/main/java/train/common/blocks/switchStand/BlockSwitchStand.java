package train.common.blocks.switchStand;
import train.common.core.util.LegacyRender;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import net.minecraft.block.SoundType;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;
import train.common.api.blocks.BlockSwitch;
import train.common.tile.switchStand.TileSwitchStand;

public class BlockSwitchStand extends BlockSwitch {

	public BlockSwitchStand() {
		super(Material.WOOD, 0);
		setCreativeTab(Traincraft.tcTab);
		this.setTickRandomly(true);
		setHardness(1.75F);
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.75F, 0.8F);
		setSoundType(SoundType.METAL);
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
		return new TileSwitchStand(this);
	}
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileSwitchStand(this);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return LegacyRender.renderType(-1);
	}

	@SideOnly(Side.CLIENT)



	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		super.onBlockPlacedBy(world, new BlockPos(i, j, k), world.getBlockState(new BlockPos(i, j, k)), entityliving, stack);
		TileSwitchStand te = (TileSwitchStand) CommonUtil.getTileEntity(world, i, j, k);
		if (te != null) {
			int dir = CommonUtil.floorDouble((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
			te.setFacing(CommonUtil.getOrientation(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
			CommonUtil.markBlockForUpdate(world, i, j, k);
		}
	}

	/**
	 * Can this block provide power. Only wire currently seems to have this change based on its state.
	 */
	public boolean canProvidePower(IBlockState state) {
		return true;
	}

}
