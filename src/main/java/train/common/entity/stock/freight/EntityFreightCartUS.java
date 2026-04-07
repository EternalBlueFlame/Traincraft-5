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

public class EntityFreightCartUS extends Freight {

    public static final Item thisItem = new ItemRollingStock(new EntityFreightCartUS(null), Info.modID+":+train_freight_3", Traincraft.tcTab); 

    public EntityFreightCartUS(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Freight Cart US";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_Brown.png" , new String[]{} ,"Brown", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_Yellow.png" , new String[]{} ,"Yellow", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_Black.png" , new String[]{} ,"Black", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_Blue.png" , new String[]{} ,"Blue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_Cyan.png" , new String[]{} ,"Cyan", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_Green.png" , new String[]{} ,"Green", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_Grey.png" , new String[]{} ,"Grey", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_LightBlue.png" , new String[]{} ,"LightBlue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_LightGrey.png" , new String[]{} ,"LightGrey", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_Lime.png" , new String[]{} ,"Lime", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_Magenta.png" , new String[]{} ,"Magenta", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_Orange.png" , new String[]{} ,"Orange", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_Pink.png" , new String[]{} ,"Pink", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_Purple.png" , new String[]{} ,"Purple", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_Red.png" , new String[]{} ,"Red", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/freightCartUS_White.png" , new String[]{} ,"White", "");
    }

    @Override
    public String getDefaultSkin(){return "Brown";}

    @Override
    public float transportTopSpeed(){return 0;}

    @Override
    public int getInventoryRows(){return 4;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 0;}

	@Override
	public String[] additionalItemText() { return new String[] {"Cargo: blocks, vanilla items"};}

	@Override
	public float weightKg(){ return 7000.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{1.56f, -1.56f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Blocks.planks, 6), 
new ItemStack(ItemIDs.ironBogie.item, 2), 
new ItemStack(ItemIDs.ironFrame.item, 2), 
new ItemStack(Items.iron_ingot, 2), 
null,
 null, 
null, 
null, 
new ItemStack(Blocks.hopper, 2),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelFreightCartUS()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.0f, -0.030000001f, 0.0f}};}
@Override
    public float[][] modelRotations(){return new float[][]{{0f,180f,180f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{3.119999885559082f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }
}


    //these only change in very specific use cases.
