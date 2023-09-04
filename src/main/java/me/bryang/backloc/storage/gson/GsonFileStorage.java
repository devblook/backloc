package me.bryang.backloc.storage.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import me.bryang.backloc.storage.Repository;
import me.bryang.backloc.storage.gson.codec.manager.JsonDeserializer;
import me.bryang.backloc.storage.gson.codec.manager.JsonSerializer;
import me.bryang.backloc.storage.user.User;
import team.unnamed.inject.InjectIgnore;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.logging.Logger;

@Singleton
public class GsonFileStorage {

    @Inject
    private Repository<User> userRepository;

    @Inject
    @Named("plugin-path")
    private Path path;

    @Inject
    private Logger logger;

    private Path usersFolder;

    private final JsonSerializer jsonSerializer;
    private final JsonDeserializer jsonDeserializer;


    public GsonFileStorage(){
        this.jsonSerializer = new JsonSerializer();
        this.jsonDeserializer = new JsonDeserializer();
    }

    public void init(){

        usersFolder = path.resolve("users");

        if (!Files.exists(usersFolder)) {
            try {
                Files.createDirectory(usersFolder);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        logger.info("Loaded storage system.");
    }

    public boolean exists(String id){
        return Files.exists(usersFolder.resolve(id + ".json"));
    }

    public User deserialize(String id) {

        Path userPath = usersFolder.resolve(id + ".json");

        try(JsonReader jsonReader = new JsonReader(Files.newBufferedReader(userPath))){

            JsonObject jsonObject = TypeAdapters.JSON_ELEMENT.read(jsonReader).getAsJsonObject();
            return jsonDeserializer.deserialize(jsonObject);
        }catch (IOException exception){
            throw new UncheckedIOException(exception);
        }

    }

    public void save(User user){

        Path userPath = usersFolder.resolve(user.uniqueId() + ".json");

        JsonObject jsonObject = jsonSerializer.serialize(user);

        try(JsonWriter jsonWriter = new JsonWriter(Files.newBufferedWriter(userPath))){

            jsonWriter.setIndent(" ");
            jsonWriter.setSerializeNulls(false);

            TypeAdapters.JSON_ELEMENT.write(jsonWriter, jsonObject);
        }catch (IOException exception){
            throw new UncheckedIOException(exception);
        }
    }

    public void stop(){

        Collection<User> usersValues = userRepository.findAll();
        usersValues.forEach(this::save);

    }
}
