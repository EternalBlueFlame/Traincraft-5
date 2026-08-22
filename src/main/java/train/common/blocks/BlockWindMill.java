package train.common.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.tile.TileWindMill;

import java.util.Random;

public class BlockWindMill extends Block {
	private IIcon texture;

	public BlockWindMill() {
		super(Material.WOOD);
		setCreativeTab(Traincraft.tcTab);
		this.setTickRandomly(true);
		// TODO 1.12: block bounds are per-state now (getBoundingBox): this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 2F, 1F);
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return new TileWindMill();
	}

	public int getRenderType() {
		return -1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null && tile instanceof TileWindMill && ((TileWindMill) tile).windClient > 0) {
			if (random.nextInt(20) == 0) {
				world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_MINECART_INSIDE, SoundCategory.NEUTRAL, random.nextFloat() * 0.25F + 0.1F, random.nextFloat() * 1F - 0.6F, true);
			}
		}
	}

	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
		int l = MathHelper.floor((double) (entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int i1 = state.getBlock().getMetaFromState(state) >> 2;
		++l;
		l %= 4;

		if (l == 0) {
			world.setBlockState(pos, state.getBlock().getStateFromMeta(2 | i1 << 2));
		}

		if (l == 1) {
			world.setBlockState(pos, state.getBlock().getStateFromMeta(3 | i1 << 2));
		}

		if (l == 2) {
			world.setBlockState(pos, state.getBlock().getStateFromMeta(0 | i1 << 2));
		}

		if (l == 3) {
			world.setBlockState(pos, state.getBlock().getStateFromMeta(1 | i1 << 2));
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		texture = iconRegister.registerIcon(Info.modID.toLowerCase() + ":wind_mill");
	}

	public IIcon getIcon(int i, int j) {
		return texture;
	}

	/**
	 * ejects contained items into the world, and notifies neighbours of an update, as appropriate
	 */
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null && tile instanceof TileWindMill) {
			tile.onChunkUnload();
		}
		super.breakBlock(world, pos, state);
	}
}
