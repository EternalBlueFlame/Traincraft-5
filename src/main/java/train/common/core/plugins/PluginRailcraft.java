package train.common.core.plugins;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Railcraft items Traincraft refers to.
 * <p>
 * Track kits, track types and the Railcraft recipes moved to
 * {@link train.common.blocks.tracks.kits.ModuleTraincraftTracks}, which Railcraft loads itself.
 */
public class PluginRailcraft {

    public enum RailcraftParts {
        COKE("railcraft:fuel_coke", 0),
        INGOT_STEEL("railcraft:ingot", 0),
        RAIL_STANDARD("railcraft:rail", 0),
        RAIL_ADVANCED("railcraft:rail", 1),
        RAIL_WOOD("railcraft:rail", 2),
        RAIL_SPEED("railcraft:rail", 3),
        RAIL_REINFORCED("railcraft:rail", 4),
        RAILBED_WOOD("railcraft:railbed", 0),
        RAILBED_STONE("railcraft:railbed", 1);

        private final String itemName;
        private final int meta;

        RailcraftParts(String itemName, int meta) {
            this.itemName = itemName;
            this.meta = meta;
        }

        /** @return the Railcraft item, or {@link ItemStack#EMPTY} when Railcraft is not installed */
        public ItemStack getStack() {
            Item item = Item.getByNameOrId(this.itemName);
            return item == null ? ItemStack.EMPTY : new ItemStack(item, 1, this.meta);
        }
    }
}
