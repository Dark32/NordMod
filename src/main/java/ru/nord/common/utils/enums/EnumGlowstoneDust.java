package ru.nord.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumGlowstoneDust implements IMetadataEnum {

    DUST1(0, "glowstoneDust1"),
    DUST2(1, "glowstoneDust2"),
    DUST3(2, "glowstoneDust3"),
    DUST4(3, "glowstoneDust4"),
    DUST5(4, "glowstoneDust5"),
    DUST6(5, "glowstoneDust6"),
    DUST7(6, "glowstoneDust7"),
    DUST8(7, "glowstoneDust8"),
    DUST9(8, "glowstoneDust9"),
    DUST10(9, "glowstoneDust10"),
    DUST11(10, "glowstoneDust11"),
    DUST12(11, "glowstoneDust12"),
    DUST13(12, "glowstoneDust13"),

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
