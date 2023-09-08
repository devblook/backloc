package me.bryang.backloc.storage.user;

import org.bukkit.Location;

import java.util.LinkedList;

public class UserBuilder {

    private String uniqueId;
    private LinkedList<Location> locations;

    public UserBuilder uniqueId(String uniqueId){
        this.uniqueId = uniqueId;
        return this;
    }

    public UserBuilder locations(LinkedList<Location> locations){
        this.locations = locations;
        return this;
    }

    public User build(){
        return new User(uniqueId, locations);
    }

}
