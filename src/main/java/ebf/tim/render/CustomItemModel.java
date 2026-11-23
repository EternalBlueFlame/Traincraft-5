package ebf.tim.render;

import fexcraft.tmt.slim.ModelRendererTurbo;
import fexcraft.tmt.slim.TextureManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.client.core.ClientProxy;
import train.common.api.AbstractTrains;
import train.common.api.blocks.TileRenderFacing;
import train.common.items.ItemRollingStock;

import java.util.HashMap;

public class CustomItemModel extends TileEntityItemStackRenderer {

    public static final CustomItemModel instance = new CustomItemModel();

    private static final HashMap<Item, TileRenderFacing> blockTextures = new HashMap<>();

    private static Integer itemSprite = null;

    public static void registerBlockTextures(Item item, TileEntity tile) {
        if (tile instanceof TileRenderFacing) {
            blockTextures.put(item, (TileRenderFacing) tile);
        }
    }

    private float scale;

    public static void render2dItem(ResourceLocation texture) {
        TextureManager.bindTexture(texture);
        GL11.glRotatef(225, 0, 1, 0);
        GL11.glScalef(1.4f, -1.4f, 1);

        if (itemSprite == null || !GL11.glIsList(itemSprite)) {
            ModelRendererTurbo box = new ModelRendererTurbo("", 0, 0, 16, 16)
                    .addBox(-8, -8, 0, 16, 16, 0f);

            itemSprite = net.minecraft.client.renderer.GLAllocation.generateDisplayLists(1);
            GL11.glNewList(itemSprite, GL11.GL_COMPILE);
            box.render();
            GL11.glEndList();
        } else {
            GL11.glEnable(GL11.GL_BLEND);
            if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
                GL11.glTranslatef(0, 0, -0.005f);
                for (int i = 0; i < 20; i++) {
                    GL11.glTranslatef(0, 0, 0.00025f * i);
                    GL11.glCallList(itemSprite);
                }
            } else {
                GL11.glCallList(itemSprite);
            }
        }
    }

    @Override
    public void renderByItem(ItemStack item, float partialTicks) {
        if (item == null) {
            return;
        }

        if (item.getItem() instanceof ItemRollingStock) {
            GL11.glPushMatrix();

            ItemRollingStock rollingStock = (ItemRollingStock) item.getItem();
            if (!ClientProxy.hdTransportItems
                    && rollingStock.getEntity() != null
                    && TextureManager.textureExists(rollingStock.getIconResource())) {
                render2dItem(rollingStock.getIconResource());
                GL11.glPopMatrix();
                return;
            }

            AbstractTrains entity = rollingStock.getEntity();
            if (entity == null) {
                GL11.glPopMatrix();
                return;
            }

            scale = entity.getHitboxSize()[0];
            if (scale != 0) {
                scale = 1.3f / (scale / 1.3f);
            }
            GL11.glScalef(scale, scale, scale);
            GL11.glRotatef(90, 0, 1, 0);
            GL11.glRotatef(-25, 1, 0, 1);
            GL11.glRotatef(180, 1, 0, 0);
            GL11.glTranslatef(0, -1.3f, 0);

            ClientProxy.transportRenderer.doRender(entity, 0, 0, 0, 0, partialTicks);
            GL11.glPopMatrix();
        } else if (blockTextures.containsKey(item.getItem())) {
            GL11.glPushMatrix();

            TileRenderFacing tile = blockTextures.get(item.getItem());
            if (tile.getIconResource() != null
                    && (!ClientProxy.hdTransportItems || tile.force2dItem())) {
                render2dItem(tile.getIconResource());
                GL11.glPopMatrix();
                return;
            }

            if (tile.host != null && tile.host.tesr instanceof net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer) {
                ((net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer) tile.host.tesr)
                        .render(tile, 0, 0, 0, partialTicks, -1, 1.0f);
            } else {
                tile.func_145828_a(null);
            }
            GL11.glPopMatrix();
        }
    }
}
