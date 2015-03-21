package ru.nord;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.nord.common.lib.tabs.TabNord;

public class NordTabs {
    public static final CreativeTabs tabGeneral = new TabNord("tabGeneral");

    public static void postInit() {
        ((TabNord) tabGeneral).setTabIconItem(new ItemStack(Items.bed));
    }
}