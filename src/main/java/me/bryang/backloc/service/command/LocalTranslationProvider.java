package me.bryang.backloc.service.command;

import me.bryang.backloc.configuration.ConfigurationContainer;
import me.bryang.backloc.configuration.type.MessageSection;
import me.fixeddev.commandflow.Namespace;
import me.fixeddev.commandflow.translator.TranslationProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LocalTranslationProvider implements TranslationProvider {

    @Inject
    private ConfigurationContainer<MessageSection> messagesFile;

    @Override
    public String getTranslation(Namespace namespace, String key) {
        MessageSection.Error errorSection = messagesFile.get().error;

        return switch (key){

            case "sender.only-player" -> errorSection.noConsole;
            case "command.no-permission" -> errorSection.noPermission;
            case "invalid.integer" -> errorSection.unknownNumber;

            default -> "[!] If you see this, please contact to the developer. Key: " + key;
        };

    }
}
