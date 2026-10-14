package train.common.api.blocks;
import train.common.core.util.LegacyRender;
import train.client.gui.GuiCrafterTier;

import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumBlockRenderType;

import net.minecraft.block.state.IBlockState;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import train.common.Traincraft;
import ebf.tim.utility.CommonUtil;
import fexcraft.tmt.slim.ModelBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.containers.ContainerTier;

import java.util.List;

/**
 * <h1>Block core</h1>
 * Generic block class to simplify the process of creating new blocks.
 * All the functionality for every block can be managed here, and then just instanced in the registry with new parameters.
 *
 * @author Eternal Blue Flame
 */
public class BlockDynamic extends BlockContainer {

	// 1.12: keep the 1.7.10 metadata (facing/variant) in the block state, see LegacyMeta
	@Override
	protected net.minecraft.block.state.BlockStateContainer createBlockState() {
		return new net.minecraft.block.state.BlockStateContainer(this, train.common.api.blocks.LegacyMeta.META);
	}

	@Override
	public net.minecraft.block.state.IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(train.common.api.blocks.LegacyMeta.META, meta & 15);
	}

	@Override
	public int getMetaFromState(net.minecraft.block.state.IBlockState state) {
		return state.getValue(train.common.api.blocks.LegacyMeta.META);
	}


	/* 1.7.10 block bounds (setBlockBounds, minX..maxZ) on top of 1.12 getBoundingBox() */
	protected double minX = 0, minY = 0, minZ = 0, maxX = 1, maxY = 1, maxZ = 1;

	public void setBlockBounds(float x1, float y1, float z1, float x2, float y2, float z2) {
		minX = x1; minY = y1; minZ = z1; maxX = x2; maxY = y2; maxZ = z2;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
	}


    public ModelBase model=null;
    public Object tesr=null;
    public int slots=0;

    public BlockDynamic(Material material, int storage) {
        super(material);
        this.slots=storage;
        setBlockBounds(hitboxShape()[0],hitboxShape()[1],hitboxShape()[2],hitboxShape()[3],hitboxShape()[4],hitboxShape()[5]);
    }

    //1.7 version of getting if block is opaque, used for server side checks like if creatures can spawn on it
    @Override
    public boolean isTopSolid(IBlockState state) {return true;}

    public Block setModel(ModelBase modelBase){
        model=modelBase;
        return this;
    }


    public Block setTESR(Object modelRender){
        tesr=modelRender;
        return this;
    }

    @Override
    public void breakBlock(World w, BlockPos pos, IBlockState state) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ(); Block b = state.getBlock(); int meta = getMetaFromState(state);
        //super.breakBlock(w, x, y, z, b, meta);
        w.removeTileEntity(pos);
    }

    public String textureName = null;

    public Block setBlockTextureName(String name) {return this;}

    public Block setTextureName(String name){
        textureName=name;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture(int x, int y, int z){
        return new ResourceLocation(this.textureName == null ? "MISSING_ICON_BLOCK_" + getIdFromBlock(this) + "_" + this.getTranslationKey() :textureName);
    }


    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return LegacyRender.renderType(-1);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
    	int metadata = getMetaFromState(state);
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return slots>0?new TileTraincraft(slots):new TileRenderFacing(this);
    }

    //returns a series of values to define the size of the block from start to end, with a normal block starting at 0 and ending at 1.
    public float[] hitboxShape(){return new float[]{0,0,0,1,1,1};}

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return new AxisAlignedBB(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
    }

    @Override
    public boolean isPassable(IBlockAccess p_149655_1_, BlockPos pos) {
    	int p_149655_2_ = pos.getX(), p_149655_3_ = pos.getY(), p_149655_4_ = pos.getZ();
        return hitboxShape()[4]>=1;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
    	int meta = getMetaFromState(state);
        return createNewTileEntity(world, meta);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        super.onBlockPlacedBy(world, new BlockPos(x, y, z), world.getBlockState(new BlockPos(x, y, z)), entity, stack);
        //force tile spawn manually and override any existing tile at the space
        CommonUtil.setTileEntity(world, x, y, z, createNewTileEntity(world,0));
        if(CommonUtil.getTileEntity(world, x, y, z) instanceof TileRenderFacing){
            switch ((CommonUtil.floorDouble(((entity.rotationYaw-45)%360) / 90.0F)&3)){
                case 0: ((TileRenderFacing) CommonUtil.getTileEntity(world, x, y, z)).setFacing(EnumFacing.SOUTH);break;
                case 1: ((TileRenderFacing) CommonUtil.getTileEntity(world, x, y, z)).setFacing(EnumFacing.EAST);break;
                case 2: ((TileRenderFacing) CommonUtil.getTileEntity(world, x, y, z)).setFacing(EnumFacing.NORTH);break;
                case 3: ((TileRenderFacing) CommonUtil.getTileEntity(world, x, y, z)).setFacing(EnumFacing.WEST);break;

            }

        }
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    	int x = pos.getX(), y = pos.getY(), z = pos.getZ(); int p_149727_6_ = facing.getIndex();
        if (player.isSneaking() || slots==0) {
            return false;
        } else if (world.isRemote) {
            return true;
        }

        if (CommonUtil.getTileEntity(world, x, y, z) instanceof TileTraincraft) {
            player.openGui(Traincraft.instance, 0, world, x, y, z);
            return true;
        } else {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public Object getGUI(EntityPlayer player, TileEntity te){
        return new GuiCrafterTier(player.inventory, (TileTraincraft) te);
    }

    public Object getInventoryManager(EntityPlayer player, TileEntity te){
        return new ContainerTier(player.inventory, (TileTraincraft) te);
    }


    public class particleTexture extends TextureAtlasSprite {


        public particleTexture(String textureName, int xOffset, int yOffset, int scale){
            super(textureName);
            initSprite(scale,scale,xOffset,yOffset,false);
        }
    }

}
