package me.bryang.backloc.service;

import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

public class CommandService implements Service{

    @Inject @Named("command-list")
    private Set<CommandClass> commandClasses;

    @Override
    public void init() {

        BukkitCommandManager bukkitCommandManager = new BukkitCommandManager("backloc");

        PartInjector partInjector = PartInjector.create();

        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder commandBuilder = new AnnotatedCommandTreeBuilderImpl(partInjector);

        commandClasses.forEach(
                commandClass -> bukkitCommandManager.registerCommands(commandBuilder.fromClass(commandClass)));

    }

    @Override
    public void stop() {

    }
}
