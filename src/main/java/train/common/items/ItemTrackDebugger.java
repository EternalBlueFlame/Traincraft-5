package train.common.items;
import train.common.Traincraft;

import net.minecraft.util.math.BlockPos;

import net.minecraft.util.EnumHand;

import net.minecraft.util.EnumFacing;

import net.minecraft.util.EnumActionResult;

import net.minecraft.client.util.ITooltipFlag;

import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
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

public class ItemTrackDebugger extends Item implements train.common.items.ILegacyIcon {
    public ItemTrackDebugger() {
        super();
        maxStackSize = 1;
        setCreativeTab(null);
    }


    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float par8, float par9, float par10) {
    	ItemStack itemstack = player.getHeldItem(hand); int x = pos.getX(), y = pos.getY(), z = pos.getZ(); int par7 = facing.getIndex();



        if (!world.isRemote) {

            if (!(player.canUseCommand(2, "") && player.capabilities.isCreativeMode)){
                CommonUtil.sendChat(player, "You are not allowed to to that!");
                return EnumActionResult.PASS;
            }

            Block block = CommonUtil.getBlockAt(world, x, y, z);
            if (block == BlockIDs.tcRail.block){
                TileTCRail tile = (TileTCRail) CommonUtil.getTileEntity(world, x, y, z);

                if (tile != null)
                    CommonUtil.sendChat(player, TextFormatting.RED + "TileTCRail");
                assert tile != null;
                CommonUtil.sendChat(player, TextFormatting.GOLD + "Name: " +  TextFormatting.WHITE + tile.getType() + TextFormatting.GOLD + " ItemID " + TextFormatting.WHITE + tile.getTrack().getItem());
                    CommonUtil.sendChat(player, TextFormatting.GOLD + "x: "    +  TextFormatting.WHITE +  tile.getPos().getX() + TextFormatting.GOLD +  " y: " +  TextFormatting.WHITE +tile.getPos().getY() +  TextFormatting.GOLD + " z: "+  TextFormatting.WHITE + tile.getPos().getZ());
                    CommonUtil.sendChat(player, TextFormatting.GOLD + "Meta: " +  TextFormatting.WHITE + tile.getBlockMetadata());
                    CommonUtil.sendChat(player, TextFormatting.GOLD + "cx: "   +  TextFormatting.WHITE + tile.cx  + TextFormatting.GOLD + " cy: "+ TextFormatting.WHITE + tile.cy  + TextFormatting.GOLD + " cz: " + TextFormatting.WHITE + tile.cz);
                    CommonUtil.sendChat(player, TextFormatting.GOLD + "r: " + TextFormatting.WHITE + tile.r);
                    CommonUtil.sendChat(player, TextFormatting.GOLD + "SwitchState: " + TextFormatting.WHITE + tile.getSwitchState() + TextFormatting.GOLD + " ManualOverride: " +  TextFormatting.GOLD + " SwitchSize: " + TextFormatting.WHITE + tile.getTrackFromName().getSwitchSize());
                    CommonUtil.sendChat(player, TextFormatting.GOLD + "LinkedX: "    +  TextFormatting.WHITE +  tile.linkedX + TextFormatting.GOLD +  " LinkedY: " +  TextFormatting.WHITE +tile.linkedY +  TextFormatting.GOLD + " LinkedZ: "+  TextFormatting.WHITE + tile.linkedZ);
                    CommonUtil.sendChat(player, TextFormatting.GOLD + "SlopeLength: "    +  TextFormatting.WHITE +  tile.slopeLength + TextFormatting.GOLD +  " SlopeHeight: " +  TextFormatting.WHITE +  TextFormatting.GOLD + " SlopeAngle: "+  TextFormatting.WHITE + tile.slopeAngle);
                    CommonUtil.sendChat(player, TextFormatting.GOLD + "BallastMaterial: "    +  TextFormatting.WHITE +  Block.getBlockById(tile.getBallastMaterial()).getLocalizedName() + TextFormatting.GOLD +  " BallastMetadata: " +  TextFormatting.WHITE +tile.ballastMetadata +  TextFormatting.GOLD + " BallastColour: "+  TextFormatting.WHITE + tile.ballastColour);


                    CommonUtil.sendChat(player, " ");
            }
            else  if (block == BlockIDs.tcRailGag.block){
                TileTCRailGag tile = (TileTCRailGag) CommonUtil.getTileEntity(world, x, y, z);
                if (tile != null) {
                    CommonUtil.sendChat(player, TextFormatting.GREEN + "TileTCGag");
                    CommonUtil.sendChat(player, TextFormatting.GOLD + "Name: " +  TextFormatting.WHITE + tile.type);
                    CommonUtil.sendChat(player, TextFormatting.GOLD + "x: "    +  TextFormatting.WHITE +  tile.getPos().getX() + TextFormatting.GOLD +  " y: " +  TextFormatting.WHITE + tile.getPos().getY() +  TextFormatting.GOLD + " z: "+  TextFormatting.WHITE + tile.getPos().getZ());
                    CommonUtil.sendChat(player, TextFormatting.GOLD + "Meta: " +  TextFormatting.WHITE + tile.getBlockMetadata());
                    CommonUtil.sendChat(player, TextFormatting.GOLD + "OriginX: "    +  TextFormatting.WHITE +  tile.originX + TextFormatting.GOLD +  " OriginY: " +  TextFormatting.WHITE + tile.originY +  TextFormatting.GOLD + " OriginZ: "+  TextFormatting.WHITE + tile.originZ);
                    CommonUtil.sendChat(player, TextFormatting.GOLD + "CanPlaceRollingStockOnDiagonal: " +  TextFormatting.WHITE + tile.canPlaceRollingstock);
                    CommonUtil.sendChat(player, " ");

                }
            }

            else if (BlockRailBase.isRailBlock((block).getDefaultState())) {
                TileEntity tile = CommonUtil.getTileEntity(world, x, y, z);
                if (tile == null) {
                    return EnumActionResult.PASS;
                }


                    CommonUtil.sendChat(player, TextFormatting.BLUE + "BlockRailBase");

            }
            else {
                CommonUtil.sendChat(player, "Not a rail");
                return EnumActionResult.PASS;
            }





        }



        return super.onItemUse(player, world, pos, hand, facing, par8, par9, par10);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, @Nullable World world, List<String> par3List, ITooltipFlag flag) {
    	boolean par4 = flag.isAdvanced(); EntityPlayer par2EntityPlayer = Traincraft.proxy.getPlayer();
        par3List.add("\u00a77" + "Gets TileEntityData for current track");
    }

    @Override
    public String legacyIcon(int meta) {
        return "tc:item_track_debugger";
    }
}
