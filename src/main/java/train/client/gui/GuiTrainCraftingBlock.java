package train.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import train.common.containers.ContainerTrainWorkbench;
import train.common.library.Info;

public class GuiTrainCraftingBlock extends GuiContainer {
    public GuiTrainCraftingBlock(InventoryPlayer invPlayer, World world, IInventory inv) {
        super(new ContainerTrainWorkbench(invPlayer, world, inv));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString("Train Workbench", 8, 6, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        String guiTexture = Info.guiPrefix + "crafting_table.png";
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        mc.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, guiTexture));

        int rectPosX = (this.width - this.xSize) / 2;
        int rectPosY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(rectPosX, rectPosY, 0, 0, this.xSize, this.ySize);
    }
}
