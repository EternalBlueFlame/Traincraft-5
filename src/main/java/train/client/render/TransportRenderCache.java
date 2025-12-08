package train.client.render;

import ebf.tim.api.TransportSkin;
import fexcraft.tmt.slim.ModelBase;
import train.common.api.TrainRenderRecord;

import java.util.ArrayList;

public class TransportRenderCache {
    public ArrayList<double[]> smokePosition = null;
    public ModelBase[] models = null;
    public Bogie[] bogies = null;
    public boolean needs_model_update = true;
    public String color="";
    public TrainRenderRecord rend;
    public TransportSkin skin;
}