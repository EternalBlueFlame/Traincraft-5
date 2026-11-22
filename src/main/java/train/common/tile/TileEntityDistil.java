package train.common.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.XmlBuilder;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import train.common.api.LiquidManager;
import train.common.api.LiquidManager.StandardTank;
import train.common.api.blocks.TileTraincraft;
import train.common.blocks.BlockDistil;
import train.common.blocks.TCBlocks;
import train.common.library.BlockIDs;
import train.common.library.Info;
import train.common.recipes.DistilRecipes;

import javax.annotation.Nullable;
import java.util.Random;

public class TileEntityDistil extends TileTraincraft implements IFluidHandler {

	public int distilBurnTime;
	public int currentItemBurnTime;
	public int distilCookTime;
	private int cookDuration;
	private Random random;
	private int updateTicks;
	private int maxTank = 0;
	public int amount;
	public int liquidItemID;

	public TileEntityDistil() {
		//slots 0=input 1=fuel 3=output 2=input canister ?=filled canister
		super(5);
		distilBurnTime = 0;
		currentItemBurnTime = 0;
		distilCookTime = 0;
		cookDuration = 400;//default is 200
		random = new Random();
		this.maxTank = 30000;
		this.fluidTank = new FluidTankInfo[]{new FluidTankInfo(new FluidStack(CommonUtil.nullFluid,0),maxTank)};
	}
	@Override
	public String getInventoryName(){
		return "Distillation tower";
	}

	/**
	 * Used by the GUI
	 */
	@SideOnly(Side.CLIENT)
	public int getLiquid() {
		return (amount);
	}

	/**
	 * Used by the GUI
	 */
	@SideOnly(Side.CLIENT)
	public int getLiquidItemID() {
		return liquidItemID;
	}


	@Override
	public void readFromNBT(NBTTagCompound nbtTag, boolean forSyncing) {
		super.readFromNBT(nbtTag, forSyncing);
		distilBurnTime = nbtTag.getShort("BurnTime");
		distilCookTime = nbtTag.getShort("CookTime");
		currentItemBurnTime = nbtTag.getShort("CurrentItemBurn");
		this.amount = nbtTag.getInteger("Amount");
		liquidItemID = nbtTag.getInteger("LiquidID");

		XmlBuilder data = new XmlBuilder(nbtTag.getString("xmlData"));
		if(getTankCapacity()!=null) {
			fluidTank = new FluidTankInfo[getTankCapacity().length];
			for (int i = 0; i < getTankCapacity().length; i++) {
				if (data.containsFluidStack("tanks." + i)) {
					fluidTank[i] = new FluidTankInfo(data.getFluidStack("tanks."+i), getTankCapacity()[i]);
				}
			}
		} else {
			fluidTank= null;
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbtTag, boolean forSyncing) {
		super.writeToNBT(nbtTag, forSyncing);
		nbtTag.setShort("BurnTime", (short) distilBurnTime);
		nbtTag.setShort("CookTime", (short) distilCookTime);
		nbtTag.setInteger("Amount", this.amount);
		nbtTag.setInteger("LiquidID", this.liquidItemID);
		nbtTag.setShort("CurrentItemBurn", (short) this.currentItemBurnTime);

		XmlBuilder data = new XmlBuilder();
		for(int i=0; i<getTankInfo(null).length;i++){
			if(getTankInfo(i)!=null && getTankInfo(i).fluid!=null) {
				data.putFluidStack("tanks." + i, getTankInfo(i).fluid);
			} else if(getTankInfo(i)!=null){
				data.putFluidStack("tanks." + i, null);
			}
		}
		//force build to be sure there's no errors
		data.buildXML();
		if(data.toXMLString() != null && data.toXMLString().length()>1) {
			nbtTag.setString("xmlData", data.toXMLString());
		}
		return nbtTag;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int i) {
		return (distilCookTime * i) / cookDuration;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int i) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = cookDuration;
		}
		return (distilBurnTime * i) / currentItemBurnTime;
	}

	public boolean isBurning() {
		return distilBurnTime > 0;
	}

	@Override
	public boolean canUpdate()
	{
		return true;
	}
	@Override
	public void updateEntity() {
		if(!getWorld().isRemote){
			updateTicks++;
			boolean flag = distilBurnTime > 0;
			boolean flag1 = false;
			if (distilBurnTime == 0 && canSmelt()) {
				currentItemBurnTime = distilBurnTime = TileEntityFurnace.getItemBurnTime(slots[1]);
				if (distilBurnTime > 0) {
					flag1 = true;
					if (slots[1] != null) {
						if (slots[1].getItem().hasContainerItem(slots[1])) {
							slots[1] = new ItemStack(slots[1].getItem().getContainerItem());
						}
						else {
							slots[1].stackSize--;
						}

						if (slots[1].stackSize == 0) {
							slots[1] = null;
						}
					}
				}
			}

			if (isBurning() && canSmelt()) {
				distilCookTime++;
				if (distilCookTime == cookDuration) {
					distilCookTime = 0;
					smeltItem();
					flag1 = true;
				}
			}
			else {
				distilCookTime = 0;
			}

			if (flag != (distilBurnTime > 0)) {
				flag1 = true;
				BlockDistil.updateDistilBlockState(distilBurnTime > 0, getWorld(), xCoord, yCoord, zCoord);
			}
			else {
				flag1 = false;
				BlockDistil.updateDistilBlockState(distilBurnTime > 0, getWorld(), xCoord, yCoord, zCoord);
				CommonUtil.markBlockForUpdate(this.getWorld(), this.xCoord, this.yCoord, this.zCoord);
			}

			if (slots[2] != null) {

				if (this.updateTicks % 8 == 0) {

					ItemStack result = LiquidManager.getInstance().processContainer(this, 2, this, slots[2]);

					if (result != null && placeInInvent(result, 4, false)) {

						placeInInvent(result, 4, true);

						if (getTankInfo(0) != null) {

							amount = getTankInfo(0).fluid.amount;
						}
						else {

							amount = 0;
						}

						if (getTankInfo(0).fluid != null) {

							liquidItemID = getTankInfo(0).fluid.getFluidID();
						}
						else {

							liquidItemID = 0;
						}

						flag1 = true;

						this.markDirty();
						CommonUtil.markBlockForUpdate(this.getWorld(), this.xCoord, this.yCoord, this.zCoord);
					}
				}
			}

			if (getTankInfo(0).fluid != null) {
				amount = getTankInfo(0).fluid.amount;
			}
			else {
				amount = 0;
			}
			if (getTankInfo(0).fluid != null) {
				liquidItemID = getTankInfo(0).fluid.getFluidID();
			}
			else {
				liquidItemID = 0;
			}
			if (updateTicks % 8 == 0){
				this.markDirty();
				CommonUtil.markBlockForUpdate(this.getWorld(), this.xCoord, this.yCoord, this.zCoord);
			}
			if (distilBurnTime > 0) {
				distilBurnTime--;
			}
			if (flag1) {
				this.syncTileEntity();
				markDirty();
			}
		}
	}

	private boolean placeInInvent(ItemStack itemstack1, int i, boolean doAdd) {
		if (CommonUtil.isItemStackEmpty(slots[i])) {
			if (doAdd)
				slots[i] = itemstack1;
			return true;
		}
		else if (slots[i] != null && Item.getIdFromItem(slots[i].getItem()) == Item.getIdFromItem(itemstack1.getItem()) && itemstack1.isStackable() && (!itemstack1.getHasSubtypes() || slots[i].getItemDamage() == itemstack1.getItemDamage()) && ItemStack.areItemStackTagsEqual(slots[i], itemstack1)) {
			int var9 = slots[i].stackSize + itemstack1.stackSize;
			if (var9 <= itemstack1.getMaxStackSize()) {
				if (doAdd)
					slots[i].stackSize = var9;

			}
			else if (slots[i].stackSize < itemstack1.getMaxStackSize()) {
				if (doAdd)
					slots[i].stackSize += 1;
			}
			return true;
		}
		return false;

	}

	private boolean canSmelt() {
		if (slots[0] == null || (slots[3] != null && slots[3].stackSize==64) || (slots[4] != null && slots[4].stackSize==64)) {
			return false;
		}
		ItemStack itemstack = DistilRecipes.smelting().getSmeltingResult(slots[0].getItem());
		if (itemstack == null) {
			return false;
		}
		if (Block.getBlockFromItem(slots[0].getItem()) == BlockIDs.oreTC.block
				&& (slots[0].getItemDamage() != 1 && slots[0].getItemDamage() != 2)) {
			return false;
		}
		FluidStack resultLiquid = FluidContainerRegistry.getFluidForFilledItem(itemstack);
		if (resultLiquid == null)
			return false;
		int used = fill(null, resultLiquid, false);
		return (used >= resultLiquid.amount);
	}

	public void smeltItem() {
		if (!canSmelt()) {
			return;
		}
		ItemStack itemstack = DistilRecipes.smelting().getSmeltingResult(slots[0].getItem());
		ItemStack plasticStack = DistilRecipes.smelting().getPlasticResult(slots[0].getItem());
		int plasticChance = DistilRecipes.smelting().getPlasticChance(slots[0].getItem());
		FluidStack resultLiquid = FluidContainerRegistry.getFluidForFilledItem(itemstack);
		if (resultLiquid == null)
			return;

		int used = fill(null, resultLiquid, false);
		if (used >= resultLiquid.amount)
		{
			fill(null, resultLiquid, true);
			if (random.nextInt(plasticChance) == 0)
				outputPlastic(plasticStack);
			if (getTankInfo(0).fluid != null) {
				amount = getTankInfo(0).fluid.amount;
			}
			if (getTankInfo(0).fluid != null) {
				liquidItemID = getTankInfo(0).fluid.getFluidID();
			}

			this.markDirty();
			CommonUtil.markBlockForUpdate(this.getWorld(), this.xCoord, this.yCoord, this.zCoord);
		}

		if (slots[0].getItem().hasContainerItem(slots[0])) {
			slots[0] = new ItemStack(slots[0].getItem().getContainerItem());
		}
		else {
			slots[0].stackSize--;
		}
		if (slots[0].stackSize <= 0) {
			slots[0] = null;
		}
		this.syncTileEntity();
	}

	private void outputPlastic(ItemStack plasticStack) {
		if (slots[3] == null) {
			slots[3] = plasticStack.copy();
		}
		else if (Item.getIdFromItem(slots[3].getItem()) == Item.getIdFromItem(plasticStack.getItem())) {
			slots[3].stackSize += plasticStack.stackSize;
		}
		this.markDirty();
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}



	/**the fluidTank tank*/
	private FluidTankInfo[] fluidTank = null;


	/**defines the capacity of the fluidTank tank.
	 * each value defibes another tank.
	 * Usually value is 1,000 *the cubic meter capacity, so 242 gallons, is 0.9161 cubic meters, which is 916.1 tank capacity
	 * mind you one water bucket is values at 1000, a full cubic meter of water.
	 *example:
	 * return new int[]{11000, 1000};
	 * may return null*/
	public int[] getTankCapacity(){return null;}

	/** defines the whitelist of fluid names for fluid tanks in order.
	 * null will accept any fluid.
	 * example:
	 * return new String[][]{{"water", "diesel"}, {"lava"}, null}*/
	public String[][] getTankFilters(){return null;}


	@Override
	public int fill(@Nullable ForgeDirection from, FluidStack resource, boolean doFill){
		if(getTankCapacity()==null){return resource.amount;}
		int leftoverDrain=resource.amount;
		for(int stack =0; stack<getTankCapacity().length;stack++) {
			if(getTankFilters()!=null && getTankFilters()[stack]!=null) {
				boolean check=false;
				for (String filter : getTankFilters()[stack]) {
					if (filter.length()==0 || CommonUtil.stringContains(filter,resource.getFluid().getName())){
						check=false;
						break;
					} else {
						check=true;
					}
				}
				if(check){
					continue;
				}
			}
			if(getTankInfo(stack)!=null && (getTankInfo(stack).fluid==null|| getTankInfo(stack).fluid.getFluid()==null
					|| getTankInfo(stack).fluid.amount==0)){

				int fill=leftoverDrain-fluidTank[stack].capacity;
				if (fill < 0) {
					if(doFill) {
						fluidTank[stack] = new FluidTankInfo(resource, getTankInfo(stack).capacity);
					}
					return 0;
				} else {
					leftoverDrain-=getTankInfo(stack).capacity;
					if(doFill) {
						fluidTank[stack] = new FluidTankInfo(
								new FluidStack(resource.getFluid(),getTankInfo(stack).capacity),
								getTankInfo(stack).capacity);
					}
				}
			}

			if (getTankInfo(stack)!=null &&  (
					resource.getFluid() == null || (getTankInfo(stack).fluid!=null &&
							getTankInfo(stack).fluid.getFluid() == resource.getFluid()))) {

				if(leftoverDrain+getTankInfo(stack).fluid.amount>getTankInfo(stack).capacity){
					leftoverDrain-=getTankInfo(stack).capacity-getTankInfo(stack).fluid.amount;
					if(doFill){
						fluidTank[stack] = new FluidTankInfo(
								new FluidStack(resource.fluid, getTankInfo(stack).capacity), getTankInfo(stack).capacity);
					}
				} else if (leftoverDrain+getTankInfo(stack).fluid.amount<0){
					leftoverDrain-=getTankInfo(stack).fluid.amount-resource.amount;
					if(doFill){
						fluidTank[stack] = new FluidTankInfo(
								new FluidStack(getTankInfo(stack).fluid, 0), getTankInfo(stack).capacity);
					}
				} else {
					if(doFill){
						fluidTank[stack] = new FluidTankInfo(
								new FluidStack(resource.fluid, getTankInfo(stack).fluid.amount+leftoverDrain),
								getTankInfo(stack).capacity);
					}
					leftoverDrain=0;
				}
				if(leftoverDrain==0){
					return 0;
				}
			}
		}
		return leftoverDrain;
	}

	@Override
	public FluidStack drain(@Nullable ForgeDirection from, FluidStack resource, boolean doDrain){
		int leftoverDrain=resource.amount;
		for(FluidTankInfo stack : getTankInfo(null)) {
			if (stack.fluid.amount > 0 && (stack.fluid.getFluid()==CommonUtil.nullFluid || stack.fluid.getFluid() == resource.getFluid())) {
				if(leftoverDrain>stack.fluid.amount){
					leftoverDrain-=stack.fluid.amount;
					if(doDrain){
						stack.fluid.amount=0;
					}
				} else {
					if(doDrain){
						stack.fluid.amount-=leftoverDrain;
					}
					return null;
				}
			}
		}
		return resource;

	}

	/*
	 * <h1>Fluid Management</h1>
	 */
	@Override
	public FluidStack drain(@Nullable ForgeDirection from, int drain, boolean doDrain){
		return drain(from, new FluidStack(CommonUtil.nullFluid, drain), doDrain);
	}


	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return getTankInfo(null)!=null;
	}

	@Override
	public boolean canDrain(@Nullable ForgeDirection from, Fluid resource){
		for(FluidTankInfo stack : getTankInfo(null)) {
			if (stack.fluid.amount > 0 && (resource == null || stack.fluid.getFluid() == resource)) {
				return true;
			}
		}
		return false;
	}

	public FluidTankInfo getTankInfo(int tank){
		return getTankInfo(null)[tank];
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from){
		if(getTankCapacity()==null || getTankCapacity().length ==0){
			return new FluidTankInfo[]{};
		}

		if (fluidTank==null || fluidTank.length<getTankCapacity().length) {
			//initialize tanks
			FluidTankInfo[] tanks = new FluidTankInfo[getTankCapacity().length];
			for (int i = 0; i < getTankCapacity().length; i++) {
				tanks[i] = new FluidTankInfo(new FluidStack(FluidRegistry.WATER, 0), getTankCapacity()[i]);
			}
			fluidTank = tanks;
		} else {
			for (int f=0; f<fluidTank.length;f++){
				if(fluidTank[f]==null){
					fluidTank[f]=new FluidTankInfo(new FluidStack(FluidRegistry.WATER, 0), getTankCapacity()[f]);
				}
			}
		}
		return fluidTank;
	}



	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side){
		if(side == 0) return false;          // bottom is extract only
		else if(side == 1) return slot == 0; // insert input into top
		else return slot == 1;               // insert fuel into sides
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side){
		return side != 1 && slot == 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ResourceLocation getTexture(int x, int y, int z){
		return (getWorld()==null|| CommonUtil.getBlockAt(Minecraft.getMinecraft().theWorld,x,y,z)== TCBlocks.distilActive)?
						textureOn:textureOff;
	}


	static final ResourceLocation textureOn = new ResourceLocation(Info.modID, "textures/blocks/distil_on.png");


	static final ResourceLocation textureOff = new ResourceLocation(Info.modID, "textures/blocks/distil_off.png");

}
