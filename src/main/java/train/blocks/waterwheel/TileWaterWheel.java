package train.blocks.waterwheel;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import ebf.tim.blocks.TileEntityStorage;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import train.blocks.TCBlocks;

public class TileWaterWheel extends TileEntityStorage implements IEnergyProvider {
	public EnergyStorage energy= new EnergyStorage(80,80);
	public TileWaterWheel() {
		super(TCBlocks.waterWheel);
	}

	@Override
	public void updateEntity() {

		if(!worldObj.isRemote) {

			Block blockXP = CommonUtil.getBlockAt(worldObj, xCoord+1, yCoord, zCoord);
			Block blockXN = CommonUtil.getBlockAt(worldObj, xCoord-1, yCoord, zCoord);
			Block blockZP = CommonUtil.getBlockAt(worldObj, xCoord, yCoord, zCoord+1);
			Block blockZN = CommonUtil.getBlockAt(worldObj, xCoord, yCoord, zCoord-1);
			Block blockTop = CommonUtil.getBlockAt(worldObj, xCoord, yCoord+1, zCoord);
			Block blockBottom = CommonUtil.getBlockAt(worldObj, xCoord, yCoord-1, zCoord);


			if (blockXP instanceof BlockLiquid && blockXP.getMaterial().isLiquid()
					&& worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord) != 0
					&& blockXP.getMaterial() != Material.lava) {
				this.energy.receiveEnergy(5, false);
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 2, 2);
			} else if (blockXN instanceof BlockLiquid && blockXN.getMaterial().isLiquid()
					&& worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord) != 0
					&& blockXN.getMaterial() != Material.lava) {
				this.energy.receiveEnergy(5, false);
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
			} else if (blockZN instanceof BlockLiquid && blockZN.getMaterial().isLiquid()
					&& worldObj.getBlockMetadata(xCoord, yCoord, zCoord - 1) != 0
					&& blockZN.getMaterial() != Material.lava) {
				this.energy.receiveEnergy(5, false);
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
			} else if (blockZP instanceof BlockLiquid && blockZP.getMaterial().isLiquid()
					&& worldObj.getBlockMetadata(xCoord, yCoord, zCoord + 1) != 0
					&& blockZP.getMaterial() != Material.lava) {
				this.energy.receiveEnergy(5, false);
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 3, 2);
			}else if(blockTop instanceof BlockLiquid && blockTop.getMaterial().isLiquid()&&worldObj.getBlockMetadata(xCoord, yCoord+1, zCoord)!= 0 && blockTop.getMaterial() != Material.lava){
				this.energy.receiveEnergy(5, false);
			}else if(blockBottom instanceof BlockLiquid && blockBottom.getMaterial().isLiquid() &&worldObj.getBlockMetadata(xCoord, yCoord-1, zCoord)!= 0 && blockBottom.getMaterial() != Material.lava){
				this.energy.receiveEnergy(5, false);
			} else {
				setFacing(-1);
			}

			if (this.energy.getEnergyStored() >0) {
				pushEnergy(worldObj, this.xCoord, this.yCoord, this.zCoord, this.energy);
			}

			this.markDirty();
			this.syncTileEntity();
		}

	}

	public int getWaterDir() {
		return facing;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTag){
		super.readFromNBT(nbtTag);
		this.energy.readFromNBT(nbtTag);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTag){
		super.writeToNBT(nbtTag);
		this.energy.writeToNBT(nbtTag);
	}

	public int[] getTankCapacity(){
		return new int[]{30000};
	}

	@Override
	public World getWorldObj(){
		return this.worldObj;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid){
		return false;
	}



	public void pushEnergy(World world, int x, int y, int z, EnergyStorage storage){
		for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity tile = world.getTileEntity(x + side.offsetX, y + side.offsetY, z + side.offsetZ);
			if (tile instanceof IEnergyReceiver && storage.getEnergyStored() > 0) {
				if (((IEnergyReceiver) tile).canConnectEnergy(side.getOpposite())) {
					int receive = ((IEnergyReceiver) tile).receiveEnergy(side.getOpposite(), Math.min(storage.getMaxExtract(), storage.getEnergyStored()), false);
					storage.extractEnergy(receive, false);
				}
			}
		}
	}


	//RF Overrides
	@Override
	public boolean canConnectEnergy(ForgeDirection dir) {
		return true;
	}
	@Override
	public int extractEnergy(ForgeDirection dir, int amount, boolean simulate) {
		return energy.extractEnergy(amount, simulate);
	}
	@Override
	public int getEnergyStored(ForgeDirection dir) {
		return energy.getEnergyStored();
	}
	@Override
	public int getMaxEnergyStored(ForgeDirection dir) {
		return this.energy.getMaxEnergyStored();
	}


}
