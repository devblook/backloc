package me.bryang.backloc.service;

public interface Service {

    void init();

    default void stop() {

    }
}
