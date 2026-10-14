package train.common.blocks;
import train.common.core.util.LegacyRender;

import net.minecraft.util.EnumFacing;

import net.minecraft.util.math.BlockPos;

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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.tile.TileTCRail;
import train.common.tile.TileTCRailGag;

import java.util.Random;

public class BlockTCRailGag extends Block {

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
		setBlockBoundsBasedOnState(source, pos.getX(), pos.getY(), pos.getZ());
		return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
	}


	public BlockTCRailGag() {
		super(Material.IRON);
		setCreativeTab(Traincraft.tcTab);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F);
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World par1World, BlockPos pos) {
		int par2 = pos.getX(), par3 = pos.getY(), par4 = pos.getZ();
		return false;
	}
	private static final int[] matrixXZ = {0,-1,-2,1,2}, matrixY = {0,-1,-2,1,2};
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ(); Block par5 = state.getBlock(); int par6 = getMetaFromState(state);
		TileTCRailGag tileEntity = (TileTCRailGag) CommonUtil.getTileEntity(world, i, j, k);
		if (tileEntity != null && tileEntity.originX.size()>0) {
			world.destroyBlock(new BlockPos(tileEntity.originX.get(0), tileEntity.originY.get(0), tileEntity.originZ.get(0)), false);
			world.removeTileEntity(new BlockPos(tileEntity.originX.get(0), tileEntity.originY.get(0), tileEntity.originZ.get(0)));
			// NOTE: func_147480_a = destroyBlock
			for(int x : matrixXZ){
				for(int z : matrixXZ){
					for(int y : matrixY){
						if (CommonUtil.getBlockAt(world, x + tileEntity.getPos().getX(), y + tileEntity.getPos().getY(), z + tileEntity.getPos().getZ())instanceof BlockTCRailGag){
							world.notifyNeighborsOfStateChange(new BlockPos((x  + tileEntity.getPos().getX()), (y + tileEntity.getPos().getY() + 1), (z  + tileEntity.getPos().getZ())), Blocks.AIR, true);
							CommonUtil.markBlockForUpdate(world, (x  + tileEntity.getPos().getX()), (y + tileEntity.getPos().getY() + 1 ), (z  + tileEntity.getPos().getZ()));
						}
						if (CommonUtil.getBlockAt(world, x + tileEntity.getPos().getX(), y + tileEntity.getPos().getY(), z + tileEntity.getPos().getZ())instanceof BlockTCRail){
							world.notifyNeighborsOfStateChange(new BlockPos((x  + tileEntity.getPos().getX()), (y + tileEntity.getPos().getY() + 1), (z  + tileEntity.getPos().getZ())), Blocks.AIR, true);
							CommonUtil.markBlockForUpdate(world, (x  + tileEntity.getPos().getX()), (y + tileEntity.getPos().getY() + 1 ), (z  + tileEntity.getPos().getZ()));
						}
					}
				}
			}

		}
		world.removeTileEntity(new BlockPos(i, j, k));
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		int x = pos.getX(), y = pos.getY(), z = pos.getZ();

		TileTCRailGag gagRail = (TileTCRailGag) CommonUtil.getTileEntity(world, x, y, z);
		TileTCRail tileEntity = (TileTCRail) CommonUtil.getTileEntity(world, gagRail.originX.get(0), gagRail.originY.get(0), gagRail.originZ.get(0));

		if (tileEntity == null) {
			return;
		}
		tileEntity.lastPlayerToInteract = player;
	}

	@Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		TileTCRailGag tileGag = (TileTCRailGag) CommonUtil.getTileEntity(world, x, y, z);
		TileTCRail tile = (TileTCRail) CommonUtil.getTileEntity(world, tileGag.originX.get(0), tileGag.originY.get(0), tileGag.originZ.get(0));
		if (tile != null && tile.idDrop != null){
			return new ItemStack(tile.idDrop);

		}
		else{
			return null;
		}
	}
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block par5, BlockPos fromPos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		TileEntity tileEntity = CommonUtil.getTileEntity(world, i, j, k);
		if (tileEntity instanceof TileTCRailGag) {
			if (((TileTCRailGag)tileEntity).originX.size()>0 && world.isAirBlock(new net.minecraft.util.math.BlockPos(((TileTCRailGag)tileEntity).originX.get(0), ((TileTCRailGag)tileEntity).originY.get(0), ((TileTCRailGag)tileEntity).originZ.get(0)))) {
				// NOTE: func_147480_a = destroyBlock
				world.destroyBlock(new BlockPos(i, j, k), false);
				world.removeTileEntity(new BlockPos(i, j, k));
			}
			if (!ebf.tim.utility.CommonUtil.hasSolidTop(world, new BlockPos(i, j - 1, k)) && CommonUtil.getBlockAt(world, i, j-1, k) != TCBlocks.bridgePillar) {
				// NOTE: func_147480_a = destroyBlock
				world.destroyBlock(new BlockPos(i, j, k), false);
				world.removeTileEntity(new BlockPos(i, j, k));
			}
		}
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int i, int j, int k) {
		TileTCRailGag tileEntity = (TileTCRailGag) par1IBlockAccess.getTileEntity(new net.minecraft.util.math.BlockPos(i, j, k));
		if (tileEntity != null) {
			//System.out.println(tileEntity.type+" "+tileEntity.bbHeight);
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, tileEntity.bbHeight, 1.0F);
		}
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
		return new TileTCRailGag();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return LegacyRender.renderType(-1);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess iblockaccess, BlockPos pos, EnumFacing facing) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ(); int l = facing.getIndex();
		return false;
	}



	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		TileEntity tileEntity = CommonUtil.getTileEntity(world, i, j, k);
		if (tileEntity instanceof TileTCRailGag && !((TileTCRailGag)tileEntity).type.equals("null")) {
			return CommonUtil.createAABB(0, 0, 0, 1, ((TileTCRailGag)tileEntity).bbHeight, 1);
		}
		return null;
	}
}