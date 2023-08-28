package me.bryang.backloc.module.submodules;

import me.bryang.backloc.command.BackCommand;
import me.bryang.backloc.command.BackListCommand;
import me.bryang.backloc.command.PluginCommand;
import me.fixeddev.commandflow.annotated.CommandClass;
import team.unnamed.inject.AbstractModule;

public class CommandModule extends AbstractModule {

    @Override
    protected void configure() {
        multibind(CommandClass.class)
                .named("command-list")
                .asSet()
                .to(BackCommand.class)
                .to(BackListCommand.class)
                .to(PluginCommand.class);
    }
}
