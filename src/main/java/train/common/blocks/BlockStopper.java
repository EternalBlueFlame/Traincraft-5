/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.tile.TileStopper;

import static net.minecraft.util.EnumFacing.UP;

public class BlockStopper extends BlockContainer {

	private IIcon texture;

	public BlockStopper() {
		super(Material.IRON);
		setCreativeTab(Traincraft.tcTab);
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public int getRenderType() {
		return -1; //RenderingRegistry.getNextAvailableRenderId();
	}

	public IIcon getIcon(int i, int j) {
		return texture;
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return (world.isSideSolid(pos.down(), UP));
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase living, ItemStack stack) {
		TileStopper te = (TileStopper) world.getTileEntity(pos);
		int var6 = MathHelper.floor(living.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		int var7 = state.getBlock().getMetaFromState(state) >> 2;
		++var6;
		var6 %= 4;

		if (var6 == 0) {
			if (te != null) {
				te.setFacing(2 | var7 << 2);
			}
		}

		if (var6 == 1) {
			if (te != null) {
				te.setFacing(3 | var7 << 2);
			}
		}

		if (var6 == 2) {
			if (te != null) {
				te.setFacing(0 | var7 << 2);
			}
		}

		if (var6 == 3) {
			if (te != null) {
				te.setFacing(1 | var7 << 2);
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileStopper(meta);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		texture = iconRegister.registerIcon(Info.modID.toLowerCase() + ":stopper");
	}
}