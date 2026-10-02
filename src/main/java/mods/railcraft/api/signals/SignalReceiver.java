/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/
package mods.railcraft.api.signals;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public abstract class SignalReceiver extends AbstractPair {
    private boolean needsInit = true;

    protected SignalReceiver(String locTag, TileEntity tile, int maxPairings) {
        super(locTag, tile, maxPairings);
    }

    public @Nullable SignalController getControllerAt(BlockPos coord) {
        TileEntity con = getPairAt(coord);
        if (con != null) {
            return ((IControllerTile) con).getController();
        }
        return null;
    }

    @Override
    public void informPairsOfNameChange() {
        for (BlockPos coord : getPairs()) {
            SignalController ctrl = getControllerAt(coord);
            if (ctrl != null) {
                ctrl.onPairNameChange(getCoords(), getName());
            }
        }
    }

    @Override
    protected String getTagName() {
        return "receiver";
    }

    @Override
    public boolean isValidPair(BlockPos otherCoord, TileEntity otherTile) {
        if (otherTile instanceof IControllerTile) {
            SignalController controller = ((IControllerTile) otherTile).getController();
            return controller.isPairedWith(getCoords());
        }
        return false;
    }

    public void onControllerAspectChange(SignalController con, SignalAspect aspect) {
        ((IReceiverTile) tile).onControllerAspectChange(con, aspect);
    }

    @Override
    public boolean createPair(TileEntity other) {
        if (tile instanceof IControllerTile) {
            registerController(((IControllerTile) other).getController());
            return true;
        }
        return false;
    }

    protected void registerController(SignalController controller) {
        addPairing(controller.getCoords());
    }

    @Override
    public void tickServer() {
        super.tickServer();
        if (needsInit) {
            needsInit = false;
            for (BlockPos pair : getPairs()) {
                SignalController controller = getControllerAt(pair);
                if (controller != null) {
                    onControllerAspectChange(controller, controller.getAspectFor(getCoords()));
                }
            }
        }
    }
}
