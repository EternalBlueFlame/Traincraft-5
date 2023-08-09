package train.entity.rollingStock;

import ebf.tim.TrainsInMotion;
import ebf.tim.api.SkinRegistry;
import ebf.tim.api.TransportSkin;
import ebf.tim.entities.GenericRailTransport;
import ebf.tim.items.ItemTransport;
import ebf.tim.registry.TiMItems;
import ebf.tim.registry.TiMOres;
import fexcraft.tmt.slim.ModelBase;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.Traincraft;
import train.library.Info;

import java.util.List;
import java.util.UUID;

public class EntityTenderBR01_DB extends GenericRailTransport {

    public EntityTenderBR01_DB(World worldObj) {
        super(worldObj);
    }

    public EntityTenderBR01_DB(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }

    public static final Item thisItem = new ItemTransport(new EntityTenderBR01_DB((World)null), Info.modID, Traincraft.tcTab);


    //main stats
    @Override
    public String transportName(){return "Tender BR01";}
    @Override
    public String transportcountry(){return "germany";}
    @Override
    public String transportYear(){return "1926–1938";}
    @Override
    public boolean isFictional(){return false;}
    @Override
    public int getInventoryRows(){return getInventoryRowCount();}
    @Override
    public List<TrainsInMotion.transportTypes> getTypes(){
        return TrainsInMotion.transportTypes.TENDER.singleton();
    }
    @Override//this is taken from the weight difference of the 56500 of TCDD, BR52, and it's tender,
    //     which is documented as the same tender as the BR01, a 2'2'T32.
    public float weightKg(){return 68300f;}

    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.render.models.ModelTenderBR01_DB()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-0.05f, 0.64f, 0.0f}};}
@Override
    public float[][] modelRotations(){return new float[][]{{0f,180f,180f}};}
    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/tenderBR01_DB.png","tenderBR01_DB", "description.tenderBR01_DB"));
    }
    @Override
    public String getDefaultSkin(){
        return Info.modID+":"+"tenderBR01_DB";
    }


    //recipe
    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(TiMOres.ingotSteel, 4), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2),
                new ItemStack(TiMOres.ingotSteel, 2), null, null, null, null, new ItemStack(Items.COAL, 1)        };
    }


    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return null;}
    @Override
    public float[] getHitboxSize(){return new float[]{3.125f,2.2f,1.5f};}
    @Override
    public float[] rotationPoints() {return new float[]{0.65f, -0.8f};}
    @Override
    public int[] getTankCapacity(){return new int[]{getTankVolume()};}



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