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
public abstract class SignalController extends AbstractPair {
    protected SignalController(String locTag, TileEntity tile, int maxPairings) {
        super(locTag, tile, maxPairings);
    }

    public @Nullable SignalReceiver getReceiverAt(BlockPos coord) {
        TileEntity recv = getPairAt(coord);
        if (recv != null) {
            return ((IReceiverTile) recv).getReceiver();
        }
        return null;
    }

    public abstract SignalAspect getAspectFor(BlockPos receiver);

    @Override
    public void informPairsOfNameChange() {
        for (BlockPos coord : getPairs()) {
            SignalReceiver recv = getReceiverAt(coord);
            if (recv != null) {
                recv.onPairNameChange(getCoords(), getName());
            }
        }
    }

    @Override
    protected String getTagName() {
        return "controller";
    }

    @Override
    public boolean isValidPair(BlockPos otherCoord, TileEntity otherTile) {
        if (otherTile instanceof IReceiverTile) {
            SignalReceiver receiver = ((IReceiverTile) otherTile).getReceiver();
            return receiver.isPairedWith(getCoords());
        }
        return false;
    }

    @Override
    public boolean createPair(TileEntity other) {
        if (other instanceof IReceiverTile) {
            registerReceiver(((IReceiverTile) other).getReceiver());
            return true;
        }
        return false;
    }

    protected void registerReceiver(SignalReceiver receiver) {
        BlockPos coords = receiver.getCoords();
        addPairing(coords);
        receiver.registerController(this);
        receiver.onControllerAspectChange(this, getAspectFor(coords));
    }

    @Override
    public void tickClient() {
        super.tickClient();
        if (SignalTools.effectManager != null && SignalTools.effectManager.isTuningAuraActive()) {
            for (BlockPos coord : getPairs()) {
                SignalReceiver receiver = getReceiverAt(coord);
                if (receiver != null) {
                    SignalTools.effectManager.tuningEffect(getTile(), receiver.getTile());
                }
            }
        }
    }
}
