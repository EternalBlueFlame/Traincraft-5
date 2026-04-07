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

public class EntityTenderAdler extends Tender {

    public static final Item thisItem = new ItemRollingStock(new EntityTenderAdler(null), Info.modID+":+train_tender_adler", Traincraft.tcTab); 

    public EntityTenderAdler(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Tender Adler";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/tender_adler.png" , new String[]{} ,"default", "");
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
	public String[] additionalItemText() { return new String[] {"Water capacity: 4000mb"};}

	@Override
	public float weightKg(){ return 1000.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{0.55f, -0.55f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Items.iron_ingot, 2), 
new ItemStack(ItemIDs.woodenBogie.item, 2), 
new ItemStack(ItemIDs.woodenFrame.item, 1), 
new ItemStack(Items.iron_ingot, 1), 
null,
 null, 
null, 
null, 
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelTenderAdler()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.0f, -1.55f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{180.0f, -90.0f, 0.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{1.100000023841858f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }    @Override
    public int[] getTankCapacity(){return new int[]{4000};}
}


    //these only change in very specific use cases.
