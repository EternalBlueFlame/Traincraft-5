/**
 * Enum containing all tracks
 * 
 * @author Spitfire4466
 * @see PluginRailcraft to register the track and name it
 */
package train.library;

import ebf.tim.registry.TiMItems;
import mods.railcraft.api.tracks.TrackRegistry;
import mods.railcraft.api.tracks.TrackSpec;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import train.blocks.tracks.*;

import java.util.Arrays;
import java.util.List;

import static train.core.plugins.PluginRailcraft.RailcraftParts.*;

public enum RCTracks {

	DETECTOR_FREIGHT(2, "detector.freight", 8, BlockDetectorFreightTrack.class, Arrays.asList("TC5-injected, 1.7 Only.","Emits a redstone signal on all","passing Traincraft freight carts"), "#A#", "#B#", "#C#", '#', RAIL_ADVANCED.stack, 'A', RAILBED_WOOD.stack, 'B', Blocks.chest, 'C', TiMItems.steelPinCircuit),
	DETECTOR_PASSENGER(2, "detector.passenger", 8, BlockDetectorPassengerTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Emits a redstone signal on all","passing Traincraft passengers carts"), "#A#", "#B#", "#C#", '#', RAIL_ADVANCED.stack, 'A', RAILBED_WOOD.stack, 'B', TiMItems.seatsWooden, 'C', TiMItems.steelPinCircuit),
	DETECTOR_ALL_LOCOMOTIVES(2, "detector.loco", 8, BlockDetectorAllLocomotiveTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Emits a redstone signal on all","passing Traincraft locomotives"), "#A#", "#B#", "#C#", '#', RAIL_ADVANCED.stack, 'A', RAILBED_WOOD.stack, 'B', TiMItems.controlPanel, 'C', TiMItems.steelPinCircuit),
	DETECTOR_STEAM_LOCOMOTIVES(2, "detector.steam", 8, BlockDetectorSteamLocomotiveTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Emits a redstone signal on all","passing Traincraft steam locomotives"), "#A#", "#B#", "#C#", '#', RAIL_ADVANCED.stack, 'A', RAILBED_WOOD.stack, 'B', TiMItems.fireboxIron, 'C', TiMItems.steelPinCircuit),
	DETECTOR_DIESEL_LOCOMOTIVES(2, "detector.diesel", 8, BlockDetectorDieselLocomotiveTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Emits a redstone signal on all","passing Traincraft diesel locomotives"), "#A#", "#B#", "#C#", '#', RAIL_ADVANCED.stack, 'A', RAILBED_WOOD.stack, 'B', TiMItems.mediumDieselEngine, 'C', TiMItems.steelPinCircuit),
	DETECTOR_TANK_CARTS(2, "detector.tank", 8, BlockDetectorTankCartsTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Emits a redstone signal on all","passing Traincraft tank carts"), "#A#", "#B#", "#C#", '#', RAIL_ADVANCED.stack, 'A', RAILBED_WOOD.stack, 'B', Items.water_bucket, 'C', TiMItems.steelPinCircuit),
	COUPLER_TRACK(2, "coupler", 8, BlockCouplerTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Will attach any passing rolling stock","(Railcraft carts included) when powered","An unpowered track will detach carts"), "#A#", "#B#", "# #", '#', RAIL_ADVANCED.stack, 'A', RAILBED_WOOD.stack, 'B', TiMItems.itemStake),
	COPPER_TRACK(2, "copper", 32, BlockCopperTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","A cheaper but slower track"), "# #", "#B#", "# #", '#', ItemIDs.copperRail.item, 'B', RAILBED_STONE.stack),
	STEEL_TRACK(2, "steel", 32, BlockSteelTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","A faster track"), "# #", "#B#", "# #", '#', ItemIDs.steelRail.item, 'B', RAILBED_STONE.stack),
	ANIMAL_BOARDING_TRACK(2, "boarding.animal", 8, BlockAnimalBoardingTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Will put any close animal inside the Stock car"), "#A#", "#B#", "#C#", '#', RAIL_ADVANCED.stack, 'A', RAILBED_WOOD.stack, 'B', Items.golden_carrot, 'C', TiMItems.seatsWooden),
	MOB_BOARDING_TRACK(2, "boarding.mob", 8, BlockMobBoardingTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Will put any close mob inside the Stock car"), "#A#", "#B#", "#C#", '#', RAIL_ADVANCED.stack, 'A', RAILBED_WOOD.stack, 'B', Items.bone, 'C', TiMItems.seatsWooden),
	DISEMBARK_TRACK(2, "disembark", 8, BlockDisembarkTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Will disembark anything mounting the Stock cart"), "#A#", "#B#", "# #", '#', RAIL_ADVANCED.stack, 'A', RAILBED_WOOD.stack, 'B', Items.shears),
	ENERGY_TRACK(4, "energy", 16, BlockEnergyTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","A track that can transfer power to", "Traincraft electric locomotives","Power it with a redstone power source","See the Traincraft guide for more info"), "#A#", "#B#", "#C#", '#', RAIL_ADVANCED.stack, 'A', RAILBED_STONE.stack, 'B', TiMItems.copperWire, 'C', TiMItems.seatsWooden),
	LOCO_SPEED_CONTROLLER(5, "speed.loco", 8, BlockLocomotiveSpeedControllerTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Controls Traincraft locomotive's max speed","Use a crowbar to change the mode"), "#A#", "#B#", "#B#", '#', RAIL_ADVANCED.stack, 'A', RAILBED_WOOD.stack, 'B', TiMItems.controlPanel),
	LOCO_HOLDING_TRACK(2, "holding.loco", 8, BlockLocomotiveHoldingTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Unpowered: hold any Traincraft locomotive","Powered: Release the locomotive and launch to its previous speed","Won't boost passing locomotives"), "#A#", "#B#", "#C#", '#', ItemIDs.steelRail.item, 'A', RAILBED_WOOD.stack, 'B', TiMItems.controlPanel, 'C', Blocks.stone_pressure_plate),
	STATION_TRACK(8, "station", 8, BlockStationTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Requires redstone power","A track which holds any Traincraft locomotive","as well as any minecart","Track has an internal clock and will activate"," by default every 15s for 5s","Use a crowbar to change the mode"), "#A#", "#B#", "#A#", '#', RAIL_ADVANCED.stack, 'A', RAILBED_WOOD.stack, 'B', TiMItems.frameIron),
	HORN_ALL_LOCOMOTIVES(2, "horn.loco", 8, BlockDetectorHornAllLocomotiveTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Sounds the horn on all","passing Traincraft locomotives"), "#A#", "#B#", "#C#", '#', RAIL_ADVANCED.stack, 'A', RAILBED_WOOD.stack, 'B', Items.sign, 'C', TiMItems.steelPinCircuit),
	VANILLA_SNOWY_TRACK(2, "snow", 1, BlockVanillaSnowyTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","Vanilla track with snow")),
	SNOWY_STEEL_TRACK(2, "snow.steel", 1, BlockSnowySteelTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","A faster track with snow")),
	SNOWY_COPPER_TRACK(2, "snow.copper", 1, BlockSnowyCopperTrack.class,Arrays.asList("TC5-injected, 1.7 Only.","A cheaper but slower track with snow"));
	

	private final String tag;
	private final int recipeOutput;
	private final int numIcons;
	private final TrackSpec trackSpec;
	public Object[] crafting = null;

	RCTracks(int numIcons, String tag, int recipeOutput, Class track, List<String> tooltip) {
		this.numIcons = numIcons;
		this.tag = tag;
		this.recipeOutput = recipeOutput;

		this.trackSpec = new TrackSpec((short) (ordinal() + 613), getTag(), RailTextureLoader.INSTANCE, track, tooltip);
		TrackRegistry.registerTrackSpec(this.trackSpec);
	}

	RCTracks(int numIcons, String tag, int recipeOutput, Class track, List<String> tooltip, Object... crafting) {
		this(numIcons, tag, recipeOutput, track, tooltip);
		this.crafting = crafting;
	}

	public String getTextureTag() {
		return Info.modID.toLowerCase() + ":rails/track." + this.tag;
	}

	public int getNumIcons() {
		return this.numIcons;
	}

	public TrackSpec getTrackSpec() {
		return this.trackSpec;
	}

	public String getTag() {
		return "traincraft.track." + this.tag;
	}

	public int getRecipeOutput() {
		return this.recipeOutput;
	}

	public ItemStack getOutput(){
		return getTrackSpec().getItem(getRecipeOutput());
	}


	static {
		TrackRegistry.registerIconLoader(RailTextureLoader.INSTANCE);
	}
}