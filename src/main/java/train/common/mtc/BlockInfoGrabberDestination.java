package train.common.mtc;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInfoGrabberDestination  extends BlockContainer implements IPeripheralProvider {

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


    public static BlockInfoGrabberDestination instance;
    public BlockInfoGrabberDestination(Material mtrl) {
        super(mtrl);
        instance = this;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileInfoGrabberDestination();
    }

    public IPeripheral getPeripheral(World world, BlockPos pos, EnumFacing side) {
        TileEntity tileEntity = world.getTileEntity(pos);
        return tileEntity instanceof IPeripheral ? (IPeripheral) tileEntity : null;
    }


}

