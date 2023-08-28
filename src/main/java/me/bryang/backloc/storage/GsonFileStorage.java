package me.bryang.backloc.storage;

import com.google.gson.Gson;
import me.bryang.backloc.storage.adapter.LocationTypeAdapter;
import me.bryang.backloc.storage.user.User;
import org.bukkit.Location;
import team.unnamed.inject.InjectAll;
import team.unnamed.inject.InjectIgnore;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.logging.Logger;

@InjectAll
@Singleton
public class GsonFileStorage {

    private Repository<User> userRepository;

    @Named("plugin-path")
    private Path path;

    private Logger logger;

    @InjectIgnore
    private Path usersFolder;

    @InjectIgnore
    private Gson localGson;

    public void init(){

        localGson = new Gson()
                .newBuilder()
                .registerTypeAdapter(Location.class, new LocationTypeAdapter())
                .create();

        usersFolder = path.resolve("users");

        logger.info("Loaded storage system.");
    }

    public boolean exists(String id){
        return Files.exists(usersFolder.resolve(id + ".json"));
    }

    public void deserializeAndPut(String id){

        Path userPath = usersFolder.resolve(id + ".json");
        String userFileLines;

        try {
            userFileLines = Files.readString(userPath);
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }

        User user = localGson.fromJson(userFileLines, User.class);
        userRepository.create(user);
    }

    public void serializeAndSave(User user){

        String serializedUserPath = localGson.toJson(user, User.class);
        Path userPath = usersFolder.resolve(user.uniqueId() + ".json");

        try {
            if (!Files.exists(usersFolder)) {
                Files.createDirectory(usersFolder);
            }

            Files.writeString(userPath, serializedUserPath);
        }catch (IOException exception){
            throw new UncheckedIOException(exception);
        }
    }

    public void stop(){

        Collection<User> usersValues = userRepository.findAll();
        usersValues.forEach(this::serializeAndSave);

    }
}
