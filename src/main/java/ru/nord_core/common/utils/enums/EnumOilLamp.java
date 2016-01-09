package ru.nord_core.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

/**
 * Created by andrew on 09.01.16.
 */
public enum EnumOilLamp implements IMetadataEnum {
    STANDART(0, "standart"),
    ;
    private final String name;
    private final int meta;

    EnumOilLamp(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
    }

    public static EnumOilLamp byMetadata(int meta) {
        return meta < EnumOilLamp.values().length ? EnumOilLamp.values()[meta] : EnumOilLamp.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumOilLamp.values().length];
        for (int i = 0; i < EnumOilLamp.values().length; i++) {
            array[i] = EnumOilLamp.values()[i].getName();
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
