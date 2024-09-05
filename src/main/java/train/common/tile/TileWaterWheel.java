package train.common.tile;

import cofh.api.energy.IEnergyProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import train.common.core.util.Energy;

public class TileWaterWheel extends Energy implements IEnergyProvider {

	public TileWaterWheel() {
		super(0, 80, 80);
		//super.setSides(new EnumFacing[]{EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH});
	}

	@Override
	public String getName(){return "WaterWheel";}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if(!getWorld().isRemote) {

			Block blockXP = getWorld().getBlock(xCoord+1, yCoord, zCoord);
			Block blockXN = getWorld().getBlock(xCoord-1, yCoord, zCoord);
			Block blockZP = getWorld().getBlock(xCoord, yCoord, zCoord+1);
			Block blockZN = getWorld().getBlock(xCoord, yCoord, zCoord-1);
			Block blockTop = getWorld().getBlock(xCoord, yCoord+1, zCoord);
			Block blockBottom = getWorld().getBlock(xCoord, yCoord-1, zCoord);


			if (blockXP instanceof BlockLiquid && blockXP.getMaterial().isLiquid()
					&& getWorld().getBlockMetadata(xCoord + 1, yCoord, zCoord) != 0
					&& blockXP.getMaterial() != Material.lava) {
				this.energy.receiveEnergy(5, false);
				getWorld().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 2, 2);
			} else if (blockXN instanceof BlockLiquid && blockXN.getMaterial().isLiquid()
					&& getWorld().getBlockMetadata(xCoord - 1, yCoord, zCoord) != 0
					&& blockXN.getMaterial() != Material.lava) {
				this.energy.receiveEnergy(5, false);
				getWorld().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
			} else if (blockZN instanceof BlockLiquid && blockZN.getMaterial().isLiquid()
					&& getWorld().getBlockMetadata(xCoord, yCoord, zCoord - 1) != 0
					&& blockZN.getMaterial() != Material.lava) {
				this.energy.receiveEnergy(5, false);
				getWorld().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
			} else if (blockZP instanceof BlockLiquid && blockZP.getMaterial().isLiquid()
					&& getWorld().getBlockMetadata(xCoord, yCoord, zCoord + 1) != 0
					&& blockZP.getMaterial() != Material.lava) {
				this.energy.receiveEnergy(5, false);
				getWorld().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 3, 2);
			}else if(blockTop instanceof BlockLiquid && blockTop.getMaterial().isLiquid()&&getWorld().getBlockMetadata(xCoord, yCoord+1, zCoord)!= 0 && blockTop.getMaterial() != Material.lava){
				this.energy.receiveEnergy(5, false);
			}else if(blockBottom instanceof BlockLiquid && blockBottom.getMaterial().isLiquid() &&getWorld().getBlockMetadata(xCoord, yCoord-1, zCoord)!= 0 && blockBottom.getMaterial() != Material.lava){
				this.energy.receiveEnergy(5, false);
			} else {
				setFacing(-1);
			}

			if (this.energy.getEnergyStored() >0) {
				pushEnergy(getWorld(), getPos(), this.energy);
			}

			this.markDirty();
			this.syncTileEntity();
		}

	}

	@Override
	public boolean canConnectEnergy(EnumFacing direction){
		if((this.getBlockMetadata()==1||this.getBlockMetadata()==3) && (direction == EnumFacing.WEST||direction == EnumFacing.EAST)) {
			return true;
		}else if((this.getBlockMetadata()==0||this.getBlockMetadata()==2) && (direction == EnumFacing.NORTH||direction == EnumFacing.SOUTH)){
			return true;
		} else {return false;}
	}


}
