package train.common.api;

public class TrainSound {
    public String addr;
    public float vol=1;
    public float pit=1;
    public int len=1;
    public boolean runningPitch=false;

    public TrainSound(String address, float volume, float pitch, int length){
        addr=address;
        vol=volume;
        pit=pitch;
        len=length;
    }
    public TrainSound enableRunningPitch(){
        runningPitch=true;
        return this;
    }

}
