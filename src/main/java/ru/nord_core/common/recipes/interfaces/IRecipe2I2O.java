package ru.nord_core.common.recipes.interfaces;

import net.minecraft.item.ItemStack;

public interface IRecipe2I2O extends IAbstractRecipe {
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
    /**
     * Входные данные
     *
     * @return предмет инпута
     */
    ItemStack getInput();

    /**
     * Результат
     *
     * @return стак результата
     */
    ItemStack getResult();

    /**
     * Второй резльтат
     *
     * @return стак второгорезультата
     */
    ItemStack getSecondResult();

    /**
     * Требуемая энергия
     *
     * @return значение
     */
    int getEnergy();

    /**
     * Получаемый опыт
     *
     * @return значение
     */
    float getExpirince();

    /**
     * Шанс второго выхода
     *
     * @return шанс
     */
    float getPercent();
}
