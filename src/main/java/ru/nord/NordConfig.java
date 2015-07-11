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
        config = new Configuration(configFile,"0.0.5");
        config.addCustomCategoryComment("Metall","You can disable or enable other metall. On 'true' will create ingot,metall block,dust and ore.");
        config.getBoolean("cooper","Metall",true,"Disable or enable cooper ore");
        config.getBoolean("tin", "Metall", true, "Disable or enable tin ore");
        config.save();
        loadConfig();
    }
    public static void loadConfig(){
    config.load();
    }
    public static boolean getCooper(){
        loadConfig();
        return config.getBoolean("cooper","Metall",true,"Disable or enable cooper ore");
    }
    public static boolean getTin(){
        loadConfig();
        return config.getBoolean("tin", "Metall", true, "Disable or enable tin ore");
    }
    public static Configuration getConfig(){
        return config;
    }

}
