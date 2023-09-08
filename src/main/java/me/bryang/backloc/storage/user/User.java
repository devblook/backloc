package me.bryang.backloc.storage.user;

import org.bukkit.Location;

import java.util.LinkedList;

public class User {

    private final String uniqueId;
    private LinkedList<Location> locations = new LinkedList<>();

    public User(String uniqueId){
        this.uniqueId = uniqueId;
    }

    protected User(String uniqueId, LinkedList<Location> locations){
        this.uniqueId = uniqueId;
        this.locations = locations;
    }

    public String uniqueId() {
        return uniqueId;
    }

    public LinkedList<Location> locations() {
        return locations;
    }

}
