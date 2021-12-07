package ebf.tim.gui;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import depreciated.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SideOnly(Side.CLIENT)
public abstract class GUIContainerNoNEI extends GuiScreen
{
    protected static final ResourceLocation field_147001_a = new ResourceLocation("textures/gui/container/inventory.png");
    /** The X size of the inventory window in pixels. */
    //protected int xSize = 176;
    /** The Y size of the inventory window in pixels. */
    //protected int ySize = 166;
    /** A list of the players inventory slots */
    public Container inventorySlots;
    /** Starting X position for the Gui. Inconsistent use for Gui backgrounds. */
    protected float guiLeft;
    /** Starting Y position for the Gui. Inconsistent use for Gui backgrounds. */
    protected float guiTop;
    private Slot theSlot;
    /** Used when touchscreen is enabled. */
    private Slot clickedSlot;
    /** Used when touchscreen is enabled. */
    private boolean isRightMouseClick;
    /** Used when touchscreen is enabled */
    private ItemStack draggedStack;
    private float field_147011_y;
    private float field_147010_z;
    private Slot returningStackDestSlot;
    private long returningStackTime;
    /** Used when touchscreen is enabled */
    private ItemStack returningStack;
    private Slot field_146985_D;
    private long field_146986_E;
    protected final Set field_147008_s = new HashSet();
    protected boolean field_147007_t;
    private int field_146987_F;
    private int field_146988_G;
    private boolean field_146995_H;
    private int field_146996_I;
    private long field_146997_J;
    private Slot field_146998_K;
    private int field_146992_L;
    private boolean field_146993_M;
    private ItemStack field_146994_N;

    /**  */
    public GUIContainerNoNEI(Container p_i1072_1_)
    {
        this.inventorySlots = p_i1072_1_;
        this.field_146995_H = true;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        super.initGui();
        this.mc.player.openContainer = this.inventorySlots;
        this.guiLeft = (this.width - 176) *0.5f;
        this.guiTop = (this.height - 166) *0.5f;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.drawDefaultBackground();
        this.drawGuiContainerBackgroundLayer(p_73863_3_, p_73863_1_, p_73863_2_);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.guiLeft, this.guiTop, 0.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        this.theSlot = null;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float k1;

        for (int i1 = 0; i1 < this.inventorySlots.inventorySlots.size(); ++i1) {
            Slot slot = (Slot)this.inventorySlots.inventorySlots.get(i1);
            this.func_146977_a(slot);

            if (this.isMouseOverSlot(slot, p_73863_1_, p_73863_2_) && slot.func_111238_b()) {
                this.theSlot = slot;
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                int j1 = slot.xPos;
                k1 = slot.yPos;
                GL11.glColorMask(true, true, true, false);
                this.drawGradientRect(j1, k1, j1 + 16, k1 + 16, -2130706433, -2130706433);
                GL11.glColorMask(true, true, true, true);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
            }
        }

        //Forge: Force lighting to be disabled as there are some issue where lighting would
        //incorrectly be applied based on items that are in the inventory.
        GL11.glDisable(GL11.GL_LIGHTING);
        this.drawGuiContainerForegroundLayer(p_73863_1_, p_73863_2_);
        GL11.glEnable(GL11.GL_LIGHTING);
        InventoryPlayer inventoryplayer = this.mc.player.inventory;
        ItemStack itemstack = this.draggedStack == null ? inventoryplayer.getItemStack() : this.draggedStack;

        if (itemstack != null)
        {
            byte b0 = 8;
            k1 = this.draggedStack == null ? 8 : 16;
            String s = null;

            if (this.draggedStack != null && this.isRightMouseClick)
            {
                itemstack = itemstack.copy();
                itemstack.getCount() = MathHelper.ceiling_float_int((float)itemstack.getCount() / 2.0F);
            }
            else if (this.field_147007_t && this.field_147008_s.size() > 1)
            {
                itemstack = itemstack.copy();
                itemstack.getCount() = this.field_146996_I;

                if (itemstack.getCount() == 0)
                {
                    s = "" + EnumChatFormatting.YELLOW + "0";
                }
            }

            this.drawItemStack(itemstack, p_73863_1_ - this.guiLeft - b0, p_73863_2_ - this.guiTop - k1, s);
        }

        if (this.returningStack != null)
        {
            float f1 = (float)(Minecraft.getSystemTime() - this.returningStackTime) / 100.0F;

            if (f1 >= 1.0F)
            {
                f1 = 1.0F;
                this.returningStack = null;
            }

            k1 = this.returningStackDestSlot.xPos - this.field_147011_y;
            float j2 = this.returningStackDestSlot.yPos - this.field_147010_z;
            this.drawItemStack(this.returningStack,
                    this.field_147011_y + (int)((float)k1 * f1),
                    this.field_147010_z + (int)((float)j2 * f1), (String)null);
        }

        GL11.glPopMatrix();

        if (inventoryplayer.getItemStack() == null && this.theSlot != null && this.theSlot.getHasStack())
        {
            ItemStack itemstack1 = this.theSlot.getStack();
            this.renderToolTip(itemstack1, p_73863_1_, p_73863_2_);
        }

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        RenderHelper.enableStandardItemLighting();
    }

    /**
     * Draws a rectangle with a vertical gradient between the specified colors.
     */
    private void drawGradientRect(float p_73733_1_, float p_73733_2_, float p_73733_3_, float p_73733_4_, int p_73733_5_, int p_73733_6_) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(
                (p_73733_5_ >> 16 & 255) / 255.0F,
                (p_73733_5_ >> 8 & 255) / 255.0F,
                (p_73733_5_ & 255) / 255.0F,
                (p_73733_5_ >> 24 & 255) / 255.0F);
        tessellator.addVertex(p_73733_3_, p_73733_2_, this.zLevel);
        tessellator.addVertex(p_73733_1_, p_73733_2_, this.zLevel);
        tessellator.setColorRGBA_F((p_73733_6_ >> 16 & 255) / 255.0F,
                (p_73733_6_ >> 8 & 255) / 255.0F,
                (p_73733_6_ & 255) / 255.0F,
                (p_73733_6_ >> 24 & 255) / 255.0F);
        tessellator.addVertex(p_73733_1_, p_73733_4_, this.zLevel);
        tessellator.addVertex(p_73733_3_, p_73733_4_, this.zLevel);
        tessellator.draw();
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    private void drawItemStack(ItemStack p_146982_1_, float p_146982_2_, float p_146982_3_, String p_146982_4_)
    {
        GL11.glTranslatef(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        itemRender.zLevel = 200.0F;
        FontRenderer font = null;
        if (p_146982_1_ != null) font = p_146982_1_.getItem().getFontRenderer(p_146982_1_);
        if (font == null) font = fontRenderer;
        itemRender.renderItemAndEffectIntoGUI(font, this.mc.getTextureManager(), p_146982_1_, (int)p_146982_2_, (int)p_146982_3_);
        itemRender.renderItemOverlayIntoGUI(font, this.mc.getTextureManager(), p_146982_1_, (int)p_146982_2_, (int)p_146982_3_ - (this.draggedStack == null ? 0 : 8), p_146982_4_);
        this.zLevel = 0.0F;
        itemRender.zLevel = 0.0F;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {}

    /** draws the background layer for the GuiContainer*/
    protected abstract void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_);

    private void func_146977_a(Slot p_146977_1_)
    {
        int i = p_146977_1_.xPos;
        int j = p_146977_1_.yPos;
        ItemStack itemstack = p_146977_1_.getStack();
        boolean flag = false;
        boolean flag1 = p_146977_1_ == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick;
        ItemStack itemstack1 = this.mc.player.inventory.getItemStack();
        String s = null;

        if (p_146977_1_ == this.clickedSlot && this.draggedStack != null && this.isRightMouseClick && itemstack != null)
        {
            itemstack = itemstack.copy();
            itemstack.setCount(itemstack.getCount()/2);
        }
        else if (this.field_147007_t && this.field_147008_s.contains(p_146977_1_) && itemstack1 != null)
        {
            if (this.field_147008_s.size() == 1)
            {
                return;
            }

            if (Container.canAddItemToSlot(p_146977_1_, itemstack1, true) && this.inventorySlots.canDragIntoSlot(p_146977_1_))
            {
                itemstack = itemstack1.copy();
                flag = true;
                Container.func_94525_a(this.field_147008_s, this.field_146987_F, itemstack, p_146977_1_.getStack() == null ? 0 : p_146977_1_.getStack().getCount());

                if (itemstack.getCount() > itemstack.getMaxStackSize())
                {
                    s = EnumChatFormatting.YELLOW + "" + itemstack.getMaxStackSize();
                    itemstack.setCount(itemstack.getMaxStackSize());
                }

                if (itemstack.getCount() > p_146977_1_.getSlotStackLimit())
                {
                    s = EnumChatFormatting.YELLOW + "" + p_146977_1_.getSlotStackLimit();
                    itemstack.setCount(p_146977_1_.getSlotStackLimit());
                }
            }
            else
            {
                this.field_147008_s.remove(p_146977_1_);
                this.func_146980_g();
            }
        }

        this.zLevel = 100.0F;
        itemRender.zLevel = 100.0F;

        if (itemstack == null)
        {
            IIcon iicon = p_146977_1_.getBackgroundIconIndex();

            if (iicon != null)
            {
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_BLEND); // Forge: Blending needs to be enabled for this.
                this.mc.getTextureManager().bindTexture(TextureMap.locationItemsTexture);
                this.drawTexturedModelRectFromIcon(i, j, iicon, 16, 16);
                GL11.glDisable(GL11.GL_BLEND); // Forge: And clean that up
                GL11.glEnable(GL11.GL_LIGHTING);
                flag1 = true;
            }
        }

        if (!flag1)
        {
            if (flag)
            {
                drawRect(i, j, i + 16, j + 16, -2130706433);
            }

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            itemRender.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.getTextureManager(), itemstack, i, j);
            itemRender.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.getTextureManager(), itemstack, i, j, s);
        }

        itemRender.zLevel = 0.0F;
        this.zLevel = 0.0F;
    }

    private void func_146980_g()
    {
        ItemStack itemstack = this.mc.player.inventory.getItemStack();

        if (itemstack != null && this.field_147007_t)
        {
            this.field_146996_I = itemstack.getCount();
            ItemStack itemstack1;
            int i;

            for (Iterator iterator = this.field_147008_s.iterator(); iterator.hasNext(); this.field_146996_I -= itemstack1.getCount() - i)
            {
                Slot slot = (Slot)iterator.next();
                itemstack1 = itemstack.copy();
                i = slot.getStack() == null ? 0 : slot.getStack().getCount();
                Container.func_94525_a(this.field_147008_s, this.field_146987_F, itemstack1, i);

                if (itemstack1.getCount() > itemstack1.getMaxStackSize())
                {
                    itemstack1.setCount(itemstack1.getMaxStackSize());
                }

                if (itemstack1.getCount() > slot.getSlotStackLimit())
                {
                    itemstack1.setCount(slot.getSlotStackLimit());
                }
            }
        }
    }

    /**
     * Returns the slot at the given coordinates or null if there is none.
     */
    private Slot getSlotAtPosition(int p_146975_1_, int p_146975_2_)
    {
        for (int k = 0; k < this.inventorySlots.inventorySlots.size(); ++k)
        {
            Slot slot = (Slot)this.inventorySlots.inventorySlots.get(k);

            if (this.isMouseOverSlot(slot, p_146975_1_, p_146975_2_))
            {
                return slot;
            }
        }

        return null;
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        boolean flag = p_73864_3_ == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100;
        Slot slot = this.getSlotAtPosition(p_73864_1_, p_73864_2_);
        long l = Minecraft.getSystemTime();
        this.field_146993_M = this.field_146998_K == slot && l - this.field_146997_J < 250L && this.field_146992_L == p_73864_3_;
        this.field_146995_H = false;

        if (p_73864_3_ == 0 || p_73864_3_ == 1 || flag)
        {
            float i1 = this.guiLeft;
            float j1 = this.guiTop;
            boolean flag1 = p_73864_1_ < i1 || p_73864_2_ < j1 || p_73864_1_ >= i1 + 176 || p_73864_2_ >= j1 + 166;
            int k1 = -1;

            if (slot != null)
            {
                k1 = slot.slotNumber;
            }

            if (flag1)
            {
                k1 = -999;
            }

            if (this.mc.gameSettings.touchscreen && flag1 && this.mc.player.inventory.getItemStack() == null)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
                return;
            }

            if (k1 != -1)
            {
                if (this.mc.gameSettings.touchscreen)
                {
                    if (slot != null && slot.getHasStack())
                    {
                        this.clickedSlot = slot;
                        this.draggedStack = null;
                        this.isRightMouseClick = p_73864_3_ == 1;
                    }
                    else
                    {
                        this.clickedSlot = null;
                    }
                }
                else if (!this.field_147007_t)
                {
                    if (this.mc.player.inventory.getItemStack() == null)
                    {
                        if (p_73864_3_ == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100)
                        {
                            this.handleMouseClick(slot, k1, p_73864_3_, 3);
                        }
                        else
                        {
                            boolean flag2 = k1 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
                            byte b0 = 0;

                            if (flag2)
                            {
                                this.field_146994_N = slot != null && slot.getHasStack() ? slot.getStack() : null;
                                b0 = 1;
                            }
                            else if (k1 == -999)
                            {
                                b0 = 4;
                            }

                            this.handleMouseClick(slot, k1, p_73864_3_, b0);
                        }

                        this.field_146995_H = true;
                    }
                    else
                    {
                        this.field_147007_t = true;
                        this.field_146988_G = p_73864_3_;
                        this.field_147008_s.clear();

                        if (p_73864_3_ == 0)
                        {
                            this.field_146987_F = 0;
                        }
                        else if (p_73864_3_ == 1)
                        {
                            this.field_146987_F = 1;
                        }
                    }
                }
            }
        }

        this.field_146998_K = slot;
        this.field_146997_J = l;
        this.field_146992_L = p_73864_3_;
    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY,
     * lastButtonClicked & timeSinceMouseClick.
     */
    protected void mouseClickMove(int p_146273_1_, int p_146273_2_, int p_146273_3_, long p_146273_4_)
    {
        Slot slot = this.getSlotAtPosition(p_146273_1_, p_146273_2_);
        ItemStack itemstack = this.mc.player.inventory.getItemStack();

        if (this.clickedSlot != null && this.mc.gameSettings.touchscreen)
        {
            if (p_146273_3_ == 0 || p_146273_3_ == 1)
            {
                if (this.draggedStack == null)
                {
                    if (slot != this.clickedSlot)
                    {
                        this.draggedStack = this.clickedSlot.getStack().copy();
                    }
                }
                else if (this.draggedStack.getCount() > 1 && slot != null && Container.canAddItemToSlot(slot, this.draggedStack, false))
                {
                    long i1 = Minecraft.getSystemTime();

                    if (this.field_146985_D == slot)
                    {
                        if (i1 - this.field_146986_E > 500L)
                        {
                            this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
                            this.handleMouseClick(slot, slot.slotNumber, 1, 0);
                            this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
                            this.field_146986_E = i1 + 750L;
                            --this.draggedStack.getCount();
                        }
                    }
                    else
                    {
                        this.field_146985_D = slot;
                        this.field_146986_E = i1;
                    }
                }
            }
        }
        else if (this.field_147007_t && slot != null && itemstack != null && itemstack.getCount() > this.field_147008_s.size() && Container.canAddItemToSlot(slot, itemstack, true) && slot.isItemValid(itemstack) && this.inventorySlots.canDragIntoSlot(slot))
        {
            this.field_147008_s.add(slot);
            this.func_146980_g();
        }
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    protected void mouseReleased(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
        super.mouseReleased(p_146286_1_, p_146286_2_, p_146286_3_); //Forge, Call parent to release buttons
        Slot slot = this.getSlotAtPosition(p_146286_1_, p_146286_2_);
        float l = this.guiLeft;
        float i1 = this.guiTop;
        boolean flag = p_146286_1_ < l || p_146286_2_ < i1 || p_146286_1_ >= l + 176 || p_146286_2_ >= i1 + 166;
        int j1 = -1;

        if (slot != null) {
            j1 = slot.slotNumber;
        }

        if (flag) {
            j1 = -999;
        }

        Slot slot1;
        Iterator iterator;

        if (this.field_146993_M && slot != null && p_146286_3_ == 0 && this.inventorySlots.canMergeSlot(null, slot)) {
            if (isShiftKeyDown()) {
                if (slot.inventory != null && this.field_146994_N != null) {
                    iterator = this.inventorySlots.inventorySlots.iterator();

                    while (iterator.hasNext()) {
                        slot1 = (Slot)iterator.next();

                        if (slot1 != null && slot1.canTakeStack(this.mc.player) && slot1.getHasStack() && slot1.inventory == slot.inventory && Container.canAddItemToSlot(slot1, this.field_146994_N, true)) {
                            this.handleMouseClick(slot1, slot1.slotNumber, p_146286_3_, 1);
                        }
                    }
                }
            } else {
                this.handleMouseClick(slot, j1, p_146286_3_, 6);
            }

            this.field_146993_M = false;
            this.field_146997_J = 0L;
        } else {
            if (this.field_147007_t && this.field_146988_G != p_146286_3_) {
                this.field_147007_t = false;
                this.field_147008_s.clear();
                this.field_146995_H = true;
                return;
            }

            if (this.field_146995_H) {
                this.field_146995_H = false;
                return;
            }

            boolean flag1;

            if (this.clickedSlot != null && this.mc.gameSettings.touchscreen) {
                if (p_146286_3_ == 0 || p_146286_3_ == 1) {
                    if (this.draggedStack == null && slot != this.clickedSlot) {
                        this.draggedStack = this.clickedSlot.getStack();
                    }

                    flag1 = Container.canAddItemToSlot(slot, this.draggedStack, false);

                    if (j1 != -1 && this.draggedStack != null && flag1) {
                        this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, p_146286_3_, 0);
                        this.handleMouseClick(slot, j1, 0, 0);

                        if (this.mc.player.inventory.getItemStack() != ItemStack.EMPTY) {
                            this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, p_146286_3_, 0);
                            this.field_147011_y = p_146286_1_ - l;
                            this.field_147010_z = p_146286_2_ - i1;
                            this.returningStackDestSlot = this.clickedSlot;
                            this.returningStack = this.draggedStack;
                            this.returningStackTime = Minecraft.getSystemTime();
                        } else {
                            this.returningStack = null;
                        }
                    }
                    else if (this.draggedStack != null) {
                        this.field_147011_y = p_146286_1_ - l;
                        this.field_147010_z = p_146286_2_ - i1;
                        this.returningStackDestSlot = this.clickedSlot;
                        this.returningStack = this.draggedStack;
                        this.returningStackTime = Minecraft.getSystemTime();
                    }

                    this.draggedStack = null;
                    this.clickedSlot = null;
                }
            }
            else if (this.field_147007_t && !this.field_147008_s.isEmpty()) {
                this.handleMouseClick((Slot)null, -999, Container.getQuickcraftMask(0, this.field_146987_F), 5);
                iterator = this.field_147008_s.iterator();

                while (iterator.hasNext())
                {
                    slot1 = (Slot)iterator.next();
                    this.handleMouseClick(slot1, slot1.slotNumber, Container.getQuickcraftMask(1, this.field_146987_F), 5);
                }

                this.handleMouseClick((Slot)null, -999, Container.getQuickcraftMask(2, this.field_146987_F), 5);
            }
            else if (this.mc.player.inventory.getItemStack() != ItemStack.EMPTY) {
                if (p_146286_3_ == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
                    this.handleMouseClick(slot, j1, p_146286_3_, 3);
                } else {
                    flag1 = j1 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));

                    if (flag1) {
                        this.field_146994_N = slot != null && slot.getHasStack() ? slot.getStack() : null;
                    }

                    this.handleMouseClick(slot, j1, p_146286_3_, flag1 ? 1 : 0);
                }
            }
        }

        if (this.mc.player.inventory.getItemStack() == null) {
            this.field_146997_J = 0L;
        }

        this.field_147007_t = false;
    }

    /**
     * Returns if the passed mouse position is over the specified slot.
     */
    private boolean isMouseOverSlot(Slot p_146981_1_, int p_146981_2_, int p_146981_3_) {
        return this.func_146978_c(p_146981_1_.xPos, p_146981_1_.yPos, 16, 16, p_146981_2_, p_146981_3_);
    }

    /** determines whether the passed mouse position is over the specified slot */
    protected boolean func_146978_c(int p_146978_1_, int p_146978_2_, int p_146978_3_, int p_146978_4_, int p_146978_5_, int p_146978_6_)
    {
        float k1 = this.guiLeft;
        float l1 = this.guiTop;
        p_146978_5_ -= k1;
        p_146978_6_ -= l1;
        return p_146978_5_ >= p_146978_1_ - 1 && p_146978_5_ < p_146978_1_ + p_146978_3_ + 1 && p_146978_6_ >= p_146978_2_ - 1 && p_146978_6_ < p_146978_2_ + p_146978_4_ + 1;
    }

    protected void handleMouseClick(Slot p_146984_1_, int p_146984_2_, int p_146984_3_, int p_146984_4_) {
        if (p_146984_1_ != null) {
            p_146984_2_ = p_146984_1_.slotNumber;
        }

        this.mc.playerController.windowClick(this.inventorySlots.windowId, p_146984_2_, p_146984_3_, p_146984_4_, this.mc.player);
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
        if (p_73869_2_ == 1 || p_73869_2_ == this.mc.gameSettings.keyBindInventory.getKeyCode())
        {
            this.mc.player.closeScreen();
        }

        this.checkHotbarKeys(p_73869_2_);

        if (this.theSlot != null && this.theSlot.getHasStack())
        {
            if (p_73869_2_ == this.mc.gameSettings.keyBindPickBlock.getKeyCode())
            {
                this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, 0, 3);
            }
            else if (p_73869_2_ == this.mc.gameSettings.keyBindDrop.getKeyCode())
            {
                this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, isCtrlKeyDown() ? 1 : 0, 4);
            }
        }
    }

    /**
     * This function is what controls the hotbar shortcut check when you press a number key when hovering a stack.
     */
    protected boolean checkHotbarKeys(int p_146983_1_)
    {
        if (this.mc.player.inventory.getItemStack() == null && this.theSlot != null)
        {
            for (int j = 0; j < 9; ++j)
            {
                if (p_146983_1_ == this.mc.gameSettings.keyBindsHotbar[j].getKeyCode())
                {
                    this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, j, 2);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        if (this.mc.player != null)
        {
            this.inventorySlots.onContainerClosed(this.mc.player);
        }
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();

        if (!this.mc.player.isEntityAlive() || this.mc.player.isDead)
        {
            this.mc.player.closeScreen();
        }
    }

}
