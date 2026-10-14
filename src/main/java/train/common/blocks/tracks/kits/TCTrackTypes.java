package train.common.blocks.tracks.kits;

import mods.railcraft.api.core.IIngredientSource;
import mods.railcraft.api.crafting.Crafters;
import mods.railcraft.api.tracks.TrackRegistry;
import mods.railcraft.api.tracks.TrackType;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import train.common.blocks.tracks.BlockTCFlexRail;
import train.common.library.BlockIDs;
import train.common.library.Info;
import train.common.library.ItemIDs;

import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * Registers Traincraft's flex rails (copper, steel, snowy) as Railcraft track types,
 * so Railcraft and Traincraft track kits can be installed on them.
 * <p>
 * Assets (namespace {@code tc}): {@code blockstates/tracks/outfitted/type/<name>.json},
 * {@code textures/blocks/tracks/outfitted/type/<name>.png}, lang {@code track_type.railcraft.tc_<name>.name}.
 */
public enum TCTrackTypes {
    COPPER("copper", BlockIDs.track_copper, BlockTCFlexRail.SPEED_COPPER, () -> ItemIDs.copperRail.item),
    STEEL("steel", BlockIDs.track_steel, BlockTCFlexRail.SPEED_STEEL, () -> ItemIDs.steelRail.item),
    SNOWY("snowy", BlockIDs.track_snowy, BlockTCFlexRail.SPEED_DEFAULT, () -> Item.getByNameOrId("railcraft:rail")),
    SNOWY_COPPER("snowy_copper", BlockIDs.track_snowy_copper, BlockTCFlexRail.SPEED_COPPER, () -> ItemIDs.copperRail.item),
    SNOWY_STEEL("snowy_steel", BlockIDs.track_snowy_steel, BlockTCFlexRail.SPEED_STEEL, () -> ItemIDs.steelRail.item);

    private final String name;
    private final BlockIDs block;
    private final float maxSpeed;
    private final Supplier<Item> rail;

    TCTrackTypes(String name, BlockIDs block, float maxSpeed, Supplier<Item> rail) {
        this.name = name;
        this.block = block;
        this.maxSpeed = maxSpeed;
        this.rail = rail;
    }

    @Nullable
    static ItemStack railcraftStack(String name, int meta, int qty) {
        Item item = Item.getByNameOrId(name);
        return item == null ? null : new ItemStack(item, qty, meta);
    }

    private static Ingredient railbedStone() {
        ItemStack stack = railcraftStack("railcraft:railbed", 1, 1);
        return stack == null ? Ingredient.EMPTY : Ingredient.fromStacks(stack);
    }

    /** Called from {@link ModuleTraincraftTracks} during Railcraft's PRE_INIT. */
    static void registerAll() {
        for (TCTrackTypes type : values()) {
            final float speed = type.maxSpeed;
            IIngredientSource rail = () -> {
                Item item = type.rail.get();
                return item == null ? Ingredient.EMPTY : Ingredient.fromItem(item);
            };
            IIngredientSource railbed = TCTrackTypes::railbedStone;
            TrackType trackType = new TrackType(
                    new ResourceLocation(Info.modID, type.name),
                    new ResourceLocation(Info.modID, type.block.name()),
                    rail, railbed, 3.5F, false, false, 0,
                    new TrackType.EventHandler() {
                        @Override
                        public float getMaxSpeed(World world, @Nullable EntityMinecart cart, BlockPos pos) {
                            return speed;
                        }
                    });
            TrackRegistry.TRACK_TYPE.register(trackType);
        }
    }

    /**
     * Recipes for the flex rails themselves and the rolling machine recipes for copper/steel rails.
     * Railcraft-only, exactly like in 1.7.10 (they need Railcraft's railbed / rolling machine).
     */
    static void defineRecipes() {
        ItemStack railbed = railcraftStack("railcraft:railbed", 1, 1);
        if (railbed != null) {
            addTrackRecipe("track_copper", BlockIDs.track_copper.block, ItemIDs.copperRail.item, railbed);
            addTrackRecipe("track_steel", BlockIDs.track_steel.block, ItemIDs.steelRail.item, railbed);
        }
        addSnowyRecipe("track_snowy", BlockIDs.track_snowy.block, Blocks.RAIL);
        addSnowyRecipe("track_snowy_copper", BlockIDs.track_snowy_copper.block, BlockIDs.track_copper.block);
        addSnowyRecipe("track_snowy_steel", BlockIDs.track_snowy_steel.block, BlockIDs.track_steel.block);

        Crafters.rollingMachine().newRecipe(new ItemStack(ItemIDs.copperRail.item, 8))
                .name(Info.modID, "rolling_copper_rail")
                .shaped("XXX", "   ", "XXX", 'X', "ingotCopper");
        Crafters.rollingMachine().newRecipe(new ItemStack(ItemIDs.steelRail.item, 16))
                .name(Info.modID, "rolling_steel_rail")
                .shaped("XXX", "   ", "XXX", 'X', "ingotSteel");
    }

    private static void addTrackRecipe(String name, @Nullable Block track, @Nullable Item rail, ItemStack railbed) {
        if (track == null || rail == null) {
            return;
        }
        GameRegistry.addShapedRecipe(new ResourceLocation(Info.modID, name), new ResourceLocation(Info.modID, "tracks"),
                new ItemStack(track, 32), "# #", "#B#", "# #", '#', rail, 'B', railbed);
    }

    private static void addSnowyRecipe(String name, @Nullable Block snowy, @Nullable Block base) {
        if (snowy == null || base == null) {
            return;
        }
        GameRegistry.addShapelessRecipe(new ResourceLocation(Info.modID, name), new ResourceLocation(Info.modID, "tracks"),
                new ItemStack(snowy), Ingredient.fromItem(Item.getItemFromBlock(base)), Ingredient.fromItem(Items.SNOWBALL));
    }
}
