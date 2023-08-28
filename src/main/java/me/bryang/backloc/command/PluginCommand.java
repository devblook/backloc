package me.bryang.backloc.command;

import me.bryang.backloc.configuration.ConfigurationContainer;
import me.bryang.backloc.configuration.type.ConfigSection;
import me.bryang.backloc.configuration.type.MessageSection;
import me.bryang.backloc.message.MessageManager;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;
import team.unnamed.inject.InjectAll;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@InjectAll
@Singleton
@Command(
        names = {"backloc", "bloc"},
        desc = "Plugin command")
public class PluginCommand implements CommandClass {

    private ConfigurationContainer<ConfigSection> configFile;
    private ConfigurationContainer<MessageSection> messagesFile;

    private MessageManager messageManager;

    @Command(names = {"", "help"})
    public void executeMainSubCommand(@Sender Player sender) {
        messageManager.sendMessage(sender,
                "<gold>[BackLoc] <dark_grey>| <white>Help command.",
                "<dark_grey>- <white>/clab reload <dark_grey>: <white>Reload the plugin.",
                "<dark_grey>- <white>/clab about <dark_grey>: <white>About the plugin.");
    }
    @Command(names = "reload", permission = "backloc.admin")
    public void executeReloadSubCommand(@Sender Player sender) {

        CompletableFuture.runAsync(() -> {

            try {
                configFile.reload();
                messagesFile.reload();

            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }

            }, Executors.newSingleThreadExecutor())
                .thenRun(() ->
                        messageManager.sendMessage(sender,
                                "<gold>[BackLoc] <dark_grey>| <white>Plugin reloaded!"))
                .exceptionally(throwable -> {
                    messageManager.sendMessage(sender,
                            "<gold>[BackLoc] <dark_grey>| <white>There was a error in reloading the configuration",
                            "Cause: " + throwable.getMessage());
                    return null;
                });
    }

    @Command(names = "about")
    public void executeAboutSubCommand(@Sender Player sender){
        messageManager.sendMessage(sender,
                "<white>Plugin created by <yellow>BryanG",
                "<white>Version: <green>1.0",
                "Source: <aqua><click:open_url:'https://github.com/devblook/backloc'><hover:show_text:'Click here!'>[GitHub]</hover></click>");
    }


}