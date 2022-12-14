package train.blocks.switchstand;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ebf.tim.blocks.BlockSwitch;
import ebf.tim.blocks.TileSwitch;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import train.blocks.TCBlocks;
import train.library.Info;

import java.util.Random;

public class TileSwitchStand extends TileSwitch {

    public TileSwitchStand(BlockSwitch host){
        super(host);
    }

    public TileSwitchStand(){
        super();
    }

    @Override
    public boolean canUpdate(){return true;}

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(xCoord-1, yCoord-1, zCoord-1, xCoord + 2, yCoord + 2, zCoord + 2);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderModel(){
        //item render
        if(getWorldObj()==null){
            org.lwjgl.opengl.GL11.glTranslated( -0.2,  0,  0);
            org.lwjgl.opengl.GL11.glScalef(0.65f,0.65f,0.65f);

            new train.render.models.ModelSwitchStandOff().render();
        }
        //inworld render
        else {
            org.lwjgl.opengl.GL11.glTranslated(0, -0.2f, 0);

            //extra offset for track height
            if(ebf.tim.utility.ClientProxy.railSkin==3){
                org.lwjgl.opengl.GL11.glTranslatef(0, 0.09f, 0);
            } else {
                org.lwjgl.opengl.GL11.glTranslatef(0, 0.01875f, 0);
            }

            //on
            if (getEnabled()) {
                new train.render.models.ModelSwitchStandOn().render();
            } else {//off
                new train.render.models.ModelSwitchStandOff().render();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ResourceLocation getTexture(int x, int y, int z){
        if (getEnabled()) {
            return new ResourceLocation(Info.resourceLocation,Info.modelTexPrefix + "switchStand_uv_draw_1.png");
        } else {
            return new ResourceLocation(Info.resourceLocation,Info.modelTexPrefix + "switchStand_uv_draw_2.png");
        }
    }
}