package ebf.tim.render;

import ebf.tim.utility.Vec5f;
import fexcraft.tmt.slim.ModelRendererTurbo;
import fexcraft.tmt.slim.Tessellator;
import fexcraft.tmt.slim.TextureManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraft.util.EnumFacing;
import org.lwjgl.opengl.GL11;
import train.client.core.ClientProxy;
import train.common.api.AbstractTrains;
import train.common.api.blocks.TileRenderFacing;
import train.common.items.ItemRollingStock;

import java.util.HashMap;

public class CustomItemModel implements IItemRenderer /*ICustomModelLoader*/ {

    public static CustomItemModel instance = new CustomItemModel();

    private static HashMap<Item, TileRenderFacing> blockTextures = new HashMap<>();

    private static Integer itemSprite = null;

    public static void registerBlockTextures(Item itm, TileEntity tile){
        if(tile instanceof TileRenderFacing) {
            blockTextures.put(itm, (TileRenderFacing) tile);
        }
    }


    @Override// generally useless but needs to be here
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    //@Override // generally useless but needs to be here
    public void onResourceManagerReload(IResourceManager resourceManager) {}



    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;//models.containsKey(new ResourceLocation(item.getUnlocalizedName()));
    }
    float scale;

    public static void render2dItem(ResourceLocation texture, ItemRenderType type){
        TextureManager.bindTexture(texture);
        GL11.glRotatef(225,0,1,0);
        GL11.glScalef(1.4f,-1.4f,1);

        if(itemSprite==null || !GL11.glIsList(itemSprite)) {
            ModelRendererTurbo box =new ModelRendererTurbo("", 0, 0, 16, 16)
                    .addBox(-8, -8, 0, 16, 16, 0f);

            itemSprite= net.minecraft.client.renderer.GLAllocation.generateDisplayLists(1);
            GL11.glNewList(itemSprite, GL11.GL_COMPILE);
            box.render();
            GL11.glEndList();
        } else {

            switch (type){
                case INVENTORY: {
                    GL11.glEnable(GL11.GL_BLEND);
                    break;}
                case EQUIPPED_FIRST_PERSON:{
                    GL11.glTranslatef(0,-0.5f,-1);
                    break;
                }
                case EQUIPPED:{
                    GL11.glTranslatef(0,-0.5f,-1);
                    GL11.glRotatef(-45,1,0,0);
                    break;
                }
                default:{//item frame case
                    GL11.glRotatef(50,0,1,0);
                    GL11.glScalef(0.8f,0.8f,0.8f);
                }

            }
            if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
                GL11.glTranslatef(0,0,-0.005f);
                for(int i=0;i<20;i++){
                    GL11.glTranslatef(0,0,0.00025f*i);
                    GL11.glCallList(itemSprite);
                }
            } else {
                GL11.glCallList(itemSprite);
            }
        }

    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if(item==null){return;}

        if (item.getItem() instanceof ItemRollingStock){
            GL11.glPushMatrix();

            if(!ClientProxy.hdTransportItems && TextureManager.textureExists(((ItemRollingStock) item.getItem()).getIconResource())){
                render2dItem(((ItemRollingStock) item.getItem()).getIconResource(), type);
                GL11.glPopMatrix();
                return;
            }

            AbstractTrains entity = ((ItemRollingStock) item.getItem()).entity;
            scale = entity.getHitboxSize()[0];
            if(scale!=0){
                scale = 1.3f/(scale /1.3f);
            }
            GL11.glScalef(scale,scale,scale);
            switch (type){
                case EQUIPPED_FIRST_PERSON:{
                    GL11.glRotatef(270+(1*entity.getHitboxSize()[0]),0,1,0);
                    GL11.glRotatef(10+(-1*entity.getHitboxSize()[0]),0,0,1);
                    GL11.glRotatef(-1*entity.getHitboxSize()[0],1,0,0);
                    GL11.glTranslatef(1.5f,3+(0.4f*(entity.getHitboxSize()[0])),0.75f);
                    GL11.glRotatef(180,1,0,0);
                    break;
                }
                case INVENTORY: {
                    GL11.glRotatef(90,0,1,0);
                    GL11.glRotatef(-25,1,0,1);
                    GL11.glRotatef(180,1,0,0);
                    GL11.glTranslatef(0,-1.3f,0);
                    break;
                }
                case EQUIPPED:{
                    GL11.glRotatef(45,0,1,0);
                    GL11.glRotatef(10+(-1*entity.getHitboxSize()[0]),0,0,1);
                    GL11.glRotatef(-1*entity.getHitboxSize()[0],1,0,0);
                    GL11.glTranslatef(0.25f*(entity.getHitboxSize()[0]),0.15f*(entity.getHitboxSize()[0]),0.5f*(entity.getHitboxSize()[0]));
                    GL11.glTranslatef(-1,2,0);
                    GL11.glRotatef(180,1,0,0);
                    break;
                }
                default:{//item frame case
                    GL11.glRotatef(90,0,1,0);
                    GL11.glScalef(0.5f,0.5f,0.5f);
                    GL11.glTranslatef(0,1.5f,0);
                    GL11.glRotatef(180,1,0,0);
                }

            }
            ClientProxy.transportRenderer.doRender(
                    ((ItemRollingStock)item.getItem()).entity,0,0,0, 0,0);
            GL11.glPopMatrix();
        } else if(blockTextures.containsKey(item.getItem())) {
            GL11.glPushMatrix();

            if(blockTextures.get(item.getItem()).getIconResource()!=null &&
                    (!ClientProxy.hdTransportItems || blockTextures.get(item.getItem()).force2dItem(type))) {
                render2dItem(blockTextures.get(item.getItem()).getIconResource(), type);
                GL11.glPopMatrix();
                return;
            }
            switch (type){
                case EQUIPPED_FIRST_PERSON:{break;}
                case INVENTORY:{break;}
                case EQUIPPED:{break;}
                case FIRST_PERSON_MAP:{break;}
                case ENTITY:{
                    GL11.glRotatef(180,0,1,0);
                    GL11.glTranslatef(-0.5f,-0.4f,-0.5f);
                    break;
                }
                default:{//item frame case
                    break;
                }
            }
            if (blockTextures.get(item.getItem()).host!=null && blockTextures.get(item.getItem()).host.tesr instanceof TileEntitySpecialRenderer) {
                ((TileEntitySpecialRenderer) blockTextures.get(item.getItem()).host.tesr)
                        .renderTileEntityAt(blockTextures.get(item.getItem()), 0, 0, 0, 0);
            } else {
                blockTextures.get(item.getItem()).func_145828_a(null);
            }
            GL11.glPopMatrix();

        }


    }
    private static final Vec5f start = new Vec5f(0.625f, 0, 0, 0, 0), end = new Vec5f(-0.625f, 0, 0, 0, 0)
            ,tieStart = new Vec5f(0.625f*0.125f, 0, 0, 0, 0),tieEnd = new Vec5f(-0.625f*0.125f, 0, 0, 0, 0);
}
