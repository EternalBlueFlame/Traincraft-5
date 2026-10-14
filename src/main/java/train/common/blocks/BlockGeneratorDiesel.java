/*******************************************************************************
 * Copyright (c) 2013 Spitfire4466. All rights reserved.
 *
 * @name TrainCraft
 * @author Spitfire4466
 ******************************************************************************/

package train.common.blocks;
import train.common.core.util.LegacyRender;

import net.minecraft.world.IBlockAccess;

import net.minecraft.util.math.AxisAlignedBB;

import net.minecraft.util.EnumParticleTypes;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumFacing;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import net.minecraft.util.math.BlockPos;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.GuiIDs;
import train.common.library.Info;
import train.common.tile.TileGeneratorDiesel;

import java.util.Random;

public class BlockGeneratorDiesel extends BlockContainer {

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



	public BlockGeneratorDiesel() {
		super(Material.IRON);
		setCreativeTab(Traincraft.tcTab);
		this.setTickRandomly(true);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1F, 1F);
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
		return LegacyRender.renderType(-1);
	}


	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float par7, float par8, float par9) {
		int i = pos.getX(), j = pos.getY(), k = pos.getZ(); int par6 = facing.getIndex();
		TileEntity te = CommonUtil.getTileEntity(world, i, j, k);
		if (player.isSneaking()) {
			return false;
		}
		if (!world.isRemote) {
			if (te != null && te instanceof TileGeneratorDiesel) {
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
    public void neighborChanged(IBlockState state, World par1World, BlockPos pos, Block par5, BlockPos fromPos) {
    	int par2 = pos.getX(), par3 = pos.getY(), par4 = pos.getZ();
        boolean flag = par1World.isBlockPowered(new BlockPos(par2, par3, par4));
        TileGeneratorDiesel tile = (TileGeneratorDiesel)CommonUtil.getTileEntity(par1World, par2, par3, par4);

        if (tile != null)
        {
            tile.powered = flag;
        }
    }

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase living, ItemStack stack) {
		int par2 = pos.getX(), par3 = pos.getY(), par4 = pos.getZ();
		TileGeneratorDiesel te = (TileGeneratorDiesel) CommonUtil.getTileEntity(world, par2, par3, par4);
		int var6 = CommonUtil.floorDouble((double) (living.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int var7 = CommonUtil.getBlockFacing(world, par2, par3, par4);
		++var6;
		var6 %= 4;

		if (var6 == 0) {
			if (te != null) {
				te.setFacing(2 | var7 << 2);
				 CommonUtil.setBlockMeta(world, par2, par3, par4, 2 | var7 << 2);
			}
		}

		if (var6 == 1) {
			if (te != null) {
				te.setFacing(3 | var7 << 2);
				CommonUtil.setBlockMeta(world, par2, par3, par4, 3 | var7 << 2);
			}
		}

		if (var6 == 2) {
			if (te != null) {
				te.setFacing(0 | var7 << 2);
				CommonUtil.setBlockMeta(world, par2, par3, par4, 0 | var7 << 2);
			}
		}

		if (var6 == 3) {
			if (te != null) {
				te.setFacing(1 | var7 << 2);
				CommonUtil.setBlockMeta(world, par2, par3, par4, 1 | var7 << 2);
			}
		}


	}
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		int par2 = pos.getX(), par3 = pos.getY(), par4 = pos.getZ();
		int l = CommonUtil.getBlockFacing(world, par2, par3, par4);
		TileEntity tile = CommonUtil.getTileEntity(world, par2, par3, par4);
		if(tile !=null && tile instanceof TileGeneratorDiesel && ((TileGeneratorDiesel)tile).currentBurnTime > 0){
			double d0 = (double) ((float) par2 + 0.5F);
			double d2 = (double) ((float) par4 + 0.5F);
			double d3 = 1.67D;
			//System.out.println(l+" "+par1World.isRemote);
			switch(l){
			case 0:
				for(int i=0;i<40;i++){
					world.spawnParticle(EnumParticleTypes.getByName("smoke"), d0+0.2, par3 + d3, d2-0.42, 0.0D, 0.0D, 0.0D);
				}
				break;
			case 1:
				for(int i=0;i<40;i++){
					world.spawnParticle(EnumParticleTypes.getByName("smoke"), d0+0.42, par3 + d3, d2+0.2, 0.0D, 0.0D, 0.0D);
				}
				break;
			case 2:
				for(int i=0;i<40;i++){
					world.spawnParticle(EnumParticleTypes.getByName("smoke"), d0-0.2, par3 + d3, d2+0.42, 0.0D, 0.0D, 0.0D);
				}
				break;
			case 3:
				for(int i=0;i<40;i++){
					world.spawnParticle(EnumParticleTypes.getByName("smoke"), d0-0.42, par3 + d3, d2-0.2, 0.0D, 0.0D, 0.0D);
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