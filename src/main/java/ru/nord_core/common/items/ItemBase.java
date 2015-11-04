package ru.nord_core.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBase extends Item {
    private final String modid;
    private String unlocalizedName;

    public ItemBase(String modid) {
        this.modid = modid;
    }



    @Override
    public String getUnlocalizedName() {
        return "item." + modid + "." + this.unlocalizedName;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item." + modid + "." + this.unlocalizedName;
    }

    @Override
    public Item setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }
}