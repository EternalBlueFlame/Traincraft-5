package train.common.entity;

import ebf.tim.entities.EntitySeat;
import ebf.tim.utility.CommonUtil;
import ebf.tim.utility.DebugUtil;
import fexcraft.tmt.slim.Vec3d;
import mods.railcraft.api.carts.ILinkableCart;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.IMinecartCollisionHandler;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import sun.security.util.Debug;
import train.common.api.AbstractTrains;
import train.common.api.EntityBogie;
import train.common.api.EntityRollingStock;
import train.common.api.Locomotive;
import train.common.core.handlers.LinkHandler;

import java.util.ArrayList;
import java.util.List;

public class EntityHitbox {

    private float longest=0;

    public List<CollisionBox> interactionBoxes = new ArrayList<>();
    public CollisionBox front,back;

    public EntityHitbox(float depth, float height, float width, EntityRollingStock entity){
        if (entity.getWorld()==null){
            return;
        }
        depth *=0.5f;
        width *=0.5f;
        if(Math.abs(depth)>longest){
            longest=Math.abs(depth);
        }
        if(Math.abs(width)>longest){
            longest=Math.abs(width);
        }

        depth*=2;width*=2;
        depth+=entity.getOptimalDistance(null);
        if(height!=-1f) {
            interactionBoxes = new ArrayList<>();
            for (float f = 0; f < depth - (width * 0.25f); f += width) {
                CollisionBox c = new CollisionBox((entity));
                c.boundingBox.setBounds(-width*0.5,0,-width*0.5,
                        width*0.5,height,width*0.5);
                c.setPosition(entity.posX, entity.posY, entity.posZ);
                c.forceSpawn=true;
                interactionBoxes.add(c);
                entity.getWorld().spawnEntityInWorld(c);
                if(front==null){
                    front=c;
                } else{
                    back=c;
                }
            }
            position(entity.posX,entity.posY,entity.posZ,entity.serverRealPitch,entity.getYaw());
        }
    }


    public void position(double x, double y, double z, float pitch, float yaw){
        Vec3d part;
        for(int i=0; i<interactionBoxes.size();i++){
            part = CommonUtil.rotateDistance(-0.25+-interactionBoxes.get(0).host.getOptimalDistance(null)+
                            ((interactionBoxes.get(0).host.getHitboxSize()[0]/interactionBoxes.size())*(i+0.5f)),
                    -pitch, yaw+90).addVector(x,y,z);
            interactionBoxes.get(i).setLocationAndAngles(part.xCoord,part.yCoord,part.zCoord,0,0);
        }
    }

    public void manageCollision(EntityRollingStock host){
        for(Entity e:collidingEntities) {
            //on client we need to push away players.
            if (host.worldObj.isRemote) {
                if (e instanceof EntityPlayer || e instanceof EntityLiving) {
                    double[] motion = CommonUtil.rotatePoint(-0.05, 0,
                            CommonUtil.atan2degreesf(host.posZ - e.posZ, host.posX - e.posX));
                    e.addVelocity(motion[0], 0.05, motion[2]);
                }
            } else {
                if (e instanceof EntityRollingStock) {
                    EntityRollingStock entityOne = ((EntityRollingStock) e);
                    if (entityOne.isAttaching && host.isAttaching) {
                        LinkHandler.addStake(host, entityOne, true);
                        LinkHandler.addStake(entityOne, host, true);
                        return;
                    }
                    double[] motion = CommonUtil.rotatePoint(0.005, 0,
                            CommonUtil.atan2degreesf(e.posZ - host.posZ, e.posX - host.posX));
                    host.addVelocity(-motion[0], 0, -motion[2]);
                    entityOne.addVelocity(motion[0], 0, motion[2]);

                } else if (e instanceof EntityPlayer || e instanceof EntityLiving) {
                    if (host.canBePushed()) {
                        for (AbstractTrains t : host.consist) {
                            if (t.accelerate != 0) {
                                return;
                            }
                        }
                        double[] motion = CommonUtil.rotatePoint(0.005, 0,
                                CommonUtil.atan2degreesf(e.posZ - host.posZ, e.posX - host.posX));
                        host.addVelocity(-motion[0], 0, -motion[2]);
                    }

                    //hurt entity if going fast
                    if (Math.abs(host.motionX) + Math.abs(host.motionZ) > 0.25f) {
                        e.attackEntityFrom(new EntityDamageSource(
                                        host instanceof Locomotive ? "Locomotive" : "rollingstock", host),
                                (float) (Math.abs(host.motionX) + Math.abs(host.motionZ)) * 0.5f);
                    }
                }
            }
        }
    }


    /**
     * AWT methods
     */

    public List<Entity> collidingEntities = new ArrayList<>();
    public List<int[]> collidingBlocks = new ArrayList<>();
    private List[] entities;
    private int x,xMax,z,zMax;

    public void updateCollidingEntities(EntityRollingStock host){
        collidingEntities = new ArrayList<>();
        collidingBlocks = new ArrayList<>();
        if(host==null){return;}

        x = CommonUtil.floorDouble((-longest+host.posX - 16) / 16.0D);
        xMax = CommonUtil.floorDouble((longest+host.posX + 16) / 16.0D);
        z = CommonUtil.floorDouble((-longest+host.posZ - 16) / 16.0D);
        zMax = CommonUtil.floorDouble((longest+host.posZ + 16) / 16.0D);
        for (int i = x; i <= xMax; ++i) {
            for (int j = z; j <= zMax; ++j) {
                if (host.worldObj.getChunkProvider().chunkExists(i,j)) {
                    entities = host.worldObj.getChunkFromChunkCoords(i, j).entityLists;
                    for (List olist: entities) {
                        for(Object obj : olist) {
                            //this shouldn't be possible, but it's forge, sooooo....
                            if(!(obj instanceof Entity)){
                                continue;
                            }
                            //generally we only want to collide with mobs/players/other collision boxes
                            //EntityFX is client only, so we _shouldn't_ have to worry about it..?
                            //we dont collide with passenger entities, we collide with the thing they are on.
                            if(obj instanceof EntitySeat || obj instanceof EntityBogie ||
                                    ((Entity) obj).ridingEntity!=null || obj instanceof CollisionBox) {
                                continue;
                            }
                            //if it's another collision box, be sure it's not the current entity or a linked one
                            if(obj instanceof EntityRollingStock){
                                if(host.worldObj.isRemote || obj==host){
                                    continue;
                                }
                                //make sure not to interact with own consist.
                                if(host.consist.contains(obj)){
                                    continue;
                                }
                            }

                            if(containsEntity((Entity) obj)){
                                this.collidingEntities.add((Entity)obj);
                            }
                        }
                    }

                    //block collisions won't happen on client due to positioning, so there's no reason to check.
                    /*if(host.worldObj.isRemote){
                        continue;
                    }
                    //this is basically a BlockPos for where the block is, so the entity can figure out what to do.
                    // but that's not a 1.7 thing, so we do this heresy to keep code similarities for easier porting
                    for(int k=y; k<yMax;k++) {
                        if (!(CommonUtil.getBlockAt(host.worldObj, i, j, k) instanceof BlockAir)){
                            collidingBlocks.add(new int[]{i,j,k});
                        }
                    }*/
                }
            }
        }
    }


    public boolean containsEntity(Entity e){
        for(CollisionBox box : interactionBoxes){
            //faster calculations for predictable entities
            if(e instanceof EntityPlayer){
                if(Math.abs(e.posX-box.posX)<0.5 && Math.abs(e.posZ-box.posZ)<0.5 && Math.abs(e.posY-box.posY)<2){
                    return true;
                }
            }
            //for whatever reason the collision boxes dont seem to exist in the WORLD, but they do exist in their host.
            if(e instanceof EntityRollingStock){
                for(CollisionBox otherBox : ((EntityRollingStock) e).collisionHandler.interactionBoxes){
                    if(Math.abs(otherBox.posX-box.posX)<0.5 && Math.abs(otherBox.posZ-box.posZ)<0.5 && Math.abs(otherBox.posY-box.posY)<2){
                        return true;
                    }
                }
            }
            //check for X
            if (e.boundingBox.intersectsWith(box.boundingBox.expand(0.4D, e instanceof EntityPlayer ?1.2D:0.4D, 0.4D)))
                return true;
        }
        return false;
    }
}
