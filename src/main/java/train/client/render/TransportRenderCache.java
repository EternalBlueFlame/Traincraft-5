package train.client.render;

import ebf.tim.api.TransportSkin;
import fexcraft.tmt.slim.ModelBase;
import train.common.api.TrainParticle;
import train.common.api.TrainRenderRecord;

public class TransportRenderCache {
    public ModelBase[] models = null;
    public Bogie[] bogies = null;
    public TrainParticle[] effects = null;
    public boolean effectsResolved = false;
    public boolean needs_model_update = true;
    public String color="";
    public TrainRenderRecord rend;
    public TransportSkin skin;
}