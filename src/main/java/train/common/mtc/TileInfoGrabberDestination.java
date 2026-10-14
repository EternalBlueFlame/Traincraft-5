package train.common.mtc;

import net.minecraft.util.ITickable;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import train.common.api.Locomotive;

import java.util.List;

public class TileInfoGrabberDestination extends TileEntity implements ITickable, IPeripheral {

	// 1.12: modded tile entities are dropped on any block state change by default; only drop them when the block changes
	@Override
	public boolean shouldRefresh(net.minecraft.world.World world, net.minecraft.util.math.BlockPos pos, net.minecraft.block.state.IBlockState oldState, net.minecraft.block.state.IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

    public Boolean isActivated = false;
    public String trainDestination = "";
    public Boolean trainThere = false;
    public AxisAlignedBB boundingBox = null;

    public TileInfoGrabberDestination() {
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);

        this.isActivated = nbttagcompound.getBoolean("isActivated");
        this.trainDestination = nbttagcompound.getString("trainDestination");
        //this.trainID = nbttagcompound.getString("trainID");

    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        if (boundingBox == null) {
            boundingBox = ebf.tim.utility.CommonUtil.createAABB(getPos().getX(), getPos().getY(), getPos().getZ(), getPos().getX() + 2, getPos().getY() + 2, getPos().getZ() + 2);
        }
        return boundingBox;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        //    nbttagcompound.setBoolean("isActivated", this.isActivated);
        //   nbttagcompound.setInteger("setSpeed", this.setSpeed);

        nbttagcompound.setBoolean("isActivated", this.isActivated);
        nbttagcompound.setString("trainDestination", this.trainDestination);
    	return nbttagcompound;
    }

    @Override
    public String getType() {
        return "info_receiver_destination";
    }

    @Override
    public String[] getMethodNames() {
        return new String[]  {"activate", "deactivate", "getDestination", "isTrainOverSensor"};
    }

    @Override
    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
        switch(method) {
            case 0: {
                isActivated = true;
                return new Object[] {true};

            } case 1: {
                isActivated = false;
                return new Object[] {true};

            } case 2: {

                return new Object[]{trainDestination};

            } case 3: {
                return new Object[] {trainThere};



            } default:
                return new Object[] {"nil"};
        }
    }

    @Override
    public void update() {
        if (getWorld() == null) {
            return;
        }
        if (isActivated) {
            List<?> list = this.world.getEntitiesWithinAABBExcludingEntity(null, this.getRenderBoundingBox());
            if (list != null && list.size() > 0) {
                for (Object obj : list) {

                    if (obj instanceof Locomotive) {

                        Locomotive daTrain = (Locomotive) obj;
                        if (daTrain.mtcOverridePressed) { return;}
                       trainThere = true;
                       trainDestination = daTrain.getDestinationGUI();
                    }
                }
            }
        }
    }

    @Override
    public void attach(IComputerAccess computer) {

    }

    @Override
    public void detach(IComputerAccess computer) {

    }

    @Override
    public boolean equals(IPeripheral other) {
        return false;
    }
}
