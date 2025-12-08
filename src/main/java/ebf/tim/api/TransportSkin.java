package ebf.tim.api;

import java.util.ArrayList;
import java.util.List;

public class TransportSkin {
    public String addr;
    public List<String> bogieSkins= new ArrayList<>();

    public TransportSkin(String modID, String a, String b, String c){ addr= modID+":"+a;}

    public TransportSkin(String modID, String a){ addr= modID+":"+a;}
    public TransportSkin(String skin){ addr=skin;}
    @Deprecated//todo!
    public TransportSkin setRecolorsFrom(int i){return this;}
    @Deprecated//todo!
    public TransportSkin setRecolorsTo(int i){return this;}
}
