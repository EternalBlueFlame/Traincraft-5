package train.entity.rollingStock;

import ebf.tim.TrainsInMotion;
import ebf.tim.api.SkinRegistry;
import ebf.tim.api.TransportSkin;
import ebf.tim.entities.GenericRailTransport;
import ebf.tim.items.ItemTransport;
import ebf.tim.registry.TiMItems;
import fexcraft.tmt.slim.ModelBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.Traincraft;
import train.library.Info;

import java.util.List;
import java.util.UUID;

public class EntityFreightDenverRioGrande extends GenericRailTransport {

    public EntityFreightDenverRioGrande(World worldObj) {
        super(worldObj);
    }

    public EntityFreightDenverRioGrande(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }

    public static final Item thisItem = new ItemTransport(new EntityFreightDenverRioGrande((World)null), Info.modID, Traincraft.tcTab);


    //main stats
    @Override
    public String transportName(){return "Denver Rio Grande Baggage";}
    @Override
    public String transportcountry(){return "us";}
    @Override//probably wrong, this is actually taken from:
    // https://www.historycolorado.org/sites/default/files/media/documents/2017/5ah3006.pdf
    public String transportYear(){return "1914-1960";}
    @Override
    public boolean isFictional(){return false;}
    @Override
    public int getInventoryRows(){return getInventoryRowCount();}
    @Override
    public List<TrainsInMotion.transportTypes> getTypes(){
        return TrainsInMotion.transportTypes.FREIGHT.singleton();
    }
    @Override//probably wrong, this is actually taken from:
    // https://www.historycolorado.org/sites/default/files/media/documents/2017/5ah3006.pdf
    public float weightKg(){return 58967f;}

    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.render.models.ModelDRGBaggage()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.0f, 0.0f, 0.0f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 0.0f, 0.0f}};}
    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/drg_baggage_Yellow.png","Yellow", "description.drg.baggage.Yellow"));
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/drg_baggage_Red.png","Red", "description.drg.baggage.Red"));
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/drg_baggage_Green.png","Green", "description.drg.baggage.Green"));
    }
    @Override
    public String getDefaultSkin(){
        return Info.modID+":"+"Yellow";
    }


    //recipe
    @Override
    public ItemStack[] getRecipie() {
        return new ItemStack[]{
                new ItemStack(Blocks.CHEST, 4), new ItemStack(TiMItems.wheelIron, 4), new ItemStack(TiMItems.frameWood, 3),
                null, null, new ItemStack(TiMItems.cabinWood, 3),
                null, null, null        };
    }


    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return null;}
    @Override
    public float[] getHitboxSize(){return new float[]{7.2f,2.35f,1.4f};}
    @Override
    public float[] rotationPoints() {return new float[]{2.075f, -2.1f};}



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