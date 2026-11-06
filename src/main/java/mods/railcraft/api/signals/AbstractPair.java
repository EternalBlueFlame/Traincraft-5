/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/
package mods.railcraft.api.signals;

import com.google.common.collect.MapMaker;
import mods.railcraft.api.core.CollectionToolsAPI;
import mods.railcraft.api.core.INetworkedObject;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
public abstract class AbstractPair implements IPair, INetworkedObject<DataInputStream, DataOutputStream> {
    protected static final Random rand = new Random();
    private static final boolean IS_BUKKIT;

    static {
        boolean foundBukkit;
        try {
            foundBukkit = Class.forName("org.spigotmc.SpigotConfig") != null;
        } catch (ClassNotFoundException er) {
            foundBukkit = false;
        }
        IS_BUKKIT = foundBukkit;
    }

    private static final int SAFE_TIME = 32;
    private static final int PAIR_CHECK_INTERVAL = 16;
    public final TileEntity tile;
    public final String locTag;
    public final int maxPairings;
    protected final Deque<BlockPos> pairings = CollectionToolsAPI.blockPosDeque(LinkedList::new);
    protected final Set<BlockPos> invalidPairings = CollectionToolsAPI.blockPosSet(HashSet::new);
    private final Collection<BlockPos> safePairings = Collections.unmodifiableCollection(pairings);
    private final Set<BlockPos> pairingsToTest = CollectionToolsAPI.blockPosSet(HashSet::new);
    private final Set<BlockPos> pairingsToTestNext = CollectionToolsAPI.blockPosSet(HashSet::new);
    private final Map<BlockPos, TileEntity> tileCache = CollectionToolsAPI.blockPosMap(new MapMaker().weakValues().makeMap());
    private BlockPos coords;
    private boolean isBeingPaired;
    private int update = rand.nextInt();
    private int ticksExisted;
    private boolean needsInit = true;
    private @Nullable String name;

    protected AbstractPair(String locTag, TileEntity tile, int maxPairings) {
        this.tile = tile;
        this.maxPairings = maxPairings;
        this.locTag = locTag;
    }

    public @Nullable String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        if (name == null || this.name == null || !Objects.equals(this.name, name)) {
            this.name = name;
            informPairsOfNameChange();
        }
    }

    public void informPairsOfNameChange() {
    }

    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
    public void onPairNameChange(BlockPos coords, @Nullable String name) {
    }

    protected boolean isLoaded() {
        return ticksExisted >= SAFE_TIME;
    }

    protected void addPairing(BlockPos other) {
        pairings.remove(other);
        pairings.add(other);
        while (pairings.size() > getMaxPairings()) {
            pairings.remove();
        }
        SignalTools.packetBuilder.sendPairPacketUpdate(this);
    }

    public void clearPairing(BlockPos other) {
        invalidPairings.add(other);
    }

    @Override
    public void endPairing() {
        isBeingPaired = false;
    }

    public void tickClient() {
        if (needsInit) {
            needsInit = false;
            SignalTools.packetBuilder.sendPairPacketRequest(this);
        }
    }

    public void tickServer() {
        update++;
        if (!isLoaded())
            ticksExisted++;
        else if (update % PAIR_CHECK_INTERVAL == 0)
            validatePairings();
    }

    protected void validatePairings() {
        if (!pairingsToTestNext.isEmpty()) {
            pairingsToTestNext.retainAll(pairings);
            for (BlockPos coord : pairingsToTestNext) {

                World world = tile.getWorld();
                if (!world.isBlockLoaded(coord))
                    continue;

                IBlockState blockState = world.getBlockState(coord);
                if (!blockState.getBlock().hasTileEntity(blockState)) {
                    clearPairing(coord);
                    continue;
                }

                TileEntity target = world.getTileEntity(coord);
                if (target != null && !isValidPair(coord, target))
                    clearPairing(coord);
            }
            pairingsToTestNext.clear();
        }
        cleanPairings();
        for (BlockPos coord : pairings) {
            getPairAt(coord);
        }
        pairingsToTestNext.addAll(pairingsToTest);
        pairingsToTest.clear();
    }

    public void cleanPairings() {
        if (invalidPairings.isEmpty())
            return;
        boolean changed = pairings.removeAll(invalidPairings);
        invalidPairings.clear();
        if (changed)
            SignalTools.packetBuilder.sendPairPacketUpdate(this);
    }

    protected @Nullable TileEntity getPairAt(BlockPos coord) {
        if (!pairings.contains(coord))
            return null;

        boolean useCache;
        try {
            useCache = !IS_BUKKIT && SignalTools.isInSameChunk(getCoords(), coord);
        } catch (Throwable er) {
            useCache = false;
        }

        if (useCache) {
            TileEntity cacheTarget = tileCache.get(coord);
            if (cacheTarget != null) {
                if (cacheTarget.isInvalid() || !Objects.equals(cacheTarget.getPos(), coord))
                    tileCache.remove(coord);
                else if (isValidPair(coord, cacheTarget))
                    return cacheTarget;
            }
        }

        if (coord.getY() <= 0) {
            clearPairing(coord);
            return null;
        }

        World world = tile.getWorld();
        if (!world.isBlockLoaded(coord))
            return null;

        IBlockState blockState = world.getBlockState(coord);
        if (!blockState.getBlock().hasTileEntity(blockState)) {
            pairingsToTest.add(coord);
            return null;
        }

        TileEntity target = world.getTileEntity(coord);
        if (target != null && !isValidPair(coord, target)) {
            pairingsToTest.add(coord);
            return null;
        }

        if (useCache && target != null) {
            tileCache.put(coord, target);
        }

        return target;
    }

    @SuppressWarnings("UnusedParameters")
    public boolean isValidPair(BlockPos otherCoord, TileEntity otherTile) {
        return false;
    }

    public BlockPos getCoords() {
        if (coords == null)
            coords = tile.getPos().toImmutable();
        return coords;
    }

    public String getLocalizationTag() {
        return locTag;
    }

    public int getMaxPairings() {
        return maxPairings;
    }

    public int getNumPairs() {
        return pairings.size();
    }

    public boolean isPaired() {
        return !pairings.isEmpty();
    }

    public Collection<BlockPos> getPairs() {
        return safePairings;
    }

    public TileEntity getTile() {
        return tile;
    }

    @Override
    public void startPairing() {
        isBeingPaired = true;
    }

    public boolean isBeingPaired() {
        return isBeingPaired;
    }

    public boolean isPairedWith(BlockPos other) {
        return pairings.contains(other);
    }

    protected abstract String getTagName();

    public final void writeToNBT(NBTTagCompound data) {
        NBTTagCompound tag = new NBTTagCompound();
        saveNBT(tag);
        data.setTag(getTagName(), tag);
    }

    protected void saveNBT(NBTTagCompound data) {
        NBTTagList list = new NBTTagList();
        for (BlockPos c : pairings) {
            NBTTagCompound tag = new NBTTagCompound();
            SignalTools.writeToNBT(tag, "coords", c);
            list.appendTag(tag);
        }
        data.setTag("pairings", list);
        if (name != null) {
            data.setString("name", name);
        }
    }

    public final void readFromNBT(NBTTagCompound data) {
        NBTTagCompound tag = data.getCompoundTag(getTagName());
        loadNBT(tag);
    }

    protected void loadNBT(NBTTagCompound data) {
        NBTTagList list = data.getTagList("pairings", 10);
        for (byte entry = 0; entry < list.tagCount(); entry++) {
            NBTTagCompound tag = list.getCompoundTagAt(entry);
            BlockPos p = SignalTools.readFromNBT(tag, "coords");
            if (p != null)
                pairings.add(p);
        }
        this.name = data.getString("name");
        if (name.isEmpty()) {
            this.name = null;
        }
    }

    @Override
    public void writePacketData(DataOutputStream data) throws IOException {
        data.writeUTF(name != null ? name : "");
    }

    @Override
    public void readPacketData(DataInputStream data) throws IOException {
        this.name = data.readUTF();
        if (name.isEmpty()) {
            this.name = null;
        }
    }

    @Override
    public void sendUpdateToClient() {
        ((INetworkedObject) getTile()).sendUpdateToClient();
    }

    @Override
    public @Nullable World theWorld() {
        return getTile().getWorld();
    }

    @SideOnly(Side.CLIENT)
    public void addPair(BlockPos pos) {
        pairings.add(pos);
    }

    @SideOnly(Side.CLIENT)
    public void removePair(BlockPos pos) {
        pairings.remove(pos);
    }

    public void clearPairings() {
        pairings.clear();
        if (!tile.getWorld().isRemote)
            SignalTools.packetBuilder.sendPairPacketUpdate(this);
    }
}
