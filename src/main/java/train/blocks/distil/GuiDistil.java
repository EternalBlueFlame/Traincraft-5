package train.blocks.distil;

import ebf.tim.registry.TiMFluids;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.library.Info;

public class GuiDistil extends GuiContainer {

	private TileEntityDistil distilInventory;
	private int staticAmount;

	public GuiDistil(InventoryPlayer invPlayer, TileEntityDistil tileentitydistil) {
		super(new ContainerDistil(invPlayer, tileentitydistil));
		distilInventory = tileentitydistil;
		staticAmount = distilInventory.getLiquid();
	}

	@Override
	public void updateScreen(){
		if (distilInventory.getLiquid() != staticAmount){
			staticAmount = distilInventory.getLiquid();
			super.updateScreen();
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString("Distillation tower", 8, 6, 0x404040);
		fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
		if (distilInventory.inventory.get(0).getStack() != null) {
			double plasticChance = DistilRecipes.smelting().getPlasticChance(distilInventory.inventory.get(0).getStack().getItem());
			if(plasticChance!=0){//stops showing 100% for blocks that aren't part of a recipe
				double chanceShown = ((1 / plasticChance) * 100);
				fontRenderer.drawString((int) chanceShown + "%", 79, 70, 0x404040);
			}
		}
	}

	@Override
	public void drawScreen(int t, int g, float par3) {
		super.drawScreen(t, g, par3);
		if (intersectsWith(t, g)) {
			drawCreativeTabHoveringText(distilInventory.getLiquid()>0 && distilInventory.getTankInfo(0)!=null &&
					distilInventory.getTankInfo(0).fluid!=null?
					distilInventory.getTankInfo(0).fluid.getLocalizedName():"empty", t, g);
		}
	}

	@Override
	protected void drawCreativeTabHoveringText(String str, int t, int g) {

		int amount =distilInventory.getLiquid();
		int textWidth = fontRenderer.getStringWidth(amount + "/" + distilInventory.getTankCapacity()[0]);
		int startX = t + 14;
		int startY = g - 12;

		int i4 = 0xf0100010;
		drawGradientRect(startX - 3, startY - 4, startX + textWidth + 3, startY + 8 + 4 + 10, i4, i4);
		drawGradientRect(startX - 4, startY - 3, startX + textWidth + 4, startY + 8 + 3 + 10, i4, i4);
		int colour1 = 0x505000ff;
		int colour2 = (colour1 & 0xfefefe) >> 1 | colour1 & 0xff000000;
		drawGradientRect(startX - 3, startY - 3, startX + textWidth + 3, startY + 8 + 3 + 10, colour1, colour2);
		drawGradientRect(startX - 2, startY - 2, startX + textWidth + 2, startY + 8 + 2 + 10, i4, i4);
		fontRenderer.drawStringWithShadow(str, startX, startY, -1);
		fontRenderer.drawStringWithShadow( amount + "/" + distilInventory.getTankCapacity()[0], startX, startY + 10, -1);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int t, int g) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation,Info.guiPrefix + "gui_distillation_tower2.png"));
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
		int amount = distilInventory.getLiquid();
		int liqui =  Math.abs((amount * 50) / (distilInventory.getTankCapacity()[0]));
		drawTexturedModalRect(j + 145, (k + 57) - liqui, 177, 107 - liqui, 18, liqui);
		if (distilInventory.getFluidStack(0)!=null && distilInventory.getFluidStack(0).fluid == TiMFluids.fluidfueloil) {
			drawRect(j + 145, k + 57, 177, 107, 0);
		}

		if (distilInventory.isBurning()) {
			int l = distilInventory.getBurnTimeRemainingScaled(12);
			drawTexturedModalRect(j + 56, (k + 36 + 12) - l, 176, 12 - l, 14, l + 2);
		}
		int i1 = distilInventory.getCookProgressScaled(22);//31
		drawTexturedModalRect(j + 87, k + 36, 184, 15, i1, 41);//41
	}

	public boolean intersectsWith(int mouseX, int mouseY) {
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		return (mouseX >= j + 143 && mouseX <= j + 164 && mouseY >= k + 5 && mouseY <= k + 57);
	}
}
