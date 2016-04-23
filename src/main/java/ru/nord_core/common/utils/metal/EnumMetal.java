package ru.nord_core.common.utils.metal;

import com.google.common.collect.ImmutableList;
import ru.nord_core.common.utils.enums.interfaces.IMetadata2Enum;

public enum EnumMetal implements IMetadata2Enum {
    IRON("iron", true),
    GOLD("gold", true),
    SILVER("silver"),
    COPPER("copper"),
    TIN("tin"),
    ZINC("zinc"),
    BRONZE("bronze"),
    ELECTRUM("electrum"),
    STEEL("steel"),
    BRASS("brass"),
    ALUMINUM("aluminum"),
    CHROM("chrom"),
    NICEL("nicel"),
    NICHROME("nichrome"),
    INVAR("invar"),
    DURALUMIN("duralumin"),
    CAST_IRON("cast_iron"),
    MERCURY("mercury"),
    LEAD("lead"),

    ;

    private final String name;
    private final boolean vanila;

    EnumMetal(String _name) {
        this.name = _name;
        this.vanila = false;
    }

    EnumMetal(String _name, boolean vanila) {
        this.name = _name;
        this.vanila = vanila;
    }

    @Override
    public int getMetadata() {
        return this.ordinal();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean getVanila() {
        return this.vanila;
    }

    public static EnumMetal byMetadata(int meta) {
        return meta < EnumMetal.values().length ? EnumMetal.values()[meta] : EnumMetal.values()[0];
    }

    public static EnumMetal byReIndexMetadata(ImmutableList list, int meta) {
        return (EnumMetal) list.get(meta);
    }

    @Override
    public int getReIndexMetadata(ImmutableList list) {
        return list.indexOf(this);
    }
}
