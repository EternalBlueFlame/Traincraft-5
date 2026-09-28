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

public class EntityLocoSteamAlcoSC4 extends SteamTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoSteamAlcoSC4(null), Info.modID+":+train_alcosc4", Traincraft.tcTab); 

    public EntityLocoSteamAlcoSC4(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Loco Steam Alco SC4";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/AlcoSC4.png" , new String[]{} ,"default", "");
    }

    @Override
    public String getDefaultSkin(){return "default";}

    @Override
    public float transportTopSpeed(){return 120;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 800;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.0f, -3.4f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, 
new ItemStack(ItemIDs.ironBogie.item, 2), 
new ItemStack(ItemIDs.ironFrame.item, 2), 
new ItemStack(Items.iron_ingot, 2), 
new ItemStack(ItemIDs.ironChimney.item, 1), 
new ItemStack(ItemIDs.ironCab.item, 1), 
new ItemStack(ItemIDs.ironBoiler.item, 1), 
new ItemStack(ItemIDs.ironFirebox.item, 1), 
new ItemStack(Items.coal, 1),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelAlcoSC4()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-3.5f, 0.0f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{4.600000047683716f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
            new TrainParticle("largesmoke", 3, new double[]{3.55, 1.65, 0.0}),
            new TrainParticle("explode", 2, new double[]{2.9, 0.05, 0.65}),
    };
    }    //Train specific stuff
    @Override
    public String transportFuelType(){return "steam";}
    @Override
    public int getFuelConsumption(){return 80;}

    @Override
    public int getWaterConsumption(){return 100;}

    @Override
    public double getSpecAccel(){return 0.35;}

    @Override
    public double getSpecBrake(){return 0.975;}

    @Override
    public TrainSound getHorn(){return new TrainSound("tc:american_steam_horn", 0.6f,1f, 0);}

    @Override
    public TrainSound getBell(){return new TrainSound(Info.resourceLocation + ":bell",0.5f,1f, 0);}

    @Override
    public TrainSound getRunningSound(){return new TrainSound("tc:steam_run", 0.4f,0.4f, 20);}

    @Override
    public TrainSound getIdleSound(){return new TrainSound("tc:steam_run", 0.4f,0.001f, 20);}

    @Override
    public int getOverheatTime(){return 200;}

    @Override
    public int[] getTankCapacity(){return new int[]{4000, 800};}

}


    //these only change in very specific use cases.
