package train.common.items;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import train.common.library.BlockIDs;
import train.common.library.Info;
import train.common.tile.TileTCRail;
import train.common.tile.TileTCRailGag;

import java.util.List;

public class ItemTrackDebugger extends Item {
    public ItemTrackDebugger() {
        super();
        maxStackSize = 1;
        setCreativeTab(null);
    }


    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {



        if (!world.isRemote) {

            if (!(player.canCommandSenderUseCommand(2, "") && player.capabilities.isCreativeMode)){
                player.addChatMessage(new TextComponentString("You are not allowed to to that!"));
                return false;
            }

            Block block = world.getBlock(x, y, z);
            if (block == BlockIDs.tcRail.block){
                TileTCRail tile = (TileTCRail) world.getTileEntity(x, y, z);

                if (tile != null)
                    player.addChatMessage(new TextComponentString(TextFormatting.RED + "TileTCRail"));
                assert tile != null;
                player.addChatMessage(new TextComponentString( TextFormatting.GOLD + "Name: " +  TextFormatting.WHITE + tile.getType() + TextFormatting.GOLD + " ItemID " + TextFormatting.WHITE + tile.getTrack().getItem()));
                    player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "x: "    +  TextFormatting.WHITE +  tile.xCoord + TextFormatting.GOLD +  " y: " +  TextFormatting.WHITE +tile.yCoord +  TextFormatting.GOLD + " z: "+  TextFormatting.WHITE + tile.zCoord));
                    player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "Meta: " +  TextFormatting.WHITE + tile.getBlockMetadata()));
                    player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "cx: "   +  TextFormatting.WHITE + tile.cx  + TextFormatting.GOLD + " cy: "+ TextFormatting.WHITE + tile.cy  + TextFormatting.GOLD + " cz: " + TextFormatting.WHITE + tile.cz));
                    player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "r: " + TextFormatting.WHITE + tile.r));
                    player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "SwitchState: " + TextFormatting.WHITE + tile.getSwitchState() + TextFormatting.GOLD + " ManualOverride: " +  TextFormatting.GOLD + " SwitchSize: " + TextFormatting.WHITE + tile.getTrackFromName().getSwitchSize()) );
                    player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "LinkedX: "    +  TextFormatting.WHITE +  tile.linkedX + TextFormatting.GOLD +  " LinkedY: " +  TextFormatting.WHITE +tile.linkedY +  TextFormatting.GOLD + " LinkedZ: "+  TextFormatting.WHITE + tile.linkedZ));
                    player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "SlopeLength: "    +  TextFormatting.WHITE +  tile.slopeLength + TextFormatting.GOLD +  " SlopeHeight: " +  TextFormatting.WHITE +  TextFormatting.GOLD + " SlopeAngle: "+  TextFormatting.WHITE + tile.slopeAngle));
                    player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "BallastMaterial: "    +  TextFormatting.WHITE +  Block.getBlockById(tile.getBallastMaterial()).getLocalizedName() + TextFormatting.GOLD +  " BallastMetadata: " +  TextFormatting.WHITE +tile.ballastMetadata +  TextFormatting.GOLD + " BallastColour: "+  TextFormatting.WHITE + tile.ballastColour));


                    player.addChatMessage(new TextComponentString(" "));
            }
            else  if (block == BlockIDs.tcRailGag.block){
                TileTCRailGag tile = (TileTCRailGag) world.getTileEntity(x, y, z);
                if (tile != null) {
                    player.addChatMessage(new TextComponentString(TextFormatting.GREEN + "TileTCGag"));
                    player.addChatMessage(new TextComponentString( TextFormatting.GOLD + "Name: " +  TextFormatting.WHITE + tile.type));
                    player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "x: "    +  TextFormatting.WHITE +  tile.xCoord + TextFormatting.GOLD +  " y: " +  TextFormatting.WHITE + tile.yCoord +  TextFormatting.GOLD + " z: "+  TextFormatting.WHITE + tile.zCoord));
                    player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "Meta: " +  TextFormatting.WHITE + tile.getBlockMetadata()));
                    player.addChatMessage(new TextComponentString(TextFormatting.GOLD + "OriginX: "    +  TextFormatting.WHITE +  tile.originX + TextFormatting.GOLD +  " OriginY: " +  TextFormatting.WHITE + tile.originY +  TextFormatting.GOLD + " OriginZ: "+  TextFormatting.WHITE + tile.originZ));
                    player.addChatMessage(new TextComponentString( TextFormatting.GOLD + "CanPlaceRollingStockOnDiagonal: " +  TextFormatting.WHITE + tile.canPlaceRollingstock));
                    player.addChatMessage(new TextComponentString(" "));

                }
            }

            else if (BlockRailBase.func_150051_a(block)) {
                TileEntity tile = world.getTileEntity(x, y, z);
                if (tile == null) {
                    return false;
                }


                    player.addChatMessage(new TextComponentString(TextFormatting.BLUE + "BlockRailBase"));

            }
            else {
                player.addChatMessage(new TextComponentString("Not a rail"));
                return false;
            }





        }



        return super.onItemUse(itemstack, player, world, x, y, z, par7, par8, par9, par10);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Info.modID.toLowerCase() + ":item_track_debugger");
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("\u00a77" + "Gets TileEntityData for current track");
    }
}
