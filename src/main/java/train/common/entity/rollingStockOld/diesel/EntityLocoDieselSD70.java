package train.common.entity.rollingStockOld.diesel;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.DieselTrain;
import train.common.api.LiquidManager;

public class EntityLocoDieselSD70 extends DieselTrain {
	public EntityLocoDieselSD70(World world) {
		super(world, LiquidManager.dieselFilter());

	}



	@Override
	public float[][] getRiderOffsets() {

		return new float[][]{{2.45f,0,-0.15f}};
	}

	@Override
	public void setDead() {
		super.setDead();
		isDead = true;
	}

	@Override
	public String getInventoryName() {
		return "SD70M";
	}



	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return (1.2F);
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
