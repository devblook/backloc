package me.bryang.backloc.storage.gson.codec.manager;

import com.google.gson.JsonObject;
import me.bryang.backloc.storage.gson.adapter.LocalTypeAdapter;
import me.bryang.backloc.storage.gson.adapter.LocationTypeAdapter;
import me.bryang.backloc.storage.gson.codec.JsonReader;
import me.bryang.backloc.storage.user.User;
import me.bryang.backloc.storage.user.UserBuilder;
import org.bukkit.Location;

import javax.inject.Singleton;

@Singleton
public class JsonDeserializer {

    private final LocalTypeAdapter<Location> locationTypeAdapter;

    public JsonDeserializer(){
        this.locationTypeAdapter = new LocationTypeAdapter();
    }

    public User deserialize(JsonObject jsonObject){

        JsonReader jsonReader = new JsonReader(jsonObject);
        return new UserBuilder()
                .uniqueId(jsonReader.getString("unique_id"))
                .locations(jsonReader.getList("locations", locationTypeAdapter))
                .build();
    }
}
