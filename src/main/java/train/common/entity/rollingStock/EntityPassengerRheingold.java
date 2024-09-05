package train.common.entity.rollingStock;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;
import train.common.core.util.TraincraftUtil;

public class EntityPassengerRheingold extends EntityRollingStock implements IPassenger {

	public EntityPassengerRheingold(World world) {
		super(world);
	}

	public EntityPassengerRheingold(World world, double d, double d1, double d2) {
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
	public void updatePassenger(Entity passenger) {
		TraincraftUtil.updateRider(this, -0.3, 0);
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
		return 3.94F;
	}
}