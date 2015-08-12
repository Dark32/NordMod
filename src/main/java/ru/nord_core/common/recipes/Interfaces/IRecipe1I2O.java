package ru.nord_core.common.recipes.Interfaces;

import net.minecraft.item.ItemStack;

public interface IRecipe1I2O {
    /**
     * Входные данные
     *
     * @return предмет инпута
     */
    ItemStack getInput();

    /**
     * Результат
     *
     * @return
     */
    ItemStack getResult();

    /**
     * Второй резльтат
     *
     * @return
     */
    ItemStack getSecondResult();

    /**
     * Требуемая энергия
     *
     * @return
     */
    int getEnergy();

    /**
     * Получаемый опыт
     *
     * @return
     */
    float getExpirince();

    /**
     * Шанс второго выхода
     *
     * @return
     */
    float getPercent();

}
