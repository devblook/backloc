package me.bryang.backloc.storage.gson.codec;

import com.google.gson.JsonObject;
import me.bryang.backloc.storage.gson.adapter.LocalTypeAdapter;

import java.util.LinkedList;

public class JsonReader {

    private final JsonObject jsonObject;

    public JsonReader(JsonObject jsonObject){
        this.jsonObject = jsonObject;

    }


    public String getString(String key){
        return jsonObject
                .get(key)
                .getAsString();
    }


    public int getInteger(String key){
        return jsonObject
                .get(key)
                .getAsInt();
    }

    public double getDouble(String key){
        return jsonObject
                .get(key)
                .getAsDouble();
    }

    public float getFloat(String key){
        return jsonObject
                .get(key)
                .getAsFloat();
    }

    public boolean getBoolean(String key){
        return jsonObject
                .get(key)
                .getAsBoolean();
    }

    public <T> LinkedList<T> getList(String key, LocalTypeAdapter<T> localTypeAdapter){

        LinkedList<T> list = new LinkedList<>();

        jsonObject
                .getAsJsonArray(key)
                .forEach(jsonElement -> {

                    JsonObject valueObject = jsonElement.getAsJsonObject();
                    T clazz = localTypeAdapter.deserialize(new JsonReader(valueObject), valueObject);
                    list.add(clazz);

                });

        return list;
    }
}
