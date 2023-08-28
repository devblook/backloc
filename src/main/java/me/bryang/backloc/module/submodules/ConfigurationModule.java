package me.bryang.backloc.module;

import me.bryang.backloc.configuration.ConfigurationContainer;
import me.bryang.backloc.configuration.type.ConfigSection;
import me.bryang.backloc.configuration.type.MessageSection;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.Provides;

import javax.inject.Named;
import javax.inject.Singleton;
import java.nio.file.Path;

public class ConfigurationModule extends AbstractModule {

    @Provides @Singleton
    public ConfigurationContainer<ConfigSection> configContainer(@Named("plugin-path") Path path){
        return new ConfigurationContainer<>("settings", path, ConfigSection.class);
    }

    @Provides @Singleton
    public ConfigurationContainer<MessageSection> messageContainer(@Named("plugin-path") Path path){
        return new ConfigurationContainer<>("messages", path, MessageSection.class);
    }
}
