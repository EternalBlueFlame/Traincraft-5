package train.common.tile;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileMetroMadridPole extends TileEntity {

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
        return EnumFacing.UNKNOWN;
    }

    public void setFacing(EnumFacing face) {
        getWorld().markBlockForUpdate(xCoord, yCoord, zCoord);
        this.facing = face;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {

        if(nbtTag.hasKey("Orientation")) {
            facing = EnumFacing.byHorizontalIndex(nbtTag.getByte("Orientation"));
        }

        super.readFromNBT(nbtTag);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTag) {
        nbtTag.setByte("Orientation", (byte) facing.ordinal());
        super.writeToNBT(nbtTag);


    }

    @Override
    public Packet getDescriptionPacket() {

        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);

        return new SPacketUpdateTileEntity(getPos(), 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
        this.readFromNBT(pkt.func_148857_g());
        super.onDataPacket(net, pkt);
    }
    public boolean canUpdate()
    {
        return false;
    }
}
