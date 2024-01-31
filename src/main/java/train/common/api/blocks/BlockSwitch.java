package train.common.api.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSwitch extends BlockDynamic {


    public BlockSwitch(Material material, int storage) {
        super(material, storage);
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
                t.toggleEnabled(0);
                world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "random.click", 0.3F, t.getStrength(0)>0 ? 0.6F : 0.5F);
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
        if(t!=null && t.getStrength(0)>0){
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
            te.setStrength(0,0);
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block other) {
        super.onNeighborBlockChange(world, x, y, z, other);
    }
}
