package ru.nord_core.common.utils.metal;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import ru.nord_core.NordCore;

import java.lang.reflect.Type;
import java.util.Map;

public class BindMetall {
    private static BindMetall instance;
    private Type type = new TypeToken<Map<Integer, Map<String, ?>>>() {
    }.getType();

    public static BindMetall INSTANCE() {
        if (instance == null) {
            instance = new BindMetall();
        }
        return instance;
    }

    private JsonObject getMetal(EnumMetal metal) {
        JsonObject json = NordCore.getJSONConfig().getJSON();
        json = json.getAsJsonObject("metal");
        json = json.getAsJsonObject(metal.name());
        return json;
    }

    public Map<Integer, Map<String, ?>> getProcessing(EnumMetal metal, String other) {
        JsonObject json = getMetal(metal);
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

    public JsonElement getFlag(EnumMetal metal, String flag) {
        JsonObject json = getMetal(metal);
        if (json == null) {
            return null;
        }
        JsonElement jsone = json.get(flag);
        return json.get(flag);
    }

    public Boolean getVanila(EnumMetal metal) {
        JsonElement jsone = getFlag(metal, "vanila");
        return jsone != null && jsone.getAsBoolean();
    }

    public Boolean getCleanable(EnumMetal metal) {
        JsonElement jsone = getFlag(metal, "cleanable");
        return jsone == null || jsone.getAsBoolean();
    }
}
