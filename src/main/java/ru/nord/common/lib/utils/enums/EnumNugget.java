package ru.nord.common.lib.utils.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumNugget implements IStringSerializable {
    SILVER      (0, "silver"   ) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.SILVER;
        }
    },
    TIN         (2, "tin"      ) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.TIN;
        }
    },
    MERCURY        (3, "mercury"    ) {
        @Override
        public EnumMetal getMetal() {
            return null;
        }
    }
    ;
    private final String name;
    private final int meta;

    EnumNugget(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
    }

    public static EnumNugget byMetadata(int meta) {
        return meta < EnumNugget.values().length ? EnumNugget.values()[meta] : EnumNugget.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumNugget.values().length];
        for (int i = 0; i < EnumNugget.values().length; i++) {
            array[i] = EnumNugget.values()[i].getName();
        }
        return array;
    }

    public String getName() {
        return this.name;
    }
    public int getMetadata() {
        return this.meta;
    }
    abstract public EnumMetal getMetal();

}