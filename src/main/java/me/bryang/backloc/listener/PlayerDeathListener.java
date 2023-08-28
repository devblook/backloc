package me.bryang.backloc.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import javax.inject.Singleton;

@Singleton
public class DeathListener implements Listener {


    @EventHandler
    public void deathEvent(PlayerDeathEvent event){
    }
}
