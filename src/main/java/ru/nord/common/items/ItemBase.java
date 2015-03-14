package ru.nord.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nord.Nord;

public class ItemBase extends Item{
    public ItemBase(){    }
    private String unlocalizedName;

    public String getUnlocalizedName()
    {
        return "item." + Nord.MODID + "."+ this.unlocalizedName;
    }
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item." + Nord.MODID + "."+ this.unlocalizedName;
    }
    public Item setUnlocalizedName(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
        return this;
    }
}