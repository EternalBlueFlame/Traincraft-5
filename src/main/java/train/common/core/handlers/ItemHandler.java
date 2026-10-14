/*******************************************************************************
 * Copyright (c) 2013 Mrbrutal. All rights reserved.
 *
 * @name Traincraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.core.handlers;

import ebf.tim.utility.CommonUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import train.common.api.*;
import train.common.api.blocks.EnumCargoTypes;
import train.common.items.ItemTCRail;

import java.util.LinkedList;

public class ItemHandler {
	
	public static boolean handleItems(Entity entity, ItemStack itemstack) {
		if (itemstack != null) {
			if (entity instanceof Freight) {
				return handleFreight(entity, itemstack);
			} else if (entity instanceof DieselTrain) {
				return false;
			} else if (entity instanceof ElectricTrain) {
				return false;
			} else if (entity instanceof SteamTrain) {
				return false;
			} else if (entity instanceof Tender) {
				return false;
			} else {
				return false;
			}
		}
		return false;
	}

	public static boolean handleFreight(Entity entity, ItemStack itemstack) {
		int logWood = OreDictionary.getOreID("logWood");
		int plankWood = OreDictionary.getOreID("plankWood");
		int slabWood =  OreDictionary.getOreID("slabWood");
		int stairWood = OreDictionary.getOreID("stairWood");
		int rubberWood = OreDictionary.getOreID("woodRubber");
		int pulpWood = OreDictionary.getOreID("pulpWood");
		int dustWood = OreDictionary.getOreID("dustWood");
		if(itemstack == null) {
			return false;
		}
		Block block = Block.getBlockFromItem(itemstack.getItem());
		if (block == null) {
			return false;
		}
		if ((entity instanceof Freight) && ((Freight) entity).getCargoType() == EnumCargoTypes.CargoTypes.WOOD) {
            int[] ids = OreDictionary.getOreIDs(itemstack);
			return hasOre(ids, plankWood) || hasOre(ids, logWood) || hasOre(ids, slabWood) || hasOre(ids, stairWood) ||
					itemstack.getItem() == Item.getItemFromBlock(Blocks.LADDER) || itemstack.getItem() == Item.getItemFromBlock(Blocks.OAK_FENCE) || itemstack.getItem() == Item.getItemFromBlock(Blocks.OAK_FENCE_GATE) || hasOre(ids, rubberWood);
		}

		if ((entity instanceof Freight) && ((Freight) entity).getCargoType() == EnumCargoTypes.CargoTypes.RAILS) {
			return block instanceof BlockRailBase || itemstack.getItem() instanceof ItemTCRail;
		}
		if ((entity instanceof Freight) && ((Freight) entity).getCargoType() == EnumCargoTypes.CargoTypes.GRAIN) {
			Item item = itemstack.getItem();
			if (item == Items.WHEAT || item == Items.WHEAT_SEEDS || item == Items.MELON_SEEDS
					|| item == Items.PUMPKIN_SEEDS || item instanceof ItemSeeds) {
				return true;
			}
			return cropStuff(itemstack);
		}
		if ((entity instanceof Freight) && ((Freight) entity).getCargoType() == EnumCargoTypes.CargoTypes.BLOCKS) {
				return block.getDefaultState().isOpaqueCube();
		}


		if ((entity instanceof Freight) && ((Freight) entity).getCargoType() == EnumCargoTypes.CargoTypes.STONE) {
			return CommonUtil.getMaterial(block) == Material.ROCK;
		}
		if ((entity instanceof Freight) && ((Freight) entity).getCargoType() == EnumCargoTypes.CargoTypes.ICE) {
			return CommonUtil.getMaterial(block) == Material.ICE || CommonUtil.getMaterial(block) == Material.PACKED_ICE;
		}

		else {
			return true;
		}
	}

	private static boolean hasOre(int[] ids, int id) {
		for (int i : ids) {
			if (i == id) {
				return true;
			}
		}
		return false;
	}

	private static boolean cropStuff(ItemStack itemstack) {
		String[] names = new String[] { "cropCorn", "cropRice", "seedRice", "seedCorn", "listAllseed" };
		for (String name: names) {
			if (hasOre(OreDictionary.getOreIDs(itemstack), OreDictionary.getOreID(name))) {
				return true;
			}
		}
		return false;
	}
	private static boolean powderWood(ItemStack itemstack){
		String[] names = new String[] { "dustWood", "pulpWood"};
		for (String name: names){
			if (hasOre(OreDictionary.getOreIDs(itemstack), OreDictionary.getOreID(name))) {
				return true;
			}
		}
		return false;
	}
	/*private static boolean oreBlocks(ItemStack itemstack){
		String[] names = new String[] { "oreCoal", "oreIron", "oreCopper", "oreGold", "oreLapis", "oreDiamond", "oreRedstone",
				"oreEmerald", "oreQuartz", "oreAluminum", "oreSilver", "oreLead", "oreTin", "oreBauxite", "orePlatinum", "orePetroleum",
				"oreMythril", "oreCobalt", "oreFirestone", "oreSulfur", "oreOsmium", "oreSaltpeter", "orePoorLead", "orePoorTin",
				"orePoorCopper", "orePoorGold", "orePoorIron", "oreYellorite", "oreYellorium", "oreRuby", "orePeridot", "oreTopaz", "oreTanzanite",
				"oreMalachite", "oreSapphire", "oreAmber", "oreApatite", "oreUranium", "oreNickel", "oreZinc", "oreElectrotine", "oreTitaniumOre",
				"oreCinnabar", "orePlatinum", "oreCertusQuartz", "oreChargedCertusQuartz", "oreVinteum", "oreVietnam", "oreChimerite", "oreBlueTopaz",
				"oreMoonstone", "oreSunstone", "oreDraconium"};
		for (String name: names){
			if (hasOre(OreDictionary.getOreIDs(itemstack), OreDictionary.getOreID(name))) {
				return true;
			}
		}
		return false;
	}*/
	private static LinkedList<String> OREDICT_ORES = null;

	public static boolean oreBlocks(ItemStack i){
		if (OREDICT_ORES==null){
			OREDICT_ORES = new LinkedList<String>();
		}
		if (!OREDICT_ORES.contains(i.getTranslationKey())){
			for(Integer ore : OreDictionary.getOreIDs(i)) {
				if (OreDictionary.getOreName(ore).toLowerCase().startsWith("ore")){
					OREDICT_ORES.add(i.getTranslationKey());
					return true;
				}
			}
		}
		return OREDICT_ORES.contains(i.getTranslationKey());
	}

}
