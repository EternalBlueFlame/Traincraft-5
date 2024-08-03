package ebf.tim.gui;

import ebf.tim.entities.EntitySeat;
import ebf.tim.networking.PacketSeatUpdate;
import ebf.tim.utility.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import train.common.Traincraft;
import train.common.api.EntityRollingStock;

import javax.vecmath.Vector2f;
import java.util.*;


/**
 * <h1>Transport GUI</h1>
 * used to draw the GUI for choosing a seat while in a piece of rollingstock (get here from a button in the normal UI).
 * @author broscolotos
 */
public class GUISeatManager extends GuiScreen {
    public EntityRollingStock entity;

    public ArrayList<Integer> filledSeatIDs = new ArrayList<>();
    public EntitySeat currentSeat;

    public int lastClickTick = 0;

    int guiScreen=0;
    public static int guiTop;
    public static int guiLeft;

    public ArrayList<Vector2f> locations;

    public GUISeatManager(EntityRollingStock transport) {
        entity=transport;
    }

    @Override
    public void initGui() {
        if(entity !=null && entity.getRiderOffsets().length > 0) {

        }
    }
    @Override
    public void updateScreen() {}

    public static int percentTop(int value){return (int)(guiTop*(value*0.01f));}
    public static int percentLeft(int value){return (int)(guiLeft*(value*0.01f));}

    @Override
    public void drawScreen(int parWidth, int parHeight, float p_73863_3_) {
        this.drawDefaultBackground();
        super.drawScreen(parWidth, parHeight, p_73863_3_);
        locations = new ArrayList<>();
        guiLeft=new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight).getScaledWidth();
        guiTop=new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight).getScaledHeight();

        if (entity.getRiderOffsets().length==0 || entity.getRiderOffsets() == null){
            return;
        }
        for(EntitySeat seat : entity.seats) {
            if (seat.getPassenger() != null) {
                if (seat.getPassenger() == Minecraft.getMinecraft().thePlayer) {
                    currentSeat = seat;
                }
                filledSeatIDs.add(seat.getEntityId());
            }
        }
        if(filledSeatIDs.size() == entity.seats.size()) { //remove after GUI is sorted out
            return;
        }
        switch(guiScreen) {
            case 0:{defineButtons();guiSeatManager();break;}
        }
    }
    @Override
    public boolean doesGuiPauseGame() {return false;}

    public void defineButtons(){
        switch (guiScreen){
            case 0:{
                buttonList =new ArrayList();
                int column = 0;
                int activeRow = 0;
                for (int i=0;i<entity.getRiderOffsets().length; i++) {
                    if (percentTop(25) + (28 * activeRow + 1) - 10 > percentTop(60)) {
                        column++;
                        activeRow = 0;
                    }
                    activeRow++;
                }
                int totalColumns = column;
                column = 0;
                activeRow =0;
                for (int i=0;i<entity.getRiderOffsets().length; i++) {
                    if (percentTop(25)+(28*activeRow+1)-10>percentTop(60)) {
                        column++;
                        activeRow=0;
                    }
                    int width = fontRendererObj.getStringWidth("Empty Seat") + 6;
                    if (entity.seats.get(i).getPassenger() instanceof EntityPlayer) {

                        if (fontRendererObj.getStringWidth(entity.seats.get(i).getPassenger().getCommandSenderName()) > width) {
                            width = fontRendererObj.getStringWidth(entity.seats.get(i).getPassenger().getCommandSenderName()) + 7;
                            if(width%2 !=0)
                                width++;
                        }
                        buttonList.add(
                                new GUIButton(percentLeft(50)-60-(totalColumns/2)+(column*120)-(width/2), percentTop(25)+(28*activeRow+1)-11, width,20,entity.seats.get(i).getPassenger().getCommandSenderName()) {
                                    @Override
                                    public String getHoverText() {
                                        return "Seat is currently occupied";
                                    }
                                    @Override
                                    public void onClick() {}
                                    @Override
                                    public FontRenderer getFont(){return fontRendererObj;}
                                }
                        );
                    } else { //TODO: add an else if for things that aren't players. this becomes important for stuff like stock cars or putting villagers in player seats
                        buttonList.add(
                                new GUIButton( percentLeft(50)-60-(totalColumns/2)+(column*120)-(width/2), percentTop(25)+(28*activeRow+1)-11, width,20,"Empty Seat") {
                                    @Override
                                    public String getHoverText() {
                                        return "Seat is currently Empty";
                                    }
                                    @Override
                                    public void onClick() {
                                        if (lastClickTick+5 < entity.ticksExisted) {
                                            Traincraft.updateChannel.sendToServer(new PacketSeatUpdate(entity.getEntityId(),currentSeat.getPassenger().getEntityId(),entity.seats.indexOf(currentSeat),buttonList.indexOf(this),entity.dimension));
                                            lastClickTick = entity.ticksExisted;
                                        } else {
                                            System.out.println("Too fast");
                                        }
                                    }
                                    @Override
                                    public FontRenderer getFont(){return fontRendererObj;}
                                }
                        );
                    }
                    locations.add(new Vector2f(percentLeft(50)-88-(totalColumns/2)+(column*120)-(width/2),percentTop(25)+(28*activeRow+1)-13));
                    activeRow++;
                }
                buttonList.add(
                        new GUIButton( percentLeft(50)-((fontRendererObj.getStringWidth("Close")+7)/2), percentTop(75)-10, fontRendererObj.getStringWidth("Close")+7,20,"Close") {
                            @Override
                            public String getHoverText() {
                                return "Close Inventory";
                            }
                            @Override
                            public void onClick() {
                                mc.displayGuiScreen(null);
                            }
                            @Override
                            public FontRenderer getFont(){return fontRendererObj;}
                        }
                );
                break;
            }
        }
    }




    public void guiSeatManager(){
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glPushMatrix();
        mc.getTextureManager().bindTexture(ClientUtil.vanillaInventory);
        for(Vector2f pos : locations) {
            ClientUtil.drawTexturedRect(pos.x-3, pos.y-3, 54, 51, 30, 30, 20, 20);
        }

        for(EntitySeat seat: entity.seats) {
            if (seat.getPassenger() instanceof AbstractClientPlayer) {
                mc.getTextureManager().bindTexture(((AbstractClientPlayer) seat.getPassenger()).getLocationSkin());
                ClientUtil.drawTexturedRect(locations.get(entity.seats.indexOf(seat)).x, locations.get(entity.seats.indexOf(seat)).y, 32, 64, 24, 24, 32, 64);
            }
        }
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
    }
}
