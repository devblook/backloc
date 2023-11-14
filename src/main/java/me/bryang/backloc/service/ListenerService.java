package me.bryang.backloc.service;

import me.bryang.backloc.BackLocPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import team.unnamed.inject.InjectAll;

import javax.inject.Named;
import java.util.List;


@InjectAll
public class ListenerService implements Service {

    private BackLocPlugin plugin;

    @Named("listener-list")
    private List<Listener> listeners;

    @Override
    public void init() {

        PluginManager pluginManager = Bukkit.getPluginManager();
        listeners.forEach(listener -> pluginManager.registerEvents(listener, plugin));
    }

}
