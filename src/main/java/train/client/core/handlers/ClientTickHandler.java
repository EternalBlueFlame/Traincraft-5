package train.client.core.handlers;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.item.ItemStack;
import train.client.core.helpers.CapesHelper;
import train.common.Traincraft;
import train.common.core.CommonProxy;
import train.common.core.util.MP3Player;
import train.common.library.Info;

import java.util.Objects;

public class ClientTickHandler {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static boolean isHidden = false;

    @SubscribeEvent
    public void tick(TickEvent event) {
        if (event.side != Side.CLIENT) {
            return;
        }

        if (Objects.requireNonNull(event.phase) == TickEvent.Phase.START) {
            tickStart(event);
        }
    }

    private void tickStart(TickEvent event) {
        if (mc.world == null) { // fixes streaming after exiting a world
            for (MP3Player player : CommonProxy.playerList) if (player != null) player.stop();
            CommonProxy.playerList.clear();
        }
        if (mc.world != null && mc.world.playerEntities != null) {
            for (Object p : mc.world.playerEntities) {
                AbstractClientPlayer player = (AbstractClientPlayer) p;
                CapesHelper user = CapesHelper.users.get(player.getName());

                if (user == null) {
                    user = new CapesHelper(player.getName());
                    CapesHelper.users.put(player.getName(), user);
                    user.setDaemon(true);
                    user.setName("Cape for " + player.getName());
                    user.start();
                } else if (CapesHelper.isLoaded && user.MCCape) {
                    setCape(player, user.getCurrentRL());
                }
            }
        }

        if (!isHidden && Traincraft.hasNotEnoughItems()) {
            if (mc.world != null && mc.world.playerEntities != null) {
                Traincraft.proxy.doNEICheck(new ItemStack(Block.getBlockFromName(Info.modID + ":tcRail")));
                Traincraft.proxy.doNEICheck(new ItemStack(Block.getBlockFromName(Info.modID + ":tcRailGag")));
                isHidden = true;
            }
        }
    }


    /** 1.7.10 {@code AbstractClientPlayer.func_152121_a(CAPE, location)}: swap the player's cape texture. */
    private static void setCape(AbstractClientPlayer player, net.minecraft.util.ResourceLocation location) {
        try {
            net.minecraft.client.network.NetworkPlayerInfo info = net.minecraftforge.fml.common.ObfuscationReflectionHelper
                    .getPrivateValue(AbstractClientPlayer.class, player, "playerInfo", "field_175157_a");
            if (info != null) {
                java.util.Map<MinecraftProfileTexture.Type, net.minecraft.util.ResourceLocation> textures = net.minecraftforge.fml.common.ObfuscationReflectionHelper
                        .getPrivateValue(net.minecraft.client.network.NetworkPlayerInfo.class, info, "playerTextures", "field_187107_a");
                textures.put(MinecraftProfileTexture.Type.CAPE, location);
            }
        } catch (RuntimeException e) {
            // cape support is cosmetic, never crash the client over it
        }
    }
}