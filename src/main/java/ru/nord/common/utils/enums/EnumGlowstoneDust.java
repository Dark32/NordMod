package ru.nord.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumGlowstoneDust implements IMetadataEnum {

    DUST1(0, "glowstonedust1"),
    DUST2(1, "glowstonedust2"),
    DUST3(2, "glowstonedust3"),
    DUST4(3, "glowstonedust4"),
    DUST5(4, "glowstonedust5"),
    DUST6(5, "glowstonedust6"),
    DUST7(6, "glowstonedust7"),
    DUST8(7, "glowstonedust8"),
    DUST9(8, "glowstonedust9"),
    DUST10(9, "glowstonedust10"),
    DUST11(10, "glowstonedust11"),
    DUST12(11, "glowstonedust12"),
    DUST13(12, "glowstonedust13"),

    ;

    private final String name;
    private final int meta;

    EnumGlowstoneDust(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
    }

    public static EnumGlowstoneDust byMetadata(int meta) {
        return meta < EnumGlowstoneDust.values().length ? EnumGlowstoneDust.values()[meta] : EnumGlowstoneDust.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumGlowstoneDust.values().length];
        for (int i = 0; i < EnumGlowstoneDust.values().length; i++) {
            array[i] = EnumGlowstoneDust.values()[i].getName();
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

}
