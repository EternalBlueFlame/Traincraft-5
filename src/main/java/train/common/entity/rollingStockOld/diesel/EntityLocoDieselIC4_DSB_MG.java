package train.common.entity.rollingStockOld.diesel;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.api.DieselTrain;
import train.common.api.LiquidManager;
import train.common.library.GuiIDs;

public class EntityLocoDieselIC4_DSB_MG extends DieselTrain {
	public EntityLocoDieselIC4_DSB_MG(World world) {
		super(world, LiquidManager.dieselFilter());

	}



		@Override
	public void setDead() {
		super.setDead();
		isDead = true;
	}

	@Override
	public void pressKey(int i) {
		if (i == 7 && riddenByEntity != null && riddenByEntity instanceof EntityPlayer) {
			((EntityPlayer) riddenByEntity).openGui(Traincraft.instance, GuiIDs.LOCO, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
		}
	}
	@Override
	public String getInventoryName() {
		return "IC4MG";
	}


	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return (0.1f);
	}

	@Override
	public boolean canBeAdjusted(EntityMinecart cart) {
		return canBeAdjusted;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
	@Override
	public float[][] getRiderOffsets(){return new float[][]{{-3.0f,1.2f, 0f},{2f,1.2f, -0.25f},{-0.4f,1.2f, -0.25f},{-1.2f,1.2f, 0.25f}};}
    
}