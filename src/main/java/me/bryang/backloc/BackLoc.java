package me.bryang.backloc;

import me.bryang.backloc.module.MainModule;
import me.bryang.backloc.service.Service;
import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.inject.InjectAll;
import team.unnamed.inject.Injector;

import java.util.List;
import java.util.logging.Logger;

@InjectAll
public class BackLoc extends JavaPlugin {

    private List<Service> services;
    private Logger logger;

    @Override
    public void onEnable() {

        Injector injector = Injector.create(new MainModule(this));
        injector.injectMembers(this);

        services.forEach(Service::init);
        logger.info("Plugin loaded.");
    }

    @Override
    public void onDisable() {

        services.forEach(Service::stop);
        logger.info("Thanks for using my plugin!");
    }
}
