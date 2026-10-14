package train.common.items;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import train.common.Traincraft;

public class ItemoverheadWire extends ItemBlock {
    public ItemoverheadWire(Block p_i45328_1_) {
        super(p_i45328_1_);
        setTextureName("tc:overheadWireItem");
        setCreativeTab(Traincraft.tcTab);
    }

}
