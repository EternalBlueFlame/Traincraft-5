package train.client.render;

import fexcraft.tmt.slim.ModelBase;

import java.util.ArrayList;

public class TransportRenderCache {
    public ArrayList<double[]> smokePosition = null;
    public ModelBase[] models = null;
    public Bogie[] bogies = null;
    public boolean needs_model_update = true;
}