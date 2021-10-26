package ebf.tim.entities;

import ebf.tim.registry.NBTKeys;
import ebf.tim.utility.*;
import fexcraft.tmt.slim.Vec3d;
import net.minecraft.nbt.NBTTagCompound;
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
        dataWatcher.updateObject(16, tag.getFloat(NBTKeys.transportFuel));
        vectorCache[1][0] = tag.getFloat(NBTKeys.trainSpeed);


    }
    /**saves the entity to server world*/
    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger(NBTKeys.accelerator, getAccelerator());
        tag.setFloat(NBTKeys.transportFuel, dataWatcher.getWatchableObjectFloat(16));
        tag.setFloat(NBTKeys.trainSpeed, vectorCache[1][0]);

    }

    @Override
    public void entityInit(){
        super.entityInit();
        this.dataWatcher.addObject(18, accelerator);//accelerator
        this.updateWatchers = true;
    }

    @Override
    public void initInventorySlots(){
        super.initInventorySlots();
        inventory.add(fuelSlot());
        if(getTankCapacity().length>1){
            inventory.add(waterSlot());
        }
    }


    @Override
    public boolean hasDrag(){return getAccelerator()==0 || Math.abs(getAccelerator())<6;}

    @Override
    public float getPower(){
        //the average difference between metric horsepower and MHP is about 3.75% or tractiveEffort*26.3=MHP
            return (transportTractiveEffort()<1f?transportMetricHorsePower()
                    :transportTractiveEffort()*0.0035571365f);
    }

    //gets the throttle position as a percentage with 1 as max and -1 as max reverse
    public float getAcceleratiorPercentage(){
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

    private float maxPowerMicroblocks =0;

    @Override
    public void setValuesOnLinkUpdate(List<GenericRailTransport> consist){
        super.setValuesOnLinkUpdate(consist);
        maxPowerMicroblocks =0;
        for(GenericRailTransport t : consist) {
            if(t.getBoolean(boolValues.RUNNING)) {
                maxPowerMicroblocks += t.getPower();
            }
        }
    }

    /**
     * <h2>Calculate speed increase rate</h2>
     */
    public void calculateAcceleration(){
        //speed is defined by the power in newtons divided by the weight, divided by the number of ticks in a second.
        if(getPower() !=0) {
            float weight = pullingWeight* (getBoolean(boolValues.BRAKE)?0.7f:0.007f);
            //update the consist if somehow it didnt get initialized.
            if(maxPowerMicroblocks==0 || pullingWeight==0){
                updateConsist();
                weight = pullingWeight* (getBoolean(boolValues.BRAKE)?0.7f:0.007f);
            }
            weight-=this.weightKg()-1;
            // weight's effect on HP is generally inverse of HP itself, it can be described as
            // 30 lbs of coal about 100 feet in one minute = 33,000 lbf for 1.01387 MHP
            //or roughly 13.6kg over 30.48 meters in one minute = 1MHP
            // however this is for vertical, converting to horizontal means multiplying by around 85% of gravity
            // so say you have a train with 75mhp, that means your carrying capacity sits around
            // 75*1.11039648 tons. (83.279)
            //clamp to a max of the pulling power as to not generate negative pulling power

            //so roughly, we divide the weight by the standard to get the idea of how much MHP is needed
            //1814.3kg/13.6kg=133.4MHP
            //so in this case, we need 133.4MHP to pull a load of 1814.3kg with no hinderance
            //so we then divide the actual MHP by what's needed
            //75*gravityZ/133.4=0.62428
            //this end result means we now can move at 0.62428 of the speed provided by MHP
            //0.62428*0.508=0.317 blocks per second acceleration.

            //store this for later first
            vectorCache[1][2]=vectorCache[1][0];

            // so 1 mhp would normally cover 13.6kg vertically, however this is low friction horizontal
            // in which case we increase by around 70%
            vectorCache[1][0] = (maxPowerMicroblocks*(maxPowerMicroblocks*0.7f));
            //now figure out the percentage of this vs with weight subtracted
            vectorCache[1][1]=vectorCache[1][0]-weight;
            if(vectorCache[1][1]<1){
                //if too much weight, you stall
                vectorCache[1][0]=0;
            } else {
                //this is a mess, but it should reliably scale the speed based on weight pulled
                vectorCache[1][0]= (vectorCache[1][0]-vectorCache[1][1])/vectorCache[1][0];

                //now throw in the transport m/s acceleration, but convert m/s to m/t, (1/20)
                //in theory anyway, but i just threw a bunch of random garbage at this and it seems fine
                vectorCache[1][0]=(transportAcceleration()*0.0000075f)*vectorCache[1][0];

                //todo: accelerator is reverse, for some reason?
                //scale by throttle position
                vectorCache[1][0]*=-getAcceleratiorPercentage();


                if(CommonProxy.realSpeed){
                    vectorCache[1][0]*=0.25f;//nerf to more realistic speeds
                }

                //add back the speed from last tick
                vectorCache[1][0]+=vectorCache[1][2];

                if(CommonProxy.realSpeed){
                    //if speed is greater than top speed from km/h to m/s divided by 20 to get per tick
                    //add a buff to max speed of 0.25
                    if (vectorCache[1][0] < -transportTopSpeed() * (0.277778f*0.05f)*1.25f) {
                        vectorCache[1][0] = -transportTopSpeed() * (0.277778f*0.05f)*1.25f;
                    } else if (vectorCache[1][0] > transportTopSpeedReverse() * (0.277778f*0.05f)*1.25f) {
                        vectorCache[1][0] = transportTopSpeedReverse() * (0.277778f*0.05f)*1.25f;
                    }
                } else {
                    DebugUtil.println(transportTopSpeed(), transportTopSpeedReverse() * (0.277778f*0.05f),
                            -transportTopSpeed() * (0.277778f*0.075f),vectorCache[1][0]);
                    if (vectorCache[1][0] < -transportTopSpeed() * (0.277778f*0.075f)) {
                        vectorCache[1][0] = -transportTopSpeed() * (0.277778f*0.075f);
                    } else if (vectorCache[1][0] > transportTopSpeedReverse() * (0.277778f*0.075f)) {
                        vectorCache[1][0] = transportTopSpeedReverse() * (0.277778f*0.075f);
                    }
                }

            }



            //-4.0880573E-7 applied MHP somehow needs to relate to a value that can move

            //debuff traction for rain
            //vectorCache[1][1]=( (1.75f * (worldObj.isRaining()?0.5f:1)));

            //todo rework this, the math isnt based on newtons anymore.
            if(Math.abs(vectorCache[1][0])*-745.7>vectorCache[1][1]/7457){
                //todo: add sparks to animator.
                //DebugUtil.println("SCREECH","wheelspin: " + (vectorCache[1][0]*-745.7),
                //        "Grip: " + (vectorCache[1][1]/7457), "i really need to get those spark particles in..");
               // vectorCache[1][0] *=0.33f;
            }

        } else {
            updateConsist();
        }
    }
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
                if (accelerator != 0 && ticksExisted % 10 == 0) {
                    //stop calculation if it can't move, running should be managed from the fuel handler, to be more dynamic
                    if (getBoolean(boolValues.RUNNING)) {
                        //skip updating speed on TC style cruise control
                        if(accelerator!=8 && accelerator!=-8 && !getBoolean(boolValues.BRAKE)) {
                            calculateAcceleration();
                        }
                    } else {
                        accelerator = 0;
                        this.dataWatcher.updateObject(18, accelerator);
                    }
                } else if(ticksExisted % 10 == 0){
                    //basically apply normal bogie drag to acceleration
                    if((getVelocity()<0.3) || getBoolean(boolValues.BRAKE)) {
                        vectorCache[1][0] *= 0.95;
                    }
                    vectorCache[1][0] *= 0.996;
                }

                if(accelerator==0 && getBoolean(boolValues.BRAKE) && getVelocity()==0){
                    frontBogie.setVelocity(0,0,0);
                    backBogie.setVelocity(0,0,0);
                    vectorCache[1][0] = 0;
                } else {
                    Vec3d velocity = CommonUtil.rotateDistance(vectorCache[1][0],0, rotationYaw);
                    frontBogie.addVelocity(velocity.xCoord,0,velocity.zCoord);
                    backBogie.addVelocity(velocity.xCoord,0,velocity.zCoord);
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
            syncTimer=20;
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
                    //the second to last value is volume, and idk what the last one is.
                    //worldObj.playSoundEffect(posX, posY, posZ, getHorn().getResourcePath(), 1, 0.5f);
                    if(whistleDelay==0){
                        soundHorn();
                    }
                    return true;
                }case 2:{ //decrease speed
                    if (getBoolean(boolValues.RUNNING)) {
                        //if a linked transport is running, dont update
                        for(GenericRailTransport consist : getConsist()){
                            if(consist!=this && consist.getAccelerator()!=0){
                                return true;
                            }
                        }
                        if(accelerator<=-6){
                            accelerator=-6;
                        } else {
                            accelerator--;
                        }
                        this.dataWatcher.updateObject(18, accelerator);
                    }
                    return true;
                }case 3:{ //increase speed
                    if (getBoolean(boolValues.RUNNING)) {
                        //if a linked transport is running, dont update
                        for(GenericRailTransport consist : getConsist()){
                            if(consist!=this && consist.getAccelerator()!=0){
                                return true;
                            }
                        }
                        if(accelerator>=6){
                            accelerator=6;
                        } else {
                            accelerator++;
                        }
                        this.dataWatcher.updateObject(18, accelerator);
                    }
                    return true;
                }case 11:{//TC control forward
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
    /**gets the multiplication of fuel consumption, 1 is normal, 2 would be double, 1.5 would be halfway between the two, etc.*/
    public float getEfficiency(){return 1;}

}
