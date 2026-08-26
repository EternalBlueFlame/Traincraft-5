package train.common.blocks.switchStand;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import train.common.Traincraft;
import train.common.api.blocks.BlockSwitch;
import train.common.tile.switchStand.TileCircleSwitchStand;


public class BlockCircleSwitchStand extends BlockSwitch {

    public BlockCircleSwitchStand() {
        super(Material.IRON, 0);
        setCreativeTab(Traincraft.tcTab);
        this.setTickRandomly(true);
        setHardness(1.75F);
        // TODO 1.12: block bounds are per-state now (hitboxShape): this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.75F, 0.8F);
    }
    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);
        world.notifyBlockUpdate(pos, state, state, 3);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entityliving, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, entityliving, stack);
        TileCircleSwitchStand te = (TileCircleSwitchStand) world.getTileEntity(pos);
        if (te != null) {
            int dir = MathHelper.floor((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
            te.setFacing(ForgeDirection.getOrientation(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4).toDirection());
            world.notifyBlockUpdate(pos, state, state, 3);
        }
    }


    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileCircleSwitchStand(this);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileCircleSwitchStand(this);
    }
}
