package ebf.tim.blocks;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import train.blocks.switchstand.TileSwitchStand;

import java.util.List;

public class BlockSwitch extends BlockDynamic {


    public BlockSwitch(Material material, boolean isStorage) {
        super(material, isStorage);
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB box, List otherBoxes, Entity entities) {
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
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
        return -1;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileSwitch(this);
    }


    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else {

            TileSwitch t = (TileSwitch)world.getTileEntity(x,y,z);
            if(t!=null){
                t.toggleEnabled();
                world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "random.click", 0.3F, t.getEnabled() ? 0.6F : 0.5F);
                world.notifyBlocksOfNeighborChange(x, y, z, this);
            }

            return true;
        }
    }

    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
        p_149749_1_.notifyBlocksOfNeighborChange(p_149749_2_, p_149749_3_, p_149749_4_, this);
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }



    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int meta) {
        return isProvidingStrongPower(world, x, y, z, meta);
    }

    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int meta) {
        TileSwitch t = (TileSwitch)world.getTileEntity(x,y,z);
        if(t!=null && t.getEnabled()){
            return 15;
        }
        return 0;
    }

    public boolean canProvidePower() {
        return true;
    }


    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x,y,z);
        TileSwitch te = (TileSwitch) world.getTileEntity(x,y,z);
        if (te !=null) {
            te.setEnabled(false);
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block other) {
        super.onNeighborBlockChange(world, x, y, z, other);
    }
}
