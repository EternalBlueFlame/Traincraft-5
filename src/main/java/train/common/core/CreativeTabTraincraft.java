/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 *
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.core;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ebf.tim.utility.CommonUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import train.common.library.TraincraftRegistry;

import java.util.List;

public class CreativeTabTraincraft extends CreativeTabs {
    public Item tabItem;

    /**instances the tab by handling it through the super.*/
    public CreativeTabTraincraft(String name, String MODID, String textureName) {
        super(CreativeTabs.getNextID(), name);
        tabItem= TraincraftRegistry.RegisterItem(new train.common.items.ItemTabIcon(MODID + ":" + textureName),MODID,textureName,null);
    }

    public CreativeTabTraincraft(String name, Item itm) {
        super(CreativeTabs.getNextID(), name);
        tabItem= itm;
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(tabItem);
    }

    @Override
    public String getTranslationKey() {
        return CommonUtil.translate(super.getTabLabel());
    }

    /**the icon for the tab. override this one*/
    public Item getTabItem(){return tabItem;}

    /**the icon for the tab. don't override this one*/
    @Override
    public ItemStack createIcon() {
        return new ItemStack(getTabItem());
    }

    /**This is used to hide items from the creative tab, but could also be used for sorting.*/
    @SideOnly(Side.CLIENT)
    @Override
    public void displayAllRelevantItems(net.minecraft.util.NonNullList<ItemStack> p_78018_1_) {
        super.displayAllRelevantItems(p_78018_1_);
    }

}