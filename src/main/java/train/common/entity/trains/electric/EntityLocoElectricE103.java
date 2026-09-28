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

public class EntityLocoElectricE103 extends ElectricTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoElectricE103(null), Info.modID+":+train_e103", Traincraft.tcTab); 

    public EntityLocoElectricE103(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Loco Electric E103";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/E103_Red.png" , new String[]{} ,"Red", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/E103_Blue.png" , new String[]{} ,"Blue", "");
    }

    @Override
    public String getDefaultSkin(){return "Red";}

    @Override
    public float transportTopSpeed(){return 200;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 2806;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.0f, -3.6f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(ItemIDs.controls.item, 2), 
new ItemStack(ItemIDs.bogie.item, 2), 
new ItemStack(ItemIDs.steelframe.item, 2), 
new ItemStack(Items.iron_ingot, 2), 
new ItemStack(ItemIDs.steelchimney.item, 2), 
new ItemStack(ItemIDs.steelcab.item, 2), 
new ItemStack(ItemIDs.electmotor.item, 2), 
new ItemStack(ItemIDs.transformer.item, 2), 
null,
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelE103()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-2.0f, -0.55f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{{0.9f, 1.0f, 0.9f}};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{5.590000009536743f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }    //Train specific stuff
    @Override
    public String transportFuelType(){return "electric";}
    @Override
    public int getFuelConsumption(){return 8;}

    @Override
    public int getWaterConsumption(){return 0;}

    @Override
    public double getSpecAccel(){return 0.66;}

    @Override
    public double getSpecBrake(){return 0.96;}

    @Override
    public TrainSound getHorn(){return new TrainSound("tc:mg_horn", 1.0f,1f, 0);}

    @Override
    public TrainSound getBell(){return new TrainSound(Info.resourceLocation + ":bell",0.5f,1f, 0);}

    @Override
    public TrainSound getRunningSound(){return new TrainSound("tc:mg_run", 0.6f,0.4f, 8);}

    @Override
    public TrainSound getIdleSound(){return new TrainSound("tc:mg_idle", 0.4f,0.001f, 50);}

    @Override
    public int[] getTankCapacity(){return new int[]{8000};}

}


    //these only change in very specific use cases.
