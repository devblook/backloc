package me.bryang.backloc.listener;

import me.bryang.backloc.storage.GsonFileStorage;
import me.bryang.backloc.storage.Repository;
import me.bryang.backloc.storage.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import team.unnamed.inject.InjectAll;

import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@InjectAll
@Singleton
public class PlayerConnectListener implements Listener {

    private Repository<User> userRepository;
    private GsonFileStorage gsonFileStorage;

    private Logger logger;

    @EventHandler
    public void joinEvent(AsyncPlayerPreLoginEvent event){

        String senderUniqueId = event.getUniqueId().toString();

        if (!userRepository.exists(senderUniqueId)){
            if (gsonFileStorage.exists(senderUniqueId)){
                gsonFileStorage.deserializeAndPut(senderUniqueId);
            }else{
                userRepository.create(new User(senderUniqueId));
            }
        }
    }

    @EventHandler
    public void quitEvent(PlayerQuitEvent event){

        String senderUniqueId = event.getPlayer().getUniqueId().toString();

        CompletableFuture.runAsync(() -> gsonFileStorage.serializeAndSave(userRepository.findById(senderUniqueId)),
                        Executors.newSingleThreadExecutor())
                .exceptionally(throwable -> {

                    logger.info("There was a error to save " + event.getPlayer().getName() + " data.");
                    logger.info("Message: " + throwable.getMessage());
                    return null;
                });
    }
}
