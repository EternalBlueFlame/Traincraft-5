/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 *
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.core.handlers;

import train.common.core.util.TCRecipes;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import train.common.blocks.TCBlocks;
import train.common.inventory.TrainCraftingManager;
import train.common.library.BlockIDs;
import train.common.library.ItemIDs;
import train.common.recipes.RecipesArmorDyes;

import java.util.ArrayList;
import java.util.List;

import static train.common.recipes.AssemblyTableRecipes.waterContainers;

public class RecipeHandler {

    private static ArrayList<ItemStack> multiNameOreDict(String... names) {
        ArrayList<ItemStack> entries = new ArrayList<>();
        for (String name : names) {
            entries.addAll(OreDictionary.getOres(name));

        }
        return entries;
    }

    public static void initBlockRecipes() {
        java.util.List<ItemStack> iron = OreDictionary.getOres("ingotIron");

        TrainCraftingManager.instance.getRecipeList().add(new RecipesArmorDyes());
        /* Assembly tables */
        for (ItemStack ironingot : iron) {
            TCRecipes.shaped(new ItemStack(TCBlocks.assemblyTableI, 1), "IPI", "S S", "SPS", 'I', ironingot, 'P', Blocks.PISTON, 'S', Blocks.STONE);
        }
        TCRecipes.shaped(new ItemStack(TCBlocks.assemblyTableII, 1), "GPG", "O O", "OPO", 'G', Items.GOLD_INGOT, 'P', Blocks.PISTON, 'O', Blocks.OBSIDIAN);
        TCRecipes.shaped(new ItemStack(TCBlocks.assemblyTableIII, 1), "GPG", "DLD", "OPO", 'G', Items.GOLD_INGOT, 'P', Blocks.PISTON, 'D', Items.DIAMOND, 'L', Blocks.GLOWSTONE, 'O', Blocks.OBSIDIAN);

        if (!ConfigHandler.DISABLE_TRAIN_WORKBENCH) {
            for (ItemStack ironingot : iron) {
                addDictRecipe(new ItemStack(TCBlocks.trainWorkbench, 1), "###", "IFI", "###", '#', "plankWood", 'F', Blocks.FURNACE, 'I', ironingot);
            }
        }
        addDictRecipe(new ItemStack(TCBlocks.distilIdle, 1), "###", "#F#", "###", '#', "ingotSteel", 'F', ItemIDs.firebox.item);

        /* Open Hearth Furnace */
        if (!ConfigHandler.MAKE_MODPACKS_GREAT_AGAIN) {
            TCRecipes.shaped(new ItemStack(TCBlocks.openFurnaceIdle, 1), "#L#", "#B#", "#I#",
                    '#', Blocks.NETHER_BRICK, 'L', Items.LAVA_BUCKET,
                    'B', Items.BUCKET, 'I', Blocks.IRON_BLOCK);
        }

        /* Lantern */
        for (ItemStack ironingot : iron) {
            TCRecipes.shaped(new ItemStack(TCBlocks.lantern, 4), "III", "PTP", "III", 'I', ironingot, 'P', Blocks.GLASS_PANE, 'T', Blocks.TORCH);
        }

        /* Clothes */
        TCRecipes.shaped(new ItemStack(ItemIDs.overalls.item, 1), " # ", "X$X", "X$X", 'X', new ItemStack(Items.DYE, 1, 4), '$', Items.LEATHER_LEGGINGS, '#', new ItemStack(Items.DYE, 1, 1));
        TCRecipes.shaped(new ItemStack(ItemIDs.jacket.item, 1), "X X", "X$X", "X#X", 'X', new ItemStack(Items.DYE, 1, 14), '$', Items.LEATHER_CHESTPLATE, '#', Items.STRING);
        TCRecipes.shaped(new ItemStack(ItemIDs.hat.item, 1), " X ", "X$X", "#X#", 'X', new ItemStack(Items.DYE, 1, 4), '$', Items.LEATHER_HELMET, '#', Items.STRING);

        /* Driver Clothes*/
        TCRecipes.shaped(new ItemStack(ItemIDs.pants_driver_paintable.item, 1), "XXX", "XLX", "X$X", 'L', Items.LEATHER_LEGGINGS, '$', new ItemStack(Items.DYE, 1, 4), 'X', Items.STRING);
        TCRecipes.shaped(new ItemStack(ItemIDs.jacket_driver_paintable.item, 1), "X X", "XRX", "XPX", 'X', new ItemStack(Items.DYE, 1, 4), 'P', Items.LEATHER_CHESTPLATE, 'R', new ItemStack(Items.DYE, 1, 1));
        TCRecipes.shaped(new ItemStack(ItemIDs.hat_driver_paintable.item, 1), "#$#", "# #", '$', new ItemStack(Items.DYE, 1, 4), '#', Items.STRING);

        /* Ticket Man Clothes */
        TCRecipes.shaped(new ItemStack(ItemIDs.pants_ticketMan_paintable.item, 1), "XXX", "XLX", "X$X", 'L', Items.LEATHER_LEGGINGS, '$', new ItemStack(Items.DYE, 1, 8), 'X', Items.STRING);
        TCRecipes.shaped(new ItemStack(ItemIDs.jacket_ticketMan_paintable.item, 1), "X X", "XPX", "X#X", 'P', Items.LEATHER_CHESTPLATE, '#', new ItemStack(Items.DYE, 1, 4), 'X', Items.STRING);
        TCRecipes.shaped(new ItemStack(ItemIDs.hat_ticketMan_paintable.item, 1), "#$#", "# #", '$', new ItemStack(Items.DYE, 1, 0), '#', Items.STRING);

        /* Recipe book */
        TCRecipes.shaped(new ItemStack(ItemIDs.recipeBook.item, 1), "TTT", "TBT", "TTT", 'T', Blocks.RAIL, 'B', Items.BOOK);


        // Track recipes for crafting table - TheDoctor1138
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSmallStraight.item, 16),  "G G", "BPB", "G G", Character.valueOf('G'), Items.IRON_INGOT, Character.valueOf('P'), Blocks.PLANKS, Character.valueOf('B'), ItemIDs.bolt.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailMediumStraight.item, 1),  "G  ", "G  ", "G  ", Character.valueOf('G'), ItemIDs.tcRailSmallStraight.item );
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailLongStraight.item, 1),  "G  ", "G  ", "   ", Character.valueOf('G'), ItemIDs.tcRailMediumStraight.item );
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailVeryLongStraight.item, 1), "G  ", "G  ", "   ", Character.valueOf('G'), ItemIDs.tcRailLongStraight.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSmallDiagonalStraight.item, 1), " G ", "G  ", "   ", 'G', ItemIDs.tcRailSmallStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailMediumDiagonalStraight.item, 1), "  G", " G ", "G  ", 'G', ItemIDs.tcRailSmallDiagonalStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailLongDiagonalStraight.item, 1), " G ", "G  ", "   ", 'G', ItemIDs.tcRailMediumDiagonalStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailVeryLongDiagonalStraight.item, 1), " G ", "G  ", "   ", 'G', ItemIDs.tcRailLongDiagonalStraight.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRail1X1Turn.item, 1),  "GG ", "G  ", "   ", 'G', ItemIDs.tcRailSmallStraight.item );
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailMediumTurn.item, 1),  "GG ", "G  ", "   ", Character.valueOf('G'), ItemIDs.tcRail1X1Turn.item );
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailLargeTurn.item, 1), " SS", "SS ", "S  ", Character.valueOf('S'), ItemIDs.tcRailSmallStraight.item );
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailVeryLargeTurn.item, 1), "GG ","G  ","   ", Character.valueOf('G'), ItemIDs.tcRailLargeTurn.item );
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSuperLargeTurn.item, 1), "GG ","G  ","   ", Character.valueOf('G'), ItemIDs.tcRailVeryLargeTurn.item );
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRail29X29Turn.item, 1), "GG ","G  ","   ", Character.valueOf('G'), ItemIDs.tcRailSuperLargeTurn.item );
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRail32X32Turn.item, 1), "GG ","G  ","   ", Character.valueOf('G'), ItemIDs.tcRail29X29Turn.item );

        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailMedium45DegreeTurn.item, 2), ItemIDs.tcRailMediumTurn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailLarge45DegreeTurn.item, 2), ItemIDs.tcRailLargeTurn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailVeryLarge45DegreeTurn.item, 2), ItemIDs.tcRailVeryLargeTurn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailSuperLarge45DegreeTurn.item, 2), ItemIDs.tcRailSuperLargeTurn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRail45DegreeTurn9x20.item, 2), ItemIDs.tcRail29X29Turn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRail45DegreeTurn10x22.item,2),ItemIDs.tcRail32X32Turn.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSmallParallelCurve.item, 1), " S ", "SS ", "S  ", 'S', ItemIDs.tcRailSmallStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailMediumParallelCurve.item, 1), " S ", "S  ", "   ", 'S', ItemIDs.tcRailSmallParallelCurve.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailLargeParallelCurve.item, 1), " S ", "S  ", "   ", 'S', ItemIDs.tcRailMediumParallelCurve.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRail20x2SCurve.item,1), "S  ", " X ", "  S", 'S', ItemIDs.tcRailSmallParallelCurve.item, 'X', ItemIDs.tcRailMediumStraight.item);


        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailMediumSwitch.item, 1), "G  ", "GHG", "G  ", 'G', ItemIDs.tcRailSmallStraight.item, 'H', ItemIDs.tcRailMediumTurn.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailMediumParallelSwitch.item, 1), "I G", "IHH", "IH ", 'G', ItemIDs.tcRailSmallStraight.item, 'H', ItemIDs.tcRailMediumTurn.item, 'I', ItemIDs.tcRailMediumStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailLargeParallelSwitch.item, 1), "I G", "IHH", "IH ", 'G', ItemIDs.tcRailSmallStraight.item, 'H', ItemIDs.tcRailLargeTurn.item, 'I', ItemIDs.tcRailLongStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailLargeSwitch.item, 1), "G  ", "HIG", "G  ", 'G', ItemIDs.tcRailSmallStraight.item, 'H', ItemIDs.tcRailMediumStraight.item, 'I', ItemIDs.tcRailLargeTurn.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailVeryLargeSwitch.item, 1), "H  ", "HIG", "H  ", 'G', ItemIDs.tcRailSmallStraight.item, 'H', ItemIDs.tcRailMediumStraight.item, 'I', ItemIDs.tcRailVeryLargeTurn.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailMedium45DegreeSwitch.item, 1), "G D", "GH ", "G  ", 'G', ItemIDs.tcRailSmallStraight.item, 'H', ItemIDs.tcRailMedium45DegreeTurn.item, 'D', ItemIDs.tcRailSmallDiagonalStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailLarge45DegreeSwitch.item, 1), "G D", "GH ", "G  ", 'G', ItemIDs.tcRailSmallStraight.item, 'H', ItemIDs.tcRailLarge45DegreeTurn.item, 'D', ItemIDs.tcRailSmallDiagonalStraight.item);


        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSlopeWood.item, 1), " TP", "TPP", "PPP", 'T', ItemIDs.tcRailMediumStraight.item, 'P', Blocks.PLANKS);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailLargeSlopeWood.item, 1), "   ", "  T", " T ", 'T', ItemIDs.tcRailSlopeWood.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailVeryLargeSlopeWood.item, 1), "  T", " T ", "T  ", 'T', ItemIDs.tcRailSlopeWood.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSlopeSnowGravel.item, 1), " TS", "TSS", "SSS", 'T', ItemIDs.tcRailMediumStraight.item, 'S', new ItemStack(BlockIDs.snowGravel.block, 1, 0));
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailLargeSlopeSnowGravel.item, 1), "   ", "  T", " T ", 'T', ItemIDs.tcRailSlopeSnowGravel.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailVeryLargeSlopeSnowGravel.item, 1), "  T", " T ", "T  ", 'T', ItemIDs.tcRailSlopeSnowGravel.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSlopeGravel.item, 1), " TG", "TGG", "GGG", 'T', ItemIDs.tcRailMediumStraight.item, 'G', Blocks.GRAVEL);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailLargeSlopeGravel.item, 1), "   ", "  S", " S ", 'S', ItemIDs.tcRailSlopeGravel.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailVeryLargeSlopeGravel.item, 1), "  T", " T ", "T  ", 'T', ItemIDs.tcRailSlopeGravel.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSlopeDynamic.item, 1), " TG", "TGG", "GGG", 'T', ItemIDs.tcRailMediumStraight.item, 'G', new ItemStack(Items.DYE, 1, 1));
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailLargeSlopeDynamic.item, 1), "   ", "  S", " S ", 'S', ItemIDs.tcRailSlopeDynamic.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailVeryLargeSlopeDynamic.item, 1), "  T", " T ", "T  ", 'T', ItemIDs.tcRailSlopeDynamic.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSlopeBallast.item, 1), " TG", "TGG", "GGG", 'T', ItemIDs.tcRailMediumStraight.item, 'G', new ItemStack(BlockIDs.oreTC.block, 1, 3));
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailLargeSlopeBallast.item, 1), "   ", "  S", " S ", 'S', ItemIDs.tcRailSlopeBallast.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailVeryLargeSlopeBallast.item, 1), "  T", " T ", "T  ", 'T', ItemIDs.tcRailSlopeBallast.item);


        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailLargeCurvedSlopeDynamic.item, 1), "   ", " T ", " S ", 'T', ItemIDs.tcRailLargeTurn.item, 'S', ItemIDs.tcRailSlopeDynamic.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailVeryLargeCurvedSlopeDynamic.item, 1), "   ", " T ", " S ", 'T', ItemIDs.tcRailVeryLargeTurn.item, 'S', ItemIDs.tcRailLargeSlopeDynamic.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSuperLargeCurvedSlopeDynamic.item, 1), "   ", " T ", " S ", 'T', ItemIDs.tcRailSuperLargeTurn.item, 'S', ItemIDs.tcRailVeryLargeSlopeDynamic.item);


        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailTwoWaysCrossing.item, 1), " S ", "SSS", " S ", 'S', ItemIDs.tcRailSmallStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailDiagonalTwoWaysCrossing.item, 1), "D D", " D ", "D D", 'D', ItemIDs.tcRailSmallDiagonalStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailDiamondCrossing.item, 1), " SD", " S ", "DS ", 'S', ItemIDs.tcRailSmallStraight.item, 'D', ItemIDs.tcRailSmallDiagonalStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailDiamondCrossing.item, 1), "DS ", " S ", " SD", 'S', ItemIDs.tcRailSmallStraight.item, 'D', ItemIDs.tcRailSmallDiagonalStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailDoubleDiamondCrossing.item, 1), "DSD", " S ", "DSD", 'S', ItemIDs.tcRailSmallStraight.item, 'D', ItemIDs.tcRailSmallDiagonalStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailFourWaysCrossing.item, 1), "DSD", "SDS", "DSD", 'S', ItemIDs.tcRailSmallStraight.item, 'D', ItemIDs.tcRailSmallDiagonalStraight.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedSmallStraight.item, 16), "I I", "B B", "I I", 'I', Items.IRON_INGOT, 'B', ItemIDs.bolt.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedMediumStraight.item, 1), "G  ", "G  ", "G  ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedLongStraight.item, 1), "G  ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedMediumStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedVeryLongStraight.item, 1), "G  ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedLongStraight.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item, 1), " G ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedMediumDiagonalStraight.item, 1), "  G", " G ", "G  ", 'G', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedLongDiagonalStraight.item, 1), " G ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedMediumDiagonalStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedVeryLongDiagonalStraight.item, 1), " G ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedLongDiagonalStraight.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbedded1X1Turn.item, 1),  "GG ", "G  ", "   ", 'G', ItemIDs.tcRailSmallStraight.item );
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedMediumTurn.item, 1), "GG ", "G  ", "   ", 'G', ItemIDs.tcRailEmbedded1X1Turn.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedLargeTurn.item, 1), " SS", "SS ", "S  ", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedVeryLargeTurn.item, 1), "GG ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedLargeTurn.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedSuperLargeTurn.item, 1), "GG ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedVeryLargeTurn.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbedded29X29Turn.item, 1), "GG ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedSuperLargeTurn.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbedded32X32Turn.item, 1), "GG ", "G  ", "   ", 'G', ItemIDs.tcRailEmbedded29X29Turn.item);

        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailEmbeddedMedium45DegreeTurn.item, 1), ItemIDs.tcRailEmbeddedMediumTurn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailEmbeddedLarge45DegreeTurn.item, 1), ItemIDs.tcRailEmbeddedLargeTurn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailEmbeddedVeryLarge45DegreeTurn.item, 1), ItemIDs.tcRailEmbeddedVeryLargeTurn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailEmbeddedSuperLarge45DegreeTurn.item, 1), ItemIDs.tcRailEmbeddedSuperLargeTurn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailEmbedded45DegreeTurn9x20.item, 2),ItemIDs.tcRailEmbedded29X29Turn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailEmbedded45DegreeTurn10x22.item,2),ItemIDs.tcRailEmbedded32X32Turn.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedSmallParallelCurve.item, 1), " S ", "SS ", "S  ", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedMediumParallelCurve.item, 1), " S ", "SS ", "S  ", 'S', ItemIDs.tcRailEmbeddedSmallParallelCurve.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedLargeParallelCurve.item, 1), " S ", "SS ", "S  ", 'S', ItemIDs.tcRailEmbeddedMediumParallelCurve.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbedded20x2SCurve.item,1), "S  ", " X ", "  S", 'S', ItemIDs.tcRailSmallParallelCurve.item, 'X', ItemIDs.tcRailMediumStraight.item);


        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedMediumSwitch.item, 1), "G  ", "GHG", "G  ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item, 'H', ItemIDs.tcRailEmbeddedMediumTurn.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedMediumParallelSwitch.item, 1), "I G", "IHH", "IH ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item, 'H', ItemIDs.tcRailEmbeddedMediumTurn.item, 'I', ItemIDs.tcRailEmbeddedMediumStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedLargeParallelSwitch.item, 1), "I G", "IHH", "IH ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item, 'H', ItemIDs.tcRailEmbeddedLargeTurn.item, 'I', ItemIDs.tcRailEmbeddedLongStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedLargeSwitch.item, 1), "G  ", "HIG", "G  ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item, 'H', ItemIDs.tcRailEmbeddedMediumStraight.item, 'I', ItemIDs.tcRailEmbeddedLargeTurn.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedVeryLargeSwitch.item, 1), "H  ", "HIG", "H  ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item, 'H', ItemIDs.tcRailEmbeddedMediumStraight.item, 'I', ItemIDs.tcRailEmbeddedLargeTurn.item);

        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailEmbeddedMedium45DegreeTurn.item, 2), ItemIDs.tcRailEmbeddedMediumTurn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailEmbeddedLarge45DegreeTurn.item, 2), ItemIDs.tcRailEmbeddedLargeTurn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailEmbeddedVeryLarge45DegreeTurn.item, 2), ItemIDs.tcRailEmbeddedVeryLargeTurn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailEmbeddedSuperLarge45DegreeTurn.item, 2), ItemIDs.tcRailEmbeddedSuperLargeTurn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailEmbedded45DegreeTurn9x20.item, 2), ItemIDs.tcRailEmbedded29X29Turn.item);
        TCRecipes.shapeless(new ItemStack(ItemIDs.tcRailEmbedded45DegreeTurn10x22.item,2),ItemIDs.tcRailEmbedded32X32Turn.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedSlopeDynamic.item, 1), " TG", "TGG", "GGG", 'T', ItemIDs.tcRailEmbeddedMediumStraight.item, 'G', new ItemStack(Items.DYE, 1, 1));
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedLargeSlopeDynamic.item, 1), "   ", "  S", " S ", 'S', ItemIDs.tcRailEmbeddedSlopeDynamic.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedVeryLargeSlopeDynamic.item, 1), "  T", " T ", "T  ", 'T', ItemIDs.tcRailEmbeddedSlopeDynamic.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedLargeCurvedSlopeDynamic.item, 1), "   ", " T ", " S ", 'T', ItemIDs.tcRailEmbeddedLargeTurn.item, 'S', ItemIDs.tcRailEmbeddedSlopeDynamic.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedVeryLargeCurvedSlopeDynamic.item, 1), "   ", " T ", " S ", 'T', ItemIDs.tcRailEmbeddedVeryLargeTurn.item, 'S', ItemIDs.tcRailEmbeddedLargeSlopeDynamic.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedSuperLargeCurvedSlopeDynamic.item, 1), "   ", " T ", " S ", 'T', ItemIDs.tcRailEmbeddedSuperLargeTurn.item, 'S', ItemIDs.tcRailEmbeddedVeryLargeSlopeDynamic.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedTwoWaysCrossing.item, 1), " S ", "SSS", " S ", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedDiagonalTwoWaysCrossing.item, 1), "D D", " D ", "D D", 'D', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedDiamondCrossing.item, 1), " SD", " S ", "DS ", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item, 'D', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedDiamondCrossing.item, 1), "DS ", " S ", " SD", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item, 'D', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedDoubleDiamondCrossing.item, 1), "DSD", " S ", "DSD", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item, 'D', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailEmbeddedFourWaysCrossing.item, 1), "DSD", "SDS", "DSD", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item, 'D', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);


        //Track Conversion Recipes to Normal Rails

        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSmallStraight.item, 1), "   ", " R ", "   ", 'R', Item.getItemFromBlock(Blocks.RAIL));// small straight track
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSmallRoadCrossing.item, 1), "   ", "SRS", "   ", 'S', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 15), 'R', Item.getItemFromBlock(Blocks.RAIL));
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSmallRoadCrossing1.item, 1), "   ", "SRS", "   ", 'S', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 7), 'R', Item.getItemFromBlock(Blocks.RAIL));
        TCRecipes.shaped(new ItemStack(ItemIDs.tcRailSmallRoadCrossing2.item, 1), "   ", "SRS", "   ", 'S', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 3), 'R', Item.getItemFromBlock(Blocks.RAIL));
        TCRecipes.shaped(new ItemStack(Item.getItemFromBlock(Blocks.RAIL), 1), "   ", " R ", "   ", 'R', ItemIDs.tcRailSmallStraight.item);
        TCRecipes.shaped(new ItemStack(Item.getItemFromBlock(Blocks.RAIL), 1), "   ", " R ", "   ", 'R', ItemIDs.tcRailSmallRoadCrossing.item);
        TCRecipes.shaped(new ItemStack(Item.getItemFromBlock(Blocks.RAIL), 1), "   ", " R ", "   ", 'R', ItemIDs.tcRailSmallRoadCrossing1.item);
        TCRecipes.shaped(new ItemStack(Item.getItemFromBlock(Blocks.RAIL), 1), "   ", " R ", "   ", 'R', ItemIDs.tcRailSmallRoadCrossing2.item);

        TCRecipes.shaped(new ItemStack(ItemIDs.paintbrushThing.item, 1), "GB ", "RIS", " ST", 'G', new ItemStack(Items.DYE, 1, 2), 'B', new ItemStack(Items.DYE, 1, 4), 'R', new ItemStack(Items.DYE, 1, 1), 'I', new ItemStack(Items.IRON_INGOT), 'S', new ItemStack(Items.STRING), 'T', new ItemStack(Items.STICK));
        TCRecipes.shapeless(new ItemStack(BlockIDs.poweredGravel.block, 1), new ItemStack(Items.REDSTONE), new ItemStack(Blocks.GRAVEL));
        TCRecipes.shapeless(new ItemStack(BlockIDs.snowGravel.block, 1), new ItemStack(Items.SNOWBALL), new ItemStack(Blocks.GRAVEL));
        TCRecipes.shapeless(new ItemStack(BlockIDs.snowGravel.block, 4), new ItemStack(Blocks.SNOW), new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.GRAVEL));
        TCRecipes.shapeless(new ItemStack(BlockIDs.asphalt.block, 8), new ItemStack(ItemIDs.coaldust.item), new ItemStack(ItemIDs.coaldust.item), new ItemStack(ItemIDs.coaldust.item), new ItemStack(ItemIDs.coaldust.item), new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.COBBLESTONE));

        TCRecipes.shaped(new ItemStack(BlockIDs.asphaltSlab.block, 6), "BBB", "   ", "   ", 'B', new ItemStack(BlockIDs.asphalt.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.asphaltSlab.block, 6), "   ", "BBB", "   ", 'B', new ItemStack(BlockIDs.asphalt.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.asphaltSlab.block, 6), "   ", "   ", "BBB", 'B', new ItemStack(BlockIDs.asphalt.block));

        TCRecipes.shaped(new ItemStack(BlockIDs.ballastSlab.block, 6, 0), "BBB", "   ", "   ", 'B', new ItemStack(BlockIDs.oreTC.block, 1,3));
        TCRecipes.shaped(new ItemStack(BlockIDs.ballastSlab.block, 6, 0), "   ", "BBB", "   ", 'B', new ItemStack(BlockIDs.oreTC.block, 1,3));
        TCRecipes.shaped(new ItemStack(BlockIDs.ballastSlab.block, 6, 0), "   ", "   ", "BBB", 'B', new ItemStack(BlockIDs.oreTC.block, 1,3));

        TCRecipes.shaped(new ItemStack(BlockIDs.dirtyBallastSlab.block, 6), "BBB", "   ", "   ", 'B', new ItemStack(BlockIDs.dirtyBallast.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.dirtyBallastSlab.block, 6), "   ", "BBB", "   ", 'B', new ItemStack(BlockIDs.dirtyBallast.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.dirtyBallastSlab.block, 6), "   ", "   ", "BBB", 'B', new ItemStack(BlockIDs.dirtyBallast.block));

        TCRecipes.shaped(new ItemStack(BlockIDs.dirtierBallastSlab.block, 6), "BBB", "   ", "   ", 'B', new ItemStack(BlockIDs.dirtierBallast.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.dirtierBallastSlab.block, 6), "   ", "BBB", "   ", 'B', new ItemStack(BlockIDs.dirtierBallast.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.dirtierBallastSlab.block, 6), "   ", "   ", "BBB", 'B', new ItemStack(BlockIDs.dirtierBallast.block));

        TCRecipes.shaped(new ItemStack(BlockIDs.highSpeedBallastSlab.block, 6), "BBB", "   ", "   ", 'B', new ItemStack(BlockIDs.highSpeedBallast.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.highSpeedBallastSlab.block, 6), "   ", "BBB", "   ", 'B', new ItemStack(BlockIDs.highSpeedBallast.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.highSpeedBallastSlab.block, 6), "   ", "   ", "BBB", 'B', new ItemStack(BlockIDs.highSpeedBallast.block));

        TCRecipes.shaped(new ItemStack(BlockIDs.snowGravelSlab.block, 6), "BBB", "   ", "   ", 'B', new ItemStack(BlockIDs.snowGravel.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.snowGravelSlab.block, 6), "   ", "BBB", "   ", 'B', new ItemStack(BlockIDs.snowGravel.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.snowGravelSlab.block, 6), "   ", "   ", "BBB", 'B', new ItemStack(BlockIDs.snowGravel.block));

        TCRecipes.shaped(new ItemStack(BlockIDs.ballastStairs.block, 4), "B  ", "BB ", "BBB", 'B', new ItemStack(BlockIDs.oreTC.block, 1,3));
        TCRecipes.shaped(new ItemStack(BlockIDs.ballastStairs.block, 4), "  B", " BB", "BBB", 'B', new ItemStack(BlockIDs.oreTC.block, 1,3));

        TCRecipes.shaped(new ItemStack(BlockIDs.dirtyBallastStairs.block, 4), "B  ", "BB ", "BBB", 'B', new ItemStack(BlockIDs.dirtyBallast.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.dirtyBallastStairs.block, 4), "  B", " BB", "BBB", 'B', new ItemStack(BlockIDs.dirtyBallast.block));

        TCRecipes.shaped(new ItemStack(BlockIDs.dirtierBallastStairs.block, 4), "B  ", "BB ", "BBB", 'B', new ItemStack(BlockIDs.dirtierBallast.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.dirtierBallastStairs.block, 4), "  B", " BB", "BBB", 'B', new ItemStack(BlockIDs.dirtierBallast.block));

        TCRecipes.shaped(new ItemStack(BlockIDs.highSpeedBallastStairs.block, 4), "B  ", "BB ", "BBB", 'B', new ItemStack(BlockIDs.highSpeedBallast.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.highSpeedBallastStairs.block, 4), "  B", " BB", "BBB", 'B', new ItemStack(BlockIDs.highSpeedBallast.block));

        TCRecipes.shaped(new ItemStack(BlockIDs.snowGravelStairs.block, 4), "B  ", "BB ", "BBB", 'B', new ItemStack(BlockIDs.snowGravel.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.snowGravelStairs.block, 4), "  B", " BB", "BBB", 'B', new ItemStack(BlockIDs.snowGravel.block));

        TCRecipes.shaped(new ItemStack(BlockIDs.asphaltStairs.block, 4), "B  ", "BB ", "BBB", 'B', new ItemStack(BlockIDs.asphalt.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.asphaltStairs.block, 4), "  B", " BB", "BBB", 'B', new ItemStack(BlockIDs.asphalt.block));
        TCRecipes.shaped(new ItemStack(BlockIDs.signalSpanish.block, 1), " L ", " I ", "RIR", 'L', new ItemStack(Blocks.REDSTONE_LAMP), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE));
        TCRecipes.shaped(new ItemStack(BlockIDs.kSignal.block, 1), "RIR", " I ", " L ", 'L', new ItemStack(Blocks.REDSTONE_LAMP), 'I', new ItemStack(Items.IRON_INGOT), 'R', new ItemStack(Items.REDSTONE));
        TCRecipes.shaped(new ItemStack(TCBlocks.bridgePillar, 8), "SSS", "SLS", "SSS", 'S', new ItemStack(Items.STICK), 'L', new ItemStack(Blocks.LOG));
        TCRecipes.shaped(new ItemStack(TCBlocks.speedSign, 8), " R ", " I ", " I ", 'R', new ItemStack(Items.DYE, 1, 1), 'I', new ItemStack(Items.IRON_INGOT));

        //Gravel
        TCRecipes.shapeless(new ItemStack(BlockIDs.oreTC.block, 4, 3), Blocks.GRAVEL, Blocks.GRAVEL, Items.CLAY_BALL);
        TCRecipes.shapeless(new ItemStack(BlockIDs.oreTC.block, 16, 3), Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.CLAY);
        TCRecipes.shapeless(new ItemStack(BlockIDs.highSpeedBallast.block, 16), Items.QUARTZ, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.CLAY);
        TCRecipes.shapeless(new ItemStack(BlockIDs.dirtyBallast.block, 16), Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.GRAVEL, Blocks.DIRT);
        TCRecipes.shapeless(new ItemStack(BlockIDs.dirtierBallast.block, 16), new ItemStack(BlockIDs.dirtyBallast.block, 1), new ItemStack(BlockIDs.dirtyBallast.block, 1), new ItemStack(BlockIDs.dirtyBallast.block, 1), new ItemStack(BlockIDs.dirtyBallast.block, 1), new ItemStack(BlockIDs.dirtyBallast.block, 1), new ItemStack(BlockIDs.dirtyBallast.block, 1), new ItemStack(BlockIDs.dirtyBallast.block, 1), new ItemStack(BlockIDs.dirtyBallast.block, 1), Blocks.DIRT);

        for (ItemStack ironingot : iron) {
            addDictRecipe(new ItemStack(TCBlocks.switchStand, 1), " W ", " I ", " R ", 'W', Blocks.LEVER, 'R', Items.STICK, 'I', ironingot);
            addDictRecipe(new ItemStack(TCBlocks.MFPBWigWag, 1), " RW", "BWR", "AAA", 'A', new ItemStack(Items.STICK), 'B', Blocks.IRON_BARS, 'R', new ItemStack(Items.DYE, 1, 1), 'W', new ItemStack(Items.DYE, 1, 15));
            addDictRecipe(new ItemStack(TCBlocks.MFPBWigWag, 1), "IWI", " I ", " I ", 'W', Blocks.LEVER, 'I', ironingot);
            /*Buffer*/
            addDictRecipe(new ItemStack(BlockIDs.stopper.block, 1), "WWW", "I I", "RRR", 'W', "plankWood", 'R', Blocks.RAIL, 'I', ironingot);
            addDictRecipe(new ItemStack(BlockIDs.embeddedStopper.block, 1), "   ", "I I", "RRR", 'R', Blocks.RAIL, 'I', ironingot);
            addDictRecipe(new ItemStack(BlockIDs.americanstopper.block, 1), "III", "I I", "RRR", 'R', Blocks.RAIL, 'I', ironingot);
            addDictRecipe(new ItemStack(BlockIDs.metroMadridPole.block, 1), "III", " I ", "C C", 'I', ironingot, 'C', ItemIDs.ingotCopper.item);
        }

        TCRecipes.shaped(new ItemStack(ItemIDs.bolt.item, 16), " II", " I ", " I ", 'I', Items.IRON_INGOT);

    }

    public static void initItemRecipes() {

        java.util.List<ItemStack> steel = OreDictionary.getOres("ingotSteel");
        java.util.List<ItemStack> iron = OreDictionary.getOres("ingotIron");
        java.util.List<ItemStack> planks = OreDictionary.getOres("plankWood");
        java.util.List<ItemStack> logs = OreDictionary.getOres("logWood");
        ArrayList<ItemStack> plastics = multiNameOreDict("itemPlastic", "dustPlastic");//dustPlastic for MFR support
        java.util.List<ItemStack> copper = OreDictionary.getOres("ingotCopper");
        java.util.List<ItemStack> dustCoal = OreDictionary.getOres("dustCoal");
        List<ItemStack> coal = new ArrayList<ItemStack>();
        coal.add(new ItemStack(Items.COAL));
        coal.addAll(OreDictionary.getOres("coal"));
        java.util.List<ItemStack> redstone = OreDictionary.getOres("dustRedstone");
        ArrayList<ItemStack> waterbucket = waterContainers();
        // Always do this " X " instead of this "X", and do not put "" empty brackets

        /* I placed it here because workbench should be one of the first recipe shown in the recipe book */
        for (ItemStack plank : planks) {
            for (ItemStack ironingot : iron) {
                TrainCraftingManager.instance.addRecipe(new ItemStack(TCBlocks.trainWorkbench, 1), "###", "IFI", "###", '#', plank, 'F', Blocks.FURNACE, 'I', ironingot);
            }
        }

        /* Recipe book */
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.recipeBook.item, 1), "TTT", "TBT", "TTT", 'T', Blocks.RAIL, 'B', Items.BOOK);

        /* Chunk Loader Activator */
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.chunkLoaderActivator.item, 1), "  P", " S ", "S  ", 'S', Items.BLAZE_ROD, 'P', Items.ENDER_PEARL);

        // Switches -hariesh
        TCRecipes.shaped(new ItemStack(TCBlocks.owoYardSwitchStand, 1), "   ", "OS ", "IW ", 'O', new ItemStack(Items.DYE, 1, 14), 'S', Items.STICK, 'I', Items.IRON_INGOT, 'W', Blocks.PLANKS);
        TCRecipes.shaped(new ItemStack(TCBlocks.owoSwitchStand, 1), " O ", " S ", " IW", 'O', new ItemStack(Items.DYE, 1, 14), 'S', Items.STICK, 'I', Items.IRON_INGOT, 'W', Blocks.PLANKS);
        TCRecipes.shaped(new ItemStack(TCBlocks.circleSwitchStand, 1), " R ", " S ", " IW", 'R', new ItemStack(Items.DYE, 1, 1), 'S', Items.STICK, 'I', Items.IRON_INGOT, 'W', Blocks.PLANKS);
        TCRecipes.shaped(new ItemStack(TCBlocks.autoSwtichStand, 1), "   ", "C  ", "BIW", 'C', new ItemStack(ItemIDs.electronicCircuit.item, 1), 'B', Blocks.IRON_BLOCK, 'I', Items.IRON_INGOT, 'W', Blocks.PLANKS);
        TCRecipes.shaped(new ItemStack(ItemIDs.paintbrushThing.item, 1), "GB ", "RIS", " ST", 'G', new ItemStack(Items.DYE, 1, 2), 'B', new ItemStack(Items.DYE, 1, 4), 'R', new ItemStack(Items.DYE, 1, 1), 'I', new ItemStack(Items.IRON_INGOT), 'S', new ItemStack(Items.STRING), 'T', new ItemStack(Items.STICK));



        /* Assembly tables */

        for (ItemStack ironingot : iron) {
            TrainCraftingManager.instance.addRecipe(new ItemStack(TCBlocks.assemblyTableI, 1), "IPI", "S S", "SPS", 'I', ironingot, 'P', Blocks.PISTON, 'S', Blocks.STONE);
        }
        TrainCraftingManager.instance.addRecipe(new ItemStack(TCBlocks.assemblyTableII, 1), "GPG", "O O", "OPO", 'G', Items.GOLD_INGOT, 'P', Blocks.PISTON, 'O', Blocks.OBSIDIAN);
        TrainCraftingManager.instance.addRecipe(new ItemStack(TCBlocks.assemblyTableIII, 1), "GPG", "DLD", "OPO", 'G', Items.GOLD_INGOT, 'P', Blocks.PISTON, 'D', Items.DIAMOND, 'L', Blocks.GLOWSTONE, 'O', Blocks.OBSIDIAN);

        /* Open Hearth Furnace */
        TrainCraftingManager.instance.addRecipe(new ItemStack(TCBlocks.openFurnaceIdle, 1), "#L#", "#B#", "#I#", '#', Blocks.NETHER_BRICK, 'L', Items.LAVA_BUCKET, 'B', Items.BUCKET, 'I', Blocks.IRON_BLOCK);

        /* Lantern */
        for (ItemStack ironingot : iron) {
            TrainCraftingManager.instance.addRecipe(new ItemStack(TCBlocks.lantern, 4), "III", "PTP", "III", 'I', ironingot, 'P', Blocks.GLASS_PANE, 'T', Blocks.TORCH);
        }
        /* Clothes */
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.overalls.item, 1), " # ", "X$X", "X X", 'X', new ItemStack(Items.DYE, 1, 4), '$', Items.LEATHER_LEGGINGS, '#', new ItemStack(Items.DYE, 1, 1));
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.jacket.item, 1), "X X", "X$X", "X#X", 'X', new ItemStack(Items.DYE, 1, 14), '$', Items.LEATHER_CHESTPLATE, '#', Items.STRING);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.hat.item, 1), " X ", "X$X", "#X#", 'X', new ItemStack(Items.DYE, 1, 4), '$', Items.LEATHER_HELMET, '#', Items.STRING);

        /* Driver Clothes*/
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.pants_driver_paintable.item, 1), "XXX", "XLX", "X$X", 'L', Items.LEATHER_LEGGINGS, '$', new ItemStack(Items.DYE, 1, 4), 'X', Items.STRING);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.jacket_driver_paintable.item, 1), "X X", "XRX", "XPX", 'X', new ItemStack(Items.DYE, 1, 4), 'P', Items.LEATHER_CHESTPLATE, 'R', new ItemStack(Items.DYE, 1, 1));
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.hat_driver_paintable.item, 1), "#$#", "# #", '$', new ItemStack(Items.DYE, 1, 4), '#', Items.STRING);

        /* Ticket Man Clothes */
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.pants_ticketMan_paintable.item, 1), "XXX", "XLX", "X$X", 'L', Items.LEATHER_LEGGINGS, '$', new ItemStack(Items.DYE, 1, 8), 'X', Items.STRING);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.jacket_ticketMan_paintable.item, 1), "X X", "XPX", "X#X", 'P', Items.LEATHER_CHESTPLATE, '#', new ItemStack(Items.DYE, 1, 4), 'X', Items.STRING);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.hat_ticketMan_paintable.item, 1), "#$#", "# #", '$', new ItemStack(Items.DYE, 1, 0), '#', Items.STRING);


        for (ItemStack plastic : plastics) {
            /* Empty canister */
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.emptyCanister.item, 4), "PPP", "P P", "PPP", 'P', plastic);
            for (ItemStack rs : redstone) {
                /* Electronic circuit */
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.electronicCircuit.item, 1), "XXX", "RPR", "XXX", 'X', ItemIDs.copperWireFine.item, 'P', plastic, 'R', rs.getItem());
            }
            /* Composite Material*/
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.reinforcedPlastic.item, 16), "LPL", "PLP", "GPG", 'G', Blocks.GLASS_PANE, 'P', ItemIDs.graphite.item, 'L', plastic);

            if (copper != null) {
                for (ItemStack copp : copper) {
                    TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.copperWireFine.item, 6), "XXX", "XPX", "XXX", 'X', copp, 'P', plastic);
                    TCRecipes.shaped(new ItemStack(BlockIDs.overheadWire.block, 16), "IC ", "I  ", "I  ", 'I', new ItemStack(Items.IRON_INGOT, 1), 'C', copp);
                    TCRecipes.shaped(new ItemStack(BlockIDs.overheadWireDouble.block, 16), "ICI", "ICI", "I I", 'I', new ItemStack(Items.IRON_INGOT, 1), 'C', copp);

                }
            }
        }

        /* Composite Suit */
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.boots_suit_paintable.item, 1), " D ", "X X", "XFX", 'F', Items.FEATHER, 'D', Items.DIAMOND, 'X', ItemIDs.reinforcedPlates.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.pants_suit_paintable.item, 1), "XDX", "X$X", "X X", '$', Items.FIRE_CHARGE, 'X', ItemIDs.reinforcedPlates.item, 'D', Items.DIAMOND);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.jacket_suit_paintable.item, 1), "X X", "XDX", "XAX", 'A', Items.GOLDEN_APPLE, 'X', ItemIDs.reinforcedPlates.item, 'D', Blocks.DIAMOND_BLOCK);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.helmet_suit_paintable.item, 1), "#D#", "# #", 'D', Blocks.DIAMOND_BLOCK, '#', ItemIDs.reinforcedPlates.item);

        /* Trains parts */

        for (ItemStack ironingot : iron) {
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.generator.item, 1), " ##", "E$$", " ##", '#', ItemIDs.copperWireFine.item, 'E', ItemIDs.electronicCircuit.item, '$', ironingot);// generator
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.controls.item, 1), "#X#", "#E#", "$$$", '#', Blocks.LEVER, 'X', Blocks.STONE_BUTTON, '$', ironingot, 'E', ItemIDs.electronicCircuit.item);// train controls
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.electmotor.item, 1), "I#I", "#E#", "I#I", '#', ItemIDs.copperWireFine.item, 'I', ironingot, 'E', ItemIDs.electronicCircuit.item);// Electric motor
            for (ItemStack bucketWater : waterbucket) {
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.ironBoiler.item, 2), "###", "XXX", "###", '#', ironingot, 'X', bucketWater.getItem());// iron Boiler
            }
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.ironFirebox.item, 2), "###", "#X#", "###", '#', ironingot, 'X', Items.FLINT_AND_STEEL);// iron Firebox
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.ironChimney.item, 2), "# #", "# #", "# #", '#', ironingot);
        }
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.dieselengine.item, 2), "###", "XXX", "CCC", '#', ItemIDs.piston.item, 'X', ItemIDs.cylinder.item, 'C', ItemIDs.camshaft.item);// diesel engine
        for (ItemStack dustStack : dustCoal) {
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.graphite.item, 2), "###", "#X#", "###", '#', dustStack, 'X', Items.CLAY_BALL);// Graphite
        }

        if (!ConfigHandler.MAKE_MODPACKS_GREAT_AGAIN) {
            for (ItemStack c : coal) {
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.coaldust.item, 4),
                        "###", "   ", "   ", '#', c.getItem());
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.coaldust.item, 4),
                        "   ", "###", "   ", '#', c.getItem());
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.coaldust.item, 4),
                        "   ", "   ", "###", '#', c.getItem());
            }
        }
        //TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.signal.item, 2),  "#", "X", "X", Character.valueOf('X'), ItemIDs.steel.item, Character.valueOf('#'), rs );
        /* diesel generator */
        TrainCraftingManager.instance.addRecipe(new ItemStack(BlockIDs.generatorDiesel.block, 1), "C  ", "DE ", 'C', ItemIDs.steelchimney.item, 'D', ItemIDs.dieselengine.item, 'E', ItemIDs.electronicCircuit.item);

        /* Zepplin parts and zeppelin item */
        if (ConfigHandler.ENABLE_ZEPPELIN) {
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.balloon.item, 1), "###", "# #", "###", '#', Blocks.WOOL);// Balloon
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.steamengine.item, 1), "C  ", "BF ", 'C', ItemIDs.steelchimney.item, 'B', ItemIDs.boiler.item, 'F', ItemIDs.firebox.item);// Small steam engine
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.airship.item, 1), "B B", "SES", "POP", 'B', ItemIDs.balloon.item, 'S', Items.STICK, 'E', ItemIDs.steamengine.item, 'P', ItemIDs.propeller.item, 'O', Items.BOAT);
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.zeppelin.item, 1), "BBB", "SES", "POP", 'B', ItemIDs.balloon.item, 'S', ItemIDs.propeller.item, 'E', ItemIDs.controls.item, 'P', ItemIDs.electmotor.item, 'O', ItemIDs.seats.item);
        }

        for (ItemStack plankItem : planks) {
            for (ItemStack steelItem : steel) {

                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.reinforcedPlates.item, 1), "RRR", "SSS", "CCC", 'R', ItemIDs.reinforcedPlastic.item, 'S', steelItem, 'C', Items.CLAY_BALL);

                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.composite_wrench.item, 1), "S S", " R ", " R ", 'R', ItemIDs.reinforcedPlastic.item, 'S', steelItem);
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.steelcab.item, 2), "###", "X X", "XXX", 'X', steelItem, '#', plankItem);// Steel cab
                TrainCraftingManager.instance.addRecipe(new ItemStack(TCBlocks.distilIdle, 1), "###", "#F#", "###", '#', steelItem, 'F', ItemIDs.firebox.item);
                for (ItemStack rs : redstone) {
                    TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.transformer.item, 1), "# #", "XEX", "###", '#', steelItem, 'E', ItemIDs.electronicCircuit.item, 'X', rs.getItem());// transformer
                }

                for (ItemStack bucketWater : waterbucket) {
                    TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.boiler.item, 2), "###", "XXX", "###", '#', steelItem, 'X', bucketWater.getItem());// Boiler
                }
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.firebox.item, 2), "###", "#X#", "###", '#', steelItem, 'X', Items.FLINT_AND_STEEL);// Firebox
                for (ItemStack ironingot : iron) {
                    TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.bogie.item, 4), " # ", "#X#", " # ", '#', steelItem, 'X', ironingot);// Bogie
                    TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.steelframe.item, 2), "# #", "AAA", 'A', steelItem, '#', ironingot);// Steel Frame
                    TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.steelframe.item, 2), "   ", "# #", "AAA", 'A', steelItem, '#', ironingot);// Steel Frame

                    TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.stake.item, 1), "   ", "IFI", "   ", 'I', steelItem, 'F', ironingot);
                    TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.stake.item, 1), "IFI", "   ", "   ", 'I', steelItem, 'F', ironingot);
                    TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.stake.item, 1), "   ", "   ", "IFI", 'I', steelItem, 'F', ironingot);

                    TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.propeller.item, 2), " # ", "#X#", " # ", '#', plankItem, 'X', ironingot);// Propeller
                }
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.steelchimney.item, 2), "# #", "# #", "# #", '#', steelItem);// Bogie
                TrainCraftingManager.instance.addRecipe(new ItemStack(Items.FLINT_AND_STEEL, 2), "* ", " #", '*', steelItem, '#', Items.FLINT);


                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.transmition.item, 1), " # ", "#X#", " # ", '#', steelItem, 'X', ItemIDs.diesel.item);// transmition
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.piston.item, 3), " # ", " X ", '#', steelItem, 'X', Items.STICK);// piston
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.camshaft.item, 3), "###", "   ", "   ", '#', steelItem);// camshaft
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.camshaft.item, 3), "   ", "###", "   ", '#', steelItem);// camshaft
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.camshaft.item, 3), "   ", "   ", "###", '#', steelItem);// camshaft
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.cylinder.item, 3), "# #", "# #", "###", '#', steelItem);// cylinder

            }

            for (ItemStack logStack : logs) {
                /* Water Wheel */
                TrainCraftingManager.instance.addRecipe(new ItemStack(BlockIDs.waterWheel.block, 1), " P ", "PGP", " P ", 'P', logStack, 'G', ItemIDs.generator.item);

                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.woodenBogie.item, 4), " # ", "#X#", " # ", '#', plankItem, 'X', logStack);// wooden Bogie
            }
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.woodenFrame.item, 2), "# #", "AAA", 'A', plankItem, '#', plankItem);// wooden Frame
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.woodenFrame.item, 2), "   ", "# #", "AAA", 'A', plankItem, '#', plankItem);// wooden Frame
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.woodenCab.item, 2), "###", "X X", "XXX", 'X', plankItem, '#', plankItem);// wooden cab

            for (ItemStack ironingot : iron) {
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.seats.item, 1), "#  ", "## ", "XXX", '#', plankItem, 'X', ironingot);// transformer
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.ironBogie.item, 4), " # ", "#X#", " # ", '#', ironingot, 'X', plankItem);// iron Bogie
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.ironFrame.item, 2), "# #", "AAA", 'A', ironingot, '#', plankItem);// iron Frame
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.ironFrame.item, 2), "   ", "# #", "AAA", 'A', ironingot, '#', plankItem);// iron Frame
                TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.ironCab.item, 2), "###", "X X", "XXX", 'X', ironingot, '#', plankItem);// iron cab
                TrainCraftingManager.instance.addRecipe(new ItemStack(BlockIDs.stopper.block, 1), "WWW", "I I", "RRR", 'W', plankItem, 'R', Blocks.RAIL, 'I', ironingot);// stopper
                TrainCraftingManager.instance.addRecipe(new ItemStack(TCBlocks.switchStand, 1), " W ", " I ", " R ", 'W', Blocks.LEVER, 'R', Items.STICK, 'I', ironingot);//switchstand
                TrainCraftingManager.instance.addRecipe(new ItemStack(TCBlocks.MFPBWigWag, 1), "IWI", " I ", " I ", 'W', Blocks.LEVER, 'I', ironingot);//wigwag
            }
            // Short Wood Slope
            TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSlopeWood.item, 1),
                    " MW", "MWW", "WWW", 'M', ItemIDs.tcRailMediumStraight.item, 'W', plankItem);
        }
        /* Wind mill */
        for (ItemStack ironingot : iron) {
            TrainCraftingManager.instance.addRecipe(new ItemStack(BlockIDs.windMill.block, 1), " R ", " G ", "B B", 'G', ItemIDs.generator.item, 'B', ironingot, 'R', ItemIDs.propeller.item);

            if (Loader.isModLoaded("computercraft")) {
                TrainCraftingManager.instance.addRecipe(new ItemStack(BlockIDs.mtcTransmitterSpeed.block, 1), "SRS", "RTR", "SRS", 'S', ironingot, 'R', Items.REDSTONE, 'T', Blocks.STONE_PRESSURE_PLATE);
                TrainCraftingManager.instance.addRecipe(new ItemStack(BlockIDs.mtcReceiverMTC.block, 1), "STS", " R ", "SPS", 'S', ironingot, 'R', Items.REDSTONE, 'P', Items.REPEATER, 'T', new ItemStack(Blocks.TORCH, 1));
                TrainCraftingManager.instance.addRecipe(new ItemStack(BlockIDs.mtcTransmitterMTC.block, 1), "SPS", " R ", "STS", 'S', ironingot, 'R', Items.REDSTONE, 'P', Items.REPEATER, 'T', new ItemStack(Blocks.TORCH, 1));
                TrainCraftingManager.instance.addRecipe(new ItemStack(BlockIDs.mtcReceiverDestination.block, 1), "SRS", "RTR", "SRS", 'S', ironingot, 'R', Items.REDSTONE, 'T', Items.SIGN);
                TrainCraftingManager.instance.addRecipe(new ItemStack(BlockIDs.mtcATOStopTransmitter.block, 1), " S ", "RTS", " R ", 'S', ironingot, 'R', Items.REDSTONE, 'T', ItemIDs.electronicCircuit.item);
            }
        }

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSmallRoadCrossing.item, 1), "   ", "SRS", "   ", 'S', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 15), 'R', Item.getItemFromBlock(Blocks.RAIL));
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSmallRoadCrossing1.item, 1), "   ", "SRS", "   ", 'S', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 7), 'R', Item.getItemFromBlock(Blocks.RAIL));
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSmallRoadCrossing2.item, 1), "   ", "SRS", "   ", 'S', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 3), 'R', Item.getItemFromBlock(Blocks.RAIL));
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSmallRoadCrossingDynamic.item, 1), "   ", "SRS", "   ", 'S', new ItemStack(Blocks.WOOL, 1, 0), 'R', Item.getItemFromBlock(Blocks.RAIL));
        TrainCraftingManager.instance.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.RAIL), 1), "   ", " R ", "   ", 'R', ItemIDs.tcRailSmallStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.RAIL), 1), "   ", " R ", "   ", 'R', ItemIDs.tcRailSmallRoadCrossing.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.RAIL), 1), "   ", " R ", "   ", 'R', ItemIDs.tcRailSmallRoadCrossing1.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.RAIL), 1), "   ", " R ", "   ", 'R', ItemIDs.tcRailSmallRoadCrossing2.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSmallStraight.item, 16),  "G G", "BPB", "G G", Character.valueOf('G'), Items.IRON_INGOT, Character.valueOf('P'), Blocks.PLANKS, Character.valueOf('B'), ItemIDs.bolt.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailMediumStraight.item, 1),  "G  ", "G  ", "G  ", Character.valueOf('G'), ItemIDs.tcRailSmallStraight.item );
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailLongStraight.item, 1),  "G  ", "G  ", "   ", Character.valueOf('G'), ItemIDs.tcRailMediumStraight.item );
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailVeryLongStraight.item, 1), "G  ", "G  ", "   ", Character.valueOf('G'), ItemIDs.tcRailLongStraight.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSmallDiagonalStraight.item, 1), " G ", "G  ", "   ", 'G', ItemIDs.tcRailSmallStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailMediumDiagonalStraight.item, 1), "  G", " G ", "G  ", 'G', ItemIDs.tcRailSmallDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailLongDiagonalStraight.item, 1), " G ", "G  ", "   ", 'G', ItemIDs.tcRailMediumDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailVeryLongDiagonalStraight.item, 1), " G ", "G  ", "   ", 'G', ItemIDs.tcRailLongDiagonalStraight.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRail1X1Turn.item, 1),  "GG ", "G  ", "   ", 'G', ItemIDs.tcRailSmallStraight.item );
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailMediumTurn.item, 1),  "GG ", "G  ", "   ", 'G', ItemIDs.tcRail1X1Turn.item );
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailLargeTurn.item, 1), " SS", "SS ", "S  ", Character.valueOf('S'), ItemIDs.tcRailSmallStraight.item );
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailVeryLargeTurn.item, 1), "GG ","G  ","   ", Character.valueOf('G'), ItemIDs.tcRailLargeTurn.item );
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSuperLargeTurn.item, 1), "GG ","G  ","   ", Character.valueOf('G'), ItemIDs.tcRailVeryLargeTurn.item );
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRail29X29Turn.item, 1), "GG ","G  ","   ", Character.valueOf('G'), ItemIDs.tcRailSuperLargeTurn.item );
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRail32X32Turn.item, 1), "GG ","G  ","   ", Character.valueOf('G'), ItemIDs.tcRail29X29Turn.item );

        TrainCraftingManager.instance.addShapelessRecipe(new ItemStack(ItemIDs.tcRailMedium45DegreeTurn.item, 2), ItemIDs.tcRailMediumTurn.item);
        TrainCraftingManager.instance.addShapelessRecipe(new ItemStack(ItemIDs.tcRailLarge45DegreeTurn.item, 2), ItemIDs.tcRailLargeTurn.item);
        TrainCraftingManager.instance.addShapelessRecipe(new ItemStack(ItemIDs.tcRailVeryLarge45DegreeTurn.item, 2), ItemIDs.tcRailVeryLargeTurn.item);
        TrainCraftingManager.instance.addShapelessRecipe(new ItemStack(ItemIDs.tcRailSuperLarge45DegreeTurn.item, 2), ItemIDs.tcRailSuperLargeTurn.item);
        TrainCraftingManager.instance.addShapelessRecipe(new ItemStack(ItemIDs.tcRail45DegreeTurn9x20.item, 2), ItemIDs.tcRail29X29Turn.item);
        TrainCraftingManager.instance.addShapelessRecipe(new ItemStack(ItemIDs.tcRail45DegreeTurn10x22.item,2),ItemIDs.tcRail32X32Turn.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSmallParallelCurve.item, 1), " S ", "SS ", "S  ", 'S', ItemIDs.tcRailSmallStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailMediumParallelCurve.item, 1), " S ", "S  ", "   ", 'S', ItemIDs.tcRailSmallParallelCurve.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailLargeParallelCurve.item, 1), " S ", "S  ", "   ", 'S', ItemIDs.tcRailMediumParallelCurve.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRail20x2SCurve.item,1), "S  ", " X ", "  S", 'S', ItemIDs.tcRailSmallParallelCurve.item, 'X', ItemIDs.tcRailMediumStraight.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailMediumSwitch.item, 1), "G  ", "GHG", "G  ", 'G', ItemIDs.tcRailSmallStraight.item, 'H', ItemIDs.tcRailMediumTurn.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailMediumParallelSwitch.item, 1), "I G", "IHH", "IH ", 'G', ItemIDs.tcRailSmallStraight.item, 'H', ItemIDs.tcRailMediumTurn.item, 'I', ItemIDs.tcRailMediumStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailLargeParallelSwitch.item, 1), "I G", "IHH", "IH ", 'G', ItemIDs.tcRailSmallStraight.item, 'H', ItemIDs.tcRailLargeTurn.item, 'I', ItemIDs.tcRailLongStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailVeryLargeSwitch.item, 1), "H  ", "HIG", "H  ", 'G', ItemIDs.tcRailSmallStraight.item, 'H', ItemIDs.tcRailMediumStraight.item, 'I', ItemIDs.tcRailVeryLargeTurn.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailMedium45DegreeSwitch.item, 1), "G D", "GH ", "G  ", 'G', ItemIDs.tcRailSmallStraight.item, 'H', ItemIDs.tcRailMedium45DegreeTurn.item, 'D', ItemIDs.tcRailSmallDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailLarge45DegreeSwitch.item, 1), "G D", "GH ", "G  ", 'G', ItemIDs.tcRailSmallStraight.item, 'H', ItemIDs.tcRailLarge45DegreeTurn.item, 'D', ItemIDs.tcRailSmallDiagonalStraight.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSlopeWood.item, 1), " TP", "TPP", "PPP", 'T', ItemIDs.tcRailMediumStraight.item, 'P', Blocks.PLANKS);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailLargeSlopeWood.item, 1), "   ", "  T", " T ", 'T', ItemIDs.tcRailSlopeWood.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailVeryLargeSlopeWood.item, 1), "  T", " T ", "T  ", 'T', ItemIDs.tcRailSlopeWood.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSlopeSnowGravel.item, 1), " TS", "TSS", "SSS", 'T', ItemIDs.tcRailMediumStraight.item, 'S', new ItemStack(BlockIDs.snowGravel.block, 1, 0));
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailLargeSlopeSnowGravel.item, 1), "   ", "  T", " T ", 'T', ItemIDs.tcRailSlopeSnowGravel.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailVeryLargeSlopeSnowGravel.item, 1), "  T", " T ", "T  ", 'T', ItemIDs.tcRailSlopeSnowGravel.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSlopeGravel.item, 1), " TG", "TGG", "GGG", 'T', ItemIDs.tcRailMediumStraight.item, 'G', Blocks.GRAVEL);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailLargeSlopeGravel.item, 1), "   ", "  S", " S ", 'S', ItemIDs.tcRailSlopeGravel.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailVeryLargeSlopeGravel.item, 1), "  T", " T ", "T  ", 'T', ItemIDs.tcRailSlopeGravel.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSlopeDynamic.item, 1), " TG", "TGG", "GGG", 'T', ItemIDs.tcRailMediumStraight.item, 'G', new ItemStack(Items.DYE, 1, 1));
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailLargeSlopeDynamic.item, 1), "   ", "  S", " S ", 'S', ItemIDs.tcRailSlopeDynamic.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailVeryLargeSlopeDynamic.item, 1), "  T", " T ", "T  ", 'T', ItemIDs.tcRailSlopeDynamic.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSlopeBallast.item, 1), " TG", "TGG", "GGG", 'T', ItemIDs.tcRailMediumStraight.item, 'G', new ItemStack(BlockIDs.oreTC.block, 1, 3));
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailLargeSlopeBallast.item, 1), "   ", "  S", " S ", 'S', ItemIDs.tcRailSlopeBallast.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailVeryLargeSlopeBallast.item, 1), "  T", " T ", "T  ", 'T', ItemIDs.tcRailSlopeBallast.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailLargeCurvedSlopeDynamic.item, 1), "   ", " T ", " S ", 'T', ItemIDs.tcRailLargeTurn.item, 'S', ItemIDs.tcRailSlopeDynamic.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailVeryLargeCurvedSlopeDynamic.item, 1), "   ", " T ", " S ", 'T', ItemIDs.tcRailVeryLargeTurn.item, 'S', ItemIDs.tcRailLargeSlopeDynamic.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailSuperLargeCurvedSlopeDynamic.item, 1), "   ", " T ", " S ", 'T', ItemIDs.tcRailSuperLargeTurn.item, 'S', ItemIDs.tcRailVeryLargeSlopeDynamic.item);


        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailTwoWaysCrossing.item, 1), " S ", "SSS", " S ", 'S', ItemIDs.tcRailSmallStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailDiagonalTwoWaysCrossing.item, 1), "D D", " D ", "D D", 'D', ItemIDs.tcRailSmallDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailDiamondCrossing.item, 1), " SD", " S ", "DS ", 'S', ItemIDs.tcRailSmallStraight.item, 'D', ItemIDs.tcRailSmallDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailDiamondCrossing.item, 1), "DS ", " S ", " SD", 'S', ItemIDs.tcRailSmallStraight.item, 'D', ItemIDs.tcRailSmallDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailDoubleDiamondCrossing.item, 1), "DSD", " S ", "DSD", 'S', ItemIDs.tcRailSmallStraight.item, 'D', ItemIDs.tcRailSmallDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailFourWaysCrossing.item, 1), "DSD", "SDS", "DSD", 'S', ItemIDs.tcRailSmallStraight.item, 'D', ItemIDs.tcRailSmallDiagonalStraight.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedSmallStraight.item, 16), "I I", "B B", "I I", 'I', Items.IRON_INGOT, 'B', ItemIDs.bolt.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedMediumStraight.item, 1), "G  ", "G  ", "G  ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedLongStraight.item, 1), "G  ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedMediumStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedVeryLongStraight.item, 1), "G  ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedLongStraight.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item, 1), " G ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedMediumDiagonalStraight.item, 1), "  G", " G ", "G  ", 'G', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedLongDiagonalStraight.item, 1), " G ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedMediumDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedVeryLongDiagonalStraight.item, 1), " G ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedLongDiagonalStraight.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbedded1X1Turn.item, 1),  "GG ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item );
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedMediumTurn.item, 1),  "GG ", "G  ", "   ", 'G', ItemIDs.tcRailEmbedded1X1Turn.item );
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedLargeTurn.item, 1), " SS", "SS ", "S  ", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedVeryLargeTurn.item, 1), "GG ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedLargeTurn.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedSuperLargeTurn.item, 1), "GG ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedVeryLargeTurn.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbedded29X29Turn.item, 1), "GG ", "G  ", "   ", 'G', ItemIDs.tcRailEmbeddedSuperLargeTurn.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbedded32X32Turn.item, 1), "GG ", "G  ", "   ", 'G', ItemIDs.tcRailEmbedded29X29Turn.item);

        TrainCraftingManager.instance.addShapelessRecipe(new ItemStack(ItemIDs.tcRailEmbeddedMedium45DegreeTurn.item, 2), ItemIDs.tcRailEmbeddedMediumTurn.item);
        TrainCraftingManager.instance.addShapelessRecipe(new ItemStack(ItemIDs.tcRailEmbeddedLarge45DegreeTurn.item, 2), ItemIDs.tcRailEmbeddedLargeTurn.item);
        TrainCraftingManager.instance.addShapelessRecipe(new ItemStack(ItemIDs.tcRailEmbeddedVeryLarge45DegreeTurn.item, 2), ItemIDs.tcRailEmbeddedVeryLargeTurn.item);
        TrainCraftingManager.instance.addShapelessRecipe(new ItemStack(ItemIDs.tcRailEmbeddedSuperLarge45DegreeTurn.item, 2), ItemIDs.tcRailEmbeddedSuperLargeTurn.item);
        TrainCraftingManager.instance.addShapelessRecipe(new ItemStack(ItemIDs.tcRailEmbedded45DegreeTurn9x20.item, 2), ItemIDs.tcRailEmbedded29X29Turn.item);
        TrainCraftingManager.instance.addShapelessRecipe(new ItemStack(ItemIDs.tcRailEmbedded45DegreeTurn10x22.item,2),ItemIDs.tcRailEmbedded32X32Turn.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedSmallParallelCurve.item, 1), " S ", "SS ", "S  ", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedMediumParallelCurve.item, 1), " S ", "SS ", "S  ", 'S', ItemIDs.tcRailEmbeddedSmallParallelCurve.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedLargeParallelCurve.item, 1), " S ", "SS ", "S  ", 'S', ItemIDs.tcRailEmbeddedMediumParallelCurve.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbedded20x2SCurve.item,1), "S  ", " X ", "  S", 'S', ItemIDs.tcRailEmbeddedSmallParallelCurve.item, 'X', ItemIDs.tcRailEmbeddedMediumStraight.item);


        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedMediumSwitch.item, 1), "G  ", "GHG", "G  ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item, 'H', ItemIDs.tcRailEmbeddedMediumTurn.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedMediumParallelSwitch.item, 1), "I G", "IHH", "IH ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item, 'H', ItemIDs.tcRailEmbeddedMediumTurn.item, 'I', ItemIDs.tcRailEmbeddedMediumStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedLargeParallelSwitch.item, 1), "I G", "IHH", "IH ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item, 'H', ItemIDs.tcRailEmbeddedLargeTurn.item, 'I', ItemIDs.tcRailEmbeddedLongStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedLargeSwitch.item, 1), "G  ", "HIG", "G  ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item, 'H', ItemIDs.tcRailEmbeddedMediumStraight.item, 'I', ItemIDs.tcRailEmbeddedLargeTurn.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedVeryLargeSwitch.item, 1), "H  ", "HIG", "H  ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item, 'H', ItemIDs.tcRailEmbeddedMediumStraight.item, 'I', ItemIDs.tcRailEmbeddedVeryLargeTurn.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedMedium45DegreeSwitch.item, 1), "G D", "GH ", "G  ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item, 'H', ItemIDs.tcRailEmbeddedMedium45DegreeTurn.item, 'D', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedLarge45DegreeSwitch.item, 1), "G D", "GH ", "G  ", 'G', ItemIDs.tcRailEmbeddedSmallStraight.item, 'H', ItemIDs.tcRailEmbeddedLarge45DegreeTurn.item, 'D', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);


        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedSlopeDynamic.item, 1), " TG", "TGG", "GGG", 'T', ItemIDs.tcRailEmbeddedMediumStraight.item, 'G', new ItemStack(Items.DYE, 1, 1));
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedLargeSlopeDynamic.item, 1), "   ", "  S", " S ", 'S', ItemIDs.tcRailEmbeddedSlopeDynamic.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedVeryLargeSlopeDynamic.item, 1), "  T", " T ", "T  ", 'T', ItemIDs.tcRailEmbeddedSlopeDynamic.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedLargeCurvedSlopeDynamic.item, 1), "   ", " T ", " S ", 'T', ItemIDs.tcRailEmbeddedLargeTurn.item, 'S', ItemIDs.tcRailEmbeddedSlopeDynamic.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedVeryLargeCurvedSlopeDynamic.item, 1), "   ", " T ", " S ", 'T', ItemIDs.tcRailEmbeddedVeryLargeTurn.item, 'S', ItemIDs.tcRailEmbeddedLargeSlopeDynamic.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedSuperLargeCurvedSlopeDynamic.item, 1), "   ", " T ", " S ", 'T', ItemIDs.tcRailEmbeddedSuperLargeTurn.item, 'S', ItemIDs.tcRailEmbeddedVeryLargeSlopeDynamic.item);

        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedTwoWaysCrossing.item, 1), " S ", "SSS", " S ", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedDiagonalTwoWaysCrossing.item, 1), "D D", " D ", "D D", 'D', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedDiamondCrossing.item, 1), " SD", " S ", "DS ", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item, 'D', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedDiamondCrossing.item, 1), "DS ", " S ", " SD", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item, 'D', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedDoubleDiamondCrossing.item, 1), "DSD", " S ", "DSD", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item, 'D', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);
        TrainCraftingManager.instance.addRecipe(new ItemStack(ItemIDs.tcRailEmbeddedFourWaysCrossing.item, 1), "DSD", "SDS", "DSD", 'S', ItemIDs.tcRailEmbeddedSmallStraight.item, 'D', ItemIDs.tcRailEmbeddedSmallDiagonalStraight.item);

        // Bridge Pillar
        TrainCraftingManager.instance.addRecipe(new ItemStack(TCBlocks.bridgePillar, 8),
                "SSS", "SLS", "SSS", 'S', Items.STICK, 'L', Blocks.LOG);
    }

    public static void initSmeltingRecipes() {

        /* OpenHearthFurnace recipes */
        if (!ConfigHandler.MAKE_MODPACKS_GREAT_AGAIN) {
            java.util.List<ItemStack> steel = OreDictionary.getOres("ingotSteel");
            java.util.List<ItemStack> iron = OreDictionary.getOres("ingotIron");
            for (ItemStack s : steel) {
                for (ItemStack ironitm : iron) {
                    TrainCraftingManager.instance.addHearthFurnaceRecipe(ironitm,
                            new ItemStack(ItemIDs.graphite.item), s, 2F, 1000);
                }
            }
        }

        /* Vanilla Furnace recipes */
        GameRegistry.addSmelting(new ItemStack(Item.getItemFromBlock(BlockIDs.oreTC.block), 0), OreDictionary.getOres("ingotCopper").get(0), 0.7f);
    }

    public static void addDictRecipe(ItemStack stack, Object... obj) {
        TCRecipes.shaped(stack, obj);
    }
}
