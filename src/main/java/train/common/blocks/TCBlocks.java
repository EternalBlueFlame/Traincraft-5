/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.blocks;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import train.common.Traincraft;
import train.common.blocks.blockSwitch.*;
import train.common.blocks.slabs.*;
import train.common.blocks.stairs.BlockBaseStairs;
import train.common.blocks.switchStand.*;
import train.common.library.BlockIDs;
import train.common.library.Info;
import train.common.library.TraincraftRegistry;
import train.common.mtc.*;

public class TCBlocks {

	public static void init() {
		loadBlocks();
		registerBlocks();
		setHarvestLevels();
	}

	public static Block bridgePillar=new BlockBridgePillar().setHardness(3.5F);
	public static Block distilIdle=new BlockDistil(2, false).setHardness(3.5F);
	public static Block distilActive=new BlockDistil(2,true).setHardness(3.5F).setLightLevel(0.8F);
	public static Block openFurnaceIdle=new BlockOpenHearthFurnace(false).setHardness(3.5F);
	public static Block openFurnaceActive=new BlockOpenHearthFurnace(true).setHardness(3.5F).setLightLevel(0.8F);
	public static Block assemblyTableI=new BlockAssemblyTableI(Material.WOOD).setHardness(3.5F);
	public static Block assemblyTableII=new BlockAssemblyTableII(Material.ROCK).setHardness(3.5F);
	public static Block assemblyTableIII=new BlockAssemblyTableIII(Material.ROCK).setHardness(3.5F);
	public static Block trainWorkbench=new BlockTrainWorkbench(16).setHardness(1.7F);
	public static Block lantern=new BlockLantern().setHardness(1.7F);
	public static Block MFPBWigWag=new BlockMFPBWigWag().setHardness(2.5F);
	public static Block switchStand=new BlockSwitchStand().setHardness(1.7F);
	public static Block autoSwtichStand = new BlockAutoSwitchStand().setHardness(1F);
	public static Block owoSwitchStand = new BlockOWOSwitchStand().setHardness(2F);
	public static Block circleSwitchStand = new BlockCircleSwitchStand().setHardness(2F);
	public static Block owoYardSwitchStand = new BlockOWOYardSwitchStand().setHardness(4F);
	public static Block MILWSwitchStand = new BlockMILWSwitchStand().setHardness(1F);
	public static Block speedSign = new BlockSpeedSign().setHardness(1F);



	public static void loadBlocks() {
		TraincraftRegistry.registerBlock(bridgePillar,Traincraft.tcTab, Info.modID,"bridgePillar");
		TraincraftRegistry.registerBlock(distilIdle,Traincraft.tcTab, Info.modID,"distilIdle");
		TraincraftRegistry.registerBlock(distilActive,null, Info.modID,"distilActive");
		TraincraftRegistry.registerBlock(assemblyTableI,Traincraft.tcTab, Info.modID,"assemblyTableI");
		TraincraftRegistry.registerBlock(assemblyTableII,Traincraft.tcTab, Info.modID,"assemblyTableII");
		TraincraftRegistry.registerBlock(assemblyTableIII,Traincraft.tcTab, Info.modID,"assemblyTableIII");
		TraincraftRegistry.registerBlock(trainWorkbench,Traincraft.tcTab, Info.modID,"trainWorkbench");
		TraincraftRegistry.registerBlock(openFurnaceIdle,Traincraft.tcTab, Info.modID,"openFurnaceIdle");
		TraincraftRegistry.registerBlock(openFurnaceActive,null, Info.modID,"openFurnaceActive");
		TraincraftRegistry.registerBlock(lantern,Traincraft.tcTab, Info.modID,"lantern");
		TraincraftRegistry.registerBlock(MFPBWigWag,Traincraft.tcTab, Info.modID,"MFPBWigWag");
		TraincraftRegistry.registerBlock(switchStand,Traincraft.tcTab, Info.modID,"switchStand");
		TraincraftRegistry.registerBlock(autoSwtichStand, Traincraft.tcTab, Info.modID, "autoswtichStand");
		TraincraftRegistry.registerBlock(circleSwitchStand, Traincraft.tcTab, Info.modID, "circleSwitchStand");
		TraincraftRegistry.registerBlock(owoSwitchStand, Traincraft.tcTab, Info.modID, "owoSwitchStand");
		TraincraftRegistry.registerBlock(owoYardSwitchStand, Traincraft.tcTab, Info.modID, "owoYardSwitchStand");
		TraincraftRegistry.registerBlock(MILWSwitchStand, Traincraft.tcTab, Info.modID, "MILWSwitchStand");
		TraincraftRegistry.registerBlock(speedSign, Traincraft.tcTab, Info.modID, "speedSign");


		BlockIDs.waterWheel.block = new BlockWaterWheel().setHardness(1.7F);
		BlockIDs.windMill.block = new BlockWindMill().setHardness(1.7F);
		BlockIDs.generatorDiesel.block = new BlockGeneratorDiesel().setHardness(1.7F);

		BlockIDs.stopper.block = new BlockStopper().setHardness(1.7F);
		BlockIDs.embeddedStopper.block = new BlockEmbeddedStopper().setHardness(1.7F);
		BlockIDs.americanstopper.block = new BlockAmericanStopper().setHardness(1.7F);


		BlockIDs.oreTC.block = new BlockOreTC().setHardness(3.0F);
		BlockIDs.dirtyBallast.block = new baseBlock("Dirty Gravel", 1f, 1f, "shovel", 1, Material.GROUND, SoundType.GROUND, "dirtygravel");
		BlockIDs.dirtierBallast.block = new baseBlock("Dirtier Gravel", 1f, 1f, "shovel", 1, Material.GROUND, SoundType.GROUND, "blackgravel");
		BlockIDs.highSpeedBallast.block = new BlockHighSpeedBallast(Material.GROUND).setHardness(1F).setCreativeTab(Traincraft.tcTab);
		BlockIDs.snowGravel.block = new BlocksnowGravel(Material.GROUND).setHardness(1F).setCreativeTab(Traincraft.tcTab);
		BlockIDs.poweredGravel.block = new BlockpoweredGravel(Material.ROCK).setHardness(0F).setCreativeTab(Traincraft.tcTab);
		BlockIDs.asphalt.block = new Blockasphalt(Material.GROUND).setHardness(2F).setCreativeTab(Traincraft.tcTab);


		BlockIDs.ballastSlab.block = new BlockBallastSlab(false).setCreativeTab(Traincraft.tcTab);
		BlockIDs.ballastDoubleSlab.block = new BlockBallastSlab(true);
		BlockIDs.dirtyBallastSlab.block = new BlockDirtyBallastSlab(false).setCreativeTab(Traincraft.tcTab);
		BlockIDs.dirtyBallastDoubleSlab.block = new BlockDirtyBallastSlab(true);
		BlockIDs.dirtierBallastSlab.block = new BlockDirtierBallastSlab(false).setCreativeTab(Traincraft.tcTab);
		BlockIDs.dirtierBallastDoubleSlab.block = new BlockDirtierBallastSlab(true);
		BlockIDs.highSpeedBallastSlab.block = new BlockHighSpeedBallastSlab(false).setCreativeTab(Traincraft.tcTab);
		BlockIDs.highSpeedBallastDoubleSlab.block = new BlockHighSpeedBallastSlab(true);
		BlockIDs.snowGravelSlab.block = new BlockSnowGravelSlab(false).setCreativeTab(Traincraft.tcTab);
		BlockIDs.snowGravelDoubleSlab.block = new BlockSnowGravelSlab(true);
		BlockIDs.asphaltSlab.block = new BlockAsphaltSlab(false).setCreativeTab(Traincraft.tcTab);
		BlockIDs.asphaltDoubleSlab.block = new BlockAsphaltSlab(true);


		BlockIDs.ballastStairs.block = new BlockBaseStairs(BlockIDs.oreTC.block).setHardness(2.0F).setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.dirtyBallastStairs.block = new BlockBaseStairs(BlockIDs.dirtyBallast.block).setHardness(2.0F).setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.dirtierBallastStairs.block = new BlockBaseStairs(BlockIDs.dirtierBallast.block).setHardness(2.0F).setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.highSpeedBallastStairs.block = new BlockBaseStairs(BlockIDs.highSpeedBallast.block).setHardness(2.0F).setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.snowGravelStairs.block = new BlockBaseStairs(BlockIDs.snowGravel.block).setHardness(2.0F).setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.asphaltStairs.block = new BlockBaseStairs(BlockIDs.asphalt.block).setHardness(2.0F).setCreativeTab(Traincraft.tcTab).setLightOpacity(0);


		BlockIDs.tcRail.block = new BlockTCRail().setHardness(1.0F).setCreativeTab(null);
		BlockIDs.tcRailGag.block = new BlockTCRailGag().setHardness(1.0F).setCreativeTab(null);


		BlockIDs.overheadWire.block = new BlockoverheadWire().setHardness(2F);
		BlockIDs.overheadWireDouble.block = new BlockoverheadWireDouble().setHardness(2F);
		BlockIDs.signalSpanish.block = new BlocksignalSpanish().setHardness(1F).setLightLevel(0.2F).setCreativeTab(Traincraft.tcTab);
		BlockIDs.kSignal.block = new BlockkSignal().setHardness(1F).setLightLevel(0.2F).setCreativeTab(null);
		BlockIDs.metroMadridPole.block = new BlockMetroMadridPole( Material.IRON).setHardness(2F).setCreativeTab(Traincraft.tcTab);
		//BlockIDs.FortyFootContainer.block = new BlockFortyFootContainer(Material.ROCK).setHardness(4.5F).setCreativeTab(Traincraft.tcTab);

		if (Loader.isModLoaded("ComputerCraft")) {
			BlockIDs.mtcTransmitterSpeed.block = new BlockInfoTransmitterSpeed(Material.ROCK).setHardness(3.5F).setCreativeTab(Traincraft.tcTab);
			BlockIDs.mtcTransmitterMTC.block = new BlockInfoTransmitterMTC(Material.ROCK).setHardness(3.5F).setCreativeTab(Traincraft.tcTab);
			BlockIDs.mtcATOStopTransmitter.block = new BlockATOTransmitterStopPoint(Material.ROCK).setHardness(3.5F).setCreativeTab(Traincraft.tcTab);
			BlockIDs.mtcReceiverMTC.block = new BlockInfoGrabberMTC(Material.ROCK).setHardness(3.5F).setCreativeTab(Traincraft.tcTab);
			BlockIDs.mtcReceiverDestination.block = new BlockInfoGrabberDestination(Material.ROCK).setHardness(3.5F).setCreativeTab(Traincraft.tcTab);
			BlockIDs.pdmInstructionBlock.block = new BlockPDMInstructionRadio(Material.ROCK).setHardness(3.5F).setCreativeTab(Traincraft.tcTab);
		}

	}

	public static void registerBlocks() {
		for (BlockIDs blocks : BlockIDs.values()) {
			if(blocks.block != null) {
				if (blocks.hasItemBlock) {
					// GameRegistry.registerBlock(blocks.block, blocks.itemBlockClass, blocks.name());
				} else {
					// GameRegistry.registerBlock(blocks.block, blocks.name());
				}
			}
		}
	}

	public static void setHarvestLevels() {
		TCBlocks.assemblyTableII.setHarvestLevel("axe", 0);
		TCBlocks.assemblyTableIII.setHarvestLevel("axe", 0);
		BlockIDs.waterWheel.block.setHarvestLevel("axe", 0);
		BlockIDs.windMill.block.setHarvestLevel("axe", 0);
		BlockIDs.oreTC.block.setHarvestLevel("pickaxe", 1);
		BlockIDs.snowGravel.block.setHarvestLevel("shovel",0);
		BlockIDs.poweredGravel.block.setHarvestLevel("shovel",0);
		BlockIDs.signalSpanish.block.setHarvestLevel("pickaxe",1);
		BlockIDs.asphalt.block.setHarvestLevel("pickaxe", 0);
		BlockIDs.metroMadridPole.block.setHarvestLevel("pickaxed", 0);

		Blocks.RAIL.setHarvestLevel("ItemStacked", 0);
	}
}