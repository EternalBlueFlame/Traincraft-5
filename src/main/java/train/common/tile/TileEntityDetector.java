package train.common.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDetector extends TileEntity {

	// 1.12: modded tile entities are dropped on any block state change by default; only drop them when the block changes
	@Override
	public boolean shouldRefresh(net.minecraft.world.World world, net.minecraft.util.math.BlockPos pos, net.minecraft.block.state.IBlockState oldState, net.minecraft.block.state.IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	public int meta = 6;

	public TileEntityDetector() {}
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {

		// TODO This might raise an exception upon loading; the server might try to assign an instance to some coords only to realise the TileEntity does not exist there. Please verify this does not raise any issues.
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		return nbttagcompound;
	}
}