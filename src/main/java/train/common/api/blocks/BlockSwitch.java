package train.common.api.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSwitch extends BlockDynamic {


    public BlockSwitch(Material material, int storage) {
        super(material, storage);
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public int getRenderType() {
        return -1;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileSwitch(this);
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else {

            TileSwitch t = (TileSwitch)world.getTileEntity(pos);
            if(t!=null){
                t.toggleEnabled(0);
                world.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.NEUTRAL, 0.3F, t.getStrength(0)>0 ? 0.6F : 0.5F, false);
                world.notifyNeighborsOfStateChange(pos, this, true);
            }

            return true;
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        world.notifyNeighborsOfStateChange(pos, this, true);
        super.breakBlock(world, pos, state);
    }



    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int meta) {
        return isProvidingStrongPower(world, x, y, z, meta);
    }

    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int meta) {
        TileSwitch t = (TileSwitch)world.getTileEntity(new BlockPos(x, y, z));
        if(t!=null && t.getStrength(0)>0){
            return 15;
        }
        return 0;
    }

    public boolean canProvidePower() {
        return true;
    }


    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);
        TileSwitch te = (TileSwitch) world.getTileEntity(pos);
        if (te !=null) {
            te.setStrength(0,0);
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block other, BlockPos fromPos) {
        super.neighborChanged(state, world, pos, other, fromPos);
    }
}
