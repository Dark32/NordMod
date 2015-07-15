package ru.nord.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nord.common.lib.utils.Version;

public class ItemBase extends Item {
    public ItemBase() {
    }

    private String unlocalizedName;

    @Override
    public String getUnlocalizedName() {
        return "item." + Version.MODID + "." + this.unlocalizedName;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item." + Version.MODID + "." + this.unlocalizedName;
    }

    @Override
    public Item setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }
}