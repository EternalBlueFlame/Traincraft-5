/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.blocks;

import ebf.tim.TrainsInMotion;
import ebf.tim.blocks.BlockDynamic;
import ebf.tim.blocks.SimpleBlock;
import ebf.tim.registry.TiMBlocks;
import ebf.tim.registry.TiMItems;
import ebf.tim.registry.TiMOres;
import ebf.tim.utility.RecipeManager;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import train.Traincraft;
import train.blocks.bench.BlockTrainWorkbench;
import train.blocks.distil.BlockDistil;
import train.blocks.generator.BlockGeneratorDiesel;
import train.blocks.hearth.BlockOpenHearthFurnace;
import train.blocks.lantern.BlockLantern;
import train.blocks.signal.BlockTCSignal;
import train.blocks.switchstand.BlockSwitchStand;
import train.blocks.waterwheel.BlockWaterWheel;
import train.blocks.windmill.BlockWindMill;
import train.library.Info;
import train.library.ItemIDs;

import static cpw.mods.fml.common.registry.GameRegistry.addRecipe;
import static ebf.tim.registry.TiMGenericRegistry.registerBlock;

public class TCBlocks {
	public static BlockDynamic trainTableTier1 = new BlockDynamic(new Material(MapColor.mapColorArray[13]),true);
	public static BlockDynamic trainTableTier2 = new BlockDynamic(new Material(MapColor.mapColorArray[13]),true);
	public static BlockDynamic trainTableTier3 = new BlockDynamic(new Material(MapColor.mapColorArray[13]),true);

	public static SimpleBlock oilSand;
	public static SimpleBlock orePetroleum;

	public static SimpleBlock blockBallast;

	public static BlockDistil blockDistil = new BlockDistil();
	public static BlockOpenHearthFurnace blockHearthFurnace = new BlockOpenHearthFurnace();

	public static BlockGeneratorDiesel dieselGenerator = new BlockGeneratorDiesel();
	public static BlockBridgePillar bridgePillar = new BlockBridgePillar();

	public static BlockLantern lantern = new BlockLantern();
	// NOTE: i dont think this one was supposed to exist, the render is extra broken,
	//     like it wouldn't have worked in the old 1.7 builds levels of broken.
	//public static BlockSignal signal = new BlockSignal();
	public static BlockTrainWorkbench partTable = new BlockTrainWorkbench();


	public static BlockWindMill windmill = new BlockWindMill();
	public static BlockWaterWheel waterWheel = new BlockWaterWheel();
	public static BlockSwitchStand highStarSwitch = new BlockSwitchStand();
	public static BlockTCSignal signal = new BlockTCSignal();


	@Deprecated //need to use TiMGenericRegistry.registerBlock(), this will also cover tile entities and TESR.
	public static void init() {
		trainTableTier1.setTextureName(Info.modID+ ":textures/blocks/assembly_1.png");
		trainTableTier2.setTextureName(Info.modID+ ":textures/blocks/assembly_2.png");
		trainTableTier3.setTextureName(Info.modID+ ":textures/blocks/assembly_3.png");

		blockBallast = new SimpleBlock(3.0F, 6.0F, "block.ballast", Info.modID + ":ballast_test");
		oilSand = new SimpleBlock(2.0F, 2.0F, "block.oilsand", Info.modID + ":ores/ore_oilsands");
		orePetroleum = new SimpleBlock(3.0F, 3.0F, "block.petroleum", Info.modID + ":ores/ore_petroleum");

		blockBallast.setCreativeTab(Traincraft.tcTab);
		orePetroleum.setCreativeTab(Traincraft.tcTab);
		oilSand.setCreativeTab(Traincraft.tcTab);
		blockBallast.setHarvestLevel("shovel", 2);
		oilSand.setHarvestLevel("shovel", 1);
		orePetroleum.setHarvestLevel("pickaxe", 1);


		blockDistil.setTextureName(Info.modID+ ":textures/blocks/distil_off.png");

		blockHearthFurnace.setTextureName(Info.modID+ ":textures/blocks/furnace_off.png");


		partTable.setTextureName(TrainsInMotion.MODID +":textures/blocks/train_table.png");


		addRecipe(new ItemStack(registerBlock(trainTableTier1, Traincraft.tcTab, Info.modID,"block.traintabletier1", null, null),1),
				"IPI", "S S", "SPS", 'S', Blocks.stone, 'I', Items.iron_ingot, 'P', Blocks.piston); //tier 1
		addRecipe(new ItemStack(registerBlock(trainTableTier2, Traincraft.tcTab, Info.modID,"block.traintabletier2", null, null),1),
				"GPG", "O O", "OPO", 'O', Blocks.obsidian, 'G', Items.gold_ingot, 'P', Blocks.piston); //tier 2
		addRecipe(new ItemStack(registerBlock(trainTableTier3, Traincraft.tcTab, Info.modID,"block.traintabletier3", null, null),1),
				"GPG", "DLD", "OPO", 'O', Blocks.obsidian, 'G', Items.gold_ingot, 'P', Blocks.piston, 'D', Items.diamond, 'L', Blocks.glowstone); //tier 3
		addRecipe(new ItemStack(partTable), "IPI", "S S", "SPS", 'S', Blocks.stone, 'I', Blocks.planks, 'P', Blocks.piston); //part builder

		registerBlock(blockDistil, Traincraft.tcTab, Info.modID,"block.distil", null, null);

		registerBlock(blockHearthFurnace, Traincraft.tcTab, Info.modID,"block.hearthfurnace", null, null);

		registerBlock(partTable, Traincraft.tcTab, Info.modID,"block.parttable", null, null);

		registerBlock(signal, Traincraft.tcTab, Info.modID, "block.signal", null, TrainsInMotion.proxy.getTESR());

		registerBlock(highStarSwitch, Traincraft.tcTab, Info.modID, "block.highstar", null, TrainsInMotion.proxy.getTESR());

		if(TrainsInMotion.proxy.isClient()){
			registerBlock(dieselGenerator, Traincraft.tcTab, Info.modID, "block.dieselGenerator", null, new train.render.RenderGeneratorDiesel());
			registerBlock(bridgePillar, Traincraft.tcTab, Info.modID, "block.bridgePillar", null, new train.render.RenderBridgePillar());

			registerBlock(windmill, Traincraft.tcTab, Info.modID, "block.windmill", null, new train.render.RenderWindMill());
			registerBlock(waterWheel, Traincraft.tcTab, Info.modID, "block.waterwheel", null, new train.render.RenderWaterWheel());

			//registerBlock(signal, Traincraft.tcTab, Info.modID, "block.signal", null, new train.render.RenderSignal());
			registerBlock(lantern, Traincraft.tcTab, Info.modID, "block.lantern", null, new train.render.RenderLantern());
		} else {
			registerBlock(dieselGenerator, Traincraft.tcTab, Info.modID, "block.dieselGenerator", null, null);
			registerBlock(bridgePillar, Traincraft.tcTab, Info.modID, "block.bridgePillar", null, null);

			registerBlock(windmill, Traincraft.tcTab, Info.modID, "block.windmill", null, null);
			registerBlock(waterWheel, Traincraft.tcTab, Info.modID, "block.waterwheel", null, null);

			//registerBlock(signal, Traincraft.tcTab, Info.modID, "block.signal", null, null);
			registerBlock(lantern, Traincraft.tcTab, Info.modID, "block.lantern", null, null);
		}

		OreDictionary.registerOre("oreOilsands", new ItemStack(oilSand));
		OreDictionary.registerOre("orePetroleum", new ItemStack(orePetroleum));


		//OreDictionary.registerOre("dustCoal", new ItemStack(ItemIDs.coaldust.item));
		setHarvestLevels();

	}

	public static void setHarvestLevels() {
		Blocks.rail.setHarvestLevel("ItemStacked", 0);
		Blocks.detector_rail.setHarvestLevel("ItemStacked", 0);
		Blocks.golden_rail.setHarvestLevel("ItemStacked", 0);
	}

	public static void registerRecipes(){

		RecipeManager.registerRecipe(
				new Object[]{Blocks.nether_brick,Items.lava_bucket,Blocks.nether_brick,Blocks.nether_brick,Items.bucket,Blocks.nether_brick,Blocks.nether_brick,Blocks.iron_block,Blocks.nether_brick},
				new ItemStack(blockHearthFurnace), TiMBlocks.trainTable);

		RecipeManager.registerRecipe(
				new Object[]{Items.iron_ingot,Items.iron_ingot,Items.iron_ingot,Blocks.glass_pane,Blocks.torch,Blocks.glass_pane,Items.iron_ingot,Items.iron_ingot,Items.iron_ingot},
				new ItemStack(lantern), TiMBlocks.trainTable);

		RecipeManager.registerRecipe(
				new Object[]{TiMOres.ingotSteel,TiMOres.ingotSteel,TiMOres.ingotSteel,TiMOres.ingotSteel, TiMItems.fireboxSteel,TiMOres.ingotSteel,TiMOres.ingotSteel,TiMOres.ingotSteel,TiMOres.ingotSteel},
				new ItemStack(blockDistil), TiMBlocks.trainTable);

		RecipeManager.registerRecipe(
				new Object[]{TiMItems.chimneySteel,null,null,TiMItems.mediumDieselEngine,TiMItems.steelPinCircuit,null,null,null,null},
				new ItemStack(dieselGenerator), TiMBlocks.trainTable);

		RecipeManager.registerRecipe(
				new Object[]{null, ItemIDs.propeller.item, null, null, TiMItems.generator, null, Items.iron_ingot,null, Items.iron_ingot},
				new ItemStack(windmill), TiMBlocks.trainTable);

		RecipeManager.registerRecipe(
				new Object[]{null, Blocks.log,null,Blocks.log,TiMItems.generator,Blocks.log,null,Blocks.log,null},
				new ItemStack(waterWheel), TiMBlocks.trainTable);

		RecipeManager.registerRecipe(new Object[]{Blocks.gravel,Items.clay_ball,Blocks.gravel,null,null,null,null,null,null},
                new ItemStack(blockBallast), TiMBlocks.trainTable);

	}
}