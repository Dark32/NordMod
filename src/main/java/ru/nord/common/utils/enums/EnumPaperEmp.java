package ru.nord.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumPaperEmp implements IMetadataEnum {

    BAMBOOK(0, "bambook"),
    EMP1(1, "empire1"),
    EMP2(2, "empire2"),
    EMP3(3, "empire3"),
    EMP4(4, "empire4"),
    EMP5(5, "empire5"),
    EMP6(6, "empire6"),
    EMP7(7, "empire7"),
    EMP8(8, "empire8"),
    EMP9(9, "empire9"),
    EMP10(10, "empire10"),
    EMP11(11, "empire11"),

 ;
    private final String name;
    private final int meta;

    EnumPaperEmp(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
    }

    public static EnumPaperEmp byMetadata(int meta) {
        return meta < EnumPaperEmp.values().length ? EnumPaperEmp.values()[meta] : EnumPaperEmp.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumPaperEmp.values().length];
        for (int i = 0; i < EnumPaperEmp.values().length; i++) {
            array[i] = EnumPaperEmp.values()[i].getName();
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
