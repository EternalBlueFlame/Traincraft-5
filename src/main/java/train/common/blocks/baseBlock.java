package train.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;


public class baseBlock extends Block {

    public baseBlock(String name, float hardness, float resistance, String harvestTool, int harvestLevel, Material material, SoundType stepSound, String textureLocation) {
        super(material);
        setCreativeTab(Traincraft.tcTab);
        setHardness(hardness);
        setHarvestLevel(harvestTool,harvestLevel);
        setSoundType(stepSound);
        setBlockTextureName(Info.modID+":"+textureLocation);
    }

    public baseBlock setBlockTextureName(String name) {
        return this;
    }

    @Override
    public void dropBlockAsItemWithChance(World p_149642_1_, BlockPos p_149642_2_, IBlockState p_149642_3_, float p_149642_4_, int p_149642_5_) {
        super.dropBlockAsItemWithChance(p_149642_1_, p_149642_2_, p_149642_3_, p_149642_4_, p_149642_5_);
    }
}
