package train.common.blocks;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.NonNullList;

import net.minecraft.block.state.IBlockState;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;

import java.util.List;
import java.util.Random;

public class BlockOreTC extends BlockFalling {


	/** 0 copper ore, 1 oil sands (falls like sand), 2 petroleum, 3 ballast - the 1.7.10 metadata values */
	public static final net.minecraft.block.properties.PropertyInteger TYPE = net.minecraft.block.properties.PropertyInteger.create("type", 0, 3);

	public BlockOreTC() {
		super(Material.ROCK);
		setCreativeTab(Traincraft.tcTab);
		setDefaultState(this.blockState.getBaseState().withProperty(TYPE, 0));
	}

	@Override
	protected net.minecraft.block.state.BlockStateContainer createBlockState() {
		return new net.minecraft.block.state.BlockStateContainer(this, TYPE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE, Math.min(3, Math.max(0, meta)));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TYPE);
	}

	/** only the oil sands fall */
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		// stairs built on this block forward their ticks here, so check the block really is ours
		if (state.getValue(TYPE) == 1 && world.getBlockState(pos).getBlock() == this) {
			super.updateTick(world, pos, state, rand);
		}
	}

	@Override
	public net.minecraft.block.SoundType getSoundType(IBlockState state, World world, BlockPos pos, @javax.annotation.Nullable net.minecraft.entity.Entity entity) {
		int type = state.getValue(TYPE);
		return type == 1 ? net.minecraft.block.SoundType.SAND : type == 3 ? net.minecraft.block.SoundType.GROUND : net.minecraft.block.SoundType.STONE;
	}


	@Override
	public int damageDropped(IBlockState metadata) {
		return getMetaFromState(metadata);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		if (isOilSands(world, pos)) world.scheduleUpdate(pos, this, this.tickRate(world));
    }

	/** True only for a real oil sands block; BlockStairs calls this block's methods with the stairs' position too. */
	private boolean isOilSands(World world, BlockPos pos) {
		IBlockState here = world.getBlockState(pos);
		return here.getBlock() == this && here.getValue(TYPE) == 1;
	}

	@Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ();
		if (isOilSands(world, pos)) world.scheduleUpdate(pos, this, this.tickRate(world));
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		Item par1 = Item.getItemFromBlock(this);
		for (int i = 0; i < 4; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}


}
