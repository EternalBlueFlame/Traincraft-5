package train.common.blocks.blockSwitch;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import train.common.Traincraft;
import train.common.tile.switchStand.TileoverheadWire;

import java.util.List;
import java.util.Random;

public class BlockoverheadWire extends Block {
    private IIcon texture;

    public BlockoverheadWire() {
        super(Material.ROCK);
        setCreativeTab(Traincraft.tcTab);
        this.setTickRandomly(true);
        //this.setBlockBounds(0.5F , 0.0F, 0.5F , 0.5F ,  2.0F, 0.5F);
    }

    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
    {
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileoverheadWire();
    }

    public int getRenderType() {
        return -1;
    }

    @SideOnly(Side.CLIENT)
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {

    }


    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, entityliving, stack);
        TileoverheadWire te = (TileoverheadWire) world.getTileEntity(pos);
        if (te != null) {
            int dir = MathHelper.floor((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
            te.setFacing(EnumFacing.byHorizontalIndex(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
            world.notifyBlockUpdate(pos, state, state, 3);
        }
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            int i1 = state.getBlock().getMetaFromState(state);
            int j1 = i1 & 7;
            int k1 = 8 - (i1 & 8);
            world.setBlockState(pos, state);
            world.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, k1 > 0 ? 0.6F : 0.5F, false);
            world.notifyNeighborsOfStateChange(pos, this, true);

            if (j1 == 1)
            {
                world.notifyNeighborsOfStateChange(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), this, true);
            }
            else if (j1 == 2)
            {
                world.notifyNeighborsOfStateChange(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), this, true);
            }
            else if (j1 == 3)
            {
                world.notifyNeighborsOfStateChange(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), this, true);
            }
            else if (j1 == 4)
            {
                world.notifyNeighborsOfStateChange(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), this, true);
            }
            else if (j1 != 5 && j1 != 6)
            {
                if (j1 == 0 || j1 == 7)
                {
                    world.notifyNeighborsOfStateChange(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), this, true);
                }
            }
            else
            {
                world.notifyNeighborsOfStateChange(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()), this, true);
            }

            return true;
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.notifyNeighborsOfStateChange(pos, this, true);
        super.breakBlock(worldIn, pos, state);
    }

    /*
    public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_)
    {
        return (p_149709_1_.getBlockMetadata(p_149709_2_, p_149709_3_, p_149709_4_) & 8) > 0 ? 15 : 0;
    }

    public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_)
    {
        int i1 = p_149748_1_.getBlockMetadata(p_149748_2_, p_149748_3_, p_149748_4_);

        if ((i1 & 8) == 0)
        {
            return 0;
        }
        else
        {
            int j1 = i1 & 7;
            return j1 == 0 && p_149748_5_ == 0 ? 15 : (j1 == 7 && p_149748_5_ == 0 ? 15 : (j1 == 6 && p_149748_5_ == 1 ? 15 : (j1 == 5 && p_149748_5_ == 1 ? 15 : (j1 == 4 && p_149748_5_ == 2 ? 15 : (j1 == 3 && p_149748_5_ == 3 ? 15 : (j1 == 2 && p_149748_5_ == 4 ? 15 : (j1 == 1 && p_149748_5_ == 5 ? 15 : 0)))))));
        }
    }

     */

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */



    public IIcon getIcon(int i, int j) {
        return texture;
    }
}

