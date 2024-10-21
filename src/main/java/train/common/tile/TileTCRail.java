package train.common.tile;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import train.common.Traincraft;
import train.common.api.TrackRecord;
import train.common.api.TrainRecord;
import train.common.blocks.TCBlocks;
import train.common.core.handlers.ConfigHandler;
import train.common.items.ItemTCRail;
import train.common.items.TCRailTypes;
import train.common.library.BlockIDs;
import train.common.library.EnumTracks;
import train.common.library.TraincraftRegistry;

import java.util.ArrayList;
import java.util.List;

public class TileTCRail extends TileEntity {

	public double r;
	public double cx;
	public double cy;
	public double cz;
	public double slopeHeight;
	public double slopeLength;
	public double slopeAngle;
	public double railLength;
	public float bbHeight = 0.125f;

	public int ballastMaterial;
	public int ballastMetadata;
	public int ballastColour;
	private String type;

	private TCRailTypes.RailTypes railType;
	private int facingMeta;
	public boolean isLinkedToRail = false;
	public int linkedX;
	public int linkedY;
	public int linkedZ;
	public boolean hasModel = true;
	private boolean switchActive = false;
	/** stores the latest redstone state */
	public boolean previousRedstoneState;
	public boolean canTypeBeModifiedBySwitch = false;

	public boolean shouldIgnoreSwitch = false;

	private boolean manualOverride = false;

	public EntityPlayer lastPlayerToInteract = null;
	private int updateTicks;
	private int updateTicks2;
	public Item		idDrop;
	private static final float f = 0.125F;
	public boolean hasRotated = false;
	private int isLeftFlag = -5;

	public TileTCRail() {
		if(this.getWorld() != null)
			facingMeta = this.getBlockMetadata();
	}

	public int getFacing() {

		return facingMeta;
	}

	public double getMaxRenderDistanceSquared() {
		int render = ConfigHandler.TRACK_RENDER_DISTANCE & ~15;
		return  render * render;
	}





	public void setFacing(int facing) {

		this.facingMeta = facing;
	}

	public void setType(String type) {
		getWorld().markBlockForUpdate(xCoord, yCoord, zCoord);
		this.type = type;
	}

	public String getType() {

		return this.type;
	}


	public void setRailLength(Double railLength){
		getWorld().markBlockForUpdate(xCoord, yCoord, zCoord);
		this.railLength = railLength;
	}


	public double getRailLength() {
		return this.railLength;
	}




	public void setBallastMaterial(int  ballast) {
		getWorld().markBlockForUpdate(xCoord, yCoord, zCoord);
		this.ballastMaterial = ballast;
	}

	public int getBallastMaterial(){
		if (ballastMaterial != 0){

			return ballastMaterial;
		}
		else {
		return (0);
		}
	}

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		AxisAlignedBB bb = INFINITE_EXTENT_AABB;
		Block type = getBlockType();
		if (type == BlockIDs.tcRail.block )
		{
			bb = AxisAlignedBB.getBoundingBox(xCoord - 32, yCoord, zCoord - 32, xCoord + 32, yCoord , zCoord + 32);
		}

		return bb;
	}

	private EnumTracks renderType = null;

	public TCRailTypes.RailTypes getRailType(){

		railType = getTrackFromName().getRailType();

		return railType;
	}

	private EnumTracks track = null;

	@Deprecated
	public EnumTracks getTrack(){
		for(EnumTracks rail : EnumTracks.values()){
			if(rail.getLabel().equals(getType())){
				track = rail;
			}
		}
		return track;
	}

	public TrackRecord getTrackFromName(){
		return TraincraftRegistry.findTrackRecordByName(this.getType());
	}


	public EnumTracks getTrackType(){
		if (renderType == null){
			if(hasModel && getType() != null){
				for(EnumTracks rail : EnumTracks.values()){
					if (rail.getLabel().equals(getType())){
						renderType = rail;
					}
				}
			}
		}
		return renderType;
	}

	public boolean getSwitchState() {

		return switchActive;
	}



	public void printInfo() {
		System.out.println(type);
		System.out.println(getSwitchState());
		System.out.println(TCRailTypes.isStraightTrack(this));
	}

	@Override
	public void updateEntity() {
		if (getWorld().isRemote || !canTypeBeModifiedBySwitch) {

			return;
		}

		updateTicks2++;

		/*if (updateTicks2 % 20 == 0 && !isLinkedToRail && getType() != null && getType().equals(EnumTracks.SMALL_STRAIGHT.getLabel()) && !hasRotated) {
			TileEntity tileNorth = getWorld().getBlockTileEntity(xCoord, yCoord, zCoord - 1);
			TileEntity tileSouth = getWorld().getBlockTileEntity(xCoord, yCoord, zCoord + 1);
			TileEntity tileEast = getWorld().getBlockTileEntity(xCoord + 1, yCoord, zCoord);
			TileEntity tileWest = getWorld().getBlockTileEntity(xCoord - 1, yCoord, zCoord);
			if (tileNorth != null && (tileNorth instanceof TileTCRail)) {//&& (tileNorth.getBlockMetadata() == 2 || tileNorth.getBlockMetadata() == 0)) {
				getWorld().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 2, 2);
				hasRotated = true;
			}
			if (tileSouth != null && (tileSouth instanceof TileTCRail)) {//&& (tileSouth.getBlockMetadata() == 0 || tileSouth.getBlockMetadata() == 2)) {
				getWorld().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
				hasRotated = true;
			}
			if (tileEast != null && (tileEast instanceof TileTCRail)) {// && ( tileEast.getBlockMetadata() == 3 || tileEast.getBlockMetadata() == 1)) {
				getWorld().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 3, 2);
				hasRotated = true;
			}
			if (tileWest != null && (tileWest instanceof TileTCRail)) {//&& ( tileWest.getBlockMetadata() == 1 || tileWest.getBlockMetadata() == 3)) {
				getWorld().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
				hasRotated = true;
			}
		}*/

		if (updateTicks2 % 11 == 0) {
			updateTicks2 =0;
			TileEntity tile1 = null;

			switch (getWorld().getBlockMetadata(xCoord, yCoord, zCoord)) {

				case 0: {
					tile1 = getWorld().getTileEntity(xCoord, yCoord, zCoord - 1);
					break;
				}
				case 1: {
					tile1 = getWorld().getTileEntity(xCoord + 1, yCoord, zCoord);
					break;
				}
				case 2: {
					tile1 = getWorld().getTileEntity(xCoord, yCoord, zCoord + 1);
					break;
				}
				case 3: {
					tile1 = getWorld().getTileEntity(xCoord - 1, yCoord, zCoord);
					break;
				}
			}
			if (tile1 instanceof TileTCRail && TCRailTypes.isSwitchTrack((TileTCRail) tile1)) {

				TileTCRail tileSwitch = (TileTCRail) tile1;
				boolean flag1 = getWorld().isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);

				if (tileSwitch.previousRedstoneState != flag1 && ! getWorld().isBlockIndirectlyGettingPowered(tileSwitch.xCoord, tileSwitch.yCoord, tileSwitch.zCoord)) {

					tileSwitch.changeSwitchState(getWorld(), tileSwitch, tile1.xCoord, tile1.yCoord, tile1.zCoord);
					tileSwitch.previousRedstoneState = flag1;
				}
			}
			/*
			 * if (tile2 != null && tile2 instanceof TileTCRail &&
			 * ItemTCRail.isTCSwitch((TileTCRail) tile2)) { TileTCRail
			 * tileSwitch = (TileTCRail) tile2; boolean flag1 =
			 * getWorld().isBlockIndirectlyGettingPowered(xCoord, yCoord,
			 * zCoord); boolean flag2 =
			 * getWorld().isBlockIndirectlyGettingPowered(tileSwitch.xCoord,
			 * tileSwitch.yCoord, tileSwitch.zCoord);
			 * //System.out.println(flag2+" flag2"); //boolean switchState2
			 * = tileSwitch.getSwitchState(); if
			 * (tileSwitch.previousRedstoneState != flag1 && !flag2) {
			 * tileSwitch.changeSwitchState(getWorld(), tile2, tile2.xCoord,
			 * tile2.yCoord, tile2.zCoord); tileSwitch.previousRedstoneState
			 * = flag1; } }
			 */
		}

		if (manualOverride) {

			updateTicks++;

			if (updateTicks > 60) {
				List list = getWorld().getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox(xCoord + f,
						yCoord, zCoord + f, xCoord + 1 - f, yCoord + 1 - f, zCoord + 1 - f));

				if (list.isEmpty()) {

					manualOverride = false;
					//setSwitchState(false,false);
					// getWorld().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, facingMeta, 2);
					// System.out.println("X: " + xCoord + " Y: " + yCoord + " Z: " + zCoord);
					changeSwitchState(getWorld(), this, xCoord, yCoord, zCoord);
					setSwitchState(previousRedstoneState, false);
					updateTicks = 0;
				} else {
					updateTicks -=20;
				}
			}
		}

		if (!getSwitchState() && updateTicks2 % 10 ==0) {

			/* Right-handed switch types create a value of 1, left-handed switch types a value of type -1. If neither cases match, value is set to 0. */
			if (isLeftFlag == -5) {
				if (type.contains("SWITCH") && type.contains("RIGHT")) {
					isLeftFlag = 1;
				} else if (type.contains("SWITCH") && type.contains("LEFT")) {
					isLeftFlag = -1;
				} else {
					isLeftFlag = 0;
				}
			}


			if (isLeftFlag != 0) {
				List list;

				switch (facingMeta) {
					// Bounding box generated from -x -z to x z
					case 0: {
						if (isLeftFlag == 1) {
							list = getWorld().getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox(this.xCoord - 1.0D, this.yCoord, this.zCoord + 1.0D, this.xCoord - f, this.yCoord + 1.0D - f, this.zCoord + Math.min(5.0D,this.getTrack().getSwitchSize()) - f));
						} else {
							list = getWorld().getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox(this.xCoord + 1.0D, this.yCoord, this.zCoord + 1.0D, this.xCoord + 2.0D - f, this.yCoord + 1.0D - f, this.zCoord + Math.min(5.0D,this.getTrack().getSwitchSize()) - f));
						}
						break;
					}
					case 1: {
						if (isLeftFlag == 1) {
							list = getWorld().getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox(this.xCoord - Math.min(4.0D,this.getTrack().getSwitchSize()), this.yCoord, this.zCoord - 1.0D, this.xCoord - f, this.yCoord + 1.0D - f, this.zCoord - f));
						} else {
							list = getWorld().getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox(this.xCoord - Math.min(4.0D,this.getTrack().getSwitchSize()), this.yCoord, this.zCoord + 1.0D, this.xCoord - f, this.yCoord + 1.0D - f, this.zCoord + 2.0D - f));
						}
						break;
					}
					case 2: {
						if (isLeftFlag == 1) {
							list = getWorld().getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox(this.xCoord + 1.0D, this.yCoord, this.zCoord - Math.min(4.0D,this.getTrack().getSwitchSize()), this.xCoord + 2.0D - f, this.yCoord + 1.0D - f, this.zCoord - f));
						} else {
							list = getWorld().getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox(this.xCoord - 1.0D, this.yCoord, this.zCoord - Math.min(4.0D,this.getTrack().getSwitchSize()), this.xCoord - f, this.yCoord + 1.0D - f, this.zCoord - f));
						}
						break;
					}
					case 3: {
						if (isLeftFlag == 1) {
							list = getWorld().getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox(this.xCoord + 1.0D, this.yCoord, this.zCoord + 1.0D, this.xCoord + Math.min(5.0D,this.getTrack().getSwitchSize()) - f, this.yCoord + 1.0D - f, this.zCoord + 2.0D - f));
						} else {
							list = getWorld().getEntitiesWithinAABB(EntityMinecart.class, AxisAlignedBB.getBoundingBox(this.xCoord + 1.0D, this.yCoord, this.zCoord - 1.0D, this.xCoord + Math.min(5.0D,this.getTrack().getSwitchSize()) - f, this.yCoord + 1.0 - f, this.zCoord - f));
						}
						break;
					}
					default: {
						list = new ArrayList();
						break;
					}
				}
				if (!list.isEmpty()) {

					changeSwitchState(getWorld(), this, xCoord, yCoord, zCoord);
					setSwitchState(true, true);
				}
			}
		}
	}



	public void setSwitchState(boolean state, boolean manualOverride) {
		previousRedstoneState = getWorld().isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
		this.switchActive = state;
		this.manualOverride = manualOverride;

		if (manualOverride) {
			updateTicks = 0;
		}

		this.markDirty();
		this.getWorld().markBlockForUpdate(getPos());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		facingMeta = nbt.getByte("Orientation");
		r = nbt.getDouble("r");
		cx = nbt.getDouble("cx");
		cy = nbt.getDouble("cy");
		cz = nbt.getDouble("cz");
		cy = nbt.getDouble("cy");
		railLength = nbt.getDouble("railLength");

		slopeHeight = nbt.getDouble("slopeHeight");
		slopeLength = nbt.getDouble("slopeLength");
		slopeAngle = nbt.getDouble("slopeAngle");
		linkedX = nbt.getInteger("linkedX");
		linkedY = nbt.getInteger("linkedY");
		linkedZ = nbt.getInteger("linkedZ");
		ballastMetadata = nbt.getInteger("ballastMetadata");
		ballastColour = nbt.getInteger("ballastColour");
		if (nbt.hasKey("type")) {
			type = nbt.getString("type");
		} else {
			type = EnumTracks.SMALL_STRAIGHT.getLabel();
		}
		if(nbt.hasKey("ballastMaterial")) {
			ballastMaterial = nbt.getInteger("ballastMaterial");
		} else {
			ballastMaterial=0;
		}


		isLinkedToRail = nbt.getBoolean("isLinkedToRail");
		hasModel = nbt.getBoolean("hasModel");
		switchActive = nbt.getBoolean("switchActive");
		canTypeBeModifiedBySwitch = nbt.getBoolean("canTypeBeModifiedBySwitch");
		manualOverride = nbt.getBoolean("manualOverride");
		shouldIgnoreSwitch = nbt.getBoolean("shouldIgnoreSwitch");
		hasRotated = nbt.getBoolean("hasRotated");
		idDrop = Item.getItemById(nbt.getInteger("idDrop"));
		previousRedstoneState = nbt.getBoolean("previousRedstoneState");


		/**
		 * Hacky TC Code to fix already placed slopes
		 * ETERNAL NOTE: checking if it's a slope before checking what kind of slope, in theory, should improve performance
		 */
		if(type.contains("SLOPE")) {
			if (type.equals(EnumTracks.SLOPE_WOOD.getLabel())
					|| type.equals(EnumTracks.SLOPE_GRAVEL.getLabel())
					|| type.equals(EnumTracks.SLOPE_BALLAST.getLabel())
					|| type.equals(EnumTracks.SLOPE_SNOW_GRAVEL.getLabel())
						|| type.equals(EnumTracks.SLOPE_DYNAMIC.getLabel())) {
				slopeAngle = 0.13;
			} else if (type.equals(EnumTracks.LARGE_SLOPE_WOOD.getLabel())
					|| type.equals(EnumTracks.LARGE_SLOPE_GRAVEL.getLabel())
					|| type.equals(EnumTracks.LARGE_SLOPE_BALLAST.getLabel())
					|| type.equals(EnumTracks.LARGE_SLOPE_SNOW_GRAVEL.getLabel())
					|| type.equals(EnumTracks.LARGE_SLOPE_DYNAMIC.getLabel())) {
				slopeAngle = 0.0666;
			} else if (type.equals(EnumTracks.VERY_LARGE_SLOPE_WOOD.getLabel())
					|| type.equals(EnumTracks.VERY_LARGE_SLOPE_GRAVEL.getLabel())
					|| type.equals(EnumTracks.VERY_LARGE_SLOPE_BALLAST.getLabel())
					|| type.equals(EnumTracks.VERY_LARGE_SLOPE_SNOW_GRAVEL.getLabel())
					|| type.equals(EnumTracks.VERY_LARGE_SLOPE_DYNAMIC.getLabel())) {
				slopeAngle = 0.0444;
			}
			else if (type.equals(EnumTracks.LARGE_CURVED_SLOPE_DYNAMIC.getLabel())){
				slopeAngle = 0.1558;
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("Orientation", (byte) facingMeta);
		nbt.setDouble("r", r);
		nbt.setDouble("cx", cx);
		nbt.setDouble("cy", cy);
		nbt.setDouble("cz", cz);
		nbt.setDouble("railLength", railLength);
		nbt.setDouble("slopeHeight", slopeHeight);
		nbt.setDouble("slopeLength", slopeLength);
		nbt.setDouble("slopeAngle", slopeAngle);
		nbt.setInteger("linkedX", linkedX);
		nbt.setInteger("linkedY", linkedY);
		nbt.setInteger("linkedZ", linkedZ);
		nbt.setInteger("ballastMetadata", ballastMetadata);
		nbt.setInteger("ballastColour", ballastColour);
		if (type != null) {
			nbt.setString("type", type);
		}
		if (ballastMaterial  != 0) {
			nbt.setInteger("ballastMaterial", ballastMaterial);
		}
		nbt.setBoolean("isLinkedToRail", isLinkedToRail);
		nbt.setBoolean("hasModel", hasModel);
		nbt.setBoolean("switchActive", switchActive);
		nbt.setBoolean("canTypeBeModifiedBySwitch", canTypeBeModifiedBySwitch);
		nbt.setBoolean("manualOverride", manualOverride);
		nbt.setBoolean("shouldIgnoreSwitch", shouldIgnoreSwitch);
		nbt.setBoolean("hasRotated", hasRotated);
		nbt.setInteger("idDrop", Item.getIdFromItem(idDrop));
		nbt.setBoolean("previousRedstoneState", previousRedstoneState);
	}

	@Override
	public Packet getDescriptionPacket() {

		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);

		return new SPacketUpdateTileEntity(getPos(), 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
		this.readFromNBT(pkt.func_148857_g());
		super.onDataPacket(net, pkt);
	}

	public void changeSwitchState(World world, TileTCRail tileEntity, int i, int j, int k) {
		if (tileEntity.getType() != null && (tileEntity.getType().contains("SWITCH"))) {
			tileEntity.setSwitchState(!tileEntity.getSwitchState(),false);
			TileEntity te1;
			int a = 0;
			int b = 0;
			int c = 0;
			switch (tileEntity.getBlockMetadata()) {
				case 0:
					c = 1;
					break;
				case 1:
					a = -1;
					break;
				case 2:
					c = -1;
					break;
				case 3:
					a = 1;
					break;
				default:
					Traincraft.tcLog.log(Level.WARN, "Unsupported block meta for switch state.");
					return;
			}
			int offsetX = a;
			int offsetY = b;
			int offsetZ = c;
			while (Math.abs(offsetX) < tileEntity.getTrack().getSwitchSize() && Math.abs(offsetY) < tileEntity.getTrack().getSwitchSize() && Math.abs(offsetZ) < tileEntity.getTrack().getSwitchSize()) {
				te1 = world.getTileEntity(i + offsetX, j + offsetY, k + offsetZ);
				if (te1 != null && te1 instanceof TileTCRail) {
					if (tileEntity.getSwitchState()) {
						if (tileEntity.getType().contains("SWITCH") && tileEntity.getType().contains("LEFT")) {
							((TileTCRail) te1).setType(EnumTracks.MEDIUM_LEFT_TURN.getLabel());
						} else if (tileEntity.getType().contains("SWITCH") && tileEntity.getType().contains("RIGHT")) {
							((TileTCRail) te1).setType(EnumTracks.MEDIUM_RIGHT_TURN.getLabel());
						}
					} else {
						((TileTCRail) te1).setType(EnumTracks.SMALL_STRAIGHT.getLabel());
					}
				}
				offsetX += a;
				offsetY += b;
				offsetZ += c;
			}
		}
	}
}
