package train.common.items;

import com.sun.istack.internal.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemBlockGeneratorWindMill extends ItemBlock{

	public ItemBlockGeneratorWindMill(Block id) {
		super(id);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> par3List, ITooltipFlag flagIn) {
		par3List.add("\u00a77" + "RF generator.");
		par3List.add("\u00a77" + "Max Production (storm): 15 RF/t.");
	}
}
