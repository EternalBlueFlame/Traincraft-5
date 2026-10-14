package train.common.core;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class TrainModBlockUtil {
    public static ArrayList<ItemStack> getItemStackFromBlock(World world, int i, int j, int k) {
        Block block = CommonUtil.getBlockAt(world, i, j, k);

        if (block == null) {
            return null;
        }

        int meta = CommonUtil.getBlockFacing(world, i, j, k);
        return block.getDrops(world, i, j, k, meta, 0);
    }
}