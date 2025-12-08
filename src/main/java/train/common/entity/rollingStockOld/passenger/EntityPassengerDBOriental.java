/*******************************************************************************
 * Copyright (c) 2013 Mrbrutal. All rights reserved.
 * 
 * @name Traincraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.entity.rollingStockOld.passenger;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;

public class EntityPassengerDBOriental extends EntityRollingStock implements IPassenger {

	public EntityPassengerDBOriental(World world) {
		super(world);
	}

	public EntityPassengerDBOriental(World world, double d, double d1, double d2) {
		this(world);
		setPosition(d, d1 + (double) yOffset, d2);
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
		return 2.25F;
	}

	public float[] rotationPoints() {
		return new float[]{1f, 0f};
	}
	@Override
	public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f},{-1,1.2f, 0f},{1,1.2f, 0f}};}
    
}