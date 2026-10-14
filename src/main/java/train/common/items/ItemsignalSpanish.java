package train.common.items;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import train.common.Traincraft;
import train.common.library.Info;

public class ItemsignalSpanish extends ItemBlock implements train.common.items.ILegacyIcon {

    public ItemsignalSpanish(Block id) {
        super(id);
        maxStackSize = 64;
        setCreativeTab(Traincraft.tcTab);
    }




    @Override
    public String legacyIcon(int meta) {
        return "tc:paintbrushThing";
    }
}
