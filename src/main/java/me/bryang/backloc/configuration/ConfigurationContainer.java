package me.bryang.backloc.configuration;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;

public class ConfigurationContainer<T> {

    private HoconConfigurationLoader loader;
    private final Class<T> clazz;
    private AtomicReference<T> internClass;

    public ConfigurationContainer(String fileName, Path path, Class<T> clazz) {

        this.clazz = clazz;
        start(fileName, path);
    }

    public void start(String fileName, Path path) {

        loader = HoconConfigurationLoader
                .builder()
                .path(path.resolve(fileName + ".conf"))
                .defaultOptions(config ->
                        config.header("""
                            %s
                            """.replace("%s", fileName + ".conf")))

                .build();

        try {
            CommentedConfigurationNode node = loader.load();

            internClass = new AtomicReference<>(node.get(clazz));

            node.set(clazz, internClass.get());
            loader.save(node);

        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }

    public T get() {
        return internClass.get();
    }

    public void reload() throws IOException {

        CommentedConfigurationNode node = loader.load();

        T newClass = node.get(clazz);
        node.set(clazz, newClass);

        internClass.set(newClass);
        loader.save(node);



    }

}
