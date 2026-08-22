package train.client.gui;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.common.library.Info;

/**
 * @author 02skaplan
 * <p>Class for buttons used for the lock menu.</p>
 */
@SideOnly(Side.CLIENT)
class GuiButtonLockMenu extends GuiButton {
    enum Type {
        LOCKED,
        UNLOCKED,
        CLOSE,
        REMOVE,
        BREAKACCESSON,
        BREAKACCESSOFF,
        SAVETOALL,
        ARROWUP,
        ARROWDOWN,
        COPY,
        PASTE
    }
    enum Texture {
        ACTIVE,
        INACTIVE
    }
    /**
     * if the button has to be drawn drawButton is more than just draw, it makes the button exists or not too
     */
    public boolean showButton;
    /**
     * Starting x-value on texture.
     */
    private int u;
    /**
     * Starting y-value on texture.
     */
    private int v = 0;
    private int TEXTURE_WIDTH;
    private int TEXTURE_HEIGHT;
    private Texture texture;
    private Type type;

    public GuiButtonLockMenu(int buttonID, int x, int y, int xSize, int ySize, Type type) {
        super(buttonID, x, y, xSize, ySize, "");
        this.setType(type, Texture.INACTIVE);
    }

    /**
     * Draws this button to the screen.
     */
    @Override
    public void drawButton(Minecraft mc, int par2, int par3) {
        if (this.visible && showButton) {
            if (par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height) { // If mouse is hovering over...
                this.setType(this.type, Texture.ACTIVE);
            } else {
                this.setType(this.type, Texture.INACTIVE);
            }
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glPushMatrix();
            mc.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, Info.guiPrefix + "gui_lockmenu.png"));
            this.drawTexturedModalRect(this.xPosition, this.yPosition, u, v, TEXTURE_WIDTH, TEXTURE_HEIGHT);
            RenderHelper.disableStandardItemLighting();
            GL11.glPopMatrix();
        }
    }
    public void setType(Type type, Texture texture) {
        this.type = type;
        this.texture = texture;
        switch (type) {
            case LOCKED:
            case UNLOCKED:
            case CLOSE:
            case REMOVE:
                if (texture == Texture.INACTIVE)
                    v = 0;
                else
                    v = 25;
                if (type == Type.LOCKED || type == Type.UNLOCKED) {
                    TEXTURE_WIDTH = 17;
                    TEXTURE_HEIGHT = 25;
                    if (type == Type.LOCKED)
                        u = 176;
                    else
                        u = 193;
                } else {
                    TEXTURE_WIDTH = 17;
                    TEXTURE_HEIGHT = 17;
                    if (type == Type.CLOSE)
                        u = 210;
                    else
                        u = 227;
                }
                break;
            case BREAKACCESSON:
            case BREAKACCESSOFF:
            case SAVETOALL:
                TEXTURE_WIDTH = 17;
                TEXTURE_HEIGHT = 17;
                if (texture == Texture.INACTIVE)
                    v = 50;
                else
                    v = 75;
                if (type == Type.BREAKACCESSON)
                    u = 193;
                else if (type == Type.BREAKACCESSOFF)
                    u = 210;
                else
                    u = 227;
                break;
            case ARROWUP:
            case ARROWDOWN:
            case COPY:
            case PASTE:
                if (texture == Texture.INACTIVE)
                    v = 92;
                else
                    v = 117;
                if (type == Type.ARROWUP || type == Type.ARROWDOWN) {
                    TEXTURE_WIDTH = 7;
                    TEXTURE_HEIGHT = 22;
                    if (type == Type.ARROWUP)
                        u = 176;
                    else
                        u = 192;
                } else {
                    TEXTURE_WIDTH = 17;
                    TEXTURE_HEIGHT = 17;
                    if (type == Type.COPY)
                        u = 210;
                    else
                        u = 227;
                }
        }
    }
    public Texture getTexture() { return this.texture; }
    public Type getType() { return this.type; }
}
