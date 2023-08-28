package me.bryang.backloc.storage;

import java.util.Collection;

public interface Repository<T>{

    void create(T t);

    T findById(String id);

    boolean exists(String id);

    void deleteById(String id);

    Collection<T> findAll();
}
