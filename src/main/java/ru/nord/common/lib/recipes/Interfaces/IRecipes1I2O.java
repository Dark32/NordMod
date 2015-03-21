package ru.nord.common.lib.recipes.Interfaces;

import net.minecraft.item.ItemStack;

/**
 * Интерфейс описывающий абстрактный класс рецептов
 * <ul>
 * <li> с одним входом  </li>
 * <li> двумя выходами  </li>
 * <li> требованием к энергии  </li>
 * <li> шансом второго выхода  </li>
 * <li> опытом за переработку  </li>
 * </ul>
 *
 * @author andrew
 */
public interface IRecipes1I2O extends IAbstractRecipes {

    /**
     * Получаем сингелтон
     *
     * @return Себя
     */
    //IRecipes1I2O INSTANCE();

    /**
     * Получаем рецепт по предмету
     *
     * @param item из чего
     * @return рецепт
     */
    IRecipe1I2O getRecipe(ItemStack item);

    /**
     * Получаем рецепт по индексу
     *
     * @param index - индекс
     * @return рецепт
     */
    IRecipe1I2O getRecipe(int index);

    /**
     * Получаем индекс рецепта по предмету
     *
     * @param item предмет
     * @return индекс
     */
    int getIndexRecipe(ItemStack item);

}
