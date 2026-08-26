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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.tile.TileTCRail;
import train.common.tile.TileTCRailGag;

import java.util.Random;

public class BlockTCRailGag extends Block {
	private IIcon texture;

	public BlockTCRailGag() {
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
	private static final int[] matrixXZ = {0,-1,-2,1,2}, matrixY = {0,-1,-2,1,2};
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileTCRailGag tileEntity = (TileTCRailGag) world.getTileEntity(pos);
		if (tileEntity != null && tileEntity.originX.size()>0) {
			BlockPos origin = new BlockPos(tileEntity.originX.get(0), tileEntity.originY.get(0), tileEntity.originZ.get(0));
			// NOTE: func_147480_a = destroyBlock
			world.destroyBlock(origin, false);
			world.removeTileEntity(origin);
			for(int x : matrixXZ){
				for(int z : matrixXZ){
					for(int y : matrixY){
						BlockPos check = new BlockPos(x + tileEntity.getPos().getX(), y + tileEntity.getPos().getY(), z + tileEntity.getPos().getZ());
						if (world.getBlockState(check).getBlock()instanceof BlockTCRailGag){
							BlockPos above = new BlockPos(x + tileEntity.getPos().getX(), y + tileEntity.getPos().getY() + 1, z + tileEntity.getPos().getZ());
							world.setBlockToAir(above);
							IBlockState s = world.getBlockState(above);
							world.notifyBlockUpdate(above, s, s, 3);
						}
						if (world.getBlockState(check).getBlock()instanceof BlockTCRail){
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

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random) {
		return 0;
	}

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {

		TileTCRailGag gagRail = (TileTCRailGag) world.getTileEntity(pos);
		TileTCRail tileEntity = (TileTCRail) world.getTileEntity(new BlockPos(gagRail.originX.get(0), gagRail.originY.get(0), gagRail.originZ.get(0)));

		if (tileEntity == null) {
			return;
		}
		tileEntity.lastPlayerToInteract = player;
	}

	@Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		TileTCRailGag tileGag = (TileTCRailGag) world.getTileEntity(pos);
		TileTCRail tile = (TileTCRail) world.getTileEntity(new BlockPos(tileGag.originX.get(0), tileGag.originY.get(0), tileGag.originZ.get(0)));
		if (tile != null && tile.idDrop != null){
			return new ItemStack(tile.idDrop);

		}
		else{
			return null;
		}
	}
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof TileTCRailGag) {
			if (((TileTCRailGag)tileEntity).originX.size()>0 && world.isAirBlock(new BlockPos(((TileTCRailGag)tileEntity).originX.get(0), ((TileTCRailGag)tileEntity).originY.get(0), ((TileTCRailGag)tileEntity).originZ.get(0)))) {
				// NOTE: func_147480_a = destroyBlock
				world.destroyBlock(pos, false);
				world.removeTileEntity(pos);
			}
			BlockPos below = pos.down();
			if (!world.isSideSolid(below, EnumFacing.UP) && world.getBlockState(below).getBlock() != TCBlocks.bridgePillar) {
				// NOTE: func_147480_a = destroyBlock
				world.destroyBlock(pos, false);
				world.removeTileEntity(pos);
			}
		}
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int i, int j, int k) {
		// TODO 1.12: block bounds are per-state now (getBoundingBox), setBlockBounds removed
		// TileTCRailGag tileEntity = (TileTCRailGag) par1IBlockAccess.getTileEntity(new BlockPos(i, j, k));
		// if (tileEntity != null) {
		//     //System.out.println(tileEntity.type+" "+tileEntity.bbHeight);
		//     this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, tileEntity.bbHeight, 1.0F);
		// }
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
		return new TileTCRailGag();
	}

	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		texture = iconRegister.registerIcon(Info.modID.toLowerCase() + ":tracks/rail_normal_turned");
	}

	public IIcon getIcon(int i, int j) {
		return texture;
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
		TileEntity tileEntity = world.getTileEntity(new BlockPos(i, j, k));
		if (tileEntity instanceof TileTCRailGag && !((TileTCRailGag)tileEntity).type.equals("null")) {
			return new AxisAlignedBB(i, j, k, i + 1, j + ((TileTCRailGag)tileEntity).bbHeight, k + 1);
		}
		return null;
	}
}
