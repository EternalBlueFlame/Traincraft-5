package train.core.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import train.Traincraft;
import train.core.util.MP3Player;

public class ClientTickHandler {

	@SubscribeEvent
	public void tick(TickEvent event) {
		if(event.side != Side.CLIENT) {
			return;
		}
		if(event.phase == TickEvent.Phase.START) {
			if (Minecraft.getMinecraft().theWorld == null) { // fixes streaming after exiting a world
				for (MP3Player player : Traincraft.proxy.playerList) {
					if (player != null) {
						player.stop();
					}
				}
				Traincraft.proxy.playerList.clear();
			}
		}
	}


}