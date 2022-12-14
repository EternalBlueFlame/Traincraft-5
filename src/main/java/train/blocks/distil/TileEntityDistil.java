package train.blocks.distil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.blocks.BlockDynamic;
import ebf.tim.blocks.TileEntityStorage;
import ebf.tim.registry.TiMFluids;
import ebf.tim.registry.TiMOres;
import ebf.tim.utility.CommonUtil;
import ebf.tim.utility.ItemStackSlot;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import train.blocks.TCBlocks;
import train.library.ItemIDs;

import java.util.ArrayList;
import java.util.Random;

public class TileEntityDistil extends TileEntityStorage implements ISidedInventory {

	public int distilBurnTime;
	public int currentItemBurnTime;
	public int distilCookTime;
	private int cookDuration;
	private Random random;
	private int updateTicks;
	public boolean wasBurning=false;

	public TileEntityDistil(){}

	public TileEntityDistil(BlockDynamic host) {
		//slots 0=input 1=fuel 3=output 2=input canister ?=filled canister
		super(host);
		initInventoryFromBlock( host );
	}

	protected void initInventoryFromBlock( BlockDynamic block )
	{
		if ( host == null ) {
			super.initInventoryFromBlock(block);
		}

		distilBurnTime = 0;
		currentItemBurnTime = 0;
		distilCookTime = 0;
		cookDuration = 400;//default is 200
		random = new Random();
		storageType=-1;
		//this.theTank = LiquidManager.getInstance().new FilteredTank(maxTank, new FluidStack[]{TiMFluids.fluidDiesel, }, 1);
		inventory=new ArrayList<>();

		inventory.add(new ItemStackSlot(this, 400, 56, 17).setCraftingInput(true).setOverlay(TCBlocks.orePetroleum));
		inventory.add(new ItemStackSlot(this, 401, 56, 53).setCraftingInput(true).setOverlay(Items.coal));
		inventory.add(new ItemStackSlot(this, 402, 123, 8).setCraftingInput(true).setOverlay(Items.bucket));

		inventory.add(new ItemStackSlot(this, 403, 116, 60).setCraftingOutput(true));
		inventory.add(new ItemStackSlot(this, 404, 123, 33).setCraftingOutput(true));
	}

	/**
	 * Used by the GUI
	 */
	@SideOnly(Side.CLIENT)
	public int getLiquid() {
		return getTankInfo(0)!=null&&getTankInfo(0).fluid!=null?getTankInfo(0).fluid.amount:0;
	}

	@Override
	public String[][] getTankFilters(){return new String[][]{{"diesel"}};}


	@Override
	public void readFromNBT(NBTTagCompound nbtTag) {
		super.readFromNBT(nbtTag);
		distilBurnTime = nbtTag.getShort("BurnTime");
		distilCookTime = nbtTag.getShort("CookTime");
		currentItemBurnTime = nbtTag.getShort("CurrentItemBurn");
	}

	public int[] getTankCapacity() {
		return new int[]{30000};
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTag) {
		super.writeToNBT(nbtTag);
		nbtTag.setShort("BurnTime", (short) distilBurnTime);
		nbtTag.setShort("CookTime", (short) distilCookTime);
		nbtTag.setShort("CurrentItemBurn", (short) this.currentItemBurnTime);
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


	@Override
	public String getInventoryName(){
		return "distil";
	}
	public boolean isBurning() {
		return distilBurnTime > 0;
	}

	@Override
	public boolean canUpdate(){return true;}

	@Override
	public void updateEntity() {
		if(!worldObj.isRemote){
			updateTicks++;
			boolean flag = distilBurnTime > 0;
			boolean flag1 = false;
			if (distilBurnTime == 0 && canSmelt()) {
				currentItemBurnTime = distilBurnTime = TileEntityFurnace.getItemBurnTime(getSlotIndexByID(401).getStack());
				if (distilBurnTime > 0) {
					if (getSlotIndexByID(401).getStack() != null) {
						if (getSlotIndexByID(401).getStack().getItem().hasContainerItem(getSlotIndexByID(401).getStack())) {
							getSlotIndexByID(401).setStack(new ItemStack(getSlotIndexByID(401).getStack().getItem().getContainerItem()));
						}
						else {
							getSlotIndexByID(401).decrStackSize(1);
						}

						if (getSlotIndexByID(401).getStack()!=null &&
								getSlotIndexByID(401).getStack().stackSize == 0) {
							getSlotIndexByID(401).setStack(null);
						}
					}
				}
			}

			if (isBurning() && canSmelt()) {
				distilCookTime++;
				if (distilCookTime == cookDuration) {
					distilCookTime = 0;
					smeltItem();
				}
			} else {
				distilCookTime = 0;
			}

			if (flag != (distilBurnTime > 0)) {
				flag1 = true;
			}

			if (this.updateTicks % 8 == 0) {
				if (getSlotIndexByID(402).getStack() != null) {

					if(getSlotIndexByID(402).getStack().getItem()==Items.bucket){
						if(getTankInfo(0).fluid.amount>0){
							placeInInvent(FluidContainerRegistry.fillFluidContainer(
									getTankInfo(0).fluid, getSlotIndexByID(402).getStack()));
							flag1 = true;
						}

					} else if (getSlotIndexByID(402).getStack().getItem()== ItemIDs.emptyCanister.item){
						if(getTankInfo(0).fluid.getFluid().getName().toLowerCase().contains("diesel")){
							placeInInvent(new ItemStack(ItemIDs.diesel_canister.item));
							flag1 = true;
						}
					} else if(FluidContainerRegistry.isContainer(getSlotIndexByID(402).getStack()) &&
					fill(null, FluidContainerRegistry.getFluidForFilledItem(getSlotIndexByID(402).getStack()),false)==0){
						fill(null, FluidContainerRegistry.getFluidForFilledItem(getSlotIndexByID(402).getStack()),true);
						ItemStack output = FluidContainerRegistry.drainFluidContainer(getSlotIndexByID(402).getStack());
						if(getSlotIndexByID(404).getStack()==null) {
							getSlotIndexByID(404).setStack(output);
						} else if(getSlotIndexByID(404).getStack().getItem()==output.getItem()){
							getSlotIndexByID(404).decrStackSize(-1);
						}
						getSlotIndexByID(402).decrStackSize(1);
						flag1 = true;
					}
				}
			}

			if (distilBurnTime > 0) {
				distilBurnTime--;
			}
			if (flag1) {
				this.syncTileEntity();
				markDirty();
				CommonUtil.markBlockForUpdate(worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
	}

	private void placeInInvent(ItemStack itemstack1) {
		if (getSlotIndexByID(404).getStack() == null) {
			getSlotIndexByID(404).setStack(itemstack1);
			getSlotIndexByID(402).decrStackSize(1);
			drain(null, 1000, true);
		}
		else if (getSlotIndexByID(404).getItem() == itemstack1.getItem() &&
				(!itemstack1.getHasSubtypes() || getSlotIndexByID(404).getStack().getItemDamage() == itemstack1.getItemDamage())
				&& ItemStack.areItemStackTagsEqual(getSlotIndexByID(404).getStack(), itemstack1)) {
			int var9 = getSlotIndexByID(404).getStack().stackSize + itemstack1.stackSize;
			if (var9 <= itemstack1.getMaxStackSize()) {
				getSlotIndexByID(404).setSlotStacksize(var9);
				getSlotIndexByID(402).decrStackSize(1);
				drain(null, 1000, true);
			}
		}
	}

	private boolean canSmelt() {
		if (getSlotIndexByID(400).getStack() == null ||
				(getSlotIndexByID(403).getStack() != null && getSlotIndexByID(403).getStackSize()==64) ||
				(getSlotIndexByID(404).getStack() != null && getSlotIndexByID(404).getStackSize()==64)) {
			return false;
		}
		ItemStack itemstack = getSmeltingResult(getSlotIndexByID(400).getStack());
		if (itemstack == null || itemstack.getItem()==null) {
			return false;
		}
		FluidStack resultLiquid = FluidContainerRegistry.getFluidForFilledItem(itemstack);
		if (resultLiquid == null) {
			return false;
		}
		int used = fill(null, resultLiquid, false);
		return (used ==0);
	}

	public void smeltItem() {
		if (!canSmelt()) {
			return;
		}
		ItemStack itemstack = getSmeltingResult(getSlotIndexByID(400).getStack());
		ItemStack plasticStack = getPlasticResult(getSlotIndexByID(400).getStack());

		fill(null, FluidContainerRegistry.getFluidForFilledItem(itemstack), true);
		if (random.nextInt(getPlasticChance(getSlotIndexByID(400).getStack())) == 0) {
			outputPlastic(plasticStack, getSlotIndexByID(400).getStack().getItem() == TiMFluids.bucketDiesel);
		}

		this.markDirty();
		CommonUtil.markBlockForUpdate(worldObj, this.xCoord, this.yCoord, this.zCoord);

		if (getSlotIndexByID(400).getStack().getItem().hasContainerItem(getSlotIndexByID(400).getStack())) {
			getSlotIndexByID(400).setStack(new ItemStack(getSlotIndexByID(400).getStack().getItem().getContainerItem()));
		} else {
			getSlotIndexByID(400).getStack().stackSize--;
		}
		if (getSlotIndexByID(400).getStack().stackSize <= 0) {
			getSlotIndexByID(400).setStack(null);
		}
		this.syncTileEntity();
	}

	private void outputPlastic(ItemStack plasticStack, boolean wasDeisel) {
		if (getSlotIndexByID(403).getStack() == null) {
			if(wasDeisel){
				getSlotIndexByID(403).setStack(new ItemStack(Items.bucket,1));
			} else {
				getSlotIndexByID(403).setStack(plasticStack.copy());
			}
		} else if(wasDeisel){
			if(getSlotIndexByID(403).getStack().getItem()==Items.bucket){
				getSlotIndexByID(403).getStack().stackSize += plasticStack.stackSize;
			} else {
				getSlotIndexByID(403).getStack().stackSize += plasticStack.stackSize;
			}
		} else if (getSlotIndexByID(403).getStack().getItem() == plasticStack.getItem()) {
			getSlotIndexByID(403).getStack().stackSize += plasticStack.stackSize;
		}
		this.markDirty();
	}



	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public void markDirty() {
		if(wasBurning!=isBurning()) {
			super.markDirty();
			wasBurning=isBurning();
		} else {
			if (this.worldObj != null) {
				CommonUtil.markBlockForUpdate(worldObj, xCoord, yCoord, zCoord);
				worldObj.markTileEntityChunkModified(xCoord, yCoord, zCoord, this);
				this.worldObj.func_147453_f(this.xCoord, this.yCoord, this.zCoord, CommonUtil.getBlockAt(worldObj, xCoord, yCoord, zCoord));
			}
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		if(this.inventory.size() > 0){
			int[] theInt = new int[inventory.size()];
			for(int i = 0; i < theInt.length; i++){
				theInt[i] = i;
			}
			return theInt;
		} else {
			return new int[0];
		}
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



	public static float getExperience(ItemStack item) {//todo: spawn experience on crafting
		if(item.getItem() == Item.getItemFromBlock(TCBlocks.orePetroleum)){
			return 0.5f;
		}else if(isOil(item)){
			return 1;
		} else if(item.getItem() == Items.reeds || item.getItem() == Items.wheat ||
				item.getItem() == Item.getItemFromBlock(Blocks.leaves)){
			return 0.2f;
		}
		return 0;
	}

	public static  int getPlasticChance(ItemStack item) {
		if(item.getItem()==Item.getItemFromBlock(TCBlocks.orePetroleum) || isOil(item)){
			return 1;
		} else if(item.getItem() == Items.reeds || item.getItem()==Items.wheat){
			return 4;
		} else if (item.getItem()==Item.getItemFromBlock(Blocks.leaves)){
			return 6;
		}
		return 0;
	}

	public static ItemStack getSmeltingResult(ItemStack item) {
		if(item.getItem() == Items.reeds || item.getItem()==Items.wheat ||
				item.getItem()==Item.getItemFromBlock(Blocks.leaves)){
			return new ItemStack(TiMFluids.bucketDiesel, 1);
		} else if(item.getItem()==Item.getItemFromBlock(TCBlocks.orePetroleum)){
			return new ItemStack(TiMFluids.bucketDiesel, 2);
		} else if(isOil(item)){
			return new ItemStack(TiMFluids.bucketFuelOil, 2);
		}
		return null;
	}

	public static ItemStack getPlasticResult(ItemStack item) {
		if(item.getItem() == Items.reeds || isOil(item)){
			return new ItemStack(TiMOres.dustPlastic,2);
		} else if(item.getItem()==Item.getItemFromBlock(TCBlocks.orePetroleum) ||
				item.getItem() ==Item.getItemFromBlock(Blocks.leaves) ||
				item.getItem() == Items.wheat){
			return new ItemStack(TiMOres.dustPlastic,1);
		}
		return null;
	}

	public static boolean isOil(ItemStack s){
		return FluidContainerRegistry.getFluidForFilledItem(s)!=null &&
				(FluidContainerRegistry.getFluidForFilledItem(s).getUnlocalizedName().toLowerCase().contains("diesel")
				|| FluidContainerRegistry.getFluidForFilledItem(s).getUnlocalizedName().toLowerCase().contains("oil"));
	}
}
