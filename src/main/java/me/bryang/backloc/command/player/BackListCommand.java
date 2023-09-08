package me.bryang.backloc.command.player;

import me.bryang.backloc.configuration.ConfigurationContainer;
import me.bryang.backloc.configuration.type.MessageSection;
import me.bryang.backloc.message.MessageManager;
import me.bryang.backloc.storage.Repository;
import me.bryang.backloc.storage.user.User;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import team.unnamed.inject.InjectAll;

import javax.inject.Singleton;
import java.util.List;

@InjectAll
@Singleton
public class BackListCommand implements CommandClass {

    private ConfigurationContainer<MessageSection> messagesFile;

    private Repository<User> userRepository;

    private MessageManager messageManager;


    @Command(
            names = {"backlist", "backl"},
            desc = "Command to see the list of your backs")
    public void execute(@Sender Player sender) {

        MessageSection.Plugin pluginSection = messagesFile.get().plugin;

        User user = userRepository.findById(sender.getUniqueId().toString());
        List<Location> locations = user.locations();

        if (locations.isEmpty()){
            messageManager.sendMessage(sender, messagesFile.get().error.noDeathPoints);
            return;
        }

        List<String> backListFormat = pluginSection.backList.commandMessage;
        String backCoordFormat = pluginSection.backList.backCoordsFormat;
        String lastFormat = pluginSection.backList.lastIdFormat;


        backListFormat.forEach(backLine -> {

            if (backLine.contains("<back_format>")) {

                for (int id = 0; id < locations.size(); id++) {

                    String locationLine = backLine;
                    Location location = locations.get(id);

                    String locationPath = backCoordFormat
                            .replace("<x>", String.valueOf(location.getBlockX()))
                            .replace("<y>", String.valueOf(location.getBlockY()))
                            .replace("<z>", String.valueOf(location.getBlockZ()));

                    if (id == 0){
                        locationLine = locationLine
                                .replace("<last_id>", lastFormat);
                    }else{
                        locationLine = locationLine
                                .replace("<last_id>", "");
                    }

                    messageManager.sendMessage(sender, locationLine,
                            Placeholder.unparsed("back_format", ""),
                            Placeholder.parsed("back_id", String.valueOf(id)),
                            Placeholder.unparsed("back_coords", locationPath));
                }
                return;
            }

            messageManager.sendMessage(sender, backLine,
                    Placeholder.unparsed("back_length", String.valueOf(locations.size())));

        });
    }

}