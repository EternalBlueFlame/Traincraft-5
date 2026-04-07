package train.common.entity.stock.tanker;

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

public class EntityTankWagon extends LiquidTank {

    public static final Item thisItem = new ItemRollingStock(new EntityTankWagon(null), Info.modID+":+train_tank_us", Traincraft.tcTab); 

    public EntityTankWagon(World world) {
    super (world); } 
    //main stats
    @Override
    public String transportName(){return "Tank Wagon Yellow";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/tankwagon.png" , new String[]{} ,"default", "");
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
	public String[] additionalItemText() { return new String[] {"Capacity: 40000mb"};}

	@Override
	public float weightKg(){ return 12000.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{1.7f, -1.7f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Items.iron_ingot, 6), 
new ItemStack(ItemIDs.bogie.item, 2), 
new ItemStack(ItemIDs.steelframe.item, 2), 
new ItemStack(Items.iron_ingot, 2), 
null,
 null, 
null, 
null, 
new ItemStack(Items.potionitem, 1),
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
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelTankWagon()};}
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
    public float[] getHitboxSize(){return new float[]{3.4000000953674316f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
    };
    }    @Override
    public int[] getTankCapacity(){return new int[]{40000};}
}


    //these only change in very specific use cases.
