package train.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.ItemIDs;
import train.common.tile.TileSignal;

import java.util.Random;

/*
 I propose a simple x-bit identifier in the following format:
 xxxxx
 |||||
 ||||+ Color		0=green		1=red
 |||+  Mounted		0=no		1=yes
 ||+   Directions	0=both		1=one
 ++    Direction facing	00=north	01=east	10=south	11=west
 */

public class BlockSignal extends BlockContainer {

	public BlockSignal() {
		super(Material.CIRCUITS);
		this.setLightLevel(1.0F);
		// TODO 1.12: block bounds via IBlockState
		// setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 2.6F, 0.8F);
		setCreativeTab(Traincraft.tcTab);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int j) {
		return ItemIDs.signal.item;
	}
	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public int getRenderType() {
		return -1; //RenderingRegistry.getNextAvailableRenderId();
	}

	public IIcon getIcon(int i, int j) {
		return null;
	}

	public int tickRate() {
		return 4;
	}
	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		// TODO 1.12: setBlockBoundsBasedOnState no longer exists; bounds come from IBlockState
		return super.getSelectedBoundingBox(state, worldIn, pos);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
		TileSignal te = (TileSignal) world.getTileEntity(pos);

		/*
		 * if (l == 0) { world.setBlockMetadataWithNotify(i, j, k, 2); te.rot = 2; } if (l == 1) { world.setBlockMetadataWithNotify(i, j, k, 5); te.rot = 5; } if (l == 2) { world.setBlockMetadataWithNotify(i, j, k, 3); te.rot = 3; } if (l == 3) { world.setBlockMetadataWithNotify(i, j, k, 4); te.rot = 4; } */
		int var6 = MathHelper.floor((double) (entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int var7 = state.getBlock().getMetaFromState(state) >> 2;
		++var6;
		var6 %= 4;

		if (var6 == 0) {
			//world.setBlockMetadataWithNotify(par2, par3, par4, 2 | var7 << 2);
			if (te != null) {
				te.setFacing(2 | var7 << 2);
			}
		}

		if (var6 == 1) {
			//world.setBlockMetadataWithNotify(par2, par3, par4, 3 | var7 << 2);
			if (te != null) {
				te.setFacing(3 | var7 << 2);
			}
		}

		if (var6 == 2) {
			//world.setBlockMetadataWithNotify(par2, par3, par4, 0 | var7 << 2);
			if (te != null) {
				te.setFacing(0 | var7 << 2);
			}
		}

		if (var6 == 3) {
			//world.setBlockMetadataWithNotify(par2, par3, par4, 1 | var7 << 2);
			if (te != null) {
				te.setFacing(1 | var7 << 2);
			}
		}

		world.scheduleBlockUpdate(pos, this, 4, 0);
		updateTick(world, pos);
	}
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		TileSignal te = (TileSignal) world.getTileEntity(pos);

		if (world.getRedstonePowerFromNeighbors(pos) > 0) {

			te.state = 1;
		}
		/* int l = world.getBlockMetadata(i, j, k); if (l == 2) {
		 * 
		 * te.rot = 2; } if (l == 5) {
		 * 
		 * te.rot = 5; } if (l == 3) {
		 * 
		 * te.rot = 3; } if (l == 4) { te.rot = 4; } */
		//System.out.println("added " + te.rot);
		updateTick(world, pos);
	}

	/**
	 * Sets whether this block type will receive random update ticks
	 */
	@Override
	public Block setTickRandomly(boolean par1) {
		this.needsRandomTick = true;
		return this;
	}

	/**
	 * Returns whether or not this block is of a type that needs random ticking. Called for ref-counting purposes by ExtendedBlockStorage in order to broadly cull a chunk from the random chunk update list for efficiency's sake.
	 */
	@Override
	public boolean getTickRandomly() {
		return this.needsRandomTick;
	}

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
		updateTick(world, pos);
	}

	public void updateTick(World world, BlockPos pos) {

		TileSignal te = (TileSignal) world.getTileEntity(pos);
		if (te == null)
			return;
		//te.rot = l;
		// int l = world.getBlockMetadata(i, j, k);
		if (te.state == 1 && !(world.getRedstonePowerFromNeighbors(pos) > 0)) {
			te.state = 0;
		}
		if (te.state == 0 && world.getRedstonePowerFromNeighbors(pos) > 0) {
			te.state = 1;
		}
	}
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entityplayer, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		updateTick(world, pos);
		return true;
	}
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		TileSignal te = (TileSignal) world.getTileEntity(pos);
		if (te == null)
			return;
		if (te.state == 1 && !(world.getRedstonePowerFromNeighbors(pos) > 0)) {
			world.scheduleBlockUpdate(pos, this, 4, 0);
		}
		else if (te.state == 0 && world.getRedstonePowerFromNeighbors(pos) > 0) {
			// world.setBlockWithNotify(i, j, k,Train.ActiveSignalBlock.blockID);

			te.state = 1;
			// world.setBlockMetadata(i, j, k,l);
		}
		updateTick(world, pos);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileSignal();
	}
}