package me.bryang.backloc.storage.gson.codec.manager;

import com.google.gson.JsonObject;
import me.bryang.backloc.storage.gson.adapter.LocalTypeAdapter;
import me.bryang.backloc.storage.gson.adapter.LocationTypeAdapter;
import me.bryang.backloc.storage.gson.codec.JsonWriter;
import me.bryang.backloc.storage.user.User;
import org.bukkit.Location;

public class JsonSerializer {

    private final LocalTypeAdapter<Location> locationTypeAdapter;

    public JsonSerializer(){
        this.locationTypeAdapter = new LocationTypeAdapter();
    }

    public JsonObject serialize(User user){
        return new JsonWriter()
                .object("unique_id", user.uniqueId())
                .list("locations", user.locations(), locationTypeAdapter)
                .build();
    }
}
