package train.client.gui;

import ebf.tim.gui.GUIButton;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.common.Traincraft;
import train.common.api.LiquidManager;
import train.common.api.Locomotive;
import train.common.api.SteamTrain;
import train.common.core.network.PacketParkingBrake;
import train.common.core.network.PacketSetTrainLockedToClient;
import train.common.inventory.InventoryForney;
import train.common.library.Info;

public class GuiForney extends GuiContainer {


    private final String texture = Info.guiPrefix + "customButton.png";
    private final Locomotive loco;
    private GuiButton buttonLock;
    private int textureX = 0;
    private int textureY = 46;
    private int textureSizeX = 40;
    private int textureSizeY = 13;
    private int buttonPosX = 0;
    private int buttonPosY = 0;

    private InventoryPlayer player;
    private GUIButton buttonSeatManager;

    public GuiForney(InventoryPlayer inventoryplayer, Entity entityminecart) {
        super(new InventoryForney(inventoryplayer, (Locomotive) entityminecart));
        loco = (Locomotive) entityminecart;
        player = inventoryplayer;
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        if (!loco.parkingBrake) {
            // Brake: On
            if (loco instanceof SteamTrain) {
                textureX = 41;
                textureY = 13;
                textureSizeX = 40;
                textureSizeY = 13;
            } else {
                textureX = 126;
                textureY = 13;
                textureSizeX = 43;
                textureSizeY = 13;
            }
            buttonPosX = 33;
        } else {
            // Brake: Off
            if (loco instanceof SteamTrain) {
                textureX = 0;
                textureY = 13;
                textureSizeX = 40;
                textureSizeY = 13;
            } else {
                textureX = 82;
                textureY = 13;
                textureSizeX = 43;
                textureSizeY = 13;
            }
            buttonPosX = 0;
        }
        buttonPosY = -13;

        buttonList.add(new GuiCustomButton(2, ((width - xSize) / 2) + buttonPosX, ((height - ySize) / 2) + buttonPosY, textureSizeX, textureSizeY, "", texture, textureX, textureY));

        int buttonPosX = (this.width - xSize) / 2;
        int buttonPosY = (this.height - ySize) / 2;

        if (!loco.getTrainLockedFromPacket()) {
            this.buttonList.add(this.buttonLock = new GuiButton(3, buttonPosX + 124, buttonPosY - 10, 51, 10, "Unlocked"));
        } else {
            this.buttonList.add(this.buttonLock = new GuiButton(3, buttonPosX + 130, buttonPosY - 10, 43, 10, "Locked"));
        }
        if (loco.seats.size() > 1) {
            this.buttonList.add(this.buttonSeatManager = new GUIButton(buttonPosX + 124,buttonPosY - 20, 51,10, "Seats") {
                @Override
                public String getHoverText() {
                    return "gui.seats";
                }

                @Override
                public int[] getColor(){
                    return null;
                }

                @Override
                public void onClick() {
                    Traincraft.proxy.seatGUI(player.player,loco);
                }

                @Override
                public FontRenderer getFont(){return fontRendererObj;}
            });
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float par3) {
        for(Object guiButton: buttonList) {
            if (guiButton instanceof GUIButton) {
                ((GUIButton)guiButton).drawText(mouseX-(int)guiLeft,mouseY-(int)guiTop);
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton guibutton) {
        if (guibutton.id == 2) {
            if ((!loco.getParkingBrakeFromPacket()) && loco.getSpeed() < 10) {
                Traincraft.brakeChannel.sendToServer(new PacketParkingBrake(true, loco.getEntityId()));
                loco.parkingBrake = true;
                guibutton.displayString = "Brake: On";
                this.initGui();
            } else if (loco.getSpeed() < 10) {
                Traincraft.brakeChannel.sendToServer(new PacketParkingBrake(false, loco.getEntityId()));
                loco.parkingBrake = false;
                guibutton.displayString = "Brake: Off";
                this.initGui();
            }
        }

        if (guibutton.id == 3) {
            //System.out.println("1 "+loco.getTrainOwner());
            //System.out.println("2 "+((EntityPlayer)loco.riddenByEntity).getDisplayName());
            //System.out.println(((EntityPlayer)loco.riddenByEntity).getDisplayName().equals(loco.getTrainOwner().trim()));

            if (loco.riddenByEntity != null && loco.riddenByEntity instanceof EntityPlayer && ((EntityPlayer) loco.riddenByEntity).getDisplayName().equals(loco.getTrainOwner())) {
                if (!loco.getTrainLockedFromPacket()) {
                    Traincraft.lockChannel.sendToServer(new PacketSetTrainLockedToClient(true, loco.getEntityId()));
                    loco.locked = true;
                    guibutton.displayString = "Locked";
                    this.initGui();
                } else {
                    Traincraft.lockChannel.sendToServer(new PacketSetTrainLockedToClient(false, loco.getEntityId()));
                    loco.locked = false;
                    guibutton.displayString = "Unlocked";
                    this.initGui();
                }
            } else if (loco.riddenByEntity != null && loco.riddenByEntity instanceof EntityPlayer) {
                ((EntityPlayer) loco.riddenByEntity).addChatMessage(new ChatComponentText("You are not the owner"));
            }
        } else if (guibutton instanceof GUIButton) {
            ((GUIButton)guibutton).onClick();
        }
    }

    @Override
    protected void drawCreativeTabHoveringText(String str, int t, int g) {

        //int liqui = (dieselInventory.getLiquidAmount() * 50) / dieselInventory.getTankCapacity();
        String state = "";
        if (loco.getTrainLockedFromPacket()) {
            state = "Locked";
        } else {
            state = "Unlocked";
        }

        int textWidth = fontRendererObj.getStringWidth("the GUI, change speed, destroy it.");
        int startX = 90;
        int startY = 5;

        int i4 = 0xf0100010;
        drawGradientRect(startX - 3, startY - 4, startX + textWidth + 3, startY + 8 + 4 + 40, i4, i4);
        drawGradientRect(startX - 4, startY - 3, startX + textWidth + 4, startY + 8 + 3 + 40, i4, i4);
        int colour1 = 0x505000ff;
        int colour2 = (colour1 & 0xfefefe) >> 1 | colour1 & 0xff000000;
        drawGradientRect(startX - 3, startY - 3, startX + textWidth + 3, startY + 8 + 3 + 40, colour1, colour2);
        drawGradientRect(startX - 2, startY - 2, startX + textWidth + 2, startY + 8 + 2 + 40, i4, i4);
        fontRendererObj.drawStringWithShadow(str, startX, startY, -1);
        fontRendererObj.drawStringWithShadow("only its owner can open", startX, startY + 10, -1);
        fontRendererObj.drawStringWithShadow("the GUI, change speed, destroy it.", startX, startY + 20, -1);
        fontRendererObj.drawStringWithShadow("Current state: " + state, startX, startY + 30, -1);
        fontRendererObj.drawStringWithShadow("Owner: " + loco.getTrainOwner().trim(), startX, startY + 40, -1);
    }

    public boolean intersectsWith(int mouseX, int mouseY) {
        //System.out.println(mouseX+" "+mouseY);
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        return (mouseX >= j + 124 && mouseX <= j + 174 && mouseY >= k - 10 && mouseY <= k);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        fontRendererObj.drawString(loco.getCommandSenderName(), 37, 3, 0x000000);
        fontRendererObj.drawString(loco.getCommandSenderName(), 39, 1, 0x000000);
        fontRendererObj.drawString(loco.getCommandSenderName(), 37, 1, 0x000000);
        fontRendererObj.drawString(loco.getCommandSenderName(), 39, 3, 0x000000);

        fontRendererObj.drawString(loco.getCommandSenderName(), 38, 3, 0x000000);
        fontRendererObj.drawString(loco.getCommandSenderName(), 38, 1, 0x000000);
        fontRendererObj.drawString(loco.getCommandSenderName(), 37, 2, 0x000000);
        fontRendererObj.drawString(loco.getCommandSenderName(), 39, 2, 0x000000);
        fontRendererObj.drawString(loco.getCommandSenderName(), 38, 2, 0xd3a900);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        if (intersectsWith(i, j)) {
            drawCreativeTabHoveringText("When a locomotive is locked,", i, j);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int t, int g) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, Info.guiPrefix + "gui_forney.png"));
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

        if (loco.getIsFuelled()) {
            int l = loco.getFuelDiv(12);
            drawTexturedModalRect(j + 8, (k + 30) - l, 176, 12 - l, 14, l + 2);
        }

        if (loco instanceof SteamTrain) {
            int load = (((SteamTrain) loco).getWater());
            int lo = Math.abs(((load * 50) / (((SteamTrain) loco).getCartTankCapacity())));

            if (((SteamTrain) loco).getLiquidItemID() == LiquidManager.WATER_FILTER.getFluidID()) {
                drawTexturedModalRect(j + 143, (k + 68) - lo, 190, 69 - lo, 18, lo + 1);
            }
        }

        fontRendererObj.drawStringWithShadow("Carts pulled: " + loco.getCurrentNumCartsPulled(), 1, 10, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Mass pulled: " + loco.getCurrentMassPulled(), 1, 20, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Speed reduction: " + loco.getCurrentSpeedSlowDown() + " km/h", 1, 30, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Accel reduction: " + loco.getCurrentAccelSlowDown(), 1, 40, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Brake reduction: " + loco.getCurrentBrakeSlowDown(), 1, 50, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Fuel consumption: 1 every " + loco.getFuelConsumption() + " ticks", 1, 60, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Fuel: " + loco.getFuel(), 1, 70, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Power: " + loco.transportMetricHorsePower() + " Mhp", 1, 80, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("State: " + loco.getState(), 1, 90, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Heat level: " + loco.getOverheatLevel(), 1, 100, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Maximum Speed: " + (loco.getCustomSpeedGUI()), 1, 110, 0xFFFFFF);
    }
}