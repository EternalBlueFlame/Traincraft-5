package train.common.entity.rollingStockOld.caboose;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.api.AbstractWorkCart;
import train.common.library.GuiIDs;

public class EntityCabooseWorkCart extends AbstractWorkCart implements IInventory {

	public EntityCabooseWorkCart(World world) {
		super(world);
		initCabooseWorkCart();
	}

	public void initCabooseWorkCart() {
		furnaceItemStacks = new ItemStack[3];
		furnaceBurnTime = 0;
		currentItemBurnTime = 0;
		furnaceCookTime = 0;
	}

	public EntityCabooseWorkCart(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
	}

		@Override
	public void setDead() {
		super.setDead();
		isDead = true;
	}

	@Override
	public void pressKey(int i) {
		if(locked && getPassengers().get(0) != null && getPassengers().get(0) instanceof EntityPlayer&& !((EntityPlayer)getPassengers().get(0)).getDisplayName().toLowerCase().equals(this.trainOwner.toLowerCase())){
			return;
		}
		if (i == 7 && getPassengers().get(0) != null && getPassengers().get(0) instanceof EntityPlayer) {
			((EntityPlayer) getPassengers().get(0)).openGui(Traincraft.instance, GuiIDs.CRAFTING_CART, getWorld(), (int) this.posX, (int) this.posY, (int) this.posZ);
		}
		if (i == 9 && getPassengers().get(0) != null && getPassengers().get(0) instanceof EntityPlayer) {
			((EntityPlayer) getPassengers().get(0)).openGui(Traincraft.instance, GuiIDs.FURNACE_CART, getWorld(), (int) this.posX, (int) this.posY, (int) this.posZ);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		updateBurning();
	}

	@Override
	public String getName() {
		return "Caboose";
	}


	@Override
	public boolean isStorageCart() {
		return false;
	}

	@Override
	public boolean canBeRidden() {
		return true;
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return (1.45F);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return !isDead &&entityplayer.getDistanceSqToEntity(this) <= 124D;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	public void markDirty(){}
	@Override
	public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    
}