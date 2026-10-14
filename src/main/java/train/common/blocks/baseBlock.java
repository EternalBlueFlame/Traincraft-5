package train.common.blocks;

import net.minecraft.block.SoundType;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;


public class baseBlock extends Block {

    public baseBlock(String name, float hardness, float resistance, String harvestTool, int harvestLevel, Material material, SoundType stepSound, String textureLocation) {
        super(material);
        setCreativeTab(Traincraft.tcTab);
        setTranslationKey(name);
        setHardness(hardness);
        setResistance(resistance);
        setHarvestLevel(harvestTool,harvestLevel);
        setSoundType(stepSound);
        // 1.12: textures come from the block model json (was setBlockTextureName)
    }

}
