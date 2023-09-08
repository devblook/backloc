package me.bryang.backloc.storage.gson.codec.manager;

import com.google.gson.JsonObject;
import me.bryang.backloc.storage.gson.adapter.LocalTypeAdapter;
import me.bryang.backloc.storage.gson.adapter.LocationTypeAdapter;
import me.bryang.backloc.storage.gson.codec.JsonWriter;
import me.bryang.backloc.storage.user.User;
import org.bukkit.Location;

import javax.inject.Singleton;

@Singleton
public class JsonSerializer {

    private final LocalTypeAdapter<Location> locationTypeAdapter;

    public JsonSerializer(){
        this.locationTypeAdapter = new LocationTypeAdapter();
    }

    public JsonObject serialize(User user){
        return new JsonWriter()
                .writePrimitive("unique_id", user.uniqueId())
                .writeList("locations", user.locations(), locationTypeAdapter)
                .build();
    }
}
