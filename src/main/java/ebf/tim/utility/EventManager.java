package ebf.tim.utility;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.TrainsInMotion;
import ebf.tim.blocks.RailTileEntity;
import ebf.tim.entities.EntitySeat;
import ebf.tim.entities.GenericRailTransport;
import ebf.tim.networking.PacketInteract;
import ebf.tim.registry.TiMGenericRegistry;
import fexcraft.tmt.slim.Tessellator;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.world.ChunkEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>event management</h1>
 * used to manage specific events that can't be predicted, like player key presses.
 * @author Eternal Blue Flame
 */
public class EventManager {

    private static List<GenericRailTransport> stock;
    private static GenericRailTransport selected=null, lastSelected=null;
    private static int holdTimer=0;
    private static boolean inited=false;
    /**
     * <h2>Keybind management</h2>
     * manages key pressed or released, since 1.7.10 has no direct support for key released we have to do it directly through LWJGL.
     * Most cases just send a packet to manage things
     * @see PacketInteract
     *
     * Credit to Ferdinand for help with this function.
     *
     * @param event the event of a key being pressed on client.
     */
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientKeyPress(InputEvent.KeyInputEvent event) {
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        if (player.ridingEntity instanceof EntitySeat) {
            //for lamp
            if (ClientProxy.KeyLamp.getIsKeyPressed()) {
                GenericRailTransport parent = (GenericRailTransport) player.worldObj.getEntityByID(((EntitySeat) player.ridingEntity).parentId);
                TrainsInMotion.keyChannel.sendToServer(new PacketInteract(0,parent.getEntityId()));
                parent.setBoolean(GenericRailTransport.boolValues.LAMP, !parent.getBoolean(GenericRailTransport.boolValues.LAMP));
            }
            //for inventory
            if (ClientProxy.KeyInventory.getIsKeyPressed()) {
                TrainsInMotion.keyChannel.sendToServer(new PacketInteract(1, ((EntitySeat) player.ridingEntity).parentId));
            }
            if (ClientProxy.KeyLamp.getIsKeyPressed()) {
                TrainsInMotion.keyChannel.sendToServer(new PacketInteract(5, ((EntitySeat) player.ridingEntity).parentId));
            }
            if (((EntitySeat) player.ridingEntity).isLocoSeat()) {
                //for speed change
                if (FMLClientHandler.instance().getClient().gameSettings.keyBindForward.getIsKeyPressed()) {
                    //dont send if controls are TC mode
                    if (holdTimer<15 && ClientProxy.controls!=1){
                        TrainsInMotion.keyChannel.sendToServer(new PacketInteract(2, ((EntitySeat) player.ridingEntity).parentId));
                    }
                    //if speed is in TC mode and going backwards, reset speed.
                    if(((GenericRailTransport) player.worldObj.getEntityByID(((EntitySeat) player.ridingEntity).parentId)).getAccelerator()>6) {
                        TrainsInMotion.keyChannel.sendToServer(new PacketInteract(16, ((EntitySeat) player.ridingEntity).parentId));
                    }
                } else if (FMLClientHandler.instance().getClient().gameSettings.keyBindBack.getIsKeyPressed()) {
                    //dont send if controls are TC mode
                    if (holdTimer<15 && ClientProxy.controls!=1){
                        TrainsInMotion.keyChannel.sendToServer(new PacketInteract(3, ((EntitySeat) player.ridingEntity).parentId));
                    }
                    //if speed is in TC mode and going forwards, reset speed.
                    if(((GenericRailTransport) player.worldObj.getEntityByID(((EntitySeat) player.ridingEntity).parentId)).getAccelerator()<-6) {
                        TrainsInMotion.keyChannel.sendToServer(new PacketInteract(16, ((EntitySeat) player.ridingEntity).parentId));
                    }
                } else if (ClientProxy.KeyHorn.getIsKeyPressed()){
                    TrainsInMotion.keyChannel.sendToServer(new PacketInteract(9, ((EntitySeat) player.ridingEntity).parentId));
                } else if (FMLClientHandler.instance().getClient().gameSettings.keyBindJump.getIsKeyPressed()){
                    TrainsInMotion.keyChannel.sendToServer(new PacketInteract(15, ((EntitySeat) player.ridingEntity).parentId));
                }

                //manage key release events
                if (Keyboard.getEventKey() == FMLClientHandler.instance().getClient().gameSettings.keyBindJump.getKeyCode() && !Keyboard.getEventKeyState()){
                    TrainsInMotion.keyChannel.sendToServer(new PacketInteract(15, ((EntitySeat) player.ridingEntity).parentId));
                }
            }
        } else if(DebugUtil.dev) {
            if (ClientProxy.raildevtoolUp.getIsKeyPressed()){
                ClientProxy.devSplineModification[ClientProxy.devSplineCurrentPoint][0]+=0.0625;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("current spline shape is " +
                        ClientProxy.devSplineModification[0][0] + "," + ClientProxy.devSplineModification[0][1] +"," + ClientProxy.devSplineModification[0][2] +" : " +
                        ClientProxy.devSplineModification[1][0] + "," + ClientProxy.devSplineModification[1][1] +"," + ClientProxy.devSplineModification[1][2] +" : " +
                        ClientProxy.devSplineModification[2][0] + "," + ClientProxy.devSplineModification[2][1] +"," + ClientProxy.devSplineModification[2][2] +" : " +
                        ClientProxy.devSplineModification[3][0] + "," + ClientProxy.devSplineModification[3][1] +"," + ClientProxy.devSplineModification[3][2]));
            } else if (ClientProxy.raildevtoolDown.getIsKeyPressed()){
                ClientProxy.devSplineModification[ClientProxy.devSplineCurrentPoint][0]-=0.0625;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("current spline shape is " +
                        ClientProxy.devSplineModification[0][0] + "," + ClientProxy.devSplineModification[0][1] +"," + ClientProxy.devSplineModification[0][2] +" : " +
                        ClientProxy.devSplineModification[1][0] + "," + ClientProxy.devSplineModification[1][1] +"," + ClientProxy.devSplineModification[1][2] +" : " +
                        ClientProxy.devSplineModification[2][0] + "," + ClientProxy.devSplineModification[2][1] +"," + ClientProxy.devSplineModification[2][2] +" : " +
                        ClientProxy.devSplineModification[3][0] + "," + ClientProxy.devSplineModification[3][1] +"," + ClientProxy.devSplineModification[3][2]));
            }
            if (ClientProxy.raildevtoolLeft.getIsKeyPressed()){
                ClientProxy.devSplineModification[ClientProxy.devSplineCurrentPoint][2]+=0.0625;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("current spline shape is " +
                        ClientProxy.devSplineModification[0][0] + "," + ClientProxy.devSplineModification[0][1] +"," + ClientProxy.devSplineModification[0][2] +" : " +
                        ClientProxy.devSplineModification[1][0] + "," + ClientProxy.devSplineModification[1][1] +"," + ClientProxy.devSplineModification[1][2] +" : " +
                        ClientProxy.devSplineModification[2][0] + "," + ClientProxy.devSplineModification[2][1] +"," + ClientProxy.devSplineModification[2][2] +" : " +
                        ClientProxy.devSplineModification[3][0] + "," + ClientProxy.devSplineModification[3][1] +"," + ClientProxy.devSplineModification[3][2]));
            } else if (ClientProxy.raildevtoolRight.getIsKeyPressed()){
                ClientProxy.devSplineModification[ClientProxy.devSplineCurrentPoint][2]-=0.0625;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("current spline shape is " +
                        ClientProxy.devSplineModification[0][0] + "," + ClientProxy.devSplineModification[0][1] +"," + ClientProxy.devSplineModification[0][2] +" : " +
                        ClientProxy.devSplineModification[1][0] + "," + ClientProxy.devSplineModification[1][1] +"," + ClientProxy.devSplineModification[1][2] +" : " +
                        ClientProxy.devSplineModification[2][0] + "," + ClientProxy.devSplineModification[2][1] +"," + ClientProxy.devSplineModification[2][2] +" : " +
                        ClientProxy.devSplineModification[3][0] + "," + ClientProxy.devSplineModification[3][1] +"," + ClientProxy.devSplineModification[3][2]));
            }
            if (ClientProxy.raildevtoolRaise.getIsKeyPressed()){
                ClientProxy.devSplineModification[ClientProxy.devSplineCurrentPoint][1]+=0.0625;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("current spline shape is " +
                        ClientProxy.devSplineModification[0][0] + "," + ClientProxy.devSplineModification[0][1] +"," + ClientProxy.devSplineModification[0][2] +" : " +
                        ClientProxy.devSplineModification[1][0] + "," + ClientProxy.devSplineModification[1][1] +"," + ClientProxy.devSplineModification[1][2] +" : " +
                        ClientProxy.devSplineModification[2][0] + "," + ClientProxy.devSplineModification[2][1] +"," + ClientProxy.devSplineModification[2][2] +" : " +
                        ClientProxy.devSplineModification[3][0] + "," + ClientProxy.devSplineModification[3][1] +"," + ClientProxy.devSplineModification[3][2]));
            } else if (ClientProxy.raildevtoolLower.getIsKeyPressed()){
                ClientProxy.devSplineModification[ClientProxy.devSplineCurrentPoint][1]-=0.0625;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("current spline shape is " +
                        ClientProxy.devSplineModification[0][0] + "," + ClientProxy.devSplineModification[0][1] +"," + ClientProxy.devSplineModification[0][2] +" : " +
                        ClientProxy.devSplineModification[1][0] + "," + ClientProxy.devSplineModification[1][1] +"," + ClientProxy.devSplineModification[1][2] +" : " +
                        ClientProxy.devSplineModification[2][0] + "," + ClientProxy.devSplineModification[2][1] +"," + ClientProxy.devSplineModification[2][2] +" : " +
                        ClientProxy.devSplineModification[3][0] + "," + ClientProxy.devSplineModification[3][1] +"," + ClientProxy.devSplineModification[3][2]));
            }

            if (ClientProxy.raildevtoolNextPoint.getIsKeyPressed()){
                ClientProxy.devSplineCurrentPoint++;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("current point is now " + ClientProxy.devSplineCurrentPoint));
            } else if (ClientProxy.raildevtoolLastPoint.getIsKeyPressed()){
                ClientProxy.devSplineCurrentPoint--;
                if (ClientProxy.devSplineCurrentPoint<0){
                    ClientProxy.devSplineCurrentPoint = 0;
                }
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("current point is now " + ClientProxy.devSplineCurrentPoint));
            }
            if (ClientProxy.raildevtoolQuality.getIsKeyPressed()){
                ClientProxy.railSkin++;
                if(ClientProxy.railSkin>3){
                    ClientProxy.railSkin=0;
                }
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Displaying rail model " + ClientProxy.railSkin));

                for(Object te : Minecraft.getMinecraft().thePlayer.worldObj.loadedTileEntityList){
                    if(te instanceof RailTileEntity){
                        ((RailTileEntity) te).railGLID=null;
                    }
                }
            }
        }
    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onClientTick(TickEvent.PlayerTickEvent event) {
        if(!inited && event.phase== TickEvent.Phase.END){
            try{
                GLContext.getCapabilities();
                fexcraft.tmt.slim.TextureManager.collectIngotColors();
                inited=true;
            } catch (RuntimeException e){}//this is thrown when world render isn't initialized yet
        }
        if(event.player.ridingEntity instanceof EntitySeat){
            if (FMLClientHandler.instance().getClient().gameSettings.keyBindForward.getIsKeyPressed()) {
                //for TC only controls, skip wait, for TiM only controls just stop.
                if(ClientProxy.controls==1 && holdTimer<40){
                    holdTimer=40;
                } else if (ClientProxy.controls==2){
                    return;
                }
                if (holdTimer==40){
                    TrainsInMotion.keyChannel.sendToServer(new PacketInteract(12, ((EntitySeat) event.player.ridingEntity).parentId));
                    holdTimer++;
                } else if (holdTimer<40){
                    holdTimer++;
                }
            } else if (FMLClientHandler.instance().getClient().gameSettings.keyBindBack.getIsKeyPressed()) {
                //for TC only controls, skip wait, for TiM only controls just stop.
                if(ClientProxy.controls==1 && holdTimer<40){
                    holdTimer=40;
                } else if (ClientProxy.controls==2){
                    return;
                }

                if (holdTimer==40){
                    TrainsInMotion.keyChannel.sendToServer(new PacketInteract(11, ((EntitySeat) event.player.ridingEntity).parentId));
                    holdTimer++;
                } else if (holdTimer<40){
                    holdTimer++;
                }
            }
            else if(!FMLClientHandler.instance().getClient().gameSettings.keyBindBack.getIsKeyPressed() &&
                    !FMLClientHandler.instance().getClient().gameSettings.keyBindForward.getIsKeyPressed()){
                if (holdTimer>40){
                    if(ClientProxy.controls!=1) {
                        TrainsInMotion.keyChannel.sendToServer(new PacketInteract(4, ((EntitySeat) event.player.ridingEntity).parentId));
                    } else {
                        TrainsInMotion.keyChannel.sendToServer(new PacketInteract(14, ((EntitySeat) event.player.ridingEntity).parentId));
                    }
                }
                holdTimer=0;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientKeyPress(InputEvent.MouseInputEvent event) {
        if (Mouse.isButtonDown(1) || Mouse.isButtonDown(0)) {
            if (selected != null) {
                (selected).interact(Minecraft.getMinecraft().thePlayer.getEntityId(), false, false, Mouse.isButtonDown(1) ? -1 : -999);
                //MinecraftForge.EVENT_BUS.post(new EntityInteractEvent(Minecraft.getMinecraft().thePlayer, selected));
            }
        }
    }

    private static void getTrainsInRange(Entity entity){
        stock=new ArrayList<>();
        List e = new ArrayList();
        for(int x=-1;x<=1;x++) {
            for(int z=-1;z<=1;z++) {
                for (List l : entity.worldObj.
                        getChunkFromChunkCoords(entity.chunkCoordX + x, entity.chunkCoordZ + z)
                        .entityLists) {
                    e.addAll(l);
                }
            }
        }

        for (Object obj : e) {
            if (obj instanceof GenericRailTransport) {
                stock.add((GenericRailTransport) obj);
            }
        }
    }

    //we have to maintain a backup value during processing because the entire render tick is threaded.
    //otherwise while the value of selected is being changed it could cause several frames of the tooltip being missing.
    public static GenericRailTransport getSelected() {
        return selected==null?lastSelected:selected;
    }



    @SubscribeEvent
    public void unloadChunk(ChunkEvent.Unload e){
        /*for(List l: e.getChunk().entityLists){
            for(Object o:l){
                if(o instanceof GenericRailTransport){
                    for(ModelBase m: ((GenericRailTransport) o).renderData.modelList){
                        for(Integer i : m.displayList){
                            if(i!=null){
                                GL11.glDeleteLists(i,1);
                            }
                        }
                    }
                    ((GenericRailTransport) o).renderData.modelList=null;
                    ((GenericRailTransport) o).renderData.needsModelUpdate=true;
                }
            }

        }*/
        for (Object te:e.getChunk().chunkTileEntityMap.entrySet()){
            if(te instanceof RailTileEntity){
                GL11.glDeleteLists(((RailTileEntity) te).railGLID,1);
                ((RailTileEntity) te).railGLID=null;
            }
        }
    }


    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if(event.side.isClient() && Minecraft.getMinecraft().currentScreen==null && getSelected()!=null){
            left=new ScaledResolution(Minecraft.getMinecraft(),Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight).getScaledWidth()/2;
            disp=getStaticStrings(getSelected(), Minecraft.getMinecraft().thePlayer);
            longest=0;
            for(String s: disp){
                if(Minecraft.getMinecraft().fontRenderer.getStringWidth(s)>longest){
                    longest=Minecraft.getMinecraft().fontRenderer.getStringWidth(s);
                }
            }
            longest*=0.3;
            longest+=10;
            GL11.glPushMatrix();
            drawTooltipBox(left-(longest)-35, 2, 70+(longest*2), 8+(10*disp.length), ClientProxy.WAILA_BGCOLOR, ClientProxy.WAILA_GRADIENT1, ClientProxy.WAILA_GRADIENT2,100);

            GL11.glTranslatef(0.0F, 0.0F, 32.0F);
            if(getSelected()!=null && getSelected().getCartItem()!=null) {
                itemRender.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().getTextureManager(),
                        getSelected().getCartItem(), left - (longest) - 30, 12);
            }
            //GL11.glDisable(GL11.GL_LIGHTING);
            for(int ii=0; ii<disp.length;ii++) {
                Minecraft.getMinecraft().fontRenderer.drawString(disp[ii],
                        40+left-(longest*3)+ ((longest-disp[ii].length())*2), 8+(ii*10),ii==0?0xFFFFFFFF:ClientProxy.WAILA_FONTCOLOR);
            }
            //GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            //todo: draw an array of strings for the tooltip info, derrived from the transport's class.
            GL11.glPopMatrix();
        }
    }

    private static int left=0,longest;
    private static String[] disp;
    public static RenderItem itemRender = new RenderItem();

    private static String[] getStaticStrings(GenericRailTransport t, EntityPlayer p){
        return new String[]{
                CommonUtil.translate(t.getInventoryName().replace(".storage","")+".name"),
                CommonUtil.translate("gui.owner") +": " + t.getOwnerName(),
                CommonUtil.translate("gui.skin")+": " + CommonUtil.translate(t.getTexture(p).name)
        };
    }




    public static void drawGradientRect(int x, int y, int w, int h, int grad1, int grad2, int alpha) {
        Tessellator.getInstance().startDrawing(GL11.GL_QUADS);
        GL11.glColor4ub((byte)((grad1 >> 16) & 0xFF), (byte)((grad1 >> 8) & 0xFF), (byte)(grad1 & 0xFF), (byte)((255f/100f)*alpha));
        Tessellator.getInstance().addVertex((x + w), y, 0f);
        Tessellator.getInstance().addVertex(x, y, 0f);
        GL11.glColor4ub((byte)((grad2 >> 16) & 0xFF), (byte)((grad2 >> 8) & 0xFF), (byte)(grad2 & 0xFF), (byte)((255f/100f)*alpha));
        Tessellator.getInstance().addVertex(x, (y + h), 0f);
        Tessellator.getInstance().addVertex((x + w), (y + h), 0f);
        Tessellator.getInstance().draw();
    }

    public static void drawTooltipBox(int x, int y, int w, int h, int bg, int grad1, int grad2, int alpha) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        drawGradientRect(x + 1, y, w - 1, 1, bg, bg,alpha);
        drawGradientRect(x + 1, y + h, w - 1, 1, bg, bg,alpha);
        drawGradientRect(x + 1, y + 1, w - 1, h - 1, bg, bg,alpha);
        drawGradientRect(x, y + 1, 1, h - 1, bg, bg,alpha);
        drawGradientRect(x + w, y + 1, 1, h - 1, bg, bg,alpha);
        drawGradientRect(x + 1, y + 2, 1, h - 3, grad1, grad2,alpha);
        drawGradientRect(x + w - 1, y + 2, 1, h - 3, grad1, grad2,alpha);
        drawGradientRect(x + 1, y + 1, w - 1, 1, grad1, grad1,alpha);
        drawGradientRect(x + 1, y + h - 1, w - 1, 1, grad2, grad2,alpha);
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }


    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onPreRenderEvent(RenderLivingEvent.Pre event){
        if (event.entity.ridingEntity instanceof EntitySeat) {
            GL11.glPushMatrix();
            GenericRailTransport t = (GenericRailTransport) event.entity.worldObj.getEntityByID(((EntitySeat) event.entity.ridingEntity).parentId);
            if(t!=null) {
                GL11.glScalef(t.getPlayerScale(), t.getPlayerScale(), t.getPlayerScale());
            }

        }
    }
    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onPostRenderEvent(RenderLivingEvent.Post event){
        if (event.entity.ridingEntity instanceof EntitySeat) {
            GL11.glPopMatrix();
        }
    }

    /**
     * <h2>join world</h2>
     * This event is called when a player joins the world, we use this to display the alpha notice, and check for new mod versions, this is only displayed on the client side, but can be used for server..
     */
    @SubscribeEvent
    @SuppressWarnings("unused")
    public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
    }

    /**
     * used to make vanilla buckets pickup custom fluids.
     * i think it only runs on client, which is a HORRIBLE and LAZY design by mojank, but oh well.
     * @see EventManagerServer#onBucketFillServer(FillBucketEvent)
     * adding to server event manager anyway, just in case.
     * @param event
     */
    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onBucketFill(FillBucketEvent event){
        Block b = CommonUtil.getBlockAt(event.world,event.target.blockX, event.target.blockY, event.target.blockZ);
        if(TiMGenericRegistry.fluidMap.get(b) !=null){
            event.result= new ItemStack(TiMGenericRegistry.fluidMap.get(b));
            event.setResult(Event.Result.ALLOW);
        }
    }
}
