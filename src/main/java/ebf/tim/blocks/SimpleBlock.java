package ebf.tim.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import ebf.tim.TrainsInMotion;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SimpleBlock extends Block {
    public SimpleBlock(float hardness, float resistance, String unlocalizedName, String texture) {
        super(Material.rock);

        setHardness(hardness);
        setResistance(resistance);
        setBlockName(unlocalizedName);
        setBlockTextureName(texture);
        setHarvestLevel("pickaxe", 2); //iron pick for all blocks

        setCreativeTab(TrainsInMotion.creativeTab);
        GameRegistry.registerBlock(this, unlocalizedName);
    }
}
