package me.bryang.backloc.storage.gson.adapter;

import com.google.gson.JsonObject;
import me.bryang.backloc.storage.gson.codec.JsonReader;
import me.bryang.backloc.storage.gson.codec.JsonWriter;

public interface LocalTypeAdapter<T>{

    JsonObject serialize(JsonWriter jsonWriter, T t);

    T deserialize(JsonReader jsonReader,  JsonObject jsonObject);

}
