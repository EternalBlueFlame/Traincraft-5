package train.common.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.api.blocks.BlockDynamic;
import train.common.library.GuiIDs;
import train.common.library.Info;
import train.common.tile.TileEntityDistil;

import java.util.Random;

public class BlockDistil extends BlockDynamic {

	private final boolean isActive;
	private static boolean keepDistilInventory = false;
	private Random distilRand;
	public BlockDistil(int j, boolean flag) {
		super(Material.ANVIL,j);
		isActive = flag;
		distilRand = new Random();
		//setRequiresSelfNotify();
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune) {
		return Item.getItemFromBlock(TCBlocks.distilIdle);
	}


	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = world.getTileEntity(pos);
		if (player.isSneaking()) {
			return false;
		}
		if (!world.isRemote) {
			if (te != null && te instanceof TileEntityDistil) {
				player.openGui(Traincraft.instance, GuiIDs.DISTIL, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
		if (this.isActive) {
			EnumFacing side = ((TileEntityDistil) world.getTileEntity(pos)).getFacing();
			float var7 = (float) pos.getX() + 0.5F;
			float var8 = (float) pos.getY() + 0.0F + random.nextFloat() * 6.0F / 16.0F;
			float var9 = (float) pos.getZ() + 0.5F;
			float var10 = 0.52F;
			float var11 = random.nextFloat() * 0.6F - 0.3F;
			for (int t = 0; t < 10; t++) {

				world.spawnParticle(EnumParticleTypes.SPELL_MOB_AMBIENT, var7, (double) pos.getY() + 1F, var9, 0, 0, 0);
			}
			if (side == EnumFacing.WEST) {
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (var7 - var10), (double) var8, (double) (var9 + var11), 0.0D, 0.0D, 0.0D);
				world.spawnParticle(EnumParticleTypes.FLAME, (double) (var7 - var10), (double) var8, (double) (var9 + var11), 0.0D, 0.0D, 0.0D);
			}
			else if (side == EnumFacing.EAST) {
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (var7 + var10), (double) var8, (double) (var9 + var11), 0.0D, 0.0D, 0.0D);
				world.spawnParticle(EnumParticleTypes.FLAME, (double) (var7 + var10), (double) var8, (double) (var9 + var11), 0.0D, 0.0D, 0.0D);
			}
			else if (side == EnumFacing.NORTH) {
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (var7 + var11), (double) var8, (double) (var9 - var10), 0.0D, 0.0D, 0.0D);
				world.spawnParticle(EnumParticleTypes.FLAME, (double) (var7 + var11), (double) var8, (double) (var9 - var10), 0.0D, 0.0D, 0.0D);
			}
			else if (side == EnumFacing.SOUTH) {
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double) (var7 + var11), (double) var8, (double) (var9 + var10), 0.0D, 0.0D, 0.0D);
				world.spawnParticle(EnumParticleTypes.FLAME, (double) (var7 + var11), (double) var8, (double) (var9 + var10), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	public static void updateDistilBlockState(boolean flag, World world, int i, int j, int k) {
		BlockPos pos = new BlockPos(i, j, k);
		int l = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
		TileEntity tileentity = world.getTileEntity(pos);
		keepDistilInventory = true;
		if (flag) {
			world.setBlockState(pos, TCBlocks.distilActive.getStateFromMeta(l));
		}
		else {
			world.setBlockState(pos, TCBlocks.distilIdle.getStateFromMeta(l));
		}
		keepDistilInventory = false;
		if (tileentity != null) {
			tileentity.validate();
			world.setTileEntity(pos, tileentity);
		}
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (!keepDistilInventory) {
			TileEntityDistil tileentitydistil = (TileEntityDistil) world.getTileEntity(pos);
			if (tileentitydistil != null) {
				label0: for (int l = 0; l < tileentitydistil.getSizeInventory(); l++) {
					ItemStack itemstack = tileentitydistil.getStackInSlot(l);
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
						itemstack.shrink(i1);
						EntityItem entityitem = new EntityItem(world, pos.getX() + f, pos.getY() + f1, pos.getZ() + f2, itemstack.splitStack(i1));
						float f3 = 0.05F;
						entityitem.motionX = (float) distilRand.nextGaussian() * f3;
						entityitem.motionY = (float) distilRand.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float) distilRand.nextGaussian() * f3;
						world.spawnEntity(entityitem);
					} while (true);
				}
			}
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		world.notifyBlockUpdate(pos, state, state, 3);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
		TileEntityDistil te = (TileEntityDistil) world.getTileEntity(pos);
		if (te != null) {
			int dir = MathHelper.floor((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
			te.setFacing(EnumFacing.byHorizontalIndex(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
			world.notifyBlockUpdate(pos, state, state, 3);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getTexture(int x, int y, int z){
		return new ResourceLocation(Info.modID,
				((x==0&&y==0&&z==0)||CommonUtil.getBlockAt(Minecraft.getMinecraft().world,x,y,z)==TCBlocks.distilActive)?
					"textures/blocks/distil_on.png":"textures/blocks/distil_off.png");
	}
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityDistil();
	}
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityDistil();
	}

}
