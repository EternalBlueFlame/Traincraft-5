package train.common.tile;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileMetroMadridPole extends TileEntity {

	// 1.12: modded tile entities are dropped on any block state change by default; only drop them when the block changes
	@Override
	public boolean shouldRefresh(net.minecraft.world.World world, net.minecraft.util.math.BlockPos pos, net.minecraft.block.state.IBlockState oldState, net.minecraft.block.state.IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}


    private EnumFacing facing;

    @SideOnly(Side.CLIENT)
    @Override
    public double getMaxRenderDistanceSquared() {
        if(FMLClientHandler.instance()!=null && FMLClientHandler.instance().getClient()!=null && FMLClientHandler.instance().getClient().gameSettings!=null){
            if (FMLClientHandler.instance().getClient().gameSettings.renderDistanceChunks == 0) {
                return 30000.0D;
            }
            else if (FMLClientHandler.instance().getClient().gameSettings.renderDistanceChunks == 1) {
                return 15900.0D;
            }
            else if (FMLClientHandler.instance().getClient().gameSettings.renderDistanceChunks == 2) {
                return 4000.0D;
            } else return 4096.0;
        }else{
            return 4096.0;
        }
    }



    public EnumFacing getFacing() {
        if(facing != null){
            return this.facing;
        }
        return null;
    }

    public void setFacing(EnumFacing face) {
        CommonUtil.markBlockForUpdate(world, getPos().getX(), getPos().getY(), getPos().getZ());
        this.facing = face;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {

        if(nbtTag.hasKey("Orientation")) {
            facing = EnumFacing.byIndex(nbtTag.getByte("Orientation"));
        }

        super.readFromNBT(nbtTag);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTag) {
        nbtTag.setByte("Orientation", (byte) facing.ordinal());
        super.writeToNBT(nbtTag);
    	return nbtTag;
    }

    /** 1.12 sends this with the chunk data (1.7.10 sent the description packet instead). */
    @Override
    public NBTTagCompound getUpdateTag() {
    	return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {

        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);

        return new SPacketUpdateTileEntity(getPos(), 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
        this.readFromNBT(pkt.getNbtCompound());
        super.onDataPacket(net, pkt);
    }
    public boolean canUpdateLegacy() {
        return false;
    }
}
