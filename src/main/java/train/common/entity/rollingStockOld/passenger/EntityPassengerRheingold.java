package train.common.entity.rollingStockOld.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;

public class EntityPassengerRheingold extends EntityRollingStock implements IPassenger {

	public EntityPassengerRheingold(World world) {
		super(world);
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
		return 3.94F;
	}
	@Override
	public float[][] getRiderOffsets(){return new float[][]{
            {-2.2f,1.2f, -0.1f},{-1.0f,1.2f, -0.1f},{-0.3f,1.2f, -0.1f},{0.9f,1.2f, -0.1f},{2.1f,1.2f, -0.1f}
    };}
    
}