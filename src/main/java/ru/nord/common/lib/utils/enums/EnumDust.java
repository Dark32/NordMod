package ru.nord.common.lib.utils.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumDust implements IStringSerializable {
    IRON        (0, "iron",     false,  -1),
    GOLD        (1, "gold",     false,  -1),
    SILVER      (2, "silver",   false,  0),
    COPPER      (3, "copper",   false,  1),
    TIN         (4, "tin",      false,  2),
    ZINC        (5, "zinc",     false,  3),
    BRONZE      (6, "bronze",   true,   4),
    ELECTRUM    (7, "electrum", true,   5),
    STEEL       (8, "steel",    true,   6),
    BRASS       (9, "brass",    true,   7);

    private final String name;
    private final int meta;
    private final boolean alloy;
    private final int metal;

    EnumDust(int meta, String name, boolean alloy,int metal) {
        this.name = name;
        this.meta = meta;
        this.alloy = alloy;
        this.metal = metal;
    }

    public static EnumDust byMetadata(int meta) {
        return meta < EnumDust.values().length ? EnumDust.values()[meta] : EnumDust.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumDust.values().length];
        for (int i = 0; i < EnumDust.values().length; i++) {
            array[i] = EnumDust.values()[i].getName();
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
    public EnumMetal getMetall() {
        return EnumMetal.byMetadata(metal);
    }

}
