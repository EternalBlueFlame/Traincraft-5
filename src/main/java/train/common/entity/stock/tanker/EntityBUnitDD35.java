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

public class EntityBUnitDD35 extends LiquidTank {

    public static final Item thisItem = new ItemRollingStock(new EntityBUnitDD35(null), Info.modID+":+train_dd35b", Traincraft.tcTab); 

    public EntityBUnitDD35(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "DD35 B Unit";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/dd35b_Orange.png" , new String[]{} ,"Orange", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/dd35b_Black.png" , new String[]{} ,"Black", "");
    }

    @Override
    public String getDefaultSkin(){return "Orange";}

    @Override
    public float transportTopSpeed(){return 0;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 0;}

	@Override
	public String[] additionalItemText() { return new String[] {"Reduces weight carried by 80 tons when fueled"};}

	@Override
	public float weightKg(){ return 16000.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{3.1f, -3.1f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, 
new ItemStack(ItemIDs.bogie.item, 8), 
new ItemStack(ItemIDs.steelframe.item, 3), 
new ItemStack(Items.iron_ingot, 2), 
new ItemStack(ItemIDs.steelchimney.item, 2), 
new ItemStack(ItemIDs.steelcab.item, 1), 
new ItemStack(ItemIDs.electmotor.item, 8), 
new ItemStack(ItemIDs.dieselengine.item, 11), 
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelDD35B()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.0f, -0.68f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{{0.9f, 1.0f, 0.9f}};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{6.199999809265137f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }    @Override
    public int[] getTankCapacity(){return new int[]{12000};}
}


    //these only change in very specific use cases.
