package ru.nord.common.lib.recipes;

import net.minecraft.item.ItemStack;
import ru.nord.common.lib.recipes.Interfaces.IRecipes1I2O;
import ru.nord.common.lib.recipes.abstracts.Recipe1I2O;
import ru.nord.common.lib.recipes.abstracts.Recipes1I2O;

public class GreatFurnaceRecipes1I2O extends Recipes1I2O {
        public static void addRecipe(ItemStack input, ItemStack output1,
                                     ItemStack output2, int needEnergy, float percent, float exp) {
                Recipe1I2O recipe = new Recipe1I2O(input, output1, output2, needEnergy,
                        percent, exp);
                recipes.add(recipe);
        }

        private static IRecipes1I2O INSTANCE;

        public static IRecipes1I2O INSTANCE() {
                if (INSTANCE == null) {
                        INSTANCE = new GreatFurnaceRecipes1I2O();
                }
                return INSTANCE;
        }
}
