/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2020

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/
package mods.railcraft.api.carts;

import com.mojang.authlib.GameProfile;
import mods.railcraft.api.core.RailcraftConstantsAPI;
import mods.railcraft.api.core.RailcraftFakePlayer;
import mods.railcraft.api.items.IMinecartItem;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants.NBT;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SuppressWarnings({"WeakerAccess", "unused"})
public final class CartToolsAPI {
    private static ILinkageManager linkageManager = new ILinkageManager() {
    };
    private static ITrainTransferHelper transferHelper = new ITrainTransferHelper() {
    };

    public static ITrainTransferHelper transferHelper() {
        return transferHelper;
    }

    /**
     * Returns an instance of ILinkageManager.
     * <p/>
     * Will return null if Railcraft is not installed.
     *
     * @return an instance of ILinkageManager
     */
    public static ILinkageManager linkageManager() {
        return linkageManager;
    }

    /**
     * Sets a carts owner.
     * <p/>
     * The is really only needed by the bukkit ports.
     */
    public static void setCartOwner(EntityMinecart cart, EntityPlayer owner) {
        setCartOwner(cart, owner.getGameProfile());
    }

    /**
     * Sets a carts owner.
     * <p/>
     * The is really only needed by the bukkit ports.
     */
    public static void setCartOwner(EntityMinecart cart, GameProfile owner) {
        if (!cart.getEntityWorld().isRemote) {
            NBTTagCompound data = cart.getEntityData();
            data.setTag("owner", NBTUtil.writeGameProfile(new NBTTagCompound(), new GameProfile(owner.getId(), owner.getName())));
//            if (owner.getName() != null)
//                data.setString("owner", owner.getName());
//            if (owner.getId() != null)
//                data.setString("ownerId", owner.getId().toString());
        }
    }

    /**
     * Gets a carts owner. (player.username)
     * <p/>
     * The is really only needed by the bukkit ports.
     */
    public static GameProfile getCartOwner(EntityMinecart cart) {
        NBTTagCompound data = cart.getEntityData();

        if (data.hasKey("owner", NBT.TAG_COMPOUND)) {
            GameProfile profile = NBTUtil.readGameProfileFromNBT(data.getCompoundTag("owner"));
            if (profile != null) {
                return profile;
            }
        }

        String ownerName = RailcraftConstantsAPI.UNKNOWN_PLAYER;
        if (data.hasKey("owner", NBT.TAG_STRING))
            ownerName = data.getString("owner");

        UUID ownerId = null;
        if (data.hasKey("ownerId"))
            ownerId = UUID.fromString(data.getString("ownerId"));
        return new GameProfile(ownerId, ownerName);
    }

    /**
     * Does the cart have a owner?
     * <p/>
     * The is really only needed by the bukkit ports.
     */
    public static boolean doesCartHaveOwner(EntityMinecart cart) {
        NBTTagCompound data = cart.getEntityData();
        return data.hasKey("owner");
    }

    /**
     * Spawns a new cart entity using the provided item.
     * <p/>
     * The backing item must implement {@code IMinecartItem} and/or extend
     * {@code ItemMinecart}.
     * <p/>
     * Generally Forge requires all cart items to extend ItemMinecart.
     *
     * @param owner The player name that should used as the owner
     * @param cart  An ItemStack containing a cart item, will not be changed by
     *              the function
     * @param world The World object
     * @return the cart placed or null if failed
     * @see IMinecartItem, ItemMinecart
     */
    public static @Nullable EntityMinecart placeCart(GameProfile owner, ItemStack cart, WorldServer world, BlockPos pos) {
        cart = cart.copy();
        if (cart.getItem() instanceof IMinecartItem) {
            IMinecartItem mi = (IMinecartItem) cart.getItem();
            return mi.placeCart(owner, cart, world, pos);
        } else if (cart.getItem() instanceof ItemMinecart)
            try {
                EnumActionResult placed = cart.getItem().onItemUse(RailcraftFakePlayer.get(world, pos, cart, EnumHand.MAIN_HAND), world, pos, EnumHand.MAIN_HAND, EnumFacing.DOWN, 0, 0, 0);
                if (placed == EnumActionResult.SUCCESS) {
                    List<EntityMinecart> carts = getMinecartsAt(world, pos, 0.3f);
                    if (!carts.isEmpty()) {
                        setCartOwner(carts.get(0), owner);
                        return carts.get(0);
                    }
                }
            } catch (Exception e) {
                return null;
            }

        return null;
    }

    // Most of these functions have been replaced internally by the EntitySearcher, but they remain here in the API
    // for historic purposes and the fact that the EntitySearcher isn't part of the API.

    public static boolean isMinecartOnRailAt(World world, BlockPos pos, float sensitivity) {
        return isMinecartOnRailAt(world, pos, sensitivity, EntityMinecart.class);
    }

    public static boolean isMinecartOnRailAt(World world, BlockPos pos, float sensitivity, Class<? extends EntityMinecart> type) {
        return BlockRailBase.isRailBlock(world, pos) && isMinecartAt(world, pos, sensitivity, type);
    }

    public static boolean isMinecartOnAnySide(World world, BlockPos pos, float sensitivity) {
        return isMinecartOnAnySide(world, pos, sensitivity, EntityMinecart.class);
    }

    public static boolean isMinecartOnAnySide(World world, BlockPos pos, float sensitivity, Class<? extends EntityMinecart> type) {
        return Arrays.stream(EnumFacing.VALUES).anyMatch(side -> !getMinecartsOnSide(world, pos, sensitivity, side, type).isEmpty());
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isMinecartAt(World world, BlockPos pos, float sensitivity) {
        return isMinecartAt(world, pos, sensitivity, EntityMinecart.class);
    }

    public static boolean isMinecartAt(World world, BlockPos pos, float sensitivity, Class<? extends EntityMinecart> type) {
        return !getMinecartsAt(world, pos, sensitivity, type).isEmpty();
    }

    public static List<EntityMinecart> getMinecartsOnAllSides(World world, BlockPos pos, float sensitivity) {
        return getMinecartsOnAllSides(world, pos, sensitivity, EntityMinecart.class);
    }

    public static <T extends EntityMinecart> List<T> getMinecartsOnAllSides(World world, BlockPos pos, float sensitivity, Class<T> type) {
        return Arrays.stream(EnumFacing.VALUES).flatMap(side -> getMinecartsOnSide(world, pos, sensitivity, side, type).stream()).collect(Collectors.toList());
    }

    public static boolean isMinecartOnSide(World world, BlockPos pos, float sensitivity, EnumFacing side) {
        return getMinecartOnSide(world, pos, sensitivity, side) != null;
    }

    public static boolean isMinecartOnSide(World world, BlockPos pos, float sensitivity, EnumFacing side, Class<? extends EntityMinecart> type) {
        return getMinecartOnSide(world, pos, sensitivity, side, type) != null;
    }

    public static List<EntityMinecart> getMinecartsOnSide(World world, BlockPos pos, float sensitivity, EnumFacing side) {
        return getMinecartsOnSide(world, pos, sensitivity, side, EntityMinecart.class);
    }

    public static <T extends EntityMinecart> List<T> getMinecartsOnSide(World world, BlockPos pos, float sensitivity, EnumFacing side, Class<T> type) {
        return getMinecartsAt(world, pos.offset(side), sensitivity, type);
    }

    public static @Nullable EntityMinecart getMinecartOnSide(World world, BlockPos pos, float sensitivity, EnumFacing side) {
        return getMinecartOnSide(world, pos, sensitivity, side, EntityMinecart.class);
    }

    public static @Nullable <T extends EntityMinecart> T getMinecartOnSide(World world, BlockPos pos, float sensitivity, EnumFacing side, Class<T> type) {
        return getMinecartsOnSide(world, pos, sensitivity, side, type).stream().findAny().orElse(null);
    }

    /**
     * @param sensitivity Controls the size of the search box, ranges from
     *                    (-inf, 0.49].
     */
    public static List<EntityMinecart> getMinecartsAt(World world, BlockPos pos, float sensitivity) {
        return getMinecartsAt(world, pos, sensitivity, EntityMinecart.class);
    }

    public static <T extends EntityMinecart> List<T> getMinecartsAt(World world, BlockPos pos, float sensitivity, Class<T> type) {
        sensitivity = Math.min(sensitivity, 0.49f);
        return world.getEntitiesWithinAABB(type, new AxisAlignedBB(pos.getX() + sensitivity, pos.getY(), pos.getZ() + sensitivity,
                pos.getX() + 1 - sensitivity, pos.getY() + 1 - sensitivity, pos.getZ() + 1 - sensitivity), EntitySelectors.IS_ALIVE);
    }

    public static List<EntityMinecart> getMinecartsIn(World world, BlockPos p1, BlockPos p2) {
        return getMinecartsIn(world, p1, p2, EntityMinecart.class);
    }

    public static <T extends EntityMinecart> List<T> getMinecartsIn(World world, BlockPos p1, BlockPos p2, Class<T> type) {
        return world.getEntitiesWithinAABB(type, new AxisAlignedBB(p1.getX(), p1.getY(), p1.getZ(), p2.getX(), p2.getY(), p2.getZ())).stream()
                .filter(cart -> !cart.isDead).collect(Collectors.toList());
    }

    /**
     * Returns the cart's "speed". It is not capped by the carts max speed, it
     * instead returns the cart's "potential" speed. Used by collision and
     * linkage logic. Do not use this to determine how fast a cart is currently
     * moving.
     *
     * @return speed
     */
    public static double getCartSpeedUncapped(EntityMinecart cart) {
        return Math.sqrt(cart.motionX * cart.motionX + cart.motionZ * cart.motionZ);
    }

    public static double getCartSpeedUncappedSquared(EntityMinecart cart) {
        return cart.motionX * cart.motionX + cart.motionZ * cart.motionZ;
    }

    public static boolean cartVelocityIsLessThan(EntityMinecart cart, float vel) {
        return Math.abs(cart.motionX) < vel && Math.abs(cart.motionZ) < vel;
    }

    private CartToolsAPI() {
    }
}
