package train.common.library;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fexcraft.tmt.slim.ModelBase;
import net.minecraft.util.ResourceLocation;
import train.common.Traincraft;
import train.common.api.*;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.item.Item;
import train.client.render.RenderEnum;
import train.common.core.handlers.EntityHandler;
import train.common.items.ItemRollingStock;

public class TraincraftRegistry {

    private List<TrainRecord> trainRecords = new ArrayList<>();
    private Map<Item, TrainRecord> trainRecordsByItem = new HashMap<>();
    private Map<Class<?>, TrainRenderRecord> trainRenderRecords = new HashMap<>();
    private List<TrainSoundRecord> trainSoundRecords = new ArrayList<>();

    public TraincraftRegistry() {
    }

    public void init() {
        for (TrainRecord train : EnumTrains.trains()) {
            TraincraftRegistry.this.registerTrainRecord(train);
        }

        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.CLIENT) {
            for (RenderEnum render : RenderEnum.values()) {
                TraincraftRegistry.this.registerTrainRenderRecord(render);
            }
        }

        for (TrainSoundRecord sound : EnumSounds.values()) {
            TraincraftRegistry.this.registerTrainSoundRecord(sound);
        }
    }

    public TrainRecord getTrainRecord(Class<?> entityClass) {
        if (entityClass == null) return null;

        for (TrainRecord record : trainRecords) {
            if (entityClass.equals(record.getEntityClass())) {
                return record;
            }
        }
        return null;
    }

    public TrainSoundRecord getTrainSoundRecord(Class<?> entityClass) {
        if (entityClass == null) return null;

        for (TrainSoundRecord record : trainSoundRecords) {
            if (entityClass.equals(record.getEntityClass())) {
                return record;
            }
        }
        return null;
    }

    public TrainRecord findTrainRecordByItem(Item item) {
        return trainRecordsByItem.get(item);
    }

    public TrainRenderRecord getTrainRenderRecord(Class<?> entityClass) {
        return trainRenderRecords.get(entityClass);
    }

    public void registerTrainRecord(TrainRecord record) {
        trainRecords.add(record);
        trainRecordsByItem.put(record.getItem(), record);
    }

    public void registerTrainRenderRecord(TrainRenderRecord record) {
        trainRenderRecords.put(record.getEntityClass(), record);
    }

    public void registerTrainSoundRecord(TrainSoundRecord sound) {
        trainSoundRecords.add(sound);
    }

    public void addLivery(Class<?> entityClass, String name){
        for (TrainRecord record : trainRecords) {
            if (entityClass.equals(record.getEntityClass()) && !record.getLiveries().contains(name)) {
                record.skins.add(name);
            }
        }
    }


    public static void registerTransports(String MODID, List<AbstractTrains> entities) {
        registerTransports(MODID,entities.toArray(new AbstractTrains[]{}));
    }

    public static void registerTransports(String MODID, AbstractTrains[] entities) {
        for(final AbstractTrains trains : entities){
            EntityRegistry.registerModEntity(trains.getClass(), MODID+":"+trains.transportName(), EntityHandler.trainID, Traincraft.instance, 512, 1, true);
            trains.registerSkins();
            GameRegistry.registerItem(trains.getItem(), MODID+":entity/"+trains.transportName());
            EntityHandler.trainID+=1;
            //todo:this part should be unnecessary? double-check.
            if(Traincraft.proxy.isClient()){
                Traincraft.instance.traincraftRegistry.registerTrainRenderRecord(new TrainRenderRecord() {
                    @Override
                    public Class<? extends AbstractTrains> getEntityClass() {
                        return trains.getClass();
                    }

                    @Override
                    public ModelBase getModel() {
                        return trains.getModel()[0];
                    }

                    @Override
                    public boolean hasSmoke() {
                        return false;
                    }

                    @Override
                    public boolean hasSmokeOnSlopes() {
                        return false;
                    }

                    @Override
                    public String getSmokeType() {
                        return null;
                    }

                    @Override
                    public ArrayList<double[]> getSmokeFX() {
                        return null;
                    }

                    @Override
                    public String getExplosionType() {
                        return null;
                    }

                    @Override
                    public boolean hasExplosion() {
                        return false;
                    }

                    @Override
                    public ArrayList<double[]> getExplosionFX() {
                        return null;
                    }

                    @Override
                    public float[] getTrans() {
                        return new float[]{0,0,0};
                    }

                    @Override
                    public float[] getRotate() {
                        return new float[]{0,0,0};
                    }

                    @Override
                    public float[] getScale() {
                        return new float[]{0,0,0};
                    }

                    @Override
                    public ResourceLocation getTextureFile(String colorString) {
                        return null;
                    }

                    @Override
                    public int getSmokeIterations() {
                        return 0;
                    }

                    @Override
                    public int getExplosionFXIterations() {
                        return 0;
                    }
                });
            }
        }
    }

    private static String typeDecor="decorative",typeDiesel="diesel", typeSteam="steam",typeElectric="electric",typePassenger="passenger",typeTender="tender", typeWork="work",typeFreight="freight",typeTank="tank";
    public static String findTrainType(AbstractTrains t){
        if(t instanceof SteamTrain){
            return typeSteam;
        }
        if(t instanceof DieselTrain){
            return typeDiesel;
        }
        if(t instanceof ElectricTrain){
            return typeElectric;
        }
        if(t instanceof Tender){
            return typeTender;
        }
        if(t instanceof AbstractWorkCart){
            return typeWork;
        }
        if(t instanceof Freight){
            return typeFreight;
        }
        if(t instanceof IPassenger){
            return typePassenger;
        }
        if(t instanceof LiquidTank){
            return typeTank;
        }
        return typeDecor;
    }
}
