package train.common;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;
import ebf.tim.entities.EntitySeat;
import ebf.tim.networking.PacketSeatUpdate;
import ebf.tim.utility.DebugUtil;
import fexcraft.tmt.slim.TextureManager;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import train.common.api.AbstractTrains;
import train.common.api.EntityBogie;
import train.common.api.LiquidManager;
import train.common.api.TrainRecord;
import train.common.blocks.TCBlocks;
import train.common.core.CommonProxy;
import train.common.core.CreativeTabTraincraft;
import train.common.core.EntityIds;
import train.common.core.TrainModCore;
import train.common.core.handlers.*;
import train.common.entity.stock.special.*;
import train.common.entity.stock.freight.*;
import train.common.entity.stock.tender.*;
import train.common.entity.stock.passenger.*;
import train.common.entity.stock.tanker.*;
import train.common.entity.stock.work.*;
import train.common.entity.trains.steam.*;
import train.common.entity.trains.diesel.*;
import train.common.entity.trains.electric.*;
import train.common.entity.zeppelin.EntityZeppelinOneBalloon;
import train.common.entity.zeppelin.EntityZeppelinTwoBalloons;
import train.common.generation.ComponentVillageTrainstation;
import train.common.generation.WorldGenWorld;
import train.common.items.TCItems;
import train.common.library.EnumTrains;
import train.common.library.Info;
import train.common.library.TraincraftRegistry;
import train.common.recipes.AssemblyTableRecipes;

import java.io.File;

@Mod(modid = Info.modID, name = Info.modName, version = Info.modVersion)
public class Traincraft {

    /* TrainCraft instance */
    @Instance(Info.modID)
    public static Traincraft instance;

    /* TrainCraft proxy files */
    @SidedProxy(clientSide = "train.client.core.ClientProxy", serverSide = "train.common.core.CommonProxy")
    public static CommonProxy proxy;

    /* TrainCraft Logger */
    public static Logger tcLog = LogManager.getLogger(Info.modName);

    /**
     * Network Channel to send packets on
     */
    public static SimpleNetworkWrapper modChannel;
    public static SimpleNetworkWrapper keyChannel;
    public static SimpleNetworkWrapper rotationChannel;


    public static SimpleNetworkWrapper slotschannel;
    public static SimpleNetworkWrapper ignitionChannel;
    public static SimpleNetworkWrapper brakeChannel;
    public static SimpleNetworkWrapper lockChannel;
    public static SimpleNetworkWrapper builderChannel;
    public static SimpleNetworkWrapper updateTrainIDChannel = NetworkRegistry.INSTANCE.newSimpleChannel("TrainIDChannel");
    public static SimpleNetworkWrapper updateDestinationChannel = NetworkRegistry.INSTANCE.newSimpleChannel("updateDestnChannel");
    public static SimpleNetworkWrapper updateChannel = NetworkRegistry.INSTANCE.newSimpleChannel("updateChannel");
    public static SimpleNetworkWrapper paintbrushColorChannel;
    public static SimpleNetworkWrapper overlayTextureChannel;
    public static SimpleNetworkWrapper rollingStockLightsChannel;
    public static SimpleNetworkWrapper rollingStockBeaconChannel;
    public static SimpleNetworkWrapper rollingStockDitchLightsChannel;

    public static final SimpleNetworkWrapper itaChannel = NetworkRegistry.INSTANCE.newSimpleChannel("TransmitterAspect");
    public static SimpleNetworkWrapper itsChannel = NetworkRegistry.INSTANCE.newSimpleChannel("TransmitterSpeed");
    //public static  SimpleNetworkWrapper mtcsChannel = NetworkRegistry.INSTANCE.newSimpleChannel("MTCSysSetSpeed");
    public static SimpleNetworkWrapper itnsChannel = NetworkRegistry.INSTANCE.newSimpleChannel("TransmitterNextSpeed");
    public static final SimpleNetworkWrapper mtlChannel = NetworkRegistry.INSTANCE.newSimpleChannel("MTCLevelUpdater");
    public static final SimpleNetworkWrapper msChannel = NetworkRegistry.INSTANCE.newSimpleChannel("MTCStatus");
    public static final SimpleNetworkWrapper mscChannel = NetworkRegistry.INSTANCE.newSimpleChannel("MTCStatusToClient");
    public static final SimpleNetworkWrapper atoChannel = NetworkRegistry.INSTANCE.newSimpleChannel("ATOPacket");
    public static final SimpleNetworkWrapper atoDoSlowDownChannel = NetworkRegistry.INSTANCE.newSimpleChannel("ATODoSlowDown");
    public static final SimpleNetworkWrapper atoDoAccelChannel = NetworkRegistry.INSTANCE.newSimpleChannel("ATODoAccel");
    public static final SimpleNetworkWrapper atoSetStopPoint = NetworkRegistry.INSTANCE.newSimpleChannel("ATOSetStopPoint");
    public static final SimpleNetworkWrapper NCSlowDownChannel = NetworkRegistry.INSTANCE.newSimpleChannel("NCDoSlowDown");
    //public static final SimpleNetworkWrapper ctChannel = NetworkRegistry.INSTANCE.newSimpleChannel("ctmChannel");
    public static final SimpleNetworkWrapper gsfsChannel = NetworkRegistry.INSTANCE.newSimpleChannel("gsfsChannel");
    public static final SimpleNetworkWrapper gsfsrChannel = NetworkRegistry.INSTANCE.newSimpleChannel("gsfsReturnChannel");

    public final TraincraftRegistry traincraftRegistry = new TraincraftRegistry();


    public static File configDirectory;

    /* Creative tab for Traincraft */
    public static CreativeTabTraincraft tcTab, tcTrainTab, tcCommunityTab ;

    public ArmorMaterial armor = EnumHelper.addArmorMaterial("Armor", 5, new int[]{1, 2, 2, 1}, 25);
    public ArmorMaterial armorCloth = EnumHelper.addArmorMaterial("TCcloth", 5, new int[]{1, 2, 2, 1}, 25);
    public ArmorMaterial armorCompositeSuit = EnumHelper.addArmorMaterial("TCsuit", 70, new int[]{2, 6, 5, 2}, 50);
    public static int trainArmor;
    public static int trainCloth;
    public static int trainCompositeSuit;


    public static WorldGenWorld worldGen;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        DebugUtil.dev = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
        tcLog.info("Starting Traincraft " + Info.modVersion + "!");
        /* Config handler */
        configDirectory = event.getModConfigurationDirectory();
        ConfigHandler.init(new File(event.getModConfigurationDirectory(), Info.modName + ".cfg"));

        proxy.configDirectory = event.getModConfigurationDirectory().getAbsolutePath();
        /* Register the KeyBinding Handler */
        proxy.registerKeyBindingHandler();

        /* Register Items, Blocks, ... */
        tcLog.info("Initialize Blocks, Items, ...");
        tcTab = new CreativeTabTraincraft("Traincraft", Info.modID, "trains/train_br80");
        if (ConfigHandler.SPLIT_CREATIVE) {
            tcTrainTab = new CreativeTabTraincraft("Traincraft Trains",  Info.modID,"trains/train_br01");
        }
        trainArmor = proxy.addArmor("armor");
        trainCloth = proxy.addArmor("Paintable");
        trainCompositeSuit = proxy.addArmor("CompositeSuit");

        if (Loader.isModLoaded("ComputerCraft")) {
            try {
                proxy.registerComputerCraftPeripherals();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (hasTCCEAddon()){
            tcCommunityTab = new CreativeTabTraincraft("Traincraft: Community Edition", Info.modID, "trains/train_mogul");
        }

        /* Other Proxy init */
        tcLog.info("Initialize Renderer and Events");
        proxy.registerEvents(event);


        tcLog.info("Finished PreInitialization");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        tcLog.info("Start Initialization");
        TCBlocks.init();

        // if (Loader.isModLoaded("ForgeMultipart"))
        // {
        //     tcLog.info("ForgeMultipart detected. Registering Traincraft Blocks");
        //     train.common.core.plugins.ForgeMultiPart.registerBlocks();
        // }

        TCItems.init();
        if (Traincraft.hasTCCEAddon()) {
            TCItems.registerTCCERollingStock();
            Traincraft.tcLog.info("Enabled Traincraft: Community Edition rollingstock");
        }


        proxy.registerTileEntities();

        tcLog.info("Initialize Fluids");
        LiquidManager.getInstance().registerLiquids();

        proxy.registerSounds();
        proxy.setHook(); // Moved file needed to run JLayer, we need to set a hook in order to retrieve it

        GameRegistry.registerFuelHandler(new FuelHandler());
        AchievementHandler.load();
        AchievementPage.registerAchievementPage(AchievementHandler.tmPage);
        GameRegistry.registerWorldGenerator(worldGen = new WorldGenWorld(), 5);

        //Retrogen Handling
        RetrogenHandler retroGen = new RetrogenHandler();
        MinecraftForge.EVENT_BUS.register(retroGen);
        FMLCommonHandler.instance().bus().register(retroGen);

        MapGenStructureIO.func_143031_a(ComponentVillageTrainstation.class, "Trainstation");

        //proxy.getCape();

        /* GUI handler initiation */
        tcLog.info("Initialize Gui");
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
        FMLCommonHandler.instance().bus().register(new CraftingHandler());

        /* Ore dictionary */
        OreHandler.registerOres();

        /* Recipes */
        tcLog.info("Initialize Recipes");
        RecipeHandler.initBlockRecipes();
        RecipeHandler.initItemRecipes();
        RecipeHandler.initSmeltingRecipes();
        AssemblyTableRecipes.recipes();

        EntityRegistry.registerModEntity(EntityZeppelinTwoBalloons.class, "zeppelin", EntityIds.ZEPPELIN, Traincraft.instance, 512, 1, true);//zepplin
        EntityRegistry.registerModEntity(EntityBogie.class, "Entity Front Bogie", EntityIds.LOCOMOTIVE_BOGIE, Traincraft.instance, 512, 3, true);//front bogie
        EntityRegistry.registerModEntity(EntityZeppelinOneBalloon.class, "zeppelin big", EntityIds.ZEPPELIN_BIG, Traincraft.instance, 512, 1, true);//zepplin big
        EntityRegistry.registerModEntity(EntitySeat.class, "Seat", 16, Traincraft.instance,512,3,true);//seat
        for(TrainRecord trains : EnumTrains.trains()){
            //TraincraftRegistry.registerTransport(trains);
        }


        TraincraftRegistry.registerTransports("", listspecial());
        TraincraftRegistry.registerTransports("", listfreight());
        TraincraftRegistry.registerTransports("", listpassenger());
        TraincraftRegistry.registerTransports("", listtanker());
        TraincraftRegistry.registerTransports("", listtender());
        TraincraftRegistry.registerTransports("", liststeam());
        TraincraftRegistry.registerTransports("", listdiesel());
        TraincraftRegistry.registerTransports("", listelectric());



        /* Liquid FX */
        proxy.registerTextureFX();

        /*Trainman Villager*/
        tcLog.info("Initialize Station Chief Villager");
        VillagerRegistry.instance().registerVillagerId(ConfigHandler.TRAINCRAFT_VILLAGER_ID);
        VillagerTraincraftHandler villageHandler = new VillagerTraincraftHandler();
        VillagerRegistry.instance().registerVillageCreationHandler(villageHandler);
        proxy.registerVillagerSkin(ConfigHandler.TRAINCRAFT_VILLAGER_ID, "station_chief.png");
        // VillagerRegistry.instance().registerVillageTradeHandler(ConfigHandler.TRAINCRAFT_VILLAGER_ID, villageHandler);
        Traincraft.updateChannel.registerMessage(PacketSeatUpdate.Handler.class, PacketSeatUpdate.class, 8, Side.CLIENT);
        Traincraft.updateChannel.registerMessage(PacketSeatUpdate.Handler.class, PacketSeatUpdate.class, 9, Side.SERVER);


        proxy.registerBookHandler();
        proxy.registerPlayerScaler();

        /* Networking and Packet initialisation, apparently this needs to be in init to prevent conflicts */
        PacketHandler.init();
        proxy.registerRenderInformation();



        traincraftRegistry.init();

        tcLog.info("Finished Initialization");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent evt) {
        tcLog.info("Start to PostInitialize");
        TraincraftRegistry.endRegistration();

        tcLog.info("Activation Mod Compatibility");
        TrainModCore.ModsLoaded();

        if(proxy.isClient()) {
            if(DebugUtil.dev) {
                trainConverter.write(EnumTrains.trains());
            }
            TextureManager.collectIngotColors();
        }

        tcLog.info("Finished PostInitialization");
    }

    @EventHandler
    public void serverStop(FMLServerStoppedEvent event) {
        CommonProxy.killAllStreams();
    }

    public static boolean hasComputerCraft() {
        return Loader.isModLoaded("ComputerCraft");
    }

    public static boolean hasNotEnoughItems() {
        return Loader.isModLoaded("NotEnoughItems");
    }

    public static boolean hasRailcraft() {
        return Loader.isModLoaded("Railcraft");
    }

    public static boolean hasTCCEAddon() {return Loader.isModLoaded("tcce");}




    public static AbstractTrains[] listspecial() {
        return new AbstractTrains[]{new EntityJukeBoxCart(null), new EntityTracksBuilder(null), new EntityPropagandaUS(null), new EntityPropagandaUSSR(null), new EntityPropagandaJapan(null), new EntityPropagandaBritain(null)};
    }
    public static AbstractTrains[] listtender() {
        return new AbstractTrains[]{new EntityTenderSmall(null), new EntityTenderHeavy(null), new EntityTenderGS4(null), new EntityTender4000(null), new EntityTenderFowler4F(null), new EntityTenderBerk1225(null), new EntityTender4_4_0(null), new EntityTenderA4(null), new EntityTenderBR01_DB(null), new EntityTenderCoranationClass(null), new EntityTenderEr_Ussr(null), new EntityTenderC62Class(null), new EntityTenderD51(null), new EntityTenderAdler(null), new EntityTender_C41(null), new EntityTender_Southern1102(null), new EntityTenderMILW(null)};
    }
    public static AbstractTrains[] listdiesel() {
        return new AbstractTrains[]{new EntityLocoDieselKof_DB(null), new EntityLocoDieselCD742(null), new EntityLocoDieselChME3(null), new EntityLocoDieselGP7Red(null), new EntityLocoDieselSD40(null), new EntityLocoDieselSD70(null), new EntityLocoDieselShunter(null), new EntityLocoDieselV60_DB(null), new EntityLocoDieselIC4_DSB_MG(null), new EntityLocoDieselMILW_H1044(null), new EntityLocoDieselEMDF7(null), new EntityLocoDieselEMDF3(null), new EntityLocoDieselClass66(null), new EntityLocoDieselDeltic(null), new EntityLocoDieselDD35A(null), new EntityLocoDiesel44TonSwitcher(null), new EntityLocoDieselBamboo(null), new EntityLocoDieselWLs40(null), new EntityLocoDieselFOLM1(null), new EntityLocoDieselFOLM1B(null)};
    }
    public static AbstractTrains[] listpassenger() {
        return new AbstractTrains[]{new EntityPassenger2(null), new EntityPassenger5(null), new EntityPassenger7(null), new EntityPassenger_1class_DB(null), new EntityPassenger_2class_DB(null), new EntityPassengerHighSpeedCarZeroED(null), new EntityPassengerTramNY(null), new EntityPassengerAdler(null), new EntityPassengerDBOriental(null), new PassengerIC4_DSB_FG(null), new PassengerIC4_DSB_FH(null), new EntityPassengerICE_1class(null), new EntityPassengerICE_2class(null), new EntityPassengerICE_Restaurant(null), new EntityPassengerGS4(null), new EntityPassengerGS4_Observatory(null), new EntityPassengerGS4_Tail(null), new EntityPassengerDenverRioGrande(null), new EntityPassengerDenverRioGrandeCombo(null), new EntityPassengerRheingold(null), new EntityPassengerRheingoldPanorama(null), new EntityPassengerMILW(null), new EntityPassengerMILWTail(null), new EntityPassengerBamboo(null), new EntityCaboose(null), new EntityCaboose3(null), new EntityStockCar(null), new EntityStockCarDRWG(null), new EntityFlatCart(null), new EntityFlatCartSU(null), new EntityFlatCartUS(null), new EntityFlatCar_DB(null)};
    }
    public static AbstractTrains[] listwork() {
        return new AbstractTrains[]{new EntityPassengerRheingoldDining1(null), new EntityPassengerRheingoldDining2(null), new EntityGWRBrakeVan(null), new EntityWorkCart(null), new EntityCabooseWorkCart(null), new EntityCabooseLogging(null), new EntityCabooseLoggingPRR(null), new EntityMailWagen_DB(null)};
    }
    public static AbstractTrains[] listfreight() {
        return new AbstractTrains[]{new EntityFreightCart2(null), new EntityFreightCart(null), new EntityFreightWood(null), new EntityFreightGrain(null), new EntityFreightKClassRailBox(null), new EntityFreightShortCoveredHopper(null), new EntityFreightLongCoveredHopper(null), new EntityFreightOpenWagon(null), new EntityFreightHopperUS(null), new EntityFreight100TonHopper(null), new EntityFlatCartWoodUS(null), new EntityBulkheadFlatCart(null), new EntityFreightCartUS(null), new EntityBoxCartUS(null), new EntityBoxCartPRR(null), new EntityFreightCartSmall(null), new EntityFreightMinetrain(null), new EntityFreightGTNG(null), new EntityFreightWood2(null), new EntityFreightClosed(null), new EntityFreightOpen2(null), new EntityFreightWagenDB(null), new EntityFlatCarRails_DB(null), new EntityFreightASTFAutorack(null), new EntityFlatCarLogs_DB(null), new EntityFreightSlateWagon(null), new EntityFreightIceWagon(null), new EntityFreightGS4_Baggage(null), new EntityFreightGondola_DB(null), new EntityFreightCenterbeam_Empty(null), new EntityFreightCenterbeam_Wood_1(null), new EntityFreightCenterbeam_Wood_2(null), new EntityFreightWellcar(null), new EntityFreightTrailer(null), new EntityFreightDenverRioGrande(null), new EntityFreightBaggageMILW(null), new EntityFreightHeavyweight(null), new EntityFreightBamboo(null), new EntityFreightGermanPost(null), new EntityFreightDepressedFlatbed(null), new EntityFreightCartL(null), new EntityFreightHeavyweightBaggage(null)};
    }
    public static AbstractTrains[] listelectric() {
        return new AbstractTrains[]{new EntityLocoElectricVL10(null), new EntityLocoElectricBR_E69(null), new EntityLocoElectricMinetrain(null), new EntityLocoElectricHighSpeedZeroED(null), new EntityLocoElectricICE1(null), new EntityLocoElectricTramWood(null), new EntityLocoElectricTramNY(null), new EntityLocoElectricBR185(null), new EntityLocoElectricE10_DB(null), new EntityLocoElectricE103(null), new EntityLocoElectricClass85(null), new EntityLocoElectricCD151(null), new EntityLocoElectricBP4(null)};
    }
    public static AbstractTrains[] liststeam() {
        return new AbstractTrains[]{new EntityLocoSteamMallardA4(null), new EntityLocoSteamHallClass(null), new EntityLocoSteamBerk1225(null), new EntityLocoSteamBerk765(null), new EntityLocoSteamFowler(null), new EntityLocoSteamKingClass(null), new EntityLocoSteamMILWClassA(null), new EntityLocoSteamCherepanov(null), new EntityLocoSteamBR80_DB(null), new EntityLocoSteam4_4_0(null), new EntityLocoSteamSmall(null), new EntityLocoSteamLSSP7(null), new EntityLocoSteamHeavy(null), new EntityLocoSteamC62Class(null), new EntityLocoSteamD51(null), new EntityLocoSteamD51Long(null), new EntityLocoSteamBR01_DB(null), new EntityLocoSteamCoranationClass(null), new EntityLocoSteamGS4(null), new EntityLocoSteamEr_Ussr(null), new EntityLocoSteamC41(null), new EntityLocoSteamC41_080(null), new EntityLocoSteamAlcoSC4(null), new EntityLocoSteamSouthern1102(null), new EntityLocoSteamUSATCUS(null), new EntityLocoSteamUSATCUK(null), new EntityLocoSteamC41T(null), new EntityLocoSteamForneyRed(null), new EntityLocoSteamMogulBlue(null), new EntityLocoSteamShay(null), new EntityLocoSteamVBShay(null), new EntityLocoSteamClimax(null), new EntityLocoSteamPannier(null), new EntityLocoSteamAlice0_4_0(null), new EntityLocoSteamGLYN042T(null), new EntityLocoSteam262T(null), new EntityLocoSteam040VB(null), new EntityLocoSteamAdler(null), new EntityLocoSteamSnowPlow(null)};
    }
    public static AbstractTrains[] listtanker() {
        return new AbstractTrains[]{new EntityBUnitEMDF7(null), new EntityBUnitEMDF3(null), new EntityBUnitDD35(null), new EntityTankWagon_DB(null), new EntityTankWagonThreeDome(null), new EntityTankWagonUS(null), new EntityTankWagon2(null), new EntityTankLava(null), new EntityTankWagon(null)};
    }



}
