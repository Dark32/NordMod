package ru.nord_core.common.recipes.interfaces;

import net.minecraft.item.ItemStack;
import ru.nord_core.common.recipes.abstracts.ARecipe1I2OOD;

import java.util.List;

/**
 * Интерфейс описывающий абстрактный класс рецептов
 * <ul>
 * <li> с одним входом  </li>
 * <li> по ItemStack или String из словаря руд</li>
 * <li> двумя выходами  </li>
 * <li> требованием к энергии  </li>
 * <li> шансом второго выхода  </li>
 * <li> опытом за переработку  </li>
 * </ul>
 *
 * @author andrew
 */
public interface IRecipes1I2OOD extends IAbstractRecipes {

    /**
     * Получаем переменную рецептов
      * @return рецепты
     */
    List<ARecipe1I2OOD> getRecipes();

    /**
     * Получаем рецепт по предмету
     *
     * @param item из чего
     * @return рецепт
     */
    IRecipe1I2OOD getRecipe(ItemStack item);

    /**
     * Получаем рецепт по индексу
     *
     * @param index - индекс
     * @return рецепт
     */
    IRecipe1I2OOD getRecipe(int index);

    /**
     * Получаем индекс рецепта по предмету
     *
     * @param item предмет
     * @return индекс
     */
    int getIndexRecipe(ItemStack item);

}
