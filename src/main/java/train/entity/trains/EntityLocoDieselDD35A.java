package train.entity.trains;

import ebf.tim.TrainsInMotion;
import ebf.tim.api.SkinRegistry;
import ebf.tim.api.TransportSkin;
import ebf.tim.entities.EntityTrainCore;
import ebf.tim.items.ItemTransport;
import ebf.tim.registry.TiMItems;
import ebf.tim.registry.TiMOres;
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

public class EntityLocoDieselDD35A extends EntityTrainCore {

    public EntityLocoDieselDD35A(World worldObj) {
        super(worldObj);
    }

    public EntityLocoDieselDD35A(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }

    public static final Item thisItem = new ItemTransport(new EntityLocoDieselDD35A((World)null), Info.modID, Traincraft.tcTab);


    //main stats
    @Override
    public String transportName(){return "Loco Diesel DD35A";}
    @Override
    public String transportcountry(){return "us";}
    @Override
    public String transportYear(){return "1965";}
    @Override
    public boolean isFictional(){return false;}
    @Override
    public int getInventoryRows(){return 1;}
    @Override
    public List<TrainsInMotion.transportTypes> getTypes(){
        return TrainsInMotion.transportTypes.DIESEL.singleton();
    }
    @Override
    public float weightKg(){return 235575f;}

    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.render.models.ModelDD35A()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-2.8f, 0.0f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 0.0f, 0.0f}};}
    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/dd35a_Orange.png","Orange", "description.dd35a.Orange"));
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/dd35a_Black.png","Black", "description.dd35a.Black"));
    }
    @Override
    public String getDefaultSkin(){
        return Info.modID+":"+"Orange";
    }


    //recipe
    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(TiMItems.controlPanel, 2), new ItemStack(TiMItems.wheelSteel, 8), new ItemStack(TiMItems.frameSteel, 3),
                new ItemStack(TiMOres.ingotSteel, 2), new ItemStack(TiMItems.chimneySteel, 2), new ItemStack(TiMItems.cabinSteel, 1),
                new ItemStack(TiMItems.smallElectricEngine, 8), new ItemStack(TiMItems.smallDieselEngine, 11), new ItemStack(TiMItems.generator, 4)        };
    }


    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{8.3f,2.6f,1.2f};}
    @Override
    public float[] rotationPoints() {return new float[]{2.6f, -2.6f};}
    //Train specific stuff
    @Override
    public String transportFuelType(){return "diesel";}
    @Override
    public float transportMetricHorsePower(){return 5070;}
    @Override
    public float transportTopSpeed(){return 145;}
    @Override
    public ItemStackSlot fuelSlot(){
        return super.fuelSlot();
    }
    @Override
    public int[] getTankCapacity(){return new int[]{31150};}



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