package train.common.entity.stock.work;

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

public class EntityCabooseLoggingPRR extends AbstractWorkCart {

    public static final Item thisItem = new ItemRollingStock(new EntityCabooseLoggingPRR(null), Info.modID+":+train_prr_caboose", Traincraft.tcTab); 
    
    public EntityCabooseLoggingPRR(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "PRR Caboose Logging";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/PRRCaboose_Red.png" , new String[]{} ,"Red", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/PRRCaboose_Blue.png" , new String[]{} ,"Blue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/PRRCaboose_Green.png" , new String[]{} ,"Green", "");
    }

    @Override
    public String getDefaultSkin(){return "Red";}

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
	public float weightKg(){ return 400.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{2.2f, -2.2f};}

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
new ItemStack(Blocks.crafting_table, 1),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelPRRCaboose()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.0f, -0.120000005f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{4.400000095367432f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }
}


    //these only change in very specific use cases.
