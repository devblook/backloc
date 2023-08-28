package me.bryang.backloc.storage;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import me.bryang.backloc.storage.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GsonTypeAdapter extends TypeAdapter<Location> {


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

        jsonWriter.endObject();
    }

    @Override
    public Location read(JsonReader in) throws IOException {

        in.beginObject();

        String world = in.nextString();

        double x = in.nextDouble();
        double y = in.nextDouble();
        double z = in.nextDouble();

        return new Location(Bukkit.getWorld(world), x, y, z);

    }

}
