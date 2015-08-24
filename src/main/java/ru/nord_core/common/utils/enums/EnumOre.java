package ru.nord_core.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumOre implements IMetadataEnum {
    SILVER(0, "silver", "silver") {
        @Override
        public EnumNugget getNugget() {
            return EnumNugget.SILVER;
        }
    },
    GALENA(1, "galena", "silver") {
        @Override
        public EnumOreDrop getOreDrop() {
            return EnumOreDrop.GALENA;
        }
    },
    TIN(2, "tin", "tin") {
        @Override
        public EnumNugget getNugget() {
            return EnumNugget.TIN;
        }
    },
    ZINCITE(3, "zincite", "zinc") {
        @Override
        public EnumOreDrop getOreDrop() {
            return EnumOreDrop.ZINCITE;
        }
    },
    BAUXITE(4, "bauxite", "aluminum") {
        @Override
        public EnumOreDrop getOreDrop() {
            return EnumOreDrop.BAUXITE;
        }
    },
    UVAROVITE(5, "uvarovite", "chrom") {
        @Override
        public EnumOreDrop getOreDrop() {
            return EnumOreDrop.UVAROVITE;
        }
    },
    CROCOITE(6, "crocoite", "chrom") {
        @Override
        public EnumOreDrop getOreDrop() {
            return EnumOreDrop.CROCOITE;
        }
    },
    COPPER(7, "copper", "copper") {
        @Override
        public EnumOreDrop getOreDrop() {
            return EnumOreDrop.COPPER;
        }
    },
    OLIVINE(8, "olivine", "iron") {
        @Override
        public EnumOreDrop getOreDrop() {
            return EnumOreDrop.OLIVINE;
        }
    },
    MAGNETITE(9, "magnetite", "iron") {
        @Override
        public EnumOreDrop getOreDrop() {
            return EnumOreDrop.MAGNETITE;
        }
    },
    LIMONITE(10, "limonite", "iron") {
        @Override
        public EnumOreDrop getOreDrop() {
            return EnumOreDrop.LIMONITE;
        }
    },
    ALMANDINE(11, "almandine", "iron") {
        @Override
        public EnumOreDrop getOreDrop() {
            return EnumOreDrop.ALMANDINE;
        }
    },
    PENTLANDITE(12, "pentlandite", "nicel") {
        @Override
        public EnumOreDrop getOreDrop() {
            return EnumOreDrop.PENTLANDITE;
        }
    },
    MILLERITE(13, "millerite", "nicel") {
        @Override
        public EnumOreDrop getOreDrop() {
            return EnumOreDrop.MILLERITE;
        }
    },
    MERCURY(14, "mercury", "mercury") {
        @Override
        public EnumNugget getNugget() {
            return EnumNugget.MERCURY;
        }
    },;


    private final String name;
    private final String metal;
    private final int meta;

    EnumOre(int _meta, String _name, String metal) {
        this.meta = _meta;
        this.name = _name;
        this.metal = metal;
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

    @Override
    public String getName() {
        return this.name;
    }

    public String getMetal() {
        return this.metal;
    }

    @Override
    public int getMetadata() {
        return this.meta;
    }

    public EnumNugget getNugget() {
        return null;
    }


    public EnumOreDrop getOreDrop() {
        return null;
    }
}
