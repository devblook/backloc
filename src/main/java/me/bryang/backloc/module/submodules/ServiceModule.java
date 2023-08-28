package me.bryang.backloc.module.submodules;

import me.bryang.backloc.service.ListenerService;
import me.bryang.backloc.service.Service;
import me.bryang.backloc.service.StorageService;
import me.bryang.backloc.service.command.CommandService;
import team.unnamed.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        multibind(Service.class)
                .asSet()
                .to(StorageService.class)
                .to(CommandService.class)
                .to(ListenerService.class);
    }
}
