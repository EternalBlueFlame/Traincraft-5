package train.common.entity.rollingStockOld;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.AbstractWorkCart;

public class EntityWorkCart extends AbstractWorkCart implements IInventory {
	public EntityWorkCart(World world) {
		super(world);
		initWorkCart();
	}

	public void initWorkCart() {
		furnaceItemStacks = new ItemStack[3];
		furnaceBurnTime = 0;
		currentItemBurnTime = 0;
		furnaceCookTime = 0;
	}

	public EntityWorkCart(World world, double d, double d1, double d2) {
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
	public float[][] getRiderOffsets() {

		return new float[][]{{0f,-0.15f,0f}};
	}

	@Override
	public void setDead() {
		super.setDead();
		isDead = true;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		updateBurning();
	}

	@Override
	public String getInventoryName() {
		return "Work cart";
	}

	@Override
	public boolean interactFirst(EntityPlayer entityplayer) {
		if ((super.interactFirst(entityplayer))) {
			return false;
		}
		if (!worldObj.isRemote) {
			ItemStack itemstack = entityplayer.inventory.getCurrentItem();
			if(lockThisCart(itemstack, entityplayer))return true;
			if (riddenByEntity != null && (riddenByEntity instanceof EntityPlayer) && riddenByEntity != entityplayer) {
				return true;
			}
			if (!worldObj.isRemote) {
				entityplayer.mountEntity(this);
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
		return (1.8F);
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	public void markDirty(){}
}