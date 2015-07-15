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
        config = new Configuration(configFile,"0.0.8");
        config.getBoolean("Enable_Ore", "copper", true, "Disable or enable cooper ore");
        config.getInt("MinY", "copper", 10, 0, 128, "Min Y");
        config.getInt("MaxY", "copper", 72, 0, 128, "Max Y");
        config.getInt("VeinSize", "copper",20, 0,64,"Vein Size");
        config.getInt("FrequencyOre", "copper", 5, 0, 16,"Frequency Ore");
        config.getBoolean("Enable_Ore", "tin", true, "Disable or enable tin ore");
        config.getInt("MinY", "tin", 20, 0, 128, "Min Y");
        config.getInt("MaxY", "tin", 72, 0, 128, "Max Y");
        config.getInt("VeinSize", "tin", 4, 0, 64, "Vein Size");
        config.getInt("FrequencyOre", "tin", 20, 0, 16,"Frequency Ore");
        config.getBoolean("Enable_Ore", "zinc", true, "Disable or enable zinc ore");
        config.getInt("MinY", "zinc", 20, 0, 128, "Min Y");
        config.getInt("MaxY", "zinc", 72, 0, 128, "Max Y");
        config.getInt("VeinSize", "zinc", 4, 0, 64,"Vein Size");
        config.getInt("FrequencyOre", "zinc", 20, 0, 16,"Frequency Ore");
        config.getBoolean("Enable_Ore", "silver", true, "Disable or enable silver ore");
        config.getInt("MinY", "silver", 40, 0, 128, "Min Y");
        config.getInt("MaxY", "silver", 72, 0, 128, "Max Y");
        config.getInt("VeinSize", "silver", 10, 0, 64,"Vein Size");
        config.getInt("FrequencyOre", "silver", 16, 0, 16,"Frequency Ore");
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
        return config.getInt("FrequencyOre", ore, 2, 0, 16,"Vein Size");
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

    public static Configuration getConfig(){
        return config;
    }

}
