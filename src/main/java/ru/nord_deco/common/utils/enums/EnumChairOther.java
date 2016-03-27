package ru.nord_deco.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumChairOther implements IMetadataEnum {
    // не больше 4 типов, т.к. 4 бита на поворот уже занято
   STREET_CHAIR(0, "streetchair")
   ;
    private final String name;
    private final int meta;

    EnumChairOther(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
    }

    public static EnumChairOther byMetadata(int meta) {
        return meta < EnumChairOther.values().length ? EnumChairOther.values()[meta] : EnumChairOther.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumChairOther.values().length];
        for (int i = 0; i < EnumChairOther.values().length; i++) {
            array[i] = EnumChairOther.values()[i].getName();
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
