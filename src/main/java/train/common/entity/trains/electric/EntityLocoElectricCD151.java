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

public class EntityLocoElectricCD151 extends ElectricTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoElectricCD151(null), Info.modID+":+train_CD151", Traincraft.tcTab); 
    
    public EntityLocoElectricCD151(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Loco Electric CD151";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/CD151_Blue.png" , new String[]{} ,"Blue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/CD151_Yellow.png" , new String[]{} ,"Yellow", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/CD151_Red.png" , new String[]{} ,"Red", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/CD151_Green.png" , new String[]{} ,"Green", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/CD151_Cyan.png" , new String[]{} ,"Cyan", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/CD151_Brown.png" , new String[]{} ,"Brown", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/CD151_Orange.png" , new String[]{} ,"Orange", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/CD151_Purple.png" , new String[]{} ,"Purple", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/CD151_White.png" , new String[]{} ,"White", "");
    }

    @Override
    public String getDefaultSkin(){return "Blue";}

    @Override
    public float transportTopSpeed(){return 160;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 1133;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.0f, -4.0f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(ItemIDs.controls.item, 2), 
new ItemStack(ItemIDs.bogie.item, 2), 
new ItemStack(ItemIDs.steelframe.item, 1), 
new ItemStack(Items.iron_ingot, 2), 
null,
 new ItemStack(ItemIDs.steelcab.item, 2), 
new ItemStack(ItemIDs.electmotor.item, 3), 
new ItemStack(ItemIDs.dieselengine.item, 3), 
new ItemStack(ItemIDs.generator.item, 2),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelLocoCD151()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-2.0f, -0.68f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{{0.9f, 1.0f, 0.9f}};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{4.949999988079071f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }    //Train specific stuff
    @Override
    public String transportFuelType(){return "electric";}
    @Override
    public int getFuelConsumption(){return 10;}

    @Override
    public int getWaterConsumption(){return 0;}

    @Override
    public double getSpecAccel(){return 0.7;}

    @Override
    public double getSpecBrake(){return 0.965;}

    @Override
    public TrainSound getHorn(){return new TrainSound("tc:mg_horn", 1.0f,1f, 0);}

    @Override
    public TrainSound getBell(){return new TrainSound(Info.resourceLocation + ":bell",0.5f,1f, 0);}

    @Override
    public TrainSound getRunningSound(){return new TrainSound("tc:mg_run", 0.8f,0.4f, 10);}

    @Override
    public TrainSound getIdleSound(){return new TrainSound("tc:mg_idle", 0.6f,0.001f, 50);}

    @Override
    public int[] getTankCapacity(){return new int[]{6850};}

}


    //these only change in very specific use cases.
