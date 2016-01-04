package ru.nord_core.common.recipes.interfaces;

import net.minecraft.item.ItemStack;

public interface IRecipe1I2OOD extends IAbstractRecipe{

    /**
     * Входные данные
     *
     * @return предмет инпута
     */
    ItemStack getInput();

    /**
     * Входные данные по словарю
     * @return словарь руд
     */
    String getOreDictInput();

    /**
     * Количество предмета по словарю руд
     * @return количество
     */
    int getOreDictInputCount();
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

    /**
     * Добвлен ли рецепт через предмет или через словарь
     * Отвечает за поиск в словаре руд.
     * Если ложно - то проверка через словарь руд не делается
     * Если истина - по идёт проверка через словарь руд
     * @return через словарь?
     */
    boolean isSoftOre();


}
