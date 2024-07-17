/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.blocks;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import train.common.Traincraft;
import train.common.blocks.blockSwitch.*;
import train.common.blocks.slabs.*;
import train.common.blocks.stairs.*;
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

	public static Block bridgePillar=new BlockBridgePillar().setHardness(3.5F).setStepSound(Block.soundTypeWood);
	public static Block distilIdle=new BlockDistil(2, false).setHardness(3.5F).setStepSound(Block.soundTypeStone);
	public static Block distilActive=new BlockDistil(2,true).setHardness(3.5F).setStepSound(Block.soundTypeStone).setLightLevel(0.8F);
	public static Block assemblyTableI=new BlockAssemblyTableI(Material.wood).setHardness(3.5F).setStepSound(Block.soundTypeWood);
	public static Block assemblyTableII=new BlockAssemblyTableII(Material.rock).setHardness(3.5F).setStepSound(Block.soundTypeWood);
	public static Block assemblyTableIII=new BlockAssemblyTableIII(Material.rock).setHardness(3.5F).setStepSound(Block.soundTypeWood);

	public static void loadBlocks() {
		TraincraftRegistry.registerBlock(bridgePillar,Traincraft.tcTab, Info.modID,"bridgePillar");
		TraincraftRegistry.registerBlock(distilIdle,Traincraft.tcTab, Info.modID,"distilIdle");
		TraincraftRegistry.registerBlock(distilActive,null, Info.modID,"distilActive");
		TraincraftRegistry.registerBlock(assemblyTableI,null, Info.modID,"assemblyTableI");
		TraincraftRegistry.registerBlock(assemblyTableII,null, Info.modID,"assemblyTableII");
		TraincraftRegistry.registerBlock(assemblyTableIII,null, Info.modID,"assemblyTableIII");

		//TCBlocks.distilIdle.block = new BlockDistil(2, false).setHardness(3.5F).setStepSound(Block.soundTypeStone);
		//BlockIDs.distilActive.block = new BlockDistil(2, true).setHardness(3.5F).setStepSound(Block.soundTypeStone).setLightLevel(0.8F);
		//BlockIDs.signal.block = new BlockSignal(BlockIDs.signal.block, 16).setHardness(1.7F).setStepSound(Block.soundTypeMetal);

		//TCBlocks.assemblyTableI = new BlockAssemblyTableI(Material.wood).setHardness(3.5F).setStepSound(Block.soundTypeWood);
		//TCBlocks.assemblyTableII = new BlockAssemblyTableII(Material.rock).setHardness(3.5F).setStepSound(Block.soundTypeWood);
		//TCBlocks.assemblyTableIII = new BlockAssemblyTableIII(Material.rock).setHardness(3.5F).setStepSound(Block.soundTypeWood);

		BlockIDs.trainWorkbench.block = new BlockTrainWorkbench(16).setHardness(1.7F).setStepSound(Block.soundTypeWood);
		BlockIDs.stopper.block = new BlockStopper().setHardness(1.7F).setStepSound(Block.soundTypeWood);
		BlockIDs.embeddedStopper.block = new BlockEmbeddedStopper().setHardness(1.7F).setStepSound(Block.soundTypeMetal);
		BlockIDs.americanstopper.block = new BlockAmericanStopper().setHardness(1.7F).setStepSound(Block.soundTypeMetal);

		BlockIDs.openFurnaceIdle.block = new BlockOpenHearthFurnace(false).setHardness(3.5F).setStepSound(Block.soundTypeStone);
		BlockIDs.openFurnaceActive.block = new BlockOpenHearthFurnace(true).setHardness(3.5F).setStepSound(Block.soundTypeStone);


		BlockIDs.oreTC.block = new BlockOreTC().setHardness(3.0F).setResistance(5F).setStepSound(Block.soundTypeStone);
		BlockIDs.dirtyBallast.block = new baseBlock("Dirty Gravel", 1f, 1f, "shovel", 1, Material.ground, Block.soundTypeGravel, "dirtygravel");
		BlockIDs.dirtierBallast.block = new baseBlock("Dirtier Gravel", 1f, 1f, "shovel", 1, Material.ground, Block.soundTypeGravel, "blackgravel");
		BlockIDs.highSpeedBallast.block = new BlockHighSpeedBallast(Material.ground).setHardness(1F).setStepSound(Block.soundTypeGravel).setBlockTextureName("tc:ballasthighspeed").setBlockName("tc.ballastHighSpeed").setResistance(10F).setCreativeTab(Traincraft.tcTab);
		BlockIDs.snowGravel.block = new BlocksnowGravel(Material.ground).setHardness(1F).setStepSound(Block.soundTypeGravel).setBlockTextureName("tc:snowgravel").setBlockName("snowGravel").setCreativeTab(Traincraft.tcTab);
		BlockIDs.poweredGravel.block = new BlockpoweredGravel(Material.rock).setHardness(0F).setStepSound(Block.soundTypeGravel).setBlockTextureName("tc:poweredGravel").setBlockName("poweredGravel").setCreativeTab(Traincraft.tcTab);
		BlockIDs.asphalt.block = new Blockasphalt(Material.ground).setHardness(2F).setStepSound(Block.soundTypeStone).setBlockTextureName("tc:asphalt").setBlockName("tc.asphalt").setResistance(10F).setCreativeTab(Traincraft.tcTab);


		BlockIDs.ballastSlab.block = new BlockBallastSlab(false).setCreativeTab(Traincraft.tcTab).setBlockTextureName("tc:ballast_test");
		BlockIDs.ballastDoubleSlab.block = new BlockBallastSlab(true).setBlockTextureName("tc:ballast_test");
		BlockIDs.dirtyBallastSlab.block = new BlockDirtyBallastSlab(false).setCreativeTab(Traincraft.tcTab).setBlockTextureName("tc:dirtygravel");
		BlockIDs.dirtyBallastDoubleSlab.block = new BlockDirtyBallastSlab(true).setBlockTextureName("tc:dirtygravel");
		BlockIDs.dirtierBallastSlab.block = new BlockDirtierBallastSlab(false).setCreativeTab(Traincraft.tcTab).setBlockTextureName("tc:blackgravel");
		BlockIDs.dirtierBallastDoubleSlab.block = new BlockDirtierBallastSlab(true).setBlockTextureName("tc:blackgravel");
		BlockIDs.highSpeedBallastSlab.block = new BlockHighSpeedBallastSlab(false).setCreativeTab(Traincraft.tcTab).setBlockTextureName("tc:ballasthighspeed");
		BlockIDs.highSpeedBallastDoubleSlab.block = new BlockHighSpeedBallastSlab(true).setBlockTextureName("tc:ballasthighspeed");
		BlockIDs.snowGravelSlab.block = new BlockSnowGravelSlab(false).setCreativeTab(Traincraft.tcTab).setBlockTextureName("tc:snowgravel");
		BlockIDs.snowGravelDoubleSlab.block = new BlockSnowGravelSlab(true).setBlockTextureName("tc:snowgravel");
		BlockIDs.asphaltSlab.block = new BlockAsphaltSlab(false).setCreativeTab(Traincraft.tcTab).setBlockTextureName("tc:asphalt");
		BlockIDs.asphaltDoubleSlab.block = new BlockAsphaltSlab(true).setBlockTextureName("tc:asphalt");


		BlockIDs.ballastStairs.block = new BlockBaseStairs(BlockIDs.oreTC.block).setHardness(2.0F).setStepSound(Block.soundTypeStone).setBlockName("ballast stairs").setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.dirtyBallastStairs.block = new BlockBaseStairs(BlockIDs.dirtyBallast.block).setHardness(2.0F).setStepSound(Block.soundTypeStone).setBlockName("dirty ballast stairs").setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.dirtierBallastStairs.block = new BlockBaseStairs(BlockIDs.dirtierBallast.block).setHardness(2.0F).setStepSound(Block.soundTypeStone).setBlockName("dirtier ballast stairs").setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.highSpeedBallastStairs.block = new BlockBaseStairs(BlockIDs.highSpeedBallast.block).setHardness(2.0F).setStepSound(Block.soundTypeStone).setBlockName("high speed ballast stairs").setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.snowGravelStairs.block = new BlockBaseStairs(BlockIDs.snowGravel.block).setHardness(2.0F).setStepSound(Block.soundTypeStone).setBlockName("snow gravel stairs").setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.asphaltStairs.block = new BlockBaseStairs(BlockIDs.asphalt.block).setHardness(2.0F).setStepSound(Block.soundTypeStone).setBlockName("asphalt stairs").setCreativeTab(Traincraft.tcTab).setLightOpacity(0);



		BlockIDs.lantern.block = new BlockLantern().setHardness(1.7F).setStepSound(Block.soundTypeMetal).setLightLevel(0.98F);
		BlockIDs.switchStand.block = new BlockSwitchStand().setHardness(1.7F).setStepSound(Block.soundTypeMetal);
		BlockIDs.MFPBWigWag.block = new BlockMFPBWigWag().setHardness(2.5F).setStepSound(Block.soundTypeMetal);
		BlockIDs.waterWheel.block = new BlockWaterWheel().setHardness(1.7F).setStepSound(Block.soundTypeWood);
		BlockIDs.windMill.block = new BlockWindMill().setHardness(1.7F).setStepSound(Block.soundTypeWood);
		BlockIDs.generatorDiesel.block = new BlockGeneratorDiesel().setHardness(1.7F).setStepSound(Block.soundTypeMetal);
		
		BlockIDs.tcRail.block = new BlockTCRail().setHardness(1.0F).setStepSound(Block.soundTypeMetal).setCreativeTab(null);
		BlockIDs.tcRailGag.block = new BlockTCRailGag().setHardness(1.0F).setStepSound(Block.soundTypeMetal).setCreativeTab(null);

		BlockIDs.MILWSwitchStand.block = new BlockMILWSwitchStand().setHardness(1F).setStepSound(Block.soundTypeStone);
		BlockIDs.autoSwtichStand.block = new BlockautoSwitchStand().setHardness(1F).setStepSound(Block.soundTypeAnvil);
		BlockIDs.owoSwitchStand.block = new BlockowoSwitchStand().setHardness(2F).setStepSound(Block.soundTypeMetal);
		BlockIDs.circleSwitchStand.block = new BlockcircleSwitchStand().setHardness(2F).setStepSound(Block.soundTypeAnvil);
		BlockIDs.owoYardSwitchStand.block = new BlockowoYardSwitchStand().setHardness(4F).setStepSound(Block.soundTypeLadder);
		BlockIDs.overheadWire.block = new BlockoverheadWire().setHardness(2F);
		BlockIDs.overheadWireDouble.block = new BlockoverheadWireDouble().setHardness(2F);
		BlockIDs.signalSpanish.block = new BlocksignalSpanish().setHardness(1F).setResistance(10F).setLightLevel(0.2F).setCreativeTab(Traincraft.tcTab).setStepSound(Block.soundTypeMetal);
		BlockIDs.kSignal.block = new BlockkSignal().setHardness(1F).setResistance(10F).setLightLevel(0.2F).setStepSound(Block.soundTypeMetal).setCreativeTab(null);
		BlockIDs.speedSign.block = new BlockSpeedSign().setCreativeTab(Traincraft.tcTab);
		BlockIDs.metroMadridPole.block = new BlockMetroMadridPole( Material.iron).setHardness(2F).setCreativeTab(Traincraft.tcTab).setBlockName("MetroMadridPole");
		//BlockIDs.FortyFootContainer.block = new BlockFortyFootContainer(Material.rock).setHardness(4.5F).setCreativeTab(Traincraft.tcTab).setStepSound(Block.soundTypeMetal);

		if (Loader.isModLoaded("ComputerCraft")) {
			BlockIDs.mtcTransmitterSpeed.block = new BlockInfoTransmitterSpeed(Material.rock).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("tc:speedTransmitter").setBlockName("mtcspeedtransmitter").setCreativeTab(Traincraft.tcTab);
			BlockIDs.mtcTransmitterMTC.block = new BlockInfoTransmitterMTC(Material.rock).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("tc:mtctransmitter").setBlockName("mtcstatustransmitter").setCreativeTab(Traincraft.tcTab);
			BlockIDs.mtcATOStopTransmitter.block = new BlockATOTransmitterStopPoint(Material.rock).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("tc:atostoptransmitter").setBlockName("mtcatostoppointtransmitter").setCreativeTab(Traincraft.tcTab);
			BlockIDs.mtcReceiverMTC.block = new BlockInfoGrabberMTC(Material.rock).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("tc:mtcreceiver").setBlockName("mtcstatusreceiver").setCreativeTab(Traincraft.tcTab);
			BlockIDs.mtcReceiverDestination.block = new BlockInfoGrabberDestination(Material.rock).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("tc:destinationreceiver").setBlockName("mtcdestinationreceiver").setCreativeTab(Traincraft.tcTab);
			BlockIDs.pdmInstructionBlock.block = new BlockPDMInstructionRadio(Material.rock).setHardness(3.5F).setStepSound(Block.soundTypeMetal).setBlockTextureName("tc:pdmradio").setBlockName("pdmradio").setCreativeTab(Traincraft.tcTab);
		}

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

		Blocks.rail.setHarvestLevel("ItemStacked", 0);
		Blocks.detector_rail.setHarvestLevel("ItemStacked", 0);
		Blocks.golden_rail.setHarvestLevel("ItemStacked", 0);
	}
}