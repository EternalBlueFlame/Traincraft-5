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

public class EntityLocoDieselSD40 extends DieselTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoDieselSD40(null), Info.modID+":+train_sd40", Traincraft.tcTab); 

    public EntityLocoDieselSD40(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Loco Diesel SD40";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/locoSD40_Red.png" , new String[]{} ,"Red", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/locoSD40_Yellow.png" , new String[]{} ,"Yellow", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/locoSD40_Black.png" , new String[]{} ,"Black", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/locoSD40_Green.png" , new String[]{} ,"Green", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/locoSD40_Orange.png" , new String[]{} ,"Orange", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/locoSD40_Magenta.png" , new String[]{} ,"Magenta", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/locoSD40_Blue.png" , new String[]{} ,"Blue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/locoSD40_Pink.png" , new String[]{} ,"Pink", "");
    }

    @Override
    public String getDefaultSkin(){return "Red";}

    @Override
    public float transportTopSpeed(){return 105;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 3041;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.0f, -2.3f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(ItemIDs.controls.item, 2), 
new ItemStack(ItemIDs.bogie.item, 6), 
new ItemStack(ItemIDs.steelframe.item, 2), 
new ItemStack(Items.iron_ingot, 2), 
new ItemStack(ItemIDs.steelchimney.item, 1), 
new ItemStack(ItemIDs.steelcab.item, 1), 
new ItemStack(ItemIDs.electmotor.item, 6), 
new ItemStack(ItemIDs.dieselengine.item, 6), 
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelSD40()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-1.2f, -0.030000001f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{180.0f, 90.0f, 0.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{{-1.0f, -1.0f, 1.0f}};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{4.7000000953674315f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
            new TrainParticle("smoke", 5, new double[]{1.45, 1.45, 0.0}),
    };
    }    //Train specific stuff
    @Override
    public String transportFuelType(){return "diesel";}
    @Override
    public int getFuelConsumption(){return 60;}

    @Override
    public int getWaterConsumption(){return 0;}

    @Override
    public double getSpecAccel(){return 0.8;}

    @Override
    public double getSpecBrake(){return 0.97;}

    @Override
    public TrainSound getHorn(){return new TrainSound("tc:gp_horn", 0.8f,1f, 0);}

    @Override
    public TrainSound getBell(){return new TrainSound(Info.resourceLocation + ":bell",0.5f,1f, 0);}

    @Override
    public TrainSound getRunningSound(){return new TrainSound("tc:chme3_idle", 0.65f,0.4f, 40);}

    @Override
    public TrainSound getIdleSound(){return new TrainSound("tc:chme3_idle", 0.65f,0.001f, 40);}

    @Override
    public int[] getTankCapacity(){return new int[]{20000};}

}


    //these only change in very specific use cases.
