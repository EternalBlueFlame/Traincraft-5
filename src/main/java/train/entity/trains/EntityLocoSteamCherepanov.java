package train.entity.trains;

import ebf.tim.TrainsInMotion;
import ebf.tim.api.SkinRegistry;
import ebf.tim.api.TransportSkin;
import ebf.tim.entities.EntityTrainCore;
import ebf.tim.items.ItemTransport;
import ebf.tim.registry.TiMItems;
import ebf.tim.utility.ItemStackSlot;
import fexcraft.tmt.slim.ModelBase;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.Traincraft;
import train.library.Info;

import java.util.List;
import java.util.UUID;

public class EntityLocoSteamCherepanov extends EntityTrainCore {

    public EntityLocoSteamCherepanov(World worldObj) {
        super(worldObj);
    }

    public EntityLocoSteamCherepanov(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }

    public static final Item thisItem = new ItemTransport(new EntityLocoSteamCherepanov((World)null), Info.modID, Traincraft.tcTab);


    //main stats
    @Override
    public String transportName(){return "Loco Steam Cherepanov";}
    @Override
    public String transportcountry(){return "russia";}
    @Override
    public String transportYear(){return "1833-1834";}
    @Override
    public boolean isFictional(){return false;}
    @Override
    public int getInventoryRows(){return 1;}
    @Override
    public List<TrainsInMotion.transportTypes> getTypes(){
        return TrainsInMotion.transportTypes.STEAM.singleton();
    }
    @Override
    public float weightKg(){return 16380.7f;}

    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.render.models.ModelLocoCherepanov()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-0.075f, getWorld()==null?0.2f:0.6f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 0.0f, -180.0f}};}
    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/locoCherepanov.png","locoCherepanov", "description.locoCherepanov"));
    }
    @Override
    public String getDefaultSkin(){
        return Info.modID+":"+"locoCherepanov";
    }


    //recipe
    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 1),
                new ItemStack(Items.STICK, 1), new ItemStack(TiMItems.chimneyIron, 1), null, new ItemStack(TiMItems.boilerIron, 1), new ItemStack(TiMItems.fireboxIron, 1), null        };
    }


    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0.85f,1.35f, 0.1f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{getWorld()==null?2.8f:2.2f,2.7f,1.1f};}
    @Override
    public float[] rotationPoints() {return new float[]{0.1f, -0.6f};}
    //Train specific stuff
    @Override
    public String transportFuelType(){return "steam";}
    @Override
    public float transportMetricHorsePower(){return 60;}
    @Override
    public float transportTopSpeed(){return 30;}
    @Override
    public ItemStackSlot fuelSlot(){
        return super.fuelSlot();
    }
    @Override
    public int[] getTankCapacity(){return new int[]{3000, 600};}



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