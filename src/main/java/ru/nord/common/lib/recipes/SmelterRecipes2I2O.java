package ru.nord.common.lib.recipes;

import net.minecraft.item.ItemStack;
import ru.nord_core.common.recipes.abstracts.ARecipe2I2O;
import ru.nord_core.common.recipes.abstracts.ARecipes2I2O;
import ru.nord_core.common.recipes.interfaces.IRecipes2I2O;

public class SmelterRecipes2I2O extends ARecipes2I2O {
    public static void addRecipe(ItemStack input, ItemStack input2,
                                 ItemStack output1,
                                 ItemStack output2, int needEnergy, float percent, float exp
            , boolean soft) {
        ARecipe2I2O recipe = new ARecipe2I2O(input, input2, output1, output2, needEnergy,
                percent, exp, soft);
        INSTANCE().add(recipe);
    }

    private static IRecipes2I2O INSTANCE;

    public static IRecipes2I2O INSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new SmelterRecipes2I2O();
        }
        return INSTANCE;
    }

}
