package ebf.tim.registry;

import cpw.mods.fml.common.Loader;
import ebf.tim.TrainsInMotion;
import ebf.tim.blocks.OreGen;
import net.minecraft.block.material.MapColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import static ebf.tim.registry.TiMGenericRegistry.RegisterFluid;
import static ebf.tim.registry.TiMGenericRegistry.registerOreGen;

public class TiMFluids {


    /**the oil fluid*/
    public static Fluid fluidOil = new Fluid("Oil");
    public static Item bucketOil;
    /**the diesel fluid*/
    public static Fluid fluidDiesel = new Fluid("Diesel");
    public static Item bucketDiesel;
    /**the fuel oil fluid*/
    public static Fluid fluidfueloil = new Fluid("FuelOil");
    public static Item bucketFuelOil;
    /**the steam fluid*/
    public static Fluid fluidSteam = new Fluid("Steam");
    public static Item bucketSteam;
    /**the heavy steam fluid*/
    public static Fluid fluidHeavySteam = new Fluid("HeavySteam");
    public static Item bucketHeavySteam;
    /**the RF fluid*/
    public static Fluid fluidRedstone = new Fluid("Redstone");
    public static Item bucketRedstone;

    public static Fluid fluidBCFuel = FluidRegistry.getFluid("fuel");
    public static Fluid fluidEthanol = FluidRegistry.getFluid("bioethanol");
    public static Fluid fluidBiofuel = FluidRegistry.getFluid("biofuel");
    public static Fluid fluidBioDiesel = FluidRegistry.getFluid("biodiesel");
    public static Fluid fluidBiomass = FluidRegistry.getFluid("biomass");

    public static Fluid nullFluid = new Fluid("nullFluid");






    public static void registerFluids(){

        RegisterFluid(fluidOil, bucketOil, TrainsInMotion.MODID, "oil", false, 700, MapColor.blackColor, TrainsInMotion.creativeTab);
        RegisterFluid(fluidDiesel, bucketDiesel, TrainsInMotion.MODID, "diesel", false, 500, MapColor.sandColor, TrainsInMotion.creativeTab);
        RegisterFluid(fluidSteam, bucketSteam, TrainsInMotion.MODID, "steam", true, 200, MapColor.snowColor, TrainsInMotion.creativeTab);
        RegisterFluid(fluidHeavySteam, bucketHeavySteam, TrainsInMotion.MODID, "heavysteam", true, 600, MapColor.snowColor, TrainsInMotion.creativeTab);
        RegisterFluid(fluidfueloil, bucketFuelOil, TrainsInMotion.MODID, "fueloil", false, 600, MapColor.brownColor, TrainsInMotion.creativeTab);
        RegisterFluid(fluidRedstone, bucketRedstone, TrainsInMotion.MODID, "redstone", false, 100, MapColor.redColor, TrainsInMotion.creativeTab);
        RegisterFluid(nullFluid, null, TrainsInMotion.MODID, "nullFluid", false, 100, MapColor.pinkColor, null);


        //oil spawn at surface for deserts
        registerOreGen(0,
                new OreGen(fluidOil.getBlock(),-6,0,30,10,1)
                        .setBiomes(new String[]{"desert"}).setFiller(Blocks.sand).setHeightOffset(3));
        //oil spawn underground
        registerOreGen(0,
                new OreGen(fluidOil.getBlock(),40,60,20,6,3));



        if (Loader.isModLoaded("BuildCraft|Energy")) {
            TiMGenericRegistry.registerBCFluid(TiMFluids.fluidOil, 2500, 5000);
            TiMGenericRegistry.registerBCFluid(TiMFluids.fluidDiesel, 5000, 10000);
            TiMGenericRegistry.registerBCFluid(TiMFluids.fluidfueloil, 5000,25000);

        }
        if (Loader.isModLoaded("Railcraft")){
            TiMGenericRegistry.registerRCFluid(TiMFluids.fluidfueloil,2500);
            TiMGenericRegistry.registerRCFluid(TiMFluids.fluidOil,5000);
            TiMGenericRegistry.registerRCFluid(TiMFluids.fluidDiesel, 10000);
        }
        if (Loader.isModLoaded("Forestry")){

        }
    }
}
