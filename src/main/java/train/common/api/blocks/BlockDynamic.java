package train.common.api.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import train.common.Traincraft;
import ebf.tim.utility.CommonUtil;
import fexcraft.tmt.slim.ModelBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.IIcon;
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

    public ModelBase model=null;
    public Object tesr=null;
    public int slots=0;
    public String textureName;

    public BlockDynamic(Material material, int storage) {
        super(material);
        this.slots=storage;
        // TODO 1.12: block bounds are per-IBlockState now (hitboxShape())
    }

    //1.7 version of getting if block is opaque, used for server side checks like if creatures can spawn on it
    public boolean func_149730_j(){return true;}

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
        w.getChunk(pos.getX() >> 4, pos.getZ() >> 4)
                .removeTileEntity(new BlockPos(pos.getX() & 15, pos.getY(), pos.getZ() & 15));
    }

    public Block setBlockTextureName(String name){
        textureName = name;
        return this;
    }

    public Block setTextureName(String name){
        textureName=name;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture(int x, int y, int z){
        return new ResourceLocation(this.textureName == null ? "MISSING_ICON_BLOCK_" + (this.getRegistryName() == null ? "unknown" : this.getRegistryName().toString()) : textureName);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int a, int b){
        // TODO 1.12: fluid/block icon pipeline
        return null;
    }

    public int getRenderType(){
        return -1;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }

    public boolean renderAsNormalBlock(){
        return false;
    }

    @Override
    public boolean hasTileEntity()
    {
        return true;
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return slots>0?new TileTraincraft(slots):new TileRenderFacing(this);
    }

    //returns a series of values to define the size of the block from start to end, with a normal block starting at 0 and ending at 1.
    public float[] hitboxShape(){return new float[]{0,0,0,1,1,1};}

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        float[] s = hitboxShape();
        return new AxisAlignedBB((double)x + s[0], (double)y + s[1], (double)z + s[2], (double)x + s[3], (double)y + s[4], (double)z + s[5]);
    }
    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB hitboxSelf, List<AxisAlignedBB> p_149743_6_, Entity collidingEntity, boolean p_185476_7_) {
        AxisAlignedBB box = getCollisionBoundingBoxFromPool(world, pos.getX(), pos.getY(), pos.getZ());
        //if there's multiple hitboxes, ex stairs, this needs to be done for each
        if(hitboxSelf.intersects(box)) {
            p_149743_6_.add(box);
        }
    }
    public boolean getBlocksMovement(IBlockState state) {
        return hitboxShape()[4]>=1;
    }

    public TileEntity createTileEntity(World world, int meta) {
        return createNewTileEntity(world, meta);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack){
        super.onBlockPlacedBy(world, pos, state, entity, stack);
        //force tile spawn manually and override any existing tile at the space
        world.setTileEntity(pos,createNewTileEntity(world,0));
        if(world.getTileEntity(pos) instanceof TileRenderFacing){
            switch ((CommonUtil.floorDouble(((entity.rotationYaw-45)%360) / 90.0F)&3)){
                case 0: ((TileRenderFacing) world.getTileEntity(pos)).setFacing(EnumFacing.SOUTH);break;
                case 1: ((TileRenderFacing) world.getTileEntity(pos)).setFacing(EnumFacing.EAST);break;
                case 2: ((TileRenderFacing) world.getTileEntity(pos)).setFacing(EnumFacing.NORTH);break;
                case 3: ((TileRenderFacing) world.getTileEntity(pos)).setFacing(EnumFacing.WEST);break;

            }

        }
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (player.isSneaking() || slots==0) {
            return false;
        } else if (world.isRemote) {
            return true;
        }

        if (world.getTileEntity(pos) instanceof TileTraincraft) {
            player.openGui(Traincraft.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        } else {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public Object getGUI(EntityPlayer player, TileEntity te){
        return new train.client.gui.GuiCrafterTier(player.inventory, (TileTraincraft) te);
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
