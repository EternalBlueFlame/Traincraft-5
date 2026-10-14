package train.common.tile;

import net.minecraft.util.ITickable;

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

public class TileWaterWheel extends Energy implements ITickable, IEnergyProvider {

	public TileWaterWheel() {
		super(0, 80, 80);
		//super.setSides(new EnumFacing[]{EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH});
	}

	@Override
	public String getName(){return "WaterWheel";}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public void update() {
		super.update();

		if(!world.isRemote) {

			Block blockXP = CommonUtil.getBlockAt(world, getPos().getX()+1, getPos().getY(), getPos().getZ());
			Block blockXN = CommonUtil.getBlockAt(world, getPos().getX()-1, getPos().getY(), getPos().getZ());
			Block blockZP = CommonUtil.getBlockAt(world, getPos().getX(), getPos().getY(), getPos().getZ()+1);
			Block blockZN = CommonUtil.getBlockAt(world, getPos().getX(), getPos().getY(), getPos().getZ()-1);
			Block blockTop = CommonUtil.getBlockAt(world, getPos().getX(), getPos().getY()+1, getPos().getZ());
			Block blockBottom = CommonUtil.getBlockAt(world, getPos().getX(), getPos().getY()-1, getPos().getZ());


			if (blockXP instanceof BlockLiquid && CommonUtil.getMaterial(blockXP).isLiquid()
					&& CommonUtil.getBlockFacing(world, getPos().getX() + 1, getPos().getY(), getPos().getZ()) != 0
					&& CommonUtil.getMaterial(blockXP) != Material.LAVA) {
				this.energy.receiveEnergy(5, false);
				CommonUtil.setBlockMeta(world, getPos().getX(), getPos().getY(), getPos().getZ(), 2, 2);
			} else if (blockXN instanceof BlockLiquid && CommonUtil.getMaterial(blockXN).isLiquid()
					&& CommonUtil.getBlockFacing(world, getPos().getX() - 1, getPos().getY(), getPos().getZ()) != 0
					&& CommonUtil.getMaterial(blockXN) != Material.LAVA) {
				this.energy.receiveEnergy(5, false);
				CommonUtil.setBlockMeta(world, getPos().getX(), getPos().getY(), getPos().getZ(), 0, 2);
			} else if (blockZN instanceof BlockLiquid && CommonUtil.getMaterial(blockZN).isLiquid()
					&& CommonUtil.getBlockFacing(world, getPos().getX(), getPos().getY(), getPos().getZ() - 1) != 0
					&& CommonUtil.getMaterial(blockZN) != Material.LAVA) {
				this.energy.receiveEnergy(5, false);
				CommonUtil.setBlockMeta(world, getPos().getX(), getPos().getY(), getPos().getZ(), 1, 2);
			} else if (blockZP instanceof BlockLiquid && CommonUtil.getMaterial(blockZP).isLiquid()
					&& CommonUtil.getBlockFacing(world, getPos().getX(), getPos().getY(), getPos().getZ() + 1) != 0
					&& CommonUtil.getMaterial(blockZP) != Material.LAVA) {
				this.energy.receiveEnergy(5, false);
				CommonUtil.setBlockMeta(world, getPos().getX(), getPos().getY(), getPos().getZ(), 3, 2);
			}else if(blockTop instanceof BlockLiquid && CommonUtil.getMaterial(blockTop).isLiquid()&&CommonUtil.getBlockFacing(world, getPos().getX(), getPos().getY()+1, getPos().getZ())!= 0 && CommonUtil.getMaterial(blockTop) != Material.LAVA){
				this.energy.receiveEnergy(5, false);
			}else if(blockBottom instanceof BlockLiquid && CommonUtil.getMaterial(blockBottom).isLiquid() &&CommonUtil.getBlockFacing(world, getPos().getX(), getPos().getY()-1, getPos().getZ())!= 0 && CommonUtil.getMaterial(blockBottom) != Material.LAVA){
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
