package train.common.blocks.switchStand;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import train.common.Traincraft;
import train.common.api.blocks.BlockSwitch;
import train.common.tile.switchStand.TileOWOSwitchStand;


public class BlockOWOSwitchStand extends BlockSwitch {

    public BlockOWOSwitchStand() {
        super(Material.iron, 0);
        setCreativeTab(Traincraft.tcTab);
        this.setTickRandomly(true);
        setHardness(1.75F);
        setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.75F, 0.8F);
        setStepSound(soundTypeMetal);
    }
    @Override
    public void onBlockAdded(World world, int i, int j, int k) {
        super.onBlockAdded(world, i, j, k);
        world.markBlockForUpdate(i, j, k);
    }

    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack) {
        super.onBlockPlacedBy(world, i, j, k, entityliving, stack);
        TileOWOSwitchStand te = (TileOWOSwitchStand) world.getTileEntity(i, j, k);
        if (te != null) {
            int dir = MathHelper.floor_double((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
            te.setFacing(ForgeDirection.getOrientation(dir == 0 ? 2 : dir == 1 ? 5 : dir == 2 ? 3 : 4));
            world.markBlockForUpdate(i, j, k);
        }
    }


    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileOWOSwitchStand(this);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileOWOSwitchStand(this);
    }
}
