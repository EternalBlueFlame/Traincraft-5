package train.common.entity.rollingStockOld.special;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import train.common.adminbook.ServerLogger;
import train.common.api.EntityRollingStock;

public class EntityPropagandaBritain extends EntityRollingStock {


	public EntityPropagandaBritain(World world) {
		super(world);
	}



	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float i) {
		if (world.isRemote) {
			return true;
		}
		if(canBeDestroyedByPlayer(damagesource))return true;
		super.attackEntityFrom(damagesource, i);
		setRollingDirection(-getRollingDirection());
		setRollingAmplitude(10);
		setDamage(getDamage() + i * 10);
		if (getDamage() > 40) {
			if (getPassengers().get(0) != null) {
				getPassengers().get(0).startRiding(this);
			}
			this.setDead();
			ServerLogger.deleteWagon(this);
			if(damagesource.getEntity() instanceof EntityPlayer) {
				dropCartAsItem(((EntityPlayer)damagesource.getEntity()).capabilities.isCreativeMode);
			}
		}
		return true;
	}
	
	@Override
	public void setDead() {
		super.setDead();
		isDead = true;
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
		return 3.7F;
	}
}