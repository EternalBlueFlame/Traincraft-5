package src.train.common.core.handlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

//TODO Delete this class because all uses of this only Method aren't used anywhere
@Deprecated
public class BuilderOreHandler {

	public static boolean isOre(int id) {
		ArrayList<ItemStack> oreList = OreDictionary.getOres(id);
		return oreList != null && oreList.size() > 0;
	}
}