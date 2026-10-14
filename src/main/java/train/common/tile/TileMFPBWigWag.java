package train.common.tile;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.ITickable;

import ebf.tim.utility.CommonUtil;
import train.common.api.blocks.TileSwitch;
import train.common.blocks.BlockMFPBWigWag;
import train.common.library.Info;

public class TileMFPBWigWag extends TileSwitch implements ITickable {

    public TileMFPBWigWag(){

    }
    public TileMFPBWigWag(BlockMFPBWigWag block ){
        host = block;
    }

    public float rotation =0;
    public boolean flip=true, powered =false;


    public boolean canUpdateLegacy() {
        return true;
    }
    @Override
    public void update() {
        super.update();
        if(world.isRemote) {
            if (rotation > 20 || rotation < -20) {
                flip = !flip;
                CommonUtil.playSound(this, Info.resourceLocation + ":" + "bell", 1f, 1f);

            }
            powered = world.isBlockPowered(new BlockPos(getPos().getX(), getPos().getY(), getPos().getZ()));
            if (powered ) {
                rotation += flip ? 1.75f : -1.75f;
            } else {
                rotation = 0;
            }
        }
    }

}