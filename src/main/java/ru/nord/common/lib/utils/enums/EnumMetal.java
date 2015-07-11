package ru.nord.common.lib.utils.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumMetal implements IStringSerializable {
    SILVER      (0, "silver",   false,  2),
    COPPER      (1, "copper",   false,  3),
    TIN         (2, "tin",      false,  4),
    ZINC        (3, "zinc",     false,  5),
    BRONZE      (4, "bronze",   true,   6),
    ELECTRUM    (5, "electrum", true,   7),
    STEEL       (6, "steel",    true,   8),
    BRASS       (7, "brass",    true,   9);

    private final String name;
    private final int meta;
    private final boolean alloy;
    private final int dust;

    EnumMetal(int _meta, String _name, boolean _alloy, int _dust) {
        this.name = _name;
        this.meta = _meta;
        this.alloy = _alloy;
        this.dust = _dust;
    }

    public static EnumMetal byMetadata(int meta) {
        return meta < EnumMetal.values().length ? EnumMetal.values()[meta] : EnumMetal.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumMetal.values().length];
        for (int i = 0; i < EnumMetal.values().length; i++) {
            array[i] = EnumMetal.values()[i].getName();
        }
        return array;
    }

    public String getName() {
        return this.name;
    }
    public int getMetadata() {
        return this.meta;
    }
    public boolean getAlloy() {
        return this.alloy;
    }
    public EnumDust getDust() {
        return EnumDust.byMetadata(this.dust);
    }

}
