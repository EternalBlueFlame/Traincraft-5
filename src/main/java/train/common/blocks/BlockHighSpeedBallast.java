package train.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHighSpeedBallast extends Block {

    public BlockHighSpeedBallast(Material p_i45394_1_) {
        super(p_i45394_1_);

    }

    @Override
    public void dropBlockAsItemWithChance(World p_149642_1_, BlockPos p_149642_2_, IBlockState p_149642_3_, float p_149642_4_, int p_149642_5_) {
        super.dropBlockAsItemWithChance(p_149642_1_, p_149642_2_, p_149642_3_, p_149642_4_, p_149642_5_);
    }
}

