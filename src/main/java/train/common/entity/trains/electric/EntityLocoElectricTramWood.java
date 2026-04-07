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

public class EntityLocoElectricTramWood extends ElectricTrain {

    public static final Item thisItem = new ItemRollingStock(new EntityLocoElectricTramWood(null), Info.modID+":+train_tram", Traincraft.tcTab); 
    
    public EntityLocoElectricTramWood(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Loco Electric Yellow Wood Tram";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/tram.png" , new String[]{} ,"default", "");
    }

    @Override
    public String getDefaultSkin(){return "default";}

    @Override
    public float transportTopSpeed(){return 55;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 300;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.0f, -2.0f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Blocks.planks, 4), 
new ItemStack(ItemIDs.ironBogie.item, 2), 
new ItemStack(ItemIDs.woodenFrame.item, 1), 
new ItemStack(Items.iron_ingot, 1), 
null,
 new ItemStack(ItemIDs.woodenCab.item, 1), 
new ItemStack(ItemIDs.transformer.item, 1), 
new ItemStack(ItemIDs.electmotor.item, 2), 
new ItemStack(Items.redstone, 4),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelTram()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-0.8f, -0.060000002f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 0.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{3.399999976158142f,2.1f,1.1f};}
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
    public double getSpecAccel(){return 0.5;}

    @Override
    public double getSpecBrake(){return 0.965;}

    @Override
    public TrainSound getHorn(){return new TrainSound("tc:tram_bell", 1.0f,1f, 0);}

    @Override
    public TrainSound getBell(){return new TrainSound(Info.resourceLocation + ":bell",0.5f,1f, 0);}

    @Override
    public TrainSound getRunningSound(){return new TrainSound("tc:vl10_idle", 0.6f,0.4f, 50);}

    @Override
    public TrainSound getIdleSound(){return new TrainSound("tc:vl10_idle", 0.6f,0.001f, 50);}

    @Override
    public int[] getTankCapacity(){return new int[]{2250};}

}


    //these only change in very specific use cases.
