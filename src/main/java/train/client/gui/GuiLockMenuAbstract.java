package train.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import train.common.core.handlers.ConfigHandler;
import train.common.entity.TrustedPlayer;
import train.common.library.Info;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 02skaplan
 * <p>Lock and Trusted Players Menu</p>
 * <p>Allows players to lock and unlock a piece of rolling stock and add & remove trusted individuals from using the rolling stock.</p>
 */
@SuppressWarnings("unchecked")
@SideOnly(Side.CLIENT)
public abstract class GuiLockMenuAbstract extends GuiScreen {
    private final EntityPlayer editingPlayer;
    final private int MENU_TEXTURE_WIDTH = 176;
    final private int MENU_TEXTURE_HEIGHT = 222;
    /**
     * X-coordinate of top left of GUI.
     */
    private int GUI_ANCHOR_X;
    /**
     * X-coordinate of top center of GUI.
     */
    private int GUI_ANCHOR_MID_X;
    private int TEXTBOX_LEFT_ANCHOR;
    /**
     * Y-coordinate of top left of GUI.
     */
    private int GUI_ANCHOR_Y;
    private GuiButtonLockMenu lockUnlockButton;
    private GuiButtonLockMenu closeButton;
    protected GuiButtonLockMenu closeAndSavetoAll;
    private GuiButtonLockMenu arrowUp;
    private GuiButtonLockMenu arrowDown;
    private GuiButtonLockMenu copyButton;
    private GuiButtonLockMenu pasteButton;
    public final int MAX_TRUSTEES_ON_PAGE;
    protected final int textBoxWidth;
    protected final int textBoxHeight;
    protected final int textBoxAndButtonWidth;
    private int numberOfActiveTextboxes = 1;
    protected final List<TrustedPlayer> currentTrustees;
    private final List<GuiLockMenuTextField> textFieldList = new ArrayList<>();
    private final int NUMBER_OF_STATIC_BUTTONS = 7;
    private int currentPage;


    /**
     * @author 02skaplan
     */
    public GuiLockMenuAbstract(EntityPlayer editingPlayer) {
        this.editingPlayer = editingPlayer;
        currentTrustees = new ArrayList<>();
        MAX_TRUSTEES_ON_PAGE = 6;
        textBoxWidth = 100;
        textBoxHeight = 20;
        textBoxAndButtonWidth = textBoxWidth + 17 + 5;
        currentPage = 0;
    }

    /**
     * Initial setup for buttons and GUI anchors.
     */
    @Override
    public void initGui() {
        GUI_ANCHOR_MID_X = (this.width) / 2;
        GUI_ANCHOR_Y = (this.height) / 2 - (MENU_TEXTURE_HEIGHT / 2);
        GUI_ANCHOR_X = GUI_ANCHOR_MID_X - (MENU_TEXTURE_WIDTH) / 2;
        TEXTBOX_LEFT_ANCHOR = GUI_ANCHOR_MID_X - (textBoxAndButtonWidth / 2) - 7;
        this.buttonList.clear();
        this.buttonList.add(this.lockUnlockButton = new GuiButtonLockMenu(0, GUI_ANCHOR_MID_X - (17 / 2), GUI_ANCHOR_Y + 10, 17, 25, getLocked() ? GuiButtonLockMenu.Type.LOCKED : GuiButtonLockMenu.Type.UNLOCKED));
        this.buttonList.add(this.closeButton = new GuiButtonLockMenu(1, GUI_ANCHOR_X + MENU_TEXTURE_WIDTH - 22, GUI_ANCHOR_Y + 4, 17, 17, GuiButtonLockMenu.Type.CLOSE));
        this.buttonList.add(this.closeAndSavetoAll = new GuiButtonLockMenu(2, GUI_ANCHOR_X + MENU_TEXTURE_WIDTH - 41, GUI_ANCHOR_Y + 4, 17, 17, GuiButtonLockMenu.Type.SAVETOALL));
        this.buttonList.add(this.arrowUp = new GuiButtonLockMenu(3, GUI_ANCHOR_X + MENU_TEXTURE_WIDTH - 15, GUI_ANCHOR_Y + 95, 7, 22, GuiButtonLockMenu.Type.ARROWUP));
        this.buttonList.add(this.arrowDown = new GuiButtonLockMenu(4, GUI_ANCHOR_X + MENU_TEXTURE_WIDTH - 15, GUI_ANCHOR_Y + 120, 7, 22, GuiButtonLockMenu.Type.ARROWDOWN));

        this.buttonList.add(this.copyButton = new GuiButtonLockMenu(5, GUI_ANCHOR_X + 5, GUI_ANCHOR_Y + 4, 17, 17, GuiButtonLockMenu.Type.COPY));
        this.buttonList.add(this.pasteButton = new GuiButtonLockMenu(6, GUI_ANCHOR_X + 24, GUI_ANCHOR_Y + 4, 17, 17, GuiButtonLockMenu.Type.PASTE));

        textFieldList.clear();
        for (int i = 0; i < MAX_TRUSTEES_ON_PAGE; i ++) { // Add text boxes, line select delete buttons, import current trustees to text boxes.
            this.buttonList.add(new GuiButtonLockMenu(i + NUMBER_OF_STATIC_BUTTONS, TEXTBOX_LEFT_ANCHOR + textBoxWidth + 3, (GUI_ANCHOR_Y + 55 + (i * (textBoxHeight + 5))) + 2, 17, 17, GuiButtonLockMenu.Type.REMOVE));
            textFieldList.add(new GuiLockMenuTextField(i, fontRendererObj, TEXTBOX_LEFT_ANCHOR, (GUI_ANCHOR_Y + 55 + (i * (textBoxHeight + 5))), textBoxWidth, textBoxHeight, this));
            textFieldList.get(i).setCanLoseFocus(true);
            textFieldList.get(i).setMaxStringLength(16);
        }

        for (int i = 0; i < MAX_TRUSTEES_ON_PAGE; i++) { // Add break access buttons (done in separate loop to preserve order of buttonList).
            this.buttonList.add(new GuiButtonLockMenu(i + NUMBER_OF_STATIC_BUTTONS + MAX_TRUSTEES_ON_PAGE, TEXTBOX_LEFT_ANCHOR + textBoxWidth + 22, (GUI_ANCHOR_Y + 55 + (i * (textBoxHeight + 5))) + 2, 17, 17, GuiButtonLockMenu.Type.BREAKACCESSOFF));
        }

        this.updateButtons();
    }

    protected void updateButtons() {
        numberOfActiveTextboxes = Math.min(currentTrustees.size() - MAX_TRUSTEES_ON_PAGE * currentPage + 1, MAX_TRUSTEES_ON_PAGE);
        // Update text fields and break access buttons...
        for (int i = 0; i < MAX_TRUSTEES_ON_PAGE; i ++) {
            if (i + currentPage * MAX_TRUSTEES_ON_PAGE < currentTrustees.size())
                textFieldList.get(i).setText(currentTrustees.get(i + currentPage * MAX_TRUSTEES_ON_PAGE).getDisplayName());
            else
                textFieldList.get(i).setText("");
            textFieldList.get(i).setVisible(i < numberOfActiveTextboxes);
            if (i + currentPage * MAX_TRUSTEES_ON_PAGE < currentTrustees.size()) {
                boolean hasBreakAccess = currentTrustees.get(i + currentPage * MAX_TRUSTEES_ON_PAGE).hasBreakAccess();
                ((GuiButtonLockMenu) this.buttonList.get(i + NUMBER_OF_STATIC_BUTTONS + MAX_TRUSTEES_ON_PAGE)).setType(hasBreakAccess ? GuiButtonLockMenu.Type.BREAKACCESSON : GuiButtonLockMenu.Type.BREAKACCESSOFF, GuiButtonLockMenu.Texture.INACTIVE);
            }
            ((GuiButtonLockMenu) this.buttonList.get(i + NUMBER_OF_STATIC_BUTTONS)).visible = i < numberOfActiveTextboxes;
            ((GuiButtonLockMenu) this.buttonList.get(i + NUMBER_OF_STATIC_BUTTONS)).showButton = i < numberOfActiveTextboxes;
            ((GuiButtonLockMenu) this.buttonList.get(i + NUMBER_OF_STATIC_BUTTONS + MAX_TRUSTEES_ON_PAGE)).visible = i < numberOfActiveTextboxes;
            ((GuiButtonLockMenu) this.buttonList.get(i + NUMBER_OF_STATIC_BUTTONS + MAX_TRUSTEES_ON_PAGE)).showButton = i < numberOfActiveTextboxes;
        }

        // Add an extra text box at the bottom if the current one at the bottom has text in it and the page is not yet full.
        if (!textFieldList.get(numberOfActiveTextboxes - 1).isFocused()) { // Get the last text box on the page...
            if (!textFieldList.get(numberOfActiveTextboxes - 1).getText().equalsIgnoreCase("")) { // If the text box is not empty...
                if (numberOfActiveTextboxes < MAX_TRUSTEES_ON_PAGE) { // Add another text box if there is room on the page.
                    numberOfActiveTextboxes++;
                }
            }
        }

        this.lockUnlockButton.showButton = true;
        this.lockUnlockButton.visible = true;
        this.lockUnlockButton.setType(getLocked() ? GuiButtonLockMenu.Type.LOCKED : GuiButtonLockMenu.Type.UNLOCKED, this.lockUnlockButton.getTexture());
        this.closeButton.showButton = true;
        this.closeButton.visible = true;
        this.closeAndSavetoAll.showButton = true;
        this.closeAndSavetoAll.visible = true;
        this.arrowUp.showButton = currentPage != 0;
        this.arrowUp.visible = this.arrowUp.showButton;
        this.arrowDown.showButton = numberOfActiveTextboxes == MAX_TRUSTEES_ON_PAGE && !textFieldList.get(MAX_TRUSTEES_ON_PAGE - 1).getText().isEmpty();
        this.arrowDown.visible = this.arrowDown.showButton;
        this.copyButton.visible = true;
        this.copyButton.showButton = true;
        this.pasteButton.visible = true;
        this.pasteButton.showButton = true;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float par3) {
        // Draw background.
        mc.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, Info.guiPrefix + "gui_lockmenu.png"));
        this.drawTexturedModalRect(GUI_ANCHOR_X, GUI_ANCHOR_Y, 0, 0, MENU_TEXTURE_WIDTH, MENU_TEXTURE_HEIGHT);
        super.drawScreen(mouseX, mouseY, par3);
        for (int i = 0; i < numberOfActiveTextboxes; i++) {
            textFieldList.get(i).drawTextBox();
        }
        fontRendererObj.drawString(StatCollector.translateToLocal("lockmenu.Title_Trusted_Players.name"), GUI_ANCHOR_MID_X - (fontRendererObj.getStringWidth(StatCollector.translateToLocal("lockmenu.Title_Trusted_Players.name")) / 2), GUI_ANCHOR_Y + 40, -16777216);
        if (lockUnlockButton.getTexture() == GuiButtonLockMenu.Texture.ACTIVE)
            if (getLocked())
                drawHoveringText(Collections.singletonList(StatCollector.translateToLocal("lockmenu.Unlock.name")), mouseX, mouseY, fontRendererObj);
            else
                drawHoveringText(Collections.singletonList(StatCollector.translateToLocal("lockmenu.Lock.name")), mouseX, mouseY, fontRendererObj);
        if (closeButton.getTexture() == GuiButtonLockMenu.Texture.ACTIVE)
            drawHoveringText(Collections.singletonList(StatCollector.translateToLocal("lockmenu.Save and Close.name")), mouseX, mouseY, fontRendererObj);
        if (closeAndSavetoAll.getTexture() == GuiButtonLockMenu.Texture.ACTIVE)
            drawHoveringText(Collections.singletonList(StatCollector.translateToLocal("lockmenu.Save To All Cars in Consist.name")), mouseX, mouseY, fontRendererObj);
    }

    @Override
    protected void actionPerformed(GuiButton clickedButton) {
        if (clickedButton.enabled) {
            editingPlayer.playSound("random.click", 1f, 1f);
            switch (clickedButton.id) {
                case 0:  // Main Lock Button
                    setLocked(!getLocked());
                    updateButtons();
                    break;
                case 1:  // Save and Close Button
                    sendUpdatePacket(false);
                    this.mc.thePlayer.closeScreen();
                    break;
                case 2:
                    sendUpdatePacket(true);
                    this.mc.thePlayer.closeScreen();
                    break;
                case 3:
                case 4:
                    if (clickedButton.id == 3) {
                        currentPage--;
                    } else {
                        currentPage++;
                    }
                    numberOfActiveTextboxes = currentTrustees.isEmpty() ? 1 : Math.min(currentTrustees.size(), MAX_TRUSTEES_ON_PAGE);
                    updateButtons();
                case 5: // Copy
                    StringBuilder concatenatedTrustees = new StringBuilder();
                    for (TrustedPlayer trustee : currentTrustees) {
                        concatenatedTrustees.append(trustee.getDisplayName()).append(",").append(trustee.hasBreakAccess()).append(";");
                    }
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(concatenatedTrustees.toString()), null);
                    break;
                case 6: // Paste
                    try {
                        String fromClipboard = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                        String[] splitTrustees = fromClipboard.split(";");
                        if (splitTrustees.length <= ConfigHandler.MAX_TRUSTEES_ON_PADLOCK) {
                            currentTrustees.clear();
                            String[] splitTrustee;
                            for (String trustee : splitTrustees) {
                                splitTrustee = trustee.split(",");
                                if (splitTrustee.length > 1)
                                    currentTrustees.add(new TrustedPlayer(splitTrustee[0], Boolean.parseBoolean(splitTrustee[1])));
                                else
                                    break;
                            }
                            numberOfActiveTextboxes = currentTrustees.isEmpty() ? 1 : Math.min(currentTrustees.size(), MAX_TRUSTEES_ON_PAGE);
                            updateButtons();
                        }
                    } catch (UnsupportedFlavorException | IOException e) {
                        throw new RuntimeException(e);
                    }
                default:
                    if (clickedButton.id >= NUMBER_OF_STATIC_BUTTONS && clickedButton.id < MAX_TRUSTEES_ON_PAGE + NUMBER_OF_STATIC_BUTTONS) { // Line Select Delete Buttons
                        int trusteeToBeRemoved = (clickedButton.id - NUMBER_OF_STATIC_BUTTONS) + currentPage * MAX_TRUSTEES_ON_PAGE;
                        if (trusteeToBeRemoved < currentTrustees.size())
                            currentTrustees.remove(trusteeToBeRemoved);
                        updateButtons();
                    } else if (clickedButton.id > MAX_TRUSTEES_ON_PAGE + NUMBER_OF_STATIC_BUTTONS - 1) { // Break Access Buttons
                        GuiButtonLockMenu button = ((GuiButtonLockMenu) clickedButton);
                        if (button.getType() == GuiButtonLockMenu.Type.BREAKACCESSOFF)
                            button.setType(GuiButtonLockMenu.Type.BREAKACCESSON, button.getTexture());
                        else
                            button.setType(GuiButtonLockMenu.Type.BREAKACCESSOFF, button.getTexture());
                        updateTrustee((clickedButton.id - MAX_TRUSTEES_ON_PAGE - NUMBER_OF_STATIC_BUTTONS) + currentPage * MAX_TRUSTEES_ON_PAGE);
                    }
            }
        }
    }
    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

    @Override
    public void mouseClicked(int x, int y, int par3) {
        super.mouseClicked(x, y, par3);
        for (GuiTextField textField : textFieldList) {
            textField.mouseClicked(x, y, par3);
        }
    }

    @Override
    public void handleMouseInput() {
        int mouseEvent = Mouse.getEventDWheel();
        if (mouseEvent != 0) {
            if (mouseEvent > 0) { // Scroll up.
                if (currentPage != 0) {
                    currentPage--;
                }
            } else { // Scroll down.
                if (arrowDown.visible) {
                    currentPage++;
                }
            }
            updateButtons();
        }
        super.handleMouseInput();
    }

    @Override
    protected void keyTyped(char eventChar, int eventKey) {
        if (eventKey == 1) { // If ESC...
            this.mc.thePlayer.closeScreen();
        }
        for (int i = 0; i < MAX_TRUSTEES_ON_PAGE; i++) {
            GuiTextField textField = textFieldList.get(i);
            if (textField.isFocused()) {
                textField.textboxKeyTyped(eventChar, eventKey);
                if (eventChar == '\r') {
                    textField.setFocused(false);
                    if (textFieldList.size() > i + 1)
                        textFieldList.get(i + 1).setFocused(true);
                    else {
                        currentPage++;
                        textFieldList.get(0).setFocused(true);
                    }
                    updateButtons();
                }
                break;
            }
        }
    }

    protected List<TrustedPlayer> exportTrustedPlayers() {
        List<TrustedPlayer> newTrustees = new ArrayList<>(currentTrustees);
        for (int i = 0; i < numberOfActiveTextboxes; i++) {
            if (!textFieldList.get(i).getText().isEmpty()) {
                if (i + currentPage * MAX_TRUSTEES_ON_PAGE >= newTrustees.size()) {
                    newTrustees.add(i + currentPage * MAX_TRUSTEES_ON_PAGE, new TrustedPlayer(textFieldList.get(i).getText().trim(), ((GuiButtonLockMenu) this.buttonList.get(i + NUMBER_OF_STATIC_BUTTONS + MAX_TRUSTEES_ON_PAGE)).getType() == GuiButtonLockMenu.Type.BREAKACCESSON));
                } else {
                    newTrustees.set(i + currentPage * MAX_TRUSTEES_ON_PAGE, new TrustedPlayer(textFieldList.get(i).getText().trim(), ((GuiButtonLockMenu) this.buttonList.get(i + NUMBER_OF_STATIC_BUTTONS + MAX_TRUSTEES_ON_PAGE)).getType() == GuiButtonLockMenu.Type.BREAKACCESSON));
                }
            }
        }
        currentTrustees.clear();
        currentTrustees.addAll(newTrustees);
        return newTrustees;
    }

    public void updateTrustee(int trusteeIndex) {
        int indexOnPage = trusteeIndex - currentPage * MAX_TRUSTEES_ON_PAGE;
        if (!textFieldList.get(indexOnPage).getText().isEmpty()) {
            if (trusteeIndex >= currentTrustees.size()) {
                currentTrustees.add(trusteeIndex, new TrustedPlayer(textFieldList.get(indexOnPage).getText().trim(), ((GuiButtonLockMenu) this.buttonList.get(indexOnPage + NUMBER_OF_STATIC_BUTTONS + MAX_TRUSTEES_ON_PAGE)).getType() == GuiButtonLockMenu.Type.BREAKACCESSON));
            } else {
                currentTrustees.set(trusteeIndex, new TrustedPlayer(textFieldList.get(indexOnPage).getText().trim(), ((GuiButtonLockMenu) this.buttonList.get(indexOnPage + NUMBER_OF_STATIC_BUTTONS + MAX_TRUSTEES_ON_PAGE)).getType() == GuiButtonLockMenu.Type.BREAKACCESSON));
            }
        }
    }

    public abstract boolean getLocked();
    public abstract void setLocked(boolean locked);
    public abstract void sendUpdatePacket(boolean propagate);
    public List<GuiLockMenuTextField> getTextFieldList() {
        return textFieldList;
    }
    public int getCurrentPage() { return currentPage; }

}
