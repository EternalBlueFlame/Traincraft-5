package train.common.entity.rollingStock;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.api.AbstractWorkCart;
import train.common.core.util.TraincraftUtil;
import train.common.library.GuiIDs;

public class EntityPassengerRheingoldDining2  extends AbstractWorkCart implements IInventory {
	public EntityPassengerRheingoldDining2(World world) {
		super(world);
		initWorkCart();
	}

	public void initWorkCart() {
		furnaceItemStacks = new ItemStack[3];
		furnaceBurnTime = 0;
		currentItemBurnTime = 0;
		furnaceCookTime = 0;
	}

	public EntityPassengerRheingoldDining2(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + (double) yOffset, d2);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
	}

	@Override
	public void updatePassenger(Entity passenger) {
		TraincraftUtil.updateRider(this, -0.1, 0);
	}

	@Override
	public void setDead() {
		super.setDead();
		isDead = true;
	}

	@Override
	public void pressKey(int i) {
		if (getPassengers().get(0) != null && getPassengers().get(0) instanceof EntityPlayer) {
			if (locked && !((EntityPlayer) getPassengers().get(0)).getDisplayName().toLowerCase().equals(this.trainOwner.toLowerCase())) {
				return;
			}
			if (i == 7) {
				((EntityPlayer) getPassengers().get(0)).openGui(Traincraft.instance, GuiIDs.CRAFTING_CART, getWorld(), (int) this.posX, (int) this.posY, (int) this.posZ);
			}
			if (i == 9) {
				((EntityPlayer) getPassengers().get(0)).openGui(Traincraft.instance, GuiIDs.FURNACE_CART, getWorld(), (int) this.posX, (int) this.posY, (int) this.posZ);
			}
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		updateBurning();
	}

	@Override
	public String getName() {
		return "Rheingold Dining Pantograph";
	}

	@Override
	public boolean interactFirst(EntityPlayer entityplayer) {
		if ((super.interactFirst(entityplayer))) {
			return false;
		}
		if (!getWorld().isRemote) {
			ItemStack itemstack = entityplayer.inventory.getCurrentItem();
			if(lockThisCart(itemstack, entityplayer))return true;
			if (getPassengers().get(0) != null && (getPassengers().get(0) instanceof EntityPlayer) && getPassengers().get(0) != entityplayer) {
				return true;
			}
			if (!getWorld().isRemote) {
				addPassenger(entityplayer);
			}
		}
		return true;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return !isDead && entityplayer.getDistanceSqToEntity(this) <= 64D;
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
		return 3.87F;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	public void markDirty(){}
}