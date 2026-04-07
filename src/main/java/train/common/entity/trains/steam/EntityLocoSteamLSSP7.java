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

public class EntityLocoSteamLSSP7 extends SteamTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoSteamLSSP7(null), Info.modID+":+train_lssp7", Traincraft.tcTab); 

    public EntityLocoSteamLSSP7(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Loco Steam LSSP7";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/LSSP7.png" , new String[]{} ,"default", "");
    }

    @Override
    public String getDefaultSkin(){return "default";}

    @Override
    public float transportTopSpeed(){return 45;}

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
	public float[] rotationPoints(){ return new float[]{0.0f, -1.1f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, 
new ItemStack(ItemIDs.woodenBogie.item, 2), 
new ItemStack(ItemIDs.woodenFrame.item, 2), 
null, 
null,
 new ItemStack(ItemIDs.ironCab.item, 1), 
new ItemStack(ItemIDs.ironBoiler.item, 1), 
new ItemStack(ItemIDs.ironFirebox.item, 1), 
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelLSSP7()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-1.0f, -0.3f, 0.8f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{{1.0f, 1.0f, 1.0f}};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{2.1f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
            new TrainParticle("smoke", 20, new double[]{0.1, 1.15, 0.0}),
            new TrainParticle("explode", 2, new double[]{0.7, -0.4, 0.5}),
            new TrainParticle("explode", 2, new double[]{0.7, -0.4, -0.5}),
    };
    }    //Train specific stuff
    @Override
    public String transportFuelType(){return "steam";}
    @Override
    public int getFuelConsumption(){return 140;}

    @Override
    public int getWaterConsumption(){return 140;}

    @Override
    public double getSpecAccel(){return 0.5;}

    @Override
    public double getSpecBrake(){return 0.968;}

    @Override
    public TrainSound getHorn(){return new TrainSound("tc:american_steam_horn", 0.8f,1f, 0);}

    @Override
    public TrainSound getBell(){return new TrainSound(Info.resourceLocation + ":bell",0.5f,1f, 0);}

    @Override
    public TrainSound getRunningSound(){return new TrainSound("tc:steam_run", 0.2f,0.4f, 20);}

    @Override
    public TrainSound getIdleSound(){return new TrainSound("tc:steam_run", 0.2f,0.001f, 20);}

    @Override
    public int getOverheatTime(){return 160;}

    @Override
    public int[] getTankCapacity(){return new int[]{5000, 1000};}

}


    //these only change in very specific use cases.
