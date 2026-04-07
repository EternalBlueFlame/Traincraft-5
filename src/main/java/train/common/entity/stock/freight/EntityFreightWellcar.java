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

public class EntityFreightWellcar extends Freight {

    public static final Item thisItem = new ItemRollingStock(new EntityFreightWellcar(null), Info.modID+":+train_freight_wellcar", Traincraft.tcTab); 

    public EntityFreightWellcar(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Freight Well Car";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_Blue.png" , new String[]{} ,"Blue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_Red.png" , new String[]{} ,"Red", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_Green.png" , new String[]{} ,"Green", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_Black.png" , new String[]{} ,"Black", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_Grey.png" , new String[]{} ,"Grey", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_Cyan.png" , new String[]{} ,"Cyan", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_Brown.png" , new String[]{} ,"Brown", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_Lime.png" , new String[]{} ,"Lime", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_LightBlue.png" , new String[]{} ,"LightBlue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_LightGrey.png" , new String[]{} ,"LightGrey", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_Magenta.png" , new String[]{} ,"Magenta", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_Orange.png" , new String[]{} ,"Orange", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_Pink.png" , new String[]{} ,"Pink", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_Purple.png" , new String[]{} ,"Purple", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_White.png" , new String[]{} ,"White", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/wellcar_Yellow.png" , new String[]{} ,"Yellow", "");
    }

    @Override
    public String getDefaultSkin(){return "Blue";}

    @Override
    public float transportTopSpeed(){return 0;}

    @Override
    public int getInventoryRows(){return 6;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 0;}

	@Override
	public String[] additionalItemText() { return new String[] {"Cargo: any"};}

	@Override
	public float weightKg(){ return 6000.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{1.8f, -1.8f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Items.iron_ingot, 5), 
new ItemStack(ItemIDs.bogie.item, 2), 
new ItemStack(ItemIDs.steelframe.item, 2), 
new ItemStack(Items.iron_ingot, 2), 
null,
 null, 
null, 
null, 
new ItemStack(Blocks.chest, 2),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelWellcar()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.0f, -0.5f, 0.0f}};}
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
    }
}


    //these only change in very specific use cases.
