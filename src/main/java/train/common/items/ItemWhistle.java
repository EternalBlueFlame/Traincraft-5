package train.common.items;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumActionResult;

import net.minecraft.util.ActionResult;

import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.Info;

import java.util.List;

public class ItemWhistle extends Item implements train.common.items.ILegacyIcon {

    public ItemWhistle() {
        super();
        maxStackSize = 64;
        setCreativeTab(Traincraft.tcTab);
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack par1ItemStack, @Nullable World world, List<String> par3List, ITooltipFlag flag) {
    	boolean par4 = flag.isAdvanced(); EntityPlayer par2EntityPlayer = Traincraft.proxy.getPlayer();
        par3List.add("\u00a77" + TextFormatting.GREEN + "Right click to whistle!");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World p_77659_2_, EntityPlayer p_77659_3_, EnumHand hand) {
    	ItemStack p_77659_1_ = p_77659_3_.getHeldItem(hand);
        CommonUtil.playSound(p_77659_3_, Info.resourceLocation + ":" + "whistle", 1F, 1.0F);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, p_77659_1_);

    }

    @Override
    public String legacyIcon(int meta) {
        return "tc:whistle";
    }
}
