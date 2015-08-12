package ru.nord_core.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumMetal implements IMetadataEnum {
    SILVER(0, "silver", false) {
        @Override
        public EnumDust getDust() {
            return EnumDust.SILVER;
        }
    },
    COPPER(1, "copper", false) {
        @Override
        public EnumDust getDust() {
            return EnumDust.COPPER;
        }
    },
    TIN(2, "tin", false) {
        @Override
        public EnumDust getDust() {
            return EnumDust.TIN;
        }
    },
    ZINC(3, "zinc", false) {
        @Override
        public EnumDust getDust() {
            return EnumDust.ZINC;
        }
    },
    BRONZE(4, "bronze", true) {
        @Override
        public EnumDust getDust() {
            return EnumDust.BRONZE;
        }
    },
    ELECTRUM(5, "electrum", true) {
        @Override
        public EnumDust getDust() {
            return EnumDust.ELECTRUM;
        }
    },
    STEEL(6, "steel", true) {
        @Override
        public EnumDust getDust() {
            return EnumDust.STEEL;
        }
    },
    BRASS(7, "brass", true) {
        @Override
        public EnumDust getDust() {
            return EnumDust.BRASS;
        }
    },
    ALUMINUM(8, "aluminum", false) {
        @Override
        public EnumDust getDust() {
            return EnumDust.ALUMINUM;
        }
    },
    CHROM(9, "chrom", false) {
        @Override
        public EnumDust getDust() {
            return EnumDust.CHROM;
        }
    },
    NICEL(10, "nicel", false) {
        @Override
        public EnumDust getDust() {
            return EnumDust.NICEL;
        }
    },
    NICHROME(11, "nichrome", true) {
        @Override
        public EnumDust getDust() {
            return EnumDust.NICHROME;
        }
    },
    INVAR(12, "invar", true) {
        @Override
        public EnumDust getDust() {
            return EnumDust.INVAR;
        }
    },
    DURALUMIN(13, "duralumin", true) {
        @Override
        public EnumDust getDust() {
            return EnumDust.DURALUMIN;
        }
    },
    CAST_IRON(14, "cast_iron", true) {
        @Override
        public EnumDust getDust() {
            return EnumDust.CAST_IRON;
        }
    },;
    private final String name;
    private final int meta;
    private final boolean alloy;

    EnumMetal(int _meta, String _name, boolean _alloy) {
        this.name = _name;
        this.meta = _meta;
        this.alloy = _alloy;
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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getMetadata() {
        return this.meta;
    }

    public boolean getAlloy() {
        return this.alloy;
    }

    abstract public EnumDust getDust();

}
