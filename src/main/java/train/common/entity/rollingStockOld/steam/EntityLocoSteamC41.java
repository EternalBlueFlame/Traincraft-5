package train.common.entity.rollingStockOld.steam;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.LiquidManager;
import train.common.api.SteamTrain;

public class EntityLocoSteamC41 extends SteamTrain {
	public EntityLocoSteamC41(World world) {
		super(world, LiquidManager.WATER_FILTER);
	}



	@Override
	public float getPlayerScale() {
		return 0.65f;
	}

	@Override
	public float[][] getRiderOffsets() {

		return new float[][] {{-0.2f,0.2f,-0.4f},{-0.2f,0.2f,0.4f}};
	}

	@Override
	public void setDead() {
		super.setDead();
		isDead = true;
	}

	@Override
	public String getInventoryName() {
		return "C41";
	}



	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 0.6F;
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