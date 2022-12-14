package ebf.tim.entities;

import ebf.tim.registry.NBTKeys;
import ebf.tim.utility.CommonProxy;
import ebf.tim.utility.CommonUtil;
import ebf.tim.utility.FuelHandler;
import fexcraft.tmt.slim.Vec3d;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import train.library.EnumSounds;
import train.library.Info;

import java.util.List;
import java.util.UUID;

import static ebf.tim.TrainsInMotion.transportTypes.*;

/**
 * <h1>Train core</h1>
 * this is the management core for all trains.
 * @author Eternal Blue Flame
 */
public class EntityTrainCore extends GenericRailTransport {

    /**manages the items for burnHeat, and the burnHeat itself.*/
    public FuelHandler fuelHandler = new FuelHandler();
    /**defines the speed percentage the user is attempting to apply.*/
    public int accelerator =0;




    /*
     * <h2> Base train Constructor</h2>
     */

    /** default constructor for all trains, server only.
     * Usually this is the one you would reference unless you need to do something only on client.
     * @param owner the owner profile, used to define owner of the entity,
     * @param world the world to spawn the entity in, used in super's super.
     * @param xPos the x position to spawn entity at, used in super's super.
     * @param yPos the y position to spawn entity at, used in super's super.
     * @param zPos the z position to spawn entity at, used in super's super.
     */
    public EntityTrainCore(UUID owner, World world, double xPos, double yPos, double zPos){
        super(owner, world, xPos, yPos, zPos);
    }

    /**default constructor for all trains, client only.
     * use this if you need to do something only on client.
     * @param world the world to spawn the entity in, used in super's super.
     */
    public EntityTrainCore(World world){
        super(world);
    }

    /**loads the entity's save file*/
    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        accelerator = tag.getInteger(NBTKeys.accelerator);
        entityData.putFloat("boilerHeat",tag.getFloat(NBTKeys.transportFuel));

    }

    /**saves the entity to server world*/
    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger(NBTKeys.accelerator, getAccelerator());
        tag.setFloat(NBTKeys.transportFuel, FuelHandler.getBoilerHeat(this));

    }

    @Override
    public void entityInit(){
        super.entityInit();

        if(worldObj!=null) {
            this.dataWatcher.addObject(18, accelerator);//accelerator
            this.updateWatchers = true;
        }
    }

    @Override
    public void initInventorySlots(){
        super.initInventorySlots();
        inventory.add(fuelSlot());

        //see super method's to-do
        if(getTypes().contains(STEAM)){
            inventory.add(waterSlot());
        }
    }


    @Override
    public boolean hasDrag(){return getAccelerator()==0;}


    //gets the throttle position as a percentage with 1 as max and -1 as max reverse
    public float getAcceleratorPercentage(){
        switch (Math.abs(getAccelerator())){
            case 1:{return Math.copySign(0.1f,getAccelerator());}
            case 2:{return Math.copySign(0.25f,getAccelerator());}
            case 3:{return Math.copySign(0.5f,getAccelerator());}
            case 4:{return Math.copySign(0.7f,getAccelerator());}
            case 5:{return Math.copySign(0.875f,getAccelerator());}
            case 6: case 7:{return Math.copySign(1f,getAccelerator());}
            default:{return 0;}
        }
    }

    /**
     * <h2>Calculate speed increase rate</h2>
     * this has a direct relation to GenericRailTransport#applyDrag
     */
    public void calculateAcceleration(){
        //core accel speed from TC4
        float accel=0.02f* getAcceleratorPercentage();
        //buff to match engine type
        if(getTypes().contains(STEAM)){
            accel*=0.35f;
        } else if(getTypes().contains(DIESEL)){
            accel*=0.5f;
        } else if(getTypes().contains(ELECTRIC)){
            accel*=0.7f;
        }
        //scale based on power and velocity
        cachedVectors[2].xCoord=accel* (100f * (float)Math.pow(8f,((2.35f * -(getVelocity()/getPower()))-2.35f)));

        //add back in the speed from last tick, if speed was nulled from going into neutral, get it from the velocity.
        if(cachedVectors[2].yCoord!=0) {
            cachedVectors[2].xCoord += cachedVectors[2].yCoord;
        } else {
            cachedVectors[2].xCoord += getVelocity()*0.99;
        }

        //if speed is greater than top speed from km/h to m/s divided by 20 to get per tick
        if (cachedVectors[2].xCoord < -unRatio(transportTopSpeed())) {
            cachedVectors[2].xCoord = -unRatio(transportTopSpeed());
        } else if (cachedVectors[2].xCoord > unRatio(transportTopSpeedReverse())) {
            cachedVectors[2].xCoord = unRatio(transportTopSpeedReverse());
        }

        //set the last tick speed to this speed.
        cachedVectors[2].yCoord=cachedVectors[2].xCoord;

        //handle ice slipping
        if(accelerator!=8 && accelerator!=-8 && !getBoolean(boolValues.BRAKE)) {
        float slip = !getBoolean(boolValues.DERAILED)?-1.0f:
                CommonUtil.getBlockAt(worldObj,this.posX,this.posY-1,this.posZ).slipperiness;

            if(slip>0) {
                rotationYaw+=accelerator*slip;
                if(slip<6) {
                    cachedVectors[2].yCoord *= slip*0.75;
                }
                cachedVectors[2].xCoord *= slip > 0 ? slip : 0.996;
            }
        }

    }

    //TC4 uses this to scale movement speeds. documented as:
    //speed *= 10;// convert in ms
    //speed *= 6;// applying ratio
    //speed *= 3.6;// convert in km/h
    //unratio needs a +=1 because it always rounds down.
    public static float ratio(float val){return  ((val*6f)*36f)* (CommonProxy.realSpeed?1.25f:1f);}
    public static float unRatio(float val){return  (((val+1)*0.16667f)*0.027778f) * (CommonProxy.realSpeed?1.25f:1f);}

    /**a method to interface getting the accelerator value
     * this is intended for external use like collisions that need to see if the train is in gear from a superclass cast*/
    @Override
    public int getAccelerator(){
        return !worldObj.isRemote?accelerator:getDataWatcher().getWatchableObjectInt(18);
    }

    /**
     * <h2> on entity update</h2>
     * first we have to manage our speed by updating our motion vector and applying it to the bogies.
     * after that we let the super handle things.
     * @see GenericRailTransport#onUpdate()
     * lastly we use a tick check to define if we should manage fuel, since we shouldn't be doing that every tick.
     */
    @Override
    public void onUpdate() {

        super.onUpdate();

        if(frontBogie != null && backBogie != null) {

            if (!worldObj.isRemote) {
                //twice a second, re-calculate the speed.
                if (accelerator!=0 && getBoolean(boolValues.RUNNING)) {
                    if(ticksExisted % 10 == 0) {
                        calculateAcceleration();
                    }
                } else {
                    accelerator = 0;
                    this.dataWatcher.updateObject(18, accelerator);
                }

                if(accelerator==0 && getBoolean(boolValues.BRAKE) && getVelocity()==0){
                    frontBogie.setVelocity(0,0,0);
                    backBogie.setVelocity(0,0,0);
                    cachedVectors[2].xCoord = 0;
                } else {
                    //add drag to the accelerator
                    if(accelerator==0){
                        cachedVectors[2].yCoord*=0.99f;
                    } else {
                        Vec3d velocity = CommonUtil.rotateDistance(cachedVectors[2].xCoord, 0, rotationYaw);
                        frontBogie.setVelocity(velocity.xCoord, 0, velocity.zCoord);
                        backBogie.setVelocity(velocity.xCoord, 0, velocity.zCoord);
                        applyDrag();
                    }
                }

                updatePosition();
            }

        }
        if(whistleDelay>0) {
            whistleDelay--;
        }
    }

    @Override
    public void manageFuel(){
        if(getTypes().contains(STEAM)) {
            fuelHandler.manageSteam(this);
        }
        if(getTypes().contains(DIESEL)){
            FuelHandler.manageDieselFuel(this);
        }
        if(getTypes().contains(ELECTRIC)){
            FuelHandler.manageElectricFuel(this);
        }
    }

    /**the fuel type to display for the transport.*/
    public String transportFuelType(){
            if(getTypes().contains(STEAM)){
                return "Steam";
            }
            if (getTypes().contains(DIESEL)){
                return "Diesel";
            }
            if(getTypes().contains(ELECTRIC)){
                return "Electric";
            }
            return "Magic";
    }


    private int whistleDelay=0;
    public void soundHorn() {
        for (EnumSounds sounds : EnumSounds.values()) {
            if (sounds.getEntityClass() != null && !sounds.getHornString().equals("")&& sounds.getEntityClass().equals(this.getClass()) && whistleDelay == 0) {
                worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getHornString(), sounds.getHornVolume(), 1.0F);
                whistleDelay = 65;
            }
        }
        if(!worldObj.isRemote) {
            List entities = worldObj.getEntitiesWithinAABB(EntityAnimal.class, AxisAlignedBB.getBoundingBox(
                    this.posX - 20, this.posY - 5, this.posZ - 20,
                    this.posX + 20, this.posY + 5, this.posZ + 20));

            for (Object e : entities) {
                if (e instanceof EntityAnimal) {
                    ((EntityAnimal) e).setTarget(this.riddenByEntity==null?this.seats.get(0).getPassenger():riddenByEntity);
                    ((EntityAnimal) e).getNavigator().setPath(null, 0);
                }
            }
        }
    }


    @Override
    public boolean interact(int player, boolean isFront, boolean isBack, int key) {
        if (!super.interact(player, isFront, isBack, key)){
            switch (key){
                case 8:{ //toggle ignition
                    setBoolean(boolValues.RUNNING, !getBoolean(boolValues.RUNNING));
                    updateConsist();
                    return true;
                }case 9:{ //plays a sound on all clients within hearing distance
                    if(whistleDelay==0){
                        soundHorn();
                    }
                    return true;
                }case 2:{ //decrease speed
                    if (getBoolean(boolValues.RUNNING)) {
                        //if a linked transport is running, dont update
                        if(consistLeadID!=null && consistLeadID!=getEntityId()){
                            return true;
                        }
                        if(accelerator<=-6){
                            accelerator=-6;
                        } else {
                            accelerator--;
                        }
                        updateConsist();
                        this.dataWatcher.updateObject(18, accelerator);
                    }
                    return true;
                }case 3:{ //increase speed
                    if (getBoolean(boolValues.RUNNING)) {
                        //if a linked transport is running, dont update
                        if(consistLeadID!=null && consistLeadID!=getEntityId()){
                            return true;
                        }
                        if(accelerator>=6){
                            accelerator=6;
                        } else {
                            accelerator++;
                        }
                        updateConsist();
                        this.dataWatcher.updateObject(18, accelerator);
                    }
                    return true;
                } case 16:{//reset speed
                    if (getBoolean(boolValues.RUNNING)) {
                        //if a linked transport is running, dont update
                        if (consistLeadID != null && consistLeadID != getEntityId()) {
                            return true;
                        }
                        accelerator = 0;
                        updateConsist();
                        this.dataWatcher.updateObject(18, accelerator);
                    }
                    return true;
                } case 11:{//TC control forward
                    if(getBoolean(boolValues.RUNNING)) {
                        accelerator = 7;
                        this.dataWatcher.updateObject(18, accelerator);
                    }
                    return true;
                }case 12:{//TC control reverse
                    if(getBoolean(boolValues.RUNNING)) {
                        accelerator = -7;
                        this.dataWatcher.updateObject(18, accelerator);
                    }
                    return true;
                }case 4: {//TC control, keep speed
                    if(getBoolean(boolValues.RUNNING)) {
                        accelerator = (int)Math.copySign(8,accelerator);
                        this.dataWatcher.updateObject(18, accelerator);
                    }
                    return true;
                }case 14: {//TC control, keep speed
                    if(getBoolean(boolValues.RUNNING)) {
                        accelerator = 0;
                        this.dataWatcher.updateObject(18, accelerator);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getMinecartType(){return 10004;}

    /*
     * <h2>Inherited variables</h2>
     * these functions are overridden by classes that extend this so that way the values can be changed indirectly.
     */
    /**gets the resource location for the horn sound*/
    public ResourceLocation getHorn(){return null;}
    /**gets the resource location for the running/chugging sound*/
    public ResourceLocation getRunningSound(){return null;}

    @Deprecated//use GenericRailTransport#getFuelEfficiency
    public float getEfficiency(){return 1;}

}
