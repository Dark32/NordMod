package ru.nord;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.nord.common.lib.tabs.TabNord;

public class NordTabs {
    public static final CreativeTabs tabGeneral = new TabNord("tabGeneral");
    public static final CreativeTabs tabMachine = new TabNord("tabMachine");
    public static final CreativeTabs tabDecoration = new TabNord("tabDecoration");

    public static void postInit() {
        ((TabNord) tabGeneral).setTabIconItem(new ItemStack(Items.bed));
        ((TabNord) tabMachine).setTabIconItem(new ItemStack(NordBloks.accumulatorBlock));
        ((TabNord) tabDecoration).setTabIconItem(new ItemStack(NordBloks.decoStoneBlock[1],1,1));
    }
}