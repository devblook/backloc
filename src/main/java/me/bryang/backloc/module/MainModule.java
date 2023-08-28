package me.bryang.backloc.module;

import me.bryang.backloc.BackLoc;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.Provides;

import javax.inject.Singleton;
import java.nio.file.Path;
import java.util.logging.Logger;

public class PluginModule extends AbstractModule {

    private final BackLoc plugin;

    public PluginModule(BackLoc plugin){
        this.plugin = plugin;
    }

    @Provides @Singleton
    public Logger logger(){
        return plugin.getLogger();
    }
    @Override
    protected void configure() {

        bind(BackLoc.class)
                .toInstance(plugin);

        bind(Path.class)
                .named("plugin-path")
                .toInstance(plugin.getDataFolder().toPath());

        install(
                new StorageModule(),
                new ConfigurationModule(),
                new CommandModule(),
                new ListenerModule(),
                new ServiceModule());
    }
}
