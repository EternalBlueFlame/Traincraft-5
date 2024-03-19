package train.common.items.slabs;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import train.common.library.BlockIDs;

public class ItemHighSpeedBallastSlab extends ItemSlab {
    public ItemHighSpeedBallastSlab(Block block)
    {
        super(block, (BlockSlab) BlockIDs.highSpeedBallastSlab.block, (BlockSlab) BlockIDs.highSpeedBallastDoubleSlab.block, block == BlockIDs.highSpeedBallastDoubleSlab.block);
        this.setUnlocalizedName("dirty ballast slab");
    }
}
