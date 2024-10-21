package train.common.entity.rollingStock;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import train.common.Traincraft;
import train.common.api.LiquidManager;
import train.common.api.SteamTrain;
import train.common.library.EnumTrains;
import train.common.library.GuiIDs;

public class EntityLocoSteamAdler extends SteamTrain {
	public EntityLocoSteamAdler(World world) {
		super(world, LiquidManager.WATER_FILTER);
	}
	
	public EntityLocoSteamAdler(World world, double d, double d1, double d2) {
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
	public void updatePassenger(Entity passenger) {
		if(passenger==null){return;}
		double distance = -0.1;
		double pitchRads = this.anglePitchClient * 3.141592653589793D / 180.0D;
		float rotationCos1 = (float) Math.cos(Math.toRadians(this.renderYaw + 90));
		float rotationSin1 = (float) Math.sin(Math.toRadians((this.renderYaw + 90)));
		float pitch = (float) (posY + ((Math.tan(pitchRads) * distance) + getMountedYOffset())
				+ passenger.getYOffset() + 0.15);
		double bogieX1 = (this.posX + (rotationCos1 * distance));
		double bogieZ1 = (this.posZ + (rotationSin1* distance));
		
		if(anglePitchClient>20 && rotationCos1 == 1){
			bogieX1-=pitchRads*0.9;
			pitch-=pitchRads*0.3;
		}
		if(anglePitchClient>20 && rotationSin1 == 1){
			bogieZ1-=pitchRads*0.9;
			pitch-=pitchRads*0.3;
		}
		getPassengers().get(0).setPosition(bogieX1, pitch, bogieZ1);
	}

	@Override
	public void setDead() {
		super.setDead();
		isDead = true;
	}

	@Override
	public void pressKey(int i) {
		if (i == 7 && getPassengers().get(0) != null && getPassengers().get(0) instanceof EntityPlayer) {
			((EntityPlayer) getPassengers().get(0)).openGui(Traincraft.instance, GuiIDs.LOCO, getWorld(), (int) this.posX, (int) this.posY, (int) this.posZ);
		}
	}

	@Override
	public String getInventoryName() {
		return "Adler";
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
		return 0.5F;
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