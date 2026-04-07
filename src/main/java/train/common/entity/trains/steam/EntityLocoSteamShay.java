package train.common.entity.trains.steam;

import fexcraft.tmt.slim.ModelBase;
import ebf.tim.api.SkinRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.api.*;
import train.common.items.ItemRollingStock;
import train.common.library.Info;
import train.common.library.ItemIDs;

public class EntityLocoSteamShay extends SteamTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoSteamShay(null), Info.modID+":+train_shay", Traincraft.tcTab); 

    public EntityLocoSteamShay(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Loco Steam Shay";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/locoShay.png" , new String[]{} ,"default", "");
    }

    @Override
    public String getDefaultSkin(){return "default";}

    @Override
    public float transportTopSpeed(){return 50;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 250;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.0f, -1.0f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, 
new ItemStack(ItemIDs.ironBogie.item, 3), 
new ItemStack(ItemIDs.ironFrame.item, 2), 
new ItemStack(Items.iron_ingot, 2), 
new ItemStack(ItemIDs.ironChimney.item, 1), 
new ItemStack(ItemIDs.ironCab.item, 1), 
new ItemStack(ItemIDs.ironBoiler.item, 2), 
new ItemStack(ItemIDs.ironFirebox.item, 1), 
new ItemStack(Items.coal, 1),
new ItemStack(thisItem)
        };
    }


    @Override
    public Item getItem(){return thisItem;}
    @Override
    public int getTier(){
return 1;
}
    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelLocoSteamShay()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-0.4f, -0.050000012f, -0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 0.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{4.0f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
            new TrainParticle("largesmoke", 20, new double[]{1.8, 1.7, 0.2}),
            new TrainParticle("explode", 2, new double[]{1.6, -0.4, 0.8}),
    };
    }    //Train specific stuff
    @Override
    public String transportFuelType(){return "steam";}
    @Override
    public int getFuelConsumption(){return 50;}

    @Override
    public int getWaterConsumption(){return 160;}

    @Override
    public double getSpecAccel(){return 0.5;}

    @Override
    public double getSpecBrake(){return 0.968;}

    @Override
    public TrainSound getHorn(){return new TrainSound("tc:shay_horn", 0.8f,1f, 0);}

    @Override
    public TrainSound getBell(){return new TrainSound(Info.resourceLocation + ":bell",0.5f,1f, 0);}

    @Override
    public TrainSound getRunningSound(){return new TrainSound("tc:steam_run", 0.2f,0.4f, 20);}

    @Override
    public TrainSound getIdleSound(){return new TrainSound("tc:steam_run", 0.2f,0.001f, 20);}

    @Override
    public int getOverheatTime(){return 130;}

    @Override
    public int[] getTankCapacity(){return new int[]{4000, 800};}

}


    //these only change in very specific use cases.
