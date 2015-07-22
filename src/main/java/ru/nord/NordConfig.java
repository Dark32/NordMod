package ru.nord;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;

import java.io.File;

/**
 * Created by nikit_000 on 09.07.2015.
 */
public class NordConfig {
    public static Configuration config;



    public static void preInit(){
        createConfig();
    }
    public static void createConfig(){
        File configFile = new File(Loader.instance().getConfigDir(), "Nord.cfg");
        config = new Configuration(configFile,"0.0.10");
        loadConfig();
    }
    public static void loadConfig(){
        config.save();
        config.load();

    }
    public static boolean getEnableOre(String ore){
        loadConfig();
        return config.getBoolean("Enable_Ore", ore, true, "Disable or enable "+ore+" ore");
    }
    public static int getFrequencyOre(String ore){
        loadConfig();
        return config.getInt("FrequencyOre", ore, 2, 0, 16, "Vein Size");
    }
    public static int getVeinSize(String ore){
        loadConfig();
        return config.getInt("VeinSize",ore ,10, 0,64,"Vein Size");
    }
    public static int getMinY(String ore){
        loadConfig();
        return config.getInt("MinY", ore , 40, 0, 128, "Min Y");
    }
    public static int getMaxY(String ore){
        loadConfig();
        return config.getInt("MaxY",ore ,72, 0, 128, "Max Y");
    }
    public static void generateConfig(String nameOre){
        config.getBoolean("Enable_Ore", nameOre, true, "Disable or enable cooper ore");
        config.getInt("MinY", nameOre, 10, 0, 128, "Min Y");
        config.getInt("MaxY", nameOre, 72, 0, 128, "Max Y");
        config.getInt("VeinSize", nameOre,20, 0,64,"Vein Size");
        config.getInt("FrequencyOre", nameOre, 5, 0, 16,"Frequency Ore");
    }

    public static Configuration getConfig(){
        return config;
    }

}
