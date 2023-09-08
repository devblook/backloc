package me.bryang.backloc.storage.gson.codec;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.bryang.backloc.storage.gson.adapter.LocalTypeAdapter;

import java.util.List;

public class JsonWriter {

    private final JsonObject jsonObject;

    public JsonWriter() {
        this.jsonObject = new JsonObject();
    }

    public JsonWriter writePrimitive(String key, String value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public JsonWriter writePrimitive(String key, Number value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public JsonWriter writePrimitive(String key, Boolean value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public <T> JsonWriter writeList(String key, List<T> list, LocalTypeAdapter<T> localTypeAdapter){

        JsonArray jsonArray = new JsonArray();

        for (T clazz : list){

            JsonObject jsonObject = localTypeAdapter.serialize(new JsonWriter(), clazz);
            jsonArray.add(jsonObject);
        }

        jsonObject.add(key, jsonArray);
        return this;
    }

    public JsonObject build(){
        return jsonObject;
    }



}
