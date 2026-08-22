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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import train.common.Traincraft;
import train.common.tile.switchStand.TilesignalSpanish;

import java.util.List;
import java.util.Random;

public class BlocksignalSpanish extends Block {
    private IIcon texture;

    public BlocksignalSpanish() {
        super(Material.ROCK);
        setCreativeTab(Traincraft.tcTab);
        //this.setTickRandomly(true);
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
        return new TilesignalSpanish();
    }

    public int getRenderType() {
        return -1;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);
        TilesignalSpanish te = (TilesignalSpanish) world.getTileEntity(pos);

        if (world.getRedstonePowerFromNeighbors(pos) > 0) {

            te.state = 1;
        }
        /* int l = world.getBlockMetadata(i, j, k); if (l == 2) {
         *
         * te.rot = 2; } if (l == 5) {
         *
         * te.rot = 5; } if (l == 3) {
         *
         * te.rot = 3; } if (l == 4) { te.rot = 4; } */
        //System.out.println("added " + te.rot);
        updateTick(world, pos.getX(), pos.getY(), pos.getZ());
    }


    @SideOnly(Side.CLIENT)
    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        updateTick(world, i, j, k);
    }


    public int tickRate() {
        return 4;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, entityliving, stack);
        TilesignalSpanish te = (TilesignalSpanish) world.getTileEntity(pos);
        if (te != null) {
            int dir = MathHelper.floor((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
            te.setFacing(EnumFacing.byHorizontalIndex(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
            world.notifyBlockUpdate(pos, state, state, 3);
        }
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer entityplayer, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        updateTick(world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }



    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        worldIn.notifyNeighborsOfStateChange(pos, this, true);
        super.breakBlock(worldIn, pos, state);
    }

    protected void dropBlockAsItem(World p_149642_1_, BlockPos p_149642_2_, IBlockState p_149642_3_, ItemStack p_149642_4_) {
        super.dropBlockAsItemWithChance(p_149642_1_, p_149642_2_, p_149642_3_, 1.0F, 0);
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

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {

        TilesignalSpanish te = (TilesignalSpanish) world.getTileEntity(pos);
        if (te == null)
            return;
        if (te.getState() == 1 && !(world.getRedstonePowerFromNeighbors(pos) > 0)) {
            te.setState(0);
        }
        if (te.getState() == 0 && world.getRedstonePowerFromNeighbors(pos) > 0) {
            te.setState(1);
        }
        world.notifyBlockUpdate(pos, state, state, 3);
    }

    public void updateTick(World world, int i, int j, int k) {
        BlockPos pos = new BlockPos(i, j, k);

        TilesignalSpanish te = (TilesignalSpanish) world.getTileEntity(pos);
        if (te == null)
            return;
        //te.rot = l;
        // int l = world.getBlockMetadata(i, j, k);
        if (te.state == 1 && !(world.getRedstonePowerFromNeighbors(pos) > 0)) {
            te.state = 0;
        }
        if (te.state == 0 && world.getRedstonePowerFromNeighbors(pos) > 0) {
            te.state = 1;
        }
    }


    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */



    public IIcon getIcon(int i, int j) {
        return texture;
    }
}

