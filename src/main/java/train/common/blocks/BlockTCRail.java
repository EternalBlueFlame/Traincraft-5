package train.common.blocks;
import train.common.core.util.LegacyRender;

import net.minecraft.world.IBlockAccess;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumFacing;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
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


	public BlockTCRail() {
		super(Material.IRON);
		setCreativeTab(Traincraft.tcTab);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0F, 1.0F);
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World par1World, BlockPos pos) {
		int par2 = pos.getX(), par3 = pos.getY(), par4 = pos.getZ();
		return false;
	}


	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		TileTCRail tileEntity = (TileTCRail) CommonUtil.getTileEntity(world, x, y, z);
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
	public boolean hasTileEntity(IBlockState state) {
		int metadata = getMetaFromState(state);
		return true;
	}
	private static final int[] matrixXZ = {0,-1,-2,1,2}, matrixY = {0,-1,-2,1,2};

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		TileEntity tileEntity = CommonUtil.getTileEntity(world, x, y, z);
		if (tileEntity instanceof TileTCRailGag) {
			tileEntity = CommonUtil.getTileEntity(world, ((TileTCRailGag)tileEntity).originX.get(0), ((TileTCRailGag)tileEntity).originY.get(0), ((TileTCRailGag)tileEntity).originZ.get(0));
		}
		if(tileEntity instanceof TileTCRail){
			((TileTCRail)tileEntity).lastPlayerToInteract = player;
		}


    }

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ(); Block par5 = state.getBlock(); int par6 = getMetaFromState(state);
		TileTCRail tileEntity = (TileTCRail) CommonUtil.getTileEntity(world, i, j, k);
		if (tileEntity != null && tileEntity.isLinkedToRail) {
			// NOTE: func_147480_a = destroyBlock
			world.destroyBlock(new BlockPos(tileEntity.linkedX, tileEntity.linkedY, tileEntity.linkedZ), false);
			world.removeTileEntity(new BlockPos(tileEntity.linkedX, tileEntity.linkedY, tileEntity.linkedZ));
		}
		if (tileEntity != null && (tileEntity.idDrop != null) && !world.isRemote) {
			EntityPlayer player = tileEntity.lastPlayerToInteract;
			if (player != null && !player.capabilities.isCreativeMode) {
				spawnAsEntity(world, new BlockPos(i, j, k), new ItemStack(tileEntity.idDrop, 1, 0));
			}
		}
		for(int x : matrixXZ){
			for(int z : matrixXZ){
				for(int y : matrixY){
					if (tileEntity != null && CommonUtil.getBlockAt(world, x + tileEntity.getPos().getX(), y + tileEntity.getPos().getY(), z + tileEntity.getPos().getZ())instanceof BlockTCRailGag){
						if(((TileTCRailGag)CommonUtil.getTileEntity(world, x + tileEntity.getPos().getX(), y + tileEntity.getPos().getY(), z + tileEntity.getPos().getZ())).originX.size()>1){
							((TileTCRailGag)CommonUtil.getTileEntity(world, x + tileEntity.getPos().getX(), y + tileEntity.getPos().getY(), z + tileEntity.getPos().getZ())).originX.removeAll(Arrays.asList(new int[]{tileEntity.getPos().getX()}));
							((TileTCRailGag)CommonUtil.getTileEntity(world, x + tileEntity.getPos().getX(), y + tileEntity.getPos().getY(), z + tileEntity.getPos().getZ())).originY.removeAll(Arrays.asList(new int[]{tileEntity.getPos().getY()}));
							((TileTCRailGag)CommonUtil.getTileEntity(world, x + tileEntity.getPos().getX(), y + tileEntity.getPos().getY(), z + tileEntity.getPos().getZ())).originY.removeAll(Arrays.asList(new int[]{tileEntity.getPos().getZ()}));
						} else {
							world.notifyNeighborsOfStateChange(new BlockPos((x + tileEntity.getPos().getX()), (y + tileEntity.getPos().getY() + 1), (z + tileEntity.getPos().getZ())), Blocks.AIR, true);
							CommonUtil.markBlockForUpdate(world, (x + tileEntity.getPos().getX()), (y + tileEntity.getPos().getY() + 1), (z + tileEntity.getPos().getZ()));
						}
					}
					if (tileEntity != null && CommonUtil.getBlockAt(world, x + tileEntity.getPos().getX(), y + tileEntity.getPos().getY(), z + tileEntity.getPos().getZ())instanceof BlockTCRail){
						world.notifyNeighborsOfStateChange(new BlockPos((x  + tileEntity.getPos().getX()), (y + tileEntity.getPos().getY() + 1), (z  + tileEntity.getPos().getZ())), Blocks.AIR, true);
						CommonUtil.markBlockForUpdate(world, (x  + tileEntity.getPos().getX()), (y + tileEntity.getPos().getY() + 1 ), (z  + tileEntity.getPos().getZ()));
					}
				}
			}
		}

		world.removeTileEntity(new BlockPos(i, j, k));
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block par5, BlockPos fromPos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		TileEntity tile = CommonUtil.getTileEntity(world, i, j, k);
		if (tile instanceof TileTCRail) {
            if (((TileTCRail)tile).isLinkedToRail) {
                if (world.isAirBlock(new BlockPos(((TileTCRail)tile).linkedX, ((TileTCRail)tile).linkedY, ((TileTCRail)tile).linkedZ))) {
                    // NOTE: func_147480_a = destroyBlock
                    world.removeTileEntity(new BlockPos(i, j, k));
                    world.destroyBlock(new BlockPos(i, j, k), false);
                }
            }
            if (!ebf.tim.utility.CommonUtil.hasSolidTop(world, new BlockPos(i, j - 1, k)) && CommonUtil.getBlockAt(world, i, j - 1, k) != TCBlocks.bridgePillar) {
                // NOTE: func_147480_a = destroyBlock
                world.destroyBlock(new BlockPos(i, j, k), false);
                world.removeTileEntity(new BlockPos(i, j, k));
            }
        }
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return LegacyRender.renderType(-1);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		int metadata = getMetaFromState(state);
		return new TileTCRail();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float par7, float par8, float par9) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ(); int par6 = facing.getIndex();
		TileEntity te = CommonUtil.getTileEntity(world, i, j, k);
		int l = CommonUtil.getBlockFacing(world, i, j, k);
		if (!world.isRemote && te != null && (te instanceof TileTCRail)) {
			if (player != null && player.inventory != null && player.inventory.getCurrentItem() != null && (player.inventory.getCurrentItem().getItem() instanceof ItemWrench) && ((TileTCRail) te).getType() != null && ((TileTCRail) te).getType().equals( EnumTracks.SMALL_STRAIGHT.getLabel())) {
				l++;
				if (l > 3)
					l = 0;
				CommonUtil.setBlockMeta(world, i, j, k, l);
				return true;
			}
			//((TileTCRail)te).printInfo();
		}
		return false;
	}




	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();

		return world==null ? CommonUtil.createAABB(-18f, 0, -18f, 18f, 0, 18f)
		: CommonUtil.createAABB(this.minX , this.minY , this.minZ , maxX, this.maxY , maxZ);


	}
}
