package fexcraft.fvtm;

import ebf.tim.utility.ClientProxy;
import ebf.tim.utility.DebugUtil;
import fexcraft.tmt.slim.ModelBase;
import fexcraft.tmt.slim.ModelRendererTurbo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A compatibility class for "FVTM Scheme/Format" models.
 * @Author Eternal BlueFlame
 *
 * @OriginalAuthor Ferdinand Calo' (FEX___96)
 */

public class RollingStockModel extends ModelBase {
	
    public List<TurboList> groups = new ArrayList<>();
    public int textureX, textureY;
    public RollingStockModel(){}

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
        if(init){
            for(TurboList list :groups) {
                list.initAllParts();
            }
            //for the named list, we sort those into this class to avoid subclassing errors with the animator.
            for(TurboList list :groups) {
                namedList.addAll(list.namedList);
            }
        }


        if(ClientProxy.disableCache) {
            for(TurboList list :groups) {
                list.render(list.boxList);
            }
        } else if(staticPartMap.get(this.getClass().getName())==null) {
            staticPartMap.put(this.getClass().getName(), GLAllocation.generateDisplayLists(1));
            GL11.glNewList(staticPartMap.get(this.getClass().getName()), GL11.GL_COMPILE);
            for(TurboList list :groups) {
                list.render(list.boxList);
            }
            GL11.glEndList();
            if(!ClientProxy.EnableAnimations) {
                for(TurboList list :groups) {
                    list.boxList = null;
                }
            }
        } else {
            Integer m = staticPartMap.get(this.getClass().getName());
            if(GL11.glIsList(m)) {
                GL11.glCallList(m);
            } else {
                staticPartMap.put(this.getClass().getName(), GLAllocation.generateDisplayLists(1));
                GL11.glNewList(staticPartMap.get(this.getClass().getName()), GL11.GL_COMPILE);
                for(TurboList list :groups) {
                    list.render(list.boxList);
                }
                GL11.glEndList();
            }
        }

        if (namedList != null) {
            for (int i = 0; i < namedList.size(); i++) {
                if (displayList.size() > i && GL11.glIsList(displayList.get(i))) {
                    GL11.glCallList(displayList.get(i));
                } else if (namedList.get(i) != null) {
                    displayList.add(GLAllocation.generateDisplayLists(1));
                    GL11.glNewList(displayList.get(i), GL11.GL_COMPILE);
                    GL11.glPushMatrix();
                    namedList.get(i).render();
                    GL11.glPopMatrix();
                    GL11.glEndList();
                }
            }
        }
    }

    @Override
    public List<ModelRendererTurbo> getnamedParts(){
        LinkedList<ModelRendererTurbo> turboList = new LinkedList<>();
        for(TurboList g: groups){
            turboList.addAll(g.getnamedParts());
        }
        return turboList;
    }
    
}
