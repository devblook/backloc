package me.bryang.backloc.message;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;

import javax.inject.Singleton;

@Singleton
public class MessageManager {

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public void sendMessage(Player sender, String message){
        sender.sendMessage(miniMessage.deserialize(message));
    }

    public void sendMessage(Player sender, String... messages){

        for (String message : messages) {
            sender.sendMessage(miniMessage.deserialize(message));
        }
    }

    public void sendMessage(Player sender, String message, TagResolver... tagResolver){
        sender.sendMessage(miniMessage.deserialize(message, tagResolver));
    }
}
