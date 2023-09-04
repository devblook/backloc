package me.bryang.backloc.storage.user;

import org.bukkit.Location;

import java.util.List;

public class UserBuilder {

    private String uniqueId;
    private List<Location> locations;

    public UserBuilder uniqueId(String uniqueId){
        this.uniqueId = uniqueId;
        return this;
    }

    public UserBuilder locations(List<Location> locations){
        this.locations = locations;
        return this;
    }

    public User build(){
        return new User(uniqueId, locations);
    }

}
