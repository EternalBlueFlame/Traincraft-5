package train.common.entity.stock.tanker;

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

public class EntityTankWagonThreeDome extends LiquidTank {

    public static final Item thisItem = new ItemRollingStock(new EntityTankWagonThreeDome(null), Info.modID+":+train_tank_three_dome", Traincraft.tcTab); 

    public EntityTankWagonThreeDome(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Tank Wagon Three Dome";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/tanker_three_dome_Green.png" , new String[]{} ,"Green", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/tanker_three_dome_White.png" , new String[]{} ,"White", "");
    }

    @Override
    public String getDefaultSkin(){return "Green";}

    @Override
    public float transportTopSpeed(){return 0;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 0;}

	@Override
	public String[] additionalItemText() { return new String[] {"Capacity: 90000mb"};}

	@Override
	public float weightKg(){ return 15000.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{2.7f, -2.7f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Items.iron_ingot, 4), 
new ItemStack(ItemIDs.bogie.item, 2), 
new ItemStack(ItemIDs.steelframe.item, 3), 
new ItemStack(Items.iron_ingot, 4), 
null,
 null, 
null, 
null, 
new ItemStack(Items.potionitem, 1),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelTankWagonThreeDome()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.0f, -0.67f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{5.400000095367432f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }    @Override
    public int[] getTankCapacity(){return new int[]{90000};}
}


    //these only change in very specific use cases.
