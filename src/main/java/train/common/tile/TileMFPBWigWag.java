package train.common.tile;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
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
        if(getWorld().isRemote) {
            if (rotation > 20 || rotation < -20) {
                flip = !flip;
                getWorld().playSound(xCoord,yCoord,zCoord,Info.resourceLocation + ":" + "bell",1f,1f,true);

            }
            powered = getWorld().isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
            if (powered ) {
                rotation += flip ? 1.75f : -1.75f;
            } else {
                rotation = 0;
            }
        }
    }

}