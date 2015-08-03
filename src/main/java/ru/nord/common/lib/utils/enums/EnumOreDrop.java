package ru.nord.common.lib.utils.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumOreDrop implements IStringSerializable {
    GALENA(0, "galena", "silver") {
        @Override
        public EnumDust getDust() {
            return EnumDust.SILVER;
        }
    },
    ZINCITE(1, "zincite", "zinc") {
        @Override
        public EnumDust getDust() {
            return EnumDust.ZINC;
        }
    },
    BAUXITE(2, "bauxite", "aluminum") {
        @Override
        public EnumDust getDust() {
            return EnumDust.ALUMINUM;
        }
    },
    UVAROVITE(3, "uvarovite", "chrom") {
        @Override
        public EnumDust getDust() {
            return EnumDust.CHROM;
        }
    },
    CROCOITE(4, "crocoite", "chrom") {
        @Override
        public EnumDust getDust() {
            return EnumDust.CHROM;
        }
    },
    COPPER(5, "copper", "copper") {
        @Override
        public EnumDust getDust() {
            return EnumDust.COPPER;
        }
    },
    OLIVINE(6, "olivine", "iron") {
        @Override
        public EnumDust getDust() {
            return EnumDust.IRON;
        }
    },
    MAGNETITE(7, "magnetite", "iron") {
        @Override
        public EnumDust getDust() {
            return EnumDust.IRON;
        }
    },
    LIMONITE(8, "limonite", "iron") {
        @Override
        public EnumDust getDust() {
            return EnumDust.IRON;
        }
    },
    ALMANDINE(9, "almandine", "iron") {
        @Override
        public EnumDust getDust() {
            return EnumDust.IRON;
        }
    },
    PENTLANDITE(10, "pentlandite", "nicel") {
        @Override
        public EnumDust getDust() {
            return EnumDust.NICEL;
        }
    },
    MILLERITE(11, "millerite", "nicel") {
        @Override
        public EnumDust getDust() {
            return EnumDust.NICEL;
        }
    },;


    private final String name;
    private final String metal;
    private final int meta;

    EnumOreDrop(int _meta, String _name, String metal) {
        this.meta = _meta;
        this.name = _name;
        this.metal = metal;
    }


    public static EnumOreDrop byMetadata(int meta) {
        return meta < EnumOreDrop.values().length ? EnumOreDrop.values()[meta] : EnumOreDrop.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumOreDrop.values().length];
        for (int i = 0; i < EnumOreDrop.values().length; i++) {
            array[i] = EnumOreDrop.values()[i].getName();
        }
        return array;
    }

    public String getName() {
        return this.name;
    }

    public String getMetal() {
        return this.metal;
    }

    public int getMetadata() {
        return this.meta;
    }

    public abstract EnumDust getDust();
}
