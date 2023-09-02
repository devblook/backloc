package me.bryang.backloc.module.submodules;

import me.bryang.backloc.service.CommandService;
import me.bryang.backloc.service.ListenerService;
import me.bryang.backloc.service.Service;
import me.bryang.backloc.service.StorageService;
import team.unnamed.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        multibind(Service.class)
                .asList()
                .to(StorageService.class)
                .to(CommandService.class)
                .to(ListenerService.class);
    }
}
