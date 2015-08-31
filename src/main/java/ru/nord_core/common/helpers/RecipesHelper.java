package ru.nord_core.common.helpers;

import net.minecraft.item.ItemStack;
public class RecipesHelper {

    public static boolean compare(ItemStack input, ItemStack recepie) {
        return compare(input,recepie, true);
    }
    public static boolean compare(ItemStack input, ItemStack recepie, boolean eqsi) {
        boolean check_item = input.getItem() == recepie.getItem()
                && (input.getItemDamage() == recepie.getItemDamage() || recepie.getItemDamage() == 32767);
        boolean chesk_size =!eqsi ? input.stackSize >= recepie.stackSize : input.stackSize == recepie.stackSize;
        return check_item && chesk_size;
    }
}
