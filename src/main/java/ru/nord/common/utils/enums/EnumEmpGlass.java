package ru.nord.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumEmpGlass implements IMetadataEnum {

    glass1(0, "glass1"),
    glass2(1, "glass2"),
    glass3(2, "glass3"),
    glass4(3, "glass4"),
    glass5(4, "glass5"),
    glass6(5, "glass6"),;

    private final String name;
    private final int meta;

    EnumEmpGlass(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
    }

    public static EnumEmpGlass byMetadata(int meta) {
        return meta < EnumEmpGlass.values().length ? EnumEmpGlass.values()[meta] : EnumEmpGlass.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumEmpGlass.values().length];
        for (int i = 0; i < EnumEmpGlass.values().length; i++) {
            array[i] = EnumEmpGlass.values()[i].getName();
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