package ru.nord.common.lib.recipes.Interfaces;

import net.minecraft.item.ItemStack;

public interface IRecipe2I2O extends IRecipe1I2O {
        /**
         * Входные данные
         *
         * @return предмет инпута
         */
        ItemStack getSecondInput();

        /**
         * Можно ли поменять входы местами
         *
         * @return boolean
         */
        boolean getSoft();
}
