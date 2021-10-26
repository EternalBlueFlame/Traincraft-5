/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.items;

import cpw.mods.fml.common.registry.GameRegistry;
import train.Traincraft;
import train.library.Info;
import train.library.ItemIDs;

public class TCItems {


	@Deprecated //need to use TiMGenericRegistry.registerItem(), this will also cover item special render so we can skip json icons.
	public static void init() {
		loadItems();
		registerItems();

		/* Clothes
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.overalls.item, 1),  " # ", "X$X", "X X", Character.valueOf('X'), new ItemStack(Items.dye, 1, 4), Character.valueOf('$'), Items.leather_leggings, Character.valueOf('#'), new ItemStack(Items.dye, 1, 1) );
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.jacket.item, 1),  "X X", "X$X", "X#X", Character.valueOf('X'), new ItemStack(Items.dye, 1, 14), Character.valueOf('$'), Items.leather_chestplate, Character.valueOf('#'), Items.string );
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.hat.item, 1),  " X ", "X$X", "#X#", Character.valueOf('X'), new ItemStack(Items.dye, 1, 4), Character.valueOf('$'), Items.leather_helmet, Character.valueOf('#'), Items.string );

		/* Driver Clothes
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.pants_driver_paintable.item, 1),  "XXX", "XLX", "X$X", Character.valueOf('L'), Items.leather_leggings,Character.valueOf('$'), new ItemStack(Items.dye, 1, 4), Character.valueOf('X'), Items.string);
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.jacket_driver_paintable.item, 1),  "X X", "XRX", "XPX", Character.valueOf('X'), new ItemStack(Items.dye, 1, 4), Character.valueOf('P'), Items.leather_chestplate,Character.valueOf('R'),  new ItemStack(Items.dye, 1, 1) );
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.hat_driver_paintable.item, 1), "#$#", "# #", Character.valueOf('$'), new ItemStack(Items.dye, 1, 4), Character.valueOf('#'), Items.string );

		/* Ticket Man Clothes
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.pants_ticketMan_paintable.item, 1),  "XXX", "XLX", "X$X", Character.valueOf('L'), Items.leather_leggings,Character.valueOf('$'), new ItemStack(Items.dye, 1, 8), Character.valueOf('X'), Items.string);
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.jacket_ticketMan_paintable.item, 1),  "X X", "XPX", "X#X", Character.valueOf('P'), Items.leather_chestplate, Character.valueOf('#'), new ItemStack(Items.dye, 1, 4), Character.valueOf('X'), Items.string);
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.hat_ticketMan_paintable.item, 1), "#$#", "# #", Character.valueOf('$'), new ItemStack(Items.dye, 1, 0), Character.valueOf('#'), Items.string );

		/* Composite Suit
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.boots_suit_paintable.item, 1), " D ","X X", "XFX", Character.valueOf('F'), Items.feather, Character.valueOf('D'), Items.diamond, Character.valueOf('X'), ItemIDs.reinforcedPlates.item);
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.pants_suit_paintable.item, 1),  "XDX", "X$X", "X X", Character.valueOf('$'), Items.fire_charge, Character.valueOf('X'), ItemIDs.reinforcedPlates.item,Character.valueOf('D'), Items.diamond);
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.jacket_suit_paintable.item, 1),  "X X", "XDX", "XAX", Character.valueOf('A'), Items.golden_apple, Character.valueOf('X'), ItemIDs.reinforcedPlates.item,Character.valueOf('D'), Blocks.diamond_block);
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.helmet_suit_paintable.item, 1), "#D#", "# #", Character.valueOf('D'), Blocks.diamond_block, Character.valueOf('#'), ItemIDs.reinforcedPlates.item );

		/* Trains parts

		for (ItemStack ironingot : iron) {
			TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.generator.item, 1), " ##", "E$$", " ##", Character.valueOf('#'), ItemIDs.copperWireFine.item, Character.valueOf('E'), ItemIDs.electronicCircuit.item, Character.valueOf('$'), ironingot);// generator
			TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.controls.item, 1), "#X#", "#E#", "$$$", Character.valueOf('#'), Blocks.lever, Character.valueOf('X'), Blocks.stone_button, Character.valueOf('$'), ironingot, Character.valueOf('E'), ItemIDs.electronicCircuit.item);// train controls
			TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.electmotor.item, 1), "I#I", "#E#", "I#I", Character.valueOf('#'), ItemIDs.copperWireFine.item, Character.valueOf('I'), ironingot, Character.valueOf('E'), ItemIDs.electronicCircuit.item);// Electric motor
			for (ItemStack bucketWater : waterbucket) {
				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.ironBoiler.item, 2), "###", "XXX", "###", Character.valueOf('#'), ironingot, Character.valueOf('X'), bucketWater.getItem());// iron Boiler
			}
			TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.ironFirebox.item, 2),  "###", "#X#", "###", Character.valueOf('#'), ironingot, Character.valueOf('X'), Items.flint_and_steel );// iron Firebox
			TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.ironChimney.item, 2),  "# #", "# #", "# #", Character.valueOf('#'), ironingot );

		}
		TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.dieselengine.item, 2), "###", "XXX", "CCC", Character.valueOf('#'), ItemIDs.piston.item, Character.valueOf('X'), ItemIDs.cylinder.item, Character.valueOf('C'), ItemIDs.camshaft.item);// diesel engine
		for (ItemStack dustStack : dustCoal) {
			TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.graphite.item, 2),  "###", "#X#", "###", Character.valueOf('#'), dustStack, Character.valueOf('X'), Items.clay_ball );// Graphite
		}

		if (!ConfigHandler.MAKE_MODPACKS_GREAT_AGAIN) {
			for (ItemStack c : coal) {
				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.coaldust.item, 4),
						"###", "   ", "   ", Character.valueOf('#'), c.getItem());
				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.coaldust.item, 4),
						"   ", "###", "   ", Character.valueOf('#'), c.getItem());
				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.coaldust.item, 4),
						"   ", "   ", "###", Character.valueOf('#'), c.getItem());
			}
		}

		//TrainCraftingManager.instance.addShapelessRecipe(new ItemStack(ItemIDs.coaldust.item, 4),  c.getItem(), c.getItem(), c.getItem(), c.getItem() );// coal dust

		//TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.signal.item, 2),  "#", "X", "X", Character.valueOf('X'), ItemIDs.steel.item, Character.valueOf('#'), rs );

		/* Zepplin parts and zeppelin item
		if (ConfigHandler.ENABLE_ZEPPELIN) {
			TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.balloon.item, 1),  "###", "# #", "###", Character.valueOf('#'), Blocks.wool );// Balloon
			TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.steamengine.item, 1),  "C  ", "BF ", Character.valueOf('C'), ItemIDs.steelchimney.item, Character.valueOf('B'), ItemIDs.boiler.item, Character.valueOf('F'), ItemIDs.firebox.item );// Small steam engine
			TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.airship.item, 1),  "B B", "SES", "POP", Character.valueOf('B'), ItemIDs.balloon.item, Character.valueOf('S'), Items.stick, Character.valueOf('E'), ItemIDs.steamengine.item, Character.valueOf('P'), ItemIDs.propeller.item, Character.valueOf('O'), Items.boat );
			TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.zeppelin.item, 1),  "BBB", "SES", "POP", Character.valueOf('B'), ItemIDs.balloon.item, Character.valueOf('S'), ItemIDs.propeller.item, Character.valueOf('E'), ItemIDs.controls.item, Character.valueOf('P'), ItemIDs.electmotor.item, Character.valueOf('O'), ItemIDs.seats.item );
		}

		for (ItemStack plankItem : planks) {
			for (ItemStack steelItem: steel) {

				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.reinforcedPlates.item, 1),  "RRR", "SSS", "CCC", Character.valueOf('R'), ItemIDs.reinforcedPlastic.item, Character.valueOf('S'), steelItem, Character.valueOf('C'), Items.clay_ball);

				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.composite_wrench.item, 1), "S S", " R "," R ", Character.valueOf('R'), ItemIDs.reinforcedPlastic.item, Character.valueOf('S'),steelItem );
				for (ItemStack rs :redstone) {
					TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.transformer.item, 1), "# #", "XEX", "###", Character.valueOf('#'), steelItem, Character.valueOf('E'), ItemIDs.electronicCircuit.item, Character.valueOf('X'), rs.getItem());// transformer
				}

				for (ItemStack bucketWater :waterbucket) {
					TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.boiler.item, 2), "###", "XXX", "###", Character.valueOf('#'), steelItem, Character.valueOf('X'), bucketWater.getItem());// Boiler
				}
				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.firebox.item, 2),  "###", "#X#", "###", Character.valueOf('#'), steelItem, Character.valueOf('X'), Items.flint_and_steel );// Firebox
				for (ItemStack ironingot : iron) {
					TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.stake.item, 1),  "   ", "IFI", "   ", Character.valueOf('I'), steelItem, Character.valueOf('F'), ironingot );
					TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.stake.item, 1),  "IFI", "   ", "   ", Character.valueOf('I'), steelItem, Character.valueOf('F'), ironingot );
					TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.stake.item, 1),  "   ", "   ", "IFI", Character.valueOf('I'), steelItem, Character.valueOf('F'), ironingot );

					TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.propeller.item, 2),  " # ", "#X#", " # ", Character.valueOf('#'), plankItem, Character.valueOf('X'), ironingot );// Propeller
				}
				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.steelchimney.item, 2),  "# #", "# #", "# #", Character.valueOf('#'), steelItem );// Bogie
				TrainCraftingManager.instance.addRecipe(new ItemStack(Items.flint_and_steel, 2),  "* ", " #", Character.valueOf('*'), steelItem, Character.valueOf('#'), Items.flint );


				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.transmition.item, 1),  " # ", "#X#", " # ", Character.valueOf('#'), steelItem, Character.valueOf('X'), ItemIDs.diesel.item );// transmition
				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.piston.item, 3),  " # ", " X ", Character.valueOf('#'), steelItem, Character.valueOf('X'), Items.stick );// piston
				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.camshaft.item, 3),  "###", "   ", "   ", Character.valueOf('#'), steelItem );// camshaft
				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.camshaft.item, 3),  "   ", "###", "   ", Character.valueOf('#'), steelItem );// camshaft
				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.camshaft.item, 3),  "   ", "   ", "###", Character.valueOf('#'), steelItem );// camshaft
				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.cylinder.item, 3),  "# #", "# #", "###", Character.valueOf('#'), steelItem );// cylinder

			}

			for (ItemStack ironingot : iron) {
				TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.seats.item, 1), "#  ", "## ", "XXX", Character.valueOf('#'), plankItem, Character.valueOf('X'), ironingot);// transformer
				TrainCraftingManager.instance.addRecipe(new ItemStack(BlockIDs.stopper.block, 1), "WWW", "I I", "RRR", Character.valueOf('W'), plankItem, Character.valueOf('R'), Blocks.rail, Character.valueOf('I'), ironingot);// stopper
				TrainCraftingManager.instance.addRecipe(new ItemStack(BlockIDs.switchStand.block, 1), " W ", " I ", " R ", Character.valueOf('W'), Blocks.lever, Character.valueOf('R'), Items.stick, Character.valueOf('I'), ironingot);//switchstand
			}
		}
		 */




	}

	private static void loadItems() {
		for (ItemIDs items : ItemIDs.values()) {
			if (items.className != null) {
				if (items.className.equals("ItemTrain")) {
					items.item = new ItemPart(items.iconName);
				}
				else if (items.className.equals("ItemContainer")) {
					items.item = new ItemContainer(items.iconName);
				}
			}
		}
		//ItemIDs.signal.item = new ItemSignal(ItemIDs.signal.itemID, BlockIDs.activeSignal.block).setIconIndex(ItemIDs.signal.iconIndex);
		//ItemIDs.chunkLoaderActivator.item = new ItemChunkLoaderActivator();
		
		//ItemIDs.stake.item = new ItemStacked(200);
		ItemIDs.airship.item = new ItemZeppelins(0);
		ItemIDs.zeppelin.item = new ItemZeppelins(1);
		ItemIDs.overalls.item = new ItemTCArmor(ItemIDs.overalls.iconName, Traincraft.instance.armor, Traincraft.trainArmor, 2,0);
		ItemIDs.jacket.item = new ItemTCArmor(ItemIDs.jacket.iconName, Traincraft.instance.armor, Traincraft.trainArmor, 1,0);
		ItemIDs.hat.item = new ItemTCArmor(ItemIDs.hat.iconName, Traincraft.instance.armor, Traincraft.trainArmor, 0,0);
		
		/**Paintable armors:*/
		ItemIDs.pants_ticketMan_paintable.item = new ItemTCArmor(ItemIDs.pants_ticketMan_paintable.iconName, Traincraft.instance.armorCloth, Traincraft.trainCloth, 2,0xdedede);
		ItemIDs.jacket_ticketMan_paintable.item = new ItemTCArmor(ItemIDs.jacket_ticketMan_paintable.iconName, Traincraft.instance.armorCloth, Traincraft.trainCloth, 1,0x002cdb);
		ItemIDs.hat_ticketMan_paintable.item = new ItemTCArmor(ItemIDs.hat_ticketMan_paintable.iconName, Traincraft.instance.armorCloth, Traincraft.trainCloth, 0,0x9fafb5);
		
		ItemIDs.pants_driver_paintable.item = new ItemTCArmor(ItemIDs.pants_driver_paintable.iconName, Traincraft.instance.armorCloth, Traincraft.trainCloth, 2,0x1535d4);
		ItemIDs.jacket_driver_paintable.item = new ItemTCArmor(ItemIDs.jacket_driver_paintable.iconName, Traincraft.instance.armorCloth, Traincraft.trainCloth, 1,0x1469d9);
		ItemIDs.hat_driver_paintable.item = new ItemTCArmor(ItemIDs.hat_driver_paintable.iconName, Traincraft.instance.armorCloth, Traincraft.trainCloth, 0,0x1469d9);
	
		ItemIDs.boots_suit_paintable.item = new ItemTCCompositeSuit(ItemIDs.boots_suit_paintable.iconName, Traincraft.instance.armorCompositeSuit, Traincraft.trainCompositeSuit, 3,0x1535d4);
		ItemIDs.pants_suit_paintable.item = new ItemTCCompositeSuit(ItemIDs.pants_suit_paintable.iconName, Traincraft.instance.armorCompositeSuit, Traincraft.trainCompositeSuit, 2,0x1535d4);
		ItemIDs.jacket_suit_paintable.item = new ItemTCCompositeSuit(ItemIDs.jacket_suit_paintable.iconName, Traincraft.instance.armorCompositeSuit, Traincraft.trainCompositeSuit, 1,0x1469d9);
		ItemIDs.helmet_suit_paintable.item = new ItemTCCompositeSuit(ItemIDs.helmet_suit_paintable.iconName, Traincraft.instance.armorCompositeSuit, Traincraft.trainCompositeSuit, 0,0x1469d9);
		
		ItemIDs.composite_wrench.item = new ItemWrench();
	}
	
	private static void registerItems() {
		for (ItemIDs items : ItemIDs.values()) {
			if (items.item != null) {
				items.item.setUnlocalizedName(Info.modID + ":" + items.name());
				GameRegistry.registerItem(items.item, items.name());
			}
		}
	}
}
