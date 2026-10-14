package train.common.blocks.switchStand;

import net.minecraft.util.math.BlockPos;

import net.minecraft.block.state.IBlockState;

import net.minecraft.block.SoundType;

import ebf.tim.utility.CommonUtil;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;
import train.common.api.blocks.BlockSwitch;
import train.common.tile.switchStand.TileOWOSwitchStand;


public class BlockOWOSwitchStand extends BlockSwitch {

    public BlockOWOSwitchStand() {
        super(Material.IRON, 0);
        setCreativeTab(Traincraft.tcTab);
        this.setTickRandomly(true);
        setHardness(1.75F);
        setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.75F, 0.8F);
        setSoundType(SoundType.METAL);
    }
    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
    	int i = pos.getX(), j = pos.getY(), k = pos.getZ();
        super.onBlockAdded(world, new BlockPos(i, j, k), world.getBlockState(new BlockPos(i, j, k)));
        CommonUtil.markBlockForUpdate(world, i, j, k);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
    	int i = pos.getX(), j = pos.getY(), k = pos.getZ();
        super.onBlockPlacedBy(world, new BlockPos(i, j, k), world.getBlockState(new BlockPos(i, j, k)), entityliving, stack);
        TileOWOSwitchStand te = (TileOWOSwitchStand) CommonUtil.getTileEntity(world, i, j, k);
        if (te != null) {
            int dir = CommonUtil.floorDouble((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
            te.setFacing(CommonUtil.getOrientation(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
            CommonUtil.markBlockForUpdate(world, i, j, k);
        }
    }


    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
    	int metadata = getMetaFromState(state);
        return new TileOWOSwitchStand(this);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileOWOSwitchStand(this);
    }
}
