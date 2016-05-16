/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package src.train.common.core.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import src.train.common.library.BlockIDs;
import src.train.common.library.ItemIDs;

public class OreHandler {

	public static void registerOres() {
		
		OreDictionary.registerOre("oreCopper", new ItemStack(BlockIDs.oreTC.block, 1, 0));
		OreDictionary.registerOre("oreOilsands", new ItemStack(BlockIDs.oreTC.block, 1, 1));
		OreDictionary.registerOre("orePetroleum", new ItemStack(BlockIDs.oreTC.block, 1, 2));

		OreDictionary.registerOre("ingotCopper", new ItemStack(ItemIDs.ingotCopper.item));

		OreDictionary.registerOre("ingotSteel", new ItemStack(ItemIDs.steel.item));
		
		OreDictionary.registerOre("dustPlastic", new ItemStack(ItemIDs.rawPlastic.item));
		
		OreDictionary.registerOre("dustCoal", new ItemStack(ItemIDs.coaldust.item));
	}

	public static String getFirstOreDictEntry(ItemStack stack){
		int[] oreArray = OreDictionary.getOreIDs(stack);
		if(oreArray.length > 0){
			return OreDictionary.getOreName(oreArray[0]);
		}
		return "";
	}
}
