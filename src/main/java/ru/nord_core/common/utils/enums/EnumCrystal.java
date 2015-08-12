package ru.nord_core.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumCrystal implements IMetadataEnum {

    UVAROVITE(0, "uvarovite"),
    OLIVINE(1, "olivine"),
    CROCOITE(2, "crocoite"),
    ALMANDINE(3, "almandine"),;
    private final String name;
    private final int meta;

    EnumCrystal(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
    }

    public static EnumCrystal byMetadata(int meta) {
        return meta < EnumCrystal.values().length ? EnumCrystal.values()[meta] : EnumCrystal.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumCrystal.values().length];
        for (int i = 0; i < EnumCrystal.values().length; i++) {
            array[i] = EnumCrystal.values()[i].getName();
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
