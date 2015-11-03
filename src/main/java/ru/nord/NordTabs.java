package ru.nord;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.nord.common.utils.enums.EnumFoodNord;
import ru.nord.common.utils.enums.EnumGlassFood;
import ru.nord_core.common.tabs.TabNord;
import ru.nord_core.common.utils.enums.EnumMetal;

import java.util.Random;

public class NordTabs {
    public static final CreativeTabs tabDecoration = new TabNord("tabDecoration");
    public static final CreativeTabs tabMachine = new TabNord("tabMachine");
    public static final CreativeTabs tabColorStone = new TabNord("tabColorStone");
    public static final CreativeTabs tabMetallurgy = new TabNord("tabMetallurgy");
    public static final CreativeTabs tabLamp = new TabNord("tabLamp");
    public static final CreativeTabs tabFood = new TabNord("tabFood");
    public static final CreativeTabs tabOthers = new TabNord("Others");
    public static Random RandomNumb = new Random();
    public static void postInit() {
        ((TabNord) tabDecoration).setTabIconItem(new ItemStack(NordBloks.empireDecoration1, 1,3));
        ((TabNord) tabMachine).setTabIconItem(new ItemStack(NordBloks.accumulatorBlock));
        ((TabNord) tabColorStone).setTabIconItem(new ItemStack(NordBloks.decoStoneBlock[1],1,1));
        ((TabNord) tabMetallurgy).setTabIconItem(new ItemStack(NordItems.itemIngot,1,RandomNumb.nextInt(EnumMetal.values().length)));
        ((TabNord) tabLamp).setTabIconItem(new ItemStack(NordBloks.empireFloorLamp1,1,0));
        ((TabNord) tabFood).setTabIconItem(new ItemStack(NordItems.itemFoodNord,1,RandomNumb.nextInt(EnumFoodNord.values().length)));
        ((TabNord) tabOthers).setTabIconItem(new ItemStack(NordItems.itemGlassFood, 1,0));
    }
}