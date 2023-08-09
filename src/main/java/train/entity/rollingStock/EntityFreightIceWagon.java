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

public class EntityFreightIceWagon extends GenericRailTransport {

    public EntityFreightIceWagon(World worldObj) {
        super(worldObj);
    }

    public EntityFreightIceWagon(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }

    public static final Item thisItem = new ItemTransport(new EntityFreightIceWagon((World)null), Info.modID, Traincraft.tcTab);


    //main stats
    @Override
    public String transportName(){return "Freight Ice Wagon";}
    @Override
    public String transportcountry(){return "Uk";}
    @Override
    public String transportYear(){return "1980";}
    @Override
    public boolean isFictional(){return true;}
    @Override
    public int getInventoryRows(){return getInventoryRowCount();}
    @Override
    public List<TrainsInMotion.transportTypes> getTypes(){
        return TrainsInMotion.transportTypes.FREIGHT.singleton();
    }
    @Override//cart is known fictional, so we're just going off original stats.
    public float weightKg(){return 9071.85f;}

    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.render.models.ModelIceWagon()};}
    @Override
    public float[][] modelOffsets(){return getWorld()==null?new float[][]{{0.0f, -0.5f, 0.0f}}:new float[][]{{0.0f, 0.0f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 0.0f, 0.0f}};}
    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/icewagon.png","icewagon", "description.icewagon"));
    }
    @Override
    public String getDefaultSkin(){
        return Info.modID+":"+"icewagon";
    }


    //recipe
    @Override
    public ItemStack[] getRecipie() {
        return new ItemStack[]{
                null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameIron, 1),
                new ItemStack(Items.IRON_INGOT, 2), null, null, null, null, new ItemStack(Items.SNOWBALL, 9)        };
    }


    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return null;}
    @Override
    public float[] getHitboxSize(){return getWorld()==null?new float[]{2f,1.4f,1.1f}:new float[]{1.75f,1.1f,1.0f};}
    @Override
    public float[] rotationPoints() {return new float[]{0.295f, -0.285f};}



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