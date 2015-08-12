package ru.nord_core.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemMetaData extends Item {
    private final String[] names;

    public ItemMetaData(String[] names) {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.names = names;
    }
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < names.length) {
            return super.getUnlocalizedName() + "." + names[stack.getMetadata()];
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        for (int i = 0; i < names.length; ++i) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }
}
