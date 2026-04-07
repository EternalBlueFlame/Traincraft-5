package train.common.entity.stock.tender;

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

public class EntityTenderFowler4F extends Tender {

    public static final Item thisItem = new ItemRollingStock(new EntityTenderFowler4F(null), Info.modID+":+Fowler 4F Tender", Traincraft.tcTab); 

    public EntityTenderFowler4F(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Fowler 4F Tender";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/Fowler_4F_Tender.png" , new String[]{} ,"default", "");
    }

    @Override
    public String getDefaultSkin(){return "default";}

    @Override
    public float transportTopSpeed(){return 0;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 0;}

	@Override
	public String[] additionalItemText() { return new String[] {"Water capacity: 15000mb"};}

	@Override
	public float weightKg(){ return 4000.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{1.8f, -1.8f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, 
new ItemStack(ItemIDs.bogie.item, 3), 
new ItemStack(ItemIDs.steelframe.item, 3), 
new ItemStack(Items.iron_ingot, 2), 
null,
 null, 
null, 
null, 
new ItemStack(Items.coal, 2),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelFowler4FTender()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-5.25f, 0.015850008f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{3.5999999046325684f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }    @Override
    public int[] getTankCapacity(){return new int[]{15000};}
}


    //these only change in very specific use cases.
