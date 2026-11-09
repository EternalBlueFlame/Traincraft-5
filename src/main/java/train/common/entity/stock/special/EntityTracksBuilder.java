package train.common.entity.stock.special;

import com.mojang.authlib.GameProfile;
import ebf.tim.utility.CommonUtil;
import ebf.tim.api.SkinRegistry;
import fexcraft.tmt.slim.ModelBase;
import mods.railcraft.api.core.items.ITrackItem;
import mods.railcraft.api.tracks.RailTools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.BlockEvent;
import train.common.Traincraft;
import train.common.adminbook.ServerLogger;
import train.common.api.*;
import train.common.blocks.BlockTCRail;
import train.common.blocks.BlockTCRailGag;
import train.common.core.handlers.FuelHandler;
import train.common.core.plugins.PluginRailcraft;
import train.common.items.ItemRollingStock;
import train.common.items.ItemTCRail;
import train.common.library.GuiIDs;
import train.common.library.Info;
import train.common.library.ItemIDs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntityTracksBuilder extends EntityRollingStock {

    private ItemStack[] builderInventory;
    public int numBuilderSlots;
    public int numBuilderSlots1;
    public int numBuilderSlots2;
    public int numBuilderSlots3;
    public int inventoryBuilderSize;
    public int MoreBuilderInvent;
    public int trackfuel;
    public double currentHeight;
    public int plannedHeight;
    public boolean followTracks = true;
    public double pushX;
    public double pushZ;
    private int fuel;
    private boolean building;
    private int miningTickCounter;
    private int lastFace = -1;
    private boolean skipTick = true;
    private FakePlayer fakeplayer;

    private static final int SLOT_RAIL = 1;
    private static final int SLOT_UNDER_2 = 2;
    private static final int SLOT_UNDER = 3;
    private static final int SLOT_ROOF_RIGHT = 4;
    private static final int SLOT_ROOF_CENTER = 5;
    private static final int SLOT_ROOF_LEFT = 6;
    private static final int SLOT_WALL = 7;

    public static final Item thisItem = new ItemRollingStock(new EntityTracksBuilder(null), Info.modID+":+train_builder", Traincraft.tcTab); 

    public EntityTracksBuilder(World world) {
        super(world);
        numBuilderSlots = numBuilderSlots1 = numBuilderSlots2 = numBuilderSlots3 = 8;
        MoreBuilderInvent = 20;
        inventoryBuilderSize = numBuilderSlots + numBuilderSlots1 + numBuilderSlots2 + numBuilderSlots3 + MoreBuilderInvent;
        builderInventory = new ItemStack[inventoryBuilderSize];
        if (world != null) {
            dataWatcher.addObject(24, fuel);
            dataWatcher.addObject(26, plannedHeight);
            dataWatcher.addObject(27, 1);
            dataWatcher.addObject(28, 0);
        }
        if (world instanceof WorldServer) {
            String owner = trainOwner == null || trainOwner.length() == 0 ? "[Traincraft]" : trainOwner;
            fakeplayer = new FakePlayer((WorldServer) world,
                    new GameProfile(UUID.nameUUIDFromBytes(owner.getBytes()), owner));
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (worldObj == null) return;
        if (rand.nextInt(4) == 0) Smoke();
        currentHeight = posY;
        if (worldObj.isRemote) return;
        updateFuel();
        moveStacks();
        updatePushForces();
        int x = CommonUtil.floorDouble(posX);
        int y = CommonUtil.floorDouble(posY);
        int z = CommonUtil.floorDouble(posZ);
        if (skipTick) {
            skipTick = false;
            motionX = motionZ = 0;
        } else if (followTracks && canDig()) {
            building = true;
            dataWatcher.updateObject(28, 1);
            applyDragAndPushForces();
            digBuilder(x, y, z);
        } else {
            building = false;
            dataWatcher.updateObject(28, 0);
            motionX = motionZ = 0;
        }
        if (ticksExisted % 4 == 0) fillLinkedInventory();
    }

    @Override
    public boolean interactFirst(EntityPlayer player) {
        playerEntity = player;
        if (super.interactFirst(player)) return false;
        if (worldObj != null && !worldObj.isRemote) {
            player.openGui(Traincraft.instance, GuiIDs.BUILDER, worldObj, getEntityId(), -1, (int) posZ);
            pushX = posX - player.posX;
            pushZ = posZ - player.posZ;
        }
        return true;
    }

    public ItemStack[] getInventory() { return builderInventory; }

    @Override
    public int getSizeInventory() { return inventoryBuilderSize; }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return builderInventory == null || slot < 0 || slot >= builderInventory.length ? null : builderInventory[slot];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (builderInventory == null || slot < 0 || slot >= builderInventory.length) return null;
        ItemStack stack = builderInventory[slot];
        builderInventory[slot] = null;
        return stack;
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = getStackInSlot(slot);
        if (stack == null) return null;
        if (stack.stackSize <= amount) {
            builderInventory[slot] = null;
            return stack;
        }
        ItemStack split = stack.splitStack(amount);
        if (stack.stackSize == 0) builderInventory[slot] = null;
        return split;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (builderInventory == null || slot < 0 || slot >= builderInventory.length) return;
        builderInventory[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) stack.stackSize = getInventoryStackLimit();
    }

    @Override
    public String getInventoryName() { return "Tracks Builder"; }

    @Override
    public int getInventoryStackLimit() { return 64; }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return !isDead && player.getDistanceSqToEntity(this) <= 64.0D;
    }

    public boolean canInteractWith(EntityPlayer player) { return isUseableByPlayer(player); }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) { return true; }

    @Override
    public void openInventory() { }

    @Override
    public void closeInventory() { }

    @Override
    public void markDirty() { }

    public int getFuel() {
        return worldObj != null && worldObj.isRemote ? dataWatcher.getWatchableObjectInt(24) : fuel;
    }

    public boolean getState() {
        return worldObj != null && dataWatcher.getWatchableObjectInt(28) == 1;
    }

    public void setPlannedHeightFromPacket(int delta) {
        setPlannedHeight(Math.max(4, Math.min(256, getPlannedHeight() + delta)));
    }

    public void setPlannedHeight(int height) {
        plannedHeight = height;
        if (worldObj != null) dataWatcher.updateObject(26, height);
    }

    public int getPlannedHeight() {
        return worldObj == null ? plannedHeight : dataWatcher.getWatchableObjectInt(26);
    }

    public void setFollowTracksFromPacket(int follow) { setFollowTracks(follow); }

    public void setFollowTracks(int follow) {
        followTracks = follow == 1;
        if (worldObj != null) dataWatcher.updateObject(27, followTracks ? 1 : 0);
    }

    public int getFollowTracks() {
        return worldObj == null ? (followTracks ? 1 : 0) : dataWatcher.getWatchableObjectInt(27);
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setInteger("plannedHeight", plannedHeight);
        tag.setBoolean("followTracks", followTracks);
        tag.setDouble("PushX", pushX);
        tag.setDouble("PushZ", pushZ);
        tag.setShort("Trackfuel", (short) trackfuel);
        tag.setShort("fuel", (short) getFuel());
        NBTTagList items = new NBTTagList();
        if (builderInventory != null) {
            for (int i = 0; i < builderInventory.length; i++) {
                if (builderInventory[i] != null) {
                    NBTTagCompound slotTag = new NBTTagCompound();
                    slotTag.setByte("Slot", (byte) i);
                    builderInventory[i].writeToNBT(slotTag);
                    items.appendTag(slotTag);
                }
            }
        }
        tag.setTag("Items", items);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        int savedHeight = tag.getInteger("plannedHeight");
        setPlannedHeight(savedHeight < 4 ? (int) posY : savedHeight);
        setFollowTracks(tag.getBoolean("followTracks") ? 1 : 0);
        pushX = tag.getDouble("PushX");
        pushZ = tag.getDouble("PushZ");
        trackfuel = tag.getShort("Trackfuel");
        fuel = tag.getShort("fuel");
        NBTTagList items = tag.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound slotTag = items.getCompoundTagAt(i);
            int slot = slotTag.getByte("Slot") & 0xff;
            if (slot < builderInventory.length) builderInventory[slot] = ItemStack.loadItemStackFromNBT(slotTag);
        }
    }

    public void Smoke() {
        if (fuel > 0) {
            fuel--;
            if (worldObj != null && !worldObj.isRemote) dataWatcher.updateObject(24, fuel);
        }
    }

    public int scaleMaxFuel(int scale) { return fuel * scale / 5000; }

    public static boolean canBeBallast(ItemStack stack) {
        if (stack == null || !(stack.getItem() instanceof ItemBlock)) return false;
        Block block = Block.getBlockFromItem(stack.getItem());
        return block instanceof BlockFalling || canBeTunnel(stack);
    }

    public static boolean canBeTunnel(ItemStack stack) {
        if (stack == null || !(stack.getItem() instanceof ItemBlock)) return false;
        Block block = Block.getBlockFromItem(stack.getItem());
        return block != null && !block.hasTileEntity(stack.getItem().getMetadata(stack.getItemDamage()))
                && block.getRenderType() == 0 && !(block instanceof BlockFalling);
    }

    private void updatePushForcesLegacy() {
        double length = MathHelper.sqrt_double(pushX * pushX + pushZ * pushZ);
        if (length > 0.01D && motionX * motionX + motionZ * motionZ > 0.001D) {
            pushX /= length;
            pushZ /= length;
            if (pushX * motionX + pushZ * motionZ < 0) {
                pushX = pushZ = 0;
            } else {
                pushX = motionX;
                pushZ = motionZ;
            }
        }
    }

    private void applyDragAndPushForcesLegacy() {
        double length = MathHelper.sqrt_double(pushX * pushX + pushZ * pushZ);
        if (length > 0.01D) {
            pushX /= length;
            pushZ /= length;
            motionX *= 0.4D;
            motionZ *= 0.4D;
            motionX += pushX * 0.029D;
            motionZ += pushZ * 0.029D;
        } else {
            motionX *= 0.7D;
            motionZ *= 0.7D;
        }
        motionX *= 0.98D;
        motionZ *= 0.98D;
    }

    private void digBuilderLegacy(int x, int y, int z) {
        int face = getFacing();
        int dx = face == 2 ? 1 : face == 0 ? -1 : 0;
        int dz = face == 1 ? -1 : face == 3 ? 1 : 0;
        int height = getPlannedHeight() - (int) currentHeight;
        height = height == 0 || builderInventory[SLOT_RAIL].getItem() instanceof ItemTCRail ? 0 : height / Math.abs(height);
        if (!digAndPlace(x, y, z, dx, height, dz)) {
            motionX = motionY = motionZ = 0;
        }
    }

    private int getFacingLegacy() {
        rotation = CommonUtil.atan2degreesf(-motionX, -motionZ);
        if (motionX == 0 && motionZ == 0) rotation = rotationYaw;
        lastFace = MathHelper.floor_double(rotation * 4.0F / 360.0F + 0.5D) & 3;
        return lastFace;
    }

    private boolean digAndPlaceLegacy(int x, int y, int z, int dx, int height, int dz) {
        if (dx == 0 && dz == 0) return false;
        int sideX = dz;
        int sideZ = dx;
        int railX = x + dx;
        int railY = y + height;
        int railZ = z + dz;
        if (height > 0) { railX += dx; railZ += dz; }
        boolean success = true;
        for (int side = -1; side <= 1; side++) {
            for (int up = 1; up < 4 + Math.abs(height); up++) {
                success &= replaceBlockAt(railX + side * sideX, railY + up, railZ + side * sideZ, -1);
            }
        }
        success &= replaceBlockAt(railX - sideX, railY, railZ - sideZ, -1);
        success &= replaceBlockAt(railX + sideX, railY, railZ + sideZ, -1);
        for (int up = 0; up < 4; up++) {
            success &= replaceBlockAt(railX - 2 * sideX, railY + up, railZ - 2 * sideZ, SLOT_WALL);
            success &= replaceBlockAt(railX + 2 * sideX, railY + up, railZ + 2 * sideZ, SLOT_WALL);
        }
        success &= replaceBlockAt(railX - sideX, railY + 4, railZ - sideZ, SLOT_ROOF_RIGHT);
        success &= replaceBlockAt(railX, railY + 4, railZ, SLOT_ROOF_CENTER);
        success &= replaceBlockAt(railX + sideX, railY + 4, railZ + sideZ, SLOT_ROOF_LEFT);
        success &= replaceBlockAt(railX, railY - 2, railZ, SLOT_UNDER_2);
        success &= replaceBlockAt(railX, railY - 1, railZ, SLOT_UNDER);
        Block under = CommonUtil.getBlockAt(worldObj, railX, railY - 1, railZ);
        if (under instanceof BlockFalling && BlockFalling.func_149831_e(worldObj, railX, railY - 2, railZ)) {
            skipTick = true;
            return false;
        }
        success &= placeRailAt(railX, railY, railZ);
        return success;
    }

    private boolean replaceBlockAtLegacy(int x, int y, int z, int inventorySlot) {
        Block replacement = Blocks.air;
        int metadata = 0;
        boolean consume = inventorySlot > 0 && inventorySlot < builderInventory.length;
        if (consume) {
            ItemStack stack = builderInventory[inventorySlot];
            if (stack == null || stack.stackSize <= 0 || !(stack.getItem() instanceof ItemBlock)) return true;
            replacement = Block.getBlockFromItem(stack.getItem());
            metadata = stack.getItemDamage();
        }
        Block current = worldObj.getBlock(x, y, z);
        if (current == replacement && worldObj.getBlockMetadata(x, y, z) == metadata) return true;
        if (fakeplayer != null) {
            BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(x, y, z, worldObj, current,
                    worldObj.getBlockMetadata(x, y, z), fakeplayer);
            MinecraftForge.EVENT_BUS.post(event);
            if (event.isCanceled()) return false;
        }
        harvestBlock(x, y, z);
        worldObj.setBlock(x, y, z, replacement, metadata, 3);
        if (consume) decrStackSize(inventorySlot, 1);
        return true;
    }

    private void harvestBlockLegacy(int x, int y, int z) {
        Block block = worldObj.getBlock(x, y, z);
        int metadata = worldObj.getBlockMetadata(x, y, z);
        List<ItemStack> drops = block.getDrops(worldObj, x, y, z, metadata, 0);
        for (ItemStack drop : new ArrayList<ItemStack>(drops)) putInInvent(drop);
        worldObj.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (metadata << 12));
    }

    private void putInInventLegacy(ItemStack stack) {
        if (stack == null) return;
        for (int slot = MoreBuilderInvent; slot < builderInventory.length; slot++) {
            ItemStack current = builderInventory[slot];
            if (current == null) {
                builderInventory[slot] = stack;
                return;
            }
                if (current.getItem() == stack.getItem() && current.getItemDamage() == stack.getItemDamage()
                    && ItemStack.areItemStackTagsEqual(current, stack) && current.stackSize < current.getMaxStackSize()) {
                current.stackSize++;
                return;
            }
        }
        entityDropItem(stack, 1.0F);
    }

    private boolean placeRailAtLegacy(int x, int y, int z) {
        if (builderInventory[SLOT_RAIL] == null) return false;
        Block current = worldObj.getBlock(x, y, z);
        if (BlockRailBase.func_150051_a(current) || current instanceof BlockTCRail || current instanceof BlockTCRailGag) return true;
        if (fakeplayer != null) {
            BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(x, y, z, worldObj, current,
                    worldObj.getBlockMetadata(x, y, z), fakeplayer);
            MinecraftForge.EVENT_BUS.post(event);
            if (event.isCanceled()) return false;
        }
        harvestBlock(x, y, z);
        worldObj.setBlockToAir(x, y, z);
        ItemStack rail = builderInventory[SLOT_RAIL];
        if (!placeRailAt(this, rail, worldObj, x, y, z)) return false;
        decrStackSize(SLOT_RAIL, 1);
        Smoke();
        return true;
    }

    private boolean placeRailAt(int x, int y, int z) {
        return placeRailAtLegacy(x, y, z);
    }

    private boolean canBeBallastHereLegacy() { return canBeBallast(builderInventory[SLOT_UNDER]); }

    private void updateFuel() {
        if (fuel >= 5000 || builderInventory[0] == null) return;
        if (FuelHandler.steamFuelLast(builderInventory[0]) > 0 && fuel + 300 <= 5000) {
            fuel += 300;
            decrStackSize(0, 1);
        } else if (PluginRailcraft.RailcraftParts.INGOT_STEEL.stack != null
                && builderInventory[0].isItemEqual(PluginRailcraft.RailcraftParts.INGOT_STEEL.stack)
                && fuel + 800 <= 5000) {
            fuel += 800;
            decrStackSize(0, 1);
        }
        dataWatcher.updateObject(24, fuel);
    }

    private void moveStacks() {
        if (builderInventory[SLOT_RAIL] == null) {
            for (int i = 8; i <= 10; i++) {
                ItemStack stack = builderInventory[i];
                if (stack != null && (RailTools.isTrackItem(stack) || BlockRailBase.func_150051_a(Block.getBlockFromItem(stack.getItem())))) {
                    builderInventory[SLOT_RAIL] = stack.copy();
                    decrStackSize(i, stack.stackSize);
                    break;
                }
            }
        }
        if (builderInventory[SLOT_UNDER] == null) {
            for (int i = 11; i <= 13; i++) if (canBeBallast(builderInventory[i])) {
                builderInventory[SLOT_UNDER] = builderInventory[i].copy(); decrStackSize(i, builderInventory[i].stackSize); break;
            }
        }
        if (builderInventory[SLOT_UNDER_2] == null) {
            for (int i = 14; i <= 16; i++) if (canBeBallast(builderInventory[i])) {
                builderInventory[SLOT_UNDER_2] = builderInventory[i].copy(); decrStackSize(i, builderInventory[i].stackSize); break;
            }
        }
        if (builderInventory[SLOT_WALL] == null) {
            for (int i = 17; i <= 19; i++) if (canBeTunnel(builderInventory[i])) {
                builderInventory[SLOT_WALL] = builderInventory[i].copy(); decrStackSize(i, builderInventory[i].stackSize); break;
            }
        }
    }

    private void updatePushForces() {
        double length = MathHelper.sqrt_double(pushX * pushX + pushZ * pushZ);
        if (length > 0.01D && motionX * motionX + motionZ * motionZ > 0.001D) {
            pushX /= length; pushZ /= length;
            if (pushX * motionX + pushZ * motionZ < 0) pushX = pushZ = 0;
            else { pushX = motionX; pushZ = motionZ; }
        }
    }

    private void applyDragAndPushForces() {
        double length = MathHelper.sqrt_double(pushX * pushX + pushZ * pushZ);
        if (length > 0.01D) {
            pushX /= length; pushZ /= length;
            motionX = motionX * 0.4D + pushX * 0.029D;
            motionZ = motionZ * 0.4D + pushZ * 0.029D;
        } else { motionX *= 0.7D; motionZ *= 0.7D; }
        motionX *= 0.98D; motionZ *= 0.98D;
    }

    private boolean canDig() { return canBeBallastHere() && builderInventory[SLOT_RAIL] != null && getFuel() > 0; }

    private boolean canBeBallastHere() { return canBeBallast(builderInventory[SLOT_UNDER]); }

    private void digBuilder(int x, int y, int z) {
        int face = getFacing();
        int dx = face == 2 ? 1 : face == 0 ? -1 : 0;
        int dz = face == 1 ? -1 : face == 3 ? 1 : 0;
        int height = builderInventory[SLOT_RAIL].getItem() instanceof ItemTCRail ? 0 : getPlannedHeight() - (int) currentHeight;
        if (height != 0) height /= Math.abs(height);
        if (!digAndPlace(x, y, z, dx, height, dz)) motionX = motionY = motionZ = 0;
    }

    private int getFacing() {
        if (motionX != 0 || motionZ != 0) rotation = CommonUtil.atan2degreesf(motionZ, motionX);
        else rotation = rotationYaw;
        lastFace = MathHelper.floor_double(rotation * 4.0F / 360.0F + 0.5D) & 3;
        return lastFace;
    }

    private boolean digAndPlace(int x, int y, int z, int dx, int height, int dz) {
        int sideX = dz, sideZ = dx;
        int railX = x + dx, railY = y + height, railZ = z + dz;
        if (height > 0) { railX += dx; railZ += dz; }
        boolean success = true;
        for (int side = -1; side <= 1; side++) for (int up = 1; up < 4 + Math.abs(height); up++)
            success &= replaceBlockAt(railX + side * sideX, railY + up, railZ + side * sideZ, -1);
        success &= replaceBlockAt(railX - sideX, railY, railZ - sideZ, -1);
        success &= replaceBlockAt(railX + sideX, railY, railZ + sideZ, -1);
        for (int up = 0; up < 4; up++) {
            success &= replaceBlockAt(railX - 2 * sideX, railY + up, railZ - 2 * sideZ, SLOT_WALL);
            success &= replaceBlockAt(railX + 2 * sideX, railY + up, railZ + 2 * sideZ, SLOT_WALL);
        }
        success &= replaceBlockAt(railX - sideX, railY + 4, railZ - sideZ, SLOT_ROOF_RIGHT);
        success &= replaceBlockAt(railX, railY + 4, railZ, SLOT_ROOF_CENTER);
        success &= replaceBlockAt(railX + sideX, railY + 4, railZ + sideZ, SLOT_ROOF_LEFT);
        success &= replaceBlockAt(railX, railY - 2, railZ, SLOT_UNDER_2);
        success &= replaceBlockAt(railX, railY - 1, railZ, SLOT_UNDER);
        Block under = worldObj.getBlock(railX, railY - 1, railZ);
        if (under instanceof BlockFalling && BlockFalling.func_149831_e(worldObj, railX, railY - 2, railZ)) {
            skipTick = true; return false;
        }
        return success & placeRailAt(railX, railY, railZ);
    }

    private boolean replaceBlockAt(int x, int y, int z, int slot) {
        Block replacement = Blocks.air;
        int metadata = 0;
        boolean consume = slot > 0 && slot < builderInventory.length;
        if (consume) {
            ItemStack stack = builderInventory[slot];
            if (stack == null || !(stack.getItem() instanceof ItemBlock)) return true;
            replacement = Block.getBlockFromItem(stack.getItem()); metadata = stack.getItemDamage();
        }
        Block current = worldObj.getBlock(x, y, z);
        if (current == replacement && worldObj.getBlockMetadata(x, y, z) == metadata) return true;
        if (fakeplayer != null) {
            BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(x, y, z, worldObj, current, worldObj.getBlockMetadata(x, y, z), fakeplayer);
            MinecraftForge.EVENT_BUS.post(event);
            if (event.isCanceled()) return false;
        }
        harvestBlock(x, y, z);
        worldObj.setBlock(x, y, z, replacement, metadata, 3);
        if (consume) decrStackSize(slot, 1);
        return true;
    }

    private void harvestBlock(int x, int y, int z) {
        Block block = worldObj.getBlock(x, y, z);
        int metadata = worldObj.getBlockMetadata(x, y, z);
        List<ItemStack> drops = block.getDrops(worldObj, x, y, z, metadata, 0);
        for (ItemStack drop : new ArrayList<ItemStack>(drops)) putInInvent(drop);
        worldObj.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (metadata << 12));
    }

    private void putInInvent(ItemStack stack) {
        if (stack == null) return;
        for (int slot = MoreBuilderInvent; slot < builderInventory.length; slot++) {
            ItemStack current = builderInventory[slot];
            if (current == null) { builderInventory[slot] = stack; return; }
            if (current.getItem() == stack.getItem() && current.getItemDamage() == stack.getItemDamage()
                    && ItemStack.areItemStackTagsEqual(current, stack) && current.stackSize < current.getMaxStackSize()) {
                current.stackSize++;
                return;
            }
        }
        entityDropItem(stack, 1.0F);
    }

    private void fillLinkedInventory() {
        Freight linked = frontLink instanceof Freight ? (Freight) frontLink
                : backLink instanceof Freight ? (Freight) backLink : null;
        if (linked == null || linked.getInventory() == null || builderInventory == null) return;
        for (int slot = 1; slot < Math.min(11, builderInventory.length); slot++) {
            ItemStack target = builderInventory[slot];
            if (target == null || target.stackSize >= target.getMaxStackSize()) continue;
            for (int linkedSlot = 0; linkedSlot < linked.getInventory().length; linkedSlot++) {
                ItemStack source = linked.getStackInSlot(linkedSlot);
                if (source != null && source.getItem() == target.getItem()
                        && source.getItemDamage() == target.getItemDamage()) {
                    linked.decrStackSize(linkedSlot, 1);
                    target.stackSize++;
                    break;
                }
            }
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (worldObj != null && worldObj.isRemote) return true;
        if (canBeDestroyedByPlayer(source)) return true;
        super.attackEntityFrom(source, amount);
        setRollingDirection(-getRollingDirection());
        setRollingAmplitude(10);
        setBeenAttacked();
        setDamage(getDamage() + amount * 10);
        if (getDamage() > 40) {
            if (riddenByEntity != null) riddenByEntity.mountEntity(this);
            setDead();
            ServerLogger.deleteWagon(this);
            if (source.getEntity() instanceof EntityPlayer) {
                boolean creative = ((EntityPlayer) source.getEntity()).capabilities.isCreativeMode;
                dropCartAsItem(creative);
                if (!creative && builderInventory != null) {
                    for (ItemStack stack : builderInventory) if (stack != null) entityDropItem(stack, 0.0F);
                }
            }
        }
        return true;
    }

    private void updateState(boolean state) { dataWatcher.updateObject(28, state ? 1 : 0); }

    @Override
    public float getOptimalDistance(EntityMinecart cart) { return 2.1F; }

    public static boolean placeRailAt(EntityTracksBuilder builder, ItemStack stack, World world, int x, int y, int z) {
        if (stack == null || stack.getItem() == null) return false;
        if (stack.getItem() instanceof ITrackItem) {
            return ((ITrackItem) stack.getItem()).placeTrack(stack.copy(), world, x, y, z);
        }
        if (stack.getItem() instanceof ItemBlock) {
            Block block = ((ItemBlock) stack.getItem()).field_150939_a;
            if (BlockRailBase.func_150051_a(block)) {
                boolean placed = CommonUtil.setBlock(world, x, y, z, block);
                if (placed) {
                    world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, block.stepSound.func_150496_b(),
                            (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                }
                return placed;
            }
        }
        if (stack.getItem() instanceof ItemTCRail) {
            return ((ItemTCRail) stack.getItem()).onItemUse(stack, null, world, x, y, z, 0, 0, 0, builder.rotationYaw + 90);
        }
        return false;
    }
    //main stats
    @Override
    public String transportName(){return "Tracks Builder";}
    @Override
    public String transportcountry(){return "";}
    @Override
    public String transportYear(){return "";}
    @Override
    public boolean isFictional(){return false;}

    @Override
    public void registerSkins(){
        SkinRegistry.addSkin(this.getClass(), Info.modID,"textures/trains/builder2.png" , new String[]{} ,"default", "");
    }

    @Override
    public String getDefaultSkin(){return "default";}

    @Override
    public float transportTopSpeed(){return 0;}

    @Override
    public int getInventoryRows(){return 0;}

	@Override
	public float getPlayerScale(){ 	return 0.65f;}

	@Override
	public float transportMetricHorsePower(){return 0;}

	@Override
	public String[] additionalItemText() { return new String[] {""};}

	@Override
	public float weightKg(){ return 0.0f;}

	@Override
	public float[] rotationPoints(){ return new float[]{2.1f, -2.1f};}

    @Override
    public ItemStack[] getRecipe() {
        return new ItemStack[]{
                new ItemStack(Items.iron_ingot, 6), 
new ItemStack(ItemIDs.bogie.item, 2), 
new ItemStack(ItemIDs.steelframe.item, 3), 
new ItemStack(Items.iron_ingot, 1), 
new ItemStack(ItemIDs.steelchimney.item, 1), 
null, 
new ItemStack(ItemIDs.boiler.item, 1), 
new ItemStack(ItemIDs.firebox.item, 1), 
new ItemStack(Blocks.rail, 16),
new ItemStack(thisItem)
        };
    }


    @Override
    public Item getItem(){return thisItem;}
    @Override
    public int getTier(){
return 2;
}
    //Model stuff
    @Override
    public ModelBase[] getModel(){return new ModelBase[]{new train.client.render.models.ModelBuilder()};}
    @Override
    public float[][] modelOffsets(){return new float[][]{{0.0f, -0.08000001f, 0.0f}};}
@Override
    public float[][] modelRotations(){return new float[][]{{0f,180f,180f}};}
    @Override
    public float[][] getRenderScale(){return new float[][]{null};}
    //these are separated for being fiddly.
    @Override
    public float[][] getRiderOffsets(){return new float[][]{{0,1.2f, 0f}};}
    @Override
    public float[] getHitboxSize(){return new float[]{4.199999809265137f,2.1f,1.1f};}
    public TrainParticle[] getEffects(){
    return new TrainParticle[]{
            new TrainParticle("largesmoke", 3, new double[]{-1.3, 2.1, 0.0}),
    };
    }
}


    //these only change in very specific use cases.
