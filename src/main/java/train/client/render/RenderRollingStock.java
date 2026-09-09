package train.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.api.SkinRegistry;
import ebf.tim.utility.CommonUtil;
import ebf.tim.utility.DebugUtil;
import fexcraft.tmt.slim.Tessellator;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import train.common.Traincraft;
import train.common.api.*;
import train.common.blocks.BlockTCRail;
import train.common.blocks.BlockTCRailGag;
import train.common.entity.rollingStockOld.special.EntityTracksBuilder;
import train.common.library.Info;
import train.common.overlaytexture.OverlayTextureManager;

import java.util.*;

import static org.lwjgl.opengl.GL11.*;

@SideOnly(Side.CLIENT)
public class RenderRollingStock extends Render {
    private static final Random random = new Random();
	private static boolean renderModeGUI = false;
	private static boolean renderGUIFullBright = false;
    public RenderRollingStock() {
        this.shadowSize = 0.5F;
    }

    /**
     * Renders the Minecart.
     */
    public static void renderTheMinecart(EntityRollingStock cart, double x, double y, double z, float yaw, float time) {

        if(cart.render_cache.needs_model_update){
            cart.render_cache.models=cart.getModel();
            cart.render_cache.bogies=cart.bogies();
            cart.render_cache.needs_model_update=false;
        }

        if (!cart.acceptsOverlayTextures() || cart.getOverlayTextureContainer().getType() == OverlayTextureManager.Type.NONE) {
			Tessellator.bindTexture(getTexture(cart));
		} else {
			if (cart.getOverlayTextureContainer().markedForUpdate) {
				cart.getOverlayTextureContainer().renderTexture();
			}
			Tessellator.bindTexture(cart.getOverlayTextureContainer().getOverlaidTextureResource());
		}
        GL11.glPushMatrix();
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1f);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glLightModelf(GL_LIGHT_MODEL_TWO_SIDE, GL_FALSE);
        GL11.glLightModelf(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE);
        GL11.glShadeModel(GL_SMOOTH);
        GL11.glEnable(GL_NORMALIZE);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL_REPEAT);

        y-=0.3f;

        GL11.glTranslatef((float) x, (float) y, (float) z);
        int i = CommonUtil.floorDouble(cart.posX);
        int j = CommonUtil.floorDouble(cart.posY);
        int k = CommonUtil.floorDouble(cart.posZ);

        if (cart.worldObj != null && (CommonUtil.getBlockAt(cart.worldObj,i,j,k) instanceof BlockTCRail || CommonUtil.getBlockAt(cart.worldObj,i,j,k) instanceof BlockTCRailGag)) {
            GL11.glTranslatef(0f, 0.15f, 0f);
        }

        GL11.glRotatef(180-cart.rotationYaw, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(cart.rotationPitch, 0.0F, 0.0F, 1.0F);

        float var28 = cart.getRollingAmplitude() - time;

        if (var28 > 0.0F) {
            float angle = MathHelper.sin(var28) * var28 * Math.max(0.0f,cart.getDamage() - time) / 10.0F;
            angle = Math.min(angle, 0.8F);
            angle = Math.copySign(angle, cart.getRollingDirection());
            GL11.glRotatef(angle, 1.0F, 0.0F, 0.0F);
        }

        GL11.glEnable(GL11.GL_LIGHTING);
        int skyLight = cart.worldObj==null?0:cart.worldObj.getLightBrightnessForSkyBlocks(i, j, k, 0);
        if (!renderModeGUI) {
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, skyLight % 65536,
                    skyLight / 65536f);
        } else {
            if (renderGUIFullBright) {
                GL11.glDisable(GL11.GL_LIGHTING);
            }
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f,
                    240f);
        }
        //loadTexture(getTextureFile(renders.getTexture(), renders.getIsMultiTextured(), cart));
        int m=0;
        for(;m<cart.render_cache.models.length;m++) {
            GL11.glPushMatrix();

            if(cart.render_cache.models[m].getTrans()!=null){
                GL11.glTranslatef(cart.render_cache.models[m].getTrans()[0],cart.render_cache.models[m].getTrans()[1],cart.render_cache.models[m].getTrans()[2]);
            }
            else if(cart.modelOffsets()!=null &&cart.modelOffsets()[m]!=null) {
                GL11.glTranslatef(cart.modelOffsets()[m][0], cart.modelOffsets()[m][1], cart.modelOffsets()[m][2]);
            }
            if(cart.render_cache.models[m].getRotate()!=null){
                GL11.glRotatef(cart.render_cache.models[m].getRotate()[0], 1,0,0);
                GL11.glRotatef(cart.render_cache.models[m].getRotate()[1], 0,1,0);
                GL11.glRotatef(cart.render_cache.models[m].getRotate()[2], 0,0,1);
            }
            else if(cart.modelRotations()[m]!=null) {
                GL11.glRotatef(cart.modelRotations()[m][0], 1,0,0);
                GL11.glRotatef(cart.modelRotations()[m][1], 0,1,0);
                GL11.glRotatef(cart.modelRotations()[m][2], 0,0,1);
            }
            if(cart.render_cache.models[m].getScale()!=null){
                GL11.glScalef(cart.render_cache.models[m].getScale()[0],cart.render_cache.models[m].getScale()[1],cart.render_cache.models[m].getScale()[2]);
            }
            else if(cart.getRenderScale()[m]!=null) {
                GL11.glScalef(cart.getRenderScale()[m][0], cart.getRenderScale()[m][1], cart.getRenderScale()[m][2]);
            }
            cart.render_cache.models[m].render(cart, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

            GL11.glPopMatrix();
        }

        if(cart.render_cache.bogies!=null && cart.render_cache.bogies.length>0){
            DebugUtil.println(cart.render_cache.bogies.length);
            for (m=0;m<cart.render_cache.bogies.length;m++){
                if(cart.render_cache.skin.bogieSkins.size()>m) {
                    Tessellator.bindTexture(new ResourceLocation(cart.render_cache.skin.bogieSkins.get(m)));
                }
                cart.render_cache.bogies[m].render(cart);
            }
        }



        //GL11.glEnable(GL11.GL_LIGHTING);
        TrainRenderRecord render = cart.getRender();
        if(render!=null) {
            if (render.hasSmoke()) {
                if (cart.render_cache.smokePosition == null) {
                    cart.render_cache.smokePosition = new ArrayList<double[]>();
                    if (render.getModel().getSmokePosition() != null) {
                        cart.render_cache.smokePosition = render.getModel().getSmokePosition();
                    }
                    if (cart.getSmokePosition() != null) {
                        cart.render_cache.smokePosition.addAll(cart.getSmokePosition());
                    }
                    if (render.getSmokeFX() != null) {
                        cart.render_cache.smokePosition.addAll(render.getSmokeFX());
                    }
                }

                if (cart.bogieFront != null) {// || cart.bogieUtility[0]!=null){
                    renderSmokeFX(cart, 90 + cart.rotationYaw, cart.rotationPitch, render.getSmokeType(), cart.render_cache.smokePosition, render.getSmokeIterations(), time, render.hasSmokeOnSlopes());
                }
            }
            if (render.hasExplosion()) {
                if (cart.bogieFront != null) {// || cart.bogieUtility[0]!=null){
                    renderExplosionFX(cart, 90 + cart.rotationYaw, cart.rotationPitch, render.getExplosionType(), render.getExplosionFX(), render.getExplosionFXIterations(), render.hasSmokeOnSlopes());
                }
            }
        }
        if(cart.getEffects()!=null){
            for(TrainParticle p : cart.getEffects()){
                if(p.type.toLowerCase().contains("smoke")){
                    renderSmokeFX(cart, 90 + cart.rotationYaw, cart.rotationPitch, p.type, Collections.singletonList(p.position), p.density, time, true);
                } else {
                    renderExplosionFX(cart, 90 + cart.rotationYaw, cart.rotationPitch, p.type, Collections.singletonList(p.position), p.density, true);
                }
            }
        }

        GL11.glPopMatrix();
    }

    private static void renderSmokeFX(EntityRollingStock cart, float yaw, float pitch, String smokeType, List<double[]> smokeFX, int smokeIterations, float time, boolean hasSmokeOnSlopes) {
        if (cart instanceof Locomotive && !((Locomotive) cart).isLocoTurnedOn()) {
            return;
        }
        if (Math.abs(pitch) > 30) return;
        //if (pitch != 0 && !hasSmokeOnSlopes) { return; }
        if ((cart instanceof Locomotive && ((Locomotive) cart).getFuel() > 0) || (cart instanceof EntityTracksBuilder && ((EntityTracksBuilder) cart).getFuel() > 0)) {
            if (random.nextInt(10 * smokeIterations) < ((smokeIterations * 4) + (cart.getSpeed() * 5))) {
                double rotatedvec[];
                for (int j = 0; j < smokeIterations; j++) {
                    for (double[] explosion : smokeFX) {
                        rotatedvec = rotatePointF(explosion[0], explosion[1], explosion[2], pitch, yaw);
                        cart.worldObj.spawnParticle(smokeType,
                                cart.posX + rotatedvec[0], cart.posY + rotatedvec[1], cart.posZ + rotatedvec[2],
                                0, 0, 0);
                    }
                }
            }
        }
    }

    public static final float radianF = CommonUtil.radianF;

    public static double[] rotatePointF(double x, double y, double z, float pitch, float yaw) {
        double[] xyz = new double[]{x, y, z};
        float sin, cos;
        //rotate pitch
        if (pitch != 0.0F) {
            pitch *= radianF;
            cos = MathHelper.cos(pitch);
            sin = MathHelper.sin(pitch);

            xyz[0] = (y * sin) + (x * cos);
            xyz[1] = (y * cos) - (x * sin);
        }
        //rotate yaw
        if (yaw != 0.0F) {
            yaw *= radianF;
            cos = MathHelper.cos(yaw);
            sin = MathHelper.sin(yaw);

            xyz[0] = (x * cos) - (z * sin);
            xyz[2] = (x * sin) + (z * cos);
        }

        return xyz;
    }


    private static void renderExplosionFX(EntityRollingStock cart, float yaw, float pitch, String explosionType, List<double[]> explosionFX, int explosionFXIterations, boolean hasSmokeOnSlopes) {
        if (cart instanceof Locomotive && !((Locomotive) cart).isLocoTurnedOn()) return;
        float yawMod = yaw % 360;
        double pitchRads = Math.toDegrees(pitch);
        //if (pitch != 0 && !hasSmokeOnSlopes) { return; }
        if (Math.abs(pitch) > 30) return;
        if (cart instanceof Locomotive && ((Locomotive) cart).getFuel() > 0) {
            int r = random.nextInt(300);
            if (r < (explosionFXIterations * 10)) {
                for (int j = 0; j < explosionFXIterations; j++) {
                    if (yawMod == 180) {
                        for (double[] explosion : explosionFX) {
                            cart.worldObj.spawnParticle(explosionType, cart.posX - explosion[0], cart.posY + explosion[1] + ((Math.tan(pitchRads) * 4 * -explosion[1])), cart.posZ + explosion[2], 0.0D, 0.0D, 0.0D);
                            cart.worldObj.spawnParticle(explosionType, cart.posX - explosion[0], cart.posY + explosion[1] + ((Math.tan(pitchRads) * 4 * -explosion[1])), cart.posZ - explosion[2], 0.0D, 0.0D, 0.0D);
                        }
                    } else if (yawMod == 90) {
                        for (double[] explosion : explosionFX) {
                            cart.worldObj.spawnParticle(explosionType, cart.posX + explosion[2], cart.posY + explosion[1] + ((Math.tan(pitchRads) * 4 * -explosion[1])), cart.posZ + explosion[0], 0.0D, 0.0D, 0.0D);
                            cart.worldObj.spawnParticle(explosionType, cart.posX - explosion[2], cart.posY + explosion[1] + ((Math.tan(pitchRads) * 4 * -explosion[1])), cart.posZ + explosion[0], 0.0D, 0.0D, 0.0D);
                        }
                    } else if (yawMod == 0) {
                        for (double[] explosion : explosionFX) {
                            cart.worldObj.spawnParticle(explosionType, cart.posX + explosion[0], cart.posY + explosion[1] + ((Math.tan(pitchRads) * 4 * -explosion[1])), cart.posZ + explosion[2], 0.0D, 0.0D, 0.0D);
                            cart.worldObj.spawnParticle(explosionType, cart.posX + explosion[0], cart.posY + explosion[1] + ((Math.tan(pitchRads) * 4 * -explosion[1])), cart.posZ - explosion[2], 0.0D, 0.0D, 0.0D);
                        }
                    } else if (yawMod == -90) {
                        for (double[] explosion : explosionFX) {
                            cart.worldObj.spawnParticle(explosionType, cart.posX + explosion[2], cart.posY + explosion[1] + ((Math.tan(pitchRads) * 4 * -explosion[1])), cart.posZ - explosion[0], 0.0D, 0.0D, 0.0D);
                            cart.worldObj.spawnParticle(explosionType, cart.posX - explosion[2], cart.posY + explosion[1] + ((Math.tan(pitchRads) * 4 * -explosion[1])), cart.posZ - explosion[0], 0.0D, 0.0D, 0.0D);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void doRender(Entity par1Entity, double x, double y, double d2, float yaw, float time) {
        renderTheMinecart((EntityRollingStock) par1Entity, x, y, d2, yaw, time);
    }


    //@Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if(entity instanceof EntityRollingStock) {
            return getTexture((EntityRollingStock) entity);
        }
        else {
            return null;
        }
    }

    public static ResourceLocation getTexture(AbstractTrains entity) {
        if(!entity.render_cache.color.equals(entity.getColor())){
            entity.render_cache.color=entity.getColor();
            entity.render_cache.rend=entity.getRender();
            entity.render_cache.skin=SkinRegistry.get(entity).get(entity.render_cache.color);
        }

        if(entity.entity_data.getString("color")!=null) {
            return new ResourceLocation(entity.entity_data.getString("color"));
        }
        if (entity.render_cache.rend != null) {
            return entity.render_cache.rend.getTextureFile(entity.render_cache.color);
        }
        return null;
    }

	/**
	 * @author 02skaplan
	 * @param renderModeGUI GUI lighting flag
	 * 	<p>Flag of whether to allow in-game lighting conditions to effect light levels of rendered entity.
	 *  <b>Set to true if rendering in a GUI and set back to false when done.</b></p>
	 */
	public static void setRenderModeGUI(boolean renderModeGUI) {
		RenderRollingStock.renderModeGUI = renderModeGUI;
	}

	/**
	 * @param renderGUIFullBright Flag of whether to allow any lighting on a rendered entity. This will cause the entity to render
	 * unnaturally bright, which may or may not be desired.
	 */
	public static void setRenderGUIFullBright(boolean renderGUIFullBright) {
		RenderRollingStock.renderGUIFullBright = renderGUIFullBright;
	}
}
