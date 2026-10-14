package train.common.api;

import train.common.core.util.StackUtil;

import train.common.core.util.FluidContainers;

import net.minecraft.util.math.MathHelper;

import ebf.tim.utility.CommonUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import train.common.adminbook.ServerLogger;
import train.common.entity.rollingStockOld.freight.EntityTankLava;
import train.common.library.ItemIDs;

import javax.annotation.Nullable;

public class LiquidTank extends EntityRollingStock implements ISidedInventory {
    private int capacity;
    protected ItemStack[] cargoItems;
    private int update = 8;
    private FluidTank theTank;
    public TileEntity[] blocksToCheck;

	/**
	 * 
	 * @param world
	 * @param fluid
	 * @param quantity
	 */
	public LiquidTank(World world, Fluid fluid, int quantity) {
			this(new FluidStack(fluid, quantity), world);
	}
	public LiquidTank(World world) {
		this(null, world);
	}

    private LiquidTank(@Nullable FluidStack liquid, World world) {
        super(world);
        this.capacity = getTankCapacity()[0];
        this.theTank = new FluidTank(liquid, capacity);
        // slot 0: container to fill/empty, slot 1: output. The 1.7.10 subclasses allocated this themselves; the
        // generated ones don't, and the tank GUI crashed the server (NPE in getStackInSlot)
        this.cargoItems = new ItemStack[2];
        getDataWatcher().addObject(4, 0);
        getDataWatcher().addObject(22, "");

    }

    public int getAmount() {
        return (getDataWatcher().getWatchableObjectInt(18));
    }

    public int getLiquidItemID() {
        return (getDataWatcher().getWatchableObjectInt(4));
    }

    public String getLiquidName() {
        return (getDataWatcher().getWatchableObjectString(22));
    }

    public FluidTank getTank() {
        return theTank;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        this.theTank.writeToNBT(nbttagcompound);
        net.minecraft.nbt.NBTTagList items = new net.minecraft.nbt.NBTTagList();
        for (int i = 0; i < cargoItems.length; i++) {
            if (cargoItems[i] != null && !cargoItems[i].isEmpty()) {
                NBTTagCompound slot = new NBTTagCompound();
                slot.setByte("Slot", (byte) i);
                cargoItems[i].writeToNBT(slot);
                items.appendTag(slot);
            }
        }
        nbttagcompound.setTag("Items", items);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        this.theTank.readFromNBT(nbttagcompound);
        net.minecraft.nbt.NBTTagList items = nbttagcompound.getTagList("Items", net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound slot = items.getCompoundTagAt(i);
            int j = slot.getByte("Slot") & 0xff;
            if (j < cargoItems.length) {
                ItemStack stack = new ItemStack(slot);
                cargoItems[j] = stack.isEmpty() ? null : stack;
            }
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (world.isRemote) {
            return;
        }
        checkInvent(cargoItems[0]); // was in every 1.7.10 tank subclass

        if (ticksExisted % 5 == 0 && fill(new FluidStack(FluidRegistry.WATER, 100), false) == 100) {
            FluidStack drain = null;
            blocksToCheck = new TileEntity[]{CommonUtil.getTileEntity(world, MathHelper.floor(posX), MathHelper.floor(posY - 1), MathHelper.floor(posZ)),
                    CommonUtil.getTileEntity(world, MathHelper.floor(posX), MathHelper.floor(posY + 2), MathHelper.floor(posZ)),
                    CommonUtil.getTileEntity(world, MathHelper.floor(posX), MathHelper.floor(posY + 3), MathHelper.floor(posZ)),
                    CommonUtil.getTileEntity(world, MathHelper.floor(posX), MathHelper.floor(posY + 4), MathHelper.floor(posZ))
            };

            for (TileEntity block : blocksToCheck) {
                if (drain == null && FluidContainers.hasHandler(block)) {
                    for (EnumFacing direction : EnumFacing.VALUES) {
                        if (FluidContainers.drain(block, direction, 100, false) != null &&
                                FluidContainers.drain(block, direction, 100, false).getFluid() == FluidRegistry.WATER &&
                                FluidContainers.drain(block, direction, 100, false).amount == 100
                        ) {
                            drain = FluidContainers.drain(block, direction, 100, true);
                        }
                    }
                }
            }

            if (drain != null) {
                fill(drain, true);
            }
        }


        if (theTank != null && theTank.getFluid() != null) {
            getDataWatcher().updateObject(18, theTank.getFluid().amount);
            getDataWatcher().updateObject(4, LiquidManager.fluidId(theTank.getFluid().getFluid()));
            if (theTank.getFluid().getFluid() != null)
                getDataWatcher().updateObject(22, theTank.getFluid().getFluid().getUnlocalizedName());
            handleMass();
        } else if (theTank != null && theTank.getFluid() == null) {
            getDataWatcher().updateObject(18, 0);
            getDataWatcher().updateObject(4, 0);
            getDataWatcher().updateObject(22, "");
        }
    }

    /**
     * Handle mass depending on liquid amount
     */
    protected void handleMass() {
        if (this.ticksExisted % 10 != 0 && theTank.getFluid().amount > 0) {
            this.mass = this.getDefaultMass();
            double preciseAmount = theTank.getFluid().amount;
            mass += (preciseAmount / 10000);//1 bucket = 1 kilo
        }
    }

    /** lava/molten tanks; the check only knew the 1.7.10 class, so the registered lava tank refused lava */
    protected boolean isHotFluidTank() {
        return this instanceof EntityTankLava || this instanceof train.common.entity.stock.tanker.EntityTankLava;
    }

    public ItemStack checkInvent(ItemStack itemstack) {
        ItemStack result = null;
        if (world.isRemote) {
            return itemstack;
        }
        this.update += 1;
        if (this.update % 8 == 0 && itemstack != null) {
            ItemStack emptyItem = itemstack.getItem().getContainerItem(itemstack);
            if (emptyItem != null && emptyItem.isEmpty()) {
                emptyItem = null; // 1.12 returns EMPTY instead of null
            }
            if (cargoItems[1] == null) {// If the output slot is empty...
                if (theTank.getFluidAmount() == 0) {// Adding the first fluid to the tank...
                    for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
                        if (LiquidManager.getInstance().containsFluid(itemstack,
                                FluidRegistry.getFluidStack(FluidRegistry.getFluidName(fluid), 0))) {
                            if (fluid.getTemperature() < 1000) {
                                if (!isHotFluidTank()) {// Input fluid from itemstack (not lava or molten liquids).
                                    result = LiquidManager.getInstance().processContainer(this, 0, this, itemstack);
                                    break;
                                }
                            } else {
                                if (isHotFluidTank()) {// Input fluid from itemstack (lava or molten liquids).
                                    result = LiquidManager.getInstance().processContainer(this, 0, this, itemstack);
                                    break;
                                }
                            }
                        }
                    }
                } else {// Output fluid into itemstack...
                    result = LiquidManager.getInstance().processContainer(this, 0, this, itemstack);
                }
            } else if (emptyItem != null) {// Adding or removing fluid to or from the tank (if the tank already has something in it).
                if (emptyItem.getItem() == cargoItems[1].getItem()|| emptyItem.getItem().equals(ItemIDs.emptyCanister.item) && cargoItems[1].getItem().equals(ItemIDs.diesel.item)) {
                    if (cargoItems[1].getCount() + 1 <= cargoItems[1].getMaxStackSize()) {
                        result = LiquidManager.getInstance().processContainer(this, 0, this, itemstack);
                    }
                }
            } else {
                if (itemstack.getItem() == cargoItems[1].getItem()) {
                    if (cargoItems[1].getCount() + 1 <= cargoItems[1].getMaxStackSize()) {
                        result = LiquidManager.getInstance().processContainer(this, 0, this, itemstack);
                    }
                }
            }

            if (result != null && result.isEmpty()) {
                result = null; // 1.12: containers without a container item give EMPTY
            }
            if (result != null) {
                if (cargoItems[1] == null) {
                    cargoItems[1] = result;
                } else if (cargoItems[1].getItem() == result.getItem()) {
                    cargoItems[1].grow(1);
                }
            }
        }
        return itemstack;
    }

    public void setLiquid(FluidTank liquid) {
        this.theTank = liquid;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    // private int placeInSpecialInvent(ItemStack itemstack1, int i, boolean doAdd) {
    // if (cargoItems[i] == null) {
    // if (doAdd)
    // cargoItems[i] = itemstack1;
    // return itemstack1.stackSize;
    // }
    // else if (cargoItems[i] != null && cargoItems[i].getItem() == itemstack1.getItem() &&
    // itemstack1.isStackable() && (!itemstack1.getHasSubtypes() || cargoItems[i].getItemDamage() ==
    // itemstack1.getItemDamage()) && ItemStack.areItemStackTagsEqual(cargoItems[i], itemstack1)) {
    //
    // int var9 = cargoItems[i].stackSize + itemstack1.stackSize;
    // if (var9 <= itemstack1.getMaxStackSize()) {
    // if (doAdd)
    // cargoItems[i].stackSize = var9;
    // return var9;
    // }
    // else if (cargoItems[i].stackSize < itemstack1.getMaxStackSize()) {
    // if (doAdd)
    // cargoItems[i].stackSize = cargoItems[i].getMaxStackSize();
    // return Math.abs(cargoItems[i].getMaxStackSize() - cargoItems[i].stackSize -
    // itemstack1.stackSize);
    // }
    // }
    // return itemstack1.stackSize;
    // }

    //TODO Fix ISided Inventory buildcraft support
	/*
	/**
	 * Offers an ItemStack for addition to the inventory.
	 * 
	 * @param stack ItemStack offered for addition. Do not manipulate this!
	 * @param doAdd If false no actual addition should take place.
	 * @param from Orientation the ItemStack is offered from.
	 * @return Amount of items used from the passed stack.
	 */
	/*
	@Override
	public int addItem(ItemStack stack, boolean doAdd, EnumFacing from) {
		if (stack == null) {
			return 0;
		}
		if (LiquidManager.getInstance().isContainer(stack))
			return placeInSpecialInvent(stack, 0, doAdd);
		return 0;
	}
	*/

	/*
	/**
	 * Requests items to be extracted from the inventory
	 * 
	 * @param doRemove If false no actual extraction may occur.
	 * @param from Orientation the ItemStack is requested from.
	 * @param maxItemCount Maximum amount of items to extract (spread over all returned item stacks)
	 * @return Array of item stacks extracted from the inventory
	 */
	/*
	@Override
	public ItemStack[] canExtractItem(boolean doRemove, EnumFacing from, int maxItemCount) {
		return null;
	}
	*/

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, EnumFacing face) {
    	int p_102008_3_ = face.getIndex();
        return false;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing face) {
    	int p_94128_1_ = face.getIndex();
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, EnumFacing face) {
    	int p_102007_3_ = face.getIndex();
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return StackUtil.nn(cargoItems[i]);
    }

    @Override
    public ItemStack removeStackFromSlot(int par1) {
        if (this.cargoItems[par1] != null) {
            ItemStack var2 = this.cargoItems[par1];
            this.cargoItems[par1] = null;
            return StackUtil.nn(var2);
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (cargoItems[i] != null) {
            if (cargoItems[i].getCount() <= j) {
                ItemStack itemstack = cargoItems[i];
                cargoItems[i] = null;
                return StackUtil.nn(itemstack);
            }
            ItemStack itemstack1 = cargoItems[i].splitStack(j);
            if (cargoItems[i].getCount() == 0) {
                cargoItems[i] = null;
            }
            return StackUtil.nn(itemstack1);
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) { if (itemstack != null && itemstack.isEmpty()) itemstack = null;
        cargoItems[i] = itemstack;
        if (itemstack != null && itemstack.getCount() > getInventoryStackLimit()) {
            itemstack.setCount(getInventoryStackLimit());
        }
    }

    @Override
    public void markDirty() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void openInventory(EntityPlayer p) {
    }

    @Override
    public void closeInventory(EntityPlayer p) {
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public int getSizeInventory() {
        return cargoItems.length;
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float i) {
        if (world.isRemote) {
            return true;
        }
        if (canBeDestroyedByPlayer(damagesource))
            return true;
        super.attackEntityFrom(damagesource, i);
        setRollingDirection(-getRollingDirection());
        setRollingAmplitude(10);
        markVelocityChanged();
        setDamage(getDamage() + i * 10);
        if (getDamage() > 40) {
            if (ebf.tim.utility.CommonUtil.rider(this) != null) {
                this.removePassengers(); // 1.7.10 mountEntity(this) dismounted, 1.12 startRiding would mount
            }
            this.setDead();
            ServerLogger.deleteWagon(this);
            if (damagesource.damageType.equals("player")) {
                dropCartAsItem(damagesource.isCreativePlayer());
            }
        }
        return true;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    public FluidStack getFluid() {
        return theTank.getFluid();
    }

    public int getFluidAmount() {
        return theTank.getFluidAmount();
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return theTank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(theTank.getFluid())) {
            return null;
        }
        return theTank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return theTank.drain(maxDrain, doDrain);
    }

    public boolean canfill(Fluid fluid) {
        return true;
    }

        public boolean canDrain(EnumFacing from, Fluid fluid) {
        return true;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return theTank.getTankProperties();
    }
}
