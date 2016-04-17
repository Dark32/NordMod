package ru.nord_core.common.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.util.JsonUtils;
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
    private final File jsonFile;
    private final String fileName;

    /**
     * @param fileName имя файла с конфигами
     */
    public JsonConfig(String fileName) {
        this.fileName = fileName;
        jsonFile = new File(Loader.instance().getConfigDir(), fileName);
        try {
            FileReader reader = new FileReader(jsonFile);
            mainJson = new JsonParser().parse(reader).getAsJsonObject();
        } catch (Exception e) {
            FMLLog.bigWarning("Can't load json mod config!");
            e.printStackTrace();
        }
    }

    public JsonObject getJSON() {
        return mainJson;
    }
}