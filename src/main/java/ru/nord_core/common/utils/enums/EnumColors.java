package ru.nord_core.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumColors implements IMetadataEnum {
    WHITE(0, 0xDDDDDD, 0xF0F0F0, "WHITE"),
    ORANGE(1, 0xDB7D3E, 0xEB8844, "ORANGE"),
    MAGENTA(2, 0xB350BC, 0xC354CD, "MAGENTA"),
    LIGHT_BLUE(3, 0x6B8AC9, 0x6689D3, "LIGHT_BLUE"),
    YELLOW(4, 0xB1A627, 0xDECF2A, "YELLOW"),
    LIME(5, 0x41AE38, 0x41CD34, "LIME"),
    PINK(6, 0xD08499, 0xD88198, "PINK"),
    GRAY(7, 0x404040, 0x434343, "GRAY"),
    LIGHT_GRAY(8, 0x9AA1A1, 0xABABAB, "LIGHT_GRAY"),
    CYAN(9, 0x2E6E89, 0x287697, "CYAN"),
    PURPLE(10, 0x7E3DB5, 0x7B2FBE, "PURPLE"),
    BLUE(11, 0x2E388D, 0x253192, "BLUE"),
    BROWN(12, 0x4F321F, 0x51301A, "BROWN"),
    GREEN(13, 0x35461B, 0x3B511A, "GREEN"),
    RED(14, 0x963430, 0xB3312C, "RED"),
    BLACK(15, 0x191616, 0x1E1B1B, "BLACK");
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
