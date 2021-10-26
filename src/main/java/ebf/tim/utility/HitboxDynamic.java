package ebf.tim.utility;

import ebf.tim.entities.EntityBogie;
import ebf.tim.entities.EntitySeat;
import ebf.tim.entities.GenericRailTransport;
import fexcraft.tmt.slim.Vec3d;
import fexcraft.tmt.slim.Vec3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

import java.util.ArrayList;
import java.util.List;


public class HitboxDynamic {
    //shape code is depreciated, as cool as rotating hitboxes are, none of the rest of MC can be interacted through it.
    //public Vec3f[] shape, pos = new Vec3f[6], renderShape;

    private float longest=0;

    public List<CollisionBox> interactionBoxes = new ArrayList<>();


    public HitboxDynamic(float depth, float height, float width, GenericRailTransport entity){
        depth *=0.5f;
        width *=0.5f;
        /*shape= new Vec3f[]{
                new Vec3f(-depth,0,width)
                ,new Vec3f(depth,0,width)
                ,new Vec3f(depth,0,-width)
                ,new Vec3f(-depth,0,-width),
                new Vec3f(-depth,height,width),
                new Vec3f(depth,height,width),
                new Vec3f(depth,height,-width),
                new Vec3f(-depth,height,-width)};
        if(TrainsInMotion.proxy.isClient()) {
            renderShape = shape.clone();
        }*/
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
        /*pos[0] = CommonUtil.rotatePoint(shape[0].addVector(-0.25f,0,0.25f)
                , pitch,  yaw, 0).addVector(f(x), f(y), f(z));
        pos[1] = CommonUtil.rotatePoint(shape[1].addVector(0.25f,0,0.25f)
                , pitch,  yaw, 0).addVector(f(x), f(y), f(z));
        pos[2] = CommonUtil.rotatePoint(shape[6].addVector(0.25f,0.5f,-0.25f)
                , pitch,  yaw, 0).addVector(f(x), f(y), f(z));
        pos[3] = CommonUtil.rotatePoint(shape[0].addVector(-0.25f,0,0.25f)
                , pitch,  yaw-180, 0).addVector(f(x), f(y), f(z));
        pos[4] = CommonUtil.rotatePoint(shape[1].addVector(0.25f,0,0.25f)
                , pitch,  yaw-180, 0).addVector(f(x), f(y), f(z));
        pos[5] = CommonUtil.rotatePoint(shape[6].addVector(0.25f,0.5f,-0.25f)
                , pitch,  yaw-180, 0).addVector(f(x), f(y), f(z));

        for (int i = 0; i < 8; i++) {
            renderShape[i] = CommonUtil.rotatePoint(shape[i], pitch, yaw, 0);
        }*/
        for(int i=0; i<interactionBoxes.size();i++){
            Vec3d part = CommonUtil.rotateDistance(
                    (interactionBoxes.get(0).host.getHitboxSize()[0]*0.5f)-
                    ((interactionBoxes.get(0).host.getHitboxSize()[0]/interactionBoxes.size())*(i+0.5f)),
                    -pitch, yaw).addVector(x,y,z);
            interactionBoxes.get(i).setLocationAndAngles(part.xCoord,part.yCoord,part.zCoord,0,0);
        }
}
    private static float f(double d){ return (float)d;}


    /**
     * AWT methods
     */

    List<Entity> arraylist = new ArrayList<>();
    List[] entities;
    int i,j,k,l;
    GenericRailTransport stock;

    public List<Entity> getCollidingEntities(GenericRailTransport host){
        arraylist = new ArrayList<>();
        if(host.collisionHandler==null){
            return arraylist;
        }
        i = MathHelper.floor_double((-longest+host.posX - 0.25) / 16.0D);
        j = MathHelper.floor_double((longest+host.posX + 0.25) / 16.0D);
        k = MathHelper.floor_double((-longest+host.posZ - 0.25) / 16.0D);
        l = MathHelper.floor_double((longest+host.posZ + 0.25) / 16.0D);

        for (int i1 = i; i1 <= j; ++i1) {
            for (int j1 = k; j1 <= l; ++j1) {
                if (host.worldObj.getChunkProvider().chunkExists(i1, j1)) {
                    entities = host.worldObj.getChunkFromChunkCoords(i1, j1).entityLists;
                    for (List olist: entities) {
                        for(Object obj : olist) {
                            if(obj instanceof EntitySeat || obj instanceof EntityBogie || obj instanceof CollisionBox ||
                                    ((Entity)obj).getEntityId()==host.getEntityId()){continue;}

                            if(obj instanceof GenericRailTransport) {
                                if(((GenericRailTransport) obj).getEntityId()==host.getEntityId()){
                                    continue;
                                }
                                stock=((GenericRailTransport) obj);
                                if(host.frontLinkedID!=null && stock.getEntityId()==host.frontLinkedID){
                                    continue;
                                } else if(host.backLinkedID!=null && stock.getEntityId()==host.backLinkedID){
                                    continue;
                                }
                                if(stock.collisionHandler!=null) {
                                    Vec3 vec;
                                    if(host.getBoolean(GenericRailTransport.boolValues.COUPLINGFRONT)) {
                                        vec = CommonUtil.rotateDistance(longest+1.3f, host.rotationPitch, host.rotationYaw);
                                        vec.addVector(host.posX, host.posY+0.1, host.posZ);
                                        if (stock.collisionHandler.containsPoint(vec.xCoord, vec.yCoord, vec.zCoord)) {
                                            if (transportCollide(host, stock, true)) {
                                                continue;
                                            }
                                        }
                                    }
                                    if(host.getBoolean(GenericRailTransport.boolValues.COUPLINGBACK)) {
                                        vec = CommonUtil.rotateDistance(-longest - 1.3f, host.rotationPitch, host.rotationYaw);
                                        vec.addVector(host.posX, host.posY+0.1, host.posZ);
                                        if (stock.collisionHandler.containsPoint(vec.xCoord, vec.yCoord, vec.zCoord)) {
                                            if (transportCollide(host, stock, false)) {
                                                continue;
                                            }
                                        }
                                    }
                                    for (CollisionBox b : stock.collisionHandler.interactionBoxes) {
                                        if (containsEntity(b)) {
                                            arraylist.add(b);
                                        }
                                    }
                                }

                            }

                            else if(containsEntity((Entity) obj)){
                                arraylist.add((Entity)obj);
                            }
                        }
                    }
                }
            }
        }

        return arraylist;
    }

    public boolean transportCollide(GenericRailTransport host, GenericRailTransport target, boolean front){
        if(front){
            Vec3 vec = CommonUtil.rotateDistance(target.collisionHandler.longest+0.25f, target.rotationPitch, target.rotationYaw);
            vec.addVector((target).posX,(target).posY+0.3,(target).posZ);
            if(containsPoint(vec.xCoord,vec.yCoord,vec.zCoord)){
                if(target.getBoolean(GenericRailTransport.boolValues.COUPLINGFRONT)){
                    host.frontLinkedTransport=target.getUniqueID();
                    target.frontLinkedTransport=host.getUniqueID();
                    host.frontLinkedID=target.getEntityId();
                    target.frontLinkedID=host.getEntityId();
                    host.setBoolean(GenericRailTransport.boolValues.COUPLINGFRONT,false);
                    target.setBoolean(GenericRailTransport.boolValues.COUPLINGFRONT,false);
                    host.updateConsist();
                    return true;
                }
            } else {
                vec = CommonUtil.rotateDistance(-target.collisionHandler.longest-0.3f, target.rotationPitch, target.rotationYaw);
                vec.addVector((target).posX,(target).posY+0.25,(target).posZ);
                if(containsPoint(vec.xCoord,vec.yCoord,vec.zCoord)) {
                    if (target.getBoolean(GenericRailTransport.boolValues.COUPLINGBACK)) {
                        host.frontLinkedTransport=target.getUniqueID();
                        target.backLinkedTransport=host.getUniqueID();
                        host.frontLinkedID=target.getEntityId();
                        target.backLinkedID=host.getEntityId();
                        host.setBoolean(GenericRailTransport.boolValues.COUPLINGFRONT,false);
                        target.setBoolean(GenericRailTransport.boolValues.COUPLINGBACK,false);
                        host.updateConsist();
                        return true;
                    }
                }
            }
        } else {
            Vec3 vec = CommonUtil.rotateDistance(target.collisionHandler.longest+0.3f, target.rotationPitch, target.rotationYaw);
            vec.addVector((target).posX,(target).posY+0.25,(target).posZ);
            if(containsPoint(vec.xCoord,vec.yCoord,vec.zCoord)){
                if(target.getBoolean(GenericRailTransport.boolValues.COUPLINGFRONT)){
                    host.backLinkedTransport=target.getUniqueID();
                    target.frontLinkedTransport=host.getUniqueID();
                    host.backLinkedID=target.getEntityId();
                    target.frontLinkedID=host.getEntityId();
                    host.setBoolean(GenericRailTransport.boolValues.COUPLINGBACK,false);
                    target.setBoolean(GenericRailTransport.boolValues.COUPLINGFRONT,false);
                    host.updateConsist();
                    return true;
                }
            } else {
                vec = CommonUtil.rotateDistance(-target.collisionHandler.longest-0.3f, target.rotationPitch, target.rotationYaw);
                vec.addVector((target).posX,(target).posY+0.25,(target).posZ);
                if(containsPoint(vec.xCoord,vec.yCoord,vec.zCoord)) {
                    if (target.getBoolean(GenericRailTransport.boolValues.COUPLINGBACK)) {
                        host.backLinkedTransport=target.getUniqueID();
                        target.backLinkedTransport=host.getUniqueID();
                        host.backLinkedID=target.getEntityId();
                        target.backLinkedID=host.getEntityId();
                        host.setBoolean(GenericRailTransport.boolValues.COUPLINGBACK,false);
                        target.setBoolean(GenericRailTransport.boolValues.COUPLINGBACK,false);
                        host.updateConsist();
                        return true;
                    }
                }
            }
        }
        return false;

    }
    public boolean containsPoint(double x, double y, double z){
        for(CollisionBox box : interactionBoxes){
            if(x>=box.boundingBox.minX && x<=box.boundingBox.maxX &&
                    y>=box.boundingBox.minY && y<=box.boundingBox.maxY &&
                    z>=box.boundingBox.minZ && z<=box.boundingBox.maxZ){
                return true;
            }
        }
        return false;
    }

    public boolean containsEntity(Entity e){
        for(CollisionBox box : interactionBoxes){
            //check for X
            if (e.boundingBox.intersectsWith(box.boundingBox.expand(0.2D, e instanceof EntityPlayer?1.2D:0.2D, 0.2D)))
                return true;
        }
        return false;
    }

    public static boolean containsPoint(Vec3f A, Vec3f B, Vec3f C, Vec3f P){
        if(P.yCoord>A.yCoord && P.yCoord<C.yCoord) {
            // Compute vectors
            Vec3f v0 = C.subtract(A);
            Vec3f v1 = B.subtract(A);
            Vec3f v2 = P.subtract(A);

            // Compute dot products
            float dot00 = v0.dot2D(v0);
            float dot01 = v0.dot2D(v1);
            float dot02 = v0.dot2D(v2);
            float dot11 = v1.dot2D(v1);
            float dot12 = v1.dot2D(v2);

            // Compute barycentric coordinates
            float invDenom = 1f / (dot00 * dot11 - dot01 * dot01);
            float u = (dot11 * dot02 - dot01 * dot12) * invDenom;
            float v = (dot00 * dot12 - dot01 * dot02) * invDenom;

            // Check if point is in triangle
            return (u > 0) && (v > 0) && (u + v < 1);
        }
        return false;
    }




    static String dragonBoxName ="b";

}
