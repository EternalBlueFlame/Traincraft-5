package train.common.api.blocks;

import net.minecraft.util.math.BlockPos;

import net.minecraft.block.state.IBlockState;

import net.minecraft.util.ITickable;

import train.client.render.item.TCItemRenderer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.ClientUtil;
import ebf.tim.utility.CommonUtil;
import fexcraft.tmt.slim.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.util.EnumFacing;
import train.common.Traincraft;

public class TileRenderFacing extends TileEntity implements ITickable {
    public int facing =-1;
    private Integer blockGLID =null;
    private int uiTexture=0;
    public BlockDynamic host;

    public TileRenderFacing(BlockDynamic block){
        host=block;
    }

    public TileRenderFacing(){}

    public TileRenderFacing setFacing(int direction){
        facing=direction;
        this.markDirty();
        return this;
    }

    public TileRenderFacing setFacing(EnumFacing direction){
        //this follows our own orders, which don't make a lot of sense, but it works.
        switch (direction){
            case SOUTH:{facing=0;break;}
            case EAST:{facing=1;break;}
            case NORTH:{facing=2;break;}
            case WEST:{facing=3;break;}
            case DOWN:{facing=4;break;}
            case UP:{facing=5;break;}
        }
        this.markDirty();
        return this;
    }

    //for whatever dumb stupid reason, sometimes getgetWorld()ect() doesn't exist.
    public World getWorld(){
        return world;
    }

    public EnumFacing getFacing(){
        //1.8.9+ it's getHorizontal
        switch (facing){
            case 0:{return EnumFacing.SOUTH;}
            case 1:{return EnumFacing.EAST;}
            case 2:{return EnumFacing.NORTH;}
            case 3:{return EnumFacing.WEST;}
            case 4:{return EnumFacing.DOWN;}
            case 5:{return EnumFacing.UP;}
        }
        return EnumFacing.NORTH;
    }

    @Override
    public boolean shouldRenderInPass(int pass){
        return pass==0;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox(){
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public void addInfoToCrashReport(CrashReportCategory r) {
        if(r==null){
            uiTexture =  org.lwjgl.opengl.GL11.glGetInteger( org.lwjgl.opengl.GL11.GL_TEXTURE_2D);
            if(getTexture(getPos().getX(),getPos().getY(),getPos().getZ())!=null) {
                org.lwjgl.opengl.GL11.glEnable( org.lwjgl.opengl.GL11.GL_TEXTURE_2D);
                TextureManager.bindTexture(getTexture(getPos().getX(),getPos().getY(),getPos().getZ()));
            } else {
                org.lwjgl.opengl.GL11.glDisable( org.lwjgl.opengl.GL11.GL_TEXTURE_2D);
            }


            if(blockGLID ==null) {
                blockGLID = net.minecraft.client.renderer.GLAllocation.generateDisplayLists(1);
                org.lwjgl.opengl.GL11.glNewList(blockGLID, org.lwjgl.opengl.GL11.GL_COMPILE);
                if (world == null) {
                    ClientUtil.fixItemLighting(world);
                } else {
                    Minecraft.getMinecraft().entityRenderer.enableLightmap();
                }
                org.lwjgl.opengl.GL11.glTranslatef(0.5f, 0.5f, 0.5f);
                switch (facing) {
                    //north
                    case 0: {
                        org.lwjgl.opengl.GL11.glRotatef(90, 0, 1, 0);
                        break;
                    }
                    //east
                    case 1: {
                        org.lwjgl.opengl.GL11.glRotatef(180, 0, 1, 0);
                        break;
                    }
                    //south
                    case 2: {
                        org.lwjgl.opengl.GL11.glRotatef(270, 0, 1, 0);
                        break;
                    }
                    //west
                    case 3: {
                        break;
                    }
                }
                org.lwjgl.opengl.GL11.glRotatef(180, 1, 0, 0);

                renderModel();
                if (world == null) {
                    ClientUtil.fixItemLighting(world);
                }
                org.lwjgl.opengl.GL11.glEndList();
            }

            if(blockGLID!=null){
                org.lwjgl.opengl.GL11.glCallList(blockGLID);
                if(false){//todo:config option to disable render caching
                    org.lwjgl.opengl.GL11.glDeleteLists(blockGLID,1);
                    blockGLID =null;
                }
            }
            //be sure to re-enable the texture biding, because the UI wont
            org.lwjgl.opengl.GL11.glEnable( org.lwjgl.opengl.GL11.GL_TEXTURE_2D);
            net.minecraft.client.renderer.GlStateManager.bindTexture(uiTexture);
        } else{
            super.addInfoToCrashReport(r);
        }
    }

    @SideOnly(Side.CLIENT)
    public void renderModel(){
        if(host!=null && host.model!=null) {
            host.model.render();
        } else {
            for (TexturedPolygon poly : cube.faces) {
                Tessellator.getInstance().drawTexturedVertsWithNormal(poly, 0.0625f);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getTexture(int x, int y, int z){
        return host.getTexture(x,y,z);
    }

    public boolean canUpdateLegacy() {return false;}

    @Override
    public void update() {}

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
    	Block oldBlock = oldState.getBlock(), newBlock = newState.getBlock(); int oldMeta = oldBlock.getMetaFromState(oldState), newMeta = newBlock.getMetaFromState(newState); int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        return (newBlock != oldBlock);
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if(this.getWorld() != null) {
            CommonUtil.markBlockForUpdate(getWorld(), getPos().getX(), getPos().getY(), getPos().getZ());
            world.markChunkDirty(getPos(), this);
            if (host != null) this.world.updateComparatorOutputLevel(getPos(), host);
            if (world.isRemote && blockGLID != null) {
            //    org.lwjgl.opengl.GL11.glDeleteLists(blockGLID, 1);
            //    blockGLID = null;
            }
        }
    }

    @Override
    public void onChunkUnload() {
        if(Traincraft.proxy.isClient() && blockGLID!=null){
            org.lwjgl.opengl.GL11.glDeleteLists(blockGLID, 1);
            blockGLID = null;
        }
    }

    /** 1.12 sends this with the chunk data (1.7.10 sent the description packet instead). */
    @Override
    public NBTTagCompound getUpdateTag() {
    	return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeToNBT(nbttagcompound);
        return new SPacketUpdateTileEntity(getPos(), 0, nbttagcompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        if(pkt ==null){return;}
        readFromNBT(pkt.getNbtCompound());
        markDirty();
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("f", facing);
    	return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag){
        super.readFromNBT(tag);
        facing = tag.getInteger("f");
        if(getWorld()!=null && world.isRemote) {
            markDirty();
        }
    }

    public void syncTileEntity(){
        for(Object o : this.world.playerEntities){
            if(o instanceof EntityPlayerMP){
                EntityPlayerMP player = (EntityPlayerMP) o;
                if(player.getDistance(getPos().getX(), getPos().getY(), getPos().getZ()) <= 64) {
                    player.connection.sendPacket(this.getUpdatePacket());
                }
            }
        }
    }

    //todo: better control for render distance
    @SideOnly(Side.CLIENT)
    @Override
    public double getMaxRenderDistanceSquared() {
        return ((Minecraft.getMinecraft().gameSettings.renderDistanceChunks*16)+16)*((Minecraft.getMinecraft().gameSettings.renderDistanceChunks*16)+16);
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getIconResource() {
        return null;
    }
    @SideOnly(Side.CLIENT)
    public boolean force2dItem(TCItemRenderer.ItemRenderType type){return false;}

    public static final ModelRendererTurbo cube = new ModelRendererTurbo((ModelBase) null, 0,0,64,32).addBox(-8,-8,-8,16,16,16);

}
