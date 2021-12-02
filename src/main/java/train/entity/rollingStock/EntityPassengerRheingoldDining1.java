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

public class EntityPassengerRheingoldDining1 extends GenericRailTransport {

    public EntityPassengerRheingoldDining1(World worldObj) {
        super(worldObj);
    }

    public EntityPassengerRheingoldDining1(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }

    public static final Item thisItem = new ItemTransport(new EntityPassengerRheingoldDining1((World)null), Info.modID, Traincraft.tcTab);


    //main stats
    @Override
    public String transportName(){return "Rheingold Dining";}
    @Override
    public String transportcountry(){return "Germany";}
    @Override
    public String transportYear(){return "1965";}
    @Override
    public boolean isFictional(){return false;}
    @Override
    public int getInventoryRows(){return 0;}
    @Override
    public List<TrainsInMotion.transportTypes> getTypes(){
        return TrainsInMotion.transportTypes.WORKCAR.singleton();
    }
    @Override
    public float weightKg(){return 45359.2f;}

    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.render.models.ModelRheingoldPassenger_Dining1()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{1.7f, -0.005f, 0.675f}};}
    @Override
    public float[][] modelRotations(){return new float[][]{{0.0f, 0.0f, 0.0f}};}
    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/Rheingold_passenger_dining1_Blue.png","Blue", "description.Rheingold.passenger.dining1.Blue"));
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/Rheingold_passenger_dining1_Red.png","Red", "description.Rheingold.passenger.dining1.Red"));
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/Rheingold_passenger_dining1_Green.png","Green", "description.Rheingold.passenger.dining1.Green"));
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/Rheingold_passenger_dining1_LightBlue.png","LightBlue", "description.Rheingold.passenger.dining1.LightBlue"));
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/Rheingold_passenger_dining1_Magenta.png","Magenta", "description.Rheingold.passenger.dining1.Magenta"));
        SkinRegistry.addSkin(this.getClass(),
            new TransportSkin(Info.modID,"textures/trains/Rheingold_passenger_dining1_Lime.png","Lime", "description.Rheingold.passenger.dining1.Lime"));
    }
    @Override
    public String getDefaultSkin(){
        return Info.modID+":"+"Blue";
    }


    //recipe
    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1),
                null, null, new ItemStack(TiMItems.cabinSteel, 1),
                null, null, new ItemStack(Blocks.CRAFTING_TABLE, 1)        };
    }


    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{8.65f,2.25f,1.45f};}
    @Override
    public float[] rotationPoints() {return new float[]{2.55f, -2.7f};}



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