/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.library;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import train.common.items.*;
import train.common.items.slabs.*;
import train.common.wellcar.ItemFortyFootContainer;

public enum BlockIDs {

	signal(false, null),
	
	//book(true, ItemBlockBook.class),

	stopper(false, null),
	embeddedStopper(false, null),
	americanstopper(false, null),


	oreTC(true, ItemBlockOreTC.class, 3),
	dirtyBallast(false,null, 0),
	dirtierBallast(false,null, 0),
	highSpeedBallast(false, null, 0),
	poweredGravel(false,null, 0),
	snowGravel(false,null, 0),
	asphalt(false, null, 0),

	ballastSlab(true, ItemBallastSlab.class),
	ballastDoubleSlab(true, ItemBallastSlab.class),
	dirtyBallastSlab(true, ItemDirtyBallastSlab.class),
	dirtyBallastDoubleSlab(true, ItemDirtyBallastSlab.class),
	dirtierBallastSlab(true, ItemDirtierBallastSlab.class),
	dirtierBallastDoubleSlab(true, ItemDirtierBallastSlab.class),
	highSpeedBallastSlab(true, ItemHighSpeedBallastSlab.class),
	highSpeedBallastDoubleSlab(true, ItemHighSpeedBallastSlab.class),
	snowGravelSlab(true, ItemSnowGravelSlab.class),
	snowGravelDoubleSlab(true, ItemSnowGravelSlab.class),
	asphaltSlab(true, ItemAsphaltSlab.class),
	asphaltDoubleSlab(true, ItemAsphaltSlab.class),

	ballastStairs(false, null),
	dirtyBallastStairs(false, null),
	dirtierBallastStairs(false, null),
	highSpeedBallastStairs(false, null),
	snowGravelStairs(false, null),
	asphaltStairs(false, null),

	waterWheel(true, ItemBlockGeneratorWaterWheel.class),
	windMill(true, ItemBlockGeneratorWindMill.class),
	generatorDiesel(true, ItemBlockGeneratorDiesel.class),
	mtcTransmitterSpeed(false, null),
	mtcTransmitterMTC(false, null),
	mtcATOStopTransmitter(false, null),
	mtcReceiverMTC(false, null),
	mtcReceiverDestination(false, null),
	pdmInstructionBlock(false, null),
	//Liquids
	diesel(false, ItemBlockFluid.class),
	refinedFuel(false, ItemBlockFluid.class),
	
	tcRailGag(false,null),
	tcRail(false,null),
	overheadWire(false,null),
	overheadWireDouble(false,null),
	signalSpanish(false,null),//ItemsignalSpanish.class
	kSignal(false,null),
	metroMadridPole(false, null),
	FortyFootContainer(true, ItemFortyFootContainer.class),


	;

	public Block block;
	public boolean hasItemBlock;
	public Class itemBlockClass;
	/** MaxMetaData for Multipart
	 * -1 Disables the use of Multipart on the block
	 * 0 Enabled the use of multipart on the block and will allow for only the base block to use Multipart
	 */
	public final byte MaxMetadata;

	/**
	 * Construct a BlockID that has multiPart Support
	 * @param hasItemBlock
	 * @param itemBlockClass
	 */
	BlockIDs(boolean hasItemBlock, Class<? extends ItemBlock> itemBlockClass, int maxMetadata)
	{
		this.hasItemBlock = hasItemBlock;
		this.itemBlockClass = itemBlockClass;
		this.MaxMetadata = (byte)maxMetadata;
	}

	/**
	 * Construct a BlockID that does not have multiPart support
	 * @param hasItemBlock
	 * @param itemBlockClass
	 */
	BlockIDs(boolean hasItemBlock, Class<? extends ItemBlock> itemBlockClass)
	{
		this.hasItemBlock = hasItemBlock;
		this.itemBlockClass = itemBlockClass;
		this.MaxMetadata = -1;
	}
}
