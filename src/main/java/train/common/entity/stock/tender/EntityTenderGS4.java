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

public class EntityTenderGS4 extends Tender {

    public static final Item thisItem = new ItemRollingStock(new EntityTenderGS4(null), Info.modID+":+GS4_tender", Traincraft.tcTab); 
    
    public EntityTenderGS4(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Tender GS4";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/GS4_tender_Orange.png" , new String[]{} ,"Orange", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/GS4_tender_White.png" , new String[]{} ,"White", "");
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
	public String[] additionalItemText() { return new String[] {"Water capacity: 18000mb"};}

	@Override
	public float weightKg(){ return 4000.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{2.1f, -2.1f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Items.coal, 2), 
new ItemStack(ItemIDs.bogie.item, 2), 
new ItemStack(ItemIDs.steelframe.item, 1), 
null, 
null,
 null, 
null, 
null, 
null,
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelGS4Tender()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.0f, -0.65f, -0.05f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{{0.8f, 1.0f, 0.8f}};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{4.199999809265137f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }    @Override
    public int[] getTankCapacity(){return new int[]{18000};}
}


    //these only change in very specific use cases.
