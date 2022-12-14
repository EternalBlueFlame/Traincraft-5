package train.blocks.bench;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.blocks.BlockDynamic;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import train.Traincraft;

public class BlockTrainWorkbench extends BlockDynamic {

	public BlockTrainWorkbench() {
		super(Material.wood, true);
		setCreativeTab(Traincraft.tcTab);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public Object getGUI(EntityPlayer player, TileEntity te){
		return new train.blocks.bench.GuiTrainCraftingBlock(player.inventory, (TileTrainWbench) te);
	}


	@Override
	public void onBlockAdded(World world, int i, int j, int k) {
		super.onBlockAdded(world, i, j, k);
		CommonUtil.markBlockForUpdate(world, i, j, k);
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack) {
		TileTrainWbench te = (TileTrainWbench) world.getTileEntity(i, j, k);
		if (te != null) {
			int dir = CommonUtil.floorDouble((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
			te.setFacing(ForgeDirection.getOrientation(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
			CommonUtil.markBlockForUpdate(world, i, j, k);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileTrainWbench(this);
	}

}