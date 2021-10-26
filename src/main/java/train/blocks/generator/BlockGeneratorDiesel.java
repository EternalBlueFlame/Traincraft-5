/*******************************************************************************
 * Copyright (c) 2013 Spitfire4466. All rights reserved.
 *
 * @name TrainCraft
 * @author Spitfire4466
 ******************************************************************************/

package train.blocks.generator;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.blocks.BlockDynamic;
import ebf.tim.blocks.TileRenderFacing;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import train.Traincraft;
import train.library.GuiIDs;

import java.util.Random;

public class BlockGeneratorDiesel extends BlockDynamic {

	public BlockGeneratorDiesel() {
		super(Material.iron, true);
		setCreativeTab(Traincraft.tcTab);
		this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1F, 1F);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
		TileEntity te = world.getTileEntity(i, j, k);
		if (player.isSneaking()) {
			return false;
		}
		if (!world.isRemote) {
			if (te instanceof TileGeneratorDiesel) {
				player.openGui(Traincraft.instance, GuiIDs.GENERATOR_DIESEL, world, i, j, k);
			}
		}
		return true;
	}
	/**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
	@Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
    {
        boolean flag = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4);
        TileGeneratorDiesel tile = (TileGeneratorDiesel)par1World.getTileEntity(par2, par3, par4);

        if (tile != null)
        {
            tile.powered = flag;
        }
    }

	@Override
	public void onBlockPlacedBy(World world, int par2, int par3, int par4, EntityLivingBase living, ItemStack stack) {
		TileEntity te = world.getTileEntity(par2, par3, par4);
		if(!(te instanceof TileRenderFacing)){
			return;
		}
		int var6 = MathHelper.floor_double((double) (living.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int var7 = world.getBlockMetadata(par2, par3, par4) >> 2;
		++var6;
		var6 %= 4;

		if (var6 == 0) {
			((TileRenderFacing) te).setFacing(2 | var7 << 2);
			 world.setBlockMetadataWithNotify(par2, par3, par4, 2 | var7 << 2, 2);
		}

		else if (var6 == 1) {
			((TileRenderFacing) te).setFacing(3 | var7 << 2);
			world.setBlockMetadataWithNotify(par2, par3, par4, 3 | var7 << 2, 2);
		}

		else if (var6 == 2) {
			((TileRenderFacing) te).setFacing(0 | var7 << 2);
			world.setBlockMetadataWithNotify(par2, par3, par4, 0 | var7 << 2, 2);
		}

		else if (var6 == 3) {
			((TileRenderFacing) te).setFacing(1 | var7 << 2);
			world.setBlockMetadataWithNotify(par2, par3, par4, 1 | var7 << 2, 2);
		}

	}
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, int par2, int par3, int par4, Random rand) {
		int l = world.getBlockMetadata(par2, par3, par4);
		TileEntity tile = world.getTileEntity(par2, par3, par4);
		if(tile instanceof TileGeneratorDiesel && ((TileGeneratorDiesel)tile).currentBurnTime > 0){
			double d0 = (double) ((float) par2 + 0.5F);
			double d2 = (double) ((float) par4 + 0.5F);
			double d3 = 1.67D;
			//System.out.println(l+" "+par1World.isRemote);
			switch(l){
			case 0:
				for(int i=0;i<40;i++){
					world.spawnParticle("smoke", d0+0.2, par3 + d3, d2-0.42, 0.0D, 0.0D, 0.0D);
				}
				break;
			case 1:
				for(int i=0;i<40;i++){
					world.spawnParticle("smoke", d0+0.42, par3 + d3, d2+0.2, 0.0D, 0.0D, 0.0D);
				}
				break;
			case 2:
				for(int i=0;i<40;i++){
					world.spawnParticle("smoke", d0-0.2, par3 + d3, d2+0.42, 0.0D, 0.0D, 0.0D);
				}
				break;
			case 3:
				for(int i=0;i<40;i++){
					world.spawnParticle("smoke", d0-0.42, par3 + d3, d2-0.2, 0.0D, 0.0D, 0.0D);
				}
				break;
			default:
				break;
			}
			//world.spawnParticle("flame", d0, par3 + d3, d2, 0.0D, 0.0D, 0.0D);
		}
	}
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileGeneratorDiesel();
	}

}