package ru.nord.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumGlassFood implements IMetadataEnum {

    CLABBEREMPTY(0, "clabberempty"),
    PITCHER_EMPTY(1, "pitcherempty");
    private final String name;
    private final int meta;

    EnumGlassFood(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
    }

    public static EnumGlassFood byMetadata(int meta) {
        return meta < EnumGlassFood.values().length ? EnumGlassFood.values()[meta] : EnumGlassFood.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumGlassFood.values().length];
        for (int i = 0; i < EnumGlassFood.values().length; i++) {
            array[i] = EnumGlassFood.values()[i].getName();
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
