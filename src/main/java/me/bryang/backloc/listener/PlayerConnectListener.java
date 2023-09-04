package me.bryang.backloc.listener;

import me.bryang.backloc.storage.Repository;
import me.bryang.backloc.storage.gson.GsonFileStorage;
import me.bryang.backloc.storage.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import team.unnamed.inject.InjectAll;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.logging.Logger;

@InjectAll
@Singleton
public class PlayerConnectListener implements Listener {

    private Repository<User> userRepository;
    private GsonFileStorage gsonFileStorage;

    private Logger logger;

    @Named("async-thread")
    private ExecutorService executorService;

    @EventHandler
    public void joinEvent(AsyncPlayerPreLoginEvent event){

        String senderUniqueId = event.getUniqueId().toString();

        if (!userRepository.exists(senderUniqueId)){

            User user = gsonFileStorage.exists(senderUniqueId)
                    ? gsonFileStorage.deserialize(senderUniqueId)
                    : new User(senderUniqueId);

            userRepository.create(user);

        }
    }

    @EventHandler
    public void quitEvent(PlayerQuitEvent event){

        Player sender = event.getPlayer();

        String senderName = sender.getName();
        String senderUniqueId = sender.getUniqueId().toString();

        CompletableFuture
                .runAsync(() -> gsonFileStorage.save(userRepository.findById(senderUniqueId)),
                        executorService)
                .exceptionally(throwable -> {

                    logger.info("There was a error to save " + senderName + " data.");
                    logger.info("Message: " + throwable.getMessage());
                    return null;
                });
    }
}
