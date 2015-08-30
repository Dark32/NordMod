package ru.nord_core.common.recipes.interfaces;

import net.minecraft.item.ItemStack;

public interface IAbstractRecipes {
    enum TYPES {
        TYPE_1I2O,
        TYPE_2I2O,
    }

    TYPES getType();
    /**
     * Проверяем совападают ли стаки по словарю руд
     * @param input что пришло
     * @param recepies что есть
     * @return совпали ли
     */
    boolean checkInputOreDictoary(ItemStack input, ItemStack recepies);
}
