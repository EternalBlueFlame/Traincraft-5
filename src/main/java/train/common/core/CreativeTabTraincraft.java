/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 *
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import train.common.library.TraincraftRegistry;

import java.util.List;

public class CreativeTabTraincraft extends CreativeTabs {
    public Item tabItem;

    /**instances the tab by handling it through the super.*/
    public CreativeTabTraincraft(String name, String MODID, String textureName) {
        super(CreativeTabs.getNextID(), name);
        tabItem= TraincraftRegistry.RegisterItem(new Item(),MODID,textureName,null);
    }

    public CreativeTabTraincraft(String name, Item itm) {
        super(CreativeTabs.getNextID(), name);
        tabItem= itm;
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(tabItem);
    }

    @Override
    public String getTranslatedTabLabel() {
        return StatCollector.translateToLocal(super.getTabLabel());
    }

    /**the icon for the tab. override this one*/
    public Item getTabItem(){return tabItem;}

    /**the icon for the tab. don't override this one*/
    @Override
    public Item getTabIconItem() {
        return getTabItem();
    }

    /**This is used to hide items from the creative tab, but could also be used for sorting.*/
    @SideOnly(Side.CLIENT)
    public void displayAllReleventItems(List p_78018_1_) {
        super.displayAllReleventItems(p_78018_1_);
    }

}