package ru.nord.common.recipes.minetweaker;

import minetweaker.api.item.IItemStack;
import ru.nord.common.lib.recipes.SmelterRecipes2I2O;
import ru.nord.common.recipes.minetweaker.helpers.ItemStackHelper;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Created by nikit on 10.09.2015.
 */
@ZenClass("mods.nordmod.Smelter")
public class SmelterRecipe {
    @ZenMethod
    public static void addRecipe(IItemStack input1,IItemStack input2, IItemStack output1, IItemStack output2, int needEnergy, float percent, float exp,boolean soft) {
        SmelterRecipes2I2O.addRecipe(ItemStackHelper.toStack(input1),ItemStackHelper.toStack(input2), ItemStackHelper.toStack(output1), ItemStackHelper.toStack(output2), needEnergy, percent, exp,soft);
    }

    @ZenMethod
    public static void removeRecipe(IItemStack input1,IItemStack input2) {
        for (int i = 0; i < SmelterRecipes2I2O.INSTANCE().getRecipes().size(); i++)
            if (SmelterRecipes2I2O.INSTANCE().getRecipes().get(i).getInput().isItemEqual(ItemStackHelper.toStack(input1))&&SmelterRecipes2I2O.INSTANCE().getRecipes().get(i).getSecondInput().isItemEqual(ItemStackHelper.toStack(input2)))
                SmelterRecipes2I2O.INSTANCE().getRecipes().remove(i);
    }
}
