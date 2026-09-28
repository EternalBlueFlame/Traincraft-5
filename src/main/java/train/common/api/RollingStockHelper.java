package train.common.api;

import fexcraft.tmt.slim.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import train.client.render.Bogie;

/**
 * remember to include constructor methods!
 * the name of the constructor method must match the name of the class file, caps sensitive.
 *
 * example:
 *     public EntityPassengerCar1(World world, double x, double y, double z) {
 *         super(world, x, y, z);
 *     }
 *     public EntityPassengerCar1(World world){
 *         super(world);
 *     }
 */
public interface RollingStockHelper {



    /**
     * Item must be a static instance of ItemRollingStock
     * Supports classes that extent ItemRollingStock for custom features.
     * Good for shorthanding your item instance calls where the extension automatically handles modinfo/creative tab.
     * the static instance may be stored in the class, or elsewhere, this just returns it
     * <p>
     * example of a static instance for the item:
     * public static final Item thisItem = new ItemRollingStock(this, new EntityPassengerCar1(null), Info.modID, Traincraft.tcTab);
     *
     * @return will reference the static instance of the item.
     */
    Item getItem();


    /**
     * @return the name of the stock, this is also used for entity registration, so don't localize it.
     */
    String transportName();

    /**
     * @return the unlocalized country name.
     * examples:
     * us, uk, poland, germany, brazil
     */
    String transportcountry();

    /**
     * @return The year of the transport, may be a straight number like 1970, or a range like 1950-1955
     */
    String transportYear();

    /**
     * @return the fuel type of the entity.
     * Valid options are
     * "Steam"
     * "Diesel"
     * "Electric"
     * "" <- no fuel type, for rollingstock or things that handle fuel in a more custom way.
     */
    String transportFuelType();

    /**
     * @return true if the transport is from the real world.
     */
    boolean isFictional();

    /**
     * @return this returns additional text that shows at the bottom on the item when hovered.
     * may return null
     */
    String[] additionalItemText();

    /**
     * the top speed in km/h for the transport.
     * not used tor rollingstock.
     */
    float transportTopSpeed();

    /**
     * the top speed in km/h for the transport when moving in reverse, default is half for diesel and 75% for others.
     * not used tor rollingstock.
     */
    float transportTopSpeedReverse();

    /**
     * this is the default value to define the acceleration speed and pulling power of a transport.
     * Affects B-units and trains
     */
    float transportMetricHorsePower();

    /**
     * the tractive effort for the transport, this is a fallback if metric horsepower (mhp) is not available
     */
    float transportTractiveEffort();

    /**
     * defines the size of the inventory, not counting any special slots like fuel.
     * end result number of slots is this times 9. plus any crafting/fuel slots
     * may return 0
     */
    int getInventoryRows();

    /**
     * defines the capacity of the fluidTank tank.
     * each value defines another tank.
     * Usually value is 1,000 * the cubic meter capacity.
     * 242 gallons, is 0.9161 cubic meters, which would be 916.1 tank capacity
     * mind you one water bucket is 1000, a full cubic meter of water, so values less than 1000 are not usable.
     * example:
     * return new int[]{11000, 1000};
     * may return null for trains/stock that do not have any fluid storage
     */
    int[] getTankCapacity();

    /**
     * defines the rider position offsets, with 0 being the center of the entity.
     * Each set of coords represents a new rider seat, with the first one being the "driver"
     * example:
     * return new float[][]{{x1,y1,z1},{x2,y2,z2}, etc...};
     * may return null for trains/stock without support for riders
     */
    float[][] getRiderOffsets();


    /**
     * returns the size of the hitbox in blocks.
     * example:
     * return new float[]{x,y,z};
     * may not return null
     */
    float[] getHitboxSize();

    /**
     * @return true if the transport is immune to explosions
     * NOTE: not yet implemented.
     */
    boolean isReinforced();

    /**
     * @return the weight of the transport in kilograms.
     */
    float weightKg();

    /**defines the points that the entity uses for path-finding and rotation, with 0 being the entity center.
     * Usually the point where the front and back bogies would connect to the transport.
     * Or the center of the frontmost and backmost wheel if there are no bogies.
     * The first value is the back point, the second is the front point
     * example:
     * return new float{2f, -1f};
     * may not return null*/
    float[] rotationPoints();

    /**
     * this is called as a method to add skins for the model to the skins registry on mod initialization.
     * can be used to register multiple skins, one after another.
     * You can just leave with empty brackets if you register your skins elsewhere.
     * null bogie skins will inherit from the last texture bound,
     *    for example the train, or the previous bogie skin defined for the livery.
     * bogie skins follow the order of
     * @see RollingStockHelper#bogies()
     * example:
     * SkinRegistry.addSkin(this.class, MODID, "folder/mySkin.png", new String[]{"folder/myBogieSkin1.png","folder/myBogieSkin2.png"}, displayName, displayDescription);
     *
     * the first TransportSkin added to the registry for a transport class will be the default
     * additionally the addSkin function may be called from any other class at any time.
     * Skins must always be registered on server.
     *    feel free to get creative, maybe client only adds a skin for certain players, or with certain achievements.
     * */
    void registerSkins();

    /**
     * @return an override if you want to manually define the default skin
     * otherwise just return super.getDefaultSkin()
     */
    String getDefaultSkin();

    /**
     * @return the array of item stacks for the recipe.
     * Planks slot, Wheel slot, Frame slot, Coupler slot, Chimney slot, Cab slot, Boiler slot, Firebox slot,
     *    the additional slot and lastly the dye slot.
     */
    ItemStack[] getRecipe();

    /**
     * @return the tier for the crafting table, vanilla TC is 1-3.
     * add-ons that add their own crafting tables can extend TileCrafterTierI and override Tier() to add a custom tier.
     */
    int getTier();

    /**
     * @return the number of ticks before exploding when overheating. default is 200.
     * Only affects steamers.
     */
    int getOverheatTime();

    /**
     * @return the fuel consumption per 100 ticks of the entity. default is 80.
     */
    int getFuelConsumption();

    /**
     * @return the acceleration rate per tick of the entity. default is 0.4.
     */
    double getSpecAccel();

    /**
     * @return the braking rate per tick of the entity. default is 0.97.
     */
    double getSpecBrake();

    /**
     * @return the water consumption rate per 5 ticks of the entity. default is 200/
     */
    int getWaterConsumption();


    /*
    ------------------------------
    Client stuff
    ------------------------------
     NOTE:
        Model references must use the full class path
        if the model or model folder is in the class imports at the top it WILL crash dedicated servers.
     */

    /**
     * returns a list of models to be used for the bogies
     * example:
     * return new Bogie[]{new Bogie(new myMod.models.MyModel1(), new float[]{x1,y1,z1}), new Bogie(new myMod.models.MyModel2(), new float[]{x2,y2,z2}), etc...};
     * may return null.
     */
    Bogie[] bogies();

    /**returns a list of models to be used for the transport
     * example:
     * return new ModelBase[]{new myMod.models.MyModel()};
     * may return null. */
    public ModelBase[] getModel();

    /**returns the x/y/z offset for each model, with 0 being the entity center, follows order of getModel
     * @see RollingStockHelper#getModel()
     * example:
     * return new float[][]{{x1,y1,z1},{x2,y2,z2}, etc...};
     * may return null.*/
    float[][] modelOffsets();


    /**returns the x/y/z rotation for each model in degrees, follows order of getModel
     * @see RollingStockHelper#getModel()
     * example:
     * return new float[][]{{pitch1,yaw1,roll1},{pitch2,yaw2,roll2}, etc...};
     * may return null.*/
    float[][] modelRotations();

    /**
     * defines the scale to render the models at, follows order of getModel
     * @see RollingStockHelper#getModel()
     * Default scale is 0.0625
     *
     * */
    float[][] getRenderScale();

    /**
     * defines the scale to render riders at. Default is 1, although really in most cases 0.65 would be better.
     * affects all seats.
     * */
    float getPlayerScale();

    /**
     *
     * @param seat index number, follows order of getRiderOffsets()
     * @see RollingStockHelper#getRiderOffsets()
     * @return true if rider should sit
     *
     * example:
     * switch(seat){
     *     case 1:{return true;}
     *     case 2:{return true;}
     *     default:{return false;}
     * }
     *
     * or just simply:
     * return true;
     */
    boolean shouldRiderSit(int seat);

    /**
     * NOTE: not yet implemented.
     *
     * returns the info for the particles and animations.
     * may return null.
     * @param id is the id being provided by the specific part of a model.
     *
     * example:
     *
     *           switch(id){
     *              case 1:{return new int[]{Animator.Smoke,200,0xFF0000};}
     *              case 2:{return new int[]{Animator.Wheel,0,0}}
     *           }
     *           return null;
     */
    int[] getParticleData(int id);

    /**
     * @return the horn sound
     * default volume and pitch are 1.
     * length is usually 0 (full sound), define a shorter length for a cutoff point.
     * example: return new TrainSound("myHorn", volume, pitch, length)
     * may return null.
     */
    TrainSound getHorn();

    /**
     * @return the bell sound
     * default volume and pitch are 1.
     * length is usually 0 (full sound), define a shorter length for a cutoff point.
     * example: return new TrainSound("myBell", volume, pitch, length)
     * may return null.
     */
    TrainSound getBell();

    /**
     * @return the engine driving sound
     * default volume is 1.
     * default pitch is 0.4f
     * length is usually 0 (full sound), define a shorter length for a cutoff point.
     * if the pitch should change with speed, append .enableRunningPitch()
     * example: return new TrainSound("myRunningSound", volume, pitch, length).enableRunningPitch()
     * may return null.
     */
    TrainSound getRunningSound();

    /**
     * @return the idle engine sound
     * default volume is 1.
     * default pitch is 0.001f
     * length is usually 0 (full sound), define a shorter length for a cutoff point.
     * example: return new TrainSound("myBell", volume, pitch, length)
     * may return null.
     */
    TrainSound getIdleSound();

}
