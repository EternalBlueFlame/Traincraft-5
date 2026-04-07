package train.common.entity.stock.special;

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

public class EntityPropagandaUSSR extends EntityRollingStock {

    public static final Item thisItem = new ItemRollingStock(new EntityPropagandaUSSR(null), Info.modID+":+propaganda_ussr", Traincraft.tcTab); 

    public EntityPropagandaUSSR(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Propaganda USSR";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/propaganda_USSR_Blue.png" , new String[]{} ,"Blue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/propaganda_USSR_White.png" , new String[]{} ,"White", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/propaganda_USSR_Red.png" , new String[]{} ,"Red", "");
    }

    @Override
    public String getDefaultSkin(){return "Blue";}

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
	public float weightKg(){ return 200.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{3.7f, -3.7f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Blocks.planks, 2), 
new ItemStack(ItemIDs.ironBogie.item, 2), 
new ItemStack(ItemIDs.woodenFrame.item, 2), 
null, 
null,
 null, 
null, 
null, 
new ItemStack(Items.painting, 2),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelPropagandaCar()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{1.1f, -0.7f, -0.1f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 180.0f, 180.0f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{7.400000095367432f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }
}


    //these only change in very specific use cases.
