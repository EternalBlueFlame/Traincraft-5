package train.common.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import train.common.api.blocks.TileSwitch;
import train.common.api.blocks.TileTraincraft;
import train.common.blocks.TCBlocks;
import train.common.library.BlockIDs;
import train.common.library.Info;

import java.util.Random;

public class TileMFPBWigWag extends TileSwitch {

    public float rotation =0;
    public boolean flip=true, powered =false;


    @Override
    public boolean canUpdate()
    {
        return true;
    }
    @Override
    public void updateEntity() {
        super.updateEntity();
        if(worldObj.isRemote) {
            if (rotation > 20 || rotation < -20) {
                flip = !flip;
                worldObj.playSound(xCoord,yCoord,zCoord,Info.resourceLocation + ":" + "bell",1f,1f,true);

            }
            powered = getWorldObj().isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
            if (powered ) {
                rotation += flip ? 1.75f : -1.75f;
            } else {
                rotation = 0;
            }
        }
    }

}