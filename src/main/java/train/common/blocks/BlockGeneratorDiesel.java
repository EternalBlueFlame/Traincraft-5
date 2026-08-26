/*******************************************************************************
 * Copyright (c) 2013 Spitfire4466. All rights reserved.
 *
 * @name TrainCraft
 * @author Spitfire4466
 ******************************************************************************/

package train.common.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.GuiIDs;
import train.common.library.Info;
import train.common.tile.TileGeneratorDiesel;

import java.util.Random;

public class BlockGeneratorDiesel extends BlockContainer {

	private IIcon texture;

	public BlockGeneratorDiesel() {
		super(Material.IRON);
		setCreativeTab(Traincraft.tcTab);
		this.setTickRandomly(true);
		// TODO 1.12: block bounds are per-state now (getBoundingBox): this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1F, 1F);
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public int getRenderType() {
		return -1; // TODO 1.12: RenderingRegistry.getNextAvailableRenderId()
	}

	public IIcon getIcon(int i, int j) {
		return texture;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntity te = world.getTileEntity(pos);
		if (player.isSneaking()) {
			return false;
		}
		if (!world.isRemote) {
			if (te != null && te instanceof TileGeneratorDiesel) {
				player.openGui(Traincraft.instance, GuiIDs.GENERATOR_DIESEL, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}
	/**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		boolean flag = world.getRedstonePowerFromNeighbors(pos) > 0;
		TileGeneratorDiesel tile = (TileGeneratorDiesel) world.getTileEntity(pos);

		if (tile != null) {
			tile.powered = flag;
		}
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase living, ItemStack stack) {
		TileGeneratorDiesel te = (TileGeneratorDiesel) world.getTileEntity(pos);
		int var6 = MathHelper.floor((double) (living.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int var7 = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
		++var6;
		var6 %= 4;

		if (var6 == 0) {
			if (te != null) {
				te.setFacing(2 | var7 << 2);
				world.setBlockState(pos, state.getBlock().getStateFromMeta(2 | var7 << 2));
			}
		}

		if (var6 == 1) {
			if (te != null) {
				te.setFacing(3 | var7 << 2);
				world.setBlockState(pos, state.getBlock().getStateFromMeta(3 | var7 << 2));
			}
		}

		if (var6 == 2) {
			if (te != null) {
				te.setFacing(0 | var7 << 2);
				world.setBlockState(pos, state.getBlock().getStateFromMeta(0 | var7 << 2));
			}
		}

		if (var6 == 3) {
			if (te != null) {
				te.setFacing(1 | var7 << 2);
				world.setBlockState(pos, state.getBlock().getStateFromMeta(1 | var7 << 2));
			}
		}


	}
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		int l = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
		TileEntity tile = world.getTileEntity(pos);
		if (tile != null && tile instanceof TileGeneratorDiesel && ((TileGeneratorDiesel) tile).currentBurnTime > 0) {
			double d0 = (double) ((float) pos.getX() + 0.5F);
			double d2 = (double) ((float) pos.getZ() + 0.5F);
			double d3 = 1.67D;
			//System.out.println(l+" "+world.isRemote);
			switch (l) {
			case 0:
				for (int i = 0; i < 40; i++) {
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.2, pos.getY() + d3, d2 - 0.42, 0.0D, 0.0D, 0.0D);
				}
				break;
			case 1:
				for (int i = 0; i < 40; i++) {
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.42, pos.getY() + d3, d2 + 0.2, 0.0D, 0.0D, 0.0D);
				}
				break;
			case 2:
				for (int i = 0; i < 40; i++) {
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.2, pos.getY() + d3, d2 + 0.42, 0.0D, 0.0D, 0.0D);
				}
				break;
			case 3:
				for (int i = 0; i < 40; i++) {
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.42, pos.getY() + d3, d2 - 0.2, 0.0D, 0.0D, 0.0D);
				}
				break;
			default:
				break;
			}
			//world.spawnParticle(EnumParticleTypes.FLAME, d0, pos.getY() + d3, d2, 0.0D, 0.0D, 0.0D);
		}
	}
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileGeneratorDiesel();
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		texture = iconRegister.registerIcon(Info.modID.toLowerCase() + ":generator_diesel");
	}
}