package ru.nord_deco.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumTileType1 implements IMetadataEnum {

    WHITE(0,  "WHITE"),
    ORANGE(1,   "ORANGE"),
    MAGENTA(2,   "MAGENTA"),
    LIGHT_BLUE(3,   "LIGHT_BLUE"),
    YELLOW(4, "YELLOW"),
    LIME(5,  "LIME"),
    PINK(6,  "PINK"),
    GRAY(7,   "GRAY"),
        ;

    private final String name;
    private final int meta;

    EnumTileType1(int _meta, String
            _name) {
        this.name = _name;
        this.meta = _meta;
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
}
