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

public class EntityLocoDieselBamboo extends DieselTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoDieselBamboo(null), Info.modID+":+train_train_bamboo", Traincraft.tcTab); 

    public EntityLocoDieselBamboo(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Bamboo Flatcar Engine";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_Red.png" , new String[]{} ,"Red", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_Blue.png" , new String[]{} ,"Blue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_Black.png" , new String[]{} ,"Black", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_Yellow.png" , new String[]{} ,"Yellow", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_Magenta.png" , new String[]{} ,"Magenta", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_Cyan.png" , new String[]{} ,"Cyan", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_Pink.png" , new String[]{} ,"Pink", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_LightGrey.png" , new String[]{} ,"LightGrey", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_Green.png" , new String[]{} ,"Green", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_White.png" , new String[]{} ,"White", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_LightBlue.png" , new String[]{} ,"LightBlue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_Lime.png" , new String[]{} ,"Lime", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_Brown.png" , new String[]{} ,"Brown", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_Purple.png" , new String[]{} ,"Purple", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_Orange.png" , new String[]{} ,"Orange", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/loco_bamboo_Grey.png" , new String[]{} ,"Grey", "");
    }

    @Override
    public String getDefaultSkin(){return "Red";}

    @Override
    public float transportTopSpeed(){return 20;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 30;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.0f, -2.0f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, 
new ItemStack(ItemIDs.woodenBogie.item, 2), 
new ItemStack(ItemIDs.woodenFrame.item, 1), 
null, 
null,
 null, 
null, 
new ItemStack(ItemIDs.dieselengine.item, 1), 
null,
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelBambooTrainEngine()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-1.0f, -0.5f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{180.0f, 180.0f, 0.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{2.600000023841858f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
            new TrainParticle("smoke", 2, new double[]{-0.05, -0.05, 0.0}),
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
    public TrainSound getHorn(){return new TrainSound("tc:gp_horn", 0.0f,1f, 0);}

    @Override
    public TrainSound getBell(){return new TrainSound(Info.resourceLocation + ":bell",0.5f,1f, 0);}

    @Override
    public TrainSound getRunningSound(){return new TrainSound("tc:chme3_idle", 0.2f,0.4f, 40);}

    @Override
    public TrainSound getIdleSound(){return new TrainSound("tc:chme3_idle", 0.1f,0.001f, 40);}

    @Override
    public int[] getTankCapacity(){return new int[]{3000};}

}


    //these only change in very specific use cases.
