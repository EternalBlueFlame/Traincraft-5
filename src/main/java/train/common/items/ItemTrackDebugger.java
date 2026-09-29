package train.common.items;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
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
                CommonUtil.sendChat(player, "You are not allowed to to that!");
                return false;
            }

            Block block = CommonUtil.getBlockAt(world, x, y, z);
            if (block == BlockIDs.tcRail.block){
                TileTCRail tile = (TileTCRail) CommonUtil.getTileEntity(world, x, y, z);

                if (tile != null)
                    CommonUtil.sendChat(player, EnumChatFormatting.RED + "TileTCRail");
                assert tile != null;
                CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "Name: " +  EnumChatFormatting.WHITE + tile.getType() + EnumChatFormatting.GOLD + " ItemID " + EnumChatFormatting.WHITE + tile.getTrack().getItem());
                    CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "x: "    +  EnumChatFormatting.WHITE +  tile.xCoord + EnumChatFormatting.GOLD +  " y: " +  EnumChatFormatting.WHITE +tile.yCoord +  EnumChatFormatting.GOLD + " z: "+  EnumChatFormatting.WHITE + tile.zCoord);
                    CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "Meta: " +  EnumChatFormatting.WHITE + tile.getBlockMetadata());
                    CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "cx: "   +  EnumChatFormatting.WHITE + tile.cx  + EnumChatFormatting.GOLD + " cy: "+ EnumChatFormatting.WHITE + tile.cy  + EnumChatFormatting.GOLD + " cz: " + EnumChatFormatting.WHITE + tile.cz);
                    CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "r: " + EnumChatFormatting.WHITE + tile.r);
                    CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "SwitchState: " + EnumChatFormatting.WHITE + tile.getSwitchState() + EnumChatFormatting.GOLD + " ManualOverride: " +  EnumChatFormatting.GOLD + " SwitchSize: " + EnumChatFormatting.WHITE + tile.getTrackFromName().getSwitchSize());
                    CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "LinkedX: "    +  EnumChatFormatting.WHITE +  tile.linkedX + EnumChatFormatting.GOLD +  " LinkedY: " +  EnumChatFormatting.WHITE +tile.linkedY +  EnumChatFormatting.GOLD + " LinkedZ: "+  EnumChatFormatting.WHITE + tile.linkedZ);
                    CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "SlopeLength: "    +  EnumChatFormatting.WHITE +  tile.slopeLength + EnumChatFormatting.GOLD +  " SlopeHeight: " +  EnumChatFormatting.WHITE +  EnumChatFormatting.GOLD + " SlopeAngle: "+  EnumChatFormatting.WHITE + tile.slopeAngle);
                    CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "BallastMaterial: "    +  EnumChatFormatting.WHITE +  Block.getBlockById(tile.getBallastMaterial()).getLocalizedName() + EnumChatFormatting.GOLD +  " BallastMetadata: " +  EnumChatFormatting.WHITE +tile.ballastMetadata +  EnumChatFormatting.GOLD + " BallastColour: "+  EnumChatFormatting.WHITE + tile.ballastColour);


                    CommonUtil.sendChat(player, " ");
            }
            else  if (block == BlockIDs.tcRailGag.block){
                TileTCRailGag tile = (TileTCRailGag) CommonUtil.getTileEntity(world, x, y, z);
                if (tile != null) {
                    CommonUtil.sendChat(player, EnumChatFormatting.GREEN + "TileTCGag");
                    CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "Name: " +  EnumChatFormatting.WHITE + tile.type);
                    CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "x: "    +  EnumChatFormatting.WHITE +  tile.xCoord + EnumChatFormatting.GOLD +  " y: " +  EnumChatFormatting.WHITE + tile.yCoord +  EnumChatFormatting.GOLD + " z: "+  EnumChatFormatting.WHITE + tile.zCoord);
                    CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "Meta: " +  EnumChatFormatting.WHITE + tile.getBlockMetadata());
                    CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "OriginX: "    +  EnumChatFormatting.WHITE +  tile.originX + EnumChatFormatting.GOLD +  " OriginY: " +  EnumChatFormatting.WHITE + tile.originY +  EnumChatFormatting.GOLD + " OriginZ: "+  EnumChatFormatting.WHITE + tile.originZ);
                    CommonUtil.sendChat(player, EnumChatFormatting.GOLD + "CanPlaceRollingStockOnDiagonal: " +  EnumChatFormatting.WHITE + tile.canPlaceRollingstock);
                    CommonUtil.sendChat(player, " ");

                }
            }

            else if (BlockRailBase.func_150051_a(block)) {
                TileEntity tile = CommonUtil.getTileEntity(world, x, y, z);
                if (tile == null) {
                    return false;
                }


                    CommonUtil.sendChat(player, EnumChatFormatting.BLUE + "BlockRailBase");

            }
            else {
                CommonUtil.sendChat(player, "Not a rail");
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
