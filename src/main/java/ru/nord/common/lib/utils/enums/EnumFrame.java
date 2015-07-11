package ru.nord.common.lib.utils.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumFrame implements IStringSerializable {
    IRON(0, "IRON","nord:frame_wood"),
    WOOD(1, "WOOD","nord:frame_metall");
    private final int meta;
    private final String name;
    private final String model;

    EnumFrame(int meta, String name,String model) {
        this.name = name;
        this.meta = meta;
        this.model = model;
    }

    public static EnumFrame byMetadata(int meta) {
        return meta < EnumFrame.values().length ? EnumFrame.values()[meta] : EnumFrame.values()[0];
    }

    @Override
    public String getName() {
        return name;
    }
    public String geModel() {
        return model;
    }

    public int getMetadata() {
        return this.meta;
    }

    public static String[] getNames(){
        String[] array = new String[EnumFrame.values().length];
        for (int i = 0; i < EnumFrame.values().length; i++) {
            array[i]= EnumFrame.values()[i].getName();
        }
        return array;
    }
}