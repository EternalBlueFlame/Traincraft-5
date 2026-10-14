package train.common.blocks.stairs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockBaseStairs extends BlockStairs {
    public BlockBaseStairs(Block block) {
        super(block.getDefaultState());
    }

    public BlockBaseStairs(net.minecraft.block.state.IBlockState modelState) {
        super(modelState);
    }
}
