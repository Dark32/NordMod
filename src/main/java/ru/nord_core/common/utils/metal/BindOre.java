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
        JsonObject json = getOre(ore);
        if (json == null) {
            return null;
        }
        JsonElement jsone = json.get("nuggents");
        if (jsone == null || !jsone.getAsBoolean()) {
            return ore;
        } else {
            return null;
        }
    }

    public EnumOre getNuggents(EnumOre ore) {
        JsonObject json = getOre(ore);
        if (json == null) {
            return null;
        }
        JsonElement jsone = json.get("nuggents");
        if (jsone != null && jsone.getAsBoolean()) {
            return ore;
        } else {
            return null;
        }
    }

    public String getName(EnumOre ore) {
        JsonObject json = getOre(ore);
        if (json == null) {
            return null;
        }
        JsonElement jsone = json.get("name");
        if (jsone != null) {
            return jsone.getAsString();
        } else {
            return "";
        }
    }
    public Boolean getVanila(EnumOre ore) {
        JsonObject json = getOre(ore);
        if (json == null) {
            return null;
        }
        JsonElement jsone = json.get("vanila");
        return jsone != null && jsone.getAsBoolean();
    }

    public Map<Integer, Map<String, ?>> getCentrifuge(EnumOre ore) {
        JsonObject json = getOre(ore);
        if (json == null) {
            return null;
        }
        json = json.getAsJsonObject("centrifuge");
        if (json != null) {
            return new Gson().fromJson(json, type);
        } else {
            return null;
        }
    }

    public Map<Integer, Map<String, ?>> getWashing(EnumOre ore) {
        JsonObject json = getOre(ore);
        if (json == null) {
            return null;
        }
        json = json.getAsJsonObject("washing");
        if (json != null) {
            return new Gson().fromJson(json, type);
        } else {
            return null;
        }
    }

    public Map<Integer, Map<String, ?>> getSeparator(EnumOre ore) {
        JsonObject json = getOre(ore);
        if (json == null) {
            return null;
        }
        json = json.getAsJsonObject("separator");
        if (json != null) {
            return new Gson().fromJson(json, type);
        } else {
            return null;
        }
    }

    public Map<Integer, Map<String, ?>> getElectrolyzer(EnumOre ore) {
        JsonObject json = getOre(ore);
        if (json == null) {
            return null;
        }
        json = json.getAsJsonObject("electrolyzer");
        if (json != null) {
            return new Gson().fromJson(json, type);
        } else {
            return null;
        }
    }

    private JsonObject getOre(EnumOre ore) {
        JsonObject json = NordCore.getJSONConfig().getJSON();
        json = json.getAsJsonObject("ore");
        json = json.getAsJsonObject(ore.name());
        return json;
    }

    public Map<Integer, Map<String, ?>> getOther(EnumOre ore, String other) {
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
}
