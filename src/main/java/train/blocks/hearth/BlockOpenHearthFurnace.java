/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.blocks.hearth;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.TrainsInMotion;
import ebf.tim.blocks.BlockDynamic;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import train.Traincraft;
import train.blocks.TCBlocks;
import train.blocks.distil.TileEntityDistil;
import train.library.GuiIDs;

import java.util.Random;

public class BlockOpenHearthFurnace extends BlockDynamic {


	public BlockOpenHearthFurnace() {
		super(Material.rock,true);
		//setRequiresSelfNotify();
	}

	public ResourceLocation getTexture(int x, int y, int z){
		//todo this is inefficient, do from tile entity
		if(Minecraft.getMinecraft().theWorld!=null &&
				Minecraft.getMinecraft().theWorld.getTileEntity(x,y,z) instanceof TileEntityDistil){
			if(((TileEntityOpenHearthFurnace) Minecraft.getMinecraft().theWorld.getTileEntity(x,y,z)).isBurning()){
				return new ResourceLocation("traincraft", "textures/blocks/furnace_on.png");
			}
		}
		return new ResourceLocation("traincraft", "textures/blocks/furnace_off.png");
	}

	@Override
	public Item getItemDropped(int i, Random random, int j) {
		return Item.getItemFromBlock(TCBlocks.blockHearthFurnace);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity((int) minX, (int) minY, (int) minZ);
		if (te instanceof TileEntityDistil && ((TileEntityDistil) te).isBurning()) {
			float var7 = (float) i + 0.5F;
			float var9 = (float) k + 0.5F;
			float f3 = 0.009F;
			double gaussian = random.nextGaussian() * f3;
			for (int t = 0; t < 50; t++) {
				world.spawnParticle("smoke", var7, (double) j + 1.2F, var9, gaussian, gaussian * 0.002F, gaussian);
			}
			world.spawnParticle("flame", var7, (double) j + 1.03F, var9, 0, 0, 0);
			world.spawnParticle("flame", var7 + 0.06, (double) j + 1.03F, var9 + 0.06, 0, 0, 0);
			world.spawnParticle("flame", var7 - 0.06, (double) j + 1.03F, var9 - 0.06, 0, 0, 0);
			world.spawnParticle("flame", var7 + 0.06, (double) j + 1.03F, var9 - 0.06, 0, 0, 0);
			world.spawnParticle("flame", var7 - 0.06, (double) j + 1.03F, var9 + 0.06, 0, 0, 0);
			CommonUtil.getBlockAt(world, i,j,k).setLightLevel(0.8F);
		} else {
			CommonUtil.getBlockAt(world, i,j,k).setLightLevel(0F);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity te = world.getTileEntity(i, j, k);
		if (player.isSneaking()) {
			return false;
		}
		if (!world.isRemote) {
			if (te instanceof TileEntityOpenHearthFurnace) {
				player.openGui(Traincraft.instance, GuiIDs.OPEN_HEARTH_FURNACE, world, i, j, k);
			}
		}
		return true;
	}


	@Override
	public void breakBlock(World world, int i, int j, int k, Block par5, int par6) {
		//todo:keep inventory on break?

		TileEntity te = world.getTileEntity(i,j,k);
		if(te instanceof TileEntityOpenHearthFurnace && world.getGameRules().getGameRuleBooleanValue("doTileDrops")){
			((TileEntityOpenHearthFurnace) te).dropInventory();
		}
		super.breakBlock(world, i, j, k, par5, par6);
	}


	@Override
	public TileEntity createNewTileEntity(World var1, int i) {
		return new TileEntityOpenHearthFurnace(this);
	}

}
