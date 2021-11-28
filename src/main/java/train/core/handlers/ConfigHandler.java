/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 *
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.core.handlers;

import net.minecraftforge.common.config.Configuration;
import train.Traincraft;

import java.io.File;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

public class ConfigHandler {

	public static boolean ORE_GEN;
	public static boolean COPPER_ORE_GEN;
	public static boolean ENABLE_ZEPPELIN;
	public static boolean SOUNDS;
	public static boolean FLICKERING;
	public static boolean ENABLE_STEAM;
	public static boolean ENABLE_DIESEL;
	public static boolean ENABLE_ELECTRIC;
	public static boolean ENABLE_BUILDER;
	public static boolean ENABLE_TENDER;
	public static boolean ENERGYTRACK_USES_RF;
	public static int TRAINCRAFT_VILLAGER_ID;
	public static int WINDMILL_CHECK_RADIUS;
	public static boolean REAL_TRAIN_SPEED;
	public static boolean RETROGEN_CHUNKS;
	public static boolean MAKE_MODPACKS_GREAT_AGAIN;
	public static boolean DISABLE_TRAIN_WORKBENCH;
	public static int REMASTERS=1;

	public static void init(File configFile) {
		Configuration cf = new Configuration(configFile);

		cf.load();
		/* General */
		SOUNDS = cf.get(CATEGORY_GENERAL, "ENABLE_SOUNDS", true).getBoolean(true);
		FLICKERING = cf.get(CATEGORY_GENERAL, "DISABLE_FLICKERING", true,"forces trains and rollingstock to render twice, this fixes some bugs with texture flickering.").getBoolean(true);
		ORE_GEN = cf.get(CATEGORY_GENERAL, "ENABLE_FUEL_ORES_SPAWN", true).getBoolean(true);
		COPPER_ORE_GEN = cf.get(CATEGORY_GENERAL, "ENABLE_COPPER_SPAWN", true).getBoolean(true);
		ENABLE_ZEPPELIN = cf.get(CATEGORY_GENERAL, "ENABLE_ZEPPELIN", true).getBoolean(true);
		ENABLE_STEAM = cf.get(CATEGORY_GENERAL, "ENABLE_STEAM_TRAINS", true).getBoolean(true);
		ENABLE_DIESEL = cf.get(CATEGORY_GENERAL, "ENABLE_DIESEL_TRAINS", true).getBoolean(true);
		ENABLE_ELECTRIC = cf.get(CATEGORY_GENERAL, "ENABLE_ELECTRIC_TRAINS", true).getBoolean(true);
		ENABLE_BUILDER = cf.get(CATEGORY_GENERAL, "ENABLE_TRACKS_BUILDER", true).getBoolean(true);
		ENABLE_TENDER = cf.get(CATEGORY_GENERAL, "ENABLE_TENDERS", true).getBoolean(true);
		TRAINCRAFT_VILLAGER_ID = cf.get(CATEGORY_GENERAL, "TRAINCRAFT_VILLAGER_ID", 86).getInt();

		REMASTERS = cf.get(CATEGORY_GENERAL, "ENABLE_REMASTERS", 1, "setting to 0 enables remastered trains and stock alongside the originals. 1 will disable the remasters. 2 will remove the originals.").getInt();

		REAL_TRAIN_SPEED = cf.get(CATEGORY_GENERAL, "REAL_TRAIN_SPEED", false).getBoolean(false);
		ENERGYTRACK_USES_RF = cf.getBoolean("ENERGYTRACK_USES_RF", CATEGORY_GENERAL, true, "Here you can define, if electric tracks should be powered by redstone (false) or use 'real' RF-power (true) [Default: true]");
		RETROGEN_CHUNKS = cf.getBoolean("ENABLE_RETROGEN", CATEGORY_GENERAL, false, "This will generate Traincraft ores in chunks that existed prior to installing Traincraft.");
		MAKE_MODPACKS_GREAT_AGAIN = cf.getBoolean("MAKE_MODPACKS_GREAT_AGAIN", CATEGORY_GENERAL, false,
				"This will disable some of Traincrafts easier recipes to balance Modpacks");
		WINDMILL_CHECK_RADIUS = cf.getInt("WINDMILL_CHECK_RADIUS", CATEGORY_GENERAL, 1, -1, 10, "This sets the radius for the can-see-the-sky-check area around the windmill. 0=only location of windmill, 1=3x3, 2=5x5 etc. Use -1 to turn of this check completely. DEFAULT: 1");
		DISABLE_TRAIN_WORKBENCH = cf.get(CATEGORY_GENERAL, "DISABLE_TRAIN_WORKBENCH", false, "disables the train workbench, for those of you who want to use a custom part builder").getBoolean(false);


		if(cf.hasChanged()) {
			cf.save();
		}
	}
}