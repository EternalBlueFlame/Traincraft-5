package train.common.blocks.tracks.kits;

import mods.railcraft.api.tracks.ITrackKitInstance;
import mods.railcraft.api.tracks.TrackKit;
import mods.railcraft.api.tracks.TrackRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreIngredient;
import train.common.Traincraft;
import train.common.library.Info;
import train.common.library.ItemIDs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * All Traincraft track kits. Replaces the 1.7.10 {@code train.common.library.Tracks} enum.
 * <p>
 * Assets (namespace {@code tc}), following Railcraft's naming:
 * <ul>
 * <li>blockstate: {@code blockstates/tracks/outfitted/kit/<name>.json}</li>
 * <li>textures: {@code textures/blocks/tracks/outfitted/kit/<name>_<state>.png}</li>
 * <li>kit item texture: {@code textures/items/track_kits/<name>.png}</li>
 * <li>lang: {@code track_kit.railcraft.tc_<name>.name} / {@code .tips}</li>
 * </ul>
 */
public enum TCTrackKits {
    DETECTOR_FREIGHT("detector_freight", TrackKitDetectorFreight.class, 2, 8, () -> new Object[]{Blocks.CHEST, ItemIDs.electronicCircuit.item}),
    DETECTOR_PASSENGER("detector_passenger", TrackKitDetectorPassenger.class, 2, 8, () -> new Object[]{ItemIDs.seats.item, ItemIDs.electronicCircuit.item}),
    DETECTOR_LOCOMOTIVE("detector_locomotive", TrackKitDetectorLocomotive.class, 2, 8, () -> new Object[]{ItemIDs.controls.item, ItemIDs.electronicCircuit.item}),
    DETECTOR_STEAM("detector_steam", TrackKitDetectorSteam.class, 2, 8, () -> new Object[]{ItemIDs.firebox.item, ItemIDs.electronicCircuit.item}),
    DETECTOR_DIESEL("detector_diesel", TrackKitDetectorDiesel.class, 2, 8, () -> new Object[]{ItemIDs.diesel.item, ItemIDs.electronicCircuit.item}),
    DETECTOR_TANK("detector_tank", TrackKitDetectorTank.class, 2, 8, () -> new Object[]{Items.WATER_BUCKET, ItemIDs.electronicCircuit.item}),
    HORN("horn", TrackKitHorn.class, 2, 8, () -> new Object[]{Items.SIGN, ItemIDs.electronicCircuit.item}),
    COUPLER("coupler", TrackKitCoupler.class, 2, 8, () -> new Object[]{ItemIDs.stake.item}),
    BOARDING_ANIMAL("boarding_animal", TrackKitBoarding.Animal.class, 2, 8, () -> new Object[]{Items.GOLDEN_CARROT, Items.ENDER_PEARL}),
    BOARDING_MOB("boarding_mob", TrackKitBoarding.Mob.class, 2, 8, () -> new Object[]{Items.BONE, Items.ENDER_PEARL}),
    DISEMBARK("disembark", TrackKitBoarding.Disembark.class, 2, 8, () -> new Object[]{Items.SHEARS}),
    ENERGY("energy", TrackKitEnergy.class, 2, 16, () -> new Object[]{ItemIDs.copperWireFine.item, Items.ENDER_PEARL}),
    SPEED_CONTROLLER("speed_controller", TrackKitSpeedController.class, 5, 8, () -> new Object[]{ItemIDs.controls.item, ItemIDs.controls.item}),
    HOLDING("holding", TrackKitHolding.class, 2, 8, () -> new Object[]{ItemIDs.controls.item, Blocks.STONE_PRESSURE_PLATE}),
    STATION("station", TrackKitStation.class, 8, 8, () -> new Object[]{Items.CLOCK});

    private final String name;
    private final Class<? extends ITrackKitInstance> instanceClass;
    private final int renderStates;
    private final int recipeOutput;
    private final Supplier<Object[]> ingredients;
    private TrackKit trackKit;

    TCTrackKits(String name, Class<? extends ITrackKitInstance> instanceClass, int renderStates, int recipeOutput, Supplier<Object[]> ingredients) {
        this.name = name;
        this.instanceClass = instanceClass;
        this.renderStates = renderStates;
        this.recipeOutput = recipeOutput;
        this.ingredients = ingredients;
    }

    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Info.modID, this.name);
    }

    public TrackKit getTrackKit() {
        if (this.trackKit == null) {
            throw new IllegalStateException("Track kit " + this.name + " used before Railcraft registered it");
        }
        return this.trackKit;
    }

    private boolean isLockdown() {
        return this == HOLDING || this == STATION;
    }

    private boolean needsTicks() {
        return this != COUPLER && this != SPEED_CONTROLLER;
    }

    /** Called from {@link ModuleTraincraftTracks} during Railcraft's PRE_INIT. */
    static void registerAll() {
        for (TCTrackKits kit : values()) {
            kit.trackKit = new TrackKit.Builder(kit.getRegistryName(), kit.instanceClass)
                    .setRenderStates(kit.renderStates)
                    .setRequiresTicks(kit.needsTicks())
                    .setAllowedOnSlopes(!kit.isLockdown())
                    .build();
            TrackRegistry.TRACK_KIT.register(kit.trackKit);
        }
    }

    /** Called from {@link ModuleTraincraftTracks} during Railcraft's INIT, once all items exist. */
    static void defineRecipes() {
        Item trackParts = Item.getByNameOrId("railcraft:track_parts");
        if (trackParts == null) {
            Traincraft.tcLog.warn("railcraft:track_parts not found, Traincraft track kits will have no recipes");
            return;
        }
        for (TCTrackKits kit : values()) {
            ItemStack output = kit.getTrackKit().getTrackKitItem(kit.recipeOutput);
            if (output.isEmpty()) {
                continue;
            }
            List<Ingredient> parts = new ArrayList<>();
            parts.add(new OreIngredient("plankWood"));
            parts.add(Ingredient.fromItem(trackParts));
            for (Object o : kit.ingredients.get()) {
                parts.add(toIngredient(o));
            }
            GameRegistry.addShapelessRecipe(new ResourceLocation(Info.modID, "track_kit_" + kit.name),
                    new ResourceLocation(Info.modID, "track_kits"), output, parts.toArray(new Ingredient[0]));
        }
    }

    static Ingredient toIngredient(Object o) {
        if (o instanceof Ingredient) return (Ingredient) o;
        if (o instanceof String) return new OreIngredient((String) o);
        if (o instanceof ItemStack) return Ingredient.fromStacks((ItemStack) o);
        if (o instanceof Item) return Ingredient.fromItem((Item) o);
        if (o instanceof Block) return Ingredient.fromItem(Item.getItemFromBlock((Block) o));
        throw new IllegalArgumentException("Unknown ingredient " + o);
    }
}
