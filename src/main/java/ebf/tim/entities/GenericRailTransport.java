package ebf.tim.entities;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.XmlBuilder;
import ebf.tim.TrainsInMotion;
import ebf.tim.api.SkinRegistry;
import ebf.tim.api.TransportSkin;
import ebf.tim.items.ItemKey;
import ebf.tim.items.ItemPaintBucket;
import ebf.tim.items.ItemStake;
import ebf.tim.items.ItemTicket;
import ebf.tim.models.Bogie;
import ebf.tim.networking.PacketInteract;
import ebf.tim.networking.PacketUpdateClients;
import ebf.tim.registry.NBTKeys;
import ebf.tim.registry.TiMFluids;
import ebf.tim.render.ParticleFX;
import ebf.tim.render.TransportRenderData;
import ebf.tim.utility.*;
import fexcraft.tmt.slim.ModelBase;
import fexcraft.tmt.slim.Vec3f;
import io.netty.buffer.ByteBuf;
import mods.railcraft.api.carts.IFluidCart;
import mods.railcraft.api.carts.ILinkableCart;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.*;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import train.blocks.TCBlocks;

import javax.annotation.Nullable;
import java.util.*;

import static ebf.tim.TrainsInMotion.transportTypes.*;
import static ebf.tim.utility.CommonUtil.radianF;

/**
 * <h1>Generic Rail Transport</h1>
 * this is the base for all trains and rollingstock.
 * @author Eternal Blue Flame
 */
public class GenericRailTransport extends EntityMinecart implements IEntityAdditionalSpawnData, IInventory, IFluidHandler, IFluidCart, ILinkableCart, IEntityMultiPart, IMinecart {

    /*
     * <h2>variables</h2>
     */
    /**defines the colors, the outer array is for each different color, and the inner int[] is for the RGB color*/
    public List<Integer> colorsFrom = new ArrayList<>();
    public List<Integer> colorsTo = new ArrayList<>();
    /**the front entity bogie*/
    public EntityBogie frontBogie = null;
    /**the back entity bogie*/
    public EntityBogie backBogie = null;
    /**the list of seat entities*/
    public List<EntitySeat> seats = new ArrayList<>();
    /**the server-sided persistent UUID of the transport linked to the front of this,*/
    public UUID frontLinkedTransport = null;
    /**the id of the rollingstock linked to the front*/
    public Integer frontLinkedID =null;
    /**the server-sided persistent UUID of the transport linked to the back of this,*/
    public UUID backLinkedTransport = null;
    /**the id of the rollingstock linked to the back*/
    public Integer backLinkedID = null;
    /**the id of the lead entity in the consist*/
    public Integer consistLeadID=null;
    /**the destination for routing*/
    public String destination ="";
    /**used to initialize a large number of variables that are used to calculate everything from movement to linking.
     * this is so we don't have to initialize each of these variables every tick, saves CPU.
     * 0 is for rider positions
     * 1 is for bogie initialization and updating
     * 2 is for cached locomotive velocity.
     * */
    public Vec3f[] cachedVectors = new Vec3f[]{
            new Vec3f(0,0,0),new Vec3f(0,0,0),new Vec3f(0,0,0),new Vec3f(0,0,0)};
    /**the health of the entity, similar to that of EntityLiving*/
    private int health = 20;
    /**the list of items used for the inventory and crafting slots.*/
    public List<ItemStackSlot> inventory = new ArrayList<>();
    /**whether or not this needs to update the datawatchers*/
    public boolean updateWatchers = false;
    /**the ticket that gives the entity permission to load chunks.*/
    private ForgeChunkManager.Ticket chunkTicket;
    /**a cached list of the loaded chunks*/
    public List<ChunkCoordIntPair> chunkLocations = new ArrayList<>();
    /**Used same as MinecartX/Y/Z in super to smoothly move on client*/
    private double transportX=0, transportY=0, transportZ=0;
    /**this is used like the turn progress in the super class*/
    private int tickOffset=0;
    /**Used to be sure we only say once that the transport has been derailed*/
    private boolean displayDerail = false;
    public HitboxDynamic collisionHandler=null;
    /**/
    float prevRotationRoll;
    /**/
    float rotationRoll;
    /**calculated movement speed, first value is used for GUI and speed, second is used for render effects.*/
    @Deprecated //TODO: value 1 gets the number right more often, but value 2 gets direction right. HOW
    public float[] velocity=new float[]{0,0};
    public int forceBackupTimer =0, syncTimer=0;
    public float pullingWeight=0;

    private float ticksSinceLastVelocityChange=1;

    private List<GenericRailTransport> consist = new ArrayList<>();
    boolean consistListInUse=false;//use of consist variable needs to be thread safe.

    //@SideOnly(Side.CLIENT)
    public TransportRenderData renderData = new TransportRenderData();

    public XmlBuilder entityData = new XmlBuilder();


    /**the array of booleans, defined as bits
     * 0- brake: defines the brake
     * 1- locked: defines if transport is locked to owner and key holders
     * 2- lamp: defines if lamp is on
     * 3- creative: defines if the transport should consume fuels and be able to derail.
     * 4- coupling: defines if the transport is looking to couple with other transports.
     * 5- inventory whitelist: defines if the inventory is a whitelist
     * 6- running: defines if te transport is running (usually only on trains).
     * 7-15 are unused.
     * for use see
     * @see #getBoolean(boolValues)
     * @see #setBoolean(boolValues, boolean)
     */
    private BitList bools = new BitList();


    /**
     * <h2>Railcraft linkage support</h2>
     */
    //if the transport can take a link
    @Override
    public boolean isLinkable() {
        return false;
    }
    @Override
    public boolean canLinkWithCart(EntityMinecart cart) {
        //if support is to be added a hitbox will need to be made for front and back to check if it contains the cart.
        //additionally all linking functionality will have to account for if the linked entity is instanceof EntityMinecart
        return false;
    }

    @Override
    public boolean hasTwoLinks() {
        return true;
    }

    //the distance that a link can be created
    @Override
    public float getLinkageDistance(EntityMinecart cart) {
        return (getHitboxSize()[0]*0.5f)+0.5f;
    }
    //the distance to be kept between carts
    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return (getHitboxSize()[0]*0.5f);
    }

    @Override
    public boolean canBeAdjusted(EntityMinecart cart) {
        return getAccelerator()==0;
    }

    @Override
    public void onLinkCreated(EntityMinecart cart) {}

    @Override
    public void onLinkBroken(EntityMinecart cart) {
        if(frontLinkedID==cart.getEntityId()){
            frontLinkedTransport=null;
            frontLinkedID=null;
        } else if (backLinkedID==cart.getEntityId()){
            backLinkedTransport=null;
            backLinkedID=null;
        }
    }

    @Override//WHY DOES THIS NEED TO EXIST?? getWorld() IS PUBLIC?????????????//???
    public World func_82194_d() {
        return getWorld();
    }

    @Override
    public boolean attackEntityFromPart(EntityDragonPart part, DamageSource source, float p_70965_3_) {
        //todo: this could be used to cheat which side is being interacted :thonk:
        return attackEntityFrom(source, p_70965_3_);
    }

    /**
     * Returns true if the Minecart matches the item provided. Generally just
     * stack.isItemEqual(cart.getCartItem()), but some carts may need more
     * control (the Tank Cart for example).
     *
     * @param stack the Filter
     * @param cart  the Cart
     * @return true if the item matches the cart
     */
    @Override
    public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
        return stack.getItem().delegate.name().equals(getItem().delegate.name());
    }

    public enum boolValues{BRAKE(0), LOCKED(1), LAMP(2), CREATIVE(3), COUPLINGFRONT(4), COUPLINGBACK(5), WHITELIST(6), RUNNING(7), DERAILED(8);
        public int index;
        boolValues(int index){this.index = index;}
    }

    public boolean getBoolean(boolValues index){
        return getBoolean(index.index);
    }
    public boolean getBoolean(int index){
        if(getWorld().isRemote) {
            return bools.getFromInt(index, this.dataWatcher.getWatchableObjectInt(17));
        } else {
            return bools.get(index);
        }
    }

    public void setBoolean(boolValues index, boolean value){
        setBoolean(index.index, value);
    }
    public void setBoolean(int index, boolean value){
        if (getBoolean(index) != value) {
            bools.set(index, value);
            updateWatchers = true;
        }
    }

    public GenericRailTransport(World world){
        super(world);
        setSize(0.25f,0.25f);
        ignoreFrustumCheck = true;
        initInventorySlots();
        if(world!=null && collisionHandler==null) {
            this.height = 0.25f;
            collisionHandler = new HitboxDynamic(getHitboxSize()[0],getHitboxSize()[1],getHitboxSize()[2], this);
            collisionHandler.position(posX, posY, posZ, rotationPitch, rotationYaw);
        }
    }
    public GenericRailTransport(UUID owner, World world, double xPos, double yPos, double zPos){
        super(world);

        posY = yPos;
        posX = xPos;
        posZ = zPos;
        entityData.putUUID("owner", owner);
        setSize(0.25f,0.25f);
        ignoreFrustumCheck = true;
        initInventorySlots();
    }

    /**
     * <h2>Entity initialization</h2>
     * Entity init runs right before the first tick.
     * This is useful for registering the datawatchers and inventory before we actually use them.
     * NOTE: slot 0 and 1 of the datawatcher are used by vanilla. It may be wise to leave the first 5 empty.
     */
    @Override
    public void entityInit(){
        if(getWorld()!=null) {
            //0 is an integer used for the entity state, 0 is burning. 1 is sneaking. 2 is riding something. 3 is sprinting. 4. is eating
            //1 is a short used for checking if the entity is underwater and how much air is left.
            //i think 2-5 are used in 1.8.9+ for various things.
            this.dataWatcher.addObject(12, 0.0F);//float used to show the current movement velocity.
            this.dataWatcher.addObject(13, 0);//train fuel consumption current
            this.dataWatcher.addObject(20, "");//fluid tank data
            this.dataWatcher.addObject(15, 0);//train heat
            this.dataWatcher.addObject(16, 40.0f);//train heat
            this.dataWatcher.addObject(17, bools!=null?bools.toInt():BitList.newInt());//booleans
            //18 is an int used by EntityTrainCore for the accelerator
            this.dataWatcher.addObject(21, 0);//front linked transport
            this.dataWatcher.addObject(22, 0);//back linked transport


            collisionHandler = new HitboxDynamic(getHitboxSize()[0],getHitboxSize()[1],getHitboxSize()[2], this);
            collisionHandler.position(posX, posY, posZ, rotationPitch, rotationYaw);
        }
        /*possible conflict notes:
        EntityMinecart uses the following datawatchers.
         overriding them has not proven to be harmful or conflicting, but it needs notation in case that changes.

        17 used as an integer for RollingAmplitude
        18 used as an integer for RollingDirection
        20 used as an integer for the current block ID to check if it's air.
        21 used as an integer for the DisplayTile value
        22 used as a byte for if there is a DisplayTile.
         */

    }

    /**
     * <h2>Entity first placed initialization</h2>
     * this is only ever called once, from the entity's item instance when the entity is first placed.
     */
    public void entityFirstInit(ItemStack item){}

    /**
     * override this to customize the inventory slots.
     * call this in the override if you just want to add more slots to the existing planned inventory size
     */
    public void initInventorySlots(){
        if (getInventoryRows()>0) {
            int index=40;
            for(int r = 0; r< getInventoryRows(); r++){
                for (int c=0;c<9;c++){
                    inventory.add(new ItemStackSlot(this, index, -97 + (c * 18), -19 + (r * 18) + ((int)((11 - getInventoryRows()) * 0.5f) * 18)));
                    index++;
                }
            }
        }
        //EntityTrainCore has it's own tanker management because fuel slots.
        //todo: i really didnt think out function slot IDs....
        if(getTypes().contains(TANKER)){
            inventory.add(tankerInputSlot());
            inventory.add(tankerOutputSlot());
        }
    }

    public ItemStackSlot fuelSlot(){
        if(getTypes().contains(STEAM)) {
            return new ItemStackSlot(this, 400, 114, 32).setOverlay(Items.coal);
        }
        if(getTypes().contains(DIESEL)) {
            return new ItemStackSlot(this, 400, 114, 32).setOverlay(TiMFluids.bucketOil);
        }
        if(getTypes().contains(ELECTRIC)) {
            return new ItemStackSlot(this, 400, 114, 32).setOverlay(Items.redstone);
        }


        return new ItemStackSlot(this, 400, 114, 32);

    }
    public ItemStackSlot waterSlot(){
        return new ItemStackSlot(this, 401,150,32).setOverlay(Items.water_bucket);
    }

    public ItemStackSlot tankerInputSlot(){
        return new ItemStackSlot(this, 400,150,-8).setOverlay(Items.water_bucket);
    }
    public ItemStackSlot tankerOutputSlot(){
        return new ItemStackSlot(this, 401,150,32).setOverlay(Items.bucket);
    }

    /**
     * use this if you plan to implement a custom Gui and Container in your own client/common proxy.
     */
    public boolean hasCustomGUI(){
        return false;
    }

    /*
     * <h2>base entity overrides</h2>
     * modify basic entity variables to give different uses/values.
     */
    /**returns if the player can push this, we actually use our own systems for this, so we return false*/
    @Override
    public boolean canBePushed() {return false;}
    @Override
    public int getMinecartType(){return 10002;}
    /**returns the hitbox of this entity, we dont need that so return null*/
    @Override
    public AxisAlignedBB getBoundingBox(){
        return null;}
    /**returns the hitbox of this entity, we dont need that so return null*/
    @Override
    public AxisAlignedBB getCollisionBox(Entity collidedWith){
        return null;}
    /**returns if this can be collided with, we don't use this so return false*/
    @Override
    public boolean canBeCollidedWith() {return true;}
    /**client only positioning of the transport, this should help to smooth the movement*/
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
        if (frontBogie!=null && backBogie!= null){

            setRotation((float)Math.toDegrees(CommonUtil.atan2f(
                    frontBogie.posZ - backBogie.posZ,
                    frontBogie.posX - backBogie.posX)),
                    CommonUtil.calculatePitch(frontBogie.posY,backBogie.posY,Math.abs(rotationPoints()[0]) + Math.abs(rotationPoints()[1])));

            transportX=p_70056_1_;
            transportY=p_70056_3_;
            transportZ=p_70056_5_;
            tickOffset = p_70056_9_+2;

            //handle bogie rotations
            if(renderData!=null && renderData.bogies!=null){
                for(Bogie b : renderData.bogies){
                    if(ClientProxy.EnableAnimations) {
                        b.updateRotation(this);
                    } else {
                        b.rotationYaw=rotationYaw;
                    }
                }
            }

            //handle particles
            if (ClientProxy.EnableParticles){
                if(getParticles().size()>0) {
                    ParticleFX.updateParticleItterator(getParticles(), getBoolean(boolValues.RUNNING), false);
                }
                for(List<ParticleFX> p : renderData.bogieParticles){
                    ParticleFX.updateParticleItterator(p, getBoolean(boolValues.RUNNING), false);
                }
            }
        }else {
            this.setPosition(p_70056_1_, p_70056_3_, p_70056_5_);
        }
    }

    @Override
    public void moveEntity(double p_70091_1_, double p_70091_3_, double p_70091_5_){
        DebugUtil.println("this is actually used???");
        super.moveEntity(p_70091_1_,p_70091_3_, p_70091_5_);
    }

    @Override
    public boolean interactFirst(EntityPlayer p_130002_1_) {
        return getWorld().isRemote?interact(p_130002_1_.getEntityId(),false,false, -1):super.interactFirst(p_130002_1_);
    }

    //unused IDs: 14+
    public boolean interact(int player, boolean isFront, boolean isBack, int key) {
        EntityPlayer p =((EntityPlayer)getWorld().getEntityByID(player));
        if (getWorld().isRemote) {
            if (p.getHeldItem()!=null && p.getHeldItem().getItem() instanceof ItemPaintBucket) {
                p.openGui(TrainsInMotion.instance, getEntityId(), getWorld(), 0, 0, 0);
                return true;
            }
            TrainsInMotion.keyChannel.sendToServer(new PacketInteract(key, getEntityId()));
        } else {
            //check if the player has permission first.
            if (!getPermissions(p, false, false)) {
                p.addChatMessage(new ChatComponentText(CommonUtil.translate("You don't have permission to do that.")));
                return false;
            }
            switch (key){
                case -999:{//entity attacked
                    p.attackTargetEntityWithCurrentItem(this);
                    break;
                }
                case -1: {//right click
                    if (p.getHeldItem() != null) {
                        if (p.getHeldItem().getItem() instanceof ItemKey) {
                            if (ItemKey.getHostList(p.getHeldItem()) !=null) {
                                for (UUID transport : ItemKey.getHostList(p.getHeldItem())) {
                                    if (transport.equals(getPersistentID())) {
                                        return true;//end the function here if it already has the key.
                                    }
                                }
                            }
                            if(((ItemKey) p.getHeldItem().getItem()).selectedEntity ==null || ((ItemKey) p.getHeldItem().getItem()).selectedEntity != getEntityId()){
                                ((ItemKey) p.getHeldItem().getItem()).selectedEntity = getEntityId();
                                p.addChatComponentMessage(new ChatComponentText(
                                        CommonUtil.translate("Click again to add the ") + transportName() +
                                                CommonUtil.translate(" to the Item's list.")

                                ));
                                return true;//end the function here if it already has the key.
                            } else {
                                ItemKey.addHost(p.getHeldItem(), getPersistentID(), transportName());
                                p.addChatComponentMessage(new ChatComponentText(
                                        CommonUtil.translate("added ") + transportName() +
                                                CommonUtil.translate(" to the Item's list.")

                                ));
                                ((ItemKey) p.getHeldItem().getItem()).selectedEntity=null;
                                return true;//end the function here if it already has the key.
                            }
                        }
                        else if (p.getHeldItem().getItem() instanceof ItemStake){
                            boolean toset = !getBoolean(boolValues.COUPLINGFRONT);
                            setBoolean(boolValues.COUPLINGFRONT, toset);
                            setBoolean(boolValues.COUPLINGBACK, toset);
                            if(!p.isSneaking()) {
                                if (toset) {
                                    p.addChatMessage(new ChatComponentText(CommonUtil.translate("message.linking")));
                                } else {
                                    p.addChatMessage(new ChatComponentText(CommonUtil.translate("message.notlinking")));
                                }
                            } else if(frontLinkedTransport!=null || backLinkedTransport!=null){
                                //calling the method from itself is a very lazy way to do this, but it's less to write.
                                interact(player,isFront,isBack,13);
                                p.addChatMessage(new ChatComponentText(CommonUtil.translate("message.unlinked")));
                            } else {
                                p.addChatMessage(new ChatComponentText(CommonUtil.translate("message.nolinks")));
                            }
                            return true;
                        }
                        //TODO: else if(player.getHeldItem() instanceof stakeItem) {do linking/unlinking stuff dependant on if it was front or not;}
                    }
                    //be sure the player has permission to enter the transport, and that the transport has the main seat open.
                    if (getRiderOffsets() != null && getPermissions(p, false, true)) {
                        for (EntitySeat seat : seats) {
                            //1.12 is stupid, sometimes when the passenger is null, it returns the player
                            if (!getWorld().isRemote && (seat.getPassenger() == null
                                    || seat.getPassenger().getEntityId()==p.getEntityId())) {
                                seat.addPassenger(p);
                                return true;
                            }
                        }
                    }
                }
                case 1:{ //open GUI
                    p.openGui(TrainsInMotion.instance, getEntityId(), getWorld(), 0, (int)posY, 0);
                    return true;
                }case 15: {//toggle brake
                    setBoolean(boolValues.BRAKE, !getBoolean(boolValues.BRAKE));
                    updateConsist();
                    return true;
                }case 5: { //Toggle lamp
                    setBoolean(boolValues.LAMP, !getBoolean(boolValues.LAMP));
                    return true;
                }case 6:{ //Toggle locked
                    setBoolean(boolValues.LOCKED, !getBoolean(boolValues.LOCKED));
                    return true;
                }case 10:{ //Toggle transport creative mode
                    setBoolean(boolValues.CREATIVE, !getBoolean(boolValues.CREATIVE));
                    return true;
                }case 7:{ //Toggle coupling for both ends
                    boolean toset = !getBoolean(boolValues.COUPLINGFRONT);
                    setBoolean(boolValues.COUPLINGFRONT, toset);
                    setBoolean(boolValues.COUPLINGBACK, toset);
                    return true;
                }case 13:{ //unlink transports
                    GenericRailTransport transport;
                    //frontLinkedTransport
                    if (frontLinkedID != null){
                        transport = (getWorld().getEntityByID(frontLinkedID) instanceof GenericRailTransport)?(GenericRailTransport) getWorld().getEntityByID(frontLinkedID):null;
                        if (transport != null){
                            if(transport.frontLinkedID !=null && transport.frontLinkedID == this.getEntityId()){
                                transport.frontLinkedTransport = null;
                                transport.frontLinkedID = null;
                            } else {
                                transport.backLinkedTransport = null;
                                transport.backLinkedID = null;
                            }
                            frontLinkedTransport = null;
                            frontLinkedID = null;
                            transport.updateWatchers = true;
                            transport.updateConsist();
                        }
                    }
                    //backLinkedTransport
                    if (backLinkedID != null){
                        transport = (getWorld().getEntityByID(backLinkedID) instanceof GenericRailTransport)?(GenericRailTransport) getWorld().getEntityByID(backLinkedID):null;
                        if (transport != null){
                            if(transport.frontLinkedID!=null && transport.frontLinkedID == this.getEntityId()){
                                transport.frontLinkedTransport = null;
                                transport.frontLinkedID = null;
                            } else {
                                transport.backLinkedTransport = null;
                                transport.backLinkedID = null;
                            }
                            backLinkedTransport = null;
                            backLinkedID = null;
                            transport.updateWatchers = true;
                            transport.updateConsist();
                        }
                    }
                    updateConsist();
                    return true;
                }
            }
        }

        return super.interactFirst((EntityPlayer) getWorld().getEntityByID(player));

    }

    public Entity[] getParts(){
        return collisionHandler==null || collisionHandler.interactionBoxes==null?null:
                collisionHandler.interactionBoxes.toArray(new Entity[]{});
    }

    /**
     * <h2>damage and destruction</h2>
     * attackEntityFromPart is called when one of the hitboxes of the entity has taken damage of some form.
     * the damage done is handled manually so we can compensate for basically everything, and if health is 0 or lower, we destroy the entity part by part, leaving the main part of the entity for last.
     */
    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float p_70097_2_){
        if(damageSource==null){
            health -=20;
            //be sure we drop the inventory items on death.
            dropAllItems();
            entityDropItem(getCartItem(),1);
            setDead();
            return true;
        }
        if (damageSource.getEntity() instanceof GenericRailTransport){
            return false;
        }
        //if its a creative player, destroy instantly
        if (damageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer) damageSource.getEntity()).capabilities.isCreativeMode && !damageSource.isProjectile()){
            health -=20;
            //this wont normally fire off from the packet in this scenario, so it has to be fired off manually.
            ServerLogger.deleteWagon(this);
            //if its reinforced and its not an explosion
        } else if (isReinforced() && !damageSource.isProjectile() && !damageSource.isExplosion()){
            health -=1;
            //if it is an explosion and it's reinforced, or it's not an explosion and isn't reinforced
        } else if ((damageSource.isExplosion() && isReinforced()) || (!isReinforced() && !damageSource.isProjectile())){
            health -=5;
            //if it isn't reinforced and is an explosion
        } else if (damageSource.isExplosion() && !isReinforced()){
            health-=20;
        }
        //cover overheating, or other damage to self.
        if (damageSource.getSourceOfDamage() == this){
            health-=20;
        }

        //on Destruction
        if (health<1 && !getWorld().isRemote){
            //since it was a player be sure we remove the entity from the logging.
            ServerLogger.deleteWagon(this);
            //be sure we drop the inventory items on death.
            dropAllItems();
            entityDropItem(getCartItem(),1);
            setDead();
            return true;
        }
        return false;
    }

    public void setDead() {
        super.setDead();
        //remove bogies
        if (frontBogie != null) {
            frontBogie.setDead();
            getWorld().removeEntity(frontBogie);
        }
        if (backBogie != null) {
            backBogie.setDead();
            getWorld().removeEntity(backBogie);
        }
        //remove seats
        for (EntitySeat seat : seats) {
            seat.setDead();
            seat.getWorld().removeEntity(seat);
        }
        //be sure the front and back links are removed in the case of this entity being removed from the world.
        if (frontLinkedID != null){
            GenericRailTransport front = ((GenericRailTransport)getWorld().getEntityByID(frontLinkedID));
            if(front != null && front.frontLinkedID != null && front.frontLinkedID == this.getEntityId()){
                front.frontLinkedID = null;
                front.frontLinkedTransport = null;
                front.updateConsist();
            } else if(front != null && front.backLinkedID != null && front.backLinkedID == this.getEntityId()){
                front.backLinkedID = null;
                front.backLinkedTransport = null;
                front.updateConsist();
            }
        }
        if (backLinkedID != null){
            GenericRailTransport back = ((GenericRailTransport)getWorld().getEntityByID(backLinkedID));
            if(back != null && back.frontLinkedID != null && back.frontLinkedID == this.getEntityId()){
                back.frontLinkedID = null;
                back.frontLinkedTransport = null;
                back.updateConsist();
            } else if(back != null && back.backLinkedID != null && back.backLinkedID == this.getEntityId()){
                back.backLinkedID = null;
                back.backLinkedTransport = null;
                back.updateConsist();
            }
        }
        for(CollisionBox box : collisionHandler.interactionBoxes){
            if(box !=null){
                box.setDead();
                getWorld().removeEntity(box);
            }
        }

    }

    /*
     * <h3>add bogies and seats</h3>
     */
    /** this is called by the seats and seats on their spawn to add them to this entity's list of seats, we only do it on client because that's the only side that seems to lose track.
     * @see EntitySeat#readSpawnData(ByteBuf)*/
    @SideOnly(Side.CLIENT)
    public void setseats(EntitySeat seat, int seatNumber){
        if (seats.size() <= seatNumber) {
            seats.add(seat);
        } else {
            seats.set(seatNumber, seat);
        }
    }

    /** this is called by the bogies on their spawn to add them to this entity's list of bogies, we only do it on client because that's the only side that seems to lose track.
     * @see EntityBogie#readSpawnData(ByteBuf)*/
    @SideOnly(Side.CLIENT)
    public void setBogie(EntityBogie cart, boolean isFront){
        if(isFront){
            frontBogie = cart;
        } else {
            backBogie = cart;
        }
    }

    /*
     * <h2> Data Syncing and Saving </h2>
     * SpawnData is mainly used for data that has to be created on client then sent to the server, like data processed on item use.
     * NBT is save data, which only happens on server.
     */

    /**reads the data sent from client on entity spawn*/
    @Deprecated //todo: send this data over the datawatcher or other more reliable means
    @Override
    public void readSpawnData(ByteBuf additionalData) {
        inventory = new ArrayList<>();
        //shouldn't need this, but enable if getting nulls
        initInventorySlots();
        entityData = new XmlBuilder(ByteBufUtils.readUTF8String(additionalData));

    }
    @Deprecated //todo: send this data over the datawatcher or other more reliable means
    /**sends the data to server from client*/
    @Override
    public void writeSpawnData(ByteBuf buffer) {
        ByteBufUtils.writeUTF8String(buffer, entityData.toXMLString());
    }

    /**loads the entity's save file*/
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        if (tag.hasKey("entityxml")) {
            entityData = new XmlBuilder(tag.getString("entityxml"));
            for(String key : entityData.itemMap.keySet()){
                getSlotIndexByID(Integer.parseInt(key.substring(4))).setStack(entityData.getItemStack(key));
            }
        }
        bools.set(tag.getByteArray(NBTKeys.bools));
        isDead = tag.getBoolean(NBTKeys.dead);
        //load links
        if (tag.hasKey(NBTKeys.frontLinkMost)) {
            frontLinkedTransport = new UUID(tag.getLong(NBTKeys.frontLinkMost), tag.getLong(NBTKeys.frontLinkLeast));
        }
        if (tag.hasKey(NBTKeys.backLinkMost)) {
            backLinkedTransport = new UUID(tag.getLong(NBTKeys.backLinkMost), tag.getLong(NBTKeys.backLinkLeast));
        }
        //load owner
        //@DEPRECIATED, legacy support to prevent save corruption
        if (tag.hasKey(NBTKeys.ownerMost)) {
            UUID owner = new UUID(tag.getLong(NBTKeys.ownerMost), tag.getLong(NBTKeys.ownerLeast));
            entityData.putUUID("owner", owner);
        }
        if (tag.hasKey(NBTKeys.ownerName)) {
            entityData.putString("ownername", tag.getString(NBTKeys.ownerName));
        }

        if (tag.hasKey(NBTKeys.skinURI)) {
            String skin = tag.getString(NBTKeys.skinURI);

            if(getWorld().isRemote &&
                    (!entityData.containsString("skin") || !entityData.getString("skin").equals(skin))){
                this.renderData.needsModelUpdate=true;
            }

            if (SkinRegistry.getSkin(this, null, false, skin) != null) {
                entityData.putString("skin", skin);
            } else {
                entityData.putString("skin", getDefaultSkin());
            }
        }


        //load bogie velocities
        if (tag.hasKey(NBTKeys.frontBogieX)) {
            entityData.putDouble(NBTKeys.frontBogieX, tag.getDouble(NBTKeys.frontBogieX));
            entityData.putDouble(NBTKeys.frontBogieZ, tag.getDouble(NBTKeys.frontBogieZ));
            entityData.putDouble(NBTKeys.backBogieX, tag.getDouble(NBTKeys.backBogieX));
            entityData.putDouble(NBTKeys.backBogieZ, tag.getDouble(NBTKeys.backBogieZ));
        }

        rotationRoll = tag.getFloat(NBTKeys.rotationRoll);
        prevRotationRoll = tag.getFloat(NBTKeys.prevRotationRoll);

        updateWatchers = true;
    }

    /**saves the entity to server world*/
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        //force write inventory to data before saving to prevent dupe bugs
        for (ItemStackSlot slot : inventory){
            entityData.putItemStack("inv."+slot.getSlotID(), slot.getStack());
        }
        tag.setString("entityxml", entityData.toXMLString());
        tag.setByteArray(NBTKeys.bools, bools.getBits());
        tag.setBoolean(NBTKeys.dead, isDead);
        //frontLinkedTransport and backLinkedTransport bogies
        if (frontLinkedTransport != null){
            tag.setLong(NBTKeys.frontLinkMost, frontLinkedTransport.getMostSignificantBits());
            tag.setLong(NBTKeys.frontLinkLeast, frontLinkedTransport.getLeastSignificantBits());
        }
        if (backLinkedTransport != null){
            tag.setLong(NBTKeys.backLinkMost, backLinkedTransport.getMostSignificantBits());
            tag.setLong(NBTKeys.backLinkLeast, backLinkedTransport.getLeastSignificantBits());
        }


        tag.setFloat(NBTKeys.rotationRoll, rotationRoll);
        tag.setFloat(NBTKeys.prevRotationRoll, prevRotationRoll);

    }

    //stops super class from writing unnecessary things.
    @Override
    public boolean hasDisplayTile(){return false;}

    @Override
    public boolean writeMountToNBT(NBTTagCompound tag){
        return false;
    }

    /**todo: plays a sound during entity movement*/
    @Override
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {}

    public boolean hasDrag(){return consistLeadID==null || consistLeadID==getEntityId();}

    public void updatePosition(){

        //reposition bogies to be sure they are the right distance
        if(!getWorld().isRemote) {

            //do scaled rail boosting but keep it capped to the max velocity of the rail
            Block b = CommonUtil.getBlockAt(getWorld(),posX,posY,posZ);
            if (b instanceof BlockRailBase){
                setBoolean(boolValues.DERAILED, false);

                if (b == Blocks.golden_rail) {
                    if ((((BlockRailBase) b).isPowered()) &&
                            //this part keeps it capped
                            getVelocity() < maxBoost(b)) {
                        float boost = CommonUtil.getMaxRailSpeed(getWorld(), (BlockRailBase) b, this, posX, posY, posZ) * 0.005f;
                        frontBogie.addVelocity(//this part boosts in the current direction, scaled by the speed of the rail
                                Math.copySign(boost, frontBogie.motionX),
                                0,
                                Math.copySign(boost, frontBogie.motionZ));

                        backBogie.addVelocity(//this part boosts in the current direction, scaled by the speed of the rail
                                Math.copySign(boost, backBogie.motionX),
                                0,
                                Math.copySign(boost, backBogie.motionZ));
                    }
                }
            } else {
                //set the derail state based on whether or not there's a valid rail block below.
                //later this will add more inherent support for 3rd party mods like ZnD, right now it's just vanilla/RC/TiM
                setBoolean(boolValues.DERAILED, !CommonUtil.isRailBlockAt(getWorld(),posX,posY,posZ));
            }


            //handle yaw changes for derail
            if(getBoolean(boolValues.DERAILED)) {
                if(frontLinkedID!=null && backLinkedID!=null){
                    rotationYaw=CommonUtil.atan2degreesf(
                            getWorld().getEntityByID(frontLinkedID).posZ - getWorld().getEntityByID(backLinkedID).posZ,
                            getWorld().getEntityByID(frontLinkedID).posX - getWorld().getEntityByID(backLinkedID).posX);
                } else if (frontLinkedID!=null){
                    rotationYaw=CommonUtil.atan2degreesf(
                            getWorld().getEntityByID(frontLinkedID).posZ - posZ,
                            getWorld().getEntityByID(frontLinkedID).posX - posX);
                } else if (backLinkedID!=null){
                    rotationYaw=CommonUtil.atan2degreesf(
                            posZ - getWorld().getEntityByID(backLinkedID).posZ,
                            posX - getWorld().getEntityByID(backLinkedID).posX);
                }
            }


        //actually move
            moveBogies(null,null);
            //only update velocity if we've moved to any significance.
            if(Math.abs(posX-prevPosX)>0.0625 || Math.abs(posZ-prevPosZ)>0.0625) {
                motionX = (posX - prevPosX)/ticksSinceLastVelocityChange;
                motionZ = (posZ - prevPosZ)/ticksSinceLastVelocityChange;
                prevPosX = posX;
                prevPosZ = posZ;
                dataWatcher.updateObject(12, getVelocity());

                ticksSinceLastVelocityChange=1;
            } else {
                motionX = (posX - prevPosX)/ticksSinceLastVelocityChange;
                motionZ = (posZ - prevPosZ)/ticksSinceLastVelocityChange;
                ticksSinceLastVelocityChange++;
            }
        }
    }

    /**
     * if X or Z is null, the bogie's existing motion velocity will be used
     */
    public void moveBogies(Double velocityX, Double velocityZ){
        if(velocityX==null||velocityZ==null){
            frontBogie.minecartMove(this, frontBogie.motionX, frontBogie.motionZ);
            backBogie.minecartMove(this, backBogie.motionX, backBogie.motionZ);
        } else if(velocityX!=0 || velocityZ!=0) {
            frontBogie.minecartMove(this, velocityX, velocityZ);
            backBogie.minecartMove(this, velocityX, velocityZ);
        }

        cachedVectors[1] = new Vec3f(-rotationPoints()[0],0,0).rotatePoint(rotationPitch, rotationYaw,0);

        setPosition((frontBogie.posX+cachedVectors[1].xCoord),
                (frontBogie.posY+cachedVectors[1].yCoord),(frontBogie.posZ+cachedVectors[1].zCoord));
        setRotation((CommonUtil.atan2degreesf(
                frontBogie.posZ - backBogie.posZ,
                frontBogie.posX - backBogie.posX)),
                CommonUtil.calculatePitch(backBogie.posY + backBogie.yOffset, frontBogie.posY+frontBogie.yOffset,Math.abs(rotationPoints()[0]) + Math.abs(rotationPoints()[1])));


        cachedVectors[1] = CommonUtil.rotatePoint(new Vec3f(rotationPoints()[0], 0, 0), rotationPitch, rotationYaw, 0);
        //can't hard clamp
        // has to be slow and smooth, with room for a margin of error, otherwise it will rubberband into oblivion.
        if (Math.abs(cachedVectors[1].xCoord) > 0.1 || Math.abs(cachedVectors[1].zCoord) > 0.1) {
            frontBogie.minecartMove(this,
                    ((cachedVectors[1].xCoord + posX) - frontBogie.posX) * 0.2,
                    ((cachedVectors[1].zCoord + posZ) - frontBogie.posZ) * 0.2);
        }

        cachedVectors[1] = CommonUtil.rotatePoint(new Vec3f(rotationPoints()[1], 0, 0), rotationPitch, rotationYaw, 0);
        if (Math.abs(cachedVectors[1].xCoord) > 0.1 || Math.abs(cachedVectors[1].zCoord) > 0.1) {
            backBogie.minecartMove(this,
                    ((cachedVectors[1].xCoord + posX) - backBogie.posX) * 0.2,
                    ((cachedVectors[1].zCoord + posZ) - backBogie.posZ) * 0.2);
        }

        //reset the vector when we're done so it wont break trains.
        cachedVectors[1]= new Vec3f(0,0,0);

        if(collisionHandler==null) {
            collisionHandler = new HitboxDynamic(getHitboxSize()[0],getHitboxSize()[1],getHitboxSize()[2], this);
            collisionHandler.position(posX, posY, posZ, rotationPitch, rotationYaw);
        } else {
            collisionHandler.position(posX, posY, posZ, rotationPitch, rotationYaw);
        }
    }

    double maxBoost(Block booster){
        if(this.transportTopSpeed()>0){
            return Math.min(transportTopSpeed(),
                    CommonUtil.getMaxRailSpeed(getWorld(), (BlockRailBase) booster,this, posX,posY,posZ));
        }
        return CommonUtil.getMaxRailSpeed(getWorld(), (BlockRailBase) booster,this, posX,posY,posZ);
    }

    /**
     *
     * the logic of this is directly related to EnttiyTrainCore#calculateAcceleration
     * we didn't need to replace the super method, we could have used a different name. this name just reads nicer.
     */
    @Override
    public void applyDrag(){
        if(pullingWeight==0){
            updateConsist();
        }

        if(frontLinkedID==null || backLinkedID==null) {

            float drag = 0.998f,brakeBuff=0,slopeX=0,slopeZ=0;
            //iterate the consist to collect the stats, since only end units can do this.
            for(GenericRailTransport stock : getConsist()) {
                if(stock.getBoolean(boolValues.BRAKE)){
                    //realistically would be more like 2.4, but 5 makes gameplay more dramatic
                    brakeBuff+=stock.weightKg()*5.0f;
                }
                if(stock.rotationPitch!=0){
                    //vanilla uses 0.0078125 per tick for slope speed.
                    //0.00017361 would be that divided by 45 since vanilla slopes are 45 degree angles.
                    //so we buff that to just under double to balance against drag, then scale by entity pitch
                    //pith goes from -90 to 90, so it's inherently directional.
                    slopeX+=(0.00017361f*0.5f)*-stock.rotationPitch;
                }
            }

            //scale drag for derail, or air lateral friction. if you do both at the same time then it's way too much.
            if(getBoolean(boolValues.DERAILED)){
                drag*=CommonUtil.getBlockAt(getWorld(),posX,posY,posZ).slipperiness;
            } else if (getVelocity() > 0) {
                drag -= ((getFriction() * getVelocity() * 4.448f));
            }

            //add in the drag from combined weight, plus brakes.
            if(pullingWeight!=0) {//in theory this should never be 0, but we know forge is dumb
                drag -= ((getAccelerator()==0?getFriction()*0.75:getFriction()*2.5) * (pullingWeight + brakeBuff)) / 44480;
            }
            //cap the drag to prevent weird behavior.
            // if it goes to 1 or higher then we speed up, which is bad, if it's below 0 we reverse, which is also bad
            if (drag > 0.9999f) {
                drag = 0.9999f;
            } else if (drag < 0f) {
                drag = 0f;
            }

            //split the slope buff into X and Z, then rotate based on yaw.
            if (rotationYaw != 0.0F) {
                slopeZ = (slopeX * MathHelper.sin((rotationYaw-90)*radianF));
                slopeX = (slopeX * MathHelper.cos((rotationYaw-90)*radianF));
            }
            frontBogie.setVelocity(
                    (frontBogie.motionX * drag)+slopeX
                    , frontBogie.motionY,
                    (frontBogie.motionZ * drag)+slopeZ);
            backBogie.setVelocity(
                    (backBogie.motionX * drag)+slopeX,
                    backBogie.motionY,
                    (backBogie.motionZ * drag)+slopeZ);
        }
    }

    public float getFriction(){return 0.0015f;}

    /**
     * <h2> on entity update </h2>
     *
     * defines what should be done every tick
     * used for:
     * managing the list of bogies and seats, respawning them if they disappear.
     * managing speed, acceleration. and direction.
     * managing rotationYaw and rotationPitch.
     * updating rider entity positions if there is no one riding the core seat.
     * calling on link management.
     * @see #manageLink(GenericRailTransport)
     * syncing the owner entity ID with client.
     * and updating the lighting block.
     */
    @Override
    public void onUpdate() {
        if (!getWorld().isRemote) {
            if (forceBackupTimer > 0) {
                forceBackupTimer--;
            } else if (forceBackupTimer == 0) {
                ServerLogger.writeWagonToFolder(this);
                forceBackupTimer--;
            }

            if(syncTimer>0){
                syncTimer--;
            } else if (syncTimer==0) {
                TrainsInMotion.updateChannel.sendToAllAround(new PacketUpdateClients(entityData.toXMLString(),this),
                        new NetworkRegistry.TargetPoint(getWorld().provider.dimensionId,posX,posY,posZ,16*4));
                syncTimer--;
            }
        }

        //regen health after a while
        if(health<20 && ticksExisted%40==0){
            if(health>15){
                health=20;
            } else {
                health+=5;
            }
        }

        //if the cart has fallen out of the map, destroy it.
        if (posY < -64.0D & isDead){
            getWorld().removeEntity(this);
        }

        if(this.chunkTicket == null) {
            this.requestTicket();
        }

        //be sure bogies exist

        //always be sure the bogies exist on client and server.
        if (!getWorld().isRemote && (frontBogie == null || backBogie == null)) {
            //spawn front bogie
            cachedVectors[1] = new Vec3f(rotationPoints()[0],0,0).rotatePoint(rotationPitch, rotationYaw,0);
            frontBogie = new EntityBogie(getWorld(), posX + cachedVectors[1].xCoord, posY + cachedVectors[1].yCoord, posZ + cachedVectors[1].zCoord, getEntityId(), true);
            //spawn back bogie
            cachedVectors[1] = new Vec3f(rotationPoints()[1],0,0).rotatePoint(rotationPitch, rotationYaw,0);
            backBogie = new EntityBogie(getWorld(), posX + cachedVectors[1].xCoord, posY + cachedVectors[1].yCoord, posZ + cachedVectors[1].zCoord, getEntityId(), false);

            getWorld().spawnEntityInWorld(frontBogie);
            getWorld().spawnEntityInWorld(backBogie);

            if (getRiderOffsets() != null && getRiderOffsets().length >0 && seats.size()<getRiderOffsets().length) {
                for (int i = 0; i < getRiderOffsets().length; i++) {
                    EntitySeat seat = new EntitySeat(getWorld(), posX, posY, posZ, getRiderOffsets()[i][0], getRiderOffsets()[i][1],getRiderOffsets()[i][2], this, i);
                    if(i==0){
                        seat.setControlSeat();
                    }
                    getWorld().spawnEntityInWorld(seat);
                    seats.add(seat);
                }
            }
            //initialize fluid tanks
            getTankInfo(null);
            //sync inventory on spawn
            openInventory();

            if(!(this instanceof EntityTrainCore)) {
                updatePosition();
            }


            prevPosX = posX;
            prevPosZ = posZ;
            motionX = 0;
            motionZ = 0;
            dataWatcher.updateObject(12, getVelocity());
        }

        //CLIENT UPDATE
        if(getWorld().isRemote){
            if(tickOffset >0 || ticksExisted<2) {
                prevPosX = posX;
                prevPosZ = posZ;
                if(tickOffset >0) {
                    setPosition(
                            this.posX + (this.transportX - this.posX) / (double) this.tickOffset,
                            this.posY + (this.transportY - this.posY) / (double) this.tickOffset,
                            this.posZ + (this.transportZ - this.posZ) / (double) this.tickOffset
                    );
                }

                for(int i=0;i<seats.size();i++){
                    if(seats.get(i)!=null) {
                        cachedVectors[0] = new Vec3f(getRiderOffsets()[i][0], getRiderOffsets()[i][1], getRiderOffsets()[i][2])
                                .rotatePoint(rotationPitch, rotationYaw, 0f);
                        cachedVectors[0].addVector(posX, posY, posZ);
                        seats.get(i).setPosition(cachedVectors[0].xCoord, cachedVectors[0].yCoord, cachedVectors[0].zCoord);
                    }
                }

                velocity[1] = (float) ((Math.abs(posX) - Math.abs(prevPosX)) + (Math.abs(posZ) - Math.abs(prevPosZ)));
                if (frontBogie != null && backBogie != null) {
                    frontBogie.minecartMove(this, frontBogie.motionX, frontBogie.motionZ);
                    backBogie.minecartMove(this, frontBogie.motionX, frontBogie.motionZ);

                    setRotation(CommonUtil.atan2degreesf(
                            frontBogie.posZ - backBogie.posZ,
                            frontBogie.posX - backBogie.posX),
                            CommonUtil.calculatePitch(
                                    backBogie.posY, frontBogie.posY,
                                    Math.abs(rotationPoints()[0]) + Math.abs(rotationPoints()[1])));
                }
                if(ClientProxy.EnableAnimations && renderData!=null && renderData.bogies!=null){
                    for(Bogie b : renderData.bogies){
                        b.updatePosition(this, null);
                    }
                }
                collisionHandler.position(posX, posY, posZ, rotationPitch, rotationYaw);
                if(collisionHandler!=null){
                    collisionHandler.updateCollidingEntities(this);
                    for (Entity e : collisionHandler.collidingEntities) {
                        manageCollision(e);
                    }
                    for (int[] pos : collisionHandler.collidingBlocks) {
                        manageCollision(pos);
                    }
                }
                tickOffset--;
            }
        }


        /*
         * run the hitbox check whether or not the bogies exist so we can ensure interaction even during severe client-sided error.
         *check if the bogies exist, because they may not yet, and if they do, check if they are actually moving or colliding.
         * no point in processing movement if they aren't moving or if the train hit something.
         * if it is clear however, then we need to add velocity to the bogies based on the current state of the train's speed and fuel, and reposition the train.
         * but either way we have to position the bogies around the train, just to be sure they don't accidentally fly off at some point.
         *
         * this stops updating if the transport derails. Why update positions of something that doesn't move? We compensate for first tick to be sure hitboxes, bogies, etc, spawn on join.
         */
        else if (frontBogie!=null && backBogie != null && ticksExisted>5){

            //fixes consist not initializing on world load and similar
            if((frontLinkedID!=null || backLinkedID!=null) && consist.size()==1){
                updateConsist();
            }

            //calculate for slopes, friction, and drag
            if (hasDrag()) {
                applyDrag();
            }
            //update positions related to linking, this NEEDS to come after drag
            //only run updates if either the front link or the back link is null.
            //if there is a consist lead, only the lead can run updates
           if((consistLeadID!=null && getEntityId()==consistLeadID) ||
                   ((frontLinkedID==null && backLinkedID!=null) || (frontLinkedID!=null && backLinkedID==null))){
               GenericRailTransport last = null;
               for (GenericRailTransport transport : getConsist()){
                   if(last!=null) {
                       last.manageLink(transport);
                   }
                   last = transport;
               }
           }

            if(collisionHandler!=null){
                collisionHandler.updateCollidingEntities(this);
                for (Entity e : collisionHandler.collidingEntities) {
                    manageCollision(e);
                }
                for (int[] pos : collisionHandler.collidingBlocks) {
                    manageCollision(pos);
                }
            }

            if(!(this instanceof EntityTrainCore)) {
                updatePosition();
            }
        }

        //rider updating isn't called if there's no driver/conductor, so just in case of that, we reposition the seats here too.
        if (getRiderOffsets() != null) {
            for (int i = 0; i < seats.size(); i++) {
                //sometimes seats die when players log out. make new ones.
                if(seats.get(i) ==null){
                    seats.set(i, new EntitySeat(getWorld(), posX, posY,posZ,0,0,0, this,i));
                    if(i==0){
                        seats.get(i).setControlSeat();
                    }
                    getWorld().spawnEntityInWorld(seats.get(i));
                }
                cachedVectors[0] = new Vec3f(getRiderOffsets()[i][0], getRiderOffsets()[i][1], getRiderOffsets()[i][2])
                        .rotatePoint(rotationPitch, rotationYaw, 0f);
                cachedVectors[0].addVector(posX,posY,posZ);
                seats.get(i).setPosition(cachedVectors[0].xCoord, cachedVectors[0].yCoord, cachedVectors[0].zCoord);
            }
        }

        //be sure the owner entityID is currently loaded, this variable is dynamic so we don't save it to NBT.
        if (!getWorld().isRemote &&ticksExisted %20==0){

            manageFuel();


            if (!entityData.containsString("ownername") || entityData.getString("ownername").equals("")) {
                @Nullable
                Entity player = CommonProxy.getEntityFromUuid(entityData.getUUID("owner"), getWorld());
                if (player instanceof EntityPlayer) {
                    entityData.putString("ownername",((EntityPlayer) player).getDisplayName());
                    updateWatchers = true;
                }
            }
            //sync the linked transports with client, and on server, easier to use an ID than a UUID.
            Entity linkedTransport = CommonProxy.getEntityFromUuid(frontLinkedTransport, getWorld());
            if (linkedTransport instanceof GenericRailTransport
                    && (frontLinkedID == null || linkedTransport.getEntityId() != frontLinkedID)
                    && (backLinkedID == null || linkedTransport.getEntityId() != backLinkedID)) {
                frontLinkedID = linkedTransport.getEntityId();
                updateWatchers = true;
            }
            linkedTransport = CommonProxy.getEntityFromUuid(backLinkedTransport, getWorld());
            if (linkedTransport instanceof GenericRailTransport
                    && (backLinkedID == null || linkedTransport.getEntityId() != backLinkedID)
                    && (backLinkedID == null || linkedTransport.getEntityId() != backLinkedID)) {
                backLinkedID = linkedTransport.getEntityId();
                updateWatchers = true;
            }

            if (!getWorld().isRemote && getBoolean(boolValues.DERAILED) && !displayDerail){
                //todo
                //MinecraftServer.getServer().addChatMessage(new ChatComponentText(getOwner().getName()+"'s " + StatCollector.translateToLocal(getItem().getUnlocalizedName()) + " has derailed!"));
                displayDerail = true;
            }

            if(updateWatchers){
                this.dataWatcher.updateObject(17, bools.toInt());
                this.dataWatcher.updateObject(21, frontLinkedID!=null?frontLinkedID:-1);
                this.dataWatcher.updateObject(22, backLinkedID!=null?backLinkedID:-1);
            }
        }

        if (backBogie!=null && !isDead && getWorld().isRemote) {
            //handle particles
            if (ClientProxy.EnableParticles){
                if(getParticles().size()>0) {
                    ParticleFX.updateParticleItterator(getParticles(), getBoolean(boolValues.RUNNING), true);
                }
                for(List<ParticleFX> p : renderData.bogieParticles){
                    ParticleFX.updateParticleItterator(p, getBoolean(boolValues.RUNNING), true);
                }
            }
        }

        //force an additional save every half hour
        if(!getWorld().isRemote && ticksExisted%36000==0){
            ServerLogger.writeWagonToFolder(this);
        }
    }

    public void manageCollision(Entity e){
        //on client we need to push away players.
        if(getWorld().isRemote){
            if (e instanceof EntityPlayer) {
                double[] motion = CommonUtil.rotatePoint(0.1, 0,
                        CommonUtil.atan2degreesf(e.posZ- posZ, e.posX-posX));
                e.addVelocity(motion[0], 0.05, motion[2]);
            }
        } else {
            if (e instanceof EntityItem) {
                if (getTypes() != null && getTypes().contains(TrainsInMotion.transportTypes.HOPPER) && this.posY > this.posY + 0.5f &&
                        ((EntityItem) e).getEntityItem() != null && isItemValidForSlot(0, ((EntityItem) e).getEntityItem())) {
                    addItem(((EntityItem) e).getEntityItem());
                    getWorld().removeEntity(e);
                }

            } else if (e instanceof CollisionBox) {
                CollisionBox colliding = ((CollisionBox) e);

                //this is just a big mess to figure out if and which ends to couple since linking is front and back sided
                if(getBoolean(boolValues.COUPLINGFRONT)
                        && Math.abs(CommonUtil.atan2degreesf(posZ-e.posZ, posX-e.posX)-rotationYaw)<90){
                    if(colliding.host.collisionHandler.interactionBoxes.get(0)==colliding &&
                            colliding.host.getBoolean(boolValues.COUPLINGBACK)){

                        colliding.host.setbackLinkedTransport(this);
                        setFrontLinkedTransport(colliding.host);
                        EntityPlayer listener = getWorld().getClosestPlayerToEntity(this,20);
                        if(listener!=null)listener.addChatMessage(new ChatComponentText("Linked the " +
                                        CommonUtil.translate(getItem().getUnlocalizedName()) + " to the " +
                                        CommonUtil.translate(getItem().getUnlocalizedName()))
                        );
                        updateConsist();
                        colliding.host.updateConsist();
                        return;

                    } else if(colliding.host.getBoolean(boolValues.COUPLINGFRONT)){
                        colliding.host.setFrontLinkedTransport(this);
                        setFrontLinkedTransport(colliding.host);
                        EntityPlayer listener = getWorld().getClosestPlayerToEntity(this,20);
                        if(listener!=null)listener.addChatMessage(new ChatComponentText("Linked the " +
                                CommonUtil.translate(getItem().getUnlocalizedName()) + " to the " +
                                CommonUtil.translate(getItem().getUnlocalizedName()))
                        );
                        updateConsist();
                        colliding.host.updateConsist();
                        return;
                    }

                } else if(getBoolean(boolValues.COUPLINGBACK)){
                    if(colliding.host.collisionHandler.interactionBoxes.get(0)==colliding){
                        colliding.host.setbackLinkedTransport(this);
                        setbackLinkedTransport(colliding.host);
                        EntityPlayer listener = getWorld().getClosestPlayerToEntity(this,20);
                        if(listener!=null)listener.addChatMessage(new ChatComponentText("Linked the " +
                                CommonUtil.translate(getItem().getUnlocalizedName()) + " to the " +
                                CommonUtil.translate(getItem().getUnlocalizedName()))
                        );
                        updateConsist();
                        colliding.host.updateConsist();
                        return;
                    } else {
                        colliding.host.setFrontLinkedTransport(this);
                        setbackLinkedTransport(colliding.host);
                        EntityPlayer listener = getWorld().getClosestPlayerToEntity(this,20);
                        if(listener!=null)listener.addChatMessage(new ChatComponentText("Linked the " +
                                CommonUtil.translate(getItem().getUnlocalizedName()) + " to the " +
                                CommonUtil.translate(getItem().getUnlocalizedName()))
                        );
                        updateConsist();
                        colliding.host.updateConsist();
                        return;
                    }
                }

                //calculate the distance to yeet based on how far one pushed into the other
                double[] motion = CommonUtil.rotatePoint(
                        Math.max(Math.abs(getVelocity()), (Math.abs(colliding.host.getVelocity()))*0.25)+0.05,
                        0, CommonUtil.atan2degreesf(e.posZ-posZ, e.posX-posX));

                if (getBoolean(boolValues.BRAKE)) {
                    moveBogies(-motion[0]*0.5,-motion[2]*0.5);
                } else {
                    moveBogies(-motion[0],-motion[2]);
                }

                if (colliding.host.getBoolean(boolValues.BRAKE)) {
                    colliding.host.moveBogies(motion[0]*0.5,motion[2]*0.5);
                } else {
                    colliding.host.moveBogies(motion[0],motion[2]);
                }

            } else if (e instanceof EntityPlayer || e instanceof EntityLiving) {
                if (CommonProxy.pushabletrains &&
                        !getBoolean(boolValues.BRAKE) && getAccelerator() == 0 && getVelocity() < 0.01) {
                    double[] motion = CommonUtil.rotatePoint(0.25, 0,
                            CommonUtil.atan2degreesf(posZ - e.posZ, posX - e.posX));
                    moveBogies(motion[0], motion[2]);

                }


                double[] motion = CommonUtil.rotatePoint(0.025, 0,
                        CommonUtil.atan2degreesf(posZ - e.posZ, posX - e.posX));
                e.addVelocity(motion[0], 0.05, motion[2]);

                //hurt entity if going fast
                if (getVelocity() > 0.25f) {
                    e.attackEntityFrom(new EntityDamageSource(
                                    this instanceof EntityTrainCore ? "Locomotive" : "rollingstock", this),
                            (getVelocity()) * 0.5f);
                }
            }
        }
    }
    public void manageCollision(int[] pos){

    }

    public int getAccelerator(){return 0;}

    public void setFrontLinkedTransport(GenericRailTransport other){
        frontLinkedID=other.getEntityId();
        frontLinkedTransport=other.entityUniqueID;
        setBoolean(boolValues.COUPLINGFRONT, false);
    }

    public void setbackLinkedTransport(GenericRailTransport other){
        backLinkedID=other.getEntityId();
        backLinkedTransport=other.entityUniqueID;
        setBoolean(boolValues.COUPLINGBACK, false);
    }

    /**
     * iterates all the links to check if the stock has a train
     * called on linking changes and when a train changes running states
     */
    public void updateConsist(){
        List<GenericRailTransport> transports = new ArrayList<>();
        consistLeadID=null;
        GenericRailTransport link;

        //we need to have the list ordered from one of the ends, so iterate until an end is found and then use that.
        if((frontLinkedID!=null && backLinkedID!=null)) {
            link =(GenericRailTransport) getWorld().getEntityByID(frontLinkedID);
            while (link != null) {
                transports.add(link);
                if (link.frontLinkedID != null && getWorld().getEntityByID(link.frontLinkedID) instanceof GenericRailTransport &&
                        !transports.contains((GenericRailTransport) getWorld().getEntityByID(link.frontLinkedID))) {
                    link = (GenericRailTransport) getWorld().getEntityByID(link.frontLinkedID);
                } else if (link.backLinkedID != null && getWorld().getEntityByID(link.backLinkedID) instanceof GenericRailTransport &&
                        !transports.contains((GenericRailTransport) getWorld().getEntityByID(link.backLinkedID))) {
                    link = (GenericRailTransport) getWorld().getEntityByID(link.backLinkedID);
                } else {
                    break;
                }
            }
            transports = new ArrayList<>();
            transports.add(link);
        } else {
            transports.add(this);
        }

        //check the front, then loop for every transport linked to it in opposite direction of this.
        link = frontLinkedID==null?null:(GenericRailTransport) getWorld().getEntityByID(frontLinkedID);
        while(link!=null){
            transports.add(link);
            //if we find a consist lead, set it.
            if(link.getAccelerator()!=0){
                consistLeadID=link.getEntityId();
            }

            if(link.frontLinkedID!=null && getWorld().getEntityByID(link.frontLinkedID) instanceof GenericRailTransport &&
                    !transports.contains((GenericRailTransport) getWorld().getEntityByID(link.frontLinkedID))){
                link=(GenericRailTransport) getWorld().getEntityByID(link.frontLinkedID);
            } else if(link.backLinkedID!=null && getWorld().getEntityByID(link.backLinkedID) instanceof GenericRailTransport &&
                    !transports.contains((GenericRailTransport) getWorld().getEntityByID(link.backLinkedID))){
                link=(GenericRailTransport) getWorld().getEntityByID(link.backLinkedID);
            } else {
                link=null;
            }
        }
        //do it again, but for the back one
        link= backLinkedID==null?null:(GenericRailTransport) getWorld().getEntityByID(backLinkedID);
        while(link!=null){
            transports.add(link);
            //if we find a consist lead, set it.
            if(link.getAccelerator()!=0){
                consistLeadID=link.getEntityId();
            }
            if(link.frontLinkedID!=null && getWorld().getEntityByID(link.frontLinkedID) instanceof GenericRailTransport &&
                    !transports.contains((GenericRailTransport) getWorld().getEntityByID(link.frontLinkedID))){
                link=(GenericRailTransport) getWorld().getEntityByID(link.frontLinkedID);
            } else if(link.backLinkedID!=null && getWorld().getEntityByID(link.backLinkedID) instanceof GenericRailTransport &&
                    !transports.contains((GenericRailTransport) getWorld().getEntityByID(link.backLinkedID))){
                link=(GenericRailTransport) getWorld().getEntityByID(link.backLinkedID);
            } else {
                link=null;
            }
        }

        if(transports.size()>1) {
            //now tell everything in the list, including this, that there's a new list, and provide said list.
            for (GenericRailTransport t : transports) {
                t.setConsist(transports);
                t.setValuesOnLinkUpdate(transports);
                t.consistLeadID = consistLeadID;
            }
        }
    }


    /**
     * called on linking changes and when a train changes running states
     * @see #updateConsist()
     * @param consist the list of entities in the consist
     */
    public void setValuesOnLinkUpdate(List<GenericRailTransport> consist){
        pullingWeight=0;
        for(GenericRailTransport t : consist) {
            pullingWeight +=t.weightKg();
        }
    }

    /**
     * May return a 0 length array when consist is being updated.
     */
    public List<GenericRailTransport> getConsist(){
        if(!consistListInUse && consist.size()>0) {
            return consist;
        } else {
            return Collections.singletonList(this);
        }
    }
    public void setConsist(List<GenericRailTransport> input){
        consistListInUse=true;
        consist=input;
        consistListInUse=false;
    }

    //gets the power for acceleration math, result is in MHP, has a fallback that roughly converts TE to MHP
    public float getPower(){return (transportMetricHorsePower()>0f?transportMetricHorsePower()
            :transportTractiveEffort()*0.0035571365f);}
    /**gets the multiplication of fuel consumption, 1 is normal, 2 would be double, 1.5 would be halfway between the two, etc.*/
    public float getFuelEfficiency(){return 1;}

    public float getRunningEfficiency(){return 0.7f;}




    /**
     * used by EntitySeat to define if the rider should sit based on the seat ID
     * the seat ID is defined by the index of it's vector, minus one,
     *    so the second seat position would have an ID of 1.
     */
    public boolean shouldRiderSit(int seat){
        return shouldRiderSit();
    }
    @Override
    public boolean shouldRiderSit(){
        return true;
    }


    /**
     * <h2>manage links</h2>
     * this is used to reposition the transport based on the linked transports.
     * If coupling is on then it will check sides without linked transports for anything to link to.
     */
    public void manageLink(GenericRailTransport other) {
        if(other.frontBogie==null || other.backBogie==null || frontBogie==null || backBogie==null){
            return;
        }

        boolean adj1 = other.canBeAdjusted(this);
        boolean adj2 = this.canBeAdjusted(other);

        double d = other.posX - this.posX;
        double d1 = other.posZ - this.posZ;
        double vecX = other.posX - this.posX;
        double vecZ = other.posZ - this.posZ;

        double cart1MotionX=0;
        double cart1MotionZ=0;
        double cart2MotionX=0;
        double cart2MotionZ=0;


        //spring to distance
        double vecNorm = MathHelper.sqrt_double((d * d) + (d1 * d1)) -
                (other.getOptimalDistance(this)+this.getOptimalDistance(other));

        double springX = -0.4D * vecNorm * vecX;
        double springZ = -0.4D * vecNorm * vecZ;

        if(springX>14d || springX<-14d){
            springX=Math.copySign(14d, springX);
        }
        if(springZ>14d || springZ<-14d){
            springZ=Math.copySign(14d, springZ);
        }

        if (adj1) {
            cart1MotionX += springX;
            cart1MotionZ += springZ;
        }
        if (adj2) {
            cart2MotionX -= springX;
            cart2MotionZ -= springZ;
        }


        //dampen spring to smooth out
        vecNorm = MathHelper.sqrt_double(vecX * vecX + vecZ * vecZ);
        vecX /= vecNorm;
        vecZ /= vecNorm;

        vecNorm = (cart1MotionX - cart2MotionX) * vecX + (cart1MotionZ - cart2MotionZ) * vecZ;

        springX = -0.4D * vecNorm * vecX;
        springZ = -0.4D * vecNorm * vecZ;
        if(springX>14d || springX<-14d){
            springX=Math.copySign(14d, springX);
        }
        if(springZ>14d || springZ<-14d){
            springZ=Math.copySign(14d, springZ);
        }

        if (adj1) {
            cart1MotionX += springX;
            cart1MotionZ += springZ;
        }
        if (adj2) {
            cart2MotionX -= springX;
            cart2MotionZ -= springZ;
        }

        //move bogies
        if(cart1MotionX!=0 || cart1MotionZ!=0) {
            other.moveBogies(cart1MotionX, cart1MotionZ);
        }

        if(cart2MotionX!=0 || cart2MotionZ!=0) {
            this.moveBogies(cart2MotionX, cart2MotionZ);
        }

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
                || (entityData!=null && entityData.containsString("ownername") && entityData.getString("ownername").equals(player.getDisplayName()))) {
            return true;
        }

        //if a ticket is needed, like for passenger cars
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
        }

        //all else fails, just return if this is locked.
        return !getBoolean(boolValues.LOCKED);

    }

    public World getWorld(){ return worldObj;}

    @Override
    protected void setRotation(float p_70101_1_, float p_70101_2_) {
        this.prevRotationYaw = this.rotationYaw = p_70101_1_;
        this.prevRotationPitch = this.rotationPitch = p_70101_2_;
    }

    protected void setRotation(float yaw, float pitch, float roll){
        setRotation(yaw, pitch);
        this.prevRotationRoll = this.rotationRoll = roll;
    }


    public GameProfile getOwner(){
        if (entityData.containsString("ownername") && getWorld().getPlayerEntityByName(entityData.getString("ownername")) !=null){
            return (getWorld().getPlayerEntityByName(entityData.getString("ownername"))).getGameProfile();
        }
        return null;
    }

    /**defines the ID of the owner*/
    public String getOwnerName(){return entityData.containsString("ownername")?entityData.getString("ownername"):"";}

    public TransportSkin getTexture(EntityPlayer viewer){
        if(!this.entityData.containsString("skin")){
            this.entityData.putString("skin", getDefaultSkin());
        }
        return getSkinList(viewer, false).get(this.entityData.getString("skin"));
    }
    public TransportSkin getCurrentSkin(){
        if(!this.entityData.containsString("skin")){
            this.entityData.putString("skin", getDefaultSkin());
        }
        return getSkinList(null, false).get(this.entityData.getString("skin"));
    }

    public String getCurrentSkinName(){
        TransportSkin s = getCurrentSkin();
        return s==null||s.name==null?"":s.name;
    }

    //only works when called from server
    public void setSkin(String s){
        this.entityData.putString("skin", s);
        TrainsInMotion.updateChannel.sendToAllAround(new PacketUpdateClients(entityData.toXMLString(),this),
                new NetworkRegistry.TargetPoint(getWorld().provider.dimensionId,posX,posY,posZ,16*4));
    }

    public float getVelocity(){
        return getWorld().isRemote?dataWatcher.getWatchableObjectFloat(12):
                (float)(Math.abs(motionX)+Math.abs(motionZ));
    }
    /**
     * NOTE: lists are hash maps, their index order is different every time an entry is added or removed.
     * todo: reliability improvement: make a version of this that builds a list of the keys
     *     and then use the indexes of the keys to iterate, keys could also be cached on init of the skins
     *     or we could move to some form of ordered map, although that would damage normal render performance.
     * @param viewer
     * @param isPaintBucket
     * @param skinId
     * @return
     */
    public TransportSkin getTextureByID(EntityPlayer viewer, boolean isPaintBucket, String skinId){
        return getSkinList(viewer, isPaintBucket).get(skinId);
    }

    /**
     * Method to allow entities to override TransportSkin interactions.
     * for example, only allowing a specific player to apply a TransportSkin from the paint bucket,
     *     or returning a different TransportSkin during render based on the transport's state.
     *
     * If the player is null, then the call is being made for saving and loading, and usually should not be modified.
     * When the player is null, isPaintBucket is false, so that allows null checks to be skipped by checking the bool first, in most cases.
     */
    public Map<String, TransportSkin> getSkinList(EntityPlayer viewer, boolean isPaintBucket){
        return SkinRegistry.getTransportSkins(getClass());
    }

    /**
     * return the name for the default TransportSkin of the transport.
     */
    public String getDefaultSkin(){
        return getSkinList(null,false)==null?"":getSkinList(null, false).keySet().iterator().next();}

    public List<ParticleFX> getParticles(){
        return renderData.particles;
    }


    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double p_70112_1_)
    {
        return p_70112_1_ > 1D;
    }


    /*
     * <h1>Inventory management</h1>
     */

    /**
     * <h2>inventory size</h2>
     * @return the number of slots the inventory should have.
     * if it's a train we have to calculate the size based on the type and the size of inventory its supposed to have.
     * trains get 1 extra slot by default for fuel, steam and nuclear steam get another slot, and if it can take passengers there's another slot, this is added to the base inventory size.
     * if it's not a train or rollingstock, then just return the base amount for a crafting table.
     */
    @Override
    public int getSizeInventory() {
        return inventory==null?0:inventory.size();
    }

    /**
     * <h2>get item</h2>
     * @return the item in the requested slot
     */
    @Override
    public ItemStack getStackInSlot(int slot) {
        if (inventory == null || slot <0 || slot >= inventory.size()){
            return null;
        } else {
            return inventory.get(slot).getStack();
        }
    }

    public ItemStackSlot getSlotIndexByID(int id){
        for(ItemStackSlot s : inventory){
            if (s.getSlotID() == id){
                return s;
            }
        }
        return null;
    }

    /**
     * <h2>decrease stack size</h2>
     * @return the itemstack with the decreased size. If the decreased size is equal to or less than the current stack size it returns null.
     */
    @Override
    public ItemStack decrStackSize(int slot, int stackSize) {
        if (inventory!= null && getSizeInventory()>=slot) {
            return inventory.get(slot).decrStackSize(stackSize);
        } else {
            return null;
        }
    }

    /**
     * <h2>Set slot</h2>
     * sets the slot contents, this is a direct override so we don't have to compensate for anything.
     */
    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        if (inventory != null && slot >=0 && slot < getSizeInventory()) {
            inventory.get(slot).setSlotContents(itemStack,inventory);
        }
    }

    /**
     * <h2>name and stack limit</h2>
     * These are grouped together because they are pretty self-explanatory.
     */
    @Override
    public String getInventoryName() {return transportName() + ".storage";}
    @Override
    public boolean hasCustomInventoryName() {return inventory != null;}
    @Override
    public int getInventoryStackLimit() {return inventory!=null?64:0;}
    @Override
    public ItemStack getCartItem(){
        return new ItemStack(getItem(),1);
    }


    /**returns the type of transport, this is planned to be removed in favor of a few more methods.
     * for a list of options:
     * @see TrainsInMotion.transportTypes
     * may not return null.*/
    public List<TrainsInMotion.transportTypes> getTypes(){return TrainsInMotion.transportTypes.SLUG.singleton();}

    /**
     * <h2>is Locked</h2>
     * returns if the entity is locked, and if it is, if the player is the owner.
     * This makes sure the inventory can be accessed by anyone if its unlocked and only by the owner when it is locked.
     * if it's a tile entity, it's just another null check to be sure no one crashes.
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {return getPermissions(p_70300_1_, false, false);}

    /**
     * <h2>filter slots</h2>
     * used to filter inventory slots for specific items or data.
     * @param slot the slot that yis being interacted with
     * @param itemStack the stack that's being added
     * @return whether or not it can be added
     */
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if (itemStack == null){return true;}
        //compensate for specific rollingstock
        for(TrainsInMotion.transportTypes type : getTypes()){
            if(type==LOGCAR && (CommonUtil.isLog(itemStack) || CommonUtil.isPlank(itemStack))){
                return true;
            }
            if (type==COALHOPPER && CommonUtil.isCoal(itemStack)){
                return true;
            }
            if (type==STEAM){
                if (slot == 400) {
                    return TileEntityFurnace.getItemBurnTime(itemStack) > 0;
                } else if (slot ==401) {
                    return FuelHandler.getUseableFluid(itemStack, this) != null;
                }
            }
            if ((type==ELECTRIC || type == DIESEL) && slot==400){
                return FuelHandler.getUseableFluid(itemStack, this) != null;
            }
        }
        return true;
    }

    /**
     * <h2>Add item to train inventory</h2>
     * custom function for adding items to the train's inventory.
     * similar to a container's TransferStackInSlot function, this will automatically sort an item into the inventory.
     * if there is no room in the inventory for the item, it will drop on the ground.
     */
    public void addItem(ItemStack item){
        for(ItemStackSlot slot : inventory){
            item = slot.mergeStack(item,inventory,0);
            if (item == null){
                markDirty();
                return;
            }
        }
        entityDropItem(item, item.stackSize);
        markDirty();
    }

    /**
     * <h2>inventory percentage count</h2>
     * calculates percentage of inventory used then returns a value based on the intervals.
     * for example if the inventory is half full and the intervals are 100, it returns 50. or if the intervals were 90 it would return 45.
     */
    public int calculatePercentageOfSlotsUsed(int indexes){
        if (inventory == null){
            return 0;
        }
        float i=0;
        for (ItemStackSlot item : inventory){
            if (item.getHasStack()){
                i++;
            }
        }
        return i>0?(int)(((i / getSizeInventory()) *indexes)+0.5):0;
    }


    /**
     * <h2>get an item from inventory to render</h2>
     * cycles through the items in the inventory and returns the first non-null item that's index is greater than the provided number.
     * if it fails to find one it subtracts one from the index and tries again, and keeps trying until the index is negative, in which case it returns 0.
     */
    public ItemStack getFirstBlock(int index){
        for (int i=0; i<getSizeInventory(); i++){
            if (i>= index && inventory.get(i) != null && inventory.get(i).getHasStack() &&
                    inventory.get(i).getItem() instanceof ItemBlock){
                return inventory.get(i).getStack();
            }
        }
        return index>0?getFirstBlock(index-1):null;
    }

    /*
     * <h2>unused</h2>
     * we have to initialize these values, but due to the design of the entity we don't actually use them.
     */
    /**used to sync the inventory on close.*/
    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return inventory==null || inventory.size()<p_70304_1_?null:inventory.get(p_70304_1_).getStack();
    }
    @Override
    public void markDirty() {
        if(forceBackupTimer==0) {
            forceBackupTimer = 30;
        }
        for (ItemStackSlot slot : inventory){
            entityData.putItemStack("inv."+slot.getSlotID(), slot.getStack());
        }

        if(syncTimer==-1){
            syncTimer=this instanceof EntityTrainCore?20:60;
        }

    }
    /**called when the inventory GUI is opened*/
    @Override
    public void openInventory() {
        if(!getWorld().isRemote){
            entityData.buildXML();
            for(String key : entityData.itemMap.keySet()){
                getSlotIndexByID(Integer.parseInt(key.substring(4))).setStack(entityData.getItemStack(key));
            }
        }
    }
    /**called when the inventory GUI is closed*/
    @Override
    public void closeInventory() {
        if(!getWorld().isRemote) {
            markDirty();
        }
    }

    public void dropAllItems() {
        if (inventory != null) {
            for (ItemStackSlot slot : inventory) {
                if (slot.getStack() != null) {
                    this.entityDropItem(slot.getStack(), 1);
                    slot.setSlotContents(null,null);
                }
            }
        }
    }


    /*
     * <h1>Fluid Management</h1>
     */
    //attempt to drain a set amount
    //todo maybe this should cover all tanks...?
    @Override
    public FluidStack drain(@Nullable ForgeDirection from, int drain, boolean doDrain){
        return drain(from, new FluidStack(TiMFluids.nullFluid,drain), doDrain);
    }


    @Override
    public boolean canPassFluidRequests(Fluid fluid){
        return canDrain(null, fluid) || canFill(null, fluid);
    }

    @Override
    public boolean canAcceptPushedFluid(EntityMinecart requester, Fluid fluid){
        return canFill(null, fluid);
    }

    @Override
    public boolean canProvidePulledFluid(EntityMinecart requester, Fluid fluid){
        return canDrain(ForgeDirection.UNKNOWN, fluid);
    }

    @Override
    public void setFilling(boolean filling){}

    /**Returns true if the given fluid can be extracted.*/
    @Override
    public boolean canDrain(@Nullable ForgeDirection from, Fluid resource){
        for(int i=0;i<getTankCapacity().length;i++) {
            if (entityData.getFluidStack("tanks."+i).amount > 0 && (resource == null || entityData.getFluidStack("tanks."+i).getFluid() == resource)) {
                return true;
            }
        }
        return false;
    }
    /**Returns true if the given fluid can be inserted into the fluid tank.*/
    @Override
    //TODO: rework this to work more similar to the fill function
    public boolean canFill(@Nullable ForgeDirection from, Fluid resource){
        return true;
    }

    /**drain with a fluidStack, this is mostly a redirect to
     * @see #drain(ForgeDirection, int, boolean) but with added filtering for fluid type.
     */
    @Override
    public FluidStack drain(@Nullable ForgeDirection from, FluidStack resource, boolean doDrain){
        int leftoverDrain=resource.amount;
        FluidStack stack;
        for(int i=0;i<getTankCapacity().length;i++) {
            stack=entityData.getFluidStack("tanks."+i);
            if (stack.amount > 0 && (resource.getFluid()==TiMFluids.nullFluid || stack.getFluid() == resource.getFluid())) {
                if(leftoverDrain>stack.amount){
                    leftoverDrain-=stack.amount;
                    if(doDrain){
                        entityData.putFluidStack("tanks."+i,new FluidStack(stack.getFluid(),0));
                        markDirty();
                    }
                } else {
                    if(doDrain){
                        entityData.putFluidStack("tanks."+i,new FluidStack(stack.getFluid(),stack.amount-leftoverDrain));
                        markDirty();
                    }
                    return null;
                }
            }
        }
        return resource;

    }

    public int drain(@Nullable ForgeDirection from, int tankID, int amount, boolean doDrain){
        int leftoverDrain=amount;
        FluidStack stack = entityData.getFluidStack("tanks."+tankID);
        if (stack!=null && stack.amount > 0) {
            if(leftoverDrain>stack.amount){
                leftoverDrain-=stack.amount;
                if(doDrain){
                    entityData.putFluidStack("tanks."+tankID,new FluidStack(TiMFluids.nullFluid,0));
                    markDirty();
                }
            } else {
                if(doDrain){
                    entityData.putFluidStack("tanks."+tankID,new FluidStack(stack.getFluid(),stack.amount-leftoverDrain));
                    markDirty();
                }
                return 0;
            }
    }
    return leftoverDrain;

    }

    /**checks if the fluid can be put into the tank, and if doFill is true, will actually attempt to add the fluid to the tank.
     * @return the amount of fluid that was or could be put into the tank.*/
    @Override
    public int fill(@Nullable ForgeDirection from, FluidStack resource, boolean doFill){
        if(getTankCapacity()==null){return resource.amount;}
        int leftoverDrain=resource.amount;
        FluidStack fluid;
        for(int stack =0; stack<getTankCapacity().length;stack++) {
            if(getTankFilters()!=null && getTankFilters()[stack]!=null) {
                boolean check=false;
                for (String filter : getTankFilters()[stack]) {
                    if (filter.length()==0 || CommonUtil.stringContains(filter,resource.getFluid().getName())){
                        check=false;
                        break;
                    } else {
                        check=true;
                    }
                }
                if(check){
                    continue;
                }
            }
            if (entityData.containsFluidStack("tanks."+stack)&& (
                    resource==null || resource.getFluid() == null ||
                            entityData.getFluidStack("tanks."+stack)==null ||
                            entityData.getFluidStack("tanks."+stack).getFluid() == resource.getFluid() ||
                            entityData.getFluidStack("tanks."+stack).amount ==0)) {
                fluid=entityData.getFluidStack("tanks."+stack);

                if(leftoverDrain+fluid.amount>getTankCapacity()[stack]){
                    leftoverDrain-=getTankCapacity()[stack]-fluid.amount;
                    if(doFill){
                        entityData.putFluidStack("tanks."+stack,new FluidStack(resource.getFluid(),getTankCapacity()[stack]));
                        markDirty();
                    }
                } else if (leftoverDrain+fluid.amount<0){
                    leftoverDrain-=fluid.amount-resource.amount;
                    if(doFill){
                        entityData.putFluidStack("tanks."+stack,new FluidStack(resource.getFluid(),0));
                        markDirty();
                    }
                } else {
                    if(doFill){
                        entityData.putFluidStack("tanks."+stack,new FluidStack(resource.getFluid(),fluid.amount+leftoverDrain));
                        markDirty();
                    }
                    leftoverDrain=0;
                }
                if(leftoverDrain==0){
                    return 0;
                }
            }
        }
        return leftoverDrain;
    }

    /**
     * forced fill method, attempts to fill containers with the entire amount.
     * this is mainly used by the fuel handler as a shorthand but can be manually referenced by other things
     * @param from the direction to fill from, normally doesn't make a difference, can be null
     * @param resource the fluid to fill with
     * @return true if the tank was able to fill with the entire stack, false if not.
     */
    public boolean fill(@Nullable ForgeDirection from, FluidStack resource){
        if(getTankCapacity()==null || resource==null ||resource.amount<1){DebugUtil.println("no tanks?");return false;}
        for(int stack =0; stack<getTankCapacity().length;stack++) {
            if(getTankFilters()!=null && getTankFilters()[stack]!=null) {
                boolean check=false;
                for (String filter : getTankFilters()[stack]) {
                    if (filter.length()==0 || CommonUtil.stringContains(filter,resource.getFluid().getName())){
                        check=false;
                        break;
                    } else {
                        check=true;
                    }
                }
                if(check){
                    continue;
                }
            }
            if (entityData.containsFluidStack("tanks."+stack) && (
                    resource.getFluid() == null || entityData.getFluidStack("tanks."+stack).getFluid() == resource.getFluid() ||
                            entityData.getFluidStack("tanks."+stack).amount ==0)) {
                if(resource.amount+entityData.getFluidStack("tanks."+stack).amount<=getTankCapacity()[stack]){
                    entityData.putFluidStack("tanks."+stack,new FluidStack(resource.getFluid(),
                            entityData.getFluidStack("tanks."+stack).amount+resource.amount));
                    markDirty();
                    return true;
                }
            }
        }
        return false;
    }

    /**returns the list of fluid tanks and their capacity. READ ONLY!!!*/
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from){
        //todo: what the crap, this doesn't add tanks to XML. it's supposed to add tanks to XML.
        if(getTankCapacity()==null || getTankCapacity().length ==0){
            return new FluidTankInfo[]{};
        }
        //force build XML, just to be sure
        entityData.buildXML();
        //if it's not initialized, do stuff
        if (entityData.fluidMap.size()<getTankCapacity().length) {
            //initialize tanks
            for (int i = 0; i < getTankCapacity().length; i++) {
                entityData.putFluidStack("tanks."+i,new FluidStack(FluidRegistry.WATER, 0));
            }
        }

        //generate return value.
        FluidTankInfo[] tanks = new FluidTankInfo[getTankCapacity().length];
        for(int i=0;i<getTankCapacity().length;i++){
            tanks[i]= new FluidTankInfo(entityData.getFluidStack("tanks."+i),getTankCapacity()[i]);
        }
        return tanks;
    }

    /*
     * <h3> chunk management</h3>
     * small chunk management for the entity, most all of it is handled in
     * @see ChunkHandler
     */

    /**@return the chunk ticket of this entity*/
    public ForgeChunkManager.Ticket getChunkTicket(){return chunkTicket;}
    /**sets the chunk ticket of this entity to the one provided.*/
    public void setChunkTicket(ForgeChunkManager.Ticket ticket){chunkTicket = ticket;}

    /**attempts to get a ticket for chunkloading, sets the ticket's values*/
    private void requestTicket() {
        ForgeChunkManager.Ticket ticket = ForgeChunkManager.requestTicket(TrainsInMotion.instance, getWorld() , ForgeChunkManager.Type.ENTITY);
        if(ticket != null) {
            ticket.bindEntity(this);
            setChunkTicket(ticket);
        }
    }

    /*
     * <h2>Inherited variables</h2>
     * these functions are overridden by classes that extend GenericRailTransport, or EntityTrainCore so that way the values can be changed indirectly.
     */

    /*
    <h1>Bogies and models</h1>
    */

    /**returns the x/y/z offset each bogie should render at, with 0 being the entity center, in order with getBogieModels
     * example:
     * return new float[][]{{x1,y1,z1},{x2,y2,z2}, etc...};
     * may return null.*/
    @Deprecated
    @SideOnly(Side.CLIENT)
    public float[][] bogieModelOffsets(){return null;}

    /**returns a list of models to be used for the bogies
     * example:
     * return new ModelBase[]{new MyModel1(), new myModel2(), etc...};
     * may return null. */
    @Deprecated
    @SideOnly(Side.CLIENT)
    public ModelBase[] bogieModels(){return null;}


    @SideOnly(Side.CLIENT)
    public Bogie[] bogies(){
        if(bogieModelOffsets()==null || bogieModels()==null){return null;}
        Bogie[] ret = new Bogie[bogieModelOffsets().length];
        for(int i=0; i<bogieModelOffsets().length;i++){
            if(i>=bogieModels().length){
                ret[i] = new Bogie(bogieModels()[0], -bogieModelOffsets()[i][0],bogieModelOffsets()[i][1],bogieModelOffsets()[i][2]);
            } else {
                ret[i] = new Bogie(bogieModels()[i], -bogieModelOffsets()[i][0],bogieModelOffsets()[i][1],bogieModelOffsets()[i][2]);
            }
        }
        return ret;
    }

    /**defines the points that the entity uses for path-finding and rotation, with 0 being the entity center.
     * Usually the point where the front and back bogies would connect to the transport.
     * Or the center of the frontmost and backmost wheel if there are no bogies.
     * The first value is the back point, the second is the front point
     * example:
     * return new float{2f, -1f};
     * may not return null*/
    public float[] rotationPoints(){return bogieLengthFromCenter();}

    /**
     * this method has been replaced by
     * @see GenericRailTransport#rotationPoints()
     */
    public float[] bogieLengthFromCenter(){return new float[]{1,-1};}

    /**No longer used, replaced by
     * @see #getAnimationData(int)
     * defines the radius from center in microblocks that the pistons animate, if there are any.*/
    @Deprecated
    public float getPistonOffset(){return 0;}

    /**defines the scale to render the model at. Default is 0.0625*/
    public float getRenderScale(){return 0.0625f;}

    /**defines the scale to render the model at. Default is 0.65*/
    public float getPlayerScale(){return 0.65f;}

    /**returns the x/y/z offset each model should render at, with 0 being the entity center, in order with getModels
     * example:
     * return new float[][]{{x1,y1,z1},{x2,y2,z2}, etc...};
     * may return null.*/
    @SideOnly(Side.CLIENT)
    public float[][] modelOffsets(){return null;}


    /**returns the x/y/z rotation each model should render at in degrees, in order with getModels
     * example:
     * return new float[][]{{x1,y1,z1},{x2,y2,z2}, etc...};
     * may return null.*/
    @SideOnly(Side.CLIENT)
    public float[][] modelRotations(){return null;}

    /**event is to add skins for the model to the skins registry on mod initialization.
     * this function can be used to register multiple skins, one after another.
     * example:
     * SkinRegistry.addSkin(this.class, MODID, "folder/mySkin.png", new int[][]{{oldHex, newHex},{oldHex, newHex}, etc... }, displayName, displayDescription);
     * the int[][] for hex recolors may be null.
     * hex values use "0x" in place of "#"
     * "0xff00aa" as an example.
     * the first TransportSkin added to the registry for a transport class will be the default
     * additionally the addSkin function may be called from any other class at any time.
     * the registerSkins method is only for organization and convenience.*/
    public void registerSkins(){}

    /**returns a list of models to be used for the transport
     * example:
     * return new MyModel();
     * may return null. */
    @SideOnly(Side.CLIENT)
    public ModelBase[] getModel(){return null;}


    /*
    <h1>riders and interaction</h1>
    */

    /**defines the rider position offsets, with 0 being the center of the entity.
     * Each set of coords represents a new rider seat, with the first one being the "driver"
     * example:
     * return new float[][]{{x1,y1,z1},{x2,y2,z2}, etc...};
     * may return null*/
    public float[][] getRiderOffsets(){return null;}

    /**returns the size of the hitbox in blocks.
     * example:
     * return new float[]{x,y,z};
     * may not return null*/
    public float[] getHitboxSize(){return new float[]{3,1.5f,0.21f};}

    /**defines if the transport is immune to explosions*/
    public boolean isReinforced(){return false;}


    /*
    <h1> inventory and fluid tanks </h1>
    */

    /**defines the size of the inventory row by row, not counting any special slots like for fuel.
     * end result number of slots is this times 9. plus any crafting/fuel slots
     * may not return null*/
    public int getInventoryRows(){return 0;}

    /**defines the capacity of the fluidTank tank.
     * each value defibes another tank.
     * Usually value is 1,000 *the cubic meter capacity, so 242 gallons, is 0.9161 cubic meters, which is 916.1 tank capacity
     * mind you one water bucket is values at 1000, a full cubic meter of water.
     *example:
     * return new int[]{11000, 1000};
     * may return null*/
    public int[] getTankCapacity(){return null;}

    /** defines the whitelist of fluid names for fluid tanks in order.
     * null will accept any fluid.
     * example:
     * return new String[][]{{"water", "diesel"}, {"lava"}, null}*/
    public String[][] getTankFilters(){
        if(getTypes()==null){return null;}
        //multi types first
        if(getTypes().contains(DIESEL) && getTypes().contains(ELECTRIC)){
            return FuelHandler.DefaultTanks.DIESEL_ELECTRIC.value();
        }
        //then handle for individuals.
        else if(getTypes().contains(DIESEL)){
            return FuelHandler.DefaultTanks.DIESEL.value();
        } else if (getTypes().contains(STEAM)){
            return FuelHandler.DefaultTanks.STEAM.value();
        } else if(getTypes().contains(ELECTRIC)){
            return FuelHandler.DefaultTanks.ELECTRIC.value();
        }
        return null;
    }


    /**this function allows individual trains and rollingstock to implement custom fuel consumption and management
     * you can call one of the existing methods in the FuelHandler class:
     * manageSteam, manageElectric, manageDiesel
     * you may also leave it empty if you don't plan to use it.
     * for more detail on implementing custom versions, take a look at the existing ones, for example:
     * @see FuelHandler#manageSteam(EntityTrainCore) for an example*/
    public void manageFuel(){
        if(getTypes().contains(TANKER)){
            FuelHandler.manageTanker(this);
        }
    }

    /** returns the max fuel.
     * for steam trains this is cubic meters of the firebox size. (1.5 on average)
     * for diesel this is cubic meters of the fuel tank. (11.3 on average)
     * for electric this is Kw. (400 on average)*/
    public float getMaxFuel(){return 0;}


    /**
     * returns an array of integers for lamp effects.
     * the first is density.
     * the second is scale in percentage.
     * the third is color in hex.
     * todo: the fourth is speed in percentage (does not apply to cone or sphere lamps)
     * NOTE: you can use the method getCurrentSkin() to return different results based on the current TransportSkin.
     * @param id the index of the particle defined in the model
     */
    @SideOnly(Side.CLIENT)
    public int[] getParticleData(int id){
        switch (id){
            case 0:{return new int[]{3, 100, 0x232323};}//smoke
            case 1:{return new int[]{5, 100, 0x232323};}//heavy smoke
            case 2:{return new int[]{2, 100, 0xEEEEEE};}//steam
            case 3:{return new int[]{6, 100, 0xCECDCB};}//led lamp
            case 4:{return new int[]{3, 50, 0xCC0000};}//reverse lamp
            case 5:{return new int[]{3, 10, 0xCCCC00};}//small sphere lamp

            default:{return new int[]{5, 100, 0xCCCC00};}//lamp
        }
    }

    /**
     * returns an array of strings to define particles from the entity class.
     * an example is: "smoke ,0,24,55.5,12,0,0,0"
     * each value is separated by commas.
     * the first part is the tag
     * @see ebf.tim.render.AnimList
     * do NOT reference AnimList directly from inside the entity class.
     * the second is a number that defines the part it will be relative to,
     *     with 0 being the main model, and each one after that representing the bogies in order that they are defined.
     * the rest are the position data with optional decimal values, the order is
     * X,Y,Z, Pitch, Yaw, Roll
     */
    @SideOnly(Side.CLIENT)
    public String[] setParticles(){return null;}

    /**
     * returns an array of floats for animations with offsets:
     * the first is direction in degrees on the X/Y axis from center.
     * the second is distance on the X axis in microblocks from center.
     * the third is  distance on the Z axis in microblocks from center.
     * NOTE: you can use the method getCurrentSkin() to return different results based on the current TransportSkin.
     * @param id the index of the effect defined in the model
     */
    @SideOnly(Side.CLIENT)
    public float[] getAnimationData(int id) {
        switch (id) {
            case 1:{return new float[]{90, 40, 0};}//valve gear up position
            case 2:{return new float[]{270, 40, 0};}//valve gear back position
            case 3:{return new float[]{180, 40, 0};}//valve gear down position
            case 4:{return new float[]{0, 40, 0};}//valve gear forward position

            default:{return new float[]{0,0,0};}
        }
    }


    /**defines the weight of the transport.*/
    public float weightKg(){return 907.18474f;}

    /**defines the recipe in order from topleft to bottom right.
     * example:
     * return new ItemStack[]{new ItemStack(Blocks.dirt, 2), new ItemStack(Blocks.glass,1), etc};
     * array must contain 9 values. may not return null.*/
    public ItemStack[] getRecipe(){return getRecipie();}

    @Deprecated //old method for legacy support, move to #getRecipe()
    public ItemStack[] getRecipie(){
        return new ItemStack[]{
                new ItemStack(Blocks.dirt),null,null,null,null,null,null,null,null
        };
    }

    /**Both decides whether to use Traincraft's assemblytables or TiM's traintable for the crafting of this transport.
     * Return 0 (or don't override this at all - default is 0) to use TiM's traintable.
     * Return 1, 2 or 3 to use the corresponding tier of assemblytable.
     *
     * @return either 1, 2, or 3 corresponding to which table should be able to craft it, or 0, to use the TiM traintable instead.
     */
    public Block getCraftingTable() {
        return TCBlocks.trainTableTier1;
    }

    /**defines the name used for registration and the default name used in the gui.*/
    public String transportName(){return "Fugidsnot";}
    /**defines the country of origin for the transport*/
    public String transportcountry(){return "Nowhere";}
    /**the year or year range to display for the transport.*/
    public String transportYear(){return "19 somethin'";}

    /**the fuel type to display for the transport.*/
    public String transportFuelType(){return null;}

    /**the top speed in km/h for the transport.
     * not used tor rollingstock.*/
    public float transportTopSpeed(){return 0;}
    /**the top speed in km/h for the transportwhen moving in reverse, default is half for diesel and 75% for others.
     * not used tor rollingstock.*/
    public float transportTopSpeedReverse(){return getTypes().contains(DIESEL)?transportTopSpeed()*0.5f:transportTopSpeed()*0.75f;}
    /**displays in item lore if the transport is fictional or not*/
    public boolean isFictional(){return true;}
    /**the tractive effort for the transport, this is a fallback if metric horsepower (mhp) is not available*/
    public float transportTractiveEffort(){return 0;}
    /**this is the default value to define the acceleration speed and pulling power of a transport.*/
    public float transportMetricHorsePower(){return 0;}

    /**This defines the acceleration rate in meters per second
     * the example code is a little long to add more realistic scaling without needing to know any real specifics*/
    public float transportAcceleration(){
        if(transportTopSpeed()==0){return 0;}//efficiency shorthand for rollingstock.

        //the n700 is noted to go 0 to 60(96.56km/h) in 37 seconds.
        //if we assume a little high,to 45, that makes it 96.56/45=2.14579 km/s per second acceleration or
        // 2145.79 meters per second.
        if(getVelocity()<transportTopSpeed()*0.25){
            return 2145.79f;
        } else if (getVelocity()<transportTopSpeed()*0.5){
            return 2467.659f;//typically middle-speeds are geared for higher acceleration since less torque is needed
        } else if (getVelocity()<transportTopSpeed()*0.85){
            return 2145.79f;//return the normal amount for higher speeds like a bit of a bell curve
        } else {
            return 1287.4746f;//returns about 60% at the top of the gear since you're bottoming out in the transmission
        }
    }

    /**additional lore for the item, each entry in the array is a new line.
     * return null if unused.*/
    public String[] additionalItemText(){return null;}

    /**returns the item of this transport, this should be a static value in the transport's class.
     * example:
     * public static final Item thisItem = new ItemTransport(new EntityThis(null));
     * Item getItem(){return thisItem;}
     * may not return null*/
    public Item getItem(){return null;}

}