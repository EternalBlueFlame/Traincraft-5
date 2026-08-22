package train.common.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.items.ItemWrench;
import train.common.library.EnumTracks;
import train.common.library.Info;
import train.common.tile.TileTCRail;
import train.common.tile.TileTCRailGag;

import java.util.Arrays;
import java.util.Random;

public class BlockTCRail extends Block {
	private IIcon texture;

	public BlockTCRail() {
		super(Material.IRON);
		setCreativeTab(Traincraft.tcTab);
		// TODO 1.12: block bounds are per-state now (getBoundingBox): this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F);
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		return false;
	}


	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	private static final int[] matrixXZ = {0,-1,-2,1,2}, matrixY = {0,-1,-2,1,2};

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		TileTCRail tileEntity = (TileTCRail) world.getTileEntity(pos);
		if (tileEntity != null && tileEntity.idDrop != null) {
			return new ItemStack(tileEntity.idDrop);
		}
		return null;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof TileTCRailGag) {
			tileEntity = world.getTileEntity(new BlockPos(((TileTCRailGag) tileEntity).originX.get(0), ((TileTCRailGag) tileEntity).originY.get(0), ((TileTCRailGag) tileEntity).originZ.get(0)));
		}
		if (tileEntity instanceof TileTCRail) {
			((TileTCRail) tileEntity).lastPlayerToInteract = player;
		}
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileTCRail tileEntity = (TileTCRail) world.getTileEntity(pos);
		if (tileEntity != null && tileEntity.isLinkedToRail) {
			BlockPos linked = new BlockPos(tileEntity.linkedX, tileEntity.linkedY, tileEntity.linkedZ);
			// NOTE: func_147480_a = destroyBlock
			world.destroyBlock(linked, false);
			world.removeTileEntity(linked);
		}
		if (tileEntity != null && (tileEntity.idDrop != null) && !world.isRemote) {
			EntityPlayer player = tileEntity.lastPlayerToInteract;
			if (player != null && !player.capabilities.isCreativeMode) {
				this.dropBlockAsItemWithChance(world, pos, state, 1.0F, 0);
			}
		}
		for (int x : matrixXZ) {
			for (int z : matrixXZ) {
				for (int y : matrixY) {
					if (tileEntity != null) {
						BlockPos check = new BlockPos(x + tileEntity.getPos().getX(), y + tileEntity.getPos().getY(), z + tileEntity.getPos().getZ());
						if (world.getBlockState(check).getBlock() instanceof BlockTCRailGag) {
							if (((TileTCRailGag) world.getTileEntity(check)).originX.size() > 1) {
								((TileTCRailGag) world.getTileEntity(check)).originX.removeAll(Arrays.asList(new int[]{tileEntity.getPos().getX()}));
								((TileTCRailGag) world.getTileEntity(check)).originY.removeAll(Arrays.asList(new int[]{tileEntity.getPos().getY()}));
								((TileTCRailGag) world.getTileEntity(check)).originY.removeAll(Arrays.asList(new int[]{tileEntity.getPos().getZ()}));
							} else {
								BlockPos above = new BlockPos(x + tileEntity.getPos().getX(), y + tileEntity.getPos().getY() + 1, z + tileEntity.getPos().getZ());
								world.setBlockToAir(above);
								IBlockState s = world.getBlockState(above);
								world.notifyBlockUpdate(above, s, s, 3);
							}
						}
						if (world.getBlockState(check).getBlock() instanceof BlockTCRail) {
							BlockPos above = new BlockPos(x + tileEntity.getPos().getX(), y + tileEntity.getPos().getY() + 1, z + tileEntity.getPos().getZ());
							world.setBlockToAir(above);
							IBlockState s = world.getBlockState(above);
							world.notifyBlockUpdate(above, s, s, 3);
						}
					}
				}
			}
		}

		world.removeTileEntity(pos);
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos) {
		TileEntity tile = world.getTileEntity(pos);
		if (tile instanceof TileTCRail) {
			if (((TileTCRail) tile).isLinkedToRail) {
				if (world.isAirBlock(new BlockPos(((TileTCRail) tile).linkedX, ((TileTCRail) tile).linkedY, ((TileTCRail) tile).linkedZ))) {
					// NOTE: func_147480_a = destroyBlock
					world.removeTileEntity(pos);
					world.destroyBlock(pos, false);
				}
			}
			BlockPos below = pos.down();
			if (!world.isSideSolid(below, EnumFacing.UP) && world.getBlockState(below).getBlock() != TCBlocks.bridgePillar) {
				// NOTE: func_147480_a = destroyBlock
				world.destroyBlock(pos, false);
				world.removeTileEntity(pos);
			}
		}
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return new TileTCRail();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = world.getTileEntity(pos);
		int l = state.getBlock().getMetaFromState(state);
		if (!world.isRemote && te != null && (te instanceof TileTCRail)) {
			if (player != null && player.inventory != null && player.inventory.getCurrentItem() != null && (player.inventory.getCurrentItem().getItem() instanceof ItemWrench) && ((TileTCRail) te).getType() != null && ((TileTCRail) te).getType().equals(EnumTracks.SMALL_STRAIGHT.getLabel())) {
				l++;
				if (l > 3)
					l = 0;
				world.setBlockState(pos, state.getBlock().getStateFromMeta(l));
				return true;
			}
			//((TileTCRail)te).printInfo();
		}
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		texture = iconRegister.registerIcon(Info.modID.toLowerCase() + ":tracks/rail_normal_turned");
	}

	public IIcon getIcon(int i, int j) {
		return texture;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		if (world == null) {
			return new AxisAlignedBB(i - 18f, j, k - 18f, i + 18f, j, k + 18f);
		}
		// TODO 1.12: minX/maxX fields removed (block bounds are per-state now); original bounds were (0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F)
		return new AxisAlignedBB(i, j, k, i + 1, j + 1, k + 1);
	}
}
