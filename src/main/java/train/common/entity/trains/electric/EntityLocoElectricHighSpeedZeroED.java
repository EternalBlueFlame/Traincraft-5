package train.common.entity.trains.electric;

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

public class EntityLocoElectricHighSpeedZeroED extends ElectricTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoElectricHighSpeedZeroED(null), Info.modID+":+train_high_speed_loco_zeroed", Traincraft.tcTab); 

    public EntityLocoElectricHighSpeedZeroED(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Loco Electric High Speed ZeroED";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/locoHighSpeedZeroED.png" , new String[]{} ,"default", "");
    }

    @Override
    public String getDefaultSkin(){return "default";}

    @Override
    public float transportTopSpeed(){return 240;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 700;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.0f, -3.4f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(ItemIDs.controls.item, 2), 
new ItemStack(ItemIDs.bogie.item, 2), 
new ItemStack(ItemIDs.steelframe.item, 1), 
new ItemStack(Items.iron_ingot, 1), 
null,
 new ItemStack(ItemIDs.steelcab.item, 1), 
new ItemStack(ItemIDs.transformer.item, 2), 
new ItemStack(ItemIDs.electmotor.item, 2), 
new ItemStack(Items.redstone, 4),
new ItemStack(thisItem)
        };
    }


    @Override
    public Item getItem(){return thisItem;}
    @Override
    public int getTier(){
return 3;
}
    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelLocoHighSpeedZeroED()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-1.7f, -0.060000002f, 0.0f}};}
@Override
    public float[][] modelRotations(){return new float[][]{{0f,180f,180f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{4.520000004768372f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }    //Train specific stuff
    @Override
    public String transportFuelType(){return "electric";}
    @Override
    public int getFuelConsumption(){return 3;}

    @Override
    public int getWaterConsumption(){return 0;}

    @Override
    public double getSpecAccel(){return 1.4;}

    @Override
    public double getSpecBrake(){return 0.98;}

    @Override
    public TrainSound getHorn(){return new TrainSound("tc:high_speed_horn", 0.8f,1f, 0);}

    @Override
    public TrainSound getBell(){return new TrainSound(Info.resourceLocation + ":bell",0.5f,1f, 0);}

    @Override
    public TrainSound getRunningSound(){return new TrainSound("tc:vl10_idle", 0.6f,0.4f, 50);}

    @Override
    public TrainSound getIdleSound(){return new TrainSound("tc:vl10_idle", 0.6f,0.001f, 50);}

    @Override
    public int[] getTankCapacity(){return new int[]{2250};}

}


    //these only change in very specific use cases.
