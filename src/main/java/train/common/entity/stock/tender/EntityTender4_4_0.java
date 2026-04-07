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

public class EntityTender4_4_0 extends Tender {

    public static final Item thisItem = new ItemRollingStock(new EntityTender4_4_0(null), Info.modID+":+train_tender_steam", Traincraft.tcTab); 

    public EntityTender4_4_0(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Tender 4-4-0";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/4-4-0-loco_tender_Black.png" , new String[]{} ,"Black", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/4-4-0-loco_tender_White.png" , new String[]{} ,"White", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/4-4-0-loco_tender_Brown.png" , new String[]{} ,"Brown", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/4-4-0-loco_tender_Blue.png" , new String[]{} ,"Blue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/4-4-0-loco_tender_Green.png" , new String[]{} ,"Green", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/4-4-0-loco_tender_Red.png" , new String[]{} ,"Red", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/4-4-0-loco_tender_Purple.png" , new String[]{} ,"Purple", "");
    }

    @Override
    public String getDefaultSkin(){return "Black";}

    @Override
    public float transportTopSpeed(){return 0;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 0;}

	@Override
	public String[] additionalItemText() { return new String[] {"Water capacity: 8000mb"};}

	@Override
	public float weightKg(){ return 400.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{1.15f, -1.15f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, 
new ItemStack(ItemIDs.ironBogie.item, 2), 
new ItemStack(ItemIDs.woodenFrame.item, 1), 
new ItemStack(Items.stick, 2), 
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelNormalSteamTender()};}
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
    public float[] getHitboxSize(){return new float[]{2.299999952316284f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }    @Override
    public int[] getTankCapacity(){return new int[]{8000};}
}


    //these only change in very specific use cases.
