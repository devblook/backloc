package me.bryang.backloc.storage.gson.adapter;


import com.google.gson.JsonObject;
import me.bryang.backloc.storage.gson.codec.JsonReader;
import me.bryang.backloc.storage.gson.codec.JsonWriter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationTypeAdapter implements LocalTypeAdapter<Location> {


    @Override
    public JsonObject serialize(JsonWriter jsonWriter, Location location) {
        return jsonWriter
                .object("world", location.getWorld().getName())
                .object("x", location.getX())
                .object("y", location.getY())
                .object("z", location.getZ())
                .object("pitch", location.getPitch())
                .object("yaw", location.getYaw())
                .build();
    }

    @Override
    public Location deserialize(JsonReader jsonReader, JsonObject jsonObject) {

        String world = jsonReader.getString("world");
        double x = jsonReader.getDouble("x");
        double y = jsonReader.getDouble("y");
        double z = jsonReader.getDouble("z");
        float pitch = jsonReader.getFloat("pitch");
        float yaw = jsonReader.getFloat("yaw");

        return new Location(Bukkit.getWorld(world), x, y, z, pitch, yaw);
    }


}
