package train.common.core.util;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import train.common.api.blocks.TileTraincraft;

import java.util.Arrays;

public class Energy extends TileTraincraft implements IEnergyProvider {
    public EnergyStorage energy = new EnergyStorage(3000, 80); //core energy value the first value is max storage and the second is transfer max.
    private EnumFacing[] sides = new EnumFacing[]{}; //defines supported sides

    public Energy(int inventorySlots, int maxEnergy, int maxTransfer) {
        super(inventorySlots);
        this.energy.setCapacity(maxEnergy);
        this.energy.setMaxTransfer(maxTransfer);
    }

    public Energy() {
    }

    public void pushEnergy(World world, BlockPos pos, EnergyStorage storage) {
        for (EnumFacing side : getSides()) {
            if (storage.getEnergyStored() <= 0) {
                return;
            }
            TileEntity tile = world.getTileEntity(pos.offset(side));
            if (tile == null) {
                continue;
            }
            int max = Math.min(storage.getMaxExtract(), storage.getEnergyStored());
            if (tile.hasCapability(CapabilityEnergy.ENERGY, side.getOpposite())) {
                IEnergyStorage target = tile.getCapability(CapabilityEnergy.ENERGY, side.getOpposite());
                if (target != null && target.canReceive()) {
                    storage.extractEnergy(target.receiveEnergy(max, false), false);
                }
            } else if (tile instanceof IEnergyReceiver && ((IEnergyReceiver) tile).canConnectEnergy(side.getOpposite())) {
                storage.extractEnergy(((IEnergyReceiver) tile).receiveEnergy(side.getOpposite(), max, false), false);
            }
        }
    }

    /** Forge Energy view of {@link #energy} (extract only), exposed on the supported sides. */
    private final IEnergyStorage forgeEnergy = new IEnergyStorage() {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            return 0;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            return energy.extractEnergy(maxExtract, simulate);
        }

        @Override
        public int getEnergyStored() {
            return energy.getEnergyStored();
        }

        @Override
        public int getMaxEnergyStored() {
            return energy.getMaxEnergyStored();
        }

        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return false;
        }
    };

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return (capability == CapabilityEnergy.ENERGY && (facing == null || Arrays.asList(getSides()).contains(facing)))
                || super.hasCapability(capability, facing);
    }

    @Override
    @Nullable
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY && (facing == null || Arrays.asList(getSides()).contains(facing))) {
            return CapabilityEnergy.ENERGY.cast(forgeEnergy);
        }
        return super.getCapability(capability, facing);
    }


    //Implemented parts from the diesel generator
    @Override
    public void readFromNBT(NBTTagCompound nbtTag, boolean forSyncing) {
        super.readFromNBT(nbtTag, forSyncing);
        this.energy.readFromNBT(nbtTag);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTag, boolean forSyncing) {
        super.writeToNBT(nbtTag, forSyncing);
        this.energy.writeToNBT(nbtTag);
        return nbtTag;
    }

    public void setSides(EnumFacing[] listOfSides) {
        this.sides = listOfSides;
    }

    public EnumFacing[] getSides() {
        return this.sides;
    }

    //RF Overrides
    @Override
    public boolean canConnectEnergy(EnumFacing dir) {
        return Arrays.asList(sides).contains(dir);
    }

    @Override
    public int extractEnergy(EnumFacing dir, int amount, boolean simulate) {
        return energy.extractEnergy(amount, simulate);
    }

    @Override
    public int getEnergyStored(EnumFacing dir) {
        return energy.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing dir) {
        return this.energy.getMaxEnergyStored();
    }
}
