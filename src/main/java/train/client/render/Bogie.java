package train.client.render;

import ebf.tim.utility.CommonUtil;
import fexcraft.tmt.slim.ModelBase;
import fexcraft.tmt.slim.Vec3f;
import train.common.api.AbstractTrains;
import train.common.api.EntityRollingStock;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bogie {

    /**the current yaw rotation.*/
    public float rotationYaw;
    /**the model defined in the registration of this.*/
    public ModelBase bogieModel;
    public float[] offset = new float[]{0,0},prevPos = null, position = new float[]{0,0}, rotation=new float[]{0,0,0};

    public List<Bogie> subBogies=new ArrayList<>();



    public Bogie(ModelBase model, @Nullable float[] offset){
        this.bogieModel = model;
        if(offset!=null) {
            this.offset = offset;
        }
    }

    public Bogie(ModelBase model, float offset){
        this.bogieModel = model;
        this.offset = new float[]{offset,0,0};
    }
    public Bogie(ModelBase model, float offsetX, float offsetY, float offsetZ){
        this.bogieModel = model;
        this.offset = new float[]{offsetX,offsetY,offsetZ};
    }
    public Bogie(ModelBase model, float offsetX, float offsetY, float offsetZ, Bogie[] bogies){
        this.bogieModel = model;
        this.offset = new float[]{offsetX,offsetY,offsetZ};
        Collections.addAll(this.subBogies, bogies);
    }
    public Bogie(ModelBase model, @Nullable float[] offset, Bogie[] bogies){
        this.bogieModel = model;
        if(offset!=null) {
            this.offset = offset;
        }
        Collections.addAll(this.subBogies, bogies);
    }

    public Bogie addSubBogie(ModelBase model, float offsetX, float offsetY, float offsetZ){
        subBogies.add(new Bogie(model,offsetX,offsetY,offsetZ));
        return this;
    }

    public Bogie addSubBogie(ModelBase model, float[] offset){
        subBogies.add(new Bogie(model,offset));
        return this;
    }

    public Bogie addSubBogie(Bogie sub){
        subBogies.add(sub);
        return this;
    }

    /**sets rotation in degrees*/
    public Bogie setRotation(float x, float y, float z){
        rotation[0]=x;
        rotation[1]=y;
        rotation[2]=z;
        return this;
    }

    /**
     * <h2>handle rotation of model</h2>
     * updates the positions of the model, and then uses that data to set the rotations.
     * @param entity the GenericRailTransport to get the pitch from.
     */
    public void updateRotation(AbstractTrains entity){
        //update positions
        if(prevPos!=position && prevPos!=null && position!=null&&
                Math.abs(Math.abs(position[0])+Math.abs(position[1])-Math.abs(prevPos[0])-Math.abs(prevPos[1]))>0.35f
        ) {
            rotationYaw = CommonUtil.atan2degreesf(prevPos[1] - position[1], prevPos[0] - position[0]);

            if(Math.abs(entity.rotationYaw+180)-Math.abs(rotationYaw+180)>90 ||
                    Math.abs(entity.rotationYaw+180)-Math.abs(rotationYaw+180)<-90){
                rotationYaw-=180;
            }
            for(Bogie b : subBogies){
                b.updateRotation(entity);
            }
            prevPos = position;
        } else if(prevPos==null){
            rotationYaw=entity.rotationYaw;
            prevPos = position;
        }
    }

    public void updatePosition(AbstractTrains entity, Vec3f prevOffset){
        if(prevOffset==null){
            prevOffset= CommonUtil.rotatePoint(new Vec3f(offset[0],0,offset[1]),0,entity.rotationYaw,0);
        } else {
            prevOffset.add(CommonUtil.rotatePoint(new Vec3f(offset[0],0,offset[1]),0,entity.rotationYaw,0));
        }
        position=new float[]{(float)entity.posX+prevOffset.xCoord, (float)entity.posZ+prevOffset.zCoord};

        for(Bogie b : subBogies){
            b.updatePosition(entity,prevOffset);
        }
    }

    public void render(EntityRollingStock cart){
        org.lwjgl.opengl.GL11.glPushMatrix();

        org.lwjgl.opengl.GL11.glTranslatef(offset[0],offset[1],offset[2]);

        org.lwjgl.opengl.GL11.glRotatef(rotation[0],1,0,0);
        org.lwjgl.opengl.GL11.glRotatef(rotation[1],0,1,0);
        org.lwjgl.opengl.GL11.glRotatef(rotation[2],0,0,1);

        org.lwjgl.opengl.GL11.glRotatef(rotationYaw,0,1,0);

        bogieModel.render(cart,0,0,0,0,0,0);

        int m2=0;
        for(Bogie sb : subBogies){
            org.lwjgl.opengl.GL11.glPushMatrix();

            org.lwjgl.opengl.GL11.glTranslatef(sb.offset[0],sb.offset[1],sb.offset[2]);

            org.lwjgl.opengl.GL11.glRotatef(sb.rotation[0],1,0,0);
            org.lwjgl.opengl.GL11.glRotatef(sb.rotation[1],0,1,0);
            org.lwjgl.opengl.GL11.glRotatef(sb.rotation[2],0,0,1);

            org.lwjgl.opengl.GL11.glRotatef(sb.rotationYaw,0,1,0);

            sb.bogieModel.render(cart,0,0,0,0,0,0);

            org.lwjgl.opengl.GL11.glPopMatrix();
        }

        org.lwjgl.opengl.GL11.glPopMatrix();
    }

}
