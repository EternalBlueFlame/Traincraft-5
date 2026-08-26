/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 *
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

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
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import train.common.Traincraft;
import train.common.api.blocks.BlockDynamic;
import train.common.library.GuiIDs;
import train.common.library.Info;
import train.common.tile.TileEntityOpenHearthFurnace;

import java.util.Random;

public class BlockOpenHearthFurnace extends BlockDynamic {

	private static boolean keepFurnaceInventory = false;
	private Random furnaceRand;


	protected BlockOpenHearthFurnace(boolean active) {
		super(Material.ROCK,0);
		furnaceRand = new Random();
		//setRequiresSelfNotify();
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune) {
		return Item.getItemFromBlock(TCBlocks.openFurnaceIdle);
	}


	public static void updateHearthFurnaceBlockState(boolean flag, World world, int i, int j, int k, Random random) {
		BlockPos pos = new BlockPos(i, j, k);
		int l = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
		TileEntity tileentity = world.getTileEntity(pos);

		keepFurnaceInventory = true;

		if (flag) {
			world.setBlockState(pos, TCBlocks.openFurnaceActive.getStateFromMeta(l));
		}
		else {
			world.setBlockState(pos, TCBlocks.openFurnaceIdle.getStateFromMeta(l));
		}
		keepFurnaceInventory = false;
		if (tileentity != null) {
			tileentity.validate();
			world.setTileEntity(pos, tileentity);
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = world.getTileEntity(pos);
		if (player.isSneaking()) {
			return false;
		}
		if (!world.isRemote) {
			if (te != null && te instanceof TileEntityOpenHearthFurnace) {
				player.openGui(Traincraft.instance, GuiIDs.OPEN_HEARTH_FURNACE, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		world.notifyBlockUpdate(pos, state, state, 3);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if (!keepFurnaceInventory) {
			TileEntityOpenHearthFurnace tileentityfurnace = (TileEntityOpenHearthFurnace) world.getTileEntity(pos);
			if (tileentityfurnace != null) {
				label0: for (int l = 0; l < tileentityfurnace.getSizeInventory(); l++) {
					ItemStack itemstack = tileentityfurnace.getStackInSlot(l);
					if (itemstack == null) {
						continue;
					}
					float f = furnaceRand.nextFloat() * 0.8F + 0.1F;
					float f1 = furnaceRand.nextFloat() * 0.8F + 0.1F;
					float f2 = furnaceRand.nextFloat() * 0.8F + 0.1F;
					do {
						if (itemstack.getCount() <= 0) {
							continue label0;
						}
						int i1 = furnaceRand.nextInt(21) + 10;
						if (i1 > itemstack.getCount()) {
							i1 = itemstack.getCount();
						}
						itemstack.shrink(i1);
						EntityItem entityitem = new EntityItem(world, pos.getX() + f, pos.getY() + f1, pos.getZ() + f2, itemstack.splitStack(i1));
						float f3 = 0.05F;
						entityitem.motionX = (float) furnaceRand.nextGaussian() * f3;
						entityitem.motionY = (float) furnaceRand.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float) furnaceRand.nextGaussian() * f3;
						world.spawnEntity(entityitem);
					} while (true);
				}
			}
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
		TileEntityOpenHearthFurnace te = (TileEntityOpenHearthFurnace) world.getTileEntity(pos);
		if (te != null) {
			int dir = MathHelper.floor((entityliving.rotationYaw * 4F) / 360F + 0.5D) & 3;
			te.setFacing(EnumFacing.byHorizontalIndex(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
			world.notifyBlockUpdate(pos, state, state, 3);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int i) {
		return new TileEntityOpenHearthFurnace();
	}

	@Override
	public TileEntity createTileEntity(World var1, int i) {
		return new TileEntityOpenHearthFurnace();
	}
	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getTexture(int x, int y, int z){
		return new ResourceLocation(Info.modID,
				((x==0&&y==0&&z==0)|| CommonUtil.getBlockAt(Minecraft.getMinecraft().world,x,y,z)==TCBlocks.distilActive)?
						"textures/blocks/furnace_on.png":"textures/blocks/furnace_off.png");
	}

}
