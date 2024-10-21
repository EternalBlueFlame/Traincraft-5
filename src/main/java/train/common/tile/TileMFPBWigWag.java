package train.common.tile;

import train.common.api.blocks.TileSwitch;
import train.common.blocks.BlockMFPBWigWag;
import train.common.library.Info;

public class TileMFPBWigWag extends TileSwitch {

    public TileMFPBWigWag(){

    }
    public TileMFPBWigWag(BlockMFPBWigWag block ){
        host = block;
    }

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