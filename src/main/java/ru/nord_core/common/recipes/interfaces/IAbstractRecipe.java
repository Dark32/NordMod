package ru.nord_core.common.recipes.interfaces;

public interface IAbstractRecipe {
    enum TYPES {
        TYPE_1I2O,
        TYPE_1I2OOD(true),
        TYPE_2I2O,;
        private final boolean oredict;

        TYPES(boolean oredict) {
             this.oredict = oredict;
        }

        TYPES() {
            this.oredict = false;
        }

        public boolean isOredict() {
            return oredict;
        }
    }

    TYPES getType();
}
