package train.common.blocks.blockSwitch;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlocksnowGravel extends Block {

    public BlocksnowGravel(Material p_i45394_1_) {
        super(p_i45394_1_);

    }

    protected void dropBlockAsItem(World p_149642_1_, BlockPos p_149642_2_, IBlockState p_149642_3_, ItemStack p_149642_4_) {
        super.dropBlockAsItemWithChance(p_149642_1_, p_149642_2_, p_149642_3_, 1.0F, 0);
    }
}
