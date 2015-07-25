package ru.nord.common.lib.utils.enums;

import net.minecraft.util.IStringSerializable;

import java.util.ArrayList;
import java.util.List;

public enum EnumOre implements IStringSerializable {
    SILVER(0, "silver", "silver") {
        @Override
        public EnumNugget getNugget() {
            return EnumNugget.SILVER;
        }
    },
    GALENA(1, "galena", "silver"),
    TIN(2, "tin", "tin") {
        @Override
        public EnumNugget getNugget() {
            return EnumNugget.TIN;
        }
    },
    ZINCITE(3, "zincite", "zinc"),
    BAUXITE(4, "bauxite", "aluminum"),
    UVAROVITE(5, "uvarovite", "chrom") {
        @Override
        public EnumCrystal getCrystal() {
            return EnumCrystal.UVAROVITE;
        }
    },
    CROCOITE(6, "crocoite", "chrom") {
        @Override
        public EnumCrystal getCrystal() {
            return EnumCrystal.CROCOITE;
        }
    },
    COPPER(7, "copper", "copper"),
    OLIVINE(9, "olivine", "iron") {
        @Override
        public EnumCrystal getCrystal() {
            return EnumCrystal.OLIVINE;
        }
    },
    MAGNETITE(10, "magnetite", "iron"),
    LIMONITE(11, "limonite", "iron"),
    ALMANDINE(12, "almandine", "iron") {
        @Override
        public EnumCrystal getCrystal() {
            return EnumCrystal.ALMANDINE;
        }
    },
    PENTLANDITE(13, "pentlandite", "nicel"),
    MILLERITE(14, "millerite", "nicel"),
    MERCURY(15, "mercury", "mercury") {
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

    public static String[] getDropNames() {
        List<String> list = new ArrayList<String>(EnumOre.values().length);
        for (int i = 0; i < EnumOre.values().length; i++) {
            if (EnumOre.values()[i].getNugget() == null) {
                list.add(EnumOre.values()[i].getName());
            }
        }
        return list.toArray(new String[0]);
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

    public EnumNugget getNugget() {
        return null;
    }

    public EnumCrystal getCrystal() {
        return null;
    }
}
