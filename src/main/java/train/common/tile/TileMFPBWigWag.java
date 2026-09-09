package train.common.tile;

import ebf.tim.utility.CommonUtil;
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
        if(worldObj.isRemote) {
            if (rotation > 20 || rotation < -20) {
                flip = !flip;
                CommonUtil.playSound(this, Info.resourceLocation + ":" + "bell", 1f, 1f);

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