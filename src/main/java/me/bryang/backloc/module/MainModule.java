package me.bryang.backloc.module;

import me.bryang.backloc.BackLoc;
import me.bryang.backloc.module.submodules.*;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.Provides;

import javax.inject.Named;
import javax.inject.Singleton;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class MainModule extends AbstractModule {

    private final BackLoc plugin;

    public MainModule(BackLoc plugin){
        this.plugin = plugin;
    }

    @Provides @Singleton
    public Logger logger(){
        return plugin.getLogger();
    }

    @Provides @Singleton @Named("async-thread")
    public ExecutorService asyncThreadExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    @Override
    protected void configure() {

        bind(BackLoc.class)
                .toInstance(plugin);

        bind(Path.class)
                .named("plugin-path")
                .toInstance(plugin.getDataFolder().toPath());

        install(new StorageModule(),
                new ConfigurationModule(),
                new CommandModule(),
                new ListenerModule(),
                new ServiceModule());
    }
}
