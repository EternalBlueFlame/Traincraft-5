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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.Traincraft;
import train.library.Info;

import java.util.List;
import java.util.UUID;

public class EntityLocoSteamBerk765 extends EntityTrainCore {

    public EntityLocoSteamBerk765(World worldObj) {
        super(worldObj);
    }

    public EntityLocoSteamBerk765(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }

    public static final Item thisItem = new ItemTransport(new EntityLocoSteamBerk765((World)null), Info.modID, Traincraft.tcTab);


    //main stats
    @Override
    public String transportName(){return "Loco Berkshire 765";}
    @Override
    public String transportcountry(){return "us";}
    @Override
    public String transportYear(){return "1944";}
    @Override
    public boolean isFictional(){return false;}
    @Override
    public int getInventoryRows(){return 1;}
    @Override
    public List<TrainsInMotion.transportTypes> getTypes(){
        return TrainsInMotion.transportTypes.STEAM.singleton();
    }
    @Override
    public float weightKg(){return 199943.5f;}

    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.render.models.Model765()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.2f, 0.8f, -0.0625f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, -180.0f, 0.0f}};}
    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/Berk765_Locomotive.png","Berk765_Locomotive", "description.Berk765_Locomotive"));
    }
    @Override
    public String getDefaultSkin(){
        return Info.modID+":"+"Berk765_Locomotive";
    }


    //recipe
    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, new ItemStack(TiMItems.wheelSteel, 4), new ItemStack(TiMItems.frameSteel, 4),
                new ItemStack(TiMOres.ingotSteel, 2), new ItemStack(TiMItems.chimneySteel, 2), new ItemStack(TiMItems.cabinSteel, 1),
                new ItemStack(TiMItems.boilerIron, 3), new ItemStack(TiMItems.fireboxSteel, 3), null        };
    }


    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{3.4f,1.4f, 0.35f},{3.4f,1.4f, -0.35f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{7.35f,2.5f,1.5f};}
    @Override
    public float[] rotationPoints() {return new float[]{1f, -2.0f};}
    //Train specific stuff
    @Override
    public String transportFuelType(){return "steam";}
    @Override
    public float transportMetricHorsePower(){return 2563;}
    @Override
    public float transportTractiveEffort(){return 64135;}
    @Override
    public float transportTopSpeed(){return 128.7f;}
    @Override
    public ItemStackSlot fuelSlot(){
        return super.fuelSlot();
    }
    @Override
    public int[] getTankCapacity(){return new int[]{8320, 800};}



    //these only change in very specific use cases.
    @Override
    public boolean shouldRiderSit(){
        return true;
    }
    @Override
    public Item getItem(){return thisItem;}
    @Override
    public float getMaxFuel(){return 1;}

}