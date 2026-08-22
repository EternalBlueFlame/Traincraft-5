package train.common.api.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileSwitch) {
            ((TileSwitch)tile).setStrength(world.getRedstonePowerFromNeighbors(pos),0);
        }
    }

    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        // TODO 1.12: onBlockPlaced removed; kept as dead method
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
        if (tile instanceof TileSwitch) {
            ((TileSwitch)tile).setStrength(world.getRedstonePowerFromNeighbors(new BlockPos(x, y, z)),0);
        }
        return meta;
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block other, BlockPos fromPos) {
        super.neighborChanged(state, world, pos, other, fromPos);
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileSwitch && !world.isRemote) {
            ((TileSwitch) tile).setStrength(world.getRedstonePowerFromNeighbors(pos),0);
        }
    }

}
