package train.common.api;

public class TrainParticle {
    public String type;
    public int density;
    public double[] position;

    public TrainParticle(String t, int d, double[] p){
        type=t;
        density=d;
        position=p;
    }
}
