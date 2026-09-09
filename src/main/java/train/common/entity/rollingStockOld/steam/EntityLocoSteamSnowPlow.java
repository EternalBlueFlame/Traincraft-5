package train.common.entity.rollingStockOld.steam;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.api.LiquidManager;
import train.common.api.SteamTrain;
import train.common.core.FakePlayer;
import train.common.core.util.TraincraftUtil;
import train.common.library.GuiIDs;

import java.util.Random;

public class EntityLocoSteamSnowPlow extends SteamTrain {
	public EntityLocoSteamSnowPlow(World world) {
		super(world, LiquidManager.WATER_FILTER);
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

	private static final double[][]	blockpos	= { { 4, 0, 1 }, { 4, 0, -1 }, { 4, 0, 0 }};
	private double[] point1;
	private FakePlayer fakePlayer = null;
	private int rotation =0;

	private static final float radianF = CommonUtil.radianF;
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (worldObj.isRemote || bogieFront==null || bogieBack==null) {
			return;
		}
		checkInvent(cargoItems[0], cargoItems[1], this);
		if (fakePlayer == null){
			 fakePlayer = new FakePlayer(worldObj);
		}
		rotation = MathHelper.floor_float(TraincraftUtil.atan2degreesf(
				bogieFront.posZ - bogieBack.posZ,
				bogieFront.posX - bogieBack.posX));

		point1 = rotateVec3(blockpos[0], this.rotationPitch, rotation);
		point1[0] += posX;point1[1] += posY;point1[2] += posZ;
		mineSnow(worldObj, point1, cargoItems, fakePlayer);
		point1[1]++;
		mineSnow(worldObj, point1, cargoItems, fakePlayer);
		point1[1]++;
		mineSnow(worldObj, point1, cargoItems, fakePlayer);


		point1 = rotateVec3(blockpos[1], this.rotationPitch, rotation);
		point1[0] += posX;point1[1] += posY;point1[2] += posZ;
		mineSnow(worldObj, point1, cargoItems, fakePlayer);
		point1[1]++;
		mineSnow(worldObj, point1, cargoItems, fakePlayer);
		point1[1]++;
		mineSnow(worldObj, point1, cargoItems, fakePlayer);


		point1 = rotateVec3(blockpos[2], this.rotationPitch, rotation);
		point1[0] += posX;point1[1] += posY+1;point1[2] += posZ;
		mineSnow(worldObj, point1, cargoItems, fakePlayer);
		point1[1]++;
		mineSnow(worldObj, point1, cargoItems, fakePlayer);

	}

	private static void mineSnow(World worldObj, double[] point, ItemStack[] cargoItems, FakePlayer fakePlayer){
		Block b = CommonUtil.getBlockAt(worldObj, CommonUtil.floorDouble(point[0]),CommonUtil.floorDouble(point[1]),CommonUtil.floorDouble(point[2]));
		int blockMeta = CommonUtil.getBlockFacing(worldObj, CommonUtil.floorDouble(point[0]), CommonUtil.floorDouble(point[1]),
				CommonUtil.floorDouble(point[2]));

		if((b == Blocks.snow || b == Blocks.snow_layer) && b.canHarvestBlock(fakePlayer, blockMeta)){
			worldObj.setBlockToAir(CommonUtil.floorDouble(point[0]),CommonUtil.floorDouble(point[1]),CommonUtil.floorDouble(point[2]));
			int snowballs = new Random().nextInt(9);
			for(int i=2; i<cargoItems.length && snowballs>0; i++){
				if (cargoItems[i] == null){
					cargoItems[i] = new ItemStack(Items.snowball, snowballs);
					snowballs--;
				} else if (cargoItems[i].getItem() == Items.snowball && cargoItems[i].stackSize < Items.snowball.getItemStackLimit()){
					while (cargoItems[i].stackSize < cargoItems[i].getMaxStackSize() && snowballs >0){
						cargoItems[i].stackSize++;
						snowballs--;
					}
				}
				if (snowballs ==0){
					break;
				}
			}
			if (snowballs >0){
				EntityItem entityitem = new EntityItem(worldObj, point[0], point[1] + 1, point[2], new ItemStack(Items.snowball, snowballs));
				entityitem.delayBeforeCanPickup = 10;
				worldObj.spawnEntityInWorld(entityitem);

			}
		}
	}

	private static double[] rotateVec3(double[] offset, float pitch, float yaw) {
		double[] xyz = new double[]{offset[0],offset[1],offset[2]};
		//rotate pitch
		if (pitch != 0.0F) {
			pitch *= radianF;

			xyz[0] = (offset[0] * Math.cos(pitch));
			xyz[1] = (offset[0] * Math.sin(pitch));
		}
		//rotate yaw
		if (yaw != 0.0F) {
			yaw *= radianF;
			double cos = MathHelper.cos(yaw);
			double sin = MathHelper.sin(yaw);

			xyz[0] = (offset[0] * cos) - (offset[2] * sin);
			xyz[2] = (offset[0] * sin) + (offset[2] * cos);
		}
		return xyz;
	}

	@Override
	public String getInventoryName() {
		return "Steam Snow Plow";
	}



	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return (0.7F);
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
	public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    
}