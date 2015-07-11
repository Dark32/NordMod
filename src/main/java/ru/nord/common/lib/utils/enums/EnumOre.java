package ru.nord.common.lib.utils.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumOre implements IStringSerializable {
    SILVER      (0, "silver",     2),
    COPPER      (1, "copper",     3),
    TIN         (2, "tin",        4),
    ZINC        (3, "zinc",       5);

    private final String name;
    private final int meta;
    private final int dust;

    EnumOre(int _meta, String _name,  int _dust) {
        this.name = _name;
        this.meta = _meta;
        this.dust = _dust;
    }

    public static EnumOre byMetadata(int meta) {
        return meta < EnumOre.values().length ? EnumOre.values()[meta] : EnumOre.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumOre.values().length];
        for (int i = 0; i < EnumOre.values().length; i++) {
            array[i] = EnumOre.values()[i].getName();
        }
        return array;
    }

    public String getName() {
        return this.name;
    }
    public int getMetadata() {
        return this.meta;
    }
    public EnumDust getDust() {
        return EnumDust.byMetadata(this.dust);
    }

}
