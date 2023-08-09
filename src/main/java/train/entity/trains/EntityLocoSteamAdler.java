package train.entity.trains;

import ebf.tim.TrainsInMotion;
import ebf.tim.api.SkinRegistry;
import ebf.tim.api.TransportSkin;
import ebf.tim.entities.EntityTrainCore;
import ebf.tim.items.ItemTransport;
import ebf.tim.registry.TiMItems;
import ebf.tim.utility.ItemStackSlot;
import fexcraft.tmt.slim.ModelBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.Traincraft;
import train.library.Info;

import java.util.List;
import java.util.UUID;

public class EntityLocoSteamAdler extends EntityTrainCore {

    public EntityLocoSteamAdler(World worldObj) {
        super(worldObj);
    }

    public EntityLocoSteamAdler(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }

    public static final Item thisItem = new ItemTransport(new EntityLocoSteamAdler((World)null), Info.modID, Traincraft.tcTab);


    //main stats
    @Override
    public String transportName(){return "Loco Steam Adler";}
    @Override
    public String transportcountry(){return "germany";}
    @Override
    public String transportYear(){return "1835";}
    @Override
    public boolean isFictional(){return false;}
    @Override
    public int getInventoryRows(){return 1;}
    @Override
    public List<TrainsInMotion.transportTypes> getTypes(){
        return TrainsInMotion.transportTypes.STEAM.singleton();
    }
    @Override
    public float weightKg(){return 11400f;}

    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.render.models.ModelLocoSteamAdler()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.1f, getWorld()==null?-1.2f:-0.9f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, -90.0f, 0.0f}};}
    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/locoAdler.png","locoAdler", "description.locoAdler"));
    }
    @Override
    public String getDefaultSkin(){
        return Info.modID+":"+"locoAdler";
    }


    //recipe
    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Blocks.PLANKS, 8), new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameWood, 2),
                new ItemStack(Items.IRON_INGOT, 2), new ItemStack(TiMItems.chimneyIron, 1), null, new ItemStack(TiMItems.boilerIron, 1), new ItemStack(TiMItems.fireboxIron, 1), new ItemStack(Items.GOLD_INGOT, 2)        };
    }


    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{1,1.4f, 0.2f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{2.5f,2.1f,1.1f};}
    @Override
    public float[] rotationPoints() {return new float[]{0.9f, -0.6f};}
    //Train specific stuff
    @Override
    public String transportFuelType(){return "steam";}
    @Override
    public float transportMetricHorsePower(){return 200;}
    @Override
    public float transportTopSpeed(){return 64.3f;}
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