package ebf.tim.items;

import ebf.tim.blocks.TileRenderFacing;
import ebf.tim.entities.GenericRailTransport;
import ebf.tim.render.models.ModelRail;
import ebf.tim.utility.ClientProxy;
import ebf.tim.utility.Vec5f;
import fexcraft.tmt.slim.Tessellator;
import fexcraft.tmt.slim.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

import static ebf.tim.render.models.Model1x1Rail.addVertexWithOffsetAndUV;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;

public class CustomItemModel implements IItemRenderer /*ICustomModelLoader*/ {

    public static CustomItemModel instance = new CustomItemModel();

    private static HashMap<Item, TileRenderFacing> blockTextures = new HashMap<>();

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
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if(item==null){return;}

        if (item.getItem() instanceof ItemTransport){
            GL11.glPushMatrix();
            GenericRailTransport entity = ((ItemTransport) item.getItem()).entity;
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
            ClientProxy.transportRenderer.render(
                    ((ItemTransport)item.getItem()).entity,0,0,0, 0, true, null);
            GL11.glPopMatrix();
        } else if (item.getItem() instanceof ItemRail){
            if(item.getTagCompound()==null){
                return;
            }
            GL11.glPushMatrix();
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glTranslated(0, 0.05, 0);
            Tessellator.bindTexture(TextureMap.locationBlocksTexture);

            if(type==ItemRenderType.INVENTORY){
                GL11.glRotatef(90,1,0,0);
                GL11.glRotatef(-45,0,0,1);
                GL11.glRotatef(90,0,1,0);
                GL11.glScalef(1.4f,1f,1.2f);
            }
            if(item.getTagCompound().hasKey("ballast")) {
                IIcon iicon = TextureManager.bindBlockTextureFromSide(ForgeDirection.UP.ordinal(),
                        ItemStack.loadItemStackFromNBT(item.getTagCompound().getCompoundTag("ballast")));

                Tessellator.getInstance().startDrawing(GL11.GL_QUAD_STRIP);
                addVertexWithOffsetAndUV(start, 0.625f, 0, 0, iicon.getMinU(), iicon.getMinV());
                addVertexWithOffsetAndUV(start, -0.625f, 0, 0, iicon.getMinU(), iicon.getMaxV());
                addVertexWithOffsetAndUV(end, 0.625f, 0, 0, iicon.getMaxU(), iicon.getMinV());
                addVertexWithOffsetAndUV(end, -0.625f, 0, 0, iicon.getMaxU(), iicon.getMaxV());

                Tessellator.getInstance().draw();
            }

            if(item.getTagCompound().hasKey("ties")) {
                IIcon iicon = TextureManager.bindBlockTextureFromSide(ForgeDirection.WEST.ordinal(),
                        ItemStack.loadItemStackFromNBT(item.getTagCompound().getCompoundTag("ties")));

                GL11.glPushMatrix();
                GL11.glTranslatef(-0.45f,0.01f,0);
                Tessellator.getInstance().startDrawing(GL11.GL_QUAD_STRIP);
                addVertexWithOffsetAndUV(tieStart, 0.625f, 0, 0, iicon.getMinU(), iicon.getMinV());
                addVertexWithOffsetAndUV(tieStart, -0.625f, 0, 0, iicon.getMinU(), iicon.getMaxV());
                addVertexWithOffsetAndUV(tieEnd, 0.625f, 0, 0, iicon.getMaxU(), iicon.getMinV());
                addVertexWithOffsetAndUV(tieEnd, -0.625f, 0, 0, iicon.getMaxU(), iicon.getMaxV());
                Tessellator.getInstance().draw();
                GL11.glTranslatef(0.3f,0,0);
                Tessellator.getInstance().startDrawing(GL11.GL_QUAD_STRIP);
                addVertexWithOffsetAndUV(tieStart, 0.625f, 0, 0, iicon.getMinU(), iicon.getMinV());
                addVertexWithOffsetAndUV(tieStart, -0.625f, 0, 0, iicon.getMinU(), iicon.getMaxV());
                addVertexWithOffsetAndUV(tieEnd, 0.625f, 0, 0, iicon.getMaxU(), iicon.getMinV());
                addVertexWithOffsetAndUV(tieEnd, -0.625f, 0, 0, iicon.getMaxU(), iicon.getMaxV());
                Tessellator.getInstance().draw();
                GL11.glTranslatef(0.3f,0,0);
                Tessellator.getInstance().startDrawing(GL11.GL_QUAD_STRIP);
                addVertexWithOffsetAndUV(tieStart, 0.625f, 0, 0, iicon.getMinU(), iicon.getMinV());
                addVertexWithOffsetAndUV(tieStart, -0.625f, 0, 0, iicon.getMinU(), iicon.getMaxV());
                addVertexWithOffsetAndUV(tieEnd, 0.625f, 0, 0, iicon.getMaxU(), iicon.getMinV());
                addVertexWithOffsetAndUV(tieEnd, -0.625f, 0, 0, iicon.getMaxU(), iicon.getMaxV());
                Tessellator.getInstance().draw();
                GL11.glTranslatef(0.3f,0,0);
                Tessellator.getInstance().startDrawing(GL11.GL_QUAD_STRIP);
                addVertexWithOffsetAndUV(tieStart, 0.625f, 0, 0, iicon.getMinU(), iicon.getMinV());
                addVertexWithOffsetAndUV(tieStart, -0.625f, 0, 0, iicon.getMinU(), iicon.getMaxV());
                addVertexWithOffsetAndUV(tieEnd, 0.625f, 0, 0, iicon.getMaxU(), iicon.getMinV());
                addVertexWithOffsetAndUV(tieEnd, -0.625f, 0, 0, iicon.getMaxU(), iicon.getMaxV());
                Tessellator.getInstance().draw();

                GL11.glPopMatrix();
            }

            if(item.getTagCompound().hasKey("rail")) {
                int[] color = {255,255,255};
                ItemStack rail = ItemStack.loadItemStackFromNBT(item.getTagCompound().getCompoundTag("rail"));
                if(TextureManager.ingotColors.containsKey(rail)) {
                    color = TextureManager.ingotColors.get(rail);
                } else {
                    for (Map.Entry<ItemStack, int[]> e : TextureManager.ingotColors.entrySet()) {
                        if (e.getKey().getItem() ==rail.getItem() &&
                                e.getKey().getTagCompound() == rail.getTagCompound() &&
                                e.getKey().getItemDamage() == rail.getItemDamage()) {
                            color = TextureManager.ingotColors.get(e.getKey());
                            break;
                        }
                    }
                }

                GL11.glPushMatrix();
                GL11.glDisable(GL_TEXTURE_2D);
                GL11.glTranslatef(0f,0.02f,-0.05f);
                ModelRail.centerShading(-0.4f,color,30,true);
                Tessellator.getInstance().startDrawing(GL11.GL_QUAD_STRIP);
                addVertexWithOffsetAndUV(start, -0.3f, 0, 0, 0,0);
                addVertexWithOffsetAndUV(start, -0.4f, 0, 0, 0,0);
                addVertexWithOffsetAndUV(end, -0.3f, 0, 0, 0,0);
                addVertexWithOffsetAndUV(end, -0.4f, 0, 0, 0, 0);
                Tessellator.getInstance().draw();

                ModelRail.centerShading(-0.4f,color,30,false);
                Tessellator.getInstance().startDrawing(GL11.GL_QUAD_STRIP);
                addVertexWithOffsetAndUV(start, -0.2f, 0, 0, 0,0);
                addVertexWithOffsetAndUV(start, -0.3f, 0, 0, 0,0);
                addVertexWithOffsetAndUV(end, -0.2f, 0, 0, 0,0);
                addVertexWithOffsetAndUV(end, -0.3f, 0, 0, 0, 0);
                Tessellator.getInstance().draw();

                ModelRail.centerShading(0.4f,color,30,true);
                Tessellator.getInstance().startDrawing(GL11.GL_QUAD_STRIP);
                addVertexWithOffsetAndUV(start, 0.5f, 0, 0, 0,0);
                addVertexWithOffsetAndUV(start, 0.4f, 0, 0, 0,0);
                addVertexWithOffsetAndUV(end, 0.5f, 0, 0, 0,0);
                addVertexWithOffsetAndUV(end, 0.4f, 0, 0, 0, 0);
                Tessellator.getInstance().draw();

                ModelRail.centerShading(0.4f,color,30,false);
                Tessellator.getInstance().startDrawing(GL11.GL_QUAD_STRIP);
                addVertexWithOffsetAndUV(start, 0.4f, 0, 0, 0,0);
                addVertexWithOffsetAndUV(start, 0.3f, 0, 0, 0,0);
                addVertexWithOffsetAndUV(end, 0.4f, 0, 0, 0,0);
                addVertexWithOffsetAndUV(end, 0.3f, 0, 0, 0, 0);
                Tessellator.getInstance().draw();
                GL11.glEnable(GL_TEXTURE_2D);
                GL11.glColor4f(1,1,1,1);
                GL11.glPopMatrix();
            }

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
        } else if(blockTextures.containsKey(item.getItem())) {
            GL11.glPushMatrix();
            GL11.glScalef(0.95f,0.95f,0.95f);
            GL11.glTranslatef(0,-0.1f,0);
            if(blockTextures.get(item.getItem()).host.tesr instanceof TileEntitySpecialRenderer){
                ((TileEntitySpecialRenderer)blockTextures.get(item.getItem()).host.tesr)
                        .renderTileEntityAt(blockTextures.get(item.getItem()),0,0,0,0);
            } else {
                blockTextures.get(item.getItem()).func_145828_a(null);
            }
            GL11.glPopMatrix();

        }


    }
    private static final Vec5f start = new Vec5f(0.625f, 0, 0, 0, 0), end = new Vec5f(-0.625f, 0, 0, 0, 0)
            ,tieStart = new Vec5f(0.625f*0.125f, 0, 0, 0, 0),tieEnd = new Vec5f(-0.625f*0.125f, 0, 0, 0, 0);
}
