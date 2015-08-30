package ru.nord_core.common.recipes.abstracts;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import ru.nord_core.common.recipes.interfaces.IAbstractRecipes;


abstract public class ARecipes implements IAbstractRecipes {

    @Override
    public final boolean checkInputOreDictoary(ItemStack input, ItemStack recepies) {
        int[] input_ids = OreDictionary.getOreIDs(input);
        int[] recepies_ids = OreDictionary.getOreIDs(recepies);
        for (int iid : input_ids) {
            for (int rid : recepies_ids) {
                if (iid == rid) {
                    return true;
                }
            }
        }
        return false;
    }

    protected final boolean compare(ItemStack input, ItemStack recepie) {
        boolean check_item = input.getItem() == recepie.getItem()
                && (input.getItemDamage() == recepie.getItemDamage() || recepie.getItemDamage() == 32767);
        boolean check_oredict = checkInputOreDictoary(input, recepie);
        boolean chesk_size =input.stackSize >= recepie.stackSize;
        return (check_item || check_oredict) && chesk_size;
    }
}
