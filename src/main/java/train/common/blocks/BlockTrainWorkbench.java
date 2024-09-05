package train.common.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;
import train.common.api.blocks.BlockDynamic;
import train.common.library.GuiIDs;
import train.common.library.Info;
import train.common.tile.TileHelper;
import train.common.tile.TileTrainWbench;

import java.util.Random;

public class BlockTrainWorkbench extends BlockDynamic {


	public BlockTrainWorkbench(int j) {
		super(Material.wood,0);
		setCreativeTab(Traincraft.tcTab);
		setHarvestLevel("axe", 0);
	}


	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity te = world.getTileEntity(i, j, k);
		if (player.isSneaking()) {
			return false;
		}
		if (!world.isRemote) {
			if (te != null && te instanceof TileTrainWbench) {
				player.openGui(Traincraft.instance, GuiIDs.TRAIN_WORKBENCH, world, i, j, k);
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, Block par5, int par6) {
		Random distilRand = new Random();
		TileTrainWbench tilewb = (TileTrainWbench) world.getTileEntity(i, j, k);
		if (tilewb != null) {
			label0: for (int l = 0; l < tilewb.getSizeInventory(); l++) {
				ItemStack itemstack = tilewb.getStackInSlot(l);
				if (itemstack == null) {
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
					itemstack.getCount() -= i1;
					EntityItem entityitem = new EntityItem(world, (float) i + f, (float) j + f1, (float) k + f2, itemstack.splitStack(i1));
					float f3 = 0.05F;
					entityitem.motionX = (float) distilRand.nextGaussian() * f3;
					entityitem.motionY = (float) distilRand.nextGaussian() * f3 + 0.2F;
					entityitem.motionZ = (float) distilRand.nextGaussian() * f3;
					world.spawnEntityInWorld(entityitem);
				} while (true);
			}
		}
		super.breakBlock(world, i, j, k, par5, par6);
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		super.onBlockAdded(world, i, j, k);
		world.markBlockForUpdate(i, j, k);
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack) {
		TileTrainWbench te = (TileTrainWbench) world.getTileEntity(i, j, k);
		if (te != null) {
			int dir = MathHelper.floor_double((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
			te.setFacing(EnumFacing.byHorizontalIndex(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
			world.markBlockForUpdate(i, j, k);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileTrainWbench();
	}
	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileTrainWbench();
	}

}