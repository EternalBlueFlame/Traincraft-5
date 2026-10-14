package train.common.entity.stock.passenger;
import train.client.render.models.ModelFlatCarUS;

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

public class EntityFlatCartUS extends EntityRollingStock implements IPassenger {

    public static final Item thisItem = new ItemRollingStock(new EntityFlatCartUS(null), Info.modID+":+train_flat_us", Traincraft.tcTab); 

    public EntityFlatCartUS(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Flat Cart US";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/flatCartUS.png" , new String[]{} ,"default", "");
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
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 800.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{1.74f, -1.74f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Blocks.PLANKS, 3),
new ItemStack(ItemIDs.bogie.item, 2), 
new ItemStack(ItemIDs.steelframe.item, 2), 
new ItemStack(Items.IRON_INGOT, 2),
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
return 2;
}
    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new ModelFlatCarUS()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0f, -0.47f, 0f}};}
@Override
    public float[][] modelRotations(){return new float[][]{{0f,0f,0f}};}  // 1.7.10 RenderEnum: no rotation (was {0,180,180}: rendered upside down)
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{3.4800000190734863f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }
}


    //these only change in very specific use cases.
