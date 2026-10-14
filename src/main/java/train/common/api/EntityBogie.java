package train.common.api;

import net.minecraft.util.math.BlockPos;

import net.minecraft.block.state.IBlockState;

import net.minecraft.util.EnumHand;

import com.mojang.authlib.GameProfile;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.entities.EntitySeat;
import ebf.tim.utility.CommonUtil;
import ebf.tim.utility.DebugUtil;
import fexcraft.tmt.slim.Vec3f;
import mods.railcraft.api.carts.IMinecart;
import mods.railcraft.api.carts.IRoutableCart;
import mods.railcraft.api.tracks.ITrackKitSwitch;
import mods.railcraft.api.tracks.IOutfittedTrackTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import train.common.blocks.BlockTCRail;
import train.common.blocks.BlockTCRailGag;
import train.common.items.TCRailTypes;
import train.common.tile.TileTCRail;
import train.common.tile.TileTCRailGag;


public class EntityBogie extends EntityMinecart implements IMinecart, IRoutableCart {

	/** 1.7.10 Entity.yOffset (removed in 1.9; Minecraft now asks getYOffset()) */
	protected double yOffsetLegacy = 0;

	@Override
	public double getYOffset() {
		return this.yOffsetLegacy;
	}


	public boolean isOnRail;
	public int meta,oldBlockX,oldBlockZ;
	public EntityRollingStock entityMainTrain;
	public TileTCRail lastTrack=null;
	/** switch this bogie is on and the leg it chose (null = not decided yet), see moveOnTCRail */
	private TileTCRail switchTile = null;
	private Boolean switchTurn = null;
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
		this.world = world;

		setSize(0.5f, 0.25f);

		//this.boundingBox.offset(0, 0.5, 0);
		setCollisionHandler(null);
		this.yOffsetLegacy = 0.65f;
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
		this.setPosition(d, d1 + getYOffset(), d2);
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
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {

		return this.height / 2.0F;
	}

	@Override
	public boolean processInitialInteract(EntityPlayer entityplayer, EnumHand hand) {

		if (this.entityMainTrain != null) {

			this.entityMainTrain.processInitialInteract(entityplayer, net.minecraft.util.EnumHand.MAIN_HAND);
		}

		return true;
	}

	public int getMinecartTypeLegacy() {

		return -1;
	}

	@Override
	public net.minecraft.entity.item.EntityMinecart.Type getType() {
		return net.minecraft.entity.item.EntityMinecart.Type.getById(getMinecartTypeLegacy());
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
	protected void moveAlongTrack(BlockPos pos, IBlockState state) {
		super.moveAlongTrack(pos, state);
	}

	private void moveOnTCRail(int i, int j, int k, Block l) {

		prevPosX=posX;
		prevPosY=posY;
		prevPosZ=posZ;

		if(l instanceof BlockTCRail) {
			if(!TCRailTypes.isCrossingTrack((TileTCRail) CommonUtil.getTileEntity(world, i, j, k)) && !TCRailTypes.isDiagonalCrossingTrack((TileTCRail) CommonUtil.getTileEntity(world, i, j, k))) {
				lastTrack = (TileTCRail) CommonUtil.getTileEntity(world, i, j, k);
			}
		} else if(l instanceof BlockTCRailGag && (lastTrack==null || !CommonUtil.getTiles(world,i,j,k).contains(lastTrack))){
			TileTCRailGag tileGag = (TileTCRailGag) CommonUtil.getTileEntity(world, i, j, k);
			if(tileGag.originX.size()>0 && CommonUtil.getTileEntity(world, tileGag.originX.get(0), tileGag.originY.get(0), tileGag.originZ.get(0)) != null) {
				lastTrack = (TileTCRail) CommonUtil.getTileEntity(world, tileGag.originX.get(0), tileGag.originY.get(0), tileGag.originZ.get(0));
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

			// The state/direction rule above is only meaningful at the points. On the rest of the switch the
			// bogie has to follow the leg it is physically on: the rule flipped when the velocity turned during
			// the curve (vz/vx changing sign or crossing 0), the bogie got snapped onto the straight axis half a
			// block sideways, and cars ended up turned beside the track. Pick the leg by geometry, use the rule
			// only where both legs overlap, and keep the choice per bogie until it leaves the switch.
			int switchMeta = lastTrack.getBlockMetadata();
			boolean zAxis = switchMeta == 0 || switchMeta == 2;
			double dStraight = zAxis ? Math.abs(posX - (lastTrack.getPos().getX() + 0.5D)) : Math.abs(posZ - (lastTrack.getPos().getZ() + 0.5D));
			double dCurve = Math.abs(Math.sqrt((posX - lastTrack.cx) * (posX - lastTrack.cx) + (posZ - lastTrack.cz) * (posZ - lastTrack.cz)) - lastTrack.r);
			if (switchTile != lastTrack) {
				switchTile = lastTrack;
				switchTurn = null;
			}
			boolean turn;
			if (dStraight < 0.25D && dCurve < 0.25D) {
				if (switchTurn == null) {
					switchTurn = !goStraight;
				}
				turn = switchTurn;
			} else {
				turn = dCurve < dStraight;
				switchTurn = turn;
			}

			if (!turn) {
				moveOnTCStraight(j, zAxis ? 0 : 1);
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
			moveOnTCSlope(j, lastTrack.getPos().getX(), lastTrack.getPos().getZ(), lastTrack.slopeAngle, lastTrack.getBlockMetadata());
		} else if (TCRailTypes.isCurvedSlopeTrack(lastTrack)) {
			moveOnTCCurvedSlope(j, lastTrack.r, lastTrack.cx, lastTrack.cz, lastTrack.getPos().getX(), lastTrack.getPos().getZ(), lastTrack.getBlockMetadata(), lastTrack.slopeAngle);
		} else if (TCRailTypes.isDiagonalTrack(lastTrack) || TCRailTypes.isDiagonalCrossingTrack(lastTrack)){
			moveOnTCDiagonal(j);
		}
		velocity[2]=0;
		velocity[3]=0;

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
		posY = j + 0.2+ getYOffset();
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
		posY = j + 0.2+ getYOffset()-0.0F;
		setPositionRelative(railPathX*motionSqrt,0,railPathZ*motionSqrt);

	}

	private void moveOnTCCurvedSlope(int j,double radius, double startX, double startZ, int tilex, int tilez, int meta, double slopeAngle) {

		moveOnTC90TurnRail(j,radius,startX,startZ);
		railPathX2 = posX - startX;
		railPathZ2 = posZ - startZ;
		motionSqrt = Math.sqrt(railPathX2 * railPathX2 + railPathZ2 * railPathZ2);

		railPathX = startX + ((railPathX2 / motionSqrt) * radius);
		railPathZ = startZ + ((railPathZ2 / motionSqrt) * radius);

		setPosition(railPathX, j + 0.2 + getYOffset(), railPathZ);

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
		posY = Math.abs(j+ Math.min(1, (slopeAngle * Math.abs(Math.sqrt(railPathX * railPathX + railPathZ * railPathZ)))) +0.2+ getYOffset() -0.0F);
	}

	private void moveOnTCTwoWaysCrossing() {
		double norm = Math.abs(velocity[0])+Math.abs(velocity[1])+Math.abs(velocity[2])+Math.abs(velocity[3]);

		if (lastTrack.getBlockMetadata()==0||lastTrack.getBlockMetadata()==2) {
			setPositionRelative(0.0D, 0.0D, Math.copySign(norm, velocity[1] + velocity[3])); // was abs(): crossings only ran one way
		}
		else {
			setPositionRelative(Math.copySign(norm, velocity[0] + velocity[2]), 0.0D, 0.0D);
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
		double newYPos = Math.abs(j+ Math.min(1, (slopeAngle * Math.abs(Math.sqrt(railPathX * railPathX + railPathZ * railPathZ)))) + getYOffset() + 0.34f);
		setPositionRelative(0, newYPos-(j + 0.2 + getYOffset()), 0);
	}

	private void moveOnTC90TurnRail(int j,double radius, double startX, double startZ){
		posY = j + RAIL_HEIGHT; // turns kept whatever height the bogie had when it entered
        double controlX = posX - startX;
        double controlZ = posZ - startZ;

        double controlNorm = Math.sqrt(controlX * controlX + controlZ * controlZ);
		double xMotion=velocity[0]+velocity[2];
		double zMotion=velocity[1]+velocity[3];
        double vnorm = Math.sqrt(xMotion * xMotion + zMotion * zMotion);

		motionSqrt = Math.sqrt(velocity[0] * velocity[0] + velocity[1] * velocity[1]);

        double norm_cpx = controlX / controlNorm; //u
        double norm_cpz = controlZ / controlNorm; //v

        railPathX = (posX + xMotion) - startX;
        railPathZ = (posZ + zMotion) - startZ;

        double p2_c_norm = Math.sqrt((railPathX * railPathX) + (railPathZ * railPathZ));

		railPathX2 = (startX + ((railPathX / p2_c_norm) * radius)) - posX;
		railPathZ2 = (startZ + ((railPathZ / p2_c_norm) * radius)) - posZ;

        setPosition(startX + ((controlX / controlNorm) * radius), posY, startZ + ((controlZ / controlNorm) * radius));
        setPositionRelative(Math.copySign(-norm_cpz * vnorm, railPathX2), 0.0D, Math.copySign(norm_cpx * vnorm, railPathZ2));

		velocity[0] = Math.copySign(-norm_cpz * motionSqrt, railPathX2);
		velocity[1] = Math.copySign(norm_cpx * motionSqrt, railPathZ2);
	}

	private void limitSpeed(AbstractTrains host, double speedMagnitude) {

		// Default speed for most carts
		double maxSpeed = this.getMaxCartSpeedOnRail();
		// Current max speed for locos
		if (host instanceof Locomotive) {
			maxSpeed = Math.min(maxSpeed,SpeedHandler.convertSpeed((double)((Locomotive)host).getCurrentMaxSpeed()));
		}

		if (speedMagnitude > maxSpeed) {
			double overspeedFactor = speedMagnitude/maxSpeed;
			velocity[0] /= overspeedFactor;
			velocity[1] /= overspeedFactor;
		}
	}

	@Override
	public GameProfile getOwner() {

		return  this.entityMainTrain.getOwner();
	}

	/*
	 * Velocity needs to be added relative to each bogie's current rotation to prevent drifting between them, as the host's rotation doesn't match when entering curves.
	 * Always adding in the direction of existing movement prevents reverse, and is unpredictable with null starting velocity, so we compare the bogie's rotation to the host's.
	 */
	public void addVelocity(AbstractTrains host, double speed) {
		Vec3f bogieRotation;
		if(Math.abs(prevPosZ-posZ)+Math.abs(prevPosX-posX)>0.1) {
			bogieRotation=CommonUtil.rotatePoint(new Vec3f(speed, 0, 0), 0, CommonUtil.atan2degreesf(prevPosZ - posZ, prevPosX - posX), 0);
		} else {
			bogieRotation=CommonUtil.rotatePoint(new Vec3f(-speed, 0, 0), 0, host.rotationYaw, 0);
		}
		velocity[0] += bogieRotation.xCoord;
		velocity[1] += bogieRotation.zCoord;
	}

	/** adds momentum as a world-space vector (blocks/tick); the rail code turns it along the track */
	public void addVelocityVector(double x, double z) {
		velocity[0] += x;
		velocity[1] += z;
	}

	/** linking correction as a world-space vector (blocks/tick), see EntityRollingStock#manageLink */
	public void addLinkingVector(double x, double z) {
		velocity[2] += x;
		velocity[3] += z;
	}

	public void addLinking(AbstractTrains host, double speed) {
		Vec3f bogieRotation;
		if(Math.abs(prevPosZ-posZ)+Math.abs(prevPosX-posX)>0.1) {
			bogieRotation=CommonUtil.rotatePoint(new Vec3f(speed, 0, 0), 0, CommonUtil.atan2degreesf(prevPosZ - posZ, prevPosX - posX), 0);
		} else {
			bogieRotation=CommonUtil.rotatePoint(new Vec3f(-speed, 0, 0), 0, host.rotationYaw, 0);
		}
		velocity[2] += bogieRotation.xCoord;
		velocity[3] += bogieRotation.zCoord;
	}

	public void multiplyVelocity(double mult) {
		velocity[0] *= mult;
		velocity[1] *= mult;
	}

	public void setVelocity(AbstractTrains host, double speed){
		Vec3f bogieRotation;
		if(Math.abs(prevPosZ-posZ)+Math.abs(prevPosX-posX)>0.1) {
			bogieRotation=CommonUtil.rotatePoint(new Vec3f(speed, 0, 0), 0, CommonUtil.atan2degreesf(prevPosZ - posZ, prevPosX - posX), 0);
		} else {
			bogieRotation=CommonUtil.rotatePoint(new Vec3f(-speed, 0, 0), 0, host.rotationYaw, 0);
		}
		velocity[0] = bogieRotation.xCoord;
		velocity[1] = bogieRotation.zCoord;
	}

	public World getWorld(){return world;}

	/**
	 * Height of a bogie above the rail block it runs on, the same on every kind of track: TC rails use
	 * j + 0.2 + 0.425 (moveOnTC*), vanilla/Railcraft rails used to keep whatever height the cart was spawned at
	 * (j + 0.3) and only stepped +-1 on slopes. Mixing the two made trains sink/float when they changed track
	 * type, after the first push, and on vanilla slopes.
	 */
	public static final double RAIL_HEIGHT = 0.625D;
	private static final int NO_RAIL = Integer.MIN_VALUE;

	private boolean isAnyRail(int x, int y, int z) {
		Block b = CommonUtil.getBlockAt(getWorld(), x, y, z);
		return b instanceof BlockRailBase || b instanceof BlockTCRail || b instanceof BlockTCRailGag;
	}

	/** y of the rail block under a bogie at height y (checks the expected block, then one up/down for slopes) */
	private int findRailLevel(int x, double y, int z) {
		int base = CommonUtil.floorDouble(y - RAIL_HEIGHT + 0.001D);
		if (isAnyRail(x, base, z)) return base;
		if (isAnyRail(x, base + 1, z)) return base + 1;
		if (isAnyRail(x, base - 1, z)) return base - 1;
		return NO_RAIL;
	}

	/** bogie height on a vanilla rail; ascending rails rise linearly over the block like vanilla minecarts */
	private double vanillaRailY(int railY, int meta) {
		double rise = 0;
		switch (meta) {
			case 2: rise = this.posX - xFloor; break;        // ascending east
			case 3: rise = 1D - (this.posX - xFloor); break; // ascending west
			case 4: rise = 1D - (this.posZ - zFloor); break; // ascending north
			case 5: rise = this.posZ - zFloor; break;        // ascending south
			default: break;
		}
		return railY + RAIL_HEIGHT + Math.max(0D, Math.min(1D, rise));
	}

	private boolean isOnTCSlope(int railLevel) {
		TileEntity te = CommonUtil.getTileEntity(getWorld(), xFloor, railLevel, zFloor);
		TileTCRail rail = null;
		if (te instanceof TileTCRail) {
			rail = (TileTCRail) te;
		} else if (te instanceof TileTCRailGag && ((TileTCRailGag) te).originX.size() > 0) {
			TileTCRailGag gag = (TileTCRailGag) te;
			TileEntity origin = CommonUtil.getTileEntity(getWorld(), gag.originX.get(0), gag.originY.get(0), gag.originZ.get(0));
			rail = origin instanceof TileTCRail ? (TileTCRail) origin : null;
		}
		return rail != null && (TCRailTypes.isSlopeTrack(rail) || TCRailTypes.isCurvedSlopeTrack(rail));
	}

	private void snapToRail(int railLevel) {
		Block b = CommonUtil.getBlockAt(getWorld(), xFloor, railLevel, zFloor);
		double target;
		if (b instanceof BlockRailBase) {
			target = vanillaRailY(railLevel, CommonUtil.getRailMeta(getWorld(), this, xFloor, railLevel, zFloor));
		} else if (isOnTCSlope(railLevel)) {
			return; // the slope height depends on the position along the slope, set by moveOnTCSlope
		} else {
			target = railLevel + RAIL_HEIGHT;
		}
		if (Math.abs(this.posY - target) > 0.001D) {
			this.prevPosY = this.posY;
			setPosition(this.posX, target, this.posZ);
		}
	}

	public void minecartMove(AbstractTrains host) {
		//server only
		if(!world.isRemote) {
			xFloor = CommonUtil.floorDouble(this.posX);
			zFloor = CommonUtil.floorDouble(this.posZ);
			int railLevel = findRailLevel(xFloor, this.posY, zFloor);
			yFloor = railLevel != NO_RAIL ? railLevel : CommonUtil.floorDouble(this.posY);
			//prevent moving without velocity
			if (Math.abs(velocity[0]) + Math.abs(velocity[1]) + Math.abs(velocity[2]) + Math.abs(velocity[3]) ==0) {
				// a freshly placed/loaded train sits at its spawn height until it moves; put it on the rail now
				// instead of letting it jump on the first push
				if (railLevel != NO_RAIL) {
					snapToRail(railLevel);
				}
				return;
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
				if(CommonUtil.getBlockAt(getWorld(),xFloor, yFloor-1, zFloor) instanceof BlockAir){
					posY--;
					yFloor--;
				}
				l = CommonUtil.getBlockAt(getWorld(), xFloor, yFloor, zFloor);

				//if it wasn't a slope, fall back to the last block, only do this for a single full block distance.
				if(!(l instanceof BlockAir || l instanceof BlockRailBase ||
						l instanceof BlockTCRail || l instanceof BlockTCRailGag) &&
						(Math.abs(xFloor)-Math.abs(oldBlockZ))+(Math.abs(zFloor)+Math.abs(oldBlockZ))<=2){
					l=oldL;
					posY++;
					yFloor++;
				}
			} else {
				oldBlockX=xFloor;
				oldBlockZ=zFloor;
			}

			//move on rails
			isOnRail = true;
			// Fold the one-tick linking correction (velocity[2..3]) into the motion before moving. The rail code
			// re-points every velocity component along the direction of the *total* motion and moves by the sum of
			// magnitudes, so a correction pointing against the motion was added instead of subtracted and coupled
			// cars accelerated each other until they shot off. Afterwards the car keeps exactly its own momentum
			// (signed, along the new direction), so neither the linking nor the rail code's rounding (L1 norms,
			// diagonal centring) can create speed: that made pushed wagons drive themselves and ignore the brakes.
			limitSpeed(host, Math.sqrt(velocity[0] * velocity[0] + velocity[1] * velocity[1]));
			double mainX = velocity[0], mainZ = velocity[1];
			double totalX = velocity[0] + velocity[2], totalZ = velocity[1] + velocity[3];
			double totalNorm = Math.sqrt(totalX * totalX + totalZ * totalZ);
			double mainAlong = totalNorm > 1.0E-9 ? (mainX * totalX + mainZ * totalZ) / totalNorm : 0D;
			velocity[0] = totalX; velocity[1] = totalZ; velocity[2] = 0; velocity[3] = 0;
			double speedMagnitude = totalNorm;
			if (l instanceof BlockRailBase) {
				this.yOffsetLegacy = 0.3425f;
				loopVanilla(host, speedMagnitude, (BlockRailBase) l);
			} else if (l instanceof BlockTCRail || l instanceof BlockTCRailGag){
				this.yOffsetLegacy = 0.425f;
				moveOnTCRail(xFloor, yFloor, zFloor, l);
			} else {
				this.yOffsetLegacy = 1.3f;
				posX+=(velocity[2]+velocity[0])*0.5;
				posZ+=(velocity[3]+velocity[1])*0.5;
				isOnRail = false;
			}
			// back to the car's own momentum, pointing along the rail direction it ended up in
			double afterNorm = Math.sqrt(velocity[0] * velocity[0] + velocity[1] * velocity[1]);
			if (totalNorm > 1.0E-9 && afterNorm > 1.0E-9) {
				velocity[0] = velocity[0] / afterNorm * mainAlong;
				velocity[1] = velocity[1] / afterNorm * mainAlong;
			} else {
				velocity[0] = mainX;
				velocity[1] = mainZ;
			}
			velocity[2]=0;velocity[3]=0;
		}
	}


	private void loopVanilla(AbstractTrains host, double moveLength, BlockRailBase block){

		//try to adhere to limiter track
		railmax = CommonUtil.getMaxRailSpeed(getWorld(), block, host, xFloor, yFloor, zFloor);
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
				zFloor = CommonUtil.floorDouble(this.posZ);
				int railLevel = findRailLevel(xFloor, this.posY, zFloor);
				yFloor = railLevel != NO_RAIL ? railLevel : CommonUtil.floorDouble(this.posY);
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
				//slopes: findRailLevel above already picked the rail one block up/down, posY follows below

				l = CommonUtil.getBlockAt(getWorld(), xFloor, yFloor, zFloor);
				//now loop this again for the next increment of movement, if there is one
				if (l instanceof BlockRailBase) {
					block = (BlockRailBase) l;
					//do the rail functions.
					if(shouldDoRailFunctions()) {
						block.onMinecartPass(getWorld(), this, new BlockPos(xFloor, yFloor, zFloor));
					}
					//get the direction of the rail from it's metadata
					railMetadata = CommonUtil.getRailMeta(getWorld(), this, xFloor, yFloor, zFloor);
				}
				//get the direction of the rail from it's metadata
				else if (CommonUtil.getTileEntity(getWorld(), xFloor, yFloor, zFloor) instanceof IOutfittedTrackTile && (((IOutfittedTrackTile)CommonUtil.getTileEntity(getWorld(), xFloor, yFloor, zFloor)).getTrackKitInstance() instanceof ITrackKitSwitch)){
					railMetadata = CommonUtil.getRailMeta(getWorld(),this,xFloor, yFloor, zFloor);//railcraft support
				}
			}
			// follow the rail height (flat or ascending) after every step, with the block/meta of where we are now
			if (CommonUtil.getBlockAt(getWorld(), xFloor, yFloor, zFloor) instanceof BlockRailBase) {
				this.posY = vanillaRailY(yFloor, railMetadata);
			}
		}
	}


	private void moveBogieVanilla(double currentMotion){
		if(Math.abs(currentMotion)<0.000001){return;}
		//figure out the current rail's direction
		railPathX = (martix[railMetadata][2][0]);
		railPathZ = (martix[railMetadata][2][1]);

		railPathX = Math.copySign(Math.sqrt(Math.abs(railPathX)),railPathX);
		railPathZ = Math.copySign(Math.sqrt(Math.abs(railPathZ)),railPathZ);

		//cover moving reverse of track direction using the rotation from the closed loop rather than the full motion
		if((velocity[0]+velocity[2]) * railPathX + (velocity[1]+velocity[3]) * railPathZ <= 0.0D) {
			railPathX = -railPathX;
			railPathZ = -railPathZ;
		}

		setPositionRelative((currentMotion * railPathX), 0, (currentMotion * railPathZ));

		motionSqrt = Math.sqrt(Math.pow(velocity[0],2)+Math.pow(velocity[1],2));
		velocity[0] = (float)(motionSqrt * railPathX);
		velocity[1] = (float)(motionSqrt * railPathZ);

		motionSqrt = Math.sqrt(Math.pow(velocity[2],2)+Math.pow(velocity[3],2));
		velocity[2] = (float)(motionSqrt * railPathX);
		velocity[3] = (float)(motionSqrt * railPathZ);

		motionSqrt = Math.sqrt(Math.pow(velocity[0],2)+Math.pow(velocity[1],2))+Math.sqrt(Math.pow(velocity[2],2)+Math.pow(velocity[3],2));

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
		float f = this.width / 2.0F;
		// 1.12: the box must follow the absolute position (x/y/z here are deltas), like Entity.setPosition does
		setEntityBoundingBox(new AxisAlignedBB(posX - (double)f, posY, posZ - (double)f, posX + (double)f, posY + (double)this.height, posZ + (double)f));

	}
}