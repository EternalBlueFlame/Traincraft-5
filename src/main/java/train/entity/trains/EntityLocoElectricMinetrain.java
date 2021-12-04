package train.entity.trains;

import ebf.tim.TrainsInMotion;
import ebf.tim.api.SkinRegistry;
import ebf.tim.api.TransportSkin;
import ebf.tim.entities.EntityTrainCore;
import ebf.tim.items.ItemTransport;
import ebf.tim.registry.TiMItems;
import ebf.tim.utility.ItemStackSlot;
import fexcraft.tmt.slim.ModelBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.Traincraft;
import train.library.Info;

import java.util.List;
import java.util.UUID;

public class EntityLocoElectricMinetrain extends EntityTrainCore {

    public EntityLocoElectricMinetrain(World worldObj) {
        super(worldObj);
    }

    public EntityLocoElectricMinetrain(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }

    public static final Item thisItem = new ItemTransport(new EntityLocoElectricMinetrain((World)null), Info.modID, Traincraft.tcTab);


    //main stats
    @Override
    public String transportName(){return "Loco Electric Minetrain";}
    @Override
    public String transportcountry(){return null;}
    @Override
    public String transportYear(){return null;}
    @Override
    public boolean isFictional(){return true;}
    @Override
    public int getInventoryRows(){return 1;}
    @Override
    public List<TrainsInMotion.transportTypes> getTypes(){
        return TrainsInMotion.transportTypes.ELECTRIC.singleton();
    }
    @Override//the average minecart weighs anywhere from 500lb to 1500lb, considering this one hauls them im guessing probably double max?
    public float weightKg(){return 1360f;}

    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.render.models.ModelLocoMinetrain()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-0.2f, world==null?0.4f:0.62f, 0.0f}};}
@Override
    public float[][] modelRotations(){return new float[][]{{0f,180f,180f}};}
    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/locoMinetrain.png","locoMinetrain", "description.locoMinetrain"));
    }
    @Override
    public String getDefaultSkin(){
        return Info.modID+":"+"locoMinetrain";
    }


    //recipe
    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Items.IRON_INGOT, 2), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 1),
                new ItemStack(Items.IRON_INGOT, 1), null, new ItemStack(TiMItems.controlPanel, 1),
                new ItemStack(TiMItems.transformer, 1), new ItemStack(TiMItems.smallElectricEngine, 2), new ItemStack(Items.REDSTONE, 1)        };
    }


    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0.4f,1.225f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{world==null?2.3f:1.9f,1.6f,0.8f};}
    @Override
    public float[] rotationPoints() {return new float[]{0.05f, -0.5f};}
    //Train specific stuff
    @Override
    public String transportFuelType(){return "electric";}
    @Override
    public float transportMetricHorsePower(){return 500;}
    @Override
    public float transportTopSpeed(){return 40;}
    @Override
    public ItemStackSlot fuelSlot(){
        return super.fuelSlot();
    }
    public int[] getTankCapacity(){return new int[]{2250};}



    //these only change in very specific use cases.
    @Override
    public boolean shouldRiderSit(){
        return false;
    }
    @Override
    public Item getItem(){return thisItem;}
    @Override
    public float getMaxFuel(){return 1;}

}