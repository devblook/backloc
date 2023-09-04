package me.bryang.backloc.service;

import me.bryang.backloc.storage.gson.GsonFileStorage;

import javax.inject.Inject;

public class StorageService implements Service{

    @Inject
    private GsonFileStorage gsonFileStorage;


    @Override
    public void init() {
        gsonFileStorage.init();

    }

    @Override
    public void stop() {
        gsonFileStorage.stop();
    }
}
