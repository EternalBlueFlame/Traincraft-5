package train.common.entity.rollingStockOld.electric;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.ElectricTrain;

public class EntityLocoElectricHighSpeedZeroED extends ElectricTrain {
	public EntityLocoElectricHighSpeedZeroED(World world) {
		super(world);
	}



	@Override
	public float[][] getRiderOffsets() {

		return new float[][]{{2.3f,0,-0.15f}};
	}

	@Override
	public void setDead() {
		super.setDead();
		isDead = true;
	}
	@Override
	public String getInventoryName() {
		return "High Speed";
	}



	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 0.56F;
	}

	@Override
	public boolean canBeAdjusted(EntityMinecart cart) {
		return canBeAdjusted;
	}
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
}