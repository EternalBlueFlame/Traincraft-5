package train.common.api;

public class TrainParticle {
    /** Particle resource/type, such as "smoke", "largesmoke", or "explode". */
    public String type;
    /** Spawn density/iteration count used by the client renderer. */
    public int density;
    /** Particle origin relative to the rolling-stock entity. */
    public double[] position;

    public TrainParticle(String t, int d, double[] p){
        type=t;
        density=d;
        position=p;
    }
}
