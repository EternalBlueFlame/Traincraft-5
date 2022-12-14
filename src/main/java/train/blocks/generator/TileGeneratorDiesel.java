/*******************************************************************************
 * Copyright (c) 2013 Spitfire4466. All rights reserved.
 *
 * @name TrainCraft
 * @author Spitfire4466
 ******************************************************************************/

package train.blocks.generator;


import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import ebf.tim.blocks.TileEntityStorage;
import ebf.tim.utility.ItemStackSlot;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import train.blocks.TCBlocks;

import java.util.ArrayList;

public class TileGeneratorDiesel extends TileEntityStorage implements IEnergyProvider {

    public boolean powered;
    public EnergyStorage energy = new EnergyStorage(320,80); //core energy value the first value is max storage and the second is transfer max.

    public int currentBurnTime;

    private int lastTankAmount;
    private int lastBurnTime;

    public TileGeneratorDiesel(){
        super(TCBlocks.dieselGenerator);
        //this.theTank = LiquidManager.getInstance().new FilteredTank(30000, LiquidManager.dieselFilter(), 1);

        inventory=new ArrayList<>();
        inventory.add(new ItemStackSlot(this, 400, 56, 17));
        inventory.add(new ItemStackSlot(this, 401, 56, 53));

    }


    @Override
    public boolean canUpdate(){return true;}
    @Override
    public void updateEntity(){
        if(!worldObj.isRemote){
            if(getSlotIndexByID(400).getStack() != null){
                FluidStack stak = FluidContainerRegistry.getFluidForFilledItem(getSlotIndexByID(400).getStack());
                if(stak!=null && stak.amount>0){
                    if(getTankInfo(0).fluid==null || getTankInfo(0).fluid.fluid ==null || getTankInfo(0).fluid.amount==0 ||
                            (getTankInfo(0).fluid.amount+stak.amount<getTankCapacity()[0]&&
                            getTankInfo(0).fluid.fluid == stak.fluid)){

                        if(getSlotIndexByID(401).getStack()==null ||
                                (getSlotIndexByID(401).getItem()== Items.bucket &&getSlotIndexByID(401).getStackSize()< getSlotIndexByID(401).getStack().getMaxStackSize())) {

                            if(getSlotIndexByID(401).getStack()==null){
                                getSlotIndexByID(401).setStack(new ItemStack(Items.bucket));
                            } else {
                                getSlotIndexByID(401).decrStackSize(-1);
                            }
                            fill(ForgeDirection.UNKNOWN, stak, true);
                            getSlotIndexByID(400).decrStackSize(1);
                        }
                    }
                }
                this.markDirty();
            }
            int energyProduced = 70;
            if(this.currentBurnTime > 0){
                this.currentBurnTime--;
                this.energy.receiveEnergy(energyProduced, false);
            }

            if(this.powered && energyProduced <= this.energy.getMaxEnergyStored()-this.energy.getEnergyStored()){
                int fuelUsed = 50;
                if(this.currentBurnTime <= 0 && getTankInfo(0).fluid.amount >= fuelUsed){
                    this.currentBurnTime = 8;
                    drain(ForgeDirection.UNKNOWN,fuelUsed, true);
                    this.markDirty();
                }
            }

            if(this.energy.getEnergyStored() > 0){
                pushEnergy(worldObj, this.xCoord, this.yCoord, this.zCoord, this.energy);
            }
        }

        if(getTankInfo(0).fluid.amount != this.lastTankAmount || this.currentBurnTime != this.lastBurnTime){
            this.lastTankAmount = getTankInfo(0).fluid.amount;
            this.lastBurnTime = this.currentBurnTime;
            this.syncTileEntity();
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTag){
        super.readFromNBT(nbtTag);
        this.powered = nbtTag.getBoolean("Powered");
        this.currentBurnTime = nbtTag.getInteger("BurnTime");
        this.energy.readFromNBT(nbtTag);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTag){
        super.writeToNBT(nbtTag);
        nbtTag.setBoolean("Powered", this.powered);
        nbtTag.setInteger("BurnTime", this.currentBurnTime);
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
