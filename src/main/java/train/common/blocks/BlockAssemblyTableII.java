package train.common.blocks;

import net.minecraft.util.EnumHand;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;
import train.common.api.blocks.BlockDynamic;
import train.common.library.GuiIDs;
import train.common.tile.TileCrafterTierII;

import java.util.Random;

public class BlockAssemblyTableII extends BlockDynamic {

	public BlockAssemblyTableII(Material material) {
		super(material,0);
		setCreativeTab(Traincraft.tcTab);
		setHarvestLevel("axe", 0);
	}

	@Override
	public int damageDropped(IBlockState i) {
		return getMetaFromState(i);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float par7, float par8, float par9) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ(); int par6 = facing.getIndex();
		TileEntity te = CommonUtil.getTileEntity(world, i, j, k);
		if (player.isSneaking()) {
			return false;
		}
		if (!world.isRemote) {
			if (te != null && te instanceof TileCrafterTierII) {
				player.openGui(Traincraft.instance, GuiIDs.CRAFTER_TIER_II, world, i, j, k);
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ(); Block par5 = state.getBlock(); int par6 = getMetaFromState(state);
		Random distilRand = new Random();
		TileCrafterTierII tileentitytierII = (TileCrafterTierII) CommonUtil.getTileEntity(world, i, j, k);
		if (tileentitytierII != null) {
			label0: for (int l = 0; l < tileentitytierII.getSizeInventory()-8; l++) {
				ItemStack itemstack = tileentitytierII.getStackInSlot(l);
				if (CommonUtil.isItemStackEmpty(itemstack)) {
					continue;
				}
				float f = distilRand.nextFloat() * 0.8F + 0.1F;
				float f1 = distilRand.nextFloat() * 0.8F + 0.1F;
				float f2 = distilRand.nextFloat() * 0.8F + 0.1F;
				do {
					if (itemstack.getCount() <= 0) {
						continue label0;
					}
					int i1 = distilRand.nextInt(21) + 10;
					if (i1 > itemstack.getCount()) {
						i1 = itemstack.getCount();
					}
					// splitStack already takes the items out of the stack (1.7.10 did stackSize -= i1 + new ItemStack)
					EntityItem entityitem = new EntityItem(world, i + f, j + f1, k + f2, itemstack.splitStack(i1));
					float f3 = 0.05F;
					entityitem.motionX = (float) distilRand.nextGaussian() * f3;
					entityitem.motionY = (float) distilRand.nextGaussian() * f3 + 0.2F;
					entityitem.motionZ = (float) distilRand.nextGaussian() * f3;
					ebf.tim.utility.CommonUtil.spawnEntity(world, entityitem);
				} while (true);
			}
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		CommonUtil.markBlockForUpdate(world, pos.getX(),pos.getY(),pos.getZ());
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ();
		super.onBlockPlacedBy(world, new BlockPos(i, j, k), world.getBlockState(new BlockPos(i, j, k)), entityliving, stack);
		TileCrafterTierII te = (TileCrafterTierII) CommonUtil.getTileEntity(world, i, j, k);
		if (te != null) {
			int dir = CommonUtil.floorDouble((entityliving.rotationYaw * 4F) / 360F + 0.5D) & 3;
			te.setFacing(ebf.tim.utility.CommonUtil.getOrientation(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
			CommonUtil.markBlockForUpdate(world, i, j, k);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int meta) {
		return new TileCrafterTierII();
	}
	@Override
	public TileEntity createTileEntity(World var1, IBlockState state) {
		int meta = getMetaFromState(state);
		return new TileCrafterTierII();
	}
}