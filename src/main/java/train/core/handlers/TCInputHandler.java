package train.core.handlers;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import ebf.tim.utility.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import train.Traincraft;
import train.core.network.PacketKeyPress;


public class TCInputHandler {
	public static KeyBinding up;
	public static KeyBinding down;
	public static KeyBinding idle;
	public static KeyBinding furnace;
	public TCInputHandler() {
		up = new KeyBinding("key.traincraft.zepp.up", Keyboard.KEY_Y, "key.categories.traincraft5");
		ClientRegistry.registerKeyBinding(up);
		down = new KeyBinding("key.traincraft.zepp.down", Keyboard.KEY_X, "key.categories.traincraft5");
		ClientRegistry.registerKeyBinding(down);
		idle = new KeyBinding("key.traincraft.zepp.idle", Keyboard.KEY_C, "key.categories.traincraft5");
		ClientRegistry.registerKeyBinding(idle);
		furnace = new KeyBinding("key.traincraft.zepp.furnace", Keyboard.KEY_F, "key.categories.traincraft5");
		ClientRegistry.registerKeyBinding(furnace);


	}

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (!Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen()) {
			if (up.getIsKeyPressed()) {
				sendKeyControlsPacket(0);
			}
			else if (down.getIsKeyPressed()) {
				sendKeyControlsPacket(2);
			}
			else if (idle.isPressed()) {
				sendKeyControlsPacket(6);
			}
			else if (ClientProxy.KeyInventory.isPressed()) {
				sendKeyControlsPacket(7);
			}
			else if (furnace.isPressed()) {
				sendKeyControlsPacket(9);
			}
		}

		//if (FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.isPressed() && Keyboard.isKeyDown(Keyboard.KEY_F3)) {
		//	sendKeyControlsPacket(404);
		//}
	}


	
	private static void sendKeyControlsPacket(int key)
	{
		Traincraft.keyChannel.sendToServer(new PacketKeyPress(key));
	}
}