package train.entity.rollingStock;

import ebf.tim.TrainsInMotion;
import ebf.tim.api.SkinRegistry;
import ebf.tim.api.TransportSkin;
import ebf.tim.entities.GenericRailTransport;
import ebf.tim.items.ItemTransport;
import ebf.tim.registry.TiMItems;
import fexcraft.tmt.slim.ModelBase;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.Traincraft;
import train.library.Info;

import java.util.List;
import java.util.UUID;

public class EntityTenderA4 extends GenericRailTransport {

    public EntityTenderA4(World worldObj) {
        super(worldObj);
    }

    public EntityTenderA4(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }

    public static final Item thisItem = new ItemTransport(new EntityTenderA4((World)null), Info.modID, Traincraft.tcTab);


    //main stats
    @Override
    public String transportName(){return "Tender A4 Mallard";}
    @Override
    public String transportcountry(){return "uk";}
    @Override
    public String transportYear(){return "1938";}
    @Override
    public boolean isFictional(){return false;}
    @Override
    public int getInventoryRows(){return getInventoryRowCount();}
    @Override
    public List<TrainsInMotion.transportTypes> getTypes(){
        return TrainsInMotion.transportTypes.TENDER.singleton();
    }
    @Override
    public float weightKg(){return 63045.7f;}

    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.render.models.ModelTenderA4()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{-0.25f, 0.005f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 0.0f, 0.0f}};}
    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/tenderA4_UK_Blue.png","Blue", "description.tenderA4.UK.Blue"));
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/tenderA4_UK_Lime.png","Lime", "description.tenderA4.UK.Lime"));
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/tenderA4_UK_Black.png","Black", "description.tenderA4.UK.Black"));
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/tenderA4_UK_Green.png","Green", "description.tenderA4.UK.Green"));
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/tenderA4_UK_White.png","White", "description.tenderA4.UK.White"));
    }
    @Override
    public String getDefaultSkin(){
        return Info.modID+":"+"Blue";
    }


    //recipe
    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameSteel, 2),
                new ItemStack(Items.IRON_INGOT, 1), null, null, null, null, new ItemStack(Items.COAL, 1)        };
    }


    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return null;}
    @Override
    public float[] getHitboxSize(){return new float[]{4.25f,2.6f,1.3f};}
    @Override
    public float[] rotationPoints() {return new float[]{0.95f, -1.3f};}
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