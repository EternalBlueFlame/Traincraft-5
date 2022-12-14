package train.entity.trains;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.TrainsInMotion;
import ebf.tim.api.SkinRegistry;
import ebf.tim.api.TransportSkin;
import ebf.tim.entities.EntityTrainCore;
import ebf.tim.entities.GenericRailTransport;
import ebf.tim.items.ItemTransport;
import ebf.tim.models.Bogie;
import ebf.tim.registry.TiMItems;
import ebf.tim.utility.FuelHandler;
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

public class EntityLocoSteam4_4_0_Remaster extends EntityTrainCore {

    /**
     CONVERSION NOTES:
     Model information can still be found in
     @see train.render.RenderEnum
     Transport stats can still be found in
     @see train.library.EnumTrains
     */

    public EntityLocoSteam4_4_0_Remaster(UUID owner, World world, double xPos, double yPos, double zPos) {
        super(owner, world, xPos, yPos, zPos);
    }
    public EntityLocoSteam4_4_0_Remaster(World w){
        super(w);
    }

    public static final Item thisItem = new ItemTransport(new EntityLocoSteam4_4_0_Remaster(null), Info.modID, Traincraft.tcTab);


    @Override
    public String transportName(){return "American(Remastered)";}
    @Override
    public String transportcountry(){return "us";}
    @Override
    public String transportYear(){return "1855";}
    @Override
    public String transportFuelType(){return "Steam";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        //todo: needs skin credits
        //make base skin and register
        String description = "description.4-4-0-loco";
        SkinRegistry.addSkin(this.getClass(),
                new TransportSkin(Info.modID,"textures/trains/steam/4-4-0-loco_Blank.png","Blank", description));
        SkinRegistry.addSkin(this.getClass(),
                new TransportSkin(Info.modID,"textures/trains/steam/4-4-0-loco_119.png","UP 119", description));

    }

    @SideOnly(Side.CLIENT)
    public float[] getAnimationData(int id) {
        switch (id) {
            case 1:{return new float[]{270, 2.5f, 0};}//valve gear up position
            case 2:{return new float[]{0, 2.5f, 0};}//valve gear back position
            case 3:{return new float[]{90, 2.5f, 0};}//valve gear down position
            case 4:{return new float[]{180, 2.5f, 0};}//valve gear forward position
            default:{return new float[]{0,0,0};}
        }
    }
    /**
     * Defines the default skin using a ResourceLocation-esk string URI of the modid and the skin name.
     */
    @Override
    public String getDefaultSkin(){
        return Info.modID+":UP 119";
    }

    //
    /**
     * <h2>Max speed</h2>
     * @return the value of the max speed in km/h
     */
    @Override
    public float transportTopSpeed(){return accelerator>0?30f:80.4672f;}

    /**
     * <h2>Inventory Size</h2>
     * @return the size of the inventory not counting any fuel or crafting slots, those are defined by the type.
     * each row is 9 items
     */
    @Override
    public int getInventoryRows(){return 1;}
    /**
     * <h2>Type</h2>
     * @return the type which will define it's features, GUI, a degree of storage (like crafting slots), and a number of other things.
     */
    @Override
    public List<TrainsInMotion.transportTypes> getTypes(){
        return TrainsInMotion.transportTypes.STEAM.singleton();
    }
    /**
     * <h2>Max Fuel</h2>
     * @return the max heat of fuel the boiler can sustain, a vale of 1 allows for a max of 750c.
     * @see GenericRailTransport#getMaxFuel() for more info.
     * @see FuelHandler for information on fuel consumption.
     */
    @Override
    public float getMaxFuel(){return 1;}

    /**
     * defines the slot used for fuel, host class figures this out automatically from
     * @see #getTypes
     * additionally fuel management is also automatically handled from the type, if something more specific is needed
     * @see GenericRailTransport#manageFuel()
     * water slots can also be manaed via
     * @see GenericRailTransport#waterSlot()
     * and any additional slots can be assigned via
     * @see GenericRailTransport#initInventorySlots()
     * be sure to call the super class on the start of that last one or you break inventories.
     */
    @Override
    public ItemStackSlot fuelSlot(){
        return super.fuelSlot();
    }

    /**
     * <h2>Rider offset</h2>
     * @return defines the offsets of the riders in blocks,
     *     the first value is how far back, the second is how high, third is offset to the left or right
     *     Negative values are towards the front, ground, or right.
     *     Each set of floats represents a different rider.
     */
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{
        2f,1.25f, -0.2f}};}

    /**
     * defines the size of the hitbox for processing, in order of length, height, and width.
     */
    @Override
    public float[] getHitboxSize() {
        return new float[]{worldObj==null?5.5f:4.875f,2.7f,1.5f};
    }

    /**
     * <h2>Acceleration</h2>
     * <h4>TRAINS ONLY.</h4>
     * @return defines the acceleration and pulling strength.
     * if only tractive effort is known you can use
     * @see GenericRailTransport#transportTractiveEffort() which is defined in lfb.
     */
    @Override
    public float transportMetricHorsePower(){return 400f;}
    @Override
    public float transportTractiveEffort(){return 16000;}

    @Override
    public float weightKg(){return 22800f;}//taken from The General 4-4-0


    /**
     * ItemStacks are defined in order of:
     * top left, top center, top right
     * middle left, middle center, middle right
     * bottom left, bottom center, bottom right
     * the TiM crafting grid is designed the same as vanilla.
     * All ore directory support is automatically figured out by
     * @see ebf.tim.utility.RecipeManager#ODC(ItemStack)
     */
    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(TiMItems.chimneyIron, 1), new ItemStack(Items.stick, 2), null,
                new ItemStack(TiMItems.boilerIron, 1), new ItemStack(TiMItems.fireboxIron, 1), new ItemStack(TiMItems.cabinWood, 1),
                new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameWood, 2), null
        };

//        return new ItemStack[]{
//                new ItemStack(TiMItems.chimneyIron, 1), new ItemStack(Items.stick, 2), null,
//                new ItemStack(TiMItems.boilerIron, 1), new ItemStack(TiMItems.fireboxIron, 1), new ItemStack(TiMItems.cabinWood, 1),
//                new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameWood, 2), null
//        };
    }


    /**
     * Defines the points server uses to move and rotate the entity from.
     */
    @Override
    public float[] rotationPoints() {
        return new float[]{2f, -1.25f};
    }

    /**
     * @return true if the rider(s) should be sitting, false if the rider should be standing.
     */
    @Override
    public boolean shouldRiderSit(){
        return false;
    }

    /**
     * Fluid tank capacity.
     * For steam the first is water, and the second is steam.
     * for everything else the first is fuel and there isn't a second
     */
    @Override
    public int[] getTankCapacity(){return new int[]{20000, 1600};}

    /**
     * this should b exactly the same in all classes
     * the only reason we have to declare it is to be sure inheritance works correctly.
     */
    public Item getItem(){return thisItem;}

    /**
     * defines the model to render.
     */
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.render.models.steam.Classic440()};}

    @Override
    public Bogie[] bogies(){
        return new Bogie[]{new Bogie(new train.render.models.steam.Classic440_Front_Truck(), -1.3f)};
    }

    @Override
    public float[][] modelOffsets(){return new float[][]{{1.7f,worldObj==null?-0.6f:0.0f,0f}};}

    @Override
    public float[][] modelRotations(){return new float[][]{{0f,0f,0f}};}
}
