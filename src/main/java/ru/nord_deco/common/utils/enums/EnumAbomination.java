package ru.nord_deco.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumAbomination implements IMetadataEnum {

    MAGGOTS(0, "maggots"),
    ;
    private final String name;
    private final int meta;

    EnumAbomination(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
    }

    public static EnumAbomination byMetadata(int meta) {
        return meta < EnumAbomination.values().length ? EnumAbomination.values()[meta] : EnumAbomination.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumAbomination.values().length];
        for (int i = 0; i < EnumAbomination.values().length; i++) {
            array[i] = EnumAbomination.values()[i].getName();
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
