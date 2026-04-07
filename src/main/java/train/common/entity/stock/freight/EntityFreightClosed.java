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

public class EntityFreightClosed extends Freight {

    public static final Item thisItem = new ItemRollingStock(new EntityFreightClosed(null), Info.modID+":+train_freight_closed", Traincraft.tcTab); 

    public EntityFreightClosed(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Freight Cart Closed RedBrown";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightClosed.png" , new String[]{} ,"default", "");
    }

    @Override
    public String getDefaultSkin(){return "default";}

    @Override
    public float transportTopSpeed(){return 0;}

    @Override
    public int getInventoryRows(){return 4;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 0;}

	@Override
	public String[] additionalItemText() { return new String[] {"Cargo: any"};}

	@Override
	public float weightKg(){ return 5000.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{1.32f, -1.32f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Blocks.planks, 4), 
new ItemStack(ItemIDs.ironBogie.item, 2), 
new ItemStack(ItemIDs.ironFrame.item, 4), 
new ItemStack(Items.iron_ingot, 2), 
null,
 null, 
null, 
null, 
new ItemStack(Blocks.chest, 1),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelFreightClosed()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.0f, -0.060000002f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 90.0f, 0.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{2.640000104904175f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }
}


    //these only change in very specific use cases.
