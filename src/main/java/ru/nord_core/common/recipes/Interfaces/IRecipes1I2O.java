package ru.nord_core.common.recipes.Interfaces;

import net.minecraft.item.ItemStack;
import ru.nord_core.common.recipes.abstracts.Recipe1I2O;

import java.util.List;

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
     * Получаем переменную рецептов
      * @return рецепты
     */
    List<Recipe1I2O> getRecipes();

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
