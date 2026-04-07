package train.common.entity.rollingStockOld.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;

public class EntityPassengerRheingoldPanorama extends EntityRollingStock implements IPassenger {

	public EntityPassengerRheingoldPanorama(World world) {
		super(world);
	}

	

	@Override
	public float getPlayerScale() {
		return 0.65f;
	}

	@Override
	public float[][] getRiderOffsets() {
		return new float[][]{
				{1.0f,0.4f,0f},{-1.0f,0.4f,0f},{-3.05f,-0.25f,0f},{3.05f,-0.25f,0f}};
	}
		@Override
	public void setDead() {
		super.setDead();
		isDead = true;
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
		return 3.95F;
	}
}