package train.common.tile;

import cofh.api.energy.IEnergyProvider;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
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

		if(!world.isRemote) {

			Block blockXP = CommonUtil.getBlockAt(worldObj, xCoord+1, yCoord, zCoord);
			Block blockXN = CommonUtil.getBlockAt(worldObj, xCoord-1, yCoord, zCoord);
			Block blockZP = CommonUtil.getBlockAt(worldObj, xCoord, yCoord, zCoord+1);
			Block blockZN = CommonUtil.getBlockAt(worldObj, xCoord, yCoord, zCoord-1);
			Block blockTop = CommonUtil.getBlockAt(worldObj, xCoord, yCoord+1, zCoord);
			Block blockBottom = CommonUtil.getBlockAt(worldObj, xCoord, yCoord-1, zCoord);


			if (blockXP instanceof BlockLiquid && CommonUtil.getMaterial(blockXP).isLiquid()
					&& CommonUtil.getBlockFacing(worldObj, xCoord + 1, yCoord, zCoord) != 0
					&& CommonUtil.getMaterial(blockXP) != Material.lava) {
				this.energy.receiveEnergy(5, false);
				CommonUtil.setBlockMeta(worldObj, xCoord, yCoord, zCoord, 2, 2);
			} else if (blockXN instanceof BlockLiquid && CommonUtil.getMaterial(blockXN).isLiquid()
					&& CommonUtil.getBlockFacing(worldObj, xCoord - 1, yCoord, zCoord) != 0
					&& CommonUtil.getMaterial(blockXN) != Material.lava) {
				this.energy.receiveEnergy(5, false);
				CommonUtil.setBlockMeta(worldObj, xCoord, yCoord, zCoord, 0, 2);
			} else if (blockZN instanceof BlockLiquid && CommonUtil.getMaterial(blockZN).isLiquid()
					&& CommonUtil.getBlockFacing(worldObj, xCoord, yCoord, zCoord - 1) != 0
					&& CommonUtil.getMaterial(blockZN) != Material.lava) {
				this.energy.receiveEnergy(5, false);
				CommonUtil.setBlockMeta(worldObj, xCoord, yCoord, zCoord, 1, 2);
			} else if (blockZP instanceof BlockLiquid && CommonUtil.getMaterial(blockZP).isLiquid()
					&& CommonUtil.getBlockFacing(worldObj, xCoord, yCoord, zCoord + 1) != 0
					&& CommonUtil.getMaterial(blockZP) != Material.lava) {
				this.energy.receiveEnergy(5, false);
				CommonUtil.setBlockMeta(worldObj, xCoord, yCoord, zCoord, 3, 2);
			}else if(blockTop instanceof BlockLiquid && CommonUtil.getMaterial(blockTop).isLiquid()&&CommonUtil.getBlockFacing(worldObj, xCoord, yCoord+1, zCoord)!= 0 && CommonUtil.getMaterial(blockTop) != Material.lava){
				this.energy.receiveEnergy(5, false);
			}else if(blockBottom instanceof BlockLiquid && CommonUtil.getMaterial(blockBottom).isLiquid() &&CommonUtil.getBlockFacing(worldObj, xCoord, yCoord-1, zCoord)!= 0 && CommonUtil.getMaterial(blockBottom) != Material.lava){
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
