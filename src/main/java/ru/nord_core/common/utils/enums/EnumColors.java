package ru.nord_core.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IColorizeEnum;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumColors implements IMetadataEnum, IColorizeEnum {
    WHITE(0, 0xDDDDDD, 0xF0F0F0, "white"),
    ORANGE(1, 0xDB7D3E, 0xEB8844, "orange"),
    MAGENTA(2, 0xB350BC, 0xC354CD, "magenta"),
    LIGHT_BLUE(3, 0x6B8AC9, 0x6689d3, "light_blue"),
    YELLOW(4, 0xB1A627, 0xDECF2A, "yellow"),
    LIME(5, 0x41AE38, 0x41CD34, "lime"),
    PINK(6, 0xD08499, 0xD88198, "pink"),
    GRAY(7, 0x404040, 0x434343, "gray"),
    LIGHT_GRAY(8, 0x9AA1A1, 0xABAbab, "light_gray"),
    CYAN(9, 0x2E6E89, 0x287697, "cyan"),
    PURPLE(10, 0x7E3DB5, 0x7B2FBE, "purple"),
    BLUE(11, 0x2E388D, 0x253192, "blue"),
    BROWN(12, 0x4F321F, 0x51301A, "brown"),
    GREEN(13, 0x35461B, 0x3B511A, "green"),
    RED(14, 0x963430, 0xB3312C, "red"),
    BLACK(15, 0x191616, 0x1E1B1B, "black");
    private final int color;
    private final int secondColor;
    private final int meta;
    private final String name;

    EnumColors(int meta, int color, int secondColor, String name) {
        this.color = color;
        this.secondColor = secondColor;
        this.name = name;
        this.meta = meta;
    }

    public static EnumColors byMetadata(int meta) {
        return meta < EnumColors.values().length ? EnumColors.values()[meta] : EnumColors.values()[0];
    }

    public int getColor() {
        return this.color;
    }

    public int getSecondColor() {
        return this.secondColor;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMetadata() {
        return this.meta;
    }

    public static String[] getNames() {
        String[] array = new String[EnumColors.values().length];
        for (int i = 0; i < EnumColors.values().length; i++) {
            array[i] = EnumColors.values()[i].getName();
        }
        return array;
    }
}
