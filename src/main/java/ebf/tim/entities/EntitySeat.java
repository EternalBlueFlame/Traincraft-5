package ebf.tim.entities;


import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import org.lwjgl.input.Keyboard;
import train.client.core.handlers.TCKeyHandler;
import train.common.Traincraft;
import train.common.api.AbstractWorkCart;
import train.common.api.EntityRollingStock;
import train.common.api.Locomotive;
import train.common.core.network.PacketKeyPress;
import train.common.library.GuiIDs;


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
    public Vec3 pos = null;
    public EntityRollingStock parent;
    private boolean controller=false, locomotive=false;

    public EntitySeat(World world) {
        super(world);
    }

    public EntitySeat(World world, double xPos, double yPos, double zPos, double pitch, double yaw, double roll, EntityRollingStock parent, int seatNumber) {
        super(world);
        this.posX = xPos;
        this.posY = yPos;
        this.posZ = zPos;
        this.pos = Vec3.createVectorHelper(xPos,yPos,zPos);

        if (pitch!=0 || yaw !=0) {
            rotation = Vec3.createVectorHelper(pitch, yaw, roll);
        }
        parentId = parent.getEntityId();
        this.seatNumber = seatNumber;
        locomotive=parent instanceof Locomotive;
        this.parent = parent;
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
        if(ticksExisted%40==0) {
            if (parent==null) {
                if (getWorld().getEntityByID(parentId) instanceof EntityRollingStock) {
                    if (getWorld().isRemote) {
                        if (parent == null) {
                            parent = (EntityRollingStock) getWorld().getEntityByID(parentId);
                        }
                        parent.setSeats(this, seatNumber);
                    }
                } else {
                    getWorld().removeEntity(this);
                    this.setDead();
                }
            }
            if (worldObj.isRemote) {
                if (this.parent.seats.size() >= seatNumber+1 && (this.pos != this.parent.seats.get(seatNumber).pos || this.getPassenger() != this.parent.seats.get(seatNumber).getPassenger())) {
                    this.setDead();
                }
            }

        }
        if (riddenByEntity != null && riddenByEntity instanceof EntityPlayer && worldObj.isRemote && this.isControlSeat()) {
            if (TCKeyHandler.inventory.isPressed()) {
                if (this.parent instanceof Locomotive) {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(7));
                } else if (this.parent instanceof AbstractWorkCart) {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(7));
                }
            } else if (TCKeyHandler.furnace.isPressed() && this.parent instanceof AbstractWorkCart) {
                Traincraft.keyChannel.sendToServer(new PacketKeyPress(9));
            }
        }
        if (this.parent instanceof Locomotive && this.isControlSeat() && worldObj.isRemote) {
            ((Locomotive)this.parent).keyHandling();
        }
    }

    @Override
    public boolean shouldRiderSit(){
        if (parent != null) {
            return parent.shouldRiderSit(seatNumber);
        } else if (worldObj.getEntityByID(this.parentId) != null && worldObj.getEntityByID(this.parentId) instanceof EntityRollingStock) {
            parent = (EntityRollingStock) worldObj.getEntityByID(this.parentId);
            return parent.shouldRiderSit(seatNumber);
        } else {
            this.setDead();
            return false;
        }
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
            this.getPassenger().setPosition(this.posX, this.posY+(getWorld().isRemote?(this.getPassenger()==Minecraft.getMinecraft().thePlayer?0.4:-1.3):-1.5), this.posZ);
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
