package train.common.api.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import fexcraft.tmt.slim.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import train.common.Traincraft;

public class TileRenderFacing extends TileEntity {
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

    public TileRenderFacing setFacing(ForgeDirection direction){
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

    //for whatever dumb stupid reason, sometimes getWorldObject() doesn't exist.
    public World getWorld(){
        return worldObj;
    }

    public ForgeDirection getFacing(){
        //1.8.9+ it's getHorizontal
        switch (facing){
            case 0:{return ForgeDirection.SOUTH;}
            case 1:{return ForgeDirection.EAST;}
            case 2:{return ForgeDirection.NORTH;}
            case 3:{return ForgeDirection.WEST;}
            case 4:{return ForgeDirection.DOWN;}
            case 5:{return ForgeDirection.UP;}
        }
        return ForgeDirection.NORTH;
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
    public void func_145828_a(CrashReportCategory r){
        if(r==null){
            uiTexture =  org.lwjgl.opengl.GL11.glGetInteger( org.lwjgl.opengl.GL11.GL_TEXTURE_2D);
            if(getTexture(xCoord,yCoord,zCoord)!=null) {
                org.lwjgl.opengl.GL11.glEnable( org.lwjgl.opengl.GL11.GL_TEXTURE_2D);
                TextureManager.bindTexture(getTexture(xCoord,yCoord,zCoord));
            } else {
                org.lwjgl.opengl.GL11.glDisable( org.lwjgl.opengl.GL11.GL_TEXTURE_2D);
            }


            if(blockGLID ==null) {
                blockGLID = net.minecraft.client.renderer.GLAllocation.generateDisplayLists(1);
                org.lwjgl.opengl.GL11.glNewList(blockGLID, org.lwjgl.opengl.GL11.GL_COMPILE);
                if (worldObj == null) {
                    Minecraft.getMinecraft().entityRenderer.disableLightmap(1);
                } else {
                    Minecraft.getMinecraft().entityRenderer.enableLightmap(1);
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
                if (worldObj == null) {
                    Minecraft.getMinecraft().entityRenderer.disableLightmap(1);
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
            org.lwjgl.opengl.GL11.glBindTexture( org.lwjgl.opengl.GL11.GL_TEXTURE_2D,uiTexture);
        } else{
            super.func_145828_a(r);
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

    @Override
    public boolean canUpdate(){return false;}

    @Override
    public void updateEntity(){}

    @Override
    public boolean shouldRefresh(Block oldBlock, Block newBlock, int oldMeta, int newMeta, World world, int x, int y, int z) {
        return (newBlock != oldBlock);
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if(this.worldObj != null) {
            CommonUtil.markBlockForUpdate(worldObj, xCoord, yCoord, zCoord);
            worldObj.markTileEntityChunkModified(xCoord, yCoord, zCoord, this);
            this.worldObj.func_147453_f(this.xCoord, this.yCoord, this.zCoord, host);
            if (worldObj.isRemote && blockGLID != null) {
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

    @Override
    public S35PacketUpdateTileEntity getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        if(pkt ==null){return;}
        readFromNBT(pkt.func_148857_g());
        markDirty();
    }


    @Override
    public void writeToNBT(NBTTagCompound tag){
        super.writeToNBT(tag);
        tag.setInteger("f", facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag){
        super.readFromNBT(tag);
        facing = tag.getInteger("f");
        if(worldObj!=null && worldObj.isRemote) {
            markDirty();
        }
    }

    public void syncTileEntity(){
        for(Object o : this.worldObj.playerEntities){
            if(o instanceof EntityPlayerMP){
                EntityPlayerMP player = (EntityPlayerMP) o;
                if(player.getDistance(xCoord, yCoord, zCoord) <= 64) {
                    player.playerNetServerHandler.sendPacket(this.getDescriptionPacket());
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
    public boolean force2dItem(net.minecraftforge.client.IItemRenderer.ItemRenderType type){return false;}

    public static final ModelRendererTurbo cube = new ModelRendererTurbo((ModelBase) null, 0,0,64,32).addBox(-8,-8,-8,16,16,16);

}
