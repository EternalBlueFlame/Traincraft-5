package train.common.blocks.slabs;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.blocks.BlockOreTC;
import train.common.library.BlockIDs;

import java.util.Random;

public class BlockBallastSlab extends BlockSlab
{
    private boolean isDoubleSlab;

    public BlockBallastSlab(boolean par2)
    {
        super(Material.ROCK);
        this.isDoubleSlab = par2;
        this.setHardness(2.0F);
    }

    /************
     * STUFF
     ************/
    @Override
    public String getTranslationKey(int type)
    {
        return getTranslationKey();
    }

    @Override
    public boolean isDouble()
    {
        return this.isDoubleSlab;
    }

    @Override
    public net.minecraft.block.properties.IProperty<?> getVariantProperty()
    {
        // TODO 1.12: slab variant property
        return null;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        // TODO 1.12: slab half enum from item
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list)
    {
        Item item = Item.getItemFromBlock(this);
        if (item != Item.getItemFromBlock(BlockIDs.ballastDoubleSlab.block))
        {
            list.add(new ItemStack(item, 1, 0));
        }
    }

    @SideOnly(Side.CLIENT)
    private static boolean isBlockSingleSlab(Block block)
    {
        return block == BlockIDs.ballastSlab.block;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getItem(World world, BlockPos pos, IBlockState state)
    {
        return new ItemStack(isBlockSingleSlab(this) ? this : (this == BlockIDs.ballastDoubleSlab.block ? BlockIDs.ballastSlab.block : BlockIDs.oreTC.block));
    }

    /************
     * DROPS
     ************/
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(BlockIDs.ballastSlab.block);
    }

    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(BlockIDs.ballastSlab.block, 2, 0);
    }

    /************
     * TEXTURES
     ************/
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return ((BlockOreTC) BlockIDs.oreTC.block).getIcon(side, 3);
    }
}
