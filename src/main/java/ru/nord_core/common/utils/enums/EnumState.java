package ru.nord_core.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumState implements IMetadataEnum {
    ON(0, "ON", 1),
    OFF(1, "OFF", 1),
    INVERTED_ON(2, "INVERTED_ON", 2),
    INVERTED_OFF(3, "INVERTED_OFF", 2);
    private final int meta;
    private final int drop;
    private final String name;

    EnumState(int meta, String name, int drop) {
        this.name = name;
        this.drop = drop;
        this.meta = meta;
    }

    public static EnumState byMetadata(int meta) {
        return meta < EnumState.values().length ? EnumState.values()[meta] : EnumState.values()[0];
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMetadata() {
        return this.meta;
    }

    public int getDrop() {
        return this.drop;
    }

    public static String[] getNames() {
        String[] array = new String[EnumState.values().length];
        for (int i = 0; i < EnumState.values().length; i++) {
            array[i] = EnumState.values()[i].getName();
        }
        return array;
    }
}
