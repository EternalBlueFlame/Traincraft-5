/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.signals;

import mods.railcraft.api.core.CollectionToolsAPI;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class SignalBlockRelay extends SignalBlock {

    private final Map<BlockPos, SignalAspect> aspects = CollectionToolsAPI.blockPosMap(new HashMap<>());

    public SignalBlockRelay(String locTag, TileEntity tile) {
        super(locTag, tile, 2);
    }

    @Override
    protected void updateSignalAspect() {
        aspects.keySet().retainAll(getPairs());
        for (BlockPos otherCoord : getPairs()) {
            aspects.put(otherCoord, determineAspect(otherCoord));
        }
    }

    @Override
    public SignalAspect getSignalAspect() {
        if (isWaitingForRetest() || isBeingPaired()) {
            return SignalAspect.BLINK_YELLOW;
        }
        if (!isPaired()) {
            return SignalAspect.BLINK_RED;
        }
        SignalAspect aspect = SignalAspect.GREEN;
        for (BlockPos otherCoord : getPairs()) {
            aspect = SignalAspect.mostRestrictive(aspect, aspects.get(otherCoord));
        }
        return aspect;
    }

    @Override
    protected SignalAspect getSignalAspectForPair(BlockPos otherCoord) {
        SignalAspect aspect = SignalAspect.GREEN;
        for (Map.Entry<BlockPos, SignalAspect> entry : aspects.entrySet()) {
            if (entry.getKey().equals(otherCoord)) {
                continue;
            }
            aspect = SignalAspect.mostRestrictive(aspect, entry.getValue());
        }
        return aspect;
    }

    @Override
    protected void saveNBT(NBTTagCompound data) {
        super.saveNBT(data);
        NBTTagList tagList = data.getTagList("aspects", 10);
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound nbt = tagList.getCompoundTagAt(i);
            BlockPos coord = SignalTools.readFromNBT(nbt, "coord");
            SignalAspect aspect = SignalAspect.readFromNBT(nbt, "aspect");
            aspects.put(coord, aspect);
        }
    }

    @Override
    protected void loadNBT(NBTTagCompound data) {
        super.loadNBT(data);
        NBTTagList tagList = new NBTTagList();
        for (Map.Entry<BlockPos, SignalAspect> entry : aspects.entrySet()) {
            NBTTagCompound nbt = new NBTTagCompound();
            if (entry.getKey() != null && entry.getValue() != null) {
                SignalTools.writeToNBT(nbt, "coord", entry.getKey());
                entry.getValue().writeToNBT(nbt, "aspect");
                tagList.appendTag(nbt);
            }
        }
        data.setTag("aspects", tagList);
    }
}
