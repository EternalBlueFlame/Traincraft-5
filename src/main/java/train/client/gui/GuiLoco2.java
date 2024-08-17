package train.client.gui;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ebf.tim.gui.GUIButton;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import train.common.Traincraft;
import train.common.api.*;
import train.common.core.network.*;
import train.common.inventory.InventoryLoco;
import train.common.library.Info;

import java.util.Collections;

public class GuiLoco2 extends GuiContainer {
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


    public GuiLoco2(InventoryPlayer inventoryplayer, Entity entityminecart) {
        super(new InventoryLoco(inventoryplayer, (Locomotive) entityminecart));
        loco = (Locomotive) entityminecart;
        player = inventoryplayer;
    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();

        if (!loco.getParkingBrakeFromPacket()) {
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
            buttonPosX = 43;
            buttonPosY = -13;
            buttonList.add(new GuiCustomButton(2, ((width - xSize) / 2) + buttonPosX - 12, ((height - ySize) / 2) + buttonPosY, textureSizeX, textureSizeY, "", texture, textureX, textureY));//Brake: Off
        } else {
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
            buttonPosY = -13;
            buttonList.add(new GuiCustomButton(2, ((width - xSize) / 2) + buttonPosX, ((height - ySize) / 2) + buttonPosY, textureSizeX, textureSizeY, "", texture, textureX, textureY));//Brake: On
        }

        int buttonPosX = (this.width - xSize) / 2;
        int buttonPosY = (this.height - ySize) / 2;

        if (!loco.getTrainLockedFromPacket()) {
            this.buttonList.add(this.buttonLock = new GuiButton(3, buttonPosX + 108, buttonPosY - 10, 67, 10, "Unlocked"));
        } else {
            this.buttonList.add(this.buttonLock = new GuiButton(3, buttonPosX + 108, buttonPosY - 10, 67, 10, "Locked"));
        }

        if (!(loco instanceof SteamTrain)) {
            if (loco.isLocoTurnedOn()) {
                this.buttonList.add(this.buttonLock = new GuiButton(4, buttonPosX + 108, buttonPosY - 22, 67, 12, "Stop Engine"));
            } else {
                this.buttonList.add(this.buttonLock = new GuiButton(4, buttonPosX + 108, buttonPosY - 22, 67, 12, "Start Engine"));
            }
        }
        if (loco.seats.size() > 1) {
            this.buttonList.add(this.buttonSeatManager = new GUIButton(buttonPosX + 108, buttonPosY - 20, 67,10, "Seats") {
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

        //region Lights On/Off
        if (loco.isLightsEnabled())
        {
            buttonList.add(this.buttonLock = new GuiButton(6, buttonPosX + 108, buttonPosY + 166, 67, 12, "Lights: On"));
        }
        else
        {
            buttonList.add(this.buttonLock = new GuiButton(6, buttonPosX + 108, buttonPosY + 166, 67, 12, "Lights: Off"));
        }
        //endregion Lights On/Off

        //region Beacon On/Off
        if (loco.isBeaconEnabled())
        {
            buttonList.add(this.buttonLock = new GuiButton(7, buttonPosX + 41, buttonPosY + 166, 67, 12, "Beacon: On"));
        }
        else
        {
            buttonList.add(this.buttonLock = new GuiButton(7, buttonPosX + 41, buttonPosY + 166, 67, 12, "Beacon: Off"));
        }
        //endregion Beacon On/Off

        //region DitchLights On/Off
        if (loco.isDitchLightsEnabled())
        {
            buttonList.add(this.buttonLock = new GuiButton(8, buttonPosX + 90, buttonPosY + 178, 85, 12, "Ditch Lights: On"));
        }
        else
        {
            buttonList.add(this.buttonLock = new GuiButton(8, buttonPosX + 90, buttonPosY + 178, 85, 12, "Ditch Lights: Off"));
        }
        //endregion DitchLights On/Off
    }

    @Override
    protected void actionPerformed(GuiButton guibutton) {
        if (guibutton.id == 2) {
            if (!loco.parkingBrake && loco.getSpeed() < 10) {
                Traincraft.brakeChannel.sendToServer(new PacketParkingBrake(true, loco.getEntityId()));
                loco.parkingBrake = true;
                loco.isBraking = true;
                guibutton.displayString = "Brake: On";
                this.initGui();
            } else if (loco.getSpeed() < 10) {
                Traincraft.brakeChannel.sendToServer(new PacketParkingBrake(false, loco.getEntityId()));
                loco.parkingBrake = false;
                loco.isBraking = false;
                guibutton.displayString = "Brake: Off";
                this.initGui();
            }
        }

        if (guibutton.id == 3) {
            if (!loco.isNotOwner()) {
                if ((!loco.getTrainLockedFromPacket())) {
                    Traincraft.lockChannel.sendToServer(new PacketSetTrainLockedToClient(true, loco.getEntityId()));
                    loco.locked = true;
                    guibutton.displayString = "Locked";
                    this.initGui();
                } else {
                    Traincraft.lockChannel.sendToServer(new PacketSetTrainLockedToClient(false, loco.getEntityId()));
                    loco.locked = false;
                    guibutton.displayString = "UnLocked";
                    this.initGui();
                }
            } else {
                getEntityPlayer().addChatMessage(new ChatComponentText("You are not the owner"));
            }
        }

        if (guibutton.id == 4) {
            if (loco.isLocoTurnedOn()) {
                if (loco.getSpeed() <= 1) {
                    Traincraft.ignitionChannel.sendToServer(new PacketSetLocoTurnedOn(false));
                    loco.isLocoTurnedOn = false;
                    guibutton.displayString = "Start Engine";

                    // An auto parking brake is implemented here because Brutal tried to implement it in the Locomotive API when you turn off the Train.
                    Traincraft.brakeChannel.sendToServer(new PacketParkingBrake(true, loco.getEntityId()));
                    loco.parkingBrake = true;
                    loco.isBraking = true;
                    this.initGui();
                } else {
                    getEntityPlayer().addChatMessage(new ChatComponentText("Stop before turning it Off!"));
                }
            } else {
                Traincraft.ignitionChannel.sendToServer(new PacketSetLocoTurnedOn(true));
                loco.isLocoTurnedOn = true;
                guibutton.displayString = "Stop Engine";
            }
        } else if (guibutton instanceof GUIButton) {
            ((GUIButton)guibutton).onClick();
        }

        if (guibutton.id == 6) // Lights
        {
            if (loco.isLightsEnabled())
            {
                Traincraft.rollingStockLightsChannel.sendToServer(new PacketRollingStockLights(false, loco.getEntityId()));
                loco.setPacketLights(false);
                guibutton.displayString = "Lights: Off";
            }
            else
            {
                Traincraft.rollingStockLightsChannel.sendToServer(new PacketRollingStockLights(true, loco.getEntityId()));
                loco.setPacketLights(true);
                guibutton.displayString = "Lights: On";
            }
        }

        if (guibutton.id == 7) // Beacon
        {
            if (loco.isBeaconEnabled())
            {
                Traincraft.rollingStockBeaconChannel.sendToServer(new PacketRollingStockBeacon(false, loco.getEntityId()));
                loco.setPacketBeacon(false);
                guibutton.displayString = "Beacon: Off";
            }
            else
            {
                Traincraft.rollingStockBeaconChannel.sendToServer(new PacketRollingStockBeacon(true, loco.getEntityId()));
                loco.setPacketBeacon(true);
                guibutton.displayString = "Beacon: On";
            }
        }

        if (guibutton.id == 8) // DitchLights
        {
            if (loco.isDitchLightsEnabled())
            {
                Traincraft.rollingStockDitchLightsChannel.sendToServer(new PacketRollingStockDitchLights((byte)0, loco.getEntityId()));
                loco.setPacketDitchLightsMode((byte) 0);
                guibutton.displayString = "Ditch Lights: Off";
            }
            else
            {
                Traincraft.rollingStockDitchLightsChannel.sendToServer(new PacketRollingStockDitchLights((byte)1, loco.getEntityId()));
                loco.setPacketDitchLightsMode((byte) 1);
                guibutton.displayString = "Ditch Lights: On";
            }
        }
    }

    private EntityPlayer getEntityPlayer()
    {
        EntityPlayer p = (EntityPlayer) loco.riddenByEntity;
        if (loco.seats.size() != 0 && loco.seats.get(0).getPassenger() instanceof EntityPlayer) {
            p = (EntityPlayer) loco.seats.get(0).getPassenger();
        }

        return p;
    }

    @Override
    protected void drawCreativeTabHoveringText(String str, int t, int g) {
        //int liqui = (dieselInventory.getLiquidAmount() * 50) / dieselInventory.getTankCapacity();

        int textWidth = fontRendererObj.getStringWidth("the GUI, change speed, destroy it.");
        int startX = 90;
        int startY = 5;
        int i4 = 0xf0100010;
        int colour1 = 0x505000ff;
        int colour2 = (colour1 & 0xfefefe) >> 1 | colour1 & 0xff000000;

        drawGradientRect(startX - 3, startY - 4, startX + textWidth + 3, startY + 52, i4, i4);
        drawGradientRect(startX - 4, startY - 3, startX + textWidth + 4, startY + 51, i4, i4);
        drawGradientRect(startX - 3, startY - 3, startX + textWidth + 3, startY + 51, colour1, colour2);
        drawGradientRect(startX - 2, startY - 2, startX + textWidth + 2, startY + 50, i4, i4);

        fontRendererObj.drawStringWithShadow(str, startX, startY, -1);
        fontRendererObj.drawStringWithShadow("only its owner can open", startX, startY + 10, -1);
        fontRendererObj.drawStringWithShadow("the GUI, change speed, destroy it.", startX, startY + 20, -1);
        fontRendererObj.drawStringWithShadow("Current state: " + (loco.getTrainLockedFromPacket() ? "Locked" : "Unlocked"), startX, startY + 30, -1);
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

        fontRendererObj.drawString(loco.getInventoryName(), 39, 7, 0x000000);
        fontRendererObj.drawString(loco.getInventoryName(), 41, 5, 0x000000);
        fontRendererObj.drawString(loco.getInventoryName(), 39, 5, 0x000000);
        fontRendererObj.drawString(loco.getInventoryName(), 41, 7, 0x000000);

        fontRendererObj.drawString(loco.getInventoryName(), 39, 6, 0x000000);
        fontRendererObj.drawString(loco.getInventoryName(), 41, 6, 0x000000);
        fontRendererObj.drawString(loco.getInventoryName(), 40, 7, 0x000000);
        fontRendererObj.drawString(loco.getInventoryName(), 40, 5, 0x000000);
        fontRendererObj.drawString(loco.getInventoryName(), 40, 6, 0xd3a900);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        if (intersectsWith(i, j)) {
            drawCreativeTabHoveringText("When a locomotive is locked,", i, j);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float par3) {
        super.drawScreen(mouseX, mouseY, par3);

        if (loco instanceof SteamTrain) {
            int j = (width - xSize) / 2;
            int k = (height - ySize) / 2;

            if (mouseX > j + 143 && mouseX < j + 161 && mouseY > k + 18 && mouseY < k + 68) {
                drawHoveringText(Collections.singletonList("Water: " + (((SteamTrain) loco).getWater()) + "mb / " + (((SteamTrain) loco).getCartTankCapacity()) + "mb"),
                        mouseX, mouseY, fontRendererObj);
            }
        } else if (loco instanceof DieselTrain) {
            int j = (width - xSize) / 2;
            int k = (height - ySize) / 2;
            if (mouseX > j + 143 && mouseX < j + 161 && mouseY > k + 18 && mouseY < k + 68) {
                if (((DieselTrain) loco).getDiesel() != 0) {
                    drawHoveringText(Collections.singletonList(StatCollector.translateToLocal("fluid.tc:" + ((DieselTrain) loco).getLiquidName()) + " " +
                                    ((DieselTrain) loco).getDiesel() + "mb / " + (((DieselTrain) loco).getCartTankCapacity()) + "mb"),
                            mouseX, mouseY, fontRendererObj);
                } else {
                    drawHoveringText(Collections.singletonList("Fuel: " +
                                    "0mb / " + (((DieselTrain) loco).getCartTankCapacity()) + "mb"),
                            mouseX, mouseY, fontRendererObj);
                }
            }
        }
        for(Object guiButton: buttonList) {
            if (guiButton instanceof GUIButton) {
                ((GUIButton)guiButton).drawText(mouseX-(int)guiLeft,mouseY-(int)guiTop);
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int t, int g) {
        String i = Info.guiPrefix + "gui_loco.png";

        if (loco instanceof ElectricTrain) {
            i = Info.guiPrefix + "gui_tram.png";
        }

        if (loco instanceof SteamTrain) {
            i = Info.guiPrefix + "gui_loco_steam.png";
        }

        if (loco instanceof DieselTrain) {
            i = Info.guiPrefix + "gui_loco_diesel.png";
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, i));
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

        if (loco instanceof SteamTrain) {
            int load = (((SteamTrain) loco).getWater());
            int lo = Math.abs(((load * 50) / (((SteamTrain) loco).getCartTankCapacity())));

            if (((SteamTrain) loco).getLiquidItemID() == LiquidManager.WATER_FILTER.getFluidID()) {
                drawTexturedModalRect(j + 143, (k + 68) - lo, 190, 69 - lo, 18, lo + 1);
            }

            if (loco.getIsFuelled()) {
                int l = loco.getFuelDiv(12);
                drawTexturedModalRect(j + 8, (k + 36 + 12) - l, 176, 12 - l, 14, l + 2);
            }
        } else if (loco instanceof DieselTrain) {
            int load = (((DieselTrain) loco).getDiesel());
            int lo2 = Math.abs(((load * 50) / (((DieselTrain) loco).getCartTankCapacity())));
            drawTexturedModalRect(j + 143, (k + 68) - lo2, 192, 120 - lo2, 18, lo2);

            if (loco.getIsFuelled()) {
                int l = loco.getFuelDiv(12);
                drawTexturedModalRect(j + 10, (k + 36 + 13) - l, 178, 12 - l, 14, l + 2);
            }
        } else {
            for (int i1 = loco.numCargoSlots; i1 < 5; i1++) {
                drawTexturedModalRect(j + 79 + 18 * i1, k + 17, 190, 0, 18, 18);
            }

            for (int j1 = loco.numCargoSlots1; j1 < 5; j1++) {
                drawTexturedModalRect(j + 79 + 18 * j1, k + 35, 190, 0, 18, 18);
            }

            for (int k1 = loco.numCargoSlots2; k1 < 5; k1++) {
                drawTexturedModalRect(j + 79 + 18 * k1, k + 53, 190, 0, 18, 18);
            }

            if (loco.getIsFuelled()) {
                int l = loco.getFuelDiv(12);
                drawTexturedModalRect(j + 8, (k + 36 + 12) - l, 176, 12 - l, 14, l + 2);
            }
        }

        JsonObject guiDetails = new JsonParser().parse(loco.guiDetailsDW()).getAsJsonObject();

        fontRendererObj.drawStringWithShadow("Carts pulled: " + guiDetails. get("cartsPulled"), 1, 10, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Mass pulled: " + guiDetails.get("massPulled") +  " tons", 1, 20, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Speed reduction: " + guiDetails.get("slowDown") + " km/h", 1, 30, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Accel reduction: " + (Math.round(guiDetails.get("accelSlowDown").getAsDouble() * 1000) / 1000), 1, 40, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Brake reduction: " + (Math.round(guiDetails.get("brakeSlowDown").getAsDouble() * 1000) / 1000), 1, 50, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Fuel consumption: " + ((loco.getFuelConsumption() * 0.2) + "").substring(0, Math.min(((loco.getFuelConsumption() * 0.2) + "").length(), 4)) + " mB/s", 1,
                60, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Fuel: " + loco.getFuel(), 1, 70, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Power: " + loco.transportMetricHorsePower() + " Mhp", 1, 80, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("State: " + loco.getState(), 1, 90, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Heat level: " + loco.getOverheatLevel(), 1, 100, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Maximum Speed: " + (loco.getCustomSpeedGUI()) + " km/h" + " (" + (loco.getCustomSpeedGUI() + guiDetails.get("slowDown").getAsFloat()) + "km/h)", 1, 110, 0xFFFFFF);
        fontRendererObj.drawStringWithShadow("Destination: " + (loco.getDestinationGUI()), 1, 120, 0xFFFFFF);
    }
}