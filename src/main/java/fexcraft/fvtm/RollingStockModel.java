package fexcraft.fvtm;

import ebf.tim.utility.ClientProxy;
import ebf.tim.utility.DebugUtil;
import fexcraft.tmt.slim.ModelBase;
import fexcraft.tmt.slim.ModelRendererTurbo;
import fexcraft.tmt.slim.Tessellator;
import fexcraft.tmt.slim.TexturedPolygon;
import net.minecraft.client.Minecraft;
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

        if(namedList ==null){return;}
        ModelRendererTurbo part;
        for(int i = 0; i< namedList.size(); i++) {
            //for animations to work we have to limit the displaylist cache to ONLY the geometry, and then
            //    the position and offsets must be done manually every frame.
            if (displayList.size() > i) {
                part = namedList.get(i);
                if (!part.showModel) {
                    continue;
                }
                GL11.glPushMatrix();
                if (part.ignoresLighting) {
                    Minecraft.getMinecraft().entityRenderer.disableLightmap(1D);
                }
                GL11.glTranslatef(part.rotationPointX * 0.0625F, part.rotationPointY * 0.0625F, part.rotationPointZ * 0.0625F);
                GL11.glRotatef(part.rotateAngleY, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(part.rotateAngleZ, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(part.rotateAngleX, 1.0F, 0.0F, 0.0F);
                if (GL11.glIsList(displayList.get(i))) {
                    GL11.glCallList(displayList.get(i));
                } else {
                    int disp = GLAllocation.generateDisplayLists(1);
                    displayList.set(i, disp);
                    GL11.glNewList(disp, GL11.GL_COMPILE);
                    for (TexturedPolygon poly : namedList.get(i).faces) {
                        Tessellator.getInstance().drawTexturedVertsWithNormal(poly, 0.0625F);
                    }
                    GL11.glEndList();
                }

                GL11.glTranslatef(-part.rotationPointX * 0.0625F, -part.rotationPointY * 0.0625F, -part.rotationPointZ * 0.0625F);
                if (part.ignoresLighting) {
                    Minecraft.getMinecraft().entityRenderer.enableLightmap(1D);
                }
                GL11.glPopMatrix();

            } else if (namedList.get(i) != null) {
                int disp = GLAllocation.generateDisplayLists(1);
                displayList.add(disp);
                GL11.glNewList(disp, GL11.GL_COMPILE);
                for (TexturedPolygon poly : namedList.get(i).faces) {
                    Tessellator.getInstance().drawTexturedVertsWithNormal(poly, 0.0625F);
                }
                GL11.glEndList();
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
