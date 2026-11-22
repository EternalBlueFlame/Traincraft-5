package train.common.blocks.slabs;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import train.common.Traincraft;
import train.common.library.BlockIDs;

import java.util.List;
import java.util.Random;

public class BlockBallastSlab extends BlockSlab
{
    public BlockBallastSlab(boolean par2)
    {
        super(par2, Material.ROCK);
        this.useNeighborBrightness = true;
        this.setSoundType(SoundType.STONE);
        this.setResistance(5.0F);
        this.setHardness(2.0F);
        this.setTranslationKey("ballast slab");
    }

    /************
     * STUFF
     ************/
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> list)
    {
        if (item != Item.getItemFromBlock(BlockIDs.ballastDoubleSlab.block))
        {
            list.add(new ItemStack(item, 1, 0));
        }
    }

    @Override
    public String getTranslationKey(int par1)
    {
        return super.getTranslationKey();
    }

    @SideOnly(Side.CLIENT)
    private static boolean isBlockSingleSlab(Block block)
    {
        return block == BlockIDs.ballastSlab.block;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World par1World, int par2, int par3, int par4)
    {
        return isBlockSingleSlab(this) ? Item.getItemFromBlock(this) : (this == BlockIDs.ballastDoubleSlab.block ? Item.getItemFromBlock(BlockIDs.ballastSlab.block) : Item.getItemFromBlock(BlockIDs.oreTC.block));
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
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return BlockIDs.oreTC.block.getIcon(side, 3);
    }
}