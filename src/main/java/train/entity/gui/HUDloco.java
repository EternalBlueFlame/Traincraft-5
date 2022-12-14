package train.entity.gui;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ebf.tim.TrainsInMotion;
import ebf.tim.entities.EntitySeat;
import ebf.tim.entities.EntityTrainCore;
import ebf.tim.entities.GenericRailTransport;
import ebf.tim.utility.ClientProxy;
import ebf.tim.utility.ClientUtil;
import fexcraft.tmt.slim.TextureManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;
import train.library.Info;

public class HUDloco extends GuiScreen {
	//todo all of this is fucked, fuel bar doesnt scale, speed is wrong, heat doesn't do jack on steam

	private Minecraft game;
	private int windowWidth, windowHeight;
	private int lastTick=0, heat=0;

	@SubscribeEvent
	public void onGameRender(RenderGameOverlayEvent.Text event){
		if (game != null && game.thePlayer != null && game.thePlayer.ridingEntity instanceof EntitySeat && Minecraft.isGuiEnabled() && game.currentScreen == null) {
			if(((EntitySeat)game.thePlayer.ridingEntity).isLocoControlSeat()) {
				renderSkillHUD(event, (EntityTrainCore) game.theWorld.getEntityByID(((EntitySeat) game.thePlayer.ridingEntity).parentId));
			}
		} else {
			this.game = Minecraft.getMinecraft();
		}
	}

	public void renderSkillHUD(RenderGameOverlayEvent event, EntityTrainCore rcCar) {
		windowWidth = event.resolution.getScaledWidth();
		windowHeight = event.resolution.getScaledHeight() - 100;
		GL11.glColor4f(255, 255, 255, 255);
		renderBG(rcCar);
		/**
		 * Steam Train have water
		 */
		if (rcCar.getTypes().contains(TrainsInMotion.transportTypes.STEAM)) {
			renderWaterBar(rcCar);
			renderOverheating(rcCar);
		}
		renderSpeedometer(rcCar);
		renderFuelBar(rcCar);
		renderText(rcCar);
	}

	private void renderBG(EntityTrainCore rcCar) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(32826);
		this.zLevel = -90.0F;
		if (rcCar.getTypes().contains(TrainsInMotion.transportTypes.STEAM)) {
			game.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation,Info.guiPrefix + "loco_hud_steam.png"));
		}
		else {
			game.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation,Info.guiPrefix + "locohud.png"));
		}
		ClientUtil.drawTexturedRect(10, windowHeight, 0, 150, 137, 90);
		GL11.glDisable(32826);
		GL11.glDisable(GL11.GL_BLEND);
	}

	private void renderText(EntityTrainCore loco) {
		double speed =EntityTrainCore.ratio(loco.getVelocity());
		speed*=ClientProxy.speedInKmh?1:0.621371;
		int h;
		if (loco.getTypes().contains(TrainsInMotion.transportTypes.STEAM)) {
			h = 15;
		}
		else {
			h = 13;
		}
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(32826);
		game.fontRenderer.drawStringWithShadow("Speed:", 106, windowHeight + 7 + (h), 0xFFFFFF);
		game.fontRenderer.drawStringWithShadow("  " + Math.floor(speed), 100,
				windowHeight + 18 + (h), 0xFFFFFF);
		game.fontRenderer.drawStringWithShadow(ClientProxy.speedInKmh?" Km/h":"Mp/h", 106, windowHeight + 29 + (h), 0xFFFFFF);

		if (loco.getTypes().contains(TrainsInMotion.transportTypes.STEAM)) {
			game.fontRenderer.drawStringWithShadow("State: " + getState(loco), 50, windowHeight + 80, 0xFFFFFF);
		}
		GL11.glDisable(32826);
		GL11.glDisable(GL11.GL_BLEND);
	}

	private String getState(GenericRailTransport loco){
		float h =loco.entityData.hasFloat("boilerHeat")?loco.entityData.getFloat("boilerHeat"):0;
		if (h < 50) {
			return "cold";
		} else if (h < 90) {
			return "warm";
		} else if (h < 300) {
			return "hot";
		} else {
			return "very hot";
		}
	}

	private void renderFuelBar(EntityTrainCore loco) {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(32826);
		int l;
		/**
		 * So that the content of the tank is renderer and not the fuel currently burned
		 */
		if(!loco.getTypes().contains(TrainsInMotion.transportTypes.STEAM)) {
			l = loco.getTankInfo(null)[0] != null ? loco.getTankInfo(null)[0].fluid.amount : 1;
			l = Math.abs(((l * 70) / (loco.getTankCapacity()[0])));
		} else {

			if(loco.entityData.hasFloat("burnTime") && loco.entityData.hasFloat("maxBurn")) {
				l = (int)Math.floor(loco.entityData.getFloat("maxBurn"));
				if (l != 0 && loco.entityData.getFloat("burnTime") != 0) {
					l = (int)Math.abs(((l * 70) / (loco.entityData.getFloat("burnTime"))));
				}
			}else {
				l = 0;
			}
		}
		if (l > 70) {
			l = 70;// to fit the 70 pixels bar
		}
		else if (l < 0) {
			l = 0;
		}
		/**
		 * Things are slightly different in Steam HUD
		 * because it's a black bar that is rendered that hides the color bar the black bar is rendered from top to bottom
		 */
		if (!(loco.getTypes().contains(TrainsInMotion.transportTypes.STEAM))) {
			TextureManager.bindTexture(new ResourceLocation(Info.resourceLocation,Info.guiPrefix + "locohud.png"));
			ClientUtil.drawTexturedRect(28, windowHeight + 11, 154, 170 + l, 7, 70 - l);// l max = 70
		}
		else {
			TextureManager.bindTexture(new ResourceLocation(Info.resourceLocation,Info.guiPrefix + "loco_hud_steam.png"));
			ClientUtil.drawTexturedRect(34, windowHeight + 17, 154, 170 + l, 7, 70 - l);// l max = 70
		}
		// fontRendererObj.drawStringWithShadow("Fuel:", 4, (windowHeight/2)+1, 0xFFFFFF);
		GL11.glDisable(32826);
		GL11.glDisable(GL11.GL_BLEND);
	}

	private void renderWaterBar(EntityTrainCore loco) {
		int l = loco.getTankInfo(null)[0]!=null?loco.getTankInfo(null)[0].fluid.amount:1;
		int l_Scaled = Math.abs((l * 49) / loco.getTankCapacity()[0]);
		if (l_Scaled > 49) {
			l_Scaled = 49;// to fit the 49 pixels bar
		}
		if (l_Scaled < 0) {
			l_Scaled = 0;
		}
		/**
		 * because it's a black bar that is rendered that hides the color bar the black bar is rendered from top to bottom
		 */
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(32826);
		ClientUtil.drawTexturedRect(70, windowHeight + 17, 190, 169 + l_Scaled, 6, 49 - l_Scaled);// l max = 49
		GL11.glDisable(32826);
		GL11.glDisable(GL11.GL_BLEND);
		/* this is for the red overlay if you don't put water into steam trains */
		if (l <= 1 && loco.entityData.hasFloat("burnTime") && loco.entityData.getFloat("burnTime")>0) {
			ClientUtil.drawGradientRect(0, 0, windowWidth, windowHeight + 100, 1615855616, -1602211792);
		}
	}

	public int getHeat(GenericRailTransport loco){
		if(loco.getTypes().contains(TrainsInMotion.transportTypes.STEAM)) {
			if (loco.ticksExisted > lastTick) {
				int l = loco.getTankInfo(null)[1] != null && loco.getTankInfo(null)[1].fluid !=null?
						loco.getTankInfo(null)[1].fluid.amount : 1;
				return ((l * 100) / (loco.getTankCapacity()[1]));
			}
			return 50;
		} else {
			return loco.getBoolean(GenericRailTransport.boolValues.RUNNING)?50:0;
		}
	}

	private void renderSpeedometer(EntityTrainCore loco) {
		int speed =loco.getAccelerator();
		//clamp speed to the normal range
		speed=speed>0?Math.min(speed,6):Math.max(speed,-6);
		//scale speed to the bar
		speed*=-4;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(32826);
		/**
		 * Things are slightly different in Steam HUD
		 */
		//todo make dial thing aling with gearing
		if (loco.getTypes().contains(TrainsInMotion.transportTypes.STEAM)) {
			game.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation,Info.guiPrefix + "loco_hud_steam.png"));
			ClientUtil.drawTexturedRect(75, windowHeight + 40 - speed, 163, 150, 30, 5);
		}
		else {
			game.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation,Info.guiPrefix + "locohud.png"));
			ClientUtil.drawTexturedRect(86, windowHeight + 31 - speed, 172, 149, 16, 8);
		}
		GL11.glDisable(32826);
		GL11.glDisable(GL11.GL_BLEND);
	}

	private void renderOverheating(EntityTrainCore loco) {
		// fontRendererObj.drawStringWithShadow("Heat:", 33, (windowHeight/2)+1, 0xFFFFFF);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(32826);

		/**
		 * Steam Train have different HUD
		 */
		if (loco.getTypes().contains(TrainsInMotion.transportTypes.STEAM)) {
			game.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation,Info.guiPrefix + "loco_hud_steam.png"));
		}
		else {
			game.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation,Info.guiPrefix + "locohud.png"));
		}

		int overheatScaled = getHeat(loco);
		if (overheatScaled > 0) {
			overheatScaled=(49*overheatScaled)/100;
		}
		/**
		 * Things are slightly different in Steam HUD render overheat arrow black bar for steam train
		 */
		if (!(loco.getTypes().contains(TrainsInMotion.transportTypes.STEAM))) {
			ClientUtil.drawTexturedRect(58, windowHeight + 37 - ( overheatScaled)+20, 169, 158, 23, 5);
		}
		else {
			ClientUtil.drawTexturedRect(56, windowHeight + 17, 176, (169 + overheatScaled), 5, 49 - overheatScaled);
		}
		GL11.glDisable(32826);
		GL11.glDisable(GL11.GL_BLEND);
	}

}
