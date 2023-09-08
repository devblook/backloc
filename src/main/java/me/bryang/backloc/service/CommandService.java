package me.bryang.backloc.service;

import me.bryang.backloc.command.LocalTranslationProvider;
import me.bryang.backloc.command.LocalTranslator;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import team.unnamed.inject.InjectAll;

import javax.inject.Named;
import java.util.List;

@InjectAll
public class CommandService implements Service {

    @Named("command-list")
    private List<CommandClass> commandClasses;

    private LocalTranslator translator;
    private LocalTranslationProvider localTranslationProvider;

    @Override
    public void init() {

        BukkitCommandManager bukkitCommandManager = new BukkitCommandManager("backloc");
        bukkitCommandManager.setTranslator(translator);
        bukkitCommandManager.getTranslator().setProvider(localTranslationProvider);

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