package train.common.library;

import net.minecraft.util.EnumChatFormatting;
import train.common.api.TrainRecord;
import train.common.entity.rollingStock.*;

public class EnumTrains {

	/**
	 * Passengers
	 */
	public static TrainRecord[] trains() {
		return new TrainRecord[]{
				TrainRecord.makeEntry("passengerCartBlue", "Passenger Blue", EntityPassengerBlue.class, ItemIDs.minecartPassengerBlue.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, new String[]{"Blue", "Red", "Green", "White", "Black", "Cyan", "Orange", "Grey", "LightGrey", "Brown", "Pink", "Purple", "Lime", "Magenta", "LightBlue", "Yellow", "Skin17", "Skin19"}, 18, 0),
				TrainRecord.makeEntry("passengerCartBlackSmall", "Passenger Small Black", EntityPassenger2.class, ItemIDs.minecartPassenger2.item, "passenger", 0, 0, 0.5, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("passengerLongGreen", "Passenger Green Long", EntityPassenger5.class, ItemIDs.minecartPassenger5.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, new String[]{"Green", "Yellow"}, 18, 0),
				TrainRecord.makeEntry("passengerShortGreen", "Passenger Short Green", EntityPassenger7.class, ItemIDs.minecartPassenger7.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("passenger_1class_DB", "Passenger 1Class DB", EntityPassenger_1class_DB.class, ItemIDs.minecartPassenger8_1class_DB.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("passenger_2class_DB", "Passenger 2Class DB", EntityPassenger_2class_DB.class, ItemIDs.minecartPassenger9_2class_DB.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, new String[]{"Green", "Grey"}, 18, 0),
				TrainRecord.makeEntry("passengerHighSpeedZeroED", "Passenger High Speed Zero ED", EntityPassengerHighSpeedCarZeroED.class, ItemIDs.minecartPassengerHighSpeedCarZeroED.item, "passenger", 0, 0, 2, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("passengerTramNY", "Passenger Tram NY", EntityPassengerTramNY.class, ItemIDs.minecartPassengerTramNY.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("passengerAdler", "Passenger Adler", EntityPassengerAdler.class, ItemIDs.minecartPassengerAdler.item, "passenger", 0, 0, 0.5, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("passengerDBOriental", "Passenger DB oriental", EntityPassengerDBOriental.class, ItemIDs.minecartPassengerDBOriental.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, new String[]{"Yellow", "Blue", "White", "Green", "Purple", "Red"}, 18, 0),
				TrainRecord.makeEntry("passengerIC4_DSB_FG", "Passenger IC4 DSB FG", PassengerIC4_DSB_FG.class, ItemIDs.minecartIC4_DSB_FG.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, null, 10, 0, "Unfinished, Creative Only"),
				TrainRecord.makeEntry("passengerIC4_DSB_FH", "Passenger IC4 DSB FH", PassengerIC4_DSB_FH.class, ItemIDs.minecartIC4_DSB_FH.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, null, 10, 0, "Unfinished, Creative Only"),
				TrainRecord.makeEntry("passengerICE1_Class1", "Passenger ICE 1st Class", EntityPassengerICE_1class.class, ItemIDs.minecartICE1_1stClass.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, new String[]{"White", "Blue"}, 10, 0),
				TrainRecord.makeEntry("passengerICE1_Class2", "Passenger ICE 2nd Class", EntityPassengerICE_2class.class, ItemIDs.minecartICE1_2ndClass.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, new String[]{"White", "Blue"}, 10, 0),
				TrainRecord.makeEntry("passengerICE1_Restaurant", "ICE Restaurant", EntityPassengerICE_Restaurant.class,
						ItemIDs.minecartICE1_Restaurant.item, "passenger", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, new String[]{"White", "Blue"}, 10, 0),
				TrainRecord.makeEntry("passengerGS4", "Passenger GS4", EntityPassengerGS4.class, ItemIDs.minecartGS4_Passenger.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, new String[]{"Orange", "White", "Yellow", "Brown", "Green", "Lime", "Red", "Grey", "Green", "LightGrey", "Blue", "Black", "LightBlue"}, 10, 0),
				TrainRecord.makeEntry("passengerGS4Observatory", "Passenger GS4 Observatory", EntityPassengerGS4_Observatory.class, ItemIDs.minecartGS4_Observatory.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, new String[]{"Orange", "White", "Yellow", "Brown", "Lime", "Red", "Grey", "Green", "LightGrey", "Black", "LightBlue"}, 10, 0),
				TrainRecord.makeEntry("passengerGS4_Tail", "Passenger GS4 Tail", EntityPassengerGS4_Tail.class, ItemIDs.minecartGS4_Tail.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, new String[]{"Orange", "White", "Yellow", "Brown", "Lime", "Red", "Grey", "Green", "LightGrey", "Black", "LightBlue"}, 10, 0),

				TrainRecord.makeEntry("passengerDenverRioGrange", "Passenger Denver Rio Grande", EntityPassengerDenverRioGrande.class, ItemIDs.minecartDenverRioGrandePassenger.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, new String[]{"Yellow", "Red", "Green"}, 10, 0),
				TrainRecord.makeEntry("passengerDenverRioGrandeCombo", "Passenger Denver Rio Grande Combo", EntityPassengerDenverRioGrandeCombo.class, ItemIDs.minecartDenverRioGrandeCombo.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, new String[]{"Yellow", "Red", "Green"}, 10, 0),

				TrainRecord.makeEntry("passengerRheingold", "Passenger Rheingold", EntityPassengerRheingold.class, ItemIDs.minecartPassengerRheingold.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, new String[]{"Blue", "Red", "Green", "LightBlue", "Magenta", "Lime", "Brown"}, 18, 0),
				TrainRecord.makeEntry("passengerRheingoldDining1", "Rheingold Dining", EntityPassengerRheingoldDining1.class, ItemIDs.minecartPassengerRheingoldDining1.item, "work", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, new String[]{"Blue", "Red", "Green", "LightBlue", "Magenta", "Lime"}, 18, 0),
				TrainRecord.makeEntry("passengerRheingoldDining2", "Rheingold Dining Alternate", EntityPassengerRheingoldDining2.class, ItemIDs.minecartPassengerRheingoldDining2.item, "work", 0, 0, 1.5, 0, 0, 0, 0, 0, 0, new String[]{"Blue", "Red", "Green", "LightBlue", "Magenta", "Lime"}, 18, 0),
				TrainRecord.makeEntry("passengerRheingoldPanorama", "Rheingold Panorama", EntityPassengerRheingoldPanorama.class, ItemIDs.minecartPassengerRheingoldPanorama.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, new String[]{"Blue", "Red"}, 18, 0),

				TrainRecord.makeEntry("passengerMILW", "Passenger MILW", EntityPassengerMILW.class, ItemIDs.minecartPassengerMILW.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("passengerMILWTail", "Passenger MILW Tail", EntityPassengerMILWTail.class, ItemIDs.minecartPassengerMILWTail.item, "passenger", 0, 0, 1, 0, 0, 0, 0, 0, 0, null, 18, 0),


				TrainRecord.makeEntry("passengerBamboo", "Bamboo Flatcar Passenger", EntityPassengerBamboo.class, ItemIDs.minecartPassengerBamboo.item, "passenger", 0, 0, 0.1, 0, 0, 0, 0, 0, 0, new String[]{"Red", "Blue", "Black", "Yellow", "Magenta", "Cyan", "Pink", "LightGrey", "Green", "White", "LightBlue", "Lime", "Brown", "Purple", "Orange", "Grey"}, 18, 0),


				/**
				 * Caboose
				*/
				TrainRecord.makeEntry("cabooseRed", "Caboose Red", EntityCaboose.class, ItemIDs.minecartCaboose.item, "caboose", 0, 0, 0.5, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("cabooseBlack", "Caboose Black", EntityCaboose3.class, ItemIDs.minecartCaboose3.item, "caboose", 0, 0, 0.5, 0, 0, 0, 0, 0, 0, null, 18, 0),

				/**
				 * Specials
				 **/
				TrainRecord.makeEntry("GWRBrakeVan", "GWR Toad Brake Van", EntityGWRBrakeVan.class, ItemIDs.minecartGWRBrakeVan.item, "work", 0, 0, 0.7, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("stockCar", "Stock Cart", EntityStockCar.class, ItemIDs.minecartStockCar.item, "special", 0, 0, 2, 0, 0, 0, 0, 0, 0, new String[]{"Blue", "Red", "Yellow", "White"}, 18, 0),
				TrainRecord.makeEntry("drwgStockCar", "DRWG Stock Cart", EntityStockCarDRWG.class, ItemIDs.minecartDRWGStockCar.item, "special", 0, 0, 2, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("workCart", "Work Cart Yellow", EntityWorkCart.class, ItemIDs.minecartWork.item, "work", 0, 0, 0.7, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("flatCart", "Flat Cart", EntityFlatCart.class, ItemIDs.minecartFlatCart.item, "flat", 0, 0, 0.2, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("workCaboose", "Work Caboose", EntityCabooseWorkCart.class, ItemIDs.minecartCabooseWork.item, "work", 0, 0, 0.6, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("cabooseLogging", "Caboose Logging", EntityCabooseLogging.class, ItemIDs.minecartCabooseLogging.item, "work", 0, 0, 0.2, 0, 0, 0, 0, 0, 0, new String[]{"Red", "Cyan", "Grey"}, 18, 0),
				TrainRecord.makeEntry("cabooseLoggingPRR", "PRR Caboose Logging", EntityCabooseLoggingPRR.class, ItemIDs.minecartCabooseLoggingPRR.item, "work", 0, 0, 0.2, 0, 0, 0, 0, 0, 0, new String[]{"Red", "Blue", "Green"}, 18, 0),
				TrainRecord.makeEntry("mailWagen_DB", "Mail Wagon DB", EntityMailWagen_DB.class, ItemIDs.minecartMailWagon_DB.item, "work", 0, 0, 1, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("jukeBoxCart", "JukeBox Cart", EntityJukeBoxCart.class, ItemIDs.minecartJukeBoxCart.item, "special", 0, 0, 0.2, 0, 0, 0, 0, 0, 0, null, 18, 0, "Supports MP3/OGG format M3U/PLS links, like Shoutcast"),
				TrainRecord.makeEntry("flatCartSU", "Flat Cart SU", EntityFlatCartSU.class, ItemIDs.minecartFlatCartSU.item, "flat", 0, 0, 0.2, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("flatCartUS", "Flat Cart US", EntityFlatCartUS.class, ItemIDs.minecartFlatCartUS.item, "flat", 0, 0, 0.4, 0, 0, 0, 0, 0, 0, null, 18, 0),
				TrainRecord.makeEntry("tracksBuilder", "Tracks Builder", EntityTracksBuilder.class, ItemIDs.minecartBuilder.item, "special", 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 14, 0),
				TrainRecord.makeEntry("flatCar_DB", "Flat Cart DB", EntityFlatCar_DB.class, ItemIDs.minecartFlatCart_DB.item, "flat", 0, 0, 0.2, 0, 0, 0, 0, 0, 0, new String[]{"Red", "Green"}, 18, 0),
				TrainRecord.makeEntry("BUnitEMDF7", "EMD F7 B Unit", EntityBUnitEMDF7.class, ItemIDs.minecartLocoEMDF7B.item, "b-unit", 0, 0, 5, 0,
						0,
						0, 0, 0, 12000, new String[]{"Black", "Lime", "Red", "Brown", "Green", "Orange", "Yellow"}, 18, 0,
						"Reduces weight carried by 50 tons when fueled"),
				TrainRecord.makeEntry("BUnitEMDF3", "EMD F3 B Unit", EntityBUnitEMDF3.class, ItemIDs.minecartLocoEMDF3B.item, "b-unit", 0, 0, 5, 0,
						0,
						0, 0, 0, 12000, new String[]{"Yellow", "Black", "Orange", "Blue", "Brown", "Green", "Magenta"}, 18, 0,
						"Reduces weight carried by 50 tons when fueled"),
				TrainRecord.makeEntry("BUnitDD35", "DD35 B Unit", EntityBUnitDD35.class, ItemIDs.minecartLocoDD35B.item, "b-unit", 0, 0, 8, 0, 0, 0, 0, 0,
						12000, new String[]{"Orange", "Black"}, 18, 0, "Reduces weight carried by 80 tons when fueled"),
				TrainRecord.makeEntry("propagandaUs", "Propaganda USA", EntityPropagandaUS.class, ItemIDs.minecartPropagandaUs.item, "decorative", 0, 0, 0.1, 0, 0, 0, 0, 0, 0, new String[]{"Blue", "White", "Red"}, 14, 0),
				TrainRecord.makeEntry("propagandaUSSR", "Propaganda USSR", EntityPropagandaUSSR.class, ItemIDs.minecartPropagandaUSSR.item, "decorative", 0, 0, 0.1, 0, 0, 0, 0, 0, 0, new String[]{"Blue", "White", "Red"}, 14, 0),
				TrainRecord.makeEntry("propagandaJapan", "Propaganda Japan", EntityPropagandaJapan.class, ItemIDs.minecartPropagandaJapan.item, "decorative", 0, 0, 0.1, 0, 0, 0, 0, 0, 0, new String[]{"Red", "White", "Yellow"}, 14, 0),
				TrainRecord.makeEntry("propagandaBritain", "Propaganda Britain", EntityPropagandaBritain.class, ItemIDs.minecartPropagandaBritish.item, "decorative", 0, 0, 0.1, 0, 0, 0, 0, 0, 0, new String[]{"Blue", "White", "Yellow"}, 14, 0),

				/**
				 * Freight
				*/
				TrainRecord.makeEntry("freightCartRed", "Freight Cart Red", EntityFreightCart2.class, ItemIDs.minecartFreightCart2.item, "freight", 3, null, 18, 36, "Cargo: any"),
				TrainRecord.makeEntry("freightCartYellow", "Freight Cart Yellow", EntityFreightCart.class, ItemIDs.minecartChest.item, "freight", 3, null, 18, 36, "Cargo: any"),
				TrainRecord.makeEntry("freightWood", "Freight Flat Cart Log", EntityFreightWood.class, ItemIDs.minecartWood.item, "freight", 3, null, 18, 27, "Cargo: only Logs"),
				TrainRecord.makeEntry("freightHopper", "Freight Hopper Green", EntityFreightGrain.class, ItemIDs.minecartGrain.item, "freight", 4, null, 18, 36, "Cargo: wheat, seeds"),
				TrainRecord.makeEntry("freightKClassRailBox", "Freight K Class Rail Box", EntityFreightKClassRailBox.class, ItemIDs.minecartKClassRailBox.item, "freight", 4, new String[]{"Yellow", "Orange"}, 18, 36, "Fictional. Cargo: any"),
				TrainRecord.makeEntry("freightShortCoveredHopper", "Freight Short Covered Hopper", EntityFreightShortCoveredHopper.class, ItemIDs.minecartShortCoveredHopper.item, "freight", 4, new String[]{"Grey", "Orange", "LightBlue", "Lime", "Blue", "Yellow"}, 18, 36, "Cargo: any"),
				TrainRecord.makeEntry("freightLongCoveredHopper", "Freight Long Covered Hopper", EntityFreightLongCoveredHopper.class, ItemIDs.minecartLongCoveredHopper.item, "freight", 6, new String[]{"LightGrey", "Grey", "Pink", "White", "Green", "Orange", "Lime"}, 18, 54, "Cargo: any"),
				TrainRecord.makeEntry("freightOpenWagon", "Freight Open Wagon", EntityFreightOpenWagon.class, ItemIDs.minecartOpenWagon.item, "freight", 2, null, 18, 36, "Cargo: blocks, vanilla items"),//"train_hopper" for open wagon => weird
				TrainRecord.makeEntry("freightHopperUS", "Freight Hopper US", EntityFreightHopperUS.class, ItemIDs.minecartFreightHopperUS.item, "freight", 4, new String[]{"Brown", "Grey", "LightGrey", "Blue", "Red", "Yellow", "Black", "LightBlue", "Purple", "Green", "Magenta", "Orange", "Lime"}, 18, 27, "Cargo: blocks"),
				TrainRecord.makeEntry("freight100TonHopper", "Freight 100 Ton Hopper", EntityFreight100TonHopper.class, ItemIDs.minecartFreight100TonHopper.item, "freight", 4, new String[]{"Red", "Black", "Blue", "Grey"}, 18, 54, "Cargo: blocks"),
				TrainRecord.makeEntry("flatCartWoodUS", "Freight Flat Cart Wood US", EntityFlatCartWoodUS.class, ItemIDs.minecartFlatCartWoodUS.item, "freight", 3, null, 18, 27, "Cargo: wood stuff"),
				TrainRecord.makeEntry("bulkheadFlatCartWood", "Freight Bulkhead Flat Cart", EntityBulkheadFlatCart.class, ItemIDs.minecartBulkheadFlatCart.item, "freight", 3, new String[]{"Brown", "Yellow", "Green"}, 18, 27, "Cargo: only planks"),
				TrainRecord.makeEntry("freightCartUS", "Freight Cart US", EntityFreightCartUS.class, ItemIDs.minecartFreightCartUS.item, "freight", 3.5, new String[]{"Brown", "Yellow", "Black", "Blue", "Cyan", "Green", "Grey", "LightBlue", "LightGrey", "Lime", "Magenta", "Orange", "Pink", "Purple", "Red", "White"}, 18, 36, "Cargo: blocks, vanilla items"),
				TrainRecord.makeEntry("freightBoxCartUS", "Freight Box Cart US", EntityBoxCartUS.class, ItemIDs.minecartBoxCartUS.item, "freight", 2, new String[]{"Brown", "Red", "Blue", "Black", "Yellow", "Magenta", "Cyan", "Pink", "LightGrey", "Green", "White", "LightBlue", "Lime", "Purple", "Orange", "Grey"}, 18, 45, "Cargo: any"),
				TrainRecord.makeEntry("freightBoxCartPRR", "Freight Box Cart PRR", EntityBoxCartPRR.class, ItemIDs.minecartBoxCartPRR.item, "freight", 2, null, 18, 45, "Cargo: any"),
				TrainRecord.makeEntry("freightCartSmall", "Freight Cart Small", EntityFreightCartSmall.class, ItemIDs.minecartFreightCartSmall.item, "freight", 1, null, 18, 36, "Cargo: any"),
				TrainRecord.makeEntry("freightMinetrain", "Freight Minecart Yellow", EntityFreightMinetrain.class, ItemIDs.minecartMineTrain.item, "freight", 0.5, null, 18, 18, "Cargo: opaque blocks"),
				TrainRecord.makeEntry("freightGTNG", "Freight GTNG Ore Wagon", EntityFreightGTNG.class, ItemIDs.minecartFreightGTNG.item, "freight", 0.5, null, 18, 18, "Cargo: opaque blocks"),
				TrainRecord.makeEntry("flatCartWoodLogs", "Freight Flat Logs", EntityFreightWood2.class, ItemIDs.minecartFreightWood2.item, "freight", 3, null, 18, 18, "Cargo: only logs"),
				TrainRecord.makeEntry("freightClosedRedBrown", "Freight Cart Closed RedBrown", EntityFreightClosed.class, ItemIDs.minecartFreightClosed.item, "freight", 2.5, null, 18, 36, "Cargo: any"),
				TrainRecord.makeEntry("freightOpenRedBrown", "Freight Open RedBrown", EntityFreightOpen2.class, ItemIDs.minecartFreightOpen2.item, "freight", 5, null, 18, 21, "Cargo: any"),
				TrainRecord.makeEntry("freightWagen_DB", "Freight Wagon DB", EntityFreightWagenDB.class, ItemIDs.minecartFreightWagon_DB.item, "freight", 4, new String[]{"Red", "Green", "Yellow", "Blue"}, 18, 54, "Cargo: any"),
				TrainRecord.makeEntry("flatCarRails_DB", "Freight Flat Cart Rails DB", EntityFlatCarRails_DB.class, ItemIDs.minecartFlatCartRail_DB.item, "freight", 5, new String[]{"Red", "Green"}, 18, 36, "Cargo: only rails"),
				TrainRecord.makeEntry("ASTFAutorack", "Freight ASTF Autorack", EntityFreightASTFAutorack.class, ItemIDs.minecartASTFAutorack.item, "freight", 5, null, 18, 36, "Cargo: any | Stack limit 1"),
				TrainRecord.makeEntry("flatCarLogs_DB", "Freight Flat Cart Logs DB", EntityFlatCarLogs_DB.class, ItemIDs.minecartFlatCartLogs_DB.item, "freight", 4, new String[]{"Red", "Green"}, 18, 45, "Cargo: only logs"),
				TrainRecord.makeEntry("slateWagon", "Freight Slate Wagon", EntityFreightSlateWagon.class, ItemIDs.minecartSlateWagon.item, "freight",
						0.5, null, 18, 38, "Cargo: only stone and ores"),
				TrainRecord.makeEntry("iceWagon", "Freight Ice Wagon", EntityFreightIceWagon.class, ItemIDs.minecartIceWagon.item, "freight",
						0.5, null, 18, 38, "Cargo: only ice"),
				TrainRecord.makeEntry("freightCartGS4", "Freight Cart GS4 Baggage", EntityFreightGS4_Baggage.class, ItemIDs.minecartGS4_Baggage.item, "freight", 1, new String[]{"Orange", "White", "Yellow", "Brown", "Lime", "Red", "Grey", "Green", "LightGrey", "Black", "LightBlue"}, 18, 45, "Cargo: any"),
				TrainRecord.makeEntry("freightGondola_DB", "Freight Gondola DB", EntityFreightGondola_DB.class, ItemIDs.minecartFreightGondola_DB.item, "freight", 3.5, new String[]{"Red", "Green"}, 18, 45, "Cargo: blocks, vanilla items"),
				TrainRecord.makeEntry("freightCenterBeam_Empty", "Freight Center Beam Empty", EntityFreightCenterbeam_Empty.class, ItemIDs.minecartFreightCenterBeam_Empty.item, "freight", 1, new String[]{"Grey", "LightGrey"}, 18, 54, "Cargo: any"),
				TrainRecord.makeEntry("freightCenterBeam_Wood1", "Freight Center Beam Wood1", EntityFreightCenterbeam_Wood_1.class, ItemIDs.minecartFreightCenterBeam_Wood_1.item, "freight", 3, new String[]{"Brown", "Blue", "White"}, 18, 54, "Cargo: wood stuff"),
				TrainRecord.makeEntry("freightCenterBeam_Wood2", "Freight Center Beam Wood2", EntityFreightCenterbeam_Wood_2.class, ItemIDs.minecartFreightCenterBeam_Wood_2.item, "freight", 3, new String[]{"Brown", "Yellow", "White"}, 18, 54, "Cargo: wood stuff"),
				TrainRecord.makeEntry("freightWellcar", "Freight Well Car", EntityFreightWellcar.class, ItemIDs.minecartFreightWellcar.item, "freight", 3, new String[]{"Blue", "Red", "Green", "Black", "Grey", "Cyan", "Brown", "Lime", "LightBlue", "LightGrey", "Magenta", "Orange", "Pink", "Purple", "White", "Yellow"}, 18, 54, "Cargo: any"),
				TrainRecord.makeEntry("freightTrailer", "Freight Trailer", EntityFreightTrailer.class, ItemIDs.minecartFreightTrailer.item, "freight", 3, new String[]{"Blue", "Yellow", "LightBlue", "Red", "Grey", "LightGrey", "Magenta", "Orange", "Pink", "Purple", "Lime", "White"}, 18, 54, "Cargo: any"),
				TrainRecord.makeEntry("freightDenverRioGrange", "Denver Rio Grande Baggage", EntityFreightDenverRioGrande.class, ItemIDs.minecartDenverRioGrandeBaggage.item, "freight", 0.5, new String[]{"Yellow", "Red", "Green"}, 18, 54, "Cargo: any"),
				TrainRecord.makeEntry("freightMILWBaggage", "MILW Baggage", EntityFreightBaggageMILW.class, ItemIDs.minecartBaggageMILW.item, "freight", 0.5, null, 18, 54, "Cargo: any"),
				TrainRecord.makeEntry("freightheavyweight", "Heavyweight Freight Car", EntityFreightHeavyweight.class, ItemIDs.minecartHeavyweightMailcar.item, "freight", 0.5, null, 18, 27, "Cargo: any"),
				TrainRecord.makeEntry("freightCartBamboo", "Bamboo Flatcar Freight", EntityFreightBamboo.class, ItemIDs.minecartFreightBamboo.item, "freight", 0.1, new String[]{"Red", "Blue", "Black", "Yellow", "Magenta", "Cyan", "Pink", "LightGrey", "Green", "White", "LightBlue", "Lime", "Brown", "Purple", "Orange", "Grey"}, 18, 36, "Cargo: any"),
				TrainRecord.makeEntry("freightGermanPost", "Freight German Post", EntityFreightGermanPost.class, ItemIDs.minecartFreightGermanPost.item, "freight", 0.1, new String[]{"Yellow", "Red", "Blue"}, 18, 36, "Cargo: non-blocks"),
				TrainRecord.makeEntry("freightDepressedFlatbed", "Freight Depressed Flatcar", EntityFreightDepressedFlatbed.class, ItemIDs.minecartFreightDepressedFlatbed.item, "freight", 0.5, new String[]{"Black", "Grey", "LightGrey", "Yellow"}, 18, 36, "Cargo: any"),
				TrainRecord.makeEntry("freightCarL", "Freight Car L", EntityFreightCartL.class, ItemIDs.minecartFreightL.item, "freight", 3, new String[]{"Red", "Blue"}, 18, 27, "Cargo: any"),
				TrainRecord.makeEntry("freightHeavyweight", "Freight Heavyweight Baggage", EntityFreightHeavyweightBaggage.class, ItemIDs.minecartHeavyweightFreight.item, "freight", 3, new String[]{"Red", "Grey"}, 18, 36, "Cargo: any"),
				/**
				 * Tanks
				 **/
				TrainRecord.makeEntry("tankWagon_DB", "Tank Wagon DB", EntityTankWagon_DB.class, ItemIDs.minecartTankWagon_DB.item, "tank", 0, 0, 6, 0, 0, 0, 0, 0, 50000, new String[]{"Blue", "Green"}, 18, 0, "Capacity: 50000mb"),
				TrainRecord.makeEntry("tankThreeDome", "Tank Wagon Three Dome", EntityTankWagonThreeDome.class, ItemIDs.minecartTankWagonThreeDome.item, "tank", 0, 0, 7.5, 0, 0, 0, 0, 0, 90000, new String[]{"Green", "White"}, 18, 0, "Capacity: 90000mb"),
				TrainRecord.makeEntry("tankWagonUS", "Tank Wagon US", EntityTankWagonUS.class, ItemIDs.minecartTankWagonUS.item, "tank", 0, 0, 6, 0, 0, 0, 0, 0, 70000, new String[]{"Black", "Pink", "Grey", "White", "LightGrey", "Yellow", "Green", "Purple", "Red", "Brown"}, 18, 0, "Capacity: 70000mb"),
				TrainRecord.makeEntry("tankWagonGrey", "Tank Wagon Grey", EntityTankWagon2.class, ItemIDs.minecartTankWagon2.item, "tank", 0, 0, 3, 0, 0, 0, 0, 0, 40000, null, 18, 0, "Capacity: 40000mb"),
				TrainRecord.makeEntry("tankCartLava", "Tank Lava", EntityTankLava.class, ItemIDs.minecartWatertransp.item, "tank", 0, 0, 5, 0, 0, 0, 0, 0, 30000, new String[]{"Empty", "Full"}, 18, 0, "Lava Capacity: 30000mb"),
				TrainRecord.makeEntry("tankWagonYellow", "Tank Wagon Yellow", EntityTankWagon.class, ItemIDs.minecartTankWagon.item, "tank", 0, 0, 6, 0, 0, 0, 0, 0, 40000, null, 18, 0, "Capacity: 40000mb"),

				/**
				 * Tenders
				*/
				TrainRecord.makeEntry("tenderSmall", "Tender Small Black", EntityTenderSmall.class, ItemIDs.minecartTender.item, "tender", 0, 0, 0.1, 0, 0, 0, 0, 0, 5000, new String[]{"Black", "Red", "Blue", "Green", "Yellow"}, 18, 0, "Water capacity: 5000mb"),
				TrainRecord.makeEntry("tenderHeavy", "Tender Heavy", EntityTenderHeavy.class, ItemIDs.minecartTenderHeavy.item, "tender", 0, 0, 2, 0, 0, 0, 0, 0, 14000, new String[]{"Black", "Brown"}, 18, 0,
						"Water capacity: 14000mb"),
				TrainRecord.makeEntry("tenderGS4", "Tender GS4", EntityTenderGS4.class, ItemIDs.minecartGS4_Tender.item, "tender", 0, 0, 2, 0, 0, 0, 0, 0, 18000, new String[]{"Orange", "White"}, 18, 0, "Water capacity: 18000mb"),
				TrainRecord.makeEntry("Model4000GallonTender", "4000GallonTender", EntityTender4000.class, ItemIDs.minecart4000GallonTender.item, "tender", 0, 0, 2, 0, 0, 0, 0, 0, 15000, new String[]{"Green", "Blue", "Red", "Lime"}, 18, 0, "Water capacity: 15000mb"),
				TrainRecord.makeEntry("ModelFowler4FTender", "Fowler 4F Tender", EntityTenderFowler4F.class, ItemIDs.minecartFowler4FTender.item, "tender", 0, 0, 2, 0, 0, 0, 0, 0, 15000, null, 18, 0, "Water capacity: 15000mb"),
				TrainRecord.makeEntry("Model225Tender", "1225 tender", EntityTenderBerk1225.class, ItemIDs.minecarttenderBerk1225.item, "tender", 0, 0, 2, 0, 0, 0, 0, 0, 15000, new String[]{"Black", "Grey"}, 18, 0, "Water capacity: 15000mb"),
				TrainRecord.makeEntry("tender4_4_0", "Tender 4-4-0", EntityTender4_4_0.class, ItemIDs.minecartSteamRedTender.item, "tender", 0, 0, 0.2,
						0, 0, 0, 0, 0, 8000, new String[]{"Black", "White", "Brown", "Blue", "Green", "Red", "Purple"}, 18, 0,
						"Water capacity: 8000mb"),
				TrainRecord.makeEntry("tenderA4", "Tender A4 Mallard", EntityTenderA4.class, ItemIDs.minecartLocoA4MallardTender.item, "tender", 0, 0, 0.2, 0, 0, 0, 0, 0, 6000, new String[]{"Blue", "Lime", "Black", "Green", "White"}, 18, 0, "Water capacity: 6000mb"),
				TrainRecord.makeEntry("tenderBR01", "Tender BR01", EntityTenderBR01_DB.class, ItemIDs.minecartTenderBR01_DB.item, "tender", 0, 0, 0.5, 0, 0, 0, 0, 0, 20000, null, 18, 0, "Water capacity: 20000mb"),
				TrainRecord.makeEntry("tenderCoranationClass", "Tender Coranation Class", EntityTenderCoranationClass.class, ItemIDs.minecartLocoCoranationClassTender.item, "tender", 0, 0, 0.5, 0, 0, 0, 0, 0, 20000, null, 18, 0, "Water capacity: 20000mb"),
				TrainRecord.makeEntry("tenderEr_Ussr", "Tender ER_USSR", EntityTenderEr_Ussr.class, ItemIDs.minecartTenderEr.item, "tender", 0, 0, 2, 0, 0, 0, 0, 0, 16000, null, 18, 0, "Water capacity: 16000mb"),
				TrainRecord.makeEntry("tenderC62Class", "Tender C62Class", EntityTenderC62Class.class, ItemIDs.minecartTenderC62Class.item, "tender", 0,
						0, 0.5, 0, 0, 0, 0, 0, 14000, new String[]{"Black", "Red"}, 18, 0,
						"Water capacity: 14000mb"),
				TrainRecord.makeEntry("tenderD51", "Tender D51", EntityTenderD51.class, ItemIDs.minecartTenderD51.item, "tender", 0,
						0, 0.5, 0, 0, 0, 0, 0, 14000, null, 18, 0,
						"Water capacity: 12000mb"),
				TrainRecord.makeEntry("tenderAdler", "Tender Adler", EntityTenderAdler.class, ItemIDs.minecartTenderAdler.item, "tender", 0, 0, 0.5, 0, 0, 0, 0, 0, 4000, null, 18, 0, "Water capacity: 4000mb"),
				TrainRecord.makeEntry("tender_C41", "Tender C41", EntityTender_C41.class, ItemIDs.minecartTenderC41.item, "tender", 0, 0, 1.5, 0, 0, 0, 0, 0, 16000, null, 18, 0, "Water capacity: 16000mb"),
				TrainRecord.makeEntry("tender_Southern1102", "Tender Southern1102", EntityTender_Southern1102.class, ItemIDs.minecartTenderSouthern1102.item, "tender", 0, 0, 1.5, 0, 0, 0, 0, 0, 16000, null, 18, 0, "Water capacity: 16000mb"),
				TrainRecord.makeEntry("tender_MILW", "Tender MILW", EntityTenderMILW.class, ItemIDs.minecartMILWTender.item, "tender", 0, 0, 1.5, 0, 0, 0, 0, 0, 16000, null, 18, 0, "Water capacity: 16000mb"),
				/** Diesel */
				TrainRecord.makeEntry("locoDieselKOF", "Loco Diesel KOF DB", EntityLocoDieselKof_DB.class, ItemIDs.minecartKof_DB.item, "diesel", 750, 45, 0, 60, 0, 170, 0.66, 0.96, 5000, new String[]{"Red", "Green", "Yellow", "Black", "Blue"}, 17, -1.6),
				TrainRecord.makeEntry("locoDieselGP40", "Loco Diesel CD742", EntityLocoDieselCD742.class, ItemIDs.minecartCD742.item, "diesel", 1184, 90,
						0, 50, 0, 250, 0.8, 0.966, 10000, new String[]{"Yellow", "White", "Blue", "Orange", "LightBlue"}, 15, -2.5),
				TrainRecord.makeEntry("locoDieselChME3", "Loco Diesel ChME3", EntityLocoDieselChME3.class, ItemIDs.minecartChmE3.item, "diesel", 2383, 95, 0, 60, 0, 170, 0.66, 0.96, 5000, null, 15, -1.2),
				TrainRecord.makeEntry("locoDieselGP7Red", "Loco Diesel GP7", EntityLocoDieselGP7Red.class, ItemIDs.minecartGP7Red.item, "diesel", 2464, 105, 0, 50, 0, 200, 0.74, 0.96, 20000, new String[]{"Red", "Blue", "Black", "Yellow", "Magenta", "Cyan", "Pink", "LightGrey", "Green", "White", "LightBlue", "Lime", "Brown", "Purple", "Orange", "Grey", "Skin16"}, 15, -1.4),
				TrainRecord.makeEntry("locoDieselSD40", "Loco Diesel SD40", EntityLocoDieselSD40.class, ItemIDs.minecartLocoSD40.item, "diesel", 3041, 105, 0, 60, 0, 200, 0.8, 0.97, 20000, new String[]{"Red", "Yellow", "Black", "Green", "Orange", "Magenta", "Blue", "Pink"}, 10, -2.3),
				TrainRecord.makeEntry("locoDieselSD70", "Loco Diesel SD70", EntityLocoDieselSD70.class, ItemIDs.minecartLocoSD70.item, "diesel", 4055, 120, 0, 60, 0, 200, 0.8, 0.97, 20000, new String[]{"Orange", "Yellow", "Red", "Blue", "Magenta", "Black", "Pink"}, 10, -2.3),
				TrainRecord.makeEntry("locoDieselShunter", "Loco Diesel Shunter", EntityLocoDieselShunter.class, ItemIDs.minecartShunter.item, "diesel", 354, 32, 0, 70, 0, 260, 0.6, 0.94, 8000, new String[]{"Blue", "Green", "Red", "Black"}, 14, -2.58),
				TrainRecord.makeEntry("locoDieselV60_DB", "Loco Diesel V60 DB", EntityLocoDieselV60_DB.class, ItemIDs.minecartV60_DB.item, "diesel", 1058, 60, 0, 60, 0, 170, 0.66, 0.96, 8000, new String[]{"Red", "Green", "Yellow", "Cyan"}, 15, -1.5),
				TrainRecord.makeEntry("locoDieselIC4_DSB_MG", "Loco Diesel IC4 DSB MG", EntityLocoDieselIC4_DSB_MG.class, ItemIDs.minecartIC4_DSB_MG.item, "diesel", 760, 200, 0, 60, 0, 200, 0.8, 0.97, 20000, new String[]{"White", "Red"}, 10, -6, "Unfinished, Creative Only"),
				TrainRecord.makeEntry("locoDieselMILW_H1044", "Loco Diesel MILW H10-44", EntityLocoDieselMILW_H1044.class, ItemIDs.minecartMILW_H1044.item, "diesel", 1618, 97, 0, 60, 0, 170, 0.66, 0.96, 8000, new String[]{"Orange", "Yellow", "Black", "Red", "Blue", "Grey", "LightBlue", "Green"}, 15, -4.4),
				TrainRecord.makeEntry("locoDieselEMDF7", "Loco Diesel EMD F-7", EntityLocoDieselEMDF7.class, ItemIDs.minecartLocoEMDF7.item, "diesel",
						1600, 150, 0, 50, 0, 200, 0.8, 0.97, 12000, new String[]{"Black", "Lime", "Red", "Brown", "Green", "Orange", "Yellow"}, 10, -2.4),
				TrainRecord.makeEntry("locoDieselEMDF3", "Loco Diesel EMD F-3", EntityLocoDieselEMDF3.class, ItemIDs.minecartLocoEMDF3.item, "diesel",
						1500, 166, 0, 55, 0, 200, 0.8, 0.97, 12000, new String[]{"Yellow", "Black", "Orange", "Blue", "Brown", "Green", "Magenta"}, 10, -2.25),
				TrainRecord.makeEntry("locoDieselEWSClass66", "Loco Electric EWS Class 66", EntityLocoDieselClass66.class, ItemIDs.minecartLocoEWSClass66.item, "diesel", 3300, 121, 0, 10, 0, 170, 0.7, 0.965, 6400, new String[]{"Pink", "Green", "Red"}, 18, -5.5),
				TrainRecord.makeEntry("locoDieselDeltic", "Loco Diesel Deltic", EntityLocoDieselDeltic.class, ItemIDs.minecartLocoDeltic.item, "diesel",
						3300, 161, 0, 10, 0, 170, 0.7, 0.965, 6400, null, 18, -4.7),
				TrainRecord.makeEntry("locoDieselDD35A", "Loco Diesel DD35A", EntityLocoDieselDD35A.class, ItemIDs.minecartLocoDD35A.item, "diesel", 5070, 145, 0, 10, 0, 170, 0.7, 0.965, 6850, new String[]{"Orange", "Black"}, 18, -4.75),
				TrainRecord.makeEntry("locoDiesel44TonSwitcher", "GE 44-ton Diesel switcher", EntityLocoDiesel44TonSwitcher.class, ItemIDs.minecartLoco44TonSwitcher.item, "diesel", 400, 56, 0, 10, 0, 170, 0.7, 0.965, 6850, new String[]{"Black", "Cyan"}, 18, -2.75),
				TrainRecord.makeEntry("locoDieselBamboo", "Bamboo Flatcar Engine", EntityLocoDieselBamboo.class, ItemIDs.minecartTrainBamboo.item, "diesel", 30, 20, 0, 10, 0, 170, 0.7, 0.965, 3000, new String[]{"Red", "Blue", "Black", "Yellow", "Magenta", "Cyan", "Pink", "LightGrey", "Green", "White", "LightBlue", "Lime", "Brown", "Purple", "Orange", "Grey"}, 18, -2),
				TrainRecord.makeEntry("locoDieselWLs40", "Loco WLs40", EntityLocoDieselWLs40.class, ItemIDs.minecartLocoWLs40.item, "diesel", 60, 17, 0, 10, 0, 170, 0.7, 0.965, 3000, null, 18, -3),
				TrainRecord.makeEntry("locoDieselFOL_M1", "Loco FOL M1", EntityLocoDieselFOLM1.class, ItemIDs.minecartLocoFOLM1.item, "diesel", 5000, 110, 0, 10, 0, 170, 0.7, 0.965, 15000, new String[]{"Grey", "Blue"}, 18, -3.9, "Fictional loco from Factorio"),
				TrainRecord.makeEntry("FOLM1B", "FOL-M1B", EntityLocoDieselFOLM1B.class, ItemIDs.minecartFOLM1B.item, "diesel", 5000, 110, 0, 10, 0, 170, 0.7, 0.965, 15000, new String[]{"Grey", "Blue"}, 18, -3.8, "Fictional B unit for the Fictional loco from Factorio"),


				/** Electric */
				TrainRecord.makeEntry("locoElectricVL10", "Loco Electric VL10", EntityLocoElectricVL10.class, ItemIDs.minecartVL10.item, "electric", 6250, 100, 0, 8, 0, 400, 1.1, 0.956, 0, null, 14, -2.3),
				TrainRecord.makeEntry("locoElectricBR_E69", "Loco Electric BR_E69", EntityLocoElectricBR_E69.class, ItemIDs.minecartBR_E69.item, "electric", 400, 50, 0, 5, 0, 400, 0.9, 0.946, 0, new String[]{"Green", "Red", "Black", "Grey"}, 18, 0),
				TrainRecord.makeEntry("locoElectricMineTrain", "Loco Electric Minetrain", EntityLocoElectricMinetrain.class, ItemIDs.minecartLocoMineTrain.item, "electric", 500, 40, 0, 80, 0, 160, 0.5, 0.97, 0, null, 18, -1),
				TrainRecord.makeEntry("locoElectricSpeedZeroED", "Loco Electric High Speed ZeroED", EntityLocoElectricHighSpeedZeroED.class, ItemIDs.minecartLocoHighSpeedZeroED.item, "electric", 700, 240, 0, 3, 0, 230, 1.4, 0.98, 0, null, 13, -3.4),
				TrainRecord.makeEntry("locoElectricICE1", "Loco Electric ICE 1", EntityLocoElectricICE1.class, ItemIDs.minecartICE1_Loco.item,
						"electric", 869, 280, 0, 40, 0, 250, 1.5, 0.98, 0, new String[]{"White", "Blue"}, 13, -5),
				//TrainRecord.makeEntry("locoSpeedGrey",Loco High Speed",EntityLocoElectricNewHighSpeedLoco.class, null,null),
				TrainRecord.makeEntry("locoElectricTramYellow", "Loco Electric Yellow Wood Tram", EntityLocoElectricTramWood.class, ItemIDs.minecartTramWood.item, "electric", 300, 55, 0, 10, 0, 140, 0.5, 0.965, 0, null, 14, -2),
				TrainRecord.makeEntry("locoElectricTramNY", "Loco Electric Tram NY", EntityLocoElectricTramNY.class, ItemIDs.minecartNYTram.item,
						"electric", 1327, 89, 0, 10, 0, 170, 0.7, 0.965, 0, null, 18, -3),
				TrainRecord.makeEntry("locoElectricBR185", "Loco Electric BR 185", EntityLocoElectricBR185.class, ItemIDs.minecartLocoBR185.item, "electric", 2890, 160, 0, 6, 0, 170, 0.9, 0.965, 0, new String[]{"Red", "Blue", "Magenta", "Cyan", "Grey", "LightBlue", "LightGrey", "Orange", "Pink", "Purple", "White", "Yellow", "Black"}, 18, -5),
				TrainRecord.makeEntry("locoDieselE10lDB", "Loco Electric E10 DB", EntityLocoElectricE10_DB.class, ItemIDs.minecartE10_DB.item, "electric", 2473, 150, 0, 8, 0, 170, 0.66, 0.96, 8000, new String[]{"Blue", "Red", "Grey", "Brown", "Green"}, 15, -3.8),
				TrainRecord.makeEntry("locoDieselE103", "Loco Electric E103", EntityLocoElectricE103.class, ItemIDs.minecartE103.item, "electric", 2806, 200, 0, 8, 0, 170, 0.66, 0.96, 8000, new String[]{"Red", "Blue"}, 15, -3.6),
				TrainRecord.makeEntry("locoElectricClass85", "Loco Electric Class 85", EntityLocoElectricClass85.class, ItemIDs.minecartLocoClass85.item, "electric", 2400, 160, 0, 10, 0, 170, 0.7, 0.965, 0, new String[]{"Blue", "Red"}, 18, -5),
				TrainRecord.makeEntry("locoElectricCD151", "Loco Electric CD151", EntityLocoElectricCD151.class, ItemIDs.minecartLocoCD151.item, "electric", 1133, 160, 0, 10, 0, 170, 0.7, 0.965, 6850, new String[]{"Blue", "Yellow", "Red", "Green", "Cyan", "Brown", "Orange", "Purple", "White"}, 18, -4),
				TrainRecord.makeEntry("locoElectricBP4", "Loco Electric BP4", EntityLocoElectricBP4.class, ItemIDs.minecartLocoBP4.item, "electric", 1520, 105, 0, 60, 0, 200, 0.8, 0.97, 8650, new String[]{"Green", "Purple"}, 10, -4.25),

				/** Steam */
				TrainRecord.makeEntry("locoSteamA4", "Loco Steam A4 Mallard", EntityLocoSteamMallardA4.class, ItemIDs.minecartLocoA4Mallard.item, "steam", 1418, 203, 0, 60, 200, 160, 0.65, 0.97, 10000, new String[]{"Blue", "Lime", "Black", "Green", "White"}, 7, -5),
				TrainRecord.makeEntry("locosteamHallClass", "Loco Hall Class", EntityLocoSteamHallClass.class, ItemIDs.minecartLocoHallClass.item, "steam", 1091, 164, 0, 60, 200, 160, 0.65, 0.97, 10000, new String[]{"Green", "Red", "Lime"}, 7, -4.95),
				TrainRecord.makeEntry("locosteamBerk1225", "Loco Berkshire 1225", EntityLocoSteamBerk1225.class, ItemIDs.minecartLocoBerk1225.item, "steam", 2775, 164, 0, 60, 200, 160, 0.65, 0.97, 10000, null, 7, -4.25),
				TrainRecord.makeEntry("locosteamBerk765", "Loco Berkshire 765", EntityLocoSteamBerk765.class, ItemIDs.minecartLocoBerk765.item, "steam", 2563, 164, 0, 60, 200, 160, 0.65, 0.97, 10000, null, 7, -4.25),
				TrainRecord.makeEntry("locosteamFowler", "Loco Fowler", EntityLocoSteamFowler.class, ItemIDs.minecartLocoFowler.item, "steam", 980, 102, 0, 60, 200, 160, 0.65, 0.97, 10000, null, 7, -3.25),
				TrainRecord.makeEntry("locosteamKingClass", "Loco King Class", EntityLocoSteamKingClass.class, ItemIDs.minecartLocoKingClass.item, "steam", 1613, 174, 0, 60, 200, 160, 0.65, 0.97, 10000, new String[]{"Green", "Blue", "Lime"}, 7, -5.35),
				TrainRecord.makeEntry("locoSteamMILW", "Loco Steam MILW Class A", EntityLocoSteamMILWClassA.class, ItemIDs.minecartLocoMILWClassA.item, "steam", 1228, 193, 0, 60, 150, 160, 0.65, 0.97, 10000, null, 7, -4.5),
				TrainRecord.makeEntry("locoSteamCherepanov", "Loco Steam Cherepanov", EntityLocoSteamCherepanov.class, ItemIDs.minecartLocoCherepanov.item, "steam", 60, 30, 0, 40, 120, 120, 0.3D, 0.98D, 3000, null, 18, -1.7),
				TrainRecord.makeEntry("locoSteamBR80", "Loco Steam BR80", EntityLocoSteamBR80_DB.class, ItemIDs.minecartLocoBR80_DB.item, "steam", 575, 45, 0, 100, 130, 135, 0.45, 0.97, 7000, new String[]{"Black", "Green"}, 16, -1.1),
				TrainRecord.makeEntry("locoSteam4_4_0", "Loco Steam 4-4-0", EntityLocoSteam4_4_0.class, ItemIDs.minecartPower.item, "steam", 400, 50, 0, 40, 160, 190, 0.65, 0.95, 5000, new String[]{"Red", "White", "Blue", "Brown", "Green", "Black", "Purple"}, 16, -2),
				TrainRecord.makeEntry("locoSteamSmall", "Loco Steam Small", EntityLocoSteamSmall.class, ItemIDs.minecartLoco3.item, "steam", 250, 45, 0, 140, 140, 160, 0.5D, 0.968D, 5000, new String[]{"Blue", "Red", "Green", "Yellow", "Black"}, 18, -1.7),
				TrainRecord.makeEntry("locoSteamLSSP7", "Loco Steam LSSP7", EntityLocoSteamLSSP7.class, ItemIDs.minecartLocoLSSP7.item, "steam", 250, 45, 0, 140, 140, 160, 0.5D, 0.968D, 5000, null, 18, -1.1),
				TrainRecord.makeEntry("locoHeavySteam", "Loco Steam Heavy", EntityLocoSteamHeavy.class, ItemIDs.minecartHeavySteam.item, "steam", 3000, 75, 0, 40, 140, 190, 0.4D, 0.9D, 10000, new String[]{"Black", "Brown"}, 7, -3),//its back
				TrainRecord.makeEntry("locoSteamC62Class", "Loco Steam C62Class", EntityLocoSteamC62Class.class, ItemIDs.minecartLocoC62Class.item,
						"steam", 1223, 129, 0, 60, 180, 160, 0.7, 0.97, 10000, new String[]{"Black", "Red"}, 7, -5.66),
				TrainRecord.makeEntry("locoSteamD51Short", "Loco Steam D51 Short Streamlining", EntityLocoSteamD51.class, ItemIDs.minecartLocoD51Short.item,
						"steam", 1658, 85, 0, 60, 180, 160, 0.7, 0.97, 10000, new String[]{"Black", "Grey"}, 7, -5.66),
				TrainRecord.makeEntry("locoSteamD51Long", "Loco Steam D51 Long Streamlining", EntityLocoSteamD51Long.class, ItemIDs.minecartLocoD51Long.item,
						"steam", 1658, 85, 0, 60, 180, 160, 0.7, 0.97, 10000, new String[]{"Black", "Grey"}, 7, -5.66),
				TrainRecord.makeEntry("locoSteamBR01_DB", "Loco Steam BR01", EntityLocoSteamBR01_DB.class, ItemIDs.minecartLocoBR01_DB.item, "steam", 2120, 130, 0, 60, 200, 300, 0.6, 0.97, 10000, new String[]{"Black", "Grey", "LightGrey"}, 10, -3.7),
				TrainRecord.makeEntry("locoSteamCoranationClass", "Loco Steam Coranation Class", EntityLocoSteamCoranationClass.class, ItemIDs.minecartLocoCoranationClass.item, "steam", 1619, 183, 0, 60, 200, 300, 0.6, 0.97, 10000, null, 10,
						-6),
				TrainRecord.makeEntry("locoSteamGS4", "Loco Steam GS4", EntityLocoSteamGS4.class, ItemIDs.minecartGS4_Loco.item, "steam", 2653, 180, 0, 60, 350, 450, 0.6, 0.95, 8800, new String[]{"Orange", "White"}, 10, -6),
				TrainRecord.makeEntry("locoSteamEr_USSR", "Loco Steam ER_USSR", EntityLocoSteamEr_Ussr.class, ItemIDs.minecartLocoEr.item, "steam", 800, 80, 0, 80, 100, 200, 0.35, 0.975, 10000, null, 10, -3.7),
				TrainRecord.makeEntry("locoSteamC41", "Loco Steam C41", EntityLocoSteamC41.class, ItemIDs.minecartLocoC41.item, "steam", 1484, 120, 0, 80, 100, 200, 0.35, 0.975, 4000, null, 10, -3.4),
				TrainRecord.makeEntry("locoSteamC41_080", "Loco Steam C41 0-8-0", EntityLocoSteamC41_080.class, ItemIDs.minecartLocoC41_080.item, "steam", 1484, 120, 0, 80, 100, 200, 0.35, 0.975, 4000, null, 10, -3.4),
				TrainRecord.makeEntry("locoSteamAlcoSc4", "Loco Steam Alco SC4", EntityLocoSteamAlcoSC4.class, ItemIDs.minecartLocoAlcoSC4.item, "steam", 800, 120, 0, 80, 100, 200, 0.35, 0.975, 4000, null, 10, -3.4),
				TrainRecord.makeEntry("locoSteamSouthern1102", "Loco Steam Southern 1102", EntityLocoSteamSouthern1102.class, ItemIDs.minecartLocoSouthern1102.item, "steam", 1236, 118, 0, 80, 100, 200, 0.35, 0.975, 10000, null, 10, -3.4),
				TrainRecord.makeEntry("locoSteamUSATCUS", "Loco Steam USATCUS", EntityLocoSteamUSATCUS.class, ItemIDs.minecartLocoUSATCUS.item, "steam", 210, 75, 0, 80, 100, 200, 0.35, 0.975, 10000, null, 10, -2.5),
				TrainRecord.makeEntry("locoSteamUSATCUK", "Loco Steam USATCUK", EntityLocoSteamUSATCUK.class, ItemIDs.minecartLocoUSATCUK.item, "steam", 210, 75, 0, 80, 100, 200, 0.35, 0.975, 10000, null, 10, -2.5),
				TrainRecord.makeEntry("locoSteamC41T", "Loco Steam C41T", EntityLocoSteamC41T.class, ItemIDs.minecartLocoC41T.item, "steam", 1484, 110, 0, 80, 100, 200, 0.35, 0.975, 16000, null, 10, -3.4),
				TrainRecord.makeEntry("locoSteamForney", "Loco Steam Forney", EntityLocoSteamForneyRed.class, ItemIDs.minecartLocoForneyRed.item, "steam", 600, 70, 0, 60, 160, 130, 0.44, 0.968, 8000, new String[]{"Red", "Grey", "Yellow", "Brown", "Blue", "Green"}, 17, -1.8),
				TrainRecord.makeEntry("locoSteamMogul", "Loco Steam Mogul", EntityLocoSteamMogulBlue.class, ItemIDs.minecartLocomogulBlue.item, "steam", 500, 65, 0, 50, 180, 180, 0.56, 0.967, 5000, new String[]{"Blue", "Black", "Brown", "Green", "Red", "White"}, 15, -2.2),
				TrainRecord.makeEntry("locoSteamShay", "Loco Steam Shay", EntityLocoSteamShay.class, ItemIDs.minecartLocoSteamShay.item, "steam", 250, 50, 0, 50, 160, 130, 0.5, 0.968, 4000, null, 15, -1),
				TrainRecord.makeEntry("locoSteamVBShay", "Loco Steam VB Shay", EntityLocoSteamVBShay.class, ItemIDs.minecartLocoSteamVBShay.item, "steam", 250, 32, 0, 40, 140, 100, 0.5, 0.968, 3000, null, 15, -0.5),
				TrainRecord.makeEntry("locoSteamClimax", "Loco Steam Climax", EntityLocoSteamClimax.class, ItemIDs.minecartLocoSteamClimax.item, "steam", 250, 45, 0, 50, 160, 130, 0.5, 0.968, 4000, null, 15, -1.5),
				TrainRecord.makeEntry("locoSteamPannier", "Loco Steam Pannier", EntityLocoSteamPannier.class, ItemIDs.minecartLocoSteamPannier.item, "steam", 903, 80, 0, 50, 160, 130, 0.5, 0.968, 8000, null, 15, -3.5),
				TrainRecord.makeEntry("locoSteamAlice", "Loco Steam Alice", EntityLocoSteamAlice0_4_0.class, ItemIDs.minecartLocoSteamAlice.item, "steam", 200, 32, 0, 60, 160, 200, 0.5, 0.968, 3750, null, 15, -2),
				TrainRecord.makeEntry("locoSteamGLYN", "Loco Steam glyn", EntityLocoSteamGLYN042T.class, ItemIDs.minecartLocoSteamGLYN.item, "steam", 600, 32, 0, 60, 160, 200, 0.45, 0.968, 3750, null, 15, -2.5),
				TrainRecord.makeEntry("locoSteam262T", "Loco Steam 262T", EntityLocoSteam262T.class, ItemIDs.minecartLocoSteam262T.item, "steam", 300, 70, 0, 60, 160, 300, 0.5, 0.968, 4250, null, 15, -3),
				TrainRecord.makeEntry("locoSteam040VB", "Loco Steam 040VB", EntityLocoSteam040VB.class, ItemIDs.minecartLocoSteam040vb.item, "steam", 200, 32, 0, 40, 120, 200, 0.5, 0.968, 2500, null, 15, -1.1),
				TrainRecord.makeEntry("locoSteamAdler", "Loco Steam Adler", EntityLocoSteamAdler.class, ItemIDs.minecartLocoSteamAdler.item, "steam", 200, 65, 0, 60, 160, 300, 0.5, 0.968, 3000, null, 15, -1.5),
				TrainRecord.makeEntry("locoSteamSnowPlow", "Loco Steam Snow Plow", EntityLocoSteamSnowPlow.class, ItemIDs.minecartLocoSnowPlow.item, "steam, snow plow", 200, 20, 0, 10, 120, 170, 0.7, 0.965, 6850, null, 18, -4.75),

				};
	}


	//String trainType,int MHP,int maxSpeed, double mass, int fuelConsumption, int waterConsumption, int heatingTime, double accelerationRate, double brakeRate, int tankCapacity, String[] colors,
	
	/*private String internalName;
	private Class entityClass;
	private Item item;
	private String trainType;
	private int MHP;
	private int maxSpeed;
	private double mass;
	private int fuelConsumption;
	private int waterConsumption;
	private int heatingTime;
	private double accelerationRate;
	private double brakeRate;
	private int tankCapacity;
	private int[] colors;
	private int guiRenderScale;
	private double bogieLocoPosition;
	private String additionnalTooltip;
	private int cargoCapacity;
	
	/**
	 * 
	 * @param internalName : Only used by EntityRegistry
	 * @param entityClass
	 * @param item
	 * @param trainType: "steam", "diesel", "freight", "passenger", "special", "flat", "electric"
	 * @param MHP: power of the locomotive
	 * @param maxSpeed
	 * @param mass (will be multiplied by 10 internally. That means putting 0.1 here will create a mass of 1 Ton in game)
	 * @param fuelConsumption: 1 unit is consumed every x ticks
	 * @param waterConsumption: 1 unit is consumed every x ticks
	 * @param heatingTime
	 * @param accelerationRate: generally around 0.45
	 * @param brakeRate: generally around 0.98
	 * @param tankCapacity
	 * @param colors: an array with all possible colors. Index 0 is used as default color when train is first spawned. leave null if no color available
	 * @param guiRenderScale: scale at which the entity will be rendered inside the GUI (crafting GUI)
	 *
	private EnumTrains(String internalName,Class entityClass, Item item, String trainType,int MHP,int maxSpeed, double mass, int fuelConsumption, int waterConsumption, int heatingTime, double accelerationRate, double brakeRate, int tankCapacity, int[] colors, int guiRenderScale, double bogieLocoPosition){
		this.internalName = internalName;
		this.entityClass = entityClass;
		this.item = item;
		this.trainType=trainType;
		this.MHP=MHP;
		this.maxSpeed=maxSpeed;
		this.mass=mass;
		this.fuelConsumption=fuelConsumption;
		this.waterConsumption=waterConsumption;
		this.heatingTime=heatingTime;
		this.accelerationRate=accelerationRate;
		this.brakeRate=brakeRate;
		this.tankCapacity=tankCapacity;
		this.colors=colors;
		this.guiRenderScale = guiRenderScale;
		this.bogieLocoPosition = bogieLocoPosition;
	}
	
	/**
	 * Constructor for additionnal tooltips on the item
	 * @param internalName
	 * @param entityClass
	 * @param item
	 * @param trainType
	 * @param MHP
	 * @param maxSpeed
	 * @param mass
	 * @param fuelConsumption
	 * @param waterConsumption
	 * @param heatingTime
	 * @param accelerationRate
	 * @param brakeRate
	 * @param tankCapacity
	 * @param colors
	 * @param guiRenderScale
	 * @param bogieLocoPositions
	 * @param additionnalTooltip
	 *
	private EnumTrains(String internalName,Class entityClass, Item item, String trainType,int MHP,int maxSpeed, double mass, int fuelConsumption,int waterConsumption, int heatingTime, double accelerationRate, double brakeRate, int tankCapacity, int[] colors, int guiRenderScale, double bogieLocoPositions, String additionnalTooltip){
		this.internalName = internalName;
		this.entityClass = entityClass;
		this.item = item;
		this.trainType=trainType;
		this.MHP=MHP;
		this.maxSpeed=maxSpeed;
		this.mass=mass;
		this.fuelConsumption=fuelConsumption;
		this.waterConsumption=waterConsumption;
		this.heatingTime=heatingTime;
		this.accelerationRate=accelerationRate;
		this.brakeRate=brakeRate;
		this.tankCapacity=tankCapacity;
		this.colors=colors;
		this.guiRenderScale = guiRenderScale;
		this.bogieLocoPosition = bogieLocoPositions;
		this.additionnalTooltip=additionnalTooltip;
	}
	
	/**
	 * Constructor for freight carts
	 * @param internalName
	 * @param entityClass
	 * @param item
	 * @param trainType
	 * @param mass
	 * @param colors
	 * @param guiRenderScale
	 * //@param bogieLocoPositions
	 * //@param bogieUtilityPositions
	 * @param cargoCapacity
	 * @param additionnalTooltip
	 *
	private EnumTrains(String internalName,Class entityClass, Item item, String trainType, double mass, int[] colors, int guiRenderScale, int cargoCapacity, String additionnalTooltip){
		this.internalName = internalName;
		this.entityClass = entityClass;
		this.item = item;
		this.trainType=trainType;
		this.mass=mass;
		this.colors=colors;
		this.guiRenderScale = guiRenderScale;
		this.additionnalTooltip=additionnalTooltip;
		this.cargoCapacity = cargoCapacity;
	}


	@Override
	public String getName(){
		return this.name();
	}

	@Override
	public String getInternalName(){
		return this.internalName;
	}

	@Override
	public Item getItem(){
		return this.item;
	}

	@Override
	public String getTrainType(){
		return this.trainType;
	}

	@Override
	public int getMHP(){
		return this.MHP;
	}

	@Override
	public int getMaxSpeed(){
		return this.maxSpeed;
	}

	@Override
	public double getMass(){
		return this.mass;
	}

	@Override
	public int getFuelConsumption(){
		return this.fuelConsumption;
	}

	@Override
	public int getWaterConsumption(){
		return this.waterConsumption;
	}

	@Override
	public int getHeatingTime(){
		return this.heatingTime;
	}

	@Override
	public double getAccelerationRate(){
		return this.accelerationRate;
	}

	@Override
	public double getBrakeRate(){
		return this.brakeRate;
	}

	@Override
	public int getTankCapacity(){
		return this.tankCapacity;
	}

	@Override
	public int[] getColors(){
		return this.colors;
	}

	@Override
	public double getBogieLocoPosition(){
		return this.bogieLocoPosition;
	}


	@Override
	public Class getEntityClass() {
		return this.entityClass;
	}

	@Override
	public int getGuiRenderScale(){
		return this.guiRenderScale;
	}

	@Override
	public String getAdditionnalTooltip(){
		return this.additionnalTooltip;
	}

	@Override
	public int getCargoCapacity(){
		return cargoCapacity;
	}
	@Override
	public AbstractTrains getEntity(World world){
		try {
			return (AbstractTrains) entityClass.getConstructor(World.class).newInstance(world);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public AbstractTrains getEntity(World world, double x, double y, double z){
		try {
			if(world.isRemote){
				entityClass.getConstructor(World.class).newInstance(world);
			} else {
				return (AbstractTrains) entityClass.getConstructor(World.class, double.class, double.class, double.class).newInstance(world, x, y, z);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
