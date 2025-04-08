package train.common.api;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.entities.EntitySeat;
import ebf.tim.utility.CommonUtil;
import ebf.tim.utility.DebugUtil;
import fexcraft.tmt.slim.Vec3f;
import mods.railcraft.api.carts.IMinecart;
import mods.railcraft.api.carts.IRoutableCart;
import mods.railcraft.api.tracks.ITrackSwitch;
import mods.railcraft.api.tracks.ITrackTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import train.common.blocks.BlockTCRail;
import train.common.blocks.BlockTCRailGag;
import train.common.items.TCRailTypes;
import train.common.library.BlockIDs;
import train.common.tile.TileTCRail;
import train.common.tile.TileTCRailGag;

import java.util.List;

import static train.common.core.util.TraincraftUtil.isRailBlockAt;

public class EntityBogie extends EntityMinecart implements IMinecart, IRoutableCart {

	public boolean isOnRail;
	public float prevDpdx;
	public float prevDpdz;
	public World worldObj;
	public int meta;
	public EntityRollingStock entityMainTrain;
	public int entityMainTrainID;
	public TileTCRail lastTrack=null;

	double[] velocity = new double[]{0,0,0,0,0,0};



	private int turnProgress;
    private double minecartX;
    private double minecartY;
    private double minecartZ;
    private double minecartYaw;
    private double minecartPitch;




	public EntityBogie(World world) {

		super(world);

		this.isOnRail = false;
		this.prevDpdx = 0F;
		this.prevDpdz = 0F;
		this.worldObj = world;

		setSize(0.5f, 1.25f);

		//this.boundingBox.offset(0, 0.5, 0);
		setCollisionHandler(null);
		this.yOffset = 0.65f;
		//this.setSize(0.1F, 1.98F);
		isImmuneToFire = true;
		noClip=true;
	}

	public EntityBogie(World world, double d, double d1, double d2, EntityRollingStock mainTrain, int id) {

		this(world);

		this.entityMainTrain = mainTrain;
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = d;
		this.prevPosY = d1;
		this.prevPosZ = d2;
		this.entityMainTrainID = id;
		this.setPosition(d, d1 + this.yOffset, d2);
		isImmuneToFire = true;
		setSize(0.5f, 1.25f);
		noClip=true;
	}

	@Override
	public boolean canBePushed() {

		return false;
	}

	/**
	 * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be pushable on contact, like boats or minecarts.
	 */
	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {

		return null;
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float f) {
		return (this.entityMainTrain != null && entityMainTrain.attackEntityFrom(damageSource, f));
	}

	@Override
	public void applyEntityCollision(Entity entity) {

		if (this.entityMainTrain != null && entity != this.entityMainTrain && !(entity instanceof EntitySeat) ) {

			this.entityMainTrain.applyEntityCollision(entity);

		}
	}

	private boolean isDerail = false;
	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {

		return this.height / 2.0F;
	}

	@Override
	public boolean interactFirst(EntityPlayer entityplayer) {

		if (this.entityMainTrain != null) {

			this.entityMainTrain.interactFirst(entityplayer);
		}

		return true;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {

		this.entityMainTrainID = nbttagcompound.getInteger("trainID");

		super.readEntityFromNBT(nbttagcompound);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {

		nbttagcompound.setInteger("trainID", entityMainTrainID);

		super.writeEntityToNBT(nbttagcompound);
	}

	@Override
	public int getMinecartType() {

		return -1;
	}

	@Override
	public String getDestination() {

		if (this.entityMainTrain != null) {

			return this.entityMainTrain.getDestination();
		}

		return null;
	}

	@Override
	public boolean setDestination(ItemStack ticket) {

		return (this.entityMainTrain != null && this.entityMainTrain.setDestination(ticket));
	}

	@Override
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {

		return false;
	}

	/**
	 * Return false if this cart should not call onMinecartPass() and should ignore Powered Rails.
	 *
	 * @return True if this cart should call onMinecartPass().
	 */
	@Override
	public boolean shouldDoRailFunctions() {

		return true;
	}

	@Override
	public double getSlopeAdjustment() {

		return 0;
	}

	/**
	 * Returns the carts max speed when traveling on rails. Carts going faster than 1.1 cause issues
	 * with chunk loading. This value is compared with the rails max speed and the carts current
	 * speed cap to determine the carts current max speed. A normal rail's max speed is 0.4.
	 *
	 * @return Carts max speed.
	 */
	@Override
	public float getMaxCartSpeedOnRail() {

		return 1.8f;
	}

	@Override
	protected void func_145821_a(int x, int y, int z, double maxSpeed, double slopeAdjustment, Block block, int railMeta) {
		//super.func_145821_a(x, y, z, maxSpeed, slopeAdjustment, block, railMeta);
		super.func_145821_a(x, y, z, this.getMaxCartSpeedOnRail(), slopeAdjustment, block, railMeta);
	}
	private static final double[][][] matrix = new double[][][] {
			//straight
			{{0, -0.5}, {0, 0.5}, {0, -1}},
			{{ -0.5, 0}, {0.5, 0}, {-1, 0}},
			//slope
			{{ -0.5, 0}, {0.5, 0}, {-1, 0}},
			{{ -0.5, 0}, {0.5, 0}, {-1, 0}},
			{{0, -0.5}, {0, 0.5}, {0, -1}},
			{{0, -0.5}, {0, 0.5}, {0, -1}},
			//turns
			{{0, 0.5}, {0.5, 0}, {-1, 1}},
			{{0, 0.5}, { -0.5, 0}, {1, 1}},
			{{0, -0.5}, { -0.5, 0}, {1, -1}},
			{{0, -0.5}, {0.5, 0}, {-1, -1}}
	};
	boolean flag,flag1;
	private void updateOnTrack(int i, int j, int k, Block l) {

		if (canUseRail() && BlockRailBase.func_150051_a(l)) {
			super.onUpdate();
			lastTrack=null;
			int i1 = ((BlockRailBase) l).getBasicRailMetadata(worldObj, this, i, j, k);
			meta = i1;
			posY = j;
			flag = false;
			flag1 = l == Blocks.golden_rail;
			if (l == Blocks.golden_rail) {
				flag = worldObj.getBlockMetadata(i, j, k) > 2;
				if (i1 == 8) {
					i1 = 0;
				} else if (i1 == 9) {
					i1 = 1;
				}
			}

			if (l == Blocks.detector_rail) {
				worldObj.setBlockMetadataWithNotify(i, j, k, meta | 8, 3);
				worldObj.notifyBlocksOfNeighborChange(i, j, k, l);
				worldObj.notifyBlocksOfNeighborChange(i, j - 1, k, l);
				worldObj.markBlockRangeForRenderUpdate(i, j, k, i, j, k);
				worldObj.scheduleBlockUpdate(i, j, k, l, l.tickRate(worldObj));
			}

			if (i1 >= 2 && i1 <= 5) {
				posY = (j + 1);
			}


			int ai[][] = EntityRollingStock.matrix[i1];
			double d9 = ai[1][0] - ai[0][0];
			double d10 = ai[1][2] - ai[0][2];
			double d11 = Math.sqrt(d9 * d9 + d10 * d10);
			if (motionX * d9 + motionZ * d10 < 0.0D) {
				d9 = -d9;
				d10 = -d10;
			}
			double d13 = Math.sqrt(motionX * motionX + motionZ * motionZ);
			motionX = (d13 * d9) / d11;
			motionZ = (d13 * d10) / d11;
			if (flag1 && !flag) {
				if (Math.sqrt(motionX * motionX + motionZ * motionZ) < 0.029999999999999999D) {
					motionX = 0.0D;
					motionY = 0.0D;
					motionZ = 0.0D;
				} else {
					motionX *= 0.5D;
					motionY *= 0.0D;
					motionZ *= 0.5D;
				}
			}
			double d17 = 0.0D;
			double d18 = i + 0.5D + ai[0][0] * 0.5D;
			double d19 = k + 0.5D + ai[0][2] * 0.5D;
			double d20 = i + 0.5D + ai[1][0] * 0.5D;
			double d21 = k + 0.5D + ai[1][2] * 0.5D;
			d9 = d20 - d18;
			d10 = d21 - d19;
			if (d9 == 0.0D) {
				posX = i + 0.5D;
				d17 = posZ - k;
			}
			else if (d10 == 0.0D) {
				posZ = k + 0.5D;
				d17 = posX - i;
			}
			else {
				double d22 = posX - d18;
				double d24 = posZ - d19;
				d17 = (d22 * d9 + d24 * d10) * 2D;
				//double derailSpeed = 0;//0.46;
			}
			posX = d18 + d9 * d17;
			posZ = d19 + d10 * d17;
			setPosition(posX, posY + yOffset + 0.35, posZ);

			moveMinecart(i, j, k);

			if (ai[0][1] != 0 && MathHelper.floor_double(posX) - i == ai[0][0] &&
					MathHelper.floor_double(posZ) - k == ai[0][2]) {
				setPosition(posX, posY + ai[0][1], posZ);
			}
			else if (ai[1][1] != 0 && MathHelper.floor_double(posX) - i == ai[1][0] &&
					MathHelper.floor_double(posZ) - k == ai[1][2]) {
				setPosition(posX, posY + ai[1][1], posZ);
			}

			double d14 = Math.sqrt(motionX * motionX + motionZ * motionZ);
			if (d14 > 0.0D) {
				motionX = (motionX / d14) * (d14);
				motionZ = (motionZ / d14) * (d14);
			}
			setPosition(posX, posY + yOffset - 0.8d, posZ);
			int k1 = MathHelper.floor_double(posX);
			int l1 = MathHelper.floor_double(posZ);
			if (k1 != i || l1 != k) {
				double d15 = Math.sqrt(motionX * motionX + motionZ * motionZ);
				motionX = d15 * (k1 - i);
				motionZ = d15 * (l1 - k);
			}

			((BlockRailBase) l).onMinecartPass(worldObj, this, i, j, k);

			if (flag) {
				double d31 = Math.sqrt(motionX * motionX + motionZ * motionZ);
				if (d31 > 0.01D) {
					motionX += (motionX / d31) * 0.059999999999999998D;
					motionZ += (motionZ / d31) * 0.059999999999999998D;
				} else if (i1 == 1) {
					if (worldObj.isBlockNormalCubeDefault(i - 1, j, k, false)) {
						motionX = 0.02D;
					} else if (worldObj.isBlockNormalCubeDefault(i + 1, j, k, false)) {
						motionX = -0.02D;
					}
				} else if (i1 == 0) {
					if (worldObj.isBlockNormalCubeDefault(i, j, k - 1, false)) {
						motionZ = 0.02D;
					} else if (worldObj.isBlockNormalCubeDefault(i, j, k + 1, false)) {
						motionZ = -0.02D;
					}
				}
			}

		}
		else if (l == BlockIDs.tcRail.block) {
			limitSpeedOnTCRail();

			lastTrack = (TileTCRail) worldObj.getTileEntity(i, j, k);

			if (TCRailTypes.isStraightTrack(lastTrack) || (TCRailTypes.isSwitchTrack(lastTrack) && !lastTrack.getSwitchState())) {
				moveOnTCStraight(j, lastTrack.xCoord, lastTrack.zCoord, lastTrack.getBlockMetadata());
			}
			else if (TCRailTypes.isTurnTrack(lastTrack) || (TCRailTypes.isSwitchTrack(lastTrack) && lastTrack.getSwitchState())) {
				if (shouldIgnoreSwitch(lastTrack, i, j, k, lastTrack.getBlockMetadata())) {
					moveOnTCStraight(j, lastTrack.xCoord, lastTrack.zCoord, lastTrack.getBlockMetadata());
				}
				else {
					if (TCRailTypes.isTurnTrack(lastTrack) || (TCRailTypes.isSwitchTrack(lastTrack) && lastTrack.getSwitchState())) {
						moveOnTC90TurnRail(j, lastTrack.r, lastTrack.cx, lastTrack.cz);
					}
				}
			} else if (TCRailTypes.isCrossingTrack(lastTrack)) {
				moveOnTCTwoWaysCrossing();
			} else if (TCRailTypes.isSlopeTrack(lastTrack)) {
				moveOnTCSlope(j, lastTrack.xCoord, lastTrack.zCoord, lastTrack.slopeAngle, lastTrack.slopeHeight, lastTrack.getBlockMetadata());
			} else if (TCRailTypes.isDiagonalCrossingTrack(lastTrack)) {
				moveOnTCDiamondCrossing(i, j, k, lastTrack.xCoord,  lastTrack.zCoord );
			} else if (TCRailTypes.isDiagonalCrossingTrack(lastTrack)) {
				moveOnTCDiagonal(i, j, k, lastTrack.xCoord, lastTrack.zCoord, lastTrack.getBlockMetadata(), lastTrack.getRailLength());
			} else if (TCRailTypes.isCurvedSlopeTrack(lastTrack)) {
				moveOnTCCurvedSlope(i, j, k, lastTrack.r, lastTrack.cx, lastTrack.cz, lastTrack.xCoord, lastTrack.zCoord, lastTrack.getBlockMetadata(), 1, lastTrack.slopeAngle);
			} else if (TCRailTypes.isDiagonalTrack(lastTrack)) {
				moveOnTCDiagonal(i, j, k, lastTrack.xCoord, lastTrack.zCoord, lastTrack.getBlockMetadata(), lastTrack.getRailLength());
			}

		}
		else if (l == BlockIDs.tcRailGag.block) {
			limitSpeedOnTCRail();

			if(lastTrack==null || !CommonUtil.getTiles(worldObj,i,j,k).contains(lastTrack)){
				TileTCRailGag tileGag = (TileTCRailGag) worldObj.getTileEntity(i, j, k);
				if(tileGag.originX.size()>0 && worldObj.getTileEntity(tileGag.originX.get(0), tileGag.originY.get(0), tileGag.originZ.get(0)) != null) {
					lastTrack = (TileTCRail) worldObj.getTileEntity(tileGag.originX.get(0), tileGag.originY.get(0), tileGag.originZ.get(0));
				}
			}
			if (TCRailTypes.isStraightTrack(lastTrack) || (TCRailTypes.isSwitchTrack(lastTrack) && !lastTrack.getSwitchState())) {
				moveOnTCStraight(j, lastTrack.xCoord, lastTrack.zCoord, lastTrack.getBlockMetadata());
			}
			else if (TCRailTypes.isTurnTrack(lastTrack) || (TCRailTypes.isSwitchTrack(lastTrack) && lastTrack.getSwitchState())) {
				if (shouldIgnoreSwitch(lastTrack, i, j, k, lastTrack.getBlockMetadata())) {
					moveOnTCStraight(j, lastTrack.xCoord, lastTrack.zCoord, lastTrack.getBlockMetadata());
				}
				else {
					if (TCRailTypes.isTurnTrack(lastTrack) || (TCRailTypes.isSwitchTrack(lastTrack) && lastTrack.getSwitchState())) {
						moveOnTC90TurnRail(j, lastTrack.r, lastTrack.cx, lastTrack.cz);
					}
				}
			} else if (TCRailTypes.isCrossingTrack(lastTrack)) {
				moveOnTCTwoWaysCrossing();
			} else if (TCRailTypes.isSlopeTrack(lastTrack)) {
				moveOnTCSlope(j, lastTrack.xCoord, lastTrack.zCoord, lastTrack.slopeAngle, lastTrack.slopeHeight, lastTrack.getBlockMetadata());
			} else if (TCRailTypes.isDiagonalCrossingTrack(lastTrack)) {
				moveOnTCDiamondCrossing(i, j, k, lastTrack.xCoord,  lastTrack.zCoord );
			} else if (TCRailTypes.isDiagonalCrossingTrack(lastTrack)) {
				moveOnTCDiagonal(i, j, k, lastTrack.xCoord, lastTrack.zCoord, lastTrack.getBlockMetadata(), lastTrack.getRailLength());
			} else if (TCRailTypes.isCurvedSlopeTrack(lastTrack)) {
				moveOnTCCurvedSlope(i, j, k, lastTrack.r, lastTrack.cx, lastTrack.cz, lastTrack.xCoord, lastTrack.zCoord, lastTrack.getBlockMetadata(), 1, lastTrack.slopeAngle);
			} else if (TCRailTypes.isDiagonalTrack(lastTrack)) {
				moveOnTCDiagonal(i, j, k, lastTrack.xCoord, lastTrack.zCoord, lastTrack.getBlockMetadata(), lastTrack.getRailLength());
			}
		}
		else {
			lastTrack=null;
			super.onUpdate();
		}
	}


	public void moveMinecart(int i, int j, int k) {
		Block id = worldObj.getBlock(i, j, k);
		if (!BlockRailBase.func_150051_a(id)) { return; }
		float railMaxSpeed = ((BlockRailBase) id).getRailMaxSpeed(worldObj, this, i, j, k);
		float maxSpeed = Math.max(railMaxSpeed, getMaxCartSpeedOnRail());
		maxSpeed = SpeedHandler.handleSpeed(railMaxSpeed, maxSpeed, this);
		if (entityMainTrain!=null && entityMainTrain.speedLimiter != 0) {
			float targetSpeed = (float) (maxSpeed * entityMainTrain.speedLimiter);
			float targetSpeedX = (float) Math.copySign(targetSpeed, motionX);
			float targetSpeedZ = (float) Math.copySign(targetSpeed, motionZ);
			if (motionX > targetSpeedX && motionX != 0) motionX -= 0.01;
			if (motionZ > targetSpeedZ && motionZ != 0) motionZ -= 0.01;
			if (motionX < targetSpeedX && motionX != 0) motionX += 0.01;
			if (motionZ < targetSpeedZ && motionZ != 0) motionZ += 0.01;
		}
		if (motionX < -maxSpeed) {
			motionX = -maxSpeed;
		}
		if (motionX > maxSpeed) {
			motionX = maxSpeed;
		}
		if (motionZ < -maxSpeed) {
			motionZ = -maxSpeed;
		}
		if (motionZ > maxSpeed) {
			motionZ = maxSpeed;
		}
		moveEntity(motionX, 0.0D, motionZ);
	}


	/**
	 * Called to update the entity's position/logic.
	 */
	Block l;
	@Override
	public void onUpdate(){
		if(ticksExisted%100==1){
			if(entityMainTrain==null){
				setDead();
			}
		}
	}

	public void moveBogie(){

		this.setCurrentCartSpeedCapOnRail(1.8F);
		this.setMaxSpeedAirLateral(1.8F);
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.worldObj.isRemote) {
			if (this.turnProgress > 0) {
				double d6 = this.posX + (this.minecartX - this.posX) / this.turnProgress;
				double d7 = this.posY + (this.minecartY - this.posY) / this.turnProgress;
				double d1 = this.posZ + (this.minecartZ - this.posZ) / this.turnProgress;
				double d3 = MathHelper.wrapAngleTo180_double(this.minecartYaw - this.rotationYaw);
				this.rotationYaw = (float)(this.rotationYaw + d3 / this.turnProgress);
				this.rotationPitch = (float)(this.rotationPitch + (this.minecartPitch - this.rotationPitch) / this.turnProgress);
				--this.turnProgress;
				this.setPosition(d6, d7, d1);
				this.setRotation(this.rotationYaw, this.rotationPitch);
			} else {
				this.setPosition(this.posX, this.posY, this.posZ);
				this.setRotation(this.rotationYaw, this.rotationPitch);
			}

		}
		else {
			if(ticksExisted>60 && ticksExisted%120==0 && entityMainTrain == null){
				worldObj.removeEntity(this);
			}
			int i = MathHelper.floor_double(this.posX);
			int j = MathHelper.floor_double(this.posY);
			int k = MathHelper.floor_double(this.posZ);
			if (worldObj.isAirBlock(i,j,k)) {
				j--;
			}
			else if (isRailBlockAt(worldObj, i, j + 1, k) || worldObj.getBlock(i, j + 1, k) instanceof BlockTCRail || worldObj.getBlock(i, j + 1, k) instanceof BlockTCRailGag) {
				j++;
			}

			l = worldObj.getBlock(i, j, k);
			updateOnTrack(i, j, k, l);

		}


		this.func_145775_I();
		this.rotationPitch = 0.0F;

		if (!this.worldObj.isRemote) {

			if(this.entityMainTrain == null) {

				this.setDead();
				worldObj.removeEntity(this);
			}

			AxisAlignedBB axisAlignedBB;

			if (getCollisionHandler() != null) {

				axisAlignedBB = getCollisionHandler().getMinecartCollisionBox(this);
			}
			else {

				axisAlignedBB = this.boundingBox.expand(0.2D, 0.0D, 0.2D);
			}

			@SuppressWarnings("rawtypes")
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, axisAlignedBB);

			if (list != null && !list.isEmpty()) {

				Entity entity;

				for (Object o : list) {

					entity = (Entity) o;

					if (entity != this.riddenByEntity) {

						this.applyEntityCollision(entity);
					}
				}
			}
		}
		if (posX == 0 && posZ == 0) {
			worldObj.removeEntity(this);
		}
	}

	protected void moveOnTCDiamondCrossing(int i, int j, int k, double cx, double cz) {



		double norm = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

		if (Math.abs(motionZ) > Math.abs(motionX * 2)) {
			this.moveEntity(0.0D, 0.0D, Math.copySign(norm, this.motionZ));
		}
		else if (Math.abs(motionZ * 2) < Math.abs(motionX)) {
			this.moveEntity(Math.copySign(norm, this.motionX), 0.0D, 0.0D);
		}
		else {
			this.moveEntity(Math.copySign(norm, this.motionX), 0.0D, Math.copySign(norm, this.motionZ));
		}
	}

	private void moveOnTCDiagonal(int i, int j, int k, double cx, double cz, int meta, double length) {

		double Y_OFFSET = 0.2;
		double X_OFFSET = 0.5;
		double Z_OFFSET = 1.5;
		posY = j + Y_OFFSET;

		double exitX = 0;
		double exitZ = 0;
		double directionX;
		double directionZ;
		double norm = Math.sqrt(motionX * motionX + motionZ * motionZ);
		double distanceNorm;

		if (meta == 6) {
			exitX = (motionX > 0) ? cx + length + X_OFFSET : cx - X_OFFSET;
			exitZ = (motionX > 0) ? cz - length + X_OFFSET : cz + Z_OFFSET;
		} else if (meta == 4) {
			exitX = (motionX > 0) ? cx + Z_OFFSET : cx - (length - X_OFFSET);
			exitZ = (motionX > 0) ? cz - X_OFFSET : cz + (length + X_OFFSET);
		} else if (meta == 5) {
			exitX = (motionX > 0) ? cx + Z_OFFSET : cx - (length + X_OFFSET);
			exitZ = (motionX > 0) ? cz + Z_OFFSET : cz - (length + X_OFFSET);
		} else if (meta == 7) {
			exitX = (motionX > 0) ? cx + (length + X_OFFSET) : cx - X_OFFSET;
			exitZ = (motionX > 0) ? cz + (length + X_OFFSET) : cz - X_OFFSET;
		}

		directionX = exitX - posX;
		directionZ = exitZ - posZ;
		distanceNorm = Math.sqrt(directionX * directionX + directionZ * directionZ);
		motionX = (directionX / distanceNorm) * norm;
		motionZ = (directionZ / distanceNorm) * norm;
		this.boundingBox.offset(Math.copySign(motionX, this.motionX), 0 , Math.copySign(motionZ, this.motionZ));

		this.posX = (this.boundingBox.minX + this.boundingBox.maxX) / 2.0D;
		this.posY = this.boundingBox.minY + (double)this.yOffset - (double)this.ySize;
		this.posZ = (this.boundingBox.minZ + this.boundingBox.maxZ) / 2.0D;
	}

	private void moveOnTCStraight(int j, double cx, double cz, int meta) {
		posY = j + 0.2;
		if (meta == 2 || meta == 0) {
			double norm = Math.sqrt(motionX * motionX + motionZ * motionZ);

			setPosition(cx + 0.5, posY + yOffset, posZ);
			//setPosition(posX, posY + yOffset, posZ);
			motionX = 0;
			motionZ = Math.copySign(norm, motionZ);
			this.boundingBox.offset(0, 0 , Math.copySign(norm, this.motionZ));

			this.posX = (this.boundingBox.minX + this.boundingBox.maxX) / 2.0D;
			this.posY = this.boundingBox.minY + (double)this.yOffset - (double)this.ySize;
			this.posZ = (this.boundingBox.minZ + this.boundingBox.maxZ) / 2.0D;


		}
		if (meta == 1 || meta == 3) {

			setPosition(posX, posY + yOffset, cz + 0.5);
			//setPosition(posX, posY + yOffset, posZ);

			motionX = Math.copySign(Math.sqrt(motionX * motionX + motionZ * motionZ), motionX);
			motionZ = 0;
			this.boundingBox.offset(motionX, 0 , 0);

			this.posX = (this.boundingBox.minX + this.boundingBox.maxX) / 2.0D;
			this.posY = this.boundingBox.minY + (double)this.yOffset - (double)this.ySize;
			this.posZ = (this.boundingBox.minZ + this.boundingBox.maxZ) / 2.0D;


		}
	}

	private void moveOnTCCurvedSlope(int i, int j, int k,double r, double cx, double cz, int tilex, int tilez, int meta, double slopeHeight, double slopeAngle) {
		double newTilex = tilex;
		double newTilez = tilez;
		if (meta == 2 ) {
			newTilez += 1;
			newTilex += 0.5;
		}
		if (meta == 0) {
			newTilex += 0.5;
		}
		if (meta == 1 ) {
			newTilex += 1;
			newTilez += 0.5;
		}
		if (meta == 3) {
			newTilez += 0.5;
		}
		double cpx = posX - cx;
		double cpz = posZ - cz;
		double tpx = newTilex - posX;
		double tpz = newTilez - posZ;

		double tpnorm = Math.sqrt(tpx * tpx + tpz * tpz);

		double cp_norm = Math.sqrt(cpx * cpx + cpz * cpz);
		double vnorm = Math.sqrt(motionX * motionX + motionZ * motionZ);

		double norm_cpx = cpx / cp_norm; //u
		double norm_cpz = cpz / cp_norm; //v

		double vx2 = -norm_cpz * vnorm;//-v
		double vz2 = norm_cpx * vnorm;//u

		double px2 = posX + motionX;
		double pz2 = posZ + motionZ;

		double px2_cx = px2 - cx;
		double pz2_cz = pz2 - cz;

		double p2_c_norm = Math.sqrt((px2_cx * px2_cx) + (pz2_cz * pz2_cz));

		double px2_cx_norm = px2_cx / p2_c_norm;
		double pz2_cz_norm = pz2_cz / p2_c_norm;

		double px3 = cx + (px2_cx_norm * r);
		double pz3 = cz + (pz2_cz_norm * r);

		double signX = px3 - posX;
		double signZ = pz3 - posZ;

		vx2 = Math.copySign(vx2, signX);
		vz2 = Math.copySign(vz2, signZ);

		double p_corr_x = cx + ((cpx / cp_norm) * r);
		double p_corr_z = cz + ((cpz / cp_norm) * r);
		motionX = vx2;
		motionZ = vz2;

		double newYPos = Math.abs(j+ Math.min(1, (slopeAngle * Math.abs(tpnorm))) + yOffset + 0.34f);
		setPosition(p_corr_x, newYPos, p_corr_z);
		moveEntity(vx2,  0, vz2);

	}

	private void moveOnTCTwoWaysCrossing() {
		/*
		 * Nitro-Note: Do we need all those shitty motionX and Z? We don't even
		 * need something to parse to this function. setPosition is superflous since you can't place
		 * trains down on 2 way crossings.
		 */


		double norm = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

		if (Math.abs(motionZ) > Math.abs(motionX)) {

			// this.setPosition(this.posX, this.posY + this.yOffset, cz + 0.5D);
			this.moveEntity(0.0D, 0.0D, Math.copySign(norm, this.motionZ));

			// this.motionX = 0.0D;
			// this.motionZ = Math.copySign(norm, this.motionZ);
		}
		else {

			// double norm = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

			// this.setPosition(cx + 0.5D, this.posY + this.yOffset, this.posZ);
			this.moveEntity(Math.copySign(norm, this.motionX), 0.0D, 0.0D);

			// this.motionX = Math.copySign(norm, this.motionX);
			// this.motionZ = 0.0D;
		}

	}
	private void moveOnTCSlope(int j, double cx, double cz, double slopeAngle, double slopeHeight, int meta) {

		// posY = j + 2.5;
		if (meta == 2 || meta == 0) {

			if (meta == 2) {
				cz += 1;
			}

			double norm = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			double newPosY = Math.abs(j + (Math.tan(slopeAngle * Math.abs(cz - this.posZ))) + this.yOffset + 0.3);
			if (this.isDerail) {
				for (int i = -2; i< 3;i++) {
					if (worldObj.getBlock((int) Math.round(cx), (int) Math.round(newPosY) + i, (int) Math.round(this.posZ)) instanceof BlockTCRail ||
							worldObj.getBlock((int) Math.round(cx), (int) Math.round(newPosY) + i, (int) Math.round(this.posZ)) instanceof BlockTCRailGag) {
						if (Math.round(this.entityMainTrain.posY) == Math.round(this.posY)) {
							newPosY += i + 1;
							break;
						}
					}
				}
			}
			this.setPosition(cx + 0.5D, newPosY, this.posZ);

			this.boundingBox.offset(0, 0 , Math.copySign(norm, this.motionZ));
			this.posX = (this.boundingBox.minX + this.boundingBox.maxX) / 2.0D;
			this.posY = this.boundingBox.minY + (double)this.yOffset - (double)this.ySize;
			this.posZ = (this.boundingBox.minZ + this.boundingBox.maxZ) / 2.0D;

			this.motionX = 0.0D;
			this.motionY = 0.0D;
			this.motionZ = Math.copySign(norm, this.motionZ);
		} else if (meta == 1 || meta == 3) {
			if (meta == 1) {
				cx += 1;
			}

			double norm = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
			double newPosY = (j + (Math.tan(slopeAngle * Math.abs(cx - this.posX))) + this.yOffset + 0.3);
			if (this.isDerail) {
				for (int i = -2; i< 3;i++) {
					if (worldObj.getBlock((int) Math.round(this.posX), (int) Math.round(newPosY) + i, (int) Math.round(cz)) instanceof BlockTCRail ||
							worldObj.getBlock((int) Math.round(this.posX), (int) Math.round(newPosY) + i, (int) Math.round(cz)) instanceof BlockTCRailGag) {
						if (Math.round(this.entityMainTrain.posY) == Math.round(this.posY)) {
							newPosY += i + 1;
							break;
						}
					}
				}
			}
			this.setPosition(this.posX, newPosY, cz + 0.5D);

			this.boundingBox.offset(Math.copySign(norm, this.motionX), 0 ,0);
			this.posX = (this.boundingBox.minX + this.boundingBox.maxX) / 2.0D;
			this.posY = this.boundingBox.minY + (double)this.yOffset - (double)this.ySize;
			this.posZ = (this.boundingBox.minZ + this.boundingBox.maxZ) / 2.0D;

			this.motionX = Math.copySign(norm, this.motionX);
			this.motionY = 0.0D;
			this.motionZ = 0.0D;
		}
	}

	private void moveOnTC90TurnRail(int j,double r, double cx, double cz){

		posY = j + 0.2;
		double cpx = posX - cx;
		double cpz = posZ - cz;
		double cp_norm = Math.sqrt(cpx * cpx + cpz * cpz);

		double vnorm = Math.sqrt(motionX * motionX + motionZ * motionZ);

		double norm_cpx = cpx / cp_norm; //u
		double norm_cpz = cpz / cp_norm; //v

		double vx2 = -norm_cpz * vnorm;//-v
		double vz2 = norm_cpx * vnorm;//u

		double px2 = posX + motionX;
		double pz2 = posZ + motionZ;

		double px2_cx = px2 - cx;
		double pz2_cz = pz2 - cz;

		double p2_c_norm = Math.sqrt((px2_cx * px2_cx) + (pz2_cz * pz2_cz));

		double px2_cx_norm = px2_cx / p2_c_norm;
		double pz2_cz_norm = pz2_cz / p2_c_norm;

		double px3 = cx + (px2_cx_norm * r);
		double pz3 = cz + (pz2_cz_norm * r);

		double signX = px3 - posX;
		double signZ = pz3 - posZ;

		vx2 = Math.copySign(vx2, signX);
		vz2 = Math.copySign(vz2, signZ);

		double p_corr_x = cx + ((cpx / cp_norm) * r);
		double p_corr_z = cz + ((cpz / cp_norm) * r);


		setPosition(p_corr_x, posY + yOffset, p_corr_z);
		moveEntity(vx2, 0.0D, vz2);
		motionX = vx2;
		motionZ = vz2;

	}
	private boolean shouldIgnoreSwitch(TileTCRail tile, int i, int j, int k, int meta) {
		if (TCRailTypes.isTurnTrack(tile) && tile.canTypeBeModifiedBySwitch) {
			if (meta == 2) {
				if (motionZ > 0 && Math.abs(motionX) < 0.01) {
					TileEntity tile2 = worldObj.getTileEntity(i, j, k + 1);
					if (tile2 instanceof TileTCRail) {
						((TileTCRail) tile2).setSwitchState(false, true);
					}
					return true;
				}
			}
			if (meta == 0) {
				if (motionZ < 0 && Math.abs(motionX) < 0.01) {
					TileEntity tile2 = worldObj.getTileEntity(i, j, k - 1);
					if (tile2 instanceof TileTCRail) {
						((TileTCRail) tile2).setSwitchState(false, true);
					}
					return true;
				}
			}
			if (meta == 1) {
				if (Math.abs(motionZ) < 0.002 && motionX > 0) { //allow a little more off-axis motion
					TileEntity tile2 = worldObj.getTileEntity(i + 1, j, k);
					if (tile2 instanceof TileTCRail) {
						((TileTCRail) tile2).setSwitchState(false, true);
					}
					return true;
				}
			}
			if (meta == 3) {
				if (Math.abs(motionZ) < 0.01 && motionX < 0) {
					TileEntity tile2 = worldObj.getTileEntity(i - 1, j, k);
					if (tile2 instanceof TileTCRail) {
						((TileTCRail) tile2).setSwitchState(false, true);
					}
					return true;
				}
			}
		}
		return false;
	}
	private void limitSpeedOnTCRail() {

		/*
		Block id = worldObj.getBlock(x, y, z);

		if (!BlockRailBase.isRailBlock(id)) {

			return;
		}

		railMaxSpeed = ((BlockRailBase) Block.blocksList[id]).getRailMaxSpeed(worldObj, this, x, y, z);
		 */

		//double railMaxSpeed = 3; // XXX Really? Define a field for THAT? Come on..
		double maxSpeed = Math.min(3.0D, getMaxCartSpeedOnRail());

		if (this.motionX < -maxSpeed) {

			this.motionX = -maxSpeed;
		}
		else if (this.motionX > maxSpeed) {

			this.motionX = maxSpeed;
		}

		if (this.motionZ < -maxSpeed) {

			this.motionZ = -maxSpeed;
		}
		else if (this.motionZ > maxSpeed) {

			this.motionZ = maxSpeed;
		}
	}

	@Override
	public GameProfile getOwner() {

		return  this.entityMainTrain.getOwner();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_,
			float p_70056_8_, int p_70056_9_) {
		super.setPositionAndRotation2(p_70056_1_, p_70056_3_, p_70056_5_, p_70056_7_, p_70056_8_, p_70056_9_);
		this.minecartX = p_70056_1_;
		this.minecartY = p_70056_3_;
		this.minecartZ = p_70056_5_;
		this.minecartYaw = p_70056_7_;
		this.minecartPitch = p_70056_8_;
		this.turnProgress = p_70056_9_ + 2;
	}

	public void addVelocity(AbstractTrains host, double speed){
		//cache rotation so it only has to be processed once per tick
		if(velocity[4]==0 && velocity[5]==0){
			Vec3f vec = CommonUtil.rotatePoint(new Vec3f(1,0,0),0,180+host.rotationYaw,0);
			velocity[4]=vec.xCoord;
			velocity[5]=vec.zCoord;
		}

		velocity[0]+=speed*velocity[4];
		velocity[1]+=speed*velocity[5];
	}
	public void addLinking(AbstractTrains host, double speed){
		//cache rotation so it only has to be processed once per tick
		if(velocity[4]==0 && velocity[5]==0){
			Vec3f vec = CommonUtil.rotatePoint(new Vec3f(1,0,0),0,180+host.rotationYaw,0);
			velocity[4]=vec.xCoord;
			velocity[5]=vec.zCoord;
		}

		velocity[2]+=speed*velocity[4];
		velocity[3]+=speed*velocity[5];
	}

	public void drag(AbstractTrains host, double drag){
		velocity[0]*=drag;
		velocity[1]*=drag;
	}


	public World getWorld(){return worldObj;}
	private int railMetadata, xFloor=0,yFloor=0,zFloor=0;
	private Block blockNext, blockCurrent;
	float railmax;
	private double railPathX=0, railPathZ=0,motionSqrt,railPathX2, railPathZ2;
	private static final double[][][] martix = new double[][][] {
			//straight
			{{0, -0.5}, {0, 0.5}, {0, -1}},
			{{ -0.5, 0}, {0.5, 0}, {-1, 0}},
			//slope
			{{ -0.5, 0}, {0.5, 0}, {-1, 0}},
			{{ -0.5, 0}, {0.5, 0}, {-1, 0}},
			{{0, -0.5}, {0, 0.5}, {0, -1}},
			{{0, -0.5}, {0, 0.5}, {0, -1}},
			//turns
			{{0, 0.5}, {0.5, 0}, {-0.5, 0.5}},
			{{0, 0.5}, { -0.5, 0}, {0.5, 0.5}},
			{{0, -0.5}, { -0.5, 0}, {0.5, -0.5}},
			{{0, -0.5}, {0.5, 0}, {-0.5, -0.5}}
	};

	public void minecartMove(AbstractTrains host) {
		//server only
		if(!getWorld().isRemote) {
			if(yFloor==0) {
				xFloor = CommonUtil.floorDouble(this.posX);
				yFloor = CommonUtil.floorDouble(this.posY);
				zFloor = CommonUtil.floorDouble(this.posZ);
				blockCurrent=CommonUtil.getBlockAt(getWorld(),xFloor,yFloor,zFloor);
			}
			//prevent moving without velocity
			if (Math.abs(velocity[0]) + Math.abs(velocity[1] + Math.abs(velocity[2]) + Math.abs(velocity[3])) < 0.0000001) {
				//return;
			}

			//reset rotation
			velocity[4]=0;velocity[5]=0;
			//update old position, add the gravity, and get the block below this,
			this.prevPosX = this.posX;
			this.prevPosY = this.posY;
			this.prevPosZ = this.posZ;

			Block block = CommonUtil.getBlockAt(getWorld(), xFloor, yFloor, zFloor);
			//todo: if (block instanceof BlockRailCore) {
			//update using spline movement
			//} else if (block instanceof BlockRailBase)

			if(!(block instanceof BlockRailBase)){
				if(CommonUtil.getBlockAt(getWorld(), xFloor, yFloor+1, zFloor) instanceof BlockRailBase) {
					prevPosY=posY;
					posY++;
					yFloor++;
					block = CommonUtil.getBlockAt(getWorld(), xFloor, yFloor, zFloor);
				} else if(CommonUtil.getBlockAt(getWorld(), xFloor, yFloor-1, zFloor) instanceof BlockRailBase) {
					prevPosY=posY;
					posY--;
					yFloor--;
					block = CommonUtil.getBlockAt(getWorld(), xFloor, yFloor, zFloor);
				} else if(block instanceof BlockAir) {
					//moveOffRail();
				}
			}

			//update on normal rails
			if (block instanceof BlockRailBase) {
				this.yOffset=(block instanceof BlockTCRail?0.425f:0.3425f);
				loopVanilla(host, Math.abs(velocity[0])+Math.abs(velocity[1])+Math.abs(velocity[2])+Math.abs(velocity[3]), (BlockRailBase) block);
				//update on ZnD rails, and ones that don't extend block rail base.
				//todo ZnD support, either by jar reference or API update
				//} else if (block instanceof ITrackBase) {
				//update position for ZnD rails.
				//moveBogieZnD(motionX, motionZ, floorX, floorY, floorZ, (ITrackBase) block);
			}
			velocity[2]=0;velocity[3]=0;
		}
	}


	private void loopVanilla(AbstractTrains host, double moveLength, BlockRailBase block){

		//try to adhere to limiter track
		railmax = block.getRailMaxSpeed(getWorld(),this,xFloor, yFloor, zFloor);
		Block blockUp;
		if(railmax!=0.4f){
			moveLength=Math.min(moveLength,railmax);
		}
		railMetadata = CommonUtil.getRailMeta(getWorld(), this, xFloor, yFloor, zFloor);
		//actually move
		while (moveLength>0) {
			moveBogieVanilla(Math.min(0.3, moveLength));
			moveLength -= 0.3;

			//update the last used block to the one we just used, if it's actually different.
			if(xFloor!=CommonUtil.floorDouble(this.posX) || zFloor != CommonUtil.floorDouble(this.posZ)) {
				xFloor = CommonUtil.floorDouble(this.posX);
				yFloor = CommonUtil.floorDouble(this.posY);
				zFloor = CommonUtil.floorDouble(this.posZ);
				blockCurrent=CommonUtil.getBlockAt(getWorld(),xFloor,yFloor,zFloor);
				//check for collisions and skip update
				for (int i = 1; i < host.getHitboxSize()[1] - 1; i++) {
					blockUp = CommonUtil.getBlockAt(getWorld(), xFloor, yFloor + i, zFloor);
					if (!(blockUp instanceof BlockAir)) {
						host.bogieBack.motionX=0;
						host.bogieBack.motionZ=0;
						host.bogieFront.motionX=0;
						host.bogieFront.motionZ=0;
						return;
					}
				}
				//handle slope movement before other interactions
				if(!CommonUtil.isRailBlockAt(getWorld(), xFloor, yFloor, zFloor)){
					this.prevPosY =posY;
					if(CommonUtil.isRailBlockAt(getWorld(), xFloor, yFloor+1, zFloor)){
						posY++;
					} else if (CommonUtil.isRailBlockAt(getWorld(), xFloor, yFloor-1, zFloor)) {
						posY--;
					}
					yFloor = CommonUtil.floorDouble(this.posY);
				}

				blockNext = CommonUtil.getBlockAt(getWorld(), xFloor, yFloor, zFloor);
				//now loop this again for the next increment of movement, if there is one
				if (blockNext instanceof BlockRailBase) {
					block = (BlockRailBase) blockNext;
					//do the rail functions.
					if(shouldDoRailFunctions()) {
						block.onMinecartPass(getWorld(), this, xFloor, yFloor, zFloor);
					}
					//get the direction of the rail from it's metadata
					railMetadata = CommonUtil.getRailMeta(getWorld(), this, xFloor, yFloor, zFloor);
				}
				//get the direction of the rail from it's metadata
				else if (getWorld().getTileEntity(xFloor, yFloor, zFloor) instanceof ITrackTile && (((ITrackTile)getWorld().getTileEntity(xFloor, yFloor, zFloor)).getTrackInstance() instanceof ITrackSwitch)){
					railMetadata = CommonUtil.getRailMeta(getWorld(),this,xFloor, yFloor, zFloor);//railcraft support
				}
			}
		}
	}


	private void moveBogieVanilla(double currentMotion){
		if(Math.abs(currentMotion)<0.000001){return;}
		//figure out the current rail's direction
		railPathX = (martix[railMetadata][2][0]);
		railPathZ = (martix[railMetadata][2][1]);

		//cover moving reverse of track direction using the rotation from the closed loop rather than the full motion
		if((velocity[0]+velocity[2]) * railPathX + (velocity[1]+velocity[3]) * railPathZ <= 0.0D) {
			railPathX = -railPathX;
			railPathZ = -railPathZ;
		}

		setPositionRelative((currentMotion * railPathX), 0, (currentMotion * railPathZ));

		motionSqrt = Math.abs(velocity[0])+Math.abs(velocity[1]);
		velocity[0] = (float)(motionSqrt * railPathX);
		velocity[1] = (float)(motionSqrt * railPathZ);

		motionSqrt = Math.abs(velocity[2])+Math.abs(velocity[3]);
		velocity[2] = (float)(motionSqrt * railPathX);
		velocity[3] = (float)(motionSqrt * railPathZ);

		motionSqrt = Math.abs(velocity[0])+Math.abs(velocity[1])+Math.abs(velocity[2])+Math.abs(velocity[3]);

		//define the rail path again, to center the transport.
		railPathX2 = xFloor + 0.5D + martix[railMetadata][0][0];
		railPathZ2 = zFloor + 0.5D + martix[railMetadata][0][1];
		railPathX = (xFloor + 0.5D + martix[railMetadata][1][0]) - railPathX2;
		railPathZ = (zFloor + 0.5D + martix[railMetadata][1][1]) - railPathZ2;

		//based on the path direction, try to center the bogie on the track
		if (railPathX == 0.0D) {
			motionSqrt = this.posZ - zFloor;
		} else if (railPathZ == 0.0D) {
			motionSqrt = this.posX - xFloor;
		} else {
			motionSqrt = ((this.posX - railPathX2) * railPathX + (this.posZ - railPathZ2) * railPathZ) * 2.0D;
		}
		//do the centering movement
		setPosition((railPathX2 + railPathX * motionSqrt), posY, (railPathZ2 + railPathZ * motionSqrt));
	}

	public void setPositionRelative(double x, double y, double z) {
		posX+=((int)(x*10000))*0.0001;
		if(y!=0) {//usually we won't be changing this, so this is more efficient
			posY += ((int) (y * 10000)) * 0.0001;
		}
		posZ+=((int)(z*10000))*0.0001;
	}
}