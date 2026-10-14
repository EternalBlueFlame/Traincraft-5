package train.common.items;

import net.minecraft.world.World;

import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import train.common.Traincraft;
import train.common.library.Info;

import java.util.List;

public class ItemChunkLoaderActivator extends Item implements train.common.items.ILegacyIcon {

	public ItemChunkLoaderActivator() {
		super();
		maxStackSize = 1;
		setCreativeTab(Traincraft.tcTab);
		setMaxDamage(10);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack, @Nullable World world, List<String> par3List, ITooltipFlag flag) {
		boolean par4 = flag.isAdvanced(); EntityPlayer par2EntityPlayer = Traincraft.proxy.getPlayer();
		par3List.add("\u00a77" + "Right click on a Locomotive");
		par3List.add("\u00a77" + " to start/stop chunk loading.");
		par3List.add("\u00a77" + "Locomotives will load chunks");
		par3List.add("\u00a77" + "around attached carts.");
	}

    @Override
    public String legacyIcon(int meta) {
        return "tc:chunk_loader";
    }
}
