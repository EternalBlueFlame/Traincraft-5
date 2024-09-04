package ebf.tim.api;

import train.common.Traincraft;
import train.common.api.AbstractTrains;
import train.common.library.TraincraftRegistry;

@Deprecated
public class SkinRegistry {
    public static void addSkin(Class<? extends AbstractTrains> train, TransportSkin str){
        Traincraft.instance.traincraftRegistry.addLivery(train,str.addr);
    }

    public static void addSkin(Class<? extends AbstractTrains> train, String modid,String addr,String[] bogieSkins,String name, String description){
        Traincraft.instance.traincraftRegistry.addLivery(train,modid+":"+addr);
    }

    public static void addSkin(Class<? extends AbstractTrains> train, String modid,String addr,String bogieSkin,String name, String description){
        Traincraft.instance.traincraftRegistry.addLivery(train,modid+":"+addr);
    }
}
