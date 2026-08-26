package train.common.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;
import train.common.tile.TileMetroMadridPole;

import java.util.List;

public class BlockMetroMadridPole extends Block implements ITileEntityProvider {
    private IIcon texture;

    public BlockMetroMadridPole(Material p_i45410_2_) {
        super(p_i45410_2_);
        setCreativeTab(Traincraft.tcTab);

        //this.setTickRandomly(true);
        // TODO 1.12: block bounds are per-IBlockState now
        // this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 1F, 1F);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase living, ItemStack stack) {
        TileMetroMadridPole te = (TileMetroMadridPole) world.getTileEntity(pos);
        int dir = MathHelper.floor((double) ((living.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        te.setFacing(EnumFacing.byHorizontalIndex(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
    }

    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_)
    {
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }


    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }


    public TileEntity createTileEntity(World world, int metadata) {
        return new TileMetroMadridPole();
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileMetroMadridPole();
    }


    public int getRenderType() {
        return -1;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        super.breakBlock(world, pos, state);
        if(world.getTileEntity(pos)!=null){
            world.removeTileEntity(pos);
        }
    }








    public IIcon getIcon(int i, int j) {
        return texture;
    }
}
