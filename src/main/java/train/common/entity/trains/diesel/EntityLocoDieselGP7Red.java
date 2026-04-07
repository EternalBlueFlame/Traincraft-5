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

public class EntityLocoDieselGP7Red extends DieselTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoDieselGP7Red(null), Info.modID+":+train_gp7", Traincraft.tcTab); 

    public EntityLocoDieselGP7Red(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Loco Diesel GP7";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Red.png" , new String[]{} ,"Red", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Blue.png" , new String[]{} ,"Blue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Black.png" , new String[]{} ,"Black", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Yellow.png" , new String[]{} ,"Yellow", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Magenta.png" , new String[]{} ,"Magenta", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Cyan.png" , new String[]{} ,"Cyan", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Pink.png" , new String[]{} ,"Pink", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_LightGrey.png" , new String[]{} ,"LightGrey", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Green.png" , new String[]{} ,"Green", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_White.png" , new String[]{} ,"White", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_LightBlue.png" , new String[]{} ,"LightBlue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Lime.png" , new String[]{} ,"Lime", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Brown.png" , new String[]{} ,"Brown", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Purple.png" , new String[]{} ,"Purple", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Orange.png" , new String[]{} ,"Orange", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Grey.png" , new String[]{} ,"Grey", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/gp7_Skin16.png" , new String[]{} ,"Skin16", "");
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
	public float transportMetricHorsePower(){return 2464;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.0f, -1.4f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(ItemIDs.controls.item, 2), 
new ItemStack(ItemIDs.bogie.item, 3), 
new ItemStack(ItemIDs.steelframe.item, 2), 
new ItemStack(Items.iron_ingot, 2), 
new ItemStack(ItemIDs.steelchimney.item, 1), 
new ItemStack(ItemIDs.steelcab.item, 1), 
new ItemStack(ItemIDs.electmotor.item, 4), 
new ItemStack(ItemIDs.dieselengine.item, 4), 
new ItemStack(ItemIDs.generator.item, 3),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelGP7()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-0.8f, -0.030000001f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 0.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{3.6799999713897704f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
            new TrainParticle("smoke", 4, new double[]{-0.2, 1.3, 0.0}),
            new TrainParticle("smoke", 4, new double[]{0.45, 1.3, 0.0}),
    };
    }    //Train specific stuff
    @Override
    public String transportFuelType(){return "diesel";}
    @Override
    public int getFuelConsumption(){return 50;}

    @Override
    public int getWaterConsumption(){return 0;}

    @Override
    public double getSpecAccel(){return 0.74;}

    @Override
    public double getSpecBrake(){return 0.96;}

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
