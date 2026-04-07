package train.common.entity.stock.passenger;

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

public class EntityPassengerRheingoldPanorama extends EntityRollingStock implements IPassenger {

    public static final Item thisItem = new ItemRollingStock(new EntityPassengerRheingoldPanorama(null), Info.modID+":+train_passenger_rheingoldpanorama", Traincraft.tcTab); 

    public EntityPassengerRheingoldPanorama(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Rheingold Panorama";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/Rheingold_passenger_panorama_Blue.png" , new String[]{} ,"Blue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/Rheingold_passenger_panorama_Red.png" , new String[]{} ,"Red", "");
    }

    @Override
    public String getDefaultSkin(){return "Blue";}

    @Override
    public float transportTopSpeed(){return 0;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 0;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 2000.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{3.95f, -3.95f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Blocks.glass, 1), 
new ItemStack(ItemIDs.bogie.item, 2), 
new ItemStack(ItemIDs.steelframe.item, 1), 
null, 
null,
 new ItemStack(ItemIDs.steelcab.item, 1), 
null, 
null, 
new ItemStack(ItemIDs.seats.item, 1),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelRheingoldPassenger_Panorama()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-0.1f, -0.65f, 0.1f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{{0.9f, 1.0f, 0.9f}};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{7.900000095367432f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }
}


    //these only change in very specific use cases.
