package ebf.tim.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockTiM extends net.minecraft.item.ItemBlock {

    private int burn_time = -1;

    public ItemBlockTiM(Block block){
        super(block);
    }

    public ItemBlockTiM(Block block, int burnTime){
        super(block);
        burn_time=burnTime;
    }

    @Override
    public int getMetadata(int meta){
        return meta;
    }

    //@Override
    //todo:unused in 1.7, possibly add to the burnables registry?
    public int getItemBurnTime(ItemStack stack){
        return burn_time;
    }

}
