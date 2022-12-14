package ebf.tim.blocks;

import ebf.tim.TrainsInMotion;
import ebf.tim.registry.TiMGenericRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SimpleBlock extends Block {
    public SimpleBlock(float hardness, float resistance, String unlocalizedName, String texture) {
        super(Material.rock);

        setHardness(hardness);
        setResistance(resistance);
        setBlockName(unlocalizedName);
        setHarvestLevel("pickaxe", 2); //iron pick for all blocks

        TiMGenericRegistry.registerBlock(this, TrainsInMotion.creativeTab,TrainsInMotion.MODID,unlocalizedName,null,null);
        setBlockTextureName(texture);
    }
}
