package train.common.items.slabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import train.common.library.BlockIDs;

public class ItemSnowGravelSlab extends ItemSlab {
    public ItemSnowGravelSlab(Block block)
    {
        super(block, (BlockSlab) BlockIDs.snowGravelSlab.block, (BlockSlab) BlockIDs.snowGravelDoubleSlab.block, block == BlockIDs.snowGravelDoubleSlab.block);
        this.setUnlocalizedName("dirty ballast slab");
    }
}
