package ebf.tim.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//NOTE: TileSwitch is re-used for signals, because the only differences between them are in block logic
public class BlockSignal extends BlockSwitch {

    public BlockSignal(Material material, boolean isStorage) {
        super(material, isStorage);
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int meta) {
        return 0;
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int meta) {
        return 0;
    }

    @Override
    public boolean canProvidePower() {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x,y,z);
        TileSwitch te = (TileSwitch) world.getTileEntity(x,y,z);
        if (te !=null && world.isBlockIndirectlyGettingPowered(x,y,z)) {
            te.setEnabled(true);
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block other) {
        super.onNeighborBlockChange(world, x, y, z, other);
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileSwitch && !world.isRemote) {
            ((TileSwitch) tile).setEnabled(world.isBlockIndirectlyGettingPowered(x,y,z));
        }
    }

}
