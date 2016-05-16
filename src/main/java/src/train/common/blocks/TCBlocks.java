/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package src.train.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import src.train.common.library.BlockIDs;
import src.train.common.library.Info;

public class TCBlocks {

	public static void init() {
		loadBlocks();
		registerBlocks();
		setHarvestLevels();
	}

	public static void loadBlocks() {
		BlockIDs.distilIdle.block = new BlockDistil(2, false).setHardness(3.5F).setStepSound(Block.soundTypeStone);
		BlockIDs.distilActive.block = new BlockDistil(2, true).setHardness(3.5F).setStepSound(Block.soundTypeStone).setLightLevel(0.8F);
		//BlockIDs.signal.block = new BlockSignal(BlockIDs.signal.blockID, 16).setHardness(1.7F).setStepSound(Block.soundTypeMetal);

		BlockIDs.assemblyTableI.block = new BlockAssemblyTableI(32, Material.wood).setHardness(3.5F).setStepSound(Block.soundTypeWood);
		BlockIDs.assemblyTableII.block = new BlockAssemblyTableII(48, Material.rock).setHardness(3.5F).setStepSound(Block.soundTypeWood);
		BlockIDs.assemblyTableIII.block = new BlockAssemblyTableIII(64, Material.rock).setHardness(3.5F).setStepSound(Block.soundTypeWood);

		BlockIDs.trainWorkbench.block = new BlockTrainWorkbench(16).setHardness(1.7F).setStepSound(Block.soundTypeWood);
		BlockIDs.stopper.block = new BlockStopper(16).setHardness(1.7F).setStepSound(Block.soundTypeWood);

		BlockIDs.openFurnaceIdle.block = new BlockOpenHearthFurnace(20, false).setHardness(3.5F).setStepSound(Block.soundTypeStone);
		BlockIDs.openFurnaceActive.block = new BlockOpenHearthFurnace(20, true).setHardness(3.5F).setStepSound(Block.soundTypeStone);
		BlockIDs.oreTC.block = new BlockOreTC(0).setHardness(3.0F).setResistance(5F).setStepSound(Block.soundTypeStone);

		BlockIDs.lantern.block = new BlockLantern().setHardness(1.7F).setStepSound(Block.soundTypeMetal).setLightLevel(0.98F);
		BlockIDs.waterWheel.block = new BlockWaterWheel().setHardness(1.7F).setStepSound(Block.soundTypeWood);
		BlockIDs.windMill.block = new BlockWindMill().setHardness(1.7F).setStepSound(Block.soundTypeWood);
		BlockIDs.generatorDiesel.block = new BlockGeneratorDiesel().setHardness(1.7F).setStepSound(Block.soundTypeMetal);
		
		BlockIDs.tcRail.block = new BlockTCRail().setHardness(1.0F).setStepSound(Block.soundTypeMetal).setCreativeTab(null);
		BlockIDs.tcRailGag.block = new BlockTCRailGag().setHardness(1.0F).setStepSound(Block.soundTypeMetal).setCreativeTab(null);
		
		BlockIDs.bridgePillar.block = new BlockBridgePillar().setHardness(3.5F).setStepSound(Block.soundTypeWood);

		//BlockIDs.book.block = new BlockBook(BlockIDs.book.blockID);
	}

	public static void registerBlocks() {
		for (BlockIDs blocks : BlockIDs.values()) {
			if(blocks.block != null) {
				blocks.block.setBlockName(Info.modID + ":" + blocks.name());
				if (blocks.hasItemBlock) {
					GameRegistry.registerBlock(blocks.block, blocks.itemBlockClass, blocks.name());
				} else {
					GameRegistry.registerBlock(blocks.block, blocks.name());
				}
			}
		}
	}

	public static void setHarvestLevels() {
		BlockIDs.trainWorkbench.block.setHarvestLevel("axe", 0);
		BlockIDs.assemblyTableI.block.setHarvestLevel("axe", 0);
		BlockIDs.assemblyTableII.block.setHarvestLevel("axe", 0);
		BlockIDs.assemblyTableIII.block.setHarvestLevel("axe", 0);
		BlockIDs.waterWheel.block.setHarvestLevel("axe", 0);
		BlockIDs.windMill.block.setHarvestLevel("axe", 0);
		BlockIDs.bridgePillar.block.setHarvestLevel("axe", 0);

		Blocks.rail.setHarvestLevel("ItemStacked", 0);
		Blocks.detector_rail.setHarvestLevel("ItemStacked", 0);
		Blocks.golden_rail.setHarvestLevel("ItemStacked", 0);
	}
}