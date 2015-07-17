package ru.nord.common.lib.utils.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumCrystal implements IStringSerializable {

    UVAROVITE   (0,  "uvarovite"),
    OLIVINE     (1,  "olivine"),
    CROCOITE    (2,  "crocoite"),
    ALMANDINE   (3, "almandine"),
    ;
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

    public String getName() {
        return this.name;
    }
    public int getMetadata() {
        return this.meta;
    }

}
