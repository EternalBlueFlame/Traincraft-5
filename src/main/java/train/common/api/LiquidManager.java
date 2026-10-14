package train.common.api;

import train.common.core.util.FluidContainers;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import mods.railcraft.api.fuel.FluidFuelManager;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.IFluidHandler;
import train.common.blocks.BlockTraincraftFluid;
import train.common.items.ItemBlockFluid;
import train.common.library.BlockIDs;
import train.common.library.ItemIDs;

public class LiquidManager {

	private static LiquidManager instance;
	public static FluidStack WATER_FILTER = new FluidStack(FluidRegistry.WATER, 1);
	public static FluidStack LAVA_FILTER = new FluidStack(FluidRegistry.LAVA, 1);

	public static final Fluid DIESEL = new Fluid("diesel", new net.minecraft.util.ResourceLocation("tc:blocks/liquid_diesel"), new net.minecraft.util.ResourceLocation("tc:blocks/liquid_diesel_flow")).setUnlocalizedName("diesel.name").setDensity(860);
	public static final Fluid REFINED_FUEL = new Fluid("refinedfuel", new net.minecraft.util.ResourceLocation("tc:blocks/liquid_refinedfuel"), new net.minecraft.util.ResourceLocation("tc:blocks/liquid_refinedfuel_flow")).setDensity(820).setUnlocalizedName("refinedfuel.name");

	/** Stable int id for a fluid (1.12 has no public numeric fluid ids): its name's hash, 0 for none. */
	public static int fluidId(Fluid fluid) {
		return fluid == null ? 0 : fluid.getName().hashCode();
	}

	/** Reverse of {@link #fluidId(Fluid)}; null when no fluid matches. */
	public static Fluid fluidById(int id) {
		if (id == 0) {
			return null;
		}
		for (Fluid f : FluidRegistry.getRegisteredFluids().values()) {
			if (f.getName().hashCode() == id) {
				return f;
			}
		}
		return null;
	}

	public static LiquidManager getInstance() {
		if (instance == null) {
			instance = new LiquidManager();
		}
		return instance;
	}

	public static void registerFluidBlock(BlockTraincraftFluid block){
		if (block.getRegistryName() != null) {
			return;
		}
		block.setRegistryName("fluid." + block.getFluid().getName());
		net.minecraftforge.fml.common.registry.ForgeRegistries.BLOCKS.register(block);
		net.minecraftforge.fml.common.registry.ForgeRegistries.ITEMS.register(new ItemBlockFluid(block).setRegistryName(block.getRegistryName()));
	}

	public void registerLiquids() {
		FluidRegistry.registerFluid(DIESEL);
		FluidRegistry.registerFluid(REFINED_FUEL);
		BlockIDs.diesel.block = new BlockTraincraftFluid(DIESEL, Material.WATER).setFlammable(true).setFlammability(5);
		DIESEL.setBlock(BlockIDs.diesel.block);
		BlockIDs.refinedFuel.block = new BlockTraincraftFluid(REFINED_FUEL, Material.WATER).setFlammable(true).setFlammability(4);
		REFINED_FUEL.setBlock(BlockIDs.refinedFuel.block);
		FluidContainers.register(DIESEL, new ItemStack(ItemIDs.diesel.item), new ItemStack(ItemIDs.emptyCanister.item));
		FluidContainers.register(REFINED_FUEL, new ItemStack(ItemIDs.refinedFuel.item), new ItemStack(ItemIDs.emptyCanister.item));
		if (Loader.isModLoaded("railcraft")) {
			addRCFluids();
		}
		if (Loader.isModLoaded("buildcraftenergy")) {
			addBCFluid(DIESEL, 30, 200000);
			addBCFluid(REFINED_FUEL, 60, 100000);
		}
		MinecraftForge.EVENT_BUS.register(this);

		registerFluidBlock((BlockTraincraftFluid) BlockIDs.diesel.block);
		registerFluidBlock((BlockTraincraftFluid) BlockIDs.refinedFuel.block);
	}

	/**
	 * Registers a BuildCraft combustion engine fuel. Done by reflection so Traincraft doesn't need the BuildCraft API
	 * to compile ({@code BuildcraftFuelRegistry.fuel.addFuel(fluid, powerPerCycle, totalBurningTime)}).
	 */
	public static void addBCFluid(Fluid fluid, long powerPerCycle, int totalBurningTime) {
		try {
			Object manager = Class.forName("buildcraft.api.fuels.BuildcraftFuelRegistry").getField("fuel").get(null);
			if (manager == null) {
				return;
			}
			for (java.lang.reflect.Method m : manager.getClass().getMethods()) {
				if (m.getName().equals("addFuel") && m.getParameterCount() == 3 && m.getParameterTypes()[0] == Fluid.class) {
					Class<?> power = m.getParameterTypes()[1];
					m.invoke(manager, fluid, power == long.class ? (Object) powerPerCycle : (Object) (int) powerPerCycle, totalBurningTime);
					return;
				}
			}
		} catch (ReflectiveOperationException | RuntimeException e) {
			train.common.Traincraft.tcLog.warn("Couldn't register " + fluid.getName() + " as a BuildCraft fuel", e);
		}
	}

	@Optional.Method(modid = "railcraft")
	private void addRCFluids() {
		FluidFuelManager.addFuel(DIESEL, 60000);
		FluidFuelManager.addFuel(REFINED_FUEL, 96000);
	}
	
	public boolean isDieselLocoFuel(ItemStack stack) {
		String[] multiFilter;
		FluidStack bucketLiquid = getFluidInContainer(stack);
		LiquidManager.getInstance();
		multiFilter = LiquidManager.dieselFilter();
		if (multiFilter != null) {
			for(String aMultiFilter : multiFilter){
				if(aMultiFilter != null && bucketLiquid != null && bucketLiquid.getUnlocalizedName().toLowerCase().contains(aMultiFilter))
					return true;
				if(isEmptyContainer(stack))
					return true;
			}
		}
		return false;
	}

	public static String[] dieselFilter() {
		return new String[]{"diesel","fuel"};
	}

	public boolean isContainer(ItemStack stack) {
		return FluidContainers.isContainer(stack);
	}

	public boolean isFilledContainer(ItemStack stack) {
		return FluidContainers.isFilledContainer(stack);
	}

	public boolean isEmptyContainer(ItemStack stack) {
		return FluidContainers.isEmptyContainer(stack);
	}

	public ItemStack fillFluidContainer(FluidStack liquid, ItemStack empty) {
		if ((liquid == null) || (empty == null))
			return null;
		return FluidContainers.fillFluidContainer(liquid, empty);
	}

	public FluidStack getFluidInContainer(ItemStack stack) {
		return FluidContainers.getFluidForFilledItem(stack);
	}

	public boolean containsFluid(ItemStack stack, FluidStack liquid) {
		return FluidContainers.containsFluid(stack, liquid);
	}


	public ItemStack processContainer(IInventory inventory, int inventoryIndex, IFluidHandler tank, ItemStack itemstack) {
		FluidStack bucketLiquid = getFluidInContainer(itemstack);
		ItemStack emptyItem = itemstack.getItem().getContainerItem(itemstack);
		if ((bucketLiquid != null)) {
			int used = tank.fill(bucketLiquid, false);
			if (used >= bucketLiquid.amount) {
				tank.fill(bucketLiquid, true);
				if (itemstack.getItem() == Items.POTIONITEM){
					return new ItemStack(Items.GLASS_BOTTLE, 1);
				}
				inventory.decrStackSize(inventoryIndex, 1);
				return emptyItem;
			}
		}
		else if ((getInstance().isEmptyContainer(itemstack))) {
			ItemStack filled = getInstance().fillFluidContainer(tank.drain(1000,false), itemstack);
			if ((filled != null)) {
				FluidStack liquid = getFluidInContainer(filled);
				FluidStack drain = tank.drain(liquid.amount, false);
				if ((drain != null) && (drain.amount > 0)) {
					tank.drain(liquid.amount, true);
					inventory.decrStackSize(inventoryIndex, 1);
					return filled;
				}
			}
		}
		return null;
	}

	public class StandardTank extends FluidTank {
		private int tankIndex;

		public StandardTank(int capacity) {
			super(capacity);
		}

		public void setTankIndex(int index) {
			this.tankIndex = index;
		}

		public int getTankIndex() {
			return this.tankIndex;
		}

		public boolean isEmpty() {
			return (getFluid() == null) || (getFluid().amount <= 0);
		}
	}

	public class FilteredTank extends StandardTank {
		private final FluidStack filter;
		private final String[] multiFilter;

		public FilteredTank(int capacity, FluidStack filter) {
			super(capacity);
			this.filter = filter;
			this.multiFilter = null;
		}

		public FilteredTank(int capacity, FluidStack filter, int pressure) {
			this(capacity, filter);
		}

		public FilteredTank(int capacity, String[] filter) {
			super(capacity);
			this.multiFilter = filter;
			this.filter = null;
		}

		public FilteredTank(int capacity, String[] filter, int pressure) {
			this(capacity, filter);
		}

		@Override
		public int fill(FluidStack resource, boolean doFill) {
			if (multiFilter != null) {
				for (String s : multiFilter) {
					if (s != null && resource.getUnlocalizedName().toLowerCase().contains(s)) {
						return super.fill(resource, doFill);
					}
				}
			}
			else if (this.filter.isFluidEqual(resource)) {
				return super.fill(resource, doFill);
			}
			return 0;
		}

		public FluidStack getFilter() {
			return this.filter.copy();
		}

		public boolean liquidMatchesFilter(FluidStack resource) {
			return !((resource == null) || (this.filter == null)) && this.filter.isFluidEqual(resource);
		}
	}
}
