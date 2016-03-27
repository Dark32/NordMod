package ru.nord_deco.common.utils.enums;

import ru.nord_core.common.utils.enums.EnumColors;
import ru.nord_core.common.utils.enums.interfaces.IColorizeEnum;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumTileType1 implements IMetadataEnum, IColorizeEnum {

    WHITE(0, "white", 0),
    ORANGE(1, "orange", 1),
    MAGENTA(2, "magenta", 2),
    LIGHT_BLUE(3, "light_blue", 3),
    YELLOW(4, "yellow", 4),
    LIME(5, "lime", 5),
    PINK(6, "pink", 6),
    GRAY(7, "gray", 7),;

    private final String name;
    private final int meta;
    private final int color;

    EnumTileType1(int _meta, String
            _name, int _color) {
        this.name = _name;
        this.meta = _meta;
        this.color = _color;
    }

    public static EnumTileType1 byMetadata(int meta) {
        return meta < EnumTileType1.values().length ? EnumTileType1.values()[meta] : EnumTileType1.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumTileType1.values().length];
        for (int i = 0; i < EnumTileType1.values().length; i++) {
            array[i] = EnumTileType1.values()[i].getName();
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
