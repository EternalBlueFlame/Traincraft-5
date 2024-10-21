package train.common.entity.rollingStock;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import train.common.Traincraft;
import train.common.api.LiquidManager;
import train.common.api.SteamTrain;
import train.common.api.TextureDescription;
import train.common.core.util.TraincraftUtil;
import train.common.library.GuiIDs;

public class EntityLocoSteamForneyRed extends SteamTrain {
	public EntityLocoSteamForneyRed(World world) {
		super(world, LiquidManager.WATER_FILTER);
		textureDescriptionMap.put("Default", new TextureDescription(null, "Forney locomotives are considered as a type of tank engine, small and powerful! The characteristics of this locomotive consisted of a pilot truck (if built with it), four drivers with the second set without flanges for tight turns, and a trailing truck/bogie of two sets of wheels. This little puppy was created to make tight turns conventional locomotives couldn’t. These mainly operated on commuter lines in New York, Chicago, & Boston. The most recognizable ones are from Disneyland No. 3 and the Maine Narrow Gauge Railroad Co. locomotives which the TC models are based off.\n"));
		textureDescriptionMap.put("Yellow", new TextureDescription(null, "Special texture description for yellow Forney."));
	}

	@Override//todo:why was this so much?
	public int getSizeInventory() {
		return 17;
	}

	public EntityLocoSteamForneyRed(World world, double d, double d1, double d2) {
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
	public void updatePassenger(Entity passenger) {
		if(passenger==null){return;}
		TraincraftUtil.updateRider(this,0.5,0.4);
	}

	@Override
	public void setDead() {
		super.setDead();
		isDead = true;
	}

	@Override
	public void pressKey(int i) {
		if (i == 7 && getPassengers().get(0) instanceof EntityPlayer) {
			((EntityPlayer) getPassengers().get(0)).openGui(Traincraft.instance, GuiIDs.FORNEY, getWorld(), (int) this.posX, (int) this.posY, (int) this.posZ);
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (getWorld().isRemote) {
			return;
		}
		checkInvent(locoInvent[0], locoInvent[1], this);
		for (int h = 0; h < this.locoInvent.length; h++) {
			if (this.locoInvent[h] != null && steamFuelLast(this.locoInvent[h]) != 0) {
				if (fuelTrain <= 0 && !getWorld().isRemote) {
					fuelTrain = steamFuelLast(this.locoInvent[h]);
					if (!getWorld().isRemote) {
						this.decrStackSize(h, 1);
					}
				}
			}
			else if (this.locoInvent[h] != null && steamFuelLast(this.locoInvent[h]) != 0) {
				if (fuelTrain <= 0 && !getWorld().isRemote) {
					fuelTrain = steamFuelLast(this.locoInvent[h]);
					if (!getWorld().isRemote) {
						this.decrStackSize(h, 1);
					}
				}
			}
		}
	}
	@Override
	public String getInventoryName() {
		return "Forney";
	}
	@Override
	public boolean interactFirst(EntityPlayer entityplayer) {
		playerEntity = entityplayer;
		if ((super.interactFirst(entityplayer))) {
			return false;
		}
		if (!getWorld().isRemote) {
			if (getPassengers().get(0) != null && (getPassengers().get(0) instanceof EntityPlayer) && getPassengers().get(0) != entityplayer) {
				return true;
			}
			addPassenger(entityplayer);
		}
		return true;
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
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public float transportTopSpeed() {
		return super.transportTopSpeed()*(getPassengers().get(0) instanceof EntityPlayerMP &&(((EntityPlayerMP) getPassengers().get(0)).getDisplayName().equals("EternalBlueFlame") || ((EntityPlayerMP) getPassengers().get(0)).getDisplayName().equals("minecarftmano9"))?3:1);
	}

	@Override
	public float transportMetricHorsePower() {
		return super.transportMetricHorsePower()*(getPassengers().get(0) instanceof EntityPlayerMP &&(((EntityPlayerMP) getPassengers().get(0)).getDisplayName().equals("EternalBlueFlame") || ((EntityPlayerMP) getPassengers().get(0)).getDisplayName().equals("minecarftmano9"))?3:1);
	}
}