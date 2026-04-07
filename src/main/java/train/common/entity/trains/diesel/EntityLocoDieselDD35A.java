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

public class EntityLocoDieselDD35A extends DieselTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoDieselDD35A(null), Info.modID+":+train_dd35a", Traincraft.tcTab); 

    public EntityLocoDieselDD35A(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Loco Diesel DD35A";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/dd35a_Orange.png" , new String[]{} ,"Orange", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/dd35a_Black.png" , new String[]{} ,"Black", "");
    }

    @Override
    public String getDefaultSkin(){return "Orange";}

    @Override
    public float transportTopSpeed(){return 145;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 5070;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.0f, -4.75f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(ItemIDs.controls.item, 2), 
new ItemStack(ItemIDs.bogie.item, 8), 
new ItemStack(ItemIDs.steelframe.item, 3), 
new ItemStack(Items.iron_ingot, 2), 
new ItemStack(ItemIDs.steelchimney.item, 2), 
new ItemStack(ItemIDs.steelcab.item, 1), 
new ItemStack(ItemIDs.electmotor.item, 8), 
new ItemStack(ItemIDs.dieselengine.item, 11), 
new ItemStack(ItemIDs.generator.item, 4),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelDD35A()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-5.0f, -0.68f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{{0.9f, 1.0f, 0.9f}};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{7.349999904632568f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
            new TrainParticle("smoke", 4, new double[]{3.9, 1.6, 0.0}),
            new TrainParticle("smoke", 4, new double[]{-0.4, 1.6, 0.0}),
    };
    }    //Train specific stuff
    @Override
    public String transportFuelType(){return "diesel";}
    @Override
    public int getFuelConsumption(){return 10;}

    @Override
    public int getWaterConsumption(){return 0;}

    @Override
    public double getSpecAccel(){return 0.7;}

    @Override
    public double getSpecBrake(){return 0.965;}

    @Override
    public TrainSound getHorn(){return new TrainSound("tc:sd70_horn", 0.8f,1f, 0);}

    @Override
    public TrainSound getBell(){return new TrainSound(Info.resourceLocation + ":bell",0.5f,1f, 0);}

    @Override
    public TrainSound getRunningSound(){return new TrainSound("tc:chme3_idle", 0.65f,0.4f, 40);}

    @Override
    public TrainSound getIdleSound(){return new TrainSound("tc:chme3_idle", 0.65f,0.001f, 40);}

    @Override
    public int[] getTankCapacity(){return new int[]{6850};}

}


    //these only change in very specific use cases.
