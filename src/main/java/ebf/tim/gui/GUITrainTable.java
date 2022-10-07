package ebf.tim.gui;

import ebf.tim.TrainsInMotion;
import ebf.tim.blocks.TileEntityStorage;
import ebf.tim.networking.PacketCraftingPage;
import ebf.tim.utility.*;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Train crafting table GUI</h1>
 * @author Eternal Blue Flame
 * @author ComputerButter
 */
public class GUITrainTable extends GuiContainer {
    private static List<ItemStack> slots = new ArrayList<>();
    private static List<ItemStack> overlays = new ArrayList<>();
    private static List<Integer> slotx = new ArrayList<>();
    private static List<Integer> sloty = new ArrayList<>();
    private static int xCoord, yCoord, zCoord, dimension;
    private static final ResourceLocation[] asmTableGUIs = { //hardcoded modID to avoid having to import traincraft.
            new ResourceLocation("traincraft", "textures/gui/gui_tierI_ironAge.png"),
            new ResourceLocation("traincraft", "textures/gui/gui_tierII_steelAge.png"),
            new ResourceLocation("traincraft", "textures/gui/gui_tierIII_advancedAge.png")
    };

    private String hostname;
    /**a reference to the player that opened the GUI.*/
    private EntityPlayer player;

    public GUITrainTable(InventoryPlayer inventoryPlayer, TileEntity te) {
        super(new TransportSlotManager(inventoryPlayer, (TileEntityStorage) te));
        hostname=te.getBlockType().getTranslationKey();
        xCoord=te.getPos().getX();yCoord=te.getPos().getY();zCoord=te.getPos().getZ();dimension=te.getWorld().provider.getDimension();
        player = inventoryPlayer.player;

        if (CommonProxy.isTraincraft && hostname.startsWith("tile.block.traintabletier")) {
            this.ySize = 256;
        }
    }

    @Override
    public void initGui() {
        super.initGui();

        buttonList =new ArrayList();

        //adjust button position for TC vs TiM traintable
        int[] upButtonCoord = {145, 126};
        int[] downButtonCoord = {145, 144};
        if (hostname.equals("tile.block.traintable")) {
            upButtonCoord = new int[]{105, 34};
            downButtonCoord = new int[]{141, 34};
        }

        if (hostname.equals("tile.block.traintable")) {
            this.buttonList.add(new GUIButton(guiLeft + upButtonCoord[0], guiTop + upButtonCoord[1], 18, 18, "<<") {
                @Override
                public String getHoverText() {
                    return "Previous Page";
                }

                @Override
                public void onClick() {
                    TrainsInMotion.keyChannel.sendToServer(new PacketCraftingPage(false, xCoord, yCoord, zCoord, dimension));
                }

                @Override
                public FontRenderer getFont(){return fontRenderer;}
            });

            this.buttonList.add(new GUIButton(this.guiLeft + downButtonCoord[0], this.guiTop + downButtonCoord[1], 18, 18, ">>") {
                @Override
                public String getHoverText() {
                    return "Next Page";
                }

                @Override
                public void onClick() {
                    TrainsInMotion.keyChannel.sendToServer(new PacketCraftingPage(true, xCoord, yCoord, zCoord, dimension));
                }

                @Override
                public FontRenderer getFont(){return fontRenderer;}
            });
        }
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        if (!CommonProxy.isTraincraft || hostname.equals("tile.block.traintable")) {
            this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
        } else {
            if (hostname.startsWith("tile.block.traintable")) {
                //assembly table and traincraft
                this.fontRenderer.drawString(I18n.format("container.inventory"), 8, this.ySize - 92, 4210752);
                this.fontRenderer.drawString(I18n.format(hostname + ".name"), 8, 5, 12241200);
                this.fontRenderer.drawString(I18n.format("container.storage"), 8, 118, 4210752);
                this.fontRenderer.drawString(I18n.format("container.output"), 90, 118, 12241200);
            }
        }

        //draw button hover text
        for (Object b : buttonList){
            if(b instanceof GUIButton) {
                ((GUIButton) b).drawText(p_146979_1_, p_146979_2_);
            }
        }
    }
    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseY) {
        GL11.glPushMatrix();

        if(hostname.equals("tile.block.railtable")){

            ClientUtil.drawTextOutlined(fontRenderer,"Rail", guiLeft+8, guiTop+2,0xffffff);
            ClientUtil.drawTextOutlined(fontRenderer,"Ties", guiLeft+6, guiTop+24,0xffffff);
            ClientUtil.drawTextOutlined(fontRenderer,"Ballast", guiLeft-8, guiTop+41,0xffffff);

            ClientUtil.drawTextOutlined(fontRenderer,"Old Shape", guiLeft+108, guiTop+18,0xffffff);

            ClientUtil.drawTextOutlined(fontRenderer,"Output", guiLeft+116, guiTop+53,0xffffff);

            ClientUtil.drawTextOutlined(fontRenderer,"Unused", guiLeft+50, guiTop-4,0xffffff);
            ClientUtil.drawTextOutlined(fontRenderer,"Unused", guiLeft+50, guiTop+46,0xffffff);


            slots = new ArrayList<>();
            overlays = new ArrayList<>();
            slotx = new ArrayList<>();
            sloty = new ArrayList<>();
            for (ItemStackSlot s : ((TileEntityStorage)((TransportSlotManager)this.inventorySlots).hostInventory).inventory) {
                slots.add(s.getStack());
                overlays.add(s.getOverlay());
                slotx.add(s.xPos);
                sloty.add(s.yPos);

            }

            GL11.glPushMatrix();
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glColor4f(1,1,1,1);
            GL11.glEnable(GL11.GL_BLEND);
            ClientUtil.drawSlots(slots,overlays, slotx,sloty,mouseX,mouseY,guiLeft,guiTop,itemRender);



            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            mc.getTextureManager().bindTexture(ClientUtil.vanillaChest);
            //draw the player inventory and toolbar background.
            ClientUtil.drawTexturedRect(guiLeft, guiTop+64, 0, 0, 176,  16);//top
            ClientUtil.drawTexturedRect(guiLeft,   guiTop+78, 0, 134, 176, 88);//actual inventory
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();

        } else if (hostname.startsWith("tile.block.traintable")){
            if (!CommonProxy.isTraincraft || hostname.equals("tile.block.traintable")) { //TiM stuff
                this.mc.getTextureManager().bindTexture(ClientUtil.craftingTableGuiTextures);
                this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.xSize, this.ySize);

                slots = new ArrayList<>();
                overlays = new ArrayList<>();
                slotx = new ArrayList<>();
                sloty = new ArrayList<>();
                for (ItemStackSlot s : ((TileEntityStorage) ((TransportSlotManager) this.inventorySlots).hostInventory).inventory) {
                    slots.add(s.getStack());
                    overlays.add(s.getOverlay());
                    slotx.add(s.xPos);
                    sloty.add(s.yPos);

                }

                GL11.glPushMatrix();
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glColor4f(1, 1, 1, 1);
                GL11.glEnable(GL11.GL_BLEND);
                ClientUtil.drawSlots(slots, overlays, slotx, sloty, mouseX, mouseY, guiLeft, guiTop, itemRender);


                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glPopMatrix();
            } else {
                //traincraft assembly tables gui.
                //No need to generate the slot backgrounds because texture already has it.
                //TODO: fancy itemSlot borders and glowing

                switch (hostname) {
                    case "tile.block.traintabletier1":
                        this.mc.getTextureManager().bindTexture(asmTableGUIs[0]);
                        break;
                    case "tile.block.traintabletier2":
                        this.mc.getTextureManager().bindTexture(asmTableGUIs[1]);
                        break;
                    case "tile.block.traintabletier3":
                        this.mc.getTextureManager().bindTexture(asmTableGUIs[2]);
                        break;
                    default:
                        this.mc.getTextureManager().bindTexture(asmTableGUIs[0]);
                        DebugUtil.println("Block comparison failed, defaulting to original");
                        break;
                }
                this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
            }

            //disable or enable buttons depending on pages
            if (((TileEntityStorage) ((TransportSlotManager) this.inventorySlots).hostInventory).pages > 1) {
                for (Object button : buttonList) {
                    if (button instanceof GuiButton) {
                        ((GuiButton) button).visible = true;
                    }
                }
            } else {
                for (Object button : buttonList) {
                    if (button instanceof GuiButton) {
                        ((GuiButton) button).visible = false;
                    }
                }
            }

        } else {
            this.mc.getTextureManager().bindTexture(ClientUtil.craftingTableGuiTextures);
            this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
        }
        GL11.glPopMatrix();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button instanceof GUIButton) {
            ((GUIButton) button).onClick();
        }
    }


    @Override
    protected void handleMouseClick(Slot p_146984_1_, int p_146984_2_, int p_146984_3_, ClickType clickType) {
        if (p_146984_1_ != null) {
            p_146984_2_ = p_146984_1_.slotNumber;
        }

        if (clickType == ClickType.THROW){ //todo: what are the 1.7.10 clicktypes
            clickType = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) ? ClickType.QUICK_MOVE ://cover shift click
                    player.inventory.getItemStack() != ItemStack.EMPTY ? ClickType.THROW : //cover if the cursor is carrying an item
                            (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL))?ClickType.CLONE://cover CTRL clicking
                                    ClickType.PICKUP;//cover everything else
        }
        this.mc.playerController.windowClick(this.inventorySlots.windowId, p_146984_2_, p_146984_3_, clickType, this.mc.player);
    }

}
