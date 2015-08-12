package ru.nord_core.common.utils;

import ru.nord_core.common.utils.enums.EnumColors;

public class Things {


    public static int getColor(int i) {
        return  EnumColors.values()[i & 15].getSecondColor();
    }
}
