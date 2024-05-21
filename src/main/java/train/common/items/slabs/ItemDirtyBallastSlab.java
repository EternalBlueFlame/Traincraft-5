package train.common.items.slabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import train.common.library.BlockIDs;

public class ItemDirtyBallastSlab extends ItemSlab {
    public ItemDirtyBallastSlab(Block block)
    {
        super(block, (BlockSlab) BlockIDs.dirtyBallastSlab.block, (BlockSlab) BlockIDs.dirtyBallastDoubleSlab.block, block == BlockIDs.dirtyBallastDoubleSlab.block);
        this.setUnlocalizedName("dirty ballast slab");
    }
}
