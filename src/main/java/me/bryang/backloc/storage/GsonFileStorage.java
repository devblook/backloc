package me.bryang.backloc.storage;

import com.google.gson.Gson;
import me.bryang.backloc.storage.user.User;
import me.bryang.backloc.storage.user.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.nio.file.Files;
import java.nio.file.Path;

@Singleton
public class JsonStorage {

    @Inject
    private UserRepository userRepository;

    @Inject @Named("plugin-path")
    private Path path;

    private Path usersFolder;

    private Gson localGson;

    public void init(){

        localGson = new Gson();
        usersFolder = path.resolve("users");

    }

    public void deserializeAndPut(String id){

        Path userPath = usersFolder.resolve(id);

        String userFileLines;
        try {
            userFileLines = Files.readString(userPath);
        }catch (Exception exception){
            exception.fillInStackTrace();
            return;
        }

        User user = localGson.fromJson(userFileLines, User.class);
        
        userRepository.
    }

    public void serializeAndSave(User user){

        String serializedUserPath = localGson.toJson(user);

        Path userPath = usersFolder.resolve(user.uniqueId());

        if (!Files.exists(userPath)) {

            try {
                Files.createFile(userPath);
            } catch (Exception exception) {
                exception.fillInStackTrace();
            }
        }

        try {
            Files.writeString(usersFolder.resolve(user.uniqueId()), serializedUserPath);
        }catch (Exception exception){
            exception.fillInStackTrace();
        }

    }

    public void stop(){

    }
}
