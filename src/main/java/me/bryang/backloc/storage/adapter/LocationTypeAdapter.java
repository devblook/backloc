package me.bryang.backloc.storage.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.io.IOException;
import java.nio.channels.FileLock;

public class LocationTypeAdapter extends TypeAdapter<Location> {

    @Override
    public void write(JsonWriter jsonWriter, Location value) throws IOException {

        jsonWriter.beginObject();

        jsonWriter
                .name("world")
                .value(value.getWorld().getName());
        jsonWriter
                .name("x")
                .value(value.getX());
        jsonWriter
                .name("y")
                .value(value.getY());
        jsonWriter
                .name("z")
                .value(value.getZ());
        jsonWriter
                .name("pitch")
                .value(value.getPitch());
        jsonWriter
                .name("yaw")
                .value(value.getYaw());

        jsonWriter.endObject();
    }

    @Override
    public Location read(JsonReader jsonReader) throws IOException {

        jsonReader.beginObject();

        String world = "";

        double x = 0;
        double y = 0;
        double z = 0;

        float pitch = 0;
        float yaw = 0;

        while (jsonReader.hasNext()) {

            String key = jsonReader.nextName();

            switch (key){
                case "world" ->
                    world = jsonReader.nextString();
                case "x" ->
                    x = jsonReader.nextDouble();
                case "y" ->
                    y = jsonReader.nextDouble();
                case "z" ->
                    z = jsonReader.nextDouble();
                case "pitch" ->
                    pitch = (float) jsonReader.nextDouble();
                case "yaw" ->
                    yaw = (float) jsonReader.nextDouble();
            }
        }

        jsonReader.endObject();
        return new Location(Bukkit.getWorld(world), x, y, z, pitch, yaw);
    }

}
