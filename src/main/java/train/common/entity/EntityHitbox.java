package train.common.entity;

import ebf.tim.entities.EntitySeat;
import ebf.tim.utility.CommonUtil;
import ebf.tim.utility.DebugUtil;
import fexcraft.tmt.slim.Vec3d;
import fexcraft.tmt.slim.Vec3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EntityDamageSource;
import train.common.api.AbstractTrains;
import train.common.api.EntityBogie;
import train.common.api.EntityRollingStock;
import train.common.api.Locomotive;
import train.common.core.handlers.ConfigHandler;

import java.util.ArrayList;
import java.util.List;

public class EntityHitbox {

    private float longest=0;

    public List<CollisionBox> interactionBoxes = new ArrayList<>();
    public CollisionBox front,back;
    public EntityRollingStock host;

    public EntityHitbox(EntityRollingStock entity){
        if (entity.getWorld()==null){
            return;
        }
        host=entity;
    }


    public void position(double x, double y, double z, float pitch, float yaw){
        if(interactionBoxes.size()<1){
            float depth =host.getHitboxSize()[0]*0.5f;
            float width =host.getHitboxSize()[2]*0.5f;
            longest=Math.abs(depth);

            depth*=2;width*=2;
            depth+=host.getOptimalDistance(null);
            interactionBoxes = new ArrayList<>();
            for (float f = 0; f < depth - (width * 0.25f); f += width) {
                CollisionBox c = new CollisionBox((host));
                c.boundingBox.setBounds(-width*0.5,0,-width*0.5,
                        width*0.5,host.getHitboxSize()[1],width*0.5);
                c.setPosition(host.posX+f, host.posY, host.posZ);
                c.host=host;
                interactionBoxes.add(c);
                host.getWorld().spawnEntityInWorld(c);
                if(front==null){
                    front=c;
                } else{
                    back=c;
                }
            }
        }
        Vec3d part;
        for(int i=0; i<interactionBoxes.size();i++) {
            part = CommonUtil.rotateDistance( -host.getOptimalDistance(null) +
                            ((host.getHitboxSize()[0] / interactionBoxes.size()) * (i + 0.5f)),
                    -pitch, yaw).addVector(x, y, z);
            interactionBoxes.get(i).setPosition(part.xCoord, part.yCoord, part.zCoord);
        }
    }

    public void manageCollision(){
        for(Entity e:collidingEntities) {
            //on client we need to push away players.
            if (host.worldObj.isRemote) {
                if (e instanceof EntityPlayer || e instanceof EntityLiving) {
                    double[] motion = CommonUtil.rotatePoint(-0.075, 0,
                            CommonUtil.atan2degreesf(host.posZ - e.posZ, host.posX - e.posX));
                    e.addVelocity(motion[0], 0.05, motion[2]);
                }
            } else {
                if (e instanceof CollisionBox) {
                    if(((CollisionBox) e).host==null){
                        continue;
                    }
                    EntityRollingStock entityOne = (((CollisionBox) e).host);
                    if (entityOne.isAttaching && host.isAttaching) {
                        if(entityOne.canBeAdjusted(host) || host.canBeAdjusted(entityOne)){


                            if(new Vec3f(front.posX,front.posY,front.posZ).subtract(new Vec3f(entityOne.posX,entityOne.posY,entityOne.posZ)).length()
                                    <
                                    new Vec3f(back.posX,back.posY,back.posZ).subtract(new Vec3f(entityOne.posX,entityOne.posY,entityOne.posZ)).length()
                            ){
                                if(host.frontLink==null){
                                    host.frontLink=entityOne;
                                }
                            } else {
                                if(host.backLink==null){
                                    host.backLink=entityOne;
                                }
                            }

                            if(new Vec3f(entityOne.collisionHandler.front.posX,entityOne.collisionHandler.front.posY,entityOne.collisionHandler.front.posZ).subtract(new Vec3f(host.posX,host.posY,host.posZ)).length()
                                    <
                                    new Vec3f(entityOne.collisionHandler.back.posX,entityOne.collisionHandler.back.posY,entityOne.collisionHandler.back.posZ).subtract(new Vec3f(host.posX,host.posY,host.posZ)).length()
                            ){
                                if(entityOne.frontLink==null){
                                    entityOne.frontLink=host;
                                }
                            } else {
                                if(entityOne.backLink==null){
                                    entityOne.backLink=host;
                                }
                            }
                            entityOne.isAttaching = false;
                            host.isAttaching = false;

                            host.updateLinks();


                            EntityPlayer entityplayer = host.worldObj.getClosestPlayerToEntity(host, 20);//
                            if (entityplayer != null) {
                                entityplayer.addChatMessage(new ChatComponentText("attached!"));
                            }

                        } else {
                            EntityPlayer p = host.getWorld().getClosestPlayerToEntity(host,32);
                            if(p!=null){
                                p.addChatComponentMessage(new ChatComponentText("One or more trains is not in towing mode."));
                                p.addChatComponentMessage(new ChatComponentText("Use a Stake while sneaking to toggle towing mode."));
                            }
                        }
                    } else {
                        double distanceFront = Math.sqrt((e.posX - front.posX) * (e.posX - front.posX)
                                + (e.posZ - front.posZ) * (e.posZ - front.posZ));
                        double distanceBack = Math.sqrt((e.posX - back.posX) * (e.posX - back.posX)
                                + (e.posZ - back.posZ) * (e.posZ - back.posZ));
                        if (distanceFront<distanceBack) {
                            host.appendMovement(-0.005);
                        } else {
                            host.appendMovement(0.005);
                        }
                    }

                } else if (e instanceof EntityPlayer || e instanceof EntityLiving) {
                    //hurt entity if going fast
                    if (Math.abs(host.motionX) + Math.abs(host.motionZ) > 0.25f) {
                        e.attackEntityFrom(new EntityDamageSource(
                                        host instanceof Locomotive ? "Locomotive" : "rollingstock", host),
                                (float) (Math.abs(host.motionX) + Math.abs(host.motionZ)) * 0.5f);
                    } else if (Math.abs(host.motionX) + Math.abs(host.motionZ) <0.05) {
                        double distanceFront = Math.sqrt((e.posX - front.posX) * (e.posX - front.posX)
                                + (e.posZ - front.posZ) * (e.posZ - front.posZ));
                        double distanceBack = Math.sqrt((e.posX - back.posX) * (e.posX - back.posX)
                                + (e.posZ - back.posZ) * (e.posZ - back.posZ));
                        if (distanceFront<distanceBack) {
                            host.appendMovement(-0.005);
                        } else {
                            host.appendMovement(0.005);
                        }
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

                            //No matter what, we don't want to push a locomotive.
                            //If the config is disabled, we don't want to push ANYTHING
                            if (!ConfigHandler.PUSHABLE_ROLLINGSTOCK || host instanceof Locomotive) {
                                //still need to push the player back though
                                if (containsEntity((Entity)obj)) {
                                    ((Entity)obj).applyEntityCollision(host);
                                }
                                continue;
                            }

                            //we don't want to collide with bogies, data(EntityRollingStock, ElectricTrain, ect...), or seat entities.
                            //Since those are mounted on a train itself, we can ignore them.
                            if (obj instanceof EntityBogie || obj instanceof EntitySeat || obj instanceof AbstractTrains) {
                                continue;
                            }

                            //we don't want to collide with any passenger that is in a seat. Can just blanket skip everything that is riding something else.
                            if (((Entity) obj).ridingEntity != null) {
                                continue;
                            }

                            //we don't want to collide with our own CollisionBoxes, or the CollisionBoxes of our own consist either
                            if(obj instanceof CollisionBox && (((CollisionBox) obj).host==host || host.consist.contains(((CollisionBox) obj).host))){
                                continue;
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
            //check for X
            if (e.boundingBox.intersectsWith(box.boundingBox.expand(0.2D, e instanceof EntityPlayer?1.2D:0.2D, 0.2D)))
                return true;
        }
        return false;
    }
}
