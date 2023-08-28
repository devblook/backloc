package me.bryang.backloc.storage.user;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String uniqueId;
    private final List<Location> locations = new ArrayList<>();

    public User(String uniqueId){
        this.uniqueId = uniqueId;
    }

    public String uniqueId() {
        return uniqueId;
    }

    public List<Location> locations() {
        return locations;
    }

}
