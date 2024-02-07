package ebf.tim.api;

import train.common.Traincraft;
import train.common.api.AbstractTrains;
import train.common.library.TraincraftRegistry;

@Deprecated
public class SkinRegistry {
    public static void addSkin(Class<? extends AbstractTrains> train, TransportSkin str){
        Traincraft.instance.traincraftRegistry.addLivery(train,str.addr);
    }
}
