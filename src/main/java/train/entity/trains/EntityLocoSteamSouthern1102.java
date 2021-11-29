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

public class EntityLocoSteamSouthern1102 extends EntityTrainCore {

    public EntityLocoSteamSouthern1102(World worldObj) {
        super(worldObj);
    }

    public EntityLocoSteamSouthern1102(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }

    public static final Item thisItem = new ItemTransport(new EntityLocoSteamSouthern1102((World)null), Info.modID, Traincraft.tcTab);


    //main stats
    @Override
    public String transportName(){return "Loco Steam Southern 1102";}
    @Override
    public String transportcountry(){return "us";}
    @Override
    public String transportYear(){return "1903-1935";}
    @Override
    public boolean isFictional(){return false;}
    @Override
    public int getInventoryRows(){return 1;}
    @Override
    public List<TrainsInMotion.transportTypes> getTypes(){
        return TrainsInMotion.transportTypes.STEAM.singleton();
    }
    @Override
    public float weightKg(){return 124450f;}

    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.render.models.ModelSouthern1102()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-1.5f, 0.7f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 0.0f, 0.0f}};}
    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/Southern1102.png","Southern1102", "description.Southern1102"));
    }
    @Override
    public String getDefaultSkin(){
        return Info.modID+":"+"Southern1102";
    }


    //recipe
    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameIron, 2),
                new ItemStack(Items.iron_ingot, 2), new ItemStack(TiMItems.chimneyIron, 1), new ItemStack(TiMItems.cabinIron, 1),
                new ItemStack(TiMItems.boilerIron, 2), new ItemStack(TiMItems.fireboxIron, 1), new ItemStack(Items.COAL, 1)        };
    }


    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{2.1f,1.5f, -0.3f},{2.1f,1.5f, 0.3f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{5f,2.4f,1.4f};}
    @Override
    public float[] rotationPoints() {return new float[]{1.3f, -2f};}
    //Train specific stuff
    @Override
    public String transportFuelType(){return "steam";}
    @Override
    public float transportMetricHorsePower(){return 1236;}
    @Override
    public float transportTopSpeed(){return 118;}
    @Override
    public ItemStackSlot fuelSlot(){
        return super.fuelSlot();
    }
    @Override
    public int[] getTankCapacity(){return new int[]{10000, 2000};}



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