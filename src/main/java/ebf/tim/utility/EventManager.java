package ebf.tim.utility;


import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.lwjgl.opengl.GL11;
import train.common.api.EntityRollingStock;

public class EventManager {

    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onPreRenderEvent(RenderLivingEvent.Pre event) {
        if (event.entity.ridingEntity instanceof EntityRollingStock) {
            GL11.glPushMatrix();
            EntityRollingStock t = (EntityRollingStock) event.entity.ridingEntity;
            if (t != null) {
                GL11.glScalef(t.getPlayerScale(), t.getPlayerScale(), t.getPlayerScale());
            }

        }
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onPostRenderEvent(RenderLivingEvent.Post event) {
        if (event.entity.ridingEntity instanceof EntityRollingStock) {
            GL11.glPopMatrix();
        }
    }

}