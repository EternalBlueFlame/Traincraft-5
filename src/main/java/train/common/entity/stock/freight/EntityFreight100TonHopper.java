package train.common.entity.stock.freight;

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

public class EntityFreight100TonHopper extends Freight {

    public static final Item thisItem = new ItemRollingStock(new EntityFreight100TonHopper(null), Info.modID+":+train_100_ton_hopper", Traincraft.tcTab); 

    public EntityFreight100TonHopper(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Freight 100 Ton Hopper";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freight_100tonhopper_Red.png" , new String[]{} ,"Red", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freight_100tonhopper_Black.png" , new String[]{} ,"Black", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freight_100tonhopper_Blue.png" , new String[]{} ,"Blue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freight_100tonhopper_Grey.png" , new String[]{} ,"Grey", "");
    }

    @Override
    public String getDefaultSkin(){return "Red";}

    @Override
    public float transportTopSpeed(){return 0;}

    @Override
    public int getInventoryRows(){return 6;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 0;}

	@Override
	public String[] additionalItemText() { return new String[] {"Cargo: blocks"};}

	@Override
	public float weightKg(){ return 8000.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{2.9f, -2.9f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Items.iron_ingot, 6), 
new ItemStack(ItemIDs.bogie.item, 2), 
new ItemStack(ItemIDs.steelframe.item, 3), 
new ItemStack(Items.iron_ingot, 2), 
null,
 null, 
null, 
null, 
new ItemStack(Blocks.hopper, 3),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.Model100TonHopper()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-0.1f, -0.5f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{{0.9f, 1.0f, 0.9f}};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{5.800000190734863f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }
}


    //these only change in very specific use cases.
