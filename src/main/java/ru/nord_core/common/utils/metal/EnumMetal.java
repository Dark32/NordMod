package ru.nord_core.common.utils.metal;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumMetal implements IMetadataEnum {
    IRON("iron"),
    GOLD("gold"),
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

    SULFUR("sulfur", false),
    FLINT("flint", false),

    ;

    private final String name;
    private final boolean metal;

    EnumMetal(String _name) {
        this.name = _name;
        this.metal = true;
    }

    EnumMetal(String _name, boolean metal) {
        this.name = _name;
        this.metal = metal;
    }

    @Override
    public int getMetadata() {
        return this.ordinal();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean getMetal() {
        return this.metal;
    }
}
