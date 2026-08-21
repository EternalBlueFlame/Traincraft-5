package train.common.tile;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import ebf.tim.utility.DebugUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;

import javax.annotation.Nullable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import train.common.Traincraft;
import train.common.api.TrackRecord;
import train.common.blocks.BlockTCRail;
import train.common.blocks.BlockTCRailGag;
import train.common.core.handlers.ConfigHandler;
import train.common.items.TCRailTypes;
import train.common.library.BlockIDs;
import train.common.library.EnumTracks;
import train.common.library.TraincraftRegistry;

import java.util.ArrayList;
import java.util.List;

public class TileTCRail extends TileEntity implements ITickable {

	public double r;
	public double cx;
	public double cy;
	public double cz;
	public double slopeLength;
	public double slopeAngle;

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

	public EntityPlayer lastPlayerToInteract = null;
	private int updateTicks;
	public Item		idDrop;

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
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		this.type = type;
		for (EnumTracks rail : EnumTracks.values()) {
			if (rail.getLabel().equals(type)) {
				track = rail;
			}
		}
	}

	public String getType() {

		return this.type;
	}





	public void setBallastMaterial(int  ballast) {
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
		this.ballastMaterial = ballast;
	}

	public int getBallastMaterial(){
		return ballastMaterial;
	}

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		AxisAlignedBB bb = INFINITE_EXTENT_AABB;
		Block type = getBlockType();
		if (type == BlockIDs.tcRail.block )
		{
			bb = new AxisAlignedBB(pos.getX() - 32, pos.getY(), pos.getZ() - 32, pos.getX() + 32, pos.getY(), pos.getZ() + 32);
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
		if(track==null) {
			for (EnumTracks rail : EnumTracks.values()) {
				if (rail.getLabel().equals(getType())) {
					track = rail;
				}
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

	private byte checkBlockXZ=0;
	@Override
	public void update() {
		if (world.isRemote || !TCRailTypes.isSwitchTrack(this)) {

			return;
		}

		if (updateTicks % 11 == 0 || updateTicks==1) {
			boolean flag = world.isBlockPowered(pos);

			if(checkBlockXZ==0){
				if(CommonUtil.getBlockAt(world,pos.getX(),pos.getY(),pos.getZ()+1) instanceof BlockTCRail){
					checkBlockXZ=1;
				} else {
					checkBlockXZ=2;
				}
			}
			if(!flag){
				if(checkBlockXZ==1){
					flag = world.isBlockPowered(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1));
					if(!flag){
						flag = world.isBlockPowered(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1));
					}
				} else if(checkBlockXZ==2){
					flag = world.isBlockPowered(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()));
					if(!flag){
						flag = world.isBlockPowered(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()));
					}
				}
			}
			if (getSwitchState() != flag) {
				setSwitchState(flag);
			}
		}

		updateTicks++;
	}



	public void setSwitchState(boolean state) {
		this.switchActive = state;
		TileEntity te1;
		int a = 0;
		int b = 0;
		int c = 0;
		switch (getBlockMetadata()) {
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
		while (Math.abs(offsetX) < getTrack().getSwitchSize() && Math.abs(offsetY) < getTrack().getSwitchSize() && Math.abs(offsetZ) < getTrack().getSwitchSize()) {
			te1 = world.getTileEntity(new BlockPos(pos.getX() + offsetX, pos.getY() + offsetY, pos.getZ() + offsetZ));
			if (te1 instanceof TileTCRail) {
				if (getSwitchState()) {
					if (getType().contains("SWITCH") && getType().contains("LEFT")) {
						((TileTCRail) te1).setType(EnumTracks.MEDIUM_LEFT_TURN.getLabel());
						((TileTCRail) te1).switchActive=true;
					} else if (getType().contains("SWITCH") && getType().contains("RIGHT")) {
						((TileTCRail) te1).setType(EnumTracks.MEDIUM_RIGHT_TURN.getLabel());
						((TileTCRail) te1).switchActive=true;
					}
				} else {
					((TileTCRail) te1).setType(EnumTracks.SMALL_STRAIGHT.getLabel());
					((TileTCRail) te1).switchActive=false;
				}
			}
			offsetX += a;
			offsetY += b;
			offsetZ += c;
		}

		this.markDirty();
		this.world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
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
		idDrop = Item.getItemById(nbt.getInteger("idDrop"));


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
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("Orientation", (byte) facingMeta);
		nbt.setDouble("r", r);
		nbt.setDouble("cx", cx);
		nbt.setDouble("cy", cy);
		nbt.setDouble("cz", cz);
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
		nbt.setInteger("idDrop", Item.getIdFromItem(idDrop));
		return nbt;
	}

	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {

		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);

		return new SPacketUpdateTileEntity(getPos(), 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
		this.readFromNBT(pkt.getNbtCompound());
		super.onDataPacket(net, pkt);
	}

	public void changeSwitchState(World world, TileTCRail tileEntity, int i, int j, int k) {
		if (tileEntity.getType() != null && (tileEntity.getType().contains("SWITCH"))) {
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
				te1 = world.getTileEntity(new BlockPos(i + offsetX, j + offsetY, k + offsetZ));
				if (te1 != null && te1 instanceof TileTCRail) {
					if (tileEntity.getSwitchState()) {
						if (tileEntity.getType().contains("SWITCH") && tileEntity.getType().contains("LEFT")) {
							((TileTCRail) te1).setType(EnumTracks.MEDIUM_LEFT_TURN.getLabel());
							((TileTCRail) te1).switchActive=true;
						} else if (tileEntity.getType().contains("SWITCH") && tileEntity.getType().contains("RIGHT")) {
							((TileTCRail) te1).setType(EnumTracks.MEDIUM_RIGHT_TURN.getLabel());
							((TileTCRail) te1).switchActive=true;
						}
					} else {
						((TileTCRail) te1).setType(EnumTracks.SMALL_STRAIGHT.getLabel());
						((TileTCRail) te1).switchActive=false;
					}
				}
				offsetX += a;
				offsetY += b;
				offsetZ += c;
			}
		}
	}
}
