package train.entity.rollingStock;

import ebf.tim.TrainsInMotion;
import ebf.tim.api.SkinRegistry;
import ebf.tim.api.TransportSkin;
import ebf.tim.entities.GenericRailTransport;
import ebf.tim.items.ItemTransport;
import ebf.tim.registry.TiMItems;
import fexcraft.tmt.slim.ModelBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.Traincraft;
import train.library.Info;

import java.util.List;
import java.util.UUID;

public class EntityTender_C41 extends GenericRailTransport {

    public EntityTender_C41(World worldObj) {
        super(worldObj);
    }

    public EntityTender_C41(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }

    public static final Item thisItem = new ItemTransport(new EntityTender_C41((World)null), Info.modID, Traincraft.tcTab);


    //main stats
    @Override
    public String transportName(){return "Tender C41";}
    @Override
    public String transportcountry(){return "us";}
    @Override
    public String transportYear(){return "1902";}
    @Override
    public boolean isFictional(){return false;}
    @Override
    public int getInventoryRows(){return 1;}
    @Override
    public List<TrainsInMotion.transportTypes> getTypes(){
        return TrainsInMotion.transportTypes.TENDER.singleton();
    }
    @Override
    public float weightKg(){return 49124f;}

    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.render.models.ModelTenderC41()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.1f, 0.005f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 0.0f, 0.0f}};}
    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/C41Tender.png","C41Tender", "description.C41Tender"));
    }
    @Override
    public String getDefaultSkin(){
        return Info.modID+":"+"C41Tender";
    }


    //recipe
    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Items.iron_ingot, 2), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 2),
                new ItemStack(Items.iron_ingot, 2), null, null, null, null, new ItemStack(Items.coal, 2)        };
    }


    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return null;}
    @Override
    public float[] getHitboxSize(){return new float[]{3.7f,1.775f,1.4f};}
    @Override
    public float[] rotationPoints() {return new float[]{1f, -0.875f};}
    @Override
    public int[] getTankCapacity(){return new int[]{22712};}



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