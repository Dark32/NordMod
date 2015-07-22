package ru.nord.common.lib.utils;

import net.minecraftforge.fml.common.ModMetadata;

/**
 * Created by andrew on 21.03.15.
 */
public class Version {
    public static final String MODID = "nord";
    public static final String VERSION = "0.0.10";
    public static final String NAME = "Nord Mod 1.8";
    public static void setupModInfo(ModMetadata meta){
        meta.authorList.add("dark32");
        meta.authorList.add("BluDiss");
        meta.authorList.add("NikitaWhite");
        meta.authorList.add("LionZXY");
    }

}
