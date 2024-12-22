package train.common.entity.rollingStock;

import ebf.tim.api.SkinRegistry;
import fexcraft.tmt.slim.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import train.client.render.models.ModelPassenger6;
import train.common.Traincraft;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;
import train.common.items.ItemRollingStock;
import train.common.library.Info;
import train.common.library.ItemIDs;

import static ebf.tim.utility.CommonUtil.DefineStack;

/**
 * <h1>Pullman's Palace entity</h1>
 * For more information on the overrides and functions:
 * @see EntityPassengerPassengerCar1
 * @author Eternal Blue Flame
 */
public class EntityPassengerPassengerCar1 extends EntityRollingStock implements IPassenger {
    /*private static final String[] itemDescription = new String[]{
            "\u00A77" + StatCollector.translateToLocal("menu.item.weight") +": 2 " + StatCollector.translateToLocal("menu.item.tons"),
            "\u00A77" + StatCollector.translateToLocal("menu.item.seats") +": 4 " + StatCollector.translateToLocal("menu.item.players")};*/


    public static final Item thisItem = new ItemRollingStock(new EntityPassengerPassengerCar1(null), Info.modID, Traincraft.tcTab);


    public EntityPassengerPassengerCar1(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
    }
    public EntityPassengerPassengerCar1(World world){
        super(world);
    }

    /**
     * <h1>Variable Overrides</h1>
     */
    /**
     * <h2>Bogie Offset</h2>
     */
    @Override
    public float[] rotationPoints(){return new float[]{1.7f,-1.7f};}



    @Override
    public void registerSkins() {
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Red.png", new String[]{},
                "Red", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Blue.png", new String[]{},
                "Blue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Green.png", new String[]{},
                "Green", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_White.png", new String[]{},
                "White", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Black.png", new String[]{},
                "Black", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Cyan.png", new String[]{},
                "Cyan", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Orange.png", new String[]{},
                "Orange", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Grey.png", new String[]{},
                "Grey", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_LightGrey.png", new String[]{},
                "LightGrey", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Brown.png", new String[]{},
                "Brown", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Pink.png", new String[]{},
                "Pink", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Purple.png", new String[]{},
                "Purple", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Lime.png", new String[]{},
                "Lime", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Magenta.png", new String[]{},
                "Magenta", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_LightBlue.png", new String[]{},
                "LightBlue", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Yellow.png", new String[]{},
                "Yellow", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Skin17.png", new String[]{},
                "Skin", "");
        SkinRegistry.addSkin(this.getClass(), Info.modID, "textures/trains/passenger_Skin19.png", new String[]{},
                "Skin19", "");


    }

    @Override
    public boolean isReinforced() {
        return false;
    }

    @Override
    public int[] getTankCapacity() {
        return null;
    }

    @Override
    public float weightKg() {
        return 1000f;
    }

    @Override
    public ItemStack[] getRecipe() {
        ItemStack dyeBlue = OreDictionary.getOres("dyeBlue").get(0);
        ItemStack itemSteel = OreDictionary.getOres("ingotSteel").get(0);

        return new ItemStack[]{
            new ItemStack(itemSteel.getItem(), 5, itemSteel.getItemDamage()),
            new ItemStack(ItemIDs.bogie.item, 2),
            new ItemStack(ItemIDs.steelframe.item, 2),
            new ItemStack(itemSteel.getItem(), 2, itemSteel.getItemDamage()),
            null,
            new ItemStack(ItemIDs.steelcab.item, 1),
            null,
            new ItemStack(ItemIDs.seats.item, 1),
            null,
            new ItemStack(dyeBlue.getItem(), 1, dyeBlue.getItemDamage()),
            new ItemStack(thisItem)

        };
    }

    @Override
    public int getTier(){ return 2; }

    @Override
    public String transportName() {
        return "Passenger Car 1";
    }

    @Override
    public String transportcountry() {
        return "us";
    }

    @Override
    public String transportYear() {
        return "";
    }

    @Override
    public float transportTopSpeed() {
        return 0;
    }

    @Override
    public boolean isFictional() {
        return true;
    }

    @Override
    public float transportTractiveEffort() {
        return 0;
    }

    @Override
    public float transportMetricHorsePower() {
        return 0;
    }

    @Override
    public String[] additionalItemText() {
        return null;
    }

    /**
     * <h2>Inventory Size</h2>
     */
    @Override
    public int getInventoryRows(){return 0;}/**
     * <h2>Rider offsets</h2>
     */
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{2f,0.5f, 0.2f},};}

    @Override
    public float[] getHitboxSize() {
        return new float[]{6.25f,2,1.5f};
    }

    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new ModelPassenger6()};}
    @Override
    public float[][] modelRotations() {
        return new float[][] {{0.0f,180.0f,0.0f}};
    }

    @Override
    public float[][] modelOffsets() { return new float[][] {{0.0f, -0.47f, 0.0f}};}

    /**
     * <h2>pre-asigned values</h2>
     */
    @Override
    public Item getItem(){
        return thisItem;
    }
}
