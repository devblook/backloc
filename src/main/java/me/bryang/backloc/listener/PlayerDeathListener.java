package me.bryang.backloc.listener;

import me.bryang.backloc.configuration.ConfigurationContainer;
import me.bryang.backloc.configuration.type.ConfigSection;
import me.bryang.backloc.configuration.type.MessageSection;
import me.bryang.backloc.message.MessageManager;
import me.bryang.backloc.storage.Repository;
import me.bryang.backloc.storage.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import team.unnamed.inject.InjectAll;

import javax.inject.Singleton;

@InjectAll
@Singleton
public class PlayerDeathListener implements Listener {

    private Repository<User> userRepository;

    private ConfigurationContainer<ConfigSection> configFile;
    private ConfigurationContainer<MessageSection> messagesFile;

    private MessageManager messageManager;

    @EventHandler
    public void deathEvent(PlayerDeathEvent event){

        Player sender = event.getPlayer();

        User user = userRepository.findById(sender.getUniqueId().toString());

        if (user.locations().size() >= configFile.get().plugin.maxBacks) {
            return;
        }

        user.locations().add(sender.getLocation());
        messageManager.sendMessage(sender, messagesFile.get().plugin.deathMessage);
    }
}
