package train.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import train.common.core.managers.TierRecipeManager;
import train.common.library.ItemIDs;
import train.common.api.crafting.ITierCraftingManager;

import java.util.ArrayList;

public class AssemblyTableRecipes {

	//private static ItemStack ingotSteel = new ItemStack(ItemIDs.steel.stack);
	private static ItemStack ingotIron = new ItemStack(Items.iron_ingot);

	public static void recipes() {

		ITierCraftingManager cm = TierRecipeManager.getInstance();

		ArrayList<ItemStack> ingotSteel = OreDictionary.getOres("ingotSteel");
		if (ingotSteel != null && ingotSteel.size() >= 0) {
			for (int k = 0; k < ingotSteel.size(); k++) {

				//=================== TIER I ===================//
				cm.addRecipe(1, null, new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 1), new ItemStack(Items.stick, 1), new ItemStack(ItemIDs.ironChimney.item, 1), null, new ItemStack(ItemIDs.ironBoiler.item, 1), new ItemStack(ItemIDs.ironFirebox.item, 1), null, null, new ItemStack(ItemIDs.minecartLocoCherepanov.item, 1), 1);
				cm.addRecipe(1, new ItemStack(Blocks.crafting_table, 1), new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 1), new ItemStack(Items.stick, 2), null, new ItemStack(ItemIDs.woodenCab.item, 1), null, null, new ItemStack(Blocks.furnace, 1), null, new ItemStack(ItemIDs.minecartWork.item, 1), 1);
				cm.addRecipe(1, null, new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 1), new ItemStack(Items.stick, 2), null, null, null, null, new ItemStack(Items.coal, 1), null, new ItemStack(ItemIDs.minecartTender.item, 1), 1);
				cm.addRecipe(1, new ItemStack(Items.iron_ingot, 6), new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 2), new ItemStack(Items.stick, 2), null, null, null, null, new ItemStack(Items.lava_bucket, 1), null, new ItemStack(ItemIDs.minecartWatertransp.item, 1), 1);
				cm.addRecipe(1, null, new ItemStack(ItemIDs.ironBogie.item, 3), new ItemStack(ItemIDs.ironFrame.item, 2), new ItemStack(ingotIron.getItem(), 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(ItemIDs.ironCab.item, 1), new ItemStack(ItemIDs.ironBoiler.item, 2), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(Items.water_bucket, 1), new ItemStack(Items.dye, 1, 2), new ItemStack(ItemIDs.minecartLocoBR80_DB.item, 1), 1);
				cm.addRecipe(1, null, new ItemStack(ItemIDs.ironBogie.item, 3), new ItemStack(ItemIDs.ironFrame.item, 2), new ItemStack(ingotIron.getItem(), 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(ItemIDs.ironCab.item, 1), new ItemStack(ItemIDs.ironBoiler.item, 2), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(Items.coal, 1), null, new ItemStack(ItemIDs.minecartLocoSteamShay.item, 1), 1);
				cm.addRecipe(1, null, new ItemStack(ItemIDs.ironBogie.item, 3), new ItemStack(ItemIDs.ironFrame.item, 2), new ItemStack(ingotIron.getItem(), 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(ItemIDs.ironCab.item, 1), new ItemStack(ItemIDs.ironBoiler.item, 2), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(Items.coal, 1), new ItemStack(Items.dye, 1, 1), new ItemStack(ItemIDs.minecartLocoForneyRed.item, 1), 1);
				cm.addRecipe(1, null, new ItemStack(ItemIDs.ironBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 1), new ItemStack(Items.stick, 2), null, null, null, null, new ItemStack(Items.coal, 1), new ItemStack(Items.dye, 1, 1), new ItemStack(ItemIDs.minecartSteamRedTender.item, 1), 1);
				cm.addRecipe(1, null, new ItemStack(ItemIDs.ironBogie.item, 3), new ItemStack(ItemIDs.woodenFrame.item, 2), new ItemStack(Items.stick, 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(ItemIDs.woodenCab.item, 1), new ItemStack(ItemIDs.ironBoiler.item, 1), new ItemStack(ItemIDs.ironFirebox.item, 1), null, new ItemStack(Items.dye, 1, 1), new ItemStack(ItemIDs.minecartPower.item, 1), 1);
				cm.addRecipe(1, null, new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 2), new ItemStack(Items.stick, 2), new ItemStack(ItemIDs.ironChimney.item, 1), new ItemStack(ItemIDs.woodenCab.item, 1), new ItemStack(ItemIDs.ironBoiler.item, 1), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(Blocks.torch, 1), new ItemStack(Items.dye, 1, 4), new ItemStack(ItemIDs.minecartLoco3.item, 1), 1);
				//cm.addRecipe(1, new ItemStack(Block.planks, 4), new ItemStack(ItemIDs.ironBogie.stack, 2), new ItemStack(ItemIDs.woodenFrame.stack, 1), new ItemStack(ingotIron.getItem(), 1), null, new ItemStack(ItemIDs.woodenCab.stack, 1), null, new ItemStack(ItemIDs.electmotor.stack, 2), new ItemStack(Item.redstone, 4), null, new ItemStack(ItemIDs.minecartTramWood.stack, 1), 1);
				cm.addRecipe(1, new ItemStack(ingotIron.getItem(), 2), new ItemStack(ItemIDs.ironBogie.item, 2), new ItemStack(ItemIDs.ironFrame.item, 1), new ItemStack(ingotIron.getItem(), 2), null, null, null, null, new ItemStack(Blocks.chest, 1), new ItemStack(Items.dye, 1, 11), new ItemStack(ItemIDs.minecartMineTrain.item, 1), 1);
				cm.addRecipe(1, new ItemStack(ingotIron.getItem(), 2), new ItemStack(ItemIDs.ironBogie.item, 2), new ItemStack(ItemIDs.ironFrame.item, 1), new ItemStack(ingotIron.getItem(), 1), null, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(ItemIDs.transformer.item, 1), new ItemStack(ItemIDs.electmotor.item, 2), new ItemStack(Items.redstone, 1), new ItemStack(Items.dye, 1, 11), new ItemStack(ItemIDs.minecartLocoMineTrain.item, 1), 1);

				//=================== TIER II ==================//

				cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(ItemIDs.bogie.item, 3), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.item, 1), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.electmotor.item, 4), new ItemStack(ItemIDs.dieselengine.item, 4), new ItemStack(ItemIDs.generator.item, 2), new ItemStack(Items.dye, 1, 11), new ItemStack(ItemIDs.minecartCD742.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(ItemIDs.bogie.item, 4), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.item, 1), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.transmition.item, 1), new ItemStack(ItemIDs.dieselengine.item, 3), null, new ItemStack(Items.dye, 1, 4), new ItemStack(ItemIDs.minecartShunter.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(ItemIDs.bogie.item, 3), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.item, 1), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.electmotor.item, 2), new ItemStack(ItemIDs.dieselengine.item, 2), new ItemStack(ItemIDs.generator.item, 2), new ItemStack(Items.dye, 1, 2), new ItemStack(ItemIDs.minecartChmE3.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(ItemIDs.bogie.item, 3), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.item, 1), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.electmotor.item, 4), new ItemStack(ItemIDs.dieselengine.item, 4), new ItemStack(ItemIDs.generator.item, 3), new ItemStack(Items.dye, 1, 1), new ItemStack(ItemIDs.minecartGP7Red.item, 1), 1);
				cm.addRecipe(2, null, new ItemStack(ItemIDs.bogie.item, 3), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.item, 1), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.boiler.item, 2), new ItemStack(ItemIDs.firebox.item, 1), null, null, new ItemStack(ItemIDs.minecartLocoBR01_DB.item, 1), 1);
				cm.addRecipe(2, null, new ItemStack(ItemIDs.bogie.item, 3), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.item, 1), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.boiler.item, 3), new ItemStack(ItemIDs.firebox.item, 1), null, null, new ItemStack(ItemIDs.minecartLocoC62Class.item, 1), 1);
				cm.addRecipe(2, null, new ItemStack(ItemIDs.bogie.item, 3), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.item, 1), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.boiler.item, 2), new ItemStack(ItemIDs.firebox.item, 1), null, new ItemStack(Items.dye, 1, 4), new ItemStack(ItemIDs.minecartLocomogulBlue.item, 1), 1);
				//cm.addRecipe(2, null, new ItemStack(ItemIDs.bogie.stack, 3), new ItemStack(ItemIDs.steelframe.stack, 4), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.stack, 1), new ItemStack(ItemIDs.steelcab.stack, 1), new ItemStack(ItemIDs.boiler.stack, 4), new ItemStack(ItemIDs.firebox.stack, 2), null, null, new ItemStack(ItemIDs.minecartLoco4.stack, 1), 1);
				//cm.addRecipe(2, null, new ItemStack(ItemIDs.bogie.stack, 3), new ItemStack(ItemIDs.steelframe.stack, 3), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.stack, 1), new ItemStack(ItemIDs.steelcab.stack, 1), new ItemStack(ItemIDs.boiler.stack, 3), new ItemStack(ItemIDs.firebox.stack, 2), null, null, new ItemStack(ItemIDs.minecartHeavySteam.stack, 1), 1);//TODO put it back once Heavy Steam is back
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 6), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), new ItemStack(ingotSteel.get(k).getItem(), 1), new ItemStack(ItemIDs.steelchimney.item, 1), null, new ItemStack(ItemIDs.boiler.item, 1), new ItemStack(ItemIDs.firebox.item, 1), new ItemStack(Blocks.rail, 16), null, new ItemStack(ItemIDs.minecartBuilder.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, new ItemStack(ItemIDs.steelcab.item, 1), null, null, new ItemStack(ItemIDs.seats.item, 1), new ItemStack(Items.dye, 1, 2), new ItemStack(ItemIDs.minecartPassenger5.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 6), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, null, null, new ItemStack(ItemIDs.minecartFlatCartSU.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 6), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Items.water_bucket, 1), new ItemStack(Items.dye, 1, 11), new ItemStack(ItemIDs.minecartTankWagon.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 6), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Items.water_bucket, 1), new ItemStack(Items.dye, 1, 8), new ItemStack(ItemIDs.minecartTankWagon2.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Blocks.chest, 1), null, new ItemStack(ItemIDs.minecartBoxCartUS.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Items.wheat, 3), null, new ItemStack(ItemIDs.minecartFreightHopperUS.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 6), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Items.water_bucket, 1), null, new ItemStack(ItemIDs.minecartTankWagonUS.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 6), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Items.coal, 1), null, new ItemStack(ItemIDs.minecartTenderBR01_DB.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 6), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Items.coal, 1), null, new ItemStack(ItemIDs.minecartTenderC62Class.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, new ItemStack(ItemIDs.steelcab.item, 1), null, null, new ItemStack(ItemIDs.seats.item, 1), new ItemStack(Items.dye, 1, 3), new ItemStack(ItemIDs.minecartPassenger8_1class_DB.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, new ItemStack(ItemIDs.steelcab.item, 1), null, null, new ItemStack(ItemIDs.seats.item, 1), new ItemStack(Items.dye, 1, 2), new ItemStack(ItemIDs.minecartPassenger9_2class_DB.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Blocks.chest, 1), null, new ItemStack(ItemIDs.minecartFreightWagon_DB.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Blocks.chest, 1), null, new ItemStack(ItemIDs.minecartMailWagon_DB.item, 1), 1);
				cm.addRecipe(2, null, new ItemStack(ItemIDs.bogie.item, 3), new ItemStack(ItemIDs.steelframe.item, 3), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.item, 2), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.boiler.item, 3), new ItemStack(ItemIDs.firebox.item, 2), null, null, new ItemStack(ItemIDs.minecartLocoEr.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ingotSteel.get(k).getItem(), 6), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 3), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Items.coal, 1), null, new ItemStack(ItemIDs.minecartTenderEr.item, 1), 1);
				cm.addRecipe(2, new ItemStack(ItemIDs.controls.item,1), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), new ItemStack(ingotSteel.get(k).getItem(), 1), null, new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.transformer.item,2), new ItemStack(ItemIDs.electmotor.item, 2), new ItemStack(Items.redstone, 4), null, new ItemStack(ItemIDs.minecartNYTram.item, 1), 1);
				cm.addRecipe(2, null, new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), new ItemStack(ingotSteel.get(k).getItem(), 1), null, new ItemStack(ItemIDs.steelcab.item, 1), null, null, null, null, new ItemStack(ItemIDs.minecartPassengerTramNY.item, 1), 1);
				//=================== TIER III =================//

				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.item, 1), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.transmition.item, 1), new ItemStack(ItemIDs.dieselengine.item, 2), null, new ItemStack(Items.dye, 1, 1), new ItemStack(ItemIDs.minecartKof_DB.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 1), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.item, 1), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.transmition.item, 1), new ItemStack(ItemIDs.dieselengine.item, 3), null, new ItemStack(Items.dye, 1, 1), new ItemStack(ItemIDs.minecartV60_DB.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item,2), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), new ItemStack(ingotSteel.get(k).getItem(), 1), null, new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.transformer.item,2), new ItemStack(ItemIDs.electmotor.item, 2), new ItemStack(Items.redstone, 4), null, new ItemStack(ItemIDs.minecartLocoHighSpeedZeroED.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, new ItemStack(ItemIDs.steelcab.item, 1), null, new ItemStack(ItemIDs.seats.item, 5), null, null, new ItemStack(ItemIDs.minecartPassengerHighSpeedCarZeroED.item, 1), 1);
				//cm.addRecipe(3, new ItemStack(ItemIDs.controls.stack,3), new ItemStack(ItemIDs.bogie.stack, 2), new ItemStack(ItemIDs.steelframe.stack, 1), new ItemStack(ingotSteel.get(k).getItem(), 1), null, new ItemStack(ItemIDs.steelcab.stack, 1), new ItemStack(ItemIDs.transformer.stack,4), new ItemStack(ItemIDs.electmotor.stack, 4), new ItemStack(Item.redstone, 4), new ItemStack(Items.dye, 1, 8), new ItemStack(ItemIDs.minecartNewHighSpeedLoco.stack, 1), 1);
				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(ItemIDs.bogie.item, 3), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 1), new ItemStack(ItemIDs.steelchimney.item, 1), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.transformer.item, 2), new ItemStack(ItemIDs.electmotor.item, 2), new ItemStack(Items.redstone, 2), new ItemStack(Items.dye, 1, 2), new ItemStack(ItemIDs.minecartVL10.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(ItemIDs.bogie.item, 3), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 1), new ItemStack(ItemIDs.steelchimney.item, 1), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.transformer.item, 2), new ItemStack(ItemIDs.electmotor.item, 2), new ItemStack(Items.redstone, 2), new ItemStack(Items.dye, 1, 2), new ItemStack(ItemIDs.minecartBR_E69.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item, 2), new ItemStack(ItemIDs.bogie.item, 3), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.item, 1), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.electmotor.item, 6), new ItemStack(ItemIDs.dieselengine.item, 6), new ItemStack(ItemIDs.generator.item, 4), new ItemStack(Items.dye, 1, 14), new ItemStack(ItemIDs.minecartLocoSD70.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ItemIDs.controls.item,2), new ItemStack(ItemIDs.bogie.item, 3), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), new ItemStack(ItemIDs.steelchimney.item, 1), new ItemStack(ItemIDs.steelcab.item, 1), new ItemStack(ItemIDs.electmotor.item,6), new ItemStack(ItemIDs.dieselengine.item, 6), new ItemStack(ItemIDs.generator.item,4), new ItemStack(Items.dye, 1, 1), new ItemStack(ItemIDs.minecartLocoSD40.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Blocks.chest, 1), null, new ItemStack(ItemIDs.minecartOpenWagon.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Blocks.jukebox, 1), null, new ItemStack(ItemIDs.minecartJukeBoxCart.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, new ItemStack(ItemIDs.steelcab.item, 1), null, new ItemStack(ItemIDs.seats.item, 1), null, new ItemStack(Items.dye, 1, 4), new ItemStack(ItemIDs.minecartPassengerBlue.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Blocks.rail, 1), null, new ItemStack(ItemIDs.minecartFlatCartRail_DB.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, null, null, new ItemStack(ItemIDs.minecartFlatCart_DB.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Blocks.chest, 1), null, new ItemStack(ItemIDs.minecartFreightGondola_DB.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Items.water_bucket, 1), null, new ItemStack(ItemIDs.minecartTankWagon_DB.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Blocks.chest, 1), new ItemStack(Items.dye, 1, 7), new ItemStack(ItemIDs.minecartFreightTrailer.item, 1), 1);
				cm.addRecipe(3, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Blocks.chest, 2), new ItemStack(Items.dye, 1, 1), new ItemStack(ItemIDs.minecartFreightWellcar.item, 1), 1);

				ArrayList<ItemStack> s1 = OreDictionary.getOres("plankWood");
				ArrayList<ItemStack> s2 = OreDictionary.getOres("logWood");
				if (s1 != null && s1.size() >= 0) {
					for (int i = 0; i < s1.size(); i++) {
						if (s2 != null && s2.size() >= 0) {
							for (int j = 0; j < s2.size(); j++) {
								cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 2), new ItemStack(Items.stick, 2), null, null, null, null, new ItemStack(s2.get(j).getItem(), 1, OreDictionary.WILDCARD_VALUE), null, new ItemStack(ItemIDs.minecartWood.item, 1), 1);
								cm.addRecipe(2, new ItemStack(s1.get(i).getItem(), 3, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 1), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(s2.get(j).getItem(), 1, OreDictionary.WILDCARD_VALUE), null, new ItemStack(ItemIDs.minecartFreightWood2.item, 1), 1);
								cm.addRecipe(2, new ItemStack(s1.get(i).getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(s1.get(i).getItem(), 1, OreDictionary.WILDCARD_VALUE), null, new ItemStack(ItemIDs.minecartFreightCenterBeam_Wood_1.item, 1), 1);
								cm.addRecipe(2, new ItemStack(s1.get(i).getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(s1.get(i).getItem(), 4, OreDictionary.WILDCARD_VALUE), null, new ItemStack(ItemIDs.minecartFlatCartWoodUS.item, 1), 1);
								cm.addRecipe(2, new ItemStack(s1.get(i).getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(s1.get(i).getItem(), 1, OreDictionary.WILDCARD_VALUE), null, new ItemStack(ItemIDs.minecartFreightCenterBeam_Wood_2.item, 1), 1);
								cm.addRecipe(3, new ItemStack(ingotSteel.get(k).getItem(), 5), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(s2.get(j).getItem(), 1, OreDictionary.WILDCARD_VALUE), null, new ItemStack(ItemIDs.minecartFlatCartLogs_DB.item, 1), 1);
								cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 8, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.woodenBogie.item, 3), new ItemStack(ItemIDs.woodenFrame.item, 2), new ItemStack(ingotIron.getItem(), 2), new ItemStack(ItemIDs.ironChimney.item, 1), null, new ItemStack(ItemIDs.ironBoiler.item, 1), new ItemStack(ItemIDs.ironFirebox.item, 1), new ItemStack(Items.gold_ingot, 2), new ItemStack(Items.dye, 1, 2), new ItemStack(ItemIDs.minecartLocoSteamAdler.item, 1), 1);
								cm.addRecipe(1, new ItemStack(ingotIron.getItem(), 2), new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 1), new ItemStack(ingotIron.getItem(), 1), null, null, null, null, new ItemStack(Items.coal, 1), new ItemStack(Items.dye, 1, 2), new ItemStack(ItemIDs.minecartTenderAdler.item, 1), 1);
								cm.addRecipe(1, new ItemStack(ingotIron.getItem(), 1), new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 2), new ItemStack(ingotIron.getItem(), 1), null, new ItemStack(ItemIDs.woodenCab.item, 1), null, null, new ItemStack(ItemIDs.seats.item, 1), new ItemStack(Items.dye, 1, 11), new ItemStack(ItemIDs.minecartPassengerAdler.item, 1), 1);
							}
						}
						//=================== TIER I ===================//
						cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 2), new ItemStack(Items.stick, 2), null, new ItemStack(ItemIDs.woodenCab.item, 1), null, null, new ItemStack(ItemIDs.seats.item, 1), null, new ItemStack(ItemIDs.minecartPassenger2.item, 1), 1);
						cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 2), new ItemStack(Items.stick, 2), null, new ItemStack(ItemIDs.woodenCab.item, 1), null, null, new ItemStack(ItemIDs.seats.item, 1), null, new ItemStack(ItemIDs.minecartCaboose.item, 1), 1);
						cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 2), new ItemStack(Items.stick, 2), null, null, null, null, new ItemStack(Blocks.chest, 1), null, new ItemStack(ItemIDs.minecartFreightCartSmall.item, 1), 1);
						cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 3, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 2), new ItemStack(Items.stick, 2), null, null, null, null, null, null, new ItemStack(ItemIDs.minecartFlatCart.item, 1), 1);
						cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 3, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.woodenBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 2), new ItemStack(ingotIron.getItem(), 1), null, null, null, null, new ItemStack(Blocks.crafting_table, 1), new ItemStack(Items.dye, 1, 1), new ItemStack(ItemIDs.minecartCabooseLogging.item, 1), 1);
						cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.ironBogie.item, 2), new ItemStack(ItemIDs.ironFrame.item, 2), new ItemStack(ingotIron.getItem(), 2), null, null, null, null, new ItemStack(Blocks.chest, 2), null, new ItemStack(ItemIDs.minecartChest.item, 1), 1);
						cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.ironBogie.item, 2), new ItemStack(ItemIDs.ironFrame.item, 2), new ItemStack(ingotIron.getItem(), 2), null, null, null, null, new ItemStack(Blocks.chest, 2), null, new ItemStack(ItemIDs.minecartFreightCart2.item, 1), 1);
						cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.ironBogie.item, 2), new ItemStack(ItemIDs.ironFrame.item, 2), new ItemStack(ingotIron.getItem(), 2), null, null, null, null, new ItemStack(Blocks.chest, 2), null, new ItemStack(ItemIDs.minecartFreightCartUS.item, 1), 1);
						cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 4, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.ironBogie.item, 2), new ItemStack(ItemIDs.ironFrame.item, 4), new ItemStack(ingotIron.getItem(), 2), null, null, null, null, new ItemStack(Blocks.chest, 1), null, new ItemStack(ItemIDs.minecartFreightClosed.item, 1), 1);
						cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 2, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.ironBogie.item, 2), new ItemStack(ItemIDs.ironFrame.item, 2), new ItemStack(ingotIron.getItem(), 2), null, null, null, null, new ItemStack(Blocks.chest, 2), null, new ItemStack(ItemIDs.minecartFreightOpen2.item, 1), 1);
						cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 2, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.ironBogie.item, 2), new ItemStack(ItemIDs.ironFrame.item, 2), new ItemStack(ingotIron.getItem(), 2), null, new ItemStack(ItemIDs.woodenCab.item, 1), null, null, new ItemStack(ItemIDs.seats.item, 1), null, new ItemStack(ItemIDs.minecartCaboose3.item, 1), 1);
						cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 2, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.ironBogie.item, 2), new ItemStack(ItemIDs.ironFrame.item, 2), new ItemStack(ingotIron.getItem(), 2), null, new ItemStack(ItemIDs.ironCab.item, 1), null, null, new ItemStack(ItemIDs.seats.item, 1), null, new ItemStack(ItemIDs.minecartPassenger7.item, 1), 1);
						cm.addRecipe(1, new ItemStack(s1.get(i).getItem(), 4, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.ironBogie.item, 2), new ItemStack(ItemIDs.woodenFrame.item, 1), new ItemStack(ingotIron.getItem(), 1), null, new ItemStack(ItemIDs.woodenCab.item, 1), new ItemStack(ItemIDs.transformer.item, 1), new ItemStack(ItemIDs.electmotor.item, 2), new ItemStack(Items.redstone, 4), null, new ItemStack(ItemIDs.minecartTramWood.item, 1), 1);
						//=================== TIER II ==================//
						cm.addRecipe(2, new ItemStack(s1.get(i).getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Blocks.crafting_table, 1), new ItemStack(Items.dye, 1, 1), new ItemStack(ItemIDs.minecartCabooseWork.item, 1), 1);
						cm.addRecipe(2, new ItemStack(s1.get(i).getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, new ItemStack(Items.leather, 1), null, new ItemStack(ItemIDs.minecartStockCar.item, 1), 1);
						cm.addRecipe(2, new ItemStack(s1.get(i).getItem(), 3, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, null, null, new ItemStack(ItemIDs.minecartFlatCartUS.item, 1), 1);
						cm.addRecipe(2, new ItemStack(s1.get(i).getItem(), 6, OreDictionary.WILDCARD_VALUE), new ItemStack(ItemIDs.bogie.item, 2), new ItemStack(ItemIDs.steelframe.item, 2), new ItemStack(ingotSteel.get(k).getItem(), 2), null, null, null, null, null, null, new ItemStack(ItemIDs.minecartFreightCenterBeam_Empty.item, 1), 1);
					}
				}
			}
		}
	}
}