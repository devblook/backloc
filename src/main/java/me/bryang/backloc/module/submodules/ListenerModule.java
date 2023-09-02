package me.bryang.backloc.module.submodules;

import me.bryang.backloc.listener.PlayerConnectListener;
import me.bryang.backloc.listener.PlayerDeathListener;
import org.bukkit.event.Listener;
import team.unnamed.inject.AbstractModule;

public class ListenerModule extends AbstractModule {

    @Override
    protected void configure() {
        multibind(Listener.class)
                .named("listener-list")
                .asList()
                .to(PlayerConnectListener.class)
                .to(PlayerDeathListener.class);
    }
}
