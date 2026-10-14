package train.common.entity.rollingStockOld.special;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import train.common.adminbook.ServerLogger;
import train.common.api.EntityRollingStock;

public class EntityPropagandaJapan extends EntityRollingStock {


	public EntityPropagandaJapan(World world) {
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
			if (ebf.tim.utility.CommonUtil.rider(this) != null) {
				this.removePassengers(); // 1.7.10 mountEntity(this) dismounted, 1.12 startRiding would mount
			}
			this.setDead();
			ServerLogger.deleteWagon(this);
			if(damagesource.damageType.equals("player")) {
				dropCartAsItem(damagesource.isCreativePlayer());
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