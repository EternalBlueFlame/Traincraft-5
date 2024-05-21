package train.common.items.slabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import train.common.library.BlockIDs;

public class ItemBallastSlab extends ItemSlab {
    public ItemBallastSlab(Block block)
    {
        super(block, (BlockSlab) BlockIDs.ballastSlab.block, (BlockSlab) BlockIDs.ballastDoubleSlab.block, block == BlockIDs.ballastDoubleSlab.block);
        this.setUnlocalizedName("ballast slab");
    }
}
