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

public class EntityLocoSteamBR01_DB extends SteamTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoSteamBR01_DB(null), Info.modID+":+train_br01", Traincraft.tcTab); 
    
    public EntityLocoSteamBR01_DB(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Loco Steam BR01";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/locoBR01_DB_Black.png" , new String[]{} ,"Black", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/locoBR01_DB_Grey.png" , new String[]{} ,"Grey", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/locoBR01_DB_LightGrey.png" , new String[]{} ,"LightGrey", "");
    }

    @Override
    public String getDefaultSkin(){return "Black";}

    @Override
    public float transportTopSpeed(){return 130;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 2120;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.0f, -3.7f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, 
new ItemStack(ItemIDs.bogie.item, 3), 
new ItemStack(ItemIDs.steelframe.item, 2), 
new ItemStack(Items.iron_ingot, 2), 
new ItemStack(ItemIDs.steelchimney.item, 1), 
new ItemStack(ItemIDs.steelcab.item, 1), 
new ItemStack(ItemIDs.boiler.item, 2), 
new ItemStack(ItemIDs.firebox.item, 1), 
null,
new ItemStack(thisItem)
        };
    }


    @Override
    public Item getItem(){return thisItem;}
    @Override
    public int getTier(){
return 2;
}
    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelLocoBR01_DB()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-1.0f, -0.060000002f, 0.0f}};}
@Override
    public float[][] modelRotations(){return new float[][]{{0f,180f,180f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{4.500000011920929f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
            new TrainParticle("largesmoke", 3, new double[]{3.45, 1.8, 0.0}),
            new TrainParticle("explode", 2, new double[]{3.4, -0.2, 0.8}),
    };
    }    //Train specific stuff
    @Override
    public String transportFuelType(){return "steam";}
    @Override
    public int getFuelConsumption(){return 60;}

    @Override
    public int getWaterConsumption(){return 200;}

    @Override
    public double getSpecAccel(){return 0.6;}

    @Override
    public double getSpecBrake(){return 0.97;}

    @Override
    public TrainSound getHorn(){return new TrainSound("tc:german_steam_horn", 0.8f,1f, 0);}

    @Override
    public TrainSound getBell(){return new TrainSound(Info.resourceLocation + ":bell",0.5f,1f, 0);}

    @Override
    public TrainSound getRunningSound(){return new TrainSound("tc:steam_run", 0.4f,0.4f, 20);}

    @Override
    public TrainSound getIdleSound(){return new TrainSound("tc:steam_run", 0.4f,0.001f, 20);}

    @Override
    public int getOverheatTime(){return 300;}

    @Override
    public int[] getTankCapacity(){return new int[]{10000, 2000};}

}


    //these only change in very specific use cases.
