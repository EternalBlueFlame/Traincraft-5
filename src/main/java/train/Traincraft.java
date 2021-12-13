package train;

import net.minecraft.command.CommandException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import ebf.tim.TrainsInMotion;
import ebf.tim.gui.GUICraftBook;
import ebf.tim.items.TiMTab;
import ebf.tim.registry.TiMGenericRegistry;
import ebf.tim.utility.JsonRecipeHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import train.blocks.TCBlocks;
import train.blocks.fluids.LiquidManager;
import train.core.CommonProxy;
import train.core.handlers.ConfigHandler;
import train.core.handlers.FuelHandler;
import train.core.handlers.VillagerTraincraftHandler;
import train.core.network.PacketKeyPress;
import train.core.network.PacketLantern;
import train.core.network.PacketSetJukeboxStreamingUrl;
import train.core.plugins.AssemblyTableNEIIntegration;
import train.entity.trains.EntityLocoSteamBR80_DB;
import train.entity.zeppelin.EntityZeppelinOneBalloon;
import train.entity.zeppelin.EntityZeppelinTwoBalloons;
import train.generation.ComponentVillageTrainstation;
import train.items.TCItems;
import train.library.Info;
import train.library.TrainRegistry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Mod(modid = Info.modID, name = Info.modName, dependencies="after:"+ TrainsInMotion.MODID)
public class Traincraft {

	/* TrainCraft instance */
	@Instance(Info.modID)
	public static Traincraft instance;

	/* TrainCraft proxy files */
	@SidedProxy(clientSide = "train.core.ClientProxy", serverSide = "train.core.CommonProxy")
	public static CommonProxy proxy;

	/* TrainCraft Logger */
	public static Logger tcLog = LogManager.getLogger(Info.modName);

	/** Network Channel to send packets on */
	public static SimpleNetworkWrapper modChannel;
	public static SimpleNetworkWrapper keyChannel;
	public static SimpleNetworkWrapper rotationChannel;


	public static File configDirectory;

	/* Creative tab for Traincraft */
	public static TiMTab tcTab;

	public ArmorMaterial armor = EnumHelper.addArmorMaterial("Armor", 5, new int[] { 1, 2, 2, 1 }, 25);
	public ArmorMaterial armorCloth = EnumHelper.addArmorMaterial("TCcloth", 5, new int[] {1, 2, 2, 1}, 25);
	public ArmorMaterial armorCompositeSuit = EnumHelper.addArmorMaterial("TCsuit", 70, new int[] {2, 6, 5, 2}, 50);
	public static int trainArmor;
	public static int trainCloth;
	public static int trainCompositeSuit;


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

				/* Config handler */
		configDirectory= event.getModConfigurationDirectory();
		ConfigHandler.init(new File(event.getModConfigurationDirectory(), Info.modName + ".cfg"));

		/* Register the KeyBinding Handler */
		proxy.registerKeyBindingHandler();

		/* Other Proxy init */
		tcLog.info("Initialize Renderer and Events");
		proxy.registerRenderInformation();
		proxy.registerEvents(event);

		/* Networking and Packet initialisation */
		tcLog.info("Initialize Packets");
		modChannel = NetworkRegistry.INSTANCE.newSimpleChannel(Info.channel);
		keyChannel = NetworkRegistry.INSTANCE.newSimpleChannel(Info.keyChannel);
		rotationChannel = NetworkRegistry.INSTANCE.newSimpleChannel(Info.rotationChannel);


		keyChannel.registerMessage(PacketKeyPress.Handler.class, PacketKeyPress.class, 1, Side.SERVER);
		modChannel.registerMessage(PacketSetJukeboxStreamingUrl.Handler.class,
				PacketSetJukeboxStreamingUrl.class, 2, Side.SERVER);
		modChannel.registerMessage(PacketLantern.Handler.class, PacketLantern.class, 3, Side.SERVER);

		tcLog.info("Finished PreInitialization");
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		tcLog.info("Start Initialization");

		if(event.getSide().isClient()) {
			GUICraftBook.addPage(Info.modID, "Traincraft\n" +
					"Developers: \n" +
					"Eternal Blue Flame\n" +
					"Canitzp, ComputerButter\n\n" +
					"Project Overseer:\nSpitfire4466\n\n" +
					"Lead artists: \nBroscolotos, Riggs64"
			);

			GUICraftBook.addPage(Info.modID, "Honorable Mentions:\n" +
					"Mr. Brutal,\n" +
					"helldiver, DAYdiecast,\n" +
					"BlockStormTwo, FriscoWolf,\n" +
					"ChandlerBingUA, KiraKun,\n" +
					"NitroxydeX");

			GUICraftBook.addPage(Info.modID, "DISCLAIMER:\n" +
					"All transport into including\n" +
					"but not limited to\n" +
					"weight, year, country, \n" +
					"seating capacity, etc...\n" +
					"may be inaccurate, this\n" +
					"is written to the best of \n" +
					"our knowledge and we\n" +
					"encourage the community to\n" +
					"correct us, with citation.");

			GUICraftBook.addPage(Info.modID,
					"WARNING:\nThis release is an alpha,\n" +
							"and some features may be\n" +
							"missing, buggy, or\n" +
							"incomplete.\n" +
							"We appreciate your\n" +
							"patience and reports as\n" +
							"we work on adding back all\n" +
							"of the missing features,\nand many many more.");

			GUICraftBook.addPage(Info.modID,
					"I WILL STATE THIS AGAIN\n" +
							"This release is an alpha,\n" +
							"and some features may be\n" +
							"missing, buggy, or\n" +
							"incomplete.\n" +
							"We appreciate your\n" +
							"patience and reports as\n" +
							"we work on adding back all\n" +
							"of the missing features,\nand many many more.");
		}

		/* Register Items, Blocks, ... */
		tcLog.info("Initialize Blocks, Items, ...");
		tcTab = new TiMTab("Traincraft", EntityLocoSteamBR80_DB.thisItem);
		trainArmor = proxy.addArmor("armor");
		trainCloth = proxy.addArmor("Paintable");
		trainCompositeSuit = proxy.addArmor("CompositeSuit");
		TCBlocks.init();
		TCBlocks.registerRecipes();
		TCItems.init();

		//parse and register json crafting recipes
		JsonRecipeHelper.loadRecipes(Info.modID, this.getClass());

		if(ConfigHandler.ENABLE_STEAM) {
			//the null last value defines we aren't implementing a custom entity render.
			TiMGenericRegistry.registerTransports(Info.modID, TrainRegistry.listSteam(),null);
		}
		if(ConfigHandler.ENABLE_DIESEL) {
			TiMGenericRegistry.registerTransports(Info.modID, TrainRegistry.listDiesel(),null);
		}
		if(ConfigHandler.ENABLE_ELECTRIC) {
			TiMGenericRegistry.registerTransports(Info.modID, TrainRegistry.listElectric(),null);
		}
		if(ConfigHandler.ENABLE_TENDER) {
			TiMGenericRegistry.registerTransports(Info.modID, TrainRegistry.listTender(),null);
		}

		TiMGenericRegistry.registerTransports(Info.modID, TrainRegistry.listPassenger(),null);
		TiMGenericRegistry.registerTransports(Info.modID, TrainRegistry.listFreight(),null);
		TiMGenericRegistry.registerTransports(Info.modID, TrainRegistry.listWorkCart(),null);
		TiMGenericRegistry.registerTransports(Info.modID, TrainRegistry.listTanker(),null);
		TiMGenericRegistry.registerTransports(Info.modID, TrainRegistry.listSpecial(),null);

		if(ConfigHandler.ENABLE_ZEPPELIN) {
			//TiM doesn't have a generic entity registration yet, so these will likely have to remain as-is for now.
			EntityRegistry.registerModEntity(new ResourceLocation(Info.modID, "zeppelin"), EntityZeppelinTwoBalloons.class, "zeppelin", TiMGenericRegistry.registryPosition, Traincraft.instance, 512, 3, true);//zepplin
			TiMGenericRegistry.registryPosition++;
			EntityRegistry.registerModEntity(new ResourceLocation(Info.modID, "zeppelin_big"), EntityZeppelinOneBalloon.class, "zeppelin big", TiMGenericRegistry.registryPosition, Traincraft.instance, 512, 3, true);//zepplin big
			TiMGenericRegistry.registryPosition++;
		}

		proxy.setHook(); // Moved file needed to run JLayer, we need to set a hook in order to retrieve it

		GameRegistry.registerFuelHandler(new FuelHandler());


		MapGenStructureIO.registerStructureComponent(ComponentVillageTrainstation.class, "Trainstation");

		/* GUI handler initiation */
		tcLog.info("Initialize Gui");
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);


		/* Recipes */
		tcLog.info("Initialize Recipes");


		/*Trainman Villager*/
		tcLog.info("Initialize Station Chief Villager");
		VillagerTraincraftHandler villageHandler = new VillagerTraincraftHandler();
		//1.12 handles these as an event, done below.
		//proxy.registerVillagerSkin(ConfigHandler.TRAINCRAFT_VILLAGER_ID+1, "station_chief.png");
		//VillagerRegistry.instance().registerVillageTradeHandler(ConfigHandler.TRAINCRAFT_VILLAGER_ID+1, villageHandler);
		VillagerRegistry.instance().registerVillageCreationHandler(villageHandler);

		
		tcLog.info("Finished Initialization");


	}

	@SubscribeEvent
	public static void onEvent(final RegistryEvent.Register<VillagerRegistry.VillagerProfession> event) {
		VillagerRegistry.VillagerProfession stationChef = new VillagerRegistry.VillagerProfession(
				Info.modID+"station_manager", "station_chief.png",Info.modID+"station_zombie"
		);
		stationChef.getCareer(0).addTrade(1,new VillagerTraincraftHandler());
		event.getRegistry().register(stationChef);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent evt) {
		tcLog.info("Start to PostInitialize");
		tcLog.info("Register ChunkHandler");

		tcLog.info("Activation Mod Compatibility");
		//railcraft recipe compatibility
		if(Loader.isModLoaded("Railcraft") && !Loader.isModLoaded("tc")){
			File file = new File("./config/railcraft/railcraft.cfg");
			try {
				@SuppressWarnings("resource") Scanner scanner = new Scanner(new FileInputStream(file));

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine().trim();

					if (line.equals("B:useAltRecipes=true")) {
						Traincraft.tcLog.info(
								"You've enabled vanilla rail recipes in Railcraft. Disable them to get Traincraft additional tracks");
						break;
					} else if (line.equals("B:useAltRecipes=false")) {
						Traincraft.tcLog.info("Enabled Traincraft additional tracks for Railcraft");
						break;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		LiquidManager.getLiquidsFromDictionnary();

		if (Loader.isModLoaded("NotEnoughItems")) {
			AssemblyTableNEIIntegration.setupNEIIntegration();
		}

		tcLog.info("Finished PostInitialization");
	}

	@EventHandler
	public void serverStop(FMLServerStoppedEvent event) {
		proxy.killAllStreams();
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new tcAdminPerm());
	}


	public class tcAdminPerm extends CommandBase {
		public String getName() {return "tc.admin";}
		public String getUsage(ICommandSender CommandSender) {return "/tcadmin";}


		public int getRequiredPermissionLevel() {return 2;}

		public void execute(MinecraftServer server, ICommandSender commandSender, String[] args) throws CommandException {
			if(commandSender instanceof EntityPlayer) {
				((EntityPlayer) getCommandSenderAsPlayer(commandSender)).sendMessage(
						new TextComponentString(
								"this command exists as a placeholder to allow admin permissions in TC via plugins and mds such as GroupManager and Forge Essentials"));
			}
		}
	}


}
