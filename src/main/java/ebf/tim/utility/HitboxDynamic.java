package ebf.tim.utility;

import ebf.tim.entities.EntityBogie;
import ebf.tim.entities.EntitySeat;
import ebf.tim.entities.GenericRailTransport;
import fexcraft.tmt.slim.Vec3d;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;
import java.util.List;


public class HitboxDynamic {

    private float longest=0;

    public List<CollisionBox> interactionBoxes = new ArrayList<>();


    public HitboxDynamic(float depth, float height, float width, GenericRailTransport entity){
        depth *=0.5f;
        width *=0.5f;
        if(Math.abs(depth)>longest){
            longest=Math.abs(depth);
        }
        if(Math.abs(width)>longest){
            longest=Math.abs(width);
        }

        depth*=2;width*=2;
        if(height!=-1f) {
            interactionBoxes = new ArrayList<>();
            for (float f = 0; f < depth - (width * 0.25f); f += width) {
                CollisionBox c = new CollisionBox((entity));
                c.boundingBox.setBounds(-width*0.5,0,-width*0.5,
                        width*0.5,height,width*0.5);
                c.setPosition(entity.posX, entity.posY, entity.posZ);
                interactionBoxes.add(c);
                entity.worldObj.spawnEntityInWorld(c);
            }
        }
    }


    public void position(double x, double y, double z, float pitch, float yaw){
        for(int i=0; i<interactionBoxes.size();i++){
            Vec3d part = CommonUtil.rotateDistance(
                    (interactionBoxes.get(0).host.getHitboxSize()[0]*0.5f)-
                    ((interactionBoxes.get(0).host.getHitboxSize()[0]/interactionBoxes.size())*(i+0.5f)),
                    -pitch, yaw).addVector(x,y,z);
            interactionBoxes.get(i).setLocationAndAngles(part.xCoord,part.yCoord,part.zCoord,0,0);
        }
}


    /**
     * AWT methods
     */

    public List<Entity> collidingEntities = new ArrayList<>();
    public List<int[]> collidingBlocks = new ArrayList<>();
    private List[] entities;
    private int x,xMax,z,zMax,y,yMax;

    public void updateCollidingEntities(GenericRailTransport host){
        collidingEntities = new ArrayList<>();
        collidingBlocks = new ArrayList<>();
        if(host==null){return;}

        x = CommonUtil.floorDouble((-longest+host.posX - 0.25) / 16.0D);
        xMax = CommonUtil.floorDouble((longest+host.posX + 0.25) / 16.0D);
        z = CommonUtil.floorDouble((-longest+host.posZ - 0.25) / 16.0D);
        zMax = CommonUtil.floorDouble((longest+host.posZ + 0.25) / 16.0D);

        y = CommonUtil.floorDouble(host.posY);
        yMax = CommonUtil.floorDouble(host.getHitboxSize()[1]+0.25);

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
                                    obj instanceof GenericRailTransport || ((Entity) obj).ridingEntity!=null) {
                                continue;
                            }
                            //if it's another collision box, be sure it's not the current entity or a linked one
                            if(obj instanceof CollisionBox){
                                if(((CollisionBox) obj).host==null){
                                    continue;
                                }


                                //make sure not to interact with own consist.
                                //we have to check against front and back link on top of entire consist,
                                //    in case entire consist hasn't updated just yet
                                if((host.frontLinkedID!=null && host.frontLinkedID==((CollisionBox) obj).host.getEntityId()) ||
                                        (host.backLinkedID!=null && host.backLinkedID==((CollisionBox) obj).host.getEntityId())||
                                        host.getConsist().contains(((CollisionBox) obj).host)){
                                    continue;
                                }
                            }

                            if(containsEntity((Entity) obj)){
                                this.collidingEntities.add((Entity)obj);
                            }
                        }
                    }

                    //block collisions won't happen on client due to positioning, so there's no reason to check.
                    if(host.worldObj.isRemote){
                        continue;
                    }
                    //this is basically a BlockPos for where the block is, so the entity can figure out what to do.
                    // but that's not a 1.7 thing, so we do this heresy to keep code similarities for easier porting
                    for(int k=y; k<yMax;k++) {
                        if (!(CommonUtil.getBlockAt(host.worldObj, i, j, k) instanceof BlockAir)){
                            collidingBlocks.add(new int[]{i,j,k});
                        }
                    }
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



    static String dragonBoxName ="b";

}
