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

public class EntityLocoSteamAlice0_4_0 extends SteamTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoSteamAlice0_4_0(null), Info.modID+":+train_alice", Traincraft.tcTab); 

    public EntityLocoSteamAlice0_4_0(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Loco Steam Alice";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/0-4-0-loco-alice.png" , new String[]{} ,"default", "");
    }

    @Override
    public String getDefaultSkin(){return "default";}

    @Override
    public float transportTopSpeed(){return 32;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 200;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.0f, -2.0f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Blocks.planks, 2), 
new ItemStack(ItemIDs.ironBogie.item, 2), 
new ItemStack(ItemIDs.woodenFrame.item, 1), 
null, 
new ItemStack(ItemIDs.ironChimney.item, 1), 
null, 
new ItemStack(ItemIDs.boiler.item, 1), 
new ItemStack(ItemIDs.firebox.item, 1), 
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelLocoAlice040()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.0f, -0.65f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{{0.9f, 1.0f, 0.9f}};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{3.799999952316284f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
            new TrainParticle("smoke", 20, new double[]{2.2, 1.6, 0.0}),
            new TrainParticle("explode", 2, new double[]{1.8, -0.4, 0.8}),
    };
    }    //Train specific stuff
    @Override
    public String transportFuelType(){return "steam";}
    @Override
    public int getFuelConsumption(){return 60;}

    @Override
    public int getWaterConsumption(){return 160;}

    @Override
    public double getSpecAccel(){return 0.5;}

    @Override
    public double getSpecBrake(){return 0.968;}

    @Override
    public TrainSound getHorn(){return new TrainSound("tc:german_steam_horn", 0.8f,1f, 0);}

    @Override
    public TrainSound getBell(){return new TrainSound(Info.resourceLocation + ":bell",0.5f,1f, 0);}

    @Override
    public TrainSound getRunningSound(){return new TrainSound("tc:steam_run", 0.4f,0.4f, 20);}

    @Override
    public TrainSound getIdleSound(){return new TrainSound("tc:steam_run", 0.4f,0.001f, 20);}

    @Override
    public int getOverheatTime(){return 200;}

    @Override
    public int[] getTankCapacity(){return new int[]{3750, 750};}

}


    //these only change in very specific use cases.
