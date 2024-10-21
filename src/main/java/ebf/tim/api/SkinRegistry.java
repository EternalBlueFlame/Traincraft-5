package ebf.tim.api;

import train.common.Traincraft;
import train.common.api.AbstractTrains;
import train.common.library.TraincraftRegistry;

import java.util.*;


public class SkinRegistry {

    public static Map<Class<? extends AbstractTrains>, List<String>> liveryMap = new HashMap<Class<? extends AbstractTrains>, List<String>>();

    public static List<String> get(AbstractTrains t){
        if(liveryMap.containsKey(t.getClass())) {
            return liveryMap.get(t.getClass());
        } else {
            return new ArrayList<String>();
        }
    }

    public static void addSkin(Class<? extends AbstractTrains> train, TransportSkin str){
        if(!liveryMap.containsKey(train)) {
            liveryMap.put(train, Collections.singletonList(str.addr));
        } else {
            List<String> temp = new ArrayList<>();
            temp.addAll(liveryMap.get(train));
            temp.add(str.addr);
            liveryMap.put(train,temp);
        }
    }

    public static void addSkin(Class<? extends AbstractTrains> train, String str){
        if(!liveryMap.containsKey(train)) {
            liveryMap.put(train, Collections.singletonList(str));
        } else {
            List<String> temp = new ArrayList<>();
            temp.addAll(liveryMap.get(train));
            temp.add(str);
            liveryMap.put(train,temp);
        }
    }

    public static void addSkin(Class<? extends AbstractTrains> train, String modid,String addr,String[] bogieSkins,String name, String description){
        if(!liveryMap.containsKey(train)) {
            liveryMap.put(train, Collections.singletonList(modid+":"+addr));
        } else {
            List<String> temp = new ArrayList<>();
            temp.addAll(liveryMap.get(train));
            temp.add(modid+":"+addr);
            liveryMap.put(train,temp);
        }
    }

    public static void addSkin(Class<? extends AbstractTrains> train, String modid,String addr,String bogieSkin,String name, String description){
        if(!liveryMap.containsKey(train)) {
            liveryMap.put(train, Collections.singletonList(modid+":"+addr));
        } else {
            List<String> temp = new ArrayList<>();
            temp.addAll(liveryMap.get(train));
            temp.add(modid+":"+addr);
            liveryMap.put(train,temp);
        }
    }

}
