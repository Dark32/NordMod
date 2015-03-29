package ru.nord.common.lib.utils.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumStone implements IStringSerializable {
    BURNT           (0, "burnt"),
    SMOOTH          (1, "smooth"),
    DOUBLE          (2, "double"),
    DOUBLE_HALSF    (3, "double_half"),
    DOUBLE_SMALL    (4, "double_small"),
    SMALL           (5, "small"),
    LINE            (6, "line"),
    SPIRAL          (7, "spiral"),
    DARK            (8, "dark"),
    LIGHT           (9, "light"),
    VART_SMALL      (10,"vert_small"),
    VART_DOUBLE     (11,"vert_double"),
//    QUAD            (12,"quad"),
    VERT_LINE       (12,"vert_line"),
    GEM             (13,"gem"),
    VORTEX          (14,"vortex"),
    CREEP           (15,"creep");

    private final String name;
    private final int meta;

    EnumStone(int meta, String name) {
        this.name = name;
        this.meta = meta;
    }

    public String getName() {
        return this.name;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    public static EnumStone byMetadata(int meta)
    {
        return meta <EnumStone.values().length ?  EnumStone.values()[meta] :  EnumStone.values()[0];
    }

    public static String[] getNames(){
        String[] array = new String[EnumStone.values().length];
        for (int i = 0; i < EnumStone.values().length; i++) {
            array[i]=EnumStone.values()[i].getName();
        }
        return array;
    }
}
