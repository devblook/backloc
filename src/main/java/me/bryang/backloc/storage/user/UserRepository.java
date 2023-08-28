package me.bryang.backloc.storage.user;

import me.bryang.backloc.storage.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserRepository implements Repository<User> {

    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public void create(User user) {
        userMap.put(user.uniqueId(), user);
    }

    @Override
    public User findById(String id) {
        return userMap.get(id);
    }

    @Override
    public boolean exists(String id) {
        return userMap.containsKey(id);
    }

    @Override
    public void deleteById(String id) {
        userMap.remove(id);
    }


    @Override
    public Collection<User> findAll() {
        return userMap.values();
    }
}
