package ebf.tim.api;

import train.common.Traincraft;
import train.common.api.AbstractTrains;
import train.common.library.TraincraftRegistry;

import java.util.*;


public class SkinRegistry {

    public static HashMap<Class<? extends AbstractTrains>, HashMap<String,TransportSkin>> liveryMap = new HashMap<Class<? extends AbstractTrains>, HashMap<String,TransportSkin>>();

    public static Map<String,TransportSkin> get(AbstractTrains t){
        if(liveryMap.containsKey(t.getClass())) {
            return liveryMap.get(t.getClass());
        } else {
            return new HashMap<String,TransportSkin>();
        }
    }
    public static Map<String,TransportSkin> get(Class<? extends AbstractTrains> t){
        if(liveryMap.containsKey(t)) {
            return liveryMap.get(t);
        } else {
            return new HashMap<String,TransportSkin>();
        }
    }

    public static void addSkin(Class<? extends AbstractTrains> train, TransportSkin str, String color){
        if(!liveryMap.containsKey(train)) {
            HashMap<String,TransportSkin> m = new HashMap<String, TransportSkin>();
            m.put(color,str);
            liveryMap.put(train, m);
        } else {
            liveryMap.get(train).put(color,str);
        }
    }

    public static void addSkin(Class<? extends AbstractTrains> train, String str, String color){
        if(!liveryMap.containsKey(train)) {
            HashMap<String,TransportSkin> m = new HashMap<String, TransportSkin>();
            m.put(color,new TransportSkin(str));
            liveryMap.put(train, m);
        } else {
            liveryMap.get(train).put(color,new TransportSkin(str));
        }
    }

    @Deprecated
    public static void addSkin(Class<? extends AbstractTrains> train, String str){
        if(!liveryMap.containsKey(train)) {
            HashMap<String,TransportSkin> m = new HashMap<String, TransportSkin>();
            m.put(str,new TransportSkin(str));
            liveryMap.put(train, m);
        } else {
            liveryMap.get(train).put(str,new TransportSkin(str));
        }
    }

    public static void addSkin(Class<? extends AbstractTrains> train, String modid,String addr, String[] bogieSkins,String name, String description){
        if(!liveryMap.containsKey(train)) {
            HashMap<String,TransportSkin> m = new HashMap<String, TransportSkin>();
            m.put(name,new TransportSkin(modid,addr));
            liveryMap.put(train, m);
        } else {
            liveryMap.get(train).put(name,new TransportSkin(modid,addr));
        }

        liveryMap.get(train).get(name).bogieSkins=Arrays.asList(bogieSkins);
    }

    public static void addSkin(Class<? extends AbstractTrains> train, String modid,String addr, String bogieSkin,String name, String description){
        if(!liveryMap.containsKey(train)) {
            HashMap<String,TransportSkin> m = new HashMap<String, TransportSkin>();
            m.put(name,new TransportSkin(modid,addr));
            liveryMap.put(train, m);
        } else {
            liveryMap.get(train).put(name,new TransportSkin(modid,addr));
        }

        liveryMap.get(train).get(name).bogieSkins= Collections.singletonList(bogieSkin);
    }

}
