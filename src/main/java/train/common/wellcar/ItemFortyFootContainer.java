package train.common.wellcar;

import com.sun.istack.internal.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class ItemFortyFootContainer extends ItemBlock {
    public ItemFortyFootContainer(Block p_i45328_1_) {
        super(p_i45328_1_);
        setMaxStackSize(1);
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> par3List, ITooltipFlag flagIn) {
        par3List.add("\u00a77" + "A container that you can put items in.");
        par3List.add("\u00a77" + "Can be put on wellcars.");
        par3List.add("\u00a77" + "Color can be changed with a paintbrush.");
        par3List.add("\u00a77" + TextFormatting.RED + "Warning, a work in progress! Things may break!");
    }


}
