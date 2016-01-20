package ru.nord.common.recipes.minetweaker;

import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import ru.nord.common.lib.recipes.FlowingRecipes1I2O;
import ru.nord.common.recipes.minetweaker.helpers.ItemStackHelper;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Created by nikit on 10.09.2015.
 */
@ZenClass("mods.nordmod.Flowing")
public class FlowingRecipe {

    //ItemStack input, ItemStack output1,ItemStack output2, int needEnergy, float percent, float exp
    @ZenMethod
    public static void addRecipe(IItemStack input, IItemStack output1, IItemStack output2, int needEnergy, float percent, float exp) {
        FlowingRecipes1I2O.addRecipe(ItemStackHelper.toStack(input), ItemStackHelper.toStack(output1), ItemStackHelper.toStack(output2), needEnergy, percent, exp);
    }

    @ZenMethod
    public static void removeRecipe(IItemStack input) {
        for (int i = 0; i < FlowingRecipes1I2O.INSTANCE().getRecipes().size(); i++)
            if (FlowingRecipes1I2O.INSTANCE().getRecipes().get(i).getInput().isItemEqual(ItemStackHelper.toStack(input)))
                FlowingRecipes1I2O.INSTANCE().getRecipes().remove(i);
    }
}
