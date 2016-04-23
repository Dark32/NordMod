package ru.nord_core.common.utils.metal;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import ru.nord_core.NordCore;

import java.lang.reflect.Type;
import java.util.Map;

public class BindOre {
    private static BindOre instance;
    private Type type = new TypeToken<Map<Integer, Map<String, ?>>>() {
    }.getType();

    public static BindOre INSTANCE() {
        if (instance == null) {
            instance = new BindOre();
        }
        return instance;
    }

    public EnumOre getDrop(EnumOre ore) {
        JsonElement jsone = getFlag(ore, "nuggents");
        return jsone == null || !jsone.getAsBoolean() ? ore : null;
    }

    public EnumOre getNuggents(EnumOre ore) {
        JsonElement jsone = getFlag(ore, "nuggents");
        return jsone != null && jsone.getAsBoolean() ? ore : null;
    }

    public String getName(EnumOre ore) {
        JsonElement jsone = getFlag(ore, "name");
        return (jsone != null) ? jsone.getAsString() : "";

    }

    public Boolean getVanila(EnumOre ore) {
        JsonElement jsone = getFlag(ore, "vanila");
        return jsone != null && jsone.getAsBoolean();
    }
    public Boolean getCrystal(EnumOre ore) {
        JsonElement jsone = getFlag(ore, "crystal");
        return jsone != null && jsone.getAsBoolean();
    }

    public Map<Integer, Map<String, ?>> getCentrifuge(EnumOre ore) {
        return getProcessing(ore, "centrifuge");
    }

    public Map<Integer, Map<String, ?>> getWashing(EnumOre ore) {
        return getProcessing(ore, "washing");
    }

    public Map<Integer, Map<String, ?>> getSeparator(EnumOre ore) {
        return getProcessing(ore, "separator");
    }

    public Map<Integer, Map<String, ?>> getElectrolyzer(EnumOre ore) {
        return getProcessing(ore, "electrolyzer");
    }

    private JsonObject getOre(EnumOre ore) {
        JsonObject json = NordCore.getJSONConfig().getJSON();
        json = json.getAsJsonObject("ore");
        json = json.getAsJsonObject(ore.name());
        return json;
    }

    public Map<Integer, Map<String, ?>> getProcessing(EnumOre ore, String other) {
        JsonObject json = getOre(ore);
        if (json == null) {
            return null;
        }
        json = json.getAsJsonObject(other);
        if (json != null) {
            return new Gson().fromJson(json, type);
        } else {
            return null;
        }
    }

    public JsonElement getFlag(EnumOre ore, String flag) {
        JsonObject json = getOre(ore);
        if (json == null) {
            return null;
        }
        JsonElement jsone = json.get(flag);
        return json.get(flag);
    }
}
