/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal & DV8FromTheWorld. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal & DV8FromTheWorld
 ******************************************************************************/

package train.core.handlers;

public class AchievementHandler {

	/*todo: make an achievement handler in TiM, branch add-ons off the core and then individual achievements off that.
	//    Additionally add further support for recursive achievement branching.
	// ex: TiMGenericRegistry.registerAchievement(MODID,Name,DisplayItem).setParent(TiMGenericRegistry.getAchievement(Name));

	public static AchievementPage tmPage;

	private static Achievement achievement(String name, int column, int row, Block block, Achievement parent) {
		return achievement(name, column, row, new ItemStack(block), parent);
	}

	private static Achievement achievement(String name, int column, int row, Item item, Achievement parent) {
		return achievement(name, column, row, new ItemStack(item), parent);
	}

	private static Achievement achievement(String name, int column, int row, ItemStack stack, Achievement parent) {
		Achievement result = new Achievement("achievement.tc." + name, "tc:" + name, column, row, stack, parent);
		if(stack == null) {
			result.initIndependentStat();
		}
		return result.registerStat();
	}

	public static void load() {
		AchievementIDs.zeppelin.achievement = achievement("zeppelin", 2, 4, ItemIDs.airship.item, AchievementIDs.firebox.achievement);
		AchievementIDs.smallSteam.achievement = achievement("smallSteam", -2, 6, ItemIDs.minecartLoco3.item, AchievementIDs.firebox.achievement);
		AchievementIDs.normalSteam.achievement = achievement("normalSteam", 0, 6, ItemIDs.minecartPower.item, AchievementIDs.firebox.achievement);
		AchievementIDs.heavySteam.achievement = achievement("heavySteam", 2, 6, ItemIDs.minecartHeavySteam.item, AchievementIDs.firebox.achievement);
		//TODO put it back once Heavy Steam is back

		AchievementIDs.diesel.achievement = achievement("dieselLoco", -6, 6, ItemIDs.minecartCD742.item, AchievementIDs.dieselEngine.achievement);

		AchievementIDs.tram.achievement = achievement("tram", -12, 10, ItemIDs.minecartTramWood.item, AchievementIDs.electMotor.achievement);
		AchievementIDs.fast.achievement = achievement("fast", -8, 10, ItemIDs.minecartVL10.item, AchievementIDs.electMotor.achievement);

		AchievementIDs.engineer.achievement = achievement("engineer", -2, -2, ItemIDs.overalls.item, null);

		AchievementIDs.passenger.achievement = achievement("passenger", 5, 2, ItemIDs.minecartPassenger2.item, AchievementIDs.assemblyTable.achievement);
		AchievementIDs.freight.achievement = achievement("freight", 7, 2, ItemIDs.minecartChest.item, AchievementIDs.assemblyTable.achievement);
		AchievementIDs.liquid.achievement = achievement("liquid", 5, 4, ItemIDs.minecartWatertransp.item, AchievementIDs.assemblyTable.achievement);
		AchievementIDs.tender.achievement = achievement("tender", 7, 4, ItemIDs.minecartTender.item, AchievementIDs.assemblyTable.achievement);
		AchievementIDs.workCart.achievement = achievement("workCart", 5, 6, ItemIDs.minecartWork.item, AchievementIDs.assemblyTable.achievement).setSpecial();
		AchievementIDs.builder.achievement = achievement("builder", 7, 6, ItemIDs.minecartBuilder.item, AchievementIDs.assemblyTable.achievement);
		AchievementIDs.jukebox.achievement = achievement("jukebox", 5, 8, ItemIDs.minecartJukeBoxCart.item,
				AchievementIDs.assemblyTable.achievement).setSpecial();
		AchievementIDs.minetrain.achievement = achievement("minetrain", 7, 8, ItemIDs.minecartMineTrain.item, AchievementIDs.assemblyTable.achievement);
		AchievementIDs.cherepanov.achievement = achievement("cherepanov", 5, 10, ItemIDs.minecartLocoCherepanov.item, AchievementIDs.assemblyTable.achievement);
		AchievementIDs.mail.achievement = achievement("mail", 7, 10, ItemIDs.minecartMailWagon_DB.item, AchievementIDs.assemblyTable.achievement);
		AchievementIDs.stockCar.achievement = achievement("stockcar", 5, 12, ItemIDs.minecartStockCar.item, AchievementIDs.assemblyTable.achievement);
		AchievementIDs.caboose.achievement = achievement("caboose", 7, 12, ItemIDs.minecartCaboose.item, AchievementIDs.assemblyTable.achievement);
		AchievementIDs.flatCart.achievement = achievement("flatCart", 5, 14, ItemIDs.minecartFlatCartRail_DB.item, AchievementIDs.assemblyTable.achievement);
		AchievementIDs.mtg.achievement = achievement("mtg",-14,10,ItemIDs.minecartLUengine.item, AchievementIDs.tram.achievement);
		AchievementIDs.massTransit.achievement = achievement("massTransit", -14, 12, ItemIDs.minecartClass345car.item, AchievementIDs.mtg.achievement);
		AchievementIDs.cercanias.achievement = achievement("cercanias", -16, 10, ItemIDs.minecartLocoRenfe446Motor.item, AchievementIDs.mtg.achievement);

		AchievementIDs.geared.achievement = achievement("geared", 0, 8, ItemIDs.minecartVBShay2.item, AchievementIDs.normalSteam.achievement);
		AchievementIDs.powerful.achievement = achievement("powerful", -8, 5, ItemIDs.minecartLocoDD35A.item, AchievementIDs.dieselEngine.achievement);
		AchievementIDs.switchin.achievement = achievement("switchin", -8, 3, ItemIDs.minecartLocoSW8.item, AchievementIDs.dieselEngine.achievement);



		//TODO put this: AchievementIDs.heavySteam.achievement  back once Heavy Steam is back
		Achievement ach[] = new Achievement[] { AchievementIDs.steel.achievement, AchievementIDs.stake.achievement,
				AchievementIDs.dieselFuel.achievement, AchievementIDs.electMotor.achievement,
				AchievementIDs.dieselEngine.achievement, AchievementIDs.firebox.achievement,
				AchievementIDs.zeppelin.achievement, AchievementIDs.smallSteam.achievement,
				AchievementIDs.normalSteam.achievement, AchievementIDs.passenger.achievement,
				AchievementIDs.freight.achievement, AchievementIDs.liquid.achievement,
				AchievementIDs.tender.achievement, AchievementIDs.diesel.achievement, AchievementIDs.tram.achievement,
				AchievementIDs.fast.achievement, AchievementIDs.workCart.achievement,
				AchievementIDs.builder.achievement, AchievementIDs.openHearth.achievement,
				AchievementIDs.engineer.achievement, AchievementIDs.distilationTower.achievement,
				AchievementIDs.plastic.achievement, AchievementIDs.fineCopperWire.achievement,
				AchievementIDs.electronicCircuit.achievement, AchievementIDs.generator.achievement,
				AchievementIDs.woodenParts.achievement, AchievementIDs.ironParts.achievement,
				AchievementIDs.steelParts.achievement, AchievementIDs.trainWB.achievement,
				AchievementIDs.assemblyTable.achievement, AchievementIDs.mail.achievement,
				AchievementIDs.minetrain.achievement, AchievementIDs.cherepanov.achievement,
				AchievementIDs.flatCart.achievement, AchievementIDs.stockCar.achievement,
				AchievementIDs.caboose.achievement, AchievementIDs.mtg.achievement,
				AchievementIDs.massTransit.achievement, AchievementIDs.cercanias.achievement,
				AchievementIDs.powerful.achievement, AchievementIDs.switchin.achievement,
				AchievementIDs.heavySteam.achievement,AchievementIDs.jukebox.achievement, AchievementIDs.geared.achievement};

		tmPage = new AchievementPage("TrainCraft", ach);

	}


	public enum AchievementIDs {
		zeppelin(new Item[] { ItemIDs.zeppelin.item, ItemIDs.airship.item }),
		smallSteam(new Item[] { ItemIDs.minecartLoco3.item }),
		normalSteam(new Item[] { ItemIDs.minecartPower.item, ItemIDs.minecartLocoBR01_DB.item,
				EntityLocoSteamBR80_DB.thisItem, ItemIDs.minecartLocoEr.item, ItemIDs.minecartLocoForneyRed.item,
				ItemIDs.minecartLocomogulBlue.item, }),
		passenger(new Item[] { ItemIDs.minecartPassenger5.item, ItemIDs.minecartPassengerBlue.item,
				ItemIDs.minecartPassenger2.item, ItemIDs.minecartCaboose.item, ItemIDs.minecartCaboose3.item,
				ItemIDs.minecartPassenger7.item, ItemIDs.minecartPassenger8_1class_DB.item,
				ItemIDs.minecartPassenger9_2class_DB.item }),
		freight(new Item[] { ItemIDs.minecartChest.item, ItemIDs.minecartGrain.item, ItemIDs.minecartWood.item,
				ItemIDs.minecartFreightCart2.item, ItemIDs.minecartOpenWagon.item, ItemIDs.minecartBoxCartUS.item,
				ItemIDs.minecartFreightCartSmall.item, ItemIDs.minecartFreightCartUS.item,
				ItemIDs.minecartFreightCenterBeam_Empty.item, ItemIDs.minecartFreightCenterBeam_Wood_1.item,
				ItemIDs.minecartFreightCenterBeam_Wood_2.item, ItemIDs.minecartFreightClosed.item,
				ItemIDs.minecartFreightClosed.item, ItemIDs.minecartFreightGondola_DB.item,
				ItemIDs.minecartFreightHopperUS.item, ItemIDs.minecartFreightOpen2.item,
				ItemIDs.minecartFreightTrailer.item, ItemIDs.minecartFreightWagon_DB.item,
				ItemIDs.minecartFreightWellcar.item, ItemIDs.minecartFreightWood2.item,
				ItemIDs.minecartOpenWagon.item }),
		liquid(new Item[] { ItemIDs.minecartWatertransp.item, ItemIDs.minecartTankWagon.item,
				ItemIDs.minecartTankWagon2.item, ItemIDs.minecartTankWagon_DB.item,
				ItemIDs.minecartTankWagonUS.item }),
		tender(new Item[] { ItemIDs.minecartTender.item, ItemIDs.minecartTenderBR01_DB.item,
				ItemIDs.minecartTenderEr.item, ItemIDs.minecartSteamRedTender.item }),
		diesel(new Item[] { ItemIDs.minecartGP7Red.item, ItemIDs.minecartShunter.item, ItemIDs.minecartChmE3.item,
				ItemIDs.minecartCD742.item, ItemIDs.minecartKof_DB.item, ItemIDs.minecartLocoSD70.item,
				ItemIDs.minecartV60_DB.item }),
		tram(new Item[] { ItemIDs.minecartTramWood.item, ItemIDs.minecartTramWood.item }),
		fast(new Item[] { ItemIDs.minecartVL10.item, ItemIDs.minecartBR_E69.item }),
		// heavySteam(new Item[] { ItemIDs.minecartHeavySteam.item }),//TODO put it back once Heavy
		// Steam is back
		workCart(new Item[] { ItemIDs.minecartWork.item, ItemIDs.minecartCabooseWork.item }),
		builder(new Item[] { ItemIDs.minecartBuilder.item }),
		jukebox(new Item[] { ItemIDs.minecartJukeBoxCart.item }),

		assemblyTable(new Item[] { Item.getItemFromBlock(TCBlocks.trainTableTier1),
				Item.getItemFromBlock(TCBlocks.trainTableTier2),
				Item.getItemFromBlock(TCBlocks.trainTableTier3) }),
		engineer(new Item[] { ItemIDs.overalls.item, ItemIDs.hat.item, ItemIDs.jacket.item }),
		cherepanov(new Item[] {EntityLocoSteamCherepanov.thisItem}),
		minetrain(new Item[] { ItemIDs.minecartLocoMineTrain.item }),
		flatCart(new Item[] { ItemIDs.minecartFlatCart.item, EntityFlatCar_DB.thisItem,
				ItemIDs.minecartFlatCartLogs_DB.item, ItemIDs.minecartFlatCartRail_DB.item,
				ItemIDs.minecartFlatCartSU.item, ItemIDs.minecartFlatCartUS.item,
				ItemIDs.minecartFlatCartWoodUS.item }),
		mail(new Item[] { ItemIDs.minecartMailWagon_DB.item }),
		caboose(new Item[] { ItemIDs.minecartCaboose.item, ItemIDs.minecartCaboose3.item,
				ItemIDs.minecartCabooseWork.item }),
		stockCar(new Item[] { ItemIDs.minecartStockCar.item, ItemIDs.minecartDRWGStockCar.item }),
		mtg(new Item[] {ItemIDs.minecartLUengine.item}),
		massTransit(new Item[] { ItemIDs.minecartClass345engine.item, ItemIDs.minecartDstockEngine.item, ItemIDs.minecartLocoRenfe446Motor.item }),
		cercanias(new Item[] { ItemIDs.minecartLocoRenfe446Motor.item,}),
		geared(new Item[] { ItemIDs.minecartVBShay2.item, ItemIDs.minecartLocoSteamVBShay.item,
				ItemIDs.minecartLocoSteamShay.item, ItemIDs.minecartLocoSteamClimax.item,
				ItemIDs.minecartLocoLSSP7.item}),
		switchin(new Item[]{ItemIDs.minecartLocoSW8.item, ItemIDs.minecartLocoGP15.item}),
		powerful(new Item[]{ItemIDs.minecartLocoDD35A.item}),
		heavySteam(new Item[]{ItemIDs.minecartHeavySteam.item}),
		;

		public Achievement	achievement;
		protected Item[]	items;

		public Item[] getItems() {
			return this.items;
		}

		private AchievementIDs(Item[] items) {
			this.items = items;
		}
	}*/
}