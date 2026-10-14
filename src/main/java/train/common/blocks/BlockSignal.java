package train.common.blocks;
import train.common.core.util.LegacyRender;

import net.minecraft.world.IBlockAccess;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumFacing;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
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


	public BlockSignal() {
		super(Material.CIRCUITS);
		this.setLightLevel(1.0F);
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 2.6F, 0.8F);
		setCreativeTab(Traincraft.tcTab);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int j) {
		int i = getMetaFromState(state);
		return ItemIDs.signal.item;
	}
	@Override
	public int quantityDropped(Random random) {
		return 1;
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
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return LegacyRender.renderType(-1); // drawn by RenderSignal (TESR)
	}


	public int tickRate() {
		return 4;
	}
	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World world, BlockPos pos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		return super.getSelectedBoundingBox(state, world, pos);
	}

	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving) {
		TileSignal te = (TileSignal) CommonUtil.getTileEntity(world, i, j, k);

		/*
		 * if (l == 0) { world.setBlockMetadataWithNotify(i, j, k, 2); te.rot = 2; } if (l == 1) { world.setBlockMetadataWithNotify(i, j, k, 5); te.rot = 5; } if (l == 2) { world.setBlockMetadataWithNotify(i, j, k, 3); te.rot = 3; } if (l == 3) { world.setBlockMetadataWithNotify(i, j, k, 4); te.rot = 4; } */
		int var6 = CommonUtil.floorDouble((double) (entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int var7 = CommonUtil.getBlockFacing(world, i, j, k) >> 2;
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

		world.scheduleUpdate(new BlockPos(i, j, k), this, 4);
		updateTick(world, i, j, k);
	}
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		super.onBlockAdded(world, pos, state);
		TileSignal te = (TileSignal) CommonUtil.getTileEntity(world, i, j, k);

		if (world.isBlockPowered(new BlockPos(i, j, k))) {

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
		updateTick(world, i, j, k);
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
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		updateTick(world, i, j, k);
	}

	public void updateTick(World world, int i, int j, int k) {

		TileSignal te = (TileSignal) CommonUtil.getTileEntity(world, i, j, k);
		if (te == null)
			return;
		//te.rot = l;
		// int l = world.getBlockMetadata(i, j, k);
		if (te.state == 1 && !world.isBlockPowered(new BlockPos(i, j, k))) {
			te.state = 0;
		}
		if (te.state == 0 && world.isBlockPowered(new BlockPos(i, j, k))) {
			te.state = 1;
		}
	}
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entityplayer, EnumHand hand, EnumFacing facing, float par7, float par8, float par9) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ(); int par6 = facing.getIndex();
		updateTick(world, i, j, k);
		return true;
	}
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block l, BlockPos fromPos) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		TileSignal te = (TileSignal) CommonUtil.getTileEntity(world, i, j, k);
		if (te == null)
			return;
		if (te.state == 1 && !world.isBlockPowered(new BlockPos(i, j, k))) {
			world.scheduleUpdate(new BlockPos(i, j, k), this, 4);
		}
		else if (te.state == 0 && world.isBlockPowered(new BlockPos(i, j, k))) {
			// world.setBlockWithNotify(i, j, k,Train.ActiveSignalBlock.blockID);

			te.state = 1;
			// world.setBlockMetadata(i, j, k,l);
		}
		updateTick(world, i, j, k);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileSignal();
	}
}