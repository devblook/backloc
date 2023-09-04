package me.bryang.backloc.storage.user;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String uniqueId;
    private List<Location> locations = new ArrayList<>();

    public User(String uniqueId){
        this.uniqueId = uniqueId;
    }

    protected User(String uniqueId, List<Location> locations){
        this.uniqueId = uniqueId;
        this.locations = locations;
    }

    public String uniqueId() {
        return uniqueId;
    }

    public List<Location> locations() {
        return locations;
    }

}
