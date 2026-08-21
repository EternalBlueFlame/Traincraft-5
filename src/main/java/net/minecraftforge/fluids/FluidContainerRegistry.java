package net.minecraftforge.fluids;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import java.util.ArrayList;
import java.util.List;

public class FluidContainerRegistry {
	public static final int BUCKET_VOLUME = 1000;

	private static final List<FluidContainerData> dataList = new ArrayList<FluidContainerData>();

	public static class FluidContainerData {
		public final FluidStack fluid;
		public final ItemStack filledContainer;
		public final ItemStack emptyContainer;

		public FluidContainerData(FluidStack stack, ItemStack filledContainer, ItemStack emptyContainer) {
			this.fluid = stack;
			this.filledContainer = filledContainer;
			this.emptyContainer = emptyContainer;
		}
	}

	public static boolean registerFluidContainer(Fluid fluid, ItemStack filledContainer, ItemStack emptyContainer) {
		if (fluid == null || filledContainer == null) {
			return false;
		}
		return registerFluidContainer(new FluidStack(fluid, BUCKET_VOLUME), filledContainer, emptyContainer);
	}

	public static boolean registerFluidContainer(FluidStack stack, ItemStack filledContainer, ItemStack emptyContainer) {
		if (stack == null || filledContainer == null) {
			return false;
		}
		return registerFluidContainer(new FluidContainerData(stack, filledContainer, emptyContainer));
	}

	public static boolean registerFluidContainer(FluidContainerData data) {
		if (data == null || data.fluid == null || data.filledContainer == null) {
			return false;
		}
		dataList.add(data);
		return true;
	}

	public static boolean isContainer(ItemStack stack) {
		return isFilledContainer(stack) || isEmptyContainer(stack);
	}

	public static boolean isFilledContainer(ItemStack stack) {
		return getFluidForFilledItem(stack) != null;
	}

	public static boolean isEmptyContainer(ItemStack stack) {
		if (stack == null || stack.isEmpty()) {
			return false;
		}
		for (FluidContainerData data : dataList) {
			if (data.emptyContainer != null && data.emptyContainer.isItemEqual(stack)) {
				return true;
			}
		}
		if (stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
			return FluidUtil.getFluidContained(stack) == null;
		}
		return false;
	}

	public static ItemStack fillFluidContainer(FluidStack liquid, ItemStack empty) {
		if (liquid == null || empty == null || empty.isEmpty()) {
			return null;
		}
		for (FluidContainerData data : dataList) {
			if (data.emptyContainer != null && data.emptyContainer.isItemEqual(empty)
					&& data.fluid != null && data.fluid.isFluidEqual(liquid)
					&& liquid.amount >= data.fluid.amount) {
				return data.filledContainer.copy();
			}
		}
		ItemStack copy = empty.copy();
		copy.setCount(1;
		IFluidHandlerItem handler = FluidUtil.getFluidHandler(copy);
		if (handler != null) {
			int filled = handler.fill(liquid, true);
			if (filled > 0) {
				return handler.getContainer();
			}
		}
		return null;
	}

	public static FluidStack getFluidForFilledItem(ItemStack stack) {
		if (stack == null || stack.isEmpty()) {
			return null;
		}
		for (FluidContainerData data : dataList) {
			if (data.filledContainer != null && data.filledContainer.isItemEqual(stack) && data.fluid != null) {
				return data.fluid.copy();
			}
		}
		return FluidUtil.getFluidContained(stack);
	}

	public static boolean containsFluid(ItemStack stack, FluidStack liquid) {
		FluidStack contained = getFluidForFilledItem(stack);
		return contained != null && liquid != null && contained.isFluidEqual(liquid) && contained.amount >= liquid.amount;
	}

	public static FluidContainerData[] getRegisteredFluidContainerData() {
		return dataList.toArray(new FluidContainerData[0]);
	}
}
