package train.common.entity.rollingStock;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;

public class EntityStockCar extends EntityRollingStock implements IPassenger {
	public EntityStockCar(World world) {
		super(world);
	}

	public EntityStockCar(World world, double d, double d1, double d2) {
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
		if(passenger==null){return;}
		getPassengers().get(0).setPosition(posX, posY + getMountedYOffset() + passenger.getYOffset() + 0.2F, posZ);
	}

	@Override
	public void setDead() {
		super.setDead();
		isDead = true;
	}

	@Override
	public boolean interactFirst(EntityPlayer entityplayer) {
		playerEntity = entityplayer;
		if ((super.interactFirst(entityplayer))) {
			return false;
		}
		if (!getWorld().isRemote) {
			ItemStack itemstack = entityplayer.inventory.getCurrentItem();
			if(lockThisCart(itemstack, entityplayer))return true;
		}
		if (!getWorld().isRemote) {
			if (getPassengers().get(0) != null) {
				getPassengers().get(0).mountEntity(this);
				return true;
			}
		}
		return true;
	}
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return !isDead && entityplayer.getDistanceSqToEntity(this) <= 64D;
	}

	@Override
	public boolean canBeRidden() {
		return true;
	}

	@Override
	public boolean isStorageCart() {
		return false;
	}

	@Override
	public boolean isPoweredCart() {
		return false;
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.45F;
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		if (getPassengers().get(0) != null) {

			NBTTagCompound c = new NBTTagCompound();
			if(getPassengers().get(0).writeMountToNBT(c)) {
				nbttagcompound.setTag("mob", c);
			}
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		if(nbttagcompound.hasKey("mob")){
			readEntityFromNBT(nbttagcompound.getCompoundTag("mob"));
		}
	}

}