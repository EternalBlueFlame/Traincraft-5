/**
 * A track that changes locomotive's speed 
 * 
 * @author Spitfire4466
 */
package train.blocks.tracks;

import ebf.tim.entities.EntityTrainCore;
import ebf.tim.utility.CommonUtil;
import mods.railcraft.api.core.items.IToolCrowbar;
import mods.railcraft.api.tracks.ITrackPowered;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import train.library.Tracks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BlockLocomotiveSpeedControllerTrack extends TrackBaseTraincraft implements ITrackPowered{
	private int mode = 0;
	private boolean powered;
	
	public BlockLocomotiveSpeedControllerTrack() {
		this.speedController = SpeedControllerSteel.getInstance();
	}

	@Override
	public Tracks getTrackType() {
		return Tracks.LOCO_SPEED_CONTROLLER;
	}
	@Override
	public boolean blockActivated(EntityPlayer player) {
		if (getWorld().isRemote) {
			return false;
		}
		ItemStack current = player.inventory.getCurrentItem();
		if ((current != null) && ((current.getItem() instanceof IToolCrowbar))) {
			IToolCrowbar crowbar = (IToolCrowbar) current.getItem();
			if (crowbar.canWhack(player, current, getX(), getY(), getZ())) {
				this.mode += 3;
				if (mode > 15)mode = 0;
				if (this.mode == 0)
					player.sendMessage(new TextComponentString("20 percent of max speed"));
				if (this.mode == 3)
					player.sendMessage(new TextComponentString("40 percent of max speed"));
				if (this.mode == 6)
					player.sendMessage(new TextComponentString("60 percent of max speed"));
				if (this.mode == 9)
					player.sendMessage(new TextComponentString("80 percent of max speed"));
				if (this.mode == 12)
					player.sendMessage(new TextComponentString("90 percent of max speed"));
				if (this.mode == 15)
					player.sendMessage(new TextComponentString("100 percent of max speed"));
				crowbar.onWhack(player, current, getX(), getY(), getZ());
				sendUpdateToClient();
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void onNeighborBlockChange(Block block) {
		if(this.powered){
			this.mode = getWorld().getBlockPowerInput(getX(), getY(), getZ());
			//System.out.println(input);
		}
		super.onNeighborBlockChange(block);
	}

	@Override
	public void onMinecartPass(EntityMinecart cart) {
		if (((cart instanceof EntityTrainCore))) {
			//todo: this is not a feature in the TiM API yet.
			/*
			if (this.mode == 0)
				((EntityTrainCore) cart).speedLimiter = 0.1;
			if (this.mode == 1)
				((EntityTrainCore) cart).speedLimiter = 0.15;
			if (this.mode == 2)
				((EntityTrainCore) cart).speedLimiter = 0.2;
			if (this.mode == 3)
				((EntityTrainCore) cart).speedLimiter = 0.25;
			if (this.mode == 4)
				((EntityTrainCore) cart).speedLimiter = 0.3;
			if (this.mode == 5)
				((EntityTrainCore) cart).speedLimiter = 0.35;
			if (this.mode == 6)
				((EntityTrainCore) cart).speedLimiter = 0.4;
			if (this.mode == 7)
				((EntityTrainCore) cart).speedLimiter = 0.45;
			if (this.mode == 8)
				((EntityTrainCore) cart).speedLimiter = 0.5;
			if (this.mode == 9)
				((EntityTrainCore) cart).speedLimiter = 0.6;
			if (this.mode == 10)
				((EntityTrainCore) cart).speedLimiter = 0.65;
			if (this.mode == 11)
				((EntityTrainCore) cart).speedLimiter = 0.7;
			if (this.mode == 12)
				((EntityTrainCore) cart).speedLimiter = 0.75;
			if (this.mode == 13)
				((EntityTrainCore) cart).speedLimiter = 0.85;
			if (this.mode == 14)
				((EntityTrainCore) cart).speedLimiter = 0.9;
			if (this.mode == 15)
				((EntityTrainCore) cart).speedLimiter = 1;
			
			((EntityTrainCore) cart).speedWasSet = true;
			*/
		}
	}

	@Override
	public IIcon getIcon() {
		int value = 0;
		if(mode>=0 && mode<3)value=0;
		if(mode>=3 && mode<6)value=1;
		if(mode>=6 && mode<8)value=2;
		if(mode>=8 && mode<13)value=3;
		if(mode>=13 && mode<=15)value=4;
		
		return getIcon(value);
	}

	protected void notifyNeighbors() {
		Block block = CommonUtil.getBlockAt(getWorld(), getX(), getY(), getZ());
		getWorld().notifyBlocksOfNeighborChange(getX(), getY(), getZ(), block);
		getWorld().notifyBlocksOfNeighborChange(getX(), getY() - 1, getZ(), block);

		markBlockNeedsUpdate();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("mode", this.mode);
	}
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		this.mode = nbttagcompound.getInteger("mode");
	}
	@Override
	public void writePacketData(DataOutputStream data) throws IOException {
		super.writePacketData(data);

		data.writeInt(this.mode);
	}
	@Override
	public void readPacketData(DataInputStream data) throws IOException {
		super.readPacketData(data);

		this.mode = data.readInt();

		markBlockNeedsUpdate();
	}

	@Override
	public boolean isPowered() {
		return powered;
	}

	@Override
	public void setPowered(boolean powered) {
		this.powered = powered;
	}

}
