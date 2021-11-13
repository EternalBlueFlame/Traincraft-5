package ebf.tim.entities;


import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.blocks.rails.BlockRailCore;
import ebf.tim.utility.CommonUtil;
import ebf.tim.utility.DebugUtil;
import io.netty.buffer.ByteBuf;
import mods.railcraft.api.carts.IMinecart;
import mods.railcraft.api.carts.IRoutableCart;
import mods.railcraft.api.tracks.ITrackSwitch;
import mods.railcraft.api.tracks.ITrackTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * <h1>Bogie Core</h1>
 * this controls the behavior of the bogies in trains and rollingstock.
 * @author Eternal Blue Flame
 */
public class EntityBogie extends EntityMinecart implements IMinecart, IRoutableCart, IEntityAdditionalSpawnData {

    /** used to keep a reference to the parent train/rollingstock.*/
    private int parentId = 0;
    /**defines if this is the front bogie of the transport*/
    private boolean isFront=true;
    /**used to calculate the X/Y/Z velocity based on the direction the rail is facing, similar to how vanilla minecarts work.*/
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
            {{0, 0.5}, {0.5, 0}, {-1, 1}},
            {{0, 0.5}, { -0.5, 0}, {1, 1}},
            {{0, -0.5}, { -0.5, 0}, {1, -1}},
            {{0, -0.5}, {0.5, 0}, {-1, -1}}
    };

    /**cached values for the rail path
     * prevents need to generate a new variable multiple times per tick and reduces GC strain*/
    private double railPathX, railPathZ;
    private double railPathSqrt, motionSqrt, railPathDirection;
    private double railPathX2, railPathZ2;
    private int railMetadata;
    private Block blockNext;
    double[] movementPath, retValue;
    float railmax;
    double[] directionalVelocity;
    /**normally this variable exists already in 1.7, this additional declaration of it is support for 1.8.9+*/
    public float yOffset=0.425f;


    public EntityBogie(World world) {
        super(world);
    }

    /**
     * used to initialize the entity
     * @param parent The EntityID of the parent entity (Must extend GenericRailTransport).
     * @param front whether or not this is the front bogie.
     */
    public EntityBogie(World world, double xPos, double yPos, double zPos, int parent, boolean front) {
        super(world);
        posX = xPos;
        posY = yPos;
        posZ = zPos;
        parentId = parent;
        isFront = front;
    }

    /**Small networking check to add the bogie to the host train/rollingstock. Or to remove the bogie from the world if the host doesn't exist.*/
    @Override
    public void readSpawnData(ByteBuf additionalData) {
        isFront = additionalData.readBoolean();
        parentId = additionalData.readInt();
    }
    /**sends the networking check on spawn/respawn so this can see if it should exist in the first place.*/
    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeBoolean(isFront);
        buffer.writeInt(parentId);
    }

    /**plays a sound during entity movement*/
    @Override
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {}

    /**used by the game to tell different types of minecarts from eachother, this doesnt effect us, so just use something random*/
    @Override
    public int getMinecartType() {
        return 10001;
    }
    /**returns if the cart can make itself move*/
    @Override
    public boolean isPoweredCart() {
        return true;
    }
    /**returns if the cart can be ridden*/
    @Override
    public boolean canBeRidden() {
        return false;
    }
    /**if the cart can be pushed by an entity*/
    @Override
    public boolean canBePushed() {
        return false;
    }
    /**returns if the rider can interact with this, I don't think this makes any difference given the design of the bogie*/
    @Override
    public boolean canRiderInteract() {
        return false;
    }
    /**defines the max speed the minecart can move on a rail that is not an extension of BlockRailBase*/
    @Override
    public float getMaxCartSpeedOnRail() {
        return 1.2F;
    }
    /**defines the update tick of the entity, in this case we rely on the transport to provide that for us, keeps things synced on the chance entities ever get individualized threads*/
    @Override
    public void onUpdate() {

        //client only, update position
        if (this.worldObj.isRemote){
            super.onUpdate();
        }

        if(ticksExisted%40==0 || ticksExisted==0) {
            //be sure to remove this if the parent is null, or in a different castle, I mean world.
            if (worldObj.getEntityByID(parentId) instanceof GenericRailTransport) {
                if (worldObj.isRemote) {
                    ((GenericRailTransport) worldObj.getEntityByID(parentId)).setBogie(this, isFront);
                }
            } else {
                worldObj.removeEntity(this);
            }
        }
    }
    /**returns if this can be collided with, since we don't process collisions, we return false*/
    @Override
    public boolean canBeCollidedWith() {
        return true;
    }
    /**disables reading from NBT*/
    @Override
    public void readFromNBT(NBTTagCompound tag){}
    /**disables writing to NBT, which kills the entity on game end.*/
    @Override
    public void writeToNBT(NBTTagCompound tag){}
    @Override
    public boolean writeToNBTOptional(NBTTagCompound tagCompound){return false;}
    @Override
    public boolean writeMountToNBT(NBTTagCompound tagCompound){return false;}


    /**
     * <h2> movement management</h2>
     * this is modified movement from the super class, should be more efficient, and reliable, but generally does the same thing, minus ability to collide.
     * @see EntityMinecart#onUpdate()
     * Some features are replaced using our own for compatibility with ZoraNoDensha
     * @see CommonUtil
     * returns true or false depending on whether or not it derails from having no rail.
     */
    public boolean minecartMove(GenericRailTransport host, double moveX, double moveZ) {
        //server only
        if(!worldObj.isRemote) {
            //define the yaw from the super
            this.setRotation(host.rotationYaw, host.rotationPitch);

            //prevent moving without motion velocity
            if (Math.abs(moveX) + Math.abs(moveZ) < 0.000001) {
                return true;
            }

            //update old position, add the gravity, and get the block below this,
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;


            int floorX = CommonUtil.floorDouble(this.posX);
            int floorY = CommonUtil.floorDouble(this.posY);
            int floorZ = CommonUtil.floorDouble(this.posZ);

            double velocity = moveX*moveX+moveZ*moveZ;


            Block block = CommonUtil.getBlockAt(worldObj, floorX, floorY, floorZ);
            //todo: if (block instanceof BlockRailCore) {
            //update using spline movement
            //} else if (block instanceof BlockRailBase)

            //update on normal rails
            if (block instanceof BlockRailBase) {
                loopVanilla(velocity, moveX, moveZ, floorX,floorY,floorZ, (BlockRailBase) block);
                //update on ZnD rails, and ones that don't extend block rail base.
                //todo ZnD support, either by jar reference or API update
            //} else if (block instanceof ITrackBase) {
                //update position for ZnD rails.
                //moveBogieZnD(motionX, motionZ, floorX, floorY, floorZ, (ITrackBase) block);
            } else {

                while (velocity>0) {
                    posX+=Math.min(0.35, motionX);
                    posZ+=Math.min(0.35, motionZ);
                    motionX-=Math.min(0.35, motionX);
                    motionZ-=Math.min(0.35, motionZ);
                    velocity -= 0.35;

                    if (CommonUtil.getBlockAt(worldObj, posX, posY,posZ) instanceof BlockAir) {
                        posY--;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void loopVanilla(double velocity, double velocityX, double velocityZ, int floorX, int floorY,int floorZ, BlockRailBase block){
        this.yOffset=(block instanceof BlockRailCore?0.425f:0.3425f);

        //try to adhere to limiter track
        railmax = block.getRailMaxSpeed(worldObj,this,floorX, floorY, floorZ);
        if(railmax!=0.4f){
            velocity=Math.min(velocity,railmax);
        }
        directionalVelocity = new double[]{velocityX,velocityZ};
        railMetadata = block.getBasicRailMetadata(worldObj, this, floorX, floorY, floorZ);
        //actually move
        while (velocity>0) {
            moveBogieVanilla(Math.min(0.35, velocity), floorX, floorZ);
            velocity -= 0.35;

            //update the last used block to the one we just used, if it's actually different.
            if(floorX!=CommonUtil.floorDouble(this.posX) || floorZ != CommonUtil.floorDouble(this.posZ)) {
                floorX = CommonUtil.floorDouble(this.posX);
                floorY = CommonUtil.floorDouble(this.posY);
                floorZ = CommonUtil.floorDouble(this.posZ);
                //handle slope movement before other interactions
                if(!BlockRailBase.func_150049_b_(worldObj, floorX, floorY, floorZ)){
                    this.prevPosY =posY;
                    if(BlockRailBase.func_150049_b_(worldObj, floorX, floorY+1, floorZ)){
                        posY++;
                    } else if (BlockRailBase.func_150049_b_(worldObj, floorX, floorY-1, floorZ)) {
                        posY--;
                    }
                    floorY = CommonUtil.floorDouble(this.posY);
                }

                blockNext = this.worldObj.getBlock(floorX, floorY, floorZ);
                //now loop this again for the next increment of movement, if there is one
                if (blockNext instanceof BlockRailBase) {
                    block = (BlockRailBase) blockNext;
                    //do the rail functions.
                    if(shouldDoRailFunctions()) {
                        block.onMinecartPass(worldObj, this, floorX, floorY, floorZ);
                    }
                    if (block == Blocks.activator_rail) {
                        this.onActivatorRailPass(floorX, floorY, floorZ, (worldObj.getBlockMetadata(floorX, floorY, floorZ) & 8) != 0);
                    }
                    //get the direction of the rail from it's metadata
                    railMetadata = block.getBasicRailMetadata(worldObj, this, floorX, floorY, floorZ);
                }
                //get the direction of the rail from it's metadata
                else if (worldObj.getTileEntity(floorX, floorY, floorZ) instanceof ITrackTile && (((ITrackTile)worldObj.getTileEntity(floorX, floorY, floorZ)).getTrackInstance() instanceof ITrackSwitch)){
                    railMetadata =((ITrackTile)worldObj.getTileEntity(floorX, floorY, floorZ)).getTrackInstance().getBasicRailMetadata(this);//railcraft support
                }
            }
        }
    }


    private void moveBogieVanilla(double currentMotion, int floorX, int floorZ){
        //figure out the current rail's direction
        railPathX = (martix[railMetadata][2][0]);
        railPathZ = (martix[railMetadata][2][1]);
        railPathSqrt = Math.sqrt(railPathX * railPathX + railPathZ * railPathZ);

        if (motionX * railPathX + motionZ * railPathZ < 0.0D) {
            railPathX = -railPathX;
            railPathZ = -railPathZ;
        }

        motionSqrt = Math.sqrt(motionX * motionX + railPathZ * railPathZ);


        if (motionSqrt > 2.0D) {
            motionSqrt = 2.0D;
        }

        retValue = new double[]{railPathX==0.0D?0:motionSqrt * (railPathX / railPathSqrt),
                railPathZ==0.0D?0:motionSqrt * (railPathZ / railPathSqrt)};

        movementPath = new double[]{railPathX==0.0D?0:currentMotion * (railPathX / railPathSqrt),0,
                railPathZ==0.0D?0:currentMotion * (railPathZ / railPathSqrt)};


        //define the rail path again, to center the transport.
        railPathX2 = floorX + 0.5D + martix[railMetadata][0][0];
        railPathZ2 = floorZ + 0.5D + martix[railMetadata][0][1];
        railPathX = (floorX + 0.5D + martix[railMetadata][1][0]) - railPathX2;
        railPathZ = (floorZ + 0.5D + martix[railMetadata][1][1]) - railPathZ2;




        //pick the bigger one
        if (railPathX == 0.0D) {
            railPathDirection = this.posZ - floorZ;
        } else if (railPathZ == 0.0D) {
            railPathDirection = this.posX - floorX;
        } else {
            railPathDirection = ((this.posX - railPathX2) * railPathX + (this.posZ - railPathZ2) * railPathZ) * 2.0D;
        }
        //do the centering movement
        this.posX = (railPathX2 + railPathX * railPathDirection)+movementPath[0];
        this.posZ = (railPathZ2 + railPathZ * railPathDirection)+movementPath[2];
        //endMagic();

        motionX=retValue[0];
        motionZ=retValue[1];
    }


    /*
    //todo ZnD support, either by jar reference or API update
    private void moveBogieZnD(double currentMotionX, double currentMotionZ, int floorX, int floorY, int floorZ, ITrackBase track){
        double[][] posVec6 = track.getPositionOnTrack(this);
        posX = posVec6[0][0];
        posY = posVec6[0][1];
        posZ = posVec6[0][2];
        //6[0] is xyz
        //6[1] is rotations
        //System.out.println(track.getDirectionOfSection().toString() + ":::" + track.getOrientation());
    }*/



    /*
     * <h1>Railcraft Functionality</h1>
     */


    /**checks if the parent transport matches the filter for railcraft items, if the filter is null or the parent is null, return false.*/
    @Override
    public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
        if (stack == null || worldObj.getEntityByID(parentId) == null) {
            return false;
        } else {
            Item cartItem = ((GenericRailTransport)worldObj.getEntityByID(parentId)).getItem();
            return cartItem != null && stack.getItem() == cartItem;
        }
    }
    /**returns the gameprofile of the owner for railcraft.*/
    @Override
    public GameProfile getOwner(){
        if (worldObj.getEntityByID(parentId) instanceof GenericRailTransport){
            GenericRailTransport parent = (GenericRailTransport) worldObj.getEntityByID(parentId);
            if (parent.getOwner() != null){
                return parent.getOwner();
            }
        }
        return null;
    }
    /**returns the destination of the parent for railcraft*/
    @Override
    public String getDestination() {
        if (worldObj.getEntityByID(parentId) instanceof GenericRailTransport){
            GenericRailTransport parent = (GenericRailTransport) worldObj.getEntityByID(parentId);
            if (parent.getOwner() != null){
                return parent.destination;
            }
        }
        return "";
    }
    /**this is supposed to set the ticket for the railcraft destination, but we don't use that, so we return false and do nothing.*/
    @Override
    public boolean setDestination(ItemStack ticket) {
        return false;
    }


    /*
     * <h2>Client Movement code</h2>
     */

    /**used to update positioning on client, and update the velocity multiplier*/
    @Override
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int turnProgress) {
        super.setPositionAndRotation2(x,y,z,yaw,pitch,turnProgress);
    }


    @Override
    public void setVelocity(double x, double y, double z) {
        motionX=(((long)(x*100000000))*0.00000001);
        motionY=(((long)(y*100000000))*0.00000001);
        motionZ=(((long)(z*100000000))*0.00000001);


        isAirBorne = true;
    }
    /**used to add to the current velocity movement, also sets this as airborne*/
    @Override
    public void addVelocity(double velocityX, double velocityY, double velocityZ){
        setVelocity(motionX + velocityX, motionY + velocityY, motionZ + velocityZ);
    }
    /**override of the super method just so we can set the position without updating the hitbox, because we don't need to.*/
    @Override
    public void setPosition(double x, double y, double z) {
        posX=(int)(x*1000);
        posX*=0.001;
        posY=(int)(y*1000);
        posY*=0.001;
        posZ=(int)(z*1000);
        posZ*=0.001;
    }


}