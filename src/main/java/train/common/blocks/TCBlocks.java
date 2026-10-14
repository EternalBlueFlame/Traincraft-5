/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.blocks;

import net.minecraft.block.SoundType;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import train.common.Traincraft;
import train.common.blocks.blockSwitch.*;
import train.common.blocks.slabs.*;
import train.common.blocks.stairs.BlockBaseStairs;
import train.common.blocks.switchStand.*;
import train.common.blocks.tracks.BlockTCFlexRail;
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

	public static Block bridgePillar=TCBlockUtil.sound(new BlockBridgePillar().setHardness(3.5F), SoundType.WOOD);
	public static Block distilIdle=TCBlockUtil.sound(new BlockDistil(2, false).setHardness(3.5F), SoundType.STONE);
	public static Block distilActive=TCBlockUtil.sound(new BlockDistil(2,true).setHardness(3.5F), SoundType.STONE).setLightLevel(0.8F);
	public static Block openFurnaceIdle=TCBlockUtil.sound(new BlockOpenHearthFurnace(false).setHardness(3.5F), SoundType.STONE);
	public static Block openFurnaceActive=TCBlockUtil.sound(new BlockOpenHearthFurnace(true).setHardness(3.5F), SoundType.STONE).setLightLevel(0.8F);
	public static Block assemblyTableI=TCBlockUtil.sound(new BlockAssemblyTableI(Material.WOOD).setHardness(3.5F), SoundType.WOOD);
	public static Block assemblyTableII=TCBlockUtil.sound(new BlockAssemblyTableII(Material.ROCK).setHardness(3.5F), SoundType.WOOD);
	public static Block assemblyTableIII=TCBlockUtil.sound(new BlockAssemblyTableIII(Material.ROCK).setHardness(3.5F), SoundType.WOOD);
	public static Block trainWorkbench=TCBlockUtil.sound(new BlockTrainWorkbench(16).setHardness(1.7F), SoundType.WOOD);
	public static Block lantern=TCBlockUtil.sound(new BlockLantern().setHardness(1.7F), SoundType.METAL);
	public static Block MFPBWigWag=TCBlockUtil.sound(new BlockMFPBWigWag().setHardness(2.5F), SoundType.METAL);
	public static Block switchStand=TCBlockUtil.sound(new BlockSwitchStand().setHardness(1.7F), SoundType.METAL);
	public static Block autoSwtichStand = TCBlockUtil.sound(new BlockAutoSwitchStand().setHardness(1F), SoundType.METAL);
	public static Block owoSwitchStand = TCBlockUtil.sound(new BlockOWOSwitchStand().setHardness(2F), SoundType.METAL);
	public static Block circleSwitchStand = TCBlockUtil.sound(new BlockCircleSwitchStand().setHardness(2F), SoundType.METAL);
	public static Block owoYardSwitchStand = TCBlockUtil.sound(new BlockOWOYardSwitchStand().setHardness(4F), SoundType.METAL);
	public static Block MILWSwitchStand = TCBlockUtil.sound(new BlockMILWSwitchStand().setHardness(1F), SoundType.METAL);
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


		BlockIDs.waterWheel.block = TCBlockUtil.sound(new BlockWaterWheel().setHardness(1.7F), SoundType.WOOD);
		BlockIDs.windMill.block = TCBlockUtil.sound(new BlockWindMill().setHardness(1.7F), SoundType.WOOD);
		BlockIDs.generatorDiesel.block = TCBlockUtil.sound(new BlockGeneratorDiesel().setHardness(1.7F), SoundType.METAL);

		BlockIDs.stopper.block = TCBlockUtil.sound(new BlockStopper().setHardness(1.7F), SoundType.WOOD);
		BlockIDs.embeddedStopper.block = TCBlockUtil.sound(new BlockEmbeddedStopper().setHardness(1.7F), SoundType.METAL);
		BlockIDs.americanstopper.block = TCBlockUtil.sound(new BlockAmericanStopper().setHardness(1.7F), SoundType.METAL);


		BlockIDs.oreTC.block = TCBlockUtil.sound(new BlockOreTC().setHardness(3.0F).setResistance(5F), SoundType.STONE);
		BlockIDs.dirtyBallast.block = new baseBlock("Dirty Gravel", 1f, 1f, "shovel", 1, Material.GROUND, SoundType.GROUND, "dirtygravel");
		BlockIDs.dirtierBallast.block = new baseBlock("Dirtier Gravel", 1f, 1f, "shovel", 1, Material.GROUND, SoundType.GROUND, "blackgravel");
		BlockIDs.highSpeedBallast.block = TCBlockUtil.sound(new BlockHighSpeedBallast(Material.GROUND).setHardness(1F), SoundType.GROUND).setTranslationKey("tc.ballastHighSpeed").setResistance(10F).setCreativeTab(Traincraft.tcTab);
		BlockIDs.snowGravel.block = TCBlockUtil.sound(new BlocksnowGravel(Material.GROUND).setHardness(1F), SoundType.GROUND).setTranslationKey("snowGravel").setCreativeTab(Traincraft.tcTab);
		BlockIDs.poweredGravel.block = TCBlockUtil.sound(new BlockpoweredGravel(Material.ROCK).setHardness(0F), SoundType.GROUND).setTranslationKey("poweredGravel").setCreativeTab(Traincraft.tcTab);
		BlockIDs.asphalt.block = TCBlockUtil.sound(new Blockasphalt(Material.GROUND).setHardness(2F), SoundType.STONE).setTranslationKey("tc.asphalt").setResistance(10F).setCreativeTab(Traincraft.tcTab);


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


		BlockIDs.ballastStairs.block = TCBlockUtil.sound(new BlockBaseStairs(BlockIDs.oreTC.block.getDefaultState().withProperty(BlockOreTC.TYPE, 3)).setHardness(2.0F), SoundType.STONE).setTranslationKey("ballast stairs").setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.dirtyBallastStairs.block = TCBlockUtil.sound(new BlockBaseStairs(BlockIDs.dirtyBallast.block).setHardness(2.0F), SoundType.STONE).setTranslationKey("dirty ballast stairs").setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.dirtierBallastStairs.block = TCBlockUtil.sound(new BlockBaseStairs(BlockIDs.dirtierBallast.block).setHardness(2.0F), SoundType.STONE).setTranslationKey("dirtier ballast stairs").setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.highSpeedBallastStairs.block = TCBlockUtil.sound(new BlockBaseStairs(BlockIDs.highSpeedBallast.block).setHardness(2.0F), SoundType.STONE).setTranslationKey("high speed ballast stairs").setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.snowGravelStairs.block = TCBlockUtil.sound(new BlockBaseStairs(BlockIDs.snowGravel.block).setHardness(2.0F), SoundType.STONE).setTranslationKey("snow gravel stairs").setCreativeTab(Traincraft.tcTab).setLightOpacity(0);
		BlockIDs.asphaltStairs.block = TCBlockUtil.sound(new BlockBaseStairs(BlockIDs.asphalt.block).setHardness(2.0F), SoundType.STONE).setTranslationKey("asphalt stairs").setCreativeTab(Traincraft.tcTab).setLightOpacity(0);


		BlockIDs.tcRail.block = TCBlockUtil.sound(new BlockTCRail().setHardness(1.0F), SoundType.METAL).setCreativeTab(null);
		BlockIDs.tcRailGag.block = TCBlockUtil.sound(new BlockTCRailGag().setHardness(1.0F), SoundType.METAL).setCreativeTab(null);


		BlockIDs.overheadWire.block = new BlockoverheadWire().setHardness(2F);
		BlockIDs.overheadWireDouble.block = new BlockoverheadWireDouble().setHardness(2F);
		BlockIDs.signalSpanish.block = TCBlockUtil.sound(new BlocksignalSpanish().setHardness(1F).setResistance(10F).setLightLevel(0.2F).setCreativeTab(Traincraft.tcTab), SoundType.METAL);
		BlockIDs.kSignal.block = TCBlockUtil.sound(new BlockkSignal().setHardness(1F).setResistance(10F).setLightLevel(0.2F), SoundType.METAL).setCreativeTab(null);
		BlockIDs.metroMadridPole.block = new BlockMetroMadridPole( Material.IRON).setHardness(2F).setCreativeTab(Traincraft.tcTab).setTranslationKey("MetroMadridPole");
		BlockIDs.track_copper.block = new BlockTCFlexRail(BlockTCFlexRail.SPEED_COPPER).setCreativeTab(Traincraft.tcTab);
		BlockIDs.track_steel.block = new BlockTCFlexRail(BlockTCFlexRail.SPEED_STEEL).setCreativeTab(Traincraft.tcTab);
		BlockIDs.track_snowy.block = new BlockTCFlexRail(BlockTCFlexRail.SPEED_DEFAULT).setCreativeTab(Traincraft.tcTab);
		BlockIDs.track_snowy_copper.block = new BlockTCFlexRail(BlockTCFlexRail.SPEED_COPPER).setCreativeTab(Traincraft.tcTab);
		BlockIDs.track_snowy_steel.block = new BlockTCFlexRail(BlockTCFlexRail.SPEED_STEEL).setCreativeTab(Traincraft.tcTab);

		//BlockIDs.FortyFootContainer.block = new BlockFortyFootContainer(Material.ROCK).setHardness(4.5F).setCreativeTab(Traincraft.tcTab).setStepSound(Block.soundTypeMetal);

		if (Loader.isModLoaded("computercraft")) {
			BlockIDs.mtcTransmitterSpeed.block = TCBlockUtil.sound(new BlockInfoTransmitterSpeed(Material.ROCK).setHardness(3.5F), SoundType.METAL).setTranslationKey("mtcspeedtransmitter").setCreativeTab(Traincraft.tcTab);
			BlockIDs.mtcTransmitterMTC.block = TCBlockUtil.sound(new BlockInfoTransmitterMTC(Material.ROCK).setHardness(3.5F), SoundType.METAL).setTranslationKey("mtcstatustransmitter").setCreativeTab(Traincraft.tcTab);
			BlockIDs.mtcATOStopTransmitter.block = TCBlockUtil.sound(new BlockATOTransmitterStopPoint(Material.ROCK).setHardness(3.5F), SoundType.METAL).setTranslationKey("mtcatostoppointtransmitter").setCreativeTab(Traincraft.tcTab);
			BlockIDs.mtcReceiverMTC.block = TCBlockUtil.sound(new BlockInfoGrabberMTC(Material.ROCK).setHardness(3.5F), SoundType.METAL).setTranslationKey("mtcstatusreceiver").setCreativeTab(Traincraft.tcTab);
			BlockIDs.mtcReceiverDestination.block = TCBlockUtil.sound(new BlockInfoGrabberDestination(Material.ROCK).setHardness(3.5F), SoundType.METAL).setTranslationKey("mtcdestinationreceiver").setCreativeTab(Traincraft.tcTab);
			BlockIDs.pdmInstructionBlock.block = TCBlockUtil.sound(new BlockPDMInstructionRadio(Material.ROCK).setHardness(3.5F), SoundType.METAL).setTranslationKey("pdmradio").setCreativeTab(Traincraft.tcTab);
		}

	}

	public static void registerBlocks() {
		for (BlockIDs blocks : BlockIDs.values()) {
			if(blocks.block != null) {
				blocks.block.setTranslationKey(Info.modID + ":" + blocks.name());
				if (blocks.block.getRegistryName() != null) {
					continue; // registered elsewhere (e.g. fluid blocks by LiquidManager)
				}
				blocks.block.setRegistryName(Info.modID, blocks.name());
				net.minecraftforge.fml.common.registry.ForgeRegistries.BLOCKS.register(blocks.block);
				net.minecraft.item.ItemBlock item;
				if (blocks.hasItemBlock && blocks.itemBlockClass != null) {
					try {
						item = (net.minecraft.item.ItemBlock) blocks.itemBlockClass.getConstructor(net.minecraft.block.Block.class).newInstance(blocks.block);
					} catch (ReflectiveOperationException e) {
						throw new RuntimeException("Can't create the item for block " + blocks.name(), e);
					}
				} else {
					item = new net.minecraft.item.ItemBlock(blocks.block);
				}
				item.setRegistryName(blocks.block.getRegistryName());
				net.minecraftforge.fml.common.registry.ForgeRegistries.ITEMS.register(item);
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
		Blocks.DETECTOR_RAIL.setHarvestLevel("ItemStacked", 0);
		Blocks.GOLDEN_RAIL.setHarvestLevel("ItemStacked", 0);
	}
}