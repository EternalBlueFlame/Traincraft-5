package train.blocks.hearth;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.XmlBuilder;
import ebf.tim.blocks.BlockDynamic;
import ebf.tim.blocks.TileEntityStorage;
import ebf.tim.registry.TiMItems;
import ebf.tim.utility.ItemStackSlot;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Random;

public class TileEntityOpenHearthFurnace extends TileEntityStorage {

	public int furnaceBurnTime;
	public int currentItemBurnTime;
	public int furnaceCookTime;
	private int cookDuration;
	private Random random;
	private boolean wasBurning = false;

	public TileEntityOpenHearthFurnace() {}

	public TileEntityOpenHearthFurnace(BlockDynamic host) {
		super(host);
		initInventoryFromBlock( host );
	}

	protected void initInventoryFromBlock( BlockDynamic block )
	{
		if ( host == null )
			super.initInventoryFromBlock( block );

		furnaceBurnTime = 0;
		currentItemBurnTime = 0;
		furnaceCookTime = 0;
		cookDuration = 600;//default is 200
		random = new Random();
		storageType=-1;
		inventory=new ArrayList<>();

		inventory.add(new ItemStackSlot(this, 400, 56, 17).setCraftingInput(true).setOverlay(Items.iron_ingot));//iron
		inventory.add(new ItemStackSlot(this, 401, 35, 17).setCraftingInput(true).setOverlay(TiMItems.graphite));//graphite
		inventory.add(new ItemStackSlot(this, 402, 47, 53).setCraftingInput(true));//burnable
		inventory.add(new ItemStackSlot(this, 403, 116, 35).setCraftingOutput(true));//output
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int i) {
		return (furnaceCookTime * i) / cookDuration;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int i) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = cookDuration;
		}
		return (furnaceBurnTime * i) / currentItemBurnTime;
	}

	public boolean isBurning() {
		return furnaceBurnTime > 0;
	}

	@Override
	public boolean canUpdate(){return true;}

	@Override
	public void updateEntity() {
		boolean flag = furnaceBurnTime > 0;
		boolean flag1 = false;

		if (furnaceBurnTime > 0) {
			furnaceBurnTime--;
		}
		if (!worldObj.isRemote) {
			if (furnaceBurnTime == 0 && canSmelt()) {
				if (getSlotIndexByID(402) != null) {
					currentItemBurnTime = furnaceBurnTime = TileEntityFurnace.getItemBurnTime(getSlotIndexByID(402).getStack());
					if (furnaceBurnTime > 0) {
						flag1 = true;
						if (getSlotIndexByID(402).getItem().hasContainerItem(getSlotIndexByID(402).getStack())) {
							getSlotIndexByID(402).setStack(new ItemStack(getSlotIndexByID(402).getItem().getContainerItem()));
						}
						else {
							getSlotIndexByID(402).decrStackSize(1);
						}
					}
				}
			}
			if (isBurning() && canSmelt()) {
				furnaceCookTime++;
				if (furnaceCookTime == cookDuration) {
					furnaceCookTime = 0;
					smeltItem();
					flag1 = true;
				}
			}
			else {
				furnaceCookTime = 0;
			}
			if (flag != (furnaceBurnTime > 0)) {
				flag1 = true;
			}
		}
		if (flag1) {
			this.syncTileEntity();
			markDirty();
		}
	}


	private boolean canSmelt(){
		//be sure slot 1 is some form of iron
		boolean fail=true;
		if(getSlotIndexByID(400).getStack() != null){
			ArrayList<ItemStack> iron = OreDictionary.getOres("ingotIron");
			for(ItemStack i : iron){
				if(i.getItem()==getSlotIndexByID(400).getItem()){
					fail=false;
				}
			}
		} else {
			return false;
		}
		if(fail){return false;}
		//be sure slot 2 is graphite
		return getSlotIndexByID(401).getStack() != null && getSlotIndexByID(401).getItem() == TiMItems.graphite;
	}

	public void smeltItem() {
		if (!canSmelt()) {
			return;
		}

		ArrayList<ItemStack> steel = OreDictionary.getOres("ingotSteel");

		if (getSlotIndexByID(403).getStack() == null) {
			getSlotIndexByID(403).setStack(new ItemStack(steel.get(0).getItem(),1));

		}
		else {
			for(ItemStack s : steel){
				if(s.getItem()==getSlotIndexByID(403).getItem()){
					getSlotIndexByID(403).decrStackSize(-1);
					break;
				}
			}
		}

		getSlotIndexByID(400).decrStackSize(1);
		getSlotIndexByID(401).decrStackSize(1);

		cookDuration = 1000;
	}


	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setShort("BurnTime", (short) furnaceBurnTime);
		nbt.setShort("CookTime", (short) furnaceCookTime);
		nbt.setShort("ItemBurnTime", (short) currentItemBurnTime);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		furnaceBurnTime = nbt.getShort("BurnTime");
		furnaceCookTime = nbt.getShort("CookTime");
		currentItemBurnTime = nbt.getShort("ItemBurnTime");
	}


	@Override
	public String getInventoryName(){
		return "hearthfurnace";
	}

	@Override
	public void markDirty() {
		if(wasBurning!=isBurning()) {
			super.markDirty();
			wasBurning=isBurning();
		} else {
			if (this.worldObj != null) {
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				worldObj.markTileEntityChunkModified(xCoord, yCoord, zCoord, this);
				this.worldObj.func_147453_f(this.xCoord, this.yCoord, this.zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
			}
		}
	}
}
