package train.common.entity.rollingStockOld.electric;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.api.ElectricTrain;
import train.common.library.GuiIDs;

public class EntityLocoElectricE10_DB extends ElectricTrain {
	public EntityLocoElectricE10_DB(World world) {
		super(world);
	}

	public EntityLocoElectricE10_DB(World world, double d, double d1, double d2) {
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
		return "E10 (DB)";
	}



	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1F;
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
	public float[][] getRiderOffsets(){return new float[][]{{-2.1f,1.4f, -0.2f},{2.2f,1.4f, 0.2f}};}
    
}