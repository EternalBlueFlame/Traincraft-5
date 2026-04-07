package train.common.entity.trains.diesel;

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

public class EntityLocoDieselShunter extends DieselTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoDieselShunter(null), Info.modID+":+train_shunter", Traincraft.tcTab); 
    
    public EntityLocoDieselShunter(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Loco Diesel Shunter";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/shunter_Blue.png" , new String[]{} ,"Blue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/shunter_Green.png" , new String[]{} ,"Green", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/shunter_Red.png" , new String[]{} ,"Red", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/shunter_Black.png" , new String[]{} ,"Black", "");
    }

    @Override
    public String getDefaultSkin(){return "Blue";}

    @Override
    public float transportTopSpeed(){return 32;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 354;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.0f, -2.58f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(ItemIDs.controls.item, 1), 
new ItemStack(ItemIDs.bogie.item, 4), 
new ItemStack(ItemIDs.steelframe.item, 2), 
new ItemStack(Items.iron_ingot, 2), 
new ItemStack(ItemIDs.steelchimney.item, 1), 
new ItemStack(ItemIDs.steelcab.item, 1), 
new ItemStack(ItemIDs.transmition.item, 1), 
new ItemStack(ItemIDs.dieselengine.item, 3), 
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelShunter()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-1.2f, -0.048999995f, 0.0f}};}
@Override
    public float[][] modelRotations(){return new float[][]{{0f,180f,180f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{3.780000047683716f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
            new TrainParticle("smoke", 5, new double[]{2.4, 1.5, -0.1}),
            new TrainParticle("smoke", 5, new double[]{2.4, 1.5, 0.1}),
    };
    }    //Train specific stuff
    @Override
    public String transportFuelType(){return "diesel";}
    @Override
    public int getFuelConsumption(){return 70;}

    @Override
    public int getWaterConsumption(){return 0;}

    @Override
    public double getSpecAccel(){return 0.6;}

    @Override
    public double getSpecBrake(){return 0.94;}

    @Override
    public TrainSound getHorn(){return new TrainSound("tc:british_two_tone", 0.8f,1f, 0);}

    @Override
    public TrainSound getBell(){return new TrainSound(Info.resourceLocation + ":bell",0.5f,1f, 0);}

    @Override
    public TrainSound getRunningSound(){return new TrainSound("tc:chme3_idle", 0.65f,0.4f, 40);}

    @Override
    public TrainSound getIdleSound(){return new TrainSound("tc:chme3_idle", 0.65f,0.001f, 40);}

    @Override
    public int[] getTankCapacity(){return new int[]{8000};}

}


    //these only change in very specific use cases.
