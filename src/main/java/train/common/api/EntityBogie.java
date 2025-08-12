package train.common.api;

import com.mojang.authlib.GameProfile;
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
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import train.common.blocks.BlockTCRail;
import train.common.blocks.BlockTCRailGag;
import train.common.items.TCRailTypes;
import train.common.tile.TileTCRail;
import train.common.tile.TileTCRailGag;


public class EntityBogie extends EntityMinecart implements IMinecart, IRoutableCart {

	public boolean isOnRail;
	public int meta,oldBlockX,oldBlockZ;
	public EntityRollingStock entityMainTrain;
	public TileTCRail lastTrack=null;
	public Block l,oldL;


	private int railMetadata, xFloor=0,yFloor=0,zFloor=0;
	private float railmax;
	private double railPathX=0, railPathZ=0,motionSqrt,railPathX2, railPathZ2;
	public static final double[][][] martix = new double[][][] {
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

	double[] velocity = new double[]{0,0,0,0,0,0};




	public EntityBogie(World world) {

		super(world);

		this.isOnRail = false;
		this.worldObj = world;

		setSize(0.5f, 1.25f);

		//this.boundingBox.offset(0, 0.5, 0);
		setCollisionHandler(null);
		this.yOffset = 0.65f;
		//this.setSize(0.1F, 1.98F);
		isImmuneToFire = true;
		noClip=true;
	}

	public EntityBogie(World world, double d, double d1, double d2, EntityRollingStock mainTrain) {

		this(world);

		this.entityMainTrain = mainTrain;
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = d;
		this.prevPosY = d1;
		this.prevPosZ = d2;
		this.setPosition(d, d1 + this.yOffset, d2);
		isImmuneToFire = true;
		setSize(0.5f, 1.25f);
		noClip=true;
	}

	@Override
	public boolean canBePushed() {

		return false;
	}

	@Override
	public boolean canBeRidden() {
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

	private void moveOnTCRail(int i, int j, int k, Block l) {
		limitSpeedOnTCRail();

		if(l instanceof BlockTCRail) {
			if(!TCRailTypes.isCrossingTrack((TileTCRail) worldObj.getTileEntity(i, j, k)) && !TCRailTypes.isDiagonalCrossingTrack((TileTCRail) worldObj.getTileEntity(i,j,k))) {
				lastTrack = (TileTCRail) worldObj.getTileEntity(i, j, k);
			}
		} else if(l instanceof BlockTCRailGag && (lastTrack==null || !CommonUtil.getTiles(worldObj,i,j,k).contains(lastTrack))){
			TileTCRailGag tileGag = (TileTCRailGag) worldObj.getTileEntity(i, j, k);
			if(tileGag.originX.size()>0 && worldObj.getTileEntity(tileGag.originX.get(0), tileGag.originY.get(0), tileGag.originZ.get(0)) != null) {
				lastTrack = (TileTCRail) worldObj.getTileEntity(tileGag.originX.get(0), tileGag.originY.get(0), tileGag.originZ.get(0));
			}
		}

		// --- Switch track logic fix ---
		if (TCRailTypes.isSwitchTrack(lastTrack)) {
			// Determine direction of travel
			double vx = velocity[0] + velocity[2];
			double vz = velocity[1] + velocity[3];
			boolean goStraight = false;

			// For each meta, determine if the bogie is going "against" the curve (should ignore switch)
			switch (lastTrack.getBlockMetadata()) {
				case 0: // North-South (Z axis)
					goStraight = (vz > 0 && !lastTrack.getSwitchState()) || (vz < 0 && lastTrack.getSwitchState());
					break;
				case 2: // South-North (Z axis)
					goStraight = (vz < 0 && !lastTrack.getSwitchState()) || (vz > 0 && lastTrack.getSwitchState());
					break;
				case 1: // West-East (X axis)
					goStraight = (vx > 0 && !lastTrack.getSwitchState()) || (vx < 0 && lastTrack.getSwitchState());
					break;
				case 3: // East-West (X axis)
					goStraight = (vx < 0 && !lastTrack.getSwitchState()) || (vx > 0 && lastTrack.getSwitchState());
					break;
			}

			if (goStraight) {
				moveOnTCStraight(j, meta);
			} else {
				moveOnTC90TurnRail(j, lastTrack.r, lastTrack.cx, lastTrack.cz);
			}
		}
		else if (TCRailTypes.isStraightTrack(lastTrack)) {
			moveOnTCStraight(j, lastTrack.getBlockMetadata());
		} else if(TCRailTypes.isTurnTrack(lastTrack)){
			moveOnTC90TurnRail(j, lastTrack.r, lastTrack.cx, lastTrack.cz);
		} else if (TCRailTypes.isCrossingTrack(lastTrack)) {
			moveOnTCTwoWaysCrossing();
		} else if (TCRailTypes.isSlopeTrack(lastTrack)) {
			moveOnTCSlope(j, lastTrack.xCoord, lastTrack.zCoord, lastTrack.slopeAngle, lastTrack.getBlockMetadata());
		} else if (TCRailTypes.isCurvedSlopeTrack(lastTrack)) {
			moveOnTCCurvedSlope(j, lastTrack.r, lastTrack.cx, lastTrack.cz, lastTrack.xCoord, lastTrack.zCoord, lastTrack.getBlockMetadata(), 1, lastTrack.slopeAngle);
		} else if (TCRailTypes.isDiagonalTrack(lastTrack) || TCRailTypes.isDiagonalCrossingTrack(lastTrack)){
			moveOnTCDiagonal(j);
		}

	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate(){
		if(ticksExisted%100==1){
			if(entityMainTrain==null){
				setDead();
			}
		}
	}

	private void moveOnTCDiagonal(int j) {

		railPathX=Math.copySign(0.5,velocity[0]+velocity[2]);
		railPathZ=Math.copySign(0.5,velocity[1]+velocity[3]);
		motionSqrt = Math.abs(velocity[0])+Math.abs(velocity[1]);
		velocity[0] = motionSqrt * railPathX;
		velocity[1] = motionSqrt * railPathZ;

		if(velocity[2]!=0 || velocity[3]!=0) {
			motionSqrt = Math.abs(velocity[2]) + Math.abs(velocity[3]);
			velocity[2] = motionSqrt * railPathX;
			velocity[3] = motionSqrt * railPathZ;
		}

		centerDiagonal(posX-xFloor,posZ-zFloor);

		motionSqrt = Math.abs(velocity[0])+Math.abs(velocity[1])+Math.abs(velocity[2])+Math.abs(velocity[3]);
		posY = j + 0.2+ yOffset;
		setPositionRelative(railPathX*motionSqrt,0,railPathZ*motionSqrt);
	}

	public void centerDiagonal(double x, double z) {
		// Calculate the vector from the center to the given point
		railPathX2 = x - 0.5;
		railPathZ2 = z - 0.5;
		// get the nearest 45 degree angle from the block center to the current position
		double nearestAngleRadians = (Math.round(CommonUtil.atan2degreesf(railPathZ2, railPathX2) / 45.0) * 45.0)*
				CommonUtil.radianF;
		// Calculate the distance from the center to the given point
		double distance = Math.sqrt(railPathX2 * railPathX2 + railPathZ2 * railPathZ2);
		//offset the secondary movement vector (because it gets nuked at the start of every tick)
		velocity[2]+= x-(0.5 + distance * Math.cos(nearestAngleRadians));
		velocity[3]+= z-(0.5 + distance * Math.sin(nearestAngleRadians));
	}

	private void moveOnTCStraight(int j, int meta) {
		if(meta==2 || meta==0){
			railPathX=0;
			railPathZ=Math.copySign(1,velocity[1]+velocity[3]);
			posX=xFloor+0.5;
		} else {
			railPathX=Math.copySign(1,velocity[0]+velocity[2]);
			railPathZ=0;
			posZ=zFloor+0.5;
		}
		motionSqrt = Math.abs(velocity[0])+Math.abs(velocity[1]);
		velocity[0] = motionSqrt * railPathX;
		velocity[1] = motionSqrt * railPathZ;

		if(velocity[2]!=0 || velocity[3]!=0) {
			motionSqrt = Math.abs(velocity[2]) + Math.abs(velocity[3]);
			velocity[2] = motionSqrt * railPathX;
			velocity[3] = motionSqrt * railPathZ;
		}

		motionSqrt = Math.abs(velocity[0])+Math.abs(velocity[1])+Math.abs(velocity[2])+Math.abs(velocity[3]);
		posY = j + 0.2+ yOffset-ySize;
		setPositionRelative(railPathX*motionSqrt,0,railPathZ*motionSqrt);

	}

	private void moveOnTCCurvedSlope(int j,double radius, double startX, double startZ, int tilex, int tilez, int meta, double slopeHeight, double slopeAngle) {

		railPathX2 = posX - startX;
		railPathZ2 = posZ - startZ;
		motionSqrt = Math.sqrt(railPathX2 * railPathX2 + railPathZ2 * railPathZ2);

		railPathX = startX + ((railPathX2 / motionSqrt) * radius);
		railPathZ = startZ + ((railPathZ2 / motionSqrt) * radius);

		setPosition(railPathX, j + 0.2 + yOffset, railPathZ);

		double[] vel1 = turnOffsetXZ(startX,startZ,velocity[0],velocity[1],radius,motionSqrt);
		double[] vel2 = turnOffsetXZ(startX,startZ,velocity[2],velocity[3],radius,motionSqrt);

		railPathX = tilex - posX;
		railPathZ = tilez - posZ;
		if (meta == 2 ) {
			railPathZ += 1;
			railPathX += 0.5;
		} else if (meta == 0) {
			railPathX += 0.5;
		} else if (meta == 1 ) {
			railPathX += 1;
			railPathZ += 0.5;
		} else if (meta == 3) {
			railPathZ += 0.5;
		}
		double newYPos = Math.abs(j+ Math.min(1, (slopeAngle * Math.abs(Math.sqrt(railPathX * railPathX + railPathZ * railPathZ)))) + yOffset + 0.34f);

		setPositionRelative(vel1[0]+vel2[0], newYPos-posY, vel1[1]+vel2[1]);
		velocity[0] = vel1[0];
		velocity[1] = vel1[1];

	}

	private void moveOnTCTwoWaysCrossing() {
		double norm = Math.abs(velocity[0])+Math.abs(velocity[1])+Math.abs(velocity[2])+Math.abs(velocity[3]);

		if (lastTrack.blockMetadata==0||lastTrack.blockMetadata==2) {
			setPositionRelative(0.0D, 0.0D, Math.copySign(norm, Math.abs(velocity[1])+ Math.abs(velocity[3])));
		}
		else {
			setPositionRelative(Math.copySign(norm, Math.abs(velocity[0])+Math.abs(velocity[2])), 0.0D, 0.0D);
		}

	}

	private void moveOnTCSlope(int j, double tilex, double tilez, double slopeAngle, int meta) {
		//slopes use the opposite axis of straights for some reason, so we gotta invert it.
		moveOnTCStraight(j, meta);
		railPathX = tilex - posX;
		railPathZ = tilez - posZ;
		if (meta == 2 ) {
			railPathZ += 1;
			railPathX += 0.5;
		} else if (meta == 0) {
			railPathX += 0.5;
		} else if (meta == 1 ) {
			railPathX += 1;
			railPathZ += 0.5;
		} else if (meta == 3) {
			railPathZ += 0.5;
		}
		double newYPos = Math.abs(j+ Math.min(1, (slopeAngle * Math.abs(Math.sqrt(railPathX * railPathX + railPathZ * railPathZ)))) + yOffset + 0.34f);
		setPositionRelative(0, newYPos-(j + 0.2 + yOffset), 0);
	}

	private void moveOnTC90TurnRail(int j,double radius, double startX, double startZ){

		railPathX2 = posX - startX;
		railPathZ2 = posZ - startZ;
		motionSqrt = Math.sqrt(railPathX2 * railPathX2 + railPathZ2 * railPathZ2);

		railPathX = startX + ((railPathX2 / motionSqrt) * radius);
		railPathZ = startZ + ((railPathZ2 / motionSqrt) * radius);

		setPosition(railPathX, j + 0.2 + yOffset, railPathZ);

		double[] vel1 = turnOffsetXZ(startX,startZ,velocity[0],velocity[1],radius,motionSqrt);
		double[] vel2 = turnOffsetXZ(startX,startZ,velocity[2],velocity[3],radius,motionSqrt);

		setPositionRelative(vel1[0]+vel2[0], 0, vel1[1]+vel2[1]);
		velocity[0] = vel1[0];
		velocity[1] = vel1[1];
	}


	private double[] turnOffsetXZ(double startX, double startZ, double speedX, double speedZ, double radius, double norm){

		if(speedX==0 && speedZ==0){
			return new double[]{0,0};
		}

		railPathX = posX + speedX - startX;
		railPathZ = posZ + speedZ - startZ;

		motionSqrt = Math.sqrt((railPathX * railPathX) + (railPathZ * railPathZ));

		railPathX = (startX + ((railPathX / motionSqrt) * radius)) - posX;
		railPathZ = (startZ + ((railPathZ / motionSqrt) * radius)) - posZ;

		motionSqrt = Math.sqrt(velocity[0] * velocity[0] + velocity[1] * velocity[1]);

		railPathX = Math.copySign(-(railPathZ2 / norm) * motionSqrt, railPathX);
		railPathZ = Math.copySign((railPathX2 / norm) * motionSqrt, railPathZ);

		return new double[]{railPathX+0,railPathZ+0};

	}

	private void limitSpeedOnTCRail() {
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

	public void addVelocity(AbstractTrains host, double speed) {
		//cache rotation so it only has to be processed once per tick
		if(velocity[4] == 0 && velocity[5] == 0) {
			Vec3f vec = CommonUtil.rotatePoint(new Vec3f(1,0,0),0,180 + host.rotationYaw,0);
			velocity[4] = vec.xCoord;
			velocity[5] = vec.zCoord;
		}

		velocity[0] += speed * velocity[4];
		velocity[1] += speed * velocity[5];
	}

	public void multiplyVelocity(AbstractTrains host, double mult) {
		if (velocity[4] == 0 & velocity[5] == 0) {
			Vec3f vec = CommonUtil.rotatePoint(new Vec3f(1,0,0),0,180 + host.rotationYaw,0);
			velocity[4] = vec.xCoord;
			velocity[5] = vec.zCoord;
		}
		velocity[0] *= (mult * velocity[4]);
		velocity[1] *= (mult * velocity[5]);
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

	public void minecartMove(AbstractTrains host) {
		//server only
		if(!getWorld().isRemote) {
			xFloor = CommonUtil.floorDouble(this.posX);
			yFloor = CommonUtil.floorDouble(this.posY);
			zFloor = CommonUtil.floorDouble(this.posZ);
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

			oldL=l;

			l = CommonUtil.getBlockAt(getWorld(), xFloor, yFloor, zFloor);
			//detect slopes
			if(!(l instanceof BlockRailBase || l instanceof BlockTCRail || l instanceof BlockTCRailGag)){
				prevPosY = posY;
				if(getWorld().isAirBlock(xFloor, yFloor+1, zFloor)){
					posY--;
					yFloor--;
				} else {
					posY++;
					yFloor++;
				}
				l = CommonUtil.getBlockAt(getWorld(), xFloor, yFloor, zFloor);

				//if it wasn't a slope, fall back to the last block, only do this for a single full block distance.
				if(!(l instanceof BlockAir || l instanceof BlockRailBase ||
						l instanceof BlockTCRail || l instanceof BlockTCRailGag) &&
						(Math.abs(xFloor)-Math.abs(oldBlockZ))+(Math.abs(zFloor)+Math.abs(oldBlockZ))<=2){
					l=oldL;
				}
			} else {
				oldBlockX=xFloor;
				oldBlockZ=zFloor;
			}



			//move on rails
			if (l instanceof BlockRailBase) {
				this.yOffset=0.3425f;
				loopVanilla(host, Math.abs(velocity[0])+Math.abs(velocity[1])+Math.abs(velocity[2])+Math.abs(velocity[3]), (BlockRailBase) l);
			} else if (l instanceof BlockTCRail || l instanceof BlockTCRailGag){
				this.yOffset=0.425f;
				moveOnTCRail(xFloor, yFloor, zFloor, l);
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

				l = CommonUtil.getBlockAt(getWorld(), xFloor, yFloor, zFloor);
				//now loop this again for the next increment of movement, if there is one
				if (l instanceof BlockRailBase) {
					block = (BlockRailBase) l;
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