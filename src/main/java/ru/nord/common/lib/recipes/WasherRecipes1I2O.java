package ru.nord.common.lib.recipes;

import net.minecraft.item.ItemStack;
import ru.nord_core.common.recipes.abstracts.ARecipe1I2O;
import ru.nord_core.common.recipes.abstracts.ARecipes1I2O;
import ru.nord_core.common.recipes.interfaces.IRecipes1I2O;

public class WasherRecipes1I2O extends ARecipes1I2O {
    public static void addRecipe(ItemStack input,
                                 ItemStack output1,
                                 ItemStack output2, int needEnergy, float percent, float exp
    ) {
        ARecipe1I2O recipe = new ARecipe1I2O(input, output1, output2, needEnergy,
                percent, exp);
        INSTANCE().add(recipe);
    }

    private static IRecipes1I2O INSTANCE;

    public static IRecipes1I2O INSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new WasherRecipes1I2O();
        }
        return INSTANCE;
    }

}
