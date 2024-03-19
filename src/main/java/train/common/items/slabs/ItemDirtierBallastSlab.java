package train.common.items.slabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import train.common.library.BlockIDs;

public class ItemDirtierBallastSlab extends ItemSlab {
    public ItemDirtierBallastSlab(Block block)
    {
        super(block, (BlockSlab) BlockIDs.dirtierBallastSlab.block, (BlockSlab) BlockIDs.dirtierBallastDoubleSlab.block, block == BlockIDs.dirtierBallastDoubleSlab.block);
        this.setUnlocalizedName("dirtier ballast slab");
    }
}
