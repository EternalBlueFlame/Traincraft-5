package train.common.entity.rollingStockOld;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.api.LiquidManager;
import train.common.api.SteamTrain;
import train.common.library.GuiIDs;

public class EntityLocoSteamVBShay extends SteamTrain {
	public EntityLocoSteamVBShay(World world) {
		super(world, LiquidManager.WATER_FILTER);
	}

	public EntityLocoSteamVBShay(World world, double d, double d1, double d2) {
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
	public void updateRiderPosition() {
		if(riddenByEntity==null){return;}
		riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset() + 0.65, posZ);// default
	}
	@Override
	public boolean shouldRiderSit(){return false;}

	@Override
	public void setDead() {
		super.setDead();
		isDead = true;
	}

	@Override
	public void pressKey(int i) {
		if (i == 7 && riddenByEntity instanceof EntityPlayer) {
			((EntityPlayer) riddenByEntity).openGui(Traincraft.instance, GuiIDs.LOCO, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
		}
	}
	@Override
	public String getInventoryName() {
		return "Vertical Boiler Shay";
	}

	@Override
	public boolean interactFirst(EntityPlayer entityplayer) {
		playerEntity = entityplayer;
		if ((super.interactFirst(entityplayer))) {
			return false;
		}
		if (!worldObj.isRemote) {
			if (riddenByEntity != null && (riddenByEntity instanceof EntityPlayer) && riddenByEntity != entityplayer) {
				return true;
			}
			entityplayer.mountEntity(this);
		}
		return true;
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 2.275F;
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