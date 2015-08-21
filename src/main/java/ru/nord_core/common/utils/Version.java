package ru.nord_core.common.utils;

import net.minecraftforge.fml.common.ModMetadata;

public class Version {
    public static final String MODID = "nord_core";
    public static final String VERSION = "0.1.0";
    public static final String NAME = "Core Nord Mod 1.8";
    public static void setupModInfo(ModMetadata meta){
        meta.authorList.add("dark32");
        meta.authorList.add("BluDiss");
        meta.authorList.add("NikitaWhite");
        meta.authorList.add("LionZXY");
    }

}
