package me.bryang.backloc.command;

import me.bryang.backloc.configuration.ConfigurationContainer;
import me.bryang.backloc.configuration.type.MessageSection;
import me.bryang.backloc.message.MessageManager;
import me.bryang.backloc.storage.Repository;
import me.bryang.backloc.storage.user.User;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import team.unnamed.inject.InjectAll;

import javax.inject.Singleton;
import java.util.List;

@InjectAll
@Singleton
public class BackCommand implements CommandClass {

    private ConfigurationContainer<MessageSection> messageFile;

    private Repository<User> userRepository;

    private MessageManager messageManager;

    @Command(
            names = "back",
            desc = "Back command.")
    public void execute(@Sender Player sender, @OptArg("-1") int id){

        MessageSection messageSection = messageFile.get();

        User user = userRepository.findById(sender.getUniqueId().toString());
        List<Location> locations = user.locations();

        if (locations.isEmpty()){
            messageManager.sendMessage(sender, messageSection.error.noBacks);
            return;
        }

        Location location;

        if (id == -1){
            location = locations.get(locations.size() -1);
        } else {

            try{
                location = locations.get(id);
            }catch (IndexOutOfBoundsException exception) {
                messageManager.sendMessage(sender, messageSection.error.unknownId,
                        Placeholder.unparsed("id", String.valueOf(id)));
                return;
            }
        }

        sender.teleport(location);
        messageManager.sendMessage(sender, messageFile.get().plugin.teleportMessage);
    }

}
