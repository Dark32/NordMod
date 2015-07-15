package ru.nord.common.lib.utils;

import ru.nord.common.lib.utils.enums.EnumColors;

public class Things {


    public static int getColor(int i) {
        return  EnumColors.values()[i & 15].getSecondColor();
    }
}
