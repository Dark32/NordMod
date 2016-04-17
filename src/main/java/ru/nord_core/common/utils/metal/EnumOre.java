package ru.nord_core.common.utils.metal;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumOre implements IMetadataEnum {
    SILVER("silver"),
    GALENA("galena"),
    TIN("tin"),
    ZINCITE("zincite"),
    BAUXITE("bauxite"),
    UVAROVITE("uvarovite"),
    CROCOITE("crocoite"),
    COPPER("copper"),
    OLIVINE("olivine"),
    MAGNETITE("magnetite"),
    HEMATITE("hematite"),
    LIMONITE("limonite"),
    ALMANDINE("almandine"),
    PENTLANDITE("pentlandite"),
    MILLERITE("millerite"),
    MERCURY("mercury"),
    CINNABAR("cinnabar"),
    MALACHITE("malachite"),

    ;
    private final String name;

    EnumOre(String _name) {
        this.name = _name;
    }

    @Override
    public int getMetadata() {
        return this.ordinal();
    }

    @Override
    public String getName() {
        return this.name;
    }
}
