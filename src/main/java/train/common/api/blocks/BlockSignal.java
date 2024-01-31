package train.common.api.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//NOTE: TileSwitch is re-used for signals, because the only differences between them are in block logic
public class BlockSignal extends BlockSwitch {

    public BlockSignal(Material material, int storage) {
        super(material, storage);
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
        TileEntity tile = world.getTileEntity(x,y,z);
        if (tile instanceof TileSwitch) {
            ((TileSwitch)tile).setStrength(world.getBlockPowerInput(x,y,z),0);
        }
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        TileEntity tile = world.getTileEntity(x,y,z);
        if (tile instanceof TileSwitch) {
            ((TileSwitch)tile).setStrength(world.getBlockPowerInput(x,y,z),0);
        }
        return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block other) {
        super.onNeighborBlockChange(world, x, y, z, other);
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileSwitch && !world.isRemote) {
            ((TileSwitch) tile).setStrength(world.getBlockPowerInput(x,y,z),0);
        }
    }

}
