//package train.blocks.bench;
//
//import net.minecraft.init.Blocks;
//import net.minecraft.init.Items;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.fluids.FluidContainerRegistry;
//import net.minecraftforge.fluids.FluidRegistry;
//import net.minecraftforge.oredict.OreDictionary;
//import train.core.managers.TierRecipeManager;
//import train.library.ItemIDs;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AssemblyTableRecipes {
//
//
//
//	public static void recipes() {
//		//initialize these in the function because this only ever gets called on startup, gives a lot of space for GC to do its job.
//		ArrayList<ItemStack> ingotIron	= OreDictionary.getOres("ingotIron");
//		ArrayList<ItemStack> s1 = OreDictionary.getOres("plankWood");
//		ArrayList<ItemStack> s2 = OreDictionary.getOres("logWood");
//		ArrayList<ItemStack> ingotSteel = OreDictionary.getOres("ingotSteel");
//		ArrayList<ItemStack> dyeRed = OreDictionary.getOres("dyeRed"); //1
//		ArrayList<ItemStack> dyeYellow = OreDictionary.getOres("dyeYellow"); //11
//		ArrayList<ItemStack> dyeOrange = OreDictionary.getOres("dyeOrange");//14
//		ArrayList<ItemStack> dyeGreen = OreDictionary.getOres("dyeGreen"); //2
//		ArrayList<ItemStack> dyeBlue = OreDictionary.getOres("dyeBlue"); //4
//		ArrayList<ItemStack> dyeBrown = OreDictionary.getOres("dyeBrown"); //3
//		ArrayList<ItemStack> dyeGray = OreDictionary.getOres("dyeGray"); //3
//		ArrayList<ItemStack> dyeLightGray = OreDictionary.getOres("dyeLightGray"); //7
//		ArrayList<ItemStack> dyeBlack = OreDictionary.getOres("dyeBlack");
//		ArrayList<ItemStack> dyeWhite = OreDictionary.getOres("dyeWhite");
//		List<ItemStack> coal = new ArrayList<ItemStack>();
//		coal.add(new ItemStack(Items.COAL));
//		coal.addAll(OreDictionary.getOres("coal"));
//		ArrayList<ItemStack> redstone = OreDictionary.getOres("dustRedstone");
//		ArrayList<ItemStack> waterbucket = waterContainers();
//
//
//		ITierCraftingManager cm = TierRecipeManager.getInstance();
//
//		for (ItemStack k :ingotSteel) {
//			Item itemSteel = k.getItem();
//			int itemDamageSteel = k.getItemDamage();
//
//
//			//=================== TIER I ===================//
//
//			cm.addRecipe(1, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 1), new ItemStack(Items.STICK, 1), new ItemStack(ItemIDs.ironChimney.item, 1), null, new ItemStack(ItemIDs.ironBoiler.item, 1), new ItemStack(ItemIDs.ironFirebox.item, 1), null, null, new ItemStack(ItemIDs.minecartLocoCherepanov.item, 1), 1);
//			cm.addRecipe(1, new ItemStack(Blocks.CRAFTING_TABLE, 1), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 1), new ItemStack(Items.STICK, 2), null, new ItemStack(TiMItems.cabinWood, 1), null, null, new ItemStack(Blocks.FURNACE, 1), null, new ItemStack(ItemIDs.minecartWork.item, 1), 1);
//			for (ItemStack c : coal) {
//				cm.addRecipe(1, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 1), new ItemStack(Items.STICK, 2), null, null, null, null, new ItemStack(c.getItem(), 1), null, new ItemStack(ItemIDs.minecartTender.item, 1), 1);
//			}
//			cm.addRecipe(1, new ItemStack(Items.IRON_INGOT, 6), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 2), new ItemStack(Items.STICK, 2), null, null, null, null, new ItemStack(Items.LAVA_BUCKET, 1), null, new ItemStack(ItemIDs.minecartWatertransp.item, 1), 1);
//			for (ItemStack iron : ingotIron) {
//				for (ItemStack dye : dyeGreen) {
//					for (ItemStack bucketWater : waterbucket) {
//						cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinIron, 1), new ItemStack(ItemIDs.ironBoiler.item, 2), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(bucketWater.getItem(), 1), dye, new ItemStack(ItemIDs.minecartLocoBR80_DB.item, 1), 1);
//					}
//				}
//				for (ItemStack c : coal) {
//					cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinIron, 1), new ItemStack(ItemIDs.ironBoiler.item, 2), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(c.getItem(), 1), null, new ItemStack(ItemIDs.minecartLocoSteamShay.item, 1), 1);
//					cm.addRecipe(1, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameIron, 1), new ItemStack(iron.getItem(), 2), null, null, null, null, new ItemStack(c.getItem(), 1), null, new ItemStack(ItemIDs.minecartSlateWagon.item, 1), 1);
//
//					cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinIron, 1), new ItemStack(ItemIDs.ironBoiler.item, 1), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(c.getItem(), 1), null, new ItemStack(ItemIDs.minecartLocoAlcoSC4.item, 1), 1);
//					cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinIron, 1), new ItemStack(ItemIDs.ironBoiler.item, 2), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(c.getItem(), 1), null, new ItemStack(ItemIDs.minecartLocoC41.item, 1), 1);
//					cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 6), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinIron, 1), new ItemStack(ItemIDs.ironBoiler.item, 2), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(c.getItem(), 1), null, new ItemStack(ItemIDs.minecartLocoC41_080.item, 1), 1);
//					cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinIron, 1), new ItemStack(ItemIDs.ironBoiler.item, 2), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(c.getItem(), 1), null, new ItemStack(ItemIDs.minecartLocoC41T.item, 1), 1);
//					cm.addRecipe(1, new ItemStack(iron.getItem(), 2), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), null, null, null, null, new ItemStack(c.getItem(), 2), null, new ItemStack(ItemIDs.minecartTenderC41.item, 1), 1);
//
//					cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinIron, 1), new ItemStack(ItemIDs.ironBoiler.item, 2), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(c.getItem(), 1), null, new ItemStack(ItemIDs.minecartLocoSouthern1102.item, 1), 1);
//			for (ItemStack dye : dyeGray) {
//				cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameSteel, 2),
//						new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1),
//						new ItemStack(ItemIDs.ironBoiler.item, 1), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(c.getItem(), 1), dye,
//						new ItemStack(ItemIDs.minecartLocoUSATCUS.item, 1), 1);
//				cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameSteel, 2),
//						new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1),
//						new ItemStack(ItemIDs.ironBoiler.item, 1), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(c.getItem(), 1), dye,
//						new ItemStack(ItemIDs.minecartLocoUSATCUK.item, 1), 1);
//			}
//					cm.addRecipe(1, new ItemStack(iron.getItem(), 2), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), null, null, null, null, new ItemStack(c.getItem(), 2), null, new ItemStack(ItemIDs.minecartTenderSouthern1102.item, 1), 1);
//				}
//				cm.addRecipe(1, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameIron, 1), new ItemStack(iron.getItem(), 2), null, null, null, null, new ItemStack(Items.snowball, 9), null, new ItemStack(ItemIDs.minecartIceWagon.item, 1), 1);
//			}
//			for (ItemStack dye : dyeRed) {
//				for (ItemStack c : coal) {
//					cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameWood, 1), new ItemStack(Items.STICK, 2), null, null, null, null, new ItemStack(c.getItem(), 1), dye, new ItemStack(ItemIDs.minecartSteamRedTender.item, 1), 1);
//					for (ItemStack iron : ingotIron) {
//						cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinIron, 1), new ItemStack(ItemIDs.ironBoiler.item, 2), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(c.getItem(), 1), dye, new ItemStack(ItemIDs.minecartLocoForneyRed.item, 1), 1);
//					}
//				}
//				cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameWood, 2), new ItemStack(Items.STICK, 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinWood, 1), new ItemStack(ItemIDs.ironBoiler.item, 1), new ItemStack(ItemIDs.ironFirebox.item, 1), null, dye, new ItemStack(ItemIDs.minecartPower.item, 1), 1);
//			}
//			cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameWood, 4), null, new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinWood, 1), new ItemStack(ItemIDs.ironBoiler.item, 1), new ItemStack(ItemIDs.ironFirebox.item, 1), null, null, new ItemStack(ItemIDs.minecartLocoSnowPlow.item, 1), 1);
//			for (ItemStack dye : dyeBlue) {
//				cm.addRecipe(1, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 2), new ItemStack(Items.STICK, 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinWood, 1), new ItemStack(ItemIDs.ironBoiler.item, 1), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(Blocks.TORCH, 1), dye, new ItemStack(ItemIDs.minecartLoco3.item, 1), 1);
//			}
//			cm.addRecipe(1, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 2), null, null, new ItemStack(TiMItems.cabinIron, 1), new ItemStack(ItemIDs.ironBoiler.item, 1), new ItemStack(ItemIDs.ironFirebox.item, 1), null, null, new ItemStack(ItemIDs.minecartLocoLSSP7.item, 1), 1);
//			//cm.addRecipe(1, new ItemStack(Block.planks, 4), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameWood, 1), new ItemStack(ingotIron.getItem(), 1), null, new ItemStack(TiMItems.cabinWood, 1), null, new ItemStack(TiMItems.smallElectricEngine, 2), new ItemStack(Item.redstone, 4), null, new ItemStack(ItemIDs.minecartTramWood.item, 1), 1);
//
//			for (ItemStack i : s1) {
//				for (ItemStack j : s2) {
//					cm.addRecipe(1, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 2), new ItemStack(Items.STICK, 2), null, null, null, null, new ItemStack(j.getItem(), 1, OreDictionary.WILDCARD_VALUE), null, new ItemStack(ItemIDs.minecartWood.item, 1), 1);
//				}
//				for (ItemStack iron : ingotIron) {
//					for (ItemStack dye : dyeGreen) {
//						cm.addRecipe(1, new ItemStack(i.getItem(), 8, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameWood, 2), new ItemStack(iron.getItem(), 2), new ItemStack(ItemIDs.ironChimney.item, 1), null, new ItemStack(ItemIDs.ironBoiler.item, 1), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(Items.GOLD_INGOT, 2), dye, new ItemStack(ItemIDs.minecartLocoSteamAdler.item, 1), 1);
//						for (ItemStack c : coal) {
//							cm.addRecipe(1, new ItemStack(iron.getItem(), 2), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 1), new ItemStack(iron.getItem(), 1), null, null, null, null, new ItemStack(c.getItem(), 1), dye, new ItemStack(ItemIDs.minecartTenderAdler.item, 1), 1);
//						}
//					}
//					for (ItemStack dye : dyeYellow) {
//						cm.addRecipe(1, new ItemStack(iron.getItem(), 1), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 2), new ItemStack(iron.getItem(), 1), null, new ItemStack(TiMItems.cabinWood, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), dye, new ItemStack(ItemIDs.minecartPassengerAdler.item, 1), 1);
//					}
//				}
//
//				cm.addRecipe(1, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 2), new ItemStack(Items.STICK, 2), null, new ItemStack(TiMItems.cabinWood, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), null, new ItemStack(ItemIDs.minecartPassenger2.item, 1), 1);
//				cm.addRecipe(1, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 2), new ItemStack(Items.STICK, 2), null, new ItemStack(TiMItems.cabinWood, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), null, new ItemStack(ItemIDs.minecartCaboose.item, 1), 1);
//				cm.addRecipe(1, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 2), new ItemStack(Items.STICK, 2), null, null, null, null, new ItemStack(Blocks.CHEST, 1), null, new ItemStack(ItemIDs.minecartFreightCartSmall.item, 1), 1);
//				cm.addRecipe(1, new ItemStack(i.getItem(), 3, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 2), new ItemStack(Items.STICK, 2), null, null, null, null, null, null, new ItemStack(ItemIDs.minecartFlatCart.item, 1), 1);
//				for (ItemStack iron : ingotIron) {
//					cm.addRecipe(1, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), null, null, null, null, new ItemStack(Blocks.CHEST, 2), null, new ItemStack(ItemIDs.minecartChest.item, 1), 1);
//					cm.addRecipe(1, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), null, null, null, null, new ItemStack(Blocks.CHEST, 2), null, new ItemStack(ItemIDs.minecartFreightCart2.item, 1), 1);
//					cm.addRecipe(1, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), null, null, null, null, new ItemStack(Blocks.HOPPER, 2), null, new ItemStack(ItemIDs.minecartFreightCartUS.item, 1), 1);
//					cm.addRecipe(1, new ItemStack(i.getItem(), 4, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 4), new ItemStack(iron.getItem(), 2), null, null, null, null, new ItemStack(Blocks.CHEST, 1), null, new ItemStack(ItemIDs.minecartFreightClosed.item, 1), 1);
//					cm.addRecipe(1, new ItemStack(i.getItem(), 2, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), null, null, null, null, new ItemStack(Blocks.HOPPER, 2), null, new ItemStack(ItemIDs.minecartFreightOpen2.item, 1), 1);
//					cm.addRecipe(1, new ItemStack(i.getItem(), 2, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), null, new ItemStack(TiMItems.cabinWood, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), null, new ItemStack(ItemIDs.minecartCaboose3.item, 1), 1);
//					cm.addRecipe(1, new ItemStack(i.getItem(), 2, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 2), new ItemStack(iron.getItem(), 2), null, new ItemStack(TiMItems.cabinIron, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), null, new ItemStack(ItemIDs.minecartPassenger7.item, 1), 1);
//					for (ItemStack rs : redstone) {
//						cm.addRecipe(1, new ItemStack(i.getItem(), 4, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameWood, 1), new ItemStack(iron.getItem(), 1), null, new ItemStack(TiMItems.cabinWood, 1), new ItemStack(ItemIDs.transformer.item, 1), new ItemStack(TiMItems.smallElectricEngine, 2), new ItemStack(rs.getItem(), 4), null, new ItemStack(ItemIDs.minecartTramWood.item, 1), 1);
//					}
//					for (ItemStack dye : dyeRed) {
//						cm.addRecipe(1, new ItemStack(i.getItem(), 3, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 2), new ItemStack(iron.getItem(), 1), null, null, null, null, new ItemStack(Blocks.CRAFTING_TABLE, 1), dye, new ItemStack(ItemIDs.minecartCabooseLogging.item, 1), 1);
//					}
//				}
//				cm.addRecipe(1, new ItemStack(Blocks.CRAFTING_TABLE, 1), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameWood, 1), new ItemStack(i.getItem(), 8, OreDictionary.WILDCARD_VALUE), null, new ItemStack(TiMItems.cabinWood, 1), null, null, new ItemStack(Blocks.FURNACE, 1), null, new ItemStack(ItemIDs.minecartGWRBrakeVan.item, 1), 1);
//				cm.addRecipe(1, new ItemStack(i.getItem(), 2, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameWood, 2), null, new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinWood, 1), new ItemStack(TiMItems.boilerSteel, 1), new ItemStack(TiMItems.fireboxSteel, 1), null, new ItemStack(i.getItem(), 4, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.minecartLocoSteamVBShay.item, 1), 1);
//				cm.addRecipe(1, new ItemStack(i.getItem(), 2, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameWood, 1), null, new ItemStack(ItemIDs.ironChimney.item, 1), null, new ItemStack(TiMItems.boilerSteel, 1), new ItemStack(TiMItems.fireboxSteel, 1), null, null, new ItemStack(ItemIDs.minecartLocoSteamAlice.item, 1), 1);
//				cm.addRecipe(1, new ItemStack(i.getItem(), 4, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameWood, 1), null, new ItemStack(ItemIDs.ironChimney.item, 1), null, new ItemStack(TiMItems.boilerSteel, 1), new ItemStack(TiMItems.fireboxSteel, 1), null, null, new ItemStack(ItemIDs.minecartLocoSteam040vb.item, 1), 1);
//
//				cm.addRecipe(1, new ItemStack(i.getItem(), 2, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameWood, 2), null, null, null, null, null, new ItemStack(Items.PAINTING, 2), null, new ItemStack(ItemIDs.minecartPropagandaUs.item, 1), 1);
//				cm.addRecipe(1, new ItemStack(i.getItem(), 2, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameWood, 2), null, null, null, null, null, new ItemStack(Items.PAINTING, 2), null, new ItemStack(ItemIDs.minecartPropagandaUSSR.item, 1), 1);
//				cm.addRecipe(1, new ItemStack(i.getItem(), 2, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameWood, 2), null, null, null, null, null, new ItemStack(Items.PAINTING, 2), null, new ItemStack(ItemIDs.minecartPropagandaBritish.item, 1), 1);
//				cm.addRecipe(1, new ItemStack(i.getItem(), 2, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameWood, 2), null, null, null, null, null, new ItemStack(Items.PAINTING, 2), null, new ItemStack(ItemIDs.minecartPropagandaJapan.item, 1), 1);
//			}
//			cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameIron, 1), null, new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinWood, 1), new ItemStack(TiMItems.boilerSteel, 1), new ItemStack(TiMItems.fireboxSteel, 1), null, null, new ItemStack(ItemIDs.minecartLocoSteamGLYN.item, 1), 1);
//			cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 4), new ItemStack(TiMItems.frameWood, 1), null, new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinIron, 1), new ItemStack(TiMItems.boilerSteel, 1), new ItemStack(TiMItems.fireboxSteel, 1), null, null, new ItemStack(ItemIDs.minecartLocoSteam262T.item, 1), 1);
//			cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameIron, 1), null, new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(TiMItems.cabinWood, 1), new ItemStack(TiMItems.boilerSteel, 1), new ItemStack(TiMItems.fireboxSteel, 1), null, null, new ItemStack(ItemIDs.minecartLocoSteamClimax.item, 1), 1);
//
//			cm.addRecipe(1, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 1), null, null, null, null, null, null, null, new ItemStack(ItemIDs.minecartPassengerBamboo.item, 1), 1);
//			cm.addRecipe(1, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 1), null, null, null, null, null, new ItemStack(Blocks.CHEST, 1), null, new ItemStack(ItemIDs.minecartFreightBamboo.item, 1), 1);
//			cm.addRecipe(1, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 1), null, null, null, null, new ItemStack(TiMItems.mediumDieselEngine, 1), null, null, new ItemStack(ItemIDs.minecartTrainBamboo.item, 1), 1);
//
//
//			for (ItemStack dye : dyeYellow) {
//				cm.addRecipe(1, new ItemStack(Blocks.CHEST, 4), new ItemStack(TiMItems.wheelIron, 4), new ItemStack(TiMItems.frameWood, 3), null, null, new ItemStack(TiMItems.cabinWood, 3), null, null, null, dye, new ItemStack(ItemIDs.minecartDenverRioGrandeBaggage.item, 1), 1);
//				cm.addRecipe(1, new ItemStack(Blocks.CHEST, 4), new ItemStack(TiMItems.wheelIron, 4), new ItemStack(TiMItems.frameWood, 3), null, null, new ItemStack(TiMItems.cabinWood, 3), null, null, new ItemStack(TiMItems.seatsWooden, 1), dye, new ItemStack(ItemIDs.minecartDenverRioGrandeCombo.item, 1), 1);
//				cm.addRecipe(1, null, new ItemStack(TiMItems.wheelIron, 4), new ItemStack(TiMItems.frameWood, 3), null, null, new ItemStack(TiMItems.cabinWood, 3), null, null, new ItemStack(TiMItems.seatsWooden, 1), dye, new ItemStack(ItemIDs.minecartDenverRioGrandePassenger.item, 1), 1);
//
//				for (ItemStack iron : ingotIron) {
//					cm.addRecipe(1, new ItemStack(iron.getItem(), 2), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 1), new ItemStack(iron.getItem(), 2), null, null, null, null, new ItemStack(Blocks.CHEST, 1), dye, new ItemStack(ItemIDs.minecartMineTrain.item, 1), 1);
//					for (ItemStack rs : redstone) {
//						cm.addRecipe(1, new ItemStack(iron.getItem(), 2), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameIron, 1), new ItemStack(iron.getItem(), 1), null, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(ItemIDs.transformer.item, 1), new ItemStack(TiMItems.smallElectricEngine, 2), new ItemStack(rs.getItem(), 1), dye, new ItemStack(ItemIDs.minecartLocoMineTrain.item, 1), 1);
//					}
//				}
//
//
//				//=================== TIER II ==================//
//				for (ItemStack bucketWater : waterbucket) {
//					cm.addRecipe(2, new ItemStack(itemSteel, 6, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(bucketWater.getItem(), 1), dye, new ItemStack(ItemIDs.minecartTankWagon.item, 1), 1);
//				}
//			}
//
//			for (ItemStack dye : dyeGreen) {
//				cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 3),
//						new ItemStack(itemSteel, 1, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 2),
//						new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 2),
//						new ItemStack(TiMItems.fireboxSteel, 2), null, dye,
//						new ItemStack (ItemIDs.minecartLocoHallClass.item, 1), 1);
//				cm.addRecipe( 2, null,new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 3),
//						new ItemStack(itemSteel,2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 2),
//						new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 3),
//						new ItemStack(TiMItems.fireboxSteel, 2), null,dye,
//						new ItemStack(ItemIDs.minecartLocoKingClass.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.smallElectricEngine, 2), new ItemStack(TiMItems.mediumDieselEngine, 2), new ItemStack(TiMItems.generator, 2), dye, new ItemStack(ItemIDs.minecartChmE3.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Items.WHEAT, 3), dye, new ItemStack(ItemIDs.minecartGrain.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, new ItemStack(TiMItems.cabinSteel, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), dye, new ItemStack(ItemIDs.minecartPassenger9_2class_DB.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, new ItemStack(TiMItems.cabinSteel, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), dye, new ItemStack(ItemIDs.minecartPassenger5.item, 1), 1);
//				for (ItemStack bucketWater : waterbucket) {
//					cm.addRecipe(2, new ItemStack(itemSteel, 4, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 3), new ItemStack(itemSteel, 4, itemDamageSteel), null, null, null, null, new ItemStack(bucketWater.getItem(), 1), dye, new ItemStack(ItemIDs.minecartTankWagonThreeDome.item, 1), 1);
//				}
//				cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 3), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 2), new ItemStack(TiMItems.fireboxSteel, 1), null, dye, new ItemStack(ItemIDs.minecartLocoSteamPannier.item, 1), 1);
//			}
//			for (ItemStack dye : dyeBlue) {
//				cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(TiMItems.wheelWood, 4), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(ItemIDs.transmition.item, 1), new ItemStack(TiMItems.mediumDieselEngine, 3), null, dye, new ItemStack(ItemIDs.minecartShunter.item, 1), 1);
//				cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 2), new ItemStack(TiMItems.fireboxSteel, 1), null, dye, new ItemStack(ItemIDs.minecartLocomogulBlue.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood, 3),
//						new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel),
//						new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1),
//						new ItemStack(TiMItems.smallElectricEngine, 4), new ItemStack(TiMItems.mediumDieselEngine, 4),
//						new ItemStack(TiMItems.generator, 2), dye,
//						new ItemStack(ItemIDs.minecartLocoEWSClass66.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), new ItemStack(itemSteel, 2, itemDamageSteel), null, new ItemStack(TiMItems.cabinSteel, 2), new ItemStack(TiMItems.smallElectricEngine, 3), new ItemStack(TiMItems.mediumDieselEngine, 3), new ItemStack(TiMItems.generator, 2), dye, new ItemStack(ItemIDs.minecartLocoCD151.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood, 3),
//						new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel),
//						new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1),
//						new ItemStack(TiMItems.smallElectricEngine, 4), new ItemStack(TiMItems.mediumDieselEngine, 4),
//						new ItemStack(TiMItems.generator, 3), dye,
//						new ItemStack(ItemIDs.minecartLocoDeltic.item, 1), 1);
//			}
//
//			cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(TiMItems.wheelWood, 4), new ItemStack(TiMItems.frameSteel, 3), null, null, new ItemStack(TiMItems.cabinSteel, 2), new ItemStack(ItemIDs.transmition.item, 1), new ItemStack(TiMItems.mediumDieselEngine, 2), null, null, new ItemStack(ItemIDs.minecartLoco44TonSwitcher.item, 1), 1);
//			cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(TiMItems.wheelWood, 4), new ItemStack(TiMItems.frameSteel, 2), null, null, new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(ItemIDs.transmition.item, 1), new ItemStack(TiMItems.mediumDieselEngine, 1), null, null, new ItemStack(ItemIDs.minecartLocoWLs40.item, 1), 1);
//
//			for (ItemStack dye : dyeRed) {
//				cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.smallElectricEngine, 4), new ItemStack(TiMItems.mediumDieselEngine, 4), new ItemStack(TiMItems.generator, 2), dye, new ItemStack(ItemIDs.minecartCD742.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.smallElectricEngine, 4), new ItemStack(TiMItems.mediumDieselEngine, 4), new ItemStack(TiMItems.generator, 3), dye, new ItemStack(ItemIDs.minecartGP7Red.item, 1), 1);
//				for (ItemStack c : coal) {
//					cm.addRecipe(2, new ItemStack(itemSteel, 4, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(c.getItem(), 1), dye, new ItemStack(ItemIDs.minecartTenderBR01_DB.item, 1), 1);
//				}
//				cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 3),
//						new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 2),
//						new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 3),
//						new ItemStack(TiMItems.fireboxSteel, 2), null, dye,
//						new ItemStack(ItemIDs.minecartLocoCoranationClass.item, 1), 1);
//			}
//            for(ItemStack dye : dyeGreen){
//                cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 3),
//                        new ItemStack(itemSteel, 1, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 2),
//                        new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 2),
//                        new ItemStack(TiMItems.fireboxSteel, 2), null, dye,
//                        new ItemStack (ItemIDs.minecartLocoHallClass.item, 1), 1);
//            }
//            for (ItemStack dye : dyeBlack) {
//				cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 4), new ItemStack(TiMItems.frameSteel, 4),
//						new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 2),
//						new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 3),
//						new ItemStack(TiMItems.fireboxSteel, 3), null, dye,
//						new ItemStack(ItemIDs.minecartLocoBerk1225.item, 1), 1);
//				cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 4), new ItemStack(TiMItems.frameSteel, 4),
//						new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 2),
//						new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 3),
//						new ItemStack(TiMItems.fireboxSteel, 3), null, dye,
//						new ItemStack(ItemIDs.minecartLocoBerk765.item, 1), 1);
//				cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 4), new ItemStack(TiMItems.frameSteel, 4),
//						new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 2),
//						new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 3),
//						new ItemStack(TiMItems.fireboxSteel, 3), null, dye,
//						new ItemStack(ItemIDs.minecartLocoFowler.item, 1), 1);
//					for (ItemStack c : coal) {
//						cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 4), new ItemStack(TiMItems.frameSteel, 4),
//								new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null,
//								new ItemStack(c.getItem(), 2), dye,
//								new ItemStack(ItemIDs.minecarttenderBerk1225.item, 1), 1);
//						cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 3),
//								new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null,
//								new ItemStack(c.getItem(), 2), dye,
//								new ItemStack(ItemIDs.minecartFowler4FTender.item, 1), 1);
//					}
//			}
//			cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 2), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.smallElectricEngine, 4), new ItemStack(TiMItems.mediumDieselEngine, 4), new ItemStack(TiMItems.generator, 3), null, new ItemStack(ItemIDs.minecartMILW_H1044.item, 1), 1);
//			cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 2), new ItemStack(TiMItems.fireboxSteel, 1), null, null, new ItemStack(ItemIDs.minecartLocoBR01_DB.item, 1), 1);
//			cm.addRecipe(2, new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 5), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 3), new ItemStack(TiMItems.fireboxSteel, 1), null, null, new ItemStack(ItemIDs.minecartLocoC62Class.item, 1), 1);
//			cm.addRecipe(2, new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 5), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 2), new ItemStack(TiMItems.fireboxSteel, 1), null, null, new ItemStack(ItemIDs.minecartLocoD51Short.item, 1), 1);
//			cm.addRecipe(2, new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 5), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 2), new ItemStack(TiMItems.fireboxSteel, 1), null, null, new ItemStack(ItemIDs.minecartLocoD51Long.item, 1), 1);
//			for (ItemStack c: coal) {
//				cm.addRecipe(2, new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(c.getItem(), 1), null, new ItemStack(ItemIDs.minecartTenderD51.item, 1), 1);
//			}
//			//cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 4), new ItemStack(itemSteel, 2), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 4), new ItemStack(TiMItems.fireboxSteel, 2), null, null, new ItemStack(ItemIDs.minecartLoco4.item, 1), 1);
//			//cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 3), new ItemStack(itemSteel, 2), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 3), new ItemStack(TiMItems.fireboxSteel, 2), null, null, new ItemStack(ItemIDs.minecartHeavySteam.item, 1), 1);//TODO put it back once Heavy Steam is back
//			cm.addRecipe(2, new ItemStack(itemSteel, 6, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 3), new ItemStack(itemSteel, 1, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), null, new ItemStack(TiMItems.boilerSteel, 1), new ItemStack(TiMItems.fireboxSteel, 1), new ItemStack(Blocks.RAIL, 16), null, new ItemStack(ItemIDs.minecartBuilder.item, 1), 1);
//			cm.addRecipe(2, new ItemStack(itemSteel, 6, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, null, null, new ItemStack(ItemIDs.minecartFlatCartSU.item, 1), 1);
//			for (ItemStack dye : dyeGray) {
//				for (ItemStack bucketWater : waterbucket) {
//					cm.addRecipe(2, new ItemStack(itemSteel, 6, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(bucketWater.getItem(), 1), dye, new ItemStack(ItemIDs.minecartTankWagon2.item, 1), 1);
//				}
//			}
//			cm.addRecipe(2, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.CHEST, 2), null, new ItemStack(ItemIDs.minecartBoxCartUS.item, 1), 1);
//			cm.addRecipe(2, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 3), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.CHEST, 2), null, new ItemStack(ItemIDs.minecartBoxCartPRR.item, 1), 1);
//			cm.addRecipe(2, new ItemStack(itemSteel, 4, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.CHEST, 1), null, new ItemStack(ItemIDs.minecartFreightL.item, 1), 1);
//			cm.addRecipe(2, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Items.WHEAT, 3), null, new ItemStack(ItemIDs.minecartFreightHopperUS.item, 1), 1);
//			for (ItemStack dye : dyeWhite) {
//				cm.addRecipe(2, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, dye, null, new ItemStack(ItemIDs.minecartShortCoveredHopper.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null,dye, null, new ItemStack(ItemIDs.minecartLongCoveredHopper.item, 1), 1);
//			}
//			for (ItemStack dye : dyeYellow) {
//				cm.addRecipe(2, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, dye, null, new ItemStack(ItemIDs.minecartKClassRailBox.item, 1), 1);
//			}
//			cm.addRecipe(2, new ItemStack(itemSteel, 6, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 3), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.HOPPER, 3), null, new ItemStack(ItemIDs.minecartFreight100TonHopper.item, 1), 1);
//			for (ItemStack bucketWater : waterbucket) {
//				cm.addRecipe(2, new ItemStack(itemSteel, 6, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(bucketWater.getItem(), 1), null, new ItemStack(ItemIDs.minecartTankWagonUS.item, 1), 1);
//			}
//			for (ItemStack c : coal) {
//				cm.addRecipe(2, new ItemStack(itemSteel, 6, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(c.getItem(), 2), null, new ItemStack(ItemIDs.minecartTenderC62Class.item, 1), 1);
//			}
//			for (ItemStack dye : dyeBrown) {
//				cm.addRecipe(2, new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, new ItemStack(TiMItems.cabinSteel, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), dye, new ItemStack(ItemIDs.minecartPassenger8_1class_DB.item, 1), 1);
//			}
//			{
//			cm.addRecipe(2, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.CHEST, 2), null, new ItemStack(ItemIDs.minecartFreightWagon_DB.item, 1), 1); }
//			{
//			cm.addRecipe(2, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.CRAFTING_TABLE, 1), null, new ItemStack(ItemIDs.minecartMailWagon_DB.item, 1), 1); }
//			{
//			cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 3), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 2), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.boilerSteel, 3), new ItemStack(TiMItems.fireboxSteel, 2), null, null, new ItemStack(ItemIDs.minecartLocoEr.item, 1), 1); }
//			for (ItemStack c : coal) {
//				cm.addRecipe(2, new ItemStack(itemSteel, 6, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 3), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(c.getItem(), 1), null, new ItemStack(ItemIDs.minecartTenderEr.item, 1), 1); }
//			for (ItemStack c : coal) {
//					cm.addRecipe(2, new ItemStack(itemSteel, 6, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2),
//						new ItemStack(TiMItems.frameSteel, 3), new ItemStack(itemSteel, 2, itemDamageSteel), null, null,
//						null, null, new ItemStack(c.getItem(), 1), null,
//						new ItemStack(ItemIDs.minecartLocoCoranationClassTender.item, 1), 1); }
//			for (ItemStack c : coal) {
//				cm.addRecipe(2, new ItemStack(itemSteel, 6, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 3),
//						new ItemStack(TiMItems.frameSteel, 3), new ItemStack(itemSteel, 2, itemDamageSteel), null, null,
//						null, null, new ItemStack(c.getItem(), 1), null,
//						new ItemStack(ItemIDs.minecart4000GallonTender.item, 1), 1); }
//			for (ItemStack c : coal) {
//			cm.addRecipe(2, new ItemStack(itemSteel, 4, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 4), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(c.getItem(), 1), null, new ItemStack(ItemIDs.minecartTenderHeavy.item, 1), 1);
//			}
//			for (ItemStack rs : redstone) {
//				cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), new ItemStack(itemSteel, 1, itemDamageSteel), null, new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(ItemIDs.transformer.item, 2), new ItemStack(TiMItems.smallElectricEngine, 2), new ItemStack(rs.getItem(), 4), null, new ItemStack(ItemIDs.minecartNYTram.item, 1), 1);
//			}
//			cm.addRecipe(2, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), new ItemStack(itemSteel, 1, itemDamageSteel), null, new ItemStack(TiMItems.cabinSteel, 1), null, null, null, null, new ItemStack(ItemIDs.minecartPassengerTramNY.item, 1), 1);
//
//			for(ItemStack iron : ingotIron) {
//				cm.addRecipe(2, new ItemStack(iron.getItem(), 2), new ItemStack(TiMItems.wheelIron, 3), new ItemStack(TiMItems.frameSteel, 2), null, new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(ItemIDs.ironBoiler.item, 2), new ItemStack(ItemIDs.ironFirebox.item, 1), null, null, new ItemStack(ItemIDs.minecartLocoA4Mallard.item, 1), 1);
//			}
//			for (ItemStack c : coal) {
//				cm.addRecipe(2, null, new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(Items.IRON_INGOT, 1), null, null, null, null, new ItemStack(c.getItem(), 1), null, new ItemStack(ItemIDs.minecartLocoA4MallardTender.item, 1), 1);
//			}
//
//			for (ItemStack i : s1) {
//				for (ItemStack j : s2) {
//					for (ItemStack iron : ingotIron) {
//						cm.addRecipe(2, new ItemStack(i.getItem(), 3, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameIron, 1), new ItemStack(iron.getItem(), 2, iron.getItemDamage()), null, null, null, null, new ItemStack(j.getItem(), 1, OreDictionary.WILDCARD_VALUE), null, new ItemStack(ItemIDs.minecartFreightWood2.item, 1), 1);
//					}
//				}
//				cm.addRecipe(2, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(i.getItem(), 1, OreDictionary.WILDCARD_VALUE), null, new ItemStack(ItemIDs.minecartFreightCenterBeam_Wood_1.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, null, null, new ItemStack(ItemIDs.minecartFlatCartWoodUS.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(i.getItem(), 1, OreDictionary.WILDCARD_VALUE), null, new ItemStack(ItemIDs.minecartFreightCenterBeam_Wood_2.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(i.getItem(), 1, OreDictionary.WILDCARD_VALUE), null, new ItemStack(ItemIDs.minecartFreightCenterBeam_Empty.item, 1), 1);
//
//				for (ItemStack dye : dyeRed) {
//					cm.addRecipe(2, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.CRAFTING_TABLE, 1), dye, new ItemStack(ItemIDs.minecartCabooseWork.item, 1), 1);
//				}
//				cm.addRecipe(2, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameWood, 2), new ItemStack(Items.STICK, 2), null, new ItemStack(TiMItems.cabinWood, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), null, new ItemStack(ItemIDs.minecartPassengerDBOriental.item, 1), 1);
//
//				cm.addRecipe(2, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Items.LEATHER, 1), null, new ItemStack(ItemIDs.minecartStockCar.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(i.getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Items.LEATHER, 1), null, new ItemStack(ItemIDs.minecartDRWGStockCar.item, 1), 1);
//				cm.addRecipe(2, new ItemStack(i.getItem(), 3, OreDictionary.WILDCARD_VALUE), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, null, null, new ItemStack(ItemIDs.minecartFlatCartUS.item, 1), 1);
//			}
//
//
//
//			//=================== TIER III =================//
//
//			for (ItemStack dye : dyeRed) {
//				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(ItemIDs.transmition.item, 1), new ItemStack(TiMItems.mediumDieselEngine, 2), null, dye, new ItemStack(ItemIDs.minecartKof_DB.item, 1), 1);
//				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel),null, new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(ItemIDs.transmition.item, 1), new ItemStack(TiMItems.mediumDieselEngine, 3), null, dye, new ItemStack(ItemIDs.minecartV60_DB.item, 1), 1);				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item,2), new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.smallElectricEngine,6), new ItemStack(TiMItems.mediumDieselEngine, 6), new ItemStack(TiMItems.generator,4), dye, new ItemStack(ItemIDs.minecartLocoSD40.item, 1), 1);
//				cm.addRecipe(3, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.CHEST, 2), dye, new ItemStack(ItemIDs.minecartFreightWellcar.item, 1), 1);
//				for (ItemStack rs : redstone) {
//					cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood, 2),
//							new ItemStack(TiMItems.frameSteel, 2), null, null, new ItemStack(TiMItems.cabinSteel, 1),
//							new ItemStack(ItemIDs.transformer.item, 2), new ItemStack(TiMItems.smallElectricEngine, 2),
//							new ItemStack(rs.getItem(), 4), dye, new ItemStack(ItemIDs.minecartLocoBR185.item, 1), 1);
//				}
//				cm.addRecipe(3, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2),
//						new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null,
//						null, null, null, new ItemStack(Blocks.CRAFTING_TABLE, 1), dye,
//						new ItemStack(ItemIDs.minecartCabooseLoggingPRR.item, 1), 1);
//				cm.addRecipe(3, new ItemStack(itemSteel, 4, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.CHEST, 2), dye, new ItemStack(ItemIDs.minecartHeavyweightMailcar.item, 1), 1);
//				cm.addRecipe(3, new ItemStack(itemSteel, 4, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.CHEST, 2), dye, new ItemStack(ItemIDs.minecartHeavyweightFreight.item, 1), 1);
//			}
//			for (ItemStack rs : redstone) {
//				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), new ItemStack(itemSteel, 1, itemDamageSteel), null, new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(ItemIDs.transformer.item, 2), new ItemStack(TiMItems.smallElectricEngine, 2), new ItemStack(rs.getItem(), 4), null, new ItemStack(ItemIDs.minecartLocoHighSpeedZeroED.item, 1), 1);
//			}
//			cm.addRecipe(3, new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, new ItemStack(TiMItems.cabinSteel, 1), null, new ItemStack(TiMItems.seatsWooden, 5), null, null, new ItemStack(ItemIDs.minecartPassengerHighSpeedCarZeroED.item, 1), 1);
//
//			for (ItemStack dye : dyeOrange){
//				cm.addRecipe(3,null, new ItemStack(TiMItems.wheelWood,2),new ItemStack(TiMItems.frameSteel,1), null,null, new ItemStack(TiMItems.cabinSteel,1),null, null,new ItemStack(TiMItems.seatsWooden,1), dye,new ItemStack(ItemIDs.minecartGS4_Passenger.item,1), 1);
//				cm.addRecipe(3,null, new ItemStack(TiMItems.wheelWood,2),new ItemStack(TiMItems.frameSteel,1), null,null, new ItemStack(TiMItems.cabinSteel,1),null, null,new ItemStack(TiMItems.seatsWooden,1), dye,new ItemStack(ItemIDs.minecartGS4_Tail.item,1), 1);
//				cm.addRecipe(3,new ItemStack(Blocks.GLASS,1), new ItemStack(TiMItems.wheelWood,2),new ItemStack(TiMItems.frameSteel,1), null,null, new ItemStack(TiMItems.cabinSteel,1),null, null,new ItemStack(TiMItems.seatsWooden,1), dye,new ItemStack(ItemIDs.minecartGS4_Observatory.item,1), 1);
//				for (ItemStack c : coal) {
//					cm.addRecipe(3, new ItemStack(c.getItem(), 2), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), null, null, null, null, null, null, dye, new ItemStack(ItemIDs.minecartGS4_Tender.item, 1), 1);
//				}
//				cm.addRecipe(3,null, new ItemStack(TiMItems.wheelWood,2),new ItemStack(TiMItems.frameSteel,1), null,null, null,null, null,new ItemStack(Blocks.CHEST,2), dye,new ItemStack(ItemIDs.minecartGS4_Baggage.item,1), 1);
//				cm.addRecipe(3,null, new ItemStack(TiMItems.wheelWood,4),new ItemStack(TiMItems.frameSteel,1), null,new ItemStack(TiMItems.chimneySteel,1), new ItemStack(TiMItems.cabinSteel,1),new ItemStack(TiMItems.boilerSteel,4), new ItemStack(TiMItems.fireboxSteel,1),null, dye,new ItemStack(ItemIDs.minecartGS4_Loco.item,1), 1);
//
//				cm.addRecipe(3,null, new ItemStack(TiMItems.wheelWood,2),new ItemStack(TiMItems.frameSteel,1), null,null, new ItemStack(TiMItems.cabinSteel,1),null, null,new ItemStack(TiMItems.seatsWooden,1), dye,new ItemStack(ItemIDs.minecartPassengerMILW.item,1), 1);
//				cm.addRecipe(3,null, new ItemStack(TiMItems.wheelWood,2),new ItemStack(TiMItems.frameSteel,1), null,null, new ItemStack(TiMItems.cabinSteel,1),null, null,new ItemStack(TiMItems.seatsWooden,1), dye,new ItemStack(ItemIDs.minecartPassengerMILWTail.item,1), 1);
//				for (ItemStack c : coal) {
//					cm.addRecipe(3, new ItemStack(c.getItem(), 2), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), null, null, null, null, null, null, dye, new ItemStack(ItemIDs.minecartMILWTender.item, 1), 1);
//				}
//				cm.addRecipe(3,null, new ItemStack(TiMItems.wheelWood,2),new ItemStack(TiMItems.frameSteel,1), null,null, null,null, null,new ItemStack(Blocks.CHEST,2), dye,new ItemStack(ItemIDs.minecartBaggageMILW.item,1), 1);
//				cm.addRecipe(3,null, new ItemStack(TiMItems.wheelWood,4),new ItemStack(TiMItems.frameSteel,1), null,new ItemStack(TiMItems.chimneySteel,1), new ItemStack(TiMItems.cabinSteel,1),new ItemStack(TiMItems.boilerSteel,3), new ItemStack(TiMItems.fireboxSteel,1),null, dye,new ItemStack(ItemIDs.minecartLocoMILWClassA.item,1), 1);
//
//				cm.addRecipe(3,null, new ItemStack(TiMItems.wheelWood,8),new ItemStack(TiMItems.frameSteel,3), new ItemStack(itemSteel, 2, itemDamageSteel),new ItemStack(TiMItems.chimneySteel,2), new ItemStack(TiMItems.cabinSteel,1),new ItemStack(TiMItems.smallElectricEngine,8), new ItemStack(TiMItems.mediumDieselEngine,11),new ItemStack(TiMItems.generator,4), dye,new ItemStack(ItemIDs.minecartLocoDD35B.item,1), 1);
//				cm.addRecipe(3,new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood,8),new ItemStack(TiMItems.frameSteel,3), new ItemStack(itemSteel, 2, itemDamageSteel),new ItemStack(TiMItems.chimneySteel,2), new ItemStack(TiMItems.cabinSteel,1),new ItemStack(TiMItems.smallElectricEngine,8), new ItemStack(TiMItems.mediumDieselEngine,11),new ItemStack(TiMItems.generator,4), dye,new ItemStack(ItemIDs.minecartLocoDD35A.item,1), 1);
//			}
//
//			for (ItemStack dye : dyeWhite) {
//				for (ItemStack rs : redstone) {
//					cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), null, null, new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(ItemIDs.transformer.item, 4), new ItemStack(TiMItems.smallElectricEngine, 4), new ItemStack(rs.getItem(), 4), dye, new ItemStack(ItemIDs.minecartICE1_Loco.item, 1), 1);
//				}
//				cm.addRecipe(3, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), null, null, new ItemStack(TiMItems.cabinSteel, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), dye, new ItemStack(ItemIDs.minecartICE1_1stClass.item, 1), 1);
//				cm.addRecipe(3, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), null, null, new ItemStack(TiMItems.cabinSteel, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), dye, new ItemStack(ItemIDs.minecartICE1_2ndClass.item, 1), 1);
//				cm.addRecipe(3, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), null, null, new ItemStack(TiMItems.cabinSteel, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), dye, new ItemStack(ItemIDs.minecartICE1_Restaurant.item, 1), 1);
//			}
//			cm.addRecipe(3,new ItemStack(ItemIDs.controls.item,2),new ItemStack(TiMItems.wheelWood,2),new ItemStack(TiMItems.frameSteel,2),null,null,new ItemStack(TiMItems.cabinSteel, 1),new ItemStack(TiMItems.smallElectricEngine, 4), new ItemStack(TiMItems.mediumDieselEngine, 6), new ItemStack(TiMItems.generator, 4), null,new ItemStack(ItemIDs.minecartLocoEMDF7.item, 1),1);
//			cm.addRecipe(3,new ItemStack(ItemIDs.controls.item,2),new ItemStack(TiMItems.wheelWood,2),new ItemStack(TiMItems.frameSteel,2),null,null,new ItemStack(TiMItems.cabinSteel, 1),new ItemStack(TiMItems.smallElectricEngine, 4), new ItemStack(TiMItems.mediumDieselEngine, 6), new ItemStack(TiMItems.generator, 4),null,new ItemStack(ItemIDs.minecartLocoEMDF3.item, 1),1);
//			cm.addRecipe(3,null,new ItemStack(TiMItems.wheelWood,2),new ItemStack(TiMItems.frameSteel,2),null,null,null,new ItemStack(TiMItems.smallElectricEngine, 4), new ItemStack(TiMItems.mediumDieselEngine, 6), new ItemStack(TiMItems.generator, 4),null,new ItemStack(ItemIDs.minecartLocoEMDF7B.item, 1),1);
//			cm.addRecipe(3,null,new ItemStack(TiMItems.wheelWood,2),new ItemStack(TiMItems.frameSteel,2),null,null,null,new ItemStack(TiMItems.smallElectricEngine, 4), new ItemStack(TiMItems.mediumDieselEngine, 6), new ItemStack(TiMItems.generator, 4),null,new ItemStack(ItemIDs.minecartLocoEMDF3B.item, 1),1);
//
//			cm.addRecipe(3,new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood,8),new ItemStack(TiMItems.frameSteel,3), new ItemStack(itemSteel, 2, itemDamageSteel),null, new ItemStack(TiMItems.cabinSteel,1),new ItemStack(TiMItems.smallElectricEngine,2), new ItemStack(TiMItems.mediumDieselEngine,4),new ItemStack(TiMItems.generator,4), null,new ItemStack(ItemIDs.minecartLocoFOLM1.item,1), 1);
//			//cm.addRecipe(3, new ItemStack(ItemIDs.controls.item,3), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), new ItemStack(itemSteel, 1), null, new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(ItemIDs.transformer.item,4), new ItemStack(TiMItems.smallElectricEngine, 4), new ItemStack(Item.redstone, 4), new ItemStack(Items.DYE, 1, 8), new ItemStack(ItemIDs.minecartNewHighSpeedLoco.item, 1), 1);
//
//			/*cm.addRecipe(3, null, new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameSteel, 1),
//					new ItemStack(itemSteel, 3), null, null, null, new ItemStack(TiMItems.seatsWooden, 8), null, null,
//					new ItemStack(ItemIDs.minecartIC4_DSB_FG.item, 1), 1);
//			cm.addRecipe(3, null, new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameSteel, 1),
//					new ItemStack(itemSteel, 3), null, null, null, new ItemStack(TiMItems.seatsWooden, 8), null, null,
//					new ItemStack(ItemIDs.minecartIC4_DSB_FH.item, 1), 1);
//			cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(TiMItems.wheelIron, 2), new ItemStack(TiMItems.frameSteel, 1), new ItemStack(itemSteel, 3), null, new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.smallElectricEngine, 6), new ItemStack(TiMItems.mediumDieselEngine, 6), new ItemStack(TiMItems.generator, 4), null, new ItemStack(ItemIDs.minecartIC4_DSB_MG.item, 1), 1);*/
//
//			for (ItemStack dye : dyeGreen) {
//				for (ItemStack rs : redstone) {
//					cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 1, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(ItemIDs.transformer.item, 2), new ItemStack(TiMItems.smallElectricEngine, 2), new ItemStack(rs.getItem(), 2), dye, new ItemStack(ItemIDs.minecartVL10.item, 1), 1);
//					cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 1, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(ItemIDs.transformer.item, 2), new ItemStack(TiMItems.smallElectricEngine, 2), new ItemStack(rs.getItem(), 2), dye, new ItemStack(ItemIDs.minecartBR_E69.item, 1), 1);
//				}
//			}
//
//			for (ItemStack dye : dyeOrange) {
//				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(TiMItems.smallElectricEngine, 4), new ItemStack(TiMItems.mediumDieselEngine, 4), new ItemStack(TiMItems.generator, 4), dye, new ItemStack(ItemIDs.minecartLocoSD70.item, 1), 1);
//			}
//			for(ItemStack dye :dyeGreen){
//				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood, 3), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1), new ItemStack(ItemIDs.transformer.item, 2), new ItemStack(TiMItems.smallElectricEngine, 2), null, dye, new ItemStack(ItemIDs.minecartLocoBP4.item, 1), 1);
//			}
//			cm.addRecipe(3, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.HOPPER, 1), null, new ItemStack(ItemIDs.minecartOpenWagon.item, 1), 1);
//			cm.addRecipe(3, new ItemStack(itemSteel, 6, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.HOPPER, 1), null, new ItemStack(ItemIDs.minecartFreightGTNG.item, 1), 1);
//			cm.addRecipe(3, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2),new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null,null, null, null, new ItemStack(Blocks.jukebox, 1), null, new ItemStack(ItemIDs.minecartJukeBoxCart.item, 1), 1);
//
//			for (ItemStack dye: dyeYellow){
//				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood,2),new ItemStack(TiMItems.frameSteel,2), new ItemStack(itemSteel, 2, itemDamageSteel),new ItemStack(TiMItems.chimneySteel,2), new ItemStack(TiMItems.cabinSteel,2),new ItemStack(TiMItems.smallElectricEngine,2), new ItemStack(ItemIDs.transformer.item,2),null, dye,new ItemStack(ItemIDs.minecartE103.item,1), 1);
//			}
//
//			for (ItemStack dye : dyeBlue) {
//				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood,2),new ItemStack(TiMItems.frameSteel,2), new ItemStack(itemSteel, 2, itemDamageSteel),new ItemStack(TiMItems.chimneySteel,2), new ItemStack(TiMItems.cabinSteel,1),new ItemStack(TiMItems.smallElectricEngine,1), new ItemStack(ItemIDs.transformer.item,2),null, dye,new ItemStack(ItemIDs.minecartE10_DB.item,1), 1);
//				cm.addRecipe(3, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), null, null, new ItemStack(TiMItems.cabinSteel, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), dye, new ItemStack(ItemIDs.minecartPassengerRheingold.item, 1), 1);
//				cm.addRecipe(3, new ItemStack(Blocks.GLASS,1), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), null, null, new ItemStack(TiMItems.cabinSteel, 1), null, null, new ItemStack(TiMItems.seatsWooden, 1), dye, new ItemStack(ItemIDs.minecartPassengerRheingoldPanorama.item, 1), 1);
//				cm.addRecipe(3, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), null, null, new ItemStack(TiMItems.cabinSteel, 1), null, null, new ItemStack(Blocks.CRAFTING_TABLE, 1), dye, new ItemStack(ItemIDs.minecartPassengerRheingoldDining1.item, 1), 1);
//				cm.addRecipe(3, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), null, null, new ItemStack(TiMItems.cabinSteel, 1), null, null, new ItemStack(Blocks.CRAFTING_TABLE, 1), dye, new ItemStack(ItemIDs.minecartPassengerRheingoldDining2.item, 1), 1);
//
//				cm.addRecipe(3, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, new ItemStack(TiMItems.cabinSteel, 1), null, new ItemStack(TiMItems.seatsWooden, 1), null, dye, new ItemStack(ItemIDs.minecartPassengerBlue.item, 1), 1);
//				for (ItemStack bucketWater : waterbucket) {
//					cm.addRecipe(3, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(bucketWater.getItem(), 1), dye, new ItemStack(ItemIDs.minecartTankWagon_DB.item, 1), 1);
//				}
//				for (ItemStack rs : redstone) {
//					cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(TiMItems.wheelWood, 3),
//							new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 1, itemDamageSteel),
//							new ItemStack(TiMItems.chimneySteel, 1), new ItemStack(TiMItems.cabinSteel, 1),
//							new ItemStack(ItemIDs.transformer.item, 2), new ItemStack(TiMItems.smallElectricEngine, 2),
//							new ItemStack(rs.getItem(), 2), dye, new ItemStack(ItemIDs.minecartLocoClass85.item, 1), 1);
//				}
//			}
//			for (ItemStack dye : dyeYellow){
//				cm.addRecipe(3, null, new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 1), null, null, new ItemStack(TiMItems.cabinSteel, 1), null, null, new ItemStack(Blocks.CHEST, 1), dye, new ItemStack(ItemIDs.minecartFreightGermanPost.item, 1), 1);
//			}
//			cm.addRecipe(3, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.RAIL, 1), null, new ItemStack(ItemIDs.minecartFlatCartRail_DB.item, 1), 1);
//			cm.addRecipe(3, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, null, null, new ItemStack(ItemIDs.minecartFlatCart_DB.item, 1), 1);
//			cm.addRecipe(3, new ItemStack(itemSteel, 4, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 4, itemDamageSteel), null, null, null, null, null, null, new ItemStack(ItemIDs.minecartBulkheadFlatCart.item, 1), 1);
//			cm.addRecipe(3, new ItemStack(itemSteel, 2, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 3), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, null, null, new ItemStack(ItemIDs.minecartFreightDepressedFlatbed.item, 1), 1);
//			cm.addRecipe(3, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.HOPPER, 1), null, new ItemStack(ItemIDs.minecartFreightGondola_DB.item, 1), 1);
//			cm.addRecipe(3, new ItemStack(itemSteel, 4, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 6), new ItemStack(itemSteel, 4, itemDamageSteel), null, null, null, null, null, null, new ItemStack(ItemIDs.minecartASTFAutorack.item, 1), 1);
//
//			for (ItemStack dye : dyeLightGray) {
//				cm.addRecipe(3, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(Blocks.CHEST, 1), dye, new ItemStack(ItemIDs.minecartFreightTrailer.item, 1), 1);
//			}
//			for (ItemStack j : s2) {
//				cm.addRecipe(3, new ItemStack(itemSteel, 5, itemDamageSteel), new ItemStack(TiMItems.wheelWood, 2), new ItemStack(TiMItems.frameSteel, 2), new ItemStack(itemSteel, 2, itemDamageSteel), null, null, null, null, new ItemStack(j.getItem(), 1, OreDictionary.WILDCARD_VALUE), null, new ItemStack(ItemIDs.minecartFlatCartLogs_DB.item, 1), 1);
//			}
//		}
//	}
//
//	public static ArrayList<ItemStack> waterContainers(){
//		ArrayList<ItemStack> containers = new ArrayList<ItemStack>();
//		for (FluidContainerRegistry.FluidContainerData data : FluidContainerRegistry.getRegisteredFluidContainerData()){
//			if(data.fluid.fluid == FluidRegistry.WATER){
//				containers.add(data.filledContainer);
//			}
//		}
//		return containers;
//	}
//}
