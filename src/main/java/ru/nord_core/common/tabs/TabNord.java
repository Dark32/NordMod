package ru.nord_core.common.tabs;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TabNord extends CreativeTabs {
    ItemStack item;

    public TabNord(String lable) {
        super(lable);
    }

    public void setTabIconItem(ItemStack item) {
        this.item = item;
    }

    @Override
    public Item getTabIconItem() {
        return item.getItem();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {
        return item;
    }
}
