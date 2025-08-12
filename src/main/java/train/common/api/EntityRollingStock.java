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
import net.minecraft.entity.TraincraftEntityHelper;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent;
import train.client.core.handlers.SoundUpdaterRollingStock;
import train.common.Traincraft;
import train.common.adminbook.ServerLogger;
import train.common.core.HandleOverheating;
import train.common.core.handlers.ConfigHandler;
import train.common.core.handlers.FuelHandler;
import train.common.core.handlers.TrainHandler;
import train.common.core.network.PacketRollingStockRotation;
import train.common.core.util.DepreciatedUtil;
import train.common.entity.CollisionBox;
import train.common.entity.EntityHitbox;
import train.common.entity.TrustedPlayer;
import train.common.entity.rollingStockOld.special.EntityTracksBuilder;
import train.common.items.ItemPadlock;
import train.common.items.ItemPaintbrushThing;
import train.common.items.ItemRollingStock;
import train.common.items.ItemWrench;
import train.common.library.BlockIDs;
import train.common.library.GuiIDs;

import java.util.ArrayList;
import java.util.List;

import static ebf.tim.utility.CommonUtil.radianF;
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

    public float maxSpeed;
    public double speedLimiter = 1;

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

    public EntityHitbox collisionHandler=null;

    private TrainsOnClick trainsOnClick;
    public boolean isBraking;
    public int overheatLevel;
    public int linkageNumber;

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
    private boolean firstLoad = true;
    private boolean hasSpawnedBogie = false;
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
        setPosition(d, d1, d2);
        initRollingStock(world);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
        consist = new ArrayList<AbstractTrains>();
        consist.add(this);
    }

    public void initRollingStock(World world) {
        dataWatcher.addObject(20, 0);//heat
        dataWatcher.addObject(14, 0);
        dataWatcher.addObject(21, 0);

        preventEntitySpawning = true;
        isImmuneToFire = true;
        setSize(0.25f,0.25f);
        yOffset = 0;

        linkageNumber = 0;

        entityCollisionReduction = 0.8F;

        consist = new ArrayList<AbstractTrains>();
        consist.add(this);
        updateLinks();
        handleOverheating = new HandleOverheating(this);

        collisionHandler=new EntityHitbox(this);
        trainsOnClick = new TrainsOnClick();

        /* Railcraft's stuff */
        //maxSpeed = defaultMaxSpeedRail;
        //maxSpeedGround = defaultMaxSpeedGround;
        maxSpeedAirLateral = defaultMaxSpeedAirLateral;
        maxSpeedAirVertical = defaultMaxSpeedAirVertical;

        /**
         * Trains are always rendered even if out player's sight => no more
         * flickering/disappearing
         */
        if (ConfigHandler.FLICKERING) {
            this.ignoreFrustumCheck = true;
        }

        if (Traincraft.proxy.isClient()) {
            sndUpdater = new SoundUpdaterRollingStock();
        }

        setCollisionHandler(null);
    }


    public Entity[] getParts(){
        return collisionHandler==null || collisionHandler.interactionBoxes==null?null:
                collisionHandler.interactionBoxes.toArray(new Entity[]{});
    }

    /**
     * <p>This method is called on the client side when an entity is being loaded in. The additionalData buffer is sent from the server
     * and is populated by the server using the writeSpawnData method.</p>
     * <br></br><p>"this is basically NBT for entity spawn, to keep data between client and server in sync because some data is not automatically shared."</p>
     * @param additionalData The packet data stream
     */
    @Override
    public void readSpawnData(ByteBuf additionalData) {
        isBraking = additionalData.readBoolean();
        setTrainLockedFromPacket(additionalData.readBoolean());
        int numOfTrustedPlayers = additionalData.readInt();
        for (int i = 0; i < numOfTrustedPlayers; i++) {
            getTrustedList().add(new TrustedPlayer(ByteBufUtils.readUTF8String(additionalData), additionalData.readBoolean()));
        }
        if (additionalData.readBoolean()) { // If accepts overlay textures...
            getOverlayTextureContainer().importFromConfigTag(ByteBufUtils.readTag(additionalData));
        }
    }

    /**
     * <p>This method is called on the server side when a connected client is loading the entity. Data written
     * to the ByteBuffer will be synced with the client and available to the client through the readSpawnData method.</p>
     * <br></br><p>"this is basically NBT for entity spawn, to keep data between client and server in sync because some data is not automatically shared."</p>
     * @param buffer The packet data stream
     */
    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeBoolean(isBraking);
        buffer.writeBoolean(getTrainLockedFromPacket());
        buffer.writeInt(getTrustedList().size());
        for (TrustedPlayer player : getTrustedList()) {
            ByteBufUtils.writeUTF8String(buffer, player.getDisplayName());
            buffer.writeBoolean(player.hasBreakAccess());
        }
        buffer.writeBoolean(acceptsOverlayTextures());
        if (acceptsOverlayTextures()) {
            ByteBufUtils.writeTag(buffer, getOverlayTextureContainer().getOverlayConfigTag());

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
                    if (frontLink.consist != null){
                        frontLink.consist.clear();
                        frontLink.consist.add(frontLink);
                        frontLink.updateLinks();
                    }

                } else if (frontLink.Link2 == this.uniqueID) {
                    frontLink.Link2 = 0;
                    frontLink.backLink = null;
                    if (frontLink.consist != null){
                        frontLink.consist.clear();
                        frontLink.consist.add(frontLink);
                        frontLink.updateLinks();
                    }

                }
            }
            if (this.backLink != null) {
                if (backLink.Link1 == this.uniqueID) {
                    backLink.Link1 = 0;
                    backLink.frontLink = null;
                    if (backLink.consist != null){
                        backLink.consist.clear();
                        backLink.consist.add(backLink);
                        frontLink.updateLinks();
                    }

                } else if (backLink.Link2 == this.uniqueID) {
                    backLink.Link2 = 0;
                    backLink.backLink = null;
                    if (backLink.consist != null){
                        backLink.consist.clear();
                        backLink.consist.add(backLink);
                        frontLink.updateLinks();
                    }

                }
            }
            this.frontLink = null;
            this.backLink = null;
            this.isAttached = false;
            updateLinks();
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
            return !((EntityPlayer) p).getDisplayName().equalsIgnoreCase(this.getTrainOwner()) && !isPlayerTrusted(((EntityPlayer) p).getDisplayName());
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

    private double rollingX=0,rollingY=0,rollingZ=0, rollingPitch=0;
    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     */
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
        this.rollingX = par1;
        this.rollingY = par3;
        this.rollingZ = par5;
        this.rollingturnProgress = par9 + 2;
        this.rollingPitch=par8;
    }

    List list = null;
    Block l;


    @Override
    public void onUpdate() {
        if (addedToChunk && !this.hasSpawnedBogie) {

            if (bogieFront == null) {
                double[] offset=CommonUtil.rotatePoint(this.rotationPoints()[0], 0,180+rotationYaw);
                this.bogieFront = new EntityBogie(worldObj,offset[0]+posX,posY,offset[2]+posZ, this);

                offset=CommonUtil.rotatePoint(this.rotationPoints()[1], 0,180+rotationYaw);
                this.bogieBack = new EntityBogie(worldObj,offset[0]+posX,posY,offset[2]+posZ, this);

                worldObj.spawnEntityInWorld(bogieBack);
                worldObj.spawnEntityInWorld(bogieFront);
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
        //just so we aren't doing it *every* tick, but still frequent enough to not let the player actually take damage
        if(ticksExisted % 18 == 0) {
            if (!seats.isEmpty()) {
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
        }
        //dont check for jumping until at least a tick after seats spawned
        else if (!seats.isEmpty() && worldObj.isRemote && Traincraft.proxy.getCurrentScreen() == null && seats.get(0).getPassenger() != null) {
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
            if (rollingturnProgress > 0) {
                this.setPosition(this.posX + (this.rollingX - this.posX) / (double)this.rollingturnProgress,
                        this.posY + (this.rollingY - this.posY) / (double)this.rollingturnProgress,
                        this.posZ + (this.rollingZ - this.posZ) / (double)this.rollingturnProgress);
                --this.rollingturnProgress;

                if(bogieFront!=null && bogieBack !=null){
                    posY=(bogieFront.posY+bogieBack.posY)*0.5;
                    d6 = bogieBack.posX - bogieFront.posX;
                    d7 = bogieBack.posZ - bogieFront.posZ;
                    rotationPitch = CommonUtil.atan2degreesf(bogieFront.posY - bogieBack.posY, Math.sqrt(d6 * d6 + d7 * d7));
                }
            } else {
                setPosition(posX, posY, posZ);

            }

            if(render_cache!=null && render_cache.bogies!=null){
                for (train.client.render.Bogie b : render_cache.bogies) {
                    if (b != null) {
                        b.updatePosition(this, null);
                        b.updateRotation(this);
                    }
                }
            }

            collisionHandler.position(posX, posY, posZ, rotationPitch, rotationYaw);
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

        if (worldObj.isAirBlock(floor_posX, floor_posY, floor_posZ)) {
            floor_posY--;
        } else if (isRailBlockAt(worldObj, floor_posX, floor_posY + 1, floor_posZ) || worldObj.getBlock(floor_posX, floor_posY + 1, floor_posZ) == BlockIDs.tcRail.block || worldObj.getBlock(floor_posX, floor_posY + 1, floor_posZ) == BlockIDs.tcRailGag.block) {
            floor_posY++;
        }

        l = worldObj.getBlock(floor_posX, floor_posY, floor_posZ);

        updatePosition();




        //double var49 = MathHelper.wrapAngleTo180_float(this.rotationYaw - this.prevRotationYaw);

        if (bogieFront != null && bogieBack!=null) {

            d6 = bogieBack.posX - bogieFront.posX;
            d7 = bogieBack.posZ - bogieFront.posZ;
            prevRotationYaw = rotationYaw;

            this.rotationYaw = CommonUtil.atan2degreesf(d7, d6);
            rotationPitch = CommonUtil.atan2degreesf(bogieFront.posY - bogieBack.posY, Math.sqrt(d6 * d6 + d7 * d7));
        }


        if (!worldObj.isRemote && ticksExisted % 2 == 0) {
            Traincraft.rotationChannel.sendToAllAround(new PacketRollingStockRotation(this), new TargetPoint(worldObj.provider.dimensionId, posX, posY, posZ, 300.0D));
        }

        handleTrain();
        handleOverheating.HandleHeatLevel(this);
        this.func_145775_I();
        MinecraftForge.EVENT_BUS.post(new MinecartUpdateEvent(this, floor_posX, floor_posY, floor_posZ));

        numLaps++;
        if ((this instanceof Locomotive) && (this.Link1 == 0) && (this.Link2 == 0) && numLaps > 700) {
            this.consist.clear();
            consist.add(this);
            updateLinks();
        }


        //update the collision handler's positions
        collisionHandler.position(posX, posY, posZ, rotationPitch, rotationYaw);
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
                if(seats.get(i1) == null){
                    seats.set(i1, new EntitySeat(getWorld(), posX, posY,posZ,0,0,0, this,i1));
                    if(i1==0){
                        seats.get(i1).setControlSeat();
                    }
                    getWorld().spawnEntityInWorld(seats.get(i1));
                }
                cachedVectors[0] = new Vec3f(getRiderOffsets()[i1][0], getRiderOffsets()[i1][1], getRiderOffsets()[i1][2])
                        .rotatePoint(rotationPitch, rotationYaw, 0f);
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
                //derail= !CommonUtil.isTrack(getWorld(),posX,posY,posZ);
            }


            //handle yaw changes for derail
            if(derail) {
                if(frontLink instanceof EntityRollingStock &&
                        backLink instanceof EntityRollingStock){
                    rotationYaw=CommonUtil.atan2degreesf(
                            backLink.posZ - frontLink.posZ,
                            backLink.posX - frontLink.posX);
                } else if (backLink instanceof EntityRollingStock){
                    rotationYaw=CommonUtil.atan2degreesf(
                            backLink.posZ - posZ,
                            backLink.posX - posX);
                } else if (frontLink instanceof EntityRollingStock){
                    rotationYaw=CommonUtil.atan2degreesf(
                            posZ - frontLink.posZ,
                            posX - frontLink.posX);
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
                if(ticksSinceLastVelocityChange<200){
                    ticksSinceLastVelocityChange++;
                }
            }
        }
    }

    public void appendMovement(double velocity){
        //the logic gets stupid if it's not sorted from one end or another.
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

    public void addLinkingMove(double velocity){
        bogieBack.addLinking(this, velocity);
        bogieFront.addLinking(this, velocity);
    }
    public void manageLink(AbstractTrains other) {
        if(isAccelerating() || other.bogieBack ==null || other.bogieFront ==null || bogieBack ==null || bogieFront ==null) {
            return;
        }

        double vecX = other.posX - posX;
        double vecZ = other.posZ - posZ;


        double springDist = MathHelper.sqrt_double(vecX * vecX + vecZ * vecZ)
                -(getOptimalDistance(other)+other.getOptimalDistance(this));

        if(getVelocity()>0.3) {
            springDist *= 0.45;
        } else if (getVelocity()<0.1){
            springDist*=0.1;
        } else {
            springDist*=0.3;
        }
        if(backLink!=null && other == backLink) {
            springDist *= -1;
        }

        if(Math.abs(springDist)>0.01) {
            addLinkingMove(springDist);
        }

    }

    /**
     * if X or Z is null, the bogie's existing motion velocity will be used
     */
    public void finalMove(){

        applyDrag();
        if(frontLink!=null) {
            manageLink(frontLink);
        }
        if(backLink!=null){
            manageLink(backLink);
        }

        applyDrag();
        cachedVectors[1] = new Vec3f(rotationPoints()[1], 0, 0).rotatePoint(0, rotationYaw, 0)
                .addVector(bogieBack.posX,0,bogieBack.posZ);
        setPosition(cachedVectors[1].xCoord, (bogieBack.posY+bogieFront.posY)*0.5,cachedVectors[1].zCoord);

        bogieFront.minecartMove(this);
        bogieBack.minecartMove(this);

        //update rotation
        setRotation((CommonUtil.atan2degreesf(
                bogieBack.posZ - bogieFront.posZ,
                bogieBack.posX - bogieFront.posX)),
                CommonUtil.calculatePitch(bogieFront.posY, bogieBack.posY , Math.abs(rotationPoints()[0]) + Math.abs(rotationPoints()[1])));

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
        boolean canSlope=ConfigHandler.ENABLE_SLOPE_ACCELERATION;
        float drag = 0.9998f, brakeBuff = 0;
        //check if lope things can be done at all
        for(AbstractTrains stock : consist) {
            if(stock!=this && getAccelerator()!=0){
                canSlope=false;
                break;
            }
        }
        if (isBraking) {
            //realistically would be more like 2.4, but 5 makes gameplay more dramatic
            brakeBuff += weightKg() * 5.0f;
        }
        if(canSlope) {
            if (Math.abs(rotationPitch) - 1 > 0) { //cap the pitch that we actually consider to be on a slope
                //vanilla uses 0.0078125 per tick for slope speed.
                //0.00017361 would be that divided by 45 since vanilla slopes are 45 degree angles.
                //scale by entity pitch, it's backwards here for some reason, idk.
                appendMovement((0.00017361) * -rotationPitch);
            }
        }

        //now do drag stuff

        //scale drag for derail, or air lateral friction. if you do both at the same time then it's way too much.
        if(derail){
            drag*=CommonUtil.getBlockAt(getWorld(),posX,posY,posZ).slipperiness;
        } else if (cachedVectors[2].yCoord > 0) {
            drag -= ((getFriction() * cachedVectors[2].yCoord * 4.448f)); //we don't know what 4.448 does
        }

        //add in the drag from combined weight, plus brakes.
        if(pullingWeight!=0) {//in theory this should never be 0, but we know forge is dumb
            drag -= ((getAccelerator()==0?getFriction()*0.75:getFriction()*2.5) * (pullingWeight + brakeBuff)) / 1000; //was 4448, no idea. Just adjusted until something felt nice
        }
        //cap the drag to prevent weird behavior.
        // if it goes to 1 or higher then we speed up, which is bad, if it's below 0 we reverse, which is also bad
        if (drag > 0.9999f) {
            drag = 0.9999f;
        } else if (drag < 0f) {
            drag = 0f;
        }

        for(AbstractTrains t : consist){
            t.bogieFront.drag(t,drag);
            t.bogieBack.drag(t,drag);
        }
    }

    public float getFriction(){return 0.15f;}

    public double getAccelerator(){return accelerate;}


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
        nbttagcompound.setBoolean("firstLoad", this.firstLoad);
        nbttagcompound.setFloat("rotation", this.rotation);
        nbttagcompound.setBoolean("brake", isBraking);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);
        this.speedLimiter = nbttagcompound.getDouble("speedLimiter");

        this.firstLoad = nbttagcompound.getBoolean("firstLoad");
        this.rotation = nbttagcompound.getFloat("rotation");
        this.isBraking = nbttagcompound.getBoolean("brake");
    }

    @Override
    public boolean interactFirst(EntityPlayer entityplayer) {
        if (super.interactFirst(entityplayer)) {
            return true;
        }
        //if we are mounted on a seat, ignore this interaction
        if (entityplayer.ridingEntity instanceof EntitySeat) {
            return true;
        }

        playerEntity = entityplayer;
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();

        if (this.getTrainLockedFromPacket() && !worldObj.isRemote) {
            boolean isTrustedPlayer = isPlayerTrusted(playerEntity.getDisplayName());
            if (!playerEntity.getDisplayName().equalsIgnoreCase(this.getTrainOwner()) && !canBeRiddenWhileLocked(this) && !isTrustedPlayer) {
                if (!worldObj.isRemote) entityplayer.addChatMessage(new ChatComponentText("Train is locked by " + this.getTrainOwner() + "."));
                return true;
            }
            else if (!playerEntity.getDisplayName().equalsIgnoreCase(this.getTrainOwner()) && entityplayer.inventory.getCurrentItem() != null && entityplayer.inventory.getCurrentItem().getItem() instanceof ItemDye && (this instanceof Locomotive) && !isTrustedPlayer) {
                if (!worldObj.isRemote) entityplayer.addChatMessage(new ChatComponentText("Train is locked by " + this.getTrainOwner() + "."));
                return true;
            }
        }


        if(itemstack != null) {
            if (itemstack.getItem() instanceof ItemWrench && this instanceof Locomotive && entityplayer.isSneaking() && !worldObj.isRemote) {
                destination = "";
                entityplayer.addChatMessage(new ChatComponentText("Destination reset"));
                return true;
            }
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
                        if (itemstack.getItemDamage() == DepreciatedUtil.getColorFromString(SkinRegistry.get(this).get(i).addr)) {
                            this.setColor(SkinRegistry.get(this).get(i).addr);
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

            if (itemstack.getItem() instanceof ItemPaintbrushThing && entityplayer.isSneaking()) {
                if (SkinRegistry.get(this).size() > 0) {
                    entityplayer.openGui(Traincraft.instance, GuiIDs.PAINTBRUSH, entityplayer.getEntityWorld(), this.getEntityId(), -1, (int) this.posZ);
                }

                if (SkinRegistry.get(this).size() == 0) {
                    entityplayer.addChatMessage(new ChatComponentText("There are no other colors available."));
                }
                return true;
            } else if (itemstack.getItem() instanceof ItemPaintbrushThing) {
                for (int i = 0; i < SkinRegistry.get(this).size(); i++) {
                    if (this.getColor().equals(SkinRegistry.get(this).get(i))) {
                        if (SkinRegistry.get(this).size() > i+1) {
                            setColor(SkinRegistry.get(this).get(i+1).addr);
                        } else {
                            setColor(SkinRegistry.get(this).get(0).addr);
                        }
                        return true;
                    }
                }
            }  else if (entityplayer.isSneaking() && itemstack.getItem() instanceof ItemPadlock) {
                if (getTrainOwner().equalsIgnoreCase(entityplayer.getDisplayName())) {
                    entityplayer.openGui(Traincraft.instance, GuiIDs.LOCK_MENU, entityplayer.getEntityWorld(), this.getEntityId(), -1, (int) this.posZ);
                    return true;
                } else {
                    if (!worldObj.isRemote) entityplayer.addChatMessage(new ChatComponentText("Train is locked by " + this.getTrainOwner() + "."));
                    return false;
                }
            }
        }


        //be sure the player has permission to enter the transport, and that the transport has the main seat open.
        if (getRiderOffsets() != null && getPermissions(playerEntity, false) && !entityplayer.isSneaking()) {
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

    public void multiplyVelocity(double vel) {
        EntityRollingStock last = this;
        for(AbstractTrains train : consist) {
            if (train == null || train.bogieBack == null || train.bogieFront == null) { continue; } //This method can fire before the stock fully initializes, so we need to make sure bogies exist.
            if (train.backLink != null && last.backLink != null
                    && last == train.backLink
                    && train == last.backLink) {
                train.bogieBack.multiplyVelocity(train, -vel);
                train.bogieFront.multiplyVelocity(train, -vel);
            } else if (train.frontLink != null && last.frontLink != null
                    && last == train.frontLink
                    && train == last.frontLink) {
                train.bogieBack.multiplyVelocity(train, -vel);
                train.bogieFront.multiplyVelocity(train, -vel);
            } else {
                train.bogieBack.multiplyVelocity(train, vel);
                train.bogieFront.multiplyVelocity(train, vel);
            }
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
    public void onLinkCreated(EntityMinecart cart) {}

    /**
     * Called when a link is broken (usually).
     *
     * @param cart The cart we were linked with.
     */
    @Override
    public void onLinkBroken(EntityMinecart cart) {}

    @Override
    public boolean isLinked() {return frontLink !=null || backLink!=null;}

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
        return seats!=null && !seats.isEmpty();
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
        return isAccelerating()?1D:0.9998D;
    }

    @Override
    public void moveMinecartOnRail(int i, int j, int k, double d) {}




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
        if (seats.size() < seatNumber || seats.isEmpty()) { //there is a case where seatNumber == 0 so seats.size() was always ==.
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
    public boolean getPermissions(EntityPlayer player, boolean driverOnly) {
        //make sure the player is not null, and be sure that driver only rules are applied.
        if (player ==null) {
            return false;
        } else if (driverOnly && (!(player.ridingEntity instanceof EntitySeat) || ! ((EntitySeat) player.ridingEntity).isControlSeat())){
            return false;
        }

        //be sure operators and owners can do whatever
        if ((player.capabilities.isCreativeMode && player.canCommandSenderUseCommand(2, ""))
                || (this.getOwner()!=null && this.getOwner() == player.getGameProfile())
                || isPlayerTrusted(player.getDisplayName())
                || canBeRiddenWhileLocked(this)) {
            return true;
        }

        return !this.getTrainLockedFromPacket();
    }
}