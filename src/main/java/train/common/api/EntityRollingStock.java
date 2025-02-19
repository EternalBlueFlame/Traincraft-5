package train.common.api;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.api.SkinRegistry;
import ebf.tim.entities.EntitySeat;
import ebf.tim.utility.CommonUtil;
import ebf.tim.utility.DebugUtil;
import fexcraft.tmt.slim.Vec3f;
import io.netty.buffer.ByteBuf;
import mods.railcraft.api.carts.CartTools;
import mods.railcraft.api.carts.ILinkableCart;
import mods.railcraft.api.tracks.RailTools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.TraincraftEntityHelper;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartCollisionEvent;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;
import train.client.core.handlers.SoundUpdaterRollingStock;
import train.common.Traincraft;
import train.common.adminbook.ServerLogger;
import train.common.core.HandleOverheating;
import train.common.core.handlers.*;
import train.common.core.network.PacketRollingStockRotation;
import train.common.core.util.DepreciatedUtil;
import train.common.core.util.TraincraftUtil;
import train.common.entity.CollisionBox;
import train.common.entity.EntityHitbox;
import train.common.entity.rollingStockOld.EntityTracksBuilder;
import train.common.items.*;
import train.common.library.BlockIDs;
import train.common.library.GuiIDs;
import train.common.tile.TileTCRail;

import java.util.ArrayList;
import java.util.List;

import static train.common.core.util.TraincraftUtil.degrees;
import static train.common.core.util.TraincraftUtil.isRailBlockAt;

public class EntityRollingStock extends AbstractTrains implements ILinkableCart {
    public int fuelTrain;
    protected static final int[][][] matrix = {
            {{0, 0, -1}, {0, 0, 1}},
            {{-1, 0, 0}, {1, 0, 0}},
            {{-1, -1, 0}, {1, 0, 0}},
            {{-1, 0, 0}, {1, -1, 0}},
            {{0, 0, -1}, {0, -1, 1}},
            {{0, -1, -1}, {0, 0, 1}},
            {{0, 0, 1}, {1, 0, 0}},
            {{0, 0, 1}, {-1, 0, 0}},
            {{0, 0, -1}, {-1, 0, 0}},
            {{0, 0, -1}, {1, 0, 0}}};

    protected EntityPlayer playerEntity;

    /**
     * Axis aligned bounding box. this needs to be it's own thing because collisions
     */
    private AxisAlignedBB boundingBoxSmall;

    public float maxSpeed;
    public float railMaxSpeed;
    public double speedLimiter = 1;
    public boolean speedWasSet = false;

    public ItemStack item;
    public float rotation;

    public int rail;
    public int meta;
    public double d6;
    public double d7;

    /**
     * appears to be the progress of the turn
     */
    private int rollingturnProgress;
    private double rollingX=0;
    private double rollingY=0;
    private double rollingZ=0;
    private float rollingServerPitch;
    public double rotationYawClient;
    public float rotationYawClientReal;
    public float anglePitchClient;//was a double
    private float previousServerRealRotation;
    public boolean isServerInReverse = false;
    public boolean isClientInReverse = false;
    public boolean serverInReverseSignPositive = false;
    public float serverRealPitch;
    private double rollingPitch;
    public float oldClientYaw = 0;//used in rendering class
    @SideOnly(Side.CLIENT)
    private double rollingVelocityX;
    @SideOnly(Side.CLIENT)
    private double rollingVelocityY;
    @SideOnly(Side.CLIENT)
    private double rollingVelocityZ;

    public EntityHitbox collisionHandler=null;

    private LinkHandler linkhandler;
    private TrainsOnClick trainsOnClick;
    public boolean isBraking;
    public boolean isClimbing;
    public int overheatLevel;
    public int linkageNumber;

    public Side side;
    @SideOnly(Side.CLIENT)
    private SoundHandler theSoundManager;
    @SideOnly(Side.CLIENT)
    private SoundUpdaterRollingStock sndUpdater;
    /**
     * Array containing @TrainHandler objects. In other words it contains all
     * the "trains" object the train object contains an array which contains all @RollingStocks
     * that are part of the train
     */
    public static ArrayList<TrainHandler> allTrains = new ArrayList<TrainHandler>();
    private HandleOverheating handleOverheating;
    /**
     * each ticks: numLaps++ used for fuel consumption rate
     */
    private int numLaps;

    private int ticksSinceHeld = 0;
    private boolean cartLocked = false;

    /**
     * New physics integration
     */
    private double bogieShift = 0;
    private boolean needsBogieUpdate;
    private boolean firstLoad = true;
    private boolean hasSpawnedBogie = false;
    public double posYFromServer=0;
    private boolean derail = false;

    private int ticksSinceLastVelocityChange=0;

    public Vec3f[] cachedVectors = new Vec3f[]{
            new Vec3f(0,0,0),new Vec3f(0,0,0),new Vec3f(0,0,0),new Vec3f(0,0,0)};

    public EntityRollingStock(World world) {
        super(world);
        initRollingStock(world);
    }

    @Override
    public GameProfile getOwner() {
        return CartTools.getCartOwner(this);
    }

    public EntityRollingStock(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
        if(world==null){return;}
        setPosition(d, d1 + yOffset, d2);
        initRollingStock(world);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
    }

    public void initRollingStock(World world) {
        dataWatcher.addObject(20, 0);//heat
        dataWatcher.addObject(14, 0);
        dataWatcher.addObject(21, 0);

        preventEntitySpawning = true;
        isImmuneToFire = true;
        //field_70499_f = false;

        setSize(0.98F, 1.98F);
        //yOffset = 0;
        //ySize = 0.98F;
        yOffset = 0.65f;

        linkageNumber = 0;

        entityCollisionReduction = 0.8F;

        boundingBoxSmall = AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 0.0D, 1.0D, 1.0D);
        //setBoundingBoxSmall(0.0D, 0.0D, 0.0D, 0.98F, 0.7F);
        setBoundingBoxSmall(0.0D, 0.0D, 0.0D, 1.0F, 1.0F);
        consist = new ArrayList<AbstractTrains>();
        handleOverheating = new HandleOverheating(this);

        collisionHandler=new EntityHitbox(this);
        linkhandler = new LinkHandler(world);
        trainsOnClick = new TrainsOnClick();

        /* Railcraft's stuff */
        //maxSpeed = defaultMaxSpeedRail;
        //maxSpeedGround = defaultMaxSpeedGround;
        maxSpeedAirLateral = defaultMaxSpeedAirLateral;
        maxSpeedAirVertical = defaultMaxSpeedAirVertical;
        dragAir = defaultDragAir;

        /**
         * Trains are always rendered even if out player's sight => no more
         * flickering/disappearing
         */
        if (ConfigHandler.FLICKERING) {
            this.ignoreFrustumCheck = true;
        }
        side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.CLIENT) {
            sndUpdater = new SoundUpdaterRollingStock();
        }

        this.needsBogieUpdate = false;
        setCollisionHandler(null);
        //this.boundingBox.offset(0, 0.5, 0);
    }


    public Entity[] getParts(){
        return collisionHandler==null || collisionHandler.interactionBoxes==null?null:
                collisionHandler.interactionBoxes.toArray(new Entity[]{});
    }

    /**
     * this is basically NBT for entity spawn, to keep data between client and server in sync because some data is not automatically shared.
     */
    @Override
    public void readSpawnData(ByteBuf additionalData) {
        isBraking = additionalData.readBoolean();
        setTrainLockedFromPacket(additionalData.readBoolean());
        if (additionalData.readBoolean()) { // If accepts overlay textures...
            getOverlayTextureContainer().importFromConfigTag(ByteBufUtils.readTag(additionalData));
        }
    }
    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeBoolean(isBraking);
        buffer.writeBoolean(getTrainLockedFromPacket());
        buffer.writeBoolean(acceptsOverlayTextures());
        if (acceptsOverlayTextures()) {
            if (acceptsOverlayTextures()) {
                ByteBufUtils.writeTag(buffer, getOverlayTextureContainer().getOverlayConfigTag());
            }
        }
    }

    public String getTrainName() {
        return dataWatcher.getWatchableObjectString(9);
    }

    public String getTrainType() {
        return dataWatcher.getWatchableObjectString(6);
    }

    @Override
    public String getTrainOwner() {
        return dataWatcher.getWatchableObjectString(7);
    }

    public String getTrainCreator() {
        return dataWatcher.getWatchableObjectString(13);
    }

    public int getIDForServer() {
        return dataWatcher.getWatchableObjectInt(5);
    }

    public int getNumberOfTrainsForServer() {
        return dataWatcher.getWatchableObjectInt(10);
    }

    public int getUniqueTrainIDClient() {
        return dataWatcher.getWatchableObjectInt(11);
    }

    /*
     * @Override public int getID() { return ID; }
     */

    @Override
    public double getMountedYOffset() {
        return 0;
    }

    @Override
    protected void entityInit() {
        if(getWorld()!=null) {
            dataWatcher.addObject(16, (byte) 0);
            dataWatcher.addObject(17, 0);
            dataWatcher.addObject(18, 1);
            dataWatcher.addObject(19, 0.0F);
            dataWatcher.addObject(29, 0.0F);
        }
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity entity) {
        return null;
    }

    @Override
    public boolean canBePushed() {
        return true;
    }

    @Override
    public boolean isLocomotive() {
        return (this instanceof Locomotive);
    }

    @Override
    public boolean isPassenger() {
        return (this instanceof IPassenger);
    }

    @Override
    public boolean isFreightCart() {
        return (this instanceof Freight || this instanceof LiquidTank);
    }

    @Override
    public boolean isFreightOrPassenger() {
        return (this instanceof Freight || this instanceof IPassenger || this instanceof LiquidTank);
    }

    @Override
    public boolean isBuilder() {
        return (this instanceof EntityTracksBuilder);
    }

    @Override
    public boolean isTender() {
        return (this instanceof Tender);
    }

    @Override
    public boolean isWorkCart() {
        return (this instanceof AbstractWorkCart);
    }

    @Override
    public boolean isElectricTrain() {
        return (this instanceof ElectricTrain);
    }

    protected int steamFuelLast(ItemStack it) {
        return FuelHandler.steamFuelLast(it);
    }

    @Override
    public boolean attackEntityFromPart(EntityDragonPart part, DamageSource damagesource, float i) {
        return attackEntityFrom(damagesource,i);
    }

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float i) {
        if (worldObj.isRemote || isDead) {
            return true;
        }
        if (damagesource.getEntity() instanceof EntityPlayer && !damagesource.isProjectile()) {
            if (this instanceof IPassenger) {
                if (canBeDestroyedByPlayer(damagesource)) return false;
            }
            setRollingDirection(-getRollingDirection());
            setRollingAmplitude(10);
            setBeenAttacked();
            if (((EntityPlayer) damagesource.getEntity()).capabilities.isCreativeMode) {
                this.setDamage(1000);
                if (ConfigHandler.ENABLE_WAGON_REMOVAL_NOTICES && ((EntityPlayer) damagesource.getEntity()).canCommandSenderUseCommand(2, "")) {
                    ((EntityPlayer) damagesource.getEntity()).addChatComponentMessage(new ChatComponentText("Operator removed train owned by " + getTrainOwner()));
                }
            }
            setDamage(getDamage() + i * 10);
            if (getDamage() > 40) {
/*                if (riddenByEntity != null) {
                    riddenByEntity.mountEntity(this);
                }*/ //#!#
                ServerLogger.deleteWagon(this);
                /**
                 * Destroy IPassenger since they don't extend Freight or
                 * Locomotive and don't have a proper attackEntityFrom() method
                 */
                if (this instanceof IPassenger) {
                    this.setDead();
                    dropCartAsItem(((EntityPlayer) damagesource.getEntity()).capabilities.isCreativeMode);
                }
            }
        }
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void performHurtAnimation() {
        setRollingDirection(-getRollingDirection());
        setRollingAmplitude(10);
        setDamage(getDamage() + getDamage() * 10);
    }

    public void unLink() {
        if (this.isAttached) {
            if (this.frontLink != null) {
                if (frontLink.Link1 == this.uniqueID) {
                    frontLink.Link1 = 0;
                    frontLink.frontLink = null;
                    if (frontLink.consist != null) frontLink.consist.clear();

                } else if (frontLink.Link2 == this.uniqueID) {
                    frontLink.Link2 = 0;
                    frontLink.backLink = null;
                    if (frontLink.consist != null) frontLink.consist.clear();

                }
            }
            if (this.backLink != null) {
                if (backLink.Link1 == this.uniqueID) {
                    backLink.Link1 = 0;
                    backLink.frontLink = null;
                    if (backLink.consist != null) backLink.consist.clear();

                } else if (backLink.Link2 == this.uniqueID) {
                    backLink.Link2 = 0;
                    backLink.backLink = null;
                    if (backLink.consist != null) backLink.consist.clear();

                }
            }
            this.frontLink = null;
            this.backLink = null;
            this.isAttached = false;
        }
    }

    @Override
    public void setDead() {
        super.setDead();
        this.unLink();
        if (train != null) {
            if (train.getTrains() != null) {
                for (int i2 = 0; i2 < train.getTrains().size(); i2++) {
                    if ((train.getTrains().get(i2)) instanceof Locomotive) {
                        train.getTrains().get(i2).frontLink = null;
                        train.getTrains().get(i2).Link1 = 0;
                        train.getTrains().get(i2).backLink = null;
                        train.getTrains().get(i2).Link2 = 0;
                    }
                    if ((train.getTrains().get(i2)) != this) {
                        if (train != null && train.getTrains() != null && train.getTrains().get(i2) != null && train.getTrains().get(i2).train != null && train.getTrains().get(i2).train.getTrains() != null) train.getTrains().get(i2).train.getTrains().clear();
                    }
                }
            }
        }
        if (train != null && train.getTrains().size() <= 1) {
            train.getTrains().clear();
            allTrains.remove(train);
        }
        if (this.bogieFront != null) {
            bogieFront.setDead();
            bogieFront.isDead = true;
        }
        if (this.bogieBack != null) {
            bogieBack.setDead();
            bogieBack.isDead = true;
        }
        isDead = true;
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.CLIENT) {
            soundUpdater();
        }
        //remove seats
        for (EntitySeat seat : seats) {
            seat.setDead();
            seat.getWorld().removeEntity(seat);
        }

        for(CollisionBox box : collisionHandler.interactionBoxes){
            if(box !=null){
                box.setDead();
                getWorld().removeEntity(box);
            }
        }
    }


    @Override
    public boolean canBeCollidedWith() {
        return !isDead;
    }

    public void pressKey(int i) {
    }


    public float getPlayerScale() {
        return 1f;
    }

    /**
     * gets packet from server and distribute for GUI handles motion
     *
     * @param
     */
    public boolean isLockedAndNotOwner(int player) {
        Entity p = getWorld().getEntityByID(player);
        if(!(p instanceof EntityPlayer)){
            return false;
        }
        if (this.getTrainLockedFromPacket()) {
            return !((EntityPlayer) p).getDisplayName().equalsIgnoreCase(this.getTrainOwner());
        }
        return false;
    }
    public void keyHandlerFromPacket(int i, int player) {
        if (this.getTrainLockedFromPacket()) {
            if (isLockedAndNotOwner(player)) {
                return;
            }
        }
        this.pressKey(i);
        if (i == 7) {
            if (this instanceof AbstractWorkCart) {
                if (this.seats != null && this.seats.size() != 0 && this.seats.get(0).getPassenger() != null) {
                    ((EntityPlayer) this.seats.get(0).getPassenger()).openGui(Traincraft.instance, GuiIDs.CRAFTING_CART, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
                } else {
                    playerEntity.openGui(Traincraft.instance,GuiIDs.CRAFTING_CART, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
                }
            } else if (this.seats != null && this.seats.size() > 1 && this.getInventoryRows() == 0 && riddenByEntity != null && riddenByEntity instanceof EntityPlayer) {
                ((EntityPlayer) riddenByEntity).openGui(Traincraft.instance, GuiIDs.SEAT_GUI, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
            }
        }
        if (i == 9) {
            if (this instanceof AbstractWorkCart) {
                if (this.seats != null && this.seats.size() != 0 && this.seats.get(0).getPassenger() != null) {
                    ((EntityPlayer) this.seats.get(0).getPassenger()).openGui(Traincraft.instance, GuiIDs.FURNACE_CART, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
                } else {
                    playerEntity.openGui(Traincraft.instance,GuiIDs.FURNACE_CART, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
                }
            }
        }


    }

    private void handleTrain() {
        if (this instanceof Locomotive && train != null) {
            for (int i2 = 0; i2 < train.getTrains().size(); i2++) {
                if (RailTools.isCartLockedDown(train.getTrains().get(i2))) {
                    cartLocked = true;
                    /** If something in the train is locked down */
                    ticksSinceHeld = 40;
                    if (!((Locomotive) this).canBeAdjusted) {
                        ((Locomotive) this).setCanBeAdjusted(true);

                    }
                }
                cartLocked = false;
            }
            if (ticksSinceHeld > 0 && !cartLocked) {
                ticksSinceHeld--;
            }
            if (ticksSinceHeld <= 0 && !cartLocked) {
                if (((Locomotive) this).canBeAdjusted && !((Locomotive) this).canBePulled) {
                    ((Locomotive) this).setCanBeAdjusted(false);

                }
            }
        }

        /*
         * if(train!=null && RailTools.isCartLockedDown((EntityMinecart) this)){
         * train.setTicksSinceHeld(100); train.setCartLocked(true); for(int
         * i2=0;i2<train.getTrains().size();i2++){ if(train.getTrains().get(i2)
         * instanceof Locomotive &&
         * !((Locomotive)train.getTrains().get(i2)).canBeAdjusted){
         * ((Locomotive)train.getTrains().get(i2)).setCanBeAdjusted(true);
         * System
         * .out.println(((Locomotive)train.getTrains().get(i2))+"canBeAdjusted=true"
         * ); } } }
         */

        /**
         * if the global train list is empty this is only used when the first @EntityRollingStock
         * is put down or when the world reloads
         */
        if (ticksExisted % 20 != 0) return;
        if (allTrains.isEmpty()) {
            if ((this.frontLink != null || this.backLink != null)) {
                train = new TrainHandler(this);
            }
            /**
             * This is used when global train list isn't empty but this @EntityRollingStock
             * isn't part of a train yet
             */
        } else if (train == null || train.getTrains().isEmpty()) {
            if ((this.frontLink != null || this.backLink != null)) {
                if (this.frontLink != null && frontLink.train != null && frontLink.train.getTrains() != null && !frontLink.train.getTrains().isEmpty()) {
                    train = frontLink.train;
                    return;
                }
                if (this.backLink != null && backLink.train != null && backLink.train.getTrains() != null && !backLink.train.getTrains().isEmpty()) {
                    train = backLink.train;
                    return;
                }

                train = new TrainHandler(this);
            }
        }
        /**
         * getting main locomotive of the train and copying its destination to
         * all attached carts
         */
        if (train != null && train.getTrains().size() > 1) {
            if (this instanceof Locomotive && !((Locomotive) this).canBeAdjusted && !this.getDestination().isEmpty()) {
                for (int i = 0; i < train.getTrains().size(); i++) {
                    if (train.getTrains().get(i) != null && !train.getTrains().get(i).equals(this))
                        train.getTrains().get(i).destination = this.getDestination();
                    CartTools.setCartOwner(train.getTrains().get(i), CartTools.getCartOwner(this));
                }
            }
        }
        /**
         * Resets destination
         */
        else if (!(this instanceof Locomotive)) {
            destination = "";
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     */
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
        this.rollingX = par1;
        this.rollingY = posYFromServer!=0?posYFromServer:par3;
        this.rollingZ = par5;
        this.rollingPitch = par8;
        this.rotationYaw = par7;
        this.rollingturnProgress = par9 + 2;
        this.motionX = this.rollingVelocityX;
        this.motionY = this.rollingVelocityY;
        this.motionZ = this.rollingVelocityZ;
    }

    List list = null;
    Block l;


    @Override
    public void onUpdate() {
        if (addedToChunk && !this.hasSpawnedBogie) {

            if (bogieFront == null) {
                this.bogieShift = this.rotationPoints()[1];
                this.bogieFront = new EntityBogie(worldObj,
                        (posX - Math.cos(this.serverRealRotation * TraincraftUtil.radian) * this.bogieShift),
                        posY + ((Math.tan(this.renderPitch * TraincraftUtil.radian) * -this.bogieShift) - 0.1d),
                        (posZ - Math.sin(this.serverRealRotation * TraincraftUtil.radian) * this.bogieShift), this, this.uniqueID, this.bogieShift);


                if (!worldObj.isRemote) worldObj.spawnEntityInWorld(bogieFront);

                this.bogieBack = new EntityBogie(worldObj,
                        (posX - Math.cos(this.serverRealRotation * TraincraftUtil.radian) * this.rotationPoints()[0]),
                        posY + ((Math.tan(this.renderPitch * TraincraftUtil.radian) * -this.rotationPoints()[0]) - 0.1d),
                        (posZ - Math.sin(this.serverRealRotation * TraincraftUtil.radian) * this.rotationPoints()[0]), this, this.uniqueID, this.rotationPoints()[0]);


                if (!worldObj.isRemote) worldObj.spawnEntityInWorld(bogieBack);
                this.needsBogieUpdate = true;
            }
            this.hasSpawnedBogie = true;
        }

        /**
         * manage chunkloading
         */
        if (!worldObj.isRemote && this.uniqueID == -1) {
            if (FMLCommonHandler.instance().getMinecraftServerInstance() != null) {

                setNewUniqueID(this.getEntityId());
            }
        }
        shouldChunkLoad = getFlag(7);
        if (shouldChunkLoad) {
            if (this.chunkTicket == null) {
                this.requestTicket();
            }
        }

        /**
         * Set the uniqueID if the entity doesn't have one.
         */
        if (!worldObj.isRemote && this.uniqueID == -1) {
            if (FMLCommonHandler.instance().getMinecraftServerInstance() != null) {
                setNewUniqueID(this.getEntityId());
            }
        }
        if(ticksExisted % 18 == 0) { //just so we aren't doing it *every* tick, but still frequent enough to not let the player actually take damage
            if (seats.size() != 0) {
                for (EntitySeat seat : seats) {
                    if (seat.getPassenger() != null) {
                        seat.getPassenger().addPotionEffect(new PotionEffect(Potion.resistance.id, 20, 5, true));
                    }
                }
            }
        }
        if (getRollingAmplitude() > 0) {
            setRollingAmplitude(getRollingAmplitude() - 1);
        }
        if (getDamage() > 0) {
            setDamage(getDamage() - 1);
        }

        isBraking = false;

        if (getRiderOffsets() != null && getRiderOffsets().length > 0 && seats.size() < getRiderOffsets().length) {
            for (int i = 0; i < getRiderOffsets().length; i++) {
                EntitySeat seat = new EntitySeat(getWorld(), posX, posY, posZ, getRiderOffsets()[i][0], getRiderOffsets()[i][1] + 2, getRiderOffsets()[i][2], this, i);
                seats.add(seat);
                if (i == 0) {
                    seats.get(i).setControlSeat();
                }
                getWorld().spawnEntityInWorld(seats.get(i));
            }
        } //dont check for jumping until at least a tick after seats spawned
        else if (seats.size() != 0 && worldObj.isRemote && Traincraft.proxy.getCurrentScreen() == null && seats.get(0).getPassenger() != null) {
            if (TraincraftEntityHelper.getIsJumping(seats.get(0).getPassenger())) isBraking = true;
        }

        int var2;
        if (!this.worldObj.isRemote && this.worldObj instanceof WorldServer) {
            this.worldObj.theProfiler.startSection("portal");
            MinecraftServer var1 = MinecraftServer.getServer();
            var2 = this.getMaxInPortalTime();

            if (this.inPortal) {
                if (var1.getAllowNether()) {
                    if (this.ridingEntity == null && this.portalCounter++ >= var2) {
                        this.portalCounter = var2;
                        this.timeUntilPortal = this.getPortalCooldown();
                        byte var3;

                        if (this.worldObj.provider.dimensionId == -1) {
                            var3 = 0;
                        } else {
                            var3 = -1;
                        }

                        this.travelToDimension(var3);
                    }

                    this.inPortal = false;
                }
            } else {
                if (this.portalCounter > 0) {
                    this.portalCounter -= 4;
                }

                if (this.portalCounter < 0) {
                    this.portalCounter = 0;
                }
            }

            if (this.timeUntilPortal > 0) {
                --this.timeUntilPortal;
            }

            this.worldObj.theProfiler.endSection();
        }

        if (Traincraft.proxy.isClient()) {
            soundUpdater();
        }

        if (worldObj.isRemote) {
            //rotationYaw = (float) rotationYawClient;
            if (rollingturnProgress > 0) {
                rotationYaw = (float) rotationYawClient;
                this.rotationPitch = (float) (this.rotationPitch + (this.rollingPitch - this.rotationPitch) / this.rollingturnProgress);

                this.setPosition(this.posX + (this.rollingX - this.posX) / (double)this.rollingturnProgress,
                        this.posY + (this.rollingY - this.posY) / (double)this.rollingturnProgress,
                        this.posZ + (this.rollingZ - this.posZ) / (double)this.rollingturnProgress);
                --this.rollingturnProgress;
                this.setRotation(this.rotationYaw, this.rotationPitch);

            } else {
                setPosition(posX, posY, posZ);
                setRotation(rotationYaw, rotationPitch);

            }

            collisionHandler.position(posX, posY, posZ, rotationPitch, getYaw());
            collisionHandler.updateCollidingEntities(this);
            collisionHandler.manageCollision();
            positionSeats();
            return;
        }
        /**
         * As entities can't be registered in nbttagcompound I had to setup this
         * system... When world loads, only the (double) Link1 and Link2 are
         * known. This method search for the entity with the ID corresponding to
         * Link1 or Link2 When it finds it, (EntityRollingStock)frontLink and
         * backLink will be updated accordingly
         */
        if (addedToChunk && ((this.frontLink == null && this.Link1 != 0) || (this.backLink == null && this.Link2 != 0))) {
            list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(15, 15, 15));

            if (list != null && list.size() > 0) {
                for (Object entity : list) {
                    if (entity instanceof EntityRollingStock) {
                        if (((EntityRollingStock) entity).uniqueID == this.Link1) {
                            this.frontLink = (EntityRollingStock) entity;
                        } else if (((EntityRollingStock) entity).uniqueID == this.Link2) {
                            this.backLink = (EntityRollingStock) entity;
                        }
                    }
                }
            }
        }

        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        int floor_posX = MathHelper.floor_double(posX);
        int floor_posY = MathHelper.floor_double(posY);
        int floor_posZ = MathHelper.floor_double(posZ);

        if (needsBogieUpdate) {
            if (bogieFront != null) {
                float rotationCos1 = (float) Math.cos(this.serverRealRotation * TraincraftUtil.radian);
                float rotationSin1 = (float) Math.sin(this.serverRealRotation * TraincraftUtil.radian);
                //TODO: this should be removed?
                if (!firstLoad) {
                    rotationCos1 = (float) Math.cos((this.serverRealRotation + 90) * TraincraftUtil.radian);
                    rotationSin1 = (float) Math.sin((this.serverRealRotation + 90) * TraincraftUtil.radian);
                }
                double bogieX1 = (this.posX + (rotationCos1 * (rotationPoints()[0])));
                double bogieZ1 = (this.posZ + (rotationSin1 *(rotationPoints()[0])));
                this.bogieFront.setPosition(bogieX1, bogieFront.posY, bogieZ1);

            }
            if (bogieBack != null) {
                float rotationCos1 = (float) Math.cos(this.serverRealRotation * TraincraftUtil.radian);
                float rotationSin1 = (float) Math.sin(this.serverRealRotation * TraincraftUtil.radian);
                if (!firstLoad) {
                    rotationCos1 = (float) Math.cos((this.serverRealRotation + 90) * TraincraftUtil.radian);
                    rotationSin1 = (float) Math.sin((this.serverRealRotation + 90) * TraincraftUtil.radian);
                }
                double bogieX1 = (this.posX + (rotationCos1 * rotationPoints()[1]));
                double bogieZ1 = (this.posZ + (rotationSin1 * rotationPoints()[1]));
                this.bogieBack.setPosition(bogieX1, bogieBack.posY, bogieZ1);

            }
            firstLoad = false;

            needsBogieUpdate = false;
        }

        if (worldObj.isAirBlock(floor_posX, floor_posY, floor_posZ)) {
            floor_posY--;
        } else if (isRailBlockAt(worldObj, floor_posX, floor_posY + 1, floor_posZ) || worldObj.getBlock(floor_posX, floor_posY + 1, floor_posZ) == BlockIDs.tcRail.block || worldObj.getBlock(floor_posX, floor_posY + 1, floor_posZ) == BlockIDs.tcRailGag.block) {
            floor_posY++;
        }

        l = worldObj.getBlock(floor_posX, floor_posY, floor_posZ);

        updatePosition();

        d6 = prevPosX - posX;
        d7 = prevPosZ - posZ;
        prevRotationYaw = rotationYaw;

        //this.rotationPitch = 0.0F;

        if (d6 * d6 + d7 * d7 > 0.0001D) {
            this.rotationYaw = (float) (Math.atan2(d7, d6) * 180.0D / Math.PI);
            if (this.isClientInReverse) {
                this.rotationYaw += 180.0F;
            }
        }


        //double var49 = MathHelper.wrapAngleTo180_float(this.rotationYaw - this.prevRotationYaw);

        float anglePitch = 0;
        if (bogieFront != null) {

            serverRealRotation = MathHelper.wrapAngleTo180_float((float) Math.toDegrees(Math.atan2((float) (bogieFront.posZ - this.posZ), (float) (bogieFront.posX - this.posX))) - 90F);

            anglePitch = (float) Math.atan(((bogieFront.posY - posY)) /
                    MathHelper.sqrt_double(((bogieFront.posX - posX) * (bogieFront.posX - posX)) +
                            ((bogieFront.posZ - posZ) * (bogieFront.posZ - posZ))));//1.043749988079071
            serverRealPitch = anglePitch + (float)
                    ((bogieFront.posZ - posZ) * (bogieFront.posZ - posZ));//1.043749988079071
        }


        if (ticksExisted % 2 == 0) {
            Traincraft.rotationChannel.sendToAllAround(new PacketRollingStockRotation(this, (int) (anglePitch * 60)), new TargetPoint(worldObj.provider.dimensionId, posX, posY, posZ, 300.0D));
        }
        if (!worldObj.isRemote) {
            anglePitchClient = (anglePitch * 60);
        }

        //this.setRotation(this.rotationYaw, this.rotationPitch);

        handleTrain();
        handleOverheating.HandleHeatLevel(this);
        linkhandler.handleStake(this);
        this.func_145775_I();
        MinecraftForge.EVENT_BUS.post(new MinecartUpdateEvent(this, floor_posX, floor_posY, floor_posZ));
        //setBoundingBoxSmall(posX, posY, posZ, 0.98F, 0.7F);
        numLaps++;
        if ((this instanceof Locomotive) && (this.Link1 == 0) && (this.Link2 == 0) && numLaps > 700) {
            this.consist.clear();
        }


        //update the collision handler's positions
        collisionHandler.position(posX, posY, posZ, rotationPitch, getYaw());
        collisionHandler.updateCollidingEntities(this);
        collisionHandler.manageCollision();
        for (EntitySeat seat: seats) { //handle died in train
            if (seat.getPassenger() != null && (seat.getPassenger().isDead || seat != seat.getPassenger().ridingEntity)) {
                seat.getPassenger().ridingEntity = null;
                seat.removePassenger(seat.getPassenger());
            }
        }
        this.dataWatcher.updateObject(14, (int) (motionX * 100));
        this.dataWatcher.updateObject(21, (int) (motionZ * 100));
        positionSeats();
        if (ConfigHandler.ENABLE_LOGGING && !worldObj.isRemote && ticksExisted % 120 == 0) {
            ServerLogger.writeWagonToFolder(this);
        }
        if(!getWorld().isRemote) {
            dataWatcher.updateObject(29, getVelocity());
        }
    }

    private void positionSeats(){
        //rider updating isn't called if there's no driver/conductor, so just in case of that, we reposition the seats here too.
        if (getRiderOffsets() != null) {
            for (int i1 = 0; i1 < seats.size(); i1++) {
                //sometimes seats die when players log out. make new ones.
                if(seats.get(i1) ==null){
                    seats.set(i1, new EntitySeat(getWorld(), posX, posY,posZ,0,0,0, this,i1));
                    if(i1==0){
                        seats.get(i1).setControlSeat();
                    }
                    getWorld().spawnEntityInWorld(seats.get(i1));
                }
                cachedVectors[0] = new Vec3f(getRiderOffsets()[i1][0], getRiderOffsets()[i1][1], getRiderOffsets()[i1][2])
                        .rotatePoint(rotationPitch, serverRealRotation, 0f);
                cachedVectors[0].addVector(posX,posY,posZ);
                seats.get(i1).setPosition(cachedVectors[0].xCoord, cachedVectors[0].yCoord, cachedVectors[0].zCoord);
            }
        }
    }

    public void updatePosition(){

        //reposition bogies to be sure they are the right distance
        if(!getWorld().isRemote) {

            //do scaled rail boosting but keep it capped to the max velocity of the rail
            Block b = CommonUtil.getBlockAt(getWorld(),posX,posY,posZ);
            if (b instanceof BlockRailBase){
                derail= false;


                if (b == Blocks.golden_rail) {
                    if ((((BlockRailBase) b).isPowered()) &&
                            //this part keeps it capped
                            getVelocity() < maxBoost(b)) {
                        float boost = CommonUtil.getMaxRailSpeed(getWorld(), (BlockRailBase) b, this, posX, posY, posZ) * 0.005f;
                        appendMovement(Math.copySign(cachedVectors[2].yCoord,boost));
                    }
                }
            } else {
                //set the derail state based on whether or not there's a valid rail block below.
                //later this will add more inherent support for 3rd party mods like ZnD, right now it's just vanilla/RC/TiM
                derail= !CommonUtil.isTrack(getWorld(),posX,posY,posZ);
            }


            //handle yaw changes for derail
            if(derail) {
                if(frontLink!=null && backLink!=null &&
                        frontLink instanceof EntityRollingStock &&
                        backLink instanceof EntityRollingStock){
                    rotationYaw=CommonUtil.atan2degreesf(
                            frontLink.posZ - backLink.posZ,
                            frontLink.posX - backLink.posX);
                } else if (frontLink!=null && frontLink instanceof EntityRollingStock){
                    rotationYaw=CommonUtil.atan2degreesf(
                            frontLink.posZ - posZ,
                            frontLink.posX - posX);
                } else if (backLink!=null && backLink instanceof EntityRollingStock){
                    rotationYaw=CommonUtil.atan2degreesf(
                            posZ - backLink.posZ,
                            posX - backLink.posX);
                }
            }


            //actually move
            finalMove();
            //only update velocity if we've moved to any significance.
            if(Math.abs(posX-prevPosX)>0.0625 || Math.abs(posZ-prevPosZ)>0.0625) {
                motionX = (posX - prevPosX)/ticksSinceLastVelocityChange;
                motionZ = (posZ - prevPosZ)/ticksSinceLastVelocityChange;
                prevPosX = posX;
                prevPosZ = posZ;

                ticksSinceLastVelocityChange=1;
            } else {
                motionX = (posX - prevPosX)/ticksSinceLastVelocityChange;
                motionZ = (posZ - prevPosZ)/ticksSinceLastVelocityChange;
                ticksSinceLastVelocityChange++;
            }
        }
    }

    public void appendMovement(double velocity){

        //the logic gets stupid if it's not sorted from one end or another.
        //todo: this is a trash fix, it would be better for the list to be reliably sorted
        if(frontLink!=null && backLink!=null){
            return;
        }
        EntityRollingStock last = this;
        for(AbstractTrains t:consist) {
            if(t.backLink!=null && last.backLink!=null
                    && last==t.backLink
                    && t==last.backLink){
                t.bogieBack.addVelocity(t, -velocity);
                t.bogieFront.addVelocity(t, -velocity);
            } else if(t.frontLink!=null && last.frontLink!=null
                    && last==t.frontLink
                    && t==last.frontLink){
                t.bogieBack.addVelocity(t, -velocity);
                t.bogieFront.addVelocity(t, -velocity);
            } else {
                t.bogieBack.addVelocity(t, velocity);
                t.bogieFront.addVelocity(t, velocity);
            }
        }
    }

    public void manageLink(AbstractTrains other) {
        if(other.bogieBack ==null || other.bogieFront ==null || bogieBack ==null || bogieFront ==null) {
            return;
        }

        double vecX = other.posX - posX;
        double vecZ = other.posZ - posZ;


        double springDist = MathHelper.sqrt_double(vecX * vecX + vecZ * vecZ)
                -(getLinkageDistance(other)+other.getLinkageDistance(this));
        springDist*=0.1;

        if(getVelocity()>0.03) {
            springDist *= 0.045;
        } else if (getVelocity()<0.01){
            springDist*=0.01;
        } else {
            springDist*=0.03;
        }
        if(backLink!=null && other.getEntityId() == backLink.getEntityId()) {
            springDist *= -1;
        }

        if(Math.abs(springDist)>0.01) {
            addLinkingMove(springDist);
        }
    }

    public void addLinkingMove(double velocity){
        bogieBack.addLinking(this, velocity);
        bogieFront.addLinking(this, velocity);
    }

    /**
     * if X or Z is null, the bogie's existing motion velocity will be used
     */
    public void finalMove(){
        cachedVectors[1] = new Vec3f(rotationPoints()[1], 0, 0).rotatePoint(0, rotationYaw, 0)
                .addVector(bogieBack.posX,bogieBack.posY,bogieBack.posZ);
        setPosition(cachedVectors[1].xCoord, cachedVectors[1].yCoord,cachedVectors[1].zCoord);

        bogieFront.moveBogie();
        bogieBack.moveBogie();
        //reset the y coord so they will re-calculate the yaw
        if(hasDrag()) {
            applyDrag();
        }
        //update positions related to linking, this NEEDS to come after drag
        if(frontLink!=null){
            manageLink(frontLink);
        }
        if(backLink!=null){
            manageLink(backLink);
        }
        cachedVectors[2].yCoord=0;
        //update rotation
        setRotation((CommonUtil.atan2degreesf(
                bogieBack.posZ - bogieFront.posZ,
                bogieBack.posX - bogieFront.posX)),
                CommonUtil.calculatePitch(bogieFront.posY + bogieFront.yOffset, bogieBack.posY + bogieBack.yOffset, Math.abs(rotationPoints()[0]) + Math.abs(rotationPoints()[1])));

        //reset the vector when we're done so it wont break trains.
        cachedVectors[1]= new Vec3f(0,0,0);
        //update the collision handler's positions
        if(collisionHandler==null) {
            collisionHandler = new EntityHitbox(this);
            collisionHandler.position(posX, posY, posZ, rotationPitch, rotationYaw);
        } else {
            collisionHandler.position(posX, posY, posZ, rotationPitch, rotationYaw);
        }
    }

    @Override
    public void applyDrag() {
        //this may need tweaking, it's essentially just a copy of what the minecart does.
        if (this.riddenByEntity != null) {
            multiplyVelocity(0.996999979019165D);
        } else {
            multiplyVelocity(0.9599999785423279D);
        }
    }


    public boolean hasDrag(){
        for(AbstractTrains t:consist){
            if(t instanceof Locomotive){
                if(((Locomotive) t).backwardPressed || ((Locomotive) t).forwardPressed) {
                    return false;
                }
            }
        }
        return true;
    }

    public float getVelocity(){
        return getWorld().isRemote?dataWatcher.getWatchableObjectFloat(29):
                (float)(Math.abs(motionX)+Math.abs(motionZ));
    }
    double maxBoost(Block booster){
        if(this.transportTopSpeed()>0){
            return Math.min(transportTopSpeed(),
                    CommonUtil.getMaxRailSpeed(getWorld(), (BlockRailBase) booster,this, posX,posY,posZ));
        }
        return CommonUtil.getMaxRailSpeed(getWorld(), (BlockRailBase) booster,this, posX,posY,posZ);
    }
    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setDouble("speedLimiter", this.speedLimiter);
        nbttagcompound.setFloat("serverRealRotation", this.serverRealRotation);
        nbttagcompound.setFloat("yawRotation", this.rotationYaw);
        nbttagcompound.setBoolean("firstLoad", this.firstLoad);
        nbttagcompound.setFloat("rotation", this.rotation);
        nbttagcompound.setBoolean("brake", isBraking);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        this.speedLimiter = nbttagcompound.getDouble("speedLimiter");
        this.serverRealRotation = nbttagcompound.getFloat("serverRealRotation");

        if (nbttagcompound.hasKey("yawRotation")) {
            rotationYaw = nbttagcompound.getFloat("yawRotation");
        }
        this.firstLoad = nbttagcompound.getBoolean("firstLoad");
        this.rotation = nbttagcompound.getFloat("rotation");
        this.isBraking = nbttagcompound.getBoolean("brake");
    }

    @Override
    public boolean interactFirst(EntityPlayer entityplayer) {
        if (super.interactFirst(entityplayer)) {
            return true;
        }
        if (entityplayer.ridingEntity instanceof EntitySeat) {
            return false;
        }

        playerEntity = entityplayer;
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();

        if (this.getTrainLockedFromPacket()) {
            if (!playerEntity.getDisplayName().toLowerCase().equals(this.trainOwner.toLowerCase()) && !canBeRiddenWhileLocked(this)) {
                if (!worldObj.isRemote) entityplayer.addChatMessage(new ChatComponentText("Train is locked"));
                return true;
            } else if (!playerEntity.getDisplayName().toLowerCase().equals(this.trainOwner.toLowerCase()) && entityplayer.inventory.getCurrentItem() != null && entityplayer.inventory.getCurrentItem().getItem() instanceof ItemDye && (this instanceof Locomotive)) {
                if (!worldObj.isRemote) entityplayer.addChatMessage(new ChatComponentText("Train is locked"));
                return true;
            }

        }

        if (itemstack != null && itemstack.getItem() instanceof ItemWrench && this instanceof Locomotive && entityplayer.isSneaking() && !worldObj.isRemote) {
            destination = "";
            entityplayer.addChatMessage(new ChatComponentText("Destination reset"));
            return true;
        }
        if(itemstack != null) {
            ItemStack crowbar = GameRegistry.findItemStack("railcraft", "tool.crowbar", 1);
            ItemStack crowbar1 = GameRegistry.findItemStack("railcraft", "tool.crowbar.reinforced", 1);
            if (itemstack == crowbar || itemstack == crowbar1) {
                return false;
            }
            if (itemstack.hasTagCompound() && getTicketDestination(itemstack) != null && getTicketDestination(itemstack).length() > 0) {
                this.setDestination(itemstack);
                /**
                 * ticket are single use but golden ones are multiple uses
                 */
                ItemStack ticket = GameRegistry.findItemStack("Railcraft", "railcraft.routing.ticket", 1);
                if (ticket != null && ticket.getItem() != null && itemstack.getItem() == ticket.getItem()) {
                    if (--itemstack.stackSize == 0) {
                        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, null);
                    }
                }
                return true;
            }
            /**
             * If the color is valid for the cart, then change it and reduce
             * itemstack size
             */
            if (itemstack.getItem() instanceof ItemDye) {
                if (SkinRegistry.get(this).size() > 0) {
                    for (int i = 0; i < SkinRegistry.get(this).size(); i++) {
                        if (itemstack.getItemDamage() == DepreciatedUtil.getColorFromString(SkinRegistry.get(this).get(i))) {
                            this.setColor(SkinRegistry.get(this).get(i));
                            itemstack.stackSize--;

                            //if (!worldObj.isRemote)PacketHandler.sendPacketToClients(PacketHandler.sendStatsToServer(10,this.uniqueID,trainName ,trainType, this.trainOwner, this.getColorAsString(itemstack.getItemDamage()), (int)posX, (int)posY, (int)posZ),this.worldObj, (int)posX,(int)posY,(int)posZ, 12.0D);

                            return true;
                        }
                    }
                    if (worldObj.isRemote && ConfigHandler.SHOW_POSSIBLE_COLORS) {
                        String concatColors = ": ";
                        for (int t = 0; t < SkinRegistry.get(this).size(); t++) {
                            concatColors = concatColors.concat(SkinRegistry.get(this).get(t) + ", ");
                        }
                        entityplayer.addChatMessage(new ChatComponentText("Possible colors" + concatColors));
                        entityplayer.addChatMessage(new ChatComponentText("To paint, click me with the right dye"));
                        return true;
                    }
                } else if (SkinRegistry.get(this) != null || SkinRegistry.get(this).size() == 0) {
                    entityplayer.addChatMessage(new ChatComponentText("No other colors available"));
                }
            }
            if ((trainsOnClick.onClickWithStake(this, itemstack, playerEntity, worldObj))) {
                return true;
            }

            if (itemstack.getItem() instanceof ItemPaintbrushThing && !entityplayer.isSneaking()) {
                if (SkinRegistry.get(this).size() > 0) {
                    entityplayer.openGui(Traincraft.instance, GuiIDs.PAINTBRUSH, entityplayer.getEntityWorld(), this.getEntityId(), -1, (int) this.posZ);
                }

                if (SkinRegistry.get(this).size() == 0) {
                    entityplayer.addChatMessage(new ChatComponentText("There are no other colors available."));
                }
                return true;
            } else if (itemstack.getItem() instanceof ItemPaintbrushThing && entityplayer.isSneaking()) {
                for (int i = 0; i < SkinRegistry.get(this).size(); i++) {
                    if (this.getColor().equals(SkinRegistry.get(this).get(i))) {
                        if (SkinRegistry.get(this).size() > i + 1) {
                            setColor(i + 1);
                        } else {
                            setColor(0);
                        }
                        return true;
                    }
                }
            }
        }


        //be sure the player has permission to enter the transport, and that the transport has the main seat open.
        if (getRiderOffsets() != null && getPermissions(playerEntity, false, true) && !entityplayer.isSneaking()) {
            for (EntitySeat seat : seats) {
                //1.12 is stupid, sometimes when the passenger is null, it returns the player
                if (!getWorld().isRemote && (seat.getPassenger() == null
                        || seat.getPassenger().getEntityId()==playerEntity.getEntityId())) {
                    seat.addPassenger(playerEntity);
                    entityplayer.mountEntity(seat);
                    return true;
                }
            }
        }

        if (MinecraftForge.EVENT_BUS.post(new MinecartInteractEvent(this, entityplayer))) {
            return true;
        }

        return worldObj.isRemote;
    }

    @SideOnly(Side.CLIENT)
    private void soundUpdater() {
        if(ticksExisted>0) {
            if (FMLClientHandler.instance().getClient() != null) {
                this.theSoundManager = FMLClientHandler.instance().getClient().getSoundHandler();
            }
            if (FMLClientHandler.instance().getClient() != null && this.theSoundManager != null && FMLClientHandler.instance().getClient().thePlayer != null) {
                if (sndUpdater != null) {
                    sndUpdater.update(FMLClientHandler.instance().getClient().getSoundHandler(), this, FMLClientHandler.instance().getClient().thePlayer);
                }
            }
        }
    }

    /**
     * Applies a velocity to each of the entities pushing them away from each
     * other. Args: entity
     */
    @Override
    public void applyEntityCollision(Entity par1Entity) {}

    public void multiplyVelocity(double vel){
        this.motionX *= vel;
        this.motionZ *= vel;
        this.isAirBorne = true;
        if(bogieFront!=null){
            bogieFront.motionX *= vel;
            bogieFront.motionZ *= vel;
        }
        if(bogieBack!=null){
            bogieBack.motionX *= vel;
            bogieBack.motionZ *= vel;
        }
    }

    @Override
    public void setVelocity(double p_70024_1_, double p_70024_3_, double p_70024_5_) {
        this.motionX = p_70024_1_;
        this.motionY = p_70024_3_;
        this.motionZ = p_70024_5_;
        this.isAirBorne = true;
        if(bogieFront!=null){
            bogieFront.motionX = p_70024_1_;
            bogieFront.motionY = p_70024_3_;
            bogieFront.motionZ = p_70024_5_;
        }
        if(bogieBack!=null){
            bogieBack.motionX = p_70024_1_;
            bogieBack.motionY = p_70024_3_;
            bogieBack.motionZ = p_70024_5_;
        }
    }

    @Override
    public void addVelocity(double p_70024_1_, double p_70024_3_, double p_70024_5_) {
        this.motionX += p_70024_1_;
        this.motionY += p_70024_3_;
        this.motionZ += p_70024_5_;
        this.isAirBorne = true;
        if(bogieFront!=null){
            bogieFront.motionX += p_70024_1_;
            bogieFront.motionY += p_70024_3_;
            bogieFront.motionZ += p_70024_5_;
        }
        if(bogieBack!=null){
            bogieBack.motionX += p_70024_1_;
            bogieBack.motionY += p_70024_3_;
            bogieBack.motionZ += p_70024_5_;
        }
    }

    /**
     * To disable linking altogether, return false here.
     *
     * @return True if this cart is linkable.
     */
    @Override
    public boolean isLinkable() {
        return true;
    }

    /**
     * Check called when attempting to link carts.
     *
     * @param cart The cart that we are attempting to link with.
     * @return True if we can link with this cart.
     */
    @Override
    public boolean canLinkWithCart(EntityMinecart cart) {
        return true;
    }

    /**
     * Returns true if this cart has two links or false if it can only link with
     * one cart.
     *
     * @return True if two links
     */
    @Override
    public boolean hasTwoLinks() {
        return true;
    }

    /**
     * Gets the distance at which this cart can be linked. This is called on
     * both carts and added together to determine how close two carts need to be
     * for a successful link. Default = LinkageManager.LINKAGE_DISTANCE
     *
     * @param cart The cart that you are attempting to link with.
     * @return The linkage distance
     */
    @Override
    public float getLinkageDistance(EntityMinecart cart) {
        return this.getOptimalDistance(cart);
    }

    /**
     * Gets the optimal distance between linked carts. This is called on both
     * carts and added together to determine the optimal rest distance between
     * linked carts. The LinkageManager will attempt to maintain this distance
     * between linked carts at all times. Default =
     * LinkageManager.OPTIMAL_DISTANCE
     * ETERNAL's NOTE: because this is forcing the value of EntityMinecart, it's actually a call to the super but using this instance. Not actually an infinate look like compiler thinks.
     *
     * @param cart The cart that you are linked with.
     * @return The optimal rest distance
     */
    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return getHitboxSize()[0];
    }

    /**
     * Return false if linked carts have no effect on the velocity of this cart.
     * Use carefully, if you link two carts that can't be adjusted, it will
     * behave as if they are not linked.
     *
     * @param cart The cart doing the adjusting.
     * @return Whether the cart can have its velocity adjusted.
     */
    @Override
    public boolean canBeAdjusted(EntityMinecart cart) {
        return true;
    }

    @Override
    public void onLinkCreated(EntityMinecart cart) {
        linked = true;
    }

    /**
     * Called when a link is broken (usually).
     *
     * @param cart The cart we were linked with.
     */
    @Override
    public void onLinkBroken(EntityMinecart cart) {
        linked = false;
    }

    @Override
    public boolean isLinked() {
        return linked;
    }

    /**
     * Returns true if this cart is self propelled.
     *
     * @return True if powered.
     */
    @Override
    public boolean isPoweredCart() {
        return (isLocomotive());
    }

    /**
     * Returns true if this cart is a storage cart Some carts may have
     * inventories but not be storage carts and some carts without inventories
     * may be storage carts.
     *
     * @return True if this cart should be classified as a storage cart.
     */
    public boolean isStorageCart() {
        return (isFreightCart());
    }

    /**
     * Returns true if this cart can be ridden by an Entity.
     *
     * @return True if this cart can be ridden.
     */
    @Override
    public boolean canBeRidden() {
        return ((isLocomotive() || isPassenger() || isWorkCart()));
    }

    /**
     * Returns true if this cart can currently use rails. This function is
     * mainly used to gracefully detach a minecart from a rail.
     *
     * @return True if the minecart can use rails.
     */
    @Override
    public boolean canUseRail() {
        return canUseRail;
    }

    /**
     * Set whether the minecart can use rails. This function is mainly used to
     * gracefully detach a minecart from a rail.
     *
     * @param use Whether the minecart can currently use rails.
     */
    @Override
    public void setCanUseRail(boolean use) {
        canUseRail = use;
    }

    /**
     * Return false if this cart should not call IRail.onMinecartPass() and
     * should ignore Powered Rails.
     *
     * @return True if this cart should call IRail.onMinecartPass().
     */
    @Override
    public boolean shouldDoRailFunctions() {
        return true;
    }

    protected void applyDragAndPushForces() {
        motionX *= getDragAir();
        motionY *= 0.0D;
        motionZ *= getDragAir();
    }

    /**
     * Carts should return their drag factor here
     *
     * @return The drag rate.
     */
    @Override
    public double getDragAir() {
        return 0.9998D;
    }

    @Override
    public void moveMinecartOnRail(int i, int j, int k, double d) {
        Block id = worldObj.getBlock(i, j, k);
        if (!BlockRailBase.func_150051_a(id)) {
            return;
        }
        railMaxSpeed = ((BlockRailBase) id).getRailMaxSpeed(worldObj, this, i, j, k);
        maxSpeed = Math.max(railMaxSpeed, getMaxCartSpeedOnRail());
        maxSpeed = SpeedHandler.handleSpeed(railMaxSpeed, maxSpeed, this);
        if (this.speedLimiter != 0 && speedWasSet) {
            //maxSpeed *= this.speedLimiter;
            adjustSpeed(maxSpeed, speedLimiter);
        }
        if ((!isLocomotive())) {
            motionX *= 0.99D;
            motionZ *= 0.99D;
        } else {
            motionX *= 0.9D;
            motionZ *= 0.9D;
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

    public void adjustSpeed(float maxSpeed, double limiter) {
        float targetSpeed = (float) (maxSpeed * limiter);
        float targetSpeedX = (float) Math.copySign(targetSpeed, motionX);
        float targetSpeedZ = (float) Math.copySign(targetSpeed, motionZ);
        if (motionX > targetSpeedX && motionX != 0) motionX -= 0.01;
        if (motionZ > targetSpeedZ && motionZ != 0) motionZ -= 0.01;
        if (motionX < targetSpeedX && motionX != 0) motionX += 0.01;
        if (motionZ < targetSpeedZ && motionZ != 0) motionZ += 0.01;
        if ((Math.abs(motionX) < Math.abs(targetSpeedX) + 0.01) && (Math.abs(motionX) > Math.abs(targetSpeedX) - 0.01)) {
            speedWasSet = false;
        }
        if ((Math.abs(motionZ) < Math.abs(targetSpeedZ) + 0.01) && (Math.abs(motionZ) > Math.abs(targetSpeedZ) - 0.01)) {
            speedWasSet = false;
        }
    }


    protected void adjustSlopeVelocities(int i1) {
        if (this instanceof Locomotive) {
            return;
        }
        double d4 = -0.002D;//0.0078125D
        if (i1 == 2) {
            motionX -= d4;
        } else if (i1 == 3) {
            motionX += d4;
        } else if (i1 == 4) {
            motionZ += d4;
        } else if (i1 == 5) {
            motionZ -= d4;
        }
    }



    /**
     * Returns the carts max speed. Carts going faster than 1.1 cause issues
     * with chunk loading. This value is compared with the rails max speed to determine
     * the carts current max speed. A normal rails max speed is 0.4.
     *
     * @return Carts max speed.
     */
    @Override
    public float getMaxCartSpeedOnRail() {
        return maxSpeed;
    }

    @Override
    public float getMaxSpeedAirLateral() {
        return maxSpeedAirLateral;
    }

    @Override
    public void setMaxSpeedAirLateral(float value) {
        maxSpeedAirLateral = value;
    }

    @Override
    public float getMaxSpeedAirVertical() {
        return maxSpeedAirVertical;
    }

    @Override
    public void setMaxSpeedAirVertical(float value) {
        maxSpeedAirVertical = value;
    }

    @Override
    public void setDragAir(double value) {
        dragAir = value;
    }

    @Override
    public boolean canOverheat() {
        return false;
    }

    @Override
    public int getOverheatTime() {
        return 0;
    }

    /**
     * returns the middle of the overheat bar in the HUD
     */
    public int getAverageOverheat() {
        return (this.getOverheatTime() + 30) / 2;
    }

    /**
     * client-server communication
     */
    public void setOverheatLevel(int overheatLevel) {
        this.overheatLevel = overheatLevel;
        this.dataWatcher.updateObject(20, overheatLevel);
    }

    /**
     * client-server communication
     */
    public int getOverheatLevel() {
        return (this.dataWatcher.getWatchableObjectInt(20));
    }

    /**
     * @see SpeedHandler description in SpeedHandler
     */
    public double convertSpeed(Locomotive entity) {
        double speed = entity.getCustomSpeed();// speed in m/s
        if (ConfigHandler.REAL_TRAIN_SPEED) {
            speed /= 2;// applying ratio
        } else {
            speed /= 6;
        }
        speed /= 10;
        return speed;
    }

    /**
     * Used in SoundUpdaterRollingStock
     */
    public int getMotionXClient() {
        return (this.dataWatcher.getWatchableObjectInt(14));
    }

    /**
     * Used in SoundUpdaterRollingStock
     */
    public int getMotionZClient() {
        return (this.dataWatcher.getWatchableObjectInt(21));
    }

    @Override
    protected void func_145775_I() {
        int var1 = MathHelper.floor_double(this.boundingBoxSmall.minX + 0.001D);
        int var2 = MathHelper.floor_double(this.boundingBoxSmall.minY + 0.001D);
        int var3 = MathHelper.floor_double(this.boundingBoxSmall.minZ + 0.001D);
        int var4 = MathHelper.floor_double(this.boundingBoxSmall.maxX - 0.001D);
        int var5 = MathHelper.floor_double(this.boundingBoxSmall.maxY - 0.001D);
        int var6 = MathHelper.floor_double(this.boundingBoxSmall.maxZ - 0.001D);

        if (this.worldObj.checkChunksExist(var1, var2, var3, var4, var5, var6)) {
            for (int var7 = var1; var7 <= var4; ++var7) {
                for (int var8 = var2; var8 <= var5; ++var8) {
                    for (int var9 = var3; var9 <= var6; ++var9) {
                        Block var10 = this.worldObj.getBlock(var7, var8, var9);

                        if (var10 != null) {
                            var10.onEntityCollidedWithBlock(this.worldObj, var7, var8, var9, this);
                        }
                    }
                }
            }
        }
    }

    private void setBoundingBoxSmall(double par1, double par3, double par5, float width, float height) {
        float var7 = width * 0.5F;
        this.boundingBoxSmall.setBounds(par1 - var7, par3, par5 - var7, par1 + var7, par3 + height, par5 + var7);
    }

    public float getYaw() {
        return getWorld().isRemote?rotationYawClientReal:this.rotationYaw+90;
    }

    public float getPitch() {
        return this.rotationPitch;
    }

    @Override
    public int getMinecartType() {
        return 0;
    }

    @Override
    public List<ItemStack> getItemsDropped() {
        List<ItemStack> items = new ArrayList<ItemStack>();
        TrainRecord train = Traincraft.instance.traincraftRegistry.getTrainRecord(this.getClass());
        if (train != null) {
            items.add(ItemRollingStock.setPersistentData(new ItemStack(getItem()), this, this.getUniqueTrainID(), trainCreator, trainOwner, getColor()));
            return items;
        }
        return null;
    }


    public ItemStack[] getInventory() {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public void setSeats(EntitySeat seat, int seatNumber){
        if (seats.size() <= seatNumber) {
            seats.add(seat);
        } else {
            seats.set(seatNumber, seat);
        }
    }

    public boolean shouldRiderSit(int seat){
        return this.shouldRiderSit();
    }
    @Override
    public boolean shouldRiderSit(){
        return true;
    }

    /**
     * <h2>Permissions handler</h2>
     * Used to check if the player has permission to do whatever it is the player is trying to do. Yes I could be more vague with that.
     *
     * @param player the player attenpting to interact.
     * @param driverOnly can this action only be done by the driver/conductor?
     * @return if the player has permission to continue
     */
    public boolean getPermissions(EntityPlayer player, boolean driverOnly, boolean decreaseTicketStack) {
        //make sure the player is not null, and be sure that driver only rules are applied.
        if (player ==null){
            return false;
        } else if (driverOnly && (!(player.ridingEntity instanceof EntitySeat) || ! ((EntitySeat) player.ridingEntity).isControlSeat())){
            return false;
        }

        //be sure operators and owners can do whatever
        if ((player.capabilities.isCreativeMode && player.canCommandSenderUseCommand(2, ""))
                || (this.getOwner()!=null && this.getOwner() == player.getGameProfile())) {
            return true;
        }

        /*//if a ticket is needed, like for passenger cars
        if(getBoolean(boolValues.LOCKED) && getRiderOffsets().length>1){
            for(ItemStack stack : player.inventory.mainInventory){
                if(stack.getItem() instanceof ItemKey){
                    for(UUID id : ItemKey.getHostList(stack)){
                        if (id == this.entityUniqueID){
                            if(stack.getItem() instanceof ItemTicket &&decreaseTicketStack) {
                                stack.stackSize--;
                                if (stack.stackSize<=0){
                                    stack=null;
                                }
                            }
                            return true;
                        }
                    }
                }
            }
            return false;
        }*/

        //all else fails, just return if this is locked.
        //return !getBoolean(boolValues.LOCKED);
        return !this.getTrainLockedFromPacket();
    }
}