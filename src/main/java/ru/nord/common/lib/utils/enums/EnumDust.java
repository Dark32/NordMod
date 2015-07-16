package ru.nord.common.lib.utils.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumDust implements IStringSerializable {

    IRON        (0, "iron",     false) {
        @Override
        public EnumMetal getMetal() {
            return null;
        }
    },
    GOLD        (1, "gold",     false) {
        @Override
        public EnumMetal getMetal() {
            return null;
        }
    },
    SILVER(2, "silver", false) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.SILVER;
        }
    },
    COPPER(3, "copper", false) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.COPPER;
        }
    },
    TIN(4, "tin", false) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.TIN;
        }
    },
    ZINC(5, "zinc", false) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.ZINC;
        }
    },
    BRONZE(6, "bronze", true) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.BRONZE;
        }
    },
    ELECTRUM(7, "electrum", true) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.ELECTRUM;
        }
    },
    STEEL(8, "steel", true) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.STEEL;
        }
    },
    BRASS(9, "brass", true) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.BRASS;
        }
    },
    ALUMINUM(10, "aluminum", false) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.ALUMINUM;
        }
    },
    CHROM(11, "chrom", false) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.CHROM;
        }
    },
    NICEL(12, "nicel", false) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.NICEL;
        }
    },
    NICHROME(13, "nichrome", true) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.NICHROME;
        }
    },
    INVAR(14, "invar", true) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.INVAR;
        }
    },
    DURALUMIN(15, "duralumin", true) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.DURALUMIN;
        }
    },
    CAST_IRON(16, "cast_iron", true) {
        @Override
        public EnumMetal getMetal() {
            return EnumMetal.CAST_IRON;
        }
    },;

    private final String name;
    private final int meta;
    private final boolean alloy;

    EnumDust(int meta, String name, boolean alloy) {
        this.name = name;
        this.meta = meta;
        this.alloy = alloy;
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

    abstract public EnumMetal getMetal();

}
