package ebf.tim.blocks;

import ebf.tim.utility.DebugUtil;
import net.minecraft.nbt.NBTTagCompound;

public class TileSwitch extends TileRenderFacing {
    public boolean enabled=false, animationReversing=false;
    public int crossingTick=0;
    public long lastTick=0, lastSoundMS=0;

    public TileSwitch(BlockSwitch block){
        host=block;
    }
    public TileSwitch(){}

    public boolean toggleEnabled(){
        enabled=!enabled;
        markDirty();
        return enabled;
    }

    public void setEnabled(boolean e){
        if(e!=enabled){
            enabled=e;
            markDirty();
        }
    }

    public boolean getEnabled(){return enabled;}

    @Override
    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag);
        tag.setBoolean("e", enabled);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag){
        enabled = tag.getBoolean("e");
        super.readFromNBT(tag);
    }

    //how often to repeat the sound in MS, this includes the length of the sound itself
    public int getSoundInterval(){
        return 2000;
    }

    //remember to list the sound file in your sounds.json and ID it there
    // TC5 does not yet feature direct audio streaming.
    public String soundFile(){return "";}
    //optional pitch shift for sound
    public float soundPitch(){return 1f;}
    //sound volume
    public float soundVolume(){return 1f;}

    //sets the min and max rotation angles.
    // if there is a difference of exactly 360 between the two, it will not reverse
    public float maxAngle(){return 360;}
    public float minAngle(){return 0;}
    //how many degrees to move each animation tick.
    public float animationSpeed(){return 1;}
    //this prevents the tick from reversing or looping until the redstone state changes
    public boolean angleStops(){return false;}

    @Override
    public boolean canUpdate(){return true;}

    @Override
    public void updateEntity() {
        if(!getWorldObj().isRemote){
            return;
        }
        long time = System.currentTimeMillis();
        //only tick every 1/20 of a second. Client tick tends to be fast and unreliable depending on FPS
        if(time>lastTick+50){
            lastTick=time;
            if(angleStops()){
                if (crossingTick<=maxAngle() && getEnabled()){
                    crossingTick+=animationSpeed();
                } else if (crossingTick>=minAngle() && !getEnabled()){
                    crossingTick-=animationSpeed();
                }
            } else {
                if(maxAngle()-minAngle()==360) {
                    if(getEnabled()) {
                        crossingTick += animationSpeed();
                        if (crossingTick >= 360) {
                            crossingTick = 0;
                        }
                    }
                } else if(getEnabled() || crossingTick!=0){
                    if(animationReversing){
                        crossingTick -= animationSpeed();
                    } else {
                        crossingTick += animationSpeed();
                    }
                    if(crossingTick<=minAngle() || crossingTick>=maxAngle()){
                        animationReversing=!animationReversing;
                    }
                }

            }
        }

        //if there's a defined sound, play that every interval.
        if(getEnabled() && soundFile()!=null){
            if(time>lastSoundMS+getSoundInterval()){
                getWorldObj().playSound(xCoord,yCoord,zCoord, soundFile(), soundVolume(),soundPitch(),false);
                lastSoundMS=time;
            }
        }
    }
}
