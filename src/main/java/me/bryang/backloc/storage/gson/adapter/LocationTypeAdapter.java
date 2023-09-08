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
                .writePrimitive("world", location.getWorld().getName())
                .writePrimitive("x", location.getBlockX())
                .writePrimitive("y", location.getBlockY())
                .writePrimitive("z", location.getBlockZ())
                .writePrimitive("pitch", location.getPitch())
                .writePrimitive("yaw", location.getYaw())
                .build();
    }

    @Override
    public Location deserialize(JsonReader jsonReader, JsonObject jsonObject) {

        String world = jsonReader.getString("world");
        double x = jsonReader.getInteger("x");
        double y = jsonReader.getInteger("y");
        double z = jsonReader.getInteger("z");
        float pitch = jsonReader.getFloat("pitch");
        float yaw = jsonReader.getFloat("yaw");

        return new Location(Bukkit.getWorld(world), x, y, z, pitch, yaw);
    }


}
