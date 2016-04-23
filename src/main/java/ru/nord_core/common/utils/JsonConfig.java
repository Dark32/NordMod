package ru.nord_core.common.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.io.FileReader;

/**
 * @author andrew
 *         https://github.com/Dark32
 *         Edited on 17.04.2016
 */
public class JsonConfig {

    private JsonObject mainJson = new JsonObject();
    private File jsonFile;
    private final String fileName;

    /**
     * @param fileName имя файла с конфигами
     */
    public JsonConfig(String fileName) {
        this.fileName = fileName;
        jsonFile = new File(Loader.instance().getConfigDir(), fileName);
        FileReader reader = null;
        try {
            reader = new FileReader(jsonFile);
        } catch (Exception e) {
            FMLLog.bigWarning("Can't load json mod config!");
            e.printStackTrace();
        }
        mainJson = new JsonParser().parse(reader).getAsJsonObject();
    }


    public JsonObject getJSON() {
        return mainJson;
    }
}