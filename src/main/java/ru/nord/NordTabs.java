package ru.nord;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.nord.common.items.ItemIngot;
import ru.nord.common.lib.tabs.TabNord;

import java.util.Random;

public class NordTabs {
    public static final CreativeTabs tabGeneral = new TabNord("tabGeneral");
    public static final CreativeTabs tabMachine = new TabNord("tabMachine");
    public static final CreativeTabs tabDecoration = new TabNord("tabDecoration");
    public static final CreativeTabs tabMetallurgy = new TabNord("tabMetallurgy");
    public static Random RandomNumb = new Random();
    public static void postInit() {
        ((TabNord) tabGeneral).setTabIconItem(new ItemStack(Items.bed));
        ((TabNord) tabMachine).setTabIconItem(new ItemStack(NordBloks.accumulatorBlock));
        ((TabNord) tabDecoration).setTabIconItem(new ItemStack(NordBloks.decoStoneBlock[1],1,1));
        ((TabNord) tabMetallurgy).setTabIconItem(new ItemStack(NordItems.itemIngot,1,RandomNumb.nextInt(ItemIngot.nameIngot.length)));
    }
}