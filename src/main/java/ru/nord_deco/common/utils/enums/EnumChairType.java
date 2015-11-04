package ru.nord_deco.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumChairType implements IMetadataEnum {
    // не больше 4 типов, т.к. 4 бита на поворот уже занято
    CHAIR(0, "chair"),
    STOOL(1,"stool"),
    STOOL2(2,"stool2"),
    ;
    private final String name;
    private final int meta;

    EnumChairType(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
    }

    public static EnumChairType byMetadata(int meta) {
        return meta < EnumChairType.values().length ? EnumChairType.values()[meta] : EnumChairType.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumChairType.values().length];
        for (int i = 0; i < EnumChairType.values().length; i++) {
            array[i] = EnumChairType.values()[i].getName();
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
