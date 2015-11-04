package ru.nord_deco;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.nord_core.common.tabs.TabNord;

import java.util.Random;

public class NordTabsDeco {
    public static final CreativeTabs tabDecoration = new TabNord("tabDecoration");
    public static Random RandomNumb = new Random();
    public static void postInit() {
        ((TabNord) tabDecoration).setTabIconItem(new ItemStack(Items.coal, 1,3));
    }
}