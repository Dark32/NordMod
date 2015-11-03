package ru.nord.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumWhiteStone implements IMetadataEnum {

    WHITE1(0, "white1"),
    WHITE2(1, "white2"),
    WHITE3(2, "white3"),
    WHITE4(3, "white4"),
    WHITE5(4, "white5"),
    WHITE6(5, "white6"),
    WHITE7(6, "white7"),
 ;
    private final String name;
    private final int meta;

    EnumWhiteStone(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
    }

    public static EnumWhiteStone byMetadata(int meta) {
        return meta < EnumWhiteStone.values().length ? EnumWhiteStone.values()[meta] : EnumWhiteStone.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumWhiteStone.values().length];
        for (int i = 0; i < EnumWhiteStone.values().length; i++) {
            array[i] = EnumWhiteStone.values()[i].getName();
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
