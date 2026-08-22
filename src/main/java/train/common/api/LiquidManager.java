package train.common.api;

import buildcraft.api.fuels.BuildcraftFuelRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import mods.railcraft.api.fuel.FuelManager;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.*;
import train.common.blocks.BlockTraincraftFluid;
import train.common.items.ItemBlockFluid;
import train.common.library.BlockIDs;
import train.common.library.ItemIDs;

public class LiquidManager {

	private static LiquidManager instance;
	public static FluidStack WATER_FILTER = new FluidStack(FluidRegistry.WATER, 1);
	public static FluidStack LAVA_FILTER = new FluidStack(FluidRegistry.LAVA, 1);

	public static final Fluid DIESEL = new Fluid("Diesel", new ResourceLocation("tc", "textures/blocks/liquid_diesel.png"), new ResourceLocation("tc", "textures/blocks/liquid_diesel_flow.png")).setUnlocalizedName("diesel.name").setDensity(860);
	public static final Fluid REFINED_FUEL = new Fluid("RefinedFuel", new ResourceLocation("tc", "textures/blocks/liquid_refinedfuel.png"), new ResourceLocation("tc", "textures/blocks/liquid_refinedfuel_flow.png")).setDensity(820).setUnlocalizedName("refinedfuel.name");

	public static LiquidManager getInstance() {
		if (instance == null) {
			instance = new LiquidManager();
		}
		return instance;
	}

	public static void registerFluidBlock(BlockTraincraftFluid block){
		// GameRegistry.registerBlock(block, ItemBlockFluid.class, "fluid." + block.getFluid().getName());
	}

	public void registerLiquids() {
		FluidRegistry.registerFluid(DIESEL);
		FluidRegistry.registerFluid(REFINED_FUEL);
		BlockIDs.diesel.block = new BlockTraincraftFluid(DIESEL, Material.WATER).setFlammable(true).setFlammability(5);
		DIESEL.setBlock(BlockIDs.diesel.block);
		BlockIDs.refinedFuel.block = new BlockTraincraftFluid(REFINED_FUEL, Material.WATER).setFlammable(true).setFlammability(4);
		REFINED_FUEL.setBlock(BlockIDs.refinedFuel.block);
		FluidContainerRegistry.registerFluidContainer(DIESEL, new ItemStack(ItemIDs.diesel.item), new ItemStack(ItemIDs.emptyCanister.item));
		FluidContainerRegistry.registerFluidContainer(REFINED_FUEL, new ItemStack(ItemIDs.refinedFuel.item), new ItemStack(ItemIDs.emptyCanister.item));
		if (Loader.isModLoaded("Railcraft")) {
			addRCFluids();
		}
		if (Loader.isModLoaded("BuildCraft|Energy")) {
			addBCFluids();
		}
		MinecraftForge.EVENT_BUS.register(this);

		registerFluidBlock((BlockTraincraftFluid) BlockIDs.diesel.block);
		registerFluidBlock((BlockTraincraftFluid) BlockIDs.refinedFuel.block);
	}

	@Optional.Method(modid = "BuildCraft|Energy")
	private void addBCFluids(){
		BuildcraftFuelRegistry.fuel.addFuel(DIESEL, 30, 200000);
		BuildcraftFuelRegistry.fuel.addFuel(REFINED_FUEL, 60, 100000);
	}

	@Optional.Method(modid = "Railcraft")
	private void addRCFluids() {
		FuelManager.addBoilerFuel(DIESEL, 60000);
		FuelManager.addBoilerFuel(REFINED_FUEL, 96000);
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void textureHook(TextureStitchEvent.Post event) {
		// TODO 1.12: fluid still/flow icon assignment needs the 1.12 texture pipeline
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
		return FluidContainerRegistry.isContainer(stack);
	}

	public boolean isFilledContainer(ItemStack stack) {
		return FluidContainerRegistry.isFilledContainer(stack);
	}

	public boolean isEmptyContainer(ItemStack stack) {
		return FluidContainerRegistry.isEmptyContainer(stack);
	}

	public ItemStack fillFluidContainer(FluidStack liquid, ItemStack empty) {
		if ((liquid == null) || (empty == null))
			return null;
		return FluidContainerRegistry.fillFluidContainer(liquid, empty);
	}

	public FluidStack getFluidInContainer(ItemStack stack) {
		return FluidContainerRegistry.getFluidForFilledItem(stack);
	}

	public boolean containsFluid(ItemStack stack, FluidStack liquid) {
		return FluidContainerRegistry.containsFluid(stack, liquid);
	}


	public ItemStack processContainer(IInventory inventory, int inventoryIndex, AbstractTrains tank, ItemStack itemstack) {
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
			ItemStack filled = getInstance().fillFluidContainer(tank.drain(1000, false), itemstack);
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
