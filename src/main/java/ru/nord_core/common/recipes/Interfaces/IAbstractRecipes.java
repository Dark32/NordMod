package ru.nord_core.common.recipes.Interfaces;

public interface IAbstractRecipes {
    enum TYPES {
        TYPE_1I2O,
        TYPE_2I2O,
    }

    TYPES getType();
}
