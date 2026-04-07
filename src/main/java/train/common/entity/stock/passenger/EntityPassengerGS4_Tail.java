package train.common.entity.stock.passenger;

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

public class EntityPassengerGS4_Tail extends EntityRollingStock implements IPassenger {

    public static final Item thisItem = new ItemRollingStock(new EntityPassengerGS4_Tail(null), Info.modID+":+GS4_tail", Traincraft.tcTab); 
    
    public EntityPassengerGS4_Tail(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Passenger GS4 Tail";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/GS4_Tail_Orange.png" , new String[]{} ,"Orange", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/GS4_Tail_White.png" , new String[]{} ,"White", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/GS4_Tail_Yellow.png" , new String[]{} ,"Yellow", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/GS4_Tail_Brown.png" , new String[]{} ,"Brown", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/GS4_Tail_Lime.png" , new String[]{} ,"Lime", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/GS4_Tail_Red.png" , new String[]{} ,"Red", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/GS4_Tail_Grey.png" , new String[]{} ,"Grey", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/GS4_Tail_Green.png" , new String[]{} ,"Green", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/GS4_Tail_LightGrey.png" , new String[]{} ,"LightGrey", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/GS4_Tail_Black.png" , new String[]{} ,"Black", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/GS4_Tail_LightBlue.png" , new String[]{} ,"LightBlue", "");
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
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 2000.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{3.3f, -3.3f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, 
new ItemStack(ItemIDs.bogie.item, 2), 
new ItemStack(ItemIDs.steelframe.item, 1), 
null, 
null,
 new ItemStack(ItemIDs.steelcab.item, 1), 
null, 
null, 
new ItemStack(ItemIDs.seats.item, 1),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelGS4Tail()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-0.2f, -0.525f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{{0.8f, 1.0f, 0.8f}};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{6.599999904632568f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }
}


    //these only change in very specific use cases.
