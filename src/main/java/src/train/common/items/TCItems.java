/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package src.train.common.items;

import cpw.mods.fml.common.registry.GameRegistry;
import src.train.common.Traincraft;
import src.train.common.library.Info;
import src.train.common.library.ItemIDs;

public class TCItems {

	public static void init() {
		loadItems();
		nameItems();
	}

	private static void loadItems() {
		for (ItemIDs items : ItemIDs.values()) {
			if (items.className != null) {
				if (items.className.equals("ItemTrain")) {
					items.item = new ItemTrain();
				}
				else if (items.className.equals("ItemRollingStock")) {
					items.item = new ItemRollingStock();
				}
				else if (items.className.equals("ItemRotativeDigger")) {
					items.item = new ItemRotativeDigger();
				}
				else if (items.className.equals("ItemContainer")) {
					items.item = new ItemContainer();
				}
			}
		}
		//ItemIDs.signal.item = new ItemSignal(ItemIDs.signal.itemID, BlockIDs.activeSignal.block).setIconIndex(ItemIDs.signal.iconIndex);
		ItemIDs.chunkLoaderActivator.item = new ItemChunkLoaderActivator();
		ItemIDs.recipeBook.item = new ItemRecipeBook();
		
		ItemIDs.stake.item = new ItemStacked(200);
		ItemIDs.airship.item = new ItemZeppelins(0);
		ItemIDs.zeppelin.item = new ItemZeppelins(1);
		ItemIDs.overalls.item = new ItemTCArmor(Traincraft.instance.armor, Traincraft.trainArmor, 2,0);
		ItemIDs.jacket.item = new ItemTCArmor(Traincraft.instance.armor, Traincraft.trainArmor, 1,0);
		ItemIDs.hat.item = new ItemTCArmor(Traincraft.instance.armor, Traincraft.trainArmor, 0,0);
		
		/**Paintable armors:*/
		ItemIDs.pants_ticketMan_paintable.item = new ItemTCArmor(Traincraft.instance.armorCloth, Traincraft.trainCloth, 2,0xdedede);
		ItemIDs.jacket_ticketMan_paintable.item = new ItemTCArmor(Traincraft.instance.armorCloth, Traincraft.trainCloth, 1,0x002cdb);
		ItemIDs.hat_ticketMan_paintable.item = new ItemTCArmor(Traincraft.instance.armorCloth, Traincraft.trainCloth, 0,0x9fafb5);
		
		ItemIDs.pants_driver_paintable.item = new ItemTCArmor(Traincraft.instance.armorCloth, Traincraft.trainCloth, 2,0x1535d4);
		ItemIDs.jacket_driver_paintable.item = new ItemTCArmor(Traincraft.instance.armorCloth, Traincraft.trainCloth, 1,0x1469d9);
		ItemIDs.hat_driver_paintable.item = new ItemTCArmor(Traincraft.instance.armorCloth, Traincraft.trainCloth, 0,0x1469d9);
	
		ItemIDs.boots_suit_paintable.item = new ItemTCCompositeSuit(Traincraft.instance.armorCompositeSuit, Traincraft.trainCompositeSuit, 3,0x1535d4);
		ItemIDs.pants_suit_paintable.item = new ItemTCCompositeSuit(Traincraft.instance.armorCompositeSuit, Traincraft.trainCompositeSuit, 2,0x1535d4);
		ItemIDs.jacket_suit_paintable.item = new ItemTCCompositeSuit(Traincraft.instance.armorCompositeSuit, Traincraft.trainCompositeSuit, 1,0x1469d9);
		ItemIDs.helmet_suit_paintable.item = new ItemTCCompositeSuit(Traincraft.instance.armorCompositeSuit, Traincraft.trainCompositeSuit, 0,0x1469d9);
		
		ItemIDs.composite_wrench.item = new ItemWrench();
		
		/**Tracks*/
		ItemIDs.tcRailMediumTurn.item = new ItemTCRail(ItemTCRail.TrackTypes.MEDIUM_TURN);
		ItemIDs.tcRailLargeTurn.item = new ItemTCRail(ItemTCRail.TrackTypes.LARGE_TURN);
		ItemIDs.tcRailVeryLargeTurn.item = new ItemTCRail(ItemTCRail.TrackTypes.VERY_LARGE_TURN);
		ItemIDs.tcRailLongStraight.item = new ItemTCRail(ItemTCRail.TrackTypes.LONG_STRAIGHT);
		ItemIDs.tcRailMediumStraight.item = new ItemTCRail(ItemTCRail.TrackTypes.MEDIUM_STRAIGHT);
		ItemIDs.tcRailSmallStraight.item = new ItemTCRail(ItemTCRail.TrackTypes.SMALL_STRAIGHT);
		ItemIDs.tcRailMediumSwitch.item = new ItemTCRail(ItemTCRail.TrackTypes.MEDIUM_SWITCH);
		ItemIDs.tcRailLargeSwitch.item = new ItemTCRail(ItemTCRail.TrackTypes.LARGE_SWITCH);
		ItemIDs.tcRailMediumParallelSwitch.item = new ItemTCRail(ItemTCRail.TrackTypes.MEDIUM_PARALLEL_SWITCH);
		
		ItemIDs.tcRailTwoWaysCrossing.item = new ItemTCRail(ItemTCRail.TrackTypes.TWO_WAYS_CROSSING);
		ItemIDs.tcRailLargeSlopeWood.item = new ItemTCRail(ItemTCRail.TrackTypes.LARGE_SLOPE_WOOD);
		ItemIDs.tcRailLargeSlopeGravel.item = new ItemTCRail(ItemTCRail.TrackTypes.LARGE_SLOPE_GRAVEL);
		ItemIDs.tcRailLargeSlopeBallast.item = new ItemTCRail(ItemTCRail.TrackTypes.LARGE_SLOPE_BALLAST);
		
	}

	private static void nameItems() {
		for (ItemIDs items : ItemIDs.values()) {
			if (items.item != null) {
				items.item.setUnlocalizedName(Info.modID + ":" + items.name());
				items.item.setTextureName(Info.modID + ":" + items.iconName);
				GameRegistry.registerItem(items.item, items.name());
			}
		}
	}
}