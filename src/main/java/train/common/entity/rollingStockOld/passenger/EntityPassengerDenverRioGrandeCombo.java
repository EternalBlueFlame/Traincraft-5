package train.common.entity.rollingStockOld.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;

public class EntityPassengerDenverRioGrandeCombo extends EntityRollingStock implements IPassenger {

	public EntityPassengerDenverRioGrandeCombo(World world) {
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
		return 3.15F;
	}
	@Override
	public float[][] getRiderOffsets(){return new float[][]{{1.6f,1.5f, 0f},{0.2f,1.4f, 0f},{-1f,1.5f, 0f},{-2.2f,1.5f, 0f}};}
    
}