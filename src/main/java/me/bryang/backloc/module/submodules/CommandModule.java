package me.bryang.backloc.module.submodules;

import me.bryang.backloc.command.player.BackCommand;
import me.bryang.backloc.command.player.BackListCommand;
import me.bryang.backloc.command.player.PluginCommand;
import me.fixeddev.commandflow.annotated.CommandClass;
import team.unnamed.inject.AbstractModule;

public class CommandModule extends AbstractModule {

    @Override
    protected void configure() {
        multibind(CommandClass.class)
                .named("command-list")
                .asList()
                .to(BackCommand.class)
                .to(BackListCommand.class)
                .to(PluginCommand.class);
    }
}
