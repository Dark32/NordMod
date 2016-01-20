package ru.nord.common.recipes.minetweaker;

import minetweaker.api.item.IItemStack;
import ru.nord.common.lib.recipes.ExtractorRecipes1I2O;
import ru.nord.common.recipes.minetweaker.helpers.ItemStackHelper;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Created by nikit on 10.09.2015.
 */
@ZenClass("mods.nordmod.Extractor")
public class ExtractorRecipe {

    @ZenMethod
    public static void addRecipe(IItemStack input, IItemStack output1, IItemStack output2, int needEnergy, float percent, float exp) {
        ExtractorRecipes1I2O.addRecipe(ItemStackHelper.toStack(input), ItemStackHelper.toStack(output1), ItemStackHelper.toStack(output2), needEnergy, percent, exp);
    }

    @ZenMethod
    public static void removeRecipe(IItemStack input) {
        for (int i = 0; i < ExtractorRecipes1I2O.INSTANCE().getRecipes().size(); i++)
            if (ExtractorRecipes1I2O.INSTANCE().getRecipes().get(i).getInput().isItemEqual(ItemStackHelper.toStack(input)))
                ExtractorRecipes1I2O.INSTANCE().getRecipes().remove(i);
    }
}
