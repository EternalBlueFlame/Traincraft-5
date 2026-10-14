package train.common.api;

import train.common.core.util.StackUtil;

import train.common.core.util.FluidContainers;

import net.minecraft.util.math.MathHelper;

import ebf.tim.utility.CommonUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import train.common.api.LiquidManager.StandardTank;
import train.common.entity.rollingStockOld.special.EntityBUnitDD35;
import train.common.entity.rollingStockOld.special.EntityBUnitEMDF3;
import train.common.entity.rollingStockOld.special.EntityBUnitEMDF7;

public abstract class Tender extends Freight implements IFluidHandler {

    public ItemStack[] tenderItems;
    private int maxTank;
    private int update = 8;
    private StandardTank theTank;
    public TileEntity[] blocksToCheck;

    /**
     *
     * @param world world
     * @param fluid fluid
     * @param quantity quantity
     */
    public Tender(World world, Fluid fluid, int quantity) {
        this(new FluidStack(fluid, quantity), world, null);
    }
    public Tender(World world){
        // the generated tender classes only call this one: it left the tank, the tank data watchers (4, 27) and
        // the item slots uninitialised, so the GUI/HUD and saving the world crashed. Same setup as the 1.7.10 tenders.
        this((FluidStack) null, world, LiquidManager.WATER_FILTER);
    }

    /** fuel slot + 15 cargo slots, as InventoryTender lays them out */
    public static final int TENDER_SLOTS = 16;

    public Tender(World world, Fluid fluid, int quantity, FluidStack filter) {
        this(new FluidStack(fluid, quantity), world, filter);
    }

    private Tender(FluidStack fluid, World world, FluidStack filter) {
        super(world);
        this.maxTank = getTankCapacity()[0];
        if (filter == null)
            this.theTank = LiquidManager.getInstance().new StandardTank(maxTank);
        if (filter != null)
            this.theTank = LiquidManager.getInstance().new FilteredTank(maxTank, filter);
        IFluidTank[] tankArray = new IFluidTank[1];
        tankArray[0] = theTank;
        getDataWatcher().addObject(4, 0);
        getDataWatcher().addObject(27, 0);
        this.tenderItems = new ItemStack[TENDER_SLOTS];
    }

    @Override
    public int getSizeInventory(){
        return this.tenderItems == null ? TENDER_SLOTS : this.tenderItems.length;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        this.theTank.writeToNBT(nbttagcompound);
        NBTTagList items = new NBTTagList();
        for (int i = 0; i < tenderItems.length; i++) {
            if (tenderItems[i] != null && !tenderItems[i].isEmpty()) {
                NBTTagCompound slot = new NBTTagCompound();
                slot.setByte("Slot", (byte) i);
                tenderItems[i].writeToNBT(slot);
                items.appendTag(slot);
            }
        }
        nbttagcompound.setTag("TenderItems", items);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        this.theTank.readFromNBT(nbttagcompound);
        NBTTagList items = nbttagcompound.getTagList("TenderItems", net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound slot = items.getCompoundTagAt(i);
            int j = slot.getByte("Slot") & 0xff;
            if (j < tenderItems.length) {
                ItemStack stack = new ItemStack(slot);
                tenderItems[j] = stack.isEmpty() ? null : stack;
            }
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (world.isRemote)
            return;
        checkInvent(tenderItems[0], this); // was in every 1.7.10 tender subclass
        if (theTank != null && theTank.getFluid() != null) {
            getDataWatcher().updateObject(27, theTank.getFluid().amount);
            getDataWatcher().updateObject(4, LiquidManager.fluidId(theTank.getFluid().getFluid()));
        } else if (theTank != null && theTank.getFluid() == null) {
            getDataWatcher().updateObject(27, 0);
            getDataWatcher().updateObject(4, 0);
        }
    }

    /**
     * handle mass depending on items and liquid
     */
    @Override
    protected void handleMass() {
        if (this.ticksExisted % 10 != 0) return;
        double preciseAmount = 0;
        this.mass = this.getDefaultMass();
        if (theTank != null && theTank.getFluid() != null && theTank.getFluid().amount > 0) {
            preciseAmount = theTank.getFluid().amount;
        }
        this.itemInsideCount = 0;
        for (int i = 0; i < getSizeInventory(); i++) {
            ItemStack itemstack = getStackInSlot(i);
            if (itemstack != null && itemstack.getCount() > 0) {
                this.itemInsideCount += itemstack.getCount();
            }
        }
        mass += (this.itemInsideCount * 0.0001);//1 item = 1 kilo
        mass += (preciseAmount / 10000);//1 bucket = 1 kilo
    }

    /**
     * added for SMP, used by the HUD
     *
     * @return
     */
    public int getWater() {
        return (getDataWatcher().getWatchableObjectInt(27));
    }

    /**
     * used by the GUI
     *
     * @return int
     */
    public int getLiquidItemID() {
        return (getDataWatcher().getWatchableObjectInt(4));
    }

    public int getCartTankCapacity() {
        return maxTank;
    }

    public StandardTank getTank() {
        return theTank;
    }

    private void placeInInvent(ItemStack itemstack1, Tender tender) {
        for (int i = 1; i < tender.tenderItems.length; i++) {
            if (tender.tenderItems[i] == null) {
                tender.tenderItems[i] = itemstack1;
                return;
            } else if (tender.tenderItems[i] != null && tender.tenderItems[i].getItem() == itemstack1.getItem() && itemstack1.isStackable() &&
                    (!itemstack1.getHasSubtypes() || tender.tenderItems[i].getItemDamage() == itemstack1.getItemDamage()) && ItemStack.areItemStackTagsEqual(tender.tenderItems[i], itemstack1)) {
                int var9 = tender.tenderItems[i].getCount() + itemstack1.getCount();
                if (var9 <= tender.tenderItems[i].getMaxStackSize()) {
                    tender.tenderItems[i].setCount(var9);
                    return;
                } else if (tender.tenderItems[i].getCount() < tender.tenderItems[i].getMaxStackSize()) {
                    tender.tenderItems[i].grow(1);
                    return;
                }
            } else if (i == tender.tenderItems.length - 1) {
                entityDropItem(itemstack1, 1);
                return;
            }
        }
    }

    public void liquidInSlot(ItemStack itemstack, Tender tender) {
        if (world.isRemote)
            return;
        this.update += 1;
        if (this.update % 8 == 0 && itemstack != null) {
            ItemStack result = LiquidManager.getInstance().processContainer(this, 0, this, itemstack);
            if (result != null) {
                placeInInvent(result, tender);
                decrStackSize(0, 1);
            }
        }
    }

    protected void checkInvent(ItemStack tenderInvent, Tender loco) {
        if (tenderInvent != null) {
            liquidInSlot(tenderInvent, loco);
        }

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
            if (drain == null && frontLink instanceof LiquidTank
                    && !(frontLink instanceof EntityBUnitEMDF7) && !(frontLink instanceof EntityBUnitEMDF3) && !(frontLink instanceof EntityBUnitDD35)) {
                if (getFluid() == null) {
                    drain = ((LiquidTank) frontLink).drain(new FluidStack(FluidRegistry.WATER, 100), true);
                } else if (getFluid().getFluid() == FluidRegistry.WATER) {
                    drain = ((LiquidTank) frontLink).drain(new FluidStack(FluidRegistry.WATER, 100), true);
                }
            } else if (drain == null && backLink instanceof LiquidTank
                    && !(frontLink instanceof EntityBUnitEMDF7) && !(frontLink instanceof EntityBUnitEMDF3) && !(frontLink instanceof EntityBUnitDD35)) {
                if (getFluid() == null) {
                    drain = ((LiquidTank) backLink).drain(new FluidStack(FluidRegistry.WATER, 100), true);
                } else if (getFluid().getFluid() == FluidRegistry.WATER) {
                    drain = ((LiquidTank) backLink).drain(new FluidStack(FluidRegistry.WATER, 100), true);
                }
            }
            if (drain != null) {
                fill(drain, true);
            }
        }
    }

    /*IInventory implements*/
    @Override
    public ItemStack getStackInSlot(int i) {
        return StackUtil.nn(tenderItems[i]);
    }

    @Override
    public ItemStack removeStackFromSlot(int par1) {
        if (this.tenderItems[par1] != null) {
            ItemStack var2 = this.tenderItems[par1];
            this.tenderItems[par1] = null;
            return StackUtil.nn(var2);
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (tenderItems[i] != null) {
            if (tenderItems[i].getCount() <= j) {
                ItemStack itemstack = tenderItems[i];
                tenderItems[i] = null;
                return StackUtil.nn(itemstack);
            }
            ItemStack itemstack1 = tenderItems[i].splitStack(j);
            if (tenderItems[i].getCount() == 0) {
                tenderItems[i] = null;
            }
            return StackUtil.nn(itemstack1);
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) { if (itemstack != null && itemstack.isEmpty()) itemstack = null;
        tenderItems[i] = itemstack;
        if (itemstack != null && itemstack.getCount() > getInventoryStackLimit()) {
            itemstack.setCount(getInventoryStackLimit());
        }
    }

    public void setLiquid(FluidStack liquid) {
    }

    public void setCapacity(int capacity) {
        this.maxTank = capacity;
    }

    public int getCapacity() {
        return this.maxTank;
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

    public FluidStack getFluid() {
        return theTank.getFluid();
    }

    public int getFluidAmount() {
        return theTank.getFluidAmount();
    }

    @Override
    public void dropCartAsItem(boolean isCreative) {
        if (!itemdropped) {
            super.dropCartAsItem(isCreative);
            for (ItemStack stack : tenderItems) {
                if (stack != null) {
                    entityDropItem(stack, 0);
                }
            }
        }
    }
}