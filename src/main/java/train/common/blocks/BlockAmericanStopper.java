/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 *
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.blocks;
import train.common.core.util.LegacyRender;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.tile.TileAmericanStopper;

import static net.minecraft.util.EnumFacing.UP;

public class BlockAmericanStopper extends BlockContainer {

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



    public BlockAmericanStopper() {
        super(Material.IRON);
        setCreativeTab(Traincraft.tcTab);
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
        return LegacyRender.renderType(-1); //RenderingRegistry.getNextAvailableRenderId();
    }


    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        return (world.isSideSolid(new BlockPos(x, y-1, z), UP));
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase living, ItemStack stack) {
    	int par2 = pos.getX(), par3 = pos.getY(), par4 = pos.getZ();
        TileAmericanStopper te = (TileAmericanStopper) CommonUtil.getTileEntity(world, par2, par3, par4);
        int var6 = CommonUtil.floorDouble(living.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int var7 = CommonUtil.getBlockFacing(world, par2, par3, par4) >> 2;
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
        return new TileAmericanStopper(meta);
    }

}