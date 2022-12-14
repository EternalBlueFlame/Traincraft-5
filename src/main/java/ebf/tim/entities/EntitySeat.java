package ebf.tim.entities;


import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * <h1>Seat Entity</h1>
 * A placeholder entity to serve as the seats (beyond the first one) for trains and rollingstock.
 * in 1.9+ this class is no longer necessary.
 * @author Eternal Blue Flame
 */
public class EntitySeat extends Entity implements IEntityAdditionalSpawnData {

    /**
     * <h2>variables</h2>
     * parentId is used to keep a reference to the parent train/rollingstock.
     * the velocities are to replace the client only velocities in forge that have private access.
     */
    /**used to keep a reference to the parent train/rollingstock.*/
    public int parentId = 0;
    /**used to define which index the seat is supposed to be at*/
    private int seatNumber =0;

    public Vec3 rotation =null;
    GenericRailTransport parent;
    //private EntityLivingBase passengerEntity=null;
    private boolean controller=false, locomotive=false;

    public EntitySeat(World world) {
        super(world);
    }

    public EntitySeat(World world, double xPos, double yPos, double zPos, double pitch, double yaw, double roll, GenericRailTransport parent, int seatNumber) {
        super(world);
        this.posX = xPos;
        this.posY = yPos;
        this.posZ = zPos;
        if (pitch!=0 || yaw !=0) {
            rotation = Vec3.createVectorHelper(pitch, yaw, roll);
        }
        parentId = parent.getEntityId();
        this.seatNumber = seatNumber;
        controller=seatNumber==0;
        locomotive=parent instanceof EntityTrainCore;
    }

    public World getWorld(){return worldObj;}
    /** returns if this can be pushed*/
    @Override
    public boolean canBePushed() {
        return false;
    }
    /**returns if the rider can interact, it shouldn't be necessary, but we'll leave it true just in case*/
    @Override
    public boolean canRiderInteract() {
        return false;
    }
    /**actually useless for this entity*/
    @Override
    public void entityInit(){}
    /**actually useless for this entity*/
    @Override
    public void onUpdate() {
        if(ticksExisted%40==0 || parent==null) {
            if (getWorld().getEntityByID(parentId) instanceof GenericRailTransport) {
                if (getWorld().isRemote) {
                    if (parent == null) {
                        parent = (GenericRailTransport) getWorld().getEntityByID(parentId);
                    }
                    parent.setseats(this, seatNumber);
                }
            } else {
                getWorld().removeEntity(this);
            }
        }

    }
    @Override
    public boolean shouldRiderSit(){
        return parent.shouldRiderSit(seatNumber);
    }
    /**returns the bounding box, this doesn't handle collisions, soo.. null.*/
    @Override
    public AxisAlignedBB getBoundingBox(){
        return null;
    }
    /**returns the bounding box, this doesn't handle collisions, soo.. null.*/
    @Override
    public AxisAlignedBB getCollisionBox(Entity collidedWith){
        return null;
    }
    /**returns if this can be collided with, but this doesn't handle collisions.*/
    @Override
    public boolean canBeCollidedWith() {
        return false;
    }
    /**reads from NBT but this entity isn't persistent, so, don't,*/
    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {}
    /**writes to NBT but this entity isn't persistent, so, don't,*/
    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {}
    @Override
    public boolean writeToNBTOptional(NBTTagCompound tagCompound){return false;}
    @Override
    public boolean writeMountToNBT(NBTTagCompound tagCompound){return false;}

    /**plays a sound during entity movement*/
    @Override
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {}

    @Override
    public Vec3 getLookVec() {
        return rotation;
    }

    /**
     * <h2>Spawn Data</h2>
     * Small networking check to add the seat to the host train/rollingstock. Or to remove the seat from the world if the host doesn't exist.
     */
    @Override
    public void readSpawnData(ByteBuf additionalData) {
        parentId = additionalData.readInt();
        seatNumber = additionalData.readInt();
        locomotive = additionalData.readBoolean();
        controller=seatNumber==0;
    }
    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(parentId);
        buffer.writeInt(seatNumber);
        buffer.writeBoolean(locomotive);
    }

    @Override
    public void updateRiderPosition() {
        if (this.getPassenger() != null) {
            this.getPassenger().setPosition(this.posX, this.posY+0.4, this.posZ);
        }
    }

    public EntityLivingBase getPassenger(){
        return (EntityLivingBase) this.riddenByEntity;
    }

    //@Override
    public void addPassenger(Entity passenger) {
//        DebugUtil.println(passengerEntity==null, passengerEntity.ridingEntity==null, passenger instanceof EntityLivingBase);
        if(riddenByEntity==null && passenger instanceof EntityLivingBase) {
            //super.addPassenger(passenger);
            this.riddenByEntity=passenger;
            passenger.ridingEntity=this;
            passenger.mountEntity(this);
        }
    }

    //@Override
    public void removePassenger(Entity passenger){
        //super.removePassenger(passenger);
        //passengerEntity=null;
        this.riddenByEntity=null;
        passenger.ridingEntity=null;
    }

    public boolean isControlSeat(){return controller;}
    public boolean isLocoControlSeat(){return controller && locomotive;}
    public boolean isLocoSeat(){return locomotive;}

    public void setControlSeat(){controller=true;}


    /**
     * <h2>Client Movement code</h2>
     * handle this from host entity
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int turnProgress) {
    }
    @Override
    public void setVelocity(double x, double y, double z) {
        motionX = x;
        motionY = y;
        motionZ = z;
    }

    public void setPosition(double p_70107_1_, double p_70107_3_, double p_70107_5_) {
        this.posX = p_70107_1_;
        this.posY = p_70107_3_;
        this.posZ = p_70107_5_;
        float f = this.width / 2.0F;
        float f1 = this.height;
        this.boundingBox.setBounds(p_70107_1_ - (double)f, p_70107_3_ - (double)this.yOffset + (double)this.ySize, p_70107_5_ - (double)f, p_70107_1_ + (double)f, p_70107_3_ - (double)this.yOffset + (double)this.ySize + (double)f1, p_70107_5_ + (double)f);
    }
}
