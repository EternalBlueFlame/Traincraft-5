package ebf.tim.registry;

import ebf.tim.TrainsInMotion;
import ebf.tim.items.*;
import net.minecraft.item.Item;

public class TiMItems {

    //todo
    public static Item adminKey,crowbar;


    public static Item cabinWood,cabinAluminium,cabinCopper,cabinIron,cabinSteel,cabinFiberglass,cabinPlastic;
    public static Item frameWood,frameAluminium,frameCopper,frameIron,frameSteel,frameFiberglass,framePlastic;
    public static Item wheelWood,wheelAluminium,wheelIron,wheelSteel,wheelPlastic;
    public static Item chimneyWood,chimneyAluminium,chimneyCopper,chimneyIron,chimneySteel,chimneyFiberglass,chimneyPlastic, chimneyAluminiumShort, chimneySteelShort;

    public static Item boilerWood,boilerIron,boilerSteel,boilerCopper;
    public static Item fireboxIron, fireboxSteel;
    public static Item petrolEngine,smallDieselEngine,mediumDieselEngine,largeDieselEngine;
    public static Item smallElectricEngine,mediumElectricEngine,largeElectricEngine;

    public static Item seatsWooden,seatsIron,seatsPadded,seatsLuxury;

    public static Item hydraulicTransmission,pneumaticTransmission,transformer,transformerHV,electricControls;

    public static Item enginePiston,cylinder,camshaft,graphite,steelPinCircuit, goldPinCircuit, connectingRod;

    public static Item copperWire,goldWire, carbonWire, steelWire, aluminiumWire, insulatedCopperWire, insulatedAluminiumWire;

    public static Item controlPanel, controlStand;

    public static Item walschaertsValveGear, stephensonValveGear, bakerValveGear, radialValveGear, conjugatingValveGear;

    public static Item railItem;

    public static Item generator;

    public static Item itemStake = new ItemStake();
    public static Item recipeBook = new ItemCraftGuide();


    public static void registerItems(){

        TiMGenericRegistry.RegisterItem(itemStake, TrainsInMotion.MODID, "stake",TrainsInMotion.creativeTab);

        TiMGenericRegistry.RegisterItem(new ItemAdminBook(),TrainsInMotion.MODID, "adminbook", TrainsInMotion.creativeTab);
        TiMGenericRegistry.RegisterItem(recipeBook,TrainsInMotion.MODID, "craftbook", TrainsInMotion.creativeTab);

        TiMGenericRegistry.RegisterItem(new ItemPaintBucket(),TrainsInMotion.MODID, "paintbucket", TrainsInMotion.creativeTab);
        TiMGenericRegistry.RegisterItem(new ItemKey(),TrainsInMotion.MODID,  "transportkey", TrainsInMotion.creativeTab);
        TiMGenericRegistry.RegisterItem(new ItemTicket(),TrainsInMotion.MODID,  "transportticket", TrainsInMotion.creativeTab);


        //overides the server registration of the rail item, so the client can have a complex model.
        //   server can't load the CustomItemModel class due to it's reliance on GL imports.
        if(!TrainsInMotion.proxy.isClient()) {
            railItem = TiMGenericRegistry.RegisterItem( new ItemRail(), TrainsInMotion.MODID, "timrail", TrainsInMotion.creativeTab);
        } else {
            railItem = TiMGenericRegistry.RegisterItem(new ItemRail(),TrainsInMotion.MODID,  "timrail", null, TrainsInMotion.creativeTab, null, ebf.tim.items.CustomItemModel.instance);
        }


        //Cabins
        cabinWood = createItem("cabin.wood");
        cabinAluminium = createItem("cabin.aluminium");
        cabinCopper = createItem("cabin.copper");
        cabinIron = createItem("cabin.iron");
        cabinSteel = createItem("cabin.steel");
        cabinFiberglass = createItem("cabin.fiberglass");
        cabinPlastic = createItem("cabin.plastic");

        //Frames
        frameWood = createItem("frame.wood");
        frameAluminium = createItem("frame.aluminium");
        frameCopper = createItem("frame.copper");
        frameIron = createItem("frame.iron");
        frameSteel = createItem("frame.steel");
        frameFiberglass = createItem("frame.fiberglass");
        framePlastic = createItem("frame.plastic");

        //Wheels
        wheelWood = createItem("wheel.wood");
        wheelAluminium = createItem("wheel.aluminium");
        wheelIron = createItem("wheel.iron");
        wheelSteel = createItem("wheel.steel");
        wheelPlastic = createItem("wheel.plastic");

        //Chimneys
        chimneyWood = createItem("chimney.wood");
        chimneyAluminium = createItem("chimney.aluminium");
        chimneyCopper = createItem("chimney.copper");
        chimneyIron = createItem("chimney.iron");
        chimneySteel = createItem("chimney.steel");
        chimneyFiberglass = createItem("chimney.fiberglass");
        chimneyPlastic = createItem("chimney.plastic");
        chimneyAluminiumShort = createItem("chimney.aluminium.short");
        chimneySteelShort = createItem("chimney.steel.short");

        //Boilers
        boilerWood = createItem("boiler.wood");
        boilerIron = createItem("boiler.iron");
        boilerSteel = createItem("boiler.steel");
        boilerCopper = createItem("boiler.copper");

        //Fireboxes
        fireboxIron = createItem("firebox.iron");
        fireboxSteel = createItem("firebox.steel");

        //Engines
        petrolEngine = createItem("petrol.engine"); //c
        smallDieselEngine = createItem("small.diesel.engine");
        mediumDieselEngine = createItem("medium.diesel.engine");
        largeDieselEngine = createItem("large.diesel.engine"); //c
        smallElectricEngine = createItem("small.electric.engine");
        mediumElectricEngine = createItem("medium.electric.engine"); //c
        largeElectricEngine = createItem("large.electric.engine"); //c

        //Seats
        seatsWooden = createItem("seats.wooden");
        seatsIron = createItem("seats.iron"); //c
        seatsPadded = createItem("seats.padded"); //c
        seatsLuxury = createItem("seats.luxury"); //c

        //Transmissions + Other
        hydraulicTransmission = createItem("hydraulic.transmission");
        pneumaticTransmission = createItem("pneumatic.transmission");
        transformer = createItem("transformer");
        transformerHV = createItem("transformer.hv"); //c
        electricControls = createItem("electric.controls"); //c

        //Wires
        //TODO in future? rename to wire.material to match naming scheme
        copperWire = createItem("copper.wire");
        goldWire = createItem("gold.wire");
        carbonWire = createItem("carbon.wire");
        steelWire = createItem("steel.wire");
        aluminiumWire = createItem("aluminium.wire");
        insulatedCopperWire = createItem("insulated.copper.wire"); //c
        insulatedAluminiumWire = createItem("insulated.aluminium.wire"); //c

        //Valve Gears
        //TODO in future? reverse order to match naming scheme
        walschaertsValveGear = createItem("walschaerts.valve.gear"); //c
        stephensonValveGear = createItem("stephenson.valve.gear"); //c
        bakerValveGear = createItem("baker.valve.gear"); //c
        radialValveGear = createItem("radial.valve.gear"); //c
        conjugatingValveGear = createItem("conjugating.valve.gear"); //c

        //Other
        //TODO in future? reverse order to match naming scheme
        enginePiston = createItem("engine.piston");
        cylinder = createItem("engine.cylinder");
        camshaft = createItem("camshaft");
        graphite = createItem("graphite");
        steelPinCircuit = createItem("steel.pin.circuit");
        goldPinCircuit = createItem("gold.pin.circuit"); //c
        controlPanel = createItem("control.panel");
        controlStand = createItem("control.stand"); //c
        connectingRod = createItem("connecting.rod"); //c
        generator = createItem("generator");
    }

    private static Item createItem(String unlocalizedName) {
        Item item = new Item();
        TiMGenericRegistry.RegisterItem(item, TrainsInMotion.MODID, unlocalizedName, TrainsInMotion.creativeTabCrafting);
        item.setTextureName(TrainsInMotion.MODID+ ":" + "crafting/" + item.getUnlocalizedName().replace("item.", ""));
        return item;
    }
}
