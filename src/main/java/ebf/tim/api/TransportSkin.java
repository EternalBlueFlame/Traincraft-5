package ebf.tim.api;

public class TransportSkin {
    String addr;
    public TransportSkin(String modID, String a, String b, String c){ addr= modID+":"+a;}
    @Deprecated//todo!
    public TransportSkin setRecolorsFrom(int i){return this;}
    @Deprecated//todo!
    public TransportSkin setRecolorsTo(int i){return this;}
}
