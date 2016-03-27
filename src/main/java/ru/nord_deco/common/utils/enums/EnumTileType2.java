package ru.nord_deco.common.utils.enums;

import ru.nord_core.common.utils.enums.EnumColors;
import ru.nord_core.common.utils.enums.interfaces.IColorizeEnum;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumTileType2 implements IMetadataEnum, IColorizeEnum {

    LIGHT_GRAY(0, "light_gray", 8),
    CYAN(1, "cyan", 9),
    PURPLE(2, "purple", 10),
    BLUE(3, "blue", 11),
    BROWN(4, "brown", 12),
    GREEN(5, "green", 13),
    RED(6, "red", 14),
    BLACK(7, "black", 15);

    private final String name;
    private final int meta;
    private final int color;

    EnumTileType2(int _meta, String
            _name, int _color) {
        this.name = _name;
        this.meta = _meta;
        this.color = _color;
    }

    public static EnumTileType2 byMetadata(int meta) {
        return meta < EnumTileType2.values().length ? EnumTileType2.values()[meta] : EnumTileType2.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumTileType2.values().length];
        for (int i = 0; i < EnumTileType2.values().length; i++) {
            array[i] = EnumTileType2.values()[i].getName();
        }
        return array;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getMetadata() {
        return this.meta;
    }

    @Override
    public int getColor() {
        return EnumColors.byMetadata(this.color).getColor();
    }
}
