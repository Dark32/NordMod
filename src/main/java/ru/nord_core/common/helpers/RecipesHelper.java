package ru.nord_core.common.helpers;

import net.minecraft.item.ItemStack;
public class RecipesHelper {
    /**
     * Сравниваем стак
     * @param input вход
     * @param recepie требование
     * @return рвенство
     */
    public static boolean compare(ItemStack input, ItemStack recepie) {
        return compare(input,recepie, true);
    }

    /**
     * Сравниваем стак без/c учёта количества
     * @param input вход
     * @param recepie требования
     * @param eqsi с/без учёта
     * @return равенство
     */
    public static boolean compare(ItemStack input, ItemStack recepie, boolean eqsi) {
        boolean check_item = input.getItem() == recepie.getItem()
                && (input.getItemDamage() == recepie.getItemDamage() || recepie.getItemDamage() == 32767);
        boolean chesk_size =!eqsi ? input.stackSize >= recepie.stackSize : input.stackSize == recepie.stackSize;
        return check_item && chesk_size;
    }

    /**
     * Сравниваем два стака
     * @param input1 первый вход
     * @param recepie1 первое требование
     * @param input2 второй вход
     * @param recepie2 второе требование
     * @param soft перестановка
     * @param eqsi с/без учёта
     * @return равенство
     */
    public static boolean compare(ItemStack input1, ItemStack recepie1,ItemStack input2, ItemStack recepie2, boolean soft, boolean eqsi){
        boolean check_11= compare(input1, recepie1, eqsi);
        boolean check_22= compare(input2, recepie2, eqsi);
        boolean check_12= false;
        boolean check_21= false;
        if (soft){
            check_12= compare(input1, recepie2, eqsi);
            check_21= compare(input2, recepie1, eqsi);
        }
        return (check_11 && check_22) || (check_12 && check_21);
    }
}
