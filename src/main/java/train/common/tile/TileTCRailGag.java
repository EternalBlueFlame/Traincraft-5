package train.common.tile;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;
import train.common.blocks.BlockTCRail;
import train.common.items.TCRailTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileTCRailGag extends TileEntity {

	protected Random rand = new Random();
	protected Side side;
	public List<Integer> originX = new ArrayList<>();
	public List<Integer> originY = new ArrayList<>();
	public List<Integer> originZ = new ArrayList<>();
	public String type = "";
	public float bbHeight = 0.125f;
	public boolean canPlaceRollingstock = false;

	@Override
	public void readFromNBT(NBTTagCompound nbt) {


		if(nbt.hasKey("Xorigins")){
			originX= new ArrayList<Integer>();
			int[] org=nbt.getIntArray("Xorigins");
			for (int i:org){
				originX.add(i);
			}
			originY= new ArrayList<Integer>();
			org=nbt.getIntArray("Yorigins");
			for (int i:org){
				originY.add(i);
			}
			originZ= new ArrayList<Integer>();
			org=nbt.getIntArray("Zorigins");
			for (int i:org){
				originZ.add(i);
			}
		} else {
			originX.add(nbt.getInteger("originX"));
			originY.add(nbt.getInteger("originY"));
			originZ.add(nbt.getInteger("originZ"));
		}
		bbHeight = nbt.getFloat("bbHeight");
		type = nbt.getString("type");
		canPlaceRollingstock = nbt.getBoolean("canPlaceRollingstock");


		super.readFromNBT(nbt);
	}

	public void setCanPlaceRollingStock(boolean canPlace){
		TileTCRail tile = (TileTCRail) worldObj.getTileEntity(originX.get(0), originY.get(0), originZ.get(0));
		if (tile != null){
			if (tile.getTrackFromName().getRailType() == TCRailTypes.RailTypes.STRAIGHT || tile.getTrackFromName().getRailType() == TCRailTypes.RailTypes.DIAGONAL){
				canPlaceRollingstock = canPlace;
			}
		}
	}



	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		int[] xorg=new int[originX.size()];
		for (int i=0; i<originX.size();i++){
			xorg[i]=originX.get(i);
		}
		nbt.setIntArray("Xorigins", xorg);

		int[] yorg=new int[originY.size()];
		for (int i=0; i<originY.size();i++){
			yorg[i]=originY.get(i);
		}
		nbt.setIntArray("Yorigins", yorg);

		int[] zorg=new int[originZ.size()];
		for (int i=0; i<originZ.size();i++){
			zorg[i]=originZ.get(i);
		}
		nbt.setIntArray("Zorigins", zorg);
		nbt.setFloat("bbHeight", bbHeight);
		if (type.equals("")){
			type = "null";
		}
		nbt.setString("type", type);
		nbt.setBoolean("canPlaceRollingstock", canPlaceRollingstock);

		super.writeToNBT(nbt);
	}

	private static final int[] matrixXZ = {0,-1,1}, matrixY = {0,-1,+1};
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
		for(int x : matrixXZ){
			for(int z : matrixXZ){
				for(int y : matrixY){
					if(p_149749_1_.getBlock(xCoord,yCoord,zCoord)instanceof BlockTCRail){
						p_149749_1_.func_147453_f(p_149749_2_,p_149749_3_,p_149749_4_, Blocks.air);
						p_149749_1_.markBlockForUpdate(p_149749_2_,p_149749_3_,p_149749_4_);
					}
				}
			}
		}
	}

	public double getMaxRenderDistanceSquared() {
		/*if(FMLClientHandler.instance()!=null && FMLClientHandler.instance().getClient()!=null && FMLClientHandler.instance().getClient().gameSettings!=null){
			if (FMLClientHandler.instance().getClient().gameSettings.renderDistanceChunks == 12) {
				return 30000.0D;
			}
			else if (FMLClientHandler.instance().getClient().gameSettings.renderDistanceChunks == 1) {
				return 15900.0D;
			}
			else if (FMLClientHandler.instance().getClient().gameSettings.renderDistanceChunks == 2) {
				return 4000.0D;
			} else return 4096.0;
		}else{*/
		return 16384.0;
		}


		@Override
	public Packet getDescriptionPacket() {

		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
		this.readFromNBT(pkt.func_148857_g());
		super.onDataPacket(net, pkt);
	}
}