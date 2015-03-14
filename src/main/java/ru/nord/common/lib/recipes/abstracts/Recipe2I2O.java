package ru.nord.common.lib.recipes.abstracts;

import net.minecraft.item.ItemStack;
import ru.nord.common.lib.recipes.Interfaces.IRecipe2I2O;

public class Recipe2I2O extends Recipe1I2O implements IRecipe2I2O {

        private final ItemStack secondInput;
        private final boolean soft;


        public Recipe2I2O(ItemStack input, ItemStack secondInput,
                          ItemStack result,
                          ItemStack secondResult, int energy, float expirince, float exp, boolean soft) {
                super(input, result, secondResult, energy, expirince, exp);
                this.secondInput = secondInput;
                this.soft = soft;

        }

        @Override
        public ItemStack getSecondInput() {
                return secondInput;
        }

        @Override
        public boolean getSoft() {
                return soft;
        }


}
