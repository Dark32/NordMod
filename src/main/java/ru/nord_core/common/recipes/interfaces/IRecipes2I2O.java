package ru.nord_core.common.recipes.interfaces;

import net.minecraft.item.ItemStack;
import ru.nord_core.common.recipes.abstracts.ARecipe2I2O;

import java.util.List;

/**
 * Интерфейс описывающий абстрактный класс рецептов
 * <ul>
 * <li> с двумя входами  </li>
 * <li> двумя выходами  </li>
 * <li> требованием к энергии  </li>
 * <li> шансом второго выхода  </li>
 * <li> опытом за переработку  </li>
 * </ul>
 *
 * @author andrew
 */
public interface IRecipes2I2O extends IAbstractRecipes {

    /**
     * Получаем переменную рецептов
     * @return рецепты
     */
    List<ARecipe2I2O> getRecipes();
    /**
     * Получаем рецепт по предмету
     *
     * @param item из чего
     * @param slot номер слота
     * @return рецепт
     */
    IRecipe2I2O getPartRecipe(ItemStack item, int slot);

    /**
     * Получаем рецепт по индексу
     *
     * @param index - индекс
     * @param slot  номер слота
     * @return рецепт
     */
    IRecipe2I2O getPartRecipe(int index, int slot);

    /**
     * Получаем индекс рецепта по предмету
     *
     * @param item предмет
     * @param slot номер слота
     * @return индекс
     */
    int getIndexPartRecipe(ItemStack item, int slot);

    /**
     * Получаем рецепт по предмету
     *
     * @param item  из чего
     * @param item2 из чего
     * @return рецепт
     */


    IRecipe2I2O getRecipe(ItemStack item, ItemStack item2);

    /**
     * Получаем рецепт по предмету
     *
     * @param index номер рецепта
     * @return рецепт
     */
    IRecipe2I2O getRecipe(int index);

    /**
     * Получаем индекс рецепта по предмету
     *
     * @param item предмет
     * @return индекс
     */
    int getIndexRecipe(ItemStack item, ItemStack item2);

}
