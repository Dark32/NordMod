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
        config = new Configuration(configFile,"0.0.6");
        config.getBoolean("Enable_Ore", "Cooper", true, "Disable or enable cooper ore");
        config.get("Cooper","MinY", 40);
        config.get("Cooper","MaxY",72);
        config.getBoolean("Enable_Ore", "Tin", true, "Disable or enable tin ore");
        config.get("Tin","MinY",40);
        config.get("Tin","MaxY",72);
        config.getBoolean("Enable_Ore", "Zinc", true, "Disable or enable tin ore");
        config.get("Zinc","MinY",40);
        config.get("Zinc","MaxY",72);
        config.getBoolean("Enable_Ore", "Silver", true, "Disable or enable tin ore");
        config.get("Silver","MinY",40);
        config.get("Silver","MaxY",72);
        config.save();
        loadConfig();
    }
    public static void loadConfig(){
    config.load();
    }

    public static Configuration getConfig(){
        return config;
    }

}
