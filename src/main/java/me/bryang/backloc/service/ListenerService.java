package me.bryang.backloc.service;

import me.bryang.backloc.BackLoc;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import team.unnamed.inject.InjectAll;

import javax.inject.Named;
import java.util.Set;


@InjectAll
public class ListenerService implements Service {

    private BackLoc plugin;

    @Named("listener-list")
    private Set<Listener> listeners;


    @Override
    public void init() {

        PluginManager pluginManager = Bukkit.getPluginManager();
        listeners.forEach(listener -> pluginManager.registerEvents(listener, plugin));
    }

    @Override
    public void stop() {

    }
}
