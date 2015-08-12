package ru.nord.common.lib.recipes;

import net.minecraft.item.ItemStack;
import ru.nord_core.common.recipes.Interfaces.IRecipes1I2O;
import ru.nord_core.common.recipes.abstracts.Recipe2I2O;
import ru.nord_core.common.recipes.abstracts.Recipes2I2O;

public class SmelterRecipes2I2O extends Recipes2I2O {
    public static void addRecipe(ItemStack input, ItemStack input2,
                                 ItemStack output1,
                                 ItemStack output2, int needEnergy, float percent, float exp
            , boolean soft) {
        Recipe2I2O recipe = new Recipe2I2O(input, input2, output1, output2, needEnergy,
                percent, exp, soft);
        INSTANCE().getRecipes().add(recipe);
    }

    private static IRecipes1I2O INSTANCE;

    public static IRecipes1I2O INSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new SmelterRecipes2I2O();
        }
        return INSTANCE;
    }

}
