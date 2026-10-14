package train.common.blocks.blockSwitch;
import train.common.core.util.LegacyRender;

import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;
import train.common.tile.switchStand.TileoverheadWireDouble;

import java.util.List;
import java.util.Random;

public class BlockoverheadWireDouble extends Block {

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


    public BlockoverheadWireDouble() {
        super(Material.ROCK);
        setCreativeTab(Traincraft.tcTab);
        this.setTickRandomly(true);
        //this.setBlockBounds(0.5F , 0.0F, 0.5F , 0.5F ,  2.0F, 0.5F);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World p_149743_1_, BlockPos pos, AxisAlignedBB p_149743_5_, List<AxisAlignedBB> p_149743_6_, @Nullable Entity p_149743_7_, boolean isActualState) {
    	int p_149743_2_ = pos.getX(), p_149743_3_ = pos.getY(), p_149743_4_ = pos.getZ();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
    	int metadata = getMetaFromState(state);
        return true;
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
    public TileEntity createTileEntity(World world, IBlockState state) {
    	int metadata = getMetaFromState(state);
        return new TileoverheadWireDouble();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return LegacyRender.renderType(-1);
    }

    @SideOnly(Side.CLIENT)
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @Override
    public void randomDisplayTick(IBlockState state, World par1World, BlockPos pos, Random par5Random) {
    	int par2 = pos.getX(), par3 = pos.getY(), par4 = pos.getZ();

    }


    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
    	int i = pos.getX(), j = pos.getY(), k = pos.getZ();
        super.onBlockPlacedBy(world, new BlockPos(i, j, k), world.getBlockState(new BlockPos(i, j, k)), entityliving, stack);
        TileoverheadWireDouble te = (TileoverheadWireDouble) CommonUtil.getTileEntity(world, i, j, k);
        if (te != null) {
            int dir = CommonUtil.floorDouble((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
            te.setFacing(CommonUtil.getOrientation(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
            CommonUtil.markBlockForUpdate(world, i, j, k);
        }
    }


    public void breakBlock(World p_149749_1_, BlockPos pos, IBlockState state) {
    	int p_149749_2_ = pos.getX(), p_149749_3_ = pos.getY(), p_149749_4_ = pos.getZ(); Block p_149749_5_ = state.getBlock(); int p_149749_6_ = getMetaFromState(state);
        if ((p_149749_6_ & 8) > 0)
        {
            p_149749_1_.notifyNeighborsOfStateChange(new BlockPos(p_149749_2_, p_149749_3_, p_149749_4_), this, true);
            int i1 = p_149749_6_ & 7;

            if (i1 == 1)
            {
                p_149749_1_.notifyNeighborsOfStateChange(new BlockPos(p_149749_2_ - 1, p_149749_3_, p_149749_4_), this, true);
            }
            else if (i1 == 2)
            {
                p_149749_1_.notifyNeighborsOfStateChange(new BlockPos(p_149749_2_ + 1, p_149749_3_, p_149749_4_), this, true);
            }
            else if (i1 == 3)
            {
                p_149749_1_.notifyNeighborsOfStateChange(new BlockPos(p_149749_2_, p_149749_3_, p_149749_4_ - 1), this, true);
            }
            else if (i1 == 4)
            {
                p_149749_1_.notifyNeighborsOfStateChange(new BlockPos(p_149749_2_, p_149749_3_, p_149749_4_ + 1), this, true);
            }
            else if (i1 != 5 && i1 != 6)
            {
                if (i1 == 0 || i1 == 7)
                {
                    p_149749_1_.notifyNeighborsOfStateChange(new BlockPos(p_149749_2_, p_149749_3_ + 1, p_149749_4_), this, true);
                }
            }
            else
            {
                p_149749_1_.notifyNeighborsOfStateChange(new BlockPos(p_149749_2_, p_149749_3_ - 1, p_149749_4_), this, true);
            }
        }

        super.breakBlock(p_149749_1_, pos, state);
    }



    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */



}

