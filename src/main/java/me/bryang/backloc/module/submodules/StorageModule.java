package me.bryang.backloc.module;

import me.bryang.backloc.storage.Repository;
import me.bryang.backloc.storage.user.User;
import me.bryang.backloc.storage.user.UserRepository;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.key.TypeReference;

public class StorageModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(new TypeReference<Repository<User>>(){})
                .toInstance(new UserRepository());
    }
}
