package train.common.core.util;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Replacement for Forge 1.7.10's {@code FluidContainerRegistry} (removed in 1.12).
 * <p>
 * Traincraft's own "filled item / empty item" pairs (e.g. diesel canisters) are registered here
 * and checked first; anything else falls back to the 1.12 fluid capability via {@link FluidUtil}
 * (buckets, tanks and fluid containers of other mods).
 */
public final class FluidContainers {
    public static final int BUCKET_VOLUME = 1000;

    public static final class Data {
        public final FluidStack fluid;
        public final ItemStack filledContainer;
        public final ItemStack emptyContainer;

        Data(FluidStack fluid, ItemStack filledContainer, ItemStack emptyContainer) {
            this.fluid = fluid;
            this.filledContainer = filledContainer;
            this.emptyContainer = emptyContainer;
        }
    }

    private static final List<Data> CONTAINERS = new ArrayList<>();

    private FluidContainers() {
    }

    public static void register(Fluid fluid, ItemStack filled, ItemStack empty) {
        register(new FluidStack(fluid, BUCKET_VOLUME), filled, empty);
    }

    public static void register(FluidStack fluid, ItemStack filled, ItemStack empty) {
        CONTAINERS.add(new Data(fluid.copy(), filled.copy(), empty.copy()));
    }

    /** Traincraft-registered containers plus the vanilla water/lava buckets. */
    public static List<Data> getRegisteredData() {
        List<Data> all = new ArrayList<>(CONTAINERS);
        all.add(new Data(new FluidStack(FluidRegistry.WATER, BUCKET_VOLUME), new ItemStack(Items.WATER_BUCKET), new ItemStack(Items.BUCKET)));
        all.add(new Data(new FluidStack(FluidRegistry.LAVA, BUCKET_VOLUME), new ItemStack(Items.LAVA_BUCKET), new ItemStack(Items.BUCKET)));
        return Collections.unmodifiableList(all);
    }

    private static boolean same(ItemStack a, ItemStack b) {
        return !a.isEmpty() && !b.isEmpty() && a.getItem() == b.getItem() && a.getMetadata() == b.getMetadata();
    }

    @Nullable
    public static FluidStack getFluidForFilledItem(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        for (Data d : CONTAINERS) {
            if (same(d.filledContainer, stack)) {
                return d.fluid.copy();
            }
        }
        return FluidUtil.getFluidContained(stack);
    }

    public static boolean isFilledContainer(@Nullable ItemStack stack) {
        return getFluidForFilledItem(stack) != null;
    }

    public static boolean isEmptyContainer(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        for (Data d : CONTAINERS) {
            if (same(d.emptyContainer, stack)) {
                return true;
            }
        }
        return FluidUtil.getFluidHandler(stack) != null && FluidUtil.getFluidContained(stack) == null;
    }

    public static boolean isContainer(@Nullable ItemStack stack) {
        return isFilledContainer(stack) || isEmptyContainer(stack);
    }

    public static boolean containsFluid(@Nullable ItemStack stack, @Nullable FluidStack fluid) {
        FluidStack contained = getFluidForFilledItem(stack);
        return contained != null && fluid != null && contained.isFluidEqual(fluid);
    }

    /** Fills one empty container with {@code fluid}; returns the filled item or {@code null} if impossible. */
    @Nullable
    public static ItemStack fillFluidContainer(@Nullable FluidStack fluid, @Nullable ItemStack empty) {
        if (fluid == null || empty == null || empty.isEmpty()) {
            return null;
        }
        for (Data d : CONTAINERS) {
            if (same(d.emptyContainer, empty) && d.fluid.isFluidEqual(fluid) && fluid.amount >= d.fluid.amount) {
                return d.filledContainer.copy();
            }
        }
        ItemStack copy = empty.copy();
        copy.setCount(1);
        IFluidHandlerItem handler = FluidUtil.getFluidHandler(copy);
        if (handler == null) {
            return null;
        }
        int filled = handler.fill(fluid.copy(), true);
        return filled > 0 ? handler.getContainer() : null;
    }

    /** The fluid handler of a tile entity / object on the given side (1.12 capability, or a direct implementation). */
    @Nullable
    public static IFluidHandler getHandler(@Nullable Object target, @Nullable EnumFacing side) {
        if (target instanceof TileEntity) {
            TileEntity te = (TileEntity) target;
            if (te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side)) {
                return te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
            }
        }
        return target instanceof IFluidHandler ? (IFluidHandler) target : null;
    }

    /** True if the object exposes a fluid handler on any side. */
    public static boolean hasHandler(@Nullable Object target) {
        if (getHandler(target, null) != null) {
            return true;
        }
        for (EnumFacing side : EnumFacing.VALUES) {
            if (getHandler(target, side) != null) {
                return true;
            }
        }
        return false;
    }

    /** 1.7.10 {@code ((IFluidHandler) tile).drain(side, amount, doDrain)}; null if there is no handler on that side. */
    @Nullable
    public static FluidStack drain(@Nullable Object target, @Nullable EnumFacing side, int amount, boolean doDrain) {
        IFluidHandler handler = getHandler(target, side);
        return handler == null ? null : handler.drain(amount, doDrain);
    }
}
