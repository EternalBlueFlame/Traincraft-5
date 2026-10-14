package train.common.tile;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.ITickable;


import cofh.api.energy.IEnergyProvider;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import train.common.core.handlers.ConfigHandler;
import train.common.core.handlers.WorldEvents;
import train.common.core.util.Energy;
import train.common.library.BlockIDs;

import java.util.Random;

public class TileWindMill extends Energy implements ITickable, IEnergyProvider {
	private int updateTicks = 0;
	private static Random rand = new Random();
	public int windClient = 0;
    public int standsOpen = 0;


	public TileWindMill() {
		super(0, 240, 80);
		super.setSides(new EnumFacing[]{EnumFacing.EAST, EnumFacing.WEST, EnumFacing.SOUTH, EnumFacing.NORTH, EnumFacing.DOWN});
	}

	@Override
	public String getName(){return "Wind Mill";}


	@Override
	public void readFromNBT(NBTTagCompound nbt, boolean synced) {
		super.readFromNBT(nbt, synced);
		this.windClient = nbt.getInteger("Wind");
        this.standsOpen = nbt.getInteger("standsOpen");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt, boolean synced) {
		super.writeToNBT(nbt, synced);
		nbt.setInteger("Wind", this.windClient);
        nbt.setInteger("standsOpen", this.standsOpen);
		return nbt;
	}

	@Override
	public void update() {
		super.update();
		updateTicks++;
		/**
		 * Remove any block on top of the wind mill
		 */
		if (!world.isRemote) {
			if (updateTicks % 20 == 0) {
				if (!this.world.isAirBlock(new BlockPos(this.getPos().getX(), this.getPos().getY() + 1, this.getPos().getZ()))) {
					Block block = CommonUtil.getBlockAt(this.world, this.getPos().getX(), this.getPos().getY() + 1, this.getPos().getZ());
					if (block != null) {
						EntityItem entityitem = new EntityItem(getWorld(), this.getPos().getX(), this.getPos().getY() + 1, this.getPos().getZ(), new ItemStack(Item.getItemFromBlock(BlockIDs.windMill.block),1));
						float f3 = 0.05F;
						entityitem.motionX = (float) rand.nextGaussian() * f3;
						entityitem.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float) rand.nextGaussian() * f3;
						CommonUtil.spawnEntity(world, entityitem);
					}
					this.world.setBlockToAir(getPos());
				}
			}

			/**
			 * Check every 6 seconds if a selectable area around the windmill top-block can see the sky
			 */
            if(this.updateTicks % 120 == 0) {
                this.standsOpen = 0;
                int st = ConfigHandler.WINDMILL_CHECK_RADIUS;
                if(st >= 0) {
                   int en = st+1;
                   louter:
                   for(int x=-st;x<en;x++)
                     for(int z=-st;z<en;z++)
                       if(!this.world.canSeeSky(new net.minecraft.util.math.BlockPos(this.getPos().getX() + x, this.getPos().getY() + 1, this.getPos().getZ() + z)))
                       {
                           this.standsOpen++;
                           break louter;
                       }
                }
            } 

			/**
			 * Calculate production using wind strength
			 */
			if (this.standsOpen == 0 && updateTicks % 4 == 0) {
				this.energy.receiveEnergy((WorldEvents.windStrength + (Math.round(this.getPos().getY() *0.25f)) * 10), false);
				if (this.world.isThundering()) {
					this.energy.receiveEnergy(Math.round(this.energy.getEnergyStored() * 3.5f), false);
				} else if (this.world.isRaining()) {
					this.energy.receiveEnergy(Math.round(this.energy.getEnergyStored() * 2.2f), false);
				}
			}
			if (this.energy.getEnergyStored() > 0) {
				pushEnergy(getWorld(), getPos(), this.energy);
			}

			this.markDirty();
			this.syncTileEntity();
		}
	}
}
